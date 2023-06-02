package com.quathar.contactbook.ui.table;

import com.quathar.contactbook.ui.model.MailModel;

import java.io.Serial;

/**
 * <h1>MailTable</h1>
 *
 * Componente JTable de la tabla <b>'correos'</b> en la BBDD.
 *
 * @since 2022-05-04
 * @see GeneralTable
 * @see MailModel
 * @author Q
 */
public class MailTable extends GeneralTable {

	@Serial
	private static final long serialVersionUID = 1L;
	
	// CAMPOS
//	private MailModel mModel;
//
//	// CONSTRUCTOR
//	public MailTable(DAO dao) {
//		mModel = new MailModel(dao);
//		setModel(mModel);
//		removeColumn(0);
//	}
//
//	/**
//	 * Constructor.<br><br>
//	 *
//	 * Crea una tabla con los correos de un contacto.
//	 *
//	 * @param dao the data access object
//	 * @param id contact ID
//	 */
//	public MailTable(DAO dao, int id) {
//		mModel = new MailModel(dao, id);
//		setModel(mModel);
//		removeColumn(0);
//	}
//
//	// M�TODOS
//	/**
//	 * Inserta un nuevo correo en el modelo.
//	 *
//	 * @param data the data to insert
//	 */
//	public void insertRow(Object[] data) {
//		mModel.insertRow(getRowCount(), data);
//	}
//
//	/**
//	 * Elimina las filas seleccionadas en el modelo o en el modelo y la BBDD, dependiendo de su ID.
//	 */
//	public void deleteSelectedRows() {
//		mModel.deleteSelectedRows(getSelectedRows());
//	}
//
//	/**
//	 * Elimina todas las filas de la tabla.
//	 */
//	public void clean() {
//		super.clean(mModel);
//	}
//
//	// GETTER
//	/**
//	 * @return the MailModel
//	 */
//	public MailModel getModel() {
//		return mModel;
//	}
	
}
