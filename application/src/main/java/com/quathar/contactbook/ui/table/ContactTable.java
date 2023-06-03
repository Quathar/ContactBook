package com.quathar.contactbook.ui.table;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.quathar.contactbook.config.AppConfiguration;
import com.quathar.contactbook.data.enumerator.ContactType;
import com.quathar.contactbook.ui.model.ContactModel;

import javax.annotation.Nullable;
import javax.swing.*;
import java.io.Serial;

/**
 * <h1>ContactTable</h1>
 *
 * @since 2022-05-04
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
		Injector injector = Guice.createInjector(new AppConfiguration());
		_contactModel = injector.getInstance(ContactModel.class);
		setModel(_contactModel);
		removeColumn(columnModel.getColumn(0));
	}

	// <<-METHODS->>
	public void place() {
//		center(new int[] {0});
//		resize(new int[] {1, 2, 3}, 225);
	}

	public void update() {
		update(ALL);
	}

	public void update(@Nullable String contactType) {
		ContactType type = null;
		if (contactType != null && !contactType.equalsIgnoreCase(ALL))
			type = ContactType.valueOf(contactType);
		_contactModel.createModelWithType(type);
		removeColumn(columnModel.getColumn(0));
	}

	public void update(String contactType, String word) {
		ContactType type = contactType.equalsIgnoreCase(ALL) ?
							null : ContactType.valueOf(contactType);
		_contactModel.createModelWithParams(type, word);
		removeColumn(columnModel.getColumn(0));
	}

	public void deleteRows() {
		_contactModel.removeRow(getSelectedRows());
	}

}
