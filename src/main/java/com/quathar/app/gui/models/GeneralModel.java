package com.quathar.app.gui.models;

import javax.swing.table.DefaultTableModel;

import com.quathar.app.dao.DAO;
import com.quathar.app.database.Database;

/**
 * GeneralModel.<br><br>
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
public abstract class GeneralModel extends DefaultTableModel { // CLASE FINALIZADA
	
	private static final long serialVersionUID = 1L;
	
	// CAMPOS
	/**
	 * Data Access Object.
	 */
	protected DAO dao;
	
	// CONSTRUCTOR
	/**
	 * Constructor.
	 * 
	 * @param dao the data access object
	 */
	public GeneralModel(DAO dao) {
		this.dao = dao;
	}
	
	// M�TODOS
	/**
	 * Llena el modelo con la informaci�n contenida en el array bidimensional.
	 * 
	 * @param data the bidimensional array
	 */
	private void fillModel(Object[][] data) {
		for (int i = 0; i < getRowCount(); i++)
			for (int j = 0; j < getColumnCount(); j++)
				super.setValueAt(data[i][j], i, j);
	}
	
	/**
	 * Crea las dimensiones del modelo y lo llena de informaci�n.
	 * 
	 * @param data the information to set
	 * @param columnCount the number of columns in the model
	 */
	protected void create(Object[][] data, int columnCount) {
		setColumnCount(columnCount);
		setRowCount(data.length);
		fillModel(data);
	}
	
	/**
	 * Crea el modelo de informaci�n a partir de una tabla en la BBDD.
	 * 
	 * @param table the table whose information is required
	 * @param columnCount the number of columns in the model
	 */
	protected void createModel(String table, int columnCount) {
		Object[][] data = dao.getData(table, columnCount);
		create(data, columnCount);
	}
	
	/**
	 * Crea el modelo de informaci�n a partir de una tabla en la BBDD y el ID de un contacto.
	 * 
	 * @param table the table whose information is required
	 * @param columnCount the number of columns in the model
	 * @param id contact ID
	 */
	protected void createModelWhereIdC(String table, int columnCount, int id) {
		table = table.toLowerCase().equals(Database.HobbiesTitle) ? Database.ContactsHobbiesTitle : table;
		Object[][] data = dao.getDataWhereIdC(table, columnCount, id);
		create(data, columnCount);
	}
	
	/**
	 * Cambia el modelo de informaci�n a partir de una tabla en la BBDD.
	 * 
	 * @param table the table whose information is required
	 * @param columnCount the number of columns in the model
	 */
	protected void changeView(String table, int columnCount) {
		table = (table.toLowerCase().equals("todos") ? "contactos" : table);
		createModel(table, columnCount);
	}
	
	/**
	 * Cambia el modelo de informaci�n a partir de una palabra y una tabla en la BBDD.
	 * 
	 * @param table the table where the word is going to be searched
	 * @param word the word to search in the com.quathar.app.database
	 * @param columnCount the number of columns in the model
	 */
	public void searchWord(String table, String word, int columnCount) {
		Object[][] data = dao.getDataWhereNombreOrAficionLike(table, word, columnCount);
		create(data, columnCount);
	}
	
	/**
	 * Ordena de mayor a menor el array enviado (filas seleccionadas).<br><br>
	 * 
	 * Las filas seleccionadas siempre vienen ordenadas de menor a mayor y conduce a error del tipo ArrayIndexOutOfBoundsException
	 * porque cuando se utiliza el m�todo removeRow() las filas con mayor n�mero de fila cambian su n�mero
	 * y esto puede hacer que salte la Exception o que borre la fila equivocada.
	 * 
	 * @param selectedRows the selected rows
	 */
	protected void flip(int[] selectedRows) {
		for (int i = 0; i < selectedRows.length - 1; i++)
			for (int j = i + 1; j < selectedRows.length; j++)
				if (selectedRows[i] < selectedRows[j]) {
					int temporalStore = selectedRows[i];
					selectedRows[i] = selectedRows[j];
					selectedRows[j] = temporalStore;
				}
	}
	
	/**
	 * Elimina las filas seleccionadas en el modelo.
	 * 
	 * @param selectedRows the selected rows
	 */
	public void deleteRow(int[] selectedRows) {
		flip(selectedRows);
 		for (int row : selectedRows)
 			removeRow(row);
	}
	
	/**
	 * Elimina todas las filas del modelo.
	 */
	public void deleteAll() {
		int rowCount = getRowCount() - 1;
		while (rowCount >= 0)
			removeRow(rowCount--);
	}
	
}
