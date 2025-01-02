package com.example.library.controller;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import com.example.library.service.loginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

    @FXML // fx:id="forgor_backBtn"
    private Button forgor_backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="forgor_btn"
    private Button forgor_btn; // Value injected by FXMLLoader

    @FXML // fx:id="forgor_email"
    private TextField forgor_email; // Value injected by FXMLLoader

    @FXML // fx:id="forgor_form"
    private AnchorPane forgor_form; // Value injected by FXMLLoader

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

    @FXML // fx:id="signup_firstname"
    private TextField  signup_firstName; // Value injected by FXMLLoader

    @FXML
    private TextField  signup_lastName; // Value injected by FXMLLoader

    @FXML
    private Label successLabel;

    @FXML
    private Label errorLabel;

    private Tooltip changePassTooltip;
    private Tooltip loginTooltip;

    private com.example.library.service.loginService loginService = new loginService();

    public void onSwitchToLoginAs() {
        String enteredEmail = signup_email.getText(); // Assuming you have a TextField for the email
        String enteredPassword = signup_pass.getText(); // Assuming you have a PasswordField for the password

        try {
<<<<<<< HEAD
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
=======
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
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60
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

    // Helper method to show an error message to the user
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
<<<<<<< HEAD
=======
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
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60
        login_signUpBtn.setOnAction(this::handleLoginButtonClick);
        signup_btn.setOnAction(this::handleSignupButtonClick);
        signup_logInBtn.setOnAction(this::handleSignupButtonClick);
        forgor_btn.setOnAction(this::handleForgorButtonClick);
        forgor_backBtn.setOnAction(this::handleSignupButtonClick);
        changePass_btn.setOnAction(this::handleChangePassButtonClick);
        changePass_backBtn.setOnAction(this::handleForgorPassLinkClick);
<<<<<<< HEAD
        login_btn.setOnAction(this::performLogin);
=======
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60

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

    private boolean isValidEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    private void handleForgorPassLinkClick(ActionEvent actionEvent) {
        // Set visibility for forgot password form
        login_form.setVisible(false);
        signup_form.setVisible(false);
        changePass_form.setVisible(false);
        forgor_form.setVisible(true);
    }

    public LoginController() {
        this.loginService = new loginService();
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

        if (isRegistered) {
            showSuccess("Signup successful!");
            // Tiến hành các bước sau khi đăng ký thành công (ví dụ: chuyển sang trang đăng nhập hoặc trang chủ)
        } else {
            showError("An error occurred during signup. Please try again.");
        }
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

<<<<<<< HEAD
    private void performLogin(ActionEvent actionEvent) {
        String enteredEmail = login_email.getText();
        String enteredPassword = login_pass.getText();

        // Use the loginService to validate the login
        if (loginService.validateLogin(enteredEmail, enteredPassword)) {
            // Show success message
            showSuccess("Login successful!");

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

=======
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60
    private void togglePasswordVisibility(CheckBox checkBox, PasswordField passwordField, Tooltip tooltip) {
        if (checkBox.isSelected()) {
            tooltip.setText(passwordField.getText());
            tooltip.show(passwordField,
                    passwordField.getScene().getWindow().getX() + passwordField.getLayoutX(),
                    passwordField.getScene().getWindow().getY() + passwordField.getLayoutY() - 25);
        } else {
            tooltip.hide();
        }
    }


    // Hàm hiển thị thông báo thành công
    private void showSuccess(String message) {
        if (successLabel != null) {
            successLabel.setText(message);
            successLabel.setStyle("-fx-text-fill: green;");
        } else {
            System.err.println("Error: successLabel is not initialized.");
        }
    }

}