package com.quathar.contactbook.model.dto;

import com.quathar.contactbook.data.embeddable.Mail;
import com.quathar.contactbook.data.embeddable.Telephone;
import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.entity.Hobby;
import com.quathar.contactbook.data.enumerator.ContactType;
import com.quathar.contactbook.data.enumerator.Gender;
import com.quathar.contactbook.data.enumerator.TelephoneType;
import com.quathar.contactbook.ui.component.table.HobbyTable;
import com.quathar.contactbook.ui.component.table.MailTable;
import com.quathar.contactbook.ui.component.table.TelephoneTable;
import com.quathar.contactbook.ui.frame.helper.Placeholder;

import com.toedter.calendar.JDateChooser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Contact Info DTO (Data Transfer Object)</h1>
 *
 * @since 2023-05-30
 * @version 1.0
 * @author Q
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactInfoDTO {

    // <<-FIELDS->>
    private JComboBox<ContactType> contactTypeCB;
    private JTextField nameTF;
    private JTextField surnamesTF;
    private JTextField addressTF;
    private JDateChooser birthDC;
    private JComboBox<Gender> genderCB;
    private JTextArea notesTA;

    private HobbyTable hobbyTable;
    private TelephoneTable telephoneTable;
    private MailTable mailTable;

    /**
     * Transform the current instance of the DTO into an entity
     *
     * @return the contact entity
     */
    public Contact toEntity() {
        Contact contact = new Contact();

        // Common fields
        contact.setType((ContactType) contactTypeCB.getSelectedItem());
        if (!nameTF.getText()
                   .equals(Placeholder.NAME.getText()))
            contact.setName(nameTF.getText());
        if (!addressTF.getText()
                      .equals(Placeholder.ADDRESS.getText()))
            contact.setAddress(addressTF.getText());
        if (!notesTA.getText()
                    .equals(Placeholder.NOTES.getText()))
            contact.setNotes(notesTA.getText());

        // Other fields
        if (surnamesTF.isEnabled() && surnamesTF.getText()
                                                .equals(Placeholder.SURNAMES.getText()))
            contact.setSurnames(surnamesTF.getText());
        if (genderCB.isEnabled())
            contact.setGender((Gender) genderCB.getSelectedItem());
        if (birthDC.isEnabled())
            contact.setBirthDate(birthDC.getDateFormatString());

        if (telephoneTable.isEnabled() && telephoneTable.getRowCount() > 0) {
            List<Telephone> telephones = new ArrayList<>();
            for (int i = 0; i < telephoneTable.getRowCount(); i++) {
                Telephone telephone = new Telephone();
                telephone.setNumber((String) telephoneTable.getValueAt(i, 0));
                telephone.setType  ((TelephoneType) telephoneTable.getValueAt(i, 1));
                telephones.add(telephone);
            }
            contact.setTelephones(telephones);
        }
        if (mailTable.isEnabled() && mailTable.getRowCount() > 0) {
            List<Mail> mails = new ArrayList<>();
            for (int i = 0; i < telephoneTable.getRowCount(); i++) {
                Mail mail = new Mail();
                mail.setName((String) telephoneTable.getValueAt(i, 0));
                mails.add(mail);
            }
            contact.setMails(mails);
        }

        // Always enable
        if (hobbyTable.getRowCount() > 0) {
            List<Hobby> hobbies = new ArrayList<>();
            for (int i = 0; i < hobbyTable.getRowCount(); i++) {
                TableModel hobbyModel = hobbyTable.getModel();
                Hobby hobby = new Hobby();
                hobby.setId  ((Long)   hobbyModel.getValueAt(i, 0));
                hobby.setName((String) hobbyModel.getValueAt(i, 1));
                hobbies.add(hobby);
            }
            contact.setHobbies(hobbies);
        }

        return contact;
    }

    /**
     * Clears the input fields and resets them to their default values or placeholders.
     * This method is used to clean the form fields and reset them to their initial state.
     */
    public void cleanFields() {
        // Common fields
        nameTF   .setText(Placeholder.NAME.getText());
        addressTF.setText(Placeholder.ADDRESS.getText());
        notesTA  .setText(Placeholder.NOTES.getText());

        // Person fields
        surnamesTF.setText(Placeholder.SURNAMES.getText());
        genderCB  .setSelectedIndex(0);

        // Person and Pet
        birthDC.cleanup();

        // Tables
        hobbyTable    .clean();
        telephoneTable.clean();
        mailTable     .clean();
    }

}