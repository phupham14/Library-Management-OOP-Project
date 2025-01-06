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

    public String getUserId(String email) {
        String query = "SELECT personid FROM person WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("personid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no user found or an error occurs
    }

    public String getCustomerId(String personid) {
        String query = "SELECT customerid FROM customer JOIN person ON customer.personid = person.personid WHERE person.personid = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int personIdInt = Integer.parseInt(personid); // personid là String
            preparedStatement.setInt(1, personIdInt); // Truyền giá trị kiểu int vào PreparedStatement
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return String.valueOf(resultSet.getInt("customerid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy
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
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // Returns true if the login is valid
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getUserRole(Integer userId) {
        String role = "unknown"; // Giá trị mặc định nếu không tìm thấy vai trò
        String query = "SELECT role FROM person WHERE personid = ?";

        try (
                // Kết nối đến database
                Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            // Gán tham số vào query
            preparedStatement.setInt(1, userId);

            // Thực thi query và lấy kết quả
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    role = resultSet.getString("role");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log lỗi nếu có vấn đề với database
        }

        return role;
    }

}
