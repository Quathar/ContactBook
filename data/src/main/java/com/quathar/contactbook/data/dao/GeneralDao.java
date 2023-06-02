package com.quathar.contactbook.data.dao;

import java.util.List;
import java.util.Optional;

/**
 *
 * @param <T>
 * @param <ID>
 *
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

}
