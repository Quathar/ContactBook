package com.quathar.app.gui.table;

import javax.swing.JComboBox;

import com.quathar.app.dao.DAO;
import com.quathar.app.gui.frame.ContactBook;
import com.quathar.app.gui.model.HobbyModel;

/**
 * HobbyTable.<br><br>
 * 
 * Componente JTable de la tabla <b>'aficiones'</b> en la BBDD.
 *
 * @since 2022-05-04
 * @see HobbyModel
 * @see GeneralTable
 * @author Q
 */
public class HobbyTable extends GeneralTable { // CLASE FINALIZADA

	private static final long serialVersionUID = 1L;
	
	// CAMPOS
	/**
	 * Modelo de la tabla.
	 */
	private HobbyModel hModel;
	
	// CONSTRUCTORES
	/**
	 * Constructor.
	 * 
	 * @param frame the frame to set in the model
	 * @param dao the data access object
	 */
	public HobbyTable(ContactBook frame, DAO dao) {
		hModel = new HobbyModel(dao);
		hModel.setGUI(frame);
		setModel(hModel);
		place(new int[] {0}, new int[] {1}, 300);
	}
	
	/**
	 * Constructor.<br><br>
	 * 
	 * Crea una tabla vac�a.
	 * 
	 * @param dao the data access object
	 * @param falso
	 */
	public HobbyTable(DAO dao, boolean falso) {
		hModel = new HobbyModel(dao, falso);
		setModel(hModel);
		removeColumn(0);
	}
	
	/**
	 * Constructor.<br><br>
	 * 
	 * Crea una tabla con las aficiones de un contacto.
	 * 
	 * @param dao the data access object
	 * @param id the contact ID
	 */
	public HobbyTable(DAO dao, int id) {
		hModel = new HobbyModel(dao, id);
		setModel(hModel);
		removeColumn(0);
	}

	// M�TODOS
	/**
	 * Inserta una nueva afici�n en la tabla.
	 * 
	 * @param hobby
	 */
	public void insertHobby(String hobby) {
		hModel.insertHobby(hobby);
		place(new int[] {0}, new int[] {1}, 300);
	}
	
	/**
	 * A�ade una nueva afici�n al modelo.
	 * 
	 * @param hobby the hobby to add
	 */
	public void addHobby(String hobby) {
		hModel.addHobby(hobby);
	}
	
	public void addToComboBox(JComboBox<String> comboBox) {
		int[] selectedRows = getSelectedRows();
		for (int i = 0; i < getSelectedRowCount(); i++) {
			String item = hModel.getValueAt(selectedRows[i], 1).toString();
			comboBox.addItem(item);
		}
	}
	
	/**
	 * Actualiza la vista de la tabla a partir de una palabra.
	 * 
	 * @param word the word to update the table
	 */
	public void update(String word) {
		hModel.searchWord(word);
		place(new int[] {0}, new int[] {1}, 300);
	}
	
	/**
	 * Elimina las filas seleccionadas del modelo.
	 */
	public void deleteSelectedRows() {
		hModel.deleteSelectedRows(getSelectedRows());
	}
	
	/**
	 * Elimina las filas seleccionadas en el modelo o en el modelo y la BBDD, dependiendo de su ID.
	 */
	public void deleteSelectedRows(int id_c) {
		hModel.deleteSelectedRows(id_c, getSelectedRows());
	}
	
	/**
	 * Elimina todas las filas de la tabla.
	 */
	public void clean() {
		super.clean(hModel);
	}
	
	// GETTER
	/**
	 * @return the HobbyModel
	 */
	public HobbyModel getModel() {
		return hModel;
	}
	
}
