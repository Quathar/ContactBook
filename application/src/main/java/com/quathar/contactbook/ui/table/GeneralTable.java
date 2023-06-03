package com.quathar.contactbook.ui.table;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import java.io.Serial;

/**
 * <h1>GeneralTable</h1>
 * <br>
 * Abstract class with common methods for tables:
 * <ul>
 *     <li>ContactTable</li>
 *     <li>HobbyTable</li>
 *     <li>ContactHobbyTable</li>
 *     <li>TelephoneTable</li>
 *     <li>MailTable</li>
 * </ul>
 *
 * @since 2022-05-04
 * @version 1.0
 * @author Q
 */
public abstract class GeneralTable extends JTable {

	// <<-CONSTANTS->>
	@Serial
	private static final long serialVersionUID = 1L;

	// <<-METHODS->>
	/**
	 * Centers the specified columns of the table.
	 *
	 * @param columnIndexes the indexes of the columns to be centered
	 */
	protected void center(int[] columnIndexes) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		for (int index : columnIndexes)
			columnModel.getColumn(index)
					   .setCellRenderer(centerRenderer);
	}

	/**
	 * Rescales the specified columns of the table.
	 *
	 * @param columnIndexes the indexes of the columns to be rescaled
	 * @param width the width at which they will be rescaled
	 */
	protected void resize(int[] columnIndexes, int width) {
		for (int index : columnIndexes)
			columnModel.getColumn(index)
					   .setPreferredWidth(width);
	}

	/**
	 * Centers and rescales the specified columns of the table.
	 *
	 * @param columnIndexes2BeCentered the indexes of the columns to be centered
	 * @param columnIndexes2BeResized the indexes of the columns to be rescaled
	 * @param resizeWidth the width at which they will be rescaled
	 */
	protected void place(
			int[] columnIndexes2BeCentered,
			int[] columnIndexes2BeResized,
			int resizeWidth
	) {
		center(columnIndexes2BeCentered);
		resize(columnIndexes2BeResized, resizeWidth);
	}

//	protected void removeColumn(int columnIndex) {
//		removeColumn(getColumnModel().getColumn(columnIndex));
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
