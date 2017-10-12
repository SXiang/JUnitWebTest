/**
 *
 */
package surveyor.regression.mobile.source;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.Log;
import common.source.TestContext;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.dataprovider.InvestigationReportDataProvider;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.data.UserDataReader.UserDataRow;
import surveyor.scommon.entities.LeakDetailEntity;
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
public class ComplianceReportsInvestigationPageTest_Tahoe extends BaseReportsPageActionTest {
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
	 * Test Case ID: TC2793_ComplianceReportForAssetBoxAreNotIntersectingWithLisas
	 * Test Description: Generate report for Asset Box algorithm when Assets are not intersecting with LISAs
	 *	- Customer has Asset Box Algorithm permission
	 *	- Survey having LISAs but not a single LISA has assets intersected to it
	 * Script: -
	 *	-Log in as utility admin
	 *	- Navigate to Reports -> Compliance
	 *	- Generate compliance report with above survey (At least one view with LISAs and Assets selected)
	 *	- Download View PDF
	 *	- Click on Investigate button
	 *	- Click on Assign Investigators button
	 *	- Assign couple of LISAs for investigation
	 *	- Log in to mobile view as assigned user
	 *	- Investigate any one of the LISAs
	 *	- Download investigate PDF and CSV button
	 * Results: -
	 *	-Assets should not be intersected with any LISAs
	 *	- LISAs should be present on Investigation List screen
	 *	- LISAs should be present instead of Assets in Assign Investigator Map screen
	 *	- User should be able to investigate LISAs successfully
	 *	- Investigate PDF and CSV should have correct LISA information and other details
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2793, location = InvestigationReportDataProvider.class)
	public void TC2793_ComplianceReportForAssetBoxAreNotIntersectingWithLisas(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.info("\nRunning TC2793_ComplianceReportForAssetBoxAreNotIntersectingWithLisas ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		// Generate report
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		String reportName = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportName(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID));

		// Verify View - Assets should not be intersected with any LISAs
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID));
		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID));
		complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines_Static("false", getReportRowID(reportDataRowID)));
		complianceReportsPageAction.clickOnCloseReportViewer(EMPTY, reportDataRowID);
		
		// Assign Lisas to user
		int workingLisa = 1;
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		reportInvestigationsPage.selectLisasByNumber(reportName, workingLisa);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);

		// Mobile - login and investigate Lisas
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow.username, mobileUserDataRow.password);
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);

		// Mobile - add leak and complete
		LeakDetailEntity leakDetails = new LeakDetailEntity(mobileUserDataRow.username, workingLisa);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(reportName, workingLisa);
		mobileInvestigatePage.clickOnInvestigate(leakDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddLeak();
		leakDetails.setDefaultTestData();
		mobileLeakSourcePage.addLeakDetails(leakDetails);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails);
		
		/* 2. Verify investigation reports - PDF and CSV */		
		complianceReportsPageAction.open(EMPTY, reportDataRowID);
		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationPDF(EMPTY, getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.waitForInvestigationPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID)));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationData(EMPTY, getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.waitForInvestigationCSVFileDownloadToComplete(EMPTY, reportDataRowID));
		complianceReportsPageAction.clickOnCloseReportViewer(EMPTY, getReportRowID(reportDataRowID));

		
		assertTrue(mobileLeakSourcePage.verifyPDFLeakDetails(leakDetails.toPDFLeakDetails(), complianceReportsPageAction.getLISAInvestigationPDFData(1,reportDataRowID)));
		assertTrue(mobileLeakSourcePage.verifyMetaLeakDetails(leakDetails.toCSVLeakDetails(), complianceReportsPageAction.getLISAInvestigationMetaData(1,reportDataRowID)));

		mobileLoginPage.logout();		
	}
	
	/**
	 * Test Case ID: TC2794_ComplianceReportCopyForAssetBoxAreNotIntersectingWithLisas
	 * Test Description: Generate report for Asset Box algorithm using copy functionality when Assets are not intersecting with LISAs
	 *	- Customer has Asset Box Algorithm permission
	 *	- Survey having LISAs but not a single LISA has assets intersected to it
	 *	- Old Compliance report where assets are not intersecting with LISAs
	 * Script: -
	 *	-Log in as utility admin
	 *	- Click on copy button of above report
	 *	- Click OK
	 *	- Download View PDF
	 *	- Click on Investigate button
	 *	- Click on Assign Investigators button
	 *	- Assign couple of LISAs for investigation
	 *	- Log in to mobile view as assigned user
	 *	- Investigate any one of the LISAs
	 *	- Download investigate PDF and CSV button
	 * Results: -
	 *	-Assets should not be intersected with any LISAs
	 *	- LISAs should be present on Investigation List screen
	 *	- LISAs should be present instead of Assets in Assign Investigator Map screen
	 *	- User should be able to investigate LISAs successfully
	 *	- Investigate PDF and CSV should have correct LISA information and other details
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2794, location = InvestigationReportDataProvider.class)
	public void TC2794_ComplianceReportCopyForAssetBoxAreNotIntersectingWithLisas(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.info("\nRunning TC2794_ComplianceReportCopyForAssetBoxAreNotIntersectingWithLisas ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		// Generate report
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		complianceReportsPageAction.waitForReportGenerationToComplete(EMPTY, getReportRowID(reportDataRowID));
		complianceReportsPageAction.copyReport(EMPTY, getReportRowID(reportDataRowID));
		String reportName = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportName(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID));

		// Verify View - Assets should not be intersected with any LISAs
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID));
		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID));
		complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines_Static("false", getReportRowID(reportDataRowID)));
		complianceReportsPageAction.clickOnCloseReportViewer(EMPTY, reportDataRowID);
		
		// Assign Lisas to user
		int workingLisa = 1;
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		reportInvestigationsPage.selectLisasByNumber(reportName, workingLisa);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);

		// Mobile - login and investigate Lisas
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow.username, mobileUserDataRow.password);
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);

		// Mobile - add leak and complete
		LeakDetailEntity leakDetails = new LeakDetailEntity(mobileUserDataRow.username, workingLisa);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(reportName, workingLisa);
		mobileInvestigatePage.clickOnInvestigate(leakDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddLeak();
		leakDetails.setDefaultTestData();
		mobileLeakSourcePage.addLeakDetails(leakDetails);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails);
		
		/* 2. Verify investigation reports - PDF and CSV */		
		complianceReportsPageAction.open(EMPTY, reportDataRowID);
		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationPDF(EMPTY, getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.waitForInvestigationPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID)));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationData(EMPTY, getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.waitForInvestigationCSVFileDownloadToComplete(EMPTY, reportDataRowID));
		complianceReportsPageAction.clickOnCloseReportViewer(EMPTY, getReportRowID(reportDataRowID));

		
		assertTrue(mobileLeakSourcePage.verifyPDFLeakDetails(leakDetails.toPDFLeakDetails(), complianceReportsPageAction.getLISAInvestigationPDFData(1,reportDataRowID)));
		assertTrue(mobileLeakSourcePage.verifyMetaLeakDetails(leakDetails.toCSVLeakDetails(), complianceReportsPageAction.getLISAInvestigationMetaData(1,reportDataRowID)));

		mobileLoginPage.logout();		
	}
	
	/**
	 * Test Case ID: TC2828_ComplianceReportForLisaAssetsAreNotIntersectingWithLisas
	 * Test Description: Generate report for LISAs Asset Highlight algorithm when Assets are not intersecting with LISAs
	 *	- Customer has LISA Asset Highlight Algorithm permission
	 *	- Survey having LISAs but not a single LISA has assets intersected to it
	 * Script: -
	 *	- Log in as utility admin
	 *	- Navigate to Reports -> Compliance
	 *	- Generate compliance report with above survey (Atleast one view with LISAs and Assets selected)
	 *	- Download View PDF
	 *	- Click on Investigate button
	 *	- Click on Assign Investigators button
	 *	- Assign couple of LISAs for investigation
	 *	- Log in to mobile view as assigned user
	 *	- Investigate any one of the LISAs
	 *	- Download investigate PDF and CSV button
	 * Results: -
	 *	- Assets should not be intersected with any LISAs
	 *	- LISAs should be present on Investigation List screen
	 *	- LISAs should be present instead of Assets in Assign Investigator Map screen
	 *	- User should be able to investigate LISAs successfully
	 *	- Investigate PDF and CSV should have correct LISA information and other details
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2828, location = InvestigationReportDataProvider.class)
	public void TC2828_ComplianceReportForLisaAssetsAreNotIntersectingWithLisas(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.info("\nRunning TC2828_ComplianceReportForLisaAssetsAreNotIntersectingWithLisas ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		// Generate report
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		String reportName = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportName(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID));

		// Verify View - Assets should not be intersected with any LISAs
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID));
		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID));
		complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines_Static("false", getReportRowID(reportDataRowID)));
		complianceReportsPageAction.clickOnCloseReportViewer(EMPTY, reportDataRowID);
		
		// Assign Lisas to user
		int workingLisa = 1;
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		reportInvestigationsPage.selectLisasByNumber(reportName, workingLisa);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);

		// Mobile - login and investigate Lisas
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow.username, mobileUserDataRow.password);
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);

		// Mobile - add leak and complete
		LeakDetailEntity leakDetails = new LeakDetailEntity(mobileUserDataRow.username, workingLisa);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(reportName, workingLisa);
		mobileInvestigatePage.clickOnInvestigate(leakDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddLeak();
		leakDetails.setDefaultTestData();
		mobileLeakSourcePage.addLeakDetails(leakDetails);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails);
		
		/* 2. Verify investigation reports - PDF and CSV */		
		complianceReportsPageAction.open(EMPTY, reportDataRowID);
		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationPDF(EMPTY, getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.waitForInvestigationPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID)));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationData(EMPTY, getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.waitForInvestigationCSVFileDownloadToComplete(EMPTY, reportDataRowID));
		complianceReportsPageAction.clickOnCloseReportViewer(EMPTY, getReportRowID(reportDataRowID));

		
		assertTrue(mobileLeakSourcePage.verifyPDFLeakDetails(leakDetails.toPDFLeakDetails(), complianceReportsPageAction.getLISAInvestigationPDFData(1,reportDataRowID)));
		assertTrue(mobileLeakSourcePage.verifyMetaLeakDetails(leakDetails.toCSVLeakDetails(), complianceReportsPageAction.getLISAInvestigationMetaData(1,reportDataRowID)));

		mobileLoginPage.logout();		
	}
	
	/**
	 * Test Case ID: TC2829_ComplianceReportCopyForLisaAssetsAreNotIntersectingWithLisas
	 * Test Description: Generate report for LISAs Asset Highlight algorithm using copy functionality when Assets are not intersecting with LISAs
	 *	- Customer has LISAs Asset Highlight Algorithm permission
	 *	- Survey having LISAs but not a single LISA has assets intersected to it
	 *	- Old Compliance report where assets are not intersecting with LISAs
	 * Script: -
	 *	- Log in as utility admin
	 *	- Click on copy button of above report
	 *	- Click OK
	 *	- Download View PDF
	 *	- Click on Investigate button
	 *	- Click on Assign Investigators button
	 *	- Assign couple of LISAs for investigation
	 *	- Log in to mobile view as assigned user
	 *	- Investigate any one of the LISAs
	 *	- Download investigate PDF and CSV button
	 * Results: -
	 *	- Assets should not be intersected with any LISAs
	 *	- LISAs should be present on Investigation List screen
	 *	- LISAs should be present instead of Assets in Assign Investigator Map screen
	 *	- User should be able to investigate LISAs successfully
	 *	- Investigate PDF and CSV should have correct LISA information and other details
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2829, location = InvestigationReportDataProvider.class)
	public void TC2829_ComplianceReportCopyForLisaAssetsAreNotIntersectingWithLisas(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.info("\nRunning TC2829_ComplianceReportCopyForLisaAssetsAreNotIntersectingWithLisas ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		// Generate report
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		complianceReportsPageAction.waitForReportGenerationToComplete(EMPTY, getReportRowID(reportDataRowID));
		complianceReportsPageAction.copyReport(EMPTY, getReportRowID(reportDataRowID));
		String reportName = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportName(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID));

		// Verify View - Assets should not be intersected with any LISAs
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID));
		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID));
		complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines_Static("false", getReportRowID(reportDataRowID)));
		complianceReportsPageAction.clickOnCloseReportViewer(EMPTY, reportDataRowID);
		
		// Assign Lisas to user
		int workingLisa = 1;
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		reportInvestigationsPage.selectLisasByNumber(reportName, workingLisa);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);

		// Mobile - login and investigate Lisas
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow.username, mobileUserDataRow.password);
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);

		// Mobile - add leak and complete
		LeakDetailEntity leakDetails = new LeakDetailEntity(mobileUserDataRow.username, workingLisa);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(reportName, workingLisa);
		mobileInvestigatePage.clickOnInvestigate(leakDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddLeak();
		leakDetails.setDefaultTestData();
		mobileLeakSourcePage.addLeakDetails(leakDetails);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails);
		
		/* 2. Verify investigation reports - PDF and CSV */		
		complianceReportsPageAction.open(EMPTY, reportDataRowID);
		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationPDF(EMPTY, getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.waitForInvestigationPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID)));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationData(EMPTY, getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.waitForInvestigationCSVFileDownloadToComplete(EMPTY, reportDataRowID));
		complianceReportsPageAction.clickOnCloseReportViewer(EMPTY, getReportRowID(reportDataRowID));

		
		assertTrue(mobileLeakSourcePage.verifyPDFLeakDetails(leakDetails.toPDFLeakDetails(), complianceReportsPageAction.getLISAInvestigationPDFData(1,reportDataRowID)));
		assertTrue(mobileLeakSourcePage.verifyMetaLeakDetails(leakDetails.toCSVLeakDetails(), complianceReportsPageAction.getLISAInvestigationMetaData(1,reportDataRowID)));

		mobileLoginPage.logout();		
	}
	
	/**
	 * Test Case ID: TC2201_HighlightLISAAssetsSelectedInViewsSection
	 * Test Description: Compliance Report generated with Asset Boxes selected from Search Area Preference dropdown. 
	 * 						Make sure Highlight LISA Assets, LISA and Assets are selected in Views section
	 * Script:
	 * - Log in as Customer Supervisor user
	 * - Navigate to Compliance Reports page
	 * - Click on Investigate button
	 * - Select one or more LISAs by checking the checkbox(es) on the right
	 * - There are two Assign Investigators buttons near the top right. Click the leftmost of these buttons.
	 * - From the dropdown, select a user to whom to assign the LISA(s)
	 * - On a mobile device, log in as the selected user
	 * - Select the appropriate report
	 * - Select "LISA" from the dropdown
	 * - Click on a LISA
	 * - Click on Follow button
	 * Results:
	 * - User is navigated to LISA Investigations page by default. Dropdown is present at top left to select LISAs or Gaps
	 * - A dropdown menu will appear with names of users for this customer
	 * - The dropdown will disappear and the selected name will appear in the Investigator column of the selected LISA(s)
	 * - User is navigated to a page with a list of one or more reports 
	 * - User is navigated to a page with a list of one or more LISAs and a dropdown menu with selections "Indication" and "Gap". Gaps should not appear on this list unless selected from the dropdown
	 * - User is navigated to a map showing user's location
	 * - On map, assets contained within Asset Box are highlighted  in green. Asset Box is outlined in red
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2201, location = InvestigationReportDataProvider.class)
	public void TC2201_HighlightLISAAssetsSelectedInViewsSection(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.info("\nRunning TC2201_HighlightLISAAssetsSelectedInViewsSection ..." +
			 "\nTest Description: Compliance Report generated with Asset Boxes selected from Search Area Preference dropdown. Make sure Highlight LISA Assets, LISA and Assets are selected in Views section");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		// Generate report
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		String reportName = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportName(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID));

		// Assign Lisas to user
		int workingLisa = 9;
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		reportInvestigationsPage.selectLisasByNumber(reportName, workingLisa);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);

		// Mobile - login and investigate lisas
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow.username, mobileUserDataRow.password);
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);

		// Mobile - add leak and complete
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(reportName, workingLisa);
		mobileInvestigatePage.clickOnFollow();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "investigationLisaMap"));

		mobileLoginPage.logout();
	}

	/**
	 * Test Case ID: TC2202_HighlightGapAssetsSelectedInViewsSection
	 * Test Description: Search valid lisa on investigation report screen
	 * Script:
	 * - Log in as Customer Supervisor user
	 * - Navigate to Compliance Reports page
	 * - Click on Investigate button
	 * - Select Gaps from dropdown near top left
	 * - Select one or more Gaps by checking the checkbox(es) on the right
	 * - There are two Assign Investigators buttons near the top right. Click the leftmost of these buttons.
	 * - From the dropdown, select a user to whom to assign the Gap(s)
	 * - On a mobile device, log in as the selected user
	 * - Select the appropriate report
	 * - Select Gap from the dropdown
	 * - Click on a Gap
	 * - Click on Follow button
	 * Results:
	 * -  User is navigated to Gap Investigations page
	 * - A dropdown menu will appear with names of users for this customer
	 * - The dropdown will disappear and the selected name will appear in the Investigator column of the selected Gap(s)
	 * - User is navigated to a page with a list of one or more reports 
	 * - User is navigated to a page with a list of one or more LISAs and a dropdown menu with selections "Indication" and "Gap"
	 * - User is navigated to a map showing user's location
	 * - On map, assets contained within Asset Box are highlighted in green. Asset Box is outlined in red
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2202, location = InvestigationReportDataProvider.class)
	public void TC2202_HighlightGapAssetsSelectedInViewsSection(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.info("\nRunning TC2202_HighlightGapAssetsSelectedInViewsSection ..." +
			 "\nTest Description: Compliance Report generated with Asset Boxes selected from Search Area Preference dropdown. Make sure Highlight Gap Assets, Gaps and Assets are selected in Views section");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		// Generate report
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		String reportName = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportName(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID));
		// Assign Lisas to user
		int workingGap = 9;
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		reportInvestigationsPage.selectGapsByNumber(reportName, workingGap);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);

		// Mobile - login and investigate lisas
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow.username, mobileUserDataRow.password);
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);

		// Mobile - add leak and complete
		mobileInvestigatePage = mobileInvestigationPage.clickOnGap(reportName, workingGap);
		mobileInvestigatePage.clickOnFollow();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "investigationGapMap"));

		mobileLoginPage.logout();
	}
}