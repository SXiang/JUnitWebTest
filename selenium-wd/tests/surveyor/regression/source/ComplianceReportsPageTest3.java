package surveyor.regression.source;

import common.source.Log;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.Test;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.ComplianceReportsPage;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest3 extends BaseReportsPageTest {

	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;
	
	private static HomePageActions homePageAction;
	private static LoginPageActions loginPageAction;
	private static TestEnvironmentActions testEnvironmentAction;
	
	private static ComplianceReportsPage complianceReportsPage;
	
	@BeforeClass
	public static void beforeTestClass() throws Exception {
		initializePageActions();
	}

	/**
	 * Initializes the page action objects.
	 */
	protected static void initializePageActions() {
		loginPageAction = new LoginPageActions(driver, baseURL, testSetup);
		homePageAction = new HomePageActions(driver, baseURL, testSetup);
		testEnvironmentAction = new TestEnvironmentActions();
	}

	/**
	 * Test Case ID: TC1319_GenerateComplianceReportPicarroSupportUserIncludePercentCoverageForecast
	 * Script: -  	
	 *	- - Log in as Picarro Support user
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button 
	 *	- - Select 2 or 3 surveys with different tag values
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)- Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Test
	public void TC1319_GenerateComplianceReportPicarroSupportUserIncludePercentCoverageForecast() throws Exception {
		Log.info("\nRunning TC1319_GenerateComplianceReportPicarroSupportUserIncludePercentCoverageForecast ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 5);	 /* Picarro Support */

	}
 
	/**
	 * Test Case ID: TC1320_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecast2SurveysDifferentTags
	 * Script: -  	
	 *	- - Log in as Customer Admin user (Eg. PG&amp;E Util Admin)
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button 
	 *	- - Add 2 Surveys (present in the selected plat) with different tag values (try to include approx 8 hours surveys)
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 * Results: - 
	 *	- - Report generated successfully- Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)- Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Test
	public void TC1320_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecast2SurveysDifferentTags() throws Exception {
		Log.info("\nRunning TC1320_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecast2SurveysDifferentTags ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 1);   /* Customer Admin */

	}
 
	/**
	 * Test Case ID: TC1339_CheckErrorMesageDisplayedIfPercentCoverageForecastCheckBoxSelected
	 * Script: -  	
	 *	- - Add one survey
	 *	- - Add 2 or 3 surveys with same tag value
	 *	- - Add 2 or 3 surveys with different tag value
	 * Results: - 
	 *	- - Error message will not be displayed to user when different tag value surveys are included
	 */
	@Test
	public void TC1339_CheckErrorMesageDisplayedIfPercentCoverageForecastCheckBoxSelected() throws Exception {
		Log.info("\nRunning TC1339_CheckErrorMesageDisplayedIfPercentCoverageForecastCheckBoxSelected ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	}
 
	/**
	 * Test Case ID: TC1340_CheckErrorMesageDisplayedIfPercentCoverageForecastCheckBoxSelected
	 * Script: -  	
	 *	- - Add 2 or 3 survey with same tag value
	 *	- - Add 2 or 3 surveys with different tag value
	 * Results: - 
	 *	- - Error message will not be displayed to user when different tag value surveys are included
	 */
	@Test
	public void TC1340_CheckErrorMesageDisplayedIfPercentCoverageForecastCheckBoxSelected() throws Exception {
		Log.info("\nRunning TC1340_CheckErrorMesageDisplayedIfPercentCoverageForecastCheckBoxSelected ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}
 
	/**
	 * Test Case ID: TC1351_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecast3SurveysDifferentTags
	 * Script: -  	
	 *	- - Log in as Customer Admin user (Eg. PG&amp;E Util Admin)
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button 
	 *	- - Add 3 Surveys (present in the selected plat) with different tag values
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 * Results: - 
	 *	- - Report generated successfully
	 */
	@Test
	public void TC1351_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecast3SurveysDifferentTags() throws Exception {
		Log.info("\nRunning TC1351_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecast3SurveysDifferentTags ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 1);   /* Customer Admin */

	}
 
	/**
	 * Test Case ID: TC1352_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecast4OrMoreSurveysDifferentTags
	 * Script: -  	
	 *	- - Log in as Customer Admin user (Eg. PG&amp;E Util Admin)
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button 
	 *	- - Add 4 or more Surveys (present in the selected plat) with different tag values
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - Prediction table will not show any records as 4 or more surveys with different tag values are not supported.
	 *	- - No Coverage Forecast Available message is displayed
	 */
	@Test
	public void TC1352_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecast4OrMoreSurveysDifferentTags() throws Exception {
		Log.info("\nRunning TC1352_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecast4OrMoreSurveysDifferentTags ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 1);   /* Customer Admin */

	}
 
	/**
	 * Test Case ID: TC1363_GenerateComplianceReportCustomerAdminUsingCopyFunctionalityIncludePercentCoverageForecast2SurveysDifferentTags
	 * Script: -  	
	 *	- - Log in as Customer Admin user (Eg. PG&amp;E Util Admin)
	 *	- - Click on Copy button of above generated report
	 *	- - Click on OK and click Compliance Viewer button
	 *	- - Report generated successfully
	 *	- - Values should match with above report values
	 * Results: - 
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)- Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Test
	public void TC1363_GenerateComplianceReportCustomerAdminUsingCopyFunctionalityIncludePercentCoverageForecast2SurveysDifferentTags() throws Exception {
		Log.info("\nRunning TC1363_GenerateComplianceReportCustomerAdminUsingCopyFunctionalityIncludePercentCoverageForecast2SurveysDifferentTags ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 1);   /* Customer Admin */

	}
 
	/**
	 * Test Case ID: TC1364_GenerateComplianceReportPicarroSupportUserUsingReprocessFunctionalityIncludePercentCoverageForecast3SurveysDifferentTags
	 * Script: -  	
	 *	- - Log in as Picarro Support user
	 *	- - Click on Reprocess button of above generated report
	 *	- - Report generated successfully
	 *	- - Values should match with above report values
	 * Results: - 
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)- Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Test
	public void TC1364_GenerateComplianceReportPicarroSupportUserUsingReprocessFunctionalityIncludePercentCoverageForecast3SurveysDifferentTags() throws Exception {
		Log.info("\nRunning TC1364_GenerateComplianceReportPicarroSupportUserUsingReprocessFunctionalityIncludePercentCoverageForecast3SurveysDifferentTags ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 5);   /* Customer Support */

	}
 
	/**
	 * Test Case ID: TC1365_GenerateComplianceReportPicarroAdminUsingCopyFunctionalityIncludePercentCoverageForecast3SurveysDifferentTags
	 * Script: -  	
	 *	- - Log in as Picarro Admin user
	 *	- - Click on Copy button of above generated report
	 *	- - Click on OK and click Compliance Viewer button
	 *	- - Report generated successfully
	 *	- - Values should match with above report values
	 * Results: - 
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)- Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Test
	public void TC1365_GenerateComplianceReportPicarroAdminUsingCopyFunctionalityIncludePercentCoverageForecast3SurveysDifferentTags() throws Exception {
		Log.info("\nRunning TC1365_GenerateComplianceReportPicarroAdminUsingCopyFunctionalityIncludePercentCoverageForecast3SurveysDifferentTags ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}
 
	/**
	 * Test Case ID: TC1366_GenerateComplianceReportPicarroAdminUsingReprocessFunctionalityIncludePercentCoverageForecast2SurveysDifferentTags
	 * Script: -  	
	 *	- - Log in as Picarro Admin user
	 *	- - Click on Reprocess button of above generated report
	 *	- - Report generated successfully
	 *	- - Values should match with above report values
	 * Results: - 
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)- Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Test
	public void TC1366_GenerateComplianceReportPicarroAdminUsingReprocessFunctionalityIncludePercentCoverageForecast2SurveysDifferentTags() throws Exception {
		Log.info("\nRunning TC1366_GenerateComplianceReportPicarroAdminUsingReprocessFunctionalityIncludePercentCoverageForecast2SurveysDifferentTags ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}
 
	/**
	 * Test Case ID: TC1367_GenerateComplianceReportCustomerSupervisorUserUsingCopyFunctionalityIncludePercentCoverageForecast3SurveysDifferentTags
	 * Script: -  	
	 *	- - Log in as Customer Supervisor user (Eg. PG&amp;E Supervisor)
	 *	- - Click on Copy button of above generated report
	 *	- - Click on OK and click Compliance Viewer button
	 *	- - Report generated successfully
	 *	- - Values should match with above report values
	 * Results: - 
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)- Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Test
	public void TC1367_GenerateComplianceReportCustomerSupervisorUserUsingCopyFunctionalityIncludePercentCoverageForecast3SurveysDifferentTags() throws Exception {
		Log.info("\nRunning TC1367_GenerateComplianceReportCustomerSupervisorUserUsingCopyFunctionalityIncludePercentCoverageForecast3SurveysDifferentTags ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 2);   /* Customer Supervisor */

	}
 
	/**
	 * Test Case ID: TC1370_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecastMultipleSurveys
	 * Script: -  	
	 *	- - Log in as Customer Admin user (Eg. PG&amp;E Util Admin)
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button 
	 *	- - Add 4 Surveys (present in the selected plat) with different tag values (try to include approx 8 hours surveys)
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 * Results: - 
	 *	- - Report generated successfully- Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)- Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Test
	public void TC1370_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecastMultipleSurveys() throws Exception {
		Log.info("\nRunning TC1370_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecastMultipleSurveys ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 1);   /* Customer Admin */

	}
 
	/**
	 * Test Case ID: TC1371_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecastMultipleSurveys
	 * Script: -  	
	 *	- - Log in as Picarro Admin user
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button 
	 *	- - Add 4 Surveys (present in the selected plat) with different tag values (try to include approx 8 hours surveys)
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 * Results: - 
	 *	- - Report generated successfully- Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)- Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Test
	public void TC1371_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecastMultipleSurveys() throws Exception {
		Log.info("\nRunning TC1371_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecastMultipleSurveys ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}
 
	/**
	 * Test Case ID: TC1373_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecastAssetsReportArea
	 * Script: -  	
	 *	- - Log in as Picarro Admin
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button 
	 *	- - Add 2 or 3 surveys with different tag values
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - Percent Coverage Assets and Report Area value is displayed
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)- Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Test
	public void TC1373_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecastAssetsReportArea() throws Exception {
		Log.info("\nRunning TC1373_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecastAssetsReportArea ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}
 
	/**
	 * Test Case ID: TC1389_MetadataExport_CSVFileMultipleSurvey_MultipleLisasISO
	 * Script: -  	
	 *	- 2. Open report.csv file.
	 *	- 3. Open survey.csv file.
	 *	- 4.Open isoCapture.csv file.
	 *	- 5. Open LISA.csv file.
	 * Results: - 
	 *	- 6. Open gap.csv file.
	 */
	@Test
	public void TC1389_MetadataExport_CSVFileMultipleSurvey_MultipleLisasISO() throws Exception {
		Log.info("\nRunning TC1389_MetadataExport_CSVFileMultipleSurvey_MultipleLisasISO ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}
 
	/**
	 * Test Case ID: TC1394_CopyButtonPresentIn_ProgressComplianceReportCustomerSupervisorUser
	 * Script: -  	
	 *	- - Log in as Customer Supervisor user (eg. PGE or CNP or Atmos)
	 *	- - Generate a new compliance report
	 *	- - Click on Copy button when report is in in-progress state
	 * Results: - 
	 *	- - Report will be in in-progress state and user can see Copy and Cancel Report buttons
	 *	- - Report is generated successfully
	 */
	@Test
	public void TC1394_CopyButtonPresentIn_ProgressComplianceReportCustomerSupervisorUser() throws Exception {
		Log.info("\nRunning TC1394_CopyButtonPresentIn_ProgressComplianceReportCustomerSupervisorUser ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 2);   /* Picarro Admin */

	}
 
	/**
	 * Test Case ID: TC1395_CopyButtonPresentIn_ProgressComplianceReportCustomerAdminUser
	 * Script: -  	
	 *	- - Log in as Customer admin user (eg. PGE or CNP or Atmos)
	 *	- - Generate a new compliance report
	 *	- - Click on Copy button when report is in in-progress state
	 *	- - Report will be in in-progress state and user can see Copy and Cancel Report buttons
	 * Results: - 
	 *	- - Report should be generated successfully and user is able to download the PDFs
	 */
	@Test
	public void TC1395_CopyButtonPresentIn_ProgressComplianceReportCustomerAdminUser() throws Exception {
		Log.info("\nRunning TC1395_CopyButtonPresentIn_ProgressComplianceReportCustomerAdminUser ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 1);   /* Customer Admin */

	}
 
	/**
	 * Test Case ID: TC12_ReportViewThumbnailsCustomBoundarySingleView
	 * Script: -  	
	 * Results: - 
	 *	- - User is navigated back to Compliance Reports page and after the report has been generated, Compliance Viewer button is present in the Action column for that report
	 */
	@Test
	public void TC12_ReportViewThumbnailsCustomBoundarySingleView() throws Exception {
		Log.info("\nRunning TC12_ReportViewThumbnailsCustomBoundarySingleView ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}

	/**
	 * Test Case ID: TC13_ReportViewThumbnailsCustomBoundaryMultipleViews
	 * Script: -  	
	 * Results: - 
	 *	- - On Compliance Reports page, click New Compliance Report button  - Select Custom Boundary, click the Lat/Long Selector button and select an area   - Set View Size values to 20 x 20 inches  - Fill out all other required fields  - For View1, under View section, select LISAs, FOV, Breadcrumb, Indications, Base Map = Map  - For View2, select LISAs, Indications, Field Notes, Gaps, Base Map = Satellite  - For View3, select FOV, Indications, Isotopic Analysis, Assets, Boundaries, Base Map = Map  - Click OK   - After the report has been generated,click on the Compliance Viewer button and then on the thumbnail for View 1  - Repeat for Views 2 and 3  - Navigate away from Compliance Reports page and return
	 */
	@Test
	public void TC13_ReportViewThumbnailsCustomBoundaryMultipleViews() throws Exception {
		Log.info("\nRunning TC13_ReportViewThumbnailsCustomBoundaryMultipleViews ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}

	/**
	 * Test Case ID: TC14_ReportViewThumbnailsCustomerBoundarySingleView
	 * Script: -  	
	 *	- - On Compliance Reports page, click New Compliance Report button  - Select Customer Boundary and select an area from the tree  - Fill out all required fields  - Under View section, select LISAs, FOV, Breadcrumb, Indications, Base Map = Map  - Click OK  - After the report has been generated,click on the Compliance Viewer button and then on a thumbnail  - Navigate away from Compliance Reports page and return 
	 * Results: - 
	 *	- - User is navigated back to Compliance Reports page and after the report has been generated,Compliance Viewer button is present in the Action column for that report  -Thumbnails will be present for compliance SSRS PDF, ZIP folders and generated view  - The thumbnail should still be present
	 */
	@Test
	public void TC14_ReportViewThumbnailsCustomerBoundarySingleView() throws Exception {
		Log.info("\nRunning TC14_ReportViewThumbnailsCustomerBoundarySingleView ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}
 
	/**
	 * Test Case ID: TC149_GenerateComplianceReportUsingSurveyorUnitTagFiltersMoreThanOneViewDownloadReport
	 * Script: -  	
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button 
	 *	- - Add By Surveyor Unit and tag filter 
	 *	- - Add View 1: select Isocapture, Annotations, Assets and Boundaries. Base Map Value : Map 
	 *	- - Add View 2: select LISA and FOV. Base Map Value : Satellite 
	 *	- - Add View 3: select Indications, breadcrum and annotations. Base Map: Map 
	 *	- - Click on OK and click Download icon
	 * Results: - 
	 *	- - Report generated successfully having specified timezone and asset data for specified tag id and date range surveys
	 *	- - User is allowed to download the report
	 *	- - View names and option selections on SSRS should match those in UI when creating report
	 *	- - Export image should show the map for the specified Lat-Long boundary
	 */
	@Test
	public void TC149_GenerateComplianceReportUsingSurveyorUnitTagFiltersMoreThanOneViewDownloadReport() throws Exception {
		Log.info("\nRunning TC149_GenerateComplianceReportUsingSurveyorUnitTagFiltersMoreThanOneViewDownloadReport ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}
 
	/**
	 * Test Case ID: TC1490_CreateNewCustomerLISABoxOption
	 * Script: -  	
	 *	- - Navigate to Picarro Administration -& Manage Customers page
	 *	- - On the Start Survey popup, fill out the details, select Standard mode and click Start a Survey
	 *	- - Log into PCubed, navigate to Reports -& Compliance Reports and click on the New Compliance Report button
	 *	- - Once the report has completed generation, click on the Compliance Viewer button and then the View thumbnail
	 *	- - Click on the Shape File button
	 *	- - Run the Shape Files through GIS software like ArcGIS
	 * Results: - 
	 *	- - The shapes drawn by the GIS software should match those of the Compliance Report views
	 */
	@Test
	public void TC1490_CreateNewCustomerLISABoxOption() throws Exception {
		Log.info("\nRunning TC1490_CreateNewCustomerLISABoxOption ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}
 
	/**
	 * Test Case ID: TC1491_CreateNewCustomerWithoutLISABoxOption
	 * Script: -  	
	 *	- - Navigate to Picarro Administration -& Manage Customers page
	 *	- - On the Start Survey popup, fill out the details, select Standard mode and click Start a Survey
	 *	- - Log into PCubed, navigate to Reports -& Compliance Reports and click on the New Compliance Report button
	 *	- - Once the report has completed generation, click on the Compliance Viewer button and then the View thumbnail
	 *	- - Click on the Shape File button
	 *	- - Run the Shape Files through GIS software like ArcGIS
	 * Results: - 
	 *	- - The shapes drawn by the GIS software should match those of the Compliance Report views
	 */
	@Test
	public void TC1491_CreateNewCustomerWithoutLISABoxOption() throws Exception {
		Log.info("\nRunning TC1491_CreateNewCustomerWithoutLISABoxOption ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}

	/**
	 * Test Case ID: TC1496_AddLISABoxOptionExistingCustomer
	 * Script: -  	
	 *	- - Navigate to Picarro Administration -& Manage Customers page
	 *	- - Navigate to Reports -& Compliance Reports and click on the New Compliance Report button
	 *	- - Once the report has completed generation, click on the Compliance Viewer button and then the View thumbnail
	 *	- - Click on the Shape File button
	 *	- - Run the Shape Files through GIS software like ArcGIS
	 * Results: - 
	 *	- - The shapes drawn by the GIS software should match those of the Compliance Report views
	 */
	@Test
	public void TC1496_AddLISABoxOptionExistingCustomer() throws Exception {
		Log.info("\nRunning TC1496_AddLISABoxOptionExistingCustomer ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}
 
	/**
	 * Test Case ID: TC1497_RemoveLISABoxOptionFromExistingCustomer
	 * Script: -  	
	 *	- - Navigate to Picarro Administration -& Manage Customers page
	 *	- -Navigate to Reports -& Compliance Reports and click on the New Compliance Report button
	 *	- - Once the report has completed generation, click on the Compliance Viewer button and then the View thumbnail
	 *	- - Click on the Shape File button
	 *	- - Run the Shape Files through GIS software like ArcGIS
	 * Results: - 
	 *	- - The shapes drawn by the GIS software should match those of the Compliance Report views
	 */
	@Test
	public void TC1497_RemoveLISABoxOptionFromExistingCustomer() throws Exception {
		Log.info("\nRunning TC1497_RemoveLISABoxOptionFromExistingCustomer ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}
}
