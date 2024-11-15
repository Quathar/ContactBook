package com.quathar.contactbook.data.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.quathar.contactbook.data.dao.ContactDao;
import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.enumerator.ContactType;
import com.quathar.contactbook.data.service.ContactService;
import com.quathar.contactbook.data.exception.ResourceNotFoundException;

import java.util.List;

/**
 * <h1>Contact Service Implementation</h1>
 *
 * @since 2023-05-30
 * @see GeneralServiceImpl
 * @see ContactService
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

    @Override
    public Contact update(Long id, Contact updatedContact) {
        Contact contact = _contactDao.findById(id)
                                     .orElseThrow(ResourceNotFoundException::new);

        // Common fields
        if (!isNull(updatedContact.getName()))
            contact.setName(updatedContact.getName());
        if (!isNull(updatedContact.getAddress()))
            contact.setAddress(updatedContact.getAddress());
        if (!isNull(updatedContact.getNotes()))
            contact.setNotes(updatedContact.getNotes());

        // Exclusive Person fields
        if (!isNull(updatedContact.getSurnames()))
            contact.setSurnames(updatedContact.getSurnames());
        if (!isNull(updatedContact.getNotes()))
            contact.setGender(updatedContact.getGender());

        // Person & Pet field
        if (!isNull(updatedContact.getBirthDate()))
            contact.setBirthDate(updatedContact.getBirthDate());

        return _contactDao.save(contact);
    }

}
