package com.quathar.app.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import com.quathar.app.dao.DAO;
import com.quathar.app.dao.Menu;

import com.quathar.app.database.Database;

import com.quathar.app.gui.components.RoundJTextField;
import com.quathar.app.gui.games.TresEnRaya;
import com.quathar.app.gui.components.ChangerComboBox;
import com.quathar.app.gui.tables.ContactHobbyTable;
import com.quathar.app.gui.tables.ContactTable;
import com.quathar.app.gui.tables.HobbyTable;
import com.quathar.app.gui.tables.MailContactTable;
import com.quathar.app.gui.tables.TelephoneContactTable;
import com.quathar.app.gui.themes.Themes;

import com.quathar.app.io.MSG;

import com.quathar.app.Agenda_Ejecutable;

/**
 * <b>GUI</b>.<br><br>
 * 
 * Interfaz gr�fica de usuario (ventana principal).<br>
 * Clase utilizada para que el usuario interact�e gr�ficamente con la agenda.<br><br>
 * 
 * GUI = Graphic User Interface.
 *
 * @since 2022-04-07
 * @see ContactTable
 * @see HobbyTable
 * @see ContactHobbyTable
 * @author Q
 */
public class GUI extends JFrame implements ActionListener, DocumentListener, FocusListener { // CLASE FINALIZADA

	// CONSTANTES
	private static final long serialVersionUID = 1L;
	/**
	 * T�tulo del marco.
	 */
	private static final String FrameTitle = "Agenda de Contactos";
	/**
	 * Tama�o de la pantalla.
	 */
	public static final Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * T�tulo de los botones del men� (btnMenu).
	 */
	private final String[] MenuButtonTitle = {"\uE125", "\uE2B1", "\uE115"};
	/**
	 * T�tulo de las vistas del CardLayout (cl).
	 */
	private final String[] VistasTitle = {"0", "1", "2"};
	/**
	 * T�tulo de los items del desplegable tipoContacto.
	 */
	private final String[] TipoContactoTitle = {"TODOS", "PERSONAS", "EMPRESAS", "MASCOTAS"};
	/**
	 * T�tulo de los botones del CRUD (btnCRUD).
	 */
	private final String[] CRUDTitle = {"ELIMINAR", "CONSULTAR", "A�ADIR"};
	/**
	 * Iconos de de estrella.
	 */
	private final String StarSymbols = "\uE113 \uE113 \uE113 \uE113 \uE113";
	/**
	 * T�tulo de los botones del CD (btnCD).
	 */
	private final String[] CDTitle = {"\uE109", "\uE108"};
	/**
	 * Texto por defecto del buscador de la vista Contactos.
	 */
	private final String ContactDefaultText = "Busca un contacto...";
	/**
	 * Texto por defecto del buscador de la vista Aficiones.
	 */
	private final String HobbyDefaultText = "Busca una afici�n...";
	/**
	 * Texto por defecto del txtCD de la vista Aficiones.
	 */
	private final String HobbyTxtDefaultText = "A�ade una afici�n...";
	
	// CAMPOS
	// <<ZONA DE DISE�O>>
	/**
	 * ContentPane del JFrame.
	 */
	private JPanel contentPane;
	
	// -MEN� Y VISTAS
	/**
	 * Botones del men�.
	 */
	private JRadioButton[] btnMenu = new JRadioButton[3];
	/**
	 * Panel contenedor principal de las vistas.
	 */
	private JPanel views;
	
	// --VISTA CONTACTOS
	/**
	 * Componente JTextField del buscador de la vista Contactos.
	 */
	private JTextField txtBuscadorC;
	/**
	 * Componente JComboBox<String> para seleccionar el tipo de contacto de la vista Contactos.
	 */
	private JComboBox<String> contactTypeComboBox;
	/**
	 * Componente ContactTable de la vista Contactos.
	 */
	private ContactTable cTable;
	/**
	 * Botones CRUD (Create, Read, Update, Delete) de la vista Contactos.
	 */
	private JButton[] btnCRUD = new JButton[3];
	
	// --VISTA AFICIONES
	/**
	 * Componente JTextField del buscador de la vista Aficiones.
	 */
	private JTextField txtBuscadorA;
	/**
	 * Componente HobbyTable de la vista Aficiones.
	 */
	private HobbyTable hTable;
	/**
	 * Componente ContactHobbyTable de la vista Aficiones.
	 */
	private ContactHobbyTable chTable;
	/**
	 * Componente JTextField de la vista Aficiones.
	 */
	private JTextField hobbiesTextField;
	/**
	 * Botones CD (Create, Delete) de la vista Aficiones.
	 */
	private JButton[] btnCD = new JButton[2];
	
	// --VISTA SETTINGS
	/**
	 * Icono del bot�n del tema en la vista Configuaci�n.
	 */
	private String btnThemeTitle;
	/**
	 * Bot�n para cambiar entre temas claros y oscuros.
	 */
	private JButton themesButton;
	/**
	 * Componente JComboBox<String> para seleccionar el Tema de la vista Configuraci�n.
	 */
	private ChangerComboBox themesComboBox;
	/**
	 * Bot�n para acceder a la consola de la vista Configuraci�n.
	 */
	private JButton consoleButton;
	/**
	 * Bot�n para acceder a juegos de la vista Configuraci�n.
	 */
	private JButton gamesButton;
	/**
	 * Bot�n para formatear la agenda de la vista Configuraci�n.
	 */
	private JButton formatButton;
	/**
	 * Componente JTable para visualizar los contactos con sus tel�fonos.
	 */
	private TelephoneContactTable tcTable;
	/**
	 * Componente JTable para visualizar los contactos con sus correos.
	 */
	private MailContactTable mcTable;

	// -LAYOUTS
	/**
	 * Card Layout.
	 */
	private CardLayout cl;
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
	 * Data Access Object.
	 */
	private DAO dao;
	/**
	 * �ndice del tema de la agenda.
	 */
	private int themeIndex;
	
	// CONSTRUCTORES
	/**
	 * Constructor.
	 */
	public GUI() {
		super(FrameTitle);
		dao = new DAO();
		setTheme(0, Themes.Light);
		initComponents();
	}
	
	/**
	 * Constructor.
	 * 
	 * @param themeIndex the new theme's index
	 * @param themeType the new theme's type
	 */
	public GUI(int themeIndex, String themeType) {
		super(FrameTitle);
		dao = new DAO();
		setTheme(themeIndex, themeType);
		initComponents();
	}
	
	/**
	 * Selecciona el icono del bot�n de temas y selecciona el tema actual.
	 * 
	 * @param themeIndex the new theme's index
	 * @param themeType the new theme's type
	 */
	private void setTheme(int themeIndex, String themeType) {
		this.themeIndex = themeIndex;
		if (themeType.equals(Themes.Light))
			btnThemeTitle = "\uE28C";
		else
			btnThemeTitle = "\uE284";
	}
	
	// M�TODOS
	/**
	 * Configura el columnWeights y el rowWeights del GridBagLayout a 0.
	 * 
	 * @param gbl the grid bag layout
	 * @param columns the layout's number of columns
	 * @param rows the layout's number of rows
	 */
	private static void setWeigthsToZero(GridBagLayout gbl, int columns, int rows) {
		gbl.columnWeights = new double[columns];
		gbl.rowWeights = new double[rows];
		
		for (int i = 0; i < columns; i++)
			gbl.columnWeights[i] = 0;
		for (int i = 0; i < rows; i++)
			gbl.rowWeights[i] = 0;
	}
	
	/**
	 * Crea un nuevo GridBagLayout con el n�mero columnas y filas pasadas por par�metro.
	 * 
	 * @param columns the layout's number of columns
	 * @param rows the layout's number of rows
	 * @param columnsToOne the columns that will have weights 1
	 * @param rowsToOne the rows that will have weights 1
	 * 
	 * @return gbc - the grid bag layout
	 */
	static GridBagLayout createGridBagLayout(int columns, int rows, int[] columnsToOne, int[] rowsToOne) {
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[columns];
		gbl.rowHeights = new int[rows];
		setWeigthsToZero(gbl, columns, rows);
		
		if (columnsToOne != null)
			for (int i = 0; i < columnsToOne.length; i++)
				gbl.columnWeights[columnsToOne[i]] = 1;
		if (rowsToOne != null)
			for (int i = 0; i < rowsToOne.length; i++)
				gbl.rowWeights[rowsToOne[i]] = 1;
		
		return gbl;
	}
	
	/**
	 * Crea un nuevo GridBagLayout con el n�mero columnas y filas pasadas por par�metro.<br>
	 * El columnWeights y el rowWeights de todas las columnas y filas es 1.
	 * 
	 * @param columns the layout's number of columns
	 * @param rows the layout's number of rows
	 * 
	 * @return gbc - the grid bag layout
	 */
	static GridBagLayout createGridBagLayoutFull(int columns, int rows) {
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[columns];
		gbl.columnWeights = new double[columns];
		gbl.rowHeights = new int[rows];
		gbl.rowWeights = new double[rows];
		
		for (int i = 0; i < gbl.columnWidths.length; i++)
			gbl.columnWeights[i] = 1;
		for (int j = 0; j < gbl.rowHeights.length; j++)
			gbl.rowWeights[j] = 1;
		
		return gbl;
	}
	
	/**
	 * Crea unas nuevas GridBagConstraints para un componente ubicandolo en la posici�n pasada por par�metro.
	 * 
	 * @param x the horizontal position
	 * @param y the vertical position
	 * @return gbc - the grid bag constraints
	 */
	static GridBagConstraints createGridBagConstraints(int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		
		return gbc;
	}
	
	// <<ZONA DE DISE�O>>
	/**
	 * Dise�a y crea los componentes de la GUI.
	 */
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(	(int) (ScreenSize.width * 0.25),	// X position
					(int) (ScreenSize.height * 0.22),	// Y position
					(int) (ScreenSize.width * 0.5), 	// Width
					(int) (ScreenSize.height * 0.55));	// Height
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		gbl = createGridBagLayoutFull(2, 1);
		gbl.columnWidths[1] = (int) (ScreenSize.width * 0.35);
		contentPane.setLayout(gbl);
		
		createMenu();
		createViews();
	}

	/**
	 * Dise�a y crea el men� de la GUI.
	 */
	private void createMenu() {
		JPanel menu = new JPanel();
		gbc = createGridBagConstraints(0, 0);
		gbc.fill = GridBagConstraints.BOTH;
		contentPane.add(menu, gbc);
		gbl = createGridBagLayout(1, 3, null, null);
		menu.setLayout(gbl);

		ButtonGroup menuGroup = new ButtonGroup();
		for (int i = 0; i < btnMenu.length; i++) {
			btnMenu[i] = new JRadioButton(MenuButtonTitle[i]);
			btnMenu[i].setFont(new Font("Segoe UI Symbol", Font.PLAIN, (int) (ScreenSize.width * 0.03125)));
			btnMenu[i].addActionListener(this);
			menuGroup.add(btnMenu[i]);
			gbc = createGridBagConstraints(0, i);
			if (i != 0)
				gbc.insets = new Insets(30, 0, 0, 0);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			menu.add(btnMenu[i], gbc);
		}
	}
	
	/**
	 * Dise�a y crea las <b>vistas</b> de la GUI.
	 */
	private void createViews() {
		views = new JPanel();
		gbc = createGridBagConstraints(1, 0);
		gbc.fill = GridBagConstraints.BOTH;
		contentPane.add(views, gbc);
		cl = new CardLayout();
		views.setLayout(cl);
		
		createContactsView();
		createHobbiesView();
		createSettingsView();
	}

	/**
	 * Dise�a y crea la <b>vista Contactos</b>.
	 */
	private void createContactsView() {
		JPanel contactsView = new JPanel();
		contactsView.setBorder(new EmptyBorder(5, 5, 0, 5));
		views.add(contactsView, VistasTitle[0]);
		contactsView.setLayout(new BorderLayout(0, 5));
		
		JPanel buscador = new JPanel();
		contactsView.add(buscador, BorderLayout.NORTH);
		gbl = createGridBagLayout(3, 1, new int[] {1}, null);
		buscador.setLayout(gbl);
		
		// NORTH - BUSCADOR
		JLabel lblBuscador = new JLabel("\uE000");
		lblBuscador.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20));
		gbc = createGridBagConstraints(0, 0);
		buscador.add(lblBuscador, gbc);
		
		txtBuscadorC = new RoundJTextField(ContactDefaultText);
		txtBuscadorC.addFocusListener(this);
		txtBuscadorC.getDocument().addDocumentListener(this);
		gbc = createGridBagConstraints(1, 0);
		gbc.insets = new Insets(0, 5, 0, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		buscador.add(txtBuscadorC, gbc);
		
		contactTypeComboBox = new JComboBox<String>(TipoContactoTitle);
		contactTypeComboBox.setPreferredSize(new Dimension((int) (ScreenSize.width * 0.0521), 30));
		contactTypeComboBox.addActionListener(this);
		gbc = createGridBagConstraints(2, 0);
		gbc.anchor = GridBagConstraints.EAST;
		buscador.add(contactTypeComboBox, gbc);
		
		// CENTER - TABLA
		JPanel table = new JPanel();
		contactsView.add(table, BorderLayout.CENTER);
		table.setLayout(createGridBagLayoutFull(1, 1));
		
		cTable = new ContactTable(dao);
		JScrollPane cScroll = new JScrollPane(cTable);
		gbc = createGridBagConstraints(0, 0);
		gbc.insets = new Insets(0, 0, 0, 1);
		gbc.fill = GridBagConstraints.BOTH;
		table.add(cScroll, gbc);
	
		// SOUTH - BOTONES CRUD
		JPanel crud = new JPanel();
		contactsView.add(crud, BorderLayout.SOUTH);
		crud.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		for (int i = 0; i < btnCRUD.length; i++) {
			btnCRUD[i] = new JButton(CRUDTitle[i]);
			btnCRUD[i].setPreferredSize(new Dimension((int) (ScreenSize.width * 0.078125), 30));
			btnCRUD[i].addActionListener(this);
			crud.add(btnCRUD[i]);
		}
	}

	/**
	 * Dise�a y crea la <b>vista Aficiones</b>.
	 */
	private void createHobbiesView() {
		JPanel hobbiesView = new JPanel();
		hobbiesView.setBorder(new EmptyBorder(5, 5, 5, 5));
		views.add(hobbiesView, VistasTitle[1]);
		hobbiesView.setLayout(new BorderLayout(3, 3));
		
		// NORTH - BUSCADOR
		JPanel buscador = new JPanel();
		hobbiesView.add(buscador, BorderLayout.NORTH);
		gbl = createGridBagLayout(3, 1, new int[] {1}, null);
		buscador.setLayout(gbl);
		
		for (int i = 0; i < 3; i = i + 2) {
			JLabel lblStars = new JLabel(StarSymbols);
			lblStars.setFont(new Font("Segoi UI Symbol", Font.PLAIN, 15));
			gbc = createGridBagConstraints(i, 0);
			buscador.add(lblStars, gbc);
		}
		
		txtBuscadorA = new RoundJTextField(HobbyDefaultText);
		txtBuscadorA.addFocusListener(this);
		txtBuscadorA.getDocument().addDocumentListener(this);
		gbc = createGridBagConstraints(1, 0);
		gbc.insets = new Insets(0, 5, 0, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		buscador.add(txtBuscadorA, gbc);

		// CENTER - TABLA AFICIONES Y CONTACTOS_AFICIONES
		JPanel tables = new JPanel();
		hobbiesView.add(tables, BorderLayout.CENTER);
		gbl = createGridBagLayout(2, 3, new int[] {0, 1}, new int[] {0});
		gbl.columnWidths[1] = (int) (ScreenSize.width * 0.1);
		tables.setLayout(gbl);
		
		// --TABLA AFICIONES
		hTable = new HobbyTable(this, dao);
		JScrollPane hScroll = new JScrollPane(hTable);
		gbc = createGridBagConstraints(0, 0);
		gbc.fill = GridBagConstraints.BOTH;
		tables.add(hScroll, gbc);
		
		hobbiesTextField = new JTextField(HobbyTxtDefaultText);
		hobbiesTextField.addFocusListener(this);
		hobbiesTextField.addActionListener(this);
		gbc = createGridBagConstraints(0, 1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		tables.add(hobbiesTextField, gbc);
		
		JPanel buttons = new JPanel();
		gbc = createGridBagConstraints(0, 2);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		tables.add(buttons, gbc);
		gbl = createGridBagLayoutFull(2, 1);
		buttons.setLayout(gbl);
		
		for (int i = 0; i < btnCD.length; i++) {
			btnCD[i] = new JButton(CDTitle[i]);
			btnCD[i].addActionListener(this);
			gbc = createGridBagConstraints(i, 0);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			buttons.add(btnCD[i], gbc);
		}
		
		// --TABLA CONTACTOS_AFICIONES
		chTable = new ContactHobbyTable(dao);
		JScrollPane sContactHobbyPane = new JScrollPane(chTable);
		gbc = createGridBagConstraints(1, 0);
		gbc.gridheight = 3;
		gbc.insets = new Insets(0, 5, 0, 0);
		gbc.fill = GridBagConstraints.BOTH;
		tables.add(sContactHobbyPane, gbc);
	}

	/**
	 * Dise�a y crea la <b>vista Configuraci�n</b>.
	 */
	private void createSettingsView() {
		JPanel vistaConfiguracion = new JPanel();
		vistaConfiguracion.setBorder(new EmptyBorder(5, 5, 5, 5));
		views.add(vistaConfiguracion, VistasTitle[2]);
		vistaConfiguracion.setLayout(new BorderLayout(2, 2));
		
		// NORTH - TEMAS, JUEGOS, CONSOLA, FORMATEAR
		JPanel north = new JPanel();
		vistaConfiguracion.add(north, BorderLayout.NORTH);
		gbl = createGridBagLayout(5, 1, new int[] {2}, null);
		north.setLayout(gbl);

		themesButton = new JButton(btnThemeTitle);
		themesButton.addActionListener(this);
		gbc = createGridBagConstraints(0, 0);
		north.add(themesButton, gbc);
		
		String[] items;
		if (btnThemeTitle.equals("\uE28C"))
			items = Themes.LightThemesNames;
		else
			items = Themes.DarkThemesNames;
		themesComboBox = new ChangerComboBox(items);
		themesComboBox.setSelectedIndex(themeIndex);
		themesComboBox.addActionListener(this);
		gbc = createGridBagConstraints(1, 0);
		north.add(themesComboBox, gbc);

		int fontSize = 18;
		gamesButton = new JButton("\uE2D2");
		gamesButton.setFont(new Font("Segoe UI Symbol", Font.PLAIN, fontSize));
		gamesButton.addActionListener(this);
		gbc = createGridBagConstraints(2, 0);
		gbc.anchor = GridBagConstraints.EAST;
		north.add(gamesButton, gbc);
		
		consoleButton = new JButton("\uE2F3");
		consoleButton.setFont(new Font("Segoe UI Symbol", Font.PLAIN, fontSize));
		consoleButton.addActionListener(this);
		gbc = createGridBagConstraints(3, 0);
		gbc.anchor = GridBagConstraints.WEST;
		north.add(consoleButton, gbc);
		
		formatButton = new JButton("\uE283");
		formatButton.setFont(new Font("Segoe UI Symbol", Font.PLAIN, fontSize));
		formatButton.addActionListener(this);
		gbc = createGridBagConstraints(4, 0);
		north.add(formatButton, gbc);
		
		// CENTER - TABLAS TEL�FONOS Y CORREOS
		JPanel center = new JPanel();
		vistaConfiguracion.add(center, BorderLayout.CENTER);
		gbl = createGridBagLayoutFull(2, 1);
		center.setLayout(gbl);

		tcTable = new TelephoneContactTable(dao);
		JScrollPane tScroll = new JScrollPane(tcTable);
		gbc = createGridBagConstraints(0, 0);
		gbc.fill = GridBagConstraints.BOTH;
		center.add(tScroll, gbc);
		
		mcTable = new MailContactTable(dao);
		JScrollPane mScroll = new JScrollPane(mcTable);
		gbc = createGridBagConstraints(1, 0);
		gbc.fill = GridBagConstraints.BOTH;
		center.add(mScroll, gbc);
	}
	
	// <<ZONA DE LISTENERS>>
	// ACTION LISTENER
	@Override
	public void actionPerformed(ActionEvent e) {
		Object event = e.getSource();

		if (!listenerMenu(event) && !contactsViewListener(event) && !hobbiesViewListener(event))
			settingsViewListener(event);
	}

	/**
	 * Define acciones para los botones del men�.
	 * 
	 * @param event the event source
	 * @return true/false - if the event source is/isn't on this method
	 */
	private boolean listenerMenu(Object event) {
		for (int i = 0; i < btnMenu.length; i++)
			if (btnMenu[i] == event) {
				cl.show(views, VistasTitle[i]);
				return true;
			}
		return false;
	}
	
	// --LISTENER VISTA CONTACTOS
	/**
	 * Define acciones para los componentes de la <b>vista Contactos</b>.
	 * 
	 * @param event the event source
	 * @return true/false - if the event source is/isn't on this method
	 */
	private boolean contactsViewListener(Object event) {
		if (listenerContactType(event))
			return true;
		if (listenerCRUD(event))
			return true;
		return false;
	}
	
	/**
	 * Define acciones para el desplegable contactTypeComboBox.
	 * 
	 * @param event the event source
	 * @return true/false - if the event source is/isn't on this method
	 */
	private boolean listenerContactType(Object event) {
		if (contactTypeComboBox == event) {
			String selected = (String) contactTypeComboBox.getSelectedItem();
			cTable.update(selected);
			return true;
		}
		return false;
	}
	
	/**
	 * Revisa el bot�n CRUD de la vista contactos que ha sido pulsado.
	 * 
	 * @param event the event source
	 * @return true/false - if the event source is/isn't on this method
	 */
	private boolean listenerCRUD(Object event) {
		for (int i = 0; i < btnCRUD.length; i++)
			if (btnCRUD[i] == event) {
				crudAction(i);
				return true;
			}
		return false;
	}
	
	/**
	 * Define las acciones para cada boton del CRUD de la <b>vista Contactos<b>.
	 * 
	 * @param i the index of the button that was pressed
	 */
	private void crudAction(int i) {
		int selectedRowCount = cTable.getSelectedRowCount();
		switch (i) {
			case 0:
				if (selectedRowCount > 0) {
					String msg = "�Borrar estos " + selectedRowCount + " contactos?";
					msg = (selectedRowCount == 1 ? "�Borrar este contacto?" : msg);
					if (MSG.questionMessage(msg) == 0) {
						cTable.deleteRows();
						updateTables();
					}
				}
				break;
			case 1:
				if (selectedRowCount > 0) {
					if (selectedRowCount == 1) {
						int id = (int) cTable.getModel().getValueAt(cTable.getSelectedRow(), 0);
						new ContactInfo(this, dao, id).setVisible(true);
					} else
						MSG.warningMessage("No se puede consultar m�s de 1 contacto a la vez");
				}
				break;
			case 2:
				setVisible(false);
				new AddContact(this, dao).setVisible(true);
				break;
		}
	}
	
	// --LISTENER VISTA AFICIONES
	/**
	 * Define las acciones para los componentes de la <b>vista Aficiones<b>.
	 * 
	 * @param event the event source
	 * @return true/false - if the event source is/isn't on this method
	 */
	private boolean hobbiesViewListener(Object event) {
		return listenerCD(event);
		
	}
	
	/**
	 * Define acciones para los botones del CD (Create, Delete) de la vista Aficiones.
	 * 
	 * @param event the event source
	 * @return true/false - if the event source is/isn't on this method
	 */
	private boolean listenerCD(Object event) {
		if (btnCD[0] == event || hobbiesTextField == event) {
			String text = hobbiesTextField.getText();
			if (!text.equals("") && !text.equals(HobbyTxtDefaultText)) {
				hTable.insertHobby(text);
				hobbiesTextField.setText(HobbyTxtDefaultText);
				return true;
			}
		} else if (btnCD[1] == event) {
			int rowCount = hTable.getSelectedRowCount();
			if (rowCount > 0) {
				String msg = (rowCount == 1 ?
						"�Est� seguro de que quiere borrar esta afici�n?": 
						"�Est� seguro de que quiere borrar " + rowCount + " aficiones?");
				if (MSG.defaultOptionMessage(msg) == 0)
					hTable.deleteSelectedRows();
				return true;
			} else if (!hobbiesTextField.getText().equals(HobbyTxtDefaultText)) {
				hobbiesTextField.setText(HobbyTxtDefaultText);
				return true;
			}
		}
		return false;
	}
	
	// --LISTENER VISTA CONFIGURACI�N
	/**
	 * Define acciones para los componentes de la <b>vista Configuraci�n</b>.
	 * 
	 * @param event the event source
	 */
	private void settingsViewListener(Object event) {
		if (!listenerTemas(event) && !listenerJuegos(event) && !listenerConsola(event))
			listenerFormatear(event);
	}
	
	/**
	 * Comprueba el nombre del tema para saber si es de tipo claro u oscuro.
	 * 
	 * @param themeName the themes's name
	 * @return the theme type (light or dark)
	 */
	private String checkThemeType(String themeName) {
		for (String name : Themes.LightThemesNames)
			if (name.equals(themeName))
				return Themes.Light;
		return Themes.Dark;
	}
	
	/**
	 * Define la acci�n para el desplegable TEMAS.
	 * 
	 * @param event  the event source
	 * @return true/false - if the event source is/isn't on this method
	 */
	private boolean listenerTemas(Object event) {
		if (themesButton == event) {
			String icon = themesButton.getText();
			if (icon.equals("\uE28C")) {
				themesButton.setText("\uE284");
				themesComboBox.changeList(Themes.DarkThemesNames);
				return true;
			} else if (icon.equals("\uE284")) {
				themesButton.setText("\uE28C");
				themesComboBox.changeList(Themes.LightThemesNames);
				return true;
			}
		}
		
		if (themesComboBox == event) {
			String themeType = checkThemeType((String) themesComboBox.getSelectedItem());
			dispose();
			Agenda_Ejecutable.changeTheme(themesComboBox.getSelectedIndex(), themeType);
			return true;
		}
		return false;
	}
	
	/**
	 * Define la acci�n para el bot�n Consola.
	 * 
	 * @param event the event source
	 * @return true/false - if the event source is/isn't on this method
	 */
	private boolean listenerConsola(Object event) {
		if (consoleButton == event) {
			if (MSG.defaultOptionMessage("�Quiere acceder a la consola?") == 0) {
				dispose();
				dao.close();
				new Menu(new Database());
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Define la acci�n para el bot�n Juegos.
	 * 
	 * @param event the event source
	 * @return true/false - if the event source is/isn't on this method
	 */
	private boolean listenerJuegos(Object event) {
		if (gamesButton == event) {
			new TresEnRaya("X", "O").setVisible(true);
			return true;
		}
		return false;
	}
	
	/**
	 * Define la acci�n para el bot�n Formatear.
	 * 
	 * @param event the event source
	 * @return true/false - if the event source is/isn't on this method
	 */
	private boolean listenerFormatear(Object event) {
		if (formatButton == event && MSG.defaultOptionMessage("�Est� seguro de que quiere FORMATEAR la agenda?\n"
				+ "ESTA ACCI�N NO SE PUEDE REVERTIR") == 0) {
				dispose();
				Agenda_Ejecutable.format();
				return true;
		}
		return false;
	}
	
	// DOCUMENT LISTENER
	@Override
	public void insertUpdate(DocumentEvent e) {
		Document event = e.getDocument();
		
		// BUSCADOR CONTACTOS
		if (txtBuscadorC.getDocument() == event) {
			String contactWord = txtBuscadorC.getText();
			if (!contactWord.equals(ContactDefaultText)) {
				String contactType = (String) contactTypeComboBox.getSelectedItem();
				cTable.update(contactType, contactWord);
			}
		}
		
		// BUSCADOR AFICIONES
		if (txtBuscadorA.getDocument() == event) {
			String hobbyWord = txtBuscadorA.getText();
			if (!hobbyWord.equals(HobbyDefaultText)) {
				hTable.update(hobbyWord);
				chTable.update(hobbyWord);
			}
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		Document event = e.getDocument();
		
		// BUSCADOR CONTACTOS
		if (txtBuscadorC.getDocument() == event) {
			String contactWord = txtBuscadorC.getText();
			if (!contactWord.equals(ContactDefaultText)) {
				String contactType = (String) contactTypeComboBox.getSelectedItem();
				cTable.update(contactType, contactWord);
			}
		}
		
		// BUSCADOR AFICIONES
		if (txtBuscadorA.getDocument() == event) {
			String hobbyWord = txtBuscadorA.getText();
			if (!hobbyWord.equals(HobbyDefaultText)) {
				hTable.update(hobbyWord);
				chTable.update(hobbyWord);
			}
		}
	}
	
	@Override
	public void changedUpdate(DocumentEvent e) {
		MSG.questionMessage("changeUpdate(DocumentEvent e) apareci� por fin");
	}

	// FOCUS LISTENER
	@Override
	public void focusGained(FocusEvent e) {
		Object event = e.getSource();
		
		// BUSCADOR CONTACTOS
		if (txtBuscadorC == event && txtBuscadorC.getText().equals(ContactDefaultText))
			txtBuscadorC.setText("");
		
		// BUSCADOR AFICIONES
		if (txtBuscadorA == event && txtBuscadorA.getText().equals(HobbyDefaultText))
			txtBuscadorA.setText("");
		
		// CD AFICIONES
		if (hobbiesTextField == event && hobbiesTextField.getText().equals(HobbyTxtDefaultText))
			hobbiesTextField.setText("");
	}

	@Override
	public void focusLost(FocusEvent e) {
		Object event = e.getSource();
		
		// BUSCADOR CONTACTOS
		if (txtBuscadorC == event && txtBuscadorC.getText().equals(""))
			txtBuscadorC.setText(ContactDefaultText);
		
		// BUSCADOR AFICIONES
		if (txtBuscadorA == event && txtBuscadorA.getText().equals(""))
			txtBuscadorA.setText(HobbyDefaultText);
		
		// CD AFICIONES
		if (hobbiesTextField == event && hobbiesTextField.getText().equals(""))
			hobbiesTextField.setText(HobbyTxtDefaultText);
	}
	
	// M�TODOS
	/**
	 * Actualiza las vistas de todas las tablas.
	 */
	public void updateTables() {
		String selected = (String) contactTypeComboBox.getSelectedItem();
		cTable.update(selected);
		chTable.update();
		tcTable.update();
		mcTable.update();
	}
	
	// GETTERS
	/**
	 * @return the views panel
	 */
	public JPanel getViews() {
		return views;
	}
	
	/**
	 * @return the card layout
	 */
	public CardLayout getCardLayout() {
		return cl;
	}
	
	/**
	 * @return the contact table
	 */
	public ContactTable getContactTable() {
		return cTable;
	}
	
	/**
	 * @return the hobby table
	 */
	public HobbyTable getHobbyTable() {
		return hTable;
	}
	
	/**
	 * @return the contact hobby table
	 */
	public ContactHobbyTable getContactHobbyTable() {
		return chTable;
	}
	
	/**
	 * @return the telephone contact table
	 */
	public TelephoneContactTable getTelephoneContactTable() {
		return tcTable;
	}
	
	/**
	 * @return the mail contact table
	 */
	public MailContactTable getMailContactTable() {
		return mcTable;
	}
	
}
