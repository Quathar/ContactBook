package gui;

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

import classes.Contact;
import classes.Telephone;

import dao.DAO;

import gui.tables.HobbyTable;
import gui.tables.MailTable;
import gui.tables.TelephoneTable;

import io.MSG;
import io.RegexFilter;

/**
 * AddContact.<br><br>
 * 
 * Clase utilizada para añadir contactos gráficamente.
 *
 * @since 2022-04-13
 * @see GUI
 * @see DAO
 * @see HobbyTable
 * @see TelephoneTable
 * @see MailTable
 * @author Q
 */
public class AddContact extends JFrame implements ActionListener, FocusListener { // CLASE FINALIZADA

	// CONSTANTES	
	private static final long serialVersionUID = 1L;
	/**
	 * Título de los items del desplegable contactType.
	 */
	private final String[] ContactTypeTitle = {"PERSONA", "EMPRESA", "MASCOTA"};
	/**
	 * Placeholders de los componentes JTextField.
	 */
	public static final String[] PlaceHoldersTitles = {"", "Nombre...", "Apellidos...", "Dirección...", "Notas...", "Teléfono...", "Correo..."};
	/**
	 * Título de los botones AD (Add - Delete).
	 */
	private final String[] ADTitle = {"\uE109", "\uE108"};
	/**
	 * Título de los botones del VA (btnVA).
	 */
	private final String[] VATitle = {"VOLVER", "AÑADIR"};
	
	// CAMPOS
	// <<ZONA DE DISEÑO>>
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
	 * Componente JTextField para la dirección.
	 */
	private JTextField addressTextField;
	/**
	 * Componente JDateChooser para la fecha de nacimiento.
	 */
	private JDateChooser birthDateChooser;
	/**
	 * Componente JComboBox para seleccionar el género.
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
	 * Botones para añadir (add) y borrar (delete) aficiones.
	 */
	private JButton[] btnADaficiones = new JButton[2];
 	
	// --CAMPOS TELÉFONOS Y CORREOS
	/**
	 * Tabla de los teléfonos.
	 */
	private TelephoneTable tTable;
	/**
	 * Componente JTextArea para los teléfonos.
	 */
	private JTextField telephoneTextField;
	/**
	 * Componente JComboBox para seleccionar el tipo de teléfono.
	 */
	private JComboBox<String> tType;
	/**
	 * Botones para añadir (add) y borrar (delete) telefonos.
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
	 * Botones para añadir (add) y borrar (delete) correos.
	 */
	private JButton[] btnADcorreos = new JButton[2];
	
	// -SOUTH
	/**
	 * Botones Volver y Añadir.
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
	private GUI gui;
	/**
	 * Data Acces Object.
	 */
	private DAO dao;
	
	// CONSTRUCTOR
	/**
	 * Constructor.
	 * 
	 * @param gui main window
	 */
	public AddContact(GUI gui, DAO dao) {
		super("Añadir un nuevo contacto");
		this.gui = gui;
		this.dao = dao;
		initComponents();
	}
	
	// ZONA DE DISEÑO
	/**
	 * Diseña y crea los componentes del frame.
	 */
	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(	(int) (GUI.ScreenSize.width * 0.3),
					(int) (GUI.ScreenSize.height * 0.25),
					(int) (GUI.ScreenSize.width * 0.4),
					(int) (GUI.ScreenSize.height * 0.5));
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		gbl = GUI.createGridBagLayout(1, 3, new int[] {0}, new int[] {1});
		contentPane.setLayout(gbl);
		
		createNORTH();
		createCENTER();
		createSOUTH();
	}
	
	/**
	 *  Diseña y crea los componentes la zona NORTH.
	 */
	private void createNORTH() {
		cTypeComboBox = new JComboBox<String>(ContactTypeTitle);
		cTypeComboBox.addActionListener(this);
		gbc = GUI.createGridBagConstraints(0, 0);
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(0, 0, 3, 5);
		contentPane.add(cTypeComboBox, gbc);
	}
	
	/**
	 * Diseña y crea los componentes la zona CENTER.
	 */
	private void createCENTER() {
		dataPanel = new JPanel();
		gbc = GUI.createGridBagConstraints(0, 1);
		gbc.fill = GridBagConstraints.BOTH;
		contentPane.add(dataPanel, gbc);
		dataPanel.setLayout(GUI.createGridBagLayoutFull(2, 2));
		
		createFormulario();
		createAficiones();
		createTables();
	}
	
	/**
	 * Diseña y crea los componentes del formulario.
	 */
	private void createFormulario() {
		JPanel form = new JPanel();
		gbc = GUI.createGridBagConstraints(0, 0);
		gbc.insets = new Insets(0, 5, 0, 0);
		gbc.fill = GridBagConstraints.BOTH;
		dataPanel.add(form, gbc);
		gbl = GUI.createGridBagLayout(4, 5, new int[] {1}, new int[] {4});
		form.setLayout(gbl);
		
		// NOMBRE
		nameTextField = new JTextField(PlaceHoldersTitles[1]);
		nameTextField.addFocusListener(this);
		gbc = GUI.createGridBagConstraints(0, 0);
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		form.add(nameTextField, gbc);
		
		// APELLIDOS
		surnamesTextField = new JTextField(PlaceHoldersTitles[2]);
		surnamesTextField.addFocusListener(this);
		gbc = GUI.createGridBagConstraints(0, 1);
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		form.add(surnamesTextField, gbc);
		
		// FECHA DE NACIMIENTO
		JLabel lblFechaNac = new JLabel("Nacimiento:");
		gbc = GUI.createGridBagConstraints(0, 2);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(0, 5, 0, 0);
		form.add(lblFechaNac, gbc);
		
		birthDateChooser = new JDateChooser();
		gbc = GUI.createGridBagConstraints(1, 2);
		gbc.insets = new Insets(0, 5, 0, 15);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		form.add(birthDateChooser, gbc);
		
		// GÉNERO
		JLabel lblGenero = new JLabel("Género:");
		gbc = GUI.createGridBagConstraints(2, 2);
		gbc.anchor = GridBagConstraints.EAST;
		form.add(lblGenero, gbc);
		
		genderComboBox = new JComboBox<String>(new String[] {"Masculino", "Femenino", "Otro"});
		gbc = GUI.createGridBagConstraints(3, 2);
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(0, 5, 0, 0);
		form.add(genderComboBox, gbc);
		
		// DIRECCIÓN
		addressTextField = new JTextField(PlaceHoldersTitles[3]);
		addressTextField.addFocusListener(this);
		gbc = GUI.createGridBagConstraints(0, 3);
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		form.add(addressTextField, gbc);
		
		// NOTAS
		notesTextArea = new JTextArea(PlaceHoldersTitles[4]);
		notesTextArea.addFocusListener(this);
		JScrollPane sNotas = new JScrollPane(notesTextArea);
		gbc = GUI.createGridBagConstraints(0, 4);
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.BOTH;
		form.add(sNotas, gbc);
	}
	
	/**
	 * Diseña y crea los componentes de la tabla aficiones.
	 */
	private void createAficiones() {
		JPanel hobbies = new JPanel();
		gbc = GUI.createGridBagConstraints(0, 1);
		gbc.insets = new Insets(5, 5, 0, 0);
		gbc.fill = GridBagConstraints.BOTH;
		dataPanel.add(hobbies, gbc);
		gbl = GUI.createGridBagLayoutFull(3, 2);
		gbl.rowWeights[1] = 0;
		hobbies.setLayout(gbl);
		
		// TABLA
		hTable = new HobbyTable(dao, false);
		JScrollPane hScroll = new JScrollPane(hTable);
		gbc = GUI.createGridBagConstraints(0, 0);
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.BOTH;
		hobbies.add(hScroll, gbc);
		
		// DEPLEGABLE
		hobbiesComboBox = new JComboBox<String>(dao.getHobbies());
		gbc = GUI.createGridBagConstraints(0, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		hobbies.add(hobbiesComboBox, gbc);
		
		// BOTONES
		for (int i = 0; i < btnADaficiones.length; i++) {
			btnADaficiones[i] = new JButton(ADTitle[i]);
			btnADaficiones[i].addActionListener(this);
			gbc = GUI.createGridBagConstraints(i + 1, 1);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			hobbies.add(btnADaficiones[i], gbc);
		}
	}
	
	/**
	 * Diseña y crea los componentes de las tablas teléfonos y aficiones.
	 */
	private void createTables() {
		JPanel tables = new JPanel();
		gbc = GUI.createGridBagConstraints(1, 0);
		gbc.gridheight = 2;
		gbc.insets = new Insets(0, 5, 0, 5);
		gbc.fill = GridBagConstraints.BOTH;
		dataPanel.add(tables, gbc);
		gbl = GUI.createGridBagLayoutFull(1, 4);
		gbl.rowWeights[1] = 0;
		gbl.rowWeights[3] = 0;
		tables.setLayout(gbl);
		
		// TELÉFONOS
		tTable = new TelephoneTable(dao);
		JScrollPane tScroll = new JScrollPane(tTable);
		gbc = GUI.createGridBagConstraints(0, 0);
		gbc.fill = GridBagConstraints.BOTH;
		tables.add(tScroll, gbc);
		
		//-INTRODUCCIÓN DE TELÉFONOS
		JPanel iTelefonos = new JPanel();
		gbc = GUI.createGridBagConstraints(0, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		tables.add(iTelefonos, gbc);
		gbl = GUI.createGridBagLayout(4, 1, new int[] {0, 2, 3}, new int[] {0});
		iTelefonos.setLayout(gbl);
		
		telephoneTextField = new JTextField(PlaceHoldersTitles[5]);
		telephoneTextField.addFocusListener(this);
		telephoneTextField.addActionListener(this);
		gbc = GUI.createGridBagConstraints(0, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		iTelefonos.add(telephoneTextField, gbc);
		
		tType = new JComboBox<String>(Telephone.Type);
		gbc = GUI.createGridBagConstraints(1, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		iTelefonos.add(tType, gbc);
		
		for (int i = 0; i < btnADtelefonos.length; i++) {
			btnADtelefonos[i] = new JButton(ADTitle[i]);
			btnADtelefonos[i].addActionListener(this);
			gbc = GUI.createGridBagConstraints(i + 2, 0);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			iTelefonos.add(btnADtelefonos[i], gbc);
		}
		
		// CORREOS¡
		mTable = new MailTable(dao);
		JScrollPane mScroll = new JScrollPane(mTable);
		gbc = GUI.createGridBagConstraints(0, 2);
		gbc.insets = new Insets(5, 0, 0, 0);
		gbc.fill = GridBagConstraints.BOTH;
		tables.add(mScroll, gbc);
		
		//-INTRODUCCIÓN DE CORREOS
		JPanel iCorreos = new JPanel();
		gbc = GUI.createGridBagConstraints(0, 3);
		gbc.fill = GridBagConstraints.BOTH;
		tables.add(iCorreos, gbc);
		gbl = GUI.createGridBagLayoutFull(5, 1);
		iCorreos.setLayout(gbl);
		
		mailTextField = new JTextField(PlaceHoldersTitles[6]);
		mailTextField.addFocusListener(this);
		mailTextField.addActionListener(this);
		gbc = GUI.createGridBagConstraints(0, 0);
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		iCorreos.add(mailTextField, gbc);
		
		for (int i = 0; i < btnADcorreos.length; i++) {
			btnADcorreos[i] = new JButton(ADTitle[i]);
			btnADcorreos[i].addActionListener(this);
			gbc = GUI.createGridBagConstraints(i + 3, 0);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			iCorreos.add(btnADcorreos[i], gbc);
		}
	}
	
	/**
	 * Diseña y crea los componentes la zona SOUTH.
	 */
	private void createSOUTH() {
		JPanel buttons = new JPanel();
		gbc = GUI.createGridBagConstraints(0, 2);
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
	 * Define acciones para los botones AD de las tablas teléfonos y correos.
	 * 
	 * @param event the event source
	 * @return true/false - if the event source is/isn't on this method
	 */
	private boolean listenerTelephonesMails(Object event) {
		// TELÉFONOS AD
		if (btnADtelefonos[0] == event || telephoneTextField == event) {
			String telephone = RegexFilter.checkTelephone(telephoneTextField.getText());
			if (telephone.equals("ERROR")) {
				MSG.warningMessage("¡FORMATO INCORRECTO!\nIntroduzca 9 números");
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
				MSG.warningMessage("¡FORMATO INCORRECTO!\nIntroduzca: (correo)@(mail).(ext)");
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
	 * Resetea los componentes de la DAO en función del tipo de contacto que se ha añadido.
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
	 * Define acciones para los botones VA (Volver, Añadir).
	 * 
	 * @param event the event source
	 * @return true/false - if the event source is/isn't on this method
	 */
	private boolean listenerVA(Object event) {
		if (btnVA[0] == event) {
			gui.setVisible(true);
			dispose();
			return true;
		} else if (btnVA[1] == event) {
			if (!nameTextField.getText().equals(PlaceHoldersTitles[1]) || nameTextField.getText().isBlank()) {
				dao.register(this);
				cleanFields();
				gui.updateTables();
			} else MSG.warningMessage("TIENE QUE AÑADIR UN NOMBRE");
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

		// DIRECCIÓN
		if (addressTextField == e.getSource() && addressTextField.getText().equals(PlaceHoldersTitles[3]))
			addressTextField.setText(PlaceHoldersTitles[0]);

		// NOTAS
		if (notesTextArea == e.getSource() && notesTextArea.getText().equals(PlaceHoldersTitles[4]))
			notesTextArea.setText(PlaceHoldersTitles[0]);

		// TELÉFONO
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

		// DIRECCIÓN
		if (addressTextField == e.getSource() && addressTextField.getText().equals(PlaceHoldersTitles[0]))
			addressTextField.setText(PlaceHoldersTitles[3]);

		// NOTAS
		if (notesTextArea == e.getSource() && notesTextArea.getText().equals(PlaceHoldersTitles[0]))
			notesTextArea.setText(PlaceHoldersTitles[4]);

		// TELÉFONO
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
