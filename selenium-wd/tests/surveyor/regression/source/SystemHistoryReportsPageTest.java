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

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
import common.source.TestContext;
import surveyor.scommon.source.ManageSurveyorHistoriesPage;
import surveyor.scommon.source.PageObjectFactory;
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

	private static DateFormat dateFormat = new SimpleDateFormat("dd");

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

		systemHistoryReportsPage = pageObjectFactory.getSystemHistoryReportsPage();
		PageFactory.initElements(getDriver(), systemHistoryReportsPage);

		manageSurveyorHistoriesPage = pageObjectFactory.getManageSurveyorHistoriesPage();
		PageFactory.initElements(getDriver(), manageSurveyorHistoriesPage);
	}

	/**
	 * Test Case ID: TC158 Test Description: Generate system history report as Administrator
	 *
	 */
	@Ignore
	public void TC158_SysHisRpt_PicarroAdmin() {
		String rptTitle = "TC158 Report" + getTestSetup().getRandomNumber();
		Log.info("\nRunning TC158 Test Description: Generate system history report as Administrator, %s\n" + rptTitle);

		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -10);
		String startDate = dateFormat.format(cal.getTime());
		if (startDate.startsWith("0")) {
			startDate = startDate.replaceFirst("0*", "");
		}
		manageSurveyorHistoriesPage.login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		manageSurveyorHistoriesPage.open();

		String surveyorUnit = SQACUS + " - " + SQACUSLOC + " - " + SQACUSLOCSUR;
		String note = "Automation Test Note " + getTestSetup().getRandomNumber();

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
			assertTrue(systemHistoryReportsPage.validatePdfFiles(rptTitle, getTestSetup().getDownloadPath()));
			assertTrue(systemHistoryReportsPage.verifyStaticTextinPDF(getTestSetup().getDownloadPath(), rptTitle));
			assertTrue(systemHistoryReportsPage.verifyUserInputInPDF(getTestSetup().getDownloadPath(), rptTitle, inputList));
			assertTrue(systemHistoryReportsPage.verifyNotesTable(getTestSetup().getDownloadPath(), rptTitle));

		} else
			fail("\nTestcase TC158 failed.\n");

		systemHistoryReportsPage.open();
		systemHistoryReportsPage.logout();
	}

	/**
	 * Test Case ID: TC1297 Test Description:
	 * Software version present on report PDF should match with UI software version
	 *
	 */
	@Test
	public void TC1297_SysHisRpt_VerifyWebAppAndPDFVersion() {
		String rptTitle = "TC1297 Report" + getTestSetup().getRandomNumber();
		Log.info("\nRunning TC1297 Test Description: Software version present on report PDF should match with UI software version, %s\n" + rptTitle);

		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 0);
		String startDate = dateFormat.format(cal.getTime());
		if (startDate.startsWith("0")) {
			startDate = startDate.replaceFirst("0*", "");
		}

		manageSurveyorHistoriesPage.login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		manageSurveyorHistoriesPage.open();

		String surveyorUnit = SQACUS + " - " + SQACUSLOC + " - " + SQACUSLOCSUR;
		String note = "Automation Test Note " + getTestSetup().getRandomNumber();

		manageSurveyorHistoriesPage.addNewHistoryNote(surveyorUnit, note);
		systemHistoryReportsPage.login(SQACUSUA, USERPASSWORD);
		systemHistoryReportsPage.open();

		String webAppVersionNumber = systemHistoryReportsPage.getWebAppVersion();

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

		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());

		if ((systemHistoryReportsPage.checkActionStatus(rptTitle, SQACUSUA))) {
			String pdfSoftwareVersion = systemHistoryReportsPage.getSoftwareVersionFromPDF(rptTitle, getTestSetup().getDownloadPath());
			Log.info(String.format("Comparing web app version and PDF software version. WebAppVersion = '%s', PDFSoftwareVersion = '%s'",
					webAppVersionNumber, pdfSoftwareVersion));
			assertTrue(webAppVersionNumber.equals(pdfSoftwareVersion));
		}
		else
			fail("\nTestcase TC1297 failed.\n");
	}
}