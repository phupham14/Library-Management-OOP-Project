package com.example.library.model;

import java.time.LocalDateTime;

public class Rentline {
    private int rentlineId;
    private int bookId;
    private int rentId;
    private LocalDateTime returnDate;
    private String rentlineStatus; // "Returned" or "Not returned"
}


