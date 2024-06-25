package com.library.model;

public class Librarian {
    private int librarianId;
    private String username;
    private String password;
    private String name;
    private boolean isVerified;
    private int sectionId; // Section ID assigned to the librarian

    public Librarian(int librarianId, String username, String password, String name, boolean isVerified, int sectionId) {
        this.librarianId = librarianId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.isVerified = isVerified;
        this.sectionId = sectionId;
    }

    // Getters and setters
    public int getLibrarianId() {
        return librarianId;
    }

    public void setLibrarianId(int librarianId) {
        this.librarianId = librarianId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }
}
