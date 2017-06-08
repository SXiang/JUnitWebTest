package common.source;

import java.util.function.Predicate;

import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;
import com.android.ddmlib.MultiLineReceiver;

public class AdbInterface {
	private static final Integer DEFAULT_WAIT_BETWEEN_POLL_IN_MSEC = 1000;
	private static final Integer MAX_RETRIES_IN_POLL = 30;

	// TODO: Provide override value.
	public static String DEFAULT_ADB_LOCATION = "C:\\Program Files\\Android\\android-sdk\\platform-tools\\adb.exe";

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

	public static void init() {
		AndroidDebugBridge.init(false);
	}

	public static void waitForDeviceToBeReady(final String adbLocation) {
		Log.method("waitForDeviceToBeReady");
		if (adb == null) {
			adb = AndroidDebugBridge.createBridge(adbLocation, true);
		}
		PollManager.poll(() -> !adb.isConnected() || !adb.hasInitialDeviceList(), DEFAULT_WAIT_BETWEEN_POLL_IN_MSEC, 2 * MAX_RETRIES_IN_POLL);
	}

	public static String executeShellCmd(final String adbLocation, final String command) throws Exception {
		return executeShellCmdInternal(adbLocation, command);
	}

	public static boolean executeShellCmd(final String adbLocation, final String command, Predicate<String> testCondition) throws Exception {
		return testCondition.test(executeShellCmdInternal(adbLocation, command));
	}

	private static String executeShellCmdInternal(final String adbLocation, final String command) throws Exception {
		Log.method("executeShellCmdInternal", command);
		if (adb == null) {
			adb = AndroidDebugBridge.createBridge(adbLocation, true);
		}

		PollManager.poll(() -> !adb.isConnected() || !adb.hasInitialDeviceList(), DEFAULT_WAIT_BETWEEN_POLL_IN_MSEC, 2 * MAX_RETRIES_IN_POLL);
		IDevice[] devices = AndroidDebugBridge.getBridge().getDevices();
		if (devices != null && devices.length > 0) {
			IDevice device = devices[0];
			ShellOutputReceiver receiver = new ShellOutputReceiver();
			Log.info(String.format("Executing -> adb shell %s", command));
			device.executeShellCommand(command, receiver);
			String output = receiver.getOutput();
			Log.info(String.format("Command output -> %s", output));
			return output;
		} else {
			throw new Exception("executeShellCmd -> No connected devices found.");
		}
	}

	public static void stop() {
		AndroidDebugBridge.disconnectBridge();
		AndroidDebugBridge.terminate();
	}
}