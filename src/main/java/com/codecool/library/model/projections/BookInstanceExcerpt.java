package com.codecool.library.model.projections;

import com.codecool.library.model.BookInstance;
import com.codecool.library.model.Place;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "bookInstaceExcerpt", types = BookInstance.class)
public interface BookInstanceExcerpt {
    Place getPlace();
}
