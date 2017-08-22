package unittest.source;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.support.PageFactory;

import androidapp.regression.source.AndroidLeakScreenTestBase;
import androidapp.screens.source.AndroidAddLeakSourceFormDialog;
import androidapp.screens.source.AndroidAddOtherSourceFormDialog;
import androidapp.screens.source.AndroidAddSourceDialog;
import androidapp.screens.source.AndroidAddedSourceListDialog;
import androidapp.screens.source.AndroidConfirmationDialog;
import androidapp.screens.source.AndroidInvestigateMapScreen;
import androidapp.screens.source.AndroidInvestigateReportScreen;
import androidapp.screens.source.AndroidInvestigationScreen;
import androidapp.screens.source.AndroidMarkerTypeListControl;
import common.source.BackPackAnalyzer;
import common.source.Log;
import common.source.Timeout;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.source.SurveyorConstants;

public class AndroidScreenObjectTest extends AndroidLeakScreenTestBase {

	private static ThreadLocal<Boolean> appiumTestInitialized = new ThreadLocal<Boolean>();
	private static String notInvestigated;
	private static String inProgress;

	protected AndroidInvestigationScreen investigationScreen;
	protected AndroidInvestigateReportScreen investigateReportScreen;
	protected AndroidMarkerTypeListControl markerTypeDialog;
	protected AndroidInvestigateMapScreen investigateMapScreen;
	protected AndroidAddSourceDialog addSourceDialog;
	protected AndroidConfirmationDialog confirmationDialog;
	protected AndroidAddLeakSourceFormDialog addLeakSourceFormDialog;
	protected AndroidAddedSourceListDialog addedSourcesListDialog;
	protected AndroidAddOtherSourceFormDialog addOtherSourceFormDialog;

	@Rule
	public TestName testName = new TestName();

	@BeforeClass
	public static void beforeClass() throws IOException {
		initializeTestObjects(false);
		BackPackAnalyzer.restartSimulator();
	}

	@AfterClass
	public static void afterClass() throws IOException {
		BackPackAnalyzer.stopSimulator();
	}

	@Before
	public void beforeTest() throws Exception {
		notInvestigated = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Not_Investigated);
		inProgress = Resources.getResource(ResourceKeys.InvestigationStatusTypes_In_Progress);
		initializeTestDriver();
		initializeTestScreenObjects();

		startTestRecording(testName.getMethodName());
	}

	@After
	public void afterTest() throws Exception {
		stopTestRecording(testName.getMethodName());
	}

	@Test
	public void scrollOnAndroidAddLeakFormTest() throws Exception {
		Log.info("Excecuting test -> scrollOnAndroidAddLeakFormTest() ...");

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
		executeWithBackPackDataProcessesPaused(obj -> {
			navigateToInvestigationReportScreen(investigationScreen, SurveyorConstants.USERPASSWORD);
			return true;
		});

		investigationScreen.tapOnFirstInvestigation();

		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			return true;
		});

		String[] markerStatuses = {inProgress, notInvestigated};
		investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatuses));

		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();
			investigateMapScreen.clickOnAddSource();
			addSourceDialog.waitForScreenLoad();
			addSourceDialog.clickOnAddLeak();
			addLeakSourceFormDialog.waitForScreenLoad();
			addLeakSourceFormDialog.scrollToNextPage();
			assertTrue("Scroll did NOT work correctly..", addLeakSourceFormDialog.getOKButton().isDisplayed());
			return true;
		});
	}

	@Ignore    // verify and enable test after appium client issue resolved. see comments on MobileAction.zoomIn()
	public void zoomInOnMapTest() throws Exception {
		Log.info("Excecuting test -> zoomInOnMapTest() ...");

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
		executeWithBackPackDataProcessesPaused(obj -> {
			navigateToInvestigationReportScreen(investigationScreen, SurveyorConstants.USERPASSWORD);
			return true;
		});

		investigationScreen.tapOnFirstInvestigation();

		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			return true;
		});

		String[] markerStatuses = {inProgress, notInvestigated};
		investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatuses));

		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			//investigateMapScreen.applyMapZoomIn(600);
			return true;
		});
	}

	@Ignore    // verify and enable test after appium client issue resolved. see comments on MobileAction.zoomOut()
	public void zoomOutOnMapTest() throws Exception {
		Log.info("Excecuting test -> zoomOutOnMapTest() ...");

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
		executeWithBackPackDataProcessesPaused(obj -> {
			navigateToInvestigationReportScreen(investigationScreen, SurveyorConstants.USERPASSWORD);
			return true;
		});

		investigationScreen.tapOnFirstInvestigation();

		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			return true;
		});

		String[] markerStatuses = {inProgress, notInvestigated};
		investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatuses));

		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			//investigateMapScreen.applyMapZoomOut(200);
			return true;
		});
	}

	private void initializeTestScreenObjects() {
		initializeInvestigationScreen();
		initializeInvestigateReportScreen();
		initializeInvestigateMapScreen();
		initializeAddLeakSourceFormDialog();
		initializeAddSourceDialog();
	}

	protected void initializeInvestigateReportScreen() {
		investigateReportScreen = new AndroidInvestigateReportScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigateReportScreen);
	}

	protected void initializeInvestigationScreen() {
		investigationScreen = new AndroidInvestigationScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigationScreen);
	}

	protected void initializeInvestigateMapScreen() {
		investigateMapScreen = new AndroidInvestigateMapScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigateMapScreen);
	}

	protected void initializeAddLeakSourceFormDialog() {
		addLeakSourceFormDialog = new AndroidAddLeakSourceFormDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), addLeakSourceFormDialog);
	}

	protected void initializeAddSourceDialog() {
		addSourceDialog = new AndroidAddSourceDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), addSourceDialog);
	}

	private void installApkStartAppiumDriver() throws MalformedURLException, IOException {
		initializeAppiumDriver();
		installLaunchApp(AndroidActivities.MAIN_ACTIVITY);
	}

	private void initializeTestDriver() throws MalformedURLException, IOException, Exception {
		boolean isAppiumTestInitialized = appiumTestInitialized.get() != null && appiumTestInitialized.get();
		if (!isAppiumTestInitialized) {
			initializeAppiumTest();
			appiumTestInitialized.set(true);
		} else {
			installApkStartAppiumDriver();
		}
	}
}