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

    public int confirm_rent(int customerId) throws SQLException {
        // SQL statements for database operations
        String checkQuantitySql = "SELECT Quantity FROM Book WHERE BookID = ?";
        String checkCartQuantitySql = "SELECT COUNT(*) FROM Cart WHERE BookID = ? AND CustomerID = ?";
        String updateBookQuantitySql = "UPDATE Book SET Quantity = Quantity - 1 WHERE BookID = ?";
        String getMaxRentIdSql = "SELECT COALESCE(MAX(RentID), 0) FROM Rent";
        String insertRentSql = "INSERT INTO Rent (RentID, CustomerID, BorrowDate, DueDate) VALUES (?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '30 days')";
        String getMaxRentlineIdSql = "SELECT COALESCE(MAX(RentlineID), 0) FROM Rentline";
        String insertRentlineSql = "INSERT INTO Rentline (RentlineID, BookID, RentID) VALUES (?, ?, ?)";
        String updateCustomerBlockRentSql = "UPDATE Customer SET BlockRent = TRUE WHERE CustomerID = ?";

        // Variables to hold IDs and quantities
        int rentId, rentlineId, bookId, quantityInStock, quantityInCart;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
            connection.setAutoCommit(false); // Start transaction

            // Check if sufficient quantity in stock for each book in the cart
            statement = connection.prepareStatement("SELECT BookID FROM Cart WHERE CustomerID = ?");
            statement.setInt(1, customerId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                bookId = resultSet.getInt("BookID");

                // Get quantity in stock
                PreparedStatement checkQuantityStmt = connection.prepareStatement(checkQuantitySql);
                checkQuantityStmt.setInt(1, bookId);
                ResultSet quantityInStockResult = checkQuantityStmt.executeQuery();
                quantityInStockResult.next();
                quantityInStock = quantityInStockResult.getInt("Quantity");

                // Get quantity in cart
                PreparedStatement checkCartQuantityStmt = connection.prepareStatement(checkCartQuantitySql);
                checkCartQuantityStmt.setInt(1, bookId);
                checkCartQuantityStmt.setInt(2, customerId);
                ResultSet quantityInCartResult = checkCartQuantityStmt.executeQuery();
                quantityInCartResult.next();
                quantityInCart = quantityInCartResult.getInt(1);

                if (quantityInCart > quantityInStock) {
                    throw new SQLException("Insufficient quantity of book with ID " + bookId + " in stock.");
                }
            }

            // Reduce book quantity in stock
            statement = connection.prepareStatement("SELECT BookID FROM Cart WHERE CustomerID = ?");
            statement.setInt(1, customerId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                bookId = resultSet.getInt("BookID");
                PreparedStatement updateBookQuantityStmt = connection.prepareStatement(updateBookQuantitySql);
                updateBookQuantityStmt.setInt(1, bookId);
                updateBookQuantityStmt.executeUpdate();
            }

            // Generate RentID
            statement = connection.prepareStatement(getMaxRentIdSql);
            resultSet = statement.executeQuery();
            resultSet.next();
            rentId = resultSet.getInt(1) + 1;

            // Insert into Rent table
            PreparedStatement insertRentStmt = connection.prepareStatement(insertRentSql);
            insertRentStmt.setInt(1, rentId);
            insertRentStmt.setInt(2, customerId);
            insertRentStmt.executeUpdate();

            // Generate RentlineID
            statement = connection.prepareStatement(getMaxRentlineIdSql);
            resultSet = statement.executeQuery();
            resultSet.next();
            rentlineId = resultSet.getInt(1) + 1;

            // Insert into Rentline table
            statement = connection.prepareStatement("SELECT BookID FROM Cart WHERE CustomerID = ?");
            statement.setInt(1, customerId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                bookId = resultSet.getInt("BookID");
                PreparedStatement insertRentlineStmt = connection.prepareStatement(insertRentlineSql);
                insertRentlineStmt.setInt(1, rentlineId);
                insertRentlineStmt.setInt(2, bookId);
                insertRentlineStmt.setInt(3, rentId);
                insertRentlineStmt.executeUpdate();
                rentlineId++;
            }

            // Update Customer's BlockRent status
            PreparedStatement updateCustomerBlockRentStmt = connection.prepareStatement(updateCustomerBlockRentSql);
            updateCustomerBlockRentStmt.setInt(1, customerId);
            updateCustomerBlockRentStmt.executeUpdate();

            connection.commit(); // Commit transaction
            return rentId;
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback(); // Rollback on error
            }
            System.err.println("Error in confirm_rent: " + e.getMessage());
            throw e;
        } finally {
            // Close resources in reverse order
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.err.println("Error closing ResultSet: " + e.getMessage());
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println("Error closing PreparedStatement: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error closing Connection: " + e.getMessage());
                }
            }
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
