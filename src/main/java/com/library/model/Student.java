package com.library.model;

public class Student {
    private int studentId;
    private String name;
    private String username;
    private String password;
    private int accountBalance;
    private int numIssuedBooks;

    // Constructors, getters, and setters
    public Student(int studentId, String name, String username, String password, int accountBalance, int numIssuedBooks) {
        this.studentId = studentId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.accountBalance = accountBalance;
        this.numIssuedBooks = numIssuedBooks;
    }

    public Student(int studentId, String name, String username, String password) {
        this.studentId = studentId;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public Student(int studentId, String name, int accountBalance, int numIssuedBooks) {
        this.studentId = studentId;
        this.name = name;
        this.accountBalance = accountBalance;
        this.numIssuedBooks = numIssuedBooks;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public int getNumIssuedBooks() {
        return this.numIssuedBooks;
    }

    public void setNumIssuedBooks(int numIssuedBooks) {
        this.numIssuedBooks = numIssuedBooks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", accountBalance=" + accountBalance +
                ", numIssuedBooks=" + numIssuedBooks +
                '}';
    }
}