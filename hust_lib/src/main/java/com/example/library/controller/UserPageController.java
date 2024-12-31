package com.example.library.controller;

import com.example.library.model.Book;
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
import java.util.Objects;

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
    private TableColumn<Book, Integer> user_publishYear;

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

    public void onOpenCart() throws IOException {
        // Load the FXML file for the admin page
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/library/view/Cart.fxml")));

        // Create a new Stage for the new window
        Stage openCart = new Stage();

        // Create a new Scene with the loaded content
        Scene scene = new Scene(root);

        // Set the scene to the new window
        openCart.setScene(scene);
        openCart.setResizable(true);
        openCart.setTitle("Cart Page");
        openCart.show();
    }

    @FXML
    private void initialize() {
        // Set up table columns to display book details
        user_bookTitle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        user_bookPublisher.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPublisher()));
        user_bookQuantity.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());
        user_publishYear.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPublishYear()).asObject());
        user_bookPrice.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getWorth()).asObject());

        // Load all books on page open
        loadAllBooks();

        // Set up search button action
        user_findBookBtn.setOnAction(event -> handleSearch());

        // Set up issue book button action
        user_issueBookBtn.setOnAction(event -> handleIssueBook());
    }

    private void handleIssueBook() {
        Book selectedBook = user_tableView.getSelectionModel().getSelectedItem();

        if (selectedBook != null) {
            // Debug: Print the selected book ID
            System.out.println("Selected book ID: " + selectedBook.getId());

            // Reduce the book quantity in the database
            try {
                bookService.issueBook(selectedBook.getId());
            } catch (Exception e) {
                System.err.println("Error issuing book: " + e.getMessage());
                return; // Exit if there's an issue
            }

            // Update the local book list
            selectedBook.setQuantity(selectedBook.getQuantity() - 1);
            loadAllBooks(); // Reload books to reflect the change

            // Add the book to the cart
            CartScreenController cartController = new CartScreenController(); // Ensure this is properly initialized
            cartController.addToCart(selectedBook);
        } else {
            // Handle no book selected case (optional)
            System.out.println("No book selected.");
        }
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