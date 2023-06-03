package com.quathar.contactbook.ui.table;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.quathar.contactbook.config.AppConfiguration;
import com.quathar.contactbook.ui.model.HobbyModel;

import java.io.Serial;

/**
 * <h1>HobbyTable</h1>
 *
 * Componente JTable de la tabla <b>'aficiones'</b> en la BBDD.
 *
 * @since 2022-05-04
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
        Injector injector = Guice.createInjector(new AppConfiguration());
        _hobbyModel = injector.getInstance(HobbyModel.class);
        setModel(_hobbyModel);
        removeColumn(columnModel.getColumn(0));
    }

    // <<-METHODS->>
	public void addHobby(String hobby) {
        _hobbyModel.addHobby(hobby);
        removeColumn(columnModel.getColumn(0));
	}

    public void deleteRows() {
        _hobbyModel.removeRow(getSelectedRows());
    }
	
	// CONSTRUCTORES
//	public HobbyTable(ContactBook frame, DAO dao) {
//		hModel = new HobbyModel(dao);
//		hModel.setGUI(frame);
//		setModel(hModel);
//		place(new int[] {0}, new int[] {1}, 300);
//	}
//
//	public HobbyTable(DAO dao, boolean falso) {
//		hModel = new HobbyModel(dao, falso);
//		setModel(hModel);
//		removeColumn(0);
//	}
//
//	public HobbyTable(DAO dao, int id) {
//		hModel = new HobbyModel(dao, id);
//		setModel(hModel);
//		removeColumn(0);
//	}

//	public void addHobby(String hobby) {
//		hModel.addHobby(hobby);
//	}
//
//	public void addToComboBox(JComboBox<String> comboBox) {
//		int[] selectedRows = getSelectedRows();
//		for (int i = 0; i < getSelectedRowCount(); i++) {
//			String item = hModel.getValueAt(selectedRows[i], 1).toString();
//			comboBox.addItem(item);
//		}
//	}
//
//	public void update(String word) {
//		hModel.searchWord(word);
//		place(new int[] {0}, new int[] {1}, 300);
//	}
	
//	public void deleteSelectedRows() {
//		hModel.deleteSelectedRows(getSelectedRows());
//	}
//
//	public void deleteSelectedRows(int id_c) {
//		hModel.deleteSelectedRows(id_c, getSelectedRows());
//	}
//
//	public void clean() {
//		super.clean(hModel);
//	}
//
//	// GETTER
//	public HobbyModel getModel() {
//		return hModel;
//	}
	
}
