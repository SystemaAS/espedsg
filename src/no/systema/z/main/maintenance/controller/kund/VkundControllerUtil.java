package no.systema.z.main.maintenance.controller.kund;

public class VkundControllerUtil {

	public static String getTrimmedKnav(String knavn) {
		StringBuilder knavn_display = new StringBuilder();
		int maxLenght = 10;
		if (knavn.length() > maxLenght) {
			knavn_display.append(knavn.substring(0, maxLenght));
			knavn_display.append("...");
			return knavn_display.toString();
		} else {
			return knavn;
		}
	}

}
