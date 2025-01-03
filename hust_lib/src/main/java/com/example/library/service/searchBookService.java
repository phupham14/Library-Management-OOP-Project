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
     * @param keyword The title or part of the title to search for.
     * @return A list of books matching the search keyword.
     */
    public List<Book> searchBooksByTitle(String keyword) {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM book WHERE title LIKE ?";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + keyword + "%"); // Search by title with wildcards

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("bookid"),
                        resultSet.getString("title"),
                        resultSet.getString("publisher"), // Ensure publisher is correctly retrieved
                        resultSet.getInt("quantity"),
                        resultSet.getInt("publishyear"),
                        resultSet.getBigDecimal("worth"),
                        resultSet.getString("image"),
                        resultSet.getString("author")
                );
                books.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error searching for books by title: " + e.getMessage());
        }

        return books;
    }

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

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM book";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("bookid"),
                        resultSet.getString("title"),
                        resultSet.getString("publisher"), // Ensure this is correct
                        resultSet.getInt("quantity"),
                        resultSet.getBigDecimal("worth"),
                        resultSet.getString("image"),
                        resultSet.getString("author")
                );
                books.add(book);
            }

            System.out.println("Total books retrieved: " + books.size()); // Debug line

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving all books: " + e.getMessage());
        }

        return books; // Return the list of books
    }

    public void deleteBook(Book book) {
        String query = "DELETE FROM book WHERE bookid = ?";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, book.getBookId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No book found with ID: " + book.getBookId());
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting book: " + e.getMessage());
        }
    }

    public void updateBook(Book book) {
        String query = "UPDATE book SET title = ?, author = ?, publisher = ?, quantity = ?, worth = ?, image = ? WHERE bookid = ?";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getPublisher());
            preparedStatement.setInt(4, book.getQuantity());
            preparedStatement.setBigDecimal(5, book.getWorth()); // Corrected index
            preparedStatement.setString(6, book.getImage());
            preparedStatement.setInt(7, book.getBookId());

            preparedStatement.executeUpdate();
            System.out.println("Book updated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating book: " + e.getMessage());
        }
    }

    public Book findBookByTitle(String title) {
        Book book = null;
        String query = "SELECT * FROM book WHERE title = ?";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, title);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                book = new Book(
                        rs.getInt("bookid"),
                        rs.getString("title"),
                        rs.getString("publisher"),
                        rs.getInt("quantity"),
                        rs.getBigDecimal("worth"),
                        rs.getString("image"),
                        rs.getString("author")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding book by title: " + e.getMessage(), e);
        }

        return book;
    }
}