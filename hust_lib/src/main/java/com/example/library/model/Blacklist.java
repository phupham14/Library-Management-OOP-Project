package com.example.library.model;

public class Blacklist {
    private int blackListId;
    private String reason;
    private double fineAmount;
    private int rentId;

    public Blacklist() {
        // Default constructor
    }

    public Blacklist(int blackListId, String reason, double fineAmount, int rentId) {
        this.blackListId = blackListId;
        this.reason = reason;
        this.fineAmount = fineAmount;
        this.rentId = rentId;
    }

    public int getBlackListId() {
        return blackListId;
    }

    public void setBlackListId(int blackListId) {
        this.blackListId = blackListId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    public int getRentId() {
        return rentId;
    }

    public void setRentId(int rentId) {
        this.rentId = rentId;
    }
}