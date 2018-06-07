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
    private List<Book> translations = new ArrayList<>();

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

    private int publicationYear;

    public Book() {
    }

    public Book(Author author, String title, Publisher publisher, String location, int publicationYear){
        addAuthor(author);
        setTitle(title);
        setPublisher(publisher);
        //setLocation(location); //TODO: uncomment after merge
        setPublicationYear(publicationYear);
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

        author.addBook(this);

    }

    public void addTranslator(Author translator){
        if(translator == null)
            throw new IllegalArgumentException("Translator cannot be null.");

        if(translatorList.contains(translator))
            return;

        translatorList.add(translator);
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

    public List<Book> getTranslations() {
        return Collections.unmodifiableList(translations);
    }

    public void addTranslation(Book book){
        if(book == null)
            throw new IllegalArgumentException("Book may not be null");

        if(book.equals(this))
            throw new IllegalArgumentException("A book may not be a translation of itself.");

        if(book.getLanguage() == getLanguage())
            throw new IllegalArgumentException("A translation may not be of the same language as the source book.");

        if(book.getPublicationYear() < getPublicationYear()){
            throw new IllegalArgumentException("Publication year of translations may not be earlier than the publication year of the translated book.");
        }

        if(translations.contains(book))
            return;

        translations.add(book);

        book.translationOf = this;
    }

    public Book getTranslationOf() {
        return translationOf;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        if(translationOf != null && translationOf.getPublicationYear() > getPublicationYear()){
            throw new IllegalArgumentException("Publication year of translations may not be earlier than the publication year of the translated book.");
        }
        this.publicationYear = publicationYear;
    }
}
