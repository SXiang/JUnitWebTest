package common.source;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class BcpDatFileTransferUtility {

	private static final String CLEANUP_BCP_FILES_CMD = "Cleanup-BCPRemoteFiles.cmd";
	private static final String DAT_FILE_FOLDER = "data\\sql";
	private static final String LOG_FOLDER = "C:\\QATestLogs";

	public static void transferDatFileToDBServer(String datFilename) throws IOException {
		String executionPath = TestSetup.getExecutionPath(TestSetup.getRootPath());
		String destMachineIPAddress = TestContext.INSTANCE.getTestSetup().getDbServerMachineIPAddress();
		String destMachineUsername = TestContext.INSTANCE.getTestSetup().getDbServerMachineUsername();
		String destMachinePassword = TestContext.INSTANCE.getTestSetup().getDbServerMachinePassword();
		String sourceFileLocation = Paths.get(executionPath, DAT_FILE_FOLDER + "\\" + datFilename).toString();
		String destinationDir = Paths.get(executionPath, DAT_FILE_FOLDER).toString();
		String destFileLocation = Paths.get(destinationDir, datFilename).toString();
		FileTransferUtility.transferFile(destMachineIPAddress, destMachineUsername, destMachinePassword, sourceFileLocation, destFileLocation, LOG_FOLDER);
	}

	public static void cleanupBcpDatFilesOnRemoteMachine(String[] datFiles) throws IOException {
		String destMachineIPAddress = TestContext.INSTANCE.getTestSetup().getDbServerMachineIPAddress();
		String destMachineUsername = TestContext.INSTANCE.getTestSetup().getDbServerMachineUsername();
		String destMachinePassword = TestContext.INSTANCE.getTestSetup().getDbServerMachinePassword();
		String filesToDelete = String.join(",", datFiles);

		String workingFolder = TestSetup.getRootPath();
		String cleanupDatFilesCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib";
		String cleanupDatFilesCmdFullPath = cleanupDatFilesCmdFolder + File.separator + CLEANUP_BCP_FILES_CMD;
		String command = "cd \"" + cleanupDatFilesCmdFolder + "\" && " + cleanupDatFilesCmdFullPath +
				String.format(" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\"",
						workingFolder, destMachineIPAddress, destMachineUsername, destMachinePassword, filesToDelete);
		Log.info("Cleanup BCP .dat files on remote machine. Command -> " + command);
		ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ true);
	}

}