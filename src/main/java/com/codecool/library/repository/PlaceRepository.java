package com.codecool.library.repository;

import com.codecool.library.model.Place;
import com.codecool.library.model.projections.PlaceExcerptProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = PlaceExcerptProjection.class)
public interface PlaceRepository extends JpaRepository<Place, Long> {
}
