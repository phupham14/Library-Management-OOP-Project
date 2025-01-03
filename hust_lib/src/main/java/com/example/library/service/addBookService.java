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
    public void handleSaveAction(String bookTitle, String publisher, String author, int quantity, double price) {
        String query = "INSERT INTO book (title, publisher, author, quantity, worth) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Gán giá trị từ tham số vào câu lệnh SQL
            preparedStatement.setString(1, bookTitle);
            preparedStatement.setString(2, publisher);
            preparedStatement.setString(3, author);
            preparedStatement.setInt(4, quantity);
            preparedStatement.setDouble(5, price);

            // Thực thi câu lệnh SQL
            preparedStatement.executeUpdate();
            System.out.println("Sách đã được lưu thành công!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lưu sách: " + e.getMessage());
        }
    }

    // Method to fetch a book by its ID
    public Book fetchBookById(int bookId) {
        String query = "SELECT * FROM book WHERE bookid = ?";
        Book book = null;
        return book;
    }
}
