package androidapp.regression.source;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import androidapp.dataprovider.ReportListDataProvider;
import androidapp.screens.source.AndroidInvestigateReportScreen;
import androidapp.screens.source.AndroidInvestigationScreen;
import androidapp.screens.source.AndroidMarkerTypeListControl;
import androidapp.screens.source.AndroidMarkerTypeListControl.MarkerType;
import common.source.Log;
import common.source.Timeout;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import surveyor.scommon.mobile.source.ReportDataGenerator;

public class ReportListScreenTest extends BaseReportTest {

	private static final Integer defaultAssignedUserDataRowID = 16;
	private static final Integer defaultUserDataRowID = 6;
	private static final Integer defaultReportDataRowID = 6;
	private static String generatedInvReportTitle;

	protected AndroidInvestigationScreen investigationScreen;
	protected AndroidInvestigateReportScreen investigateReportScreen;
	protected AndroidMarkerTypeListControl markerTypeDialog;

	private static ThreadLocal<Boolean> appiumTestInitialized = new ThreadLocal<Boolean>();

	@Rule
	public TestName testName = new TestName();

	@Before
	public void beforeTest() throws Exception {
		createTestCaseData(testName);
		initializeTestDriver();
		initializeTestScreenObjects();
	}

	/**
	 *	Test Case: TC2429_EnergyBackpackReportListScreen
	 *	Description:
	 *	- Display the list of compliance reports assigned to the current user.
	 *	- Touching a report in the list should navigate to the list of investigation items in that report
	 *	Pre-Conditions:
	 *	- Compliance Reports with both LISAs and Gaps to be investigated
	 *	Validation:
	 *	- Log into the tablet
	 *	- Click on the Investigate button at the bottom of the screen
	 *	- Log in with PCubed credentials
	 *	- Click on a report
	 *	- Click on the Indications button near the top right. Select Gaps from the pop up menu
	 *	Expected Result:
	 *	- User is navigated to the map
	 *	- User will see a list of Compliance Reports
	 *	- User will see a list of LISAs for investigation, if any
	 *	- User will see a list of Gaps for investigation, if any
	**/
	@Test
	@UseDataProvider(value = ReportListDataProvider.REPORT_LIST_DATA_PROVIDER_TC2429, location = ReportListDataProvider.class)
	public void TC2429_EnergyBackpackReportListScreen(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2429_EnergyBackpackReportListScreen ...");

		final Integer EXPECTED_LISA_MARKERS = 9;
		navigateToMapScreenUsingDefaultCreds(false /*waitForMapScreenLoad*/);
		executeWithBackPackDataProcessesPaused(obj -> {
			navigateToInvestigationReportScreenWithDefaultCreds(investigationScreen);
			searchForReportId(investigationScreen, generatedInvReportTitle.substring(0, 6));
			initializeInvestigationScreen();
			return true;
		});

		clickOnFirstInvestigationReport(investigationScreen);

		executeWithBackPackDataProcessesPaused(obj -> {
			assertTrue(verifyExpectedMarkersShownOnInvestigationScreen(investigateReportScreen, false /*refetchListItems*/, EXPECTED_LISA_MARKERS));
			return true;
		});
	}

	/**
	 *	Test Case: TC2430_EnergyBackpackInvestigationItemScreenNoLISAsForInvestigation
	 *	Pre-Conditions:
	 *	- Compliance Report that has Gaps for investigation but no LISAs
	 *	Script:
	 *	- Log into the tablet
	 *	- Click on the Investigate button at the bottom of the screen
	 *	- Log in with PCubed credentials
	 *	- Click on a report
	 *	- Click on the Indications button near the top right. Select Gaps from the pop up menu
	 *	Expected Result:
	 *	- User is navigated to the map
	 *	- User will see a list of Compliance Reports
	 *	- User will see a message, "No investigation markers of selected type in this report"
	 *	- User will see a list of Gaps for investigation
	**/
	@Test
	@UseDataProvider(value = ReportListDataProvider.REPORT_LIST_DATA_PROVIDER_TC2430, location = ReportListDataProvider.class)
	public void TC2430_EnergyBackpackInvestigationItemScreenNoLISAsForInvestigation(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2430_EnergyBackpackInvestigationItemScreenNoLISAsForInvestigation ...");

		final Integer EXPECTED_GAP_MARKERS = 11;
		navigateToMapScreenUsingDefaultCreds(false /*waitForMapScreenLoad*/);
		executeWithBackPackDataProcessesPaused(obj -> {
			navigateToInvestigationReportScreenWithDefaultCreds(investigationScreen);
			searchForReportId(investigationScreen, generatedInvReportTitle.substring(0, 6));
			initializeInvestigationScreen();
			return true;
		});

		clickOnFirstInvestigationReport(investigationScreen);

		executeWithBackPackDataProcessesPaused(obj -> {
			investigateReportScreen.waitForScreenLoad();
			assertTrue("No investigation markers of type=LISA expected in this report", investigateReportScreen.verifyNoInvestigationMarkersFoundInReport());
			investigateReportScreen.clickOnInvestigationMarkerType();
			markerTypeDialog.selectMarkerType(MarkerType.Gap);
			initializeInvestigateReportScreen();
			assertTrue(verifyExpectedMarkersShownOnInvestigationScreen(investigateReportScreen, true /*refetchListItems (changed on Marker=GAP)*/, EXPECTED_GAP_MARKERS));
			return true;
		});
	}

	/**
	 *	Test Case: TC2431_EnergyBackpackInvestigationItemScreenNoGapsForInvestigation
	 *	Pre-Conditions:
	 *	- Compliance Report that has LISAs for investigation but no Gaps
	 *	Script:
	 *	- Log into the tablet
	 *	- Click on the Investigate button at the bottom of the screen
	 *	- Log in with PCubed credentials
	 *	- Click on a report
	 *	- Click on the Indications button near the top right. Select Gaps from the pop up menu
	 *	Expected Result:
	 *	- User is navigated to the map
	 *	- User will see a list of Compliance Reports
	 *	- User will see a list of LISAs for investigation
	 *	- User will see a message, "No investigation markers of selected type in this report"
	**/
	@Test
	@UseDataProvider(value = ReportListDataProvider.REPORT_LIST_DATA_PROVIDER_TC2431, location = ReportListDataProvider.class)
	public void TC2431_EnergyBackpackInvestigationItemScreenNoGapsForInvestigation(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2431_EnergyBackpackInvestigationItemScreenNoGapsForInvestigation ...");

		final Integer EXPECTED_LISA_MARKERS = 9;
		navigateToMapScreenUsingDefaultCreds(false /*waitForMapScreenLoad*/);
		executeWithBackPackDataProcessesPaused(obj -> {
			navigateToInvestigationReportScreenWithDefaultCreds(investigationScreen);
			searchForReportId(investigationScreen, generatedInvReportTitle.substring(0, 6));
			initializeInvestigationScreen();
			return true;
		});

		clickOnFirstInvestigationReport(investigationScreen);

		executeWithBackPackDataProcessesPaused(obj -> {
			investigateReportScreen.waitForScreenLoad();
			assertTrue(verifyExpectedMarkersShownOnInvestigationScreen(investigateReportScreen, false /*refetchListItems*/, EXPECTED_LISA_MARKERS));
			investigateReportScreen.clickOnInvestigationMarkerType();
			markerTypeDialog.selectMarkerType(MarkerType.Gap);
			initializeInvestigateReportScreen();
			assertTrue("No investigation markers of type=GAP expected in this report", investigateReportScreen.verifyNoInvestigationMarkersFoundInReport());
			return true;
		});
	}

	private void createTestCaseData(TestName testName) throws Exception {
		String methodName = testName.getMethodName();
		Integer userDataRowID = defaultUserDataRowID;
		Integer reportDataRowID1 = defaultReportDataRowID;
		String tcId = "";
		if (methodName.startsWith("TC2429_")) {
			Object[][] tc2429 = ReportListDataProvider.dataProviderReportList_TC2429();
			userDataRowID = (Integer)tc2429[0][1];
			reportDataRowID1 = (Integer)tc2429[0][2];
			tcId = "TC2429";
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator().createReportAndAssignLisasToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1).getReportTitle();
		} else if (methodName.startsWith("TC2430_")) {
			Object[][] tc2430 = ReportListDataProvider.dataProviderReportList_TC2430();
			userDataRowID = (Integer)tc2430[0][1];
			reportDataRowID1 = (Integer)tc2430[0][2];
			tcId = "TC2430";
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator().createReportAndAssignGapsToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1).getReportTitle();
		} else if (methodName.startsWith("TC2431_")) {
			Object[][] tc2431 = ReportListDataProvider.dataProviderReportList_TC2431();
			userDataRowID = (Integer)tc2431[0][1];
			reportDataRowID1 = (Integer)tc2431[0][2];
			tcId = "TC2431";
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator().createReportAndAssignLisasToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1).getReportTitle();
		}
	}

	private void initializeTestScreenObjects() {
		initializeInvestigationScreen();
		initializeInvestigateReportScreen();
		initializeMarkerTypeDialog();
	}

	private void initializeInvestigateReportScreen() {
		investigateReportScreen = new AndroidInvestigateReportScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigateReportScreen);
	}

	private void initializeInvestigationScreen() {
		investigationScreen = new AndroidInvestigationScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigationScreen);
	}

	private void initializeMarkerTypeDialog() {
		markerTypeDialog = new AndroidMarkerTypeListControl(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), markerTypeDialog);
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