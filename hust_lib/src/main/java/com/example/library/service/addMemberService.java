package com.example.library.service;

import com.example.library.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addMemberService {
    private Connection connection;

    public addMemberService() {
        // Initialize the database connection (replace with your actual database URL, username, password)
        String dbName = "hust_lib";
        String user = "hustlib_admin";
        String password = "hustlib_admin";
        this.connection = ConnectionUtil.getInstance().connect_to_db(dbName, user, password);
    }

    public void handleSaveAction(String firstName, String lastName, String mobile, String email, String address, String password) throws Exception {
        String query = "INSERT INTO employee (firstname, lastname, phonenumber, email, address, password, role) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, mobile);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, password); // Mật khẩu lưu thẳng hoặc đã băm
            preparedStatement.setString(7, "Employee"); // Role mặc định là Employee

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted <= 0) {
                throw new Exception("Không thể lưu người dùng.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Lỗi khi thêm người dùng vào cơ sở dữ liệu: " + e.getMessage());
        }
    }
}
