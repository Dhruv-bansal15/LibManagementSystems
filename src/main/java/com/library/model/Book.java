package com.library.model;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String section;
    private int availableCopies;
    private double rating;
    private int numIssues;

    // Constructor for creating a new Book instance
    public Book(String title, String author, String section, int availableCopies) {
        this.title = title;
        this.author = author;
        this.section = section;
        this.availableCopies = availableCopies;
    }

    // Constructor with bookId for fetching existing Book from the database
    public Book(int bookId, String title, String author, String section, int availableCopies, double rating, int numIssues) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.section = section;
        this.availableCopies = availableCopies;
        this.rating = rating;
        this.numIssues = numIssues;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getIssues() {
        return this.numIssues;
    }

    public void addIssues() {
        this.numIssues += 1;
    }
}
