package com.example.library.model;

import java.time.LocalDate;

public class Rent {
    private int rentId;
    private int customerId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private Boolean returnAll;
    private boolean isCollected;
}