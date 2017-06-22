package surveyor.scommon.generators;

import org.openqa.selenium.WebDriver;

import common.source.Constants;
import common.source.TestContext;
import common.source.TestSetup;
import common.source.WebDriverFactory;
import surveyor.scommon.actions.BaseActions;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.data.UserDataReader.UserDataRow;
import surveyor.scommon.mobile.source.MobileLoginPage;
import surveyor.scommon.source.ReportInvestigationsPage;

public class ReportDataGenerator {

	private static String EMPTY = BaseActions.EMPTY;
	private static Integer NOTSET = BaseActions.NOTSET;

	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static ReportInvestigationsPage reportInvestigationsPage;
	private static MobileLoginPage mobileLoginPage;
	private Boolean isSingleUse = false;
	private WebDriver driver;

	private ReportDataGenerator() {
		this.driver = WebDriverFactory.getEmulationDriver(Constants.MobileEmulationDevice.GoogleNexus5);
	}

	/**
	 * Returns a single use generator instance that disposes itself after create method has been called.
	 * @return - Generator instance.
	 * @throws Exception
	 */
	public static ReportDataGenerator newSingleUseGenerator() throws Exception {
		ReportDataGenerator reportDataGenerator = new ReportDataGenerator();
		reportDataGenerator.initialize();
		reportDataGenerator.setSingleUse(true);
		return reportDataGenerator;
	}

	public String createReportAndAssignLisasToUser(String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		String reportId = "";
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, userDataRowID);
		complianceReportsPageAction.open(testCaseID, reportDataRowID);
		complianceReportsPageAction.createNewReport(EMPTY, reportDataRowID);
		reportId = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportName(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());

		// Assign Lisas to specified user.
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String lisaNumberPrefix = reportName+"-LISA-";
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(mobileUserDataRowID);
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		reportInvestigationsPage.selectLisa(lisaNumberPrefix+1);
		reportInvestigationsPage.selectLisa(lisaNumberPrefix+2);
		reportInvestigationsPage.selectLisa(lisaNumberPrefix+3);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);

		if (isSingleUse) {
			this.cleanUp();
		}

		return reportId;
	}

	protected void setSingleUse(Boolean singleUse) {
		this.isSingleUse = singleUse;
	}

	private void initialize() throws Exception {
		initializePageActions();
		initializePageObjects();
	}

	private void initializePageObjects() {
		TestSetup testSetup = TestContext.INSTANCE.getTestSetup();
		mobileLoginPage = new MobileLoginPage(this.driver);
		reportInvestigationsPage = new ReportInvestigationsPage(testSetup.getDriver(), testSetup.getBaseUrl(), testSetup);
	}

	private void cleanUp() {
		if (this.driver != null) {
			driver.quit();
			driver = null;
		}
	}

	/**
	 * Initializes the page action objects.
	 * @throws Exception
	 */
	private void initializePageActions() throws Exception {
		TestSetup testSetup = TestContext.INSTANCE.getTestSetup();
		loginPageAction = new LoginPageActions(testSetup.getDriver(), testSetup.getBaseUrl(), testSetup);
		complianceReportsPageAction = new ComplianceReportsPageActions(testSetup.getDriver(), testSetup.getBaseUrl(), testSetup);
	}
}