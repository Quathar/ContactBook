package com.quathar.contactbook.data.config;

import com.quathar.contactbook.data.dao.ContactDao;
import com.quathar.contactbook.data.dao.HobbyDao;
import com.quathar.contactbook.data.dao.impl.ContactDaoImpl;
import com.quathar.contactbook.data.dao.impl.HobbyDaoImpl;

/**
 * <h1>DAO Injector</h1>
 *
 * @see DataConfiguration
 * @since 2023-06-01
 * @version 1.0
 * @author Q
 */
public class DaoInjector extends DataConfiguration {

    @Override
    protected void configure() {
        bind(ContactDao.class).to(ContactDaoImpl.class);
        bind(HobbyDao.class).to(HobbyDaoImpl.class);
    }

}
