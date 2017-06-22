package androidapp.regression.source;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import androidapp.dataprovider.ReportListDataProvider;
import common.source.Log;
import common.source.TestContext;
import surveyor.scommon.generators.ReportDataGenerator;

public class ReportListScreenTest extends BaseAndroidTest {

	private static final Integer picUserDataRowID = 6;
	private static final Integer picInvAssignedUserDataRowID = 16;
	private static final Integer invReportDataRowID = 134;
	private static String generatedInvReportId;

	@BeforeClass
	public static void beforeTestClass() throws Exception {
//		generatedInvReportId = ReportDataGenerator.newSingleUseGenerator().createReportAndAssignLisasToUser("ReportListScreenTest_TCs",
//				picUserDataRowID, picInvAssignedUserDataRowID, invReportDataRowID);
	}

	@Before
	public void beforeTest() throws Exception {
		initializeAppiumTest();
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

		String backpackAddress = TestContext.INSTANCE.getTestSetup().getBackPackServerIpAddress();
		String picServerAddress = TestContext.INSTANCE.getTestSetup().getBaseUrl();
		String username = TestContext.INSTANCE.getTestSetup().getLoginUser();

		settingsScreen.saveSettings(backpackAddress, picServerAddress, username);
		assertTrue("Map screen loaded successfully!", mapScreen.waitForScreenLoad());
	}
}
