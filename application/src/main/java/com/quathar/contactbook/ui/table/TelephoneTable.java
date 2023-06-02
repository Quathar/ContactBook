package com.quathar.contactbook.ui.table;

import com.quathar.contactbook.ui.model.TelephoneModel;

import java.io.Serial;

/**
 * <h1>TelephoneTable</h1>
 *
 * Componente JTable de la tabla <b>'telefonos'</b> en la BBDD.
 *
 * @since 2022-05-04
 * @see GeneralTable
 * @see TelephoneModel
 * @author Q
 */
public class TelephoneTable extends GeneralTable {

	@Serial
	private static final long serialVersionUID = 1L;
	
	// CAMPOS
//	private TelephoneModel tModel;
//
//	// CONSTRUCTOR
//	public TelephoneTable(DAO dao) {
//		tModel = new TelephoneModel(dao);
//		setModel(tModel);
//		removeColumn(0);
//		place(new int[] {0, 1}, new int[] {0}, 200);
//	}
//
//	public TelephoneTable(DAO dao, int id) {
//		tModel = new TelephoneModel(dao, id);
//		setModel(tModel);
//		removeColumn(0);
//		place(new int[] {0, 1}, new int[] {0}, 200);
//	}
//
//	// M�TODOS
//	public void insertRow(Object[] data) {
//		tModel.insertRow(getRowCount(), data);
//		place(new int[] {0, 1}, new int[] {0}, 200);
//	}
//
//	public void deleteSelectedRows() {
//		tModel.deleteSelectedRows(getSelectedRows());
//	}
//
//	public void clean() {
//		super.clean(tModel);
//	}
//
//	// GETTER
//	public TelephoneModel getModel() {
//		return tModel;
//	}
	
}
