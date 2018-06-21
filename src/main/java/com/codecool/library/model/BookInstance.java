package com.codecool.library.model;

import javax.persistence.*;

@Entity
public class BookInstance extends BaseModel {

    @ManyToOne
    private Place place;


    public BookInstance() {
    }

    public BookInstance(Place place) {
        this.place = place;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
