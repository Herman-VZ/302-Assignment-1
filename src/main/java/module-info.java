module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example to javafx.graphics;
    opens com.example.account to javafx.fxml;
    opens com.example.leaderboardscreen to javafx.fxml;
    opens com.example.learnscreen to javafx.fxml;

    exports com.example;
    exports com.example.account;
    exports com.example.leaderboardscreen;
    exports com.example.learnscreen;
}