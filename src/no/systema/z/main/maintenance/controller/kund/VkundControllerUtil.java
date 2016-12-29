package no.systema.z.main.maintenance.controller.kund;

/**
 * 
 * Placeholder for util methods foir Kunderegister.
 * 
 * @author Fredrik Möller
 * @date Dec 28, 2016
 *
 */
public class VkundControllerUtil {

	/**
	 * For UI. Trimming knavn to fit in tab
	 * 
	 * @param knavn
	 * @return a trimmed knavn if lenght > 10
	 */
	public static String getTrimmedKnav(String knavn) {
		StringBuilder knavn_display = new StringBuilder();
		int maxLenght = 10;
		if (knavn != null && knavn.length() > maxLenght) {
			knavn_display.append(knavn.substring(0, maxLenght));
			knavn_display.append("...");
			return knavn_display.toString();
		} else {
			return knavn;
		}
	}

}
