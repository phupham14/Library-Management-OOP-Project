package com.example.library.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a person in the library system.
 * This is the base class for all user types (e.g., Employee, Customer).
 */
public class Person {
    private int personId;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
    private String password;
    private LocalDate dob;
    private char gender;
    private String role; // Employee, Customer, etc.
    private LocalDateTime createdDate;
    private LocalDateTime lastActiveDate;

<<<<<<< HEAD
    // Default constructor
    public Person() {
        this.createdDate = LocalDateTime.now();
        this.lastActiveDate = LocalDateTime.now();
    }

    // Constructor for creating a person with basic information
    public Person(String firstName, String lastName, String address, String phoneNumber,
                  String email, String password, LocalDate dob, char gender, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.role = role;
        this.createdDate = LocalDateTime.now();
        this.lastActiveDate = LocalDateTime.now();
    }

    // Constructor with all fields (used for database operations)
    public Person(int personId, String firstName, String lastName, String address, String phoneNumber,
                  String email, String password, LocalDate dob, char gender, String role,
                  LocalDateTime createdDate, LocalDateTime lastActiveDate) {
=======
    public Person(int personId, String firstName, String lastName, String address, String phoneNumber, String email, String password, LocalDate dob, char gender, String role, LocalDateTime createdDate, LocalDateTime lastActiveDate) {
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.role = role;
        this.createdDate = createdDate;
        this.lastActiveDate = lastActiveDate;
    }

<<<<<<< HEAD
    // Constructor for specific use cases (e.g., member search)
=======
    // Members Search
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60
    public Person(String firstName, String lastName, String address, String phoneNumber, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }
<<<<<<< HEAD
=======

    public Person() {

    }
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60

    // Getters and setters
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
<<<<<<< HEAD

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastActiveDate() {
        return lastActiveDate;
    }

    public void setLastActiveDate(LocalDateTime lastActiveDate) {
        this.lastActiveDate = lastActiveDate;
    }
}
=======

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastActiveDate() {
        return lastActiveDate;
    }

    public void setLastActiveDate(LocalDateTime lastActiveDate) {
        this.lastActiveDate = lastActiveDate;
    }

}
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60
