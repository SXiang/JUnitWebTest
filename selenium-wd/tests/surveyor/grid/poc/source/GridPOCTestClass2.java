/**
 * 
 */
package surveyor.grid.poc.source;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static surveyor.scommon.source.SurveyorConstants.CUSNAMEBASE;
import static surveyor.scommon.source.SurveyorConstants.KEYANNOTATION;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETCASTIRON;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETCOPPER;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETOTHERPLASTIC;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETPEPLASTIC;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETPROTECTEDSTEEL;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETUNPROTECTEDSTEEL;
import static surveyor.scommon.source.SurveyorConstants.KEYBASEMAP;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARIES;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARYDISTRICT;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARYDISTRICTPLAT;
import static surveyor.scommon.source.SurveyorConstants.KEYBREADCRUMB;
import static surveyor.scommon.source.SurveyorConstants.KEYFOV;
import static surveyor.scommon.source.SurveyorConstants.KEYGAPS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDICATIONS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDTB;
import static surveyor.scommon.source.SurveyorConstants.KEYISOANA;
import static surveyor.scommon.source.SurveyorConstants.KEYISOTOPICCAPTURE;
import static surveyor.scommon.source.SurveyorConstants.KEYLISA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCRA;
import static surveyor.scommon.source.SurveyorConstants.KEYVIEWNAME;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDRTAG;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOCSUR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSU;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.entities.ComplianceReportEntity;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.ManageAnalyzersPage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageRefGasBottlesPage;
import surveyor.scommon.source.ManageReleaseNotesPage;
import surveyor.scommon.source.ManageSurveyorPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.SurveyorTestRunner;

@RunWith(SurveyorTestRunner.class)
public class GridPOCTestClass2 extends GridPOCBaseTest {
	private static ComplianceReportsPage complianceReportsPage;
	private static ManageCustomersPage manageCustomersPage;
	private static ManageUsersPage manageUsersPage;
	private static ManageLocationsPage manageLocationsPage;
	private static ManageSurveyorPage manageSurveyorsPage;
	private static ManageAnalyzersPage manageAnalyzersPage;
	private static ManageRefGasBottlesPage manageRefGasBottlesPage;
	private static ManageReleaseNotesPage manageReleaseNotesPage;

	@BeforeClass
	public static void setupBeforeClass() {
		complianceReportsPage = new ComplianceReportsPage(getDriver(), getBaseURL(),
				getTestSetup());
		PageFactory.initElements(getDriver(), complianceReportsPage);

		manageCustomersPage = new ManageCustomersPage(getDriver(), getBaseURL(),
				getTestSetup());
		PageFactory.initElements(getDriver(), manageCustomersPage);

		manageUsersPage = new ManageUsersPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(), manageUsersPage);

		manageLocationsPage = new ManageLocationsPage(getDriver(), getBaseURL(),
				getTestSetup());
		PageFactory.initElements(getDriver(), manageLocationsPage);

		manageSurveyorsPage = new ManageSurveyorPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(), manageSurveyorsPage);

		manageAnalyzersPage = new ManageAnalyzersPage(getDriver(), getBaseURL(),
				getTestSetup());
		PageFactory.initElements(getDriver(), manageAnalyzersPage);

		manageRefGasBottlesPage = new ManageRefGasBottlesPage(getDriver(),
				getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(), manageRefGasBottlesPage);

		manageReleaseNotesPage = new ManageReleaseNotesPage(getDriver(), getBaseURL(),
				getTestSetup());
		PageFactory.initElements(getDriver(), manageReleaseNotesPage);
	}

	/**
	 * Test Case ID: TC739 Test Description: Generate compliance report as
	 * customer supervisor user by selecting report area using custom boundary
	 * @throws Exception 
	 * 
	 */
	@Test
	public void TC739_GenerateComplianceReport_CustomerSupervisor() throws Exception {
		String testCaseID = "TC739";
		String rptTitle = "Customer Supervisor Report " + testCaseID + " "
				+ getTestSetup().getRandomNumber();
		System.out
				.format("\nRunning " + testCaseID
						+ ": Generate compliance report as customer supervisor user by selecting report area using custom boundary, %s\n",
						rptTitle);

		complianceReportsPage.login(SQACUSSU, USERPASSWORD);
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("8.5");
		listBoundary.add("11");
		listBoundary.add("37.42060203616509");
		listBoundary.add("-121.97252515127416");
		listBoundary.add("37.41572797964773");
		listBoundary.add("-121.98394063284152");

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();
		Map<String, String> viewMap2 = new HashMap<String, String>();
		Map<String, String> viewMap3 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "0");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));

		viewMap2.put(KEYVIEWNAME, "Second View");
		viewMap2.put(KEYLISA, "0");
		viewMap2.put(KEYFOV, "0");
		viewMap2.put(KEYBREADCRUMB, "1");
		viewMap2.put(KEYINDICATIONS, "1");
		viewMap2.put(KEYISOTOPICCAPTURE, "0");
		viewMap2.put(KEYANNOTATION, "0");
		viewMap2.put(KEYGAPS, "1");
		viewMap2.put(KEYASSETS, "0");
		viewMap2.put(KEYBOUNDARIES, "0");
		viewMap2.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));

		viewMap3.put(KEYVIEWNAME, "Third View");
		viewMap3.put(KEYLISA, "1");
		viewMap3.put(KEYFOV, "1");
		viewMap3.put(KEYBREADCRUMB, "0");
		viewMap3.put(KEYINDICATIONS, "0");
		viewMap3.put(KEYISOTOPICCAPTURE, "1");
		viewMap3.put(KEYANNOTATION, "1");
		viewMap3.put(KEYGAPS, "0");
		viewMap3.put(KEYASSETS, "0");
		viewMap3.put(KEYBOUNDARIES, "0");
		viewMap3.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);
		viewList.add(viewMap2);
		viewList.add(viewMap3);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "1");
		tableMap.put(KEYASSETCASTIRON, "0");
		tableMap.put(KEYASSETCOPPER, "0");
		tableMap.put(KEYASSETOTHERPLASTIC, "0");
		tableMap.put(KEYASSETPEPLASTIC, "0");
		tableMap.put(KEYASSETPROTECTEDSTEEL, "0");
		tableMap.put(KEYASSETUNPROTECTEDSTEEL, "0");
		tableMap.put(KEYBOUNDARYDISTRICT, "0");
		tableMap.put(KEYBOUNDARYDISTRICTPLAT, "0");
		tablesList.add(tableMap);
		
		List<String> tagList=new ArrayList<String>();
		tagList.add(SQACUSDRTAG);

		ComplianceReportEntity rpt = new ComplianceReportEntity(rptTitle, SQACUSSU,
				CUSNAMEBASE, TIMEZONEPT, "0", listBoundary, tablesList,
				SQACUSLOCSUR,tagList , viewList);
		
		complianceReportsPage.addNewReport(rpt);

		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());
		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSSU, testCaseID))) {
			assertTrue(complianceReportsPage.findReport(rptTitle, SQACUSSU));
			assertTrue(complianceReportsPage.validatePdfFiles(rpt,
					getTestSetup().getDownloadPath()));
		} else
			fail("\nTestcase TC739 failed." + rptTitle
					+ " report failed to generate by " + SQACUSSU + " user!!\n");
	}
}