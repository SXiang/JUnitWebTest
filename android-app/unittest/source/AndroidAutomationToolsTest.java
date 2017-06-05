package unittest.source;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import common.source.AndroidAutomationTools;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;

public class AndroidAutomationToolsTest {

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
	public void testStart() throws IOException {
		AndroidAutomationTools.start();
	}

	@Test
	public void testStartReactNative() throws IOException {
		AndroidAutomationTools.startReactNative();
	}

	@Test
	public void testStop() throws IOException {
		AndroidAutomationTools.stop();
	}

	@Test
	public void testRestart() throws IOException {
		AndroidAutomationTools.restart();
	}
}