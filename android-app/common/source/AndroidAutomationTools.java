package common.source;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AndroidAutomationTools {
	private static final String DEFAULT_EMULATOR_AVD_NAME = "android_23_google_apis_x86_Tab_S_8.4_Black";
	private static final String INSTALL_LAUNCH_APK_CMD = "InstallAPKLaunchMainActivity.cmd";
	private static final String START_ANDROID_TOOLS_CMD = "StartAndroidAutomationTools.cmd";
	private static final String START_REACT_NATIVE_PACKAGER_CMD = "StartReactNativePackager.cmd";
	private static final String STOP_ANDROID_TOOLS_CMD = "StopAndroidAutomationTools.cmd";
	private static final Integer CATCH_UP_TIME_IN_SECS = 3;
	private static final Integer DEFAULT_WAIT_BETWEEN_POLL_IN_MSEC = 1000;
	private static final Integer MAX_RETRIES_IN_POLL = 30;

	public static class ShellCommands {
		public static final String DUMPSYS_ACTIVITY = "dumpsys activity";
		public static final String PM_LIST_PACKAGES = "pm list packages";
		public static final String GETPROP_INIT_SVC_BOOTANIM = "getprop init.svc.bootanim";
		public static final String SET_WINDOW_ANIMATION_SCALE = "content update --uri content://settings/system --bind value:s:0.0 --where 'name=\"window_animation_scale\"'";
		public static final String SET_TRANSITION_ANIMATION_SCALE = "content update --uri content://settings/system --bind value:s:0.0 --where 'name=\"transition_animation_scale\"'";
		public static final String SET_ANIMATOR_DURATION_SCALE = "content update --uri content://settings/system --bind value:s:0.0 --where 'name=\"animator_duration_scale\"'";
		public static final String DUMPSYS_GFXINFO = "dumpsys gfxinfo %s";     // provide app package name
		public static final String DUMPSYS_MEMINFO = "dumpsys meminfo %s";     // provide app package name
		public static final String DUMPSYS_CPUINFO = "dumpsys cpuinfo";
		public static final String TURN_ON_GPS = "settings put secure location_providers_allowed +gps";
		public static final String TURN_OFF_GPS = "settings put secure location_providers_allowed -gps";
		public static final String TURN_ON_NETWORK = "settings put secure location_providers_allowed +network";
		public static final String TURN_OFF_NETWORK = "settings put secure location_providers_allowed -network";
		public static final String TURN_ON_WIFI = "settings put secure location_providers_allowed +wifi";
		public static final String TURN_OFF_WIFI = "settings put secure location_providers_allowed -wifi";
		public static final String GPS_WIFI_NETWORK_STATUS = "settings get secure location_providers_allowed";
		public static final String DUMPSYS_INPUT_METHOD = "dumpsys input_method";
		public static final String DUMPSYS_POWER = "dumpsys power";
		public static final String TURN_ON_PROFILE_GFXINFO = "setprop debug.hwui.profile true";
		public static final String TURN_ON_PROFILE_VISUAL_BARS = "setprop debug.hwui.profile visual_bars";
		public static final String TURN_OFF_PROFILE_DEBUG = "setprop debug.hwui.profile false";
		public static final String TURN_ON_DEBUG_OVERDRAW = "setprop debug.hwui.overdraw true";
		public static final String TURN_OFF_DEBUG_OVERDRAW = "setprop debug.hwui.overdraw false";
		public static final String EXERCISE_MONKEY = "monkey -p %s -v %d -s %d --throttle %d";    // provide package name, num_events, num_seed, num_throttle
		public static final String PROCESSES = "ps";
		public static final String FILE_INFO = "ls -l %s";      // provide full path to file.
	}

	public static class AndroidFileInfo {
		public String PERMISSIONS;
		public Integer FID;
		public String USER;
		public String GROUP;
		public Integer SIZE;
		public String DATE;
		public String TIME;
		public String NAME;

		private static String PARSE_REGEX_EMULATOR = "(.+)\\s+(.+)\\s+(.+)\\s+(.+)\\s+(.+)\\s+(.+)\\s+(.+)";
		private static String PARSE_REGEX_DEVICE = "(.+)\\s+(.+)\\s+(.+)\\s+(.+)\\s+(.+)\\s+(.+)\\s+(.+)\\s+(.+)";

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
		}

		public static AndroidFileInfo fromString(String shellText) {
			String[] lines = shellText.split(BaseHelper.getLineSeperator());
			for (String line : lines) {
				String regEx = PARSE_REGEX_EMULATOR;
				if (TestContext.INSTANCE.isRunningOnAndroidDevice()) {
					regEx = PARSE_REGEX_DEVICE;
				}

				if (RegexUtility.matchesPattern(line, regEx)) {
					List<String> groups = RegexUtility.getMatchingGroups(shellText, regEx);
					AndroidFileInfo procInfo = new AndroidFileInfo();
					if (TestContext.INSTANCE.isRunningOnAndroidDevice()) {
						procInfo.PERMISSIONS = groups.get(1);
						procInfo.FID = Integer.valueOf(groups.get(2));
						procInfo.USER = groups.get(3);
						procInfo.GROUP = groups.get(4);
						procInfo.SIZE = Integer.valueOf(groups.get(5));
						procInfo.DATE = groups.get(6);
						procInfo.TIME = groups.get(7);
						procInfo.NAME = groups.get(8);
					} else {
						procInfo.PERMISSIONS = groups.get(1);
						procInfo.USER = groups.get(2);
						procInfo.GROUP = groups.get(3);
						procInfo.SIZE = Integer.valueOf(groups.get(4));
						procInfo.DATE = groups.get(5);
						procInfo.TIME = groups.get(6);
						procInfo.NAME = groups.get(7);
					}

					return procInfo;
				}
			}

			return null;
		}
	}

	public static class AndroidPaths {
		public static final String DEFAULT_ADB_LOCATION = "C:\\Program Files\\Android\\android-sdk\\platform-tools\\adb.exe";
	}

	public static void installLaunchAPK(String apkFilePath, String waitActivityName) throws IOException {
		Log.method("installLaunchAPK", apkFilePath, waitActivityName);
		String installLaunchAPKCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib";
		String repoRootFolder = TestSetup.getRootPath();
		String installLaunchAPKCmd = INSTALL_LAUNCH_APK_CMD + String.format(" %s %s %s", "\"" + repoRootFolder + "\"", "\"" + apkFilePath + "\"", "\"" + waitActivityName + "\"");
		String command = "cd \"" + installLaunchAPKCmdFolder + "\" && " + installLaunchAPKCmd;
		Log.info("Executing install/launch APK command. Command -> " + command);
		ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ true);
	}

	public static boolean isAppDrawOverlayDisplayed() throws Exception {
		Log.method("isAppDrawOverlayDisplayed");
		return AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), ShellCommands.DUMPSYS_ACTIVITY, result -> result.contains("AppDrawOverlaySettingsActivity") &&
				result.contains("MANAGE_OVERLAY_PERMISSION"));
	}

	public static void restart() throws IOException {
		Log.method("restart");
		stop();
		start();
	}

	public static void start() throws IOException {
		Log.method("start");
		start(NetworkEmulation.createDefault());
	}

	public static void start(NetworkEmulation networkEmulation) throws IOException {
		Log.method("start", (networkEmulation==null) ? "null" : networkEmulation);

		// start emulator.
		startEmulator(networkEmulation.toEmulatorArgs());

		// start appium server.
		startAppiumServer();
	}

	public static void disableAnimations() throws Exception {
		AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), ShellCommands.SET_ANIMATOR_DURATION_SCALE);
		AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), ShellCommands.SET_TRANSITION_ANIMATION_SCALE);
		AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), ShellCommands.SET_WINDOW_ANIMATION_SCALE);
	}

	public static void exerciseMonkey(Integer events, Integer throttleInMsec) throws Exception {
		Log.method("exerciseMonkey", events, throttleInMsec);
		AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), String.format(ShellCommands.EXERCISE_MONKEY,
				AppConstants.APP_PACKAGE_NAME, events, DateUtility.getCurrentUnixEpochTime(), throttleInMsec));
	}

	public static AndroidFileInfo getFileInfo(String filePath) throws Exception {
		String shellText = AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), String.format(ShellCommands.FILE_INFO, filePath));
		Log.info(String.format("Shell output -> %s", shellText));
		return AndroidFileInfo.fromString(shellText);
	}

	public static boolean isPackageInstalled(String packageName) throws Exception {
		String installedPackages = AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), ShellCommands.PM_LIST_PACKAGES);
		return installedPackages.toLowerCase().contains(packageName.toLowerCase());
	}

	public static String getGraphicsInfo() throws Exception {
		Log.method("getGraphicsInfo");
		return AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(),
				String.format(ShellCommands.DUMPSYS_GFXINFO, AppConstants.APP_PACKAGE_NAME));
	}

	public static String getMemInfo() throws Exception {
		Log.method("getMemInfo");
		return AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(),
				String.format(ShellCommands.DUMPSYS_MEMINFO, AppConstants.APP_PACKAGE_NAME));
	}

	public static String getCpuInfo() throws Exception {
		Log.method("getCpuInfo");
		return AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), ShellCommands.DUMPSYS_CPUINFO);
	}

	public static void ensureAppiumServerIsRunning() throws IOException {
		Log.method("ensureAppiumServerIsRunning");
		AppiumServerInterface.ensureAppiumServerIsRunning();
	}

	public static void startReactNative() throws IOException {
		Log.method("startReactNative");
		String startReactNativeCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib";
		String workingFolder = TestSetup.getRootPath();
		String repoRootFolder = TestSetup.getRootPath().replace("\\surveyor-qa", "");
		String startReactNativeCmd = START_REACT_NATIVE_PACKAGER_CMD + String.format(" %s %s", "\"" + workingFolder + "\"", "\"" + repoRootFolder + "\"");
		String command = "cd \"" + startReactNativeCmdFolder + "\" && " + startReactNativeCmd;
		Log.info("Executing start react native packager command. Command -> " + command);
		ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ false);
		waitForReactNativePackagerToCatchUp();
	}

	public static void stop() throws IOException {
		Log.method("stop");
		String stopAndroidToolsCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib";
		String repoRootFolder = TestSetup.getRootPath();
		String stopAndroidToolsCmdFullPath = stopAndroidToolsCmdFolder + File.separator + STOP_ANDROID_TOOLS_CMD + String.format(" %s", "\"" + repoRootFolder + "\"");
		String command = "cd \"" + stopAndroidToolsCmdFolder + "\" && " + stopAndroidToolsCmdFullPath;
		Log.info("Executing stop android automation tools. Command -> " + command);
		ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ false);
	}

	public static void startAppiumServer() throws IOException {
		Log.method("startAppiumServer");
		AppiumServerInterface.startAppiumServer();
	}

	public static void stopAppiumServer() throws Exception  {
		Log.method("stopAppiumServer");
		AppiumServerInterface.stopAppiumServer();
	}

	private static void startEmulator(String networkArgs) throws IOException {
		Log.method("startEmulator", networkArgs);
		String startAndroidToolsCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib";
		String repoRootFolder = TestSetup.getRootPath();
		String startAndroidToolsCmd = START_ANDROID_TOOLS_CMD + String.format(" %s", "\"" + repoRootFolder + "\"") + " \"" + DEFAULT_EMULATOR_AVD_NAME + "\""
				+ " \"" + networkArgs + "\"";
		String command = "cd \"" + startAndroidToolsCmdFolder + "\" && " + startAndroidToolsCmd;
		Log.info("Executing start android automation tools command. Command -> " + command);
		ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ false);

		// wait for device to boot.
		AdbInterface.waitForDeviceToBeReady(AdbInterface.getAdbLocation());
		waitForDeviceToBoot();
	}

	private static void waitForDeviceToBoot() {
		Log.method("waitForDeviceToBoot");
		PollManager.poll(()-> !AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), ShellCommands.GETPROP_INIT_SVC_BOOTANIM, result -> result.contains("stopped")),
				DEFAULT_WAIT_BETWEEN_POLL_IN_MSEC, 3 * MAX_RETRIES_IN_POLL);
	}

	private static void waitForReactNativePackagerToCatchUp() {
		Log.method("waitForReactNativePackagerToCatchUp");
		TestContext.INSTANCE.stayIdle(3 * CATCH_UP_TIME_IN_SECS);
	}
}
