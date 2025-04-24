module com.example.learnscreen {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.learnscreen to javafx.fxml;
    exports com.example.learnscreen;
}