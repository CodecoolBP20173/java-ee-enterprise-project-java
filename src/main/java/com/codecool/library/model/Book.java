package com.codecool.library.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Book extends BaseModel {
    private String title;

    private String location;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authorList = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name="translator",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name = "translator_id")
    )
    private List<Author> translatorList = new ArrayList<>();

    @ManyToOne
    private Book translationOf;

    @OneToMany(mappedBy = "translationOf")
    private List<Book> translations;

    @ManyToOne
    private Publisher publisher;

    @Enumerated(EnumType.STRING)
    private Language language;

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @OneToMany(mappedBy = "book")
    private List<BookInstance> bookInstances = new ArrayList<>();

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BookInstance> getBookInstances() {
        return Collections.unmodifiableList(bookInstances);
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public void addAuthor(Author author){
        if(author == null)
            throw new IllegalArgumentException("Author cannot be null");

        if(authorList.contains(author))
            return;

        authorList.add(author);

    }

    public List<Author> getAuthorList() {
        return Collections.unmodifiableList(authorList);
    }


    public List<Author> getTranslatorList() {
        return Collections.unmodifiableList(translatorList);
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
