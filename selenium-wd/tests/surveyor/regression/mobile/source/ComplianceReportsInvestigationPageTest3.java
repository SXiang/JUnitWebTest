/**
 *
 */
package surveyor.regression.mobile.source;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.Log;
import surveyor.dataprovider.InvestigationReportDataProvider;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.mobile.source.MobileInvestigatePage;
import surveyor.scommon.mobile.source.MobileInvestigationPage;
import surveyor.scommon.mobile.source.MobileLeakSourcePage;
import surveyor.scommon.mobile.source.MobileLoginPage;
import surveyor.scommon.mobile.source.MobileReportsPage;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.ReportInvestigationsPage;

/**
 *
 *
 */
@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsInvestigationPageTest3 extends BaseReportsPageActionTest {
	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static ReportInvestigationsPage reportInvestigationsPage;
	private static MobileLoginPage mobileLoginPage;
	private static MobileReportsPage mobileReportsPage;
	private static MobileInvestigationPage mobileInvestigationPage;
	private static MobileInvestigatePage mobileInvestigatePage;
	private static MobileLeakSourcePage mobileLeakSourcePage;

	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects();
	}

	@Before
	public void beforeTest() throws Exception {
		initializeTestObjects();
		initializePageActions();
		// Select run mode here.
		setPropertiesForTestRunMode();
		mobileLoginPage = new MobileLoginPage();
		reportInvestigationsPage = new ReportInvestigationsPage(getDriver(), getBaseURL(), getTestSetup());
	}

	private static void setPropertiesForTestRunMode() throws Exception {
		setTestRunMode(ReportTestRunMode.FullTestRun);

		if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
			complianceReportsPageAction.fillWorkingDataForReports(getUnitTestReportRowID());
		}
	}

	/**
	 * Initializes the page action objects.
	 * @throws Exception
	 */
	protected static void initializePageActions() throws Exception {
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
		complianceReportsPageAction = new ComplianceReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
		setReportsPage((ComplianceReportsPage)complianceReportsPageAction.getPageObject());
	}

	/**
	 * Test Case ID: TC1722_InProgressStateForInvestigationsInMobileApp
	 * Test Description: Mobile: "in progress" state for investigations in mobile app
	 * Script:
	 * - Log into mobile app
	 * - Select a report
	 * - Select a LISA marked as Not Investigated)
	 * - Click Investigate button
	 * - Use back button to return to previous page and click Refresh
	 * Results:
	 * - User will see list of reports containing LISAs assigned to user
	 * - User will see LISAs for that report. If user has supervisory role, all LISAs for that report will be displayed. Otherwise, only LISAs assigned to user will be displayed
	 * - User will be navigated to map view showing user's position on map
	 * - At top of screen, next to LISA ID, "In Progress" will be displayed.
	 * - LISA will be marked "In Progress"
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1722, location = InvestigationReportDataProvider.class)
	public void TC1722_InProgressStateForInvestigationsInMobileApp(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.info("\nRunning TC1722_InProgressStateForInvestigationsInMobileApp ..." +
			 "\nTest Description: Mobile: 'in progress' state for investigations in mobile app");
		//TODO: TBD
	
	}

	/**
	 * Test Case ID: TC1723_MakeColorsOfInvestigationConsistentForDisposition
	 * Test Description: Mobile: Make colors of investigation boxes and gaps consistent for their disposition
	 * Script:
	 * - Log into Pcubed as a Supervisor or admin role user
	 * -  From the Compliance Reports page, find a report that has 6 or more LISAs and click the Investigate button
	 * - Select 4 LISAs and assign them to a driver-role user by clicking on a username from the dropdown
	 * -  As the user selected above, log into the mobile app
	 * -  Click on the report above
	 * -  Click on a LISA
	 * -  Click on Investigate button
	 * -  Click on the Found Gas Leak button
	 * -  Select Leak Found
	 * -  Select In Progress
	 * -  Select another LISA and set it as Complete/No Leak Found
	 * -  Select another LISA and set it as Complete/Leak Found
	 * -  Log into the mobile app as a supervisor-role user and select the same report
	 * Results:
	 * - User is navigated to dashboard
	 * -  A list of LISAs included in that report will be displayed
	 * -  The username will appear in the Investigator column of the table for the LISAs selected
	 * -  The mobile app shows a list of reports that contain LISAs assigned to the user
	 * -  The app shows a list of the LISAs for that report that are assigned to the user
	 * -  User is taken to a map view that shows the selected LISA and its color should be light grey color
	 * - The user sees options to select Not Investigated/Found Gas Leak/Found Methane Leak/No Leak Found
	 * -  The user sees options to select In Progress/Completed
	 * -  The LISA color should turn violet ( In-Progress)
	 * -  The LISA color should turn blue (Found No Leak)
	 * -  The LISA color should turn red (Leak Found)
	 * -  The app will display all LISAs for that report regardless of assignment. All unassigned LISAs should be light orange (Not Investigated)
	 * -  All assigned bot not investigated LISA should be displayed in light grey color (Not Investigated (Light Grey))
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1723, location = InvestigationReportDataProvider.class)
	public void TC1723_MakeColorsOfInvestigationConsistentForDisposition(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.info("\nRunning TC1723_MakeColorsOfInvestigationConsistentForDisposition ..." +
			 "\nTest Description: Mobile: Make colors of investigation boxes and gaps consistent for their disposition");
		//TODO: TBD
	
	}

	/**
	 * Test Case ID: TC1761_MoibleUserIsAbleToEditLeadInfomation
	 * Test Description: Mobile app user should be able to edit leak information associated with a given LISA
	 * Script:
	 * - Log into the mobile app as a user to whom LISAs have been assigned
	 * -  Click on a report
	 * -  Click on an already investigated LISA (marked as either Complete or In Progress)
	 * -  Click on the Investigate button
	 * -  Click on the Add Source button
	 * -  Select an item marked as a Leak and click on it
	 * -  Change some of the details - be sure that all fields are filled in - and click OK
	 * -  Select the LISA again and verify the changes 
	 * Results:
	 * - User should see a list of reports containing LISAs which were assigned to user
	 * -  List of LISAs for that report should appear. If the user has a supervisory role, all LISAs for that report will appear. Otherwise, only LISAs assigned to the user will appear
	 * -  The user is navigated to the map view showing user's position with Follow and Directions buttons on the right and Investigate button at bottom left
	 * -  Add Source and Add CGI buttons will appear on the right
	 * -  A popup will appear with the details of leak(s) for that LISA
	 * -  The details for that leak will be displayed.
	 * -  All drop-down menus should drop DOWN, not go UP. The user will be navigated to the previous screen showing the list of leaks
	 * -  The edited details should persist
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1761, location = InvestigationReportDataProvider.class)
	public void TC1761_MoibleUserIsAbleToEditLeadInfomation(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.info("\nRunning TC1761_MoibleUserIsAbleToEditLeadInfomation ..." +
			 "\nTest Description: Mobile app user should be able to edit leak information associated with a given LISA");
		//TODO: TBD
	
	}

	/**
	 * Test Case ID: TC1763_MobileUserAbleToEditOtherSource
	 * Test Description: Mobile app user should be able to edit leak information associated with a given LISA
	 * Script:
	 * -  Log into the mobile app as a user to whom LISAs have been assigned
	 * -  Click on a report
	 * -  Click on an already investigated LISA (marked as either Complete or In Progress)
	 * -  Click on the Investigate button
	 * -  Click on the Add Source button
	 * -  Select an item marked as a Other Source and click on it
	 * -  Change some of the details and click OK
	 * -  Select the LISA again and verify the changes 


	 * Results:
	 * - User should see a list of reports containing LISAs which were assigned to user
	 * -  List of LISAs for that report should appear. If the user has a supervisory role, all LISAs for that report will appear. Otherwise, only LISAs assigned to the user will appear
	 * -  The user is navigated to the map view showing user's position with Follow and Directions buttons on the right and Investigate button at bottom left
	 * -  Add Source and Add CGI buttons will appear on the right
	 * -  A popup will appear with the details of leak(s) for that LISA

    Location - Latitude, Longitude
    Source - Sewer, Catch Basin, Landfill, Swamp, Customer, Other Enclosure, Other Natural Source
    Additional Notes

	 * -  The details for that leak will be displayed.
	 * -  The user will be navigated to the previous screen showing the list of leaks
	 * -  The edited details should persist
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1763, location = InvestigationReportDataProvider.class)
	public void TC1763_MobileUserAbleToEditOtherSource(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.info("\nRunning TC1763_MobileUserAbleToEditOtherSource ..." +
			 "\nTest Description: Mobile app user should be able to edit leak information associated with a given LISA");
		//TODO: TBD
	
	}

	/**
	 * Test Case ID: TC1764_MobileUserIsAbleToDeleteLeaks
	 * Test Description: Mobile app user should be able to delete leak information associated with a given LISA
	 * Script:
	 * - Log into the mobile app as a user to whom LISAs have been assigned
	 * -  Click on a report
	 * -  Click on an already investigated LISA (marked as either Complete or In Progress)
	 * -  Click on the Investigate button
	 * -  Click on the Add Source button
	 * -  Select an item marked as a Leak and click on it
	 * -  Click the Delete button
	 * -  Click OK on the Confirmation


	 * Results:
	 * -  User should see a list of reports containing LISAs which were assigned to user
	 * -  List of LISAs for that report should appear. If the user has a supervisory role, all LISAs for that report will appear. Otherwise, only LISAs assigned to the user will appear
	 * -  The user is navigated to the map view showing user's position with Follow and Directions buttons on the right and Investigate button at bottom left
	 * -  Add Source and Add CGI buttons will appear on the right
	 * -  A popup will appear with the details of leak(s) for that LISA
	 * -  The details for that leak will be displayed.
	 * -  A confirmation will appear
	 * -  The deleted leak will no longer appear in the list
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1764, location = InvestigationReportDataProvider.class)
	public void TC1764_MobileUserIsAbleToDeleteLeaks(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.info("\nRunning TC1764_MobileUserIsAbleToDeleteLeaks ..." +
			 "\nTest Description: Mobile app user should be able to delete leak information associated with a given LISA");
		//TODO: TBD
	
	}

	/**
	 * Test Case ID: TC1767_MobileUserIsAbleToDeleteAllOtherSource
	 * Test Description: Mobile app user should be able to delete information for all leaks associated with a given LISA
	 * Script:
	 * -  Log into the mobile app as a user to whom LISAs have been assigned
	 * -  Click on a report
	 * -  Click on an already investigated LISA (marked as either Complete or In Progress)
	 * -  Click on the Investigate button
	 * -  Click on the Add Source button
	 * -  Select an item marked as a Leak and click on it
	 * -  Click the Delete button
	 * -  Click OK on the Confirmation
	 * -  Repeat until all leaks have been deleted
	 * Results:
	 * - User should see a list of reports containing LISAs which were assigned to user
	 * -  List of LISAs for that report should appear. If the user has a supervisory role, all LISAs for that report will appear. Otherwise, only LISAs assigned to the user will appear
	 * -  The user is navigated to the map view showing user's position with Follow and Directions buttons on the right and Investigate button at bottom left
	 * -  Add Source and Add CGI buttons will appear on the right
	 * -  A popup will appear with the details of leak(s) for that LISA
	 * -  The details for that leak will be displayed.
	 * -  A confirmation will appear
	 * -  The deleted leak will no longer appear in the list
	 * -  No leaks will appear for that LISA
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1767, location = InvestigationReportDataProvider.class)
	public void TC1767_MobileUserIsAbleToDeleteAllOtherSource(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.info("\nRunning TC1767_MobileUserIsAbleToDeleteAllOtherSource ..." +
			 "\nTest Description: Mobile app user should be able to delete information for all leaks associated with a given LISA");
		//TODO: TBD
	
	}

}