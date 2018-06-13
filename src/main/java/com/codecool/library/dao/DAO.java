package com.codecool.library.dao;

import com.codecool.library.model.BaseModel;

import java.util.List;
import java.util.Optional;

/**
 * Interface for DAOs.
 * @param <T> The model type hadled by this DAO.
 */
public interface DAO<T extends BaseModel> {

    /**
     * Gets an instance of the object by ID.
     * @param id The id of the object to search for.
     * @return The loaded object, or an empty optional.
     */
    Optional<T> getById(int id);

    /**
     * Performs a full text search, and returns the search results.
     * @param searchTerm The search term.
     * @return The matching objects. Returns an empty list if no matching objects found.
     */
    List<T> fullTextSearch(String searchTerm);

    /**
     * Saves the object to the database backend.
     * @param instance The instance to be saved.
     */
    void persist(T instance);
}
