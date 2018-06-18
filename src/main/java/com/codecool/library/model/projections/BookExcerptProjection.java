package com.codecool.library.model.projections;

import com.codecool.library.model.Book;
import com.codecool.library.model.Language;
import com.codecool.library.model.Publisher;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="bookExcerpt", types= {Book.class})
public interface BookExcerptProjection {
    Long getId();
    String getTitle();
    String getLocation();
    Publisher getPublisher();
    int getPublicationYear();
    Language getLanguage();
}
