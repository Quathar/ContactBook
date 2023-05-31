package com.quathar.contactbook.ui.table;

import com.quathar.contactbook.ui.model.ContactModel;

import java.io.Serial;

/**
 * <h1>ContactTable</h1>
 *
 * @since 2022-05-04
 * @version 2.0
 * @author Q
 */
public class ContactTable extends GeneralTable {

	// <<-CONSTANTS->>
	@Serial
	private static final long serialVersionUID = 1L;

	// <<-FIELDS->>
	private final ContactModel contactModel;

	// <<-CONSTRUCTORS->>
	public ContactTable() {
		contactModel = new ContactModel();
		setModel(contactModel);
		removeColumn(0);
	}

	// <<-METHODS->>
	public void place() {
		center(new int[] {0});
		resize(new int[] {1, 2, 3}, 225);
	}

	public void update() {
		update("all");
	}
	
	public void update(String contactType) {
		contactType = contactType.equalsIgnoreCase("all") ?
						null : contactType;
		contactModel.createModelWithType(contactType);
		removeColumn(0);
	}
	
	public void update(String contactType, String word) {
		contactType = contactType.equalsIgnoreCase("all") ?
						null : contactType;
		contactModel.createModelWithParams(contactType, word);
		removeColumn(0);
	}
	
	public void deleteRows() {
		super.deleteRows(contactModel);
	}
	
}
