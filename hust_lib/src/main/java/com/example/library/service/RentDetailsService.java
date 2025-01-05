package com.example.library.service;

import com.example.library.model.RentDetail;
import com.example.library.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentDetailsService {

    // Method to fetch rent details based on rentId
    public List<RentDetail> fetchRentDetails(int rentId) {
        String sql = "SELECT rentline.rentlineid, rentline.returndate, rentline.rentlinestatus, book.title " +
                "FROM rentline " +
                "JOIN book ON rentline.bookid = book.bookid WHERE rentline.rentid = ?";

        List<RentDetail> rentDetails = new ArrayList<>();

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, rentId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                RentDetail detail = new RentDetail();

                detail.setRentlineId(rs.getInt("rentlineid")); // Accessing the correct column
                detail.setBookTitle(rs.getString("title")); // Accessing the book title
                detail.setReturnDate(rs.getDate("returndate") != null ? rs.getDate("returndate").toLocalDate() : null); // Handling return date
                detail.setRentStatus(rs.getString("rentlinestatus")); // Accessing rent line status

                rentDetails.add(detail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching rent details: " + e.getMessage());
        }

        return rentDetails;
    }

    // Method to update the rent status in the database
    public void updateRentStatus(int rentlineId, String newStatus) {
        String sql = "UPDATE rentline SET rentlinestatus = ? WHERE rentlineid = ?";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, newStatus);
            pstmt.setInt(2, rentlineId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating rent status: " + e.getMessage());
        }
    }

    // Method to get the rentId from the rentline table based on rentlineId
    public int getRentIdFromRentline(int rentlineId) {
        String sql = "SELECT rentid FROM rentline WHERE rentlineid = ?";
        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, rentlineId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("rentid");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error getting rentId: " + e.getMessage());
        }
        return 0; // Or handle the case where rentId is not found
    }

    // Method to call the receive_book stored procedure
    public void receiveBook(int rentId, int rentlineId, String rentlineStatus) {
        String sql = "{CALL receive_book(?, ?, ?)}";
        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             CallableStatement cstmt = connection.prepareCall(sql)) {

            cstmt.setInt(1, rentId);
            cstmt.setInt(2, rentlineId);
            cstmt.setString(3, rentlineStatus); // Use the passed rentlineStatus

            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error calling receive_book procedure: " + e.getMessage());
        }
    }
}