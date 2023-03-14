package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.Contact;

/**
 * Database.<br><br>
 * 
 * Clase con conexión a la base de datos <b>'agenda.sqlite'</b> (gestor de base de datos <b>SQLite</b>).<br><br>
 * 
 * Las tablas creadas son las siguientes:<br><br>
 * 
 * - Contactos<br>
 * - Personas<br>
 * - Empresas<br>
 * - Mascotas<br>
 * - Aficiones<br>
 * - Contactos_Aficiones<br>
 * - Telefonos<br>
 * - Correos<br><br>
 * 
 * Base de datos = BBDD
 * 
 * @since 2022-04-03
 * @author Q
 */
public class Database { // CLASE FINALIZADA

	// CONSTANTES
	/**
	 * Ruta para conectar con la BBDD.
	 */
	private static final String DSN = "jdbc:sqlite:agenda.sqlite";
	// --NOMBRE DE LAS TABLAS
	/**
	 * Nombre de la tabla contactos.
	 */
	public static final String ContactsTitle = "contactos";
	/**
	 * Nombre de la tabla aficiones.
	 */
	public static final String HobbiesTitle = "aficiones";
	/**
	 * Nombre de la tabla contactos_aficiones.
	 */
	public static final String ContactsHobbiesTitle = "contactos_aficiones";
	/**
	 * Nombre de la tabla personas.
	 */
	public static final String PersonsTitle = "personas";
	/**
	 * Nombre de la tabla empresas.
	 */
	public static final String CompaniesTitle = "empresas";
	/**
	 * Nombre de la tabla mascotas.
	 */
	public static final String PetsTitle = "mascotas";
	/**
	 * Nombre de la tabla teléfono.
	 */
	public static final String TelephonesTitle = "telefonos";
	/**
	 * Nombre de la tabla correos.
	 */
	public static final String MailsTitle = "correos";
	
	// CAMPOS
	/**
	 * Conexión a la BBDD.
	 */
	private Connection con;
	/**
	 * Sentencias de la BBDD.
	 */
	private Statement stmt;
	/**
	 * Sentencias preparadas de la BBDD.
	 */
	private PreparedStatement pstmt;
	/**
	 * Resultados de la BBDD.
	 */
	private ResultSet rs;
	
	// CONSTRUCTORES
	/**
	 * Constructor.
	 */
	public Database() {
		try {
			createConnection();
			createTablesIfNotExists();
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
		}
	}

	/**
	 * Contructor.<br><br>
	 * 
	 * - Modo 0: Borra todas las tablas de la BBDD.
	 * - Modo 1: Crea todas las tablas e inserta unos registros a la BBDD.
	 * 
	 * @param mode init mode
	 */
	public Database(int mode) {
		try {
			createConnection();
			if (mode == 0)
				dropTablesIfExists();
			else if (mode == 1) {
				createTablesIfNotExists();
				init();
			}
			close();
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
		}
	}
	
	// MÉTODOS
	// <<ESTRUCTURA INICIAL>>
	/** 
	 * Establece la conexión a la BBDD.
	 * 
	 * @throws SQLException
	 */
	private void createConnection() throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(DSN);
			stmt = con.createStatement();
		} catch (ClassNotFoundException cnfE) {
			System.err.println("DRIVER NO ENCONTRADO");
		}
	}
	
	/**
	 * Crea las tablas en la BBDD si NO existen.
	 * 
	 * @throws SQLException
	 */
	private void createTablesIfNotExists() throws SQLException {
		String sql;
		
		// Tabla CONTACTOS
		sql = "CREATE TABLE IF NOT EXISTS contactos ("
							+ "id_c INTEGER PRIMARY KEY AUTOINCREMENT, "
							+ "nombre TEXT, "
							+ "direccion TEXT, "
							+ "notas TEXT "
				+ ")";
		stmt.executeUpdate(sql);
		
		// Tabla PERSONAS
		sql = "CREATE TABLE IF NOT EXISTS personas ("
					+ "id_c INTEGER PRIMARY KEY REFERENCES contactos (id_c), "
					+ "apellidos TEXT, "
					+ "genero TEXT "
						+ "CHECK (genero IN ('M', 'F', 'O')	), "
					+ "fecha_nac TEXT"
				+ ")";
		stmt.executeUpdate(sql);
		

		// Tabla EMPRESAS
		sql = "CREATE TABLE IF NOT EXISTS empresas ("
					+ "id_c INTEGER PRIMARY KEY REFERENCES contactos (id_c)"
				+ ")";
		stmt.executeUpdate(sql);
		
		// Tabla MASCOTAS
		sql = "CREATE TABLE IF NOT EXISTS mascotas ("
					+ "id_c INTEGER PRIMARY KEY REFERENCES contactos (id_c), "
					+ "fecha_nac TEXT"
				+ ")";
		stmt.executeUpdate(sql);
		
		// Tabla AFICIONES
		sql = "CREATE TABLE IF NOT EXISTS aficiones ("
					+ "id_a INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "aficion TEXT"
				+ ")";
		stmt.executeUpdate(sql);
		
		// Tabla CONTACTOS_AFICIONES
		sql = "CREATE TABLE IF NOT EXISTS contactos_aficiones ("
						+ "id_c INTEGER REFERENCES contactos (id_c), "
						+ "id_a INTEGER REFERENCES aficiones (id_a), "
							+ "PRIMARY KEY (id_c, id_a)"
				+ ")";
		stmt.executeUpdate(sql);
		
		// Tabla TELEFONOS
		sql = "CREATE TABLE IF NOT EXISTS telefonos ("
						+ "id_t INTEGER PRIMARY KEY AUTOINCREMENT, "
						+ "id_c INTEGER REFERENCES contactos (id_c), "
						+ "telefono TEXT, "
						+ "tipo TEXT"
				+ ")";
		stmt.executeUpdate(sql);
		
		// Tabla CORREOS
		sql = "CREATE TABLE IF NOT EXISTS correos ("
						+ "id_e INTEGER PRIMARY KEY AUTOINCREMENT, "
						+ "id_c INTEGER REFERENCES contactos (id_c), "
						+ "correo TEXT"
				+ ")";
		stmt.executeUpdate(sql);
	}
	
	/**
	 * Borra las tablas si existen en la BBDD.
	 * 
	 * @throws SQLException
	 */
	public void dropTablesIfExists() throws SQLException {
		String sql;
		
		sql = "DROP TABLE IF EXISTS contactos";
		stmt.executeUpdate(sql);
		sql = "DROP TABLE IF EXISTS personas";
		stmt.executeUpdate(sql);
		sql = "DROP TABLE IF EXISTS empresas";
		stmt.executeUpdate(sql);
		sql = "DROP TABLE IF EXISTS mascotas";
		stmt.executeUpdate(sql);
		sql = "DROP TABLE IF EXISTS aficiones";
		stmt.executeUpdate(sql);
		sql = "DROP TABLE IF EXISTS contactos_aficiones";
		stmt.executeUpdate(sql);
		sql = "DROP TABLE IF EXISTS telefonos";
		stmt.executeUpdate(sql);
		sql = "DROP TABLE IF EXISTS correos";
		stmt.executeUpdate(sql);
	}
	
	// <<--CRUD-->>
	// <<CREATE>>
	/**
	 * Introduce un contacto en la BBDD.
	 * 
	 * @param c the contact object
	 * @return true/false - if the contact was added successfully/if an SQLException occurs
	 */
	public boolean create(Contact c) {
		String sql = "INSERT INTO contactos (nombre, direccion, notas) "
						+ "VALUES (?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c.getName());
			pstmt.setString(2, c.getAddress());
			pstmt.setString(3, c.getNotes());
			pstmt.executeUpdate();
			
			c.setId_c(selectMaxId_c());
			insertAficiones(c);
			
			if (c.getType().equals(Contact.Type[0])) {			// PERSONAS
				insertIntoPersonas(c);
				insertTelefonos(c);
				insertCorreos(c);
			} else if (c.getType().equals(Contact.Type[1])) {	// EMPRESAS
				insertIntoEmpresas(c);
				insertTelefonos(c);
				insertCorreos(c);
			} else if (c.getType().equals(Contact.Type[2]))		// MASCOTAS
				insertIntoMascotas(c);
			
			return true;
		} catch (SQLException sqlE) {
			return false;
		}
	}
	
	/**
	 * Devuelve el último ID introducido en la BBDD.<br><br>
	 * 
	 * Utiliza la función MAX(id_c) de SQL, como este campo auto incremental en la tabla contactos, nunca se podrá repetir.
	 * 
	 * @return id the contact ID
	 * @throws SQLException
	 */
	private int selectMaxId_c() throws SQLException {
		String sql = "SELECT MAX(id_c) FROM contactos";
		rs = stmt.executeQuery(sql);

		rs.next();
		return rs.getInt(1);
	}
	
	/**
	 * Añade una o más aficiones a un contacto en la tabla <b>'contactos_aficiones'</b>.
	 * 
	 * @param c the contact object
	 */
	private void insertAficiones(Contact c) throws SQLException {
		String sql = "INSERT INTO contactos_aficiones (id_a, id_c) VALUES (?, ?)";
		pstmt = con.prepareStatement(sql);
		
		for (int i = 0; i < c.getHobbies().size(); i++) {
			int id_h = getId_h(c.getHobbies().get(i).getName());
			pstmt.setInt(1, id_h);
			pstmt.setInt(2, c.getId_c());
			pstmt.executeUpdate();
		}
		pstmt.close();
	}
	
	/**
	 * Inserta un contacto en la tabla <b>'personas'</b>.
	 * 
	 * @param c the contact object
	 * @throws SQLException
	 */
	private void insertIntoPersonas(Contact c) throws SQLException {
		String sql = "INSERT INTO personas VALUES (?, ?, ?, ?)";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, c.getId_c());
		pstmt.setString(2, c.getSurnames());
		pstmt.setString(3, c.getGender());
		pstmt.setString(4, c.getBirthDate());
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	/**
	 * Inserta un contacto en la tabla <b>'empresas'</b>.
	 * 
	 * @param c the contact object
	 * @throws SQLException
	 */
	private void insertIntoEmpresas(Contact c) throws SQLException {
		String sql = "INSERT INTO empresas VALUES (?)";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, c.getId_c());
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	/**
	 * Inserta un contacto en la tabla <b>'mascotas'</b>.
	 * 
	 * @param c the contact object
	 * @throws SQLException
	 */
	private void insertIntoMascotas(Contact c) throws SQLException {
		String sql = "INSERT INTO mascotas VALUES (?, ?)";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, c.getId_c());
		pstmt.setString(2, c.getBirthDate());
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	/**
	 * Inserta uno o más telefonos a un contacto en la tabla <b>'telefonos'</b>.
	 * 
	 * @param c the contact object
	 * @throws SQLException
	 */
	private void insertTelefonos(Contact c) throws SQLException {
		String sql = "INSERT INTO telefonos (id_c, telefono, tipo) VALUES (?, ?, ?)";
		pstmt = con.prepareStatement(sql);
		
		for (int i = 0; i < c.getTelephones().size(); i++) {
			pstmt.setInt(1, c.getId_c());
			pstmt.setString(2, c.getTelephones().get(i).getNumber());
			pstmt.setString(3, c.getTelephones().get(i).getType());
			pstmt.executeUpdate();
		}
		pstmt.close();
	}
	
	/**
	 * Inserta uno o más correos en la tabla <b>'correos'</b>.
	 * 
	 * @param c the contact object
	 * @throws SQLException
	 */
	private void insertCorreos(Contact c) throws SQLException {
		String sql = "INSERT INTO correos (id_c, correo) VALUES (?, ?)";
		pstmt = con.prepareStatement(sql);
		
		for (int i = 0; i < c.getMails().size(); i++) {
			pstmt.setInt(1, c.getId_c());
			pstmt.setString(2, c.getMails().get(i).getName());
			pstmt.executeUpdate();
		}
		pstmt.close();
	}
	
	/**
	 * Inserta una nueva afición en la tabla <b>'aficiones'</b>.
	 * 
	 * @param newHobby the new hobby to insert.
	 * @return true/false - if the new hobby was added successfully/if an SQLException occurs
	 */
	public boolean insertNewAficion(String newHobby) throws SQLException {
		String sql = String.format("INSERT INTO aficiones (aficion) VALUES ('%s')", newHobby);
		stmt.executeUpdate(sql);
		return true;
	}
	
	// <<READ>>
	/**
	 * Devuelve el tipo del contacto a partir de su ID.
	 * 
	 * @param id the contact ID
	 * @return type/null - the contact type String/if the contact has no type or if an SQLException occurs
	 */
	public String selectContactType(int id) {
		String sql;
		try {
			for (int i = 0; i < Contact.Type.length; i++) {
				sql = String.format("SELECT id_c FROM contactos JOIN %s USING (id_c) WHERE id_c = %d", Contact.Type[i], id);
				rs = stmt.executeQuery(sql);
				if (rs.next())
					return Contact.Type[i];
			}
			rs.close();
			return null;
		} catch (SQLException sqlE) {
			return null;
		}
	}
	
	/**
	 * Devuelve toda la información de las tablas:<br><br>
	 * 
	 * - contactos<br>
	 * - aficiones<br>
	 * - contactos_aficiones<br>
	 * - personas<br>
	 * - empresas<br>
	 * - mascotas
	 * 
	 * @param table the table to get the information
	 * @return the query's ResultSet
	 * @throws SQLException
	 */
	public ResultSet selectAllFrom(String table) throws SQLException {
		final String[] Query = {
				"SELECT * FROM contactos ORDER BY nombre",
				"SELECT * FROM aficiones",
				"SELECT id_a, aficion, nombre, id_c FROM contactos_aficiones "
					+ "JOIN contactos USING (id_c) "
					+ "JOIN aficiones USING (id_a) "
					+ "ORDER BY id_a",
				"SELECT * FROM contactos JOIN " + table + " USING (id_c) ORDER BY nombre"
		};
		table = table.toLowerCase();
		
		if (table.equals(Database.ContactsTitle))
			return stmt.executeQuery(Query[0]);
		else if (table.equals(Database.HobbiesTitle))
			return stmt.executeQuery(Query[1]);
		else if (table.equals(Database.ContactsHobbiesTitle))
			return stmt.executeQuery(Query[2]);
		else 
			return stmt.executeQuery(Query[3]);
	}
	
	/**
	 * Devuelve toda la información a partir de un id de las tablas:<br><br>
	 * 
	 * - telefonos<br>
	 * - correos<br>
	 * - aficiones
	 * 
	 * @param table the table to get the information
	 * @param id_c the contact ID
	 * @return the query's ResultSet
	 * @throws SQLException
	 */
	public ResultSet selectAllFromWhereIdC(String table, int id_c) throws SQLException {
		final String[] Query = {
				"SELECT id_t, telefono, tipo FROM " + table + " WHERE id_c = " + id_c,
				"SELECT id_e, correo FROM " + table + " WHERE id_c = " + id_c,
				"SELECT id_a, aficion FROM aficiones "
					+ "WHERE id_a IN "
					+ "(SELECT id_a FROM contactos_aficiones WHERE id_c = " + id_c + ")"
		};
		table = table.toLowerCase();
		
		if (table.equals("telefonos"))
			return stmt.executeQuery(Query[0]);
		else if (table.equals("correos"))
			return stmt.executeQuery(Query[1]);
		else 
			return stmt.executeQuery(Query[2]);
	}
	
	/**
	 * Devuelve toda la información a partir de un nombre o una afición de las tablas:<br><br>
	 * 
	 * - contactos<br>
	 * - aficiones<br>
	 * - contactos_aficiones<br>
	 * - personas<br>
	 * - empresas<br>
	 * - mascotas
	 * 
	 * @param table the table to get the information
	 * @param word the word to search
	 * @return the query's ResultSet
	 * @throws SQLException
	 */
	public ResultSet selectAllFromWhereNombreOrAficionLike(String table, String word) throws SQLException {
		final String[] Query = {
				String.format("SELECT * FROM contactos WHERE lower(nombre) like '%s%%' ORDER BY nombre", word),
				String.format("SELECT * FROM aficiones WHERE lower(aficion) like '%s%%'", word),
				String.format("SELECT id_a, aficion, nombre, id_c FROM contactos_aficiones "
								+ "JOIN aficiones USING (id_a) JOIN contactos USING (id_c) "
								+ "WHERE lower(aficion) like '%s%%' ORDER BY id_a", word),
				String.format("SELECT id_c, nombre, direccion, notas FROM contactos JOIN %s USING (id_c) "
								+ "WHERE lower(nombre) like '%s%%' ORDER BY nombre", table, word)
		};
		table = table.toLowerCase();
		
		if (table.equals("contactos"))
			return stmt.executeQuery(Query[0]);
		else if (table.equals("aficiones"))
			return stmt.executeQuery(Query[1]);
		else if (table.equals("contactos_aficiones"))
			return stmt.executeQuery(Query[2]);
		else
			return stmt.executeQuery(Query[3]);
	}
	
	/**
	 * Devuelve las aficiones que no estén asignadas a un id.
	 * 
	 * @param id the contact ID
	 * @return the query's ResultSet
	 * @throws SQLException
	 */
	public ResultSet selectAficionesWhereIdCNot(int id) throws SQLException {
		String sql = "SELECT aficion FROM aficiones "
						+ "WHERE id_a NOT IN "
						+ "(SELECT id_a FROM contactos_aficiones WHERE id_c = " + id + ")";
		return stmt.executeQuery(sql);
	}
	
	/**
	 * Comprueba que una afición no esté en la tabla contactos_aficiones (que esté usada).
	 * 
	 * @param id the hobby ID
	 * @return 0/1 - 0 if the hobby is/isn't on the table
	 * @throws SQLException
	 */
	public ResultSet isHobbyUsed(int id) throws SQLException {
		String sql = "SELECT (COUNT(*) > 0) FROM contactos_aficiones WHERE id_a = " + id;
		return stmt.executeQuery(sql);
 	}
	
	/**
	 * Devuelve toda la información de la tabla telefonos o correos.
	 * 
	 * @param table the table to search
	 * @return the result set with the info
	 * @throws SQLException
	 */
	public ResultSet selectAllTelephonesOrMailsFrom(String table) throws SQLException {
		final String[] Query = {
				"SELECT nombre, telefono, tipo FROM contactos JOIN telefonos USING (id_c) ORDER BY nombre",
				"SELECT nombre, correo FROM contactos JOIN correos USING (id_c) ORDER BY nombre"
		};
		if (table.toLowerCase().equals(TelephonesTitle))
			return stmt.executeQuery(Query[0]);
		else
			return stmt.executeQuery(Query[1]);
	}
	
	/**
	 * Devuelve la información de un contacto extraída de la BBDD en un String.
	 * 
	 * @param id_c the contact ID
	 * @param type the contact type
	 * @return str/null - if the contact was read successfully/if an SQLException occurs
	 */
	public String read(int id_c, String type) {
		String sql = String.format("SELECT * FROM contactos JOIN %s USING (id_c) WHERE id_c = %d", type, id_c);
		
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				sql = String.format("%02d : [ %s | %s | %s ",
						rs.getInt("id_c"),
						rs.getString("nombre"),
						rs.getString("direccion"),
						rs.getString("notas"));
				
				if (type.equals(Contact.Type[0])) {
					sql += String.format("| %s | %s | %s ]%n",
							rs.getString("apellidos"),
							rs.getString("genero"),
							rs.getString("fecha_nac"));
					sql = readTelephonesAndMails(id_c, sql);
				} else if (type.equals(Contact.Type[1])) {
					sql += "]\n";
					sql = readTelephonesAndMails(id_c, sql);
				} else if (type.equals(Contact.Type[2])) {
					sql += String.format("| %s ]",
							rs.getString("fecha_nac"));
					rs.close();
				}
			}
			return sql;
		} catch (SQLException sqlE) { return null; }
	}
	
	/**
	 * Devuelve los teléfonos y los correos de un contacto en un String.
	 * 
	 * @param id_c the contact ID
	 * @param str the original string 
	 * 
	 * @return str - the telephones and mails of the contact
	 * @throws SQLException
	 */
	private String readTelephonesAndMails(int id_c, String str) throws SQLException {
		String sql = "SELECT id_t, telefono, tipo FROM telefonos WHERE id_c = " + id_c;
		rs = stmt.executeQuery(sql);

		str += "TELÉFONOS\n";
		while (rs.next()) {
			str += String.format("%d : [ %s | %s ]%n",
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3));
		}

		sql = "SELECT id_e, correo FROM correos WHERE id_c = " + id_c;
		rs = stmt.executeQuery(sql);

		str += "CORREOS\n";
		while (rs.next()) {
			str += String.format("%d : [ %s ]%n",
					rs.getInt(1),
					rs.getString(2));
		}
		rs.close();
		return str;
	}
	
	/**
	 * Comprueba que el ID pasado por parámetro esté entre la información del ResultSet
	 * 
	 * @param sql the query
	 * @param id the telephone/mail ID
	 * @return true/false - if the ID is/isn't in the result set
	 */
	private boolean check(String sql, int id) {
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next())
				if (rs.getInt(1) == id)
					return true;
			rs.close();
			return false;
		} catch (SQLException sqlE) { return false; }
		
	}
	
	/**
	 * Comprueba que el ID del teléfono pasado por parámetro esté entre los telefonos de un contacto.
	 * 
	 * @param id_c the contact ID
	 * @param id_t the telephone ID
	 * @return true/false - if the ID is/isn't in the result set
	 */
	public boolean checkIdT(int id_c, int id_t) {
		String sql = "SELECT id_t FROM telefonos WHERE id_c = " + id_c;
		return check(sql, id_t);
	}
	
	/**
	 * Comprueba que el ID del correo pasado por parámetro esté entre los correos de un contacto.
	 * 
	 * @param id_c the contact ID
	 * @param id_m the mail ID
	 * @return true/false - if the ID is/isn't in the result set
	 */
	public boolean checkIdM(int id_c, int id_m) {
		String sql = "SELECT id_e FROM correos WHERE id_c = " + id_c;
		return check(sql, id_m);
	}
	
	/**
	 * Devuelve un dato a partir de un campo de la tabla y un ID.
	 * 
	 * @param atributo Campo que se desea buscar en la BBDD
	 * @param id ID del contacto
	 * @param searchMode Modo de búsqueda (para buscar en la tabla "contactos" Ó en "personas", "empresas" o "mascotas")
	 * 
	 * @return atributo - Atributo requerido
	 * @return null - Si ocurre algún error del tipo SQLException
	 */
	public String selectAtributo(String atributo, int id, int searchMode) {
		final String[] Query = {
						String.format("SELECT %s FROM contactos WHERE id_c = %d", atributo, id),
						String.format("SELECT %s FROM %s WHERE id_c = %d", atributo, selectContactType(id), id)
							};
		String sql = Query[searchMode];
		
		try {
			rs = stmt.executeQuery(sql);
			
			while (rs.next())
				atributo = rs.getString(1);
			
			rs.close();
			return atributo;
		} catch (SQLException sqlE) {
			return "error" + atributo;
		}
	}
	
	/**
	 * Devuelve el nombre de una afición a partir del id
	 * 
	 * @param id ID de la afición
	 * @return aficion - el nombre de la afición
	 */
	public String getAficionWhereId(int id) throws SQLException {
		String sql = "SELECT aficion FROM aficiones WHERE id_a = " + id;
		rs = stmt.executeQuery(sql);
		
		rs.next();
		sql = rs.getString(1);
		
		rs.close();
		return sql;
	}
	
	/**
	 * Devuelve el id a partir de una afición.
	 * 
	 * @param hobby aficion de la que se desea conocer el ID
	 * @return id - ID de la aficion
	 */
	public int getId_h(String hobby) throws SQLException {
		String sql = String.format("SELECT id_a FROM aficiones WHERE aficion LIKE '%s'", hobby);
		rs = stmt.executeQuery(sql);
		
		rs.next();
		int id_h = rs.getInt(1);
		rs.close();
		return id_h;
	}
	
	/**
	 * Muestra todos los registros de la tabla "contactos".
	 * 
	 * @return out - String con todos los registros en formato tabla.
	 */
	public String list() {
		String out = "";
		
		try {
			rs = stmt.executeQuery("SELECT * FROM contactos");
			while (rs.next()) {
				out += String.format("%02d : [ %-25s | %-35s | %-40s ]%n",
						rs.getInt("id_c"),
						rs.getString("nombre"),
						rs.getString("direccion"),
						rs.getString("notas")
					);
			}
			
			rs.close();
			return out;
		} catch (SQLException sqlE) {
			return "error";
		}
	}
	
	// <<UPDATE>>
	/**
	 * Modifica un contacto de la BBDD.
	 * 
	 * @param c the contact object
	 * @return true/false - if the contact was modified successfully/if an SQLException occurs
	 */
	public boolean update(Contact c) {
		String sql = String.format(
				"UPDATE contactos SET nombre = '%s', direccion = '%s', notas = '%s' "
					+ "WHERE id_c = %d", c.getName(), c.getAddress(), c.getNotes(), c.getId_c());
		
		try {
			stmt.executeUpdate(sql);
			updateAficiones(c);
			if (c.getType().equals(Contact.Type[0])) {
				updatePersonas(c);
				if (c.getTelephones().size() > 0)
					updateTelefonos(c);
				if (c.getMails().size() > 0)
					updateCorreos(c);
			} else if (c.getType().equals(Contact.Type[1])) {
				if (c.getTelephones().size() > 0)
					updateTelefonos(c);
				if (c.getMails().size() > 0)
					updateCorreos(c);
			} else if (c.getType().equals(Contact.Type[2]))
				updateMascotas(c);
			
			return true;
		} catch (SQLException sqlE) {
			return false;
		}
	}
	
	/**
	 * Añade una o varias aficiones nuevas para un contacto en la tabla <b>'contactos_aficiones'</b>.
	 * 
	 * @param c the contact object
	 * @throws SQLException
	 */
	private void updateAficiones(Contact c) throws SQLException {
		String sql = "INSERT INTO contactos_aficiones (id_a, id_c) VALUES (?, ?)";
		
		for (int i = 0; i < c.getHobbies().size(); i++) {
			int id_h = c.getHobbies().get(i).getId();
			if (id_h == 0) {
				id_h = getId_h(c.getHobbies().get(i).getName());
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, id_h);
				pstmt.setInt(2, c.getId_c());
				pstmt.executeUpdate();
				pstmt.close();
			}
		}
	}
	
	/**
	 * Modifica un contacto en la tabla <b>'personas'</b>.
	 * 
	 * @param c the contact object
	 * @throws SQLException
	 */
	private void updatePersonas(Contact c) throws SQLException {
		String sql = String.format(
				"UPDATE personas SET apellidos = '%s', genero = '%s', fecha_nac = '%s' "
					+ "WHERE id_c = %d", c.getSurnames(), c.getGender(), c.getBirthDate(), c.getId_c());
		stmt.executeUpdate(sql);
	}
	
	/**
	 * Modifica un contacto en la tabla <b>'mascotas'</b>.
	 * 
	 * @param c the contact object
	 * @throws SQLException
	 */
	private void updateMascotas(Contact c) throws SQLException {
		String sql = String.format(
				"UPDATE mascotas SET fecha_nac = '%s' "
					+ "WHERE id_c = %d", c.getBirthDate(), c.getId_c());
		stmt.executeUpdate(sql);
	}
	
	/**
	 * Modifica todos los teléfonos de un contacto en la tabla <b>'telefonos'</b>.
	 * 
	 * @param c the contact object
	 * @throws SQLException 
	 */
	private void updateTelefonos(Contact c) throws SQLException {
		String[] Query = {
				"UPDATE telefonos SET telefono = ?, tipo = ? WHERE id_t = ?",
				"INSERT INTO telefonos (telefono, tipo, id_c) VALUES (?, ?, ?)"
		};
		
		int id_t;
		for (int i = 0; i < c.getTelephones().size(); i++) {
			id_t = c.getTelephones().get(i).getId_t();
			if (id_t != 0) {
				pstmt = con.prepareStatement(Query[0]);
				pstmt.setObject(1, c.getTelephones().get(i).getNumber());
				pstmt.setObject(2, c.getTelephones().get(i).getType());
				pstmt.setObject(3, id_t);
				pstmt.executeUpdate();
			} else {
				pstmt = con.prepareStatement(Query[1]);
				pstmt.setObject(1, c.getTelephones().get(i).getNumber());
				pstmt.setObject(2, c.getTelephones().get(i).getType());
				pstmt.setObject(3, c.getId_c());
				pstmt.executeUpdate();
			}
		}
		pstmt.close();
	}
	
	/**
	 * Modifica todos los correos de un contacto en la tabla <b>'correos'</b>.
	 * 
	 * @param c the contact object
	 * @throws SQLException 
	 */
	private void updateCorreos(Contact c) throws SQLException {
		final String[] Query = {
				"UPDATE correos SET correo = ? WHERE id_e = ?",
				"INSERT INTO correos (correo, id_c) VALUES (?, ?)"
		};
		
		int id_m;
		for (int i = 0; i < c.getMails().size(); i++) {
			id_m = c.getMails().get(i).getId_m();
			if (id_m != 0) {
				pstmt = con.prepareStatement(Query[0]);
				pstmt.setObject(1, c.getMails().get(i).getName());
				pstmt.setObject(2, id_m);
				pstmt.executeUpdate();
			} else {
				pstmt = con.prepareStatement(Query[1]);
				pstmt.setObject(1, c.getMails().get(i).getName());
				pstmt.setObject(2, c.getId_c());
				pstmt.executeUpdate();
			}
		}
		pstmt.close();
	}
	
	/**
	 * Modifica el nombre de una afición.
	 * 
	 * @param id the hobby ID
	 * @param newName nuevo nombre al que se modificará
	 */
	public void updateAficion(int id, String newName) { // VER LUEGO
		String sql = String.format("UPDATE aficiones SET aficion = '%s' WHERE id_a = %d", newName, id);
		
		try { stmt.executeUpdate(sql); }
		catch (SQLException sqlE) { System.err.println("ERROR: updateAficion()"); }
	}
	
	// <<DELETE>>
	/**
	 * Elimina un contacto de la BBDD.
	 * 
	 * @param c the contact object
	 * @return true/false - if the contact was eliminated successfully/if an SQLException occurs
	 */
	public boolean delete(Contact c) {
		int id_c = c.getId_c();
		final String[] Query = {
				"DELETE FROM contactos WHERE id_c = " + id_c,
				"DELETE FROM contactos_aficiones WHERE id_c = " + id_c,
				"DELETE FROM telefonos WHERE id_c = " + id_c,
				"DELETE FROM correos WHERE id_c = " + id_c,
				"DELETE FROM personas WHERE id_c = " + id_c,
				"DELETE FROM empresas WHERE id_c = " + id_c,
				"DELETE FROM mascotas WHERE id_c = " + id_c
		};
		
		try {
			stmt.executeUpdate(Query[0]);
			stmt.executeUpdate(Query[1]);
			if (c.getType().equals(Contact.Type[0])) {
				stmt.executeUpdate(Query[4]);
				stmt.executeUpdate(Query[2]);
				stmt.executeUpdate(Query[3]);
			} else if (c.getType().equals(Contact.Type[1])) {
				stmt.executeUpdate(Query[5]);
				stmt.executeUpdate(Query[2]);
				stmt.executeUpdate(Query[3]);
			} else if (c.getType().equals(Contact.Type[2])) {
				stmt.executeUpdate(Query[6]);
			}
			
			return true;
		} catch (SQLException sqlE) {
			return false;
		}
	}
	
	/**
	 * Elimina una afición a partir de un ID de afición.
	 * 
	 * @param id_h the hobby ID
	 * @throws SQLException
	 */
	public void deleteFromHobbiesWhereIdH(int id_h) throws SQLException {
			String sql = "DELETE FROM aficiones WHERE id_a = " + id_h;
			stmt.executeUpdate(sql);
	}
	
	/**
	 * Elimina una teléfono a partir de un ID de teléfono.
	 * 
	 * @param id_t the telephone ID
	 * @throws SQLException
	 */
	public void deleteFromTelephonesWhereIdT(int id_t) throws SQLException {
		String sql = "DELETE FROM telefonos WHERE id_t = " + id_t;
		stmt.executeUpdate(sql);
	}
	
	/**
	 * Elimina un correo a partir de un ID de correo.
	 * 
	 * @param id_m the mail ID
	 * @throws SQLException
	 */
	public void deleteFromMailsWhereIdM(int id_m) throws SQLException {
		String sql = "DELETE FROM correos WHERE id_e = " + id_m;
		stmt.executeUpdate(sql);
	}
	
	/**
	 * Elimina una afición de la tabla <b>'contactos_aficiones'</b> a partir de un ID de contacto y un ID de afición.
	 * 
	 * @param id_c the contact ID
	 * @param id_h the hobby ID
	 * @throws SQLException
	 */
	public void deleteFromContactHobbyWhereIdCAndIdH(int id_c, int id_h) throws SQLException {
		String sql = String.format(
				"DELETE FROM contactos_aficiones WHERE id_c = %d AND id_a = %d", id_c, id_h);		
		stmt.executeUpdate(sql);
	}
	
	// OTROS
	/**
	 * Cuenta las filas del ResultSet obtenido de una tabla
	 * 
	 * @param sql the sql statement
	 * @return the number of rows
	 * @throws SQLException
	 */
	private int count(String sql) throws SQLException {
		rs = stmt.executeQuery(sql);
		int cantidad = 0;
		while (rs.next()) {
			cantidad = rs.getInt(1);
		}
		
		return cantidad;
	}

	/**
	 * Cuenta las filas de una tabla.
	 * 
	 * @param table the table to count
	 * @return the number of rows
	 * @throws SQLException
	 */
	public int countRows(String table) throws SQLException {
		final String[] SQL = {
				"SELECT COUNT(id_a) FROM aficiones",
				"SELECT COUNT(id_a) FROM contactos_aficiones",
				"SELECT COUNT(id_c) FROM " + table
		};
		table = table.toLowerCase();
		
		if (table.equals(Database.HobbiesTitle))
			return count(SQL[0]);
		else if (table.equals(Database.ContactsHobbiesTitle))
			return count(SQL[1]);
		else
			return count(SQL[2]);
	}

	/**
	 * Cuenta las filas de una tabla a partir de un ID.
	 * 
	 * @param table the table to count
	 * @param id_c the contact ID
	 * @return the number of rows
	 * @throws SQLException
	 */
	public int countRowsWhereIdC(String table, int id_c) throws SQLException {
		final String[] SQL = {
				String.format("SELECT COUNT(id_t) FROM %s WHERE id_c = %d", table, id_c),
				String.format("SELECT COUNT(id_e) FROM %s WHERE id_c = %d", table, id_c),
				String.format("SELECT COUNT(id_a) FROM %s WHERE id_c = %d", table, id_c)
		};
		table = table.toLowerCase();
		
		if (table.equals("telefonos"))
			return count(SQL[0]);
		else if (table.equals("correos"))
				return count(SQL[1]);
		else
			return count(SQL[2]);
	}

	/**
	 * Cuenta las filas de una tabla a partir de un nombre o una afición.
	 * 
	 * @param table the table to count
	 * @param word the word to search
	 * @return the number of rows
	 * @throws SQLException
	 */
	public int countRowsWhereNombreOrAficionLike(String table, String word) throws SQLException {
		final String[] Query = {
				String.format("SELECT COUNT(*) FROM contactos WHERE lower(nombre) like '%s%%'", word),
				String.format("SELECT COUNT(*) FROM aficiones WHERE lower(aficion) like '%s%%'", word),
				String.format("SELECT COUNT(*) FROM contactos_aficiones "
								+ "JOIN aficiones USING (id_a) "
								+ "WHERE lower(aficion) like '%s%%'", word),
				String.format("SELECT COUNT(*) FROM contactos JOIN %s USING (id_c) "
								+ "WHERE lower(nombre) like '%s%%'", table, word)
		};
		
		switch (table.toLowerCase()) {
			case ("contactos"):
				return count(Query[0]);
			case ("aficiones"):
				return count(Query[1]);
			case ("contactos_aficiones"):
				return count(Query[2]);
			default:
				return count(Query[3]);
		}
	}
	
	/**
	 * Añade algunos registros a la BBDD.<br><br>
	 * 
	 * Creado con la finalidad de probar las funcionalidades de todos los demás métodos.
	 * 
	 * @throws SQLException
	 */
	public void init() throws SQLException {
		String sql;

		sql = "INSERT INTO contactos (nombre, direccion, notas) VALUES "						// ID:
				+ "('Alex', 'Calle A', 'Estudia en Clara del Rey'), "							// 1
				+ "('Wendy', 'Calle W', 'Psicología'), "										// 2
				+ "('Daniel', 'Calle D', 'Estudia en Clara del Rey'), "							// 3	
				+ "('Coca Cola', 'Calle de la Cola, 23', 'Lleva ingrediente secreto'), "		// 4
				+ "('Winnie', 'Avenida Mediterráneo Central, 34', 'Lazy dog'), "				// 5
				+ "('Pedro', 'Calle de 420', 'Le gusta el verde'), "							// 6
				+ "('7UP', 'Vía up, 67', 'Bubble drink'), "										// 7-
				+ "('Oddy', 'Avenida Mediterráneo Central, 192', 'Grumpy dog'), "				// 8
				+ "('Pacheco', 'Calle Toledo, 3', 'Se volvió a meter a Cryptomines'), "			// 9
				+ "('TRUHKO MakeUp', 'Calle Atocha, 94', 'Escuela de maquillaje'), "			// 10
				+ "('Milú', 'Avenida Glasgow, 45', 'Happy dog'), "								// 11-
				+ "('Tony', 'New York, 5º', 'Iron-Man'), "										// 12
				+ "('Steve', 'Calle Queens, 3', 'Captain America'), "							// 13
				+ "('Harry', 'Boulevar Spade, 2', 'Green Goblin'), "							// 14
				+ "('Rocket Raccoon', 'His spaceship', 'Groot best friend'), "					// 15
				+ "('Al', 'The Hell', 'Spawn'), "												// 16
				+ "('James', 'Calle mutant, XMen-House', 'Wolverine'), "						// 17
				+ "('Groot', 'Groot', 'Groot'), "												// 18
				+ "('Bruce', 'Mansión Wayne', 'Batman'), "										// 19
				+ "('Loki', 'Valaskjálf, Asgard', 'El origen de todo fraude'), "				// 20
				+ "('Dick', 'Mansión Wayne', 'Nightwing'), "									// 21
				+ "('Stephen', 'Sancto Sanctorum, New York', 'Doctor Strange'), "				// 22
				+ "('J. A. R. V. I. S.', 'Internet', 'Vision'), "								// 23
				+ "('Peter', 'New York, 98º', 'Spider-man'), "									// 24
				+ "('Thor', 'Valaskjálf, Asgard', 'Thunder God'), "								// 25
				+ "('Wanda', 'Su propia ilusión', 'Scarlet Witch'), "							// 26
				+ "('Alberto Marcos', 'Los Nogales', 'Compa de clase'), "						// 27
				+ "('Mario Carlos', 'Hacienda los Rosales', 'Actor de Telenovelas'), "			// 28
				+ "('Pearson-Hardman', 'Manhattan, 3', 'Firma de Abogados'), "					// 29
				+ "('Sam', 'La pradera', 'Pastor ovejero. Mejor amigo de Ralf.')";				// 30
		stmt.executeUpdate(sql);

		sql = "INSERT INTO personas VALUES"
				+ "(1, 'R', 'M', 'Fri Jun 01 2345'), "
				+ "(2, 'Rodríguez', 'F', 'Sat Jun 02 2345'), "
				+ "(3, 'M', 'M', 'Mon Apr 20 2910'), "
				+ "(6, 'Porro', 'M', 'Thu Oct 14 1999'), "
				+ "(9, 'Pacheco', 'M', 'Thu Oct 14 1999'), "
				+ "(12, 'Stark', 'M', 'Thu Oct 14 1999'), "
				+ "(13, 'Rogers', 'M', 'Thu Oct 14 1999'), "
				+ "(14, 'Osborn', 'M', 'Thu Oct 14 1999'), "
				+ "(16, 'Simmons', 'M', 'Thu Oct 14 1999'), "
				+ "(17, 'Logan', 'M', 'Thu Oct 14 1999'), "
				+ "(19, 'Wayne', 'M', 'Thu Oct 14 1999'), "
				+ "(20, 'Odinson', 'M', 'Thu Oct 14 1999'), "
				+ "(21, 'Grayson', 'M', 'Thu Oct 14 1999'), "
				+ "(22, 'Strange', 'M', 'Thu Oct 14 1999'), "
				+ "(23, 'Utron', 'O', 'Thu Oct 14 1999'), "
				+ "(24, 'Parker', 'F', 'Thu Oct 14 1999'), "
				+ "(25, 'Odinson', 'M', 'Thu Oct 14 1999'), "
				+ "(26, 'Maximoff', 'F', 'Mon Feb 14 1983'), "
				+ "(27, 'Martínez S.', 'M', 'Mon Feb 14 1983'), "
				+ "(28, 'Sandoval', 'M', 'Mon Feb 14 1983')";
		stmt.executeUpdate(sql);
		
		sql = "INSERT INTO empresas VALUES"
				+ "(4), (7), (10), (29)";
		stmt.executeUpdate(sql);

		sql = "INSERT INTO mascotas VALUES"
				+ "(5, 'Thu Apr 31 1983'), (8, 'Mon Aug 19 1983'), (11, 'Mon Jan 02 1983'),"
				+ "(15, 'Wed Feb 29 1983'), (18, 'Sun Oct 07 1983'), (30, 'Mon May 12 1983')";
		stmt.executeUpdate(sql);

		sql = "INSERT INTO aficiones (aficion) VALUES "
				+ "('Fútbol'), ('Baloncesto'), ('Balonmano'), ('Invertir en bolsa'), " 			// 1, 2, 3, 4 (23 aficiones=
				+ "('Coger la pelota'), ('Ir al teatro'), ('Viajar'), ('Restaurantes'), "		// 5, 6, 7, 8
				+ "('Dormir'), ('Jugar al bingo'), ('Leer'), ('Senderismo'), ('Escalar'), "		// 9, 10, 11, 12, 13
				+ "('Calistenia'), ('Tenis'), ('Cocinar'), ('Jugar videojuegos'), ('Pasear'), "	// 14, 15, 16, 17, 18,
				+ "('Programar'), ('Idiomas'), ('Ganar dinero'), ('Volleyball'), ('Ajedrez'), "	// 19, 20, 21, 22, 23
				+ "('Groot')"; 																	// 24
		stmt.executeUpdate(sql);
		
		sql = "INSERT INTO contactos_aficiones (id_c, id_a) VALUES "
				+ "(1, 1), (1, 3), (1, 4), (1, 7), (1, 19), (2, 7), (2, 8), (2, 11), (3, 2), (3, 4), "
				+ "(3, 7), (3, 19), (3, 17), (4, 21), (7, 21), (10, 21), (29, 21), (4, 4), (7, 4), "
				+ "(10, 4), (29, 4), (5, 5), (8, 5), (11, 5), (30, 5), (24, 8), (26, 16), (23, 19), "
				+ "(27, 23), (28, 14), (25, 20), (25, 17), (23, 23), (18, 24)";
		stmt.executeUpdate(sql);
		
		sql = "INSERT INTO telefonos (id_c, telefono, tipo) VALUES "
				+ "(1, '+34   699 228 692', 'M'), (2, '+34   922 995 223', 'F'), (3, '+34   692 222 222', 'M'), "
				+ "(6, '+34   691 539 888', 'M'), (9, '+34   991 121 555', 'F'), (12, '+34   600 112 332', 'M'), "
				+ "(13, '+34   676 153 942', 'M'), (14, '+34   669 991 333', 'M'), (16, '+34   701 119 832', 'M'), "
				+ "(17, '+34   602 684 232', 'M'), (19, '+34   748 443 344', 'M'), (20, '+34   948 430 752', 'F'), "
				+ "(21, '+34   696 279 775', 'M'), (22, '+34   943 323 232', 'F'), (23, '+34   911 228 338', 'F'), "
				+ "(24, '+34   904 363 645', 'F'), (25, '+34   638 223 848', 'M'), (27, '+34   645 834 838', 'M'), "
				+ "(28, '+34   924 234 243', 'F'), (4, '+34   935 345 967', 'F'), (7, '+34   914 937 963', 'F'), "
				+ "(10, '+34   927 743 333', 'F'), (29, '+34   748 669 344', 'M'), (16, '+34   638 228 555', 'M'), "
				+ "(10, '+34   648 443 344', 'M'), (29, '+34   638 321 942', 'M'), (19, '+34   914 119 832', 'F'), "
				+ "(10, '+34   648 645 602', 'M'), (2, '+34   971 586 293', 'F'), (3, '+34   669 333 991', 'M'), "
				+ "(10, '+34   927 743 333', 'F'), (17, '+34   638 321 942', 'M'), (1, '+34   743 344 648', 'M'), "
				+ "(12, '+34   691 914 748', 'F'), (14, '+34   725 034 891', 'M'), (12, '+34   945 967 942', 'F')";
		stmt.executeUpdate(sql);
		
		sql = "INSERT INTO correos (id_c, correo) VALUES "
				+ "(1, 'alexclaradelrey@educamadrid.es'), (2, 'wendy@gmail.com'), (3, 'daniclaradelrey@educamadrid.es'), "
				+ "(6, 'pporro@sporting.pt'), (9, 'pacheco@protonmail.com'), (12, 'tstark@avengers.com'), (13, 'capamerica@avengers.com'), "
				+ "(14, 'hosborn@villains.us'), (16, 'hell@666.hell'), (17, ''), (19, 'batman@justiceleague.com'), (20, 'loki@asgard.com'), "
				+ "(21, 'dgrayson@gotham.com'), (22, 'drstrange@avengers.com'), (23, 'jarvis@internet.net'), (24, 'peterparker@highschool.com'), "
				+ "(24, 'spiderman@avengers.com'), (25, 'thodinson@avengers.com'), (26, 'switch@avengers.com'), (27, 'berto@gmail.com'),"
				+ "(4, 'cocacola@coke.com'), (7, '7up@coke.com'), (29, 'contacto@pearhard.com'), (10, 'makeupforever@truhko.es'), "
				+ "(6, 'president@iosborn.com'), (28, 'mcarlosactor@gmail.mx'), (12, 'industriasstark@stark.com'), (4, 'info@coke.com')";
		stmt.executeUpdate(sql);
	}
	
	/**
	 * Cierra la conexión a la BBDD.
	 * 
	 * @throws SQLException
	 */
	public void close() {
		try {
			stmt.close();
			con.close();
		} catch (SQLException sqlE) {
			System.err.println("ERROR close()");
		}
	}
	
}
