package com.quathar.contactbook.ui.table;

import com.quathar.app.dao.DAO;
import com.quathar.app.gui.frame.ContactBook;
import com.quathar.app.gui.model.ContactHobbyModel;

/**
 * ContactHobbyTable.<br><br>
 * 
 * Componente JTable de la tabla <b>contactos_aficiones</b> en la BBDD.
 *
 * @since 2022-05-04
 * @see ContactHobbyModel
 * @see GeneralTable
 * @author Q
 */
public class ContactHobbyTable extends GeneralTable { // CLASE FINALIZADA

	private static final long serialVersionUID = 1L;
	
	// CAMPOS
	/**
	 * Modelo de la tabla.
	 */
	private ContactHobbyModel chModel;
	
	// CONSTRUCTOR
	/**
	 * Contructor.
	 * 
	 * @param dao the data access object
	 */
	public ContactHobbyTable(DAO dao) {
		chModel = new ContactHobbyModel(dao);
		setModel(chModel);
		place(new int[] {0, 1, 2, 3}, new int[] {1, 2}, (int) (ContactBook.ScreenSize.width * 0.105));
	}
	
	// M�TODOS
	/**
	 * Actualiza la vista de la tabla.
	 */
	public void update() {
		chModel.changeView();
		place(new int[] {0, 1, 2, 3}, new int[] {1, 2}, (int) (ContactBook.ScreenSize.width * 0.105));
	}
	
	/**
	 * Actualiza la vista de la tabla a partir de una palabra.
	 * 
	 * @param word the word to update the table
	 */
	public void update(String word) {
		chModel.searchWord(word);
		place(new int[] {0, 1, 2, 3}, new int[] {1, 2}, 200);
	}
	
	// GETTER
	/**
	 * @return the ContactHobbyModel
	 */
	public ContactHobbyModel getModel() {
		return chModel;
	}
	
}
