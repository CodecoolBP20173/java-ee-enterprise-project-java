package com.codecool.library.repository;

import com.codecool.library.model.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends BaseModelRepository<Author> {

    @Override
    @Query("SELECT a FROM Author a WHERE LOWER(a.firstName) LIKE :searchTerm OR LOWER(a.lastName) LIKE :searchTerm")
    List<Author> fullTextSearch(@Param("searchTerm") String searchTerm);
}
