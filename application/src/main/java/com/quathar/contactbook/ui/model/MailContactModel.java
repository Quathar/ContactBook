package com.quathar.contactbook.ui.model;

import com.google.inject.Inject;
import com.quathar.contactbook.data.embeddable.Mail;
import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.service.ContactService;

import javax.swing.table.DefaultTableModel;
import java.io.Serial;
import java.util.List;

/**
 * MailContactModel.<br><br>
 *
 * @since 2022-05-15
 * @version 2.0
 * @author Q
 */
public class MailContactModel extends DefaultTableModel {

    // <<-CONSTANTS->>
    @Serial
	private static final long serialVersionUID = 1L;
    private static final int COLUMNS = 2;
    private static final String[] COLUMN_NAMES = {
            "NAME",
            "MAIL"
    };

    // <<-FIELDS->>
    private final ContactService _contactService;

    // <<-CONSTRUCTOR->>
    @Inject
	public MailContactModel(ContactService contactService) {
        _contactService = contactService;
		setColumnIdentifiers(COLUMN_NAMES);
		createModel(COLUMNS);
	}

    // <<-METHODS->>
    private void fillModel(List<Contact> contacts) {
        for (int i = 0; i < getRowCount(); i++) {
            Contact contact = contacts.get(i);
            for (Mail mail : contact.getMails()) {
                super.setValueAt(contact.getName(), i, 0);
                super.setValueAt(mail.getName(),    i, 1);
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
