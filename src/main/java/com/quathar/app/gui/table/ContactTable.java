package com.quathar.app.gui.table;

import com.quathar.app.dao.DAO;
import com.quathar.app.gui.model.ContactModel;

/**
 * ContactTable.<br><br>
 * 
 * Componente JTable de la tabla <b>contactos</b> en la BBDD.
 *
 * @since 04-05-2022
 * @see GeneralTable
 * @see ContactModel
 * @author Q
 */
public class ContactTable extends GeneralTable { // CLASE FINALIZADA

	private static final long serialVersionUID = 1L;
	
	// CAMPOS
	/**
	 * Modelo de la tabla.
	 */
	private ContactModel cModel;
	
	// CONSTRUCTOR
	/**
	 * Contructor.
	 * 
	 * @param dao the data access object
	 */
	public ContactTable(DAO dao) {
		cModel = new ContactModel(dao);
		setModel(cModel);
		removeColumn(0);
	}
	
	// M�TODOS
	/**
	 * Coloca las columnas de la tabla.
	 */
	public void place() {
		center(new int[] {0});
		resize(new int[] {1, 2, 3}, 225);
	}
	
	/**
	 * Actualiza la vista de la tabla.
	 * 
	 * @param contactType tipo de contacto (persona, empresa, mascota o todos)
	 */
	public void update(String contactType) {
		cModel.changeView(contactType);
		removeColumn(0);
	}
	
	/**
	 * Actualiza la vista de la tabla contactos.
	 */
	public void update() {
		update("todos");
	}
	
	/**
	 * Actualiza la vista de la tabla a partir del tipo de contacto y de una palabra.
	 * 
	 * @param contactType
	 * @param word the word to update the table
	 */
	public void update(String contactType, String word) {
		contactType = contactType.toLowerCase().equals("todos") ? "contactos" : contactType;
		cModel.searchWord(contactType, word);
		removeColumn(0);
	}
	
	/**
	 * Elimina las filas seleccionadas de la tabla.
	 */
	public void deleteRows() {
		super.deleteRows(cModel);
	}
	
}
