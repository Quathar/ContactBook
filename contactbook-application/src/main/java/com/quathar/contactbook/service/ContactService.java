package com.quathar.contactbook.service;

import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.enumerator.ContactType;

import java.util.List;

/**
 * <h1>Contact Service</h1>
 *
 * @since 2023-05-30
 * @version 1.0
 * @author Q
 */
public interface ContactService extends GeneralService<Contact, Long> {

    /**
     * Retrieves all entities that match the specified contactType and name.
     *
     * @param contactType the contact type to search
     * @param name the name to search
     * @return a list of entities that match that parameters
     */
    List<Contact> getAllByParams(ContactType contactType, String name);

}
