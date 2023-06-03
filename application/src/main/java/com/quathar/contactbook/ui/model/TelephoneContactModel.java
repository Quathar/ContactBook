package com.quathar.contactbook.ui.model;

import com.google.inject.Inject;
import com.quathar.contactbook.data.embeddable.Telephone;
import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.service.ContactService;

import javax.swing.table.DefaultTableModel;
import java.io.Serial;
import java.util.List;

/**
 * <h1>TelephoneContactModel</h1>
 *
 * @since 2022-05-15
 * @version 2.0
 * @author Q
 */
public class TelephoneContactModel extends DefaultTableModel {

    // <<-CONSTANTS->>
    @Serial
    private static final long serialVersionUID = 1L;
    private static final int COLUMNS = 3;
    private static final String[] COLUMN_NAMES = {
            "NAME",
            "TELEPHONE",
            "TYPE"
    };

    // <<-FIELDS->>
    private final ContactService _contactService;

    // <<-CONSTRUCTOR->>
    @Inject
    public TelephoneContactModel(ContactService contactService) {
        _contactService = contactService;
        setColumnIdentifiers(COLUMN_NAMES);
        createModel(COLUMNS);
    }

    // <<-METHODS->>
    private void fillModel(List<Contact> contacts) {
        for (int i = 0; i < getRowCount(); i++) {
            Contact contact = contacts.get(i);
            for (Telephone telephone : contact.getTelephones()) {
                super.setValueAt(contact.getName(),     i, 0);
                super.setValueAt(telephone.getNumber(), i, 1);
                super.setValueAt(telephone.getType(),   i, 2);
            }
        }
    }

    private void create(List<Contact> contacts, int columnCount) {
        setColumnCount(columnCount);
        setRowCount(contacts.size());
        fillModel(contacts);
    }

    private void createModel(int columnCount) {
        List<Contact> contacts = _contactService.getAll();
        create(contacts, columnCount);
    }

    public void update() {
        createModel(COLUMNS);
    }

    @Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

}
