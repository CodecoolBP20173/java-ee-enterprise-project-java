package com.codecool.library.model.projections;

import com.codecool.library.model.Author;
import com.codecool.library.model.Book;
import com.codecool.library.model.Language;
import com.codecool.library.model.Publisher;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "bookTable", types = {Book.class})
public interface BookTableProjection {

    Long getId();
    List<Author> getAuthorList();
    String getTitle();
    String getLocation();
    Publisher getPublisher();
    int getPublicationYear();
    Language getLanguage();
}
