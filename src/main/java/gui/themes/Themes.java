package gui.themes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * Themes.<br><br>
 * 
 * Temas para escoger en la Graphic User Interface de la Agenda.
 * 
 * @since 07-04-2022
 * @author Q
 */
public class Themes { // CLASE FINALIZADA

	// <<-CONSTANTS->>
	/** Themes PATH */
	private static final String PATH = Path.of(System.getProperty("user.dir"),
			"themes", "json", "%s").toString();

	/**
	 * Nombre de todos los temas.
	 */
	public static final String[] ThemesNames = {
			"Carbon", "Cyan", "Dark Purple",
			"Dracula", "Dracula Constrast", "Github",
			"Gruvbox Dark Hard", "Gradianto Deep Ocean", "High Contrast",
			"Material", "Material Deep Ocean", "Material Deep Ocean Contrast",
			"Material Lighter", "Material Lighter Contrast", "Material Pale Night",
			"Moonlight", "Moonlight Contrast", "Nigth Owl Contrast",
			"Nord", "Solarized Light Blue", "Solarized Light Red",
			"Solarized Light Red Contrast", "Vuesion"
		};
	/**
	 * Nombre de los temas claros.
	 */
	public static final String[] LightThemesNames = {
			"Cyan", "Github", "Material Lighter",
			"Material Lighter Contrast", "Solarized Light Blue", "Solarized Light Red",
			"Solarized Light Red Contrast",
		};
	/**
	 * Nombre de los temas oscuros.
	 */
	public static final String[] DarkThemesNames = {
			"Carbon", "Dark Purple", "Dracula",
			"Dracula Constrast", "Gruvbox Dark Hard", "Gradianto Deep Ocean",
			"High Contrast", "Material", "Material Deep Ocean",
			"Material Deep Ocean Contrast", "Material Pale Night", "Moonlight",
			"Moonlight Contrast", "Nigth Owl Contrast", "Nord",
			"Vuesion"
		};
	/**
	 * Light type.
	 */
	public static final String Light = "Light";
	/**
	 * Dark type.
	 */
	public static final String Dark = "Dark";
	
	/**
	 * Devuelve un InputStream a partir de un ID.<br><br>
	 * 
	 * Los temas se entregan por orden alfab�tico.
	 * 
	 * @param id theme ID
	 * @return InputStream
	 */
	public static InputStream getTheme(int id, String type) {
			if (type.equals(Light))
				return getLightTheme(id);
			else
				return getDarkTheme(id);
	}
	
	/**
	 * Devuelve un tema claro a partir de un ID.<br><br>
	 * 
	 * Los temas se entregan por orden alfab�tico.
	 * 
	 * @param id theme ID
	 * @return InputStream
	 */
	public static InputStream getLightTheme(int id) {
		try {
			switch (id) {
				case 0: return cyan();
				case 1: return github();
				case 2: return materialLighter();
				case 3: return materialLighterContrast();
				case 4: return solarizedLightBlue();
				case 5: return solarizedLightRed();
				case 6: return solarizedLightRedContrast();
				default: return null;
			}
		} catch (FileNotFoundException fnfE) { return null; }
	}
	
	/**
	 * Devuelve un tema oscuro a partir de un ID.<br><br>
	 * 
	 * Los temas se entregan por orden alfab�tico.
	 * 
	 * @param id theme ID
	 * @return InputStream
	 */
	public static InputStream getDarkTheme(int id) {
		try {
			switch (id) {
				case 0: return carbon();
				case 1: return darkPurple();
				case 2: return dracula();
				case 3: return draculaContrast();
				case 4: return gruvboxDarkHard();
				case 5: return gradiantoDeepOcean();
				case 6: return highContrast();
				case 7: return material();
				case 8: return materialDeepOcean();
				case 9: return materialDeepOceanContrast();
				case 10: return materialPaleNightContrast();
				case 11: return moonlight();
				case 12: return moonlightContrast();
				case 13: return nightOwlContrast();
				case 14: return nord();
				case 15: return vuesion();
				default: return null;
			}
		} catch (FileNotFoundException fnfE) { return null; }
	}

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
