package com.quathar.app.gui.models;

import com.quathar.app.dao.DAO;
import com.quathar.app.database.Database;

/**
 * MailModel.<br><br>
 * 
 * Modelo para manipular la informaci�n de la tabla <b>'correos'</b> de la BBDD.
 *
 * @since 2022-04-14
 * @see GeneralModel
 * @author Q
 */
public class MailModel extends GeneralModel { // CLASE FINALIZADA
	
	// CONSTANTES
	private static final long serialVersionUID = 1L;
	/**
	 * Nombre de la columna.
	 */
	private final String[] ColumnNames = {"ID_M", "CORREO"};
	
	// CAMPOS
	/**
	 * N�mero de columnas.
	 */
	private final int COLUMNAS = 2;
	/**
	 * Nombre de la tabla en la BBDD.
	 */
	private final String TABLE = Database.MailsTitle;
	
	// CONSTRUCTORES
	/**
	 * Constructor.<br><br>
	 * 
	 * Crea un modelo vac�o.
	 * 
	 * @param dao the data access object
	 */
	public MailModel(DAO dao) {
		super(dao);
		setColumnIdentifiers(ColumnNames);
	}
	
	/**
	 * Constructor.<br><br>
	 * 
	 * Crea el modelo con los correos de un contacto.
	 * 
	 * @param dao the data access object
	 * @param id the contact ID
	 */
	public MailModel(DAO dao, int id) {
		super(dao);
		setColumnIdentifiers(ColumnNames);
		createModelWhereIdC(TABLE, COLUMNAS, id);
	}
	
	// M�TODOS
	/**
	 * Elimina las filas seleccionadas en el modelo o en el modelo y la BBDD, dependiendo de su ID.
	 * 
	 * @param selectedRows the selected rows
	 */
	public void deleteSelectedRows(int[] selectedRows) {
		if (selectedRows.length > 1)
			flip(selectedRows);
		
		int id_m;
		for (int i = 0; i < selectedRows.length; i++) {
			id_m = Integer.parseInt(getValueAt(selectedRows[i], 0).toString());
			if (id_m != 0) {
	 			dao.unregisterRow(TABLE, id_m);
	 			removeRow(selectedRows[i]);
			} else
				removeRow(selectedRows[i]);
		}
	}
	
}
