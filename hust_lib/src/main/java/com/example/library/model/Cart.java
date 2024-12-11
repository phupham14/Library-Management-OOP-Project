package com.example.library.model;

import java.time.LocalDate;

public class Cart {
    private int cartID;
    private int customerId;
    private int bookid;
    private LocalDate borrowdate;

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public LocalDate getBorrowdate() {
        return borrowdate;
    }

    public void setBorrowdate(LocalDate borrowdate) {
        this.borrowdate = borrowdate;
    }

    public Cart(int cartID, int customerId, int bookid, LocalDate borrowdate) {
        this.cartID = cartID;
        this.customerId = customerId;
        this.bookid = bookid;
        this.borrowdate = borrowdate;
    }
}
