package com.quathar.contactbook.ui.frame;

import com.quathar.contactbook.io.MSG;
import com.quathar.contactbook.ui.Themes;
import com.quathar.contactbook.ui.component.RoundJTextField;
import com.quathar.contactbook.ui.table.ContactTable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.Serial;

/**
 * <h1>GUI: MainFrame</h1>
 * GUI = Graphic User Interface.
 *
 * <p>
 *     Graphical user interface (main window).<br>
 *     Class used for the user to interact graphically with the contact book.
 * </p>
 *
 * @since 2022-04-07
 * @version 2.1
 * @author Q
 */
public class MainFrame extends JFrame implements ActionListener, DocumentListener, FocusListener {

	// <<-CONSTANTS->>
	@Serial
	private static final long serialVersionUID = 1L;
	private static final String FRAME_TITLE = "Contact Book";
	public static final Dimension ScreenSize = Toolkit.getDefaultToolkit()
													  .getScreenSize();
	private static final String[] VIEWS_TITLE = { "0", "1", "2" };
	private static final String[] CONTACT_TYPE_TITLE = { "ALL", "PERSONS", "COMPANIES", "PETS" };
//	private static final String[] CRUD_TITLE = { "REMOVE", "CONSULT", "ADD" };
//	private final String StarSymbols = "\uE113 \uE113 \uE113 \uE113 \uE113";
//	private final String[] CDTitle = {"\uE109", "\uE108"};
	private static final String CONTACT_DEFAULT_TEXT = "Search for a contact...";
//	private final String HobbyDefaultText = "Busca una afici�n...";
//	private final String HobbyTxtDefaultText = "A�ade una afici�n...";

	// <<-FIELDS->>
//	// <<ZONA DE DISE�O>>
//	private JPanel contentPane;
//
//	// -MEN� Y VISTAS
//	private JRadioButton[] btnMenu = new JRadioButton[3];
	private JPanel viewsPanel;
//
//	// --VISTA CONTACTOS
//	private JTextField txtBuscadorC;
	private JComboBox<String> contactTypeComboBox;
	private ContactTable contactTable;
//	private JButton[] btnCRUD = new JButton[3];
//
//	// --VISTA AFICIONES
//	private JTextField txtBuscadorA;
//	private HobbyTable hTable;
//	private ContactHobbyTable chTable;
//	private JTextField hobbiesTextField;
//	private JButton[] btnCD = new JButton[2];
//
//	// --VISTA SETTINGS
//	private String btnThemeTitle;
//	private JButton themesButton;
//	private ChangerComboBox themesComboBox;
//	private JButton consoleButton;
//	private JButton gamesButton;
//	private JButton formatButton;
//	private TelephoneContactTable tcTable;
//	private MailContactTable mcTable;
//
//	// < LAYOUTS >
	private CardLayout cardLayout;

	// <<ZONA FUNCIONAL>>
	private int themeIndex;

	// <<-CONSTRUCTOR->>
	public MainFrame() {
		this(0, Themes.LIGHT);
	}

	public MainFrame(int themeIndex, String themeType) {
		super(FRAME_TITLE);
		setTheme(themeIndex, themeType);
		initComponents();
	}

	private void setTheme(int themeIndex, String themeType) {
		this.themeIndex = themeIndex;
//		btnThemeTitle = themeType.equals(Themes.LIGHT) ?
//				"\uE28C" : "\uE284";
	}

	// <<-METHODS->>
	private static void setWeightsToZero(GridBagLayout gbl, int columns, int rows) {
		gbl.columnWeights = new double[columns];
		gbl.rowWeights    = new double[rows];

		for (int i = 0; i < columns; i++)
			gbl.columnWeights[i] = 0;
		for (int i = 0; i < rows; i++)
			gbl.rowWeights[i] = 0;
	}

	static GridBagLayout createGridBagLayout(int columns, int rows, int[] columnsToOne, int[] rowsToOne) {
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[columns];
		gbl.rowHeights = new int[rows];
		setWeightsToZero(gbl, columns, rows);

		if (columnsToOne != null)
			for (int i = 0; i < columnsToOne.length; i++)
				gbl.columnWeights[columnsToOne[i]] = 1;
		if (rowsToOne != null)
			for (int i = 0; i < rowsToOne.length; i++)
				gbl.rowWeights[rowsToOne[i]] = 1;

		return gbl;
	}

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

	static GridBagConstraints createGridBagConstraints(int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;

		return gbc;
	}

	// ========================
	// = = = Design Zone = = =
	// = = = Design Zone = = =
	// = = = Design Zone = = =
	// ========================
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((int) (ScreenSize.width  * 0.25),	// X position
				  (int) (ScreenSize.height * 0.22),	// Y position
			      (int) (ScreenSize.width  * 0.50),	// Width
				  (int) (ScreenSize.height * 0.55));// Height
		setResizable(false);

		GridBagLayout 	   gridBagLayout;
		GridBagConstraints gridBagConstraints;

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		gridBagLayout = createGridBagLayoutFull(2, 1);
		gridBagLayout.columnWidths[1] = (int) (ScreenSize.width * 0.35);
		contentPane.setLayout(gridBagLayout);
		setContentPane(contentPane);

		// Create Side Menu
		JPanel menuPanel = new JPanel();
		gridBagConstraints = createGridBagConstraints(0, 0);
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		contentPane.add(menuPanel, gridBagConstraints);
		gridBagLayout = createGridBagLayout(1, 3, null, null);
		menuPanel.setLayout(gridBagLayout);

		ButtonGroup menuGroup = new ButtonGroup();
		String[] menuButtonTitle = {
				"\uE125",
				"\uE2B1",
				"\uE115"
		};
		JRadioButton[] btnMenu = new JRadioButton[3];
		for (int i = 0; i < btnMenu.length; i++) {
			btnMenu[i] = new JRadioButton(menuButtonTitle[i]);
			btnMenu[i].setFont(new Font("Segoe UI Symbol", Font.PLAIN, (int) (ScreenSize.width * 0.03125)));
			int finalI = i;
			btnMenu[i].addActionListener(e -> cardLayout.show(viewsPanel, VIEWS_TITLE[finalI]));
			menuGroup.add(btnMenu[i]);
			gridBagConstraints = createGridBagConstraints(0, i);
			if (i != 0)
				gridBagConstraints.insets = new Insets(30, 0, 0, 0);
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			menuPanel.add(btnMenu[i], gridBagConstraints);
		}

		// Create Views
		viewsPanel = new JPanel();
		gridBagConstraints = createGridBagConstraints(1, 0);
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		contentPane.add(viewsPanel, gridBagConstraints);
		cardLayout = new CardLayout();
		viewsPanel.setLayout(cardLayout);

		createContactsView(gridBagLayout, gridBagConstraints);
//		createHobbiesView (gridBagLayout, gridBagConstraints);
//		createSettingsView(gridBagLayout, gridBagConstraints);
	}

	private void createContactsView(GridBagLayout gridBagLayout, GridBagConstraints gridBagConstraints) {
		JPanel contactsView = new JPanel();
		contactsView.setBorder(new EmptyBorder(5, 5, 0, 5));
		viewsPanel.add(contactsView, VIEWS_TITLE[0]);
		contactsView.setLayout(new BorderLayout(0, 5));

		JPanel browser = new JPanel();
		contactsView.add(browser, BorderLayout.NORTH);
		gridBagLayout = createGridBagLayout(3, 1, new int[] {1}, null);
		browser.setLayout(gridBagLayout);

		// NORTH :: Browser
		// NORTH :: Browser
		// NORTH :: Browser
		JLabel lblBrowser = new JLabel("\uE000");
		lblBrowser.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20));
		gridBagConstraints = createGridBagConstraints(0, 0);
		browser.add(lblBrowser, gridBagConstraints);

		JTextField contactsBrowserTF = new RoundJTextField(CONTACT_DEFAULT_TEXT);
		contactsBrowserTF.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (contactsBrowserTF.getText().equals(CONTACT_DEFAULT_TEXT))
					contactsBrowserTF.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (contactsBrowserTF.getText().isBlank())
					contactsBrowserTF.setText(CONTACT_DEFAULT_TEXT);
			}
		});
		contactsBrowserTF.getDocument()
					     .addDocumentListener(new DocumentListener() {
							 @Override
							 public void insertUpdate(DocumentEvent e) {
								 String contactWord = contactsBrowserTF.getText();
								 if (!contactWord.equals(CONTACT_DEFAULT_TEXT)) {
									 String contactType = (String) contactTypeComboBox.getSelectedItem();
									 contactTable.update(contactType, contactWord);
								 }
							 }

							 @Override
							 public void removeUpdate(DocumentEvent e) {
								 String contactWord = contactsBrowserTF.getText();
								 if (!contactWord.equals(CONTACT_DEFAULT_TEXT)) {
									 String contactType = (String) contactTypeComboBox.getSelectedItem();
									 contactTable.update(contactType, contactWord);
								 }
							 }

							 @Override
							 public void changedUpdate(DocumentEvent e) {}
						 });
		gridBagConstraints = createGridBagConstraints(1, 0);
		gridBagConstraints.insets = new Insets(0, 5, 0, 5);
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		browser.add(contactsBrowserTF, gridBagConstraints);

		contactTypeComboBox = new JComboBox<>(CONTACT_TYPE_TITLE);
		contactTypeComboBox.setPreferredSize(new Dimension((int) (ScreenSize.width * 0.0521), 30));
		contactTypeComboBox.addActionListener(e -> contactTable.update((String) contactTypeComboBox.getSelectedItem()));
		gridBagConstraints = createGridBagConstraints(2, 0);
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		browser.add(contactTypeComboBox, gridBagConstraints);

		// CENTER :: Table
		// CENTER :: Table
		// CENTER :: Table
		JPanel table = new JPanel();
		contactsView.add(table, BorderLayout.CENTER);
		table.setLayout(createGridBagLayoutFull(1, 1));

		contactTable = new ContactTable();
		JScrollPane contactScrollPanel = new JScrollPane(contactTable);
		gridBagConstraints = createGridBagConstraints(0, 0);
		gridBagConstraints.insets = new Insets(0, 0, 0, 1);
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		table.add(contactScrollPanel, gridBagConstraints);

		// SOUTH :: CRUD buttons
		// SOUTH :: CRUD buttons
		// SOUTH :: CRUD buttons
		JPanel crud = new JPanel();
		contactsView.add(crud, BorderLayout.SOUTH);
		crud.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		String[] crudTitle = {
				"REMOVE",
				"CONSULT",
				"ADD"
		};
		JButton[] btnCRUD = new JButton[3];
		for (int i = 0; i < btnCRUD.length; i++) {
			btnCRUD[i] = new JButton(crudTitle[i]);
			btnCRUD[i].setPreferredSize(new Dimension((int) (ScreenSize.width * 0.078125), 30));
			int action = i;
			btnCRUD[i].addActionListener(e -> crudAction(action));
			crud.add(btnCRUD[i]);
		}
	}

	private void crudAction(int i) {
		switch (i) {
			case 0 -> {
				int selectedRowCount = contactTable.getSelectedRowCount();
				if (selectedRowCount > 0) {
					String msg = String.format("Delete these %d contacts?", selectedRowCount);
					msg = selectedRowCount == 1 ?
							"Delete this contact?" : msg;
					if (MSG.questionMessage(msg) == 0) {
						contactTable.deleteRows();
//						updateTables();
					}
				}
			}
			case 1 -> {
				int selectedRowCount = contactTable.getSelectedRowCount();
				if (selectedRowCount == 1) {
					int id = (int) contactTable.getModel().getValueAt(contactTable.getSelectedRow(), 0);
//					new InfoFrame(this, id).setVisible(true);
				} else if (selectedRowCount > 1)
					MSG.warningMessage("It is not possible to consult more than 1 contact at a time.");
			}
			case 2 -> {
				setVisible(false);
//				new AddFrame(this).setVisible(true);
			}
		}
	}

//	private void createHobbiesView() {
//		JPanel hobbiesView = new JPanel();
//		hobbiesView.setBorder(new EmptyBorder(5, 5, 5, 5));
//		views.add(hobbiesView, VistasTitle[1]);
//		hobbiesView.setLayout(new BorderLayout(3, 3));
//
//		// NORTH - BUSCADOR
//		JPanel buscador = new JPanel();
//		hobbiesView.add(buscador, BorderLayout.NORTH);
//		gbl = createGridBagLayout(3, 1, new int[] {1}, null);
//		buscador.setLayout(gbl);
//
//		for (int i = 0; i < 3; i = i + 2) {
//			JLabel lblStars = new JLabel(StarSymbols);
//			lblStars.setFont(new Font("Segoi UI Symbol", Font.PLAIN, 15));
//			gbc = createGridBagConstraints(i, 0);
//			buscador.add(lblStars, gbc);
//		}
//
//		txtBuscadorA = new RoundJTextField(HobbyDefaultText);
//		txtBuscadorA.addFocusListener(this);
//		txtBuscadorA.getDocument().addDocumentListener(this);
//		gbc = createGridBagConstraints(1, 0);
//		gbc.insets = new Insets(0, 5, 0, 5);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		buscador.add(txtBuscadorA, gbc);
//
//		// CENTER - TABLA AFICIONES Y CONTACTOS_AFICIONES
//		JPanel tables = new JPanel();
//		hobbiesView.add(tables, BorderLayout.CENTER);
//		gbl = createGridBagLayout(2, 3, new int[] {0, 1}, new int[] {0});
//		gbl.columnWidths[1] = (int) (ScreenSize.width * 0.1);
//		tables.setLayout(gbl);
//
//		// --TABLA AFICIONES
//		hTable = new HobbyTable(this, dao);
//		JScrollPane hScroll = new JScrollPane(hTable);
//		gbc = createGridBagConstraints(0, 0);
//		gbc.fill = GridBagConstraints.BOTH;
//		tables.add(hScroll, gbc);
//
//		hobbiesTextField = new JTextField(HobbyTxtDefaultText);
//		hobbiesTextField.addFocusListener(this);
//		hobbiesTextField.addActionListener(this);
//		gbc = createGridBagConstraints(0, 1);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		tables.add(hobbiesTextField, gbc);
//
//		JPanel buttons = new JPanel();
//		gbc = createGridBagConstraints(0, 2);
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		tables.add(buttons, gbc);
//		gbl = createGridBagLayoutFull(2, 1);
//		buttons.setLayout(gbl);
//
//		for (int i = 0; i < btnCD.length; i++) {
//			btnCD[i] = new JButton(CDTitle[i]);
//			btnCD[i].addActionListener(this);
//			gbc = createGridBagConstraints(i, 0);
//			gbc.fill = GridBagConstraints.HORIZONTAL;
//			buttons.add(btnCD[i], gbc);
//		}
//
//		// --TABLA CONTACTOS_AFICIONES
//		chTable = new ContactHobbyTable(dao);
//		JScrollPane sContactHobbyPane = new JScrollPane(chTable);
//		gbc = createGridBagConstraints(1, 0);
//		gbc.gridheight = 3;
//		gbc.insets = new Insets(0, 5, 0, 0);
//		gbc.fill = GridBagConstraints.BOTH;
//		tables.add(sContactHobbyPane, gbc);
//	}
//
//	/**
//	 * Dise�a y crea la <b>vista Configuraci�n</b>.
//	 */
//	private void createSettingsView() {
//		JPanel vistaConfiguracion = new JPanel();
//		vistaConfiguracion.setBorder(new EmptyBorder(5, 5, 5, 5));
//		views.add(vistaConfiguracion, VistasTitle[2]);
//		vistaConfiguracion.setLayout(new BorderLayout(2, 2));
//
//		// NORTH - TEMAS, JUEGOS, CONSOLA, FORMATEAR
//		JPanel north = new JPanel();
//		vistaConfiguracion.add(north, BorderLayout.NORTH);
//		gbl = createGridBagLayout(5, 1, new int[] {2}, null);
//		north.setLayout(gbl);
//
//		themesButton = new JButton(btnThemeTitle);
//		themesButton.addActionListener(this);
//		gbc = createGridBagConstraints(0, 0);
//		north.add(themesButton, gbc);
//
//		String[] items;
//		if (btnThemeTitle.equals("\uE28C"))
//			items = Themes.LIGHT_THEMES;
//		else
//			items = Themes.DARK_THEMES;
//		themesComboBox = new ChangerComboBox(items);
//		themesComboBox.setSelectedIndex(themeIndex);
//		themesComboBox.addActionListener(this);
//		gbc = createGridBagConstraints(1, 0);
//		north.add(themesComboBox, gbc);
//
//		int fontSize = 18;
//		gamesButton = new JButton("\uE2D2");
//		gamesButton.setFont(new Font("Segoe UI Symbol", Font.PLAIN, fontSize));
//		gamesButton.addActionListener(this);
//		gbc = createGridBagConstraints(2, 0);
//		gbc.anchor = GridBagConstraints.EAST;
//		north.add(gamesButton, gbc);
//
//		consoleButton = new JButton("\uE2F3");
//		consoleButton.setFont(new Font("Segoe UI Symbol", Font.PLAIN, fontSize));
//		consoleButton.addActionListener(this);
//		gbc = createGridBagConstraints(3, 0);
//		gbc.anchor = GridBagConstraints.WEST;
//		north.add(consoleButton, gbc);
//
//		formatButton = new JButton("\uE283");
//		formatButton.setFont(new Font("Segoe UI Symbol", Font.PLAIN, fontSize));
//		formatButton.addActionListener(this);
//		gbc = createGridBagConstraints(4, 0);
//		north.add(formatButton, gbc);
//
//		// CENTER - TABLAS TEL�FONOS Y CORREOS
//		JPanel center = new JPanel();
//		vistaConfiguracion.add(center, BorderLayout.CENTER);
//		gbl = createGridBagLayoutFull(2, 1);
//		center.setLayout(gbl);
//
//		tcTable = new TelephoneContactTable(dao);
//		JScrollPane tScroll = new JScrollPane(tcTable);
//		gbc = createGridBagConstraints(0, 0);
//		gbc.fill = GridBagConstraints.BOTH;
//		center.add(tScroll, gbc);
//
//		mcTable = new MailContactTable(dao);
//		JScrollPane mScroll = new JScrollPane(mcTable);
//		gbc = createGridBagConstraints(1, 0);
//		gbc.fill = GridBagConstraints.BOTH;
//		center.add(mScroll, gbc);
//	}
//
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
//		for (int i = 0; i < btnMenu.length; i++)
//			if (btnMenu[i] == event) {
//				cl.show(views, VistasTitle[i]);
//				return true;
//			}
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
//		if (contactTypeComboBox == event) {
//			String selected = (String) contactTypeComboBox.getSelectedItem();
//			cTable.update(selected);
//			return true;
//		}
		return false;
	}

	/**
	 * Revisa el bot�n CRUD de la vista contactos que ha sido pulsado.
	 *
	 * @param event the event source
	 * @return true/false - if the event source is/isn't on this method
	 */
	private boolean listenerCRUD(Object event) {
//		for (int i = 0; i < btnCRUD.length; i++)
//			if (btnCRUD[i] == event) {
//				crudAction(i);
//				return true;
//			}
		return false;
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
//		if (btnCD[0] == event || hobbiesTextField == event) {
//			String text = hobbiesTextField.getText();
//			if (!text.equals("") && !text.equals(HobbyTxtDefaultText)) {
//				hTable.insertHobby(text);
//				hobbiesTextField.setText(HobbyTxtDefaultText);
//				return true;
//			}
//		} else if (btnCD[1] == event) {
//			int rowCount = hTable.getSelectedRowCount();
//			if (rowCount > 0) {
//				String msg = (rowCount == 1 ?
//						"�Est� seguro de que quiere borrar esta afici�n?":
//						"�Est� seguro de que quiere borrar " + rowCount + " aficiones?");
//				if (MSG.defaultOptionMessage(msg) == 0)
//					hTable.deleteSelectedRows();
//				return true;
//			} else if (!hobbiesTextField.getText().equals(HobbyTxtDefaultText)) {
//				hobbiesTextField.setText(HobbyTxtDefaultText);
//				return true;
//			}
//		}
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
//		for (String name : Themes.LIGHT_THEMES)
//			if (name.equals(themeName))
//				return Themes.LIGHT;
		return Themes.DARK;
	}

	/**
	 * Define la acci�n para el desplegable TEMAS.
	 *
	 * @param event  the event source
	 * @return true/false - if the event source is/isn't on this method
	 */
	private boolean listenerTemas(Object event) {
//		if (themesButton == event) {
//			String icon = themesButton.getText();
//			if (icon.equals("\uE28C")) {
//				themesButton.setText("\uE284");
//				themesComboBox.changeList(Themes.DARK_THEMES);
//				return true;
//			} else if (icon.equals("\uE284")) {
//				themesButton.setText("\uE28C");
//				themesComboBox.changeList(Themes.LIGHT_THEMES);
//				return true;
//			}
//		}
//
//		if (themesComboBox == event) {
//			String themeType = checkThemeType((String) themesComboBox.getSelectedItem());
//			dispose();
//			Application.changeTheme(themesComboBox.getSelectedIndex(), themeType);
//			return true;
//		}
		return false;
	}

	/**
	 * Define la acci�n para el bot�n Consola.
	 *
	 * @param event the event source
	 * @return true/false - if the event source is/isn't on this method
	 */
	private boolean listenerConsola(Object event) {
//		if (consoleButton == event) {
//			if (MSG.defaultOptionMessage("�Quiere acceder a la consola?") == 0) {
//				dispose();
//				dao.close();
//				new Menu(new DB());
//			}
//			return true;
//		}
		return false;
	}

	/**
	 * Define la acci�n para el bot�n Juegos.
	 *
	 * @param event the event source
	 * @return true/false - if the event source is/isn't on this method
	 */
	private boolean listenerJuegos(Object event) {
//		if (gamesButton == event) {
//			new TresEnRaya("X", "O").setVisible(true);
//			return true;
//		}
		return false;
	}

	/**
	 * Define la acci�n para el bot�n Formatear.
	 *
	 * @param event the event source
	 * @return true/false - if the event source is/isn't on this method
	 */
	private boolean listenerFormatear(Object event) {
//		if (formatButton == event && MSG.defaultOptionMessage("�Est� seguro de que quiere FORMATEAR la agenda?\n"
//				+ "ESTA ACCI�N NO SE PUEDE REVERTIR") == 0) {
//				dispose();
//				Application.format();
//				return true;
//		}
		return false;
	}

	// DOCUMENT LISTENER
	@Override
	public void insertUpdate(DocumentEvent e) {
//		Document event = e.getDocument();
//
//		// BUSCADOR CONTACTOS
//		if (txtBuscadorC.getDocument() == event) {
//			String contactWord = txtBuscadorC.getText();
//			if (!contactWord.equals(ContactDefaultText)) {
//				String contactType = (String) contactTypeComboBox.getSelectedItem();
//				cTable.update(contactType, contactWord);
//			}
//		}
//
//		// BUSCADOR AFICIONES
//		if (txtBuscadorA.getDocument() == event) {
//			String hobbyWord = txtBuscadorA.getText();
//			if (!hobbyWord.equals(HobbyDefaultText)) {
//				hTable.update(hobbyWord);
//				chTable.update(hobbyWord);
//			}
//		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
//		Document event = e.getDocument();
//
//		// BUSCADOR CONTACTOS
//		if (txtBuscadorC.getDocument() == event) {
//			String contactWord = txtBuscadorC.getText();
//			if (!contactWord.equals(ContactDefaultText)) {
//				String contactType = (String) contactTypeComboBox.getSelectedItem();
//				cTable.update(contactType, contactWord);
//			}
//		}
//
//		// BUSCADOR AFICIONES
//		if (txtBuscadorA.getDocument() == event) {
//			String hobbyWord = txtBuscadorA.getText();
//			if (!hobbyWord.equals(HobbyDefaultText)) {
//				hTable.update(hobbyWord);
//				chTable.update(hobbyWord);
//			}
//		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
//		MSG.questionMessage("changeUpdate(DocumentEvent e) apareci� por fin");
	}

	// FOCUS LISTENER
	@Override
	public void focusGained(FocusEvent e) {
//		Object event = e.getSource();
//
//		// BUSCADOR CONTACTOS
//		if (txtBuscadorC == event && txtBuscadorC.getText().equals(ContactDefaultText))
//			txtBuscadorC.setText("");
//
//		// BUSCADOR AFICIONES
//		if (txtBuscadorA == event && txtBuscadorA.getText().equals(HobbyDefaultText))
//			txtBuscadorA.setText("");
//
//		// CD AFICIONES
//		if (hobbiesTextField == event && hobbiesTextField.getText().equals(HobbyTxtDefaultText))
//			hobbiesTextField.setText("");
	}

	@Override
	public void focusLost(FocusEvent e) {
//		Object event = e.getSource();
//
//		// BUSCADOR CONTACTOS
//		if (txtBuscadorC == event && txtBuscadorC.getText().equals(""))
//			txtBuscadorC.setText(ContactDefaultText);
//
//		// BUSCADOR AFICIONES
//		if (txtBuscadorA == event && txtBuscadorA.getText().equals(""))
//			txtBuscadorA.setText(HobbyDefaultText);
//
//		// CD AFICIONES
//		if (hobbiesTextField == event && hobbiesTextField.getText().equals(""))
//			hobbiesTextField.setText(HobbyTxtDefaultText);
	}

//	// M�TODOS
//	/**
//	 * Actualiza las vistas de todas las tablas.
//	 */
//	public void updateTables() {
//		String selected = (String) contactTypeComboBox.getSelectedItem();
//		cTable.update(selected);
//		chTable.update();
//		tcTable.update();
//		mcTable.update();
//	}
//
//	// GETTERS
//	/**
//	 * @return the views panel
//	 */
//	public JPanel getViews() {
//		return views;
//	}
//
//	/**
//	 * @return the card layout
//	 */
//	public CardLayout getCardLayout() {
//		return cl;
//	}
//
//	/**
//	 * @return the contact table
//	 */
//	public ContactTable getContactTable() {
//		return cTable;
//	}
//
//	/**
//	 * @return the hobby table
//	 */
//	public HobbyTable getHobbyTable() {
//		return hTable;
//	}
//
//	/**
//	 * @return the contact hobby table
//	 */
//	public ContactHobbyTable getContactHobbyTable() {
//		return chTable;
//	}
//
//	/**
//	 * @return the telephone contact table
//	 */
//	public TelephoneContactTable getTelephoneContactTable() {
//		return tcTable;
//	}
//
//	/**
//	 * @return the mail contact table
//	 */
//	public MailContactTable getMailContactTable() {
//		return mcTable;
//	}
//
}
