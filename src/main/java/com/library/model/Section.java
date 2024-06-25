package com.library.model;

public class Section {
    private int sectionId;
    private String sectionName;

    public Section(int sectionId, String sectionName) {
        this.sectionId = sectionId;
        this.sectionName = sectionName;
    }

    // Getters and setters
    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
}
