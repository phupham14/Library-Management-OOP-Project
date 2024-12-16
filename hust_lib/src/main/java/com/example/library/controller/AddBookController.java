package com.example.library.controller;

import com.example.library.service.addBookService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBookController {

    @FXML
    private TextField addBook_author;

    @FXML
    private TextField addBook_bookID;

    @FXML
    private TextField addBook_bookTitle;

    @FXML
    private Button addBook_cancelBtn;

    @FXML
    private TextField addBook_publisher;

    @FXML
    private Button addBook_saveBtn;

    // Tạo đối tượng BookService
    private final addBookService bookService = new addBookService();

    // Xử lý khi bấm nút Cancel
    @FXML
    private void handleCancelAction() {
        Stage stage = (Stage) addBook_cancelBtn.getScene().getWindow();
        stage.close();
    }

    // Xử lý khi bấm nút Save
    @FXML
    private void handleSaveAction() {
        String bookID = addBook_bookID.getText();
        String bookTitle = addBook_bookTitle.getText();
        String author = addBook_author.getText();
        String publisher = addBook_publisher.getText();

        // Kiểm tra dữ liệu đầu vào
        if (bookID.isEmpty() || bookTitle.isEmpty() || author.isEmpty() || publisher.isEmpty()) {
            System.out.println("Vui lòng điền đầy đủ thông tin!");
            return;
        }

        // Gọi BookService để lưu sách
        try {
            bookService.saveBook(bookID, bookTitle, author, publisher);

            // Đóng cửa sổ sau khi lưu
            Stage stage = (Stage) addBook_saveBtn.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi khi lưu sách: " + e.getMessage());
        }
    }
}
