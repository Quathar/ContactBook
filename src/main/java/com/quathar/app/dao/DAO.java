package com.quathar.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.quathar.app.model.Contact;
import com.quathar.app.model.Hobby;
import com.quathar.app.model.Mail;
import com.quathar.app.model.Telephone;

import com.quathar.app.database.DB;

import com.quathar.app.gui.frame.Add;
import com.quathar.app.gui.frame.Info;
import com.quathar.app.gui.model.HobbyModel;
import com.quathar.app.gui.model.MailModel;
import com.quathar.app.gui.model.TelephoneModel;

import com.quathar.app.io.RegexFilter;

/**
 * DAO.<br><br>
 * 
 * Utilidad:<br><br>
 * 
 * - Recoge los datos de los frames<br>
 * - Crea objetos de tipo contacto y los a�ade, modifica o elimina en la BBDD<br>
 * - Convierte los ResultSet en arrays<br><br>
 * 
 * DAO = Data Acces Object.
 *
 * @since 2022-05-04
 * @see DB
 * @see Add
 * @see Info
 * @author Q
 */
public class DAO { // CLASE FINALIZADA

	// CAMPOS
	/**
	 * Clase con conexi�n a la BBDD.
	 */
	private DB db;
	/**
	 * Contact Object.
	 */
	private Contact c;
	
	// CONSTRUCTOR
	/**
	 * Constructor.
	 */
	public DAO() {
		db = new DB();
		c = new Contact();
	}
	
	// <<--CRUD-->>
	// <<CREATE>>
	/**
	 * Recoge los datos de un contacto y lo inserta en la BBDD.
	 * 
	 * @param frame the AddContact frame
	 */
	public void register(Add frame) {
		c = new Contact();
		
		// CAMPOS COMUNES
		String type = (String) (frame.getContactType().getSelectedItem() + "s").toLowerCase();
		c.setType(type);
		String name = frame.getNameTextField().getText();
		c.setName(name.equals(Add.PlaceHoldersTitles[1]) ? "" : RegexFilter.checkSpaces(name));
		String address = frame.getAddressTextField().getText();
		c.setAddress(address.equals(Add.PlaceHoldersTitles[3]) ? "" : RegexFilter.checkSpaces(address));
		String notes = frame.getNotesTextField().getText();
		c.setNotes(notes.equals(Add.PlaceHoldersTitles[4]) ? "" : RegexFilter.checkSpaces(notes));
		
		// AFICIONES
		HobbyModel hModel = frame.getHobbyTable().getModel();
		ArrayList<Hobby> hobbies = new ArrayList<Hobby>();
		for (int i = 0; i < hModel.getRowCount(); i++) {
			int id_h = Integer.parseInt(hModel.getValueAt(i, 0).toString());
			String hobby = hModel.getValueAt(i, 1).toString();
			
			hobbies.add(new Hobby(id_h, hobby));
		}
		c.setHobbies(hobbies);
		
		// PERSONAS O MASCOTAS
		if (!type.equals(Contact.Type[1])) {
			if (type.equals(Contact.Type[0])) {
				String surnames = frame.getSurnamesTextField().getText();
				c.setSurnames(surnames.equals(Add.PlaceHoldersTitles[2]) ? "" : RegexFilter.checkSpaces(surnames));
				String birthDate = frame.getBirthDateText();
				c.setBirthDate(birthDate);
				String gender = (String) frame.getGenderComboBox().getSelectedItem();
				c.setGender(gender);
			} else if (type.equals(Contact.Type[2])) {
				String birthDate = frame.getBirthDateText();
				c.setBirthDate(birthDate);
			}
		}
		
		// PERSONAS O EMPRESAS
		if (!type.equals(Contact.Type[2])) {
			// TEL�FONOS
			ArrayList<Telephone> telephones = new ArrayList<Telephone>();
			TelephoneModel tModel = frame.getTelephoneTable().getModel();
			for (int i = 0; i < tModel.getRowCount(); i++) {
				int id_t = Integer.parseInt(tModel.getValueAt(i, 0).toString());
				String tNumber = tModel.getValueAt(i, 1).toString();
				String tType = tModel.getValueAt(i, 2).toString().substring(0, 1);
				
				telephones.add(new Telephone(id_t, tNumber, tType));
			}
			c.setTelephones(telephones);
			
			// CORREOS
			ArrayList<Mail> mails = new ArrayList<Mail>();
			MailModel mModel = frame.getMailTable().getModel();
			for (int i = 0; i < mModel.getRowCount(); i++) {
				int id_m = Integer.parseInt(mModel.getValueAt(i, 0).toString());
				String mName = mModel.getValueAt(i, 1).toString();
				
				mails.add(new Mail(id_m, mName));
			}
			c.setMails(mails);
		}
		db.create(c);
	}

	/**
	 * Inserta una nueva afici�n a la BBDD.
	 * 
	 * @param hobby the hobby to insert
	 */
	public void registerHobby(String hobby) {
		try {
			db.insertNewAficion(hobby);
		} catch (SQLException sqlE) {
			System.err.println("ERROR: altaAficion");
		}
	}
	
	// <<READ>>
	/**
	 * Devuelve el tipo de contacto a partir de un ID.
	 * 
	 * @param id_c the contact ID
	 * @return the contact type
	 */
	public String getType(int id_c) {
		return db.selectContactType(id_c);
	}
	
	/**
	 * Llena el array bidimensional con el contenido del ResultSet.
	 * 
	 * @param data the array
	 * @param rs the ResultSet object
	 * @return the information in an bidimensional array
	 * @throws SQLException
	 */
	private Object[][] fill(Object[][] data, ResultSet rs, int columnCount) throws SQLException {
		int rowIndex = 0;
		while (rs.next()) {
			for (int columnIndex = 0; columnIndex < columnCount; columnIndex++)
				data[rowIndex][columnIndex] = rs.getObject(columnIndex + 1);
			rowIndex++;
		}
		rs.close();
		return data;
	}
	
	/**
	 * Devuelve un array bidimensional con toda la informaci�n de una tabla.
	 * 
	 * @param table the table whose information is required
	 * @param columnCount the table's number of columns
	 * @return the information in an bidimensional array
	 */
	public Object[][] getData(String table, int columnCount) {
		try {
			Object[][] data = new Object[db.countRows(table)][columnCount];
			ResultSet rs = db.selectAllFrom(table);
			return fill(data, rs, columnCount);
		} catch (SQLException sqlE) { return null; }
	}
	
	/**
	 * Devuelve un array bidimensional con toda la informaci�n de una tabla a partir de un ID.
	 * 
	 * @param table the table whose information is required
	 * @param columnCount the table's number of columns
	 * @param id_c the contact ID
	 * @return the information in an bidimensional array
	 */
	public Object[][] getDataWhereIdC(String table, int columnCount, int id_c) {
		try {
			Object[][] data = new Object[db.countRowsWhereIdC(table, id_c)][columnCount];
			ResultSet rs = db.selectAllFromWhereIdC(table, id_c);
			return fill(data, rs, columnCount);
		} catch (SQLException sqlE) { return null; }
	}
	
	/**
	 * Devuelve un array bidimensional con toda la informaci�n de una tabla a partir de un nombre o un afici�n.
	 * 
	 * @param table the table which information is required
	 * @param columnCount the table's number of columns
	 * @param word the word to search
	 * @return the information in an bidimensional array
	 */
	public Object[][] getDataWhereNombreOrAficionLike(String table, String word, int columnCount) {
		try {
			Object[][] data = new Object[db.countRowsWhereNombreOrAficionLike(table, word)][columnCount];
			ResultSet rs = db.selectAllFromWhereNombreOrAficionLike(table, word);
			return fill(data, rs, columnCount);
		} catch (SQLException sqlE) { return null; }
	}
	
	/**
	 * Devuelve un array bidimensional con toda la informaci�n de la tabla <b>'telefonos'</b>.
	 * 
	 * @param columnCount the table's number of columns
	 * @return the information in an bidimensional array
	 */
	public Object[][] getTelephoneContactData(int columnCount) {
		try {
			Object[][] data = new Object[db.countRows(DB.TelephonesTitle)][columnCount];
			ResultSet rs = db.selectAllTelephonesOrMailsFrom(DB.TelephonesTitle);
			return fill(data, rs, columnCount);
		} catch (SQLException sqlE) { return null; }
	}
	
	/**
	 * Devuelve un array bidimensional con toda la informaci�n de la tabla <b>'correos'</b>.
	 * 
	 * @param columnCount the table's number of columns
	 * @return the information in an bidimensional array
	 */
	public Object[][] getMailContactData(int columnCount) {
		try {
			Object[][] data = new Object[db.countRows(DB.MailsTitle)][columnCount];
			ResultSet rs = db.selectAllTelephonesOrMailsFrom(DB.MailsTitle);
			return fill(data, rs, columnCount);
		} catch (SQLException sqlE) { return null; }
	}
	
	/**
	 * Llena el array con el contenido del ResultSet.
	 * 
	 * @param rs the ResultSet object
	 * @param data the array
	 * @param fieldNumber the field to save in the array
	 * @return the information in an array
	 * @throws SQLException
	 */
	private String[] fill(ResultSet rs, String[] data, int fieldNumber) throws SQLException {
		int i = 0;
		while (rs.next())
			data[i++] = rs.getObject(fieldNumber).toString();
		rs.close();
		return data;
	}
	
	/**
	 * Devuelve todos los nombres de las aficiones.
	 * 
	 * @return the information in an array
	 */
	public String[] getHobbies() {
		try {
			String[] aficiones = new String[db.countRows("aficiones")];
			ResultSet rs = db.selectAllFrom("aficiones");
			return fill(rs, aficiones, 2);
		} catch (SQLException sqlE) { return null; }
	}
	
	/**
	 * Devuelve todos los nombres de las aficiones que no tenga el ID de contacto.
	 * 
	 * @param id_c the contact ID
	 * @return the information in an array
	 */
	public String[] getHobbiesWhereIdCNot(int id_c) {
		try {
			String[] aficiones = new String[db.countRows("aficiones") - db.countRowsWhereIdC("contactos_aficiones", id_c)];
			ResultSet rs = db.selectAficionesWhereIdCNot(id_c);
			return fill(rs, aficiones, 1);
		} catch (SQLException sqlE) {
			return null;
		}
	}
	
	/**
	 * Comprueba que el ID del hobby pasado no est� en la tabla <b>'contactos_aficiones'</b>
	 * 
	 * @param id_h the hobby ID
	 * @return true/false - if the hobby is/isn't in the table
	 */
	public boolean checkIdH(int id_h) {
		try {
			ResultSet rs = db.isHobbyUsed(id_h);
			// 0 Significa que no est�, 1 que s�
			int logic = 0;
			while (rs.next())
				logic = rs.getInt(1);
			if (logic == 1)
				return true;
			else
				return false;
		} catch (SQLException sqlE) { return false; }
	}
	
	/**
	 * Devuelve el valor de un atributo a partir de un ID
	 * 
	 * @param field the field to search
	 * @param id the contact ID
	 * @return the field content
	 */
	public String getFieldWhereIdC(String field, int id) {
		int mode = 0;
		if (field.equals("apellidos") || field.equals("fecha_nac") || field.equals("genero"))
			mode = 1;
		return db.selectAtributo(field, id, mode);
	}
	
	// <<UPDATE>>
	/**
	 * Recoge los datos de un contacto y lo modifica en la BBDD.
	 * 
	 * @param frame the ContactInfo frame
	 * @param id_c the contact ID
	 */
	public void modify(Info frame, int id_c) {
		c = new Contact(id_c);
		
		// CAMPOS COMUNES
		String type = db.selectContactType(id_c);
		c.setType(type);
		String name = frame.getTxtNombre().getText();
		c.setName(name);
		String address = frame.getTxtDireccion().getText();
		c.setAddress(address);
		String notes = frame.getTxtNotas().getText();
		c.setNotes(notes);
		
		HobbyModel hModel = frame.getHobbyTable().getModel();
		ArrayList<Hobby> hobbies = new ArrayList<Hobby>();
		for (int i = 0; i < hModel.getRowCount(); i++) {
			int id_h = Integer.parseInt(hModel.getValueAt(i, 0).toString());
			String hobby = hModel.getValueAt(i, 1).toString();
			
			hobbies.add(new Hobby(id_h, hobby));
		}
		c.setHobbies(hobbies);
		
		// PERSONAS O MASCOTAS
		if (!type.equals(Contact.Type[1])) {
			if (type.equals(Contact.Type[0])) {
				String surnames = frame.getTxtApellidos().getText();
				c.setSurnames(surnames);
				String birthDate = frame.getTxtFechaNac().getText();
				c.setBirthDate(birthDate);
				String gender = (String) frame.getCbGenero().getSelectedItem();
				c.setGender(gender);
			} else if (type.equals(Contact.Type[2])) {
				String birthDate = frame.getTxtFechaNac().getText();
				c.setBirthDate(birthDate);
			}
		}
		
		// PERSONAS O EMPRESAS
		if (!type.equals(Contact.Type[2])) {
			// TEL�FONOS
			ArrayList<Telephone> telephones = new ArrayList<Telephone>();
			
			TelephoneModel tModel = frame.getTelephoneTable().getModel();
			for (int i = 0; i < tModel.getRowCount(); i++) {
				int id_t = Integer.parseInt(tModel.getValueAt(i, 0).toString());
				String tNumber = tModel.getValueAt(i, 1).toString();
				String tType = tModel.getValueAt(i, 2).toString().substring(0, 1);
				
				telephones.add(new Telephone(id_t, tNumber, tType));
			}
			c.setTelephones(telephones);
			
			// CORREOS
			ArrayList<Mail> mails = new ArrayList<Mail>();
			
			MailModel mModel = frame.getMailTable().getModel();
			for (int i = 0; i < mModel.getRowCount(); i++) {
				int id_m = Integer.parseInt(mModel.getValueAt(i, 0).toString());
				String mName = mModel.getValueAt(i, 1).toString();
				
				mails.add(new Mail(id_m, mName));
			}
			c.setMails(mails);
		}
		db.update(c);
	}
	
	/**
	 * Modifica una afici�n en la BBDD.
	 * 
	 * @param id_h the hobby ID
	 * @param newName the new name
	 */
	public void modifyHobby(int id_h, String newName) {
		db.updateAficion(id_h, newName);
	}
	
	/**
	 * Modifica un contacto en la BBDD desde un modelo.
	 * 
	 * @param id_c the contact ID
	 * @param name the new name
	 * @param address the new address
	 * @param notes the new notes
	 */
	public void modifyModel(int id_c, String name, String address, String notes) {
		c.setId_c(id_c);
		c.setName(name);
		c.setAddress(address);
		c.setNotes(notes);
		c.setType(db.selectContactType(id_c));
		
		db.update(c);
	}
	
	// <<DELETE>>
	/**
	 * Elimina un contacto en la BBDD a partir de un ID
	 * 
	 * @param id_c the contact ID
	 */
	public void unregister(int id_c) {
		c.setId_c(id_c);
		c.setType(db.selectContactType(id_c));
		
		db.delete(c);
	}
	
	/**
	 * Elimina una fila de los modelos hobby, telephone or mail.
	 * 
	 * @param table the table to delete from
	 * @param id the hobby/telephone/mail ID
	 */
	public void unregisterRow(String table, int id) {
		try {
			if (table.equals(DB.HobbiesTitle))
				db.deleteFromHobbiesWhereIdH(id);
			else if (table.equals(DB.TelephonesTitle))
				db.deleteFromTelephonesWhereIdT(id);
			else
				db.deleteFromMailsWhereIdM(id);
		} catch (SQLException sqlE) {
			System.err.println("ERROR: bajaRegistro(table, id)");
		}	
	}
	
	/**
	 * Elimina un hobby a partir de un ID de contacto.
	 * 
	 * @param id_c the contact ID
	 * @param id_h the hobby ID
	 */
	public void unregisterHobbyWhereIdC(int id_c, int id_h) {
		try {
			db.deleteFromContactHobbyWhereIdCAndIdH(id_c, id_h);
		} catch (SQLException sqlE) {
			System.err.println("ERROR: bajaAficionWhereId");
		}
	}
	
	// CLOSE
	/**
	 * Cierra la conexi�n a la BBDD.
	 */
	public void close() {
		db.close();
	}
	
}
