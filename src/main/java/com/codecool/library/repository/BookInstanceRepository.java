package com.codecool.library.repository;

import com.codecool.library.model.BookInstance;
import com.codecool.library.model.projections.BookInstanceExcerpt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = BookInstanceExcerpt.class)
public interface BookInstanceRepository extends JpaRepository<BookInstance, Long>{
}
