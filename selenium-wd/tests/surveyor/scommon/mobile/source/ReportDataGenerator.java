package surveyor.scommon.mobile.source;

import org.openqa.selenium.WebDriver;

import common.source.Constants;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;
import common.source.WebDriverFactory;
import surveyor.scommon.actions.BaseActions;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.data.UserDataReader.UserDataRow;
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

	public String createReportAndAssignLisasAndGapsToUser(String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID,
			String[] lisaNumbers, String[] gapNumbers) throws Exception {
		Log.method("createReportAndAssignLisasAndGapsToUser", testCaseID, userDataRowID, mobileUserDataRowID, reportDataRowID);
		String reportId = createComplianceReportForInvestigation(testCaseID, userDataRowID, reportDataRowID);

		// Assign Lisas to specified user.
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String lisaNumberPrefix = reportName+"-LISA-";
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(mobileUserDataRowID);
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		String[] lisasToSelect = new String[lisaNumbers.length];
		for (int i = 0; i < lisaNumbers.length; i++) {
			lisasToSelect[i]  = lisaNumberPrefix+lisaNumbers[i];
		}
		reportInvestigationsPage.selectLisa(lisasToSelect);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);

		// Assign gaps to specified user.
		String gapNumberPrefix = reportName+"-Gap-";
		String[] gapsToSelect = new String[gapNumbers.length];
		for (int i = 0; i < gapNumbers.length; i++) {
			gapsToSelect[i]  = gapNumberPrefix+gapNumbers[i];
		}
		reportInvestigationsPage.selectMultipleGaps(gapsToSelect);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);

		if (isSingleUse) {
			this.cleanUp();
		}

		Log.info(String.format("Returning reportId=[%s]", reportId));
		return reportId;
	}

	public String createReportAndAssignLisasToUser(String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.method("createReportAndAssignLisasToUser", testCaseID, userDataRowID, mobileUserDataRowID, reportDataRowID);
		String reportId = createComplianceReportForInvestigation(testCaseID, userDataRowID, reportDataRowID);

		// Assign Lisas to specified user.
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String lisaNumberPrefix = reportName+"-LISA-";
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(mobileUserDataRowID);
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		String[] lisasToSelect = new String[] {lisaNumberPrefix+1, lisaNumberPrefix+2, lisaNumberPrefix+3};
		reportInvestigationsPage.selectLisa(lisasToSelect);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);

		if (isSingleUse) {
			this.cleanUp();
		}

		Log.info(String.format("Returning reportId=[%s]", reportId));
		return reportId;
	}

	public String createReportAndAssignGapsToUser(String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.method("createReportAndAssignLisasToUser", testCaseID, userDataRowID, mobileUserDataRowID, reportDataRowID);
		String reportId = createComplianceReportForInvestigation(testCaseID, userDataRowID, reportDataRowID);

		// Assign Gaps to specified user.
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String gapNumberPrefix = reportName+"-Gap-";
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(mobileUserDataRowID);
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		String[] gapsToSelect = new String[] {gapNumberPrefix+1, gapNumberPrefix+2, gapNumberPrefix+3};
		reportInvestigationsPage.selectMultipleGaps(gapsToSelect);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);

		if (isSingleUse) {
			this.cleanUp();
		}

		Log.info(String.format("Returning reportId=[%s]", reportId));
		return reportId;
	}

	protected void setSingleUse(Boolean singleUse) {
		this.isSingleUse = singleUse;
	}

	private void cleanUp() {
		if (this.driver != null) {
			driver.quit();
			driver = null;
		}
	}

	private String createComplianceReportForInvestigation(String testCaseID, Integer userDataRowID, Integer reportDataRowID) throws Exception {
		String reportId = "";
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, userDataRowID);
		complianceReportsPageAction.open(testCaseID, reportDataRowID);
		complianceReportsPageAction.createNewReport(EMPTY, reportDataRowID);
		reportId = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportName(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());
		return reportId;
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