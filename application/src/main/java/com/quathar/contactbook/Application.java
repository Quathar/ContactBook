package com.quathar.contactbook;

import com.formdev.flatlaf.IntelliJTheme;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.quathar.contactbook.config.AppConfiguration;
import com.quathar.contactbook.data.enumerator.ContactType;
import com.quathar.contactbook.data.service.ContactService;
import com.quathar.contactbook.ui.Themes;
import com.quathar.contactbook.ui.frame.MainFrame;
import com.quathar.contactbook.ui.frame.helper.ViewTitle;

import javax.swing.*;
import java.awt.*;

public class Application {

	// <<-CONSTANTS->>
	public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
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

	private static void init() {
		String title = "Access";
		String msg = "How do you want to access the Agenda?";
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
//		test();
	}

	private static void fastInit() {
		IntelliJTheme.setup(Themes.getTheme(3, Themes.DARK));
		new MainFrame().setVisible(true);
	}

	private static void test() {
		Injector injector = Guice.createInjector(new AppConfiguration());
		ContactService contactService = injector.getInstance(ContactService.class);

		int num = 25;
		// GET ALL
		System.out.println("=".repeat(num));
		System.out.println("ALL CONTACTS");
		System.out.println("=".repeat(num));
		contactService.getAll()
					  .forEach(System.out::println);

		// GET BY ID
		System.out.println("=".repeat(num));
		System.out.println("CONTACT WITH ID 4");
		System.out.println("=".repeat(num));
		System.out.println(contactService.getById(4L));

		// GET ALL BY PARAMS (type, name)
		System.out.println("=".repeat(num));
		System.out.println("ALL PET CONTACTS");
		System.out.println("=".repeat(num));
		contactService.getAllByParams(ContactType.PET, null)
					  .forEach(System.out::println);
	}

}
