package com.quathar.contactbook.ui.frame.listener;

import com.quathar.contactbook.data.enumerator.ContactType;
import com.quathar.contactbook.data.enumerator.TelephoneType;
import com.quathar.contactbook.model.dto.ContactDTO;
import com.quathar.contactbook.ui.frame.helper.Placeholder;
import lombok.Builder;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <h1>ActionListener</h1>
 *
 * @since 2023-06-04
 * @version 2.0
 * @author Q
 */
@Builder
public class ChangeActionListener implements ActionListener {

    // <<-FIELDS->>
    private ContactDTO contactDTO;
    private JComboBox<ContactType> contactTypeCB;
    private JComboBox<TelephoneType> telephoneTypeCB;
    private JTextField telephoneTF;
    private JTextField mailTF;
    private JButton btnAddTelephone;
    private JButton btnAddMail;
    private JButton btnDeleteTelephone;
    private JButton btnDeleteMail;

    // <<-METHODS->>
    @Override
    public void actionPerformed(ActionEvent e) {
        ContactType contactType = (ContactType) contactTypeCB.getSelectedItem();

        switch (contactType) {
            case PERSON -> {
                contactDTO.getSurnamesTF()
                          .setText(Placeholder.SURNAMES.getText());
                enablePersonInputs(true);
                enableTelephonesAndMails(true);
            }
            case COMPANY -> {
                contactDTO.getSurnamesTF()
                          .setText(Placeholder.BLANK.getText());
                enablePersonInputs(false);
                contactDTO.getBirthDC()
                          .cleanup();
                contactDTO.getGenderCB()
                          .setSelectedIndex(0);
                enableTelephonesAndMails(true);
            }
            case PET -> {
                contactDTO.getSurnamesTF()
                          .setText(Placeholder.BLANK.getText());
                enablePersonInputs(false);
                contactDTO.getBirthDC()
                          .setEnabled(true);
                contactDTO.getGenderCB()
                          .setSelectedIndex(0);
                enableTelephonesAndMails(false);
                contactDTO.getTelephoneTable()
                          .clean();
                contactDTO.getMailTable()
                          .clean();
            }
        }
    }

    private void enablePersonInputs(boolean enable) {
        contactDTO.getSurnamesTF()
                  .setEnabled(enable);
        contactDTO.getBirthDC()
                  .setEnabled(enable);
        contactDTO.getGenderCB()
                  .setEnabled(enable);
    }

    private void enableTelephonesAndMails(boolean enable) {
        // Related to telephones
        contactDTO        .getTelephoneTable()
                          .setEnabled(enable);
        telephoneTF       .setText(Placeholder.TELEPHONES.getText());
        telephoneTF       .setEnabled(enable);
        telephoneTypeCB   .setEnabled(enable);
        btnAddTelephone   .setEnabled(enable);
        btnDeleteTelephone.setEnabled(enable);

        // Related to mails
        contactDTO   .getMailTable()
                     .setEnabled(enable);
        mailTF       .setText(Placeholder.MAILS.getText());
        mailTF       .setEnabled(enable);
        btnAddMail   .setEnabled(enable);
        btnDeleteMail.setEnabled(enable);
    }

}
