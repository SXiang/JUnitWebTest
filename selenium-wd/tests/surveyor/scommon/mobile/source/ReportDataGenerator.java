package surveyor.scommon.mobile.source;

import org.openqa.selenium.WebDriver;

import common.source.CheckedSupplier;
import common.source.Constants;
import common.source.ExceptionUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;
import common.source.WebDriverFactory;
import surveyor.dataaccess.source.Report;
import surveyor.scommon.actions.BaseActions;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.data.UserDataReader.UserDataRow;
import surveyor.scommon.entities.ReportInfoEntity;
import surveyor.scommon.source.ReportInvestigationsPage;

public class ReportDataGenerator {

	private static String EMPTY = BaseActions.EMPTY;
	private static Integer NOTSET = BaseActions.NOTSET;

	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static ReportInvestigationsPage reportInvestigationsPage;
	private static MobileLoginPage mobileLoginPage;
	private Boolean isSingleUse = false;					// when set to TRUE, object will be disposed after single use.
	private Boolean isReusable = false;          			// when set to TRUE, if there is data already present in DB then existing data will be used.
	private Boolean mobileEmulationDriverEnabled = false;   // turning this ON will spawn a web driver that emulates a mobile device.
	private WebDriver driver;

	private ReportDataGenerator() {
	}

	private ReportDataGenerator(Boolean emulationDriverEnabled) {
		if (emulationDriverEnabled) {
			this.driver = WebDriverFactory.getEmulationDriver(Constants.MobileEmulationDevice.GoogleNexus5);
		}

		this.mobileEmulationDriverEnabled = emulationDriverEnabled;
	}

	/**
	 * Returns a single use generator instance that disposes itself after create method has been called.
	 * @return - Generator instance.
	 * @throws Exception
	 */
	public static ReportDataGenerator newSingleUseGenerator() throws Exception {
		return newSingleUseGeneratorInternal(false /*isReusable*/);
	}

	/**
	 * Returns a single use generator instance that disposes itself after create method has been called.
	 * isReusable - if set to TRUE, if there is data already present in DB then existing data will be re-used.
	 * @return - Generator instance.
	 * @throws Exception
	 */
	public static ReportDataGenerator newSingleUseGenerator(boolean isReusable) throws Exception {
		return newSingleUseGeneratorInternal(isReusable);
	}

	private static ReportDataGenerator newSingleUseGeneratorInternal(boolean isReusable) throws Exception {
		ReportDataGenerator reportDataGenerator = new ReportDataGenerator();
		reportDataGenerator.initialize();
		reportDataGenerator.setSingleUse(true);
		reportDataGenerator.setIsReusable(isReusable);
		return reportDataGenerator;
	}

	public ReportInfoEntity createReportAndAssignLisasAndGapsToUser(String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID,
			String[] lisaNumbers, String[] gapNumbers) throws Exception {
		return withCleanupExecute(() -> createReportAndAssignLisasAndGapsToUser(testCaseID, userDataRowID, mobileUserDataRowID, reportDataRowID, lisaNumbers, gapNumbers,
				false /*selectAllLisas*/, false /*selectAllGaps*/));
	}

	public ReportInfoEntity createReportAndAssignLisasAndGapsToUser(String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		return withCleanupExecute(() -> createReportAndAssignLisasAndGapsToUser(testCaseID, userDataRowID, mobileUserDataRowID, reportDataRowID, null /*lisaNumbers*/,
				null /*gapNumbers*/, true /*selectAllLisas*/, true /*selectAllGaps*/));
	}

	public ReportInfoEntity createReportAndAssignLisasToUser(String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID,
			String[] lisaNumbers) throws Exception {
		return withCleanupExecute(() -> createReportAndAssignLisasToUser(testCaseID, userDataRowID, mobileUserDataRowID, reportDataRowID, lisaNumbers, false /*selectAllLisas*/));
	}

	public ReportInfoEntity createReportAndAssignLisasToUser(String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		return withCleanupExecute(() -> createReportAndAssignLisasToUser(testCaseID, userDataRowID, mobileUserDataRowID, reportDataRowID, null /*lisasToSelect*/, true /*selectAllLisas*/));
	}

	public ReportInfoEntity createReportAndAssignGapsToUser(String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		return withCleanupExecute(() -> createReportAndAssignGapsToUser(testCaseID, userDataRowID, mobileUserDataRowID, reportDataRowID, null /*gapsToSelect*/, true /*selectAllGaps*/));
	}

	public ReportInfoEntity createReportAndAssignGapsToUser(String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID,
			String[] gapNumbers) throws Exception {
		return withCleanupExecute(() -> createReportAndAssignGapsToUser(testCaseID, userDataRowID, mobileUserDataRowID, reportDataRowID, null /*gapNumbers*/, false /*selectAllGaps*/));
	}

	private ReportInfoEntity createReportAndAssignLisasAndGapsToUser(String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID,
			String[] lisaNumbers, String[] gapNumbers, Boolean selectAllLisas, Boolean selectAllGaps) throws Exception {
		Log.method("createReportAndAssignLisasAndGapsToUser", testCaseID, userDataRowID, mobileUserDataRowID, reportDataRowID);

		if (isReusable) {
			ReportInfoEntity rptEntity = getMatchingReportEntityFromDB(testCaseID);
			if (rptEntity != null) {
				Log.info(String.format("isReusable set to TRUE. Found existing test data in DB. Reusing data from DB. ReportInfoEntity -> %s", rptEntity));
				return rptEntity;
			}
		}

		String reportId = createComplianceReportForInvestigation(testCaseID, userDataRowID, reportDataRowID);

		// Assign Lisas to specified user.
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String lisaNumberPrefix = reportName+"-LISA-";
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(mobileUserDataRowID);
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		selectSpecifiedLisas(lisaNumbers, selectAllLisas, lisaNumberPrefix);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);

		// Assign gaps to specified user.
		String gapNumberPrefix = reportName+"-Gap-";
		selectSpecifiedGaps(gapNumbers, selectAllGaps, gapNumberPrefix);
			reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);

		Log.info(String.format("Returning reportId=[%s]", reportId));
		return new ReportInfoEntity(ComplianceReportsPageActions.workingDataRow.get().title, reportName);
	}

	private ReportInfoEntity createReportAndAssignLisasToUser(String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID,
			String[] lisaNumbers, Boolean selectAllLisas) throws Exception {
		Log.method("createReportAndAssignLisasToUser", testCaseID, userDataRowID, mobileUserDataRowID, reportDataRowID);
		if (isReusable) {
			ReportInfoEntity rptEntity = getMatchingReportEntityFromDB(testCaseID);
			if (rptEntity != null) {
				return rptEntity;
			}
		}

		String reportId = createComplianceReportForInvestigation(testCaseID, userDataRowID, reportDataRowID);

		// Assign Lisas to specified user.
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String lisaNumberPrefix = reportName+"-LISA-";
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(mobileUserDataRowID);
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		selectSpecifiedLisas(lisaNumbers, selectAllLisas, lisaNumberPrefix);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);

		Log.info(String.format("Returning reportId=[%s]", reportId));
		return new ReportInfoEntity(ComplianceReportsPageActions.workingDataRow.get().title, reportName);
	}

	private ReportInfoEntity createReportAndAssignGapsToUser(String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID,
			String[] gapNumbers, Boolean selectAllGaps) throws Exception {
		Log.method("createReportAndAssignLisasToUser", testCaseID, userDataRowID, mobileUserDataRowID, reportDataRowID);
		if (isReusable) {
			ReportInfoEntity rptEntity = getMatchingReportEntityFromDB(testCaseID);
			if (rptEntity != null) {
				return rptEntity;
			}
		}

		String reportId = createComplianceReportForInvestigation(testCaseID, userDataRowID, reportDataRowID);

		// Assign Gaps to specified user.
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String gapNumberPrefix = reportName+"-Gap-";
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(mobileUserDataRowID);
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		selectSpecifiedGaps(gapNumbers, selectAllGaps, gapNumberPrefix);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);

		Log.info(String.format("Returning reportId=[%s]", reportId));
		return new ReportInfoEntity(ComplianceReportsPageActions.workingDataRow.get().title, reportName);
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

	private ReportInfoEntity getMatchingReportEntityFromDB(String reportTitlePrefix) {
		Log.method("getMatchingReportEntityFromDB", reportTitlePrefix);
		Report report = new Report().getTitleLike(reportTitlePrefix);
		if (report != null) {
			String reportName = "CR-"+report.getId().substring(0,6).toUpperCase();
			String reportTitle = report.getReportTitle();
			Log.method(String.format("Found report in DB -> [reportName=%s; reportTitle=%s]", reportName, reportTitle));
			return new ReportInfoEntity(reportTitle, reportName);
		}

		return null;
	}

	private void selectSpecifiedGaps(String[] gapNumbers, Boolean selectAllGaps, String gapNumberPrefix) {
		if (selectAllGaps) {
			reportInvestigationsPage.selectAllGaps();
		} else {
			String[] gapsToSelect = new String[gapNumbers.length];
			for (int i = 0; i < gapNumbers.length; i++) {
				gapsToSelect[i]  = gapNumberPrefix+gapNumbers[i];
			}

			reportInvestigationsPage.selectMultipleGaps(gapsToSelect);
		}
	}

	private void selectSpecifiedLisas(String[] lisaNumbers, Boolean selectAllLisas, String lisaNumberPrefix) {
		if (selectAllLisas) {
			reportInvestigationsPage.selectAllLisas();
		} else {
			String[] lisasToSelect = new String[lisaNumbers.length];
			for (int i = 0; i < lisaNumbers.length; i++) {
				lisasToSelect[i]  = lisaNumberPrefix+lisaNumbers[i];
			}
			reportInvestigationsPage.selectLisas(lisasToSelect);
		}
	}

	private ReportInfoEntity withCleanupExecute(CheckedSupplier<ReportInfoEntity> invokeMethod) {
		ReportInfoEntity rptInfoEntity = null;
		try {
			rptInfoEntity = invokeMethod.get();
		} catch (Exception ex) {
			Log.error(String.format("Error executing method. Exception -> %s", ExceptionUtility.getStackTraceString(ex)));
		} finally {
			if (isSingleUse) {
				this.cleanUp();
			}
		}

		return rptInfoEntity;
	}

	private void cleanUp() {
		if (this.driver != null) {
			driver.quit();
			driver = null;
		}
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

	protected void setSingleUse(Boolean singleUse) {
		this.isSingleUse = singleUse;
	}

	public Boolean getIsReusable() {
		return isReusable;
	}

	public void setIsReusable(Boolean isReusable) {
		this.isReusable = isReusable;
	}
}