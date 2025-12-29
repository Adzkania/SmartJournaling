# 📓 Smart Journal App

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Hugging Face](https://img.shields.io/badge/Hugging%20Face-FFD21E?style=for-the-badge&logo=huggingface&logoColor=000)

A Java-based journaling application with real-time weather integration and AI-powered sentiment analysis.

> 🎓 **Course Project**: Fundamentals of Programming (FOP)  
> 👥 **Team**: Adzkania & Keertti  
> 📅 **Semester**: 1, 2024/2025

---

## 📑 Table of Contents

- [Features](#-features)
- [Prerequisites](#️-prerequisites)
- [Setup Instructions](#-setup-instructions)
- [How to Use](#-how-to-use)
- [Project Structure](#-project-structure)
- [API Documentation](#-api-documentation)
- [Troubleshooting](#-troubleshooting)
- [For Team Members](#-for-team-members)
- [Security Notes](#-security-notes)

---

## 📋 Features

- **User Authentication**: Secure login and registration system
- **Daily Journaling**: Create and edit journal entries with timestamps
- **Weather Integration**: Automatic weather fetching for Kuala Lumpur using Malaysian Government API
- **Sentiment Analysis**: AI-powered mood detection using Hugging Face's DistilBERT model
- **Weekly Summary**: View your mood trends over the past 7 days
- **Data Persistence**: All user data and journals saved locally in text files

## 🛠️ Prerequisites

| Requirement | Version | Purpose |
|------------|---------|---------|
| ☕ Java JDK | 8 or higher | Core application |
| 🤗 Hugging Face Account | Free tier | Sentiment analysis API |
| 🌐 Internet Connection | Required | Weather & sentiment APIs |

## 📥 Setup Instructions

### 1. Clone or Download the Project

```bash
git clone https://github.com/Adzkania/SmartJournaling.git
cd SmartJournaling
```

### 2. Get Your Hugging Face API Token

> 🔑 **You need a free Hugging Face account to use the sentiment analysis feature**

<details>
<summary>Click here for step-by-step instructions</summary>

1. Go to [Hugging Face](https://huggingface.co/join) and create a free account
2. Navigate to [Settings → Access Tokens](https://huggingface.co/settings/tokens)
3. Click **"Create new token"**
4. Give it a name (e.g., "Smart Journal App")
5. Select **"Read"** permission
6. Click **"Generate token"**
7. **Copy the token** (it starts with `hf_`)

</details>

### 3. Create the `.env` File

> ⚠️ **IMPORTANT**: This file contains your secret API token. Never commit it to Git!

Create a file named `.env` in the project root directory (same folder as the Java files).

<details>
<summary>📝 Instructions for Windows</summary>

**PowerShell:**
```powershell
notepad .env
```

</details>

<details>
<summary>📝 Instructions for Mac/Linux</summary>

```bash
nano .env
```

</details>

Add this line to the file (replace with your actual token):
```env
BEARER_TOKEN=hf_YourActualTokenHere
```

**Example:**
```env
BEARER_TOKEN=hf_XXXXXXXXXXXXXXXXX
```

**✅ Checklist:**
- [ ] No spaces around the `=` sign
- [ ] No quotes around the token
- [ ] File is named `.env` (not `.env.txt`)
- [ ] File is in the same folder as your `.java` files

### 4. Compile the Project

Navigate to the project directory and compile all Java files:

```bash
javac *.java
```

This should compile all files without errors.

### 5. Run the Application

```bash
java SmartJournalApp
```

## 📖 How to Use

### First Time Setup

1. **Register a new account**:
   - Choose option `2` (Register)
   - Enter your email
   - Choose a display name
   - Create a password

2. **Login**:
   - Choose option `1` (Login)
   - Enter your registered email and password

### Creating Journal Entries

1. From the main menu, select **"Create, Edit & View Journals"**
2. You'll see a list of dates - today's date will be marked as "(Today)"
3. Select today's date to create a new entry
4. Write your journal entry when prompted
5. The app will automatically:
   - Fetch current weather for Kuala Lumpur
   - Analyze the sentiment of your entry (Positive 😊 or Negative 😔)
   - Save everything to a file

### Viewing Past Entries

1. Go to **"Create, Edit & View Journals"**
2. Select any date from the list
3. View the journal content, weather, and mood for that day

### Editing Today's Entry

1. Go to **"Create, Edit & View Journals"**
2. Select today's date
3. Choose **"Edit Journal"**
4. Enter your updated text
5. The mood will be re-analyzed automatically

### Weekly Summary

1. From the main menu, select **"View Weekly Mood Summary"**
2. See your journal entries, weather, and moods for the past 7 days
3. Track your emotional patterns over time

## 📁 Project Structure

```
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
```

## 🔒 Security Notes

- **Never share your `.env` file** or commit it to Git
- The `.gitignore` file prevents `.env` from being uploaded to GitHub
- Each user's password is stored in plain text in `UserData.txt` (for educational purposes only - not secure for production!)

## 🐛 Troubleshooting

<details>
<summary><b>❌ "Error: BEARER_TOKEN is not set in the environment"</b></summary>

**Solutions:**
- Make sure your `.env` file exists in the same folder as the Java files
- Check that the format is exactly: `BEARER_TOKEN=hf_yourtoken` (no spaces, no quotes)
- Verify the file is named `.env` not `.env.txt`
- Try running: `Get-Content .env` (Windows) or `cat .env` (Mac/Linux) to check contents

</details>

<details>
<summary><b>🌦️ "Weather unavailable" or 😔 "Mood unavailable"</b></summary>

**Solutions:**
- Check your internet connection
- For mood analysis: Verify your Hugging Face token is valid at [huggingface.co/settings/tokens](https://huggingface.co/settings/tokens)
- The token might be expired - generate a new one
- Check if the APIs are working by visiting them in your browser

</details>

<details>
<summary><b>🔨 Compilation errors</b></summary>

**Solutions:**
- Make sure all `.java` files are in the same directory
- Ensure you're using Java 8 or higher: `java -version`
- Try cleaning up: Delete all `.class` files and compile again
- Check for syntax errors in your code

</details>

<details>
<summary><b>🚫 "Cannot find or load main class SmartJournalApp"</b></summary>

**Solutions:**
- Make sure you compiled first: `javac *.java`
- Run from the correct directory where the `.class` files are
- Check that `SmartJournalApp.class` exists in the directory
- Try: `java -cp . SmartJournalApp`

</details>

## 🤝 For Team Members

### 🔄 Git Workflow

```bash
# Before you start working
git pull origin main

# After making changes
git add .
git commit -m "feat: add your feature description"
git push origin main
```

### 📋 Commit Message Format

Use these prefixes for clear commit history:
- `feat:` - New feature
- `fix:` - Bug fix
- `docs:` - Documentation changes
- `style:` - Code formatting
- `refactor:` - Code restructuring

**Examples:**
```bash
git commit -m "feat: add password validation"
git commit -m "fix: resolve login authentication bug"
git commit -m "docs: update README with new features"
```

### ⚠️ Important Rules

| ✅ DO | ❌ DON'T |
|-------|----------|
| Test your changes locally first | Commit the `.env` file |
| Pull before you push | Push broken code |
| Write clear commit messages | Make large commits without explanation |
| Ask for help when stuck | Delete other people's code without discussion |

### 👥 File Responsibilities

| File | Purpose | Primary Owner |
|------|---------|---------------|
| `SmartJournalApp.java` | Main UI and menu logic | [Team Lead] |
| `FileHandler.java` | File I/O operations | [Member 2] |
| `WeatherAPI.java` | Weather data fetching | [Member 3] |
| `SentimentAPI.java` | Sentiment analysis | [Member 4] |
| `User.java` / `JournalEntry.java` | Data models | [Shared] |

## 📝 API Documentation

### Weather API
- **Source**: Malaysian Government Open Data API
- **Endpoint**: `https://api.data.gov.my/weather/forecast/`
- **Authentication**: None required
- **Rate Limit**: Reasonable use

### Sentiment Analysis API
- **Source**: Hugging Face Inference API
- **Model**: `distilbert-base-uncased-finetuned-sst-2-english`
- **Authentication**: Bearer token required
- **Rate Limit**: Check your [Hugging Face account](https://huggingface.co/settings/tokens)

## 📧 Support

If you encounter issues:

1. 📖 Check the [Troubleshooting](#-troubleshooting) section above
2. 💬 Ask in the team group chat
3. 🐛 [Create an issue](https://github.com/Adzkania/SmartJournaling/issues) on GitHub
4. 📧 Contact the team lead

---

<div align="center">

## 📄 License

This project is for educational purposes as part of the **Fundamentals of Programming** course.

---

**Made with ☕ and 💻 by Team Adzkania**

**Happy Journaling! 📓✨**

[![GitHub](https://img.shields.io/badge/GitHub-SmartJournaling-blue?style=flat&logo=github)](https://github.com/Adzkania/SmartJournaling)

</div>
