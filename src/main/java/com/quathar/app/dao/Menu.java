package com.quathar.app.dao;

import java.util.ArrayList;

import com.quathar.app.classes.Contact;
import com.quathar.app.classes.Mail;
import com.quathar.app.classes.Telephone;

import com.quathar.app.database.Database;

import com.quathar.app.gui.GUI;

import com.quathar.app.io.IO;
import com.quathar.app.io.RegexFilter;

import com.quathar.app.Agenda_Ejecutable;

/**
 * Menu.<br><br>
 * 
 * Clase utilizada para interactuar con la BBDD desde la consola.<br><br>
 * S�lo permite los aspectos m�s b�sicos, NO se pueden agregar ni a�adir (a un contacto) aficiones.
 *
 * @since 2022-04-05
 * @see Database
 * @see IO
 * @author Q
 */
public class Menu { // CLASE FINALZIADA
	
	// CAMPOS
	/**
	 * Clase con conexi�n a la BBDD.
	 */
	private Database db;
	/**
	 * Contact object.
	 */
	private Contact c;
	
	// CONSTRUCTOR
	/**
	 * Constructor.
	 * 
	 * @param db agenda a la que se implementa este men�
	 */
	public Menu(Database db) {
		this.db = db;
		c = new Contact();
		while (menu());
	}
	
	// M�TODOS
	// <<ALTA>>
	/**
	 * Obtiene los datos para crear un nuevo contacto e introdcirlo en la BBDD.
	 * 
	 * @return true/false - if the contact was/wasn't added successfully
	 */
	private boolean register() {
		IO.println("NOMBRE ?");
		c.setName(IO.readStringNotBlank());
		IO.println("DIRECCION ?");
		c.setAddress(IO.readStringNotBlank());
		IO.println("NOTAS ?");
		c.setNotes(IO.readStringNotBlank());
		
		if (c.getType().equals(Contact.Type[0])) {
			IO.println("APELLIDOS ?");
			c.setSurnames(IO.readStringNotBlank());
			IO.println("G�NERO ?");
			c.setGender(IO.readStringNotBlank());
			IO.println("FECHA DE NACIMIENTO ?");
			c.setBirthDate(IO.readStringNotBlank());
		} else if (c.getType().equals(Contact.Type[2]) ) {
			IO.println("FECHA DE NACIMIENTO ?");
			c.setBirthDate(IO.readStringNotBlank());
		}
		
		// PERSONAS O EMPRESAS
		if (c.getType().equals(Contact.Type[0]) || c.getType().equals(Contact.Type[1])) {
			// TEL�FONOS
			IO.println("CU�NTOS TEL�FONOS DESEA A�ADIR ?");
			int size = IO.readInt();
			if (size > 0) {
				ArrayList<Telephone> telephones = new ArrayList<Telephone>();
				for (int i = 0; i < size; i++) {
					IO.println(i + 1 + " :");
					IO.println("N�MERO ?");
					String tNumber = RegexFilter.checkTelephone(IO.readStringNotBlank());
					while (tNumber.equals("ERROR")) {
						IO.println("Introduzca 9 n�meros");
						tNumber = RegexFilter.checkTelephone(IO.readStringNotBlank());
					}
					IO.println("TIPO ? (M�vil | Fijo)");
					String tType = IO.readStringNotBlank().toUpperCase();
					tType = (tType.equals("M") || tType.equals("F") ? tType : "M");
					telephones.add(new Telephone(0, tNumber, tType));
				}
				c.setTelephones(telephones);
			}
			
			// CORREOS
			IO.println("CU�NTOS CORREOS DESEA A�ADIR ?");
			size = IO.readInt();
			if (size > 0) {
				ArrayList<Mail> mails = new ArrayList<Mail>();
				for (int i = 0; i < size; i++) {
					IO.println(i + 1 + " :");
					IO.println("CORREO ?");
					String mName = RegexFilter.checkMail(IO.readStringNotBlank());
					while (mName.equals("ERROR")) {
						IO.println("FORMATO CORRECTO: (correo)@(mail).(ext)");
						mName = RegexFilter.checkMail(IO.readStringNotBlank());
					}
					mails.add(new Mail(0, mName));
				}
				c.setMails(mails);
			}
		}
		return db.create(c);
	}
	
	// <<CONSULTAR>>
	/**
	 * Devuelve la informaci�n de un contacto a partir de su ID.
	 * 
	 * @return info - the contact's information
	 */
	private String consult() {
		IO.println("ID DEL CONTACTO ?");
		c.setId_c(IO.readInt());
		String info = db.read(c.getId_c(), db.selectContactType(c.getId_c()));
		return info == null ? "Ese contacto no existe" : info;
	}
	
	// <<MODIFICAR>>
	/**
	 * Obtiene los datos para crear un nuevo contacto y modificarlo en la base de datos.<br><br>
	 * 
	 * Si el campo se deja vac�o se pone el antiguo valor que tuviera.
	 * 
	 * @return true/false - if the contact was/wasn't modified successfully
	 */
	private boolean modify() {
		IO.println("ID DE CONTACTO ?");
		int id_c = IO.readInt();
		c.setId_c(id_c);
		String type = db.selectContactType(id_c);
		if (type.equals("error"))
			return false;
		else {
			c.setType(type);
			
			IO.println("NUEVO NOMBRE ?");
			String nombre = IO.readString();
			c.setName(nombre.isBlank() ? db.selectAtributo("nombre", id_c, 0) : nombre);
			IO.println("NUEVO DIRECCION ?");
			String direccion = IO.readString();
			c.setAddress(direccion.isBlank() ? db.selectAtributo("direccion", id_c, 0) : direccion);
			IO.println("NUEVAS NOTAS ?");
			String notas = IO.readString();
			c.setNotes(notas.isBlank() ? db.selectAtributo("notas", id_c, 0) : notas);
			
			if (type.equals(Contact.Type[0])) {
				IO.println("NUEVOS APELLIDOS ?");
				String apellidos = IO.readString();
				c.setSurnames(apellidos.isBlank() ? db.selectAtributo("apellidos", id_c, 1) : apellidos);
				IO.println("NUEVO G�NERO ?");
				String genero = IO.readString();
				c.setGender(genero.isBlank() ? db.selectAtributo("genero", id_c, 1) : genero);
				IO.println("NUEVA FECHA DE NACIMIENTO ?");
				String fechaNac = IO.readString();
				c.setBirthDate(fechaNac.isBlank() ? db.selectAtributo("fecha_nac", id_c, 1) : fechaNac);
			} else if (type.equals(Contact.Type[2])) {
				IO.println("NUEVA FECHA DE NACIMIENTO ?");
				String fechaNac = IO.readString();
				c.setBirthDate(fechaNac.isBlank() ? db.selectAtributo("fecha_nac", id_c, 1) : fechaNac);
			}
			IO.println("DESEA MODIFICAR ALG�N TEL�FONO ? (SI | NO)");
			while (modifyTelephones());
			IO.println("DESEA MODIFICAR ALG�N CORREO ? (SI | NO)");
			while (modifyMails());
			return db.update(c);
		}
	}
	
	/**
	 * Modifica los tel�fonos de un contacto (Si tuviera).
	 * 
	 * @return true/false - if the user wants/doesn't want to edit more
	 */
	private boolean modifyTelephones() {
		char selection = IO.readUpperChar();
		ArrayList<Telephone> telephones = new ArrayList<Telephone>();
		int id_c = c.getId_c();
		switch (selection) {
			case 'S':
				while (selection == 'S') {
					IO.println(db.read(id_c, db.selectContactType(id_c)));
					IO.println("ID DEL TEL�FONO ?");
					int id_t = IO.readInt();
					if (!db.checkIdT(id_c, id_t)) {
						IO.println("ID incorrecto");
						IO.println("DESEA MODIFICAR ALG�N TEL�FONO (SI | NO)");
						return true;
					}
				
					IO.println("NUEVO N�MERO ?");
					String tNumber = RegexFilter.checkTelephone(IO.readString());
					while (tNumber.toLowerCase().equals("error")) {
						IO.println("Introduzca 9 n�meros");
						tNumber = RegexFilter.checkTelephone(IO.readStringNotBlank());
					}
					IO.println("TIPO (M�vil | Fijo) ?");
					String tType = Character.toString(IO.readUpperChar());
					if (!tType.equals("M") || !tType.equals("F"))
						tType = "M";
					telephones.add(new Telephone(id_t, tNumber, tType));
					IO.println("DESEA MODIFICAR OTRO TEL�FONO (SI | NO) ?");
					if (IO.readUpperChar() != 'S') {
						c.setTelephones(telephones);
						return false;
					}
					IO.println("CUANDO ACABE SE REFLEJAR�N LOS CAMBIOS, >>>NO REPITA ID<<<");
				}
			case 'N':
				c.setTelephones(telephones);
				return false;
			default:
				IO.println("Seleccione una opci�n v�lida");
				return true;
		}
	}
	
	/**
	 * Modifica los correos de un contacto (Si tuviera).
	 * 
	 * @return true/false - if the user wants/doesn't want to edit more
	 */
	private boolean modifyMails() {
		char selection = IO.readUpperChar();
		ArrayList<Mail> mails = new ArrayList<Mail>();
		int id_c = c.getId_c();
		switch (selection) {
			case 'S':
				while (selection == 'S') {
					IO.println(db.read(id_c, db.selectContactType(id_c)));
					IO.println("ID DEL CORREO ?");
					int id_m = IO.readInt();
					if (!db.checkIdM(id_c, id_m)) {
						IO.println("ID incorrecto");
						IO.println("DESEA MODIFICAR ALG�N CORREO (SI | NO)");
						return true;
					}
					IO.println("NUEVO CORREO ?");
					String mName = RegexFilter.checkMail(IO.readString());
					while (mName.toLowerCase().equals("error")) {
						IO.println("FORMATO CORRECTO: (correo)@(mail).(ext)");
						mName = RegexFilter.checkMail(IO.readStringNotBlank());
					}
					mails.add(new Mail(id_m, mName));
					IO.println("DESEA MODIFICAR OTRO CORREO (SI | NO) ?");
					if (IO.readUpperChar() != 'S') {
						c.setMails(mails);
						return false;
					}
					IO.println("CUANDO ACABE SE REFLEJAR�N LOS CAMBIOS, >>>NO REPITA ID<<<");
				}
			case 'N':
				c.setMails(mails);
				return false;
			default:
				IO.println("Seleccione una opci�n v�lida");
				return true;
		}
	}
	
	// <<BAJA>>
	/**
	 * Obtiene los datos y elimina un contacto de la BBDD a partir de su ID.
	 * 
	 * @return true/false - if the contact was/wasn't deleted successfully
	 */
	private boolean unregister() {
		IO.println("ID DEL CONTACTO ?");
		c.setId_c(IO.readInt());
		c.setType(db.selectContactType(c.getId_c()));
		return db.delete(c);
	}
	
	// <<SELECCIONAR TIPO>>
	/**
	 * Selecciona el tipo de contacto que el usuario desea a�adir.
	 * 
	 * @return true/false - if the option was/wasn't correct
	 */
	private boolean selectType() {
		IO.println("TIPO DE CONTACTO ? (PERSONA | EMPRESA | MASCOTA)");
		
		switch(IO.readUpperChar()) {
			case 'P':
				c.setType(Contact.Type[0]);
				return true;
			case 'E':
				c.setType(Contact.Type[1]);
				return true;
			case 'M':
				c.setType(Contact.Type[2]);
				return true;
			default:
				IO.println("Car�cter no v�lido");
				return false;
		}
	}
	
	// <<<MEN�>>>
	/**
	 * Men� interactivo para que el usuario pueda manipular la BBDD.
	 * 
	 * @return true/false - if the option was valid or invalid/if the user exit the app
	 */
	private boolean menu() {
		IO.println("[ LISTAR | ALTA | BAJA | MODIFICAR | CONSULTAR | GUI | FORMATEAR | SALIR ]");
		
		switch (IO.readUpperChar()) {
			case 'L':
				IO.println(db.list());
				return true;
			case 'A':
				while (!selectType());
				if (register())
					IO.println("Nuevo contacto dado de alta");
				else
					IO.println("No se ha podido dar de alta el contacto");
				return true;
			case 'B':
				if (unregister())
					IO.println("Contacto dado de baja");
				else
					IO.println("No se ha podido dar de baja el contacto");
				return true;
			case 'M':
				if (modify())
					IO.println("Contacto modificado");
				else
					IO.println("No se ha podido modificar el contacto");
				return true;
			case 'C':
				IO.println(consult());
				return true;
			case 'G':
				db.close();
				IO.println("Accediendo a la GUI... bye.");
				new GUI().setVisible(true);
				return false;
			case 'F':
				IO.println("Formateando :( bye.");
				Agenda_Ejecutable.format();
				return false;
			case 'S':
				db.close();
				IO.println("bye.");
				return false;
			default:
				IO.println("Seleccione una opci�n v�lida");
				return true;
		}
	}
	
}
