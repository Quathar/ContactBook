package com.quathar.app.model;

import java.util.ArrayList;

/**
 * Contact.<br><br>
 * 
 * Clase utilizada para almacenar la informaci�n de un contacto.
 *
 * @since 2022-04-03
 * @author Q
 */
public class Contact { // CLASE FINALIZADA

	// CONTANTES
	/**
	 * Tipos de contacto.
	 */
	public static final String[] Type = {"personas", "empresas", "mascotas"};
	/**
	 * G�nero de los contactos.
	 */
	public static final String[] Gender = {"Masculino", "Femenino", "Otro"};
	/**
	 * G�nero de los contactos como los devuelve la BBDD.
	 */
	public static final String[] G = {"M", "F", "O"}; // ContactInfo
	
	// CAMPOS
	/**
	 * El tipo de contacto.
	 */
	private String type;
	/**
	 * El ID del contacto.
	 */
	private int id_c;
	/**
	 * El nombre del contacto.
	 */
	private String name;
	/**
	 * La direcci�n del contacto.
	 */
	private String address;
	/**
	 * Las notas del contacto.
	 */
	private String notes;
	/**
	 * Los apellidos del contacto.
	 */
	private String surnames;
	/**
	 * El g�nero del contacto.
	 */
	private String gender;
	/**
	 * La fecha de nacimiento del contacto.
	 */
	private String birthDate;
	/**
	 * Las aficiones del contacto.
	 */
	private ArrayList<Hobby> hobbies;
	/**
	 * Los tel�fonos del contacto.
	 */
	private ArrayList<Telephone> telephones;
	/**
	 * Los correos del contacto.
	 */
	private ArrayList<Mail> mails;
	
	// CONSTRUCTORES
	/**
	 * Contructor.
	 */
	public Contact() {
		name = "";
		address = "";
		notes = "";
		surnames = "";
		gender = "M";
		birthDate = "";
		hobbies = new ArrayList<Hobby>();
		telephones = new ArrayList<Telephone>();
		mails = new ArrayList<Mail>();
	}
	
	/**
	 * Constructor.
	 * 
	 * @param id_c the contact ID
	 */
	public Contact(int id_c) {
		this.id_c = id_c;
		hobbies = new ArrayList<Hobby>();
		telephones = new ArrayList<Telephone>();
		mails = new ArrayList<Mail>();
	}

	// M�TODOS
	@Override
	public String toString() {
		String out = String.format(
				"%02d : [ %s | %s | %s | %s ]%n"
						+ "[ %s | %s | %s ]%n"
						+ "%s%n"
						+ "%s%n"
						+ "%s%n",
				id_c, name, address, notes, type,
				surnames, gender, birthDate,
				hobbies,
				telephones,
				mails
			);
		return out;
	}
	
	// GETTERS AND SETTERS
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
	
	/**
	 * @return the id_c
	 */
	public int getId_c() {
		return id_c;
	}

	/**
	 * @param id_c the id_c to set
	 */
	public void setId_c(int id_c) {
		this.id_c = id_c;
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

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return the surnames
	 */
	public String getSurnames() {
		return surnames;
	}

	/**
	 * @param surnames the surnames to set
	 */
	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Comprueba que el g�nero sea 'M', 'F' o 'O',
	 * 
	 * @param gender the gender to analize
	 */
	private void checkGender(String gender) {
		for (int i = 0; i < Gender.length; i++) { // problemon
			if (gender.equals(Gender[i]) || gender.equals(G[i]))
				this.gender = G[i];
		}
	}
	
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		checkGender(gender);
	}

	/**
	 * @return the birthDate
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	/**
	 * @return the ArrayList<Hobby> of hobbies
	 */
	public ArrayList<Hobby> getHobbies() {
		return hobbies;
	}
	
	/**
	 * @param hobbies the ArrayList<Hobby> hobbies to set
	 */
	public void setHobbies(ArrayList<Hobby> hobbies) {
		this.hobbies = hobbies;
	}
	
	/**
	 * @return the ArrayList<Telephone> of telephones 
	 */
	public ArrayList<Telephone> getTelephones() {
		return telephones;
	}

	/**
	 * @param telephones the ArrayList<Telephone> telephones to set
	 */
	public void setTelephones(ArrayList<Telephone> telephones) {
		this.telephones = telephones;
	}

	/**
	 * @return the ArrayList<String> of mails
	 */
	public ArrayList<Mail> getMails() {
		return mails;
	}

	/**
	 * @param mails the ArrayList<String> mails to set
	 */
	public void setMails(ArrayList<Mail> mails) {
		this.mails = mails;
	}
	
}
