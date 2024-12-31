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
import com.example.library.service.CartService; // Import CartService

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
        cartItems = FXCollections.observableArrayList(CartService.getInstance().getCartItemsFromDatabase());
        cart_tableView.setItems(cartItems);

        // Set up table columns to display cart item details
        cart_title.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        cart_publisher.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPublisher()));
        cart_worth.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getWorth()).asObject());

        // Update total cost
        updateTotalCost();
    }

    // Method to update the total cost label
    private void updateTotalCost() {
        double totalCost = cartItems.stream().mapToDouble(Book::getWorth).sum();
        cart_labelTotalCost.setText("Total Cost: $" + totalCost);
    }

    private void handleRemoveBook() {
        Book selectedBook = cart_tableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            // Remove the book from the database
            CartService.getInstance().removeBookFromCart(selectedBook);
            cartItems.remove(selectedBook);
            updateTotalCost();
        }
    }

    public void addToCart(Book book) {
        // Persist the book to the database
//        CartService.getInstance().addBookToCart(book);
        cartItems.add(book);
        updateTotalCost();
    }
}