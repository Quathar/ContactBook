package com.quathar.contactbook.data.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.dao.ContactDao;
import com.quathar.contactbook.data.enumerator.ContactType;
import com.quathar.contactbook.data.service.ContactService;

import java.util.List;

/**
 * @since 2023-05-30
 * @version 1.0
 * @author Q
 */
@Singleton
public class ContactServiceImpl extends GeneralServiceImpl<Contact, Long> implements ContactService {

    // <<-FIELDS->>
    private final ContactDao _contactDao;

    // <<-CONSTRUCTOR->>
    @Inject
    public ContactServiceImpl(ContactDao contactDao) {
        super(contactDao);
        _contactDao = contactDao;
    }

    // <<-METHODS->>
    @Override
    public List<Contact> getAllByParams(ContactType contactType, String name) {
        return _contactDao.findByParams(contactType, name);
    }

}
