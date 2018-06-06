package com.codecool.library.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Book extends BaseModel {
    private String title;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authorList = new ArrayList<>();

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

    @Override
    public String toString() {
        return getTitle();
    }
}
