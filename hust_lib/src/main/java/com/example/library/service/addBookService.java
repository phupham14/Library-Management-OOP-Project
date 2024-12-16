package com.example.library.service;
import com.example.library.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class addBookService {

    // Phương thức lưu sách vào cơ sở dữ liệu
    public void saveBook(String bookID, String bookTitle, String author, String publisher) {
        String query = "INSERT INTO book (bookID, bookTitle, author, publisher) VALUES (?, ?, ?, ?)";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, bookID);
            preparedStatement.setString(2, bookTitle);
            preparedStatement.setString(3, author);
            preparedStatement.setString(4, publisher);

            preparedStatement.executeUpdate();
            System.out.println("Lưu sách thành công!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lưu sách: " + e.getMessage());
        }
    }
}
