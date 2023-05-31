package com.quathar.contactbook.data.service.impl;

import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.repository.ContactRepository;
import com.quathar.contactbook.data.service.ContactService;

import java.util.List;

public class ContactServiceImpl extends GeneralServiceImpl<Contact, Long> implements ContactService {

    // <<-FIELDS->>
    private final ContactRepository _contactRepository;

    // <<-CONSTRUCTOR->>
    public ContactServiceImpl(ContactRepository contactRepository) {
        super(contactRepository);
        _contactRepository = contactRepository;
    }

    // <<-METHODS->>
    @Override
    public List<Contact> getAllByParams(String contactType, String name) {
        return _contactRepository.findByParams(contactType, name);
    }

}
