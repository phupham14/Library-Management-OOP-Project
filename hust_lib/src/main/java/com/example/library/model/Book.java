package com.example.library.model;

/**
 * Represents a book in the library system.
 * Maps directly to the Book table in the database.
 */
public class Book {
    private int bookId;           // Unique identifier for the book
    private String title;         // Title of the book
    private int publisherId;      // Publisher's ID
    private int quantity;         // Available quantity
    private int publishYear;      // Year of publication
    private double worth;         // Monetary value of the book
    private String image;         // Path or URL to the book's image

    // Default constructor
    public Book() {
    }

    /**
     * Constructor for creating a new book.
     *
     * @param title        Title of the book
     * @param publisherId  Publisher's ID
     * @param quantity     Quantity available
     * @param publishYear  Year of publication
     * @param worth        Monetary value
     * @param image        Path or URL to the book's image
     */
    public Book(String title, int publisherId, int quantity, int publishYear, double worth, String image) {
        this.title = title;
        this.publisherId = publisherId;
        this.quantity = quantity;
        this.publishYear = publishYear;
        this.worth = worth;
        this.image = image;
    }

    /**
     * Constructor with all fields, including bookId.
     *
     * @param bookId       Unique identifier
     * @param title        Title of the book
     * @param publisherId  Publisher's ID
     * @param quantity     Quantity available
     * @param publishYear  Year of publication
     * @param worth        Monetary value
     * @param image        Path or URL to the book's image
     */
    public Book(int bookId, String title, int publisherId, int quantity, int publishYear, double worth, String image) {
        this.bookId = bookId;
        this.title = title;
        this.publisherId = publisherId;
        this.quantity = quantity;
        this.publishYear = publishYear;
        this.worth = worth;
        this.image = image;
    }

    /**
     * Checks if the book is available for borrowing.
     *
     * @return True if available, false otherwise
     */
    public boolean isAvailable() {
        return quantity > 0;
    }

    /**
     * Decreases the quantity of the book when borrowed.
     *
     * @return True if successful, false if no copies are available
     */
    public boolean decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
            return true;
        }
        return false;
    }

    /**
     * Increases the quantity of the book when returned.
     */
    public void increaseQuantity() {
        quantity++;
    }

    // Getters and Setters
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

    public double getWorth() {
        return worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
