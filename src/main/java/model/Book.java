// src/main/java/model/Book.java
package model;

public class Book {
    private String bookId;
    private String bookName;
    private String author;
    private String genre;
    private String section;
    private int numCopiesAvailable;
    private double rating;
    private int numIssues;

    public Book() {
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getNumCopiesAvailable() {
        return numCopiesAvailable;
    }

    public void setNumCopiesAvailable(int numCopiesAvailable) {
        this.numCopiesAvailable = numCopiesAvailable;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumIssues() {
        return numIssues;
    }

    public void setNumIssues(int numIssues) {
        this.numIssues = numIssues;
    }
}
