package com.quathar.contactbook.config;

import com.google.inject.AbstractModule;

import com.quathar.contactbook.data.config.DaoInjector;
import com.quathar.contactbook.service.ContactService;
import com.quathar.contactbook.service.HobbyService;
import com.quathar.contactbook.service.impl.ContactServiceImpl;
import com.quathar.contactbook.service.impl.HobbyServiceImpl;

/**
 * <h1>Service Injector</h1>
 *
 * @see AbstractModule
 * @since 2023-06-01
 * @version 1.0
 * @author Q
 */
public class ServiceInjector extends DaoInjector {

    @Override
    protected void configure() {
        super.configure();
        bind(ContactService.class).to(ContactServiceImpl.class);
        bind(HobbyService.class).to(HobbyServiceImpl.class);
    }

}
