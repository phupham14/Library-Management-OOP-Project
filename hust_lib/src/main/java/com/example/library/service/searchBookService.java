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

    /**
     * Searches for books by title.
     * @param title The title or part of the title to search for.
     * @return A list of books matching the search keyword.
     */
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
                            resultSet.getInt("publisherid"),
                            resultSet.getInt("quantity"),
                            resultSet.getInt("publishyear"),
                            worth,
                            resultSet.getString("image"),
                            resultSet.getString("author"),
                            resultSet.getString("publisher")
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

    /**
     * Retrieves all books from the database.
     * @return A list of all books.
     */
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM book";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                double worth = resultSet.getBigDecimal("worth") != null ? resultSet.getBigDecimal("worth").doubleValue() : 0.0;

                Book book = new Book(
                        resultSet.getInt("bookid"),
                        resultSet.getString("title"),
                        resultSet.getInt("publisherid"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("publishyear"),
                        worth,
                        resultSet.getString("image"),
                        resultSet.getString("author"),
                        resultSet.getString("publisher")
                );

                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving all books: " + e.getMessage());
        }

        return books;
    }

    /**
     * Deletes a book from the database.
     * @param book The book to be deleted.
     */
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

    /**
     * Updates an existing book in the database.
     * @param book The book object containing updated data.
     */
    public void updateBook(Book book) {
        String query = "UPDATE book SET title = ?, author = ?, publisherid = ?, quantity = ?, publishyear = ?, worth = ?, image = ? WHERE bookid = ?";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the values from the book object
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getPublisherId());
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

    /**
     * Retrieves the publisher by ID.
     * @param publisherId The ID of the publisher.
     * @return The publisher name if found, otherwise null.
     */
    public String getPublisherById(int publisherId) {
        String query = "SELECT publisher_name FROM publisher WHERE publisherid = ?";
        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, publisherId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("publisher_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving publisher by ID: " + e.getMessage());
        }

        return null; // Return null if not found
    }

    /**
     * Finds a book by its title.
     * @param title The title of the book to find.
     * @return The book if found, otherwise null.
     */
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
                int publisherId = rs.getInt("publisherid");
                int quantity = rs.getInt("quantity");
                int publishYear = rs.getInt("publishyear");
                BigDecimal worthBD = rs.getBigDecimal("worth");
                double worth = (worthBD != null) ? worthBD.doubleValue() : 0.0; // Handle null case
                String image = rs.getString("image");
                String author = rs.getString("author");
                String publisher = rs.getString("publisher");

                book = new Book(bookId, bookTitle, publisherId, quantity, publishYear, worth, image, author, publisher);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding book by title: " + e.getMessage(), e);
        }

        return book;
    }
}