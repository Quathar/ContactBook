package com.quathar.contactbook.data.service;

import com.quathar.contactbook.data.entity.Contact;

import java.util.List;

public interface ContactService extends GeneralService<Contact, Long> {

    List<Contact> getAllByParams(String contactType, String name);

}
