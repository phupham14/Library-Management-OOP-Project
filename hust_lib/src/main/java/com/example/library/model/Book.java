package com.example.library.model;

import java.math.BigDecimal;

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
    private BigDecimal worth;     // Monetary value of the book
    private String image;         // Path or URL to the book's image
    private String author;        // Author of the book
    private String publisher;     // Publisher's name

    // Default constructor
    public Book() {
    }

    /**
     * Constructor for creating a new book.
     *
     * @param title        Title of the book
     * @param publisher    Publisher's name
     * @param quantity     Quantity available
     * @param publishYear  Year of publication
     * @param worth        Monetary value
     * @param image        Path or URL to the book's image
     * @param author       Author of the book
     */
    public Book(String title, String publisher, int quantity, int publishYear, BigDecimal worth, String image, String author) {
        this.title = title;
        this.publisher = publisher;
        this.quantity = quantity;
        this.publishYear = publishYear;
        this.worth = worth;
        this.image = image;
        this.author = author;
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
     * @param author       Author of the book
     * @param publisher    Publisher's name
     */
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

    /**
     * Constructor for the cart, only including bookId, title, publisher, and worth.
     */
    public Book(int bookId, String title, String publisher, BigDecimal worth) {
        this.bookId = bookId;
        this.title = title;
        this.publisher = publisher;
        this.worth = worth;
    }

    // Constructor to match service expectations
    public Book(int bookId, String title, String publisher, int quantity, BigDecimal worth, String image, String author) {
        this.bookId = bookId;
        this.title = title;
        this.publisher = publisher;
        this.quantity = quantity;
        this.worth = worth;
        this.image = image;
        this.author = author;
    }

    // Newly added constructor to resolve the error
    public Book(int bookId, String title, String publisher, int quantity, int publishYear, BigDecimal worth, String image, String author) {
        this.bookId = bookId;
        this.title = title;
        this.publisher = publisher;
        this.quantity = quantity;
        this.publishYear = publishYear;
        this.worth = worth;
        this.image = image;
        this.author = author;
    }

    // Another constructor for the service requirements
    public Book(int bookId, String title, int publisherId, int quantity, double worth) {
        this.bookId = bookId;
        this.title = title;
        this.publisherId = publisherId;
        this.quantity = quantity;
        this.worth = BigDecimal.valueOf(worth); // Convert double to BigDecimal
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
}