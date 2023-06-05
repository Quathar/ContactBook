package com.quathar.contactbook.data.service.impl;

import com.quathar.contactbook.data.dao.GeneralDao;
import com.quathar.contactbook.data.service.GeneralService;

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
public class GeneralServiceImpl<T, ID> implements GeneralService<T, ID> {

    // <<-FIELDS->>
    private final GeneralDao<T, ID> _generalDao;

    // <<-CONSTRUCTOR->>
    public GeneralServiceImpl(GeneralDao<T, ID> generalDao) {
        _generalDao = generalDao;
    }

    // <<-METHODS->>

    @Override
    public List<T> getAll() {
        return _generalDao.findAll();
    }

    @Override
    public T getById(ID id) {
        return _generalDao.findById(id)
                          .orElseThrow(RuntimeException::new);
    }

    @Override
    public T create(T entity) {
        return _generalDao.save(entity);
    }

    @Override
    public T update(ID id, T updatedEntity) {
        _generalDao.findById(id);

        // TODO: CHANGE LATER
        // TODO: CHANGE LATER
        // TODO: CHANGE LATER
        _generalDao.save(updatedEntity);
        return null;
    }

    @Override
    public void deleteAll() {
        _generalDao.deleteAll();
    }

    @Override
    public void deleteById(ID id) {
        _generalDao.deleteById(id);
    }

    @Override
    public boolean exists(ID id) {
        return false;
    }

}
