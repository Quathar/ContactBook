package com.quathar.contactbook.data.service.impl;

import com.quathar.contactbook.data.entity.Hobby;
import com.quathar.contactbook.data.repository.HobbyRepository;
import com.quathar.contactbook.data.service.HobbyService;

public class HobbyServiceImpl extends GeneralServiceImpl<Hobby, Long> implements HobbyService {

    // <<-FIELDS->>
    private final HobbyRepository _hobbyRepository;

    // <<-CONSTRUCTOR->>
    public HobbyServiceImpl(HobbyRepository hobbyRepository) {
        super(hobbyRepository);
        _hobbyRepository = hobbyRepository;
    }

    // <<-METHODS->>
}
