package com.quathar.contactbook.ui.component.table;

import com.quathar.contactbook.Application;
import com.quathar.contactbook.ui.component.model.ContactHobbyModel;

import java.io.Serial;

/**
 * <h1>ContactHobbyTable</h1>
 *
 * Componente JTable de la tabla <b>contactos_aficiones</b> en la BBDD.
 *
 * @since 2022-05-04
 * @author Q
 */
public class ContactHobbyTable extends GeneralTable {

    // <<-CONSTANTS->>
	@Serial
    private static final long serialVersionUID = 1L;

    // <<-FIELDS->>
    private final ContactHobbyModel _contactHobbyModel;

    // <<-CONSTRUCTOR->>
	public ContactHobbyTable() {
        _contactHobbyModel = new ContactHobbyModel();
		setModel(_contactHobbyModel);
		place(new int[] {0, 1, 2, 3},
              new int[] {1, 2},
              (int) (Application.SCREEN_SIZE.width * 0.105));
	}

    // <<-METHODS->>
    public void update() {
        _contactHobbyModel.update();
        place(new int[] {0, 1, 2, 3}, new int[] {1, 2}, (int) (Application.SCREEN_SIZE.width * 0.105));
    }

	public void update(String name) {
        _contactHobbyModel.update(name);
		place(new int[] {0, 1, 2, 3}, new int[] {1, 2}, 200);
	}

//	// GETTER
//	public ContactHobbyModel getModel() {
//		return chModel;
//	}
	
}
