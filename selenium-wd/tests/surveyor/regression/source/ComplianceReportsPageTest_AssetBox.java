package surveyor.regression.source;

import common.source.Log;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.testng.Assert;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.junit.Test;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.ReportsCompliance.EthaneFilter;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest_AssetBox extends BaseReportsPageActionTest {

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

	/**
	 * Test Case ID: TC927_InvestigationReportWithGaptable
	 * Test Description: - Investigation Report with Gap table
	 * Script: -
	 * - On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 * - TimeZone : PST, Report Mode: Standard, Exclusion Radius: 0
	 * - Click Custom Boundary
	 * - Click Lat/Long Map Selector
	 * - Press the Shift key, then right-click and drag to select the desired area on the map and click OK
	 * - Select one or more surveys
	 * - Add View with base map value: map (make sure to select Gaps in views)
	 * - Click OK
	 * - Click on Investigation PDF
	 * Results: -
	 * - Investigation PDF will have an Indication table and a separate Gap table. The Gap Table will have numbers corresponding to the gaps in the Compliance View with a check box next to each number. The numbers will run sequentially from left to right and then top to bottom. The numbers in the table should exactly match the number of gaps in the Compliance PDF and map
	 * - PDF should have "Gap Investigation Table" with columns for Gap #, Status, Investigation Date/Time, Investigator and Duration

	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC927, location = ComplianceReportDataProvider.class)
	public void TC927_InvestigationReportWithGaptable(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC927_InvestigationReportWithGaptable" +
			 "\nTest Description: Investigation Report with Gap table");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationPDF(EMPTY, getReportRowID(reportDataRowID1));
		Assert.assertTrue(complianceReportsPageAction.waitForInvestigationPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));

		Assert.assertTrue(complianceReportsPageAction.verifyLISAInvestigationTable(EMPTY, getReportRowID(reportDataRowID1)));
		Assert.assertTrue(complianceReportsPageAction.verifyGAPInvestigationTable(EMPTY, getReportRowID(reportDataRowID1)));
	}


	/**
	 * Test Case ID: TC2197_P3300AssetBoxNumberBubblesHaveSameColorAsLISABubbles
	 * Test Description: -  Asset Box Number Bubbles should be rendered with same color as LISA bubbles
	 * Script: -
	 * - Log into the UI and navigate to the Compliance Reports page
	 * - Click on New Compliance Report button
	 * - Uncheck "Exclude Possible Natural Gas", "Exclude Vehicle Exhaust" and "Exclude Biogenic Methane" checkboxes. Set Exclusion Radius at 0.
	 * - In Views section, if Algorithm for Highlighting Assets dropdown is present, select "Asset Boxes". Check LISAs, Indications, Assets, Highlight LISA Assets and Asset Box Number checkboxes.
	 * - Fill out all other required fields and click OK
	 * - When report is completed, compare colors of Asset Box numbers in View PDF with their dispositions on SSRS PDF
	 * Results: -
	 * - If customer has both Asset Box Highlighting and LISA Asset Highlighting licenses, dropdown will be present. If customer has Asset Box Highlighting license but not LISA Asset Highlighting, dropdown will be disabled
	 * - All of the colors for the square bubbles pointing to Asset Boxes should match the colors of the corresponding LISA indication bubbles in the view and their dispositions in the SSRS PDF
	 */
	@Test /* Need to update baseline with more suitable survey for this test */
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2197, location = ComplianceReportDataProvider.class)
	public void TC2197_P3300AssetBoxNumberBubblesHaveSameColorAsLISABubbles(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2197_P3300AssetBoxNumberBubblesHaveSameColorAsLISABubbles ..." +
			 "\nTest Description:  P3300 Asset Box Number Bubbles should be rendered with same color as LISA bubbles");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		ReportsCompliance rpt = complianceReportsPageAction.fillWorkingDataForReports( getReportRowID(reportDataRowID1));
		rpt.setEthaneFilter(EthaneFilter.None);
		complianceReportsPageAction.getComplianceReportsPage().addNewReport(rpt, true);
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID1));
		Assert.assertTrue(complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", getReportRowID(reportDataRowID1)));

		Assert.assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines("false", getReportRowID(reportDataRowID1)));
	}


	/**
	 * Test Case ID: TC2198_P3200AssetBoxNumberBubblesHaveSameColorAsLISABubbles
	 * Test Description: - P3200 - Asset Box Number Bubbles should be rendered with same color as LISA bubbles
	 * - Customer has GIS data, Asset Box Highlighting licenses
	 * - Survey conducted with P3200 analyzer that has indications
	 * Script: -
	 * - Log into the UI and navigate to the Compliance Reports page
	 * - Click on New Compliance Report button
	 * - Uncheck "Exclude Possible Natural Gas", "Exclude Vehicle Exhaust" and "Exclude Biogenic Methane" checkboxes. Set Exclusion Radius at 0.
	 * - In Views section, if Algorithm for Highlighting Assets dropdown is present, select "Asset Boxes". Check LISAs, Indications, Assets, Highlight LISA Assets, and Asset Box Number checkboxes.
	 * - Fill out all other required fields and click OK
	 * - When report is completed, compare colors of Asset Box numbers in View PDF with their dispositions on SSRS PDF
	 * Results: -
	 * - If customer has both Asset Box Highlighting and LISA Asset Highlighting licenses, dropdown will be present. If customer has Asset Box Highlighting license but not LISA Asset Highlighting, dropdown will be disabled
	 * - All of the square bubbles pointing to Asset Boxes should match the blue color of the corresponding LISA indication bubbles in the view
	 */
	@Test /* Need to update baseline with more suitable survey for this test */
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2198, location = ComplianceReportDataProvider.class)
	public void TC2198_P3200AssetBoxNumberBubblesHaveSameColorAsLISABubbles(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2198_P3200AssetBoxNumberBubblesHaveSameColorAsLISABubbles ..." +
			 "\nTest Description: Asset Box Number Bubbles should be rendered with same color as LISA bubbles");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		ReportsCompliance rpt = complianceReportsPageAction.fillWorkingDataForReports( getReportRowID(reportDataRowID1));
		rpt.setEthaneFilter(EthaneFilter.None);
		complianceReportsPageAction.getComplianceReportsPage().addNewReport(rpt, true);
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID1));
		Assert.assertTrue(complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", getReportRowID(reportDataRowID1)));

		Assert.assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines("false", getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC2199_AllIndicationAssetBoxBubblesContainedWithinTheReportViewPDF
	 * Test Description: - All indication and asset box bubbles should be contained within the border of the Report View PDF
	 * - Survey(s) that have a large number of indications in a small area
	 * Script: -
	 * - Log into the UI and navigate to the Compliance Reports page
	 * - Select an area in which one or more surveys taken together produce a high density of indications
	 * - Select one or more surveys which will include a large number of indications in the selected area
	 * - Uncheck Vehicle Exhaust, Not Natural Gas and Possible Natural Gas checkboxes
	 * - Set exclusion radius to 0
	 * - Set page size at 8.5 x 11
	 * - In Views section, select Indications, LISAs, Analysis and Field Notes
	 * - Fill out all other required information and click OK
	 * - When report completes, check View PDF
	 * Results: -
	 * - All indications, analysis results and field notes should be contained within the map and visible (if the density is very high, some may be obscured by others). None should be off of the map
	 */
	@Test /* Need to update baseline with more suitable survey for this test */
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2199, location = ComplianceReportDataProvider.class)
	public void TC2199_AllIndicationAssetBoxBubblesContainedWithinTheReportViewPDF(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2199_AllIndicationAssetBoxBubblesContainedWithinTheReportViewPDF ..." +
			 "\nTest Description: All indication and asset box bubbles should be contained within the border of the Report View PDF");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		ReportsCompliance rpt = complianceReportsPageAction.fillWorkingDataForReports( getReportRowID(reportDataRowID1));
		rpt.setEthaneFilter(EthaneFilter.None);
		complianceReportsPageAction.getComplianceReportsPage().addNewReport(rpt, true);
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID1));
		Assert.assertTrue(complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", getReportRowID(reportDataRowID1)));

		Assert.assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines("false", getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC2200_GapBoxNumberBubblesHasSameColorAsGap
	 * Test Description: - Gap Box Number Bubbles should be rendered with same color as Gap
	 * - Customer has GIS data, Asset Box Highlighting licenses
	 * - Survey conducted with P3200 analyzer that has indications
	 * Script: -
	 * - Log into the UI and navigate to the Compliance Reports page
	 * - Click on New Compliance Report button
	 * - In Views section, if Algorithm for Highlighting Assets dropdown is present, select "Asset Boxes". Check Assets, Gaps, Highlight Gap Assets and Asset Box Number checkboxes.
	 * - Fill out all other required fields and click OK
	 * - When report is completed, compare colors of Asset Box numbers in View PDF with their dispositions on SSRS PDF
	 * Results: -
	 * - If customer has both Asset Box Highlighting and LISA Asset Highlighting licenses, dropdown will be present. If customer has Asset Box Highlighting license but not LISA Asset Highlighting, dropdown will be disabled
	 * - All of the square bubbles pointing to Gap Boxes should match red color of the Gap Box itself
	 */
	@Test /* Need to update baseline with more suitable survey for this test */
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2200, location = ComplianceReportDataProvider.class)
	public void TC2200_GapBoxNumberBubblesHasSameColorAsGap(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2200_GapBoxNumberBubblesHasSameColorAsGap ..." +
			 "\nTest Description: Gap Box Number Bubbles should be rendered with same color as Gap");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		ReportsCompliance rpt = complianceReportsPageAction.fillWorkingDataForReports( getReportRowID(reportDataRowID1));
		rpt.setEthaneFilter(EthaneFilter.None);
		complianceReportsPageAction.getComplianceReportsPage().addNewReport(rpt, true);
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID1));
		Assert.assertTrue(complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", getReportRowID(reportDataRowID1)));

		Assert.assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines("false", getReportRowID(reportDataRowID1)));
	}

}
