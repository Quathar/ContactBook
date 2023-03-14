package com.quathar.app.gui.tables;

import com.quathar.app.dao.DAO;
import com.quathar.app.gui.models.MailModel;

/**
 * MailTable.<br><br>
 * 
 * Componente JTable de la tabla <b>'correos'</b> en la BBDD.
 *
 * @since 2022-05-04
 * @see GeneralTable
 * @see MailModel
 * @author Q
 */
public class MailTable extends GeneralTable { // CLASE FINALIZADA

	private static final long serialVersionUID = 1L;
	
	// CAMPOS
	/**
	 * Modelo de la tabla.
	 */
	private MailModel mModel;
	
	// CONSTRUCTOR
	/**
	 * Constructor.
	 * 
	 * @param dao the data access object
	 */
	public MailTable(DAO dao) {
		mModel = new MailModel(dao);
		setModel(mModel);
		removeColumn(0);
	}
	
	/**
	 * Constructor.<br><br>
	 * 
	 * Crea una tabla con los correos de un contacto.
	 * 
	 * @param dao the data access object
	 * @param id contact ID
	 */
	public MailTable(DAO dao, int id) {
		mModel = new MailModel(dao, id);
		setModel(mModel);
		removeColumn(0);
	}
	
	// M�TODOS
	/**
	 * Inserta un nuevo correo en el modelo.
	 * 
	 * @param data the data to insert
	 */
	public void insertRow(Object[] data) {
		mModel.insertRow(getRowCount(), data);
	}
	
	/**
	 * Elimina las filas seleccionadas en el modelo o en el modelo y la BBDD, dependiendo de su ID.
	 */
	public void deleteSelectedRows() {
		mModel.deleteSelectedRows(getSelectedRows());
	}
	
	/**
	 * Elimina todas las filas de la tabla.
	 */
	public void clean() {
		super.clean(mModel);
	}
	
	// GETTER
	/**
	 * @return the MailModel
	 */
	public MailModel getModel() {
		return mModel;
	}
	
}
