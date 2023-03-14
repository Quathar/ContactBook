package main;

import javax.swing.JOptionPane;

import com.formdev.flatlaf.IntelliJTheme;

import dao.Menu;

import database.Database;

import gui.GUI;
import gui.themes.Themes;

/**
 * <b>Agenda_Ejecutable</b>.<br><br>
 * 
 * Clase principal para lanzar la aplicaci�n.<br><br>
 * 
 * Tiene un panel de opci�n de manera predeterminada. Tambi�n sirve para cambiar de tema y formatear la agenda.
 *
 * @since 2022-04-05
 * @see Menu
 * @see GUI
 * @author Q
 */
public class Agenda_Ejecutable { // CLASE FINALIZADA
	
	/**
	 * Cambia el tema de la agenda.
	 * 
	 * @param themeIndex the new theme's index
	 * @param themeType the theme's type
	 */
	public static void changeTheme(int themeIndex, String themeType) {
		IntelliJTheme.setup(Themes.getTheme(themeIndex, themeType));
		GUI gui = new GUI(themeIndex, themeType);
		gui.setVisible(true);
		gui.getCardLayout().show(gui.getViews(), "2");
	}
	
	/**
	 * Formatea la agenda y la inicia de nuevo.
	 */
	public static void format() {
		new Database(0);
		// Descomentar para a�adir 
		// algunos registros a la agenda
//		new Database(1);
		init();
	}
	
	/**
	 * Lanza un panel de opciones para que el usuario elija c�mo quiere acceder a la aplicaci�n.
	 */
	private static int options() {
		String msg = "�C�mo quiere acceder a la Agenda?";
		String[] options = {"Console", "Graphic User Interface"};
		
		// Siempre inicia con el primer tema Claro (Cyan)
		IntelliJTheme.setup(Themes.getTheme(0, Themes.Light));
		return JOptionPane.showOptionDialog
				(null, msg, "ACCESO", 0, JOptionPane.QUESTION_MESSAGE, null, options, "Graphic User Interface"); 
	}
	
	/**
	 * Lanza la aplicaci�n.
	 */
	private static void init() {
		switch (options()) {
			case 0:
				new Menu(new Database());
				break;
			case 1:
				new GUI().setVisible(true);
				break;
			default:
				System.exit(0);
		}
	}

	/**
	 * M�todo main.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		init();
//		fastInit();
	}
	
	// DESCOMENTAR EL M�TODO PARA INICIAR SIN PANEL DE OPCIONES
	
	private static void fastInit() {
		IntelliJTheme.setup(Themes.getTheme(0, Themes.Light));
		new GUI().setVisible(true);
//		new menu(new database());
	}

}
