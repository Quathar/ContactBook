package com.quathar.contactbook.data.dao;

import java.util.List;
import java.util.Optional;

/**
 * <h1>General DAO (Data Access Object)</h1>
 * <br>
 * Interface with common methods of all DAOs
 *
 * @param <T> entity
 * @param <ID> the entity ID
 * @since 2023-05-30
 * @version 1.0
 * @author Q
 */
public interface GeneralDao<T, ID> {

    List<T> findAll();
    Optional<T> findById(ID id);
    T save(T entity);
    void deleteAll();
    void deleteById(ID id);
    boolean existsById(ID id);

}
