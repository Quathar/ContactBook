package com.quathar.contactbook.ui.frame.listener;

import com.quathar.contactbook.ui.frame.helper.Placeholder;

import lombok.Builder;

import javax.swing.text.JTextComponent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * <h1>Contacts Browser Focus Listener</h1>
 *
 * @since 2023-06-02
 * @version 1.0
 * @author Q
 */
@Builder
public class PlaceholderFocusListener implements FocusListener {

    // <<-FIELDS->>
    private final String defaultText;
    private final JTextComponent inputTC;

    // <<-METHODS->>
    @Override
    public void focusGained(FocusEvent e) {
        if (inputTC.getText().equals(defaultText))
            inputTC.setText(Placeholder.BLANK.getText());
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (inputTC.getText().isBlank())
            inputTC.setText(defaultText);
    }

}
