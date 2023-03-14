package com.quathar.app.gui.tables;

import com.quathar.app.dao.DAO;
import com.quathar.app.gui.models.TelephoneModel;

/**
 * TelephoneTable.<br><br>
 *
 * Componente JTable de la tabla <b>'telefonos'</b> en la BBDD.
 *
 * @since 2022-05-04
 * @see GeneralTable
 * @see TelephoneModel
 * @author Q
 */
public class TelephoneTable extends GeneralTable { // CLASE FINALIZADA

	private static final long serialVersionUID = 1L;
	
	// CAMPOS
	/**
	 * Modelo de la tabla.
	 */
	private TelephoneModel tModel;
	
	// CONSTRUCTOR
	/**
	 * Contructor.
	 * 
	 * @param dao the data access object
	 */
	public TelephoneTable(DAO dao) {
		tModel = new TelephoneModel(dao);
		setModel(tModel);
		removeColumn(0);
		place(new int[] {0, 1}, new int[] {0}, 200);
	}
	
	/**
	 * Constructor.<br><br>
	 * 
	 * Crea una tabla con los tel�fonos de un contacto.
	 * 
	 * @param dao the data access object
	 * @param id the contact ID
	 */
	public TelephoneTable(DAO dao, int id) {
		tModel = new TelephoneModel(dao, id);
		setModel(tModel);
		removeColumn(0);
		place(new int[] {0, 1}, new int[] {0}, 200);
	}
	
	// M�TODOS
	/**
	 * Inserta un nuevo tel�fono en el modelo.
	 * 
	 * @param data the data to insert
	 */
	public void insertRow(Object[] data) {
		tModel.insertRow(getRowCount(), data);
		place(new int[] {0, 1}, new int[] {0}, 200);
	}
	
	/**
	 * Elimina las filas seleccionadas en el modelo o en el modelo y la BBDD, dependiendo de su ID.
	 */
	public void deleteSelectedRows() {
		tModel.deleteSelectedRows(getSelectedRows());
	}
	
	/**
	 * Elimina todas las filas de la tabla.
	 */
	public void clean() {
		super.clean(tModel);
	}
	
	// GETTER
	/**
	 * @return the TelephoneModel
	 */
	public TelephoneModel getModel() {
		return tModel;
	}
	
}
