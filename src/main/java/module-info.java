module com.example.addressbook {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.addressbook to javafx.fxml;
    exports com.example.addressbook;
}