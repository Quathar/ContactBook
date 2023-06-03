package com.quathar.contactbook.ui.frame.listener;

import com.quathar.contactbook.ui.table.ContactHobbyTable;
import com.quathar.contactbook.ui.table.HobbyTable;
import lombok.Builder;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * <h1>HobbiesBrowserDocumentListener</h1>
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
    @Override
    public void insertUpdate(DocumentEvent e) {
//        String hobbyWord = hobbyBrowserTF.getText();
//        if (!hobbyWord.equals(defaultText)) {
//            hobbyTable.update(hobbyWord);
//            contactHobbyTable.update(hobbyWord);
//        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
//        String hobbyWord = hobbyBrowserTF.getText();
//        if (!hobbyWord.equals(defaultText)) {
//            hobbyTable.update(hobbyWord);
//            contactHobbyTable.update(hobbyWord);
//        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {}

}
