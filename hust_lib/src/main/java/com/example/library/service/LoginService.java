package com.example.library.service;

import com.example.library.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LoginService {

    public void saveUser(String firstname, String lastname, String email, String password) {
        String query = "INSERT INTO person (firstname, lastname, email, password) VALUES (?, ?, ?, ?)";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);

            preparedStatement.executeUpdate();
            System.out.println("User saved successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving user: " + e.getMessage());
        }
    }
}