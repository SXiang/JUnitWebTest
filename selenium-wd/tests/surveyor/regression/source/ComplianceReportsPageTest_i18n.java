package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC;
import static surveyor.scommon.source.SurveyorConstants.SQACUS;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;

import org.junit.Before;

import common.source.Log;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.SurveyorConstants.UserLanguage;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LatLongSelectionControl;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.PreferencesPage;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.dataprovider.ComplianceReportDataProvider;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest_i18n extends BaseReportsPageActionTest {

	private static final String EMPTY = "";

	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static LatLongSelectionControl latLongSelectionControl = null;
	private static PreferencesPage preferencesPage;
	private static String i18nUserName,customerName = SQACUS, userPassword=USERPASSWORD, locationName=SQACUSLOC, userRole=SQACUSUA;
	
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
		
		if(i18nUserName == null){
			i18nUserName = addTestUser(customerName, userPassword, userRole, locationName);
		}
	}

	private void initializeTestPageObjects() {
		PageObjectFactory pageObjectFactory = new PageObjectFactory();
		LoginPage loginPage = pageObjectFactory.getLoginPage();
		setLoginPage(loginPage);
		PageFactory.initElements(getDriver(), loginPage);

		HomePage homePage = pageObjectFactory.getHomePage();
		setHomePage(homePage);
		PageFactory.initElements(getDriver(), homePage);

		preferencesPage = pageObjectFactory.getPreferencesPage();
		PageFactory.initElements(getDriver(), preferencesPage);

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
	 * Test Case ID: TC1380_I18NGenerateComplianceReportAndDownloadSSRSAndViews
	 * Test Description: Internationalization - Generate compliance report and download SSRS and report views
	 * Script: - In Preferences select foreign language
	 * - On the Compliance Reports page, click the New Compliance Report button
	 *	- Select Custom Boundary and click Lat Long Map Selector.
	 *	- Draw report area and click OK
	 *	- Fill out the required fields
	 *	- Once the report has completed, click on the Compliance Viewer button and download report PDF and report views PDF
	 * Results: 
	 *	- The New Compliance Report page should have field names displayed in the selected language
	 *	- The Lat/Long values should be correct, with 15 digits to the right of the decimal
	 *	- SSRS PDF should have all appropriate text (field names, column names, etc) in the selected language
	 *	- Map view footer should be translated in selected language
	 */
	@Ignore
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1380, location = ComplianceReportDataProvider.class)
	public void TC1380_I18NGenerateComplianceReportAndDownloadSSRSAndViews(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning  TC1380_I18NGenerateComplianceReportAndDownloadSSRSAndViews...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(i18nUserName+":"+userPassword, getUserRowID(userDataRowID));
		preferencesPage.open();
		preferencesPage.setSelectedCulture(UserLanguage.CHINESE);
		
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
        complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
        complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID1));

        assertTrue(complianceReportsPageAction.verifyPDFContainsInputtedInformation(ComplianceReportsPageActions.workingDataRow.get().timezone, getReportRowID(reportDataRowID1)));
        assertTrue(complianceReportsPageAction.verifySSRSViewsTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifySSRSImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
        assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines("FALSE", getReportRowID(reportDataRowID1)));

		
		
	}
	
	/**
	 * Test Case ID: TC1381_I18NGenerateComplianceReportUsingCopyAndDownloadSSRSAndViews
	 * Test Description: Internationalization - Generate compliance report using copy functionality and download SSRS and report views
	 * Script: 
	 *	- In Preferences select foreign language
	 *- On the Compliance Reports page, select a report that was generated in English and click the Copy button
	 *	- On the Copy Compliance Report page, change the Report Title as necessary and click OK
	 *	- Once the report has completed, click on the Compliance Viewer button and download report PDF and report views PDF
	 * Results: 
	 * - The Copy Compliance Report page should have field names displayed in the selected language
	 *	- The Lat Long values should be correct, with 15 digits to the right of the decimal
	 *	- SSRS PDF should have all appropriate text (field names, column names, etc) in the selected language
	 *	- Map view footer should be translated in selected language
	 */
	@Ignore
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1381, location = ComplianceReportDataProvider.class)
	public void TC1381_I18NGenerateComplianceReportUsingCopyAndDownloadSSRSAndViews(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning  TC1381_I18NGenerateComplianceReportUsingCopyAndDownloadSSRSAndViews...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
	}
	
	/**
	 * Test Case ID: TC1382_I18NReprocessSSRSReports
	 * Test Description: Internationalization - Reprocess SSRS reports
	 * Script: 
	 *	- Generate compliance report in English language
	 *	- Once report is generated successfully, in Preferences select foreign language for the user
	 *	- Log into PCubed as Picarro Admin
	 *	- On the Compliance Reports page, select a report that was generated in English and click the Reprocess button, then click the Reprocess Report button on the popup
	 *	- Once the report has completed, click on the Compliance Viewer button and download report PDF and report views PDF
	 * Results: 
	 *	- SSRS PDF should have all appropriate text (field names, column names, etc) in the selected language
	 *	- Map view footer should be translated in selected language
	 */
	@Ignore
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1382, location = ComplianceReportDataProvider.class)
	public void TC1382_I18NReprocessSSRSReports(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning  TC1382_I18NReprocessSSRSReports...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
	}
}
