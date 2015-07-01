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
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC0SUR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC2SUR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ManageSurveyorHistoriesPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SystemHistoryReportsPage;

/**
 * @author pmahajan
 * 
 */
public class SystemHistoryReportsPageTest extends SurveyorBaseTest {
	private static SystemHistoryReportsPage systemHistoryReportsPage = null;
	private static ManageSurveyorHistoriesPage manageSurveyorHistoriesPage = null;

	@BeforeClass
	public static void setupSystemHistoryReportsPageTest() {
		systemHistoryReportsPage = new SystemHistoryReportsPage(driver,
				baseURL, testSetup);
		PageFactory.initElements(driver, systemHistoryReportsPage);

		manageSurveyorHistoriesPage = new ManageSurveyorHistoriesPage(driver,
				baseURL, testSetup);
		PageFactory.initElements(driver, manageSurveyorHistoriesPage);
	}

	/**
	 * Test Case ID: RPT013 Test Description: Generate system history report as
	 * Administrator
	 * 
	 */
	@Test
	public void RPT013() {
		String rptTitle = "RPT013 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT013 Test Description: Generate system history report as Administrator, %s\n",
						rptTitle);

		// DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:MM a");
		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -10);
		String startDate = dateFormat.format(cal.getTime());
		System.out.println("Start Date : " + startDate);
		if (startDate.startsWith("0")) {
			startDate = startDate.replaceFirst("0*", "");
			System.out.println("New Start Date : " + startDate);
		}

		manageSurveyorHistoriesPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		manageSurveyorHistoriesPage.open();

		String surveyorUnit = SQACUS + " - " + SQACUSLOC + "0" + " - "
				+ SQACUSLOC0SUR;
		System.out.println(surveyorUnit);
		String note = "Automation Test Note " + testSetup.getRandomNumber();

		manageSurveyorHistoriesPage.addNewHistoryNote(surveyorUnit, note);
		assertTrue("Administrator not able to add new history note!",
				manageSurveyorHistoriesPage.findExistingHistoryNote(SQACUS,
						SQACUSLOC + "0", SQACUSLOC0SUR, note));

		systemHistoryReportsPage.open();

		date = new Date();
		String endDate = dateFormat.format(date);
		System.out.println("End Date : " + endDate);
		if (endDate.startsWith("0")) {
			endDate = endDate.replaceFirst("0*", "");
			System.out.println("New End Date : " + endDate);
		}

		systemHistoryReportsPage.addNewPDReport(rptTitle, TIMEZONEPT,
				surveyorUnit, startDate, endDate);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((systemHistoryReportsPage.checkActionStatus(rptTitle, PICDFADMIN)))
			assertTrue(systemHistoryReportsPage.findExistingReport(rptTitle,
					PICDFADMIN));
		else
			fail("\nTestcase RPT013 failed.\n");

		systemHistoryReportsPage.open();
		systemHistoryReportsPage.logout();
	}
	
	/**
	 * Test Case ID: RPT015A Test Description: Pagination - 10,25,50 and 100
	 * Reports selection on system history report screen
	 * 
	 */
	@Test
	public void RPT015A() {
		System.out
				.format("\nRunning RPT015A: Pagination - 10,25,50 and 100 Reports selection on system history report screen");

		systemHistoryReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		systemHistoryReportsPage.open();
		String paginationSetting25 = "25";
		String paginationSetting50 = "50";

		assertTrue(systemHistoryReportsPage
				.checkPaginationSetting(PAGINATIONSETTING));
		assertTrue(systemHistoryReportsPage
				.checkPaginationSetting(paginationSetting25));
		assertTrue(systemHistoryReportsPage
				.checkPaginationSetting(paginationSetting50));

		systemHistoryReportsPage.open();
		systemHistoryReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT028 Test Description: Generate system history report as
	 * Customer Administrator
	 * 
	 */
	@Test
	public void RPT028() {
		String rptTitle = "RPT028 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT028 Test Description: Generate system history report as Customer Administrator, %s\n",
						rptTitle);

		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -5);
		String startDate = dateFormat.format(cal.getTime());
		if (startDate.startsWith("0")) {
			startDate = startDate.replaceFirst("0*", "");
			System.out.println("New Start Date : " + startDate);
		}

		manageSurveyorHistoriesPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		manageSurveyorHistoriesPage.open();

		String surveyorUnit = SQACUS + " - " + SQACUSLOC + "2" + " - "
				+ SQACUSLOC2SUR;
		System.out.println(surveyorUnit);
		String note = "Automation Test Note " + testSetup.getRandomNumber();

		manageSurveyorHistoriesPage.addNewHistoryNote(surveyorUnit, note);
		assertTrue("Administrator not able to add new history note!",
				manageSurveyorHistoriesPage.findExistingHistoryNote(SQACUS,
						SQACUSLOC + "2", SQACUSLOC2SUR, note));

		manageSurveyorHistoriesPage.logout();
		systemHistoryReportsPage.login(SQACUSUA, USERPASSWORD);
		systemHistoryReportsPage.open();

		date = new Date();
		String endDate = dateFormat.format(date);
		if (endDate.startsWith("0")) {
			endDate = endDate.replaceFirst("0*", "");
			System.out.println("New End Date : " + endDate);
		}

		systemHistoryReportsPage.addNewPDReport(rptTitle, TIMEZONEPT,
				surveyorUnit, startDate, endDate);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		System.out.println(rptTitle);
		if ((systemHistoryReportsPage.checkActionStatus(rptTitle, SQACUSUA)))
			assertTrue(systemHistoryReportsPage.findExistingReport(rptTitle,
					SQACUSUA));
		else
			fail("\nTestcase RPT028 failed.\n");

		systemHistoryReportsPage.open();
		systemHistoryReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT035A Test Description: Click on Cancel button present on
	 * system history report screen
	 * 
	 */
	@Test
	public void RPT035A() {
		System.out
				.format("\nRunning RPT035A: Click on Cancel button present on system history report screen\n");

		systemHistoryReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		systemHistoryReportsPage.open();

		assertTrue(systemHistoryReportsPage.verifyCancelButtonFunctionality());

		systemHistoryReportsPage.open();
		systemHistoryReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT042 Test Description: Generate system history report for
	 * single day
	 * 
	 */
	@Test
	public void RPT042() {
		String rptTitle = "RPT042 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT042 Test Description: Generate system history report for single day, %s\n",
						rptTitle);

		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String startDate = dateFormat.format(cal.getTime());
		System.out.println("Start Date : " + startDate);
		if (startDate.startsWith("0")) {
			startDate = startDate.replaceFirst("0*", "");
			System.out.println("New Start Date : " + startDate);
		}

		manageSurveyorHistoriesPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		manageSurveyorHistoriesPage.open();

		String surveyorUnit = SQACUS + " - " + SQACUSLOC + "0" + " - "
				+ SQACUSLOC0SUR;
		System.out.println(surveyorUnit);
		String note = "Automation Test Note " + testSetup.getRandomNumber();

		manageSurveyorHistoriesPage.addNewHistoryNote(surveyorUnit, note);
		assertTrue("Administrator not able to add new history note!",
				manageSurveyorHistoriesPage.findExistingHistoryNote(SQACUS,
						SQACUSLOC + "0", SQACUSLOC0SUR, note));

		systemHistoryReportsPage.open();

		date = new Date();
		String endDate = dateFormat.format(date);
		System.out.println("End Date : " + endDate);
		if (endDate.startsWith("0")) {
			endDate = endDate.replaceFirst("0*", "");
			System.out.println("New End Date : " + endDate);
		}

		systemHistoryReportsPage.addNewPDReport(rptTitle, TIMEZONEPT,
				surveyorUnit, startDate, endDate);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((systemHistoryReportsPage.checkActionStatus(rptTitle, PICDFADMIN)))
			assertTrue(systemHistoryReportsPage.findExistingReport(rptTitle,
					PICDFADMIN));
		else
			fail("\nTestcase RPT042 failed.\n");

		systemHistoryReportsPage.open();
		systemHistoryReportsPage.logout();
	}
}