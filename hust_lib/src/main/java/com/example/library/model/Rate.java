package com.example.library.model;

import java.time.LocalDateTime;

public class Rate {
    private int ratingId;
    private int customerId;
    private int bookId;
    private int star;
    private String comment;
    private LocalDateTime commentTime;

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(LocalDateTime commentTime) {
        this.commentTime = commentTime;
    }

    public Rate(int ratingId, int customerId, int bookId, int star, String comment, LocalDateTime commentTime) {
        this.ratingId = ratingId;
        this.customerId = customerId;
        this.bookId = bookId;
        this.star = star;
        this.comment = comment;
        this.commentTime = commentTime;
    }
}