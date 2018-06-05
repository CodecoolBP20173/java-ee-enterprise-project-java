package com.codecool.library.model;

import javax.persistence.Entity;

@Entity
public class Author extends  BaseModel {
    private String firstName;
    private String lastName;

    public Author() {
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
