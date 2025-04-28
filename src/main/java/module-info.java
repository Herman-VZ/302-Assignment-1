module com.example.account {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.account to javafx.fxml;
    exports com.example.account;
}