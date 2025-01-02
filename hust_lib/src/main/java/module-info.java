module com.example.library {
<<<<<<< HEAD
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
=======
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens com.example.library to javafx.fxml;
    opens com.example.library.model to javafx.base; // Allow reflective access for TableView
    opens com.example.library.controller to javafx.fxml;
    opens com.example.library.view to javafx.fxml;

    exports com.example.library;
    exports com.example.library.controller;
    opens com.example.library.service to javafx.base;
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60
}
