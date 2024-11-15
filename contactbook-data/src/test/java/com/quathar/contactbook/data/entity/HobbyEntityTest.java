package com.quathar.contactbook.data.entity;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *  <h1>Hobby Entity Test</h1>
 *
 * @since 2023-12-02
 * @version 1.0
 * @author Q
 */
public class HobbyEntityTest {

    // <<-CONSTANTS->>
    private static final long ID = 1L;
    private static final String HOBBY_NAME = "Fishing";
    private static final List<Contact> CONTACTS = List.of();


    // <<-TEST->>
    @Test
    void voidConstructorTest() {
        Hobby hobby = new Hobby();

        hobby.setId      (ID);
        hobby.setName    (HOBBY_NAME);
        hobby.setContacts(CONTACTS);

        assertEquals(ID,         hobby.getId());
        assertEquals(HOBBY_NAME, hobby.getName());
        assertEquals(CONTACTS,   hobby.getContacts());
    }

    @Test
    void fullConstructorTest() {
        Hobby hobby = new Hobby(ID, HOBBY_NAME, CONTACTS);

        assertEquals(ID,         hobby.getId());
        assertEquals(HOBBY_NAME, hobby.getName());
        assertEquals(CONTACTS,   hobby.getContacts());
    }

}