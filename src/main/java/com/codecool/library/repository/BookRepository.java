package com.codecool.library.repository;

import com.codecool.library.model.Book;
import com.codecool.library.model.projections.BookExcerptProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(excerptProjection = BookExcerptProjection.class)
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    @RestResource(path = "byTitle")
    Page<Book> findAllByTitleContainingIgnoreCase(@Param("title") String title, Pageable p);
}
