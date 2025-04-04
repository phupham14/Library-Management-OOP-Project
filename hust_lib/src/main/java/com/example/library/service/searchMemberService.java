package com.example.library.service;

import com.example.library.model.Person;
import com.example.library.util.ConnectionUtil;

import java.sql.*;
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
                        resultSet.getString("email"),
                        resultSet.getString("role"),
                        resultSet.getString("password")
                );
                members.add(member);
            }
            System.out.println("Number of members found by name '" + keyword + "': " + members.size());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error searching for members by name: " + e.getMessage());
        }

        return members;
    }

    public List<Person> getAllMembers() {
        List<Person> members = new ArrayList<>();
        String sql = "SELECT * FROM person"; // Câu lệnh SQL để lấy tất cả các thành viên

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Person person = new Person(); // Tạo đối tượng mới trong mỗi vòng lặp
                person.setFirstName(resultSet.getString("firstname"));
                person.setLastName(resultSet.getString("lastname"));
                person.setAddress(resultSet.getString("address"));
                person.setPhoneNumber(resultSet.getString("phonenumber"));
                person.setEmail(resultSet.getString("email"));
                person.setRole(resultSet.getString("role"));
                person.setPassword(resultSet.getString("password"));
               
                members.add(person); // Thêm vào danh sách
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
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
                        resultSet.getString("email"),
                        resultSet.getString("role"),
                        resultSet.getString("password")
                );
            }

            System.out.println("No member found with phone number: " + phoneNumber);
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
                        resultSet.getString("email"),
                        resultSet.getString("role"),
                        resultSet.getString("password")
                );
            }

            System.out.println("No member found with email: " + email);
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
                System.out.println("No member found with phone number: " + phoneNumber);
                throw new RuntimeException("No member found with the given phone number.");
            } else {
                System.out.println("Member with phone number " + phoneNumber + " deleted successfully.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting member: " + e.getMessage());
        }
    }

    public void updateMember(Person member) {
        String query = "UPDATE person SET firstname = ?, lastname = ?, address = ?, phonenumber = ?, password = ? WHERE email = ?"; // or phone number
        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Debugging: Log the member details before updating
            System.out.println("Attempting to update member:");
            System.out.println("First Name: " + member.getFirstName());
            System.out.println("Last Name: " + member.getLastName());
            System.out.println("Address: " + member.getAddress());
            System.out.println("Phone Number: " + member.getPhoneNumber());
            System.out.println("Email: " + member.getEmail());
            System.out.println("Password: " + member.getPassword());

            // Check if each field has changed before setting it
            preparedStatement.setString(1, member.getFirstName());
            preparedStatement.setString(2, member.getLastName());
            preparedStatement.setString(3, member.getAddress());
            preparedStatement.setString(4, member.getPhoneNumber());
            preparedStatement.setString(5, member.getPassword());
            preparedStatement.setString(6, member.getEmail()); // Use email to identify the row

            // Execute the update and check how many rows were affected
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected by update: " + rowsAffected); // Debugging log

            // If no rows were affected, log a warning
            if (rowsAffected == 0) {
                System.out.println("Warning: No rows were updated. Check if the email exists in the database.");
            }
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
            preparedStatement.setString(5, member.getEmail());
            preparedStatement.setString(6, member.getPassword());

            preparedStatement.executeUpdate();
            System.out.println("New member added: " + member.getFirstName() + " " + member.getLastName());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding new member: " + e.getMessage());
        }
    }
}
