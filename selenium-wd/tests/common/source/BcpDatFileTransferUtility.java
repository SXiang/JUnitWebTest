package common.source;

import java.io.IOException;
import java.nio.file.Paths;

public class BcpDatFileTransferUtility {

	private static final String DAT_FILE_FOLDER = "data\\sql";

	public static void transferDatFileToDBServer(String datFilename) throws IOException {
		String executionPath = TestSetup.getExecutionPath(TestSetup.getRootPath());
		String destMachineIPAddress = TestContext.INSTANCE.getTestSetup().getDbServerMachineIPAddress();
		String destMachineUsername = TestContext.INSTANCE.getTestSetup().getDbServerMachineUsername();
		String destMachinePassword = TestContext.INSTANCE.getTestSetup().getDbServerMachinePassword();
		String sourceFileLocation = Paths.get(executionPath, DAT_FILE_FOLDER + "\\" + datFilename).toString();
		String destinationDir = Paths.get(executionPath, DAT_FILE_FOLDER).toString();
		String destFileLocation = Paths.get(destinationDir, datFilename).toString();
		FileTransferUtility.transferFile(destMachineIPAddress, destMachineUsername, destMachinePassword, sourceFileLocation, destFileLocation);
	}
}