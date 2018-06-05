package com.codecool.library.model;

import javax.persistence.Entity;

@Entity
public class Book extends BaseModel {
    private String title;

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }
}
