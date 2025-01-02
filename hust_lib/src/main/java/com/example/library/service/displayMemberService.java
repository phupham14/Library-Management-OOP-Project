package com.example.library.service;

import com.example.library.model.Person;
import com.example.library.util.ConnectionUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class displayMemberService {
    public static ObservableList<Person> getAllMembers() {
        ObservableList<Person> membersList = FXCollections.observableArrayList();

        try (Connection connection = ConnectionUtil.getInstance().connect_to_db("hust_lib", "hustlib_admin", "hustlib_admin");
             Statement statement = connection.createStatement()) {

            String query = "SELECT firstname, lastname, address, email, password, phonenumber FROM person";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Person person = new Person();
                person.setFirstName(resultSet.getString("firstname"));
                person.setLastName(resultSet.getString("lastname"));
                person.setAddress(resultSet.getString("address"));
                person.setEmail(resultSet.getString("email"));
                person.setPassword(resultSet.getString("password"));
                person.setPhoneNumber(resultSet.getString("phonenumber"));
                membersList.add(person);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return membersList;
    }

}