package com.quathar.contactbook.ui.frame;

import com.quathar.contactbook.ui.game.TicTacToe;
import com.quathar.contactbook.Application;
import com.quathar.contactbook.io.MSG;
import com.quathar.contactbook.ui.Themes;
import com.quathar.contactbook.ui.component.ChangerComboBox;
import com.quathar.contactbook.ui.component.RoundJTextField;
import com.quathar.contactbook.ui.frame.helper.GBL;
import com.quathar.contactbook.ui.frame.helper.UnicodeIcon;
import com.quathar.contactbook.ui.frame.helper.ViewTitle;
import com.quathar.contactbook.ui.frame.listener.BrowserFocusListener;
import com.quathar.contactbook.ui.frame.listener.ContactsBrowserDocumentListener;
import com.quathar.contactbook.ui.frame.listener.HobbiesBrowserDocumentListener;
import com.quathar.contactbook.ui.table.ContactHobbyTable;
import com.quathar.contactbook.ui.table.ContactTable;
import com.quathar.contactbook.ui.table.HobbyTable;
import com.quathar.contactbook.ui.table.MailContactTable;
import com.quathar.contactbook.ui.table.TelephoneContactTable;

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
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Menu;
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
 * @version 3.0
 * @author Q
 */
public class MainFrame extends JFrame {

    // <<-CONSTANTS->>
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String FRAME_TITLE = "Contact Book";
    private static final String FONT_NAME = "Segoe UI Symbol";
    private static final String CONTACT_DEFAULT_TEXT  = "Search for a contact...";
    private static final String HOBBY_DEFAULT_TEXT    = "Search for a hobby...";
    private static final String HOBBY_TF_DEFAULT_TEXT = "Add a hobby...";

    // <<-FIELDS->>
    private UnicodeIcon themeTypeIcon;
    private int themeIndex;
    private JPanel viewsPanel;
    private CardLayout cardLayout;
    private JComboBox<String> contactTypeComboBox;
    private ContactTable contactTable;
    private HobbyTable hobbyTable;
    private ContactHobbyTable contactHobbyTable;
    private TelephoneContactTable telephoneContactTable;
    private MailContactTable mailContactTable;

    // <<-CONSTRUCTORS->>
    public MainFrame() {
        this(0, Themes.LIGHT, null);
    }

    public MainFrame(int themeIndex, String themeType, ViewTitle view) {
        super(FRAME_TITLE);
        setTheme(themeIndex, themeType);
        initComponents();
        if (view != null) cardLayout.show(viewsPanel, view.toString());
    }

    private void setTheme(int themeIndex, String themeType) {
        this.themeIndex    = themeIndex;
        this.themeTypeIcon = themeType.equals(Themes.LIGHT) ?
                                UnicodeIcon.SUN:
                                UnicodeIcon.MOON;
    }

    // <<-METHODS->>
    // ========================
    // = = = Design Zone = = =
    // = = = Design Zone = = =
    // = = = Design Zone = = =
    // ========================
    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds((int) (Application.SCREEN_SIZE.width  * 0.20), // X position
                  (int) (Application.SCREEN_SIZE.height * 0.20), // Y position
                  (int) (Application.SCREEN_SIZE.width  * 0.60), // Width
                  (int) (Application.SCREEN_SIZE.height * 0.60));// Height
        setResizable(false);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        GridBagLayout gridBagLayout = GBL.createGridBagLayoutFull(2, 1);
        gridBagLayout.columnWidths[1] = (int) (Application.SCREEN_SIZE.width * 0.35);
        contentPane.setLayout(gridBagLayout);

        drawSideMenu(contentPane);
        drawViews(contentPane);
    }

    private void drawSideMenu(JPanel contentPane) {
        GridBagConstraints gridBagConstraints;

        // Create Side Menu
        JPanel menuPanel = new JPanel();
        gridBagConstraints = GBL.createGridBagConstraints(0, 0);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        contentPane.add(menuPanel, gridBagConstraints);
        GridBagLayout gridBagLayout = GBL.createGridBagLayout(1, 3, null, null);
        menuPanel.setLayout(gridBagLayout);

        ButtonGroup menuGroup = new ButtonGroup();
        Font font = new Font(FONT_NAME, Font.PLAIN, (int) (Application.SCREEN_SIZE.width * 0.03125));

        JRadioButton btnContact = new JRadioButton(UnicodeIcon.CONTACTS.getCode());
        btnContact.setFont(font);
        btnContact.addActionListener(e -> cardLayout.show(viewsPanel, ViewTitle.CONTACTS.toString()));
        menuGroup.add(btnContact);
        gridBagConstraints = GBL.createGridBagConstraints(0, 0);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        menuPanel.add(btnContact, gridBagConstraints);

        JRadioButton btnHobby = new JRadioButton(UnicodeIcon.HOBBIES.getCode());
        btnHobby.setFont(font);
        btnHobby.addActionListener(e -> cardLayout.show(viewsPanel, ViewTitle.HOBBIES.toString()));
        menuGroup.add(btnHobby);
        gridBagConstraints = GBL.createGridBagConstraints(0, 1);
        gridBagConstraints.insets = new Insets(30, 0, 0, 0);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        menuPanel.add(btnHobby, gridBagConstraints);

        JRadioButton btnSettings = new JRadioButton(UnicodeIcon.SETTINGS.getCode());
        btnSettings.setFont(font);
        btnSettings.addActionListener(e -> cardLayout.show(viewsPanel, ViewTitle.SETTINGS.toString()));
        menuGroup.add(btnSettings);
        gridBagConstraints = GBL.createGridBagConstraints(0, 2);
        gridBagConstraints.insets = new Insets(30, 0, 0, 0);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        menuPanel.add(btnSettings, gridBagConstraints);
    }

    private void drawViews(JPanel contentPane) {
        viewsPanel = new JPanel();
        GridBagConstraints gridBagConstraints = GBL.createGridBagConstraints(1, 0);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        contentPane.add(viewsPanel, gridBagConstraints);

        cardLayout = new CardLayout();
        viewsPanel.setLayout(cardLayout);

        drawContactsView();
        drawHobbiesView();
        drawSettingsView();
    }

    private void drawContactsView() {
        JPanel contactsViewPanel = new JPanel();
        contactsViewPanel.setBorder(new EmptyBorder(5, 5, 0, 5));
        contactsViewPanel.setLayout(new BorderLayout(0, 5));
        viewsPanel.add(contactsViewPanel, ViewTitle.CONTACTS.toString());

        drawContactsViewNorth (contactsViewPanel);
        drawContactsViewCenter(contactsViewPanel);
        drawContactsViewSouth (contactsViewPanel);
    }

    private void drawContactsViewNorth(JPanel contactsViewPanel) {
        GridBagConstraints gridBagConstraints;

        JPanel browserPanel = new JPanel();
        contactsViewPanel.add(browserPanel, BorderLayout.NORTH);
        GridBagLayout gridBagLayout = GBL.createGridBagLayout(3, 1, new int[]{1}, null);
        browserPanel.setLayout(gridBagLayout);

        JLabel browserLbl = new JLabel(UnicodeIcon.MAGNIFYING_GLASS.getCode());
        browserLbl.setFont(new Font(FONT_NAME, Font.BOLD, 20));
        gridBagConstraints = GBL.createGridBagConstraints(0, 0);
        browserPanel.add(browserLbl, gridBagConstraints);

        JTextField contactsBrowserTF = new RoundJTextField(CONTACT_DEFAULT_TEXT);
        contactsBrowserTF.addFocusListener(
                BrowserFocusListener.builder()
                        .defaultText(CONTACT_DEFAULT_TEXT)
                        .browserTF(contactsBrowserTF)
                        .build());
        contactsBrowserTF.getDocument().addDocumentListener(
                ContactsBrowserDocumentListener.builder()
                        .defaultText(CONTACT_DEFAULT_TEXT)
                        .contactBrowserTF(contactsBrowserTF)
                        .contactTypeCB(contactTypeComboBox)
                        .contactTable(contactTable)
                        .build());
        gridBagConstraints = GBL.createGridBagConstraints(1, 0);
        gridBagConstraints.insets = new Insets(0, 5, 0, 5);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        browserPanel.add(contactsBrowserTF, gridBagConstraints);

        String[] contactTypeTitle = {
                "ALL",
                "PERSONS",
                "COMPANIES",
                "PETS"
        };
        contactTypeComboBox = new JComboBox<>(contactTypeTitle);
        contactTypeComboBox.setPreferredSize(new Dimension((int) (Application.SCREEN_SIZE.width * 0.0521), 30));
        contactTypeComboBox.addActionListener(e -> contactTable.update((String) contactTypeComboBox.getSelectedItem()));
        gridBagConstraints = GBL.createGridBagConstraints(2, 0);
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        browserPanel.add(contactTypeComboBox, gridBagConstraints);
    }

    private void drawContactsViewCenter(JPanel contactsViewPanel) {
        GridBagConstraints gridBagConstraints;

        JPanel tablePanel = new JPanel();
        contactsViewPanel.add(tablePanel, BorderLayout.CENTER);
        tablePanel.setLayout(GBL.createGridBagLayoutFull(1, 1));

        contactTable = new ContactTable();
        JScrollPane contactScrollPane = new JScrollPane(contactTable);
        gridBagConstraints = GBL.createGridBagConstraints(0, 0);
        gridBagConstraints.insets = new Insets(0, 0, 0, 1);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        tablePanel.add(contactScrollPane, gridBagConstraints);
    }

    private void drawContactsViewSouth(JPanel contactsViewPanel) {
        JPanel buttonsPanel = new JPanel();
        contactsViewPanel.add(buttonsPanel, BorderLayout.SOUTH);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        String REMOVE = "REMOVE";
        String CONSULT = "CONSULT";
        String ADD = "ADD";

        JButton btnDelete = new JButton(REMOVE);
        btnDelete.setPreferredSize(new Dimension((int) (Application.SCREEN_SIZE.width * 0.078125), 30));
        btnDelete.addActionListener(e -> {
            int selectedRowCount = contactTable.getSelectedRowCount();
            if (selectedRowCount > 0) {
                String msg = selectedRowCount == 1 ?
                        "Delete this contact?":
                        String.format("Delete these %d contacts?", selectedRowCount);
                if (MSG.questionMessage(msg) == 0) {
                    contactTable.deleteRows();
//						updateTables();
                }
            }
        });
        buttonsPanel.add(btnDelete);

        JButton btnConsult = new JButton(CONSULT);
        btnConsult.setPreferredSize(new Dimension((int) (Application.SCREEN_SIZE.width * 0.078125), 30));
        btnConsult.addActionListener(e -> {
            int selectedRowCount = contactTable.getSelectedRowCount();
            if (selectedRowCount == 1) {
                int id = (int) contactTable.getModel().getValueAt(contactTable.getSelectedRow(), 0);
//					new InfoFrame(this, id).setVisible(true);
            } else if (selectedRowCount > 1)
                MSG.warningMessage("It is not possible to consult more than 1 contact at a time.");
        });
        buttonsPanel.add(btnConsult);

        JButton btnAdd = new JButton(ADD);
        btnAdd.setPreferredSize(new Dimension((int) (Application.SCREEN_SIZE.width * 0.078125), 30));
        btnAdd.addActionListener(e -> {
            setVisible(false);
            new AddFrame(this).setVisible(true);
        });
        buttonsPanel.add(btnAdd);
    }

    private void drawHobbiesView() {
        JPanel hobbiesViewPanel = new JPanel();
        hobbiesViewPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        hobbiesViewPanel.setLayout(new BorderLayout(3, 3));
        viewsPanel.add(hobbiesViewPanel, ViewTitle.HOBBIES.toString());

        drawHobbiesViewNorth (hobbiesViewPanel);
        drawHobbiesViewCenter(hobbiesViewPanel);
    }

    private void drawHobbiesViewNorth(JPanel hobbiesViewPanel) {
        GridBagConstraints gridBagConstraints;

        JPanel browserPanel = new JPanel();
        hobbiesViewPanel.add(browserPanel, BorderLayout.NORTH);
        GridBagLayout gridBagLayout = GBL.createGridBagLayout(3, 1, new int[]{1}, null);
        browserPanel.setLayout(gridBagLayout);

        // Labels Star Symbols
        for (int i = 0; i < 3; i += 2) {
            JLabel lblStars = new JLabel(UnicodeIcon.STAR.getCode().repeat(5));
            lblStars.setFont(new Font(FONT_NAME, Font.PLAIN, 15));
            gridBagConstraints = GBL.createGridBagConstraints(i, 0);
            browserPanel.add(lblStars, gridBagConstraints);
        }

        JTextField hobbiesBrowserTF = new RoundJTextField(HOBBY_DEFAULT_TEXT);
        hobbiesBrowserTF.addFocusListener(
                BrowserFocusListener.builder()
                        .defaultText(CONTACT_DEFAULT_TEXT)
                        .browserTF(hobbiesBrowserTF)
                        .build());
        hobbiesBrowserTF.getDocument().addDocumentListener(
                HobbiesBrowserDocumentListener.builder()
                        .defaultText(HOBBY_DEFAULT_TEXT)
                        .hobbyBrowserTF(hobbiesBrowserTF)
                        .hobbyTable(null)
                        .contactHobbyTable(null)
                        .build());
        gridBagConstraints = GBL.createGridBagConstraints(1, 0);
        gridBagConstraints.insets = new Insets(0, 5, 0, 5);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        browserPanel.add(hobbiesBrowserTF, gridBagConstraints);
    }

    private void drawHobbiesViewCenter(JPanel hobbiesViewPanel) {
        GridBagLayout gridBagLayout;
        GridBagConstraints gridBagConstraints;

        JPanel tablesPanel = new JPanel();
        hobbiesViewPanel.add(tablesPanel, BorderLayout.CENTER);
        gridBagLayout = GBL.createGridBagLayout(2, 3, new int[]{0, 1}, new int[]{0});
        gridBagLayout.columnWidths[1] = (int) (Application.SCREEN_SIZE.width * 0.10);
        tablesPanel.setLayout(gridBagLayout);

        // Hobbies Table
        hobbyTable = new HobbyTable();
        JScrollPane hobbyScrollPane = new JScrollPane(hobbyTable);
        gridBagConstraints = GBL.createGridBagConstraints(0, 0);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        tablesPanel.add(hobbyScrollPane, gridBagConstraints);

        JTextField hobbyTF = new JTextField(HOBBY_TF_DEFAULT_TEXT);
        hobbyTF.addFocusListener(
                BrowserFocusListener.builder()
                        .defaultText(HOBBY_TF_DEFAULT_TEXT)
                        .browserTF(hobbyTF)
                        .build());
        hobbyTF.addActionListener(e -> {
            String text = hobbyTF.getText();
            if (!text.equals("") && !text.equals(HOBBY_TF_DEFAULT_TEXT)) {
                hobbyTable.addHobby(text);
                hobbyTF.setText(HOBBY_TF_DEFAULT_TEXT);
            }
        });
        gridBagConstraints = GBL.createGridBagConstraints(0, 1);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        tablesPanel.add(hobbyTF, gridBagConstraints);

        JPanel buttonsPanel = new JPanel();
        gridBagConstraints = GBL.createGridBagConstraints(0, 2);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        tablesPanel.add(buttonsPanel, gridBagConstraints);
        gridBagLayout = GBL.createGridBagLayoutFull(2, 1);
        buttonsPanel.setLayout(gridBagLayout);

        JButton btnCreate = new JButton(UnicodeIcon.ADD.getCode());
        btnCreate.addActionListener(e -> {
            String text = hobbyTF.getText();
            if (!text.equals("") && !text.equals(HOBBY_TF_DEFAULT_TEXT)) {
                hobbyTable.addHobby(text);
                hobbyTF.setText(HOBBY_TF_DEFAULT_TEXT);
            }
        });
        gridBagConstraints = GBL.createGridBagConstraints(0, 0);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        buttonsPanel.add(btnCreate, gridBagConstraints);

        JButton btnDelete = new JButton(UnicodeIcon.DELETE.getCode());
        btnDelete.addActionListener(e -> {
            int rowCount = hobbyTable.getSelectedRowCount();
            if (rowCount > 0) {
                String msg = rowCount == 1 ?
                        "Are you sure you want to delete this hobby?":
                        String.format("Are you sure you want to delete %d hobbies?", rowCount);
                if (MSG.defaultOptionMessage(msg) == 0)
                    hobbyTable.deleteRows();
            } else if (!hobbyTF.getText().equals(HOBBY_TF_DEFAULT_TEXT))
                hobbyTF.setText(HOBBY_TF_DEFAULT_TEXT);
        });
        gridBagConstraints = GBL.createGridBagConstraints(1, 0);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        buttonsPanel.add(btnDelete, gridBagConstraints);

        // Contacts Hobbies Table
        contactHobbyTable = new ContactHobbyTable();
        JScrollPane contactHobbyScrollPane = new JScrollPane(contactHobbyTable);
        gridBagConstraints = GBL.createGridBagConstraints(1, 0);
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        tablesPanel.add(contactHobbyScrollPane, gridBagConstraints);
    }

    private void drawSettingsView() {
        JPanel settingsViewPanel = new JPanel();
        settingsViewPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        settingsViewPanel.setLayout(new BorderLayout(2, 2));
        viewsPanel.add(settingsViewPanel, ViewTitle.SETTINGS.toString());

        drawSettingsViewNorth(settingsViewPanel);
        drawSettingsViewCenter(settingsViewPanel);
    }

    private void drawSettingsViewNorth(JPanel settingsViewPanel) {
        GridBagConstraints gridBagConstraints;

        JPanel northPanel = new JPanel();
        settingsViewPanel.add(northPanel, BorderLayout.NORTH);
        GridBagLayout gridBagLayout = GBL.createGridBagLayout(5, 1, new int[] {2}, null);
        northPanel.setLayout(gridBagLayout);

        // THEMES
        // THEMES
        // THEMES
        JButton themesButton = new JButton(themeTypeIcon.getCode());
        String[] items = themeTypeIcon == UnicodeIcon.SUN ?
                            Themes.LIGHT_THEMES:
                            Themes.DARK_THEMES;
        ChangerComboBox themesComboBox = new ChangerComboBox(items);

        themesButton.addActionListener(e -> {
            String icon = themesButton.getText();
            if (icon.equals(UnicodeIcon.SUN.getCode())) {
                themesButton.setText(UnicodeIcon.MOON.getCode());
                themesComboBox.changeList(Themes.DARK_THEMES);
            } else if (icon.equals(UnicodeIcon.MOON.getCode())) {
                themesButton.setText(UnicodeIcon.SUN.getCode());
                themesComboBox.changeList(Themes.LIGHT_THEMES);
            }
        });
        northPanel.add(themesButton, GBL.createGridBagConstraints(0, 0));

        themesComboBox.setSelectedIndex(themeIndex);
        themesComboBox.addActionListener(e -> {
            String themeType = themesButton.getText().equals(UnicodeIcon.SUN.getCode()) ?
                    Themes.LIGHT:
                    Themes.DARK;
            dispose();
            Application.changeTheme(themesComboBox.getSelectedIndex(), themeType);
        });
        northPanel.add(themesComboBox, GBL.createGridBagConstraints(1, 0));

        // GAMES
        // GAMES
        // GAMES
        Font font = new Font(FONT_NAME, Font.PLAIN, 18);
        JButton gamesButton = new JButton(UnicodeIcon.GAME_CONTROL.getCode());
        gamesButton.setFont(font);
        gamesButton.addActionListener(e -> new TicTacToe("X", "O").setVisible(true));
        gridBagConstraints = GBL.createGridBagConstraints(2, 0);
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        northPanel.add(gamesButton, gridBagConstraints);

        // CONSOLE
        // CONSOLE
        // CONSOLE
        JButton consoleButton = new JButton(UnicodeIcon.KEYBOARD.getCode());
        consoleButton.setFont(font);
        consoleButton.addActionListener(e -> {
            if (MSG.defaultOptionMessage("Do you want to access the agenda by console?") == 0) {
                dispose();
                new Menu();
            }
        });
        gridBagConstraints = GBL.createGridBagConstraints(3, 0);
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        northPanel.add(consoleButton, gridBagConstraints);

        // FORMAT
        // FORMAT
        // FORMAT
        JButton formatButton = new JButton(UnicodeIcon.FORMAT.getCode());
        formatButton.setFont(font);
        formatButton.addActionListener(e -> {
            String msg = String.format("Are you sure you want to FORMAT the agenda?%nTHIS ACTION CANNOT BE REVERSED.");
            if (MSG.defaultOptionMessage(msg) == 0) {
                // TODO: See the logic here
                // TODO: See the logic here
                // TODO: See the logic here
                // TODO: See the logic here
                // TODO: See the logic here
                System.err.println("FORMATED");
//                dispose();
//                Application.format();
            }
        });
        northPanel.add(formatButton, GBL.createGridBagConstraints(4, 0));
    }

    private void drawSettingsViewCenter(JPanel settingsViewPanel) {
        GridBagConstraints gridBagConstraints;

        JPanel centerPanel = new JPanel();
        settingsViewPanel.add(centerPanel, BorderLayout.CENTER);
        GridBagLayout gridBagLayout = GBL.createGridBagLayoutFull(2, 1);
        centerPanel.setLayout(gridBagLayout);

        telephoneContactTable = new TelephoneContactTable();
        JScrollPane tScroll = new JScrollPane(telephoneContactTable);
        gridBagConstraints = GBL.createGridBagConstraints(0, 0);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        centerPanel.add(tScroll, gridBagConstraints);

        mailContactTable = new MailContactTable();
        JScrollPane mScroll = new JScrollPane(mailContactTable);
        gridBagConstraints = GBL.createGridBagConstraints(1, 0);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        centerPanel.add(mScroll, gridBagConstraints);
    }

    public void updateTables() {
        String selected = (String) contactTypeComboBox.getSelectedItem();
        contactTable.update(selected);
        System.out.println("=".repeat(50));
        System.out.println("TODO: Fix MainFrame updateTables() commented line");
        System.out.println("=".repeat(50));
//		contactHobbyTable.update();
        telephoneContactTable.update();
        mailContactTable.update();
    }

//	// GETTERS
//	public ContactTable getContactTable() {
//		return cTable;
//	}
//
//	public HobbyTable getHobbyTable() {
//		return hTable;
//	}
//
//	public ContactHobbyTable getContactHobbyTable() {
//		return chTable;
//	}
//
//	public TelephoneContactTable getTelephoneContactTable() {
//		return tcTable;
//	}
//
//	public MailContactTable getMailContactTable() {
//		return mcTable;
//	}

}
