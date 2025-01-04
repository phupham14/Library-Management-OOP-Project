package com.example.library.controller;

import com.example.library.model.Customer;
import com.example.library.service.customerService;
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
import com.example.library.service.cartService;
import com.example.library.util.Session;
import java.sql.SQLException;

import static com.example.library.model.Customer.getCustomerId;

public class CartScreenController {

    @FXML
    private Button cart_btnPlaceOrder;

    @FXML
    private Button cart_btnRemove;

    @FXML
    private Label cart_labelTotalCost;

    @FXML
    private TableView<Book> cart_tableView;

    @FXML
    private TableColumn<Book, String> cart_publisher;

    @FXML
    private TableColumn<Book, String> cart_title;

    @FXML
    private TableColumn<Book, Double> cart_worth;

    private ObservableList<Book> cartItems;

    @FXML
    private void initialize() throws SQLException {
        if (cartItems == null) {
            cartItems = FXCollections.observableArrayList(); // Đảm bảo cartItems được khởi tạo
        }

        // Set các giá trị cho TableView
        cart_tableView.setItems(cartItems);

        // Set up table columns to display cart item details
        cart_title.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        cart_publisher.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPublisher()));
        cart_worth.setCellValueFactory(cellData -> new SimpleDoubleProperty().asObject());

        updateTotalCost();
    }

    void loadCartContents(int customerId) throws SQLException {

        // In ra customerId để kiểm tra đầu vào
        System.out.println("Loading cart contents for customer ID: " + customerId);

        // Retrieve all books in the cart for the current customer
        ObservableList<Book> cartContents = cartService.getInstance().getCartContents(customerId);
        // In ra các cuốn sách trong giỏ hàng vào console
        for (Book book : cartContents) {
            System.out.println("Title: " + book.getTitle() + ", Publisher: " + book.getPublisher() + ", Worth: " + book.getWorth());
        }

        // Add retrieved items to the cart
        cartItems.addAll(cartContents);
        System.out.println("Updated cart items: " + cartItems);

        // Set items to the TableView
        cart_tableView.setItems(cartItems);
        System.out.println("Cart items set to TableView.");

        // Update the total cost label
        updateTotalCost();
        System.out.println("Total cost updated.");
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

    public void addBookToCart(Book book, Customer customer) throws SQLException {
        // Persist the book to the database
        cartService.getInstance().addBookToCart(book, customer);
        cartItems.add(book);
        updateTotalCost();
    }

}