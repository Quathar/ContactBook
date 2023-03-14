package gui.models;

import dao.DAO;
import database.Database;

/**
 * MailModel.<br><br>
 * 
 * Modelo para manipular la información de la tabla <b>'telefonos'<b> de la BBDD.
 *
 * @since 2022-04-14
 * @see GeneralModel
 * @author Q
 */
public class TelephoneModel extends GeneralModel { // CLASE FINALIZADA
	
	// CONSTANTES
	private static final long serialVersionUID = 1L;
	/**
	 * Nombres de las columnas.
	 */
	private final String[] ColumnNames = {"ID_T", "TELÉFONO", "TIPO"};
	
	// CAMPOS
	/**
	 * Número de columnas.
	 */
	private final int COLUMNAS = 3;
	/**
	 * Nombre de la tabla en la BBDD.
	 */
	private final String TABLE = Database.TelephonesTitle;
	
	// CONSTRUCTORES
	/**
	 * Constructor.<br><br>
	 * 
	 * Crea un modelo vacío.
	 * 
	 * @param dao the data access object
	 */
	public TelephoneModel(DAO dao) {
		super(dao);
		setColumnIdentifiers(ColumnNames);
	}
	
	/**
	 * Constructor.<br><br>
	 * 
	 * Crea el modelo con los teléfonos de un contacto.
	 * 
	 * @param dao the data access object
	 * @param id the contact ID
	 */
	public TelephoneModel(DAO dao, int id) {
		super(dao);
		setColumnIdentifiers(ColumnNames);
		createModelWhereIdC(TABLE, COLUMNAS, id);
	}
	
	// MÉTODOS
	/**
	 * Elimina las filas seleccionadas en el modelo o en el modelo y la BBDD, dependiendo de su ID.
	 * 
	 * @param selectedRows the selected rows
	 */
	public void deleteSelectedRows(int[] selectedRows) {
		if (selectedRows.length > 1)
			flip(selectedRows);
		
		int id_t;
		for (int i = 0; i < selectedRows.length; i++) {
			id_t = Integer.parseInt(getValueAt(selectedRows[i], 0).toString());
			if (id_t != 0) {
	 			dao.unregisterRow(TABLE, id_t);
	 			removeRow(selectedRows[i]);
			} else
				removeRow(selectedRows[i]);
		}
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (getColumnName(columnIndex) == ColumnNames[2])
			return false;
		else
			return true;
	}
	
}
