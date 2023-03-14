package com.quathar.app.model;

/**
 * Hobby.
 * 
 * @since 2022-04-03
 * @author Q
 */
public class Hobby { // CLASE FINALIZADA

	// CAMPOS
	/**
	 * El ID de la afici�n.
	 */
	private int id_h;
	/**
	 * El nombre de la afici�n.
	 */
	private String name;

	// CONSTRUCTORES
	/**
	 * Constructor.
	 * 
	 * @param id the hobby ID
	 * @param name the hobby name
	 */
	public Hobby(int id, String name) {
		this.id_h = id;
		this.name = name;
	}
	
	/**
	 * Constructor.
	 * 
	 * @param id the hobby ID
	 */
	public Hobby(int id) {
		this.id_h = id;
	}
	
	/**
	 * Constructor.
	 * 
	 * @param name the hobby name
	 */
	public Hobby(String name) {
		this.name = name;
	}

	// M�TODOS
	@Override
	public String toString() {
		return String.format("A%02d: [ %s ]", id_h, name);
	}
	
	// GETTERS AND SETTERS
	/**
	 * @return the id_h
	 */
	public int getId() {
		return id_h;
	}

	/**
	 * @param id_h the id_h to set
	 */
	public void setId(int id) {
		this.id_h = id;
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
