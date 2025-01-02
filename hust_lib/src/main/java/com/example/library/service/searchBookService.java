package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.util.ConnectionUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class searchBookService {

    // Searches for books by title.
    public List<Book> searchBooksByTitle(String title) {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM book WHERE title LIKE ?";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + title + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    BigDecimal worthValue = resultSet.getBigDecimal("worth");
                    double worth = (worthValue != null) ? worthValue.doubleValue() : 0.0;

                    Book book = new Book(
                            resultSet.getInt("bookid"),
                            resultSet.getString("title"),
                            resultSet.getString("publisher"), // Changed from publisherId to publisher
                            resultSet.getInt("quantity"),
                            resultSet.getInt("publishyear"),
                            worth,
                            resultSet.getString("image"),
                            resultSet.getString("author")
                    );

                    books.add(book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error searching for books by title: " + e.getMessage());
        }

        return books;
    }

    //Issues a book by its ID, reducing the quantity in the database.
    public void issueBookById(int bookId) throws Exception {
        String query = "UPDATE book SET quantity = quantity - 1 WHERE bookid = ? AND quantity > 0";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, bookId);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new Exception("No book found with the ID: " + bookId + " or no copies available.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error issuing book by ID: " + e.getMessage());
        }
    }

    //Retrieves all books from the database.
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT bookid, title, publisher, quantity, worth, image, author FROM book";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                System.out.println("Row Data: " +
                        resultSet.getInt("bookid") + ", " +
                        resultSet.getString("title") + ", " +
                        resultSet.getString("publisher") + ", " +
                        resultSet.getInt("quantity") + ", " +
                        resultSet.getBigDecimal("worth") + ", " +
                        resultSet.getString("image") + ", " +
                        resultSet.getString("author")
                );

                double worth = resultSet.getBigDecimal("worth") != null ? resultSet.getBigDecimal("worth").doubleValue() : 0.0;

                Book book = new Book(
                        resultSet.getInt("bookid"),
                        resultSet.getString("title"),
                        resultSet.getString("publisher"),
                        resultSet.getInt("quantity"),
                        worth,
                        resultSet.getString("image"),
                        resultSet.getString("author")
                );

                books.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving all books: " + e.getMessage());
        }

        return books;
    }

     // Deletes a book from the database.
    public void deleteBook(Book book) {
        String query = "DELETE FROM book WHERE bookid = ?";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, book.getBookId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting book: " + e.getMessage());
        }
    }


    // Updates an existing book in the database.
    public void updateBook(Book book) {
        String query = "UPDATE book SET title = ?, author = ?, publisher = ?, quantity = ?, worth = ?, image = ? WHERE bookid = ?";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the values from the book object
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getPublisher()); // Changed to use publisher
            preparedStatement.setInt(4, book.getQuantity());
            preparedStatement.setInt(5, book.getPublishYear());
            preparedStatement.setBigDecimal(6, BigDecimal.valueOf(book.getWorth()));
            preparedStatement.setString(7, book.getImage());
            preparedStatement.setInt(8, book.getBookId());

            // Execute the update query
            preparedStatement.executeUpdate();
            System.out.println("Book updated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating book: " + e.getMessage());
        }
    }

    // Finds a book by its title.
    public Book findBookByTitle(String title) {
        Book book = null;
        String query = "SELECT * FROM book WHERE title = ?";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, title);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int bookId = rs.getInt("bookid");
                String bookTitle = rs.getString("title");
                String publisher = rs.getString("publisher"); // Changed from publisherId to publisher
                int quantity = rs.getInt("quantity");
                BigDecimal worthBD = rs.getBigDecimal("worth");
                double worth = (worthBD != null) ? worthBD.doubleValue() : 0.0; // Handle null case
                String image = rs.getString("image");
                String author = rs.getString("author");

                book = new Book(bookId, bookTitle, publisher, quantity, worth, image, author);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding book by title: " + e.getMessage(), e);
        }

        return book;
    }
}