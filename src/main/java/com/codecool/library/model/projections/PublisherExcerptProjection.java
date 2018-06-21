package com.codecool.library.model.projections;

import com.codecool.library.model.Publisher;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "publisherExcerpt", types = {Publisher.class})
public interface PublisherExcerptProjection {
    String getName();
    Long getId();
}
