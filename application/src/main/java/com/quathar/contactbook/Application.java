package com.quathar.contactbook;

import com.formdev.flatlaf.IntelliJTheme;
import com.quathar.contactbook.ui.Themes;
import com.quathar.contactbook.ui.frame.MainFrame;

import javax.swing.*;

public class Application {

	// <<-CONSTANTS->>
	private static final String CONSOLE = "Console";
	private static final String GRAPHIC_USER_INTERFACE = "Graphic User Interface";

	// <<-METHODS->>
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
//			case 0  -> new Menu(new DB());
			case 0  -> System.err.println("DEBUG: Accedió a la consola");
			case 1  -> new MainFrame().setVisible(true);
			default -> System.exit(0);
		}
	}

	public static void main(String[] args) {
		init();
//		ContactService contactService = new ContactServiceImpl(new ContactRepositoryImpl());
//		contactService.getAll().forEach(System.err::println);
	}

}
