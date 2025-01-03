package com.example.library.controller;

import com.example.library.service.addMemberService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddMemberController {

    @FXML
    private TextField addMem_adress;

    @FXML
    private Button addMem_cancelBtn;

    @FXML
    private TextField addMem_contact;

    @FXML
    private TextField addMem_email;

    @FXML
    private TextField addMem_firstName;

    @FXML
    private TextField addMem_lastName;

    @FXML
    private PasswordField addMem_pswd;

    @FXML
    private Button addMem_saveBtn;

    private final addMemberService memberService;

    public AddMemberController() {
        memberService = new addMemberService(); // Initialize the service
    }

    // Handle the action when the Cancel button is pressed
    @FXML
    private void handleCancelAction() {
        Stage stage = (Stage) addMem_cancelBtn.getScene().getWindow();
        stage.close();
    }

    // Handle the action when the Save button is pressed
    @FXML
    private void handleSaveAction() {
        String firstName = addMem_firstName.getText().trim();
        String lastName = addMem_lastName.getText().trim();
        String mobile = addMem_contact.getText().trim();
        String email = addMem_email.getText().trim();
        String address = addMem_adress.getText().trim();
        String password = addMem_pswd.getText().trim();

        // Validate input fields
        if (firstName.isEmpty() || lastName.isEmpty() || mobile.isEmpty() ||
                email.isEmpty() || address.isEmpty() || password.isEmpty()) {
            System.out.println("Please fill in all the fields.");
            return;
        }

        try {
            // Save member details
            memberService.handleSaveAction(firstName, lastName, mobile, email, address, password);
            System.out.println("Member added successfully.");
            handleCancelAction(); // Close the window after saving
        } catch (Exception e) {
            System.err.println("Error adding member: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
