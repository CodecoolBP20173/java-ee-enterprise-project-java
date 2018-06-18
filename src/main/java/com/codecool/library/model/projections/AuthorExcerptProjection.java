package com.codecool.library.model.projections;

import com.codecool.library.model.Author;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="authorExcerpt", types = {Author.class})
public interface AuthorExcerptProjection {
    Long getId();
    String getFirstName();
    String getLastName();
    String getTitle();
}
