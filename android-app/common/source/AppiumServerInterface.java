package common.source;

import java.io.IOException;

public class AppiumServerInterface {
	private static final String APPIUM = "appium";
	private static final String NODE_EXE = "node.exe";
	private static final String APPIUM_SERVER_HOSTNAME = "localhost";
	private static final String START_APPIUM_SERVER_CMD = "StartAppiumServer.cmd";
	private static final String STOP_APPIUM_SERVER_CMD = "StopAppiumServer.cmd";
	private static final Integer APPIUM_SERVER_PORT = 4723;
	private static final Integer CATCH_UP_TIME_IN_SECS = 3;
	private static final Integer DEFAULT_WAIT_BETWEEN_POLL_IN_MSEC = 1000;
	private static final Integer MAX_RETRIES_IN_POLL = 30;
	private static final Integer PING_TIMEOUT = 1000;

	public static void ensureAppiumServerIsRunning() throws IOException {
		Log.method("ensureAppiumServerIsRunning");
		if (!ProcessUtility.isProcessRunning(NODE_EXE, APPIUM)) {
			startAppiumServer();
			waitForAppiumServerToStart();
			waitForAppiumServerToCatchUp();
		}
	}

	public static void startAppiumServer() throws IOException {
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

	public static void stopAppiumServer() throws Exception {
		Log.method("stopAppiumServer");
		String stopAppiumServerCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib";
		String repoRootFolder = TestSetup.getRootPath();
		String stopAppiumServerCmd = stopAppiumServerCmdFolder + "\\" + STOP_APPIUM_SERVER_CMD + String.format(" %s", "\"" + repoRootFolder + "\"");
		String command = "cd \"" + stopAppiumServerCmdFolder + "\" && " + stopAppiumServerCmd;
		Log.info("Executing stop appium server command. Command -> " + command);
		ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ false);
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
}