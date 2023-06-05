package com.quathar.contactbook.ui.table;

import com.quathar.contactbook.ui.model.MailModel;

import java.io.Serial;

/**
 * <h1>MailTable</h1>
 *
 * Componente JTable de la tabla <b>'correos'</b> en la BBDD.
 *
 * @since 2022-05-04
 * @see GeneralTable
 * @see MailModel
 * @author Q
 */
public class MailTable extends GeneralTable {

	// <<-CONSTANTS->>
	@Serial
	private static final long serialVersionUID = 1L;

	// <<-FIELDS->>
	private final MailModel _mailModel;

	public MailTable() {
		this(0L);
	}

	public MailTable(Long id) {
		_mailModel = new MailModel(id);
		setModel(_mailModel);
		removeColumn(columnModel.getColumn(0));
	}

	// <<-METHODS->>
	public void addNewRow(Object[] data) {
		_mailModel.insertRow(getRowCount(), data);
	}

	public void deleteRows() {
		_mailModel.removeRows(getSelectedRows());
	}

	public void clean() {
		_mailModel.setRowCount(0);
	}

}
