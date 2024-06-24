// src/main/java/model/Student.java
package model;

public class Student {
    private String studentId;
    private String name;
    private String allBooksIssued;
    private String currentlyIssuedBooks;
    private double fineAmount;

    public Student() {
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAllBooksIssued() {
        return allBooksIssued;
    }

    public void setAllBooksIssued(String allBooksIssued) {
        this.allBooksIssued = allBooksIssued;
    }

    public String getCurrentlyIssuedBooks() {
        return currentlyIssuedBooks;
    }

    public void setCurrentlyIssuedBooks(String currentlyIssuedBooks) {
        this.currentlyIssuedBooks = currentlyIssuedBooks;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }
}
