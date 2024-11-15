package com.quathar.contactbook.ui.component.table;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.quathar.contactbook.Application;
import com.quathar.contactbook.config.ServiceInjector;
import com.quathar.contactbook.ui.component.model.TelephoneContactModel;

import java.io.Serial;

/**
 * <h1>TelephoneContactTable</h1>
 *
 * Componente JTable para visualizar a TODOS los contactos con sus tel�fonos.
 *
 * @since 2022-05-15
 * @version 1.0
 * @author Q
 */
public class TelephoneContactTable extends GeneralTable {

    // <<-CONSTANTS->>
    @Serial
	private static final long serialVersionUID = 1L;
    // <<-FIELDS->>
	private final TelephoneContactModel _telephoneContactModel;

    // <<-CONSTRUCTOR->>
	public TelephoneContactTable() {
        Injector injector = Guice.createInjector(new ServiceInjector());
		_telephoneContactModel = injector.getInstance(TelephoneContactModel.class);
		setModel(_telephoneContactModel);
        center(new int[] {1, 2});
        resize(new int[] {0}, (int) (Application.SCREEN_SIZE.width * 0.105));
		resize(new int[] {1}, (int) (Application.SCREEN_SIZE.width * 0.15));
	}

    // <<-METHODS->>
	public void update() {
		_telephoneContactModel.update();
        center(new int[] {1, 2});
        resize(new int[] {0}, (int) (Application.SCREEN_SIZE.width * 0.105));
		resize(new int[] {1}, (int) (Application.SCREEN_SIZE.width * 0.15));
	}
	
}
