module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.xerial.sqlitejdbc;

    opens com.example to javafx.graphics;
    opens com.example.aiconceptsexplorer.account to javafx.fxml;
    opens com.example.aiconceptsexplorer.leaderboardscreen to javafx.fxml;
    opens com.example.aiconceptsexplorer.learnscreen to javafx.fxml;
    opens com.example.aiconceptsexplorer.controllers to javafx.fxml;
    opens com.example.aiconceptsexplorer.lessons to javafx.fxml; //  this was missing
    opens com.example.aiconceptsexplorer.home to javafx.fxml; // Add this line to open the home package
    opens com.example.aiconceptsexplorer.quizzes to javafx.fxml; //  added for QuizPageController

    exports com.example;
    exports com.example.aiconceptsexplorer.account;
    exports com.example.aiconceptsexplorer.leaderboardscreen;
    exports com.example.aiconceptsexplorer.learnscreen;
    exports com.example.aiconceptsexplorer.controllers;
    exports com.example.aiconceptsexplorer.lessons;
    exports com.example.aiconceptsexplorer.home; // Ensure to export the home package
}
