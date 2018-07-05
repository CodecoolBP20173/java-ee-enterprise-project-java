package com.codecool.library.repository;

import com.codecool.library.model.Book;
import com.codecool.library.model.projections.BookExcerptProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(excerptProjection = BookExcerptProjection.class)
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    @RestResource(path = "byTitle")
    Page<Book> findAllByTitleContainingIgnoreCase(@Param("title") String title, Pageable p);

    @RestResource(path = "byTitleAndPlace")
    @Query("SELECT b from Book b, BookInstance  bi, Place p WHERE (bi member of b.bookInstances AND bi.place.id = p.id AND p.id IN :places) AND b.title LIKE CONCAT('%',:title,'%')")
    Page<Book> findByTitleAndPlace(@Param("title") String title, @Param("places") Long[] places, Pageable p);

}
