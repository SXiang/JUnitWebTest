package surveyor.regression.source;

import static org.junit.Assert.*;

import java.util.function.Predicate;

import common.source.ExceptionUtility;
import common.source.FunctionUtil;
import common.source.Log;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.Test;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.actions.ReportCommonPageActions;
import surveyor.scommon.actions.data.AssessmentReportDataReader.AssessmentReportsDataRow;
import surveyor.scommon.entities.CustomerSurveyInfoEntity;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.actions.AssessmentReportsPageActions;
import surveyor.dataprovider.AssessmentReportDataProvider;
import surveyor.scommon.source.AssessmentReportsPage;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ReportsCommonPage;

@RunWith(SurveyorTestRunner.class)
public class AssessmentReportsWithNewSurveyPageTest extends BaseReportsPageActionTest {

	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;

	private static LoginPageActions loginPageAction;
	private static AssessmentReportsPageActions assessmentReportsPageAction;

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

	private AssessmentReportsPage getAssessmentReportsPage() {
		return (AssessmentReportsPage)getReportsPage();
	}

	private static void setPropertiesForTestRunMode() throws Exception {
		setTestRunMode(ReportTestRunMode.FullTestRun);

		if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
			assessmentReportsPageAction.fillWorkingDataForReports(getUnitTestReportRowID());
		}
	}

	/**
	 * Initializes the page action objects.
	 * @throws Exception
	 */
	protected static void initializePageActions() throws Exception {
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
		assessmentReportsPageAction = new AssessmentReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
		setReportsPage((AssessmentReportsPage)assessmentReportsPageAction.getPageObject());
	}

	/**
	 * Test Case ID: TC1434_PicarroAdminCanProvideAssessmentPrivilegeExistingCustomer_NewAssessmentReport
	 * Test Description: Picarro Admin can provide Assessment privilege for existing Customer - New Assessment Report
	 * Script: -
	 *	- - Login as Picarro Admin
	 *	- - Navigate Picarro Admin -& Manage Customer -& Edit
	 *	- - Provide all required details and select Assessment privilege check box
	 *	- - Click OK
	 *	- - Login as customer admin or supervisor user
	 *	- - Click on Report link
	 *	- - Generate Assessment report (Select all options in views section)
	 *	- - Click on Report Viewer
	 *	- - Download Shape file and meta data
	 * Results: -
	 *	- - Assessment report link should be present on left navigation panel
	 *	- - Report is generated successfully
	 *	- - SSRS PDF, AssessmentZIP (PDF), AssessmentZIP (Shape), AssessmentZIP (Meta), Views PDF should be generated successfully
	 *	- -The Shapefile zip should download. Shape files should be present for FOV, LISA, GAP, BreadCrumb, PipeAll, PipesIntersectingLISA and PipesIntersectionGAP
	 *	- - LISA shape file's attribute table should have Label (LISA 1, LISA 2, etc), Lat and Long Co-ordinates
	 *	- - Meta Data zip should download. Report.csv,ReportSurvey.csv,ReportIsotopic.csv,ReportLISAS.csv,ReportGap.csv files are present.
	 *	- - All the information present in ReportLISA.csv file ReportId, ReportName, LISAId, LISANumber,Surveyor,LISADate/Time,Amplitude,Concentration,Field Notes, IndicationCoordinates, LatCoord, LongCoordis correct and matches report PDF.Verify that unique LISA numbers in the format of XXXXXX-L#, where XXXXXX is the sequentially auto-incrementing Report ID and # is the sequential LISA number. All Lisa instances should be in Caps (Eg. LISANumber values shuold be LISA 1, LISA 2, etc.)Data present in ReportLisa.csv should be same as SSRS PDF indication table
	 */
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1434, location = AssessmentReportDataProvider.class)
	public void TC1434_PicarroAdminCanProvideAssessmentPrivilegeExistingCustomer_NewAssessmentReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2, CustomerSurveyInfoEntity surveyInfoEntity) throws Exception {
		Log.info("\nRunning TC1434_PicarroAdminCanProvideAssessmentPrivilegeExistingCustomer_NewAssessmentReport ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

		// Create new customer with survey + GIS data and then execute test steps.
		assertTrue(executeAsNewCustomerWithSurveyAndGISData(assessmentReportsPageAction, surveyInfoEntity, reportDataRowID1, pageAction -> {
			try {
				String usernameColonPassword = String.format("%s:%s", ManageUsersPageActions.workingDataRow.get().username, ManageUsersPageActions.workingDataRow.get().password);
				loginPageAction.open(EMPTY, NOTSET);
				loginPageAction.login(usernameColonPassword, NOTSET);   /* login using newly created user */

				assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
				createNewReport(pageAction, getReportRowID(reportDataRowID1));
				waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));

				assessmentReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
				assessmentReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
				assessmentReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
				assessmentReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
				assessmentReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
				assessmentReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
				assessmentReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
				assessmentReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
				assessmentReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
				assessmentReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID1));
				assessmentReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", getReportRowID(reportDataRowID1));

				assessmentReportsPageAction.extractShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
				assessmentReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID1));

				//Generate new shape files and Enable the step once DE3355 gets fixed
				//assertTrue(assessmentReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
				assertTrue(assessmentReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));
			} catch (Exception ex) {
				Log.error(String.format("Failure encountered in test. Exception -> %s", ExceptionUtility.getStackTraceString(ex)));
				return false;
			}
			return true;
		}));
	}

	/**
	 * Test Case ID: TC1435_Re_EnableAssessmentPrivilegeExistingCustomer_CopyAssessmentReport
	 * Test Description: Re-enable Assessment privilege for existing Customer - Copy Assessment Report
	 * Script: -
	 *	- - Login as Picarro Admin
	 *	- - Navigate Picarro Admin -& Manage Customer -& Edit
	 *	- - Select Assessment privilege check box
	 *	- - Click OK
	 *	- - Login as customer admin or supervisor user
	 *	- - Click on Report link
	 *	- - Generate Assessment report using Copy functionality or customer boundary (e.g. Click on Copy button of 'PGE Assessment Report' generated above)
	 *	- - Modify few details and generate report
	 *	- - Click on Report Viewer
	 *	- - Download SSRS PDF, Assessment PDF, ShapeFile and MetaData ZIP
	 * Results: -
	 *	- - Assessment report link should be present on left navigation panel
	 *	- - CopyAssessmentReport screen should have same details that were present while generating report. Report title, report area, surveys, colors selected, viewcombinations, etc
	 *	- - Report is generated successfully
	 *	- - SSRS PDF,AssessmentZIP (PDF),AssessmentZIP (Shape),AssessmentZIP (Meta), Views PDF should be generated successfully
	 *	-
	 *	- -The Shapefile zip should download. Shape files should be present for FOV, LISA, GAP, BreadCrumb, PipeAll, PipesIntersectingLISA and PipesIntersectionGAP
	 *	- - LISA shape file's attribute table should have Label (LISA 1, LISA 2, etc), Lat and Long Co-ordinates
	 *	- - Meta Data zip should download. Report.csv,ReportSurvey.csv,ReportIsotopic.csv,ReportLISAS.csv,ReportGap.csv files are present.
	 *	- - All the information present in ReportLISA.csv file ReportId, ReportName, LISAId, LISANumber,Surveyor,LISADate/Time,Amplitude,Concentration,Field Notes, IndicationCoordinates, LatCoord, LongCoordis correct and matches report PDF.Verify that unique LISA numbers in the format of XXXXXX-L#, where XXXXXX is the sequentially auto-incrementing Report ID and # is the sequential LISA number. All Lisa instances should be in Caps (Eg. LISANumber values shuold be LISA 1, LISA 2, etc.)Data present in ReportLisa.csv should be same as SSRS PDF indication table
	 */
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1435, location = AssessmentReportDataProvider.class)
	public void TC1435_Re_EnableAssessmentPrivilegeExistingCustomer_CopyAssessmentReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2, CustomerSurveyInfoEntity surveyInfoEntity) throws Exception {
		Log.info("\nRunning TC1435_Re_EnableAssessmentPrivilegeExistingCustomer_CopyAssessmentReport ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		// Create new customer with survey + GIS data and then execute test steps.
		assertTrue(executeAsNewCustomerWithSurveyAndGISData(assessmentReportsPageAction, surveyInfoEntity, reportDataRowID1, pageAction -> {
			try {
				String usernameColonPassword = String.format("%s:%s", ManageUsersPageActions.workingDataRow.get().username, ManageUsersPageActions.workingDataRow.get().password);
				loginPageAction.open(EMPTY, NOTSET);
				loginPageAction.login(usernameColonPassword, NOTSET);   /* login using newly created user */

				assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
				createNewReport(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
				waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
				assessmentReportsPageAction.copyReport(AssessmentReportsPageActions.workingDataRow.get().title, getReportRowID(reportDataRowID1));
				modifyReport(assessmentReportsPageAction, getReportRowID(reportDataRowID2));
				waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID2));

				assessmentReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID2));
				assessmentReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID2));
				assessmentReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID2));
				assessmentReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID2));
				assessmentReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID2));
				assessmentReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID2));
				assessmentReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID2));
				assessmentReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID2));
				assessmentReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID2));
				assessmentReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID2));
				assessmentReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", getReportRowID(reportDataRowID2));

				assessmentReportsPageAction.extractShapeZIP(EMPTY, getReportRowID(reportDataRowID2));
				assessmentReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID2));

				//Generate new shape files and Enable the step once DE3355 gets fixed
				//assertTrue(assessmentReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID2)));
				assertTrue(assessmentReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID2)));
			} catch (Exception ex) {
				Log.error(String.format("Failure encountered in test. Exception -> %s", ExceptionUtility.getStackTraceString(ex)));
				return false;
			}
			return true;
		}));
	}

	/**
	 * Test Case ID: TC1588_AssessmentReport_RemoveLISABoxOptionFromExistingCustomer
	 * Test Description: Assessment Report - Remove LISA Box option from existing customer
	 * Script: -
	 *	- - Log into PCubed as Picarro Admin
	 *	- - Navigate to Picarro Administration -& Manage Customers page
	 *	- - Select a customer and click the Edit button
	 *	- - Uncheck LISA Box 1.0 checkbox, check Report Shape File checkbox and Assessment checkbox and click OK
	 *	- - Navigate to Reports -& Assessment Reports and click on the New Assessment Report button
	 *	- - Fill out the necessary fields and select one or more options in the Views section.
	 *	- - Click OK
	 *	- - Once the report has completed generation, click on theReport Viewbutton and the Shape File button
	 *	- - Run the Shape Files through GIS software like ArcGIS
	 * Results: -
	 *	- - The LISA shapes drawn by the GIS software should be in the shapes of fans or circles, not boxes
	 *	-
	 *	- - LISA shape file's attribute table should have Label (LISA 1, LISA 2, etc), Lat and Long Co-ordinates
	 *	-
	 */
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1588, location = AssessmentReportDataProvider.class)
	public void TC1588_AssessmentReport_RemoveLISABoxOptionFromExistingCustomer(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2, CustomerSurveyInfoEntity surveyInfoEntity) throws Exception {
		Log.info("\nRunning TC1588_AssessmentReport_RemoveLISABoxOptionFromExistingCustomer ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

		// Create new customer with survey + GIS data and then execute test steps.
		assertTrue(executeAsNewCustomerWithSurveyAndGISData(assessmentReportsPageAction, surveyInfoEntity, reportDataRowID1, pageAction -> {
			try {
				String usernameColonPassword = String.format("%s:%s", ManageUsersPageActions.workingDataRow.get().username, ManageUsersPageActions.workingDataRow.get().password);
				loginPageAction.open(EMPTY, NOTSET);
				loginPageAction.login(usernameColonPassword, NOTSET);   /* login using newly created user */

				assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
				createNewReport(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
				waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));

				assessmentReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
				assessmentReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
				assessmentReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));

				assessmentReportsPageAction.extractShapeZIP(EMPTY, getReportRowID(reportDataRowID2));

				//Generate new shape files and Enable the step once DE3355 gets fixed
				//assertTrue(assessmentReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID2)));
			} catch (Exception ex) {
				Log.error(String.format("Failure encountered in test. Exception -> %s", ExceptionUtility.getStackTraceString(ex)));
				return false;
			}
			return true;
		}));
	}

	/**
	 * Test Case ID: TC1594_AssessmentReport_CopyLISABoxReportClassicLISAReport
	 * Test Description: Assessment Report - Copy LISA Box report as Classic LISA report
	 * Script: -
	 *	- - Log into PCubed as Picarro Admin
	 *	- - Navigate to Picarro Administration -& Manage Customers page
	 *	- - Select a customer that does has LISA Box 1.0 enabled and click the Edit button
	 *	- - On the Edit Customer page, uncheck the LISA Box 1.0 box and make sure that the Report Shape File and Assessment boxes are checked and click OK
	 *	- -Navigate to Reports -& Assessment Reports and select a report for the above customer that has LISA Box shapes
	 *	- - Click the Copy button
	 *	- - Without changing any details, click OK
	 *	- - Once the report has completed generation, click on the Report View button and then click on the Shape File button
	 *	- - Run the Shape Files through GIS software like ArcGIS
	 * Results: -
	 *	- - The GIS software should draw all LISAs in the shape of fans or circles, not boxes
	 *	-
	 *	- - LISA shape file's attribute table should have Label (LISA 1, LISA 2, etc), Lat and Long Co-ordinates
	 *	-
	 */
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1594, location = AssessmentReportDataProvider.class)
	public void TC1594_AssessmentReport_CopyLISABoxReportClassicLISAReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2, CustomerSurveyInfoEntity surveyInfoEntity) throws Exception {
		Log.info("\nRunning TC1594_AssessmentReport_CopyLISABoxReportClassicLISAReport ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

		// Create new customer with survey + GIS data and then execute test steps.
		assertTrue(executeAsNewCustomerWithSurveyAndGISData(assessmentReportsPageAction, surveyInfoEntity, reportDataRowID1, pageAction -> {
			String reportTitleBeforeCopy = null;
			Boolean testFailed = false;
			try {
				String usernameColonPassword = String.format("%s:%s", ManageUsersPageActions.workingDataRow.get().username, ManageUsersPageActions.workingDataRow.get().password);
				loginPageAction.open(EMPTY, NOTSET);
				loginPageAction.login(usernameColonPassword, NOTSET);   /* login using newly created user */

				assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
				createNewReport(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
				waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
				reportTitleBeforeCopy = AssessmentReportsPageActions.workingDataRow.get().title;
				assessmentReportsPageAction.copyReport(AssessmentReportsPageActions.workingDataRow.get().title, getReportRowID(reportDataRowID1));
				modifyReport(assessmentReportsPageAction, getReportRowID(reportDataRowID2));
				waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID2));

				AssessmentReportsPageActions.workingDataRow.set((AssessmentReportsDataRow) assessmentReportsPageAction.getWorkingReportsDataRow());

				assessmentReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID2));
				assessmentReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID2));
				assessmentReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID2));
				assessmentReportsPageAction.extractShapeZIP(EMPTY, getReportRowID(reportDataRowID2));

				//Generate new shape files and Enable the step once DE3355 gets fixed
				//assertTrue(assessmentReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID2)));
			} catch (Exception ex) {
				testFailed = true;
				Log.error(String.format("Failure encountered in test. Exception -> %s", ExceptionUtility.getStackTraceString(ex)));
				return false;
			} finally {
				// BaseTest will delete the current workingDataRow report. Remove the reportBeforeCopy explicitly.
				if (!testFailed) {
					if (reportTitleBeforeCopy != null) {
						String workingReportTitle = AssessmentReportsPageActions.workingDataRow.get().title;
						AssessmentReportsPageActions.workingDataRow.get().title = reportTitleBeforeCopy;
						try {
							assessmentReportsPageAction.deleteReport(EMPTY, getReportRowID(reportDataRowID1));
						} catch (Exception e) {
							Log.error(ExceptionUtility.getStackTraceString(e));
							return false;
						} finally {
							AssessmentReportsPageActions.workingDataRow.get().title = workingReportTitle;
						}
					}
				}
			}

			return true;
		}));
	}
}