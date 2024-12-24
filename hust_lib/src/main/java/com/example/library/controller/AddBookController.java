package com.example.library.controller;

import com.example.library.service.addBookService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBookController {

    @FXML // fx:id="addBook_author"
    private TextField addBook_author; // Value injected by FXMLLoader

    @FXML // fx:id="addBook_bookID"
    private TextField addBook_bookID; // Value injected by FXMLLoader

    @FXML // fx:id="addBook_bookTitle"
    private TextField addBook_bookTitle; // Value injected by FXMLLoader

    @FXML // fx:id="addBook_cancelBtn"
    private Button addBook_cancelBtn; // Value injected by FXMLLoader

    @FXML // fx:id="addBook_publisher"
    private TextField addBook_publisher; // Value injected by FXMLLoader

    @FXML // fx:id="addBook_saveBtn"
    private Button addBook_saveBtn; // Value injected by FXMLLoader

    public void handleSaveAction(ActionEvent actionEvent) {
        String bookIDText = addBook_bookID.getText().trim();
        String bookTitle = addBook_bookTitle.getText().trim();
        String author = addBook_author.getText().trim();
        String publisher = addBook_publisher.getText().trim();

        // Kiểm tra xem các trường có bị trống hay không
        if (bookIDText.isEmpty() || bookTitle.isEmpty() || author.isEmpty() || publisher.isEmpty()) {
            System.out.println("Vui lòng điền đầy đủ thông tin.");
            return;
        }

        int bookID;
        try {
            bookID = Integer.parseInt(bookIDText);
        } catch (NumberFormatException e) {
            System.err.println("Mã sách (bookID) phải là một số nguyên hợp lệ.");
            return;
        }

        addBookService service = new addBookService();

        try {
            // Truyền bookID dạng Integer vào Service
            service.handleSaveAction(bookID, bookTitle, author, publisher);
            System.out.println("Sách đã được lưu thành công!");

            // Đóng cửa sổ sau khi lưu thành công
            Stage stage = (Stage) addBook_saveBtn.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            System.err.println("Lỗi khi lưu sách: " + e.getMessage());
        }
    }


    public void handleCancelAction(ActionEvent actionEvent) {
        // Đóng cửa sổ hiện tại khi người dùng nhấn nút "Cancel"
        Stage stage = (Stage) addBook_cancelBtn.getScene().getWindow();
        stage.close();
    }
}