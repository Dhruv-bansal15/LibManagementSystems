package com.library.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private int studentId;
    private String name;
    private List<Transaction> transactions;
    private boolean verified;

    // Constructor for creating a new Student instance
    public Student(String name) {
        this.name = name;
        this.transactions = new ArrayList<>();
        this.verified = false; // Students start as unverified
    }

    // Constructor with studentId for fetching existing Student from the database
    public Student(int studentId, String name, boolean verified) {
        this.studentId = studentId;
        this.name = name;
        this.verified = verified;
        this.transactions = new ArrayList<>();
    }

    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", transactions=" + transactions +
                ", verified=" + verified +
                '}';
    }
}
