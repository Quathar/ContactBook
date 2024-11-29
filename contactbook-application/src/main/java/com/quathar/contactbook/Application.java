package com.quathar.contactbook;

import com.formdev.flatlaf.IntelliJTheme;

import com.quathar.contactbook.data.DataLoader;
import com.quathar.contactbook.ui.Theme;
import com.quathar.contactbook.ui.Themes;
import com.quathar.contactbook.ui.frame.MainFrame;
import com.quathar.contactbook.ui.frame.helper.ViewTitle;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.InputStream;

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

    // <<-METHODS->>
    /**
     * Change the agenda theme.
     *
     * @param themeIndex the new theme's index
     * @param themeType the theme's type
     */
    public static void changeTheme(int themeIndex, Theme.Type themeType) {
        InputStream newTheme = Themes.getTheme(themeIndex, themeType);
        IntelliJTheme.setup(newTheme);
        new MainFrame(themeIndex, themeType, ViewTitle.SETTINGS).setVisible(true);
    }

    /**
     * Format the agenda and start it again.
     */
    public static void format() {
        DataLoader.format();
        Application.execute();
    }

    /**
     * Launch the interface application with 'Dracula Contrast' Theme
     */
    private static void execute() {
        InputStream defaultTheme = Themes.getTheme(3, Theme.Type.DARK);
        IntelliJTheme.setup(defaultTheme);
        new MainFrame().setVisible(true);
    }

    public static void main(String... quathar) {
        if (quathar.length > 0 && "data".equals(quathar[0]))
            DataLoader.load();
        else Application.execute();
    }

}
