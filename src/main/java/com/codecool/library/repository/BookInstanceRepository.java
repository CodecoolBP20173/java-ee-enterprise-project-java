package com.codecool.library.repository;

import com.codecool.library.model.BookInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookInstanceRepository extends JpaRepository<BookInstance, Long>{

}
