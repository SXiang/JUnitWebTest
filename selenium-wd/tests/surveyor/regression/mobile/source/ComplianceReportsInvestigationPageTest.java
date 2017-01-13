/**
 *
 */
package surveyor.regression.mobile.source;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static surveyor.scommon.source.SurveyorConstants.KEYINDTB;
import static surveyor.scommon.source.SurveyorConstants.KEYISOANA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCRA;
import static surveyor.scommon.source.SurveyorConstants.NOMATCHINGSEARCH;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDRTAG;
import static surveyor.scommon.source.SurveyorConstants.SQACUSMNTAG;

import static surveyor.scommon.source.ReportsCompliance.EthaneFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.CryptoUtility;
import common.source.Log;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.BaseReportsPageTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.MeasurementSessionsPage;
import surveyor.scommon.source.ComplianceReportsPage.ComplianceReportButtonType;
import surveyor.scommon.source.MeasurementSessionsPage.DrivingSurveyButtonType;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest.ReportTestRunMode;

/**
 *
 *
 */
@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsInvestigationPageTest extends BaseReportsPageActionTest {
	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;

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

// TC219, TC224, TC807, TC1553, TC1623, TC1624, TC1628, TC1629
// TC695, TC1378 are marked as not automatable
	
	/**
	 * Test Case ID: TC219_LisaInvestigatedAndLeakFoundStatusShouldPersistOnMobileView
	 * Test Description: Lisa Investigated and Leak found status should persist on Mobile View
	 * Script: 
	 *	- Generate compliance report for survey having indications in it
	 * - On Home Page, navigate to Reports -> Compliance Reports page
	 * - Click on Investigate icon present for the compliance report
	 * - Select the Lisa to assign and click on Assing Investigators button
	 * - Select Assigned To name from the drop down and click OK
	 * - Now login to application using mobile with Assgined To user credentials
	 * - Click on Report Name and any one of the assigned LISA
	 * - Click on Investigate
	 * - Click on Add Source -> Add Leak button
	 * - Fill out leak details in popup window and click OK, the click on Mark as Complete button
	 * - Select another LISA and repeat, but do not click on Mark as Complete button
	 * - Select a third LISA and click on Add Source -> Add Other Source button, fill in details and click OK, then click Mark as Complete
	 * - Click on first LISA -> Investigate -> Add Source
	 * - Click on second LISA -> Investigate -> Add Source
	 * - Click on third LISA -> Investigate -> Add Source
	 * - Click on Investigate button on Compliance Reports page
	 * Results: 
	 *	-  First LISA should be marked as Investigated, Leak Found, with Status of Found Gas Leak
	 * - Second LISA should be marked as Investigated, Leak Found, with Status of In Progress
	 * - Third LISA should be marked as Investigated, Leak Not Found, with Status of Found Other Source
	 */
	
	@UseDataProvider(value = ComplianceReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC219, location = ComplianceReportDataProvider.class)
	public void TC219_LisaInvestigatedAndLeakFoundStatusShouldPersistOnMobileView(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC219_LisaInvestigatedAndLeakFoundStatusShouldPersistOnMobileView ..." +
			 "\nTest Description: Lisa Investigated and Leak found status should persist on Mobile View");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		Assert.assertTrue(complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
	}
	
	/**
	 * Test Case ID: TC224_SearchValidLisaOnInvestigationReportScreen
	 * Test Description: Search valid lisa on investigation report screen
	 * Script: 
	 *	-  Navigate to investigation report screen
	 * - Search the Lisa with valid CH4 value
	 * Results: 
	 *	-  Search result should display the lisa with that provided CH4 value
	 */
	@UseDataProvider(value = ComplianceReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC224, location = ComplianceReportDataProvider.class)
	public void TC224_SearchValidLisaOnInvestigationReportScreen(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC224_SearchValidLisaOnInvestigationReportScreen ..." +
			 "\nTest Description: Search valid lisa on investigation report screen");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		Assert.assertTrue(complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
	}
	
	/**
	 * Test Case ID: TC695_VerifyMobileIsNotPreFetchingMap.
	 * Test Description: Verify that Mobile is not pre fetching map always and data usage is minimized.
	 * Script: 
	 *	-  In Chrome, got to developer tool (Click on 'Customize and control Google Chrome' (top right corner) -> More Tools -> Developer Tools) 
  - Turn on device mode by pressing the Toggle device mode  icon (When device mode         is enabled, the icon turns blue and the viewport transforms into a device emulator.)
  - Switch to Network tab on developer tool.
 - Login as Driver to Pcubed site in required environment. 
	 *	- 
	 * Results: 
	 *	-  verify that the network activity is stalled when the map is already rendered and you are not moving the screen. (Today the network activity keeps on happening in loop.)
  - data activity is not happening in the console.
	 */
	@Ignore /* Not automatable */
	@UseDataProvider(value = ComplianceReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC219, location = ComplianceReportDataProvider.class)
	public void TC695_VerifyMobileIsNotPreFetchingMap(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC695_VerifyMobileIsNotPreFetchingMap ..." +
			 "\nTest Description: Verify that Mobile is not pre fetching map always and data usage is minimized.");
	}
	
	
	/**
	 * Test Case ID: TC807_InvestigateLisaAsNewUser
	 * Test Description: Investigate Lisa as new user
	 * Script: 
	 *	- Generate a new driver user (eg. driveruser@picarro.com)
	 * - Generate Compliance report (eg. Test report)
	 *	-  Log in to Mobile View as new user (eg. driveruser@picarro.com)
	 * - Click on "I Accept" button present on EULA screen
	 * - Log in to application as admin
	 * - Navigate to compliance report page and click on Investigate button (eg. Test report)
	 * - Assign Lisa to newly created user (eg. driveruser@picarro.com)
	 * - Log in to Mobile View as new user (eg. driveruser@picarro.com)
	 * - Click on Report (eg: Test Report) -> Lisa (eg: LISA-1)
	 * - Click Investigate -> Add Source -> Add Leak
	 * - Fill out details of leak on popup and click OK
	 * - Click on Mark as Complete button
	 * - On web view, check for the above Lisa
	* Results:
    *  -  User should be able to log in and accept the EULA successfully
	 * - User is navigated to report list page and should see only those reports of which the Lisa's are assigned (eg. Test report)
	 * - User can successfully investigate the Lisa and Found Gas Leak is displayed after that Lisa in mobile view
	 * - Investigation status, Leak found, Date values should be updated
	 * - All data present on mobile app should be present in PDF and csv with same details. Eg. Lisa number, amplitude, Status, Investigation Date/Time, Investigator, Duration, Source, Lat/Long, Leak details, notes, etc
	 */
	@UseDataProvider(value = ComplianceReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC807, location = ComplianceReportDataProvider.class)
	public void TC807_InvestigateLisaAsNewUser(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC807_InvestigateLisaAsNewUser ..." +
			 "\nTest Description: Investigate Lisa as new user");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		Assert.assertTrue(complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
	}
	
	/**
	 * Test Case ID: TC1378_MobileAppMapDoesNotAutoRotate
	 * Test Description: Map on mobile device should not rotate as user walks in different directions. Map should always be oriented so that North is at the top of the screen.
	 * Script: 
	 *	- On the Compliance Reports page, select a report that has LISAs and click the Investigate button
	 * - Click on one or more of the checkboxes on the far right
	 * - Find the two Assign Investigators buttons on the right and click on the one to the left (with the square icon)
	 * - From the dropdown menu, select a user to assign for investigation and click OK
	 * - On a mobile app, log into pcubed as the user in the previous step
	 * - From the list of reports that appears on the mobile device, select the report in the first step and click on it
	 * - Select a LISA and click on it
	 * - Walk around in different directions
	 * Results: 
	 *	- While walking around and viewing the map on the mobile device, the orientation should consistently show the map with North at the top of the screen. The Map should not rotate, regardless of the direction in which the user is moving
	 */
	@Ignore /* Not automatable */
	@UseDataProvider(value = ComplianceReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1378, location = ComplianceReportDataProvider.class)
	public void TC1378_MobileAppMapDoesNotAutoRotate(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1378_MobileAppMapDoesNotAutoRotate ..." +
			 "\nTest Description: Map on mobile device should not rotate as user walks in different directions. Map should always be oriented so that North is at the top of the screen.");
	}

	/**
	 * Test Case ID: TC1553_CheckCustomerDriverCanLogoutFromMapPageInMobileView
	 * Test Description: Check customer driver user can logout from map page in mobile view
	 * Script: 
	 *	- Generate report and assign LISA for investigation to customer supervisor user 
	 *	- Log in to mobile application as customer driver user
	 * - Click on any one of the LISA for investigation
	 * - Click on Investigate -> Add Source -> Add Leak
	 * - Fill in leak details in popup and click OK
	 * - Click Mark as Complete
	 * - Click on Log out button
	 * - Download investigation report
	 * Results: 
	 *	- User is navigated to map page and Logout button is present
	 * - LISA investigation status is saved
	 * - User is logged out successfully
	 * - Investigation report SSRS PDF is displaying correct LISA investigation status.  Lisa investigated: Yes  Leak Found: Yes
	 */
	@UseDataProvider(value = ComplianceReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1553, location = ComplianceReportDataProvider.class)
	public void TC1553_CheckCustomerDriverCanLogoutFromMapPageInMobileView(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1553_CheckCustomerDriverCanLogoutFromMapPageInMobileView ..." +
			 "\nTest Description: Check customer driver user can logout from map page in mobile view");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		Assert.assertTrue(complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC1623_CustomerUserCanSearchValidInvestigationReportOnMobileView
	 * Test Description: Customer user can search a valid investigation report on mobile view
	 * Script: 
	 *	- Log in as customer supervisor or admin user
	 * - Generate compliance report for survey having indications in it
	 * - On Home Page, navigate to Reports -> Compliance Reports page
	 * - Click on Investigate icon present for the compliance report
	 * - Click couple of LISA check box and click on Assign Investigators button
	 * - Select Assigned To name from the drop down and click OK
	 *	- Log in as customer supervisor user to mobile view (eg. PGE supervisor user)
	 * - Type report title in search box
	 * - Click on searched report
	 * Results: 
	 *	- Report is searched successfully 
	 * - User can see LISA associated searched report 
	 */
	@UseDataProvider(value = ComplianceReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1623, location = ComplianceReportDataProvider.class)
	public void TC1623_CustomerUserCanSearchValidInvestigationReportOnMobileView(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1623_CustomerUserCanSearchValidInvestigationReportOnMobileView ..." +
			 "\nTest Description: Customer user can search a valid investigation report on mobile view");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		Assert.assertTrue(complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
	}
	
	/**
	 * Test Case ID: TC1624_MessageToUserInvalidInvestigationReportOnMobileView
	 * Test Description: Message is displayed to user if Customer user search invalid investigation report on mobile view
	 * Script: 
	 *	- Log in as customer supervisor or admin user
	 * - Generate compliance report for survey having indications in it
	 * - On Home Page, navigate to Reports -> Compliance Reports page
	 * - Click on Investigate icon present for the compliance report
	 * - Click couple of LISA check box and click on Assign Investigators button
	 * - Select Assigned To name from the drop down and click OK
	 * - Log in as customer supervisor user to mobile view (eg. PGE supervisor user)
	 * - Type invalid or non-existing report title in search box (eg. zzzzakfjd)
	 * Results: 
	 *	-  "No matching records found" or user friendly message should be displayed to the user
	 */
	@UseDataProvider(value = ComplianceReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1624, location = ComplianceReportDataProvider.class)
	public void TC1624_MessageToUserInvalidInvestigationReportOnMobileView(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1624_MessageToUserInvalidInvestigationReportOnMobileView ..." +
			 "\nTest Description: Message is displayed to user if Customer user search invalid investigation report on mobile view");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		Assert.assertTrue(complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
	}
	
	/**
	 * Test Case ID: TC1628_MobileViewClassicLISAshape
	 * Test Description: Mobile View - Classic LISA shape
	 * - Customer should have assets and assets should be intersected with LISAs
	 * - Compliance Report that contains LISAs for customer that has LISA Box 1.0 enabled. Make sure LISA, Assets and Highlight LISA Assets are selected in report's view section
	 * Script: 
	 *	- Log in as Customer Supervisor user
	 * - Navigate to Compliance Reports page
	 * - Click on Investigate button
	 * - Select one or more LISAs by checking the checkbox(es) on the right
	 * - There are two Assign Investigators buttons near the top right. Click the leftmost of these buttons.
	 * - From the dropdown, select a user to whom to assign the LISA(s)
	 * - On a mobile device, log in as the selected user
	 * - Select the appropriate report
	 * - Select Indication from the dropdown
	 * - Click on a LISA
	 * - Click on Follow button
	 * Results: 
	 *	-  User is navigated to LISA Investigations page
	 * - A drop-down menu will appear with names of users for this customer
	 * - The drop-down will disappear and the selected name will appear in the Investigator column of the selected LISA(s)
	 * - User is navigated to a page with a list of one or more reports 
	 * - User is navigated to a page with a list of one or more LISAs and a drop-down menu with selections "LISA" and "Gap"
	 * - User is navigated to a map showing user's location
	 * - On map, assets intersecting Classic LISAs are highlighted 
	 */
	@UseDataProvider(value = ComplianceReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1628, location = ComplianceReportDataProvider.class)
	public void TC1628_MobileViewClassicLISAshape(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1628_MobileViewClassicLISAshape ..." +
			 "\nTest Description: Mobile View - Classic LISA shape");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		Assert.assertTrue(complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
	}
	
	/**
	 * Test Case ID: TC1629_MobileViewWithLISABoxShape
	 * Test Description: Mobile View - LISA Box shape
	 * Pre-Conditions: 
	 * - Customer should have assets and assets should be intersected with LISAs
	 * - Compliance Report that contains LISAs for customer that has LISA Box 1.0 enabled. Make sure LISA, Assets and Highlight LISA Assets are selected in report's view section
	 * Script: 
	 * - Log in as Customer Supervisor user
	 * - Navigate to Compliance Reports page
	 * - Click on Investigate button
	 * - Select one or more LISAs by checking the checkbox(es) on the right
	 * - There are two Assign Investigators buttons near the top right. Click the leftmost of these buttons.
	 * - From the dropdown, select a user to whom to assign the LISA(s)
	 * - On a mobile device, log in as the selected user
	 * - Select the appropriate report 
	 * - Click on a LISA
	 * - Click on Follow button
	 * Results: 
	 * - User is navigated to LISA Investigations page
	 * - A dropdown menu will appear with names of users for this customer
	 * - The dropdown will disappear and the selected name will appear in the Investigator column of the selected LISA(s)
	 * - User is navigated to a page with a list of one or more reports 
	 * - User is navigated to a page with a list of one or more LISAs
	 * - User is navigated to a map showing user's location
	 * - On map, LISA Boxes are displayed and assets intersecting Lisa are highlighted 
	 */
	@UseDataProvider(value = ComplianceReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1629, location = ComplianceReportDataProvider.class)
	public void TC1629_MobileViewWithLISABoxShape(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1629_MobileViewWithLISABoxShape ..." +
			 "\nTest Description: Mobile View - LISA Box shape");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		Assert.assertTrue(complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
	}

}