package com.quathar.contactbook.ui.component.model;

import com.quathar.contactbook.service.GeneralService;

import javax.swing.table.DefaultTableModel;
import java.io.Serial;


/**
 * <h1>GeneralModel</h1>
 * <br>
 * Abstract class with common methods for table models:
 * <ul>
 *     <li>ContactModel</li>
 *     <li>HobbyModel</li>
 *     <li>ContactHobbyModel</li>
 *     <li>TelephoneModel</li>
 *     <li>MailModel</li>
 * </ul>
 *
 * @since 2022-04-14
 * @version 1.1
 * @author Q
 */
public abstract class GeneralModel<T, ID> extends DefaultTableModel {

	// <<-CONSTANT->>
	@Serial
	private static final long serialVersionUID = 1L;

	// <<-FIELDS->>
	private final GeneralService<T, ID> _generalService;

	// <<-CONSTRUCTOR->>
	public GeneralModel(GeneralService<T, ID> generalService) {
		_generalService = generalService;
	}

	// <<-METHODS->>
	private void fillModel(Object[][] data) {
		for (int i = 0; i < getRowCount(); i++)
			for (int j = 0; j < getColumnCount(); j++)
				super.setValueAt(data[i][j], i, j);
	}
	
	protected void create(Object[][] data, int columnCount) {
		setColumnCount(columnCount);
		setRowCount(data.length);
		fillModel(data);
	}
	
	public static void flip(int[] selectedRows) {
		for (int i = 0; i < selectedRows.length - 1; i++)
			for (int j = i + 1; j < selectedRows.length; j++)
				if (selectedRows[i] < selectedRows[j]) {
					int temporalStore = selectedRows[i];
					selectedRows[i] = selectedRows[j];
					selectedRows[j] = temporalStore;
				}
	}
	
	public void removeRow(int[] selectedRows) {
		// We change the order of the selected rows
		// so that they are deleted from highest to lowest index
		// so that there is no error.
		flip(selectedRows);
		for (int selectedRow : selectedRows) {
			super.removeRow(selectedRow);
			ID id = (ID) getValueAt(selectedRow, 0);
			_generalService.deleteById(id);
		}
		flip(selectedRows);
 		for (int row : selectedRows)
 			removeRow(row);
	}
	
	public void deleteAll() {
		int rowCount = getRowCount() - 1;
		while (rowCount >= 0)
			removeRow(rowCount--);
	}
	
}
