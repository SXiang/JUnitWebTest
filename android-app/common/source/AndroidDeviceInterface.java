package common.source;

import common.source.AndroidAutomationTools.ShellCommands;
import common.source.MobileActions.KeyCode;

public class AndroidDeviceInterface {
	private static final String M_INTERACTIVE_TRUE = "mInteractive=true";
	private static final String M_INTERACTIVE_FALSE = "mInteractive=false";
	private static final String M_HOLDING_DISPLAY_SUSPEND_BLOCKER_TRUE = "mHoldingDisplaySuspendBlocker=true";
	private static final String M_HOLDING_WAKE_LOCK_SUSPEND_BLOCKER_FALSE = "mHoldingWakeLockSuspendBlocker=false";
	private static final String NETWORK = "network";
	private static final String WIFI = "wifi";
	private static final String GPS = "gps";

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

	public static void invokeKeyEvent(KeyCode keyCode) throws Exception {
		Log.method("invokeKeyEvent", keyCode);
		AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), String.format("input keyevent %d", keyCode.getCode()));
	}

	public static boolean isNetworkOn() throws Exception {
		Log.method("isNetworkOn");
		return getGpsWifiNetworkStatus().contains(NETWORK);
	}

	public static boolean isScreenTurnedOn() throws Exception {
		Log.method("isScreenTurnedOn");
		String shellOutput = AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), ShellCommands.DUMPSYS_INPUT_METHOD);
		return shellOutput.contains(M_INTERACTIVE_TRUE);
	}

	public static boolean isScreenTurnedOff() throws Exception {
		Log.method("isScreenTurnedOff");
		String shellOutput = AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), ShellCommands.DUMPSYS_INPUT_METHOD);
		return shellOutput.contains(M_INTERACTIVE_FALSE);
	}

	public static void turnOnScreen() throws Exception {
		Log.method("turnOnScreen");
		invokeKeyEvent(KeyCode.KEYCODE_POWER);
	}

	public static void unlockDevice() throws Exception {
		Log.method("unlockDevice");
		invokeKeyEvent(KeyCode.KEYCODE_MENU);
	}

	public static void enterPin(String pin) throws Exception {
		Log.method("enterPin", pin);
		AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), String.format("input text %s", pin));
		invokeKeyEvent(KeyCode.KEYCODE_ENTER);
	}

	public static boolean isDeviceLocked() throws Exception {
		Log.method("isDeviceLocked");
		String shellOutput = AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), ShellCommands.DUMPSYS_POWER);
		return shellOutput.contains(M_HOLDING_WAKE_LOCK_SUSPEND_BLOCKER_FALSE) && shellOutput.contains(M_HOLDING_DISPLAY_SUSPEND_BLOCKER_TRUE);
	}

	public static boolean isWifiOn() throws Exception {
		Log.method("isWifiOn");
		return getGpsWifiNetworkStatus().contains(WIFI);
	}

	public static boolean isGpsOn() throws Exception {
		Log.method("isGpsOn");
		return getGpsWifiNetworkStatus().contains(GPS);
	}

	private static String getGpsWifiNetworkStatus() throws Exception {
		Log.method("getGpsWifiNetworkStatus");
		String shellCmdOutput = AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), ShellCommands.GPS_WIFI_NETWORK_STATUS);
		Log.info(String.format("Command Output -> %s", shellCmdOutput));
		return shellCmdOutput;
	}
}