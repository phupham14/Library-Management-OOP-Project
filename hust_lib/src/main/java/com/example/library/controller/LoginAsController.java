package com.example.library.controller;

import com.example.library.model.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import com.example.library.service.cartService;
import com.example.library.service.customerService;
import static com.example.library.util.Session.getInstance;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/library/view/Dashboard.fxml")));

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
        int currentPersonId = getInstance().getPersonId(); // Get person ID from session
        System.out.println("Current Person ID: " + currentPersonId); // Debug: Print currentPersonId

        Customer currentCustomer = customerService.getCustomerById(currentPersonId); // Retrieve customer using person ID
        System.out.println("Customer object: " + currentCustomer); // Debug: Print customer object

        if (currentCustomer != null) {
            if (!currentCustomer.isBlockRent()) {
                try {
                    System.out.println("Clearing cart for customer ID: " + currentCustomer.getCustomerId()); // Debug: Print before clearing
                    cartService.getInstance().clearCart(currentCustomer.getCustomerId()); // Clear cart using customer ID from the Customer object
                    System.out.println("Cart cleared successfully."); // Debug: Print after clearing
                } catch (SQLException e) {
                    System.err.println("Error clearing cart: " + e.getMessage());
                }
            } else {
                System.out.println("Customer is blocked from renting."); // Debug: Print if customer is blocked
            }

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
        } else {
            System.out.println("Customer object is null."); // Debug: Print if customer is not found
        }
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