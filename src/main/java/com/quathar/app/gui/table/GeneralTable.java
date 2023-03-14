package com.quathar.app.gui.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.quathar.app.gui.model.GeneralModel;

/**
 * GeneralTable.<br><br>
 * 
 * Clase abstracta con m�todos com�nes para las tablas:<br><br>
 * 
 * - ContactTable<br>
 * - HobbyTable<br>
 * - ContactHobbyTable<br>
 * - TelephoneTable<br>
 * - MailTable
 *
 * @since 2022-05-04
 * @see GeneralModel
 * @author Q
 */
public abstract class GeneralTable extends JTable { // CLASE FINALIZDA
	
	private static final long serialVersionUID = 1L;

	// M�TODOS
	/**
	 * Elimina una columna de la tabla.
	 * 
	 * @param columnIndex �nidice de la columna a eliminar
	 */
	protected void removeColumn(int columnIndex) {
		removeColumn(getColumnModel().getColumn(columnIndex));
	}
	
	/**
	 * Centra las columnas de una tabla.
	 * 
	 * @param columnNumbers columnas que se quieren centrar
	 */
	protected void center(int[] columnNumbers) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		for (int i = 0; i < columnNumbers.length; i++)
			getColumnModel().getColumn(columnNumbers[i]).setCellRenderer(centerRenderer);
	}
	
	/**
	 * Reescala el ancho de las columnas de una tabla.
	 * 
	 * @param width ancho que se le desea dar
	 * @param columnNumbers columnas que se quieren reescalar
	 */
	protected void resize(int[] columnNumbers, int width) {
			for (int i = 0; i < columnNumbers.length; i++)
				getColumnModel().getColumn(columnNumbers[i]).setPreferredWidth(width);
	}
	
	/**
	 * Coloca las columnas de la tabla.
	 * 
	 * @param centerColumnNumbers the columns to center
	 * @param resizeColumnNumbers the columns to resize
	 * @param width the width to resize
	 */
	protected void place(int[] centerColumnNumbers, int[] resizeColumnNumbers, int width) {
		center(centerColumnNumbers);
		resize(resizeColumnNumbers, width);
	}
	
	/**
	 * Elimina las filas seleccionadas del modelo.
	 * 
	 * @param model the model where the information will be deleted
	 */
	protected void deleteRows(GeneralModel model) {
		model.deleteRow(getSelectedRows());
	}
	
	/**
	 * Elimina todas las filas de la tabla.
	 * 
	 * @param model the model where the information will be deleted
	 */
	protected void clean(GeneralModel model) {
		model.setRowCount(0);
	}
	
}
