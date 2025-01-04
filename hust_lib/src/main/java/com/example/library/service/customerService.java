package com.example.library.service;

import com.example.library.model.Customer;
import com.example.library.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class customerService {

    // Phương thức này lấy thông tin khách hàng từ bảng customer và bảng person
    public static Customer getCustomerById(int personId) {
        Customer customer = null;
        String query = "SELECT p.*, c.blockrent, c.customerid FROM person p " +
                "JOIN customer c ON p.personid = c.personid WHERE p.personid = ?";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            System.out.println("Fetching customer with ID: " + personId);

            preparedStatement.setInt(1, personId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Customer found");
                customer = new Customer(
                        resultSet.getInt("personid"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("address"),
                        resultSet.getString("phonenumber"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getDate("dob") != null ? resultSet.getDate("dob").toLocalDate() : null,
                        resultSet.getString("gender") != null ? resultSet.getString("gender").charAt(0) : 'U',
                        resultSet.getString("role"),
                        resultSet.getTimestamp("createddate").toLocalDateTime(),
                        resultSet.getTimestamp("lastactivedate").toLocalDateTime(),
                        resultSet.getInt("customerid"),
                        resultSet.getBoolean("blockrent")
                );
            } else {
                System.out.println("No customer found with ID: " + personId);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching customer details: " + e.getMessage());
        }

        return customer;
    }

}
