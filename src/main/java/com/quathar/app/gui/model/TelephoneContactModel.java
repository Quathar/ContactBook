package com.quathar.app.gui.model;

import com.quathar.app.dao.DAO;

/**
 * TelephoneContactModel.<br><br>
 *
 * @since 2022-05-15
 * @see GeneralModel
 * @author Q
 */
public class TelephoneContactModel extends GeneralModel { // CLASE FINALIZADA

	// CONSTANTES
	private static final long serialVersionUID = 1L;
	/**
	 * Nombres de las columnas.
	 */
	private final String[] ColumnNames = {"NOMBRE", "TEL�FONO", "TIPO"};
	
	// CAMPOS
	/**
	 * N�mero de columnas.
	 */
	private final int COLUMNS = 3;
	
	// CONSTRUCTOR
	/**
	 * Constructor.
	 * 
	 * @param dao the data access object
	 */
	public TelephoneContactModel(DAO dao) {
		super(dao);
		setColumnIdentifiers(ColumnNames);
		createTelephoneContactModel();
	}
	
	/**
	 * Extrae la informaci�n de la BBDD y la introduce en el modelo.
	 */
	private void createTelephoneContactModel() {
		Object[][] data = dao.getTelephoneContactData(COLUMNS);
		create(data, COLUMNS);
	}
	
	/**
	 * Actualiza la informaci�n del modelo.
	 */
	public void update() {
		createTelephoneContactModel();
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

}
