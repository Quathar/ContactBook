package com.quathar.contactbook.config;

import com.quathar.contactbook.data.config.DataConfiguration;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Properties;

/**
 * <h1>AppConfiguration</h1>
 *
 * @since 2023-06-02
 * @see DataConfiguration
 * @version 1.0
 * @author Q
 */
public class AppConfiguration extends DataConfiguration {

    // <<-CONSTANTS->>
    private static final Path SETTINGS_PATH = Path.of(
            System.getProperty("user.dir"), "contactbook-application",
            "src", "main", "resources", "settings.properties");
    private static final String EN = "en";
    private static final String ES = "es";
    private static final String DE = "de";
    private static final String PT = "pt";
    private static final String FR = "fr";

    @Override
    protected void configure() {
        // This is necessary to have all the configuration
        // that we did in DataConfiguration (DAOs, Services),
        // which extends AbstractModule
        super.configure();
    }

    /**
     * Returns the Locale object based on the configured locale.
     *
     * @return the Locale object
     */
    public static Locale getLocale() {
        try (FileInputStream fileInputStream = new FileInputStream(SETTINGS_PATH.toString())) {
            Properties settingsProperties = new Properties();
            settingsProperties.load(fileInputStream);
            String language = (String) settingsProperties.get("language");
            return new Locale(language);
        } catch (IOException e) {
            return new Locale("en");
        }
    }

    public static void setLocale(int index) {
        try (
                FileInputStream  fileInputStream  = new FileInputStream (SETTINGS_PATH.toString());
                FileOutputStream fileOutputStream = new FileOutputStream(SETTINGS_PATH.toString())
        ) {
            Properties settingsProperties = new Properties();
            settingsProperties.load(fileInputStream);
            switch (index) {
                case 0 -> settingsProperties.setProperty("language", EN);
                case 1 -> settingsProperties.setProperty("language", ES);
                case 2 -> settingsProperties.setProperty("language", DE);
                case 3 -> settingsProperties.setProperty("language", PT);
                case 4 -> settingsProperties.setProperty("language", FR);
            }

            // TODO: Look how to not remove other properties like 'theme'
//            settingsProperties.setProperty("theme", settingsProperties.getProperty("theme"));
            settingsProperties.store(fileOutputStream, null);
        } catch (IOException ignored) {}
    }

    /**
     * Returns the file name for the resource bundle based on the current locale.
     *
     * @return the file name for the resource bundle
     */
    public static String getLocaleFileName() {
        return switch (getLocale().getLanguage()) {
            case ES   -> "messages_es.properties";
            case DE   -> "messages_de.properties";
            case PT   -> "messages_pt.properties";
            case FR   -> "messages_fr.properties";
            default   -> "messages.properties"; // Default is English (en)
        };
    }

}