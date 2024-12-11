package com.example.library.model;

public class Customer extends Person{
    private int customerId;
    private boolean blockRent;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public boolean isBlockRent() {
        return blockRent;
    }

    public void setBlockRent(boolean blockRent) {
        this.blockRent = blockRent;
    }

    public Customer(int customerId, boolean blockRent) {
        this.customerId = customerId;
        this.blockRent = blockRent;
    }
}
