package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                        resultSet.getInt("publisherid"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("publishyear"),
                        resultSet.getBigDecimal("worth"),
                        resultSet.getString("image"),
                        resultSet.getString("author"),
                        resultSet.getString("publisher")
                );
                books.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error searching for books by title: " + e.getMessage());
        }

        return books;
    }
}
