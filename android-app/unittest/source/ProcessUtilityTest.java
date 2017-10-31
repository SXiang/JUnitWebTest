package unittest.source;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import common.source.AdbInterface;
import common.source.AndroidAutomationTools;
import common.source.Log;
import common.source.ProcessUtility;
import common.source.TestContext;
import common.source.TestSetup;

public class ProcessUtilityTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Initialize TestSetup to instantiate the TestContext.
		TestSetup testSetup = new TestSetup(false /* skip initialization */);
		String rootPath;
		try {
			rootPath = TestSetup.getRootPath();
			testSetup.loadTestProperties(rootPath);
		} catch (IOException e) {
			Log.error(e.getMessage());
		}

		testSetup.initialize();
		TestContext.INSTANCE.setTestSetup(testSetup);

		AdbInterface.init(testSetup.getAdbLocation());
		AndroidAutomationTools.start();
	}

	@AfterClass
	public static void tearDownAfterClass() throws IOException {
		AndroidAutomationTools.stop();
		AdbInterface.stop();
	}

	@Test
	public void testProcessRunningWithCommandText_Success() throws IOException {
		assertTrue("dummyinstrmgr process was not found", ProcessUtility.isProcessRunning("python.exe", "dummyinstrmgr.py"));
		assertTrue("odorcallServer process was not found", ProcessUtility.isProcessRunning("python.exe", "odorcallServer.py"));
		assertTrue("dummylinearfitteralarm process was not found", ProcessUtility.isProcessRunning("python.exe", "dummylinearfitteralarm.py"));
		assertTrue("simLinearFitterBroadcaster process was not found", ProcessUtility.isProcessRunning("python.exe", "simLinearFitterBroadcaster.py"));
		assertTrue("simDataManagerBroadcaster process was not found", ProcessUtility.isProcessRunning("python.exe", "simDataManagerBroadcaster.py"));
	}

	@Test
	public void testProcessRunningWithCommandText_Failure() throws IOException {
		assertTrue("Invalid dummyinstrmgr777 process should not be found", !ProcessUtility.isProcessRunning("python.exe", "dummyinstrmgr777.py"));
		assertTrue("Invalid odorcallServer777 process should not be found", !ProcessUtility.isProcessRunning("python.exe", "odorcallServer777.py"));
		assertTrue("Invalid dummylinearfitteralarm777 process should not be found", !ProcessUtility.isProcessRunning("python.exe", "dummylinearfitteralarm777.py"));
		assertTrue("Invalid simLinearFitterBroadcaster777 process should not be found", !ProcessUtility.isProcessRunning("python.exe", "simLinearFitterBroadcaster777.py"));
		assertTrue("Invalid simDataManagerBroadcaster777 process should not be found", !ProcessUtility.isProcessRunning("python.exe", "simDataManagerBroadcaster777.py"));
	}
}
