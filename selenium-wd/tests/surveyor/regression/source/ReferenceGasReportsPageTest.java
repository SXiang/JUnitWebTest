/**
 *
 */
package surveyor.regression.source;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.SQACUS;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSU;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEMT;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONECT;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEET;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOCSUR;
import static surveyor.scommon.source.SurveyorConstants.SQAPICLOC4SURANA;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import common.source.DateUtility;
import common.source.DateUtility.DatePart;
import common.source.Log;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.ReferenceGasReportsPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;

/**
 * @author pmahajan
 *
 */
@RunWith(SurveyorTestRunner.class)
public class ReferenceGasReportsPageTest extends SurveyorBaseTest {

	private static ReferenceGasReportsPage referenceGasReportsPage = null;
	private static DateFormat dateFormat = new SimpleDateFormat("dd");
	private static final int START_DATE_DAY = 12;
	private static final int START_DATE_SINGLE_DAY = 14;
	private static final int START_DATE_MONTH = 12;
	private static final int START_DATE_YEAR = 2015;

	/**
	 * This method is called by the 'main' thread
	 */
	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects(); // ensures TestSetup and TestContext are initialized before Page object creation.
	}

	/**
	 * This method is called by the 'worker' thread
	 *
	 * @throws java.lang.Exception
	 */
	@Before
	public void beforeTest() throws Exception {
		initializeTestObjects();

		PageObjectFactory pageObjectFactory = new PageObjectFactory();
		referenceGasReportsPage = pageObjectFactory.getReferenceGasReportsPage();
		PageFactory.initElements(getDriver(), referenceGasReportsPage);
	}

	/**
	 * Test Case ID: TC159 Test Description: Generate Generate Reference Gas Capture Report as Administrator
	 *
	 */
	@Test
	public void TC159_GenerateRefGasRpt_PicarroAdmin() {
		String rptTitle = "TC159 Report" + getTestSetup().getRandomNumber();
		Log.info("\nRunning TC159 Test Description: Generate Reference Gas Capture Report as Administrator. Report title - " + rptTitle);

		String surveyorUnit = SQACUS + "-" + SQACUSLOC + "-" + SQACUSLOCSUR + "-" + SQAPICLOC4SURANA;
		String startDate = getStartDate();
		String endDate = getEndDate();
		Integer monthDiff = getNumberOfPreMonths() + 1;

		ArrayList<String> inputList=new ArrayList<String>();
		inputList.add(rptTitle);
		inputList.add(SQACUS);
		inputList.add(SQACUSLOCSUR);
		inputList.add(startDate);
		inputList.add(endDate);

		referenceGasReportsPage.login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		referenceGasReportsPage.open();

		referenceGasReportsPage.addNewReport(rptTitle, TIMEZONEET, surveyorUnit, startDate, endDate, monthDiff, 0);

		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());
		if ((referenceGasReportsPage.checkActionStatus(rptTitle, PICDFADMIN))) {
			assertTrue(referenceGasReportsPage.findReport(rptTitle, PICDFADMIN));
			assertTrue(referenceGasReportsPage.validatePdfFiles(rptTitle, getTestSetup().getDownloadPath()));
			assertTrue(referenceGasReportsPage.verifyStaticTextInPDF(getTestSetup().getDownloadPath(), rptTitle));
			assertTrue(referenceGasReportsPage.verifyUserInputInPDF(getTestSetup().getDownloadPath(), rptTitle,inputList));
			assertTrue(referenceGasReportsPage.verifyResultTable(getTestSetup().getDownloadPath(), rptTitle));
		} else
			fail("\nTestcase TC159 failed.\n");

		referenceGasReportsPage.open();
		referenceGasReportsPage.logout();
	}

	/**
	 * Test Case ID: TC162 Test Description: Pagination - 10,25,50 and 100 Reports selection on reference gas report screen
	 *
	 */
	@Test
	public void TC162_RefGasRpt_Pagination() {
		Log.info("\nRunning RPT015A: Pagination - 10,25,50 and 100 Reports selection on reference gas report screen");

		referenceGasReportsPage.login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		referenceGasReportsPage.open();
		String paginationSetting25 = "25";
		String paginationSetting50 = "50";

		assertTrue(referenceGasReportsPage.checkPaginationSetting(PAGINATIONSETTING));
		assertTrue(!(referenceGasReportsPage.getNumberofRecords() > Integer.parseInt(PAGINATIONSETTING)));

		assertTrue(referenceGasReportsPage.checkPaginationSetting(paginationSetting25));
		assertTrue(!(referenceGasReportsPage.getNumberofRecords() > Integer.parseInt(paginationSetting25)));

		assertTrue(referenceGasReportsPage.checkPaginationSetting(paginationSetting50));
		assertTrue(!(referenceGasReportsPage.getNumberofRecords() > Integer.parseInt(paginationSetting50)));

		referenceGasReportsPage.open();
		referenceGasReportsPage.logout();
	}

	/**
	 * Test Case ID: TC179 Test Description: Generate Reference Gas Capture Report as customer admin
	 *
	 */
	@Test
	public void TC179_GenerateRefGasRpt_CustAdmin() {
		String rptTitle = "TC179 Report" + getTestSetup().getRandomNumber();
		Log.info("\nRunning TC179 Test Description: Generate Reference Gas Capture Report as customer admin. Report title - " + rptTitle);

		String surveyorUnit = SQACUS + " - " + SQACUSLOC + " - " + SQACUSLOCSUR;
		String startDate = getStartDate();
		String endDate = getEndDate();
		Integer monthDiff = getNumberOfPreMonths() + 1;

		ArrayList<String> inputList=new ArrayList<String>();
		inputList.add(rptTitle);
		inputList.add(SQACUS);
		inputList.add(SQACUSLOCSUR);
		inputList.add(startDate);
		inputList.add(endDate);

		referenceGasReportsPage.login(SQACUSUA, USERPASSWORD);
		referenceGasReportsPage.open();

		referenceGasReportsPage.addNewReport(rptTitle, TIMEZONEMT, surveyorUnit, startDate, endDate, monthDiff, 0);
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());

		if ((referenceGasReportsPage.checkActionStatus(rptTitle, SQACUSUA))) {
			assertTrue(referenceGasReportsPage.findReport(rptTitle, SQACUSUA));
			assertTrue(referenceGasReportsPage.validatePdfFiles(rptTitle, getTestSetup().getDownloadPath()));
			assertTrue(referenceGasReportsPage.verifyStaticTextInPDF(getTestSetup().getDownloadPath(), rptTitle));
			assertTrue(referenceGasReportsPage.verifyUserInputInPDF(getTestSetup().getDownloadPath(), rptTitle,inputList));
			assertTrue(referenceGasReportsPage.verifyResultTable(getTestSetup().getDownloadPath(), rptTitle));
		} else
			fail("\nTestcase TC179 failed. Report not downloaded successfully!\n");

		referenceGasReportsPage.open();
		referenceGasReportsPage.logout();
	}

	/**
	 * Test Case ID: TC1297 Test Description:
	 * Software version present on report PDF should match with UI software version
	 *
	 */
	@Test
	public void TC1297_RefGasRpt_VerifyWebAppAndPDFVersion() {
		String rptTitle = "TC1297 Report" + getTestSetup().getRandomNumber();
		Log.info("\nRunning TC1297 Test Description: Software version present on report PDF should match with UI software version. Report title - " + rptTitle);

		String surveyorUnit = SQACUS + " - " + SQACUSLOC + " - " + SQACUSLOCSUR;
		String startDate = getStartDate();
		String endDate = getEndDate();
		Integer monthDiff = getNumberOfPreMonths() + 1;

		ArrayList<String> inputList=new ArrayList<String>();
		inputList.add(rptTitle);
		inputList.add(SQACUS);
		inputList.add(SQACUSLOCSUR);
		inputList.add(startDate);
		inputList.add(endDate);

		referenceGasReportsPage.login(SQACUSUA, USERPASSWORD);
		referenceGasReportsPage.open();

		String webAppVersionNumber = referenceGasReportsPage.getWebAppVersion();

		referenceGasReportsPage.addNewReport(rptTitle, TIMEZONEMT, surveyorUnit, startDate, endDate, monthDiff, 0);

		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());

		if ((referenceGasReportsPage.checkActionStatus(rptTitle, SQACUSUA))) {
			String pdfSoftwareVersion = referenceGasReportsPage.getSoftwareVersionFromPDF(rptTitle, getTestSetup().getDownloadPath());
			Log.info(String.format("Comparing web app version and PDF software version. WebAppVersion = '%s', PDFSoftwareVersion = '%s'",
					webAppVersionNumber, pdfSoftwareVersion));
			assertTrue(webAppVersionNumber.equals(pdfSoftwareVersion));

		} else
			fail("\nTestcase TC1297 failed. Report not downloaded successfully!\n");

		referenceGasReportsPage.open();
		referenceGasReportsPage.logout();
	}

	/**
	 * Test Case ID: TC187 Test Description: Click on Cancel button present on reference gas report screen
	 *
	 */
	@Test
	public void TC187_RefGasRptScreen_CancelBtn() {
		Log.info("\nRunning TC187: Click on Cancel button present on reference gas report screen\n");

		referenceGasReportsPage.login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		referenceGasReportsPage.open();

		assertTrue(referenceGasReportsPage.verifyCancelButtonFunctionality());

		referenceGasReportsPage.open();
	}

	/**
	 * Test Case ID: TC196 Test Description: Generate Generate Reference Gas Capture Report for single day
	 *
	 */
	@Test
	public void TC196_GenerateRefGasRpt_SingleDay() {
		String rptTitle = "TC196 Report" + getTestSetup().getRandomNumber();
		String startDate = getSingleDayStartDate();
		String endDate = startDate;
		Integer monthDiff = getNumberOfPreMonths() + 1;

		Log.info("\nRunning TC196 Test Description: Generate Reference Gas Capture Report for single day. Report title - " + rptTitle);

		String surveyorUnit = SQACUS + "-" + SQACUSLOC + "-" + SQACUSLOCSUR + "-" + SQAPICLOC4SURANA;

		referenceGasReportsPage.login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		referenceGasReportsPage.open();

		referenceGasReportsPage.addNewReport(rptTitle, TIMEZONEPT, surveyorUnit, startDate, endDate, monthDiff, monthDiff);

		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());

		if ((referenceGasReportsPage.checkActionStatus(rptTitle, PICDFADMIN))) {
			assertTrue(referenceGasReportsPage.findReport(rptTitle, PICDFADMIN));
			assertTrue(referenceGasReportsPage.validatePdfFiles(rptTitle, getTestSetup().getDownloadPath()));
			assertTrue(referenceGasReportsPage.verifyResultTable(getTestSetup().getDownloadPath(), rptTitle));
		} else
			fail("\nTestcase TC196 failed.\n");

		referenceGasReportsPage.open();
		referenceGasReportsPage.logout();
	}

	/**
	 * Test Case ID: TC515 Test Description: Generate Reference Gas Capture Report as customer supervisor
	 *
	 */
	@Test
	public void TC515_GenerateRefGasRpt_CustSupervisor() {
		String rptTitle = "TC515 Report" + getTestSetup().getRandomNumber();
		Log.info("\nRunning TC515 Test Description: Generate Reference Gas Capture Report as customer supervisor. Report Title - " + rptTitle);

		String surveyorUnit = SQACUS + " - " + SQACUSLOC + " - " + SQACUSLOCSUR;
		String startDate = getStartDate();
		String endDate = getEndDate();
		Integer monthDiff = getNumberOfPreMonths() + 1;

		ArrayList<String> inputList=new ArrayList<String>();
		inputList.add(rptTitle);
		inputList.add(SQACUS);
		inputList.add(SQACUSLOCSUR);
		inputList.add(startDate);
		inputList.add(endDate);

		referenceGasReportsPage.login(SQACUSSU, USERPASSWORD);
		referenceGasReportsPage.open();

		referenceGasReportsPage.addNewReport(rptTitle, TIMEZONECT, surveyorUnit, startDate, endDate, monthDiff, 0);

		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());

		if ((referenceGasReportsPage.checkActionStatus(rptTitle, SQACUSSU))) {
			assertTrue(referenceGasReportsPage.findReport(rptTitle, SQACUSSU));
			assertTrue(referenceGasReportsPage.validatePdfFiles(rptTitle, getTestSetup().getDownloadPath()));
			assertTrue(referenceGasReportsPage.verifyStaticTextInPDF(getTestSetup().getDownloadPath(), rptTitle));
			assertTrue(referenceGasReportsPage.verifyUserInputInPDF(getTestSetup().getDownloadPath(), rptTitle,inputList));
			assertTrue(referenceGasReportsPage.verifyResultTable(getTestSetup().getDownloadPath(), rptTitle));
		} else
			fail("\nTestcase RPT515 failed. Either Report failed to generate or download!\n");

		referenceGasReportsPage.open();
		referenceGasReportsPage.logout();
	}

	private String getEndDate() {
		Date date = new Date();
		String endDate = dateFormat.format(date);
		if (endDate.startsWith("0")) {
			endDate = endDate.replaceFirst("0*", "");
		}
		return endDate;
	}

	private Integer getNumberOfPreMonths() {
		LocalDate nowDate = LocalDate.now();
		// Diff in months is computed based on number of days between the 2 dates.
		// Use the first day of month as the nowDate() to get the number of times the back button on calendar needs to be clicked.
		return Integer.parseInt(DateUtility.getDateDiff(LocalDate.of(START_DATE_YEAR, START_DATE_MONTH, START_DATE_DAY),
				LocalDate.of(nowDate.getYear(), nowDate.getMonthValue(), 1 /*first day of month*/), DatePart.Month).toString());
	}

	private String getStartDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(START_DATE_YEAR, START_DATE_MONTH, START_DATE_DAY);
		String startDate = dateFormat.format(cal.getTime());
		if (startDate.startsWith("0")) {
			startDate = startDate.replaceFirst("0*", "");
		}
		return startDate;
	}

	private String getSingleDayStartDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(START_DATE_YEAR, START_DATE_MONTH, START_DATE_SINGLE_DAY);
		String startDate = dateFormat.format(cal.getTime());
		if (startDate.startsWith("0")) {
			startDate = startDate.replaceFirst("0*", "");
		}
		return startDate;
	}
}