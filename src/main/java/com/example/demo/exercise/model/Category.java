package com.example.demo.exercise.model;

public enum Category {

    SPOZYWCZE("spożywcze"),
    GOSP_DOM("gosp. domowe"),
    INNE("inne");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
