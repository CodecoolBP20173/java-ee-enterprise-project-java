package com.codecool.library.repository;

import com.codecool.library.model.Book;
import com.codecool.library.model.Publisher;
import com.codecool.library.model.projections.PublisherExcerptProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(excerptProjection = PublisherExcerptProjection.class)
public interface PublisherRepository extends PagingAndSortingRepository<Publisher, Long> {

    @RestResource(path = "byName")
    Page<Publisher> findAllByNameContainingIgnoreCase(@Param("name") String name, Pageable p);
}
