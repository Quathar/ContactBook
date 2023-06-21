package com.quathar.contactbook.io;

import javax.swing.JOptionPane;
import java.io.Serial;

/**
 * <h1>MSG</h1>
 * <br>
 * Static class for displaying messages using JOptionPane.
 *
 * @since 2022-05-05
 * @version 1.1
 * @author Q
 */
public class MSG extends JOptionPane {

    // <<-CONSTANTS->>
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String WARNING_TITLE = "WARNING";
    private static final String YES = "Yes";
    private static final String NO = "No";

    // <<-METHODS->>
    /**
     * Displays a confirmation panel with 'OK' option.
     *
     * @param msg the message to show
     * @return the index of the chosen option
     */
    public static int confirmMessage(String msg) {
        return showConfirmDialog(null, msg);
    }

    /**
     * Displays a confirmation panel with the options 'Yes / No'.
     *
     * @param msg the message to show
     * @return the index of the chosen option
     */
    public static int defaultOptionMessage(String msg) {
        Object[] options = { YES, NO };
        return showOptionDialog(
                null,
                msg,
                WARNING_TITLE,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                NO
        );
    }

    /**
     * Displays a confirmation panel with the options 'Yes / No / Cancel'.
     *
     * @param msg the message to show
     * @return the index of the chosen option
     */
    public static int questionMessage(String msg) {
        return showConfirmDialog(
                null,
                msg,
                WARNING_TITLE,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
    }

    /**
     * Displays a warning panel.
     *
     * @param msg the message to show
     */
    public static void warningMessage(String msg) {
        showMessageDialog(
                null,
                msg,
                WARNING_TITLE,
                JOptionPane.WARNING_MESSAGE,
                null
        );
    }

}