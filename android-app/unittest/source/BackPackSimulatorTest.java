package unittest.source;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import common.source.BackPackSimulator;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;

public class BackPackSimulatorTest {

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
	}

	@Test
	public void testStartSimulator() throws IOException {
		BackPackSimulator.startSimulator();
	}

	@Test
	public void testPauseSimulatorProcesses() throws IOException {
		BackPackSimulator.startSimulator();
		BackPackSimulator.pauseSimulatorProcesses();
	}

	@Test
	public void testResumeSimulatorProcesses() throws IOException {
		BackPackSimulator.resumeSimulatorProcesses();
	}

	@Test
	public void testStopSimulator() throws IOException {
		BackPackSimulator.stopSimulator();
	}
}