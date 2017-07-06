package common.source;

import java.io.File;
import java.io.IOException;

public class BackPackSimulator {
	private static final String SIMULATOR_HOSTNAME = "localhost";
	private static final String STOP_SIMULATOR_CMD = "stop-simulator.cmd";
	private static final String START_SIMULATOR_CMD = "start-simulator.cmd";
	private static final String SETUP_SIMULATOR_EXTRAS_CMD = "Setup-SimulatorExtras.cmd";
	private static final String PAUSE_RESUME_WIN_PROCESSES_CMD = "PauseResumeWinProcesses.cmd";
	private static final Integer SIMULATOR_PORT = 3000;
	private static final Integer DEFAULT_WAIT_BETWEEN_POLL_IN_MSEC = 1000;
	private static final Integer PING_TIMEOUT = 1000;
	private static final Integer MAX_RETRIES_IN_POLL = 30;
	private static final Integer CATCH_UP_TIME_IN_SECS = 3;

	public static void startSimulator() throws IOException {
		Log.method("startSimulator");
		ensureSimulatorExtrasPresent();
		String startBackPackSimCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib" + File.separator + "BackPackSim";
		String repoRootFolder = TestSetup.getRootPath().replace("\\surveyor-qa", "");
		String startBackPackSimCmd = START_SIMULATOR_CMD + String.format(" %s", repoRootFolder);
		String command = "cd \"" + startBackPackSimCmdFolder + "\" && " + startBackPackSimCmd;
		Log.info("Executing start backpack simulator command. Command -> " + command);
		ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ false);
		waitForSimulatorProcessesToStart();
		waitForSimulatorProcessesToCatchUp();
	}

	private static void ensureSimulatorExtrasPresent() throws IOException {
		Log.method("ensureSimulatorExtrasPresent");
		String setupBackPackSimExtrasCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib" + File.separator + "BackPackSim";
		String setupBackPackSimExtrasCmdFullPath = setupBackPackSimExtrasCmdFolder + File.separator + SETUP_SIMULATOR_EXTRAS_CMD;
		String command = "cd \"" + setupBackPackSimExtrasCmdFolder + "\" && " + setupBackPackSimExtrasCmdFullPath + " " + TestSetup.getRootPath();
		Log.info("Executing setup simulator extras. Command -> " + command);
		ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ true);
	}

	public static void stopSimulator() throws IOException {
		Log.method("stopSimulator");
		String stopBackPackSimCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib" + File.separator + "BackPackSim";
		String stopBackPackSimCmdFullPath = stopBackPackSimCmdFolder + File.separator + STOP_SIMULATOR_CMD;
		String command = "cd \"" + stopBackPackSimCmdFolder + "\" && " + stopBackPackSimCmdFullPath + " " + TestSetup.getRootPath();
		Log.info("Executing stop backpack simulator. Command -> " + command);
		ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ false);
	}

	public static void pauseSimulatorProcesses() throws IOException {
		pauseResumeSimulatorProcesses(false /*isResume*/);
	}

	public static void resumeSimulatorProcesses() throws IOException {
		pauseResumeSimulatorProcesses(true /*isResume*/);
	}

	private static void pauseResumeSimulatorProcesses(boolean isResume) throws IOException {
		Log.method("pauseResumeSimulatorProcesses");
		String pauseResumeProcessesCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib";
		String repoRootFolder = TestSetup.getRootPath();
		String arguments = "OdorCallServer|SimLinearFitter|SimDataBroadcaster";
		String pauseResumeArg = isResume ? "true" : "false";
		String pauseResumeProcessesCmdFullPath = pauseResumeProcessesCmdFolder + File.separator + PAUSE_RESUME_WIN_PROCESSES_CMD +
				String.format(" %s %s %s", "\"" + repoRootFolder + "\"", "\"" + arguments + "\"", "\"" + pauseResumeArg + "\"");
		String command = "cd \"" + pauseResumeProcessesCmdFolder + "\" && " + pauseResumeProcessesCmdFullPath;
		Log.info("Executing pause/resume backpack simulator processes. Command -> " + command);
		ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ false);
	}

	public static void restartSimulator() throws IOException {
		Log.method("restartSimulator");
		stopSimulator();
		TestContext.INSTANCE.stayIdle(3);
		startSimulator();
	}

	private static void waitForSimulatorProcessesToStart() {
		Log.method("waitForSimulatorProcessesToStart");
		PollManager.poll(()-> !PingUtility.isEndPointAlive(SIMULATOR_HOSTNAME, SIMULATOR_PORT, PING_TIMEOUT),
				DEFAULT_WAIT_BETWEEN_POLL_IN_MSEC, MAX_RETRIES_IN_POLL);;
	}

	private static void waitForSimulatorProcessesToCatchUp() {
		Log.method("waitForSimulatorProcessesToCatchUp");
		TestContext.INSTANCE.stayIdle(CATCH_UP_TIME_IN_SECS);
	}
}
