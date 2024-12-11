package com.example.library.model;

import java.time.LocalDateTime;

public class Rentline {
    private int rentlineId;
    private int bookId;
    private int rentId;
    private LocalDateTime returnDate;
    private String rentlineStatus; // "Returned" or "Not returned"

    public int getRentlineId() {
        return rentlineId;
    }

    public void setRentlineId(int rentlineId) {
        this.rentlineId = rentlineId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getRentId() {
        return rentId;
    }

    public void setRentId(int rentId) {
        this.rentId = rentId;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public String getRentlineStatus() {
        return rentlineStatus;
    }

    public void setRentlineStatus(String rentlineStatus) {
        this.rentlineStatus = rentlineStatus;
    }

    public Rentline(int rentlineId, int bookId, int rentId, LocalDateTime returnDate, String rentlineStatus) {
        this.rentlineId = rentlineId;
        this.bookId = bookId;
        this.rentId = rentId;
        this.returnDate = returnDate;
        this.rentlineStatus = rentlineStatus;
    }
}


