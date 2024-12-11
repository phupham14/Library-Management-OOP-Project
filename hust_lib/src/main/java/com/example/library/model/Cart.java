package com.example.library.model;

import java.time.LocalDateTime;

public class Cart {
    private int cartid;
    private int customerid;
    private int bookid;
    private LocalDateTime borrowdate;

    public int getCartid() {
        return cartid;
    }

    public void setCartid(int cartid) {
        this.cartid = cartid;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public LocalDateTime getBorrowdate() {
        return borrowdate;
    }

    public void setBorrowdate(LocalDateTime borrowdate) {
        this.borrowdate = borrowdate;
    }

    public Cart(int cartid, int customerid, int bookid, LocalDateTime borrowdate) {
        this.cartid = cartid;
        this.customerid = customerid;
        this.bookid = bookid;
        this.borrowdate = borrowdate;
    }
}
