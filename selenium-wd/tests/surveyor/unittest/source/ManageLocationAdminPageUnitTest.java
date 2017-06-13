/**
 * 
 */
package surveyor.unittest.source;


import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import surveyor.regression.source.BaseMapViewTest;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.SurveyorTestRunner;
import common.source.Log;

@RunWith(SurveyorTestRunner.class)
public class ManageLocationAdminPageUnitTest extends BaseMapViewTest {
	private static LoginPage loginPage;
	private static HomePage homePage;
	private static ManageCustomerPageActions manageCustomerPageAction;
	private static ManageLocationPageActions manageLocationPageAction;

	@BeforeClass
	public static void beforeTestClass() {
		initializeTestObjects();
		manageCustomerPageAction = ActionBuilder.createManageCustomerPageAction();
		manageLocationPageAction = ActionBuilder.createManageLocationPageAction();
		}

	@Before
	public void beforeTestMethod() {
			PageObjectFactory pageObjectFactory = new PageObjectFactory();

			loginPage = pageObjectFactory.getLoginPage();
			PageFactory.initElements(getDriver(), loginPage);

			homePage = pageObjectFactory.getHomePage();
			PageFactory.initElements(getDriver(), homePage);
		}

	/**
	 * Test Description: Add FEQ location for new customer
	 * attributes present
	 */
	@Test
	public void AddLocationWithFEQLocationParameters() throws Exception{
		Log.info("\nTestcase - AddLocationWithFEQLocationParameters\n");

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		// Create new customer.
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.createNewCustomer(EMPTY, 14 /*customerRowID*/);

		// Create new location.
		manageLocationPageAction.open(EMPTY, NOTSET);
		manageLocationPageAction.createNewLocation(EMPTY, 24 /*locationRowID*/);
	}
	
	/**
	 * Test Description: Add and Edit FEQ location for new customer
	 * attributes present
	 */
	@Test
	public void EditLocationWithFEQLocationParameters() throws Exception{
		Log.info("\nTestcase - EditLocationWithFEQLocationParameters\n");

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		// Create new customer.
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.createNewCustomer(EMPTY, 14 /*customerRowID*/);

		// Create new location.
		manageLocationPageAction.open(EMPTY, NOTSET);
		manageLocationPageAction.createNewLocation(EMPTY, 24 /*locationRowID*/);
		
		///Edit FEQ location parameters for the existing location
		manageLocationPageAction.editFEQLocationParameters(EMPTY, 25/*locationRowID*/);
		assertTrue(manageLocationPageAction.findExistingLocation(EMPTY, 25));

	}

	/**
	 * Test Description: Add MEQ location for new customer
	 * attributes present
	 */
	@Test
	public void AddLocationWithMEQLocationParameters() throws Exception{
		Log.info("\nTestcase - AddLocationWithMEQLocationParameters\n");

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		// Create new customer.
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.createNewCustomer(EMPTY, 14 /*customerRowID*/);

		// Create new location.
		manageLocationPageAction.open(EMPTY, NOTSET);
		manageLocationPageAction.createNewLocation(EMPTY, 26 /*locationRowID*/);
	}
	
	/**
	 * Test Description: Add and Edit MEQ location for new customer
	 * attributes present
	 */
	@Test
	public void EditLocationWithMEQLocationParameters() throws Exception{
		Log.info("\nTestcase - EditLocationWithMEQLocationParameters\n");

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		// Create new customer.
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.createNewCustomer(EMPTY, 14 /*customerRowID*/);

		// Create new location.
		manageLocationPageAction.open(EMPTY, NOTSET);
		manageLocationPageAction.createNewLocation(EMPTY, 26 /*locationRowID*/);
		
		///Edit MEQ location parameters for the existing location
		manageLocationPageAction.editMEQLocationParameters(EMPTY, 27/*locationRowID*/);
		assertTrue(manageLocationPageAction.findExistingLocation(EMPTY, 27));

	}

}
