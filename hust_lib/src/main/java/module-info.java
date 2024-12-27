module com.example.library {
<<<<<<< Updated upstream
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires org.postgresql.jdbc;
<<<<<<< Updated upstream
=======
    requires javafx.controls;        // Required for JavaFX controls
    requires javafx.fxml;           // Required for JavaFX FXML support
    //requires javafx.web;          // Uncomment if you need JavaFX web support
    requires java.sql;              // Required for SQL database connectivity
    requires org.postgresql.jdbc;
    requires java.desktop;   // Required for PostgreSQL JDBC driver
>>>>>>> Stashed changes
=======
    requires java.desktop;
>>>>>>> Stashed changes

    opens com.example.library to javafx.fxml; // Allow FXML to access this package
    exports com.example.library;     // Export the main library package
    exports com.example.library.controller; // Export the controller package
    opens com.example.library.controller to javafx.fxml; // Allow FXML access
    opens com.example.library.view to javafx.fxml; // Allow FXML access to views
}