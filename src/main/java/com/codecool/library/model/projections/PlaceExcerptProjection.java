package com.codecool.library.model.projections;


import com.codecool.library.model.Place;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="placeExcerpt", types = {Place.class})
public interface PlaceExcerptProjection {

    String getName();
}
