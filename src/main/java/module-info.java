module com.example.leaderboardscreen {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.leaderboardscreen to javafx.fxml;
    exports com.example.leaderboardscreen;
}