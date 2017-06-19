package surveyor.unittest.source;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.eclipse.jetty.util.log.Log;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;
import org.junit.Test;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.EQReportsPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.entities.EQReportEntity;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.EQReportsPage;

@RunWith(SurveyorTestRunner.class)
public class EQReportsPageUnitTest  extends BaseReportsPageActionTest {

	private static EQReportsPage eqReportsPage;

	private static LoginPageActions loginPageAction;
	private static ManageCustomerPageActions manageCustomerPageAction;
	private static ManageUsersPageActions manageUsersPageAction;
	private static ManageLocationPageActions manageLocationPageAction;
	private static EQReportsPageActions eqReportsPageAction;

	@BeforeClass
	public static void beforeTestClass() throws Exception {
		initializeTestObjects();

		initializePageActions();

		eqReportsPage = new EQReportsPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(), eqReportsPage);
	}

	private static void initializePageActions() {
		manageCustomerPageAction = ActionBuilder.createManageCustomerPageAction();
		manageUsersPageAction = ActionBuilder.createManageUsersPageAction();
		manageLocationPageAction = ActionBuilder.createManageLocationPageAction();
		loginPageAction = ActionBuilder.createLoginPageAction();
		eqReportsPageAction = ActionBuilder.createEQReportsPageAction();
	}

	@Test
	public void verifyEmissionsQuantificationTable() throws IOException {
		final String reportTitle = "TC532-4c986b42ed1e4b47b655";
		final String actualPath = "C:\\Users\\spulikkal\\Downloads";
		assertTrue(eqReportsPage.verifyEmissionsQuantificationTable(actualPath, reportTitle));
	}

	@Test
	public void eqReport_NewCustomerSetLocationParameterTest() throws Exception {
		final int LOGIN_USER_ROW_ID = 6;	 	/* LoginRowID. AutomationAdmin */
		final Integer customerRowID = 14;
		final Integer locationRowID = 26;
		final Integer userRowID = 32;
		final Integer reportDataRowID1 = 18;

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, LOGIN_USER_ROW_ID);

		// Create new customer.
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.createNewCustomer(EMPTY, customerRowID);

		// Create new location.
		manageLocationPageAction.open(EMPTY, NOTSET);
		manageLocationPageAction.createNewLocation(EMPTY, locationRowID);

		// Create new user.
		manageUsersPageAction.open(EMPTY, NOTSET);
		manageUsersPageAction.createNewCustomerUser(EMPTY, userRowID);

		String newUsername = ManageUsersPageActions.workingDataRow.get().username;
		String newUserPass = ManageUsersPageActions.workingDataRow.get().password;
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(String.format("%s:%s", newUsername, newUserPass), NOTSET);

		eqReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		EQReportEntity entity = (EQReportEntity)eqReportsPageAction.fillWorkingDataForReports(getReportRowID(reportDataRowID1));
		eqReportsPageAction.getEQReportsPage().clickOnNewReportBtn();
		eqReportsPageAction.getEQReportsPage().selectEQLocationParameter(entity.getEQLocationParameter());
		String actualEQLocationParameter = eqReportsPageAction.getEQReportsPage().getEqLocationSelector().getText().trim();
		Log.info(String.format("EQ Location Parameter -> Expected='%s'; Actual='%s'", entity.getEQLocationParameter().trim(), actualEQLocationParameter));
		assertTrue(entity.getEQLocationParameter().equals(actualEQLocationParameter));
	}
}