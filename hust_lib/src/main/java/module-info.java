module com.example.loginpage {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens com.example.loginpage to javafx.fxml;
    exports com.example.loginpage;
}