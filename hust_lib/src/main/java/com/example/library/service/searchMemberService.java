package com.example.library.service;

import com.example.library.model.Person;
import com.example.library.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class searchMemberService {

    public List<Person> searchMembersByName(String keyword) {
        List<Person> members = new ArrayList<>();
        String query = "SELECT * FROM person WHERE firstname LIKE ? OR lastname LIKE ?";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameters with wildcard for LIKE search
            String searchKeyword = "%" + keyword + "%";
            preparedStatement.setString(1, searchKeyword);
            preparedStatement.setString(2, searchKeyword);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                Person member = new Person(
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("address"),
                        resultSet.getString("phonenumber"),
                        resultSet.getString("email"),  // Include email
                        resultSet.getString("password") // Adjust based on your database
                );
                members.add(member);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error searching for members by name: " + e.getMessage());
        }

        return members;
    }

    public List<Person> getAllMembers() {
        List<Person> members = new ArrayList<>();
        String query = "SELECT * FROM person";

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Person member = new Person(
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("address"),
                        resultSet.getString("phonenumber"),
                        resultSet.getString("email"),  // Include email
                        resultSet.getString("password") // Adjust based on your database
                );
                members.add(member);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving all members: " + e.getMessage());
        }

        return members;
    }

    public Person findMemberByPhoneNumber(String phoneNumber) {
        String query = "SELECT * FROM person WHERE phonenumber = ?";
        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, phoneNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Person(
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("address"),
                        resultSet.getString("phonenumber"),
                        resultSet.getString("email"),  // Include email
                        resultSet.getString("password") // Adjust based on your database
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error finding member by phone number: " + e.getMessage());
        }
        return null; // Not found
    }

    public Person findMemberByEmail(String email) {
        String query = "SELECT * FROM person WHERE email = ?";
        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Person(
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("address"),
                        resultSet.getString("phonenumber"),
                        resultSet.getString("email"),  // Include email
                        resultSet.getString("password") // Adjust based on your database
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error finding member by email: " + e.getMessage());
        }
        return null; // Not found
    }

    public void deleteMember(String phoneNumber) {
        String query = "DELETE FROM person WHERE phonenumber = ?";
        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, phoneNumber);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new RuntimeException("No member found with the given phone number.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting member: " + e.getMessage());
        }
    }

    public void updateMember(Person member) {
        String query = "UPDATE person SET firstname = ?, lastname = ?, address = ?, phonenumber = ?, email = ?, password = ? WHERE phonenumber = ?";
        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, member.getFirstName());
            preparedStatement.setString(2, member.getLastName());
            preparedStatement.setString(3, member.getAddress());
            preparedStatement.setString(4, member.getPhoneNumber());
            preparedStatement.setString(5, member.getEmail()); // Include email in update
            preparedStatement.setString(6, member.getPassword()); // Adjust based on your database
            preparedStatement.setString(7, member.getPhoneNumber()); // Where clause

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating member: " + e.getMessage());
        }
    }

    public void addMember(Person member) {
        String query = "INSERT INTO person (firstname, lastname, address, phonenumber, email, password) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, member.getFirstName());
            preparedStatement.setString(2, member.getLastName());
            preparedStatement.setString(3, member.getAddress());
            preparedStatement.setString(4, member.getPhoneNumber());
            preparedStatement.setString(5, member.getEmail()); // Include email in add
            preparedStatement.setString(6, member.getPassword()); // Adjust based on your database

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding new member: " + e.getMessage());
        }
    }
}