package com.library.model;

import java.util.Date;

public class Transaction {
    private int transactionId;
    private int studentId;
    private int bookId;
    private Date issueDate;
    private Date returnDate;
    private double fine;
    private int rating;

    // Constructor for creating a new Transaction instance
    public Transaction(int studentId, int bookId, Date issueDate) {
        this.studentId = studentId;
        this.bookId = bookId;
        this.issueDate = issueDate;
    }

    // Constructor with transactionId for fetching existing Transaction from the database
    public Transaction(int transactionId, int studentId, int bookId, Date issueDate, Date returnDate, double fine, int rating) {
        this.transactionId = transactionId;
        this.studentId = studentId;
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.fine = fine;
        this.rating = rating;
    }

    // Getters and Setters
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", studentId=" + studentId +
                ", bookId=" + bookId +
                ", issueDate=" + issueDate +
                ", returnDate=" + returnDate +
                ", fine=" + fine +
                ", rating=" + rating +
                '}';
    }
}
