package com.quathar.contactbook.ui.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.quathar.contactbook.config.AppConfiguration;
import com.quathar.contactbook.data.embeddable.Mail;
import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.service.ContactService;

import javax.swing.table.DefaultTableModel;
import java.io.Serial;
import java.util.List;

/**
 * <h1>MailModel</h1>
 *
 * Modelo para manipular la informaci�n de la tabla <b>'correos'</b> de la BBDD.
 *
 * @since 2022-04-14
 * @see GeneralModel
 * @author Q
 */
public class MailModel extends DefaultTableModel {

    // <<-CONSTANTS->>
    @Serial
    private static final long serialVersionUID = 1L;

    private static final int COLUMNS = 1;
    private static final String[] COLUMN_NAMES = { "MAIL" };

    // <<-FIELDS->>
    private final ContactService _contactService;

    // <<-CONSTRUCTORS->>
    public MailModel() {
        this(0L);
    }

    public MailModel(Long id) {
        Injector injector = Guice.createInjector(new AppConfiguration());
        _contactService = injector.getInstance(ContactService.class);
        setColumnIdentifiers(COLUMN_NAMES);
        createModel(COLUMNS, id);
    }

    // <<-METHODS->>
    private void fillModel(List<Mail> telephones) {
        for (int i = 0; i < getRowCount(); i++) {
            Mail mail = telephones.get(i);
            super.setValueAt(mail.getName(), i, 0);
        }
    }

    private void create(Contact contact, int columnCount) {
        List<Mail> mails = contact.getMails();
        setColumnCount(columnCount);
        setRowCount(mails.size());
        fillModel(mails);
    }

    public void createModel(int columnCount, Long id) {
        // If the id is 0 it means the contact does not exist yet
        // i.e. is a new contact
        if (id != 0) {
            Contact contact = _contactService.getById(id);
            create(contact, columnCount);
        } else setColumnCount(columnCount);
    }

	public void removeRows(int[] selectedRows) {
        if (selectedRows.length > 1) GeneralModel.flip(selectedRows);
	}
	
}
