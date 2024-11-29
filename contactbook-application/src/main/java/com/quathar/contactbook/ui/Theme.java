package com.quathar.contactbook.ui;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * <h1>Theme Enumerator</h1>
 *
 * @since 2024-11-28
 * @version 1.0
 * @author Q
 */
@Getter
@RequiredArgsConstructor
public enum Theme {

    // Light themes
    CYAN("Cyan", "Cyan.theme.json", Type.LIGHT),
    GITHUB("Github", "Github.theme.json", Type.LIGHT),
    MATERIAL_LIGHTER("Material Lighter", "Material Lighter.theme.json", Type.LIGHT),
    MATERIAL_LIGHTER_CONTRAST("Material Lighter Contrast", "Material Lighter Contrast.theme.json", Type.LIGHT),
    SOLARIZED_LIGHT_BLUE("Solarized Light Blue", "SolarizedLight.theme.json", Type.LIGHT),
    SOLARIZED_LIGHT_RED("Solarized Light Red", "Solarized Light.theme.json", Type.LIGHT),
    SOLARIZED_LIGHT_RED_CONTRAST("Solarized Light Red Contrast", "Solarized Light Contrast.theme.json", Type.LIGHT),

    // Dark themes
    CARBON("Carbon", "Carbon.theme.json", Type.DARK),
    DARK_PURPLE("Dark Purple", "DarkPurple.theme.json", Type.DARK),
    DRACULA("Dracula", "Dracula.theme.json", Type.DARK),
    DRACULA_CONTRAST("Dracula Contrast", "Dracula Contrast.theme.json", Type.DARK),
    GRUVBOX_DARK_HARD("Gruvbox Dark Hard", "gruvbox_dark_hard.theme.json", Type.DARK),
    GRADIANTO_DEEP_OCEAN("Gradianto Deep Ocean", "Gradianto_deep_ocean.theme.json", Type.DARK),
    HIGH_CONTRAST("High Contrast", "HighContrast.theme.json", Type.DARK),
    MATERIAL("Material", "MaterialTheme.theme.json", Type.DARK),
    MATERIAL_DEEP_OCEAN("Material Deep Ocean", "Material Deep Ocean.theme.json", Type.DARK),
    MATERIAL_DEEP_OCEAN_CONTRAST("Material Deep Ocean Contrast", "Material Deep Ocean Contrast.theme.json", Type.DARK),
    MATERIAL_PALENIGHT_CONTRAST("Material Palenight", "Material Palenight Contrast.theme.json", Type.DARK),
    MOONLIGHT("Moonlight", "Moonlight.theme.json", Type.DARK),
    MOONLIGHT_CONTRAST("Moonlight Contrast", "Moonlight Contrast.theme.json", Type.DARK),
    NIGHT_OWL_CONTRAST("Night Owl Contrast", "Night Owl Contrast.theme.json", Type.DARK),
    NORD("Nord", "nord.theme.json", Type.DARK),
    VUESION("Vuesion", "vuesion_theme.theme.json", Type.DARK);

    private final String name;
    private final String fileName;
    private final Type type;

    public enum Type {
        LIGHT,
        DARK
    }

    /**
     * Get the themes by its type.
     *
     * @param type the theme type
     * @return the theme names
     */
    public static String[] getThemeNamesByType(Type type) {
        return java.util.Arrays.stream(values())
                               .filter(theme -> theme.getType() == type)
                               .map(Theme::getName)
                               .toArray(String[]::new);
    }

}
