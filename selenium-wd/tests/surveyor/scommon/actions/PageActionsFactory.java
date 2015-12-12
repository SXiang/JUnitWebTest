package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;

import common.source.TestContext;
import common.source.TestSetup;

public class PageActionsFactory {
	public static IPageActions getPageAction(String pageObjectName) {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		TestSetup testSetup = TestContext.INSTANCE.getTestSetup();
		String strBaseURL = TestContext.INSTANCE.getBaseUrl();

		if (pageObjectName.equals("AdministrationPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("ComplianceReportsPage")) {
			return new ComplianceReportsPageActions(driver, strBaseURL, testSetup);
		} else if (pageObjectName.equals("DriverViewPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("EULAPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("FleetMapPage")) {
			return new ComplianceReportsPageActions(driver, strBaseURL, testSetup);
		} else if (pageObjectName.equals("HomePage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("LoginPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("ManageAnalyzersPage")) {
			return new ComplianceReportsPageActions(driver, strBaseURL, testSetup);
		} else if (pageObjectName.equals("ManageCustomersPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("ManageLocationsPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("ManageRefGasBottlesPage")) {
			return new ComplianceReportsPageActions(driver, strBaseURL, testSetup);
		} else if (pageObjectName.equals("ManageReleaseNotesPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("ManageSurveyorPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("ManageSurveyorHistoriesPage")) {
			return new ComplianceReportsPageActions(driver, strBaseURL, testSetup);
		} else if (pageObjectName.equals("ManageUsersPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("MeasurementSessionsPage")) {
			return null;  // not yet implemented.
		} else if (pageObjectName.equals("PrimePage")) {
			return new ComplianceReportsPageActions(driver, strBaseURL, testSetup);
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
		} 
		
		return null;
	}
}
