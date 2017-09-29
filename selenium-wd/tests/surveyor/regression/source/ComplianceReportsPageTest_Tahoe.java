package surveyor.regression.source;

import static org.junit.Assert.assertTrue;
import org.junit.Before;

import common.source.Log;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.entities.ComplianceReportEntity;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.SurveyorConstants.ReportColorOption;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.LatLongSelectionControl;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.dataprovider.ComplianceReportDataProvider;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest_Tahoe extends BaseReportsPageActionTest {

	private static final String EMPTY = "";
	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static LatLongSelectionControl latLongSelectionControl = null;

	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects();
	}

	@Before
	public void beforeTest() throws Exception {
		initializeTestObjects();

		initializePageActions();

		initializeTestPageObjects();

		// Select run mode here.
		setPropertiesForTestRunMode();
	}

	private void initializeTestPageObjects() {
		latLongSelectionControl = new LatLongSelectionControl(getDriver());
		PageFactory.initElements(getDriver(), latLongSelectionControl);
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
	 * Test Case ID: TC2238_ComplianceReportWithOnlyHighlightLISAAssetsInView
	 * Test Description: Generate compliance report for customer having license for Asset box feature and only highlight LISA Assets is selected in views 
	 *	- Customer has license for  Asset box and GIS layers
	 *	- Customer has GIS data
	 *	- Customer has survey with leaks and gaps, both intersecting with couple of assets 
	 * Script: -
	 *	- - On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- - Timezone : PST, Report Mode: Standard, Exclusion Radius: 0
	 *	- - Click Custom Boundary
	 *	- - Click Lat/Long Map Selector
	 *	- - Press the Shift key, then right-click and drag to select the desired area on the map and click OK
	 *	- - Select one or more surveys
	 *	- - Select Asset Boxes from Search Area Preference
	 *	- - Add View with base map value: select only Highlight LISA Assets
	 *	- - Select indication, Gap table in Optional tabular PDF content
	 *	- - Select Asset layers
	 *	- - Click OK
	 *	- - When report has completed, click Report Viewer Icon and download Compliance Table and View PDFs
	 * Results: -
	 *	- - Assets intersecting LISA should be highlighted in report view PDF
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2238, location = ComplianceReportDataProvider.class)
	public void TC2238_ComplianceReportWithOnlyHighlightLISAAssetsInView(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2238_ComplianceReportWithOnlyHighlightLISAAssetsInView ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines_Static("false", getReportRowID(reportDataRowID1)));
	}
	
	/**
	 * Test Case ID: TC2239_ComplianceReportWithOnlyHighlightGapAssetsInView
	 * Test Description: Generate compliance report for customer having license for Asset box feature and only highlight Gap Assets is selected in views 
	 *	- Customer has license for  Asset box and GIS layers
	 *	- Customer has GIS data
	 *	- Customer has survey with leaks and gaps, both intersecting with couple of assets 
	 * Script: -
	 *	- - On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- - Timezone : PST, Report Mode: Standard, Exclusion Radius: 0
	 *	- - Click Custom Boundary
	 *	- - Click Lat/Long Map Selector
	 *	- - Press the Shift key, then right-click and drag to select the desired area on the map and click OK
	 *	- - Select one or more surveys
	 *	- - Select Asset Boxes from Search Area Preference
	 *	- - Add View with base map value: select only Highlight Gap Assets
	 *	- - Select indication, Gap table in Optional tabular PDF content
	 *	- - Select Asset layers
	 *	- - Click OK
	 *	- - When report has completed, click Report Viewer Icon and download Compliance Table and View PDFs
	 * Results: -
	 *	- - Assets intersecting Gap should be highlighted in report view PDF
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2239, location = ComplianceReportDataProvider.class)
	public void TC2239_ComplianceReportWithOnlyHighlightGapAssetsInView(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2239_ComplianceReportWithOnlyHighlightGapAssetsInView ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines_Static("false", getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC2243_ComplianceReportWithAssetBoxFeature
	 * Test Description: Generate compliance report for customer having license for Asset box feature 
	 *	- Customer has license for  Asset box and GIS layers
	 *	- Customer has GIS data
	 *	- Customer has survey with leaks and gaps, both intersecting with couple of assets 
	 * Script: -
	 *	- - On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- - Timezone : PST, Report Mode: Standard, Exclusion Radius: 0
	 *	- - Click Custom Boundary
	 *	- - Click Lat/Long Map Selector
	 *	- - Press the Shift key, then right-click and drag to select the desired area on the map and click OK
	 *	- - Select one or more surveys
	 *	- - Select non-default LISA color (E.g. Green)
	 *	- - Select Asset Boxes from Search Area Preference
	 *	- - Add View with base map value: Map, Satellite - Select all options in Views
	 *	- - Add View - Select only LISA and Highlight LISA Assets 
	 *	- - Add View - Select only Gaps and Highlight Gap Assets
	 *	- - Add View - Select only LISA, Highlight LISA Assets and Asset Box Number
	 *	- - Add View - Select only Gaps, Highlight Gap Assets and Asset Box Number
	 *	- - Select indication, Gap table in Optional tabular PDF content
	 *	- - Select Asset layers
	 *	- - Click OK
	 *	- - When report has completed, click Report Viewer Icon and download Compliance PDF, View PDFs, shape file and meta data files
	 * Results: -
	 *	- SSRS PDF should have all information about Views section, survey, date/time, etc.
	 *	- SSRS PDF should show Indication and Gap table entries and should match with data present in report view PDF
	 *	- Map PDFs should show information as per the selection
	 *	- Indication number should be present in circle and corresponding asset box number in square. When multiple asset boxes are present for single LISA then they should be displayed as "IndicationNumber-1", IndicationNumber-2 and so on
	 *	- LISA related asset box number will be displayed with color of indication disposition
	 *	- LISA asset box color should be same as LISA color (E.g. Green)
	 *	- Gap related asset box number should be present in red color same as Gaps and in square shape
	 *	- Shape ZIP should have Gap, Gap Box, LISA, LISA Box, Pipe All, PipeIntersectingLISA, PipeIntersectingGap, FOV, Breadcrumb shape files with data same as report view PDF
	 *	- Meta data ZIP folder should have Report.csv, ReportLISA.csv, ReportGap.csv and ReportSurvey.csv files. CSV files data should match with view data
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2243, location = ComplianceReportDataProvider.class)
	public void TC2243_ComplianceReportWithAssetBoxFeature(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2243_ComplianceReportWithAssetBoxFeature ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));

		ComplianceReportEntity rpt = (ComplianceReportEntity) complianceReportsPageAction.fillWorkingDataForReports(reportDataRowID1);
		complianceReportsPageAction.clickOnNewReportButton(EMPTY, reportDataRowID1);
		this.complianceReportsPageAction.getComplianceReportsPage().fillReport(rpt);
		this.complianceReportsPageAction.getComplianceReportsPage().selectLISAColor(ReportColorOption.RED);
		this.complianceReportsPageAction.getComplianceReportsPage().addReport();
		waitForReportGenerationToComplete(complianceReportsPageAction, reportDataRowID1);
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
	
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifySSRSImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifySSRSDrivingSurveyTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifySSRSViewsTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifySSRSLayersTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyLISAsIndicationTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyGapsTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyReportCreationInSSRSPDFIsCorrect(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifySSRSPDFFooter(EMPTY, getReportRowID(reportDataRowID1)));
		
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyPDFZipFilesAreCorrect(EMPTY, getReportRowID(reportDataRowID1)));
		
		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
		
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		String metadataZipFileVerifications = "True:True:True:False";  // "verifyGapMetaPresent=[TRUE]:verifyLisaMetaPresent=[TRUE]:verifySurveyMetaPresent=[TRUE]:verifyIsotopicMetaPresent=[FALSE]"
		assertTrue(complianceReportsPageAction.verifyMetaDataZIPFilesArePresent(metadataZipFileVerifications, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));

		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("2", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("2", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("3", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("3", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("4", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("4", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("5", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("5", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("5", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("5", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("6", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("6", getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines("FALSE", getReportRowID(reportDataRowID1)));
	}
}
