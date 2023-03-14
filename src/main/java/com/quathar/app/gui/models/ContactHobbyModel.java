package com.quathar.app.gui.models;

import com.quathar.app.dao.DAO;
import com.quathar.app.database.Database;

/**
 * ContactHobbyModel.<br><br>
 * 
 * Modelo que maneja informaci�n de la tabla <b>contactos_aficiones</b> en la BBDD.
 *
 * @since 2022-04-19
 * @see GeneralModel
 * @author Q
 */
public class ContactHobbyModel extends GeneralModel { // CLASE FINALIZADA

	private static final long serialVersionUID = 1L;
	/**
	 * Nombre de las columnas.
	 */
	public static final String[] ColumnNames = {"ID_A", "AFIC�N", "NOMBRE", "ID_C"};
	
	// CAMPOS
	/**
	 * N�mero de columnas.
	 */
	private final int COLUMNAS = 4;
	/**
	 * Nombre de la tabla en la BBDD.
	 */
	private final String TABLE = Database.ContactsHobbiesTitle;
	
	// CONSTRUCTOR
	/**
	 * Constructor.
	 * 
	 * @param dao the data access object
	 */
	public ContactHobbyModel(DAO dao) {
		super(dao);
		setColumnIdentifiers(ColumnNames);
		createModel(TABLE, COLUMNAS);
	}
	
	// M�TODOS
	/**
	 * Cambia la vista del modelo.
	 * 
	 * @param table the table to update
	 */
	public void changeView() {
		super.changeView(TABLE, COLUMNAS);
	}
	
	/**
	 * Cambia la vista del modelo a partir de una palabra.
	 * 
	 * @param word the word to update de model
	 */
	public void searchWord(String word) {
		super.searchWord(TABLE, word, COLUMNAS);
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
}
