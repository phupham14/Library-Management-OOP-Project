package com.example.library.service;

import com.example.library.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginService {
    private Connection connection;

    public loginService() {
        // Initialize the database connection (replace with your actual database URL, username, password)
        String dbName = "hust_lib";
        String user = "hustlib_admin";
        String password = "hustlib_admin";
        this.connection = ConnectionUtil.getInstance().connect_to_db(dbName, user, password);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    // Đăng ký tài khoản mới với firstname, lastname, email và password.
    public boolean registerUser(String firstName, String lastName, String email, String password) {
        if (isEmailAlreadyRegistered(email)) {
            return false; // Email đã tồn tại
        }

        String query = "INSERT INTO person (firstname, lastname, email, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password); // Lưu mật khẩu dạng văn bản thuần
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Kiểm tra xem email đã được đăng ký chưa.
    public boolean isEmailAlreadyRegistered(String email) {
        String query = "SELECT * FROM person WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // Email đã tồn tại nếu có kết quả
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xác minh thông tin đăng nhập.
    public boolean validateLogin(String email, String password) {
        String query = "SELECT * FROM person WHERE email = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password); // So sánh mật khẩu dạng văn bản thuần
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // Trả về true nếu thông tin đăng nhập hợp lệ
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60
