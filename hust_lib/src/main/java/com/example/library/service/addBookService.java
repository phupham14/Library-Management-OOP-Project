package com.example.library.service;

import com.example.library.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class addBookService {

    public void handleSaveAction(Integer bookId, String title, String author, String publisher) {
        String query = "INSERT INTO book (bookid, title, author, publisher) VALUES (?, ?, ?, ?)";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the values from the method parameters
            preparedStatement.setInt(1, bookId);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, author);
            preparedStatement.setString(4, publisher);

            // Execute the insert query
            preparedStatement.executeUpdate();
            System.out.println("Book saved successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving book: " + e.getMessage());
        }
    }
}
