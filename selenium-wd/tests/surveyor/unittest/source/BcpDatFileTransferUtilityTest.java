package surveyor.unittest.source;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.BeforeClass;
import org.junit.Test;

import common.source.BcpDatFileTransferUtility;
import common.source.FileTransferUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;

public class BcpDatFileTransferUtilityTest {

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
	public void testBcpDatFileTransferToDBServer() throws IOException {
		String[] datFilesToTransfer = {"Asset.BA.dat", "Boundary.BA.dat"};
		for (String datFile : datFilesToTransfer) {
			BcpDatFileTransferUtility.transferDatFileToDBServer(datFile);
		}
	}
}