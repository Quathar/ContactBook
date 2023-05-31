package com.quathar.contactbook.data.service.impl;

import com.quathar.contactbook.data.repository.ContactRepository;
import com.quathar.contactbook.data.repository.HobbyRepository;
import com.quathar.contactbook.data.service.ContactBookService;

public class ContactBookServiceImpl implements ContactBookService {

    // <<-FIELDS->>
    private final ContactRepository _contactRepository;
    private final HobbyRepository _hobbyRepository;

    // <<-CONSTRUCTOR->>
    public ContactBookServiceImpl(
            ContactRepository contactRepository,
            HobbyRepository   hobbyRepository
    ) {
        _contactRepository = contactRepository;
        _hobbyRepository   = hobbyRepository;
    }

    // <<-METHODS->>
}
