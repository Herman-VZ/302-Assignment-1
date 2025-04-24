module com.example.aiconceptsexplorer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;

    opens com.example.aiconceptsexplorer to javafx.fxml;
    exports com.example.aiconceptsexplorer;
    exports com.example.aiconceptsexplorer.controller;
    opens com.example.aiconceptsexplorer.controller to javafx.fxml;
    exports com.example.aiconceptsexplorer.model;
    opens com.example.aiconceptsexplorer.model to javafx.fxml;
}