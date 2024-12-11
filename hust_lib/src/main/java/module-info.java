module com.example.library {
    requires javafx.controls;
    //requires javafx.fxml;
    //requires javafx.web;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires javafx.fxml;

    opens com.example.library to javafx.fxml;
    exports com.example.library;
    exports com.example.library.controller;
    opens com.example.library.controller to javafx.fxml;
    opens com.example.library.view to javafx.fxml;
}