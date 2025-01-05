// HistoryService.java
package com.example.library.service;

import com.example.library.model.Rent;
import com.example.library.util.ConnectionUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistoryService {

    public List<Rent> getRentHistory(int customerId) throws SQLException {
        List<Rent> rents = new ArrayList<>();
        String query = "SELECT * FROM rent WHERE customerid = ?";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, customerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Debug: Print the values retrieved from the ResultSet
                    System.out.println("Rent ID from ResultSet: " + resultSet.getInt("rentid"));
                    System.out.println("Customer ID from ResultSet: " + resultSet.getInt("customerid"));
                    System.out.println("Borrow Date from ResultSet: " + resultSet.getObject("borrowdate", LocalDate.class));
                    System.out.println("Due Date from ResultSet: " + resultSet.getObject("duedate", LocalDate.class));
                    System.out.println("Is Collected from ResultSet: " + resultSet.getBoolean("iscollected"));

                    Rent rent = new Rent(
                            resultSet.getInt("rentid"),
                            resultSet.getInt("customerid"),
                            resultSet.getObject("borrowdate", LocalDate.class),
                            resultSet.getObject("duedate", LocalDate.class),
                            resultSet.getBoolean("iscollected")
                    );

                    // Debug: Print the created Rent object
                    System.out.println("Rent object created: " + rent);

                    rents.add(rent);
                }
            }
        }
        return rents;
    }

    public void cancelRent(int customerId) throws SQLException {
        String query = "{CALL cancel_rent(?)}";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setInt(1, customerId);
            callableStatement.execute();
        }
    }

}