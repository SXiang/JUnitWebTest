package common.source;

public class SystemUtility {
	public static String getAppDataFolder() {
		String appDataFolder = "";
		String osLower = System.getProperty("os.name").toLowerCase();
		if (osLower.contains("win")) {
			// On windows get APPDATA environment variable.
			appDataFolder = System.getenv("APPDATA");
			if (appDataFolder.contains("Roaming")) {
				appDataFolder = appDataFolder.replace("Roaming", "Local");
			}
		} else {
			// On Linux get user.home property.
			appDataFolder = System.getProperty("user.home");
		}
		return appDataFolder;
	}
}
