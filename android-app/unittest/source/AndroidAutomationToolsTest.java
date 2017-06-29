package unittest.source;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import common.source.AdbInterface;
import common.source.AndroidAutomationTools;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;

public class AndroidAutomationToolsTest {

	private static final String APP_DRAW_OVERLAY_SETTINGS_ACTIVITY = "AppDrawOverlaySettingsActivity";
	private static final String TEST_APK_LOCATION = "C:\\Repositories\\surveyor-qa\\apk\\app-debug-1.0.0-SNAPSHOT-82.apk";

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
	}

	@AfterClass
	public static void tearDownAfterClass() {
		AdbInterface.stop();
	}

	@Test
	public void testStart() throws IOException {
		AndroidAutomationTools.start();
	}

	@Test
	public void testDisableAnimations() throws Exception {
		AndroidAutomationTools.start();
		AndroidAutomationTools.disableAnimations();
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

	@Test
	public void testIsPackageInstalled_InstalledReturnsTrue() throws Exception {
		final String EXPECTED_PACKAGE_NAME = "com.picarroapp";
		AndroidAutomationTools.restart();
		AdbInterface.installPackage(TEST_APK_LOCATION, true /*replaceExisting*/, true /*allowVersionDowngrade*/, true /*grantAllRuntimePermissions*/);
		boolean installed = AndroidAutomationTools.isPackageInstalled(EXPECTED_PACKAGE_NAME);
		Log.info(String.format("Package installed=[%b]", installed));
		assertTrue("APK should be installed.", installed);
	}

	@Test
	public void testIsPackageInstalled_UninstalledReturnsFalse() throws Exception {
		final String EXPECTED_PACKAGE_NAME = "com.picarroapp";
		AndroidAutomationTools.restart();
		AdbInterface.uninstallPackage(EXPECTED_PACKAGE_NAME);
		boolean installed = AndroidAutomationTools.isPackageInstalled(EXPECTED_PACKAGE_NAME);
		Log.info(String.format("Package installed=[%b]", installed));
		assertFalse("APK should be installed.", installed);
	}

	@Test
	public void testIsAppDrawOverlayDisplayed() throws Exception {
		AndroidAutomationTools.installLaunchAPK(TEST_APK_LOCATION, APP_DRAW_OVERLAY_SETTINGS_ACTIVITY);
		assertTrue(AndroidAutomationTools.isAppDrawOverlayDisplayed());
	}

	@Test
	public void testInstallLaunchAPK() throws IOException {
		AndroidAutomationTools.installLaunchAPK(TEST_APK_LOCATION, APP_DRAW_OVERLAY_SETTINGS_ACTIVITY);
	}
}