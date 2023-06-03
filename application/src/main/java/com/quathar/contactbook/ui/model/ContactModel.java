package com.quathar.contactbook.ui.model;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.enumerator.ContactType;
import com.quathar.contactbook.data.service.ContactService;

import javax.swing.table.DefaultTableModel;
import java.io.Serial;
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

	private static final int COLUMNS = 4;
	private static final String[] COLUMN_NAMES = {
			"ID",
			"NAME",
			"ADDRESS",
			"NOTES"
	};

	// <<-FIELDS->>
	private final ContactService _contactService;

	// <<-CONSTRUCTOR->>
	@Inject
	public ContactModel(ContactService contactService) {
		_contactService = contactService;
		setColumnIdentifiers(COLUMN_NAMES);
		createModel(COLUMNS);
	}

	// <<-METHODS->>
	private void fillModel(List<Contact> contacts) {
		for (int i = 0; i < getRowCount(); i++) {
			Contact contact = contacts.get(i);
			super.setValueAt(contact.getId(),      i, 0);
			super.setValueAt(contact.getName(),    i, 1);
			super.setValueAt(contact.getAddress(), i, 2);
			super.setValueAt(contact.getNotes(),   i, 3);
		}
	}

	private void create(List<Contact> contacts, int columnCount) {
		setColumnCount(columnCount);
		setRowCount(contacts.size());
		fillModel(contacts);
	}

	public void createModel(int columnCount) {
		List<Contact> contacts = _contactService.getAll();
		create(contacts, columnCount);
	}

	public void createModelWithType(ContactType contactType) {
		List<Contact> contacts = _contactService.getAllByParams(contactType, null);
		create(contacts, COLUMNS);
	}

	public void createModelWithParams(ContactType contactType, String name) {
		List<Contact> contacts = _contactService.getAllByParams(contactType, name);
		create(contacts, COLUMNS);
	}

	public void removeRow(int[] selectedRows) {
		// We change the order of the selected rows
		// so that they are deleted from highest to lowest index
		// so that there is no error.
		GeneralModel.flip(selectedRows);
		for (int selectedRow : selectedRows) {
			super.removeRow(selectedRow);
			Long id = (Long) getValueAt(selectedRow, 0);
			_contactService.deleteById(id);
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
