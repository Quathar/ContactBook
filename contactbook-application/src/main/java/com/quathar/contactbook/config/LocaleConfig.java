package com.quathar.contactbook.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

/**
 * <h1>AppConfiguration</h1>
 *
 * @since 2023-06-02
 * @version 1.0
 * @author Q
 */
public class LocaleConfig {

    private static final Path SETTINGS_PATH = Path.of(
            System.getProperty("project.rootDir"), "contactbook-application",
            "src", "main", "resources", "settings.properties");

    /**
     * Returns the Locale object based on the configured locale.
     *
     * @return the Locale object
     */
    public static LangEnum getLocale() {
        try (FileInputStream fileInputStream = new FileInputStream(SETTINGS_PATH.toString())) {
            Properties settingsProperties = new Properties();
            settingsProperties.load(fileInputStream);
            String language = (String) settingsProperties.get("language");
            return LangEnum.valueOf(language.toUpperCase());
        } catch (IOException e) {
            return LangEnum.valueOf("EN");
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
                case 0 -> settingsProperties.setProperty("language", LangEnum.EN.getLanguage());
                case 1 -> settingsProperties.setProperty("language", LangEnum.ES.getLanguage());
            }

            // TODO: Look how to not remove other properties like 'theme'
//            settingsProperties.setProperty("theme", settingsProperties.getProperty("theme"));
            settingsProperties.store(fileOutputStream, null);
        } catch (IOException ignored) {

        }
    }

    /**
     * Returns the file name for the resource bundle based on the current locale.
     *
     * @return the file name for the resource bundle
     */
    public static String getLocaleFileName() {
        LangEnum language = getLocale();
        return switch (language) {
            case EN -> "messages.properties";
            case ES -> "messages_es.properties";
        };
    }

}
