package com.quathar.contactbook.ui.frame;

import com.google.inject.Guice;
import com.google.inject.Injector;

import com.quathar.contactbook.Application;
import com.quathar.contactbook.config.AppConfiguration;
import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.entity.Hobby;
import com.quathar.contactbook.data.enumerator.ContactType;
import com.quathar.contactbook.data.enumerator.Gender;
import com.quathar.contactbook.data.enumerator.TelephoneType;
import com.quathar.contactbook.data.service.ContactService;
import com.quathar.contactbook.data.service.HobbyService;
import com.quathar.contactbook.io.MSG;
import com.quathar.contactbook.io.RegexFilter;
import com.quathar.contactbook.model.dto.ContactInfoDTO;
import com.quathar.contactbook.ui.component.table.HobbyTable;
import com.quathar.contactbook.ui.component.table.MailTable;
import com.quathar.contactbook.ui.component.table.TelephoneTable;
import com.quathar.contactbook.ui.frame.helper.GBL;
import com.quathar.contactbook.ui.frame.i18n.Label;
import com.quathar.contactbook.ui.frame.i18n.Placeholder;
import com.quathar.contactbook.ui.frame.i18n.UnicodeIcon;
import com.quathar.contactbook.ui.frame.listener.ChangeActionListener;
import com.quathar.contactbook.ui.frame.listener.PlaceholderFocusListener;

import com.toedter.calendar.JDateChooser;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>AddContact</h1>
 * <br>
 * Class used to add contacts in the graphic user interface.
 *
 * @since 2022-04-13
 * @see JFrame
 * @version 2.0
 * @author Q
 */
public class AddFrame extends JFrame {

    // <<-CONSTANTS->>
    @Serial
    private static final long serialVersionUID = 1L;

    // <<-FIELDS->>
    private final ContactService _contactService;
    private final HobbyService _hobbyService;
    private final MainFrame _mainFrame;
    private final Map<String, JComponent> _bundle;
    private final Long _id;
    private final Contact contact;
    private ContactInfoDTO contactInfoDTO;

    // <<-CONSTRUCTORS->>
    /**
     * Constructs a new AddFrame instance without specifying an ID.
     *
     * @param mainFrame the MainFrame instance associated with this AddFrame
     */
    public AddFrame(MainFrame mainFrame) {
        this(mainFrame, 0L);
    }

    /**
     * Constructs a new AddFrame instance with the specified ID.<br>
     * If the ID is 0 or negative, a new contact will be created.
     *
     * @param mainFrame the MainFrame instance associated with this AddFrame.
     * @param id the ID of the contact to edit.
     */
    public AddFrame(MainFrame mainFrame, Long id) {
        Injector injector = Guice.createInjector(new AppConfiguration());
        _contactService   = injector.getInstance(ContactService.class);
        _hobbyService     = injector.getInstance(HobbyService.class);
        _mainFrame        = mainFrame;
        _bundle           = new HashMap<>();
        _id               = id;
        if (_id <= 0) {
            contact = new Contact();
            contact.setType(ContactType.PERSON);
        } else contact = _contactService.getById(_id);
        initComponents();
    }

    // <<-METHODS->>
    /**
     * Initializes the components of the application window.
     */
    private void initComponents() {
        setTitle(_id > 0 ?
                Label.INFO_FRAME_TITLE.getText():
                Label.ADD_FRAME_TITLE.getText());
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setBounds((int) (Application.SCREEN_SIZE.width  * 0.30), // X position
                  (int) (Application.SCREEN_SIZE.height * 0.25), // Y position
                  (int) (Application.SCREEN_SIZE.width  * 0.40), // Width
                  (int) (Application.SCREEN_SIZE.height * 0.50));// Height
        setResizable(false);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        contentPane.setLayout(new BorderLayout(0, 5));

        contactInfoDTO = new ContactInfoDTO();
        drawNorth (contentPane);
        drawCenter(contentPane);
        drawSouth (contentPane);
        addActionListeners();
    }

    /**
     * Draws the north panel of the content pane.
     *
     * @param contentPane the panel to which the north section is added
     */
    private void drawNorth(JPanel contentPane) {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        contentPane.add(northPanel, BorderLayout.NORTH);

        JComboBox<ContactType> contactTypeCB = new JComboBox<>(ContactType.values());
        contactTypeCB.setSelectedItem(contact.getType());
        // The action listener will be assigned later
        northPanel.add(contactTypeCB);
        contactInfoDTO.setContactTypeCB(contactTypeCB);
    }

    /**
     * Draws the center panel of the content pane.
     *
     * @param contentPane the panel to which the center section is added
     */
    private void drawCenter(JPanel contentPane) {
        JPanel dataPanel = new JPanel();
        contentPane.add(dataPanel, BorderLayout.CENTER);
        dataPanel.setLayout(GBL.createGridBagLayoutFull(2, 2));

        drawForm   (dataPanel);
        drawHobbies(dataPanel);
        drawTables (dataPanel);
    }

    private void drawForm(JPanel dataPanel) {
        GridBagConstraints gridBagConstraints;

        JPanel formPanel = new JPanel();
        gridBagConstraints = GBL.createGridBagConstraints(0, 0);
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        dataPanel.add(formPanel, gridBagConstraints);
        formPanel.setLayout(GBL.createGridBagLayout(4, 5, new int[] {1}, new int[] {4}));

        Insets insets = new Insets(0, 5, 0, 5);

        Font font = new Font("Segoe UI Symbol", Font.BOLD, 15);
        // Name
        JLabel lblName = new JLabel(UnicodeIcon.PERSON.getCode());
        lblName.setFont(font);
        gridBagConstraints = GBL.createGridBagConstraints(0, 0);
        gridBagConstraints.insets = insets;
        formPanel.add(lblName, gridBagConstraints);

        JTextField nameTF = new JTextField(Placeholder.NAME.getText());
        nameTF.setText(_id > 0 ? contact.getName() : Placeholder.NAME.getText());
        nameTF.addFocusListener(
                PlaceholderFocusListener.builder()
                        .inputTC(nameTF)
                        .defaultText(Placeholder.NAME.getText())
                        .build());
        gridBagConstraints = GBL.createGridBagConstraints(1, 0);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(nameTF, gridBagConstraints);
        contactInfoDTO.setNameTF(nameTF);

        // Surnames
        JLabel lblSurname = new JLabel(UnicodeIcon.PERSON.getCode());
        lblSurname.setFont(font);
        gridBagConstraints = GBL.createGridBagConstraints(0, 1);
        gridBagConstraints.insets = insets;
        formPanel.add(lblSurname, gridBagConstraints);

        JTextField surnamesTF = new JTextField(Placeholder.SURNAMES.getText());
        surnamesTF.setText(_id > 0 ? contact.getSurnames() : Placeholder.SURNAMES.getText());
        surnamesTF.setEnabled(contact.getType() == ContactType.PERSON);
        surnamesTF.addFocusListener(
                PlaceholderFocusListener.builder()
                        .inputTC(surnamesTF)
                        .defaultText(Placeholder.SURNAMES.getText())
                        .build());
        gridBagConstraints = GBL.createGridBagConstraints(1, 1);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(surnamesTF, gridBagConstraints);
        contactInfoDTO.setSurnamesTF(surnamesTF);

        // Birthdate
        JLabel lblBirthdate = new JLabel(UnicodeIcon.CALENDAR.getCode());
        gridBagConstraints = GBL.createGridBagConstraints(0, 2);
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        formPanel.add(lblBirthdate, gridBagConstraints);

        JDateChooser birthDC = new JDateChooser();
        birthDC.setEnabled(contact.getType() != ContactType.COMPANY);
        gridBagConstraints = GBL.createGridBagConstraints(1, 2);
        gridBagConstraints.insets = new Insets(0, 5, 0, 15);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(birthDC, gridBagConstraints);
        contactInfoDTO.setBirthDC(birthDC);

        // Gender
        JLabel lblGender = new JLabel(UnicodeIcon.GENDER.getCode());
        lblGender.setFont(font);
        gridBagConstraints = GBL.createGridBagConstraints(2, 2);
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        formPanel.add(lblGender, gridBagConstraints);

        JComboBox<Gender> genderCB = new JComboBox<>(Gender.values());
        genderCB.setEnabled(contact.getType() == ContactType.PERSON);
        gridBagConstraints = GBL.createGridBagConstraints(3, 2);
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        formPanel.add(genderCB, gridBagConstraints);
        contactInfoDTO.setGenderCB(genderCB);

        // Address
        JLabel lblAddress = new JLabel(UnicodeIcon.HOUSE.getCode());
        lblAddress.setFont(font);
        gridBagConstraints = GBL.createGridBagConstraints(0, 3);
        gridBagConstraints.insets = insets;
        formPanel.add(lblAddress, gridBagConstraints);

        JTextField addressTF = new JTextField(Placeholder.ADDRESS.getText());
        addressTF.setText(_id > 0 ? contact.getAddress() : Placeholder.ADDRESS.getText());
        addressTF.addFocusListener(
                PlaceholderFocusListener.builder()
                        .inputTC(addressTF)
                        .defaultText(Placeholder.ADDRESS.getText())
                        .build());
        gridBagConstraints = GBL.createGridBagConstraints(1, 3);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(addressTF, gridBagConstraints);
        contactInfoDTO.setAddressTF(addressTF);

        // Notes
        JTextArea notesTA = new JTextArea(Placeholder.NOTES.getText());
        notesTA.setText(_id > 0 ? contact.getNotes() : Placeholder.NOTES.getText());
        notesTA.addFocusListener(
                PlaceholderFocusListener.builder()
                        .inputTC(notesTA)
                        .defaultText(Placeholder.NOTES.getText())
                        .build());
        JScrollPane notesScrollPane = new JScrollPane(notesTA);
        gridBagConstraints = GBL.createGridBagConstraints(0, 4);
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        formPanel.add(notesScrollPane, gridBagConstraints);
        contactInfoDTO.setNotesTA(notesTA);
    }

    private void drawHobbies(JPanel dataPanel) {
        GridBagConstraints gridBagConstraints;

        JPanel hobbiesPanel = new JPanel();
        gridBagConstraints = GBL.createGridBagConstraints(0, 1);
        gridBagConstraints.insets = new Insets(5, 5, 0, 0);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        dataPanel.add(hobbiesPanel, gridBagConstraints);
        GridBagLayout gridBagLayout = GBL.createGridBagLayoutFull(3, 2);
        gridBagLayout.rowWeights[1] = 0;
        hobbiesPanel.setLayout(gridBagLayout);

        // Hobbies Table
        HobbyTable hobbyTable = new HobbyTable(_id);
        JScrollPane hobbiesScrollPane = new JScrollPane(hobbyTable);
        gridBagConstraints = GBL.createGridBagConstraints(0, 0);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        hobbiesPanel.add(hobbiesScrollPane, gridBagConstraints);
        contactInfoDTO.setHobbyTable(hobbyTable);

        // [ ComboBox ]

        // If the id is greater than 0,
        // then we get all the hobbies and filter them
        // to get the ones that the contact DOES NOT have
        String[] names = _id > 0 ?
                _hobbyService.getAll()
                             .stream()
                             .filter(hobby -> _contactService
                                     .getById(_id)
                                     .getHobbies()
                                     .stream()
                                     .noneMatch(contactHobby -> contactHobby
                                             .getId()
                                             .equals(hobby.getId())))
                             .map(Hobby::getName)
                             .toArray(String[]::new):
                _hobbyService.getAll()
                             .stream()
                             .map(Hobby::getName)
                             .toArray(String[]::new);
        JComboBox<String> hobbiesCB = new JComboBox<>(names);
        gridBagConstraints = GBL.createGridBagConstraints(0, 1);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        hobbiesPanel.add(hobbiesCB, gridBagConstraints);

        // [ Buttons ]
        JButton btnAdd = new JButton(UnicodeIcon.ADD.getCode());
        btnAdd.addActionListener(e -> {
            String selected = (String) hobbiesCB.getSelectedItem();
            // Add to the table and remove from the comboBox
            if (selected != null)
                hobbyTable.addHobby(selected);
            if (hobbiesCB.getItemCount() > 0)
                hobbiesCB.removeItemAt(hobbiesCB.getSelectedIndex());
        });
        gridBagConstraints = GBL.createGridBagConstraints(1, 1);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        hobbiesPanel.add(btnAdd, gridBagConstraints);

        JButton btnDelete = new JButton(UnicodeIcon.DELETE.getCode());
        btnDelete.addActionListener(e -> {
            // First we add the selected rows to the comboBox
            // then we delete the selected rows from the table
            for (int selectedRow : hobbyTable.getSelectedRows())
                hobbiesCB.addItem((String) hobbyTable.getValueAt(selectedRow, 0));
            hobbyTable.deleteRows();
        });
        gridBagConstraints = GBL.createGridBagConstraints(2, 1);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        hobbiesPanel.add(btnDelete, gridBagConstraints);
    }

    private void drawTables(JPanel dataPanel) {
        JPanel tablePanel = new JPanel();

        GridBagConstraints gridBagConstraints = GBL.createGridBagConstraints(1, 0);
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new Insets(0, 5, 0, 5);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        dataPanel.add(tablePanel, gridBagConstraints);

        GridBagLayout gridBagLayout = GBL.createGridBagLayoutFull(1, 4);
        gridBagLayout.rowWeights[1] = 0;
        gridBagLayout.rowWeights[3] = 0;
        tablePanel.setLayout(gridBagLayout);

        drawTelephoneTable(tablePanel);
        drawMailTable(tablePanel);
    }

    private void drawTelephoneTable(JPanel tablePanel) {
        GridBagConstraints gridBagConstraints;

        TelephoneTable telephoneTable = new TelephoneTable(_id);
        JScrollPane telephoneScrollPane = new JScrollPane(telephoneTable);
        telephoneScrollPane.setEnabled(contact.getType() != ContactType.PET);
        gridBagConstraints = GBL.createGridBagConstraints(0, 0);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        tablePanel.add(telephoneScrollPane, gridBagConstraints);
        contactInfoDTO.setTelephoneTable(telephoneTable);

        JPanel telephoneInputPanel = new JPanel();
        gridBagConstraints = GBL.createGridBagConstraints(0, 1);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        tablePanel.add(telephoneInputPanel, gridBagConstraints);
        telephoneInputPanel.setLayout(GBL.createGridBagLayout(4, 1, new int[] {0, 2, 3}, new int[] {0}));

        JComboBox<TelephoneType> telephoneTypeCB = new JComboBox<>(TelephoneType.values());
        telephoneTypeCB.setEnabled(contact.getType() != ContactType.PET);
        gridBagConstraints = GBL.createGridBagConstraints(1, 0);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        telephoneInputPanel.add(telephoneTypeCB, gridBagConstraints);

        JTextField telephoneTF = new JTextField();
        telephoneTF.setText(contact.getType() != ContactType.PET ?
                Placeholder.TELEPHONES.getText() :
                Placeholder.BLANK.getText());
        telephoneTF.setEnabled(contact.getType() != ContactType.PET);
        telephoneTF.addFocusListener(
                PlaceholderFocusListener.builder()
                        .inputTC(telephoneTF)
                        .defaultText(Placeholder.TELEPHONES.getText())
                        .build());
        telephoneTF.addActionListener(e -> {
            try {
                String telephone = RegexFilter.checkTelephone(telephoneTF.getText());
                Object[] data = {0, telephone, telephoneTypeCB.getSelectedItem()};
                telephoneTable.addNewRow(data);
                telephoneTF.setText(Placeholder.BLANK.getText());
            } catch (RuntimeException ex) {
                MSG.warningMessage(RegexFilter.TELEPHONE_ERROR_MSG);
            }
        });
        gridBagConstraints = GBL.createGridBagConstraints(0, 0);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        telephoneInputPanel.add(telephoneTF, gridBagConstraints);

        JButton btnAdd = new JButton(UnicodeIcon.ADD.getCode());
        btnAdd.setEnabled(contact.getType() != ContactType.PET);
        btnAdd.addActionListener(e -> {
            try {
                String telephone = RegexFilter.checkTelephone(telephoneTF.getText());
                Object[] data = {
                        telephone,
                        telephoneTypeCB.getSelectedItem()
                };
                telephoneTable.addNewRow(data);
                telephoneTF.setText(Placeholder.TELEPHONES.getText());
            } catch (RuntimeException ex) {
                MSG.warningMessage(RegexFilter.TELEPHONE_ERROR_MSG);
            }
        });
        gridBagConstraints = GBL.createGridBagConstraints(2, 0);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        telephoneInputPanel.add(btnAdd, gridBagConstraints);

        JButton btnDelete = new JButton(UnicodeIcon.DELETE.getCode());
        btnDelete.setEnabled(contact.getType() != ContactType.PET);
        btnDelete.addActionListener(e -> telephoneTable.deleteRows());
        gridBagConstraints = GBL.createGridBagConstraints(3, 0);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        telephoneInputPanel.add(btnDelete, gridBagConstraints);

        _bundle.put("telephoneTF",        telephoneTF);
        _bundle.put("telephoneTypeCB",    telephoneTypeCB);
        _bundle.put("btnAddTelephone",    btnAdd);
        _bundle.put("btnDeleteTelephone", btnDelete);
    }

    private void drawMailTable(JPanel tablePanel) {
        GridBagConstraints gridBagConstraints;

        MailTable mailTable = new MailTable(_id);
        JScrollPane mailScrollPane = new JScrollPane(mailTable);
        mailScrollPane.setEnabled(contact.getType() != ContactType.PET);
        gridBagConstraints = GBL.createGridBagConstraints(0, 2);
        gridBagConstraints.insets = new Insets(5, 0, 0, 0);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        tablePanel.add(mailScrollPane, gridBagConstraints);
        contactInfoDTO.setMailTable(mailTable);

        JPanel mailInputPanel = new JPanel();
        gridBagConstraints = GBL.createGridBagConstraints(0, 3);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        tablePanel.add(mailInputPanel, gridBagConstraints);
        mailInputPanel.setLayout(GBL.createGridBagLayoutFull(5, 1));

        JTextField mailTF = new JTextField(Placeholder.MAILS.getText());
        mailTF.setText(contact.getType() != ContactType.PET ?
                Placeholder.MAILS.getText() :
                Placeholder.BLANK.getText());
        mailTF.setEnabled(contact.getType() != ContactType.PET);
        mailTF.addFocusListener(
                PlaceholderFocusListener.builder()
                        .inputTC(mailTF)
                        .defaultText(Placeholder.MAILS.getText())
                        .build());
        mailTF.addActionListener(e -> {
            try {
                String mail = RegexFilter.checkMail(mailTF.getText());
                Object[] data = {mail};
                mailTable.addNewRow(data);
                mailTF.setText(Placeholder.BLANK.getText());
            } catch (RuntimeException ex) {
                MSG.warningMessage(RegexFilter.MAIL_ERROR_MSG);
            }
        });
        gridBagConstraints = GBL.createGridBagConstraints(0, 0);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        mailInputPanel.add(mailTF, gridBagConstraints);

        JButton btnAdd = new JButton(UnicodeIcon.ADD.getCode());
        btnAdd.setEnabled(contact.getType() != ContactType.PET);
        btnAdd.addActionListener(e -> {
            try {
                String mail = RegexFilter.checkMail(mailTF.getText());
                Object[] data = {0, mail};
                mailTable.addNewRow(data);
                mailTF.setText(Placeholder.MAILS.getText());
            } catch (RuntimeException ex) {
                MSG.warningMessage(RegexFilter.MAIL_ERROR_MSG);
            }
        });
        gridBagConstraints = GBL.createGridBagConstraints(3, 0);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        mailInputPanel.add(btnAdd, gridBagConstraints);

        JButton btnDelete = new JButton(UnicodeIcon.DELETE.getCode());
        btnDelete.setEnabled(contact.getType() != ContactType.PET);
        btnDelete.addActionListener(e -> mailTable.deleteRows());
        gridBagConstraints = GBL.createGridBagConstraints(4, 0);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        mailInputPanel.add(btnDelete, gridBagConstraints);

        _bundle.put("mailTF",        mailTF);
        _bundle.put("btnAddMail",    btnAdd);
        _bundle.put("btnDeleteMail", btnDelete);
    }

    /**
     * Draws the south panel of the content pane.
     *
     * @param contentPane the panel to which the south section is added
     */
    private void drawSouth(JPanel contentPane) {
        JPanel buttonsPanel = new JPanel();
        contentPane.add(buttonsPanel, BorderLayout.SOUTH);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        JButton btnReturn = new JButton(Label.BTN_RETURN.getText());
        btnReturn.addActionListener(e -> {
            _mainFrame.setVisible(true);
            dispose();
        });
        buttonsPanel.add(btnReturn);

        JButton btnSave = new JButton(Label.BTN_SAVE.getText());
        btnSave.addActionListener(e -> {
            String text = contactInfoDTO.getNameTF()
                                        .getText();
            if (!text.equals(Placeholder.NAME.getText()) || text.isBlank()) {
                if (_id <= 0) {
                       _contactService.create(contactInfoDTO.toEntity());
                       cleanFields();
                } else _contactService.update(_id, contactInfoDTO.toEntity());
                _mainFrame.updateTables();
            } else MSG.warningMessage("YOU NEED TO ADD A NAME");
        });
        buttonsPanel.add(btnSave);
    }

    /**
     * Adds action listeners to the components in the form.<br>
     * <br>
     * This method attaches an ActionListener to the contactTypeCB JComboBox,
     * which listens for changes in the selected contact type.<br>
     * <br>
     * When a change occurs, the associated ChangeActionListener instance is invoked,
     * which updates the form components based on the selected contact type.
     */
    private void addActionListeners() {
        JComboBox<ContactType> contactTypeCB = contactInfoDTO.getContactTypeCB();
        contactTypeCB.addActionListener(
                ChangeActionListener.builder()
                        .contactInfoDTO(contactInfoDTO)
                        .telephoneTypeCB   ((JComboBox<TelephoneType>)  _bundle.get("telephoneTypeCB"))
                        .telephoneTF       ((JTextField) _bundle.get("telephoneTF"))
                        .mailTF            ((JTextField) _bundle.get("mailTF"))
                        .btnAddMail        ((JButton) _bundle.get("btnAddMail"))
                        .btnAddTelephone   ((JButton) _bundle.get("btnAddTelephone"))
                        .btnDeleteMail     ((JButton) _bundle.get("btnDeleteMail"))
                        .btnDeleteTelephone((JButton) _bundle.get("btnDeleteTelephone"))
                        .build());
    }

    /**
     * Clears the fields of the form, resetting them to their default values.
     *
     * @see ContactInfoDTO#cleanFields()
     */
    private void cleanFields() {
        contactInfoDTO.cleanFields();

//        hobbiesComboBox.removeAllItems();
//        String[] aficiones = dao.getHobbies();
//        for (int i = 0; i < aficiones.length; i++)
//            hobbiesComboBox.addItem(aficiones[i]);

        ((JTextField) _bundle.get("telephoneTF")).setText(Placeholder.TELEPHONES.getText());
        ((JTextField) _bundle.get("mailTF"))     .setText(Placeholder.MAILS.getText());
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            dispose();
            if (_id > 0) _mainFrame.setVisible(true);
        }
        super.processWindowEvent(e);
    }

}