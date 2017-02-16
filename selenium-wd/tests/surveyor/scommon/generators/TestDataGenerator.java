package surveyor.scommon.generators;

import java.util.function.Supplier;

import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.BaseActions;
import surveyor.scommon.actions.ManageAnalyzerPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.actions.ManageRefGasBottlesPageActions;
import surveyor.scommon.actions.ManageSurveyorPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.entities.CustomerSurveyInfoEntity;

public class TestDataGenerator {

	private ManageCustomerPageActions manageCustomerPageAction;
	private ManageUsersPageActions manageUsersPageAction;
	private ManageLocationPageActions manageLocationPageAction;
	private ManageAnalyzerPageActions manageAnalyzerPageAction;
	private ManageSurveyorPageActions manageSurveyorPageAction;
	private ManageRefGasBottlesPageActions manageRefGasBottlesPageAction;

	private static final String EMPTY = BaseActions.EMPTY;
	private static final Integer NOTSET = BaseActions.NOTSET;

	public TestDataGenerator() {
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
		// Create new customer.
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.createNewCustomer(EMPTY, custSrvInfo.getCustomerRowID() /*customerRowID*/);

		// Create new location.
		manageLocationPageAction.open(EMPTY, NOTSET);
		manageLocationPageAction.createNewLocation(EMPTY, custSrvInfo.getLocationRowID() /*locationRowID*/);

		// Create new user.
		manageUsersPageAction.open(EMPTY, NOTSET);
		manageUsersPageAction.createNewCustomerUser(EMPTY, custSrvInfo.getUserRowID() /*userRowID*/);

		// Create new surveyor.
		manageSurveyorPageAction.open(EMPTY, NOTSET);
		manageSurveyorPageAction.createNewSurveyor(EMPTY, custSrvInfo.getSurveyorRowID() /*surveyorRowID*/);

		// Create new analyzer.
		manageAnalyzerPageAction.open(EMPTY, NOTSET);
		manageAnalyzerPageAction.createNewAnalyzer(EMPTY, custSrvInfo.getAnalyzerRowID() /*analyzerRowID*/);

		// Create new ref gas bottle.
		manageRefGasBottlesPageAction.open(EMPTY, NOTSET);
		manageRefGasBottlesPageAction.createNewRefGasBottle(EMPTY, custSrvInfo.getRefGasBottleRowID() /*refGasBottleRowID*/);

		// Email ID for the new created user was generated dynamically in this case by using 'GenerateRandomEmail(20)' function.
		// For such cases, use the overload with username and password for generateSurveyForUser().
		String newUsername = ManageUsersPageActions.workingDataRow.get().username;
		String newUserPass = ManageUsersPageActions.workingDataRow.get().password;
		TestEnvironmentActions.generateSurveyForUser(newUsername, newUserPass,
				custSrvInfo.getDb3AnalyzerRowID(), custSrvInfo.getSurveyRowID(), custSrvInfo.getSurveyRuntimeInSeconds());
	}
}
