package com.quathar.contactbook.ui.component.model;

import com.google.inject.Guice;
import com.google.inject.Injector;

import com.quathar.contactbook.config.AppConfiguration;
import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.entity.Hobby;
import com.quathar.contactbook.service.ContactService;
import com.quathar.contactbook.service.HobbyService;

import javax.swing.table.DefaultTableModel;
import java.io.Serial;
import java.util.List;

/**
 * <h1>HobbyModel</h1>
 * <br>
 * Model that handles information from the <b>hobbies</b> table in the database.
 *
 * @since 2022-04-19
 * @version 2.0
 * @author Q
 */
public class HobbyModel extends DefaultTableModel {

    // <<-CONSTANTS->>
    @Serial
    private static final long serialVersionUID = 1L;

    private static final int COLUMNS = 2;
    private static final String[] COLUMN_NAMES = { "ID", "HOBBY" };

    // <<-FIELDS->>
    private final HobbyService   _hobbyService;
    private final ContactService _contactService;

    // <<-CONSTRUCTORS->>
    public HobbyModel() {
        this(null);
    }

    public HobbyModel(Long id) {
        Injector injector = Guice.createInjector(new AppConfiguration());
        _hobbyService   = injector.getInstance(HobbyService.class);
        _contactService = injector.getInstance(ContactService.class);
        setColumnIdentifiers(COLUMN_NAMES);
        if (id == null)  createModel(COLUMNS);
        else if (id > 0) createModelWithId(COLUMNS, id);
    }

    // <<-METHODS->>
    private void fillModel(List<Hobby> hobbies) {
        for (int i = 0; i < getRowCount(); i++) {
            Hobby hobby = hobbies.get(i);
            super.setValueAt(hobby.getId(),   i, 0);
            super.setValueAt(hobby.getName(), i, 1);
        }
    }

    private void create(List<Hobby> hobbies, int columnCount) {
        setColumnCount(columnCount);
        setRowCount(hobbies.size());
        fillModel(hobbies);
    }

    public void createModel(int columnCount) {
        List<Hobby> hobbies = _hobbyService.getAll();
        create(hobbies, columnCount);
    }

    public void createModelWithId(int columnCount, Long id) {
        Contact contact = _contactService.getById(id);
        create(contact.getHobbies(), columnCount);
    }

    public void update(String name) {
        List<Hobby> hobbies = _hobbyService.getAllByParams(name);
        create(hobbies, COLUMNS);
    }

    public void addNewHobby(String newHobby) {
        Hobby hobby = new Hobby();
        hobby.setName(newHobby);
        _hobbyService.create(hobby);
        createModel(COLUMNS);
    }

    public void addHobby(String hobbyName) {
        Object[] data = { null, hobbyName };
        insertRow(getRowCount(), data);
    }

    public void removeRows(int[] selectedRows) {
        // We change the order of the selected rows
        // so that they are deleted from highest to lowest index
        // so that there is no error.
        if (selectedRows.length > 1) GeneralModel.flip(selectedRows);
        for (int selectedRow : selectedRows)
            super.removeRow(selectedRow);
    }

    public void removeRowsPermanently(int[] selectedRows) {
        if (selectedRows.length > 1) GeneralModel.flip(selectedRows);
        for (int selectedRow : selectedRows) {
            // Delete from the Database
            _hobbyService.deleteById((Long) getValueAt(selectedRow, 0));
            super.removeRow(selectedRow);
        }
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        super.setValueAt(aValue, row, column);
        Hobby contact = new Hobby();
        Long id       = (Long)   getValueAt(row, 0);
        contact.setName((String) getValueAt(row, 1));
        _hobbyService.update(id, contact);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return !getColumnName(columnIndex).equals(COLUMN_NAMES[0]);
    }

    // CAMPOS

//
//	// M�TODOS
//	public void insertHobby(String hobby) {
//		dao.registerHobby(hobby);
//		changeView(TABLE, COLUMNAS);
//	}
//
//	public void addHobby(String hobby) {
//		Object[] data = {0, hobby};
//		insertRow(getRowCount(), data);
//	}
//
//	private void checkStops(int stop) {
//		if (stop > 0) {
//			String[] msg = {
//					"Est� intentando borrar 1 afici�n utilizada",
//					"Est� intentando borrar " + stop + " aficiones utilizadas"
//			};
//			MSG.warningMessage(stop == 1 ? msg[0] : msg[1]);
//		}
//	}
//
//	public void deleteSelectedRows(int[] selectedRows) {
//		if (selectedRows.length > 1)
//			flip(selectedRows);
//
//		int id_h;
//		int stop = 0;
//		for (int i = 0; i < selectedRows.length; i++) {
//			id_h = Integer.parseInt(getValueAt(selectedRows[i], 0).toString());
//			if (!dao.checkIdH(id_h)) {
//				dao.unregisterRow(TABLE, id_h);
//				removeRow(selectedRows[i]);
//			} else
//				stop++;
//		}
//		checkStops(stop);
//	}

//	public void deleteSelectedRows(int id_c, int[] selectedRows) {
//		if (selectedRows.length > 1)
//			flip(selectedRows);
//
//		int id_h;
//		for (int i = 0; i < selectedRows.length; i++) {
//			id_h = Integer.parseInt(getValueAt(selectedRows[i], 0).toString());
//			if (id_h != 0) {
//	 			dao.unregisterHobbyWhereIdC(id_c, id_h);
//	 			removeRow(selectedRows[i]);
//			} else
//				removeRow(selectedRows[i]);
//		}
//	}
//
//	// SETTER
//	public void setGUI(ContactBook frame) {
//		this.frame = frame;
//	}

}
