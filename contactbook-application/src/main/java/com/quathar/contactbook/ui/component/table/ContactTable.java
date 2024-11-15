package com.quathar.contactbook.ui.component.table;

import com.google.inject.Guice;
import com.google.inject.Injector;

import com.quathar.contactbook.config.ServiceInjector;
import com.quathar.contactbook.data.enumerator.ContactType;
import com.quathar.contactbook.ui.component.model.ContactModel;

import javax.annotation.Nullable;
import javax.swing.JTable;
import java.io.Serial;

/**
 * <h1>ContactTable</h1>
 *
 * @since 2022-05-04
 * @see JTable
 * @version 2.0
 * @author Q
 */
public class ContactTable extends JTable {

    // <<-CONSTANTS->>
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String ALL = "all";

    // <<-FIELDS->>
    private final ContactModel _contactModel;

    // <<-CONSTRUCTORS->>
    public ContactTable() {
        Injector injector = Guice.createInjector(new ServiceInjector());
        _contactModel = injector.getInstance(ContactModel.class);
        setModel(_contactModel);
        removeColumn(columnModel.getColumn(0));
    }

    // <<-METHODS->>
    /**
     * Updates all contacts.
     */
    public void update() {
        update(ALL);
    }

    /**
     * Updates contacts based on the specified contact type.
     *
     * @param contactType the contact type
     */
    public void update(@Nullable String contactType) {
        ContactType type = null;
        if (contactType != null && !contactType.equalsIgnoreCase(ALL))
            type = ContactType.valueOf(contactType);
        _contactModel.createModelWithType(type);
        removeColumn(columnModel.getColumn(0));
    }

    /**
     * Updates contacts based on the selected index.
     *
     * @param selectedIndex the selected index
     */
    public void update(int selectedIndex) {
        ContactType type = null;
        switch (selectedIndex) {
            case 1 -> type = ContactType.PERSON;
            case 2 -> type = ContactType.COMPANY;
            case 3 -> type = ContactType.PET;
        }
        _contactModel.createModelWithType(type);
        removeColumn(columnModel.getColumn(0));
    }

    /**
     * Updates contacts based on the contact type and word.
     *
     * @param contactType the contact type
     * @param word the word
     */
    public void update(String contactType, String word) {
        ContactType type = contactType.equalsIgnoreCase(ALL) ?
                null : ContactType.valueOf(contactType);
        _contactModel.createModelWithParams(type, word);
        removeColumn(columnModel.getColumn(0));
    }

    /**
     * Updates contacts based on the selected index and word.
     *
     * @param selectedIndex the selected index
     * @param word the word
     */
    public void update(int selectedIndex, String word) {
        ContactType type = null;
        switch (selectedIndex) {
            case 1 -> type = ContactType.PERSON;
            case 2 -> type = ContactType.COMPANY;
            case 3 -> type = ContactType.PET;
        }
        _contactModel.createModelWithParams(type, word);
        removeColumn(columnModel.getColumn(0));
    }

    /**
     * Removes rows permanently (from the database).
     */
    public void removeRowsPermanently() {
        _contactModel.removeRowsPermanently(getSelectedRows());
    }

}