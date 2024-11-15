package com.quathar.contactbook.data.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.quathar.contactbook.data.dao.ContactDao;
import com.quathar.contactbook.data.dao.HobbyDao;
import com.quathar.contactbook.data.dao.impl.ContactDaoImpl;
import com.quathar.contactbook.data.dao.impl.HobbyDaoImpl;
import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.entity.Hobby;
import com.quathar.contactbook.data.service.ContactService;
import com.quathar.contactbook.data.service.HobbyService;
import com.quathar.contactbook.data.service.impl.ContactServiceImpl;
import com.quathar.contactbook.data.service.impl.HobbyServiceImpl;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

/**
 * <h1>DataConfiguration</h1>
 * <br>
 * Guice configuration for 'data' module
 *
 * @since 2023-06-01
 * @version 1.0
 * @author Q
 */
public class DataConfiguration extends AbstractModule {

    // <<-CONSTANT->>
    /**
     * hibernate configuration file (hibernate.properties) path
     */
    private static final Path HIBERNATE_PROPERTIES_PATH = Path.of(
            System.getProperty("user.dir"), "contactbook-data",
            "src", "main", "resources", "hibernate.properties");

    // <<-METHODS->>
    /**
     * Configures hibernate.
     *
     * @return the hibernate configuration object
     */
    @Provides
    public Configuration providesConfiguration() {
        try (FileInputStream fileInputStream = new FileInputStream(HIBERNATE_PROPERTIES_PATH.toString())) {
            Properties hibernateProperties = new Properties();
            hibernateProperties.load(fileInputStream);
            return new Configuration()
                    .addProperties(hibernateProperties)
                    .addPackage("com.quathar.contactbook")
                    .addAnnotatedClass(Contact.class)
                    .addAnnotatedClass(Hobby.class);
        } catch (IOException ioE) {
            System.err.println("ERROR: IOException :: File not found");
            System.exit(1);
            return null;
        }
    }

    @Override
    protected void configure() {
        configureContactImplementations();
        configureHobbyImplementations();
    }

    /**
     * Configures the implementations of the Contact entity to the interfaces.
     */
    protected void configureContactImplementations() {
        bind(ContactDao.class)    .to(ContactDaoImpl.class);
        bind(ContactService.class).to(ContactServiceImpl.class);
    }

    /**
     * Configures the implementations of the Hobby entity to the interfaces.
     */
    protected void configureHobbyImplementations() {
        bind(HobbyDao.class)    .to(HobbyDaoImpl.class);
        bind(HobbyService.class).to(HobbyServiceImpl.class);
    }

}
