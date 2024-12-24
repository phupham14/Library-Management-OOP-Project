package com.example.library.controller;

import com.example.library.service.addMemberService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
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

    @FXML
    private PasswordField addMem_pswd;

    @FXML // fx:id="addMem_saveBtn"
    private Button addMem_saveBtn; // Value injected by FXMLLoader

    // Handle the action when the Cancel button is pressed
    @FXML
    private void handleCancelAction() {
        Stage stage = (Stage) addMem_cancelBtn.getScene().getWindow();
        stage.close();
    }

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
        }
    }
}