package com.codecool.library.repository;

import com.codecool.library.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
