package com.example.library.controller;

import com.example.library.service.addBookService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBookController {

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

    private final addBookService service = new addBookService();

    public void initialize() {
        // Initialization logic, if needed
    }

    public void handleSaveAction(ActionEvent actionEvent) {
        String bookTitle = addBook_bookTitle.getText().trim();
        String publisher = addBook_publisher.getText().trim();
        String author = addBook_author.getText().trim();
        String quantityText = addBook_quantity.getText().trim();
        String priceText = addBook_bookPrice.getText().trim();

        // Validate input fields
        if (bookTitle.isEmpty() || publisher.isEmpty() || author.isEmpty() || quantityText.isEmpty() || priceText.isEmpty()) {
            System.out.println("Vui lòng điền đầy đủ thông tin.");
            return;
        }

        int quantity;
        double price;

        // Parse quantity and price
        try {
            quantity = Integer.parseInt(quantityText);
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            System.err.println("Số lượng (quantity) phải là số nguyên và giá (price) phải là số thực.");
            return;
        }

        try {
            // Save the book details using the service
            service.handleSaveAction(bookTitle, publisher, author, quantity, price);
            System.out.println("Sách đã được lưu thành công!");

            // Close the window after successful save
            Stage stage = (Stage) addBook_saveBtn.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            System.err.println("Lỗi khi lưu sách: " + e.getMessage());
        }
    }

    public void handleCancelAction(ActionEvent actionEvent) {
        // Close the current window when the cancel button is pressed
        Stage stage = (Stage) addBook_cancelBtn.getScene().getWindow();
        stage.close();
    }
}
