package com.quathar.app.io;

import javax.swing.JOptionPane;

/**
 * MSG.<br><br>
 * 
 * Clase est�tica para mostrar mensajes mediante JOptionPane.
 * 
 * @since 2022-05-05
 * @author Q
 */
public class MSG extends JOptionPane { // CLASE FINALIZADA

	private static final long serialVersionUID = 1L;

	/**
	 * Muestra un panel de confirmaci�n con opci�n 'OK'.
	 * 
	 * @param msg the message to show
	 * @return the index of the chosen option
	 */
	public static int confirmMessage(String msg) {
		return showConfirmDialog(null, msg);
	}
	
	/**
	 * Muestra un panel de confirmaci�n con las opciones 'S� / No'.
	 * 
	 * @param msg the message to show
	 * @return the index of the chosen option
	 */
	public static int defaultOptionMessage(String msg) {
		Object[] opciones = {"S�", "No"};
		return showOptionDialog(null, msg, "AVISO", 0, JOptionPane.QUESTION_MESSAGE, null, opciones, "No");
	}
	
	/**
	 * Muestra un panel de confirmaci�n con las opciones 'S� / No / Cancelar'.
	 * 
	 * @param msg the message to show
	 * @return the index of the chosen option
	 */
	public static int questionMessage(String msg) {
		return showConfirmDialog(null, msg, "AVISO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	}
	
	/**
	 * Muestra un panel de advertencia.
	 * 
	 * @param msg the message to show
	 */
	public static void warningMessage(String msg) {
		showMessageDialog(null, msg, "AVISO", JOptionPane.WARNING_MESSAGE, null);
	}
	
}
