Smart Journal App
A Java-based journaling application with real-time weather integration and AI-powered sentiment analysis.

📋 Features
User Authentication: Secure login and registration system
Daily Journaling: Create and edit journal entries with timestamps
Weather Integration: Automatic weather fetching for Kuala Lumpur using Malaysian Government API
Sentiment Analysis: AI-powered mood detection using Hugging Face's DistilBERT model
Weekly Summary: View your mood trends over the past 7 days
Data Persistence: All user data and journals saved locally in text files
🛠️ Prerequisites
Java JDK (version 8 or higher)
Hugging Face Account (free) - for sentiment analysis API
Internet Connection - for API calls
📥 Setup Instructions
1. Clone or Download the Project
bash
git clone https://github.com/Adzkania/SmartJournaling.git
cd SmartJournaling
2. Get Your Hugging Face API Token
Go to Hugging Face and create a free account
Navigate to Settings → Access Tokens
Click "Create new token"
Give it a name (e.g., "Smart Journal App")
Select "Read" permission
Click "Generate token"
Copy the token (it starts with hf_)
3. Create the .env File
Create a file named .env in the project root directory (same folder as the Java files).

Windows (PowerShell):

powershell
notepad .env
Mac/Linux:

bash
nano .env
Add this line to the file (replace with your actual token):

BEARER_TOKEN=hf_YourActualTokenHere
Example:

BEARER_TOKEN=hf_xxxxxxxxxxxxxxxxx
⚠️ Important:

No spaces around the = sign
No quotes around the token
Make sure the file is named .env (not .env.txt)
4. Compile the Project
Navigate to the project directory and compile all Java files:

bash
javac *.java
This should compile all files without errors.

5. Run the Application
bash
java SmartJournalApp
📖 How to Use
First Time Setup
Register a new account:
Choose option 2 (Register)
Enter your email
Choose a display name
Create a password
Login:
Choose option 1 (Login)
Enter your registered email and password
Creating Journal Entries
From the main menu, select "Create, Edit & View Journals"
You'll see a list of dates - today's date will be marked as "(Today)"
Select today's date to create a new entry
Write your journal entry when prompted
The app will automatically:
Fetch current weather for Kuala Lumpur
Analyze the sentiment of your entry (Positive 😊 or Negative 😔)
Save everything to a file
Viewing Past Entries
Go to "Create, Edit & View Journals"
Select any date from the list
View the journal content, weather, and mood for that day
Editing Today's Entry
Go to "Create, Edit & View Journals"
Select today's date
Choose "Edit Journal"
Enter your updated text
The mood will be re-analyzed automatically
Weekly Summary
From the main menu, select "View Weekly Mood Summary"
See your journal entries, weather, and moods for the past 7 days
Track your emotional patterns over time
📁 Project Structure
SmartJournaling/
├── SmartJournalApp.java    # Main application with UI logic
├── User.java                # User data model
├── JournalEntry.java        # Journal entry data model
├── FileHandler.java         # Handles file I/O operations
├── WeatherAPI.java          # Fetches weather from Malaysian Gov API
├── SentimentAPI.java        # Analyzes sentiment using Hugging Face
├── EnvLoader.java           # Loads environment variables from .env
├── .env                     # Your API token (DO NOT COMMIT TO GIT!)
├── .gitignore              # Tells Git to ignore .env file
├── UserData.txt            # Stores user accounts (auto-generated)
└── [email]_journals.txt    # Stores journals per user (auto-generated)
🔒 Security Notes
Never share your .env file or commit it to Git
The .gitignore file prevents .env from being uploaded to GitHub
Each user's password is stored in plain text in UserData.txt (for educational purposes only - not secure for production!)
🐛 Troubleshooting
"Error: BEARER_TOKEN is not set in the environment"
Make sure your .env file exists in the same folder as the Java files
Check that the format is exactly: BEARER_TOKEN=hf_yourtoken (no spaces, no quotes)
Verify the file is named .env not .env.txt
"Weather unavailable" or "Mood unavailable"
Check your internet connection
For mood analysis: Verify your Hugging Face token is valid
The token might be expired - generate a new one
Compilation errors
Make sure all .java files are in the same directory
Ensure you're using Java 8 or higher: java -version
"Cannot find or load main class SmartJournalApp"
Make sure you compiled first: javac *.java
Run from the correct directory where the .class files are
🤝 For Team Members
Before You Start Coding
Pull the latest changes: git pull origin main
Make sure your .env file is set up with your own Hugging Face token
When You Make Changes
Test your changes locally first
Never commit the .env file!
Commit with clear messages: git commit -m "Add feature: X"
Push: git push origin main
File Responsibilities
SmartJournalApp.java - Main UI and menu logic
FileHandler.java - All file reading/writing
WeatherAPI.java - Weather data fetching
SentimentAPI.java - Sentiment analysis
User.java / JournalEntry.java - Data models
📝 API Documentation
Weather API
Source: Malaysian Government Open Data API
Endpoint: https://api.data.gov.my/weather/forecast/
Authentication: None required
Rate Limit: Reasonable use
Sentiment Analysis API
Source: Hugging Face Inference API
Model: distilbert-base-uncased-finetuned-sst-2-english
Authentication: Bearer token required
Rate Limit: Check your Hugging Face account
📧 Support
If you encounter issues:

Check the Troubleshooting section above
Contact the team lead
Create an issue in the GitHub repository
📄 License
This project is for educational purposes as part of the Fundamentals of Programming course.

Happy Journaling! 📓✨

