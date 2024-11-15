package com.quathar.contactbook.data.dao;

import java.util.List;
import java.util.Optional;

/**
 * <h1>General DAO (Data Access Object)</h1>
 * <br>
 * Interface with common methods of all DAOs
 *
 * @param <T> The entity type handled by this DAO.
 * @param <ID> The type of the entity's identifier.
 * @since 2023-05-30
 * @version 1.0
 * @author Q
 */
public interface GeneralDao<T, ID> {

    /**
     * Retrieves all entities.
     *
     * @return
     */
    List<T> findAll();

    /**
     * Finds an entity by its identifier.
     *
     * @param id The identifier of the entity to find.
     * @return An Optional containing the entity if found, otherwise an empty Optional.
     */
    Optional<T> findById(ID id);

    /**
     * Saves or updates the given entity.
     *
     * @param entity The entity to be saved or updated.
     * @return entity The entity to be saved or updated.
     */
    T save(T entity);

    /**
     * Deletes all entities.
     */
    void deleteAll();

    /**
     * Deletes the entity with the specified identifier.
     *
     * @param id The identifier of the entity to delete.
     */
    void deleteById(ID id);

    /**
     * Checks if an entity with the specified identifier exists.
     *
     * @param id The identifier to check for existence.
     * @return true if an entity with the given identifier exists, false otherwise.
     */
    boolean existsById(ID id);

}
