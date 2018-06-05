package com.codecool.library.model;

import javax.persistence.Entity;

@Entity
public class Publisher extends BaseModel {
    private String name;

    public Publisher() {
    }

    public Publisher(String name) {
        this.name = name;
    }
}
