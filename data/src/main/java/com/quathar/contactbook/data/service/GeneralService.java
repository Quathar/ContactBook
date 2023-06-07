package com.quathar.contactbook.data.service;

import java.util.List;

/**
 * <h1>General Service</h1>
 * <br>
 * Interface for performing CRUD operations on entities.
 *
 * @param <T> The entity type
 * @param <ID> he ID type of the entity
 *
 * @since 2023-05-30
 * @version 1.0
 * @author Q
 */
public interface GeneralService<T, ID> {

    /**
     * Retrieves all entities.
     *
     * @return a list of all entities
     */
    List<T> getAll();

    /**
     * Retrieves an entity by its ID.
     *
     * @param id the ID of the entity
     * @return the entity with the specified ID
     */
    T getById(ID id);

    /**
     * Creates a new entity.
     *
     * @param entity the entity to create
     * @return the created entity
     */
    T create(T entity);

    /**
     * Updates an existing entity with the specified ID.
     *
     * @param id the ID of the entity to update
     * @param updatedEntity the updated entity
     * @return the updated entity
     */
    T update(ID id, T updatedEntity);

    /**
     * Deletes all entities.
     */
    void deleteAll();

    /**
     * Deletes an entity with the specified ID.
     *
     * @param id the ID of the entity to delete
     */
    void deleteById(ID id);

    /**
     * Checks if an entity with the specified ID exists.
     *
     * @param id the ID of the entity
     * @return true if the entity exists, false otherwise
     */
    boolean exists(ID id);

    /**
     * Check if a given field of T is null
     *
     * @param field a field from T
     * @return true if null, false if not null
     */
    default boolean isNull(Object field) {
        return field == null;
    }

}