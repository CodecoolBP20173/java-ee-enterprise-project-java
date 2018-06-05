package com.codecool.library.model;

import javax.persistence.Entity;

@Entity
public class Place extends BaseModel {
    private String name;

    public Place() {
    }

    public Place(String name) {
        this.name = name;
    }
}
