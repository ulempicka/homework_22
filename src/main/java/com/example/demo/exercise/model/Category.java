package com.example.demo.exercise.model;

public enum Category {

    GROCERY("spożywcze"),
    HOUSEHOLD("gosp. domowe"),
    OTHERS("inne");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
