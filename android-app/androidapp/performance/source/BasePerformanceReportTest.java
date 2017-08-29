package androidapp.performance.source;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import androidapp.regression.source.BaseReportTest;
import common.source.AndroidDeviceInterface;
import common.source.DateUtility;
import common.source.Log;
import common.source.ProcessUtility;
import common.source.TestContext;
import common.source.TestSetup;

public class BasePerformanceReportTest extends BaseReportTest {

	private static final String PUBLISH_ANDROID_APP_PERF_TEST_RESULTS_CMD = "Publish-AndroidAppPerfTestResults.cmd";
	private static Long testStartTimeInEpoch = Long.MIN_VALUE;

	@BeforeClass
	public static void setupBeforePerfTestClass() throws Exception {
		Log.method("setupBeforePerfTestClass");
		testStartTimeInEpoch = DateUtility.getCurrentUnixEpochTime()/1000;
	}

	@Before
	public void setupBeforePerfTest() throws Exception {
		if (TestContext.INSTANCE.getTestSetup().isAndroidTestPerfProfileVisualBarsEnabled()) {
			AndroidDeviceInterface.turnOnProfileVisualBars();
		} else if (TestContext.INSTANCE.getTestSetup().isAndroidTestPerfProfileDumpSysEnabled()) {
			AndroidDeviceInterface.turnOnProfileGfxInfo();
		}

		AndroidDeviceInterface.turnOnDebugOverdraw();
	}

	@After
	public void tearDownAfterPerfTest() throws Exception {
		AndroidDeviceInterface.turnOffProfileDebug();
		AndroidDeviceInterface.turnOffDebugOverdraw();
	}

	@AfterClass
	public static void tearDownAfterPerfTestClass() throws Exception {
		Log.method("tearDownAfterPerfTestClass");

		// publish perf test result to store
		publishTestResultsToStore();
	}

	private static void publishTestResultsToStore() throws IOException {
		Log.method("publishTestResultsToStore");
		String publishResultsToStoreCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib";
		String workingFolder = TestSetup.getRootPath();
		String publishResultsToStoreCmd = PUBLISH_ANDROID_APP_PERF_TEST_RESULTS_CMD + String.format(" %s %s %d", "\"" + workingFolder + "\"", "\"" +
			  TestContext.INSTANCE.getTestSetup().getAndroidPerfTestResultStoreLocation() + "\"", testStartTimeInEpoch);
		String command = "cd \"" + publishResultsToStoreCmdFolder + "\" && " + publishResultsToStoreCmd;
		Log.info("Executing publish android app perf test results command. Command -> " + command);
		ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ true);

	}
}