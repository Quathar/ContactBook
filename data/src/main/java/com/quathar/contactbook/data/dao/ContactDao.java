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

    List<Contact> findByParams(ContactType type, String name);

}
