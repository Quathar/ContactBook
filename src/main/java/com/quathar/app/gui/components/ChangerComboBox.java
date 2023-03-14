package com.quathar.app.gui.components;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 * ThemeComboBox.<br><br>
 * 
 * Componente JComboBox del desplegable de temas.
 * 
 * @since 2022-05-08
 * @author Q
 */
public class ChangerComboBox extends JComboBox<String> { // CLASE FINALIZADA

	private static final long serialVersionUID = 1L;
	
	// CONSTRUCTOR
	/**
	 * Constructor.
	 * 
	 * @param items the items to set
	 */
	public ChangerComboBox(String[] items) {
		newModel(items);
	}
	
	// M�TODOS
	/**
	 * Crea y a�ade un nuevo modelo.
	 * 
	 * @param elements the items to set
	 */
	private void newModel(String[] elements) {
		setModel(new DefaultComboBoxModel<String>(elements));
	}
	
	/**
	 * Cambia el nombre de los items que hay en el desplegable.
	 * 
	 * @param elements the items to set
	 */
	public void changeList(String[] elements) {
		newModel(elements);
	}
	
}
