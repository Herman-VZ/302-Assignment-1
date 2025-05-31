# AIENSTEIN

AIENSTEIN is an interactive JavaFX learning tool designed to help students understand foundational AI concepts — without using AI itself. Built with a strong focus on modular design, agile methodology, and user-friendly navigation.

---

## 🧠 Features

- 🔐 **Authentication System**: Signup, login, and account views with data persistence (SQLite)
- 📚 **AI Lessons**: Topic-based learning content with progress tracking
- 🧪 **Quizzes**: Reinforce learning with instant feedback and score recording
- 🏆 **Leaderboard**: Tracks and displays top user scores
- 🌱 **Learning Streaks**: Encourages continuous engagement
- 📊 **Glossary & Search**: Supports concept recall and lesson retrieval

---

## 🧱 Tech Stack

- **Java 21**
- **JavaFX 21**
- **SQLite** (via JDBC)
- **Maven** (build automation & dependency management)
- **GitHub Actions** (CI/CD)
- **JUnit** (unit testing)

---

## 🚀 How to Run

### Prerequisites:
- JDK 21+
- Maven installed

### Build & Run:
```bash
mvn clean install
mvn javafx:run
```

> If you're not using the JavaFX Maven plugin, use:
```bash
mvn exec:java -Dexec.mainClass="com.example.MainApplication"
```

---

## 🧪 Running Tests

```bash
mvn test
```

Unit tests are located in:
```
src/test/com/example/aiconceptsexplorer/
```

---

## 📂 Project Structure

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.aiconceptsexplorer
│   │   │       ├── account/
│   │   │       ├── controllers/
│   │   │       ├── home/
│   │   │       ├── learnscreen/
│   │   │       ├── leaderboardscreen/
│   │   │       ├── lessons/
│   │   │       ├── quizzes/
│   ├── test
│   │   └── ...
│   ├── resources
│       └── *.fxml, .db files, stylesheets
```

---

## 👨‍💻 Team & Workflow

- Agile sprints managed via GitHub Projects
- User stories prioritised using MoSCoW
- Weekly standups, feature-linked commits, and evolving sprint plans
- CI/CD with build/test automation on each push

---

## 📄 Javadoc

To generate Javadoc:

```bash
mvn javadoc:javadoc
```

HTML output available at:
```
target/site/apidocs/index.html
```

---

## 🧠 Known Limitations

- Teacher view was de-scoped for time reasons
- Some 'Should Have' features were deprioritised mid-semester in favour of polish and stability

---

## 🏁 Submission Contents

- ✅ Complete source code
- ✅ SQLite databases
- ✅ All FXML files
- ✅ CI/CD config
- ✅ Unit tests
- ✅ User stories & sprint plans
- ✅ Walkthrough video
- ✅ Generated Javadoc

---

## 🔗 License

MIT — for educational purposes only.
