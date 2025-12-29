import java.io.*;

public class FileHandler {
    
    // Load users from UserData.txt
    public static User[] loadUsers() {
    int userCount = 0;

    try (BufferedReader br = new BufferedReader(new FileReader("UserData.txt"))) {
        while (true) {
            String email = br.readLine();
            if (email == null) break;
            br.readLine(); // display name
            br.readLine(); // password
            userCount++;
        }
    } catch (IOException e) {
        return new User[0];
    }

    User[] users = new User[userCount];

    try (BufferedReader br = new BufferedReader(new FileReader("UserData.txt"))) {
        String email, displayName, password;
        int index = 0;

        while ((email = br.readLine()) != null) {
            displayName = br.readLine();
            password = br.readLine();

            users[index++] = new User(email, displayName, password);
        }
    } catch (IOException e) {
        System.out.println("Error loading users: " + e.getMessage());
    }

    return users;
}

    
    // Save a new user to UserData.txt
    public static void saveUser(User user) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("UserData.txt", true));
            bw.write(user.getEmail() + "\n");
            bw.write(user.getDisplayName() + "\n");
            bw.write(user.getPassword() + "\n");
            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }
    
    // Load journal entries for a specific user
    public static JournalEntry[] loadJournals(String email) {
        String filename = email.replace("@", "_") + "_journals.txt";
        
        // First, count how many journal entries exist
        int journalCount = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while (br.readLine() != null) {
                br.readLine(); // content
                br.readLine(); // weather
                br.readLine(); // mood
                journalCount++;
            }
            br.close();
        } catch (FileNotFoundException e) {
            return new JournalEntry[0]; // Return empty array if file doesn't exist
        } catch (IOException e) {
            System.out.println("Error counting journals: " + e.getMessage());
            return new JournalEntry[0];
        }
        
        // Create array and load journals
        JournalEntry[] journals = new JournalEntry[journalCount];
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String date, content, weather, mood;
            int index = 0;
            while ((date = br.readLine()) != null) {
                content = br.readLine();
                weather = br.readLine();
                mood = br.readLine();
                if (content != null && weather != null && mood != null) {
                    journals[index] = new JournalEntry(date, content, weather, mood);
                    index++;
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error loading journals: " + e.getMessage());
        }
        return journals;
    }
    
    // Save all journals for a user (overwrites file)
    public static void saveJournals(String email, JournalEntry[] journals, int count) {
        String filename = email.replace("@", "_") + "_journals.txt";
        
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            for (int i = 0; i < count; i++) {
                bw.write(journals[i].getDate() + "\n");
                bw.write(journals[i].getContent() + "\n");
                bw.write(journals[i].getWeather() + "\n");
                bw.write(journals[i].getMood() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving journals: " + e.getMessage());
        }
    }
}