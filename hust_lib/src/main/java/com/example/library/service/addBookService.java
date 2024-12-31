package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class addBookService {

    // Method to get the next book ID
    public int getNextBookId() {
        String query = "SELECT MAX(bookid) AS maxId FROM book";
        int maxId = 0;

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                maxId = resultSet.getInt("maxId");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving next book ID: " + e.getMessage());
        }

        return maxId + 1; // Return the next ID
    }

    // Method to save a book
    public void handleSaveAction(Book book) {
        String query = "INSERT INTO book (bookid, title, publisher, quantity, publishyear, worth, author) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the values from the book object
            preparedStatement.setInt(1, book.getBookId());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getPublisher());
            preparedStatement.setInt(4, book.getQuantity());
            preparedStatement.setInt(5, book.getPublishYear());
            preparedStatement.setDouble(6, book.getWorth());
            preparedStatement.setString(7, book.getAuthor()); // Add this line for the author

            // Execute the insert query
            preparedStatement.executeUpdate();
            System.out.println("Book saved successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving book: " + e.getMessage());
        }
    }

    // Method to fetch a book by its ID
    public Book fetchBookById(int bookId) {
        String query = "SELECT * FROM book WHERE bookid = ?";
        Book book = null;

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Create a Book object from the result set
                book = new Book(
                        resultSet.getInt("bookid"),
                        resultSet.getString("title"),
                        resultSet.getString("publisher"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("publishyear"),
                        resultSet.getDouble("worth"),
                        null, // Assuming image is not needed here
                        resultSet.getString("author")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching book by ID: " + e.getMessage());
        }

        return book; // Return the fetched book or null if not found
    }
}