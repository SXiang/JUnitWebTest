package common.source;

import java.io.File;
import java.io.IOException;

public class BackPackSimulator {
	private static final String STOP_SIMULATOR_CMD = "stop-simulator.cmd";
	private static final String START_SIMULATOR_CMD = "start-simulator.cmd";

	public static void startSimulator() throws IOException {
		String startBackPackSimCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib" + File.separator + "BackPackSim";
		String repoRootFolder = TestSetup.getRootPath().replace("\\surveyor-qa", "");
		String startBackPackSimCmd = START_SIMULATOR_CMD + String.format(" %s", repoRootFolder);
		String command = "cd \"" + startBackPackSimCmdFolder + "\" && " + startBackPackSimCmd;
		Log.info("Executing start backpack simulator command. Command -> " + command);
		ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ false);
	}

	public static void stopSimulator() throws IOException {
		String stopBackPackSimCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib" + File.separator + "BackPackSim";
		String stopBackPackSimCmdFullPath = stopBackPackSimCmdFolder + File.separator + STOP_SIMULATOR_CMD;
		String command = "cd \"" + stopBackPackSimCmdFolder + "\" && " + stopBackPackSimCmdFullPath + " " + TestSetup.getRootPath();
		Log.info("Executing stop backpack simulator. Command -> " + command);
		ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ false);
	}
}
