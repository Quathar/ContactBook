package com.quathar.contactbook.data.service;

import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.enumerator.ContactType;

import java.util.List;

/**
 * @since 2023-05-30
 * @version 1.0
 * @author Q
 */
public interface ContactService extends GeneralService<Contact, Long> {

    List<Contact> getAllByParams(ContactType contactType, String name);

}
