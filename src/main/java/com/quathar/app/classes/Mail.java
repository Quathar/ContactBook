package com.quathar.app.classes;

/**
 * Mail.
 * 
 * @since 2022-04-03
 * @author Q
 */
public class Mail { // CLASE FINALIZADA

	// CAMPOS
	/**
	 * El id del correo.
	 */
	private int id_m;
	/**
	 * El nombre del correo.
	 */
	private String name;
	
	// CONSTRUCTOR
	/**
	 * Constructor.
	 * 
	 * @param id_m the mail ID
	 * @param name the mail name
	 */
	public Mail(int id_m, String name) {
		this.id_m = id_m;
		this.name = name;
	}
	// M�TODOS
	@Override
	public String toString() {
		return String.format("M%02d: [ %s ]", id_m, name);
	}
		
	// GETTERS AND SETTERS
	/**
	 * @return the id_m
	 */
	public int getId_m() {
		return id_m;
	}

	/**
	 * 
	 * @param id_m the id_m to set
	 */
	public void setId_m(int id_m) {
		this.id_m = id_m;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
