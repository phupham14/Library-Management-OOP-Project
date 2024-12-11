package com.example.library.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class LoginController {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="changePass_Pass"
    private PasswordField changePass_Pass; // Value injected by FXMLLoader

    @FXML // fx:id="changePass_backBtn"
    private Button changePass_backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="changePass_btn"
    private Button changePass_btn; // Value injected by FXMLLoader

    @FXML // fx:id="changePass_cPass"
    private PasswordField changePass_cPass; // Value injected by FXMLLoader

    @FXML // fx:id="changePass_form"
    private AnchorPane changePass_form; // Value injected by FXMLLoader

    @FXML // fx:id="changePass_showPass"
    private CheckBox changePass_showPass; // Value injected by FXMLLoader

    @FXML // fx:id="forgor_answer"
    private TextField forgor_answer; // Value injected by FXMLLoader

    @FXML // fx:id="forgor_backBtn"
    private Button forgor_backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="forgor_btn"
    private Button forgor_btn; // Value injected by FXMLLoader

    @FXML // fx:id="forgor_email"
    private TextField forgor_email; // Value injected by FXMLLoader

    @FXML // fx:id="forgor_form"
    private AnchorPane forgor_form; // Value injected by FXMLLoader

    @FXML // fx:id="forgor_selectQues"
    private ComboBox<?> forgor_selectQues; // Value injected by FXMLLoader

    @FXML // fx:id="login_btn"
    private Button login_btn; // Value injected by FXMLLoader

    @FXML // fx:id="login_email"
    private TextField login_email; // Value injected by FXMLLoader

    @FXML // fx:id="login_forgorPass"
    private Hyperlink login_forgorPass; // Value injected by FXMLLoader

    @FXML // fx:id="login_form"
    private AnchorPane login_form; // Value injected by FXMLLoader

    @FXML // fx:id="login_pass"
    private PasswordField login_pass; // Value injected by FXMLLoader

    @FXML // fx:id="login_showPass"
    private CheckBox login_showPass; // Value injected by FXMLLoader

    @FXML // fx:id="login_signUpBtn"
    private Button login_signUpBtn; // Value injected by FXMLLoader

    @FXML // fx:id="signup_answer"
    private TextField signup_answer; // Value injected by FXMLLoader

    @FXML // fx:id="signup_btn"
    private Button signup_btn; // Value injected by FXMLLoader

    @FXML // fx:id="signup_cPass"
    private PasswordField signup_cPass; // Value injected by FXMLLoader

    @FXML // fx:id="signup_email"
    private TextField signup_email; // Value injected by FXMLLoader

    @FXML // fx:id="signup_form"
    private AnchorPane signup_form; // Value injected by FXMLLoader

    @FXML // fx:id="signup_logInBtn"
    private Button signup_logInBtn; // Value injected by FXMLLoader

    @FXML // fx:id="signup_pass"
    private PasswordField signup_pass; // Value injected by FXMLLoader

    @FXML // fx:id="signup_selectQues"
    private ComboBox<String> signup_selectQues; // Value injected by FXMLLoader

    @FXML // fx:id="signup_username"
    private TextField signup_username; // Value injected by FXMLLoader

    private Tooltip changePassTooltip;
    private Tooltip loginTooltip;


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert changePass_Pass != null : "fx:id=\"changePass_Pass\" was not injected: check your FXML file 'login-view.fxml'.";
        assert changePass_backBtn != null : "fx:id=\"changePass_backBtn\" was not injected: check your FXML file 'login-view.fxml'.";
        assert changePass_btn != null : "fx:id=\"changePass_btn\" was not injected: check your FXML file 'login-view.fxml'.";
        assert changePass_cPass != null : "fx:id=\"changePass_cPass\" was not injected: check your FXML file 'login-view.fxml'.";
        assert changePass_form != null : "fx:id=\"changePass_form\" was not injected: check your FXML file 'login-view.fxml'.";
        assert changePass_showPass != null : "fx:id=\"changePass_showPass\" was not injected: check your FXML file 'login-view.fxml'.";
        assert forgor_answer != null : "fx:id=\"forgor_answer\" was not injected: check your FXML file 'login-view.fxml'.";
        assert forgor_backBtn != null : "fx:id=\"forgor_backBtn\" was not injected: check your FXML file 'login-view.fxml'.";
        assert forgor_btn != null : "fx:id=\"forgor_btn\" was not injected: check your FXML file 'login-view.fxml'.";
        assert forgor_email != null : "fx:id=\"forgor_email\" was not injected: check your FXML file 'login-view.fxml'.";
        assert forgor_form != null : "fx:id=\"forgor_form\" was not injected: check your FXML file 'login-view.fxml'.";
        assert forgor_selectQues != null : "fx:id=\"forgor_selectQues\" was not injected: check your FXML file 'login-view.fxml'.";
        assert login_btn != null : "fx:id=\"login_btn\" was not injected: check your FXML file 'login-view.fxml'.";
        assert login_email != null : "fx:id=\"login_email\" was not injected: check your FXML file 'login-view.fxml'.";
        assert login_forgorPass != null : "fx:id=\"login_forgorPass\" was not injected: check your FXML file 'login-view.fxml'.";
        assert login_form != null : "fx:id=\"login_form\" was not injected: check your FXML file 'login-view.fxml'.";
        assert login_pass != null : "fx:id=\"login_pass\" was not injected: check your FXML file 'login-view.fxml'.";
        assert login_showPass != null : "fx:id=\"login_showPass\" was not injected: check your FXML file 'login-view.fxml'.";
        assert login_signUpBtn != null : "fx:id=\"login_signUpBtn\" was not injected: check your FXML file 'login-view.fxml'.";
        assert signup_answer != null : "fx:id=\"signup_answer\" was not injected: check your FXML file 'login-view.fxml'.";
        assert signup_btn != null : "fx:id=\"signup_btn\" was not injected: check your FXML file 'login-view.fxml'.";
        assert signup_cPass != null : "fx:id=\"signup_cPass\" was not injected: check your FXML file 'login-view.fxml'.";
        assert signup_email != null : "fx:id=\"signup_email\" was not injected: check your FXML file 'login-view.fxml'.";
        assert signup_form != null : "fx:id=\"signup_form\" was not injected: check your FXML file 'login-view.fxml'.";
        assert signup_logInBtn != null : "fx:id=\"signup_logInBtn\" was not injected: check your FXML file 'login-view.fxml'.";
        assert signup_pass != null : "fx:id=\"signup_pass\" was not injected: check your FXML file 'login-view.fxml'.";
        assert signup_selectQues != null : "fx:id=\"signup_selectQues\" was not injected: check your FXML file 'login-view.fxml'.";
        assert signup_username != null : "fx:id=\"signup_username\" was not injected: check your FXML file 'login-view.fxml'.";
        login_signUpBtn.setOnAction(this::handleLoginButtonClick);
        signup_btn.setOnAction(this::handleSignupButtonClick);
        signup_logInBtn.setOnAction(this::handleSignupButtonClick);
        forgor_btn.setOnAction(this::handleForgorButtonClick);
        forgor_backBtn.setOnAction(this::handleSignupButtonClick);
        changePass_btn.setOnAction(this::handleChangePassButtonClick);
        changePass_backBtn.setOnAction(this::handleForgorPassLinkClick);

        login_forgorPass.setOnAction(this::handleForgorPassLinkClick);
        questions();

        // Initialize tooltips
        changePassTooltip = new Tooltip();
        loginTooltip = new Tooltip();

        // Set tooltip properties
        changePassTooltip.setAutoHide(true);
        loginTooltip.setAutoHide(true);

        // Set action for change password checkbox
        changePass_showPass.setOnAction(event -> togglePasswordVisibility(changePass_showPass, changePass_Pass, changePassTooltip));

        // Set action for login password checkbox
        login_showPass.setOnAction(event -> togglePasswordVisibility(login_showPass, login_pass, loginTooltip));

        // Update the tooltip while typing in change password field
        changePass_Pass.textProperty().addListener((observable, oldValue, newValue) -> {
            if (changePass_showPass.isSelected()) {
                changePassTooltip.setText(newValue);
                changePassTooltip.show(changePass_Pass,
                        changePass_Pass.getScene().getWindow().getX() + changePass_Pass.getLayoutX(),
                        changePass_Pass.getScene().getWindow().getY() + changePass_Pass.getLayoutY() - 25);
            }
        });

        // Update the tooltip while typing in login password field
        login_pass.textProperty().addListener((observable, oldValue, newValue) -> {
            if (login_showPass.isSelected()) {
                loginTooltip.setText(newValue);
                loginTooltip.show(login_pass,
                        login_pass.getScene().getWindow().getX() + login_pass.getLayoutX(),
                        login_pass.getScene().getWindow().getY() + login_pass.getLayoutY() - 25);
            }
        });
    }

    private void handleForgorPassLinkClick(ActionEvent actionEvent) {
        // Set visibility for forgot password form
        login_form.setVisible(false);
        signup_form.setVisible(false);
        changePass_form.setVisible(false);
        forgor_form.setVisible(true);
    }

    private void handleSignupButtonClick(ActionEvent actionEvent) {
        // Set visibility of forms
        login_form.setVisible(true);
        forgor_form.setVisible(false);
        changePass_form.setVisible(false);
        signup_form.setVisible(false);
    }

    private void handleForgorButtonClick(ActionEvent actionEvent) {
        // Set visibility of forms
        login_form.setVisible(false);
        forgor_form.setVisible(false);
        changePass_form.setVisible(true);
        signup_form.setVisible(false);
    }

    private void handleChangePassButtonClick(ActionEvent actionEvent) {
        // Set visibility of forms
        login_form.setVisible(true);
        forgor_form.setVisible(false);
        changePass_form.setVisible(false);
        signup_form.setVisible(false);
    }

    private void handleLoginButtonClick(javafx.event.ActionEvent actionEvent) {
        // Set visibility of forms
        login_form.setVisible(false);
        forgor_form.setVisible(false);
        changePass_form.setVisible(false);
        signup_form.setVisible(true);
    }

    private String[] questionList = {
            "What's your favourite food?",
            "What's your favourite color?",
            "What's your favourite sport?",
            "What's the name of your pet?"
    };

    public void questions() {
        List<String> listQ = new ArrayList<>(Arrays.asList(questionList)); // Create a list from the array

        ObservableList<String> listData = FXCollections.observableArrayList(listQ); // Create an ObservableList
        signup_selectQues.setItems(listData); // Set items in the ComboBox
    }

    private void togglePasswordVisibility(CheckBox checkBox, PasswordField passwordField, Tooltip tooltip) {
        if (checkBox.isSelected()) {
            // Show password in tooltip
            tooltip.setText(passwordField.getText());
            tooltip.show(passwordField,
                    passwordField.getScene().getWindow().getX() + passwordField.getLayoutX(),
                    passwordField.getScene().getWindow().getY() + passwordField.getLayoutY() - 25);
        } else {
            // Hide tooltip when not showing password
            tooltip.hide();
        }
    }
}