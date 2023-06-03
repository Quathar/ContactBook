package com.quathar.contactbook.ui.frame.listener;

import lombok.Builder;

import javax.swing.JTextField;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * <h1>ContactsBrowserFocusListener</h1>
 *
 * @since 2023-06-02
 * @version 1.0
 * @author Q
 */
@Builder
public class BrowserFocusListener implements FocusListener {

    // <<-FIELDS->>
    private final String defaultText;
    private final JTextField browserTF;

    // <<-METHODS->>
    @Override
    public void focusGained(FocusEvent e) {
        if (browserTF.getText().equals(defaultText))
            browserTF.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (browserTF.getText().isBlank())
            browserTF.setText(defaultText);
    }

}
