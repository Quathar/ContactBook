package com.quathar.contactbook.data.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import com.quathar.contactbook.data.entity.Contact;
import com.quathar.contactbook.data.entity.Hobby;

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

    /**
     * hibernate configuration file (hibernate.properties) path
     */
    private static final String HIBERNATE_PROPERTIES_PATH = Path.of(
            System.getProperty("project.rootDir"),
            "contactbook-data",
            "src", "main", "resources", "hibernate.properties"
    ).toString();

    /**
     * The path to the database
     */
    private static final String DATABASE_PATH = Path.of(
            System.getProperty("project.rootDir"),
            "contactbook-data",
            "src", "main", "resources", "contactbook"
    ).toString();

    /**
     * Configures hibernate.
     *
     * @return the hibernate configuration object
     */
    @Provides
    public Configuration providesConfiguration() {
        try (FileInputStream fileInputStream = new FileInputStream(HIBERNATE_PROPERTIES_PATH)) {
            Properties hibernateProperties = new Properties();
            hibernateProperties.load(fileInputStream);
            String jdbc = hibernateProperties.get("hibernate.connection.url").toString();
            hibernateProperties.put("hibernate.connection.url", jdbc.replace("{path}", DATABASE_PATH));
            return new Configuration()
                    .addProperties(hibernateProperties)
                    .addPackage("com.quathar.contactbook.data")
                    .addAnnotatedClass(Contact.class)
                    .addAnnotatedClass(Hobby.class);
        } catch (IOException e) {
            System.err.println("ERROR: IOException :: File not found");
            System.exit(1);
            return null;
        }
    }

}
