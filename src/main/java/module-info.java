module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;  // Required for SQLite

    // Remove the problematic requires statement
    // requires org.sqlite.JDBC;  ← DELETE THIS LINE

    opens com.example to javafx.graphics;
    opens com.example.account to javafx.fxml;
    opens com.example.leaderboardscreen to javafx.fxml;

    exports com.example;
    exports com.example.account;
    exports com.example.leaderboardscreen;
}