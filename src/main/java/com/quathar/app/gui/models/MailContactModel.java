package com.quathar.app.gui.models;

import com.quathar.app.dao.DAO;

/**
 * MailContactModel.<br><br>
 *
 * @since 2022-05-15
 * @see GeneralModel
 * @author Q
 */
public class MailContactModel extends GeneralModel { // CLASE FINALIZADA
	
	// CONSTANTES
	private static final long serialVersionUID = 1L;
	/**
	 * Nombres de las columnas.
	 */
	private final String[] ColumnNames = {"NOMBRE", "CORREO"};
	
	// CAMPOS
	/**
	 * N�mero de columnas.
	 */
	private final int COLUMNS = 2;
	
	// CONSTRUCTOR
	/**
	 * Constructor.
	 * 
	 * @param dao the data access object
	 */
	public MailContactModel(DAO dao) {
		super(dao);
		setColumnIdentifiers(ColumnNames);
		createMailContactModel();
	}
	
	/**
	 * Extrae la informaci�n de la BBDD y la introduce en el modelo.
	 */
	private void createMailContactModel() {
		Object[][] data = dao.getMailContactData(COLUMNS);
		create(data, COLUMNS);
	}
	
	/**
	 * Actualiza la informaci�n del modelo.
	 */
	public void update() {
		createMailContactModel();
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
}
