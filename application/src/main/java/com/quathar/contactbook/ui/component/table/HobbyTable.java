package com.quathar.contactbook.ui.component.table;

import com.quathar.contactbook.ui.component.model.HobbyModel;

import java.io.Serial;

/**
 * <h1>HobbyTable</h1>
 *
 * JTable component to display hobbies.
 *
 * @since 2022-05-04
 * @version 2.0
 * @author Q
 */
public class HobbyTable extends GeneralTable {

    // <<-CONSTANTS->>
    @Serial
    private static final long serialVersionUID = 1L;

    // <<-FIELDS->>
    private final HobbyModel _hobbyModel;

    // <<-CONSTRUCTORS->>
    public HobbyTable() {
        _hobbyModel = new HobbyModel();
        setModel(_hobbyModel);
        removeColumn(columnModel.getColumn(0));
    }

    public HobbyTable(Long id) {
        _hobbyModel = new HobbyModel(id);
        setModel(_hobbyModel);
        removeColumn(columnModel.getColumn(0));
    }

    // <<-METHODS->>
    public void addNewHobby(String hobby) {
        _hobbyModel.addNewHobby(hobby);
        removeColumn(columnModel.getColumn(0));
    }

    public void addHobby(String hobby) {
        _hobbyModel.addHobby(hobby);
    }

    public void update(String name) {
        _hobbyModel.update(name);
        removeColumn(columnModel.getColumn(0));
    }

    public void deleteRows() {
        _hobbyModel.removeRows(getSelectedRows());
    }

    public void deleteRowsPermanently() {
        _hobbyModel.removeRowsPermanently(getSelectedRows());
    }

    public void clean() {
        _hobbyModel.setRowCount(0);
    }

//	public void addHobby(String hobby) {
//		hModel.addHobby(hobby);
//	}

//	public void deleteSelectedRows(int id_c) {
//		hModel.deleteSelectedRows(id_c, getSelectedRows());
//	}
//
//	// GETTER
//	public HobbyModel getModel() {
//		return hModel;
//	}

}
