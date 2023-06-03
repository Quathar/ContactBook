package com.quathar.contactbook.ui.model;

import com.google.inject.Inject;
import com.quathar.contactbook.data.entity.Hobby;
import com.quathar.contactbook.data.service.HobbyService;

import javax.swing.table.DefaultTableModel;
import java.io.Serial;
import java.util.List;

/**
 * <h1>HobbyModel</h1>
 *
 * Modelo que maneja informaci�n de la tabla <b>aficiones</b> en la BBDD.
 *
 * @since 2022-04-19
 * @see GeneralModel
 * @author Q
 */
public class HobbyModel extends DefaultTableModel {

    // <<-CONSTANTS->>
    @Serial
    private static final long serialVersionUID = 1L;

    private static final int COLUMNS = 2;
    private static final String[] COLUMN_NAMES = {
            "ID",
            "HOBBY"
    };

    // <<-FIELDS->>
    private final HobbyService _hobbyService;

    // <<-CONSTRUCTORS->>
    @Inject
    public HobbyModel(HobbyService hobbyService) {
        _hobbyService = hobbyService;
        setColumnIdentifiers(COLUMN_NAMES);
        createModel(COLUMNS);
    }

    // <<-METHODS->>
    private void fillModel(List<Hobby> hobbies) {
        for (int i = 0; i < getRowCount(); i++) {
            Hobby hobby = hobbies.get(i);
            super.setValueAt(hobby.getId(),      i, 0);
            super.setValueAt(hobby.getName(),    i, 1);
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

    public void addHobby(String newHobby) {
        Hobby hobby = new Hobby();
        hobby.setName(newHobby);
        _hobbyService.create(hobby);
        createModel(COLUMNS);
    }

    public void removeRow(int[] selectedRows) {
        // We change the order of the selected rows
        // so that they are deleted from highest to lowest index
        // so that there is no error.
        GeneralModel.flip(selectedRows);
        for (int selectedRow : selectedRows) {
            super.removeRow(selectedRow);
            Long id = (Long) getValueAt(selectedRow, 0);
            _hobbyService.deleteById(id);
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

//	public HobbyModel(DAO dao, boolean voidType) {
//		super(dao);
//		setColumnIdentifiers(ColumnNames);
//	}
//
//	public HobbyModel(DAO dao, int id) {
//		super(dao);
//		setColumnIdentifiers(ColumnNames);
//		createModelWhereIdC(TABLE, COLUMNAS, id);
//	}
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
//	public void searchWord(String word) {
//		super.searchWord(TABLE, word, COLUMNAS);
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
//	@Override
//	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//		super.setValueAt(aValue, rowIndex, columnIndex);
//		int id = Integer.parseInt(getValueAt(rowIndex, 0).toString());
//		String newName = aValue.toString();
//		dao.modifyHobby(id, newName);
//		frame.getContactHobbyTable().update();
//	}

    // OVERRIDE
//	@Override
//	public boolean isCellEditable(int rowIndex, int columnIndex) {
//		if (getColumnName(columnIndex) == ColumnNames[0])
//			return false;
//		else
//			return true;
//	}
//
//	// SETTER
//	public void setGUI(ContactBook frame) {
//		this.frame = frame;
//	}

}
