package com.quathar.contactbook.ui.frame.listener;

import com.quathar.contactbook.data.enumerator.ContactType;
import com.quathar.contactbook.data.enumerator.TelephoneType;
import com.quathar.contactbook.model.dto.ContactInfoDTO;
import com.quathar.contactbook.ui.frame.helper.Placeholder;

import lombok.Builder;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <h1>Change Contact Type Action Listener</h1>
 *
 * @since 2023-06-04
 * @version 2.0
 * @author Q
 */
@Builder
public class ChangeActionListener implements ActionListener {

    // <<-FIELDS->>
    private ContactInfoDTO contactInfoDTO;
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
        ContactType contactType = (ContactType) contactInfoDTO.getContactTypeCB().getSelectedItem();

        switch (contactType) {
            case PERSON -> {
                contactInfoDTO.getSurnamesTF()
                              .setText(Placeholder.SURNAMES.getText());
                enablePersonInputs(true);
                enableTelephonesAndMails(true);
            }
            case COMPANY -> {
                contactInfoDTO.getSurnamesTF()
                              .setText(Placeholder.BLANK.getText());
                enablePersonInputs(false);
                contactInfoDTO.getBirthDC()
                              .cleanup();
                contactInfoDTO.getGenderCB()
                              .setSelectedIndex(0);
                enableTelephonesAndMails(true);
            }
            case PET -> {
                contactInfoDTO.getSurnamesTF()
                              .setText(Placeholder.BLANK.getText());
                enablePersonInputs(false);
                contactInfoDTO.getBirthDC()
                              .setEnabled(true);
                contactInfoDTO.getGenderCB()
                              .setSelectedIndex(0);
                enableTelephonesAndMails(false);
                telephoneTF   .setText(Placeholder.BLANK.getText());
                contactInfoDTO.getTelephoneTable()
                              .clean();
                mailTF        .setText(Placeholder.BLANK.getText());
                contactInfoDTO.getMailTable()
                              .clean();
            }
        }
    }

    private void enablePersonInputs(boolean enable) {
        contactInfoDTO.getSurnamesTF()
                      .setEnabled(enable);
        contactInfoDTO.getBirthDC()
                      .setEnabled(enable);
        contactInfoDTO.getGenderCB()
                      .setEnabled(enable);
    }

    private void enableTelephonesAndMails(boolean enable) {
        // Related to telephones
        contactInfoDTO    .getTelephoneTable()
                          .setEnabled(enable);
        telephoneTF       .setText(Placeholder.TELEPHONES.getText());
        telephoneTF       .setEnabled(enable);
        telephoneTypeCB   .setEnabled(enable);
        btnAddTelephone   .setEnabled(enable);
        btnDeleteTelephone.setEnabled(enable);

        // Related to mails
        contactInfoDTO.getMailTable()
                      .setEnabled(enable);
        mailTF        .setText(Placeholder.MAILS.getText());
        mailTF        .setEnabled(enable);
        btnAddMail    .setEnabled(enable);
        btnDeleteMail .setEnabled(enable);
    }

}
