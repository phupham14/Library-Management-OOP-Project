package com.example.library.model;

import java.time.LocalDate;

public class RentDetail {
    private String bookTitle;
    private String rentStatus;
    private int rentlineId;
    private LocalDate returnDate;

    // Getters and Setters
    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }

    public String getRentStatus() { return rentStatus; }
    public void setRentStatus(String rentStatus) { this.rentStatus = rentStatus; }

    public int getRentlineId() { return rentlineId; }
    public void setRentlineId(int rentlineId) { this.rentlineId = rentlineId; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
}