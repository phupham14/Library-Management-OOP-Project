package com.example.library.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Employee extends Person{
    private String employeeId;

    public Employee(String employeeId) {
        this.employeeId = employeeId;
    }

    public Employee(int personId, String firstName, String lastName, String address, String phoneNumber, String email, String password, LocalDate dob, char gender, String role, LocalDateTime createdDate, LocalDateTime lastActiveDate, String employeeId) {
        super(personId, firstName, lastName, address, phoneNumber, email, password, dob, gender, role, createdDate, lastActiveDate);
        this.employeeId = employeeId;
    }
}
