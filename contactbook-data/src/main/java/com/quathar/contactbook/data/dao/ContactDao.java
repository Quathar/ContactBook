package com.quathar.contactbook.data.dao;

import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.enumerator.ContactType;

import java.util.List;

/**
 * <h1>Contact DAO (Data Access Object)</h1>
 *
 * @since 2023-05-30
 * @version 1.0
 * @author Q
 */
public interface ContactDao extends GeneralDao<Contact, Long> {

    /**
     * Finds contacts by specified parameters.
     *
     * @param type The type of contact to search for.
     * @param name The name to match in contacts.
     * @return A list of Contact entities that match the given parameters.
     */
    List<Contact> findByParams(ContactType type, String name);

}