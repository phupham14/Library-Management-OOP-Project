package com.example.library.model;

import java.math.BigDecimal;

/**
 * Represents a book in the library system.
 * Maps directly to the Book table in the database.
 */
public class Book {
<<<<<<< HEAD
    private int bookId;           // Unique identifier for the book
    private String title;         // Title of the book
    private String publisher;     // Publisher's name
    private int quantity;         // Available quantity
    private int publishYear;      // Year of publication
    private double worth;         // Monetary value of the book
    private String image;         // Path or URL to the book's image
    private String author;        // Author of the book
    private int publisherId;
    private int id;

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
    public Book(String title, String publisher, int quantity, int publishYear, double worth, String image, String author) {
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
     * @param publisher    Publisher's name
     * @param quantity     Quantity available
     * @param publishYear  Year of publication
     * @param worth        Monetary value
     * @param image        Path or URL to the book's image
     * @param author       Author of the book
     */
    public Book(int bookId, String title, String publisher, int quantity, int publishYear, double worth, String image, String author) {
=======
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
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60
        this.bookId = bookId;
        this.title = title;
        this.publisher = publisher;
        this.quantity = quantity;
        this.publishYear = publishYear;
        this.worth = worth;
        this.image = image;
        this.author = author;
<<<<<<< HEAD
=======
        this.publisher = publisher;
    }

    public Book(int bookID, String title, int publishYear, int publisherID, int quantity, double worth) {
        this.bookId = bookID;
        this.publisherId = publisherID;
        this.title = title;
        this.publishYear = publishYear;
        this.quantity = quantity;
        this.worth = BigDecimal.valueOf(worth);
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60
    }

    /**
     * Additional constructor as per the user's request.
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
    public Book(int bookId, String title, int publisherId, int quantity, double worth, String image, String author, String publisher) {
        this.bookId = bookId;
        this.title = title;
        this.publisher = publisher; // Use the publisher's name
        this.quantity = quantity;
        this.worth = worth;
        this.image = image;
        this.author = author;
        // Note: publisherId is not stored, but can be included if needed
    }

    //cart
    public Book(int bookId, String title, String publisher, double worth) {
    }

    public Book(int bookId, String bookTitle, String publisher, int quantity, double worth, String image, String author) {
    }

    //display books
    public Book(int bookid, String title, int quantity, double worth, String image, String author, String publisher) {
    }

    public Book(int bookid, String title, int publisherid, int quantity, double worth) {
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

<<<<<<< HEAD
    public int getPublisherId() {
        return publisherId; // Added return statement
    }

    public void setPublisherId(int publisherId) {

    }

    public int getId() {
        return id;
    }
=======
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    //endregion
>>>>>>> 7a7fadd8af3016c06c126e162be6add0a8d93a60
}