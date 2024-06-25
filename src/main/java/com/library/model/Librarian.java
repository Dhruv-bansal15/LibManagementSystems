package com.library.model;

public class Librarian {
    private int librarianId;
    private String username;
    private String password;
    private String name;
    private String section;

    // Constructor for creating a new Librarian instance
    public Librarian(String username, String password, String name, String section) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.section = section;
    }

    // Constructor with librarianId for fetching existing Librarian from the database
    public Librarian(int librarianId, String username, String password, String name, String section) {
        this.librarianId = librarianId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.section = section;
    }

    // Getters and Setters
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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "librarianId=" + librarianId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", section='" + section + '\'' +
                '}';
    }
}
