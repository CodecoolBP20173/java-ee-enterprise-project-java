package com.codecool.library.repository;

import com.codecool.library.model.Publisher;
import com.codecool.library.model.projections.PublisherExcerptProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = PublisherExcerptProjection.class)
public interface PublisherRepository extends PagingAndSortingRepository<Publisher, Long> {
}
