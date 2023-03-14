package com.quathar.app.model;

/**
 * Telephone.
 * 
 * @since 2022-04-03
 * @author Q
 */
public class Telephone { // CLASE FINALIZADA

	// CONSTANTES
	/**
	 * Tipos de tel�fono.
	 */
	public static final String[] Type = {"M�vil", "Fijo"};
	/**
	 * Tipo de tel�fono como lo devuelve la BBDD.
	 */
	private static final String[] G = {"M", "F", "O"};
	
	// CAMPOS
	/**
	 * El ID del tel�fono.
	 */
	private int id_t;
	/**
	 * El n�mero de tel�fono.
	 */
	private String number;
	/**
	 * El tipo de tel�fono
	 */
	private String type;
	
	// CONSTRUCTOR
	/**
	 * Constructor.
	 * 
	 * @param id_t the telephone ID
	 * @param number the telephone number
	 * @param type the telephone type
	 */
	public Telephone(int id_t, String number, String type) {
		this.id_t = id_t;
		this.number = number;
		this.type = type;
	}

	// M�TODOS
	/**
	 * Comprueba que el tipo de tel�fono sea correcto.
	 * 
	 * @param type the type to check
	 */
	public static Object checkType(Object type) {
		if (type.equals(Type[0]))
			return G[0];
		else
			return G[1];
	}
	
	@Override
	public String toString() {
		return String.format("T%02d: [ %s | %s ]", id_t, number, type);
	}
	
	// GETTERS AND SETTERS
	/**
	 * @return the id_t
	 */
	public int getId_t() {
		return id_t;
	}
	
	/**
	 * @param id_t the id_t to set
	 */
	public void setId_t(int id_t) {
		this.id_t = id_t;
	}
	
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
}
