package com.quathar.contactbook.ui.component.model;

import com.quathar.contactbook.dao.DAO;
import com.quathar.contactbook.data.service.GeneralService;

import javax.swing.table.DefaultTableModel;
import java.io.Serial;

/**
 * <h1>GeneralModel</h1>
 * 
 * Clase abstracta con m�todos comunes para los modelos:<br><br>
 * 
 *  - ContactModel<br>
 *  - HobbyModel<br>
 *  - ContactHobbyModel<br>
 *  - TelephoneModel<br>
 *  - MailModel
 * 
 * @see DAO
 * @since 2022-04-14
 * @author Q
 */
public abstract class GeneralModel<T, ID> extends DefaultTableModel {

	// <<-CONSTANTS->>
	@Serial
	private static final long serialVersionUID = 1L;

	// <<-FIELDS->>
	protected DAO dao;
	private GeneralService<T, ID> _generalService;
//	private final GeneralService<T, ID> _generalService;

	// <<-CONSTRUCTORS->>
	public GeneralModel(DAO dao) {
		this.dao = dao;
	}

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
	
//	protected void createModel(String table, int columnCount) {
//		Object[][] data = dao.getData(table, columnCount);
//		create(data, columnCount);
//	}
	
//	protected void createModelWhereIdC(String table, int columnCount, int id) {
//		table = table.toLowerCase().equals(DB.HobbiesTitle) ? DB.ContactsHobbiesTitle : table;
//		Object[][] data = dao.getDataWhereIdC(table, columnCount, id);
//		create(data, columnCount);
//	}
	
//	protected void changeView(String table, int columnCount) {
//		table = (table.equalsIgnoreCase("todos") ? "contactos" : table);
//		createModel(table, columnCount);
//	}
	
//	public void searchWord(String table, String word, int columnCount) {
//		Object[][] data = dao.getDataWhereNombreOrAficionLike(table, word, columnCount);
//		create(data, columnCount);
//	}
	
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