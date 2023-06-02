package com.quathar.contactbook.ui.model;

/**
 * <h1>ContactHobbyModel</h1>
 *
 * Modelo que maneja informaci�n de la tabla <b>contactos_aficiones</b> en la BBDD.
 *
 * @since 2022-04-19
 * @see GeneralModel
 * @author Q
 */
public class ContactHobbyModel {
//public class ContactHobbyModel extends GeneralModel {

//	private static final long serialVersionUID = 1L;
	public static final String[] ColumnNames = {"ID_A", "AFIC�N", "NOMBRE", "ID_C"};
	
	// CAMPOS
	/**
	 * N�mero de columnas.
	 */
	private final int COLUMNAS = 4;
//	private final String TABLE = DB.ContactsHobbiesTitle;
	
	// CONSTRUCTOR
//	public ContactHobbyModel(DAO dao) {
//		super(dao);
//		setColumnIdentifiers(ColumnNames);
//		createModel(TABLE, COLUMNAS);
//	}
	
	// M�TODOS
//	public void changeView() {
//		super.changeView(TABLE, COLUMNAS);
//	}
	
//	public void searchWord(String word) {
//		super.searchWord(TABLE, word, COLUMNAS);
//	}
	
//	public boolean isCellEditable(int rowIndex, int columnIndex) {
//		return false;
//	}
	
}
