package com.library.model;

import java.util.Date;

public class studentTransaction {
    public int transactionId;
    public String title;
    public Date issueDate;
    public Date returnDate;
    public int fine;
    public int rating;

    // Constructor for creating a new Transaction instance
    public studentTransaction(int transactionId, String title, Date issueDate, Date returnDate, int fine, int rating) {
        this.transactionId = transactionId;
        this.title = title;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.fine = fine;
        this.rating = rating;
    }
}
