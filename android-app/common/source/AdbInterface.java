package common.source;

import java.io.IOException;
import java.util.function.Predicate;

import com.android.ddmlib.AdbCommandRejectedException;
import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;
import com.android.ddmlib.InstallException;
import com.android.ddmlib.MultiLineReceiver;
import com.android.ddmlib.ShellCommandUnresponsiveException;
import com.android.ddmlib.TimeoutException;

public class AdbInterface {
	private static final Integer DEFAULT_WAIT_BETWEEN_POLL_IN_MSEC = 1000;
	private static final Integer MAX_RETRIES_IN_POLL = 30;
	private static String adbLocation = AndroidAutomationTools.AndroidPaths.DEFAULT_ADB_LOCATION;
	private static AndroidDebugBridge adb;
    private static class ShellOutputReceiver extends MultiLineReceiver {

    	private StringBuilder output = new StringBuilder();

		@Override
		public boolean isCancelled() {
			return false;
		}

		@Override
		public void processNewLines(String[] lines) {
			if (lines != null && lines.length > 0) {
				for (String line : lines) {
					output.append(line);
					output.append(BaseHelper.getLineSeperator());
				}
			}
		}

		public String getOutput() {
			return output.toString();
		}
    }

	public static String getAdbLocation() {
		return adbLocation;
	}

	public static void setAdbLocation(String adbLocation) {
		AdbInterface.adbLocation = adbLocation;
	}

	public static String executeShellCmd(final String command) throws Exception {
		return executeShellCmdInternal(getAdbLocation(), command);
	}

	public static String executeShellCmd(final String adbLocation, final String command) throws Exception {
		return executeShellCmdInternal(adbLocation, command);
	}

	public static boolean executeShellCmd(final String command, Predicate<String> testCondition) {
		return testCondition.test(executeShellCmdInternal(getAdbLocation(), command));
	}

	public static boolean executeShellCmd(final String adbLocation, final String command, Predicate<String> testCondition) {
		return testCondition.test(executeShellCmdInternal(adbLocation, command));
	}

	public static void installPackage(String packageFilePath, boolean replaceExisting, boolean allowVersionDowngrade, boolean grantAllRuntimePermissions)  {
		Log.method("installPackage", packageFilePath, replaceExisting, allowVersionDowngrade, grantAllRuntimePermissions);
		IDevice device = getConnectedDevice();
		if (device != null) {
			try {
				String extraArgs = "";
				if (allowVersionDowngrade) {
					extraArgs += " -d";
				}
				if (grantAllRuntimePermissions) {
					extraArgs += " -g";
				}

				if (extraArgs.length() > 0) {
					device.installPackage(packageFilePath, replaceExisting, extraArgs);
				} else {
					device.installPackage(packageFilePath, replaceExisting);
				}
			} catch (InstallException ex) {
				Log.error(ExceptionUtility.getStackTraceString(ex));
			}
		} else {
			Log.error("installPackage -> No connected devices found.");
		}
	}

	public static void init(String adbLocation) {
		setAdbLocation(adbLocation);
		AndroidDebugBridge.init(false);
	}

	public static void stop() {
		AndroidDebugBridge.disconnectBridge();
		AndroidDebugBridge.terminate();
	}

	public static void uninstallPackage(String packageName) {
		Log.method("uninstallPackage", packageName);
		IDevice device = getConnectedDevice();
		if (device != null) {
			try {
				device.uninstallPackage(packageName);
			} catch (InstallException ex) {
				Log.error(ExceptionUtility.getStackTraceString(ex));
			}
		} else {
			Log.error("uninstallPackage -> No connected devices found.");
		}
	}

	public static void waitForDeviceToBeReady(final String adbLocation) {
		Log.method("waitForDeviceToBeReady");
		if (adb == null) {
			adb = AndroidDebugBridge.createBridge(adbLocation, true);
		}
		PollManager.poll(() -> !adb.isConnected() || !adb.hasInitialDeviceList(), DEFAULT_WAIT_BETWEEN_POLL_IN_MSEC, 2 * MAX_RETRIES_IN_POLL);
	}

	private static String executeShellCmdInternal(final String adbLocation, final String command) {
		Log.method("executeShellCmdInternal", command);
		IDevice device = getConnectedDevice();
		if (device != null) {
			ShellOutputReceiver receiver = new ShellOutputReceiver();
			Log.info(String.format("Executing -> adb shell %s", command));
			try {
				device.executeShellCommand(command, receiver);
			} catch (TimeoutException | AdbCommandRejectedException | ShellCommandUnresponsiveException
					| IOException ex) {
				Log.error(ExceptionUtility.getStackTraceString(ex));
				return "";
			}
			String output = receiver.getOutput();
			Log.info(String.format("Command output -> %s", output));
			return output;
		} else {
			Log.error("executeShellCmd -> No connected devices found.");
		}

		return "";
	}

	private static IDevice getConnectedDevice() {
		IDevice device = null;
		if (adb == null) {
			adb = AndroidDebugBridge.createBridge(adbLocation, true);
		}

		PollManager.poll(() -> !adb.isConnected() || !adb.hasInitialDeviceList(), DEFAULT_WAIT_BETWEEN_POLL_IN_MSEC, 2 * MAX_RETRIES_IN_POLL);
		IDevice[] devices = AndroidDebugBridge.getBridge().getDevices();
		if (devices != null && devices.length > 0) {
			device = devices[0];
		}

		return device;
	}
}