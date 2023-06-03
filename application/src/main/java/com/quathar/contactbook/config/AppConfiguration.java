package com.quathar.contactbook.config;

import com.quathar.contactbook.data.config.DataConfiguration;
import com.quathar.contactbook.ui.table.ContactTable;

/**
 * <h1>AppConfiguration</h1>
 *
 * @since 2023-06-02
 * @see DataConfiguration
 * @version 1.0
 * @author Q
 */
public class AppConfiguration extends DataConfiguration {

    @Override
    protected void configure() {
        // This is necessary to have all the configuration
        // that we did in DataConfiguration (Daos, Services),
        // which extends AbstractModule
        super.configure();
        bind(ContactTable.class);
//        configureContact();
//        configureHobby();
    }

}
