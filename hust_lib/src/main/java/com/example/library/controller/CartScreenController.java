package com.example.library.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.example.library.model.Book;
import com.example.library.service.cartService; // Import CartService

import java.sql.SQLException;

public class CartScreenController {

    @FXML
    private Button cart_btnPlaceOrder;

    @FXML
    private Button cart_btnRemove;

    @FXML
    private Label cart_labelTotalCost;

    @FXML
    private TableColumn<Book, String> cart_publisher;

    @FXML
    private TableView<Book> cart_tableView;

    @FXML
    private TableColumn<Book, String> cart_title;

    @FXML
    private TableColumn<Book, Double> cart_worth;

    private ObservableList<Book> cartItems;

    @FXML
    private void initialize() {
        // Load cart items from the database using CartService
        cartItems = FXCollections.observableArrayList();
        cart_tableView.setItems(cartItems);

        // Set up table columns to display cart item details
        cart_title.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        cart_publisher.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPublisher()));
        cart_worth.setCellValueFactory(cellData -> new SimpleDoubleProperty().asObject());

        // Update total cost
        updateTotalCost();
    }

    // Method to update the total cost label
    private void updateTotalCost() {
        double totalCost = cartItems.stream().mapToDouble(book -> book.getWorth().doubleValue()).sum();
        cart_labelTotalCost.setText("Total Cost: $" + totalCost);
    }

    private void removeBookFromCart(int bookId, int customerId) throws SQLException {
        Book selectedBook = cart_tableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            // Remove the book from the database
            cartService.getInstance().removeBookFromCart(bookId, customerId);
            cartItems.remove(selectedBook);
            updateTotalCost();
        }
    }

    public void addBookToCart(Book book, int customerId) throws SQLException {
        // Persist the book to the database
        cartService.getInstance().addBookToCart(book, customerId);
        cartItems.add(book);
        updateTotalCost();
    }

}