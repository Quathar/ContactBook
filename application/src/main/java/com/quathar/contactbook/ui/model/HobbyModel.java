package com.quathar.contactbook.ui.model;

/**
 * <h1>HobbyModel</h1>
 *
 * Modelo que maneja informaci�n de la tabla <b>aficiones</b> en la BBDD.
 *
 * @since 2022-04-19
 * @see GeneralModel
 * @author Q
 */
public class HobbyModel {
//public class HobbyModel extends GeneralModel {

	// CONSTANTES
//	private static final long serialVersionUID = 1L;
//	private final String[] ColumnNames = {"ID", "AFICI�N"};

	// CAMPOS
//	private final int COLUMNAS = 2;
//	private final String TABLE = DB.HobbiesTitle;
//	private ContactBook frame;
	
	// CONSTRUCTORES
//	public HobbyModel(DAO dao) {
//		super(dao);
//		setColumnIdentifiers(ColumnNames);
//		createModel(TABLE, 2);
//	}
	
//	public HobbyModel(DAO dao, boolean voidType) {
//		super(dao);
//		setColumnIdentifiers(ColumnNames);
//	}
//
//	public HobbyModel(DAO dao, int id) {
//		super(dao);
//		setColumnIdentifiers(ColumnNames);
//		createModelWhereIdC(TABLE, COLUMNAS, id);
//	}
//
//	// M�TODOS
//	public void insertHobby(String hobby) {
//		dao.registerHobby(hobby);
//		changeView(TABLE, COLUMNAS);
//	}
//
//	public void addHobby(String hobby) {
//		Object[] data = {0, hobby};
//		insertRow(getRowCount(), data);
//	}
//
//	public void searchWord(String word) {
//		super.searchWord(TABLE, word, COLUMNAS);
//	}
//
//	private void checkStops(int stop) {
//		if (stop > 0) {
//			String[] msg = {
//					"Est� intentando borrar 1 afici�n utilizada",
//					"Est� intentando borrar " + stop + " aficiones utilizadas"
//			};
//			MSG.warningMessage(stop == 1 ? msg[0] : msg[1]);
//		}
//	}
//
//	public void deleteSelectedRows(int[] selectedRows) {
//		if (selectedRows.length > 1)
//			flip(selectedRows);
//
//		int id_h;
//		int stop = 0;
//		for (int i = 0; i < selectedRows.length; i++) {
//			id_h = Integer.parseInt(getValueAt(selectedRows[i], 0).toString());
//			if (!dao.checkIdH(id_h)) {
//				dao.unregisterRow(TABLE, id_h);
//				removeRow(selectedRows[i]);
//			} else
//				stop++;
//		}
//		checkStops(stop);
//	}
	
//	public void deleteSelectedRows(int id_c, int[] selectedRows) {
//		if (selectedRows.length > 1)
//			flip(selectedRows);
//
//		int id_h;
//		for (int i = 0; i < selectedRows.length; i++) {
//			id_h = Integer.parseInt(getValueAt(selectedRows[i], 0).toString());
//			if (id_h != 0) {
//	 			dao.unregisterHobbyWhereIdC(id_c, id_h);
//	 			removeRow(selectedRows[i]);
//			} else
//				removeRow(selectedRows[i]);
//		}
//	}
//
//	@Override
//	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//		super.setValueAt(aValue, rowIndex, columnIndex);
//		int id = Integer.parseInt(getValueAt(rowIndex, 0).toString());
//		String newName = aValue.toString();
//		dao.modifyHobby(id, newName);
//		frame.getContactHobbyTable().update();
//	}
	
	// OVERRIDE
//	@Override
//	public boolean isCellEditable(int rowIndex, int columnIndex) {
//		if (getColumnName(columnIndex) == ColumnNames[0])
//			return false;
//		else
//			return true;
//	}
//
//	// SETTER
//	public void setGUI(ContactBook frame) {
//		this.frame = frame;
//	}
	
}
