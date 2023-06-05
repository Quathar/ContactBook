package com.quathar.contactbook.model.dto;

import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.enumerator.ContactType;
import com.quathar.contactbook.data.enumerator.Gender;
import com.quathar.contactbook.ui.table.HobbyTable;
import com.quathar.contactbook.ui.table.MailTable;
import com.quathar.contactbook.ui.table.TelephoneTable;
import com.toedter.calendar.JDateChooser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @since 2023-05-30
 * @version 1.0
 * @author Q
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactDTO {

    // <<-FIELDS->>
    private JComboBox<String> contactTypeCB;
    private JTextField nameTF;
    private JTextField surnamesTF;
    private JTextField addressTF;
    private JDateChooser birthDC;
    private JComboBox<Gender> genderCB;
    private JTextArea notesTA;

    private HobbyTable hobbyTable;
    private TelephoneTable telephoneTable;
    private MailTable mailTable;

    public static Contact toEntity(ContactDTO contactDTO) {
        Contact contact = new Contact();

        contact.setType((ContactType) contactDTO.getContactTypeCB().getSelectedItem());
        contact.setName(contactDTO.getNameTF().getText());
        contact.setBirthDate(contactDTO.getBirthDC().getDateFormatString());
        contact.setNotes(contactDTO.getNotesTA().getText());

        JTextField surnamesTF = contactDTO.getSurnamesTF();
        JTextField addressTF  = contactDTO.getAddressTF();
        JComboBox<Gender> genderCB = contactDTO.getGenderCB();
        if (surnamesTF.isEnabled())
            contact.setSurnames(surnamesTF.getText());
        if (addressTF.isEnabled())
            contact.setAddress(addressTF.getText());
        if (genderCB.isEnabled())
            contact.setGender((Gender) genderCB.getSelectedItem());

        // TODO: Check how to pass from table to entity
        TelephoneTable telephoneTable = contactDTO.getTelephoneTable();
        MailTable      mailTable      = contactDTO.getMailTable();
        HobbyTable     hobbyTable     = contactDTO.getHobbyTable();
//        if (telephoneTable.isEnabled())
//            contact.setTelephones();
//        if (mailTable.isEnabled())
//            contact.setMails();
//        if (hobbyTable.isEnabled())
//            contact.setHobbies();

        return contact;
    }

}
