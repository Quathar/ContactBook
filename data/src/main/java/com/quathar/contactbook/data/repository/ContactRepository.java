package com.quathar.contactbook.data.repository;


import com.quathar.contactbook.data.entity.Contact;

import java.util.List;

public interface ContactRepository extends GeneralRepository<Contact, Long> {

    List<Contact> findByParams(String type, String name);

}
