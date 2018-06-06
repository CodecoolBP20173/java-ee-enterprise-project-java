package com.codecool.library.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Author extends BaseModel {
    private String firstName;
    private String lastName;
    private String title;
    private Boolean easternNameOrder;
    private Integer birthYear;
    private Integer deathYear;

    @ManyToMany(mappedBy = "authorList")
    private List<Book> bookList = new ArrayList<>();

    @ManyToMany(mappedBy = "translatorList")
    private List<Book> translatedBookList = new ArrayList<>();

    public List<Book> getBookList() {
        return Collections.unmodifiableList(bookList);
    }

    public Author() {
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Author(String firstName, String lastName, String title, Boolean easternNameOrder, Integer birthYear, Integer deathYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.easternNameOrder = easternNameOrder;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
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

    public Boolean getEasternNameOrder() {
        return easternNameOrder;
    }

    public void setEasternNameOrder(Boolean easternNameOrder) {
        this.easternNameOrder = easternNameOrder;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public List<Book> getTranslatedBookList() {
        return Collections.unmodifiableList(translatedBookList);
    }

    @Override
    public String toString() {
        return getFirstName()+" "+getLastName();
    }
}
