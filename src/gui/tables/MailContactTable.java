package gui.tables;

import dao.DAO;
import gui.GUI;
import gui.models.MailContactModel;

/**
 * MailContactTable.<br><br>
 * 
 * Componente JTable para visualizar a TODOS los contactos con sus correos.
 *
 * @since 2022-05-15
 * @see GeneralTable
 * @see MailContactModel
 * @author Q
 */
public class MailContactTable extends GeneralTable { // CLASE FINALIZADA
	
	private static final long serialVersionUID = 1L;
	
	// CAMPOS
	/**
	 * Modelo de la tabla.
	 */
	private MailContactModel mcModel; 
	
	// CONSTRUCTOR
	/**
	 * Constructor.
	 * 
	 * @param dao the data access object
	 */
	public MailContactTable(DAO dao) {
		mcModel = new MailContactModel(dao);
		setModel(mcModel);
		resize(new int[] {1}, (int) (GUI.ScreenSize.width * 0.1));
	}
	
	// MÉTODOS
	/**
	 * Actualiza la información de la tabla.
	 */
	public void update() {
		mcModel.update();
		resize(new int[] {1}, (int) (GUI.ScreenSize.width * 0.1));
	}
	
}
