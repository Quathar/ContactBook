package gui.models;

import dao.DAO;
import database.Database;

/**
 * ContactModel.<br><br>
 * 
 * Modelo para manipular la información de la tabla <b>contactos</b> de la BBDD.
 * 
 * @see GeneralModel
 * @since 2022-04-10
 * @author Q
 */
public class ContactModel extends GeneralModel { // CLASE FINALIZADA

	// CONSTANTES
	private static final long serialVersionUID = 1L;
	/**
	 * Nombres de lss columnas.
	 */
	private final String[] ColumnNames = {"ID", "NOMBRE", "DIRECCIÓN", "NOTAS"};
	
	// CAMPOS
	/**
	 * Número de columnas.
	 */
	private final int COLUMNS = 4;
	/**
	 * Nombre de la tabla en la BBDD.
	 */
	private final String TABLE = Database.ContactsTitle;
	
	// CONSTRUCTOR
	/**
	 * Constructor.
	 * 
	 * @param dao the data access object
	 */
	public ContactModel(DAO dao) {
		super(dao);
		setColumnIdentifiers(ColumnNames);
		createModel(TABLE, COLUMNS);
	}
	
	// MÉTODOS
	/**
	 * Cambia el modelo de información a partir de una tabla en la BBDD.
	 * 
	 * @param table the table whose information is required
	 */
	public void changeView(String table) {
		super.changeView(table, COLUMNS);
	}
	
	/**
	 * Cambia el modelo de información a partir de una palabra y una tabla en la BBDD.
	 * 
	 * @param contactType the table where the word is going to be searched
	 * @param word the word to search in the database
	 */
	public void searchWord(String contactType, String word) {
		super.searchWord(contactType, word, COLUMNS);
	}

	// OVERRIDE
	@Override
	public void deleteRow(int[] selectedRows) {
		flip(selectedRows);
 		for (int i = 0; i < selectedRows.length; i++) {
 			int id = (int) getValueAt(selectedRows[i], 0);
// 			removeRow(selectedRows[i]);
 			dao.unregister(id);
 		}
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		super.setValueAt(aValue, rowIndex, columnIndex);
		int id = Integer.parseInt(	getValueAt(rowIndex, 0).toString());
		String nombre             = getValueAt(rowIndex, 1).toString();
		String direccion          = getValueAt(rowIndex, 2).toString();
		String notas              = getValueAt(rowIndex, 3).toString();
		dao.modifyModel(id, nombre, direccion, notas);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (getColumnName(columnIndex) == ColumnNames[0])
			return false;
		else
			return true;
	}
	
}
