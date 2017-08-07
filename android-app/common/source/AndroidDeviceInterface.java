package common.source;

import common.source.AndroidAutomationTools.ShellCommands;

public class AndroidDeviceInterface {

	public static void turnOnGps() throws Exception {
		Log.method("turnOnGps");
		AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), ShellCommands.TURN_ON_GPS);
	}

	public static void turnOffGps() throws Exception {
		Log.method("turnOffGps");
		AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), ShellCommands.TURN_OFF_GPS);
	}

	public static void turnOnNetwork() throws Exception {
		Log.method("turnOnNetwork");
		AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), ShellCommands.TURN_ON_NETWORK);
	}

	public static void turnOffNetwork() throws Exception {
		Log.method("turnOffNetwork");
		AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), ShellCommands.TURN_OFF_NETWORK);
	}

	public static void turnOnWifi() throws Exception {
		Log.method("turnOnWifi");
		AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), ShellCommands.TURN_ON_WIFI);
	}

	public static void turnOffWifi() throws Exception {
		Log.method("turnOffWifi");
		AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), ShellCommands.TURN_OFF_WIFI);
	}

	public static boolean isNetworkOn() throws Exception {
		Log.method("isNetworkOn");
		return getGpsWifiNetworkStatus().contains("network");
	}

	public static boolean isWifiOn() throws Exception {
		Log.method("isWifiOn");
		return getGpsWifiNetworkStatus().contains("wifi");
	}

	public static boolean isGpsOn() throws Exception {
		Log.method("isGpsOn");
		return getGpsWifiNetworkStatus().contains("gps");
	}

	private static String getGpsWifiNetworkStatus() throws Exception {
		Log.method("getGpsWifiNetworkStatus");
		String shellCmdOutput = AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), ShellCommands.GPS_WIFI_NETWORK_STATUS);
		Log.info(String.format("Command Output -> %s", shellCmdOutput));
		return shellCmdOutput;
	}
}