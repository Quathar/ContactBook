package com.quathar.contactbook.ui.table;

import javax.swing.JTable;
import java.io.Serial;

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
 * @version 2.0
 * @author Q
 */
public abstract class GeneralTable extends JTable {

	// <<-CONSTANTS->>
	@Serial
	private static final long serialVersionUID = 1L;

	// <<-METHODS->>
	protected void removeColumn(int columnIndex) {
		removeColumn(getColumnModel().getColumn(columnIndex));
	}
//
//	protected void center(int[] columnNumbers) {
//		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
//
//		for (int columnNumber : columnNumbers)
//			getColumnModel()
//					.getColumn(columnNumber)
//					.setCellRenderer(centerRenderer);
//	}
//
//	protected void resize(int[] columnNumbers, int width) {
//			for (int i = 0; i < columnNumbers.length; i++)
//				getColumnModel().getColumn(columnNumbers[i]).setPreferredWidth(width);
//	}
//
//	protected void place(int[] centerColumnNumbers, int[] resizeColumnNumbers, int width) {
//		center(centerColumnNumbers);
//		resize(resizeColumnNumbers, width);
//	}
//
//	protected void deleteRows(GeneralModel model) {
//		model.deleteRow(getSelectedRows());
//	}
//
//	protected void clean(GeneralModel model) {
//		model.setRowCount(0);
//	}
	
}
