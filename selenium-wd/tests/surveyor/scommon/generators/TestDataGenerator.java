package surveyor.scommon.generators;

import java.util.function.Supplier;

import org.openqa.selenium.support.PageFactory;

import common.source.CheckedPredicate;
import common.source.ExceptionUtility;
import common.source.Log;
import common.source.TestContext;
import surveyor.dataaccess.source.Customer;
import surveyor.dataaccess.source.CustomerWithGisDataPool;
import surveyor.dataaccess.source.Location;
import surveyor.dataaccess.source.SurveyorUnit;
import surveyor.dataaccess.source.User;
import surveyor.dbseed.source.DbSeedExecutor;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.BaseActions;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.ManageAnalyzerPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.actions.ManageRefGasBottlesPageActions;
import surveyor.scommon.actions.ManageSurveyorPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.entities.CustomerSurveyInfoEntity;
import surveyor.scommon.source.ManageSurveyorAdminPage;
import surveyor.scommon.source.PageObjectFactory;

public class TestDataGenerator {

	private ManageCustomerPageActions manageCustomerPageAction;
	private ManageUsersPageActions manageUsersPageAction;
	private ManageLocationPageActions manageLocationPageAction;
	private ManageAnalyzerPageActions manageAnalyzerPageAction;
	private ManageSurveyorPageActions manageSurveyorPageAction;
	private ManageSurveyorAdminPage manageSurveyorAdminPage;
	private ManageRefGasBottlesPageActions manageRefGasBottlesPageAction;

	private static final String EMPTY = BaseActions.EMPTY;
	private static final Integer NOTSET = BaseActions.NOTSET;

	public TestDataGenerator() {
		PageObjectFactory pageObjectFactory = new PageObjectFactory();
		manageSurveyorAdminPage = pageObjectFactory.getManageSurveyorAdminPage();

		manageCustomerPageAction = ActionBuilder.createManageCustomerPageAction();
		manageUsersPageAction = ActionBuilder.createManageUsersPageAction();
		manageLocationPageAction = ActionBuilder.createManageLocationPageAction();
		manageAnalyzerPageAction = ActionBuilder.createManageAnalyzerPageAction();
		manageSurveyorPageAction = ActionBuilder.createManageSurveyorPageAction();
		manageRefGasBottlesPageAction = ActionBuilder.createManageRefGasBottlePageAction();
	}

	public CustomerSurveyInfoEntity generateCustomerSurveyInfo(Supplier<CustomerSurveyInfoEntity> objectSupplier) {
		return objectSupplier.get();
	}

	public void generateNewCustomerAndSurvey(CustomerSurveyInfoEntity custSrvInfo) throws Exception {
		generateNewCustomerAndSurvey(custSrvInfo, null /*testActions Predicate*/);
	}

	public void generateNewCustomerAndSurvey(CustomerSurveyInfoEntity custSrvInfo, CheckedPredicate<DriverViewPageActions> testActions) throws Exception {
		// Create new customer.
		manageCustomerPageAction.open(EMPTY, NOTSET);

		if (custSrvInfo.isPushGISSeedData() && TestContext.INSTANCE.getTestSetup().isGeoServerEnabled()) {
			manageCustomerPageAction.createNewGisCustomer(EMPTY, custSrvInfo.getCustomerRowID() /*customerRowID*/);
		} else {
			manageCustomerPageAction.createNewCustomer(EMPTY, custSrvInfo.getCustomerRowID() /*customerRowID*/);
		}

		String customerName = ManageCustomerPageActions.workingDataRow.get().name;

		// Create new location.
		manageLocationPageAction.open(EMPTY, NOTSET);
		manageLocationPageAction.createNewLocation(EMPTY, custSrvInfo.getLocationRowID() /*locationRowID*/);
		String locationName = ManageLocationPageActions.workingDataRow.get().name;

		// Create new user.
		manageUsersPageAction.open(EMPTY, NOTSET);
		manageUsersPageAction.createNewCustomerUser(EMPTY, custSrvInfo.getUserRowID() /*userRowID*/);

		// Create new surveyor.
		manageSurveyorPageAction.open(EMPTY, NOTSET);
		manageSurveyorPageAction.createNewSurveyor(EMPTY, custSrvInfo.getSurveyorRowID() /*surveyorRowID*/);
		String surveyorName = ManageSurveyorPageActions.workingDataRow.get().description;

		if(custSrvInfo.isCalibrationRecord())
		{
			manageSurveyorAdminPage.editExistingSurveyor(customerName, locationName, surveyorName, locationName, surveyorName, true);
		}

		// Create new analyzer.
		manageAnalyzerPageAction.open(EMPTY, NOTSET);
		manageAnalyzerPageAction.createNewAnalyzer(EMPTY, custSrvInfo.getAnalyzerRowID() /*analyzerRowID*/);

		// Create new ref gas bottle.
		manageRefGasBottlesPageAction.open(EMPTY, NOTSET);
		manageRefGasBottlesPageAction.createNewRefGasBottle(EMPTY, custSrvInfo.getRefGasBottleRowID() /*refGasBottleRowID*/);

		// Push GIS seed if specified.
		if (custSrvInfo.isPushGISSeedData() && !TestContext.INSTANCE.getTestSetup().isGeoServerEnabled()) {
			Customer customer = Customer.getCustomer(ManageCustomerPageActions.workingDataRow.get().name);
			DbSeedExecutor.executeGisSeed(customer.getId());
		}

		// Email ID for the new created user was generated dynamically in this case by using 'GenerateRandomEmail(20)' function.
		// For such cases, use the overload with username and password for generateSurveyForUser().
		String newUsername = ManageUsersPageActions.workingDataRow.get().username;
		String newUserPass = ManageUsersPageActions.workingDataRow.get().password;
		TestEnvironmentActions.generateSurveyForUser(newUsername, newUserPass,
				custSrvInfo.getDb3AnalyzerRowID(), custSrvInfo.getSurveyRowID(), custSrvInfo.getSurveyRuntimeInSeconds(),
				custSrvInfo.getInstructionFiles(), testActions);

		// Cleanup GIS seed (if not specified to be retained).
		if (!custSrvInfo.isRetainGISSeedData()) {
			if (TestContext.INSTANCE.getTestSetup().isGeoServerEnabled()) {
				CustomerWithGisDataPool.releaseCustomer(ManageCustomerPageActions.workingDataRow.get().name);
			} else {
				try {
					Customer customer = Customer.getCustomer(ManageCustomerPageActions.workingDataRow.get().name);
					DbSeedExecutor.cleanUpGisSeed(customer.getId());
				} catch (Exception e) {
					Log.error(String.format("Error in FINALLY. Exception - %s", ExceptionUtility.getStackTraceString(e)));
				}
			}
		}
	}
}