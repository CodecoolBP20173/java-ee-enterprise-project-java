package com.codecool.library.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Book extends BaseModel {
    private String title;

    private String location;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authorList = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(
            name = "translator",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "translator_id")
    )
    private List<Author> translatorList = new ArrayList<>();

    @ManyToOne
    private Book translationOf;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne
    private Publisher publisher;

    @Enumerated(EnumType.STRING)
    private Language language;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany
    private List<BookInstance> bookInstances = new ArrayList<>();
    private Integer publicationYear;

    public Book() {
    }

    public Book(String title) {
        setTitle(title);
    }

    public Book(Author author, String title, Publisher publisher, String location, Integer publicationYear) {
        addAuthor(author);
        setTitle(title);
        setPublisher(publisher);
        setLocation(location);
        setPublicationYear(publicationYear);
    }

    public Book(Author author, String title, Publisher publisher, String location, Integer publicationYear, List<BookInstance> bookInstances) {
        addAuthor(author);
        setTitle(title);
        setPublisher(publisher);
        setLocation(location);
        setPublicationYear(publicationYear);
        this.bookInstances = bookInstances;
    }

    public Book(Author author, String title, Publisher publisher, String location, Integer publicationYear, BookInstance bookInstance) {
        addAuthor(author);
        setTitle(title);
        setPublisher(publisher);
        setLocation(location);
        setPublicationYear(publicationYear);
        this.bookInstances = Collections.singletonList(bookInstance);
    }

    public Book(String title, String location, List<Author> authorList, Publisher publisher, Integer publicationYear) {
        this.title = title;
        this.location = location;
        this.authorList = authorList;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
    }

    public Book(String title, String location, List<Author> authorList, Publisher publisher, Integer publicationYear, List<BookInstance> bookInstances) {
        this.title = title;
        this.location = location;
        this.authorList = authorList;
        this.publisher = publisher;
        this.bookInstances = bookInstances;
        this.publicationYear = publicationYear;
    }
    public Book(String title, String location, List<Author> authorList, Publisher publisher, Integer publicationYear, BookInstance bookInstance) {
        this.title = title;
        this.location = location;
        this.authorList = authorList;
        this.publisher = publisher;
        this.bookInstances = Collections.singletonList(bookInstance);
        this.publicationYear = publicationYear;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
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

    public void addAuthor(Author author) {
        if (author == null)
            throw new IllegalArgumentException("Author cannot be null");

        if (authorList.contains(author))
            return;

        authorList.add(author);

        author.addBook(this);

    }

    public void addTranslator(Author translator) {
        if (translator == null)
            throw new IllegalArgumentException("Translator cannot be null.");

        if (translatorList.contains(translator))
            return;

        translatorList.add(translator);
    }

    public List<Author> getAuthorList() {
        return Collections.unmodifiableList(authorList);
    }


    public List<Author> getTranslatorList() {
        return Collections.unmodifiableList(translatorList);
    }

    public void addInstance(BookInstance instance) {
        if (instance == null)
            throw new IllegalArgumentException("Book instance cannot be null.");

        if (bookInstances.contains(instance))
            return;

        bookInstances.add(instance);
    }

    @Override
    public String toString() {
        return getTitle();
    }

    public Book getTranslationOf() {
        return translationOf;
    }

    public void setTranslationOf(Book translationOf) {
        this.translationOf = translationOf;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        if (translationOf != null && translationOf.getPublicationYear() > getPublicationYear()) {
            throw new IllegalArgumentException("Publication year of translations may not be earlier than the publication year of the translated book.");
        }
        this.publicationYear = publicationYear;
    }
}
