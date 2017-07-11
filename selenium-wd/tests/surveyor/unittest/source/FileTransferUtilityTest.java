package surveyor.unittest.source;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.BeforeClass;
import org.junit.Test;

import common.source.FileTransferUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;

public class FileTransferUtilityTest {

	private static final String DESTINATION_DIR = "C:\\temp";
	private static final String LOG_FOLDER = "C:\\QATestLogs";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestSetup testSetup = new TestSetup(true /* initialize */);
		String rootPath;
		try {
			rootPath = TestSetup.getRootPath();
			testSetup.loadTestProperties(rootPath);
		} catch (IOException e) {
			Log.error(e.getMessage());
		}
		testSetup.initializeDBProperties();
		TestContext.INSTANCE.setTestSetup(testSetup);
	}

	@Test
	public void testFileTransfer() throws IOException {
		String executionPath = TestSetup.getExecutionPath(TestSetup.getRootPath());
		String destMachineIPAddress = TestContext.INSTANCE.getTestSetup().getDbServerMachineIPAddress();
		String destMachineUsername = TestContext.INSTANCE.getTestSetup().getDbServerMachineUsername();
		String destMachinePassword = TestContext.INSTANCE.getTestSetup().getDbServerMachinePassword();
		String sourceFileLocation = Paths.get(executionPath, "data\\sql\\Boundary.BA.dat").toString();
		String destFileLocation = Paths.get(DESTINATION_DIR, "Boundary.BA.dat").toString();
		FileTransferUtility.transferFile(destMachineIPAddress, destMachineUsername, destMachinePassword, sourceFileLocation, destFileLocation, LOG_FOLDER);
	}
}