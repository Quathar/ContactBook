package com.quathar.contactbook.data.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.quathar.contactbook.data.dao.HobbyDao;
import com.quathar.contactbook.data.entity.Hobby;
import com.quathar.contactbook.data.service.HobbyService;

import java.util.List;

/**
 * <h1>Hobby Service Implementation</h1>
 *
 * @since 2023-05-30
 * @see GeneralServiceImpl
 * @see HobbyService
 * @version 1.0
 * @author Q
 */
@Singleton
public class HobbyServiceImpl extends GeneralServiceImpl<Hobby, Long> implements HobbyService {

    // <<-FIELDS->>
    private final HobbyDao _hobbyRepository;

    // <<-CONSTRUCTOR->>
    @Inject
    public HobbyServiceImpl(HobbyDao hobbyRepository) {
        super(hobbyRepository);
        _hobbyRepository = hobbyRepository;
    }

    // <<-METHODS->>
    @Override
    public List<Hobby> getAllByParams(String name) {
        return _hobbyRepository.findByParams(name);
    }

}
