package com.quathar.contactbook.data.service;

import java.util.List;

/**
 * @param <T>
 * @param <ID>
 *
 * @since 2023-05-30
 * @version 1.0
 * @author Q
 */
public interface GeneralService<T, ID> {

    default boolean isNull(Object field) {
        return field == null;
    }

    List<T> getAll();
    T getById(ID id);
    T create(T entity);
    T update(ID id, T updatedEntity);
    void deleteAll();
    void deleteById(ID id);
    boolean exists(ID id);

}