package com.codecool.library.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Author extends  BaseModel {
    private String firstName;
    private String lastName;
    private String title;
    private Boolean easternNameOrder;
    private Integer birthYear;
    private Integer deathYear;

    public List<Book> getBookList() {
        return Collections.unmodifiableList(bookList);
    }

    @ManyToMany(mappedBy = "authorList")
    private List<Book> bookList = new ArrayList<>();

    public Author(String firstName, String lastName, String title, boolean easternNameOrder, Integer birthYear, Integer deathYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public Author() {
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isEasternNameOrder() {
        return easternNameOrder;
    }

    public void setEasternNameOrder(boolean easternNameOrder) {
        this.easternNameOrder = easternNameOrder;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(int deathYear) {
        this.deathYear = deathYear;
    }

    @Override
    public String toString() {
        return getFirstName()+" "+getLastName();
    }
}
