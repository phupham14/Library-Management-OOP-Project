package com.example.library.service;

import com.example.library.model.Blacklist;
import com.example.library.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlacklistService {
    public List<Blacklist> fetchAllBlacklists() throws SQLException {
        String query = "SELECT * FROM Blacklist";
        List<Blacklist> blacklists = new ArrayList<>();

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Blacklist blacklist = new Blacklist();
                blacklist.setBlackListId(resultSet.getInt("BlacklistID")); // Set BlacklistID
                blacklist.setRentId(resultSet.getInt("RentID"));
                blacklist.setReason(resultSet.getString("Reason"));
                blacklist.setFineAmount(resultSet.getDouble("FineAmount"));
                blacklists.add(blacklist);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching blacklist data: " + e.getMessage());
            throw e;
        }

        return blacklists;
    }

    public int getCustomerIdFromRentId(int rentId) throws SQLException {
        String sql = "SELECT customerid FROM rent WHERE rentid = ?";
        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, rentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("customerid");
                }
            }
        }
        return 0; // Or handle the case where customerId is not found
    }

    public void payForFine(int rentId, double paidAmount, int customerId) throws SQLException {
        String sql = "{CALL PayForFine(?, ?, ?)}";
        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             CallableStatement cstmt = connection.prepareCall(sql)) {

            cstmt.setInt(1, rentId);
            cstmt.setDouble(2, paidAmount);
            cstmt.setInt(3, customerId);

            cstmt.execute();
        }
    }

    public void updateReason(int blacklistId, String newReason) throws SQLException {
        String query = "UPDATE Blacklist SET Reason = ? WHERE BlacklistID = ?"; // Update by BlacklistID

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newReason);
            preparedStatement.setInt(2, blacklistId); // Use blacklistId
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating reason: " + e.getMessage());
            throw e;
        }
    }
}