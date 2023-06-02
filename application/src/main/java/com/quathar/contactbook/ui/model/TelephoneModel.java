package com.quathar.contactbook.ui.model;

/**
 * MailModel.<br><br>
 * 
 * Modelo para manipular la informaci�n de la tabla <b>'telefonos'<b> de la BBDD.
 *
 * @since 2022-04-14
 * @see GeneralModel
 * @author Q
 */
public class TelephoneModel {
//public class TelephoneModel extends GeneralModel {
	
	// CONSTANTES
//	private static final long serialVersionUID = 1L;
//	private final String[] ColumnNames = {"ID_T", "TEL�FONO", "TIPO"};
//
//	// CAMPOS
//	private final int COLUMNAS = 3;
//	private final String TABLE = DB.TelephonesTitle;
//
//	// CONSTRUCTORES
//	public TelephoneModel(DAO dao) {
//		super(dao);
//		setColumnIdentifiers(ColumnNames);
//	}
//
//	public TelephoneModel(DAO dao, int id) {
//		super(dao);
//		setColumnIdentifiers(ColumnNames);
//		createModelWhereIdC(TABLE, COLUMNAS, id);
//	}
//
//	// M�TODOS
//	public void deleteSelectedRows(int[] selectedRows) {
//		if (selectedRows.length > 1)
//			flip(selectedRows);
//
//		int id_t;
//		for (int i = 0; i < selectedRows.length; i++) {
//			id_t = Integer.parseInt(getValueAt(selectedRows[i], 0).toString());
//			if (id_t != 0) {
//	 			dao.unregisterRow(TABLE, id_t);
//	 			removeRow(selectedRows[i]);
//			} else
//				removeRow(selectedRows[i]);
//		}
//	}
//
//	public boolean isCellEditable(int rowIndex, int columnIndex) {
//		return !getColumnName(columnIndex).equals(ColumnNames[2]);
//	}
	
}
