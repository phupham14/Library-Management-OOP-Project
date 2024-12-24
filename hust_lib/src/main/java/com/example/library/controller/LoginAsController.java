package com.example.library.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginAsController {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="switchToAdmin"
    private Button switchToAdmin; // Value injected by FXMLLoader

    @FXML // fx:id="switchToEmployee"
    private Button switchToEmployee; // Value injected by FXMLLoader

    @FXML // fx:id="switchToLogin"
    private Button switchToLogin; // Value injected by FXMLLoader

    @FXML // fx:id="switchToUser"
    private Button switchToUser; // Value injected by FXMLLoader


    public void onSwitchToLogin() throws IOException {
        // Load the FXML file
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/library/view/login-view.fxml")));

        // Create a new Scene with the loaded content
        Scene scene = new Scene(root);

        // Get the current window
        Stage window = (Stage) switchToLogin.getScene().getWindow();

        // Set the scene to the window
        window.setScene(scene);

        // Optionally, you can set the window to be resizable
        window.setResizable(true);

        // Show the window with preferred size
        window.sizeToScene();
    }

    public void ontSwitchToAdmin() throws IOException {
        // Load the FXML file
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/library/view/admin-page.fxml")));

        // Create a new Scene with the loaded content
        Scene scene = new Scene(root);

        // Get the current window
        Stage window = (Stage) switchToAdmin.getScene().getWindow();

        // Set the scene to the window
        window.setScene(scene);

        // Optionally, you can set the window to be resizable
        window.setResizable(true);

        // Show the window with preferred size
        window.sizeToScene();
    }

    public void onSwitchToUser() throws IOException {
        // Load the FXML file
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/library/view/user-page.fxml")));

        // Create a new Scene with the loaded content
        Scene scene = new Scene(root);

        // Get the current window
        Stage window = (Stage) switchToUser.getScene().getWindow();

        // Set the scene to the window
        window.setScene(scene);

        // Optionally, you can set the window to be resizable
        window.setResizable(true);

        // Show the window with preferred size
        window.sizeToScene();
    }

    public void onSwitchToEmployee() throws IOException {
        // Load the FXML file
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/library/view/employee-page.fxml")));

        // Create a new Scene with the loaded content
        Scene scene = new Scene(root);

        // Get the current window
        Stage window = (Stage) switchToEmployee.getScene().getWindow();

        // Set the scene to the window
        window.setScene(scene);

        // Optionally, you can set the window to be resizable
        window.setResizable(true);

        // Show the window with preferred size
        window.sizeToScene();
    }
}
