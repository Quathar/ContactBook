package gui.tables;

import dao.DAO;
import gui.GUI;
import gui.models.TelephoneContactModel;

/**
 * TelephoneContactTable.<br><br>
 * 
 * Componente JTable para visualizar a TODOS los contactos con sus teléfonos.
 *
 * @since 2022-05-15
 * @see GeneralTable
 * @see TelephoneContactModel
 * @author Q
 */
public class TelephoneContactTable extends GeneralTable { // CLASE FINALIZADA

	private static final long serialVersionUID = 1L;
	
	// CAMPOS
	/**
	 * Modelo de la tabla.
	 */
	private TelephoneContactModel tcModel; 
	
	// CONSTRUCTOR
	/**
	 * Constructor.
	 * 
	 * @param dao the data access object
	 */
	public TelephoneContactTable(DAO dao) {
		tcModel = new TelephoneContactModel(dao);
		setModel(tcModel);
		place(new int[] {1, 2}, new int[] {0, 1}, (int) (GUI.ScreenSize.width * 0.105));
		resize(new int[] {1}, (int) (GUI.ScreenSize.width * 0.15));
	}
	
	// MÉTODOS
	/**
	 * Actualiza la información de la tabla.
	 */
	public void update() {
		tcModel.update();
		place(new int[] {1, 2}, new int[] {0, 1}, (int) (GUI.ScreenSize.width * 0.105));
		resize(new int[] {1}, (int) (GUI.ScreenSize.width * 0.15));
	}
	
}
