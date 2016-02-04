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
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOCSUR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSU;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONECT;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEET;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEMT;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
import surveyor.scommon.source.ManageSurveyorHistoriesPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.SystemHistoryReportsPage;

/**
 * @author pmahajan
 * 
 */
@RunWith(SurveyorTestRunner.class)
public class SystemHistoryReportsPageTest extends SurveyorBaseTest {
	private static SystemHistoryReportsPage systemHistoryReportsPage = null;
	private static ManageSurveyorHistoriesPage manageSurveyorHistoriesPage = null;

	@BeforeClass
	public static void setupSystemHistoryReportsPageTest() {
		systemHistoryReportsPage = new SystemHistoryReportsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, systemHistoryReportsPage);

		manageSurveyorHistoriesPage = new ManageSurveyorHistoriesPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageSurveyorHistoriesPage);
	}

	/**
	 * Test Case ID: TC158 Test Description: Generate system history report as Administrator
	 * 
	 */
	@Test
	public void TC158_SysHisRpt_PicarroAdmin() {
		String rptTitle = "TC158 Report" + testSetup.getRandomNumber();
		Log.info("\nRunning TC158 Test Description: Generate system history report as Administrator, %s\n" + rptTitle);

		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -10);
		String startDate = dateFormat.format(cal.getTime());
		if (startDate.startsWith("0")) {
			startDate = startDate.replaceFirst("0*", "");
		}
		manageSurveyorHistoriesPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		manageSurveyorHistoriesPage.open();

		String surveyorUnit = SQACUS + " - " + SQACUSLOC + " - " + SQACUSLOCSUR;
		String note = "Automation Test Note " + testSetup.getRandomNumber();

		manageSurveyorHistoriesPage.addNewHistoryNote(surveyorUnit, note);
		assertTrue("Administrator not able to add new history note!", manageSurveyorHistoriesPage.findExistingHistoryNote(SQACUS, SQACUSLOC, SQACUSLOCSUR, note));
		systemHistoryReportsPage.open();
		date = new Date();
		String endDate = dateFormat.format(date);
		if (endDate.startsWith("0")) {
			endDate = endDate.replaceFirst("0*", "");
		}

		systemHistoryReportsPage.addNewPDReport(rptTitle, TIMEZONEPT, surveyorUnit, startDate, endDate);
		ArrayList<String> inputList = new ArrayList<String>();
		inputList.add(rptTitle);
		inputList.add(SQACUSLOC);
		inputList.add(SQACUSLOCSUR);
		inputList.add(startDate);
		inputList.add(endDate);

		if ((systemHistoryReportsPage.checkActionStatus(rptTitle, PICDFADMIN))) {
			assertTrue(systemHistoryReportsPage.findExistingReport(rptTitle, PICDFADMIN));
			assertTrue(systemHistoryReportsPage.validatePdfFiles(rptTitle, testSetup.getDownloadPath()));
			assertTrue(systemHistoryReportsPage.verifyStaticTextinPDF(testSetup.getDownloadPath(), rptTitle));
			assertTrue(systemHistoryReportsPage.verifyUserInputInPDF(testSetup.getDownloadPath(), rptTitle, inputList));
			assertTrue(systemHistoryReportsPage.verifyNotesTable(testSetup.getDownloadPath(), rptTitle));

		} else
			fail("\nTestcase TC158 failed.\n");

		systemHistoryReportsPage.open();
		systemHistoryReportsPage.logout();
	}

	/**
	 * Test Case ID: TC161 Test Description: Pagination - 10,25,50 and 100 Reports selection on system history report screen
	 * 
	 */
	@Test
	public void TC161_SysHisRpt_Pagination() {
		Log.info("\nRunning TC161: Pagination - 10,25,50 and 100 Reports selection on system history report screen");

		systemHistoryReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		systemHistoryReportsPage.open();
		String paginationSetting25 = "25";
		String paginationSetting50 = "50";

		assertTrue(systemHistoryReportsPage.checkPaginationSetting(PAGINATIONSETTING));
		assertTrue(systemHistoryReportsPage.checkPaginationSetting(paginationSetting25));
		assertTrue(systemHistoryReportsPage.checkPaginationSetting(paginationSetting50));

		systemHistoryReportsPage.open();
		systemHistoryReportsPage.logout();
	}

	/**
	 * Test Case ID: TC178 Test Description: Generate system history report as Customer Administrator
	 * 
	 */
	@Test
	public void TC178_SysHisRpt_CustAdmin() {
		String rptTitle = "TC178 Report" + testSetup.getRandomNumber();
		Log.info("\nRunning TC178 Test Description: Generate system history report as Customer Administrator, %s\n" + rptTitle);

		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -5);
		String startDate = dateFormat.format(cal.getTime());
		if (startDate.startsWith("0")) {
			startDate = startDate.replaceFirst("0*", "");
		}

		manageSurveyorHistoriesPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		manageSurveyorHistoriesPage.open();

		String surveyorUnit = SQACUS + " - " + SQACUSLOC + " - " + SQACUSLOCSUR;
		String note = "Automation Test Note " + testSetup.getRandomNumber();

		manageSurveyorHistoriesPage.addNewHistoryNote(surveyorUnit, note);
		assertTrue("Administrator not able to add new history note!", manageSurveyorHistoriesPage.findExistingHistoryNote(SQACUS, SQACUSLOC, SQACUSLOCSUR, note));

		manageSurveyorHistoriesPage.logout();
		systemHistoryReportsPage.login(SQACUSUA, USERPASSWORD);
		systemHistoryReportsPage.open();

		date = new Date();
		String endDate = dateFormat.format(date);
		if (endDate.startsWith("0")) {
			endDate = endDate.replaceFirst("0*", "");
		}

		ArrayList<String> inputList = new ArrayList<String>();
		inputList.add(rptTitle);
		inputList.add(SQACUSLOC);
		inputList.add(SQACUSLOCSUR);
		inputList.add(startDate);
		inputList.add(endDate);

		systemHistoryReportsPage.addNewPDReport(rptTitle, TIMEZONEET, surveyorUnit, startDate, endDate);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((systemHistoryReportsPage.checkActionStatus(rptTitle, SQACUSUA))) {
			assertTrue(systemHistoryReportsPage.findExistingReport(rptTitle, SQACUSUA));
			assertTrue(systemHistoryReportsPage.validatePdfFiles(rptTitle, testSetup.getDownloadPath()));
			assertTrue(systemHistoryReportsPage.verifyStaticTextinPDF(testSetup.getDownloadPath(), rptTitle));
			assertTrue(systemHistoryReportsPage.verifyUserInputInPDF(testSetup.getDownloadPath(), rptTitle, inputList));
			assertTrue(systemHistoryReportsPage.verifyNotesTable(testSetup.getDownloadPath(), rptTitle));
		}

		else
			fail("\nTestcase TC178 failed.\n");

		systemHistoryReportsPage.open();
		systemHistoryReportsPage.logout();
	}

	/**
	 * Test Case ID: TC186 Test Description: Click on Cancel button present on system history report screen
	 * 
	 */
	@Test
	public void TC186_SysHisRpt_CancelBtn() {
		Log.info("\nRunning TC186: Click on Cancel button present on system history report screen\n");

		systemHistoryReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		systemHistoryReportsPage.open();

		assertTrue(systemHistoryReportsPage.verifyCancelButtonFunctionality());

		systemHistoryReportsPage.open();
		systemHistoryReportsPage.logout();
	}

	/**
	 * Test Case ID: TC195 Test Description: Generate system history report for single day
	 * 
	 */
	@Test
	public void TC195_SysHisRpt_SingleDay() {
		String rptTitle = "TC195 Report" + testSetup.getRandomNumber();
		Log.info("\nRunning TC195 Test Description: Generate system history report for single day, %s\n" + rptTitle);

		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String startDate = dateFormat.format(cal.getTime());
		if (startDate.startsWith("0")) {
			startDate = startDate.replaceFirst("0*", "");
		}

		String surveyorUnit = SQACUS + " - " + SQACUSLOC + " - " + SQACUSLOCSUR;
		String note = "Automation Test Note " + testSetup.getRandomNumber();

		manageSurveyorHistoriesPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		manageSurveyorHistoriesPage.open();

		manageSurveyorHistoriesPage.addNewHistoryNote(surveyorUnit, note);
		assertTrue("Administrator not able to add new history note!", manageSurveyorHistoriesPage.findExistingHistoryNote(SQACUS, SQACUSLOC, SQACUSLOCSUR, note));

		systemHistoryReportsPage.open();
		date = new Date();
		String endDate = dateFormat.format(date);
		if (endDate.startsWith("0")) {
			endDate = endDate.replaceFirst("0*", "");
		}

		ArrayList<String> inputList = new ArrayList<String>();
		inputList.add(rptTitle);
		inputList.add(SQACUSLOC);
		inputList.add(SQACUSLOCSUR);
		inputList.add(startDate);
		inputList.add(endDate);

		systemHistoryReportsPage.addNewPDReport(rptTitle, TIMEZONEMT, surveyorUnit, startDate, endDate);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		if ((systemHistoryReportsPage.checkActionStatus(rptTitle, PICDFADMIN))) {
			assertTrue(systemHistoryReportsPage.findExistingReport(rptTitle, PICDFADMIN));
			assertTrue(systemHistoryReportsPage.validatePdfFiles(rptTitle, testSetup.getDownloadPath()));
			assertTrue(systemHistoryReportsPage.verifyStaticTextinPDF(testSetup.getDownloadPath(), rptTitle));
			assertTrue(systemHistoryReportsPage.verifyUserInputInPDF(testSetup.getDownloadPath(), rptTitle, inputList));
			assertTrue(systemHistoryReportsPage.verifyNotesTable(testSetup.getDownloadPath(), rptTitle));			
		} else
			fail("\nTestcase TC195 failed.\n");

		assertTrue(systemHistoryReportsPage.validatePdfFiles(rptTitle, testSetup.getDownloadPath()));

		systemHistoryReportsPage.open();
		systemHistoryReportsPage.logout();
	}

	/**
	 * Test Case ID: TC516 Test Description: Generate system history report as Customer Supervisor
	 * 
	 */
	@Test
	public void TC516_SysHisRpt_CustSupervisor() {
		String rptTitle = "TC516 Report" + testSetup.getRandomNumber();
		Log.info("\nRunning TC516 Test Description: Generate system history report as Customer Supervisor, %s\n" + rptTitle);

		String surveyorUnit = SQACUS + " - " + SQACUSLOC + " - " + SQACUSLOCSUR;
		String note = "Automation Test Note " + testSetup.getRandomNumber();

		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -10);
		String startDate = dateFormat.format(cal.getTime());
		if (startDate.startsWith("0")) {
			startDate = startDate.replaceFirst("0*", "");
		}

		manageSurveyorHistoriesPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		manageSurveyorHistoriesPage.open();

		manageSurveyorHistoriesPage.addNewHistoryNote(surveyorUnit, note);
		assertTrue("Administrator not able to add new history note!", manageSurveyorHistoriesPage.findExistingHistoryNote(SQACUS, SQACUSLOC, SQACUSLOCSUR, note));

		manageSurveyorHistoriesPage.logout();
		systemHistoryReportsPage.login(SQACUSSU, USERPASSWORD);
		systemHistoryReportsPage.open();

		date = new Date();
		String endDate = dateFormat.format(date);
		if (endDate.startsWith("0")) {
			endDate = endDate.replaceFirst("0*", "");
		}
		ArrayList<String> inputList = new ArrayList<String>();
		inputList.add(rptTitle);
		inputList.add(SQACUSLOC);
		inputList.add(SQACUSLOCSUR);
		inputList.add(startDate);
		inputList.add(endDate);

		systemHistoryReportsPage.addNewPDReport(rptTitle, TIMEZONECT, surveyorUnit, startDate, endDate);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((systemHistoryReportsPage.checkActionStatus(rptTitle, SQACUSSU))) {
			assertTrue(systemHistoryReportsPage.findExistingReport(rptTitle, SQACUSSU));
			assertTrue("Defect DE695!", systemHistoryReportsPage.validatePdfFiles(rptTitle, testSetup.getDownloadPath()));
			assertTrue(systemHistoryReportsPage.verifyStaticTextinPDF(testSetup.getDownloadPath(), rptTitle));
			assertTrue(systemHistoryReportsPage.verifyUserInputInPDF(testSetup.getDownloadPath(), rptTitle, inputList));
			assertTrue(systemHistoryReportsPage.verifyNotesTable(testSetup.getDownloadPath(), rptTitle));
		} else
			fail("\nTestcase TC516 failed.\n");

		systemHistoryReportsPage.open();
		systemHistoryReportsPage.logout();
	}
}