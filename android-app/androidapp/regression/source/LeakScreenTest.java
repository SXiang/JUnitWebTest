package androidapp.regression.source;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import androidapp.dataprovider.ReportListDataProvider;
import androidapp.screens.source.AndroidAddSourceDialog;
import androidapp.screens.source.AndroidInvestigateMapScreen;
import androidapp.screens.source.AndroidInvestigateReportScreen;
import androidapp.screens.source.AndroidInvestigationScreen;
import androidapp.screens.source.AndroidMarkerTypeListControl;
import common.source.Log;
import common.source.Timeout;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import surveyor.scommon.mobile.source.ReportDataGenerator;

public class LeakScreenTest extends BaseReportTest {

	private static final Integer defaultAssignedUserDataRowID = 16;
	private static final Integer defaultUserDataRowID = 6;
	private static final Integer defaultReportDataRowID = 6;
	private static String generatedInvReportId;

	protected AndroidInvestigationScreen investigationScreen;
	protected AndroidInvestigateReportScreen investigateReportScreen;
	protected AndroidMarkerTypeListControl markerTypeDialog;

	protected AndroidInvestigateMapScreen investigateMapScreen;
	protected AndroidAddSourceDialog addSourceDialog;

	//protected AndroidSettingsScreen settingsScreen;
	//protected AndroidAlarmSettingsScreen alarmSettingsScreen;

	private static ThreadLocal<Boolean> appiumTestInitialized = new ThreadLocal<Boolean>();

	@Before
	public void beforeTest() throws Exception {
		initializeTestDriver();
		initializeTestScreenObjects();
	}

	/**
	 *	Test Case: TC2434EnergyBackpackLoggingMultipleLeaksWithinClassicLISAs
	 *	Pre-Conditions:	Compliance Reports with LISAs assigned to user
	 *	Script:
	 *	- Log into Backpack tablet
	 *	- Press Investigation button
	 *	- Select a report
	 *	- Select a LISA
	 *	- Click Investigate button
	 *	- Click on Add Source button
	 *	- Click on Add Leak button
	 *	- Fill out all fields including GPS coordinates and click OK
	 *	- Repeat last two steps
	 *	Expected Result:
	 *	- User will see satellite map view
	 *	- User will see a list of Compliance Reports assigned to him/her
	 *	- User will see a list of LISAs from that report assigned to him/her
	 *	- User is navigated to map showing user's position
	 *	- Investigation Status at top of screen is set to In-Progress
	 *	- A summary popup will appear that lists sources already added (if any) and has two buttons, Add Leak and Add Other Source
	 *	- A popup appears where user can enter details for that leak
	 *	- User is navigated to previous page and details of newly-added leak appear
	 *	- All leaks entered should appear with correct data
	**/
	@Test
	@UseDataProvider(value = ReportListDataProvider.REPORT_LIST_DATA_PROVIDER_TC2434, location = ReportListDataProvider.class)
	public void TC2434EnergyBackpackLoggingMultipleLeaksWithinClassicLISAs(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2434EnergyBackpackLoggingMultipleLeaksWithinClassicLISAs ...");

		navigateToMapScreenUsingDefaultCreds(false /*waitForMapScreenLoad*/);
		executeWithBackPackDataProcessesPaused(obj -> {
			navigateToInvestigationReportScreenWithDefaultCreds(investigationScreen);
			searchForReportId(investigationScreen, generatedInvReportId.substring(0, 6));
			initializeInvestigationScreen();
			return true;
		});

		clickOnFirstInvestigationReport(investigationScreen);

		executeWithBackPackDataProcessesPaused(obj -> {
			investigateReportScreen.clickOnFirstInvestigationMarker();
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnAddSource();
			addSourceDialog.waitForScreenLoad();
			addSourceDialog.clickOnAddLeak();
			//assertTrue(verifyExpectedMarkersShownOnInvestigationScreen(investigateReportScreen, false /*refetchListItems*/, EXPECTED_LISA_MARKERS));
			return true;
		});
	}

	/**
	 *	Test Case: TC2435EnergyBackpackLoggingMultipleLeaksWithinGaps
	 *	Pre-Conditions:	Compliance Reports with Gaps assigned to user
	 *	Script:
	 *	- Log into Backpack tablet
	 *	- Press Investigation button
	 *	- Select a report
	 *	- Press arrow near top right of screen
	 *	- Select Gaps in popup
	 *	- Select a Gap
	 *	- Click Investigate button
	 *	- Click on Add Source button
	 *	- Click on Add Leak button
	 *	- Fill out all fields including GPS coordinates and click OK
	 *	- Repeat last two steps
	 *	Expected Result:
	 *	- User will see satellite map view
	 *	- User will see a list of Compliance Reports assigned to him/her
	 *	- User will see a list of LISAs from that report assigned to him/her
	 *	- Popup appears where user can select LISAs or Gaps
	 *	- User will see a list of Gaps from that report assigned to him/her
	 *	- User is navigated to map showing user's position
	 *	- Investigation Status at top of screen is set to In-Progress
	 *	- A summary popup will appear that lists sources already added (if any) and has two buttons, Add Leak and Add Other Source
	 *	- A popup appears where user can enter details for that leak
	 *	- User is navigated to previous page and details of newly-added leak appear
	 *	- All leaks entered should appear with correct data
	**/
	@Test
	@UseDataProvider(value = ReportListDataProvider.REPORT_LIST_DATA_PROVIDER_TC2435, location = ReportListDataProvider.class)
	public void TC2435EnergyBackpackLoggingMultipleLeaksWithinGaps(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2435EnergyBackpackLoggingMultipleLeaksWithinGaps ...");

	}

	/**
	 *	Test Case: TC2436EnergyBackpackLoggingMultipleOtherSourceLeaksWithinClassicLISAs
	 *	Pre-Conditions:	Compliance Reports with LISAs assigned to user
	 *	Script:
	 *	- Log into Backpack tablet
	 *	- Press Investigation button
	 *	- Select a report
	 *	- Select a LISA
	 *	- Click Investigate button
	 *	- Click on Add Source button
	 *	- Click on Add Other Source button
	 *	- Fill out all fields including GPS coordinates and click OK
	 *	- Repeat last two steps
	 *	Expected Result:
	 *	- User will see satellite map view
	 *	- User will see a list of Compliance Reports assigned to him/her
	 *	- User will see a list of LISAs from that report assigned to him/her
	 *	- User is navigated to map showing user's position
	 *	- Investigation Status at top of screen is set to In-Progress
	 *	- A summary popup will appear that lists sources already added (if any) and has two buttons, Add Leak and Add Other Source
	 *	- A popup appears where user can enter details for that leak
	 *	- User is navigated to previous page and details of newly-added leak appear
	 *	- All leaks entered should appear with correct data
	**/
	@Test
	@UseDataProvider(value = ReportListDataProvider.REPORT_LIST_DATA_PROVIDER_TC2436, location = ReportListDataProvider.class)
	public void TC2436EnergyBackpackLoggingMultipleOtherSourceLeaksWithinClassicLISAs(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2436EnergyBackpackLoggingMultipleOtherSourceLeaksWithinClassicLISAs ...");

	}

	private void createTestCaseData(TestName testName) throws Exception {
		String methodName = testName.getMethodName();
		Integer userDataRowID = defaultUserDataRowID;
		Integer reportDataRowID1 = defaultReportDataRowID;
		String tcId = "";
		if (methodName.startsWith("TC2434_")) {
			Object[][] tc2434 = ReportListDataProvider.dataProviderReportList_TC2434();
			userDataRowID = (Integer)tc2434[0][1];
			reportDataRowID1 = (Integer)tc2434[0][2];
			tcId = "TC2434";
			generatedInvReportId = ReportDataGenerator.newSingleUseGenerator().createReportAndAssignLisasToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1);
		} else if (methodName.startsWith("TC2435_")) {
			Object[][] tc2435 = ReportListDataProvider.dataProviderReportList_TC2435();
			userDataRowID = (Integer)tc2435[0][1];
			reportDataRowID1 = (Integer)tc2435[0][2];
			tcId = "TC2435";
			generatedInvReportId = ReportDataGenerator.newSingleUseGenerator().createReportAndAssignGapsToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1);
		} else if (methodName.startsWith("TC2436_")) {
			Object[][] tc2436 = ReportListDataProvider.dataProviderReportList_TC2436();
			userDataRowID = (Integer)tc2436[0][1];
			reportDataRowID1 = (Integer)tc2436[0][2];
			tcId = "TC2436";
			generatedInvReportId = ReportDataGenerator.newSingleUseGenerator().createReportAndAssignLisasToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1);
		}
	}

	private void initializeTestScreenObjects() {
		initializeInvestigationScreen();
		initializeInvestigateReportScreen();
		initializeInvestigateMapScreen();
		initializeAddSourceDialog();
	}

	private void initializeInvestigateReportScreen() {
		investigateReportScreen = new AndroidInvestigateReportScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigateReportScreen);
	}

	private void initializeInvestigationScreen() {
		investigationScreen = new AndroidInvestigationScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigationScreen);
	}

	protected void initializeInvestigateMapScreen() {
		investigateMapScreen = new AndroidInvestigateMapScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigateMapScreen);
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