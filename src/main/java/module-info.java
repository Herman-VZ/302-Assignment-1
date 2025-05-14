module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example to javafx.graphics;
    opens com.example.aiconceptsexplorer.account to javafx.fxml;
    opens com.example.aiconceptsexplorer.leaderboardscreen to javafx.fxml;
    opens com.example.aiconceptsexplorer.learnscreen to javafx.fxml;
    opens com.example.aiconceptsexplorer.controllers to javafx.fxml;
    opens com.example.aiconceptsexplorer.lessons to javafx.fxml; // ✅ this was missing

    exports com.example;
    exports com.example.aiconceptsexplorer.account;
    exports com.example.aiconceptsexplorer.leaderboardscreen;
    exports com.example.aiconceptsexplorer.learnscreen;
    exports com.example.aiconceptsexplorer.controllers;
    exports com.example.aiconceptsexplorer.lessons;
}
