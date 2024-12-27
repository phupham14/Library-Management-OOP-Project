package com.example.library.model;

/**
 * Represents a book in the library system.
 * Maps directly to the Book table in the database.
 */
public class Book {
    private int bookId;           // BookID SERIAL NOT NULL
    private String title;         // Title VARCHAR(500) NOT NULL
    private int publisherId;      // PublisherID INT NOT NULL
    private int quantity;         // Quantity INT NOT NULL
    private int publishYear;      // PublishYear INT NOT NULL
    private double worth;         // Worth NUMERIC NOT NULL
    private String image;         // Image VARCHAR(255)
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream

    //region Constructors
    public Book() {
    }

    /**
     * Constructor for creating new book
     */
    public Book(String title, int publisherId, int quantity,
                int publishYear, BigDecimal worth, String image) {
        this.title = title;
        this.publisherId = publisherId;
        this.quantity = quantity;
        this.publishYear = publishYear;
        this.worth = worth;
        this.image = image;
    }

    /**
     * Constructor with all fields
     */
    public Book(int bookId, String title, int publisherId,
                int quantity, int publishYear, BigDecimal worth,
                String image) {
=======
    private String author;        // Author's name
    private String publisher;     // Publisher's name

    // Constructor that requires all parameters
    public Book(int bookId, String title, int publisherId, int quantity, int publishYear, double worth, String image, String author, String publisher) {
>>>>>>> Stashed changes
=======
    private String author;        // Author's name
    private String publisher;     // Publisher's name

    // Constructor that requires all parameters
    public Book(int bookId, String title, int publisherId, int quantity, int publishYear, double worth, String image, String author, String publisher) {
>>>>>>> Stashed changes
=======
    private String author;        // Author's name
    private String publisher;     // Publisher's name

    // Constructor that requires all parameters
    public Book(int bookId, String title, int publisherId, int quantity, int publishYear, double worth, String image, String author, String publisher) {
>>>>>>> Stashed changes
        this.bookId = bookId;
        this.title = title;
        this.publisherId = publisherId;
        this.quantity = quantity;
        this.publishYear = publishYear;
        this.worth = worth;
        this.image = image;
<<<<<<< Updated upstream
    }
    //endregion

    //region Methods
=======
        this.author = author;
        this.publisher = publisher;
    }

<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    /**
     * Check if the book is available for borrowing
     */
    public boolean isAvailable() {
        return quantity > 0;
    }

    /**
     * Decrease quantity when the book is borrowed
     */
    public boolean decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
            return true;
        }
        return false;
    }

    /**
     * Increase quantity when the book is returned
     */
    public void increaseQuantity() {
        quantity++;
    }

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
    //endregion
}