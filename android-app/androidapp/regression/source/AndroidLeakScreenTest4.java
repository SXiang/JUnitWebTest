package androidapp.regression.source;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.support.PageFactory;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import androidapp.dataprovider.LeakScreenDataProvider;
import androidapp.screens.source.AndroidAddLeakSourceFormDialog;
import androidapp.screens.source.AndroidAddOtherSourceFormDialog;
import androidapp.screens.source.AndroidAddSourceDialog;
import androidapp.screens.source.AndroidAddedSourceListDialog;
import androidapp.screens.source.AndroidInvestigateMapScreen;
import androidapp.screens.source.AndroidInvestigateReportScreen;
import androidapp.screens.source.AndroidInvestigationScreen;
import androidapp.screens.source.AndroidMarkerTypeListControl;
import common.source.ArrayUtility;
import common.source.BackPackAnalyzer;
import common.source.Log;
import common.source.TestContext;
import common.source.Timeout;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import surveyor.dataaccess.source.Report;
import surveyor.scommon.mobile.source.ReportDataGenerator;
import surveyor.scommon.source.SurveyorConstants;

public class AndroidLeakScreenTest4 extends AndroidLeakScreenTestBase {
	private static final Integer defaultAssignedUserDataRowID = 16;
	private static final Integer defaultUserDataRowID = 6;
	private static final Integer defaultReportDataRowID = 6;
	private static String generatedInvReportTitle;

	protected AndroidInvestigationScreen investigationScreen;
	protected AndroidInvestigateReportScreen investigateReportScreen;
	protected AndroidMarkerTypeListControl markerTypeDialog;

	protected AndroidInvestigateMapScreen investigateMapScreen;
	protected AndroidAddSourceDialog addSourceDialog;

	protected AndroidAddLeakSourceFormDialog addLeakSourceFormDialog;
	protected AndroidAddedSourceListDialog addedSourcesListDialog;

	protected AndroidAddOtherSourceFormDialog addOtherSourceFormDialog;

	private static ThreadLocal<Boolean> appiumTestInitialized = new ThreadLocal<Boolean>();

	@Rule
	public TestName testName = new TestName();

	@Before
	public void beforeTest() throws Exception {
		createTestCaseData(testName);
		initializeTestDriver();
		initializeTestScreenObjects();
		if (!TestContext.INSTANCE.getTestSetup().isRunningOnBackPackAnalyzer()) {
			BackPackAnalyzer.restartSimulator();
		}

		startTestRecording(testName.getMethodName());
	}

	@After
	public void afterTest() throws Exception {
		stopTestRecording(testName.getMethodName());
		if (!TestContext.INSTANCE.getTestSetup().isRunningOnBackPackAnalyzer()) {
			BackPackAnalyzer.stopSimulator();
		}
	}

	/**
	 * Test Case ID: TC2432_EnergyBackpack_InvestigateLISA
	 * Test Description: Energy Backpack - Investigate LISA
	 * Script: -
	 *	- - Log into the tablet and launch the Backpack app
	 *	- - Click on the Investigate button at the bottom right of the screen
	 *	- - Log in with PCubed credentials, then click on a report
	 *	- - Click on one of the LISAs
	 *	- - Click on the Follow button
	 *	- - Click the Investigate button at bottom left
	 *	- - Click the Add Source button
	 *	- - Click the Add Leak button
	 *	- - Fill out each of the fields and click OK
	 *	- - Click Mark As Complete
	 * Results: -
	 *	- - User is navigated to the map
	 *	- - User will see a list of Compliance Reports
	 *	- - User will see a list of LISAs for investigation
	 *	- -  User will see a map centered on the backpack's location. Pipe data is  displayed on the map Follow and Directions buttons are present on  the right and Investigate button is present at the bottom left
	 *	- - Map is now centered on selected LISA. Pipe data is displayed on the map
	 *	- -  Add Source and Add CGI buttons are added on the right.  Investigate button disappears and is replaced by Mark As Complete  and Pause buttons
	 *	- - Dialog appears with Add Leak and Add Other Source buttons
	 *	- - A form appears where user can log details of the leak
	 *	- - The previous dialog appears with a summary of the leak details that were just entered
	 *	- - User is navigated back to the list of LISAs
	 */
	@Ignore
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2432, location = LeakScreenDataProvider.class)
	public void TC2432_EnergyBackpack_InvestigateLISA(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2432_EnergyBackpack_InvestigateLISA ...");

	}

	/**
	 * Test Case ID: TC2433_EnergyBackpack_InvestigateGap
	 * Test Description: Energy Backpack - Investigate Gap
	 * Script: -
	 *	- - Log into the tablet
	 *	- - Click on the Investigate button at the bottom of the screen
	 *	- - Log in with PCubed credentials
	 *	- - Click on a report
	 *	- - Click on the Indications button near the top right. Select Gaps from the pop up menu
	 *	- - Click on one of the Gaps
	 *	- - Click on the Follow button
	 *	- - Click the Investigate button at bottom left
	 *	- - Click the Add Source button
	 *	- - Click the Add Leak button
	 *	- - Fill out each of the fields and click OK
	 *	- - Click Mark As Complete
	 * Results: -
	 *	- - User is navigated to the map
	 *	- - User will see a list of Compliance Reports
	 *	- - User will see a list of LISAs for investigation
	 *	- - User will see a list of Gaps for investigation
	 *	- - User will see a map centered on the backpack's location. Follow and Directions buttons are present on the right and Investigate button is present at the bottom left
	 *	- - Map is now centered on selected LISA
	 *	- - Add Source and Add CGI buttons are added on the right. Investigate button disappears and is replaced by Mark As Complete and Pause buttons
	 *	- - Dialog appears with Add Leak and Add Other Source buttons
	 *	- - A form appears where user can log details of the leak
	 *	- - The previous dialog appears with a summary of the leak details that were just entered
	 *	- - User is navigated back to the list of Gaps
	 */
	@Ignore
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2433, location = LeakScreenDataProvider.class)
	public void TC2433_EnergyBackpack_InvestigateGap(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2433_EnergyBackpack_InvestigateGap ...");

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
	}

	/**
	 * Test Case ID: TC2639_EnergyBackpack_InvestigateLISAAssetBox
	 * Test Description: Energy Backpack - Investigate LISA Asset Box
	 * Script: -
	 *	- - Log into the tablet and launch the Backpack app
	 *	- - Click on the Investigate button at the bottom right of the screen
	 *	- - Log in with PCubed credentials, then click on a report
	 *	- - Click on one of the LISAs
	 *	- - Click on the Follow button
	 *	- - Click the Investigate button at bottom left
	 *	- - Click the Add Source button
	 *	- - Click the Add Leak button
	 *	- - Fill out each of the fields and click OK
	 *	- - Click Mark As Complete
	 * Results: -
	 *	- - User is navigated to the map
	 *	- - User will see a list of Compliance Reports
	 *	- - User will see a list of LISAs for investigation
	 *	- - User will see a map centered on the backpack's location. Follow and Directions buttons are present on the right and Investigate button is present at the bottom left
	 *	- - Map is now centered on selected LISA Asset Box. Box is outlined in red and pipe is highlighted in green
	 *	- - Add Source and Add CGI buttons are added on the right. Investigate button disappears and is replaced by Mark As Complete and Pause buttons
	 *	- - Dialog appears with Add Leak and Add Other Source buttons
	 *	- - A form appears where user can log details of the leak
	 *	- - The previous dialog appears with a summary of the leak details that were just entered
	 *	- - User is navigated back to the list of LISAs
	 */
	@Ignore
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2639, location = LeakScreenDataProvider.class)
	public void TC2639_EnergyBackpack_InvestigateLISAAssetBox(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2639_EnergyBackpack_InvestigateLISAAssetBox ...");

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
	}

	/**
	 * Test Case ID: TC2640_EnergyBackpack_InvestigateGapBox
	 * Test Description: Energy Backpack - Investigate Gap Box
	 * Script: -
	 *	- - Log into the tablet and launch the Backpack app
	 *	- - Click on the Investigate button at the bottom right of the screen
	 *	- - Log in with PCubed credentials, then click on a report
	 *	- - From the dropdown, select Gaps
	 *	- - Click on one of the Gaps
	 *	- - Click on the Follow button
	 *	- - Click the Investigate button at bottom left
	 *	- - Click the Add Source button
	 *	- - Click the Add Leak button
	 *	- - Fill out each of the fields and click OK
	 *	- - Click Mark As Complete
	 * Results: -
	 *	- - User is navigated to the map
	 *	- - User will see a list of Compliance Reports
	 *	- - User will see a list of LISAs for investigation
	 *	- - User will see a list of Gaps for investigation
	 *	- - User will see a map centered on the backpack's location. Follow and Directions buttons are present on the right and Investigate button is present at the bottom left
	 *	- - Map is now centered on selected Gap Box. Box is outlined in red and pipe is highlighted in green
	 *	- - Add Source and Add CGI buttons are added on the right. Investigate button disappears and is replaced by Mark As Complete and Pause buttons
	 *	- - Dialog appears with Add Leak and Add Other Source buttons
	 *	- - A form appears where user can log details of the leak
	 *	- - The previous dialog appears with a summary of the leak details that were just entered
	 *	- - User is navigated back to the list of Gaps
	 */
	@Ignore
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2640, location = LeakScreenDataProvider.class)
	public void TC2640_EnergyBackpack_InvestigateGapBox(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2640_EnergyBackpack_InvestigateGapBox ...");

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
	}

	private void createTestCaseData(TestName testName) throws Exception {
		String methodName = testName.getMethodName();
		Integer userDataRowID = defaultUserDataRowID;
		Integer reportDataRowID1 = defaultReportDataRowID;
		String tcId = "";
		String[] lisaNumbers = {"1", "2", "3", "4", "5", "6"};
		String[] gapNumbers = {"1", "2", "3"};
		String[] tcsWithReportsThatHaveLisas = {"TC2434", "TC2436", "TC2438", "TC2440", "TC2432", "TC2639"};
		ArrayUtility.shuffle(tcsWithReportsThatHaveLisas);     // add randomness to input data.
		String[] tcsWithReportsThatHaveGaps = {"TC2430", "TC2435", "TC2437", "TC2433", "TC2640"};
		ArrayUtility.shuffle(tcsWithReportsThatHaveGaps);      // add randomness to input data.
		Report matchingReport = null;
		if (methodName.startsWith("TC2432") || methodName.startsWith("TC2639")) {
			matchingReport = invReportDataVerifier.findReportOfMatchingPrefixWithCompleteOrInProgressLisaMarker(tcsWithReportsThatHaveLisas, SurveyorConstants.SQAPICDR);
		} else if (methodName.startsWith("TC2433") || methodName.startsWith("TC2640")) {
			matchingReport = invReportDataVerifier.findReportOfMatchingPrefixWithCompleteOrInProgressGapMarker(tcsWithReportsThatHaveGaps, SurveyorConstants.SQAPICDR);
		}

		if (matchingReport != null) {
			generatedInvReportTitle = matchingReport.getReportTitle();
			return;
		}

		if (methodName.startsWith("TC2432")) {
			Object[][] tc2432 = LeakScreenDataProvider.dataProviderAndroidApp_TC2432();
			userDataRowID = (Integer)tc2432[0][1];
			reportDataRowID1 = (Integer)tc2432[0][2];
			tcId = "TC2432";
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(true /*isReusable*/).createReportAndAssignLisasToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers).getReportTitle();
		} else if (methodName.startsWith("TC2433")) {
			Object[][] tc2433 = LeakScreenDataProvider.dataProviderAndroidApp_TC2433();
			userDataRowID = (Integer)tc2433[0][1];
			reportDataRowID1 = (Integer)tc2433[0][2];
			tcId = "TC2433";
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(true /*isReusable*/).createReportAndAssignLisasAndGapsToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers, gapNumbers).getReportTitle();
		} else if (methodName.startsWith("TC2639")) {
			Object[][] tc2639 = LeakScreenDataProvider.dataProviderAndroidApp_TC2639();
			userDataRowID = (Integer)tc2639[0][1];
			reportDataRowID1 = (Integer)tc2639[0][2];
			tcId = "TC2639";
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(true /*isReusable*/).createReportAndAssignLisasToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers).getReportTitle();
		} else if (methodName.startsWith("TC2640")) {
			Object[][] tc2640 = LeakScreenDataProvider.dataProviderAndroidApp_TC2640();
			userDataRowID = (Integer)tc2640[0][1];
			reportDataRowID1 = (Integer)tc2640[0][2];
			tcId = "TC2640";
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(true /*isReusable*/).createReportAndAssignLisasAndGapsToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers, gapNumbers).getReportTitle();
		}
	}
	private void initializeTestScreenObjects() {
		initializeInvestigationScreen();
		initializeInvestigateReportScreen();
		initializeInvestigateMapScreen();
		initializeAddSourceDialog();
		initializeAddLeakSourceFormDialog();
		initializeAndroidAddedLeakListDialog();
		initializeMarkerTypeDialog();
		initializeAddOtherSourceFormDialog();
	}

	@Override
	protected void initializeInvestigateReportScreen() {
		investigateReportScreen = new AndroidInvestigateReportScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigateReportScreen);
	}

	@Override
	protected void initializeInvestigationScreen() {
		investigationScreen = new AndroidInvestigationScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigationScreen);
	}

	@Override
	protected void initializeInvestigateMapScreen() {
		investigateMapScreen = new AndroidInvestigateMapScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigateMapScreen);
	}

	@Override
	protected void initializeAddSourceDialog() {
		addSourceDialog = new AndroidAddSourceDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), addSourceDialog);
	}

	@Override
	protected void initializeMarkerTypeDialog() {
		markerTypeDialog = new AndroidMarkerTypeListControl(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), markerTypeDialog);
	}

	@Override
	protected void initializeAddLeakSourceFormDialog() {
		addLeakSourceFormDialog = new AndroidAddLeakSourceFormDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), addLeakSourceFormDialog);
	}

	@Override
	protected void initializeAndroidAddedLeakListDialog() {
		addedSourcesListDialog = new AndroidAddedSourceListDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), addedSourcesListDialog);
	}

	@Override
	protected void initializeAddOtherSourceFormDialog() {
		addOtherSourceFormDialog = new AndroidAddOtherSourceFormDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), addOtherSourceFormDialog);
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