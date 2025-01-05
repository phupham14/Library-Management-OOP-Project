package com.example.library.service;

import com.example.library.model.Rent;
import com.example.library.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentService {

    // Method to fetch all rent records
    public List<Rent> fetchAllRents() {
        List<Rent> rents = new ArrayList<>();

        String query = "SELECT Rent.*, Person.firstname, Person.lastname " +
                "FROM Rent " +
                "JOIN Customer ON Rent.CustomerID = Customer.CustomerID " +
                "JOIN Person ON Person.PersonID = Customer.PersonID;";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Rent rent = new Rent();

                rent.setRentId(resultSet.getInt("RentID"));
                rent.setFirstname(resultSet.getString("firstname"));
                rent.setLastname(resultSet.getString("lastname"));

                // Ensure LocalDate conversion is correct
                rent.setBorrowDate(Date.valueOf(resultSet.getDate("BorrowDate").toLocalDate()));
                rent.setDueDate(Date.valueOf(resultSet.getDate("DueDate").toLocalDate()));

                rent.setIsCollected(resultSet.getBoolean("IsCollected"));
                rent.setReturnAll(resultSet.getBoolean("ReturnAll"));

                rents.add(rent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching rents: " + e.getMessage());
        }

        return rents;
    }

    // Method to update rent status in the database
    public void updateRent(Rent rent) {
        String query = "UPDATE Rent SET ReturnAll = ?, IsCollected = ? WHERE RentID = ?";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setBoolean(1, rent.getReturnAll());
            preparedStatement.setBoolean(2, rent.getIsCollected());
            preparedStatement.setInt(3, rent.getRentId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating rent: " + e.getMessage());
        }
    }

    public static void checkRent() {
        String sql = "{call check_rent()}";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             CallableStatement cstmt = connection.prepareCall(sql)) {

            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error calling check_rent procedure: " + e.getMessage());
        }
    }
}