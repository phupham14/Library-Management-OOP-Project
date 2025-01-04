package com.example.library.service;

import com.example.library.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.library.model.Book;
import com.example.library.util.ConnectionUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class cartService {
    private static cartService instance;

    // Singleton instance
    private cartService() {}

    public static cartService getInstance() {
        if (instance == null) {
            instance = new cartService();
        }
        return instance;
    }

    // Add a book to the cart
    public void addBookToCart(Book book, Customer customer) throws SQLException {
        String sql = "INSERT INTO cart (customerid, bookid, title, publisher, author, worth) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customer.getCustomerId());
            statement.setInt(2, book.getBookId());
            statement.setString(3, book.getTitle());
            statement.setString(4, book.getPublisher());
            statement.setString(5, book.getAuthor());
            statement.setBigDecimal(6, book.getWorth());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding book to cart: " + e.getMessage());
            throw e;
        }
    }

    // Retrieve all books in the cart for a specific customer
    public ObservableList<Book> getCartContents(int customerId) throws SQLException {
        ObservableList<Book> cartItems = FXCollections.observableArrayList();
        String sql = "SELECT bookid, title, publisher, author, worth FROM cart WHERE customerid = ?";
        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setBookId(resultSet.getInt("bookid"));
                book.setTitle(resultSet.getString("title"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setAuthor(resultSet.getString("author"));
                book.setWorth(BigDecimal.valueOf(resultSet.getDouble("worth")));
                cartItems.add(book);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching cart contents: " + e.getMessage());
            throw e;
        }
        return cartItems;
    }

    // Remove a book from the cart
    public void removeBookFromCart(int bookId, int customerId) throws SQLException {
        String sql = "DELETE FROM cart WHERE customerid = ? AND bookid = ?";
        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, customerId); // Đặt customerId
            statement.setInt(2, bookId); // Đặt bookId

            int rowsAffected = statement.executeUpdate(); // Thực thi truy vấn

            if (rowsAffected > 0) {
                System.out.println("Book removed successfully from database.");
            } else {
                System.out.println("No book found to remove with ID: " + bookId);
            }
        } catch (SQLException e) {
            System.err.println("Error removing book from cart: " + e.getMessage());
            throw e;
        }
    }


    // Calculate the total cost for a specific customer
    public double calculateTotalCost(int customerId) throws SQLException {
        String sql = "SELECT SUM(worth) AS total_cost FROM cart WHERE customer_id = ?";
        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("total_cost");
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total cost: " + e.getMessage());
            throw e;
        }
        return 0.0;
    }
}
