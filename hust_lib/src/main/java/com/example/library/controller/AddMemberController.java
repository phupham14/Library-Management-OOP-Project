package com.example.library.controller;

import com.example.library.service.addMemberService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddMemberController {

<<<<<<< HEAD
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
    private PasswordField addMem_password;

    @FXML
    private Button addMem_saveBtn;

    private addMemberService memberService;

    public AddMemberController() {
        memberService = new addMemberService(); // Initialize the service
    }
=======
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

    @FXML
    private PasswordField addMem_pswd;

    @FXML // fx:id="addMem_saveBtn"
    private Button addMem_saveBtn; // Value injected by FXMLLoader
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60

    // Handle the action when the Cancel button is pressed
    @FXML
    private void handleCancelAction() {
        Stage stage = (Stage) addMem_cancelBtn.getScene().getWindow();
        stage.close();
    }

<<<<<<< HEAD
    // Handle the action when the Save button is pressed
    @FXML
    private void handleSaveAction() {
        String firstName = addMem_firstName.getText();
        String lastName = addMem_lastName.getText();
        String mobile = addMem_contact.getText();
        String email = addMem_email.getText();
        String address = addMem_adress.getText();
        String password = addMem_password.getText();

        try {
            memberService.handleSaveAction(firstName, lastName, mobile, email, address, password);
            // Optionally show a success message
            System.out.println("Member added successfully.");
            handleCancelAction(); // Close the window after saving
        } catch (Exception e) {
            e.printStackTrace();
            // Optionally show an error message
            System.err.println("Error adding member: " + e.getMessage());
=======
    @FXML
    private void handleSaveAction() {
        String firstName = addMem_firstName.getText().trim();
        String lastName = addMem_lastName.getText().trim();
        String mobile = addMem_contact.getText().trim();
        String email = addMem_email.getText().trim();
        String address = addMem_adress.getText().trim();
        String password = addMem_pswd.getText().trim(); // Lấy mật khẩu từ trường nhập liệu

        // Kiểm tra xem các trường có bị trống hay không
        if (firstName.isEmpty() || lastName.isEmpty() || mobile.isEmpty() || email.isEmpty() || address.isEmpty() || password.isEmpty()) {
            System.out.println("Vui lòng điền đầy đủ thông tin.");
            return;
        }

        addMemberService service = new addMemberService();

        try {
            service.handleSaveAction(firstName, lastName, mobile, email, address, password); // Truyền thêm password
            System.out.println("Người dùng đã được lưu thành công!");

            // Đóng cửa sổ sau khi lưu thành công
            Stage stage = (Stage) addMem_saveBtn.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            System.err.println("Lỗi khi lưu người dùng: " + e.getMessage());
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60
        }
    }
}