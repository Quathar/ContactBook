package com.quathar.contactbook.ui.component.table;

import com.quathar.contactbook.ui.component.model.TelephoneModel;

import java.io.Serial;

/**
 * <h1>TelephoneTable</h1>
 * <br>
 * JTable component to display the telephones.
 *
 * @since 2022-05-04
 * @version 2.0
 * @author Q
 */
public class TelephoneTable extends GeneralTable {

	// <<-CONSTANTS->>
	@Serial
	private static final long serialVersionUID = 1L;

	// <<-FIELDS->>
	private final TelephoneModel _telephoneModel;

	// <<-CONSTRUCTORS->>
	public TelephoneTable() {
		this(0L);
	}

	public TelephoneTable(Long id) {
		_telephoneModel = new TelephoneModel(id);
		setModel(_telephoneModel);
		place();
	}

	// <<-METHODS->>
	private void place() {
		place(new int[] {0, 1}, new int[] {0}, 200);
	}

	public void addNewRow(Object[] data) {
		_telephoneModel.insertRow(getRowCount(), data);
		place();
	}

	public void deleteRows() {
		_telephoneModel.removeRows(getSelectedRows());
	}

	public void clean() {
		_telephoneModel.setRowCount(0);
	}

}
