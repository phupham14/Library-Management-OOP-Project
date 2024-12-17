package com.example.library.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddMemberController {

    @FXML // fx:id="addMem_adress"
    private TextField addMem_adress; // Value injected by FXMLLoader

    @FXML // fx:id="addMem_cancelBtn"
    private Button addMem_cancelBtn; // Value injected by FXMLLoader

    @FXML // fx:id="addMem_contact"
    private TextField addMem_contact; // Value injected by FXMLLoader

    @FXML // fx:id="addMem_email"
    private TextField addMem_email; // Value injected by FXMLLoader

    @FXML // fx:id="addMem_firstName"
    private TextField addMem_firstName; // Value injected by FXMLLoader

    @FXML // fx:id="addMem_lastName"
    private TextField addMem_lastName; // Value injected by FXMLLoader

    @FXML // fx:id="addMem_saveBtn"
    private Button addMem_saveBtn; // Value injected by FXMLLoader

    // Handle the action when the Cancel button is pressed
    @FXML
    private void handleCancelAction() {
        Stage stage = (Stage) addMem_cancelBtn.getScene().getWindow();
        stage.close();
    }

    // Handle the action when the Save button is pressed
    @FXML
    private void handleSaveAction() {
        Stage stage = (Stage) addMem_saveBtn.getScene().getWindow();
        stage.close();
    }
}