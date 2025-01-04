package com.example.library.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.library.service.loginService;
import com.example.library.util.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField changePass_Pass;

    @FXML
    private Button changePass_backBtn;

    @FXML
    private Button changePass_btn;

    @FXML
    private PasswordField changePass_cPass;

    @FXML
    private AnchorPane changePass_form;

    @FXML
    private CheckBox changePass_showPass;

    @FXML
    private Button forgor_backBtn;

    @FXML
    private Button forgor_btn;

    @FXML
    private TextField forgor_email;

    @FXML
    private AnchorPane forgor_form;

    @FXML
    private Button login_btn;

    @FXML
    private TextField login_email;

    @FXML
    private Hyperlink login_forgorPass;

    @FXML
    private AnchorPane login_form;

    @FXML
    private PasswordField login_pass;

    @FXML
    private CheckBox login_showPass;

    @FXML
    private Button login_signUpBtn;

    @FXML
    private Button signup_btn;

    @FXML
    private PasswordField signup_cPass;

    @FXML
    private TextField signup_email;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private Button signup_logInBtn;

    @FXML
    private PasswordField signup_pass;

    @FXML
    private TextField signup_firstName;

    @FXML
    private TextField signup_lastName;

    @FXML
    private Label successLabel;

    @FXML
    private Label errorLabel;

    private Tooltip changePassTooltip;
    private Tooltip loginTooltip;

    private loginService loginService = new loginService();

    public LoginController() {
        this.loginService = new loginService();
    }

    public void onSwitchToLoginAs() {
        String enteredEmail = signup_email.getText(); // Assuming you have a TextField for the email
        String enteredPassword = signup_pass.getText(); // Assuming you have a PasswordField for the password

        try {
            // Load the FXML file for the next view
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/library/view/loginAs.fxml")));

            // Create a new Scene with the loaded content
            Scene scene = new Scene(root);

            // Get the current window (Stage) and set the new scene
            Stage window = (Stage) login_btn.getScene().getWindow();
            window.setScene(scene);

            // Optionally, you can set the window to be resizable
            window.setResizable(true);

            // Adjust the window size to fit the content
            window.sizeToScene();

            // Show an error message if credentials are invalid
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Login Failed");
//                alert.setHeaderText("Invalid Credentials");
//                alert.setContentText("The email or password you entered is incorrect.");
//                alert.showAndWait();
        } catch (IOException e) {
            // Handle IOException (e.g., show error message in UI)
            showError("An error occurred while loading the login page. Please try again.");
        }
    }

    @FXML
    void initialize() {
        assert changePass_Pass != null : "fx:id=\"changePass_Pass\" was not injected: check your FXML file 'login-view.fxml'.";
        assert changePass_backBtn != null : "fx:id=\"changePass_backBtn\" was not injected: check your FXML file 'login-view.fxml'.";
        assert changePass_btn != null : "fx:id=\"changePass_btn\" was not injected: check your FXML file 'login-view.fxml'.";
        assert changePass_cPass != null : "fx:id=\"changePass_cPass\" was not injected: check your FXML file 'login-view.fxml'.";
        assert changePass_form != null : "fx:id=\"changePass_form\" was not injected: check your FXML file 'login-view.fxml'.";
        assert changePass_showPass != null : "fx:id=\"changePass_showPass\" was not injected: check your FXML file 'login-view.fxml'.";
        assert forgor_backBtn != null : "fx:id=\"forgor_backBtn\" was not injected: check your FXML file 'login-view.fxml'.";
        assert forgor_btn != null : "fx:id=\"forgor_btn\" was not injected: check your FXML file 'login-view.fxml'.";
        assert forgor_email != null : "fx:id=\"forgor_email\" was not injected: check your FXML file 'login-view.fxml'.";
        assert forgor_form != null : "fx:id=\"forgor_form\" was not injected: check your FXML file 'login-view.fxml'.";
        assert login_btn != null : "fx:id=\"login_btn\" was not injected: check your FXML file 'login-view.fxml'.";
        assert login_email != null : "fx:id=\"login_email\" was not injected: check your FXML file 'login-view.fxml'.";
        assert login_forgorPass != null : "fx:id=\"login_forgorPass\" was not injected: check your FXML file 'login-view.fxml'.";
        assert login_form != null : "fx:id=\"login_form\" was not injected: check your FXML file 'login-view.fxml'.";
        assert login_pass != null : "fx:id=\"login_pass\" was not injected: check your FXML file 'login-view.fxml'.";
        assert login_showPass != null : "fx:id=\"login_showPass\" was not injected: check your FXML file 'login-view.fxml'.";
        assert login_signUpBtn != null : "fx:id=\"login_signUpBtn\" was not injected: check your FXML file 'login-view.fxml'.";
        assert signup_btn != null : "fx:id=\"signup_btn\" was not injected: check your FXML file 'login-view.fxml'.";
        assert signup_cPass != null : "fx:id=\"signup_cPass\" was not injected: check your FXML file 'login-view.fxml'.";
        assert signup_email != null : "fx:id=\"signup_email\" was not injected: check your FXML file 'login-view.fxml'.";
        assert signup_form != null : "fx:id=\"signup_form\" was not injected: check your FXML file 'login-view.fxml'.";
        assert signup_logInBtn != null : "fx:id=\"signup_logInBtn\" was not injected: check your FXML file 'login-view.fxml'.";
        assert signup_pass != null : "fx:id=\"signup_pass\" was not injected: check your FXML file 'login-view.fxml'.";
        assert signup_firstName != null : "fx:id=\"signup_username\" was not injected: check your FXML file 'login-view.fxml'.";
        assert signup_lastName != null : "fx:id=\"signup_username\" was not injected: check your FXML file 'login-view.fxml'.";

        login_signUpBtn.setOnAction(this::handleLoginButtonClick);
        signup_btn.setOnAction(this::handleSignupButtonClick);
        signup_logInBtn.setOnAction(this::handleSignupButtonClick);
        forgor_btn.setOnAction(this::handleForgorButtonClick);
        forgor_backBtn.setOnAction(this::handleForgorPassLinkClick);
        changePass_btn.setOnAction(this::handleChangePassButtonClick);
        changePass_backBtn.setOnAction(this::handleForgorPassLinkClick);
        login_btn.setOnAction(this::performLogin);

        // Initialize tooltips
        changePassTooltip = new Tooltip();
        loginTooltip = new Tooltip();
        changePassTooltip.setAutoHide(true);
        loginTooltip.setAutoHide(true);

        // Password visibility toggle
        changePass_showPass.setOnAction(event -> togglePasswordVisibility(changePass_showPass, changePass_Pass, changePassTooltip));
        login_showPass.setOnAction(event -> togglePasswordVisibility(login_showPass, login_pass, loginTooltip));

        // Tooltip for password fields
        changePass_Pass.textProperty().addListener((observable, oldValue, newValue) -> {
            if (changePass_showPass.isSelected()) {
                changePassTooltip.setText(newValue);
                changePassTooltip.show(changePass_Pass,
                        changePass_Pass.getScene().getWindow().getX() + changePass_Pass.getLayoutX(),
                        changePass_Pass.getScene().getWindow().getY() + changePass_Pass.getLayoutY() - 25);
            }
        });

        login_pass.textProperty().addListener((observable, oldValue, newValue) -> {
            if (login_showPass.isSelected()) {
                loginTooltip.setText(newValue);
                loginTooltip.show(login_pass,
                        login_pass.getScene().getWindow().getX() + login_pass.getLayoutX(),
                        login_pass.getScene().getWindow().getY() + login_pass.getLayoutY() - 25);
            }
        });
    }

    private void handleLoginButtonClick(javafx.event.ActionEvent actionEvent) {
        // Set visibility of forms
        login_form.setVisible(false);
        forgor_form.setVisible(false);
        changePass_form.setVisible(false);
        signup_form.setVisible(true);
    }

    private void handleSignupButtonClick(ActionEvent event) {
        login_form.setVisible(true);
        signup_form.setVisible(false);
        changePass_form.setVisible(false);
        forgor_form.setVisible(false);

        // Lấy giá trị từ các trường nhập liệu
        String firstName = signup_firstName.getText();
        String lastName = signup_lastName.getText();
        String email = signup_email.getText();
        String password = signup_pass.getText();
        String confirmPassword = signup_cPass.getText();

        // Kiểm tra các trường nhập liệu không được để trống
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showError("All fields are required!");
            return;
        }

        // Kiểm tra mật khẩu và xác nhận mật khẩu có trùng khớp không
        if (!password.equals(confirmPassword)) {
            showError("Passwords do not match!");
            return;
        }

        // Kiểm tra tính hợp lệ của địa chỉ email
        if (!isValidEmail(email)) {
            showError("Invalid email address!");
            return;
        }

        // Kiểm tra xem email đã được đăng ký chưa
        if (loginService.isEmailAlreadyRegistered(email)) {
            showError("Email is already registered!");
            return;
        }

        // Gọi phương thức đăng ký từ LoginService để lưu thông tin người dùng vào cơ sở dữ liệu
        boolean isRegistered = loginService.registerUser(firstName, lastName, email, password);

        if (loginService.validateLogin(email, password)) {
            // Show success message
            showSuccess("Login successful!");

            // Load the next scene or controller
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/loginAs.fxml"));
                Parent root = loader.load();

//                // Optional: Pass data to the next controller
//                LoginAsController loginasController = loader.getController();
//                loginasController.setUserEmail(enteredEmail); // Example: pass user email to the next controller

                // Get the current stage and set the new scene
                Stage stage = (Stage) login_btn.getScene().getWindow(); // Replace 'currentButton' with a reference to any UI element in your current scene
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                showError("Error loading next screen: " + e.getMessage());
            }
        } else {
            // Show error message
            showError("Invalid email or password!");
        }

    }

    private void handleForgorButtonClick(ActionEvent actionEvent) {
        // Set visibility of forms
        login_form.setVisible(false);
        forgor_form.setVisible(false);
        changePass_form.setVisible(true);
        signup_form.setVisible(false);
    }

    private void handleForgorPassLinkClick(ActionEvent event) {
        // Show the forgot password form
        login_form.setVisible(false);
        signup_form.setVisible(false);
        changePass_form.setVisible(false);
        forgor_form.setVisible(true);
    }

    private void handleChangePassButtonClick(ActionEvent actionEvent) {
        // Set visibility of forms
        login_form.setVisible(true);
        forgor_form.setVisible(false);
        changePass_form.setVisible(false);
        signup_form.setVisible(false);
    }

    private void togglePasswordVisibility(CheckBox checkbox, PasswordField passwordField, Tooltip tooltip) {
        if (checkbox.isSelected()) {
            passwordField.setPromptText(passwordField.getText());
            passwordField.setText("");
            passwordField.setPromptText(passwordField.getPromptText());
        } else {
            passwordField.setText(tooltip.getText());
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    private void performLogin(ActionEvent actionEvent) {
        String enteredEmail = login_email.getText();
        String enteredPassword = login_pass.getText();

        // Use the loginService to validate the login
        if (loginService.validateLogin(enteredEmail, enteredPassword)) {

            // Show success message
            showSuccess("Login successful!");

            // Retrieve and print the user ID
            String userId = loginService.getUserId(enteredEmail);
            System.out.println("Person ID: " + userId);  // Print user ID
            Session.getInstance().setPersonId(Integer.parseInt(userId));
            String customerId = loginService.getCustomerId(userId);
            System.out.println("Customer ID:" + customerId);
            Session.getInstance().setCustomerId(Integer.parseInt(customerId));
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/library/view/loginAs.fxml"));
                Parent root = loader.load();

                // Get the current stage and set the new scene
                Stage stage = (Stage) login_btn.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                showError("Error loading next screen: " + e.getMessage());
            }

        } else {
            // Show error message for invalid credentials
            showError("Invalid email or password. Please try again.");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccess(String message) {
        if (successLabel != null) {
            successLabel.setText(message);
            successLabel.setStyle("-fx-text-fill: green;");
        } else {
            System.err.println("Error: successLabel is not initialized.");
        }
    }

}