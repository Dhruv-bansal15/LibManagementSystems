package com.library.model;

public class Student {
    private int studentId;
    private String name;
    private boolean isVerified;
    private double accountBalance;

    // Constructors, getters, and setters
    public Student(int studentId, String name, boolean isVerified, double accountBalance) {
        this.studentId = studentId;
        this.name = name;
        this.isVerified = isVerified;
        this.accountBalance = accountBalance;
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

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", isVerified=" + isVerified +
                ", accountBalance=" + accountBalance +
                '}';
    }
}
