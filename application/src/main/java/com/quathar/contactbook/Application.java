package com.quathar.contactbook;

import com.formdev.flatlaf.IntelliJTheme;
import com.quathar.contactbook.ui.Themes;
import com.quathar.contactbook.ui.frame.MainFrame;
import com.quathar.contactbook.ui.frame.helper.ViewTitle;

import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * <h1>Application</h1>
 * <br>
 * <p>
 *     Main class to launch the application.<br>
 *     <br>
 *     It has an options panel by default.<br>
 *     It also serves to change the theme and format the agenda.
 * </p>
 *
 * @since 2022-04-05
 * @version 2.0
 * @author Q
 */
public class Application {

    // <<-CONSTANTS->>
    /**
     * Screen dimensions
     */
    public  static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final String CONSOLE = "Console";
    private static final String GRAPHIC_USER_INTERFACE = "Graphic User Interface";

    // <<-METHODS->>
    /**
     * Change the agenda theme.
     *
     * @param themeIndex the new theme's index
     * @param themeType the theme's type
     */
    public static void changeTheme(int themeIndex, String themeType) {
        IntelliJTheme.setup(Themes.getTheme(themeIndex, themeType));
        new MainFrame(themeIndex, themeType, ViewTitle.SETTINGS).setVisible(true);
    }

    /**
     * Format the agenda and start it again.
     */
    public static void format() {
        DataApplication.format();
        init();
    }

    /**
     * Launch the application with an options panel.
     */
    private static void init() {
        String title = "Access";
        String msg = "How do you want to access the Contact Book?";
        String[] options = {CONSOLE, GRAPHIC_USER_INTERFACE};

        IntelliJTheme.setup(Themes.getTheme(0, Themes.LIGHT));
        switch (JOptionPane.showOptionDialog(
                null,
                msg,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                GRAPHIC_USER_INTERFACE
        )) {
//			case 0  -> new Menu();
            case 0  -> System.err.println("DEBUG: Accedió a la consola");
            case 1  -> new MainFrame().setVisible(true);
            default -> System.exit(0);
        }
    }

    public static void main(String[] args) {
//		init();
        fastInit();
    }

    /**
     * Launch the interface application with 'Dracula Contrast' Theme
     */
    private static void fastInit() {
        IntelliJTheme.setup(Themes.getTheme(3, Themes.DARK));
        new MainFrame().setVisible(true);
    }

}
