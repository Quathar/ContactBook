package com.quathar.contactbook.config;

import java.util.Locale;

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

    public static Locale getLocale() {
        // TODO: This has to be required in from a conf.properties or something like this
        String locale = ES;
        return new Locale(locale);
    }

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
