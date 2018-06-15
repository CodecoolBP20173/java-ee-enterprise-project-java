package com.codecool.library.repository;

import com.codecool.library.model.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseModelRepository<T extends  BaseModel> extends JpaRepository<T, Long> {

    List<T> fullTextSearch(String searchTerm);
}