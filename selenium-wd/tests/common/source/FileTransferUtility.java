package common.source;

import java.io.File;
import java.io.IOException;

public class FileTransferUtility {

	private static final String TRANSFER_FILE_CMD = "File-Transfer.cmd";

	public static void transferFile(String destMachineIPAddress, String destMachineUsername, String destMachinePassword,
			String sourceFileLocation, String destFileLocation) throws IOException {
		String workingFolder = TestSetup.getRootPath();
		String transferFileCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib";
		String transferFileCmdFullPath = transferFileCmdFolder + File.separator + TRANSFER_FILE_CMD;
		String command = "cd \"" + transferFileCmdFolder + "\" && " + transferFileCmdFullPath +
				String.format(" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\"",
						workingFolder, workingFolder, destMachineIPAddress, destMachineUsername,
						destMachinePassword, sourceFileLocation, destFileLocation);
		Log.info("Transfer File. Command -> " + command);
		ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ true);
	}
}