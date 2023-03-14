package com.quathar.app.gui.frame;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import com.quathar.app.model.Contact;
import com.quathar.app.model.Telephone;

import com.quathar.app.dao.DAO;

import com.quathar.app.gui.table.HobbyTable;
import com.quathar.app.gui.table.MailTable;
import com.quathar.app.gui.table.TelephoneTable;

import com.quathar.app.io.MSG;
import com.quathar.app.io.RegexFilter;

/**
 * AddContact.<br><br>
 * 
 * Clase utilizada para a�adir contactos gr�ficamente.
 *
 * @since 2022-04-13
 * @see ContactBook
 * @see DAO
 * @see HobbyTable
 * @see TelephoneTable
 * @see MailTable
 * @author Q
 */
public class Add extends JFrame implements ActionListener, FocusListener { // CLASE FINALIZADA

	// CONSTANTES	
	private static final long serialVersionUID = 1L;
	/**
	 * T�tulo de los items del desplegable contactType.
	 */
	private final String[] ContactTypeTitle = {"PERSONA", "EMPRESA", "MASCOTA"};
	/**
	 * Placeholders de los componentes JTextField.
	 */
	public static final String[] PlaceHoldersTitles = {"", "Nombre...", "Apellidos...", "Direcci�n...", "Notas...", "Tel�fono...", "Correo..."};
	/**
	 * T�tulo de los botones AD (Add - Delete).
	 */
	private final String[] ADTitle = {"\uE109", "\uE108"};
	/**
	 * T�tulo de los botones del VA (btnVA).
	 */
	private final String[] VATitle = {"VOLVER", "A�ADIR"};
	
	// CAMPOS
	// <<ZONA DE DISE�O>>
	/**
	 * ContentPane del JFrame.
	 */
	private JPanel contentPane;
	
	// -NORTH
	/**
	 * Desplegable para seleccionar el tipo de contacto.
	 */
	private JComboBox<String> cTypeComboBox;
	
	// -CENTER
	/**
	 * Panel contenedor de datos.
	 */
	private JPanel dataPanel;
	
	// --CAMPOS de FORMULARIO
	/**
	 * Componente JTextField para el nombre.
	 */
	private JTextField nameTextField;
	/**
	 * Componente JTextField para los apellidos.
	 */
	private JTextField surnamesTextField;
	/**
	 * Componente JTextField para la direcci�n.
	 */
	private JTextField addressTextField;
	/**
	 * Componente JDateChooser para la fecha de nacimiento.
	 */
	private JDateChooser birthDateChooser;
	/**
	 * Componente JComboBox para seleccionar el g�nero.
	 */
	private JComboBox<String> genderComboBox;
	/**
	 * Componente JTextArea para las notas.
	 */
	private JTextArea notesTextArea;
	
	// --CAMPOS de AFICIONES
	/**
	 * Tabla de las aficiones.
	 */
	private HobbyTable hTable;
	/**
	 * Desplegable para seleccionar aficiones.
	 */
	private JComboBox<String> hobbiesComboBox;
	/**
	 * Botones para a�adir (add) y borrar (delete) aficiones.
	 */
	private JButton[] btnADaficiones = new JButton[2];
 	
	// --CAMPOS TEL�FONOS Y CORREOS
	/**
	 * Tabla de los tel�fonos.
	 */
	private TelephoneTable tTable;
	/**
	 * Componente JTextArea para los tel�fonos.
	 */
	private JTextField telephoneTextField;
	/**
	 * Componente JComboBox para seleccionar el tipo de tel�fono.
	 */
	private JComboBox<String> tType;
	/**
	 * Botones para a�adir (add) y borrar (delete) telefonos.
	 */
	private JButton[] btnADtelefonos = new JButton[2];
	/**
	 * Tabla de los correos.
	 */
	private MailTable mTable;
	/**
	 * Componente JTextArea para los correos.
	 */
	private JTextField mailTextField;
	/**
	 * Botones para a�adir (add) y borrar (delete) correos.
	 */
	private JButton[] btnADcorreos = new JButton[2];
	
	// -SOUTH
	/**
	 * Botones Volver y A�adir.
	 */
	private JButton[] btnVA = new JButton[2];

	// --LAYOUTS
	/**
	 * Grid Bag Layout.
	 */
	private GridBagLayout gbl;
	/**
	 * Grid Bag Constraints.
	 */
	private GridBagConstraints gbc;
	
	// <<ZONA FUNCIONAL>>
	/**
	 * Ventana principal.
	 */
	private ContactBook contactBook;
	/**
	 * Data Acces Object.
	 */
	private DAO dao;
	
	// CONSTRUCTOR
	/**
	 * Constructor.
	 * 
	 * @param contactBook main window
	 */
	public Add(ContactBook contactBook, DAO dao) {
		super("A�adir un nuevo contacto");
		this.contactBook = contactBook;
		this.dao = dao;
		initComponents();
	}
	
	// ZONA DE DISE�O
	/**
	 * Dise�a y crea los componentes del frame.
	 */
	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(	(int) (ContactBook.ScreenSize.width * 0.3),
					(int) (ContactBook.ScreenSize.height * 0.25),
					(int) (ContactBook.ScreenSize.width * 0.4),
					(int) (ContactBook.ScreenSize.height * 0.5));
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		gbl = ContactBook.createGridBagLayout(1, 3, new int[] {0}, new int[] {1});
		contentPane.setLayout(gbl);
		
		createNORTH();
		createCENTER();
		createSOUTH();
	}
	
	/**
	 *  Dise�a y crea los componentes la zona NORTH.
	 */
	private void createNORTH() {
		cTypeComboBox = new JComboBox<String>(ContactTypeTitle);
		cTypeComboBox.addActionListener(this);
		gbc = ContactBook.createGridBagConstraints(0, 0);
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(0, 0, 3, 5);
		contentPane.add(cTypeComboBox, gbc);
	}
	
	/**
	 * Dise�a y crea los componentes la zona CENTER.
	 */
	private void createCENTER() {
		dataPanel = new JPanel();
		gbc = ContactBook.createGridBagConstraints(0, 1);
		gbc.fill = GridBagConstraints.BOTH;
		contentPane.add(dataPanel, gbc);
		dataPanel.setLayout(ContactBook.createGridBagLayoutFull(2, 2));
		
		createFormulario();
		createAficiones();
		createTables();
	}
	
	/**
	 * Dise�a y crea los componentes del formulario.
	 */
	private void createFormulario() {
		JPanel form = new JPanel();
		gbc = ContactBook.createGridBagConstraints(0, 0);
		gbc.insets = new Insets(0, 5, 0, 0);
		gbc.fill = GridBagConstraints.BOTH;
		dataPanel.add(form, gbc);
		gbl = ContactBook.createGridBagLayout(4, 5, new int[] {1}, new int[] {4});
		form.setLayout(gbl);
		
		// NOMBRE
		nameTextField = new JTextField(PlaceHoldersTitles[1]);
		nameTextField.addFocusListener(this);
		gbc = ContactBook.createGridBagConstraints(0, 0);
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		form.add(nameTextField, gbc);
		
		// APELLIDOS
		surnamesTextField = new JTextField(PlaceHoldersTitles[2]);
		surnamesTextField.addFocusListener(this);
		gbc = ContactBook.createGridBagConstraints(0, 1);
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		form.add(surnamesTextField, gbc);
		
		// FECHA DE NACIMIENTO
		JLabel lblFechaNac = new JLabel("Nacimiento:");
		gbc = ContactBook.createGridBagConstraints(0, 2);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(0, 5, 0, 0);
		form.add(lblFechaNac, gbc);
		
		birthDateChooser = new JDateChooser();
		gbc = ContactBook.createGridBagConstraints(1, 2);
		gbc.insets = new Insets(0, 5, 0, 15);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		form.add(birthDateChooser, gbc);
		
		// G�NERO
		JLabel lblGenero = new JLabel("G�nero:");
		gbc = ContactBook.createGridBagConstraints(2, 2);
		gbc.anchor = GridBagConstraints.EAST;
		form.add(lblGenero, gbc);
		
		genderComboBox = new JComboBox<String>(new String[] {"Masculino", "Femenino", "Otro"});
		gbc = ContactBook.createGridBagConstraints(3, 2);
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(0, 5, 0, 0);
		form.add(genderComboBox, gbc);
		
		// DIRECCI�N
		addressTextField = new JTextField(PlaceHoldersTitles[3]);
		addressTextField.addFocusListener(this);
		gbc = ContactBook.createGridBagConstraints(0, 3);
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		form.add(addressTextField, gbc);
		
		// NOTAS
		notesTextArea = new JTextArea(PlaceHoldersTitles[4]);
		notesTextArea.addFocusListener(this);
		JScrollPane sNotas = new JScrollPane(notesTextArea);
		gbc = ContactBook.createGridBagConstraints(0, 4);
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.BOTH;
		form.add(sNotas, gbc);
	}
	
	/**
	 * Dise�a y crea los componentes de la tabla aficiones.
	 */
	private void createAficiones() {
		JPanel hobbies = new JPanel();
		gbc = ContactBook.createGridBagConstraints(0, 1);
		gbc.insets = new Insets(5, 5, 0, 0);
		gbc.fill = GridBagConstraints.BOTH;
		dataPanel.add(hobbies, gbc);
		gbl = ContactBook.createGridBagLayoutFull(3, 2);
		gbl.rowWeights[1] = 0;
		hobbies.setLayout(gbl);
		
		// TABLA
		hTable = new HobbyTable(dao, false);
		JScrollPane hScroll = new JScrollPane(hTable);
		gbc = ContactBook.createGridBagConstraints(0, 0);
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.BOTH;
		hobbies.add(hScroll, gbc);
		
		// DEPLEGABLE
		hobbiesComboBox = new JComboBox<String>(dao.getHobbies());
		gbc = ContactBook.createGridBagConstraints(0, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		hobbies.add(hobbiesComboBox, gbc);
		
		// BOTONES
		for (int i = 0; i < btnADaficiones.length; i++) {
			btnADaficiones[i] = new JButton(ADTitle[i]);
			btnADaficiones[i].addActionListener(this);
			gbc = ContactBook.createGridBagConstraints(i + 1, 1);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			hobbies.add(btnADaficiones[i], gbc);
		}
	}
	
	/**
	 * Dise�a y crea los componentes de las tablas tel�fonos y aficiones.
	 */
	private void createTables() {
		JPanel tables = new JPanel();
		gbc = ContactBook.createGridBagConstraints(1, 0);
		gbc.gridheight = 2;
		gbc.insets = new Insets(0, 5, 0, 5);
		gbc.fill = GridBagConstraints.BOTH;
		dataPanel.add(tables, gbc);
		gbl = ContactBook.createGridBagLayoutFull(1, 4);
		gbl.rowWeights[1] = 0;
		gbl.rowWeights[3] = 0;
		tables.setLayout(gbl);
		
		// TEL�FONOS
		tTable = new TelephoneTable(dao);
		JScrollPane tScroll = new JScrollPane(tTable);
		gbc = ContactBook.createGridBagConstraints(0, 0);
		gbc.fill = GridBagConstraints.BOTH;
		tables.add(tScroll, gbc);
		
		//-INTRODUCCI�N DE TEL�FONOS
		JPanel iTelefonos = new JPanel();
		gbc = ContactBook.createGridBagConstraints(0, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		tables.add(iTelefonos, gbc);
		gbl = ContactBook.createGridBagLayout(4, 1, new int[] {0, 2, 3}, new int[] {0});
		iTelefonos.setLayout(gbl);
		
		telephoneTextField = new JTextField(PlaceHoldersTitles[5]);
		telephoneTextField.addFocusListener(this);
		telephoneTextField.addActionListener(this);
		gbc = ContactBook.createGridBagConstraints(0, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		iTelefonos.add(telephoneTextField, gbc);
		
		tType = new JComboBox<String>(Telephone.Type);
		gbc = ContactBook.createGridBagConstraints(1, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		iTelefonos.add(tType, gbc);
		
		for (int i = 0; i < btnADtelefonos.length; i++) {
			btnADtelefonos[i] = new JButton(ADTitle[i]);
			btnADtelefonos[i].addActionListener(this);
			gbc = ContactBook.createGridBagConstraints(i + 2, 0);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			iTelefonos.add(btnADtelefonos[i], gbc);
		}
		
		// CORREOS�
		mTable = new MailTable(dao);
		JScrollPane mScroll = new JScrollPane(mTable);
		gbc = ContactBook.createGridBagConstraints(0, 2);
		gbc.insets = new Insets(5, 0, 0, 0);
		gbc.fill = GridBagConstraints.BOTH;
		tables.add(mScroll, gbc);
		
		//-INTRODUCCI�N DE CORREOS
		JPanel iCorreos = new JPanel();
		gbc = ContactBook.createGridBagConstraints(0, 3);
		gbc.fill = GridBagConstraints.BOTH;
		tables.add(iCorreos, gbc);
		gbl = ContactBook.createGridBagLayoutFull(5, 1);
		iCorreos.setLayout(gbl);
		
		mailTextField = new JTextField(PlaceHoldersTitles[6]);
		mailTextField.addFocusListener(this);
		mailTextField.addActionListener(this);
		gbc = ContactBook.createGridBagConstraints(0, 0);
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		iCorreos.add(mailTextField, gbc);
		
		for (int i = 0; i < btnADcorreos.length; i++) {
			btnADcorreos[i] = new JButton(ADTitle[i]);
			btnADcorreos[i].addActionListener(this);
			gbc = ContactBook.createGridBagConstraints(i + 3, 0);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			iCorreos.add(btnADcorreos[i], gbc);
		}
	}
	
	/**
	 * Dise�a y crea los componentes la zona SOUTH.
	 */
	private void createSOUTH() {
		JPanel buttons = new JPanel();
		gbc = ContactBook.createGridBagConstraints(0, 2);
		gbc.fill = GridBagConstraints.BOTH;
		contentPane.add(buttons, gbc);
		buttons.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		for (int i = 0; i < btnVA.length; i++) {
			btnVA[i] = new JButton(VATitle[i]);
			btnVA[i].addActionListener(this);
			buttons.add(btnVA[i]);
		}
	}
	
	// <<ZONA DE LISTENERS>>
	// ACTION LISTENER
	@Override
	public void actionPerformed(ActionEvent e) {
		Object event = e.getSource();
		if (!listenerContactType(event) && !listenerAD(event))
			listenerVA(event);
	}
	
	/**
	 * Define acciones para el desplegable contactType.
	 * 
	 * @param event the event source
	 * @return true/false - if the event source is/isn't on this method
	 */
	private boolean listenerContactType(Object event) {
		if (cTypeComboBox == event) {
			String type = (String) (cTypeComboBox.getSelectedItem() + "s").toLowerCase();
			
			if (type.equals(Contact.Type[0])) {				// PERSONAS
				surnamesTextField.setText(PlaceHoldersTitles[2]);
				surnamesTextField.setEnabled(true);
				birthDateChooser.setEnabled(true);
				genderComboBox.setEnabled(true);

				tTable.setEnabled(true);
				telephoneTextField.setText(PlaceHoldersTitles[5]);
				telephoneTextField.setEnabled(true);
				tType.setEnabled(true);
				for (int i = 0; i < btnADtelefonos.length; i++)
					btnADtelefonos[i].setEnabled(true);
				mTable.setEnabled(true);
				mailTextField.setText(PlaceHoldersTitles[6]);
				mailTextField.setEnabled(true);
				for (int i = 0; i < btnADcorreos.length; i++)
					btnADcorreos[i].setEnabled(true);
				return true;
			} else if (type.equals(Contact.Type[1])) {		// EMPRESAS
				surnamesTextField.setText(PlaceHoldersTitles[0]);
				surnamesTextField.setEnabled(false);
				birthDateChooser.cleanup();
				birthDateChooser.setEnabled(false);
				genderComboBox.setSelectedIndex(0);
				genderComboBox.setEnabled(false);
	
				tTable.setEnabled(true);
				telephoneTextField.setText(PlaceHoldersTitles[5]);
				telephoneTextField.setEnabled(true);
				tType.setEnabled(true);
				for (int i = 0; i < btnADtelefonos.length; i++)
					btnADtelefonos[i].setEnabled(true);
				mTable.setEnabled(true);
				mailTextField.setText(PlaceHoldersTitles[6]);
				mailTextField.setEnabled(true);
				for (int i = 0; i < btnADcorreos.length; i++)
					btnADcorreos[i].setEnabled(true);
				return true;
			} else if (type.equals(Contact.Type[2])) {		// MASCOTAS
				surnamesTextField.setText(PlaceHoldersTitles[0]);
				surnamesTextField.setEnabled(false);
				birthDateChooser.setEnabled(true);
				genderComboBox.setSelectedIndex(0);
				genderComboBox.setEnabled(false);
				
				tTable.clean();
				tTable.setEnabled(false);
				telephoneTextField.setText(PlaceHoldersTitles[0]);
				telephoneTextField.setEnabled(false);
				tType.setEnabled(false);
				for (int i = 0; i < btnADtelefonos.length; i++)
					btnADtelefonos[i].setEnabled(false);
				mTable.clean();
				mTable.setEnabled(false);
				mailTextField.setText(PlaceHoldersTitles[0]);
				mailTextField.setEnabled(false);
				for (int i = 0; i < btnADcorreos.length; i++)
					btnADcorreos[i].setEnabled(false);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Define acciones para los botoenes AD (Add, Delete).
	 * 
	 * @param event the event source
	 * @return true/false - if the event source is/isn't on this method
	 */
	private boolean listenerAD(Object event) {
		if (listenerHobbies(event) || listenerTelephonesMails(event))
			return true;
		return false;
	}
	
	/**
	 * Define acciones para los botones AD de la tabla aficiones.
	 * 
	 * @param event the event source
	 * @return true/false - if the event source is/isn't on this method
	 */
	private boolean listenerHobbies(Object event) {
		if (btnADaficiones[0] == event) {
			String selected = (String) hobbiesComboBox.getSelectedItem();
			if (selected != null)
				hTable.addHobby(selected);
			if (hobbiesComboBox.getItemCount() > 0)
				hobbiesComboBox.removeItemAt(hobbiesComboBox.getSelectedIndex());
			return true;
		} else if (btnADaficiones[1] == event) {
			hTable.addToComboBox(hobbiesComboBox);
			hTable.deleteSelectedRows(0);
			return true;
		}
		return false;
	}
	
	/**
	 * Define acciones para los botones AD de las tablas tel�fonos y correos.
	 * 
	 * @param event the event source
	 * @return true/false - if the event source is/isn't on this method
	 */
	private boolean listenerTelephonesMails(Object event) {
		// TEL�FONOS AD
		if (btnADtelefonos[0] == event || telephoneTextField == event) {
			String telephone = RegexFilter.checkTelephone(telephoneTextField.getText());
			if (telephone.equals("ERROR")) {
				MSG.warningMessage("�FORMATO INCORRECTO!\nIntroduzca 9 n�meros");
				return true;
			}
			Object[] data = {0, telephone, tType.getSelectedItem()};
			tTable.insertRow(data);
			if (btnADtelefonos[0] == event)
				telephoneTextField.setText(PlaceHoldersTitles[5]);
			else
				telephoneTextField.setText(PlaceHoldersTitles[0]);
			return true;
		} else if (btnADtelefonos[1] == event) {
			tTable.deleteSelectedRows();
			return true;
		}

		// CORREOS AD
		if (btnADcorreos[0] == event || mailTextField == event) {
			String mail = RegexFilter.checkMail(mailTextField.getText());
			if (mail.equals("ERROR")) {
				MSG.warningMessage("�FORMATO INCORRECTO!\nIntroduzca: (correo)@(mail).(ext)");
				return true;
			}
			Object[] data = {0, mail};
			mTable.insertRow(data);
			if (btnADcorreos[0] == event)
				mailTextField.setText(PlaceHoldersTitles[6]);
			else
				mailTextField.setText(PlaceHoldersTitles[0]);
			return true;
		} else if (btnADcorreos[1] == event) {
			mTable.deleteSelectedRows();
			return true;
		}
		return false;
	}
	
	/**
	 * Resetea los componentes de la DAO en funci�n del tipo de contacto que se ha a�adido.
	 */
	private void cleanFields() {
		nameTextField.setText(PlaceHoldersTitles[1]);
		addressTextField.setText(PlaceHoldersTitles[3]);
		notesTextArea.setText(PlaceHoldersTitles[4]);
		hobbiesComboBox.removeAllItems();
		hTable.clean();
		String[] aficiones = dao.getHobbies();
		for (int i = 0; i < aficiones.length; i++) 
			hobbiesComboBox.addItem(aficiones[i]);
		
		surnamesTextField.setText(PlaceHoldersTitles[2]);
		birthDateChooser.cleanup();
		genderComboBox.setSelectedIndex(0);
		tTable.clean();
		telephoneTextField.setText(PlaceHoldersTitles[5]);
		mTable.clean();
		mailTextField.setText(PlaceHoldersTitles[6]);
	}
	
	/**
	 * 
	 * Define acciones para los botones VA (Volver, A�adir).
	 * 
	 * @param event the event source
	 * @return true/false - if the event source is/isn't on this method
	 */
	private boolean listenerVA(Object event) {
		if (btnVA[0] == event) {
			contactBook.setVisible(true);
			dispose();
			return true;
		} else if (btnVA[1] == event) {
			if (!nameTextField.getText().equals(PlaceHoldersTitles[1]) || nameTextField.getText().isBlank()) {
				dao.register(this);
				cleanFields();
				contactBook.updateTables();
			} else MSG.warningMessage("TIENE QUE A�ADIR UN NOMBRE");
			return true;
		}
		return false;
	}
	
	// FOCUS LISTENER
	@Override
	public void focusGained(FocusEvent e) {
		// NOMBRE
		if (nameTextField == e.getSource() && nameTextField.getText().equals(PlaceHoldersTitles[1]))
			nameTextField.setText(PlaceHoldersTitles[0]);

		// APELLIDOS
		if (surnamesTextField == e.getSource() && surnamesTextField.getText().equals(PlaceHoldersTitles[2]))
			surnamesTextField.setText(PlaceHoldersTitles[0]);

		// DIRECCI�N
		if (addressTextField == e.getSource() && addressTextField.getText().equals(PlaceHoldersTitles[3]))
			addressTextField.setText(PlaceHoldersTitles[0]);

		// NOTAS
		if (notesTextArea == e.getSource() && notesTextArea.getText().equals(PlaceHoldersTitles[4]))
			notesTextArea.setText(PlaceHoldersTitles[0]);

		// TEL�FONO
		if (telephoneTextField == e.getSource() && telephoneTextField.getText().equals(PlaceHoldersTitles[5]))
			telephoneTextField.setText(PlaceHoldersTitles[0]);

		// CORREO
		if (mailTextField == e.getSource() && mailTextField.getText().equals(PlaceHoldersTitles[6]))
			mailTextField.setText(PlaceHoldersTitles[0]);
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		// NOMBRE
		if (nameTextField == e.getSource() && nameTextField.getText().equals(PlaceHoldersTitles[0]))
			nameTextField.setText(PlaceHoldersTitles[1]);

		// APELLIDOS
		if (surnamesTextField == e.getSource() && surnamesTextField.getText().equals(PlaceHoldersTitles[0]))
			surnamesTextField.setText(PlaceHoldersTitles[2]);

		// DIRECCI�N
		if (addressTextField == e.getSource() && addressTextField.getText().equals(PlaceHoldersTitles[0]))
			addressTextField.setText(PlaceHoldersTitles[3]);

		// NOTAS
		if (notesTextArea == e.getSource() && notesTextArea.getText().equals(PlaceHoldersTitles[0]))
			notesTextArea.setText(PlaceHoldersTitles[4]);

		// TEL�FONO
		if (telephoneTextField == e.getSource() && telephoneTextField.getText().equals(PlaceHoldersTitles[0]))
			telephoneTextField.setText(PlaceHoldersTitles[5]);

		// CORREO
		if (mailTextField == e.getSource() && mailTextField.getText().equals(PlaceHoldersTitles[0]))
			mailTextField.setText(PlaceHoldersTitles[6]);
	}
	
	// GETTERS
	/**
	 * @return the cTypeComboBox
	 */
	public JComboBox<String> getContactType() {
		return cTypeComboBox;
	}
	
	/**
	 * @return the nameTextField
	 */
	public JTextField getNameTextField() {
		return nameTextField;
	}
	
	/**
	 * @return addressTextField
	 */
	public JTextField getAddressTextField() {
		return addressTextField;
	}
	
	/**
	 * @return notesTextArea
	 */
	public JTextArea getNotesTextField() {
		return notesTextArea;
	}
	
	/**
	 * @return surnamesTextField
	 */
	public JTextField getSurnamesTextField() {
		return surnamesTextField;
	}
	
	/**
	 * @return the birthDateText
	 */
	public String getBirthDateText() {
		try {
			String date = birthDateChooser.getDate().toString();
			return date.substring(0, 10) + date.substring(date.indexOf(" ", 20));
		} catch (NullPointerException npE) {
			return "";
		}
	}
	
	/**
	 * @return the genderComboBox
	 */
	public JComboBox<String> getGenderComboBox() {
		return genderComboBox;
	}
	
	/**
	 * @return the hobbyTable
	 */
	public HobbyTable getHobbyTable() {
		return hTable;
	}
	
	/**
	 * @return the telephoneTable
	 */
	public TelephoneTable getTelephoneTable() {
		return tTable;
	}
	
	/**
	 * @return the mailTable
	 */
	public MailTable getMailTable() {
		return mTable;
	}

	//REVISAR
//	public int getDefaultCloseOperation() {
//		frame.setVisible(true);
//		dispose();
//		return 0;
//	}
	
}
