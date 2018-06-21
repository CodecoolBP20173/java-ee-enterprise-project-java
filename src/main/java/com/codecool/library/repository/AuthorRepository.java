package com.codecool.library.repository;

import com.codecool.library.model.Author;
import com.codecool.library.model.projections.AuthorExcerptProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(excerptProjection = AuthorExcerptProjection.class)
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {


    @RestResource(path="byName")
    Page<Author> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(@Param("name") String firstName, @Param("name") String lastName, Pageable p);
}
