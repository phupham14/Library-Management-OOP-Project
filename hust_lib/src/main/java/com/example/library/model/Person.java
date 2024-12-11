package com.example.library.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a person in the library system.
 * This is the base class for all user types (Employee, Customer, Customer).
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
    private String role;  // Employee, Customer, Customer
    private LocalDateTime createdDate;
    private LocalDateTime lastActiveDate;

    // Constructor
    public Person() {
        this.createdDate = LocalDateTime.now();
        this.lastActiveDate = LocalDateTime.now();
    }

    /**
     * Constructor for creating a new person with basic information.
     *
     * @param firstName    The person's first name
     * @param lastName     The person's last name
     * @param address      The person's address
     * @param phoneNumber  The person's phone number
     * @param email       The person's email address
     * @param password    The person's password
     * @param dob         The person's date of birth
     * @param gender      The person's gender ('M' or 'F')
     * @param role        The person's role in the system
    public Person(String firstName, String lastName, String address,
                  String phoneNumber, String email, String password,
                  LocalDate dob, char gender, String role) {
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

    /**
     * Constructor with all fields for database operations.
     *
     * @param personId       The unique identifier for the person
     * @param firstName      The person's first name
     * @param lastName       The person's last name
     * @param address        The person's address
     * @param phoneNumber    The person's phone number
     * @param email         The person's email address
     * @param password      The person's password
     * @param dob           The person's date of birth
     * @param gender        The person's gender ('M' or 'F')
     * @param role          The person's role in the system
     * @param createdDate   The date when the account was created
     * @param lastActiveDate The last time the person was active
     */
    public Person(int personId, String firstName, String lastName,
                  String address, String phoneNumber, String email,
                  String password, LocalDate dob, char gender,
                  String role, LocalDateTime createdDate,
                  LocalDateTime lastActiveDate) {
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

    // Getters and Setters thêm sau (sau này cần gì thì tạo)

    public int getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public char getGender() {
        return gender;
    }

    public String getRole() {
        return role;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getLastActiveDate() {
        return lastActiveDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setRole(String role) {
        this.role = role;
    }


    // Method
}