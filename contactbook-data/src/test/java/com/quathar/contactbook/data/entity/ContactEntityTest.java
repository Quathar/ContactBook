package com.quathar.contactbook.data.entity;

import com.quathar.contactbook.data.embeddable.Mail;
import com.quathar.contactbook.data.embeddable.Telephone;
import com.quathar.contactbook.data.enumerator.ContactType;
import com.quathar.contactbook.data.enumerator.Gender;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *  <h1>Contact Entity Test</h1>
 *
 * @since 2023-12-02
 * @version 1.0
 * @author Q
 */
class ContactEntityTest {

    // <<-CONSTANTS->>
    private static final long ID = 1L;
    private static final String NAME = "John";
    private static final String ADDRESS = "123 Main Street";
    private static final String NOTES = "Some notes";
    private static final String SURNAMES = "Doe";
    private static final Gender GENDER = Gender.M;
    private static final String BIRTHDATE = "2000-01-01";
    private static final ContactType CONTACT_TYPE = ContactType.PERSON;
    private static final List<Telephone> TELEPHONES = List.of();
    private static final List<Mail> MAILS = List.of();
    private static final List<Hobby> HOBBIES = List.of();

    // <<-TEST->>
    @Test
    void voidConstructorTest() {
        Contact contact = new Contact();

        contact.setId        (ID);
        contact.setName      (NAME);
        contact.setAddress   (ADDRESS);
        contact.setNotes     (NOTES);
        contact.setSurnames  (SURNAMES);
        contact.setGender    (GENDER);
        contact.setBirthDate (BIRTHDATE);
        contact.setType      (CONTACT_TYPE);
        contact.setTelephones(TELEPHONES);
        contact.setMails     (MAILS);
        contact.setHobbies   (HOBBIES);

        assertEquals(ID,           contact.getId());
        assertEquals(NAME,         contact.getName());
        assertEquals(ADDRESS,      contact.getAddress());
        assertEquals(NOTES,        contact.getNotes());
        assertEquals(SURNAMES,     contact.getSurnames());
        assertEquals(GENDER,       contact.getGender());
        assertEquals(BIRTHDATE,    contact.getBirthDate());
        assertEquals(CONTACT_TYPE, contact.getType());
        assertEquals(TELEPHONES,   contact.getTelephones());
        assertEquals(MAILS,        contact.getMails());
        assertEquals(HOBBIES,      contact.getHobbies());
    }

    @Test
    void fullConstructorTest() {
        Contact contact = new Contact(
                ID,
                NAME,
                ADDRESS,
                NOTES,
                SURNAMES,
                GENDER,
                BIRTHDATE,
                TELEPHONES,
                MAILS,
                CONTACT_TYPE,
                HOBBIES);

        assertEquals(ID,           contact.getId());
        assertEquals(NAME,         contact.getName());
        assertEquals(ADDRESS,      contact.getAddress());
        assertEquals(NOTES,        contact.getNotes());
        assertEquals(SURNAMES,     contact.getSurnames());
        assertEquals(GENDER,       contact.getGender());
        assertEquals(BIRTHDATE,    contact.getBirthDate());
        assertEquals(CONTACT_TYPE, contact.getType());
        assertEquals(TELEPHONES,   contact.getTelephones());
        assertEquals(MAILS,        contact.getMails());
        assertEquals(HOBBIES,      contact.getHobbies());
    }

}