/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.assertTrue;

import static org.junit.Assert.fail;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.SQACUS;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC0SUR;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEMT;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;

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
	 * Test Case ID: RPT014 Test Description: Generate Generate Reference Gas
	 * Capture Report as Administrator
	 * 
	 */
	@Test
	public void RPT014() {
		String rptTitle = "RPT014 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT014 Test Description: Generate Reference Gas Capture Report as Administrator, %s\n",
						rptTitle);

		String surveyorUnit = SQACUS + " - " + SQACUSLOC + "0" + " - "
				+ SQACUSLOC0SUR;
		System.out.println(surveyorUnit);

		// DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:MM a");
		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -10);
		String startDate = dateFormat.format(cal.getTime());
		System.out.println("Start Date : " + startDate);

		date = new Date();
		String endDate = dateFormat.format(date);
		System.out.println("End Date : " + endDate);

		referenceGasReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		referenceGasReportsPage.open();

		referenceGasReportsPage.addNewPDReport(rptTitle, TIMEZONEPT,
				surveyorUnit, startDate, endDate);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((referenceGasReportsPage.checkActionStatus(rptTitle, PICDFADMIN)))
			assertTrue(referenceGasReportsPage.findExistingReport(rptTitle,
					PICDFADMIN));
		else
			fail("\nTestcase RPT014 failed.\n");

		referenceGasReportsPage.open();
		referenceGasReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT029 Test Description: Generate Reference Gas Capture
	 * Report as customer admin
	 * 
	 */
	@Test
	public void RPT029() {
		String rptTitle = "RPT029 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT029 Test Description: Generate Reference Gas Capture Report as customer admin, %s\n",
						rptTitle);

		String surveyorUnit = SQACUS + " - " + SQACUSLOC + "0" + " - "
				+ SQACUSLOC0SUR;
		System.out.println(surveyorUnit);

		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -5);
		String startDate = dateFormat.format(cal.getTime());
		System.out.println("Start Date : " + startDate);

		date = new Date();
		String endDate = dateFormat.format(date);
		System.out.println("End Date : " + endDate);

		referenceGasReportsPage.login(SQACUSUA, USERPASSWORD);
		referenceGasReportsPage.open();

		referenceGasReportsPage.addNewPDReport(rptTitle, TIMEZONEMT,
				surveyorUnit, startDate, endDate);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((referenceGasReportsPage.checkActionStatus(rptTitle, SQACUSUA)))
			assertTrue(referenceGasReportsPage.findExistingReport(rptTitle,
					SQACUSUA));
		else
			fail("\nTestcase RPT029 failed.\n");

		referenceGasReportsPage.open();
		referenceGasReportsPage.logout();
	}
	
	/**
	 * Test Case ID: RPT044 Test Description: Generate Generate Reference Gas
	 * Capture Report for single day
	 * 
	 */
	@Test
	public void RPT044() {
		String rptTitle = "RPT044 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT044 Test Description: Generate Reference Gas Capture Report for single day, %s\n",
						rptTitle);

		String surveyorUnit = SQACUS + " - " + SQACUSLOC + "0" + " - "
				+ SQACUSLOC0SUR;
		System.out.println(surveyorUnit);

		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String startDate = dateFormat.format(cal.getTime());
		System.out.println("Start Date : " + startDate);

		date = new Date();
		String endDate = dateFormat.format(date);
		System.out.println("End Date : " + endDate);

		referenceGasReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		referenceGasReportsPage.open();

		referenceGasReportsPage.addNewPDReport(rptTitle, TIMEZONEPT,
				surveyorUnit, startDate, endDate);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((referenceGasReportsPage.checkActionStatus(rptTitle, PICDFADMIN)))
			assertTrue(referenceGasReportsPage.findExistingReport(rptTitle,
					PICDFADMIN));
		else
			fail("\nTestcase RPT044 failed.\n");

		referenceGasReportsPage.open();
		referenceGasReportsPage.logout();
	}
}