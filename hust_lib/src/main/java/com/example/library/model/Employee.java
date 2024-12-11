package com.example.library.model;

public class Employee extends Person{
    private String employeeId;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Employee(String employeeId) {
        this.employeeId = employeeId;
    }
}
