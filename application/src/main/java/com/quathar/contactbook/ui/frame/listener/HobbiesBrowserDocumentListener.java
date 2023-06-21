package com.quathar.contactbook.ui.frame.listener;

import com.quathar.contactbook.ui.component.table.ContactHobbyTable;
import com.quathar.contactbook.ui.component.table.HobbyTable;

import lombok.Builder;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * <h1>Hobbies Browser Document Listener</h1>
 *
 * @since 2023-06-02
 * @version 1.0
 * @author Q
 */
@Builder
public class HobbiesBrowserDocumentListener implements DocumentListener {

    // <<-FIELDS->>
    private final String defaultText;
    private final JTextField hobbyBrowserTF;
    private final HobbyTable hobbyTable;
    private final ContactHobbyTable contactHobbyTable;

    // <<-METHODS->>
    private void action() {
        String hobbyName = hobbyBrowserTF.getText();
        if (!hobbyName.equals(defaultText)) {
            hobbyTable       .update(hobbyName);
            contactHobbyTable.update(hobbyName);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        action();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        action();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        action();
    }

}
