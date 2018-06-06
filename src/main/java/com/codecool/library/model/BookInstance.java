package com.codecool.library.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class BookInstance extends BaseModel {
    @ManyToOne
    private Book book;

    @ManyToOne
    private Place place;


    public BookInstance() {
    }

    public BookInstance(Book book, Place place) {
        this.book = book;
        this.place = place;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
