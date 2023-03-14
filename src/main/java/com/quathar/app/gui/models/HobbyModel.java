package com.quathar.app.gui.models;

import com.quathar.app.dao.DAO;
import com.quathar.app.database.Database;
import com.quathar.app.gui.GUI;
import com.quathar.app.io.MSG;

/**
 * HobbyModel.<br><br>
 * 
 * Modelo que maneja informaci�n de la tabla <b>aficiones</b> en la BBDD.
 *
 * @since 2022-04-19
 * @see GeneralModel
 * @see GUI
 * @author Q
 */
public class HobbyModel extends GeneralModel { // CLASE FINALIZADA

	// CONSTANTES
	private static final long serialVersionUID = 1L;
	/**
	 * Nombre de las columnas.
	 */
	private final String[] ColumnNames = {"ID", "AFICI�N"};

	// CAMPOS
	/**
	 * N�mero de columnas.
	 */
	private final int COLUMNAS = 2;
	/**
	 * Nombre de la tabla en la BBDD.
	 */
	private final String TABLE = Database.HobbiesTitle;
	/**
	 * Graphic User Interface.
	 */
	private GUI frame;
	
	// CONSTRUCTORES
	/**
	 * Constructor.
	 * 
	 * @param dao the data access object
	 */
	public HobbyModel(DAO dao) {
		super(dao);
		setColumnIdentifiers(ColumnNames);
		createModel(TABLE, 2);
	}
	
	/**
	 * Constructor.<br><br>
	 * 
	 * Crea un modelo vac�o.
	 * 
	 * @param dao the data access object
	 * @param voidType the boolean to create the void type
	 */
	public HobbyModel(DAO dao, boolean voidType) {
		super(dao);
		setColumnIdentifiers(ColumnNames);
	}
	
	/**
	 * Constructor.
	 * 
	 * Crea el modelo con las aficiones de un contacto.
	 * 
	 * @param id contact ID
	 */
	public HobbyModel(DAO dao, int id) {
		super(dao);
		setColumnIdentifiers(ColumnNames);
		createModelWhereIdC(TABLE, COLUMNAS, id);
	}
	
	// M�TODOS
	/**
	 * A�ade una afici�n a la BBDD.
	 * 
	 * @param hobby the hobby to insert
	 */
	public void insertHobby(String hobby) {
		dao.registerHobby(hobby);
		changeView(TABLE, COLUMNAS);
	}
	
	/**
	 * A�ade una afici�n al modelo.
	 * 
	 * @param hobby the hobby to add
	 */
	public void addHobby(String hobby) {
		Object[] data = {0, hobby};
		insertRow(getRowCount(), data);
	}
	
	/**
	 * Cambia la vista del modelo a partir de una palabra.
	 * 
	 * @param word the word to update de model
	 */
	public void searchWord(String word) {
		super.searchWord(TABLE, word, COLUMNAS);
	}
	
	/**
	 * Comprueba el n�mero Stops cuando se intentan borrar aficiones.
	 * 
	 * @param stop the number of hobbies that cannot be removed
	 */
	private void checkStops(int stop) {
		if (stop > 0) {
			String[] msg = {
					"Est� intentando borrar 1 afici�n utilizada",
					"Est� intentando borrar " + stop + " aficiones utilizadas"
			};
			MSG.warningMessage(stop == 1 ? msg[0] : msg[1]);
		}
	}
	
	/**
	 * Elimina las filas seleccionadas del modelo y de la tabla <b>'aficiones'</b> en la BBDD.
	 * 
	 * @param selectedRows the selected rows
	 */
	public void deleteSelectedRows(int[] selectedRows) {
		if (selectedRows.length > 1)
			flip(selectedRows);
		
		int id_h;
		int stop = 0;
		for (int i = 0; i < selectedRows.length; i++) {
			id_h = Integer.parseInt(getValueAt(selectedRows[i], 0).toString());
			if (!dao.checkIdH(id_h)) {
				dao.unregisterRow(TABLE, id_h);
				removeRow(selectedRows[i]);
			} else
				stop++;
		}
		checkStops(stop);
	}
	
	/**
	 * Elimina las filas seleccionadas en el modelo o en el modelo y la BBDD, dependiendo de su ID de afici�n.
	 * 
	 * @param selectedRows the selected rows
	 */
	public void deleteSelectedRows(int id_c, int[] selectedRows) {
		if (selectedRows.length > 1)
			flip(selectedRows);
		
		int id_h;
		for (int i = 0; i < selectedRows.length; i++) {
			id_h = Integer.parseInt(getValueAt(selectedRows[i], 0).toString());
			if (id_h != 0) {
	 			dao.unregisterHobbyWhereIdC(id_c, id_h);
	 			removeRow(selectedRows[i]);
			} else
				removeRow(selectedRows[i]);
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		super.setValueAt(aValue, rowIndex, columnIndex);
		int id = Integer.parseInt(getValueAt(rowIndex, 0).toString());
		String newName = aValue.toString();
		dao.modifyHobby(id, newName);
		frame.getContactHobbyTable().update();
	}
	
	// OVERRIDE
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (getColumnName(columnIndex) == ColumnNames[0])
			return false;
		else
			return true;
	}
	
	// SETTER
	/**
	 * @param frame the frame to set
	 */
	public void setGUI(GUI frame) {
		this.frame = frame;
	}
	
}
