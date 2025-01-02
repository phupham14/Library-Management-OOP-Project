package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.cartService; // Import the CartService
import com.example.library.service.searchBookService;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class UserPageController {

    @FXML
    private TextField user_authorFind;

    @FXML
    private TextField user_bookNameFind;

    @FXML
    private TableColumn<Book, String> user_bookTitle;

    @FXML
    private TableColumn<Book, String> user_bookPublisher;

    @FXML
    private TableColumn<Book, Integer> user_bookQuantity;

    @FXML
    private TableColumn<Book, Double> user_bookPrice;

    @FXML
    private TableView<Book> user_tableView;

    @FXML
    private Button user_findBookBtn;

    @FXML
    private Button user_checkCart;

    @FXML
    private Button user_issueBookBtn;

    private final searchBookService bookService = new searchBookService();
    private ObservableList<Book> bookList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Set up table columns to display book details
        user_bookTitle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        user_bookPublisher.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPublisher()));
        user_bookQuantity.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());
        user_bookPrice.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getWorth()).asObject());

        // Load all books on page open
        bookService.getAllBooks();

        // Set up search button action
        user_findBookBtn.setOnAction(event -> handleSearch());

        // Set up issue book button action
        user_issueBookBtn.setOnAction(event -> handleIssueBook());

        // Set up check cart button action
        user_checkCart.setOnAction(event -> {
            try {
                onOpenCart();
            } catch (IOException e) {
                System.err.println("Error opening cart: " + e.getMessage());
            }
        });
    }

    public void onOpenCart() throws IOException {
        // Load the cart FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/library/view/Cart.fxml"));
        Parent root = loader.load();

        // Show the cart
        Stage openCart = new Stage();
        openCart.setScene(new Scene(root));
        openCart.setResizable(true);
        openCart.setTitle("Cart Page");
        openCart.show();
    }

    private void handleIssueBook() {
        // Get the selected book from the TableView
        Book selectedBook = user_tableView.getSelectionModel().getSelectedItem();

        if (selectedBook != null) {
            // Ensure the selected book has a sufficient quantity
            if (selectedBook.getQuantity() <= 0) {
                System.out.println("Book is out of stock.");
                return; // Exit if there are no books left
            }

            // Debug: Print the selected book details
            System.out.println("Selected book title: " + selectedBook.getTitle());
            System.out.println("Selected book ID: " + selectedBook.getBookId());

            // Retrieve the customer ID (replace this with your actual method)
            int customerId = getCurrentCustomerId(); // Implement this method to get the current customer ID
            if (customerId == -1) {
                System.out.println("Invalid customer ID.");
                return;
            }

            try {
                // Issue the book by updating the database
                bookService.issueBookById(selectedBook.getBookId());

                // Optionally, add the book to the customer's cart
                cartService.getInstance().addBookToCart(selectedBook, customerId);

                // Update the local book quantity and reload the view
                selectedBook.setQuantity(selectedBook.getQuantity() - 1);
                loadAllBooks(); // Refresh the TableView to reflect changes

                System.out.println("Book issued successfully.");
            } catch (Exception e) {
                System.err.println("Error issuing book: " + e.getMessage());
            }
        } else {
            // Handle the case when no book is selected
            System.out.println("No book selected. Please select a book to issue.");
        }
    }

    /**
     * Dummy method to get the current customer ID.
     * Replace this with the actual implementation for retrieving customer ID.
     */
    private int getCurrentCustomerId() {
        // Replace with logic to get the actual customer ID
        // Returning -1 for invalid ID
        return -1; // Example placeholder
    }


    private void loadAllBooks() {
        List<Book> books = bookService.getAllBooks();
        bookList.clear();
        bookList.addAll(books);
        user_tableView.setItems(bookList);
    }


    private void handleSearch() {
        String bookName = user_bookNameFind.getText().trim();

        if (!bookName.isEmpty()) {
            List<Book> books = bookService.searchBooksByTitle(bookName);
            bookList.clear();
            bookList.addAll(books);
            user_tableView.setItems(bookList);
        } else {
            // If the text field is empty, reload all books
            loadAllBooks();
        }
    }
}