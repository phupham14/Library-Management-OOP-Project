package com.example.library.service;

import com.example.library.model.Customer;
import com.example.library.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class customerService {

    // Phương thức này lấy thông tin khách hàng từ bảng customer và bảng person
    public static Customer getCustomerById(int customerId) {
        Customer customer = null;
        String query = "SELECT p.*, c.blockrent FROM person p " +
                "JOIN customer c ON p.personid = c.customerid WHERE c.customerid = ?";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            System.out.println("Fetching customer with ID: " + customerId);

            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Customer found, creating Customer object...");
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
                        resultSet.getTimestamp("createdate").toLocalDateTime(),
                        resultSet.getTimestamp("lastactivedate").toLocalDateTime(),
                        resultSet.getInt("customerid"),
                        resultSet.getBoolean("blockrent")
                );
            } else {
                System.out.println("No customer found with ID: " + customerId);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching customer details: " + e.getMessage());
        }

        return customer;
    }

//    public static Customer getCustomerByCustomerId(int customerId) {
//        String query = "SELECT * FROM customer WHERE customerid = ?";
//        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//
//            preparedStatement.setInt(1, customerId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                boolean blockRent = resultSet.getBoolean("blockrent");
//                return new Customer(customerId, blockRent);  // Trả về đối tượng Customer nếu tìm thấy
//            } else {
//                return null;  // Không tìm thấy customer
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

}
