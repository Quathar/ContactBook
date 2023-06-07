package com.quathar.contactbook.ui.component.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.quathar.contactbook.config.AppConfiguration;
import com.quathar.contactbook.data.embeddable.Telephone;
import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.service.ContactService;

import javax.swing.table.DefaultTableModel;
import java.io.Serial;
import java.util.List;

/**
 * MailModel.<br><br>
 * 
 * Modelo para manipular la informaci�n de la tabla <b>'telefonos'<b> de la BBDD.
 *
 * @since 2022-04-14
 * @version 2.0
 * @author Q
 */
public class TelephoneModel extends DefaultTableModel {

    // <<-CONSTANTS->>
    @Serial
    private static final long serialVersionUID = 1L;

    private static final int COLUMNS = 2;
    private static final String[] COLUMN_NAMES = {
            "TELEPHONE",
            "TYPE"
    };

    // <<-FIELDS->>
    private final ContactService _contactService;

    // <<-CONSTRUCTORS->>
    public TelephoneModel() {
        this(0L);
    }

    public TelephoneModel(Long id) {
        Injector injector = Guice.createInjector(new AppConfiguration());
        _contactService = injector.getInstance(ContactService.class);
        setColumnIdentifiers(COLUMN_NAMES);
        createModel(COLUMNS, id);
    }

    // <<-METHODS->>
    private void fillModel(List<Telephone> telephones) {
        for (int i = 0; i < getRowCount(); i++) {
            Telephone telephone = telephones.get(i);
            super.setValueAt(telephone.getNumber(), i, 0);
            super.setValueAt(telephone.getType(),   i, 1);
        }
    }

    private void create(Contact contact, int columnCount) {
        List<Telephone> telephones = contact.getTelephones();
        setColumnCount(columnCount);
        setRowCount(telephones.size());
        fillModel(telephones);
    }

    public void createModel(int columnCount, Long id) {
        // If the id is 0 it means the contact does not exist yet
        // i.e. is a new contact
        if (id > 0) {
            Contact contact = _contactService.getById(id);
            create(contact, columnCount);
        } else setColumnCount(columnCount);
    }

    public void removeRows(int[] selectedRows) {
        if (selectedRows.length > 1) GeneralModel.flip(selectedRows);
        for (int selectedRow : selectedRows)
            super.removeRow(selectedRow);
    }

}
