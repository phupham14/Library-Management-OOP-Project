package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.CartService; // Import the CartService
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

//    private void handleIssueBook() {
//        Book selectedBook = user_tableView.getSelectionModel().getSelectedItem();
//
//        if (selectedBook != null) {
//            // Debug: Print the selected book title
//            System.out.println("Selected book title: " + selectedBook.getTitle());
//            System.out.println("Selected book ID: " + selectedBook.getBookId());
//
//            // Reduce the book quantity in the database using the title
//            try {
//                bookService.issueBookByTitle(selectedBook.getTitle());
//                // Add the book to the cart
//                CartService.getInstance().addToCart(selectedBook);
//            } catch (Exception e) {
//                System.err.println("Error issuing book: " + e.getMessage());
//                return; // Exit if there's an issue
//            }
//
//            // Update the local book list
//            selectedBook.setQuantity(selectedBook.getQuantity() - 1);
//            loadAllBooks(); // Reload books to reflect the change
//        } else {
//            // Handle no book selected case (optional)
//            System.out.println("No book selected.");
//        }
//    }

    private void handleIssueBook() {
        Book selectedBook = user_tableView.getSelectionModel().getSelectedItem();

        if (selectedBook != null) {
            // Debug: Print the selected book title and ID
            System.out.println("Selected book title: " + selectedBook.getTitle());
            System.out.println("Selected book ID: " + selectedBook.getBookId());

            // Reduce the book quantity in the database using the book ID
            try {
                bookService.issueBookById(selectedBook.getBookId());
                // Add the book to the cart
                CartService.getInstance().addToCart(selectedBook);
            } catch (Exception e) {
                System.err.println("Error issuing book: " + e.getMessage());
                return; // Exit if there's an issue
            }

            // Update the local book list
            selectedBook.setQuantity(selectedBook.getQuantity() - 1);
            loadAllBooks(); // Reload books to reflect the change
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

        // Debug: Print all book details including Id and other data types
        System.out.println("Loaded books:");
        for (Book book : books) {
            System.out.println("Book ID: " + book.getBookId() +
                    ", Title: " + book.getTitle() +
                    ", Publisher: " + book.getPublisher() +
                    ", Publisher ID: " + book.getPublisherId() +
                    ", Quantity: " + book.getQuantity() +
                    ", Publish Year: " + book.getPublishYear() +
                    ", Price: " + book.getWorth() +
                    ", Author: " + book.getAuthor() +
                    ", Image: " + book.getImage());
        }
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