package com.quathar.contactbook.ui.component.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.quathar.contactbook.config.AppConfiguration;
import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.entity.Hobby;
import com.quathar.contactbook.service.HobbyService;

import javax.swing.table.DefaultTableModel;
import java.io.Serial;
import java.util.List;
import java.util.Objects;

/**
 * <h1>ContactHobbyModel</h1>
 *
 * Modelo que maneja informaci�n de la tabla <b>contactos_aficiones</b> en la BBDD.
 *
 * @since 2022-04-19
 * @see GeneralModel
 * @author Q
 */
public class ContactHobbyModel extends DefaultTableModel {

	// <<-CONSTANTS->>
	@Serial
	private static final long serialVersionUID = 1L;

	private static final int COLUMNS = 4;
	private static final String[] COLUMN_NAMES = {
			"ID_H",
			"HOBBY",
			"NAME",
			"ID_C"
	};

	// <<-FIELDS->>
	private final HobbyService _hobbyService;

	// <<-CONSTRUCTOR->>
	public ContactHobbyModel() {
		Injector injector = Guice.createInjector(new AppConfiguration());
		_hobbyService = injector.getInstance(HobbyService.class);
		setColumnIdentifiers(COLUMN_NAMES);
		createModel(COLUMNS);
	}

	// <<-METHODS->>
	private void fillModel(List<Hobby> hobbies) {
		int i = 0;
		for (Hobby hobby : hobbies) {
			List<Contact> contacts = hobby.getContacts();
			for (Contact contact : contacts) {
				super.setValueAt(hobby.getId(),     i, 0);
				super.setValueAt(hobby.getName(),   i, 1);
				super.setValueAt(contact.getName(), i, 2);
				super.setValueAt(contact.getId(),   i, 3);
				i++;
			}
		}
	}

	private void create(List<Hobby> hobbies, int columnCount) {
		setColumnCount(columnCount);
		int count = (int) hobbies.stream()
								 .flatMap(hobby -> hobby.getContacts()
											   			.stream()) // Combines all contact lists into a single stream
								 .filter(Objects::nonNull) // Filter null contacts
								 .count();
		setRowCount(count);
		fillModel(hobbies);
	}

	public void createModel(int columnCount) {
		List<Hobby> hobbies = _hobbyService.getAll();
		create(hobbies, columnCount);
	}

	public void update() {
		createModel(COLUMNS);
	}

	public void update(String name) {
		List<Hobby> hobbies = _hobbyService.getAllByParams(name);
		create(hobbies, COLUMNS);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

}
