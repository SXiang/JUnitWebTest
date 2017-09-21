package common.source;

public class AppConstants {
	public static String APP_PACKAGE_NAME = "com.picarroapp";

	public static final String[] APP_NEEDED_PERMISSIONS = {
			"android.permission.ACCESS_FINE_LOCATION",
	        "android.permission.READ_EXTERNAL_STORAGE",
	        "android.permission.ACCESS_COARSE_LOCATION",
	        "android.permission.WRITE_EXTERNAL_STORAGE"
		};

	public static final String APP_HEAPDUMP_SAVE_LOCATION = "/data/local/tmp";
}
