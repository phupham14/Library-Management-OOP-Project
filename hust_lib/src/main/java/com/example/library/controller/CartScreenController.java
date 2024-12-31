package com.example.library.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.example.library.model.Book;

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

    private ObservableList<Book> cartItems = FXCollections.observableArrayList();

    // Method to add a book to the cart
    public void addToCart(Book book) {
        cartItems.add(book);
        updateCartTableView();
    }

    // Method to update the table view and total cost
    private void updateCartTableView() {
        cart_tableView.setItems(cartItems);

        double totalCost = cartItems.stream().mapToDouble(Book::getWorth).sum();
        cart_labelTotalCost.setText("Total Cost: $" + totalCost);
    }

    @FXML
    private void handleRemoveBook() {
        Book selectedBook = cart_tableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            cartItems.remove(selectedBook);
            updateCartTableView();
        }
    }
}