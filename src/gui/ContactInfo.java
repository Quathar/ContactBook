package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import classes.Contact;
import classes.Telephone;

import dao.DAO;

import gui.components.ChangerComboBox;
import gui.tables.HobbyTable;
import gui.tables.MailTable;
import gui.tables.TelephoneTable;

import io.MSG;
import io.RegexFilter;

/**
 * ContactInfo.<br><br>
 * 
 * Clase que muestra gráficamente toda la información de un contacto.
 *
 * @since 2022-04-17
 * @see DAO
 * @see HobbyTable
 * @see TelephoneTable
 * @see MailTable
 * @author Q
 */
public class ContactInfo extends JFrame implements ActionListener { // CLASE FINALIZADA

	// CONSTANTES
	private static final long serialVersionUID = 1L;
	/**
	 * Icono de los campos.
	 */
	private final String[] IconTitle = {"\uE13D", "\uE1C3", "\uE13D", "\uE163", "\u26A5"}; // Nombre, Dirección, Apellidos, Fecha_nac, Género
	/**
	 * Icono de los botones AD (Add, Delete).
	 */
	private final String[] ADTitle = {"\uE109", "\uE108"};
	/**
	 * Título de las vistas del CardLayout (cl).
	 */
	private final String[] ViewsTitle = {"0", "1"};
	/**
	 * Título de los botones CE (Cerrar, Editar).
	 */
	private final String[] CETitle = {"CERRAR", "EDITAR"};
	/**
	 * Título de los botones VG (Volver, Guardar).
	 */
	private final String[] VGTitle = {"VOLVER", "GUARDAR"};

	// CAMPOS
	// <<ZONA DE DISEÑO>>
	/**
	 * ContentPane del JFrame.
	 */
	private JPanel contentPane;

	//--FORMULARIO
	/**
	 * Componente JTextField para el nombre.
	 */
	private JTextField nameTextField;
	/**
	 * Componente JTextField para los apellidos.
	 */
	private JTextField surnamesTextField;
	/**
	 * Componente JTextField para la fecha de nacimiento.
	 */
	private JTextField birthDateTextField;
	/**
	 * Componente JComboBox<String> para seleccionar el género.
	 */
	private JComboBox<String> genderComboBox;
	/**
	 * Componente JTextField para la dirección.
	 */
	private JTextField addressTextField;
	/**
	 * Componente JTextArea para las notas.
	 */
	private JTextArea notesTextArea;
	
	//--AFICIONES
	/**
	 * Componente HobbyTable de las aficiones.
	 */
	private HobbyTable hTable;
	/**
	 * Componente ChangerComboBox de la tabla aficiones.
	 */
	private ChangerComboBox hobbiesComboBox;
	/**
	 * Botones AD (Add, Delete) de la tabla aficiones.
	 */
	private JButton[] btnADaficiones = new JButton[2];
	
	//--TELÉFONOS Y CORREOS
	/**
	 * Componente TelephoneTable de los teléfonos.
	 */
	private TelephoneTable tTable;
	/**
	 * Componente JTextField para los teléfonos.
	 */
	private JTextField txtTelefono;
	/**
	 * Componente JComboBox<String> para seleccionar el tipo de teléfono.
	 */
	private JComboBox<String> tType;
	/**
	 * Botones AD (Add, Delete) de teléfonos.
	 */
	private JButton[] btnADtelefonos = new JButton[2];
	/**
	 * Componente MailTable de los correos.
	 */
	private MailTable mTable;
	/**
	 * Componente JTextField para los correos.
	 */
	private JTextField txtCorreo;
	/**
	 * Botones AD (Add, Delete) de los correos.
	 */
	private JButton[] btnADcorreos = new JButton[2];

	//--BOTONES - PANEL SOUTH
	/**
	 * Componente JPanel de la zona SOUTH del BorderLayout.
	 */
	private JPanel botones;
	/**
	 * Botones CE (Cerrar, Editar) del panel botones.
	 */
	private JButton[] btnCE = new JButton[2];
	/**
	 * Botones VG (Volver, Guardar).del panel botones.
	 */
	private JButton[] btnVG = new JButton[2];
	
	//-LAYOUTS
	/**
	 * Card Layout.
	 */
	private CardLayout cl;
	/**
	 * Grid BagLayout.
	 */
	private GridBagLayout gbl;
	/**
	 * Restricciones rid Bag Constraints.
	 */
	private GridBagConstraints gbc;
	
	// <<ZONA FUNCIONAL>>
	/**
	 * Graphic User Interface.
	 */
	private GUI gui;
	/**
	 * Data Access Object.
	 */
	private DAO dao;
	/**
	 * Contact Object.
	 */
	private Contact c;
	
	// CONSTRUCTOR
	/**
	 * Constructor.
	 * 
	 * @param dao the data access object
	 * @param id the contact ID
	 */
	public ContactInfo(GUI gui, DAO dao, int id) {
		super("Información del contacto");
		this.gui = gui;
		this.dao = dao;
		c = new Contact(id);
		getFormData();
		initComponents();
	}
	
	// MÉTODOS
	/**
	 * Obtiene información del contacto no referente a las tablas.
	 */
	private void getFormData() {
		int id = c.getId_c();
		String tipo = dao.getType(c.getId_c());
		c.setType(tipo);
		
		c.setName(dao.getFieldWhereIdC("nombre", id));
		c.setAddress(dao.getFieldWhereIdC("direccion", id));
		c.setNotes(dao.getFieldWhereIdC("notas", id));
		
		if (tipo.equals(Contact.Type[0])) {
			c.setSurnames(dao.getFieldWhereIdC("apellidos", id));
			c.setBirthDate(dao.getFieldWhereIdC("fecha_nac", id));
			String genero = dao.getFieldWhereIdC("genero", id);
			c.setGender(genero);
		} else if (tipo.equals(Contact.Type[2]))
			c.setBirthDate(dao.getFieldWhereIdC("fecha_nac", id));
		
	}
	
	// <<ZONA DE DISEÑO>>
	/**
	 * Crea los componentes y diseña el frame.
	 */
	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(	(int) (GUI.ScreenSize.width * 0.35),	// X position
					(int) (GUI.ScreenSize.height * 0.3),	// Y position
					(int) (GUI.ScreenSize.width * 0.3),	// Width
					(int) (GUI.ScreenSize.height * 0.45));	// Height
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		info();
		createSOUTH();
	}
	
	/**
	 * Diseña y crea los componentes el <b>panel info</b>.
	 */
	private void info() {
		JPanel info = new JPanel();
		contentPane.add(info, BorderLayout.CENTER);
		
		String tipo = c.getType();
		gbl = GUI.createGridBagLayoutFull(2, 2);
		if (tipo.equals(Contact.Type[1]))
			gbl.columnWidths[0] = 280;
		info.setLayout(gbl);
		
		createFormulario(info);
		createAficiones(info);
		if (!tipo.equals(Contact.Type[2]))
			createTables(info);
	}
	
	/**
	 * Da un valor a las columnas y filas dependiendo del tipo de contacto.
	 */
	private int[] setGridBagLayoutSize() {
		String tipo = c.getType();
		int[] size = {4, 5};
		if (tipo.equals(Contact.Type[1])) {			// EMPRESAS
			size[0] = 2;
			size[1] = 3;
		} else if (tipo.equals(Contact.Type[2])) {	// MASCOTAS
			size[0] = 2;
			size[1] = 4;
		}
		return size;
	}
	
	/**
	 * Da un valor al índice del desplegable género.
	 * 
	 * @param gender the gender to check
	 * @return the index for the genderComboBox
	 */
	private int placeGender(String gender) {
		if (gender.equals(Contact.G[0]))
			return 0;
		else if (gender.equals(Contact.G[1]))
			return 1;
		else
			return 2;
	}
	
	/**
	 * Diseña y crea los componentes del <b>panel formulario</b>.<br><br>
	 * 
	 * El diseño y el número de componentes varía en función del tipo de contacto.
	 * 
	 * @param info the parent panel
	 */
	private void createFormulario(JPanel info) {
		JPanel form = new JPanel();
		gbc = GUI.createGridBagConstraints(0, 0);
		String tipo = c.getType();
		if (tipo.equals(Contact.Type[2]))
			gbc.gridheight = 2;
		gbc.fill = GridBagConstraints.BOTH;
		info.add(form, gbc);
		
		int[] gblSize = setGridBagLayoutSize();
		gbl = GUI.createGridBagLayout(gblSize[0], gblSize[1], new int[] {1}, new int[] {gblSize[1] - 1});
		form.setLayout(gbl);

		// NOMBRE
		int rowNumber = 0, size = 20;
		JLabel lblNombre = new JLabel(IconTitle[0]);
		lblNombre.setFont(new Font("Segoe UI Symbol", Font.PLAIN, size));
		gbc = GUI.createGridBagConstraints(0, rowNumber);
		form.add(lblNombre, gbc);
		
		nameTextField = new JTextField();
		nameTextField.setText(c.getName());
		nameTextField.setEditable(false);
		gbc = GUI.createGridBagConstraints(1, rowNumber++);
		if (tipo.equals(Contact.Type[0]))
			gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		form.add(nameTextField, gbc);

		// PERSONAS
		if (tipo.equals(Contact.Type[0])) {
			// APELLIDOS
			JLabel lblApellidos = new JLabel(IconTitle[2]);
			lblApellidos.setFont(new Font("Segoe UI Symbol", Font.PLAIN, size));
			gbc = GUI.createGridBagConstraints(0, rowNumber);
			form.add(lblApellidos, gbc);
			
			surnamesTextField = new JTextField();
			surnamesTextField.setText(c.getSurnames());
			surnamesTextField.setEditable(false);
			gbc = GUI.createGridBagConstraints(1, rowNumber++);
			gbc.gridwidth = 3;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			form.add(surnamesTextField, gbc);
			
			// GÉNERO
			JLabel lblGenero = new JLabel(IconTitle[4]);
			lblGenero.setFont(new Font("Segoe UI Symbol", Font.PLAIN, size + 5));
			gbc = GUI.createGridBagConstraints(2, rowNumber);
			form.add(lblGenero, gbc);
			
			genderComboBox = new JComboBox<String>(Contact.Gender);
			genderComboBox.setSelectedIndex(placeGender(c.getGender()));
			genderComboBox.setEnabled(false);
			gbc = GUI.createGridBagConstraints(3, rowNumber);
			form.add(genderComboBox, gbc);
		}
		
		// PERSONAS O EMPRESAS
		if (!tipo.equals(Contact.Type[1])) {
			// FECHA DE NACIMIENTO
			JLabel lblFechaNac = new JLabel(IconTitle[3]);
			lblFechaNac.setFont(new Font("Segoe UI Symbol", Font.PLAIN, size));
			gbc = GUI.createGridBagConstraints(0, rowNumber);
			form.add(lblFechaNac, gbc);
			
			birthDateTextField = new JTextField();
			birthDateTextField.setText(c.getBirthDate());
			birthDateTextField.setEditable(false);
			gbc = GUI.createGridBagConstraints(1, rowNumber++);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			form.add(birthDateTextField, gbc);
		}
		
		// DIRECCIÓN
		JLabel lblDireccion = new JLabel(IconTitle[1]);
		lblDireccion.setFont(new Font("Segoe UI Symbol", Font.PLAIN, size - 5));
		gbc = GUI.createGridBagConstraints(0, rowNumber);
		form.add(lblDireccion, gbc);
		
		addressTextField = new JTextField();
		addressTextField.setText(c.getAddress());
		addressTextField.setEditable(false);
		gbc = GUI.createGridBagConstraints(1, rowNumber++);
		if (tipo.equals(Contact.Type[0]))
			gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		form.add(addressTextField, gbc);

		// NOTAS
		notesTextArea = new JTextArea();
		notesTextArea.setText(c.getNotes());
		notesTextArea.setEditable(false);
		JScrollPane sNotas = new JScrollPane(notesTextArea);
		gbc = GUI.createGridBagConstraints(0, rowNumber);
		gbc.gridwidth = 2;
		if (tipo.equals(Contact.Type[0]))
			gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.BOTH;
		form.add(sNotas, gbc);
	}
	
	/**
	 * Diseña y crea los componentes del <b>panel aficiones</b>.
	 * 
	 * @param info the parent panel
	 */
	private void createAficiones(JPanel info) {
		String tipo = c.getType();
		int x = tipo.equals(Contact.Type[2]) ? 1 : 0;
		int y = tipo.equals(Contact.Type[2]) ? 0 : 1;
		
		JPanel aficiones = new JPanel();
		gbc = GUI.createGridBagConstraints(x, y);
		if (tipo.equals(Contact.Type[2]))
			gbc.gridheight = 2;
		gbc.fill = GridBagConstraints.BOTH;
		info.add(aficiones, gbc);
		
		gbl = GUI.createGridBagLayoutFull(3, 2);
		gbl.rowWeights[1] = 0;
		aficiones.setLayout(gbl);
		
		int id = c.getId_c();
		hTable = new HobbyTable(dao, id);
		JScrollPane sHobbies = new JScrollPane(hTable);
		gbc = GUI.createGridBagConstraints(0, 0);
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.BOTH;
		aficiones.add(sHobbies, gbc);
		
		String[] hobbies = dao.getHobbiesWhereIdCNot(id);
		hobbiesComboBox = new ChangerComboBox(hobbies);
		gbc = GUI.createGridBagConstraints(0, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		aficiones.add(hobbiesComboBox, gbc);
		
		for (int i = 0; i < ADTitle.length; i++) {
			btnADaficiones[i] = new JButton(ADTitle[i]);
			btnADaficiones[i].setEnabled(false);
			btnADaficiones[i].addActionListener(this);
			gbc = GUI.createGridBagConstraints(i + 1, 1);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			aficiones.add(btnADaficiones[i], gbc);
		}
	}
	
	/**
	 * Diseña y crea los componentes del <b>panel tables</b>.
	 * 
	 * @param info the parent panel
	 */
	private void createTables(JPanel info) {
		JPanel tables = new JPanel();
		gbc = GUI.createGridBagConstraints(1, 0);
		gbc.gridheight = 2;
		gbc.insets = new Insets(0, 5, 0, 0);
		gbc.fill = GridBagConstraints.BOTH;
		info.add(tables, gbc);
		
		gbl = GUI.createGridBagLayoutFull(1, 4);
		gbl.rowWeights[1] = 0;
		gbl.rowWeights[3] = 0;
		tables.setLayout(gbl);
		
		int id = c.getId_c();
		// TELÉFONOS
		tTable = new TelephoneTable(dao, id);
		JScrollPane tScroll = new JScrollPane(tTable);
		gbc = GUI.createGridBagConstraints(0, 0);
		gbc.fill = GridBagConstraints.BOTH;
		tables.add(tScroll, gbc);

		JPanel telefonos = new JPanel();
		gbc = GUI.createGridBagConstraints(0, 1);
		gbc.fill = GridBagConstraints.BOTH;
		tables.add(telefonos, gbc);
		
		gbl = GUI.createGridBagLayoutFull(4, 1);
		telefonos.setLayout(gbl);
		
		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.addActionListener(this);
		gbc = GUI.createGridBagConstraints(0, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		telefonos.add(txtTelefono, gbc);
		
		tType = new JComboBox<String>(Telephone.Type);
		tType.setEnabled(false);
		gbc = GUI.createGridBagConstraints(1, 0);
		telefonos.add(tType, gbc);
		
		for (int i = 0; i < ADTitle.length; i++) {
			btnADtelefonos[i] = new JButton(ADTitle[i]);
			btnADtelefonos[i].setEnabled(false);
			btnADtelefonos[i].addActionListener(this);
			gbc = GUI.createGridBagConstraints(i + 2, 0);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			telefonos.add(btnADtelefonos[i], gbc);
		}
		
		// CORREOS
		mTable = new MailTable(dao, id);
		JScrollPane cScroll = new JScrollPane(mTable);
		gbc = GUI.createGridBagConstraints(0, 2);
		gbc.fill = GridBagConstraints.BOTH;
		tables.add(cScroll, gbc);
		
		JPanel correos = new JPanel();
		gbc = GUI.createGridBagConstraints(0, 3);
		gbc.fill = GridBagConstraints.BOTH;
		tables.add(correos, gbc);
		
		gbl = GUI.createGridBagLayoutFull(3, 0);
		correos.setLayout(gbl);
		
		txtCorreo = new JTextField();
		txtCorreo.setEditable(false);
		txtCorreo.addActionListener(this);
		gbc = GUI.createGridBagConstraints(0, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		correos.add(txtCorreo, gbc);
		
		for (int i = 0; i < ADTitle.length; i++) {
			btnADcorreos[i] = new JButton(ADTitle[i]);
			btnADcorreos[i].setEnabled(false);
			btnADcorreos[i].addActionListener(this);
			gbc = GUI.createGridBagConstraints(i + 1, 0);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			correos.add(btnADcorreos[i], gbc);
		}
	}
	
	/**
	 * Diseña y crea los componentes de los paneles.<br><br>
	 * 
	 * - <b>ce</b> (Cerrar, Editar)
	 * - <b>vg</b> (Volver, Guardar).
	 */
	private void createSOUTH() {
		botones = new JPanel();
		contentPane.add(botones, BorderLayout.SOUTH);
		
		cl = new CardLayout();
		botones.setLayout(cl);
		
		// CERRAR - EDITAR
		JPanel ce = new JPanel();
		botones.add(ce, ViewsTitle[0]);
		ce.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		for (int i = 0; i < btnCE.length; i++) {
			btnCE[i] = new JButton(CETitle[i]);
			btnCE[i].addActionListener(this);
			ce.add(btnCE[i]);
		}
		
		// VOLVER - GUARDAR
		JPanel vg = new JPanel();
		botones.add(vg, ViewsTitle[1]);
		vg.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		for (int i = 0; i < btnVG.length; i++) {
			btnVG[i] = new JButton(VGTitle[i]);
			btnVG[i].addActionListener(this);
			vg.add(btnVG[i]);
		}
	}
	
	// <<ZONA DE LISTENERS>>
	// ACTION LISTENER
	@Override
	public void actionPerformed(ActionEvent e) {
		Object event = e.getSource();
		if (!listenerTables(event))
			listenerSouth(event);
	}
	
	/**
	 * Define acciones para los botones AD (Add, Delete) de las tablas.
	 * 
	 * @param event the event source
	 * @return true or false - if the event source is/isn't in this method
	 */
	private boolean listenerTables(Object event) {
		// AFICIONES
		if (btnADaficiones[0] == event) {						// BOTÓN ADD AFICIONES
			String selected = (String) hobbiesComboBox.getSelectedItem();
			if (selected != null)
				hTable.addHobby(selected);
			if (hobbiesComboBox.getItemCount() > 0)
				hobbiesComboBox.removeItemAt(hobbiesComboBox.getSelectedIndex());
			return true;
		} else if (btnADaficiones[1] == event) {				// BOTÓN DELTE AFICIONES
			hTable.addToComboBox(hobbiesComboBox);
			hTable.deleteSelectedRows(c.getId_c());
			return true;
		}
		
		// TELÉFONOS
		if (btnADtelefonos[0] == event || txtTelefono == event) {// BOTÓN ADD TELÉFONOS
			String telephone = RegexFilter.checkTelephone(txtTelefono.getText());
			if (telephone.equals("ERROR")) {
				MSG.warningMessage("¡FORMATO INCORRECTO!\nIntroduzca 9 números");
				return true;
			}
			Object[] data = {0, telephone, tType.getSelectedItem()};
			tTable.insertRow(data);
			txtTelefono.setText("");
			return true;
		} else if (btnADtelefonos[1] == event) {				// BOTÓN DELTE TELÉFONOS
			tTable.deleteSelectedRows();
			return true;
		}
		
		// CORREOS
		if (btnADcorreos[0] == event || txtCorreo == event) {	// BOTÓN ADD CORREOS
			String mail = RegexFilter.checkMail(txtCorreo.getText());
			if (mail.equals("ERROR")) {
				MSG.warningMessage("¡FORMATO INCORRECTO!\nIntroduzca: (correo)@(mail).(ext)");
				return true;
			}
			Object[] data = {0, mail};
			mTable.insertRow(data);
			txtCorreo.setText("");
			return true;
		} else if (btnADcorreos[1] == event) {					// BOTÓN DELTE CORREOS
			mTable.deleteSelectedRows();
			return true;
		}
		return false;
	}
	
	/**
	 * Define acciones para los botones del panel SOUTH.
	 * 
	 * @param event the event source
	 */
	private boolean listenerSouth(Object event) {
		if (listenerCE(event) || listenerVG(event))
			return true;
		return false;
	}
	
	/**
	 * Habilita o deshabilita todos los componentes de las tablas teléfonos y correos.
	 * 
	 * @param enable the boolean to enable (true) or disable (false) the components
	 */
	private void enableTables(boolean enable) {
		txtTelefono.setEditable(enable);
		tTable.setEnabled(enable);
		tType.setEnabled(enable);
		btnADtelefonos[0].setEnabled(enable);
		btnADtelefonos[1].setEnabled(enable);
		
		txtCorreo.setEditable(enable);
		mTable.setEnabled(enable);
		btnADcorreos[0].setEnabled(enable);
		btnADcorreos[1].setEnabled(enable);
	}
	
	/**
	 * Habilita o deshabilita los componentes de la interfaz.
	 * 
	 * @param enable the boolean to enable (true) or disable (false) the components
	 */
	private void enableComponents(boolean enable) {
		String tipo = c.getType();
		
		nameTextField.setEditable(enable);
		addressTextField.setEditable(enable);
		notesTextArea.setEditable(enable);
		
		hTable.setEnabled(enable);
		btnADaficiones[0].setEnabled(enable);
		btnADaficiones[1].setEnabled(enable);
		
		if (tipo.equals(Contact.Type[0])) {		// PARA PERSONAS
			surnamesTextField.setEditable(enable);
			birthDateTextField.setEditable(enable);
			genderComboBox.setEnabled(enable);
			enableTables(enable);
		} else if (tipo.equals(Contact.Type[1]))	// PARA EMPRESAS
			enableTables(enable);
		else if (tipo.equals(Contact.Type[2]))		// PARA MASCOTAS
			birthDateTextField.setEditable(enable);
	}
	
	/**
	 * Define acciones para los botones CE (Cerrar, Editar).
	 * 
	 * @param event the event source
	 */
	private boolean listenerCE(Object event) {
		if (btnCE[0] == event) {		// BOTÓN CERRAR
			dispose();
			return true;
		} else if (btnCE[1] == event) {	// BOTÓN EDITAR
			enableComponents(true);
			cl.show(botones, ViewsTitle[1]);
			return true;
		}
		return false;
	}
	
	/**
	 * Cambia el contenido de los componentes a su contenido original.
	 */
	private void changeToOriginalData() {
		nameTextField.setText(c.getName());
		addressTextField.setText(c.getAddress());
		notesTextArea.setText(c.getNotes());
		
		String tipo = c.getType();
		if (tipo.equals(Contact.Type[0])) {
			surnamesTextField.setText(c.getSurnames());
			birthDateTextField.setText(c.getBirthDate());
			genderComboBox.setSelectedIndex(placeGender(c.getGender()));
		} else if (tipo.equals(Contact.Type[2]))
			birthDateTextField.setText(c.getBirthDate());
	}
	
	/**
	 * Define acciones para los botones VG (Volver, Guardar).
	 * 
	 * @param event the event source
	 */
	private boolean listenerVG (Object event) {
		if (btnVG[0] == event) {		// BOTÓN VOLVER
			String out = "¿Está seguro de que quiere volver?\n"
					+ "NO se guardarán los cambios";
			if (MSG.defaultOptionMessage(out) == 0) {
				changeToOriginalData();
				enableComponents(false);
				cl.show(botones, ViewsTitle[0]);
			}
			return true;
		} else if (btnVG[1] == event) {	// BOTÓN GUARDAR
			dao.modify(this, c.getId_c());
			enableComponents(false);
			cl.show(botones, ViewsTitle[0]);
			gui.updateTables();
			return true;
		}
		return false;
	}
	
	// GETTERS
	/**
	 * @return the nameTextField
	 */
	public JTextField getTxtNombre() {
		return nameTextField;
	}
	
	/**
	 * @return the addressTextField
	 */
	public JTextField getTxtDireccion() {
		return addressTextField;
	}
	
	/**
	 * @return the notesTextArea
	 */
	public JTextArea getTxtNotas() {
		return notesTextArea;
	}
	
	/**
	 * @return the surnamesTextField
	 */
	public JTextField getTxtApellidos() {
		return surnamesTextField;
	}
	
	/**
	 * @return the birthDateTextField
	 */
	public JTextField getTxtFechaNac() {
		return birthDateTextField;
	}
	
	/**
	 * @return the genderComboBox.
	 */
	public JComboBox<String> getCbGenero() {
		return genderComboBox;
	}
	
	/**
	 * @return the hobby table
	 */
	public HobbyTable getHobbyTable() {
		return hTable;
	}
	
	/**
	 * @return the telephone table
	 */
	public TelephoneTable getTelephoneTable() {
		return tTable;
	}
	
	/**
	 * @return the mail table
	 */
	public MailTable getMailTable() {
		return mTable;
	}

}
