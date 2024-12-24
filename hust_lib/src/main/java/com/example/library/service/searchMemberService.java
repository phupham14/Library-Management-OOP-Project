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
                System.out.println("First Name: " + resultSet.getString("firstname")); // Debug log
                System.out.println("Last Name: " + resultSet.getString("lastname"));   // Debug log

                Person member = new Person(
                );
                members.add(member);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error searching for members by name: " + e.getMessage());
        }

        return members;
    }
}
