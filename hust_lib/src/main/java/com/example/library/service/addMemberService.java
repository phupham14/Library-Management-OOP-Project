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
        String insertQuery = "INSERT INTO employee (firstname, lastname, phonenumber, email, address, password, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String debugQuery = "SELECT address, role FROM employee WHERE email = ?";

        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
             PreparedStatement debugStatement = connection.prepareStatement(debugQuery)) {

            // Insert the new member
            insertStatement.setString(1, firstName);
            insertStatement.setString(2, lastName);
            insertStatement.setString(3, mobile);
            insertStatement.setString(4, email);
            insertStatement.setString(5, address);
            insertStatement.setString(6, password);
            insertStatement.setString(7, "admin");

            int rowsInserted = insertStatement.executeUpdate();
            if (rowsInserted <= 0) {
                throw new Exception("Failed to save the member.");
            }

            // Fetch and print the role and address after insertion
            debugStatement.setString(1, email);
            try (var resultSet = debugStatement.executeQuery()) {
                if (resultSet.next()) {
                    String savedAddress = resultSet.getString("address");
                    String savedRole = resultSet.getString("role");

                    System.out.println("Member saved successfully.");
                    System.out.println("Saved Address: " + savedAddress);
                    System.out.println("Saved Role: " + savedRole);
                } else {
                    throw new Exception("Failed to retrieve saved member data for debugging.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error while saving the member to the database: " + e.getMessage());
        }
    }

}
