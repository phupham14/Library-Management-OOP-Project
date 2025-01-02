package com.example.library.controller;

import com.example.library.service.addBookService;
<<<<<<< HEAD
import com.example.library.model.Book;
=======
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBookController {

<<<<<<< HEAD
    @FXML
    private TextField addBook_bookTitle; // Book Title field
    @FXML
    private TextField addBook_bookPrice; // Book Price field
    @FXML
    private TextField addBook_quantity; // Book Quantity field
    @FXML
    private TextField addBook_publisher; // Publisher field
    @FXML
    private TextField addBook_author; // Author field
    @FXML
    private Button addBook_cancelBtn; // Cancel button
    @FXML
    private Button addBook_saveBtn; // Save button

    private addBookService service = new addBookService();

    public void initialize() {
        // Initialization logic if needed
    }

    public void handleSaveAction(ActionEvent actionEvent) {
        String bookTitle = addBook_bookTitle.getText().trim();
        String publisher = addBook_publisher.getText().trim();
        String author = addBook_author.getText().trim();
        String quantityText = addBook_quantity.getText().trim();
        String priceText = addBook_bookPrice.getText().trim();

        // Kiểm tra xem các trường có bị trống hay không
        if (bookTitle.isEmpty() || publisher.isEmpty() || author.isEmpty() ||
                quantityText.isEmpty() || priceText.isEmpty()) {
=======
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
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60
            System.out.println("Vui lòng điền đầy đủ thông tin.");
            return;
        }

<<<<<<< HEAD
        int quantity;
        double price;

        // Chuyển đổi và kiểm tra giá trị của quantity và price
        try {
            quantity = Integer.parseInt(quantityText);
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            System.err.println("Số lượng (quantity) phải là số nguyên và giá (price) phải là số thực.");
=======
        int bookID;
        try {
            bookID = Integer.parseInt(bookIDText);
        } catch (NumberFormatException e) {
            System.err.println("Mã sách (bookID) phải là một số nguyên hợp lệ.");
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60
            return;
        }

        addBookService service = new addBookService();

        try {
<<<<<<< HEAD
            // Truyền các tham số đầy đủ vào Service
            service.handleSaveAction(bookTitle, publisher, author, quantity, price);
=======
            // Truyền bookID dạng Integer vào Service
            service.handleSaveAction(bookID, bookTitle, author, publisher);
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60
            System.out.println("Sách đã được lưu thành công!");

            // Đóng cửa sổ sau khi lưu thành công
            Stage stage = (Stage) addBook_saveBtn.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            System.err.println("Lỗi khi lưu sách: " + e.getMessage());
        }
    }


<<<<<<< HEAD

    public void handleCancelAction(ActionEvent actionEvent) {
        // Close the current window when the cancel button is pressed
=======
    public void handleCancelAction(ActionEvent actionEvent) {
        // Đóng cửa sổ hiện tại khi người dùng nhấn nút "Cancel"
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60
        Stage stage = (Stage) addBook_cancelBtn.getScene().getWindow();
        stage.close();
    }
}