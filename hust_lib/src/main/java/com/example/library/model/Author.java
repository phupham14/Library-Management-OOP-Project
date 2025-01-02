package com.example.library.model;

public class Author {
    private int authorId;
    private String fullName;

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Author(int authorId, String fullName) {
        this.authorId = authorId;
        this.fullName = fullName;
    }
}
