package com.quathar.contactbook.ui.component.model;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.enumerator.ContactType;
import com.quathar.contactbook.data.service.ContactService;

import javax.swing.table.DefaultTableModel;
import java.io.Serial;
import java.util.Comparator;
import java.util.List;

/**
 * <h1>ContactModel</h1>
 *
 * @since 2022-04-10
 * @version 2.0
 * @author Q
 */
@Singleton
public class ContactModel extends DefaultTableModel {

    // <<-CONSTANTS->>
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * The number of columns in the contact model.
     */
    private static final int COLUMNS = 4;
    /**
     * The column names for the contact model.
     */
    private static final String[] COLUMN_NAMES = {
            "ID",
            "NAME",
            "ADDRESS",
            "NOTES"
    };

    // <<-FIELDS->>
    /**
     * The ContactService used by the contact model.
     */
    private final ContactService _contactService;

    // <<-CONSTRUCTOR->>

    /**
     * Constructs a new ContactModel with the specified contact service.
     *
     * @param contactService the ContactService to be used by the model.
     */
    @Inject
    public ContactModel(ContactService contactService) {
        _contactService = contactService;
        setColumnIdentifiers(COLUMN_NAMES);
        createModel(COLUMNS);
    }

    // <<-METHODS->>
    /**
     * Fills the model with the given list of contacts.
     *
     * @param contacts the list of contacts to fill the model with.
     */
    private void fillModel(List<Contact> contacts) {
        for (int i = 0; i < getRowCount(); i++) {
            Contact contact = contacts.get(i);
            super.setValueAt(contact.getId(),      i, 0);
            super.setValueAt(contact.getName(),    i, 1);
            super.setValueAt(contact.getAddress(), i, 2);
            super.setValueAt(contact.getNotes(),   i, 3);
        }
    }

    /**
     * Creates the model with the given list of contacts and column count.
     *
     * @param contacts the list of contacts to fill the model with.
     * @param columnCount the number of columns in the model.
     */
    private void create(List<Contact> contacts, int columnCount) {
        setColumnCount(columnCount);
        setRowCount(contacts.size());
        contacts.sort(Comparator.comparing(Contact::getName));
        fillModel(contacts);
    }

    /**
     * Creates the model with a specific column count.
     *
     * @param columnCount the number of columns in the model.
     */
    public void createModel(int columnCount) {
        List<Contact> contacts = _contactService.getAll();
        create(contacts, columnCount);
    }

    /**
     * Creates the model with a specific contact type.
     *
     * @param contactType the contact type to filter the contacts.
     */
    public void createModelWithType(ContactType contactType) {
        List<Contact> contacts = _contactService.getAllByParams(contactType, null);
        create(contacts, COLUMNS);
    }

    /**
     * Creates the model with a specific contact type and name.
     *
     * @param contactType the contact type to filter the contacts.
     * @param name the name of the contacts to filter.
     */
    public void createModelWithParams(ContactType contactType, String name) {
        List<Contact> contacts = _contactService.getAllByParams(contactType, name);
        create(contacts, COLUMNS);
    }

    /**
     * Removes the rows permanently from the model based on the selected rows.
     *
     * @param selectedRows the array of selected rows to be removed.
     */
    public void removeRowsPermanently(int[] selectedRows) {
        // We change the order of the selected rows
        // so that they are deleted from highest to lowest index
        // so that there is no error.
        if (selectedRows.length > 1) GeneralModel.flip(selectedRows);
        for (int selectedRow : selectedRows) {
            Long id = (Long) getValueAt(selectedRow, 0);
            _contactService.deleteById(id);
            super.removeRow(selectedRow);
        }
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        super.setValueAt(aValue, row, column);
        Contact contact = new Contact();
        Long id 		= (Long)    getValueAt(row, 0);
        contact.setName   ((String) getValueAt(row, 1));
        contact.setAddress((String) getValueAt(row, 2));
        contact.setNotes  ((String) getValueAt(row, 3));
        _contactService.update(id, contact);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return !getColumnName(columnIndex).equals(COLUMN_NAMES[0]);
    }

}
