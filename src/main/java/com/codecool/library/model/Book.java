package com.codecool.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Book extends BaseModel{
    public Book(String title) {
        this.title = title;
    }

    @Column(length = 300)
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
