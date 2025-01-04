package com.example.library.model;

import java.sql.Date;
import java.time.LocalDate;

public class Rent {
    private int rentId;
    private int customerId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private Boolean returnAll;
    private Boolean isCollected; // Change to Boolean wrapper class
    private String firstname; // Added for first name
    private String lastname;  // Added for last name

    // Default constructor
    public Rent() {}

    // Constructor with all properties
    public Rent(int rentId, int customerId, LocalDate borrowDate, LocalDate dueDate,
                Boolean returnAll, Boolean isCollected, String firstname, String lastname) {
        this.rentId = rentId;
        this.customerId = customerId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnAll = returnAll;
        this.isCollected = isCollected; // Maintain the Boolean wrapper
        this.firstname = firstname; // Initialize first name
        this.lastname = lastname;  // Initialize last name
    }

    // Getters and setters for all properties (including first name and last name)

    public int getRentId() {
        return rentId;
    }

    public void setRentId(int rentId) {
        this.rentId = rentId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate.toLocalDate();
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate.toLocalDate();
    }

    public Boolean getReturnAll() {
        return returnAll;
    }

    public void setReturnAll(Boolean returnAll) {
        this.returnAll = returnAll;
    }

    public Boolean getIsCollected() { // Ensure proper getter method
        return isCollected;
    }

    public void setIsCollected(Boolean isCollected) { // Ensure proper setter method
        this.isCollected = isCollected;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    // Method to get the full username (firstName + lastName)
    public String getUsername() {
        return firstname + " " + lastname;
    }
}