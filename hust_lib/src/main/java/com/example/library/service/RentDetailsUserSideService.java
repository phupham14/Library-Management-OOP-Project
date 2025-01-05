package com.example.library.service;

import com.example.library.model.RentDetail;
import com.example.library.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentDetailsUserSideService {

    public List<RentDetail> getRentDetails(int rentId) throws SQLException {
        List<RentDetail> rentDetails = new ArrayList<>();
        String sql = "SELECT rentline.rentlineid, book.title FROM rentline JOIN book ON rentline.bookid = book.bookid WHERE rentline.rentid = ?";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, rentId);

            // Debug: Print the SQL query with the rentId parameter
            System.out.println("SQL Query: " + preparedStatement);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    RentDetail rentDetail = new RentDetail();

                    int rentlineid = resultSet.getInt("rentlineid"); // Get the value
                    System.out.println("Rentline ID from database: " + rentlineid); // Print it

                    rentDetail.setRentlineId(resultSet.getInt("rentlineid"));
                    rentDetail.setBookTitle(resultSet.getString("title"));
                    rentDetails.add(rentDetail);

                    System.out.println("RentDetail object: " + rentDetail); // Print the object
                }
            }
        }
        return rentDetails;
    }
}