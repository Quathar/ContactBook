package com.quathar.contactbook.ui.component.table;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.quathar.contactbook.Application;
import com.quathar.contactbook.config.ServiceInjector;
import com.quathar.contactbook.ui.component.model.MailContactModel;

import java.io.Serial;

/**
 * <h1>MailContactTable</h1>
 *
 * Componente JTable para visualizar a TODOS los contactos con sus correos.
 *
 * @since 2022-05-15
 * @version 1.0
 * @author Q
 */
public class MailContactTable extends GeneralTable {

    // <<-CONSTANTS->>
    @Serial
	private static final long serialVersionUID = 1L;

    // <<-FIELDS->>
	private final MailContactModel mailContactModel;

    // <<-CONSTRUCTOR->>
	public MailContactTable() {
        Injector injector = Guice.createInjector(new ServiceInjector());
		mailContactModel = injector.getInstance(MailContactModel.class);
		setModel(mailContactModel);
		resize(new int[] {1}, (int) (Application.SCREEN_SIZE.width * 0.1));
	}

    // <<-METHODS->>
	public void update() {
		mailContactModel.update();
		resize(new int[] {1}, (int) (Application.SCREEN_SIZE.width * 0.1));
	}
	
}
