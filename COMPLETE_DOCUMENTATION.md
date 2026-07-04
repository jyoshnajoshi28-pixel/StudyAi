# StudyAI - Complete Android Study Assistant

A feature-rich Android study companion app built with **Kotlin**, **Room Database**, **MVVM Architecture**, and **Jetpack Libraries**.

## ✨ Features Implemented

### 1. **AI Chat** 💬
- Ask educational questions and get AI-powered responses
- Maintains chat history
- Clear chat history option
- Copy responses functionality

### 2. **Notes** 📝
- Create, read, update, delete (CRUD) operations
- Search notes by title/content
- Full-text search capability
- Beautiful card-based UI

### 3. **Flashcards** 🎴
- Create flashcards with questions and answers
- Flip cards to reveal answers
- Navigate through cards (next/previous)
- Delete flashcards
- Topic-based organization

### 4. **Test Papers** 📋
- Generate tests with multiple question types:
  - Multiple Choice Questions (MCQ)
  - Short Answer Questions
  - Long Answer Questions
  - True/False Questions
  - Fill in the Blanks
- Difficulty levels: Easy, Medium, Hard
- Auto-scoring system
- Track test scores

### 5. **Revision Sheets** 📄
- Generate one-page revision sheets
- Extract key points from content
- Highlight important formulas
- List important definitions
- Subject-wise organization

### 6. **Summaries** ✂️
- Summarize long texts automatically
- Three summary levels: Short, Medium, Detailed
- Keyword extraction
- Text analysis

### 7. **Study Planner** 📅
- Plan subjects with exam dates
- Set daily study goals
- Track progress
- Days remaining calculator
- Goal completion tracker

### 8. **Settings** ⚙️
- API Key configuration
- Theme selection (Light/Dark)
- Font size adjustment
- Clear all data option
- Persistent storage

## 🏗️ Architecture

### Design Pattern: **MVVM** (Model-View-ViewModel)
```
┌─────────────────────┐
│     UI (Views)      │
│   Activities &      │
│   Fragments         │
└────────────┬────────┘
             ↓
┌─────────────────────┐
│   ViewModels        │
│   (Business Logic)  │
└────────────┬────────┘
             ↓
┌─────────────────────┐
│  Repositories       │
│  (Data Access)      │
└────────────┬────────┘
             ↓
┌─────────────────────┐
│  Database (Room)    │
│  & Services         │
└─────────────────────┘
```

## 📦 Project Structure

```
app/src/main/java/com/studyai/app/
├── ui/
│   ├── activities/          # All 8 Activity screens
│   ├── adapters/            # RecyclerView adapters
│   └── viewmodels/          # MVVM ViewModels
├── database/
│   ├── entities/            # Room entities (10 tables)
│   ├── dao/                 # Data Access Objects
│   └── StudyAiDatabase.kt   # Room database
├── repositories/            # Data layer abstraction
├── services/                # Business logic services
├── models/                  # Data models
├── utils/                   # Utility functions
└── ViewModelFactory.kt      # DI factory

app/src/main/res/
├── layout/                  # XML layouts (13 files)
├── drawable/                # Drawables & shapes
├── values/
│   ├── strings.xml          # All UI strings
│   ├── colors.xml           # Color palette
│   ├── styles.xml           # Theme & styles
│   └── dimens.xml           # Dimensions
└── menu/                    # Menu items
```

## 🔧 Tech Stack

| Component | Technology |
|-----------|------------|
| Language | Kotlin 1.9.0 |
| UI Framework | Android SDK 34 |
| Database | Room 2.6.1 |
| Async | Coroutines 1.7.3 |
| Lifecycle | ViewModel, LiveData |
| UI Components | Material Design 3 |
| Build System | Gradle (Kotlin DSL) |

## 📊 Database Schema

**10 Tables:**
1. `notes` - User notes
2. `flashcards` - Flashcards with Q&A
3. `chat_messages` - Chat history
4. `test_papers` - Generated tests
5. `test_questions` - Test questions
6. `planner_subjects` - Study subjects
7. `planner_goals` - Daily study goals
8. `revision_notes` - Revision sheets
9. `summaries` - Generated summaries
10. `settings` - App configuration

## 🚀 How to Build & Run

### Prerequisites
- Android Studio Giraffe or later
- Android SDK 24-34
- Kotlin 1.9.0+
- Java 11+

### Steps
1. Clone the repository
   ```bash
   git clone https://github.com/jyoshnajoshi28-pixel/StudyAI.git
   cd StudyAI
   ```

2. Open in Android Studio
   - File → Open → Select StudyAI folder

3. Build the project
   ```bash
   ./gradlew build
   ```

4. Run on emulator/device
   ```bash
   ./gradlew installDebug
   ```

## 📋 Features Checklist

- [x] AI Chat Module
- [x] Notes Management
- [x] Flashcard System
- [x] Test Paper Generator
- [x] Revision Sheet Generator
- [x] Text Summarizer
- [x] Study Planner
- [x] Settings & Configuration
- [x] SQLite Database
- [x] Room ORM
- [x] MVVM Architecture
- [x] Coroutines for Async
- [x] Material Design 3 UI
- [x] XML Layouts (No Compose)
- [x] View Binding
- [x] RecyclerView Adapters
- [x] Search & Filter
- [x] Data Persistence
- [x] Error Handling
- [x] Utility Functions

## 🎯 Best Practices Implemented

✅ **Clean Code**
- Separation of concerns (MVVM)
- Single Responsibility Principle
- DRY (Don't Repeat Yourself)

✅ **Android Best Practices**
- Coroutines for background work
- Room for database abstraction
- ViewModels for lifecycle awareness
- Data binding with view binding

✅ **Code Quality**
- Type-safe
- Null-safe
- Proper exception handling
- Meaningful variable names

✅ **Performance**
- Efficient database queries
- Lazy loading
- Proper memory management
- Coroutine scopes properly managed

## 📱 Supported Devices

- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)
- **Orientation**: Portrait & Landscape
- **Screen Sizes**: All (Phone, Tablet)

## 🔐 Security Features

- ✅ Secure API key storage (SharedPreferences with encryption)
- ✅ Input validation
- ✅ SQL injection prevention (Room with parameterized queries)
- ✅ Secure hashing utilities

## 📝 File Summary

**Total Files Created: 50+**
- Activities: 8
- Adapters: 4
- ViewModels: 8
- Repositories: 8
- Services: 4
- DAOs: 5
- Entities: 1 (multi-class)
- Layouts: 15+
- Resources: 5+
- Utilities: 4
- Configuration: 5+

## 🤝 Contributing

Feel free to submit issues and enhancement requests!

## 📄 License

MIT License - Feel free to use for educational purposes

## 👨‍💻 Author

**Jyoshna Joshi**
- GitHub: [@jyoshnajoshi28-pixel](https://github.com/jyoshnajoshi28-pixel)

---

**Happy Studying! 🎓**

For questions or support, open an issue on GitHub.
