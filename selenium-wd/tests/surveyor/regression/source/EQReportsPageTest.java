/**
 *
 */
package surveyor.regression.source;

import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEMT;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.Log;
import surveyor.scommon.source.Coordinates;
import surveyor.scommon.source.EQReportsPage;
import surveyor.scommon.source.LatLongSelectionControl;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.dataprovider.EQReportDataProvider;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.EQReportsPageActions;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.entities.EQReportEntity;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.BaseReportsPageTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest.ReportTestRunMode;

/**
 *
 *
 */
@RunWith(SurveyorTestRunner.class)
public class EQReportsPageTest extends BaseReportsPageActionTest {

		private static final String EMPTY = "";
		private static final Integer NOTSET = -1;

		private static HomePageActions homePageAction;
		private static LoginPageActions loginPageAction;
		private static ManageLocationPageActions manageLocationPageAction;
		private static ManageCustomerPageActions manageCustomerPageAction;
		private static ManageUsersPageActions manageUsersPageAction;
		private static EQReportsPageActions eqReportsPageAction;

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

		private EQReportsPage getEQReportsPage() {
			return (EQReportsPage)getReportsPage();
		}

		private static void setPropertiesForTestRunMode() throws Exception {
			setTestRunMode(ReportTestRunMode.FullTestRun);

			if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
				eqReportsPageAction.fillWorkingDataForReports(getUnitTestReportRowID());
			}
		}

		/**
		 * Initializes the page action objects.
		 * @throws Exception
		 */
		protected static void initializePageActions() throws Exception {
			loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
			homePageAction = new HomePageActions(getDriver(), getBaseURL(), getTestSetup());
			eqReportsPageAction = new EQReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
			setReportsPage((EQReportsPage)eqReportsPageAction.getPageObject());
			manageCustomerPageAction = ActionBuilder.createManageCustomerPageAction();
			manageUsersPageAction = ActionBuilder.createManageUsersPageAction();
			manageLocationPageAction = ActionBuilder.createManageLocationPageAction();
		}

		/**
		 * Test Case ID: TC1437_DisableEQFeatureEQPriviledgedCustomer
		 * Test Description: Disable EQ feature for EQ priviledged Customer
		 * Script: -
		 *	- - Login as Picarro Admin
		 *	- - Navigate Picarro Admin -& Manage Customer -& Edit
		 *	- - Unselect EQ privilege check box
		 *	- - Click OK
		 *	- - Login as customer admin or supervisor user
		 *	- - Click on Reports link
		 * Results: -
		 *	- - EQ privilege is not granted to Customer
		 *	- - EQ report not present
		 */
		// Verified.
		@Test
		@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1437, location = EQReportDataProvider.class)
		public void TC1437_DisableEQFeatureEQPriviledgedCustomer(
				String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
			Log.info("\nRunning TC1437_DisableEQFeatureEQPriviledgedCustomer ...");

			loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
			loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

			// Create new customer without EQ permission and login as new customer user.
			manageCustomerPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
			manageCustomerPageAction.createNewCustomer(EMPTY, 11 /*customerRowID*/);

			manageLocationPageAction.open(EMPTY, NOTSET);
			manageLocationPageAction.createNewLocation(EMPTY, 12 /*locationRowID*/);

			manageUsersPageAction.open(EMPTY, NOTSET);
			manageUsersPageAction.createNewCustomerUser(EMPTY, 22 /*userRowID*/);

			String usernameColonPassword = String.format("%s:%s", ManageUsersPageActions.workingDataRow.get().username, ManageUsersPageActions.workingDataRow.get().password);
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(usernameColonPassword, NOTSET);   /* login using newly created user */

			eqReportsPageAction.getEQReportsPage().open();  // open without waiting for load complete.
			assertTrue(homePageAction.verifyPageLoaded(EMPTY, NOTSET));		// verify user is redirected to home page.
		}

//	TC534
//	TC539
//	TC540
//	TC541
//	TC547
//	TC553
//	#TC561
//	#TC562
//	#TC566
//	TC568
//	TC569
//	TC570
//	TC571
//	TC576
//	TC586
//	TC602
//	TC637
//	#TC651
//	TC652
//	TC653
//	TC654
//	#TC655
//	TC656
//	TC668
//	TC774
//	TC783
//	TC1542
//	TC1543
//	TC1674
//	TC1755
//	TC1781
//	TC1782
//	TC1784

}