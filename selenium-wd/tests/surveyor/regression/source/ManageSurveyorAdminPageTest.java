/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.NOMATCHINGSEARCH;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING_100;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING_25;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING_50;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.SQACUS;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOCSUR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSURVEYOR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.SQAPICLOC;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ManageLocationsAdminPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageSurveyorAdminPage;
import surveyor.scommon.source.ManageSurveyorPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;
import common.source.BaseHelper;
import common.source.Log;

/**
 * @author zlu
 *
 */
@RunWith(SurveyorTestRunner.class)
public class ManageSurveyorAdminPageTest extends SurveyorBaseTest {
	private static ManageLocationsPage manageLocationsPage;
	private static ManageLocationsAdminPage manageLocationsAdminPage;
	private static ManageSurveyorPage manageSurveyorPage;
	private static ManageSurveyorAdminPage manageSurveyorAdminPage;

	@BeforeClass
	public static void setupManageSurveyorAdminPageTest() {
		manageLocationsPage = new ManageLocationsPage(driver, getBaseURL(), getTestSetup());
		PageFactory.initElements(driver, manageLocationsPage);

		manageLocationsAdminPage = new ManageLocationsAdminPage(driver, getBaseURL(), getTestSetup());
		PageFactory.initElements(driver, manageLocationsAdminPage);

		manageSurveyorPage = new ManageSurveyorPage(driver, getBaseURL(), getTestSetup());
		PageFactory.initElements(driver, manageSurveyorPage);

		manageSurveyorAdminPage = new ManageSurveyorAdminPage(driver, getBaseURL(), getTestSetup());
		PageFactory.initElements(driver, manageSurveyorAdminPage);
	}

	/**
	 * Test Case ID: TC455_EditSurveyor_CustUA Test Description: edit surveyor Test Script: - On Home Page, click Administration -> Manage Customer's Surveyors - Click on Edit link - Modify Surveyor
	 * details and click OK Expected Results: - Customer Admin is navigated to Manage Customer's Surveyors page and the newly created Surveyor is present in the table Current implementation: Current
	 * Issue: Future Improvement: editing a surveyor to have it associate to a different customer location is covered in CUSTADM020
	 */
	@Test
	public void TC455_EditSurveyor_CustUA() {
		String locationName = SQACUSLOC;
		String surveyorName = SQACUSLOCSUR + getTestSetup().getRandomNumber() + "TC455";
		String surveyorNameNew = surveyorName + "New";

		Log.info("\nRunning - TC455_EditSurveyor_CustUA - Test Description: edit surveyor\n");

		getLoginPage().open();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		manageSurveyorPage.open();
		manageSurveyorPage.addNewSurveyor(surveyorName, SQACUS + " - " + SQACUSLOC);

		getLoginPage().open();
		getLoginPage().loginNormalAs(SQACUSUA, USERPASSWORD);

		manageSurveyorAdminPage.open();
		manageSurveyorAdminPage.editExistingSurveyor(SQACUS, locationName, surveyorName, locationName, surveyorNameNew, false);

		assertTrue(manageSurveyorAdminPage.findExistingSurveyor(locationName, surveyorNameNew));
	}

	/**
	 * Test Case ID: TC456_EditSurveyorAssignLoc_CustUA Test Description: Administrator is allowed to associate and disassociate Surveyor Units within Locations associated only to his customer Test
	 * Script: Pre-requisite: Location1 and Location2 are associated to same customer of admin - On Home Page, click Administration -> Manage Customer's Surveyors - Click on Edit link - Deassociate
	 * SurveyorUnit1 from Location1 and associate it to Location2 Expected Results: - Customer Admin is navigated to Manage Customer's Surveyors page and SurveyorUnit1 will now be associated with
	 * Location2 Current implementation: Current Issue: Future Improvement:
	 */
	@Test
	public void TC456_EditSurveyorAssignLoc_CustUA() {
		String locationName1 = SQACUSLOC;
		String locationName2 = SQACUSLOC + getTestSetup().getRandomNumber();
		String locationName3 = SQAPICLOC;
		String surveyorName = SQACUSLOCSUR + getTestSetup().getRandomNumber() + "TC456";
		String cityName = "Santa Clara";

		Log.info("\nRunning - TC456_EditSurveyorAssignLoc_CustUA - Test Description: Administrator is allowed to associate and " + "disassociate Surveyor Units within Locations associated only to his customer\n");

		getLoginPage().open();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		// Add Surveyor with Location1
		manageSurveyorPage.open();
		manageSurveyorPage.addNewSurveyor(surveyorName, locationName1, SQACUS);

		// Add Location2 for the Customer.
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName2, SQACUS, cityName);

		// Login as Customer Utility Admin.
		getLoginPage().open();
		getLoginPage().loginNormalAs(SQACUSUA, USERPASSWORD);

		// Edit Surveyor and assign to Location2.
		manageSurveyorAdminPage.open();
		manageSurveyorAdminPage.editExistingSurveyor(SQACUS, locationName1, surveyorName, locationName2, surveyorName, false);

		// Verify Surveyor is found in Location2.
		assertTrue(manageSurveyorAdminPage.findExistingSurveyor(locationName2, surveyorName));
		// Verify Surveyor is NOT found in Location1.
		assertFalse(manageSurveyorAdminPage.findExistingSurveyor(locationName1, surveyorName));
		// Verify Surveyor cannot be edited with Location1.
		assertFalse(manageSurveyorAdminPage.editExistingSurveyor(locationName1, surveyorName, locationName3, surveyorName));
	}

	/**
	 * Test Case ID: TC457_EditSurveyorDesc50CharLimit_CustUA Test Description: More than 400 characters not allowed in Surveyor Description field Test Script: - On Home Page, and click Administration
	 * -> Manage Surveyors - Click on 'Edit' button - Provide more than 400 characters in Surveyor Description field and click OK Expected Results: User cannot enter more than 400 characters and
	 * message having limit of characters displayed Current implementation: Future Improvement:
	 */
	@Test
	public void TC457_EditSurveyorDesc400CharLimit_CustUA() {
		String str14chars = "AbcdefghI-Abcd";
		String str15chars = "AbcdefghI-Abcde";

		String surveyorName400Chars = getTestSetup().getFixedSizePseudoRandomString(381) + "TC457" + str14chars;
		String surveyorName401Chars = getTestSetup().getFixedSizePseudoRandomString(381) + "TC457" + str15chars;

		Log.info("\nRunning - TC457_EditSurveyorDesc50CharLimit_CustUA - Test Description: More than 400 characters not allowed " + "in Surveyor Description field\n");

		getLoginPage().open();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		manageSurveyorPage.open();
		manageSurveyorPage.addNewSurveyor(surveyorName400Chars, SQACUSLOC, SQACUS);
		manageSurveyorPage.waitForPageLoad();

		getLoginPage().open();
		getLoginPage().loginNormalAs(SQACUSUA, USERPASSWORD);

		Log.info("surveyorName400Chars=" + surveyorName400Chars);
		Log.info("surveyorName401Chars=" + surveyorName401Chars);
		Log.info("Starting to edit the surveyor...");

		manageSurveyorAdminPage.open();
		manageSurveyorAdminPage.editExistingSurveyor(SQACUS, SQACUSLOC, surveyorName400Chars, SQACUSLOC, surveyorName401Chars, false);

		String allowedSurveyorName = surveyorName401Chars.substring(0, 400);
		Log.info("allowedSurveyorName=" + allowedSurveyorName);

		getLoginPage().open();
		getLoginPage().loginNormalAs(SQACUSUA, USERPASSWORD);

		manageSurveyorAdminPage.open();
		assertTrue(manageSurveyorAdminPage.findExistingSurveyor(SQACUSLOC, allowedSurveyorName));
	}

	/**
	 * Test Case ID: TC458_EditSurveyorBlankRequiredFields_CustUA Test Description: edit surveyor - blank required fields Test Script: - On Home Page, click Administration -> Manage Customer's
	 * Surveyors - Click on Edit link - Delete description field data. Click OK Expected Results: "Please fill out this field." message should be displayed Current implementation: Current Issue:
	 * Future Improvement: deal with the tooltip text
	 */
	@Test
	public void TC458_EditSurveyorBlankRequiredFields_CustUA() {
		String surveyorName = getTestSetup().getRandomNumber() + "TC458";

		Log.info("\nRunning - TC458_EditSurveyorBlankRequiredFields_CustUA - Test Description: edit surveyor - blank required fields\n");

		getLoginPage().open();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		manageSurveyorPage.open();
		manageSurveyorPage.addNewSurveyor(surveyorName, SQACUSLOC, SQACUS);

		getLoginPage().open();
		getLoginPage().loginNormalAs(SQACUSUA, USERPASSWORD);

		manageSurveyorAdminPage.open();
		assertFalse(manageSurveyorAdminPage.editExistingSurveyor(SQACUS, SQACUSLOC, surveyorName, "", surveyorName, false));
	}

	/**
	 * Test Case ID: TC467_DuplicateSurveyorNotAllowed_CustUA Test Description: Edit surveyor name with an existing name asociated to same location Script: - On Home Page, click Administration ->
	 * Manage Customer's Surveyors - Click on Edit link - Provide existing Surveyor name for same location and click OK Expected Results: - Customer Admin cannot have two surveyor units associated to
	 * one location. "Surveyor name already exists, please try another name" message should be displayed
	 */
	@Test
	public void TC467_DuplicateSurveyorNotAllowed_CustUA() {
		String locationName = getTestSetup().getRandomNumber() + "TC467";
		String surveyorName1 = SQACUSLOCSUR + getTestSetup().getRandomNumber() + "TC467_1";
		String surveyorName2 = SQACUSLOCSUR + getTestSetup().getRandomNumber() + "TC467_2";
		String cityName = "Santa Clara";

		Log.info("\nRunning - TC467_DuplicateSurveyorNotAllowed_CustUA - Test Description: Edit surveyor name with an existing name asociated to same location\n");

		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, SQACUS, cityName);

		// Add Surveyor1 with Location1
		manageSurveyorPage.open();
		manageSurveyorPage.addNewSurveyor(surveyorName1, locationName, SQACUS);

		// Add Surveyor2 with Location1
		manageSurveyorPage.open();
		manageSurveyorPage.addNewSurveyor(surveyorName2, locationName, SQACUS);

		// Login as Customer Utility Admin.
		getLoginPage().open();
		getLoginPage().loginNormalAs(SQACUSUA, USERPASSWORD);

		// Edit SurveyorName same as existing one associated to same location
		manageSurveyorAdminPage.open();

		assertFalse(manageSurveyorAdminPage.editExistingSurveyor(SQACUS, locationName, surveyorName1, locationName, surveyorName2, false));
		assertTrue(manageSurveyorAdminPage.isDuplicateSurMsgPresent(locationName));
	}

	/**
	 * Test Case ID: TC450_ManageSurveyorAdminPagination Test Description: Pagination (Manage Surveyor Customer Admin) Test Script: 10,25,50 and 100 records selection on all customer admin screens
	 * Expected Results: Specified number of records will be listed in the table
	 */
	@Test
	public void TC450_ManageSurveyorAdminPagination() {
		List<String> surveyorList;
		Log.info("\nRunning - TC450_ManageSurveyorAdminPagination - Test Description: Pagination (Manage Surveyor)\n");

		getLoginPage().open();
		getLoginPage().loginNormalAs(SQACUSUA, USERPASSWORD);

		manageSurveyorAdminPage.open();
		manageSurveyorAdminPage.setPagination(PAGINATIONSETTING);
		surveyorList = manageSurveyorAdminPage.getSurveyorList(false, Integer.valueOf(PAGINATIONSETTING));

		assertTrue(surveyorList.size() <= Integer.valueOf(PAGINATIONSETTING));
		assertTrue(manageSurveyorAdminPage.getListSize(surveyorList));

		manageSurveyorAdminPage.open();
		manageSurveyorAdminPage.setPagination(PAGINATIONSETTING_25);
		surveyorList = manageSurveyorAdminPage.getSurveyorList(false, Integer.valueOf(PAGINATIONSETTING_25));

		assertTrue(surveyorList.size() <= Integer.valueOf(PAGINATIONSETTING_25));
		assertTrue(manageSurveyorAdminPage.getListSize(surveyorList));

		manageSurveyorAdminPage.open();
		manageSurveyorAdminPage.setPagination(PAGINATIONSETTING_50);
		surveyorList = manageSurveyorAdminPage.getSurveyorList(false, Integer.valueOf(PAGINATIONSETTING_50));

		assertTrue(surveyorList.size() <= Integer.valueOf(PAGINATIONSETTING_50));
		assertTrue(manageSurveyorAdminPage.getListSize(surveyorList));

		manageSurveyorAdminPage.open();
		manageSurveyorAdminPage.setPagination(PAGINATIONSETTING_100);
		surveyorList = manageSurveyorAdminPage.getSurveyorList(false, Integer.valueOf(PAGINATIONSETTING_100));

		assertTrue(surveyorList.size() <= Integer.valueOf(PAGINATIONSETTING_100));
		assertTrue(manageSurveyorAdminPage.getListSize(surveyorList));
	}

	/**
	 * Test Case ID: TC451 Test Description: Search valid surveyor record
	 */
	@Test
	public void TC451_SearchValidSurveyor() {
		String locationName = "TC451Location" + getTestSetup().getRandomNumber();
		String surveyorName = "TC451_" + SQACUSSURVEYOR + getTestSetup().getRandomNumber();
		String cityName = "Santa Clara";
		Log.info("\nRunning - TC451 - Test Description: Search valid surveyor record\n");

		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, SQACUS, cityName);

		manageSurveyorPage.open();
		manageSurveyorPage.addNewSurveyor(surveyorName, locationName, SQACUS);

		getLoginPage().open();
		getLoginPage().loginNormalAs(SQACUSUA, USERPASSWORD);
		manageSurveyorAdminPage.open();

		assertTrue(manageSurveyorAdminPage.searchSurveyor(locationName, surveyorName));
	}

	/**
	 * Test Case ID: TC452 Test Description: Search invalid surveyor record
	 */
	@Test
	public void TC452_SearchInvalidSurveyor() {
		String surveyorName = "TC452_" + SQACUSSURVEYOR + getTestSetup().getRandomNumber();
		Log.info("\nRunning - TC451 - Test Description: Search valid surveyor record\n");

		getLoginPage().open();
		getLoginPage().loginNormalAs(SQACUSUA, USERPASSWORD);
		manageSurveyorAdminPage.open();
		manageSurveyorAdminPage.waitForPageLoad();
		manageSurveyorAdminPage.getInputSearch().sendKeys(surveyorName);
		manageSurveyorAdminPage.waitForPageToLoad();

		assertTrue(manageSurveyorAdminPage.getLabelNoMatchingSearch().equalsIgnoreCase(NOMATCHINGSEARCH));
	}

	/**
	 * Test Case ID: TC453 Test Description: Sort surveyor records based on attributes present
	 */
	@Test
	public void TC453_SortSurveyorRecords() {
		List<String> list = new ArrayList<String>();
		Log.info("\nRunning - TC453 - Test Description: Sort surveyor records based on attributes present\n");

		getLoginPage().open();
		getLoginPage().loginNormalAs(SQACUSUA, USERPASSWORD);
		manageSurveyorAdminPage.open();

		manageSurveyorAdminPage.getTheadLocation().click();
		list = manageSurveyorAdminPage.getLocationList(false, Integer.valueOf(PAGINATIONSETTING_100));
		assertTrue(BaseHelper.isStringListSortedDes(list));
		manageSurveyorAdminPage.getTheadLocation().click();
		list = manageSurveyorAdminPage.getLocationList(false, Integer.valueOf(PAGINATIONSETTING_100));
		assertTrue(BaseHelper.isStringListSorted(list));

		manageSurveyorAdminPage.getTheadSurveyor().click();
		list = manageSurveyorAdminPage.getSurveyorList(false, Integer.valueOf(PAGINATIONSETTING_100));
		assertTrue(BaseHelper.isStringListSorted(list));
		manageSurveyorAdminPage.getTheadSurveyor().click();
		list = manageSurveyorAdminPage.getSurveyorList(false, Integer.valueOf(PAGINATIONSETTING_100));
		assertTrue(BaseHelper.isStringListSortedDes(list));
	}
}