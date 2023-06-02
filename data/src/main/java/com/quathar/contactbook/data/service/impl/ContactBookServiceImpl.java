package com.quathar.contactbook.data.service.impl;

import com.quathar.contactbook.data.dao.ContactDao;
import com.quathar.contactbook.data.dao.HobbyDao;
import com.quathar.contactbook.data.service.ContactBookService;

/**
 * @since 2023-05-30
 * @version 1.0
 * @author Q
 */
public class ContactBookServiceImpl implements ContactBookService {

    // <<-FIELDS->>
    private final ContactDao _contactDao;
    private final HobbyDao _hobbyRepository;

    // <<-CONSTRUCTOR->>
    public ContactBookServiceImpl(
            ContactDao contactDao,
            HobbyDao hobbyRepository
    ) {
        _contactDao = contactDao;
        _hobbyRepository   = hobbyRepository;
    }

    // <<-METHODS->>
}
