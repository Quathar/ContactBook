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
import com.quathar.contactbook.model.dto.ContactDTO;
import com.quathar.contactbook.ui.frame.helper.GBL;
import com.quathar.contactbook.ui.frame.helper.Placeholder;
import com.quathar.contactbook.ui.frame.helper.UnicodeIcon;
import com.quathar.contactbook.ui.frame.listener.ChangeActionListener;
import com.quathar.contactbook.ui.frame.listener.PlaceholderFocusListener;
import com.quathar.contactbook.ui.table.HobbyTable;
import com.quathar.contactbook.ui.table.MailTable;
import com.quathar.contactbook.ui.table.TelephoneTable;
import com.toedter.calendar.JDateChooser;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.Serial;

/**
 * <h1>AddContact</h1>
 *
 * Class used to add contacts in the graphic user interface.
 *
 * @since 2022-04-13
 * @version 2.0
 * @author Q
 */
public class AddFrame extends JFrame {

    // <<-CONSTANTS->>
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String FRAME_TITLE = "Add a new contact";

    // <<-FIELDS->>
    private final ContactService _contactService;
    private final HobbyService   _hobbyService;
    private final MainFrame      _mainFrame;
    private final Long _id;
    private Contact contact;
    private ContactDTO contactDTO;

    // <<-CONSTRUCTORS->>
    public AddFrame(MainFrame mainFrame) {
        this(mainFrame, 0L);
    }

    public AddFrame(MainFrame mainFrame, Long id) {
        super(FRAME_TITLE);
        Injector injector = Guice.createInjector(new AppConfiguration());
        _contactService   = injector.getInstance(ContactService.class);
        _hobbyService     = injector.getInstance(HobbyService.class);
        _mainFrame        = mainFrame;
        _id = id;
        if (_id <= 0) {
            contact = new Contact();
            contact.setType(ContactType.PERSON);
        } else contact = _contactService.getById(_id);
        initComponents();
    }

    // <<-METHODS->>
    // ========================
    // = = = Design Zone = = =
    // = = = Design Zone = = =
    // = = = Design Zone = = =
    // ========================
    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds((int) (Application.SCREEN_SIZE.width  * 0.30), // X position
                  (int) (Application.SCREEN_SIZE.height * 0.25), // Y position
                  (int) (Application.SCREEN_SIZE.width  * 0.40), // Width
                  (int) (Application.SCREEN_SIZE.height * 0.50));// Height
        setResizable(false);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        contentPane.setLayout(new BorderLayout(0, 5));

        drawNorth (contentPane);
        drawCenter(contentPane);
        drawSouth (contentPane);
    }

    private void drawNorth(JPanel contentPane) {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        contentPane.add(northPanel, BorderLayout.NORTH);

        JComboBox<ContactType> contactTypeCB = new JComboBox<>(ContactType.values());
        contactTypeCB.setSelectedItem(contact.getType());
        contactTypeCB.addActionListener(
                ChangeActionListener.builder()
                        .contactTypeCB(contactTypeCB)
                        .contactDTO(contactDTO)
                        .build());
        northPanel.add(contactTypeCB);
    }

    private void drawCenter(JPanel contentPane) {
        JPanel dataPanel = new JPanel();
        contentPane.add(dataPanel, BorderLayout.CENTER);
        dataPanel.setLayout(GBL.createGridBagLayoutFull(2, 2));

        contactDTO = new ContactDTO();
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

        // Name
        JLabel lblName = new JLabel(UnicodeIcon.PERSON.getCode());
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
        contactDTO.setNameTF(nameTF);

        // Surnames
        JLabel lblSurname = new JLabel(UnicodeIcon.PERSON.getCode());
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
        contactDTO.setSurnamesTF(surnamesTF);

        // Birthdate
        JLabel lblBirthdate = new JLabel(UnicodeIcon.CALENDAR.getCode());
//        lblBirthdate.setFont(new Font());
        gridBagConstraints = GBL.createGridBagConstraints(0, 2);
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        formPanel.add(lblBirthdate, gridBagConstraints);

        JDateChooser birthDC = new JDateChooser();
        birthDC.setEnabled(contact.getType() != ContactType.COMPANY);
        gridBagConstraints = GBL.createGridBagConstraints(1, 2);
        gridBagConstraints.insets = new Insets(0, 5, 0, 15);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(birthDC, gridBagConstraints);
        contactDTO.setBirthDC(birthDC);

        // Gender
        JLabel lblGender = new JLabel(UnicodeIcon.GENDER.getCode());
        gridBagConstraints = GBL.createGridBagConstraints(2, 2);
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        formPanel.add(lblGender, gridBagConstraints);

        JComboBox<Gender> genderCB = new JComboBox<>(Gender.values());
        genderCB.setEnabled(contact.getType() == ContactType.PERSON);
        gridBagConstraints = GBL.createGridBagConstraints(3, 2);
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        formPanel.add(genderCB, gridBagConstraints);
        contactDTO.setGenderCB(genderCB);

        // Address
        JLabel lblAddress = new JLabel(UnicodeIcon.HOUSE.getCode());
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
        contactDTO.setAddressTF(addressTF);

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
        contactDTO.setNotesTA(notesTA);
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
        HobbyTable hobbyTable = new HobbyTable(this._id);
        JScrollPane hobbiesScrollPane = new JScrollPane(hobbyTable);
        gridBagConstraints = GBL.createGridBagConstraints(0, 0);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        hobbiesPanel.add(hobbiesScrollPane, gridBagConstraints);
        contactDTO.setHobbyTable(hobbyTable);

        // ComboBox
        String[] names = _hobbyService.getAll()
                                      .stream()
                                      .map(Hobby::getName)
                                      .toArray(String[]::new);
        JComboBox<String> hobbiesCB = new JComboBox<>(names);
        gridBagConstraints = GBL.createGridBagConstraints(0, 1);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        hobbiesPanel.add(hobbiesCB, gridBagConstraints);

        // Buttons
        JButton btnAdd = new JButton(UnicodeIcon.ADD.getCode());
        btnAdd.addActionListener(e -> {
            String selected = (String) hobbiesCB.getSelectedItem();
            if (selected != null)
                hobbyTable.addNewHobby(selected);
            if (hobbiesCB.getItemCount() > 0)
                hobbiesCB.removeItemAt(hobbiesCB.getSelectedIndex());
        });
        gridBagConstraints = GBL.createGridBagConstraints(1, 1);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        hobbiesPanel.add(btnAdd, gridBagConstraints);

        JButton btnDelete = new JButton(UnicodeIcon.DELETE.getCode());
        btnDelete.addActionListener(e -> {
            hobbyTable.addToComboBox(hobbiesCB);
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

        TelephoneTable telephoneTable = new TelephoneTable(this._id);
        JScrollPane telephoneScrollPane = new JScrollPane(telephoneTable);
        telephoneScrollPane.setEnabled(contact.getType() != ContactType.PET);
        gridBagConstraints = GBL.createGridBagConstraints(0, 0);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        tablePanel.add(telephoneScrollPane, gridBagConstraints);
        contactDTO.setTelephoneTable(telephoneTable);

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
                Object[] data = { 0, telephone, telephoneTypeCB.getSelectedItem() };
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
    }

    private void drawMailTable(JPanel tablePanel) {
        GridBagConstraints gridBagConstraints;

        MailTable mailTable = new MailTable(this._id);
        JScrollPane mailScrollPane = new JScrollPane(mailTable);
        mailScrollPane.setEnabled(contact.getType() != ContactType.PET);
        gridBagConstraints = GBL.createGridBagConstraints(0, 2);
        gridBagConstraints.insets = new Insets(5, 0, 0, 0);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        tablePanel.add(mailScrollPane, gridBagConstraints);
        contactDTO.setMailTable(mailTable);

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
                Object[] data = {0, mail};
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
    }

    private void drawSouth(JPanel contentPane) {
        JPanel buttonsPanel = new JPanel();
        contentPane.add(buttonsPanel, BorderLayout.SOUTH);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        JButton btnReturn = new JButton("RETURN");
        btnReturn.addActionListener(e -> {
            _mainFrame.setVisible(true);
            dispose();
        });
        buttonsPanel.add(btnReturn);

        JButton btnAdd = new JButton("SAVE");
        btnAdd.addActionListener(e -> {
            String text = contactDTO.getNameTF()
                                    .getText();
            if (!text.equals(Placeholder.NAME.getText()) || text.isBlank()) {
                _contactService.create(ContactDTO.toEntity(contactDTO));
                cleanFields();
                _mainFrame.updateTables();
            } else MSG.warningMessage("YOU NEED TO ADD A NAME");
        });
        buttonsPanel.add(btnAdd);
    }

    private void cleanFields() {
        contactDTO.getNameTF()
                  .setText(Placeholder.NAME.getText());
        contactDTO.getSurnamesTF()
                  .setText(Placeholder.SURNAMES.getText());
        contactDTO.getBirthDC()
                  .cleanup();
        contactDTO.getGenderCB()
                  .setSelectedIndex(0);
        contactDTO.getAddressTF()
                  .setText(Placeholder.ADDRESS.getText());
        contactDTO.getNotesTA()
                  .setText(Placeholder.NOTES.getText());
//        contactDTO.getHobbyTable()
//                  .clean();
//        hobbiesComboBox.removeAllItems();
//        hTable.clean();
//        String[] aficiones = dao.getHobbies();
//        for (int i = 0; i < aficiones.length; i++)
//            hobbiesComboBox.addItem(aficiones[i]);
//
//        genderComboBox.setSelectedIndex(0);
//        tTable.clean();
//        telephoneTextField.setText(PlaceHoldersTitles[5]);
//        mTable.clean();
//        mailTextField.setText(PlaceHoldersTitles[6]);
    }

    // TODO: CHECK
    // TODO: CHECK
    // TODO: CHECK
    // TODO: CHECK
//	public int getDefaultCloseOperation() {
//		frame.setVisible(true);
//		dispose();
//		return 0;
//	}

}
