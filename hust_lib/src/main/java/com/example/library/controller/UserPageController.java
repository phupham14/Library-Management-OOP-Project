package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.cartService;
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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class UserPageController {

    @FXML
    private TextField user_authorFind;

    @FXML
    private TextField user_bookNameFind;

    @FXML
    private Label user_username;

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
        user_bookTitle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        user_bookPublisher.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPublisher()));
        user_bookQuantity.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());
        user_bookPrice.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getWorth() != null ? cellData.getValue().getWorth().doubleValue() : 0.0).asObject());

        loadAllBooks();

        user_findBookBtn.setOnAction(event -> handleSearch());
        user_issueBookBtn.setOnAction(event -> handleIssueBook());
        user_checkCart.setOnAction(event -> {
            try {
                onOpenCart();
            } catch (IOException e) {
                System.err.println("Error opening cart: " + e.getMessage());
            }
        });
    }

    public void onOpenCart() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/library/view/Cart.fxml"));
        Parent root = loader.load();
        Stage openCart = new Stage();
        openCart.setScene(new Scene(root));
        openCart.setResizable(true);
        openCart.setTitle("Cart Page");
        openCart.show();
    }

    private void handleIssueBook() {
        Book selectedBook = user_tableView.getSelectionModel().getSelectedItem();

        if (selectedBook != null) {
            if (selectedBook.getQuantity() <= 0) {
                System.out.println("Book is out of stock.");
                return;
            }

            int customerId = getCurrentCustomerId();
            if (customerId == -1) {
                System.out.println("Invalid customer ID.");
                return;
            }

            try {
                bookService.issueBookById(selectedBook.getBookId());
                cartService.getInstance().addBookToCart(selectedBook, customerId);
                selectedBook.setQuantity(selectedBook.getQuantity() - 1);
                loadAllBooks();
                System.out.println("Book issued successfully.");
            } catch (Exception e) {
                System.err.println("Error issuing book: " + e.getMessage());
            }
        } else {
            System.out.println("No book selected. Please select a book to issue.");
        }
    }

    private int getCurrentCustomerId() {
        return -1; // Replace with actual implementation
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
            loadAllBooks();
        }
    }
}
