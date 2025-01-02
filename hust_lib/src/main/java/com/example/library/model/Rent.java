package com.example.library.model;

import java.time.LocalDate;

public class Rent {
    private int rentId;
    private int customerId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private Boolean returnAll;
    private boolean isCollected;

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

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getReturnAll() {
        return returnAll;
    }

    public void setReturnAll(Boolean returnAll) {
        this.returnAll = returnAll;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }

    public Rent(int rentId, int customerId, LocalDate borrowDate, LocalDate dueDate, Boolean returnAll, boolean isCollected) {
        this.rentId = rentId;
        this.customerId = customerId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnAll = returnAll;
        this.isCollected = isCollected;
    }
}