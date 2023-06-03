package com.quathar.contactbook.ui.frame.listener;

import com.quathar.contactbook.ui.table.ContactTable;
import lombok.Builder;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * <h1>ContactsBrowserDocumentListener</h1>
 *
 * @since 2023-06-02
 * @version 1.0
 * @author Q
 */
@Builder
public class ContactsBrowserDocumentListener implements DocumentListener {

    // <<-FIELDS->>
    private final String defaultText;
    private final JTextField contactBrowserTF;
    private final JComboBox<String> contactTypeCB;
    private final ContactTable contactTable;

    // <<-METHODS->>
    @Override
    public void insertUpdate(DocumentEvent e) {
        String contactWord = contactBrowserTF.getText();
        if (!contactWord.equals(defaultText)) {
            String contactType = (String) contactTypeCB.getSelectedItem();
            contactTable.update(contactType, contactWord);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        String contactWord = contactBrowserTF.getText();
        if (!contactWord.equals(defaultText)) {
            String contactType = (String) contactTypeCB.getSelectedItem();
            contactTable.update(contactType, contactWord);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {}

}
