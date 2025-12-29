import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SmartJournalApp {
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;
    private static JournalEntry[] userJournals = new JournalEntry[100]; // Max 100 journals
    private static int journalCount = 0;
    
    public static void main(String[] args) {
        System.out.println("=== Welcome to Smart Journal App ===\n");
        
        // Main loop
        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }
    }
    
    // Login/Registration Menu
    private static void showLoginMenu() {
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        
        String choice = scanner.nextLine();
        
        switch (choice) {
            case "1":
                login();
                break;
            case "2":
                register();
                break;
            case "3":
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please try again.\n");
        }
    }
    
    // Login function
    private static void login() {
        System.out.print("\nEnter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        User[] users = FileHandler.loadUsers();
        for (int i = 0; i < users.length; i++) {
            if (users[i].getEmail().equals(email) && users[i].getPassword().equals(password)) {
                currentUser = users[i];
                JournalEntry[] loadedJournals = FileHandler.loadJournals(email);
                journalCount = loadedJournals.length;
                for (int j = 0; j < journalCount; j++) {
                    userJournals[j] = loadedJournals[j];
                }
                System.out.println("Login successful!\n");
                return;
            }
        }
        
        System.out.println("Invalid email or password.\n");
    }
    
    // Register function
    private static void register() {
        System.out.print("\nEnter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter display name: ");
        String displayName = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        // Check if email already exists
        User[] users = FileHandler.loadUsers();
        for (int i = 0; i < users.length; i++) {
            if (users[i].getEmail().equals(email)) {
                System.out.println("Email already registered.\n");
                return;
            }
        }
        
        User newUser = new User(email, displayName, password);
        FileHandler.saveUser(newUser);
        System.out.println("Registration successful! Please login.\n");
    }
    
    // Main Menu after login
    private static void showMainMenu() {
        // Show welcome message with greeting
        String greeting = getGreeting();
        System.out.println("\n=== " + greeting + ", " + currentUser.getDisplayName() + "! ===");
        
        System.out.println("\n1. Create, Edit & View Journals");
        System.out.println("2. View Weekly Mood Summary");
        System.out.println("3. Logout");
        System.out.print("Choose an option: ");
        
        String choice = scanner.nextLine();
        
        switch (choice) {
            case "1":
                showJournalPage();
                break;
            case "2":
                showWeeklySummary();
                break;
            case "3":
                FileHandler.saveJournals(currentUser.getEmail(), userJournals, journalCount);
                currentUser = null;
                journalCount = 0;
                System.out.println("Logged out successfully.\n");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
    
    // Get greeting based on time
    private static String getGreeting() {
        LocalTime now = LocalTime.now();
        int hour = now.getHour();
        
        if (hour >= 0 && hour < 12) {
            return "Good Morning";
        } else if (hour >= 12 && hour < 17) {
            return "Good Afternoon";
        } else {
            return "Good Evening";
        }
    }
    
    // Edit today's journal
private static void editJournal(String date) {
    for (int i = 0; i < journalCount; i++) {
        if (userJournals[i].getDate().equals(date)) {

            System.out.print("Edit your journal entry for " + date + ":\n> ");
            String newContent = scanner.nextLine();

            // Update content
            userJournals[i].setContent(newContent);

            // Re-analyze mood
            System.out.println("Re-analyzing sentiment...");
            String newMood = SentimentAPI.analyzeSentiment(newContent);
            userJournals[i].setMood(newMood);

            // Save changes
            FileHandler.saveJournals(
                currentUser.getEmail(),
                userJournals,
                journalCount
            );

            System.out.println("Journal updated successfully!");
            return;
        }
    }

    System.out.println("Journal not found.");
}


    // Journal Page
    private static void showJournalPage() {
        System.out.println("\n=== Journals Page ===");
        System.out.println("=== Journal Dates ===");
        
        // Get list of unique dates
        String[] dates = new String[100];
        int dateCount = 0;
        
        // Add existing journal dates
        for (int i = 0; i < journalCount; i++) {
            String date = userJournals[i].getDate();
            boolean exists = false;
            for (int j = 0; j < dateCount; j++) {
                if (dates[j].equals(date)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                dates[dateCount] = date;
                dateCount++;
            }
        }
        
        // Add today's date if not present
        String today = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
        boolean todayExists = false;
        for (int i = 0; i < dateCount; i++) {
            if (dates[i].equals(today)) {
                todayExists = true;
                break;
            }
        }
        if (!todayExists) {
            dates[dateCount] = today;
            dateCount++;
        }
        
        // Sort dates (simple bubble sort)
        for (int i = 0; i < dateCount - 1; i++) {
            for (int j = 0; j < dateCount - i - 1; j++) {
                if (dates[j].compareTo(dates[j + 1]) > 0) {
                    String temp = dates[j];
                    dates[j] = dates[j + 1];
                    dates[j + 1] = temp;
                }
            }
        }
        
        // Display dates
        for (int i = 0; i < dateCount; i++) {
            if (dates[i].equals(today)) {
                System.out.println((i + 1) + ". " + dates[i] + " (Today)");
            } else {
                System.out.println((i + 1) + ". " + dates[i]);
            }
        }
        
        // Check if today has a journal
        boolean hasTodayJournal = false;
        for (int i = 0; i < journalCount; i++) {
            if (userJournals[i].getDate().equals(today)) {
                hasTodayJournal = true;
                break;
            }
        }
        
        if (hasTodayJournal) {
            System.out.print("\nSelect a date to view journal, or edit the journal for today: ");
        } else {
            System.out.print("\nSelect a date to view journal, or create a new journal for today: ");
        }
        
        String choice = scanner.nextLine();
        
        try {
            int index = Integer.parseInt(choice) - 1;
            if (index >= 0 && index < dateCount) {
                String selectedDate = dates[index];
                
                if (selectedDate.equals(today)) {
                    handleTodayJournal(today, hasTodayJournal);
                } else {
                    viewJournal(selectedDate);
                }
            } else {
                System.out.println("Invalid selection.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }
    
    // Handle today's journal (create or edit)
    private static void handleTodayJournal(String today, boolean exists) {
        if (!exists) {
            // Create new journal
            System.out.print("Enter your journal entry for " + today + ":\n> ");
            String content = scanner.nextLine();
            
            // Get weather
            System.out.println("Fetching weather data...");
            String weather = WeatherAPI.getWeather();
            
            // Get mood
            System.out.println("Analyzing sentiment...");
            String mood = SentimentAPI.analyzeSentiment(content);
            
            JournalEntry newEntry = new JournalEntry(today, content, weather, mood);
            userJournals[journalCount] = newEntry;
            journalCount++;
            FileHandler.saveJournals(currentUser.getEmail(), userJournals, journalCount);
            
            System.out.println("Journal saved successfully!");
            
            // Show options
            showTodayOptions(today);
        } else {
            // Edit or view
            showTodayOptions(today);
        }
    }


    // Show options for today's journal
    private static void showTodayOptions(String today) {
        System.out.println("\nWould you like to:");
        System.out.println("1. View Journal");
        System.out.println("2. Edit Journal");
        System.out.println("3. Back to Dates");
        System.out.print("> ");
        
        String choice = scanner.nextLine();
        
        switch (choice) {
            case "1":
                viewJournal(today);
                break;
            case "2":
                editJournal(today);
                break;
            case "3":
                // Go back
                break;
            default:
                System.out.println("Invalid option.");
        }
    }
    
    // View a journal entry
    private static void viewJournal(String date) {
        for (int i = 0; i < journalCount; i++) {
            if (userJournals[i].getDate().equals(date)) {
                System.out.println("\n=== Journal Entry for " + date + " ===");
                System.out.println("Weather: " + userJournals[i].getWeather());
                System.out.println("Mood: " + userJournals[i].getMood());
                System.out.println("\n" + userJournals[i].getContent());
                System.out.println("\nPress Enter to go back.");
                scanner.nextLine();
                return;
            }
        }
        System.out.println("No journal found for this date.");
    }
    
    // Edit today's journal
   
    
    // Weekly Summary
    private static void showWeeklySummary() {
        System.out.println("\n=== Weekly Mood Summary ===");
        
        // Get last 7 days
        LocalDate today = LocalDate.now();
        String[] last7Days = new String[7];
        
        for (int i = 6; i >= 0; i--) {
            last7Days[6 - i] = today.minusDays(i).format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
        
        System.out.println("Date\t\t| Weather\t\t\t| Mood");
        System.out.println("-------------------------------------------------------------");
        
        for (int i = 0; i < 7; i++) {
            String date = last7Days[i];
            String weather = "No entry";
            String mood = "No entry";
            
            for (int j = 0; j < journalCount; j++) {
                if (userJournals[j].getDate().equals(date)) {
                    weather = userJournals[j].getWeather();
                    mood = userJournals[j].getMood();
                    break;
                }
            }
            
            System.out.printf("%s\t| %-30s| %s\n", date, weather, mood);
        }
        
        System.out.println("\nPress Enter to go back.");
        scanner.nextLine();
    }
}