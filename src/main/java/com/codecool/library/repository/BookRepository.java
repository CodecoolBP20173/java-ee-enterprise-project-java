package com.codecool.library.repository;

import com.codecool.library.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends BaseModelRepository<Book> {

    @Override
    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE :searchTerm")
    List<Book> fullTextSearch(@Param("searchTerm")String searchTerm);
}
