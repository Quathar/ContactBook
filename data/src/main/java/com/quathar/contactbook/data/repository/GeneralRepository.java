package com.quathar.contactbook.data.repository;

import java.util.List;

/**
 *
 * @param <T>
 * @param <ID>
 *
 * @since 2023-05-30
 * @version 1.0
 * @author Q
 */
public interface GeneralRepository<T, ID> {

    List<T> findAll();
    T findById(ID id);
    T save(T entity);
    void deleteAll();
    void deleteById(ID id);

}
