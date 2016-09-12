package surveyor.regression.source;

import common.source.Log;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.Test;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest4 extends BaseReportsPageActionTest {
	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;
	
	private static HomePageActions homePageAction;
	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static TestEnvironmentActions testEnvironmentAction;
	
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
		homePageAction = new HomePageActions(getDriver(), getBaseURL(), getTestSetup());
		complianceReportsPageAction = new ComplianceReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
		setReportsPage((ComplianceReportsPage)complianceReportsPageAction.getPageObject());
		testEnvironmentAction = new TestEnvironmentActions();
	}

	/**
	 * Test Case ID: TC153_CopyModifyComplianceReportFromPreviouslyRunReports
	 * Script: -  	
	 *	- - Login as picarro admin
	 *	- - On Home Page, click Reports -& Compliance 
	 *	- - Click on 'Copy' button 
	 *  - - Modify few details and generate report
	 *  - - Base Map Value as None 
	 *	- - Click on OK and click ZIP (PDF) icon
	 * Results: - 
	 *	- - Copy Compliance Report screen should have same details that were present while generating report. Report title, report area, surveys, colors selected, view combinations, etc
	 *	- - Report with specified modification is generated successfully
	 */
	@Test
	public void TC153_CopyModifyComplianceReportFromPreviouslyRunReports() throws Exception {
		Log.info("\nRunning TC153_CopyModifyComplianceReportFromPreviouslyRunReports ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, NOTSET);
		createNewComplianceReport(complianceReportsPageAction, 4);
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, 4);
		complianceReportsPageAction.copyReport(ComplianceReportsPageActions.workingDataRow.get().title, NOTSET);
		complianceReportsPageAction.verifyReportPageFieldsAreCorrect(EMPTY, 4);
		modifyComplianceReport(complianceReportsPageAction, 5);
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, 5);
		complianceReportsPageAction.verifyReportFilesArePresent(EMPTY, 5);
	}
 
	/**
	 * Test Case ID: TC180_GenerateManualReportFromExistingReportsHavingSurveysOfStandardOrRapidResponseTypesUsingCopyFeature
	 * Script: -  	
	 *	- - Generate Standard report (include Standard survey) 
	 *	- - Click on copy button present next to the generated report 
	 *  - - Change the report mode to Manual and provide min amplitude value 
	 *  - - Click on OK button
	 * Results: - 
	 *	- - UI should remove surveys other than manual and only manual surveys shows up in the filter
	 *	- - Manual Report should have only manual surveys.
	 */
	@Test
	public void TC180_GenerateManualReportFromExistingReportsHavingSurveysOfStandardOrRapidResponseTypesUsingCopyFeature() throws Exception {
		Log.info("\nRunning TC180_GenerateManualReportFromExistingReportsHavingSurveysOfStandardOrRapidResponseTypesUsingCopyFeature ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, NOTSET);
		createNewComplianceReport(complianceReportsPageAction, 6);
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, 6);
		complianceReportsPageAction.copyReport(ComplianceReportsPageActions.workingDataRow.get().title, NOTSET);
		complianceReportsPageAction.selectReportMode("Manual", 7);
		complianceReportsPageAction.clickOnSurveySelectorSearchButton(EMPTY, 7);
		complianceReportsPageAction.verifySearchedSurveysMatchSelectedMode(EMPTY, 7);
		modifyComplianceReport(complianceReportsPageAction, 7);		
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, 7);
		complianceReportsPageAction.verifyReportFilesArePresent(EMPTY, 7);
	}
 
	/**
	 * Test Case ID: TC182_GenerateStandardReportFromExistingReportsHavingSurveyOfRapidResponseTypeUsingCopyFeature
	 * Script: -  	
	 *	- - Generate Rapid Response report (include Rapid Response survey) 
	 *	- - Click on copy button present next to the generated report 
	 *  - - Change the report mode to Standard
	 *  - - Click on OK button
	 * Results: - 
	 *	- - UI should not allow user to generate the Standard report if that report contains Rapid Response Surveys
	 *	- - Standard report can have Standard and Operator Surveys
	 */
	@Test
	public void TC182_GenerateStandardReportFromExistingReportsHavingSurveyOfRapidResponseTypeUsingCopyFeature() throws Exception {
		Log.info("\nRunning TC182_GenerateStandardReportFromExistingReportsHavingSurveyOfRapidResponseTypeUsingCopyFeature ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, NOTSET);
		createNewComplianceReport(complianceReportsPageAction, 8);
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, 8);
		complianceReportsPageAction.copyReport(ComplianceReportsPageActions.workingDataRow.get().title, NOTSET);
		complianceReportsPageAction.selectReportMode("Standard", 7);
		complianceReportsPageAction.clickOnSurveySelectorSearchButton(EMPTY, 7);
		complianceReportsPageAction.verifySearchedSurveysMatchSelectedMode(EMPTY, 7);
		modifyComplianceReport(complianceReportsPageAction, 9);
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, 9);
		complianceReportsPageAction.verifyReportFilesArePresent(EMPTY, 9);
	}

	/**
	 * Test Case ID: TC165_SortReportListBasedOnCompletionDateAndOtherAttributes
	 * Description: Sort report list based on completion date and other attributes
	 * Script:
	 * 		- Sort report list by report title or created by or date attributes present on all reports screen 
	 * Results:
	 * 		- User is able to sort the list of reports based on specified attribute
	 *		- Sorting by Report Name not allowed
	 */
	@Ignore
	public void TC165_SortReportListBasedOnCompletionDateAndOtherAttributes() throws Exception {
		Log.info("\nRunning TC165_SortReportListBasedOnCompletionDateAndOtherAttributes ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, NOTSET);

		assertTrue(getComplianceReportsPage().isReportColumnSorted("Report Title","String"));
		assertTrue(getComplianceReportsPage().isReportColumnSorted("Created By","String"));
		assertTrue(getComplianceReportsPage().isReportColumnSorted("Date","Date"));
		assertFalse(getComplianceReportsPage().isReportColumnSorted("Report Name","String"));
	}
	
	/**
	 * Test Case ID: TC175_GenerateReportAsPicarroAdminUserForThesurveyDoneByAnyCustomerUser
	 * Description: Generate report as Picarro Admin user for the survey done by any of the customer user
	 * Script:
	 * 		- Login as Picarro Administrator
	 * 		- On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 * 		- Select the customer other then Picarro from the drop down
	 * 		- Check the Surveyor dropdown in the Survey Selector section
	 * 		- Select all tables, assets, boundaries boxes
	 * 		- Select everything in views section
	 * 		- Add survey done by customer's user
	 * 		- Select "Coverage Percentage of the assets" check box
	 * Results:
	 * 		- Verify that the Surveyor units in the Survey Selector section belong to selected customer
	 * 		- Report is generated successfully for specified customer survey and Assets of specified customer are present in maps
	 * 		- Percent Coverage value is present in SSRS PDF
	 */
	@Ignore
	public void TC175_GenerateReportAsPicarroAdminUserForThesurveyDoneByAnyCustomerUser() throws Exception {
		Log.info("\nRunning TC175_GenerateReportAsPicarroAdminUserForThesurveyDoneByAnyCustomerUser ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, NOTSET);
	}
	
	/**
	 * Test Case ID: TC183_GenerateReportHavingMultiplesurveysOfStandardOperatorAndRepidResponseTypesInRapidResponseReportMode
	 * Description: Generate report having multiple surveys of Standard, Operator and Rapid Response types in Rapid Response report mode
	 * Script:
	 * 		- Generate report in Rapid Response report mode
	 * 		- Select Standard, Rapid Response and Operator Survey from differnet pages of the Surveys list and click on Add Survey button
	 * Results:
	 * 		- All surveys selected from different pages should be included
	 * 		- Report shoud be generated successfully showing information of all selected surveys
	 */
	@Ignore
	public void TC183_GenerateReportHavingMultiplesurveysOfStandardOperatorAndRepidResponseTypesInRapidResponseReportMode() throws Exception {
		Log.info("\nRunning TC183_GenerateReportHavingMultiplesurveysOfStandardOperatorAndRepidResponseTypesInRapidResponseReportMode ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, NOTSET);
	}	
	
	/**
	 * Test Case ID: TC191_GenerateReportHavingMultipleSurveysAndVerifyGapsForThem
	 * Description: Generate report having multiple surveys and verify Gaps for them
	 * Script:
	 * 		- Include Surveys mostly of the same area
	 * 		- Give FoV other then blue and different for all included surveys
	 * 		- Add View and select FoV and Gaps
	 * Results:
	 * 		- Report should be generated successfully and Gaps should be present according to the surveys included
	 */
	@Ignore
	public void TC191_GenerateReportHavingMultipleSurveysAndVerifyGapsForThem() throws Exception {
		Log.info("\nRunning TC191_GenerateReportHavingMultipleSurveysAndVerifyGapsForThem ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, NOTSET);
	}
	
	private ComplianceReportsPage getComplianceReportsPage() {
		return (ComplianceReportsPage)getReportsPage();
	}
}