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
import static surveyor.scommon.source.SurveyorConstants.SQACUSSU;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEMT;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONECT;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEET;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOCSUR;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ReferenceGasReportsPage;
import surveyor.scommon.source.SurveyorBaseTest;

/**
 * @author pmahajan
 * 
 */
public class ReferenceGasReportsPageTest extends SurveyorBaseTest {
	private static ReferenceGasReportsPage referenceGasReportsPage = null;

	@BeforeClass
	public static void setupReferenceGasReportsPageTest() {
		referenceGasReportsPage = new ReferenceGasReportsPage(driver, baseURL,
				testSetup);
		PageFactory.initElements(driver, referenceGasReportsPage);
	}

	/**
	 * Test Case ID: TC159 Test Description: Generate Generate Reference Gas
	 * Capture Report as Administrator
	 * 
	 */
	@Test
	public void TC159_GenerateRefGasRpt_PicarroAdmin() {
		String rptTitle = "TC159 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning TC159 Test Description: Generate Reference Gas Capture Report as Administrator, %s\n",
						rptTitle);

		String surveyorUnit = SQACUS + " - " + SQACUSLOC + " - " + SQACUSLOCSUR;
		System.out.println(surveyorUnit);

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
		date = new Date();
		String endDate = dateFormat.format(date);
		System.out.println("End Date : " + endDate);
		if (endDate.startsWith("0")) {
			endDate = endDate.replaceFirst("0*", "");
			System.out.println("New End Date : " + endDate);
		}

		referenceGasReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		referenceGasReportsPage.open();

		referenceGasReportsPage.addNewReport(rptTitle, TIMEZONEET,
				surveyorUnit, startDate, endDate, 7, 0);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((referenceGasReportsPage.checkActionStatus(rptTitle, PICDFADMIN))) {
			assertTrue(referenceGasReportsPage.findReport(rptTitle, PICDFADMIN));
			assertTrue(referenceGasReportsPage.validatePdfFiles(rptTitle,
							testSetup.getDownloadPath()));
		} else
			fail("\nTestcase TC159 failed.\n");

		referenceGasReportsPage.open();
		referenceGasReportsPage.logout();
	}

	/**
	 * Test Case ID: TC162 Test Description: Pagination - 10,25,50 and 100
	 * Reports selection on reference gas report screen
	 * 
	 */
	@Test
	public void TC162_RefGasRpt_Pagination() {
		System.out
				.format("\nRunning RPT015A: Pagination - 10,25,50 and 100 Reports selection on reference gas report screen");

		referenceGasReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		referenceGasReportsPage.open();
		String paginationSetting25 = "25";
		String paginationSetting50 = "50";

		assertTrue(referenceGasReportsPage
				.checkPaginationSetting(PAGINATIONSETTING));
		assertTrue(referenceGasReportsPage
				.checkPaginationSetting(paginationSetting25));
		assertTrue(referenceGasReportsPage
				.checkPaginationSetting(paginationSetting50));

		referenceGasReportsPage.open();
		referenceGasReportsPage.logout();
	}

	/**
	 * Test Case ID: TC179 Test Description: Generate Reference Gas Capture
	 * Report as customer admin
	 * 
	 */
	@Test
	public void TC179_GenerateRefGasRpt_CustAdmin() {
		String rptTitle = "TC179 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning TC179 Test Description: Generate Reference Gas Capture Report as customer admin, %s\n",
						rptTitle);

		String surveyorUnit = SQACUS + " - " + SQACUSLOC + " - " + SQACUSLOCSUR;
		System.out.println(surveyorUnit);

		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -5);
		String startDate = dateFormat.format(cal.getTime());
		System.out.println("Start Date : " + startDate);
		if (startDate.startsWith("0")) {
			startDate = startDate.replaceFirst("0*", "");
			System.out.println("New Start Date : " + startDate);
		}

		date = new Date();
		String endDate = dateFormat.format(date);
		System.out.println("End Date : " + endDate);
		if (endDate.startsWith("0")) {
			endDate = endDate.replaceFirst("0*", "");
			System.out.println("New End Date : " + endDate);
		}

		referenceGasReportsPage.login(SQACUSUA, USERPASSWORD);
		referenceGasReportsPage.open();

		referenceGasReportsPage.addNewReport(rptTitle, TIMEZONEMT,
				surveyorUnit, startDate, endDate, 6, 0);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((referenceGasReportsPage.checkActionStatus(rptTitle, SQACUSUA))) {
			assertTrue(referenceGasReportsPage.findReport(rptTitle, SQACUSUA));
			assertTrue(referenceGasReportsPage.validatePdfFiles(rptTitle,
					testSetup.getDownloadPath()));
		} else
			fail("\nTestcase TC179 failed. Report not downloaded successfully!\n");

		referenceGasReportsPage.open();
		referenceGasReportsPage.logout();
	}

	/**
	 * Test Case ID: TC187 Test Description: Click on Cancel button present on
	 * reference gas report screen
	 * 
	 */
	@Test
	public void TC187_RefGasRptScreen_CancelBtn() {
		System.out
				.format("\nRunning TC187: Click on Cancel button present on reference gas report screen\n");

		referenceGasReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		referenceGasReportsPage.open();

		assertTrue(referenceGasReportsPage.verifyCancelButtonFunctionality());

		referenceGasReportsPage.open();
		referenceGasReportsPage.logout();
	}

	/**
	 * Test Case ID: TC196 Test Description: Generate Generate Reference Gas
	 * Capture Report for single day
	 * 
	 */
//	@Test
// Need to check how to select 6/29 start date
	public void TC196_GenerateRefGasRpt_SingleDay() {
		String rptTitle = "TC196 Report" + testSetup.getRandomNumber();
		String startDate = "28";
		String endDate = "29";
		
		System.out
				.format("\nRunning TC196 Test Description: Generate Reference Gas Capture Report for single day, %s\n",
						rptTitle);

		String surveyorUnit = SQACUS + " - " + SQACUSLOC + "0" + " - "
				+ SQACUSLOC0SUR;
		System.out.println(surveyorUnit);

		referenceGasReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		referenceGasReportsPage.open();

		referenceGasReportsPage.addNewReport(rptTitle, TIMEZONEPT,
				surveyorUnit, startDate, endDate, 6, 6);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((referenceGasReportsPage.checkActionStatus(rptTitle, PICDFADMIN))) {
			assertTrue(referenceGasReportsPage.findReport(rptTitle, PICDFADMIN));
			assertTrue(referenceGasReportsPage.validatePdfFiles(rptTitle,
							testSetup.getDownloadPath()));
		} else
			fail("\nTestcase TC196 failed.\n");

		referenceGasReportsPage.open();
		referenceGasReportsPage.logout();
	}

	/**
	 * Test Case ID: TC515 Test Description: Generate Reference Gas Capture
	 * Report as customer supervisor
	 * 
	 */
	@Test
	public void TC515_GenerateRefGasRpt_CustSupervisor() {
		String rptTitle = "TC515 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning TC515 Test Description: Generate Reference Gas Capture Report as customer supervisor, %s\n",
						rptTitle);

		String surveyorUnit = SQACUS + " - " + SQACUSLOC + " - " + SQACUSLOCSUR;
		System.out.println(surveyorUnit);

		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -30);
		String startDate = dateFormat.format(cal.getTime());
		System.out.println("Start Date : " + startDate);
		if (startDate.startsWith("0")) {
			startDate = startDate.replaceFirst("0*", "");
			System.out.println("New Start Date : " + startDate);
		}

		date = new Date();
		String endDate = dateFormat.format(date);
		System.out.println("End Date : " + endDate);
		if (endDate.startsWith("0")) {
			endDate = endDate.replaceFirst("0*", "");
			System.out.println("New End Date : " + endDate);
		}

		referenceGasReportsPage.login(SQACUSSU, USERPASSWORD);
		referenceGasReportsPage.open();

		referenceGasReportsPage.addNewReport(rptTitle, TIMEZONECT,
				surveyorUnit, startDate, endDate, 6, 0);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((referenceGasReportsPage.checkActionStatus(rptTitle, SQACUSSU))) {
			assertTrue(referenceGasReportsPage.findReport(rptTitle, SQACUSSU));
			assertTrue("Defect DE696!",
					referenceGasReportsPage.validatePdfFiles(rptTitle,
							testSetup.getDownloadPath()));
		} else
			fail("\nTestcase RPT515 failed. Either Report failed to generate or download!\n");

		referenceGasReportsPage.open();
		referenceGasReportsPage.logout();
	}
}