package com.example.library.controller;

import com.example.library.model.Customer;
import com.example.library.service.customerService;
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
    private TableColumn<Book, String> cart_author;

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
        cart_author.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));

        //updateTotalCost();
    }

    void loadCartContents(int customerId) throws SQLException {

        // In ra customerId để kiểm tra đầu vào
        System.out.println("Loading cart contents for customer ID: " + customerId);

        // Retrieve all books in the cart for the current customer
        ObservableList<Book> cartContents = cartService.getInstance().getCartContents(customerId);
        // In ra các cuốn sách trong giỏ hàng vào console
        for (Book book : cartContents) {
            System.out.println("Title: " + book.getTitle() + ", Publisher: " + book.getPublisher() + ", Author: " + book.getAuthor());
        }

        // Add retrieved items to the cart
        cartItems.addAll(cartContents);
        System.out.println("Updated cart items: " + cartItems);

        // Set items to the TableView
        cart_tableView.setItems(cartItems);
        System.out.println("Cart items set to TableView.");

        // Update the total cost label
        //updateTotalCost();
        System.out.println("Total cost updated.");
    }

    // Method to update the total cost label
//    private void updateTotalCost() {
//        double totalCost = cartItems.stream().mapToDouble(book -> book.getWorth().doubleValue()).sum();
//        cart_labelTotalCost.setText("Total Cost: " + totalCost);
//    }

    @FXML
    private void removeBookFromCart() throws SQLException {
        // Lấy sách được chọn từ bảng
        Book selectedBook = cart_tableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            int bookId = selectedBook.getBookId(); // Lấy ID sách
            int customerId = Session.getInstance().getCustomerId(); // Lấy ID khách hàng

            try {
                // Gọi service để xóa sách khỏi cơ sở dữ liệu
                cartService.getInstance().removeBookFromCart(bookId, customerId);

                // Xóa sách khỏi giao diện
                cartItems.remove(selectedBook);

                // Cập nhật tổng chi phí
                //updateTotalCost();

                System.out.println("Book removed from cart: " + bookId);
            } catch (SQLException e) {
                System.err.println("Error removing book from cart: " + e.getMessage());
            }
        } else {
            System.out.println("No book selected to remove.");
        }
    }

    public void addBookToCart(Book book, Customer customer) throws SQLException {
        // Persist the book to the database
        cartService.getInstance().addBookToCart(book, customer);
        cartItems.add(book);
        //updateTotalCost();
    }

    @FXML
    private void placeOrder() {
        try {
            // Lấy customerId từ Session
            int customerId = com.example.library.util.Session.getInstance().getCustomerId();

            // Gọi hàm confirm_rent trong cartService
            cartService.getInstance().confirm_rent(customerId);

            // Xóa sách khỏi giỏ hàng sau khi đặt hàng thành công
            cartItems.clear();
            cart_tableView.refresh();

            // Hiển thị thông báo đặt hàng thành công (hoặc xử lý lỗi nếu cần)
            System.out.println("Order placed successfully for customer ID: " + customerId);
        } catch (SQLException e) {
            System.err.println("Error placing order: " + e.getMessage());
            // Xử lý lỗi (ví dụ: hiển thị thông báo lỗi cho người dùng)
        }
    }
}