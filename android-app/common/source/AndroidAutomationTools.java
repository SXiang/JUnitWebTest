package common.source;

import java.io.File;
import java.io.IOException;

public class AndroidAutomationTools {
	private static final String DEFAULT_EMULATOR_AVD_NAME = "android_23_google_apis_x86";
	private static final String APPIUM_SERVER_HOSTNAME = "localhost";
	private static final Integer APPIUM_SERVER_PORT = 4723;
	private static final String STOP_ANDROID_TOOLS_CMD = "StopAndroidAutomationTools.cmd";
	private static final String START_ANDROID_TOOLS_CMD = "StartAndroidAutomationTools.cmd";
	private static final String START_REACT_NATIVE_PACKAGER_CMD = "StartReactNativePackager.cmd";
	private static final Integer DEFAULT_WAIT_BETWEEN_POLL_IN_MSEC = 1000;
	private static final Integer PING_TIMEOUT = 1000;
	private static final Integer MAX_RETRIES_IN_POLL = 30;
	private static final Integer CATCH_UP_TIME_IN_SECS = 5;

	public static void start() throws IOException {
		Log.method("start");
		String startAndroidToolsCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib";
		String repoRootFolder = TestSetup.getRootPath();
		String startAndroidToolsCmd = START_ANDROID_TOOLS_CMD + String.format(" %s", "\"" + repoRootFolder + "\"") + " \"" + DEFAULT_EMULATOR_AVD_NAME + "\"";
		String command = "cd \"" + startAndroidToolsCmdFolder + "\" && " + startAndroidToolsCmd;
		Log.info("Executing start android automation tools command. Command -> " + command);
		ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ false);
		waitForAppiumServerToStart();
		waitForAppiumServerToCatchUp();
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

	public static void startReactNative() throws IOException {
		Log.method("start");
		String startReactNativeCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib";
		String workingFolder = TestSetup.getRootPath();
		String repoRootFolder = TestSetup.getRootPath().replace("\\surveyor-qa", "");
		String startReactNativeCmd = START_REACT_NATIVE_PACKAGER_CMD + String.format(" %s %s", "\"" + workingFolder + "\"", "\"" + repoRootFolder + "\"");
		String command = "cd \"" + startReactNativeCmdFolder + "\" && " + startReactNativeCmd;
		Log.info("Executing start react native packager command. Command -> " + command);
		ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ false);
		waitForReactNativePackagerToCatchUp();
	}

	public static void restart() throws IOException {
		Log.method("restart");
		stop();
		start();
	}

	private static void waitForAppiumServerToStart() {
		Log.method("waitForAppiumServerToStart");
		PollManager.poll(()-> !PingUtility.isEndPointAlive(APPIUM_SERVER_HOSTNAME, APPIUM_SERVER_PORT, PING_TIMEOUT),
				DEFAULT_WAIT_BETWEEN_POLL_IN_MSEC, MAX_RETRIES_IN_POLL);;
	}

	private static void waitForAppiumServerToCatchUp() {
		Log.method("waitForAppiumServerToCatchUp");
		TestContext.INSTANCE.stayIdle(CATCH_UP_TIME_IN_SECS);
	}

	private static void waitForReactNativePackagerToCatchUp() {
		Log.method("waitForReactNativePackagerToCatchUp");
		TestContext.INSTANCE.stayIdle(CATCH_UP_TIME_IN_SECS);
	}
}