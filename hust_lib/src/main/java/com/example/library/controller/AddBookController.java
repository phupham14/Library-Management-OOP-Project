package com.example.library.controller;

import com.example.library.service.addBookService;
import com.example.library.model.Book;
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
    private TextField addBook_publishYear; // Publish Year field
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
        String publishYearText = addBook_publishYear.getText().trim();
        String priceText = addBook_bookPrice.getText().trim();

        // Validate fields
        if (bookTitle.isEmpty() || publisher.isEmpty() || author.isEmpty() ||
                quantityText.isEmpty() || publishYearText.isEmpty() || priceText.isEmpty()) {
            System.out.println("Please fill in all the fields.");
            return;
        }

        int quantity;
        int publishYear;
        double price;

        try {
            quantity = Integer.parseInt(quantityText);
            publishYear = Integer.parseInt(publishYearText);
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            System.err.println("Invalid input: " + e.getMessage());
            return;
        }

        try {
            int bookID = service.getNextBookId(); // Get the next available book ID
            System.out.println("Next available book ID: " + bookID); // Debug statement to print the book ID
            Book newBook = new Book(bookID, bookTitle, publisher, quantity, publishYear, price, null, author); // Ensure author is passed
            service.handleSaveAction(newBook);
            System.out.println("Book saved successfully!");

            // Fetch the saved book by ID to get the correct ID from the database
            Book savedBook = service.fetchBookById(bookID);
            if (savedBook != null) {
                newBook.setBookId(savedBook.getBookId()); // Update the ID
                System.out.println("Updated Book ID: " + newBook.getBookId()); // Debug statement
            } else {
                System.err.println("Error: No book found with ID: " + bookID);
            }

            // Debug: Print out the book details after fetching
            System.out.println("Saved Book Details:");
            System.out.println("ID: " + newBook.getBookId());
            System.out.println("Title: " + newBook.getTitle());
            System.out.println("Publisher: " + newBook.getPublisher());
            System.out.println("Quantity: " + newBook.getQuantity());
            System.out.println("Publish Year: " + newBook.getPublishYear());
            System.out.println("Price: " + newBook.getWorth());
            System.out.println("Author: " + newBook.getAuthor());

            // Close the window after successful save
            Stage stage = (Stage) addBook_saveBtn.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            System.err.println("Error while saving book: " + e.getMessage());
        }
    }

    public void handleCancelAction(ActionEvent actionEvent) {
        // Close the current window when the cancel button is pressed
        Stage stage = (Stage) addBook_cancelBtn.getScene().getWindow();
        stage.close();
    }
}