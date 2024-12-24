package com.example.library.model;

import java.math.BigDecimal;

/**
 * Represents a book in the library system.
 * Maps directly to the Book table in database.
 */
public class Book {
    private int bookId;           // BookID SERIAL NOT NULL
    private String title;         // Title VARCHAR(500) NOT NULL
    private int publisherId;      // PublisherID INT NOT NULL
    private int quantity;         // Quantity INT NOT NULL
    private int publishYear;      // PublishYear INT NOT NULL
    private BigDecimal worth;     // Worth NUMERIC NOT NULL
    private String image;         // Image VARCHAR(255)
    private String author;
    private String publisher;

    public Book(int bookId, String title, int publisherId, int quantity, int publishYear, BigDecimal worth, String image, String author, String publisher) {
        this.bookId = bookId;
        this.title = title;
        this.publisherId = publisherId;
        this.quantity = quantity;
        this.publishYear = publishYear;
        this.worth = worth;
        this.image = image;
        this.author = author;
        this.publisher = publisher;
    }

    public Book(int bookID, String title, int publishYear, int publisherID, int quantity, double worth) {
        this.bookId = bookID;
        this.publisherId = publisherID;
        this.title = title;
        this.publishYear = publishYear;
        this.quantity = quantity;
        this.worth = BigDecimal.valueOf(worth);
    }

    /**
     * Check if book is available for borrowing
     */
    public boolean isAvailable() {
        return quantity > 0;
    }

    /**
     * Decrease quantity when book is borrowed
     */
    public boolean decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
            return true;
        }
        return false;
    }

    /**
     * Increase quantity when book is returned
     */
    public void increaseQuantity() {
        quantity++;
    }
    //endregion

    //region Getters and Setters
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public BigDecimal getWorth() {
        return worth;
    }

    public void setWorth(BigDecimal worth) {
        this.worth = worth;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    //endregion
}