package com.quathar.contactbook.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;

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
	/**
	 * Light type.
	 */
	public static final String LIGHT = "Light";
	/**
	 * Dark type.
	 */
	public static final String DARK = "Dark";

	/**
	 * Themes names by alphabetical order.
	 */
	public static final String[] THEMES = {
			"Carbon",
			"Cyan",
			"Dark Purple",
			"Dracula",
			"Dracula Constrast",
			"Github",
			"Gruvbox Dark Hard",
			"Gradianto Deep Ocean",
			"High Contrast",
			"Material",
			"Material Deep Ocean",
			"Material Deep Ocean Contrast",
			"Material Lighter",
			"Material Lighter Contrast",
			"Material Pale Night",
			"Moonlight",
			"Moonlight Contrast",
			"Nigth Owl Contrast",
			"Nord",
			"Solarized Light Blue",
			"Solarized Light Red",
			"Solarized Light Red Contrast",
			"Vuesion"
	};
	/**
	 * Light Themes names.
	 */
	public static final String[] LIGHT_THEMES = {
			"Cyan",
			"Github",
			"Material Lighter",
			"Material Lighter Contrast",
			"Solarized Light Blue",
			"Solarized Light Red",
			"Solarized Light Red Contrast"
	};
	/**
	 * Dark Themes names.
	 */
	public static final String[] DARK_THEMES = {
			"Carbon",
			"Dark Purple",
			"Dracula",
			"Dracula Constrast",
			"Gruvbox Dark Hard",
			"Gradianto Deep Ocean",
			"High Contrast",
			"Material",
			"Material Deep Ocean",
			"Material Deep Ocean Contrast",
			"Material Pale Night",
			"Moonlight",
			"Moonlight Contrast",
			"Nigth Owl Contrast",
			"Nord",
			"Vuesion"
	};

	// <<-METHODS->>
	/**
	 * Returns an InputStream from the ID and a theme type.<br>
	 * <br>
	 * Themes are delivered in alphabetical order.
	 * 
	 * @param id theme ID
	 *
	 * @return the specified theme stream
	 */
	public static InputStream getTheme(int id, String type) {
		try {
			return switch (type) {
				case LIGHT -> getLightTheme(id);
				case DARK  -> getDarkTheme(id);
				default    -> null;
			};
		} catch (FileNotFoundException fnfE) {
			return null;
		}
	}

	/**
	 * Returns an InputStream from a light theme ID.
	 *
	 * @param id the theme ID
	 *
	 * @return the specified light theme stream
	 */
	public static InputStream getLightTheme(int id) throws FileNotFoundException {
		return switch (id) {
			case 0  -> cyan();
			case 1  -> github();
			case 2  -> materialLighter();
			case 3  -> materialLighterContrast();
			case 4  -> solarizedLightBlue();
			case 5  -> solarizedLightRed();
			case 6  -> solarizedLightRedContrast();
			default -> null;
		};
	}

	/**
	 * Returns an InputStream from a dark theme ID.
	 *
	 * @param id the theme ID
	 *
	 * @return the specified dark theme stream
	 */
	public static InputStream getDarkTheme(int id) throws FileNotFoundException {
		return switch (id) {
			case 0  -> carbon();
			case 1  -> darkPurple();
			case 2  -> dracula();
			case 3  -> draculaContrast();
			case 4  -> gruvboxDarkHard();
			case 5  -> gradiantoDeepOcean();
			case 6  -> highContrast();
			case 7  -> material();
			case 8  -> materialDeepOcean();
			case 9  -> materialDeepOceanContrast();
			case 10 -> materialPaleNightContrast();
			case 11 -> moonlight();
			case 12 -> moonlightContrast();
			case 13 -> nightOwlContrast();
			case 14 -> nord();
			case 15 -> vuesion();
			default -> null;
		};
	}

	// <<-THEMES->>
	public static InputStream carbon() throws FileNotFoundException {
		return new FileInputStream(String.format(PATH, "Carbon.theme.json"));
	}

	public static InputStream cyan() throws FileNotFoundException {
		return new FileInputStream(String.format(PATH, "Cyan.theme.json"));
	}

	public static InputStream darkPurple() throws FileNotFoundException {
		return new FileInputStream(String.format(PATH, "DarkPurple.theme.json"));
	}

	public static InputStream dracula() throws FileNotFoundException {
		return new FileInputStream(String.format(PATH, "Dracula.theme.json"));
	}

	public static InputStream draculaContrast() throws FileNotFoundException {
		return new FileInputStream(String.format(PATH, "Dracula Contrast.theme.json"));
	}

	public static InputStream github() throws FileNotFoundException {
		return new FileInputStream(String.format(PATH, "Github.theme.json"));
	}

	public static InputStream gruvboxDarkHard() throws FileNotFoundException {
		return new FileInputStream(String.format(PATH, "gruvbox_dark_hard.theme.json"));
	}

	public static InputStream gradiantoDeepOcean() throws FileNotFoundException {
		return new FileInputStream(String.format(PATH, "Gradianto_deep_ocean.theme.json"));
	}

	public static InputStream highContrast() throws FileNotFoundException {
		return new FileInputStream(String.format(PATH, "HighContrast.theme.json"));
	}

	public static InputStream material() throws FileNotFoundException {
		return new FileInputStream(String.format(PATH, "MaterialTheme.theme.json"));
	}

	public static InputStream materialDeepOcean() throws FileNotFoundException {
		return new FileInputStream(String.format(PATH, "Material Deep Ocean.theme.json"));
	}

	public static InputStream materialDeepOceanContrast() throws FileNotFoundException {
		return new FileInputStream(String.format(PATH, "Material Deep Ocean Contrast.theme.json"));
	}

	public static InputStream materialLighter() throws FileNotFoundException {
		return new FileInputStream(String.format(PATH, "Material Lighter.theme.json"));
	}

	public static InputStream materialLighterContrast() throws FileNotFoundException {
		return new FileInputStream(String.format(PATH, "Material Lighter Contrast.theme.json"));
	}

	public static InputStream materialPaleNightContrast() throws FileNotFoundException {
		return new FileInputStream(String.format(PATH, "Material Palenight Contrast.theme.json"));
	}

	public static InputStream moonlight() throws FileNotFoundException {
		return new FileInputStream(String.format(PATH, "Moonlight.theme.json"));
	}

	public static InputStream moonlightContrast() throws FileNotFoundException {
		return new FileInputStream(String.format(PATH, "Moonlight Contrast.theme.json"));
	}

	public static InputStream nightOwlContrast() throws FileNotFoundException {
		return new FileInputStream(String.format(PATH, "Night Owl Contrast.theme.json"));
	}

	public static InputStream nord() throws FileNotFoundException {
		return new FileInputStream(String.format(PATH, "nord.theme.json"));
	}

	public static InputStream solarizedLightBlue() throws FileNotFoundException {
		return new FileInputStream(String.format(PATH, "SolarizedLight.theme.json"));
	}

	public static InputStream solarizedLightRed() throws FileNotFoundException {
		return new FileInputStream(String.format(PATH, "Solarized Light.theme.json"));
	}

	public static InputStream solarizedLightRedContrast() throws FileNotFoundException {
		return new FileInputStream(String.format(PATH, "Solarized Light Contrast.theme.json"));
	}

	public static InputStream vuesion() throws FileNotFoundException {
		return new FileInputStream(String.format(PATH, "vuesion_theme.theme.json"));
	}

}
