package common.source;

import java.io.File;
import java.io.IOException;

public class AndroidAutomationTools {
	private static final String APPIUM_SERVER_HOSTNAME = "localhost";
	private static final String DEFAULT_EMULATOR_AVD_NAME = "android_23_google_apis_x86_Tab_S_8.4_Black";
	private static final String INSTALL_LAUNCH_APK_CMD = "InstallAPKLaunchMainActivity.cmd";
	private static final String START_ANDROID_TOOLS_CMD = "StartAndroidAutomationTools.cmd";
	private static final String START_APPIUM_SERVER_CMD = "StartAppiumServer.cmd";
	private static final String START_REACT_NATIVE_PACKAGER_CMD = "StartReactNativePackager.cmd";
	private static final String STOP_ANDROID_TOOLS_CMD = "StopAndroidAutomationTools.cmd";
	private static final Integer APPIUM_SERVER_PORT = 4723;
	private static final Integer CATCH_UP_TIME_IN_SECS = 3;
	private static final Integer DEFAULT_WAIT_BETWEEN_POLL_IN_MSEC = 1000;
	private static final Integer MAX_RETRIES_IN_POLL = 30;
	private static final Integer PING_TIMEOUT = 1000;

	public static class ShellCommands {
		public static final String DUMPSYS_ACTIVITY = "dumpsys activity";
		public static final String PM_LIST_PACKAGES = "pm list packages";
		public static final String GETPROP_INIT_SVC_BOOTANIM = "getprop init.svc.bootanim";
		public static final String SET_WINDOW_ANIMATION_SCALE = "content update --uri content://settings/system --bind value:s:0.0 --where 'name=\"window_animation_scale\"'";
		public static final String SET_TRANSITION_ANIMATION_SCALE = "content update --uri content://settings/system --bind value:s:0.0 --where 'name=\"transition_animation_scale\"'";
		public static final String SET_ANIMATOR_DURATION_SCALE = "content update --uri content://settings/system --bind value:s:0.0 --where 'name=\"animator_duration_scale\"'";
		public static final String DUMPSYS_GFXINFO = "dumpsys gfxinfo %s";     // provide app package name
		public static final String DUMPSYS_CPUINFO = "dumpsys cpuinfo";
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

		// start emulator.
		String startAndroidToolsCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib";
		String repoRootFolder = TestSetup.getRootPath();
		String startAndroidToolsCmd = START_ANDROID_TOOLS_CMD + String.format(" %s", "\"" + repoRootFolder + "\"") + " \"" + DEFAULT_EMULATOR_AVD_NAME + "\"";
		String command = "cd \"" + startAndroidToolsCmdFolder + "\" && " + startAndroidToolsCmd;
		Log.info("Executing start android automation tools command. Command -> " + command);
		ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ false);

		// wait for device to boot.
		AdbInterface.waitForDeviceToBeReady(AdbInterface.getAdbLocation());
		waitForDeviceToBoot();

		// start appium server.
		startAppiumServer();
	}

	public static void disableAnimations() throws Exception {
		AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), ShellCommands.SET_ANIMATOR_DURATION_SCALE);
		AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), ShellCommands.SET_TRANSITION_ANIMATION_SCALE);
		AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), ShellCommands.SET_WINDOW_ANIMATION_SCALE);
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

	public static String getCpuInfo() throws Exception {
		Log.method("getCpuInfo");
		return AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), ShellCommands.DUMPSYS_CPUINFO);
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

	private static void startAppiumServer() throws IOException {
		Log.method("startAppiumServer");
		String startAppiumServerCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib";
		String repoRootFolder = TestSetup.getRootPath();
		String startAppiumServerCmd = START_APPIUM_SERVER_CMD + String.format(" %s", "\"" + repoRootFolder + "\"");
		String command = "cd \"" + startAppiumServerCmdFolder + "\" && " + startAppiumServerCmd;
		Log.info("Executing start appium server command. Command -> " + command);
		ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ false);
		waitForAppiumServerToStart();
		waitForAppiumServerToCatchUp();
	}

	private static void waitForDeviceToBoot() {
		Log.method("waitForDeviceToBoot");
		PollManager.poll(()-> !AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), ShellCommands.GETPROP_INIT_SVC_BOOTANIM, result -> result.contains("stopped")),
				DEFAULT_WAIT_BETWEEN_POLL_IN_MSEC, 3 * MAX_RETRIES_IN_POLL);
	}

	private static void waitForAppiumServerToStart() {
		Log.method("waitForAppiumServerToStart");
		PollManager.poll(()-> !PingUtility.isEndPointAlive(APPIUM_SERVER_HOSTNAME, APPIUM_SERVER_PORT, PING_TIMEOUT),
				DEFAULT_WAIT_BETWEEN_POLL_IN_MSEC, MAX_RETRIES_IN_POLL);
	}

	private static void waitForAppiumServerToCatchUp() {
		Log.method("waitForAppiumServerToCatchUp");
		TestContext.INSTANCE.stayIdle(CATCH_UP_TIME_IN_SECS);
	}

	private static void waitForReactNativePackagerToCatchUp() {
		Log.method("waitForReactNativePackagerToCatchUp");
		TestContext.INSTANCE.stayIdle(3 * CATCH_UP_TIME_IN_SECS);
	}
}