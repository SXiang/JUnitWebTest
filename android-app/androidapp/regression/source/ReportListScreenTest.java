package androidapp.regression.source;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import androidapp.dataprovider.ReportListDataProvider;
import androidapp.screens.source.AndroidInvestigateReportScreen;
import androidapp.screens.source.AndroidInvestigationScreen;
import common.source.Log;
import common.source.TestContext;
import common.source.Timeout;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import surveyor.scommon.generators.ReportDataGenerator;

public class ReportListScreenTest extends BaseAndroidTest {

	private static final Integer picInvAssignedUserDataRowID = 16;
	private static String generatedInvReportId;

	protected AndroidInvestigationScreen investigationScreen;
	protected AndroidInvestigateReportScreen investigateReportScreen;

	@Rule
	public TestName testName = new TestName();

	@Before
	public void beforeTest() throws Exception {
		createTestCaseData(testName);
		initializeAppiumTest();
		initializeTestScreenObjects();
	}

	private void createTestCaseData(TestName testName) throws Exception {
		String methodName = testName.getMethodName();
		if (methodName.startsWith("TC2429_")) {
			Object[][] tc2429 = ReportListDataProvider.dataProviderReportList_TC2429();
			Integer userDataRowID = (Integer)tc2429[0][1];
			Integer reportDataRowID1 = (Integer)tc2429[0][2];
			generatedInvReportId = ReportDataGenerator.newSingleUseGenerator().createReportAndAssignLisasToUser("TC2429",
					userDataRowID, picInvAssignedUserDataRowID, reportDataRowID1);
		}
	}

	private void initializeTestScreenObjects() {
		initializeInvestigationScreen();
		initializeInvestigateReportScreen();
	}

	private void initializeInvestigateReportScreen() {
		investigateReportScreen = new AndroidInvestigateReportScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigateReportScreen);
	}

	private void initializeInvestigationScreen() {
		investigationScreen = new AndroidInvestigationScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigationScreen);
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

		final Integer EXPECTED_INVESTIGATION_MARKERS = 9;
		String backpackAddress = TestContext.INSTANCE.getTestSetup().getBackPackServerIpAddress();
		String picServerAddress = TestContext.INSTANCE.getTestSetup().getBaseUrl();
		String username = TestContext.INSTANCE.getTestSetup().getLoginUser();
		String password = TestContext.INSTANCE.getTestSetup().getLoginPwd();
		String reportIdSuffix = generatedInvReportId.substring(0, 6);

		settingsScreen.saveSettings(backpackAddress, picServerAddress, username);
		mapScreen.waitForScreenLoad();
		Log.info("Map screen loaded successfully!");

		mapScreen.clickOnInvestigate();
		initializeMapScreen();

		mapScreen.waitForLoginDialogToShow();
		mapScreen.enterPassword(password);
		mapScreen.clickOnSubmit();

		investigationScreen.waitForScreenLoad();
		investigationScreen.performSearch(reportIdSuffix);

		investigationScreen.clickOnFirstInvestigation();
		investigateReportScreen.waitForScreenLoad();
		Integer count = investigateReportScreen.getInvestigationMarkersCount();
		Log.info(String.format("Found %d investigation markers", count));
		assertTrue(count == EXPECTED_INVESTIGATION_MARKERS);
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
	@Ignore
	@UseDataProvider(value = ReportListDataProvider.REPORT_LIST_DATA_PROVIDER_TC2430, location = ReportListDataProvider.class)
	public void TC2430_EnergyBackpackInvestigationItemScreenNoLISAsForInvestigation(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2430_EnergyBackpackInvestigationItemScreenNoLISAsForInvestigation ...");

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
	@Ignore
	@UseDataProvider(value = ReportListDataProvider.REPORT_LIST_DATA_PROVIDER_TC2431, location = ReportListDataProvider.class)
	public void TC2431_EnergyBackpackInvestigationItemScreenNoGapsForInvestigation(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2431_EnergyBackpackInvestigationItemScreenNoGapsForInvestigation ...");

	}
}