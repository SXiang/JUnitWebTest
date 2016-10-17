package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;

import common.source.TestContext;
import common.source.TestSetup;

public class ActionBuilder {
	private static WebDriver driver = TestContext.INSTANCE.getDriver();
	private static String baseURL = TestContext.INSTANCE.getBaseUrl();
	private static TestSetup testSetup = TestContext.INSTANCE.getTestSetup();
	
	public ActionBuilder() {
	}

	public static LoginPageActions createLoginPageAction() {
		return new LoginPageActions(driver, baseURL, testSetup);
	}

	public static DriverViewPageActions createDriverViewPageAction() {
		return new DriverViewPageActions(driver, baseURL, testSetup);
	}

	public static SurveyViewPageActions createSurveyViewPageAction() {
		return new SurveyViewPageActions(driver, baseURL, testSetup);
	}

	public static ObserverViewPageActions createObserverViewPageAction() {
		return new ObserverViewPageActions(driver, baseURL, testSetup);
	}

	public static ManageCustomerPageActions createManageCustomerPageAction() {
		return new ManageCustomerPageActions(driver, baseURL, testSetup);
	}

	public static ManageLocationPageActions createManageLocationPageAction() {
		return new ManageLocationPageActions(driver, baseURL, testSetup);
	}

	public static ManageAnalyzerPageActions createManageAnalyzerPageAction() {
		return new ManageAnalyzerPageActions(driver, baseURL, testSetup);
	}

	public static ManageSurveyorPageActions createManageSurveyorPageAction() {
		return new ManageSurveyorPageActions(driver, baseURL, testSetup);
	}

	public static ManageRefGasBottlesPageActions createManageRefGasBottlePageAction() {
		return new ManageRefGasBottlesPageActions(driver, baseURL, testSetup);
	}

	public static ManageUsersPageActions createManageUsersPageAction() {
		return new ManageUsersPageActions(driver, baseURL, testSetup);
	}
	
	public static ComplianceReportsPageActions createComplianceReportsPageAction() {
		return new ComplianceReportsPageActions(driver, baseURL, testSetup);
	}
	
	public static TestEnvironmentActions createTestEnvironmentAction() {
		return new TestEnvironmentActions();
	}
}
