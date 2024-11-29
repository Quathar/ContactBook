package com.quathar.contactbook.ui;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * <h1>Themes</h1>
 * <br>
 * Themes to choose from in the Agenda's graphic user interface (GUI).
 *
 * @since 2022-04-07
 * @version 2.0
 * @author Q
 */
public class Themes {

    // <<-CONSTANTS->>
    /**
     * Themes PATH
     */
    private static final String PATH = Path.of(
            System.getProperty("user.dir"), "contactbook-application",
            "src", "main", "resources", "json", "%s").toString();

    // <<-METHODS->>
    /**
     * Returns an InputStream from the ID and a theme type.<br>
     * <br>
     * Themes are delivered in alphabetical order.
     *
     * @param id   theme ID
     * @param type theme type
     * @return the specified theme stream
     */
    public static InputStream getTheme(int id, Theme.Type type) {
        try {
            Path path = switch (type) {
                case LIGHT -> createThemeMap(Theme.Type.LIGHT).get(id);
                case DARK -> createThemeMap(Theme.Type.DARK).get(id);
            };
            return Files.newInputStream(path);
        } catch (java.io.IOException e) {
            e.printStackTrace();
            // Will use the classic theme
            return null;
        }
    }

    private static Map<Integer, Path> createThemeMap(Theme.Type type) {
        var index = new AtomicInteger(0);
        return Arrays.asList(Theme.values())
                     .parallelStream()
                     .filter(theme -> type.equals(theme.getType()))
                     .collect(Collectors.toMap(
                             theme -> index.getAndIncrement(),
                             theme -> Path.of(String.format(PATH, theme.getFileName()))
                     ));
    }

}
