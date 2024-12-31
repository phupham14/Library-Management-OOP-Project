module com.example.library {
    requires javafx.controls;        // JavaFX controls
    requires javafx.fxml;            // JavaFX FXML support
    requires java.sql;               // SQL database connectivity
    requires org.postgresql.jdbc;    // PostgreSQL JDBC driver
    requires java.desktop;           // Desktop integration (e.g., for images)

    opens com.example.library to javafx.fxml;                // Allow FXML to access this package
    exports com.example.library;                             // Export the main library package
    exports com.example.library.controller;                  // Export the controller package
    opens com.example.library.controller to javafx.fxml;     // Allow FXML access to controllers
    opens com.example.library.view to javafx.fxml;           // Allow FXML access to views
}
