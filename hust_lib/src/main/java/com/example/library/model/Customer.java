package com.example.library.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Customer extends Person{
    private int customerId;
    private boolean blockRent;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public boolean isBlockRent() {
        return blockRent;
    }

    public void setBlockRent(boolean blockRent) {
        this.blockRent = blockRent;
    }

    public Customer(int customerId, boolean blockRent) {
        this.customerId = customerId;
        this.blockRent = blockRent;
    }

    public Customer(int personId, String firstName, String lastName, String address, String phoneNumber, String email, String password, LocalDate dob, char gender, String role, LocalDateTime createdDate, LocalDateTime lastActiveDate, int customerId, boolean blockRent) {
        super(personId, firstName, lastName, address, phoneNumber, email, password, dob, gender, role, createdDate, lastActiveDate);
        this.customerId = customerId;
        this.blockRent = blockRent;
    }
}
