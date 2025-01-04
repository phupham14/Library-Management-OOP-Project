package com.example.library.service;

import com.example.library.model.RentDetail;
import com.example.library.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}