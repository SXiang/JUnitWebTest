package common.source;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import com.android.ddmlib.AdbCommandRejectedException;
import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;
import com.android.ddmlib.InstallException;
import com.android.ddmlib.MultiLineReceiver;
import com.android.ddmlib.ScreenRecorderOptions;
import com.android.ddmlib.ShellCommandUnresponsiveException;
import com.android.ddmlib.SyncException;
import com.android.ddmlib.TimeoutException;

public class AdbInterface {
	private static final Integer SCREENRECORD_MAX_TIMELIMIT_IN_SECS = 300;
	private static final Integer SCREENRECORD_DEFAULT_BIT_MBPS = 3;
	private static final Integer SCREENRECORD_HEIGHT = 720;
	private static final Integer SCREENRECORD_WIDTH = 1280;
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

	public static void clearAppCache(String appPackageName) throws Exception {
		Log.method("clearAppCache");
		executeShellCmd(AdbInterface.getAdbLocation(), String.format("pm clear %s", appPackageName));
	}

	public static void grantPermissions(String appPackageName, String[] permissionsList) throws Exception {
		Log.method("grantPermissions", appPackageName, permissionsList);
		for (String permission : permissionsList) {
			executeShellCmd(AdbInterface.getAdbLocation(), String.format("pm grant %s %s", appPackageName, permission));
		}
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

	public static void pullFile(String deviceFileLocation, String saveFileLocation) {
		Log.method("pullFile", deviceFileLocation, saveFileLocation);
		IDevice device = getConnectedDevice();
		if (device != null) {
			try {
				device.pullFile(deviceFileLocation, saveFileLocation);
			} catch (SyncException | IOException  | AdbCommandRejectedException | TimeoutException ex) {
				Log.error(ExceptionUtility.getStackTraceString(ex));
			}
		} else {
			Log.error("pullFile -> No connected devices found.");
		}
	}

	public static void pushFile(String localFileLocation, String deviceFileLocation) {
		Log.method("pushFile", localFileLocation, deviceFileLocation);
		IDevice device = getConnectedDevice();
		if (device != null) {
			try {
				device.pushFile(localFileLocation, deviceFileLocation);
			} catch (SyncException | IOException  | AdbCommandRejectedException | TimeoutException ex) {
				Log.error(ExceptionUtility.getStackTraceString(ex));
			}
		} else {
			Log.error("pushFile -> No connected devices found.");
		}
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

	public static void startScreenRecording(String videoFilePath) {
		Log.method("startScreenRecording", videoFilePath);
		IDevice device = getConnectedDevice();
		if (device != null) {
			ShellOutputReceiver receiver = new ShellOutputReceiver();
			try {
				ScreenRecorderOptions recorderOptions = new ScreenRecorderOptions.Builder()
					.setBitRate(SCREENRECORD_DEFAULT_BIT_MBPS)
					.setTimeLimit(SCREENRECORD_MAX_TIMELIMIT_IN_SECS, TimeUnit.SECONDS)
					.setSize(SCREENRECORD_WIDTH, SCREENRECORD_HEIGHT)
					.build();
				device.startScreenRecorder(videoFilePath, recorderOptions, receiver);
			} catch (IOException  | AdbCommandRejectedException | ShellCommandUnresponsiveException | TimeoutException ex) {
				Log.warn(ex.getMessage());
			}
		} else {
			Log.error("pullFile -> No connected devices found.");
		}
	}

	private static String executeShellCmdInternal(final String adbLocation, final String command) {
		Log.method("executeShellCmdInternal", command);
		IDevice device = getConnectedDevice();
		if (device != null) {
			ShellOutputReceiver receiver = new ShellOutputReceiver();
			Log.info(String.format("Executing -> adb shell %s", command));
			RetryUtil.retryOnException(
					() -> {
						try {
							device.executeShellCommand(command, receiver);
							return true;
						} catch (TimeoutException | AdbCommandRejectedException | ShellCommandUnresponsiveException
								| IOException ex) {
							Log.warn(ExceptionUtility.getStackTraceString(ex));
							return false;
						}
					},
					() -> {
						if (isTypeCommand(command)) {
							Log.method("Type text command failed. Reverting typed text ...");
							FunctionUtil.warnOnError(() -> revertTypeTextCommand(command));
						}
						return true;
					},
					Constants.THOUSAND_MSEC_WAIT_BETWEEN_RETRIES * 2,
					Constants.DEFAULT_MAX_RETRIES, false /*takeScreenshotOnFailure*/);

			String output = receiver.getOutput();
			Log.info(String.format("Command output -> %s", output));
			return output;
		} else {
			Log.error("executeShellCmd -> No connected devices found.");
		}

		return "";
	}

	private static boolean isTypeCommand(final String command) {
		return command.contains("input text");
	}

	private static void revertTypeTextCommand(String command) throws Exception {
		Log.method("revertTypeTextCommand", command);
		String text = command.replace("input text ", "").replace("\"", "");
		MobileActions.newAction().undoText(text);
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