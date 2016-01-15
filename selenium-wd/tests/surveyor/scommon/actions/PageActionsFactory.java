package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;

import common.source.TestContext;
import common.source.TestSetup;

public class PageActionsFactory {
	public static IActions getAction(String pageObjectName) {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		TestSetup testSetup = TestContext.INSTANCE.getTestSetup();
		String strBaseURL = TestContext.INSTANCE.getBaseUrl();

		if (pageObjectName.equals("AdministrationPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("ComplianceReportsPage")) {
			return new ComplianceReportsPageActions(driver, strBaseURL, testSetup);
		} else if (pageObjectName.equals("DriverViewPage")) {
			return new DriverViewPageActions(driver, strBaseURL, testSetup);
		} else if (pageObjectName.equals("EULAPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("FleetMapPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("HomePage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("LoginPage")) {
			return new LoginPageActions(driver, strBaseURL, testSetup);
		} else if (pageObjectName.equals("ManageAnalyzersPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("ManageCustomersPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("ManageLocationsPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("ManageRefGasBottlesPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("ManageReleaseNotesPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("ManageSurveyorPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("ManageSurveyorHistoriesPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("ManageUsersPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("MeasurementSessionsPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("PrimePage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("ReferenceGasReportsPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("SendFeedbackPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("SurveyorSystemsPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("SurveyorHistoryReportsPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("UserFeedbackPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("TestEnvironment")) {
			return new TestEnvironmentActions();
		} 
		
		return null;
	}

	public static IMethodInvoker getMethodInvoker() {
		return new TableMapMethodInvoker();
	}
}
