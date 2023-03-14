package gui.themes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Themes.<br><br>
 * 
 * Temas para escoger en la Graphic User Interface de la Agenda.
 * 
 * @since 07-04-2022
 * @author Q
 */
public class Themes { // CLASE FINALIZADA
	
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
	 * Los temas se entregan por orden alfabético.
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
	 * Los temas se entregan por orden alfabético.
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
	 * Los temas se entregan por orden alfabético.
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
				case 4: return gruvbox_dark_hard();
				case 5: return gradianto_deep_ocean();
				case 6: return highContrast();
				case 7: return material();
				case 8: return materialDeepOcean();
				case 9: return materialDeepOceanContrast();
				case 10: return materialPaleNightContrast();
				case 11: return moonlight();
				case 12: return moonlightContrast();
				case 13: return nightOwlContrast();
				case 14: return nord();
				case 15: return vuesion_theme();
				default: return null;
			}
		} catch (FileNotFoundException fnfE) { return null; }
	}
	
	/**
	 * Tema <b>Carbon<b>.
	 * 
	 * @return InputStream
	 * @throws FileNotFoundException
	 */
	public static InputStream carbon() throws FileNotFoundException {
		return new FileInputStream(new File("src/themes/Carbon.theme.json"));
	}
	
	/**
	 * Tema <b>Cyan<b>.
	 * 
	 * @return InputStream
	 * @throws FileNotFoundException
	 */
	public static InputStream cyan() throws FileNotFoundException {
		return new FileInputStream(new File("src/themes/Cyan.theme.json"));
	}
	
	/**
	 * Tema <b>Dark Purple<b>.
	 * 
	 * @return InputStream
	 * @throws FileNotFoundException
	 */
	public static InputStream darkPurple() throws FileNotFoundException {
		return new FileInputStream(new File("src/themes/DarkPurple.theme.json"));
	}
	
	/**
	 * Tema <b>Dracula<b>.
	 * 
	 * @return InputStream
	 * @throws FileNotFoundException
	 */
	public static InputStream dracula() throws FileNotFoundException {
		return new FileInputStream(new File("src/themes/Dracula.theme.json"));
	}
	
	/**
	 * Tema <b>Dracula Contrast<b>.
	 * 
	 * @return InputStream
	 * @throws FileNotFoundException
	 */
	public static InputStream draculaContrast() throws FileNotFoundException {
		return new FileInputStream(new File("src/themes/Dracula Contrast.theme.json"));
	}
	
	/**
	 * Tema <b>Github<b>.
	 * 
	 * @return InputStream
	 * @throws FileNotFoundException
	 */
	public static InputStream github() throws FileNotFoundException {
		return new FileInputStream(new File("src/themes/Github.theme.json"));
	}
	
	/**
	 * Tema <b>Gruvbox Dark Hard<b>.
	 * 
	 * @return InputStream
	 * @throws FileNotFoundException
	 */
	public static InputStream gruvbox_dark_hard() throws FileNotFoundException {
		return new FileInputStream(new File("src/themes/gruvbox_dark_hard.theme.json"));
	}
	
	/**
	 * Tema <b>Gradianto Deep Ocean<b>.
	 * 
	 * @return InputStream
	 * @throws FileNotFoundException
	 */
	public static InputStream gradianto_deep_ocean() throws FileNotFoundException {
		return new FileInputStream(new File("src/themes/Gradianto_deep_ocean.theme.json"));
	}
	
	/**
	 * Tema <b>High Contrast<b>.
	 * 
	 * @return InputStream
	 * @throws FileNotFoundException
	 */
	public static InputStream highContrast() throws FileNotFoundException {
		return new FileInputStream(new File("src/themes/HighContrast.theme.json"));
	}
	
	/**
	 * Tema <b>Material<b>.
	 * 
	 * @return InputStream
	 * @throws FileNotFoundException
	 */
	public static InputStream material() throws FileNotFoundException {
		return new FileInputStream(new File("src/themes/MaterialTheme.theme.json"));
	}
	
	/**
	 * Tema <b>Material Deep Ocean<b>.
	 * 
	 * @return InputStream
	 * @throws FileNotFoundException
	 */
	public static InputStream materialDeepOcean() throws FileNotFoundException {
		return new FileInputStream(new File("src/themes/Material Deep Ocean.theme.json"));
	}
	
	/**
	 * Tema <b>Material Deep Ocean Contrast<b>.
	 * 
	 * @return InputStream
	 * @throws FileNotFoundException
	 */
	public static InputStream materialDeepOceanContrast() throws FileNotFoundException {
		return new FileInputStream(new File("src/themes/Material Deep Ocean Contrast.theme.json"));
	}
	
	/**
	 * Tema <b>Material Lighter<b>.
	 * 
	 * @return InputStream
	 * @throws FileNotFoundException
	 */
	public static InputStream materialLighter() throws FileNotFoundException {
		return new FileInputStream(new File("src/themes/Material Lighter.theme.json"));
	}
	
	/**
	 * Tema <b>Material Lighter Contrast<b>.
	 * 
	 * @return InputStream
	 * @throws FileNotFoundException
	 */
	public static InputStream materialLighterContrast() throws FileNotFoundException {
		return new FileInputStream(new File("src/themes/Material Lighter Contrast.theme.json"));
	}
	
	/**
	 * Tema <b>Material Pale Night Contrast<b>.
	 * 
	 * @return InputStream
	 * @throws FileNotFoundException
	 */
	public static InputStream materialPaleNightContrast() throws FileNotFoundException {
		return new FileInputStream(new File("src/themes/Material Palenight Contrast.theme.json"));
	}
	
	/**
	 * Tema <b>Moonlight<b>.
	 * 
	 * @return InputStream
	 * @throws FileNotFoundException
	 */
	public static InputStream moonlight() throws FileNotFoundException {
		return new FileInputStream(new File("src/themes/Moonlight.theme.json"));
	}
	
	/**
	 * Tema <b>Moonlight Contrast<b>.
	 * 
	 * @return InputStream
	 * @throws FileNotFoundException
	 */
	public static InputStream moonlightContrast() throws FileNotFoundException {
		return new FileInputStream(new File("src/themes/Moonlight Contrast.theme.json"));
	}
	
	/**
	 * Tema <b>Night Owl Contrast<b>.
	 * 
	 * @return InputStream
	 * @throws FileNotFoundException
	 */
	public static InputStream nightOwlContrast() throws FileNotFoundException {
		return new FileInputStream(new File("src/themes/Night Owl Contrast.theme.json"));
	}
	
	/**
	 * Tema <b>Nord<b>.
	 * 
	 * @return InputStream
	 * @throws FileNotFoundException
	 */
	public static InputStream nord() throws FileNotFoundException {
		return new FileInputStream(new File("src/themes/nord.theme.json"));
	}
	
	/**
	 * Tema <b>Solarized Light Blue<b>.
	 * 
	 * @return InputStream
	 * @throws FileNotFoundException
	 */
	public static InputStream solarizedLightBlue() throws FileNotFoundException {
		return new FileInputStream(new File("src/themes/SolarizedLight.theme.json"));
	}
	
	/**
	 * Tema <b>Solarized Light Red<b>.
	 * 
	 * @return InputStream
	 * @throws FileNotFoundException
	 */
	public static InputStream solarizedLightRed() throws FileNotFoundException {
		return new FileInputStream(new File("src/themes/Solarized Light.theme.json"));
	}
	
	/**
	 * Tema <b>Solarized Light Red Contrast<b>.
	 * 
	 * @return InputStream
	 * @throws FileNotFoundException
	 */
	public static InputStream solarizedLightRedContrast() throws FileNotFoundException {
		return new FileInputStream(new File("src/themes/Solarized Light Contrast.theme.json"));
	}
	
	/**
	 * Tema <b>Vuesion Theme<b>.
	 * 
	 * @return InputStream
	 * @throws FileNotFoundException
	 */
	public static InputStream vuesion_theme() throws FileNotFoundException {
		return new FileInputStream(new File("src/themes/vuesion_theme.theme.json"));
	}
	
}
