module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example to javafx.graphics;
    opens com.example.account to javafx.fxml;
    opens com.example.leaderboardscreen to javafx.fxml;
    opens com.example.learnscreen to javafx.fxml;
    opens com.example.aiconceptsexplorer.controllers to javafx.fxml; // ✅ added this

    exports com.example;
    exports com.example.account;
    exports com.example.leaderboardscreen;
    exports com.example.learnscreen;
    exports com.example.aiconceptsexplorer.controllers; // ✅ added this
}
