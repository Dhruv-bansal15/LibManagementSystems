package com.library.model;

public class Section {
    private int sectionId;
    private String name;
    private int librarianId;

    // Constructor for creating a new Section instance
    public Section(String name, int librarianId) {
        this.name = name;
        this.librarianId = librarianId;
    }

    // Constructor with sectionId for fetching existing Section from the database
    public Section(int sectionId, String name, int librarianId) {
        this.sectionId = sectionId;
        this.name = name;
        this.librarianId = librarianId;
    }

    // Getters and Setters
    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLibrarianId() {
        return librarianId;
    }

    public void setLibrarianId(int librarianId) {
        this.librarianId = librarianId;
    }

    @Override
    public String toString() {
        return "Section{" +
                "sectionId=" + sectionId +
                ", name='" + name + '\'' +
                ", librarianId=" + librarianId +
                '}';
    }
}
