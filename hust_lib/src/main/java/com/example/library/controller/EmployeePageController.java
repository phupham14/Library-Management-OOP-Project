package com.example.library.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class EmployeePageController {

    @FXML
    private TextField employeeBookSearchField;

    @FXML
    private MenuBar employee_menubar;

    @FXML
    private Menu employee_issuedBooks;

    @FXML
    private ListView<?> employee_listView;

    @FXML
    private Button employee_renewBtn;

    @FXML
    private Button employee_submissionBtn;

    // This method is called when the issuedBooks menu item is clicked
    @FXML
    private void handleOpenIssuedBooks() throws IOException {
        // Load the FXML file for the issued books page
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/library/view/issued-books.fxml")));

        // Create a new Stage for the new window
        Stage issuedBooksStage = new Stage();

        // Create a new Scene with the loaded content
        Scene scene = new Scene(root);

        // Set the scene to the new window
        issuedBooksStage.setScene(scene);

        // Optionally, you can set the window to be resizable
        issuedBooksStage.setResizable(true);

        // Set a title for the new window (optional)
        issuedBooksStage.setTitle("Issued Books Page");

        // Show the new window
        issuedBooksStage.show();
    }
}