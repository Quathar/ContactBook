package com.quathar.contactbook.data.service.impl;

import com.quathar.contactbook.data.repository.GeneralRepository;
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
    private final GeneralRepository<T, ID> _generalRepository;

    // <<-CONSTRUCTOR->>
    public GeneralServiceImpl(GeneralRepository<T, ID> generalRepository) {
        _generalRepository = generalRepository;
    }

    // <<-METHODS->>

    @Override
    public List<T> getAll() {
        return _generalRepository.findAll();
    }

    @Override
    public T getById(ID id) {
        return _generalRepository.findById(id);
    }

    @Override
    public T create(T entity) {
        return _generalRepository.save(entity);
    }

    @Override
    public T update(ID id, T updatedEntity) {
        _generalRepository.findById(id);

        // TODO: CHANGE LATER
        // TODO: CHANGE LATER
        // TODO: CHANGE LATER
        _generalRepository.save(updatedEntity);
        return null;
    }

    @Override
    public void deleteAll() {
        _generalRepository.deleteAll();
    }

    @Override
    public void deleteById(ID id) {
        _generalRepository.deleteById(id);
    }

    @Override
    public boolean exists(ID id) {
        return false;
    }

}
