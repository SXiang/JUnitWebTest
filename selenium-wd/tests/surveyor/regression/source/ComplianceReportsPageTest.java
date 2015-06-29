/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static surveyor.scommon.source.SurveyorConstants.CUSNAMEBASE;
import static surveyor.scommon.source.SurveyorConstants.KEYANNOTATION;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYBASEMAP;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARIES;
import static surveyor.scommon.source.SurveyorConstants.KEYBREADCRUMB;
import static surveyor.scommon.source.SurveyorConstants.KEYFOV;
import static surveyor.scommon.source.SurveyorConstants.KEYGAPS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDICATIONS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDTB;
import static surveyor.scommon.source.SurveyorConstants.KEYISOANA;
import static surveyor.scommon.source.SurveyorConstants.KEYISOTOPICCAPTURE;
import static surveyor.scommon.source.SurveyorConstants.KEYLISA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCRA;
import static surveyor.scommon.source.SurveyorConstants.KEYVIEWNAME;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.SQACUS;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDRTAG;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC1SUR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC2SUR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC3SUR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSU;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSUTAG;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSUUSER;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUATAG;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUAUSER;
import static surveyor.scommon.source.SurveyorConstants.SQAPICAD;
import static surveyor.scommon.source.SurveyorConstants.SQAPICADMANUALTAG;
import static surveyor.scommon.source.SurveyorConstants.SQAPICADRRTAG;
import static surveyor.scommon.source.SurveyorConstants.SQAPICADSTNDTAG;
import static surveyor.scommon.source.SurveyorConstants.SQAPICADTAG;
import static surveyor.scommon.source.SurveyorConstants.SQAPICADUSER;
import static surveyor.scommon.source.SurveyorConstants.SQAPICDRTAG;
import static surveyor.scommon.source.SurveyorConstants.SQAPICLOC3SUR;
import static surveyor.scommon.source.SurveyorConstants.SQAPICSUTAG;
import static surveyor.scommon.source.SurveyorConstants.SQAPICUA;
import static surveyor.scommon.source.SurveyorConstants.SQAPICUATAG;
import static surveyor.scommon.source.SurveyorConstants.SQAPICUAUSER;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONECT;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEET;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.SurveyorBaseTest;

/**
 * @author zlu
 * 
 */
public class ComplianceReportsPageTest extends SurveyorBaseTest {
	private static ComplianceReportsPage complianceReportsPage = null;
	private String STRReportAreaTooLargeMsg = "Area selected is too large";
	private String STRReportAreaTooSmallMsg = "Area Selected is too small";

	@BeforeClass
	public static void setupComplianceReportsPageTest() {
		complianceReportsPage = new ComplianceReportsPage(driver, baseURL,
				testSetup);
		PageFactory.initElements(driver, complianceReportsPage);
	}

	// Comment out Test Case ID: RPT000. It was created for testing the code and
	// the scenario was covered by other test cases
	// tag dmcs1-sqa01 survey need to be generated first to run this sample test
	// code
	// /**
	// * Test Case ID: RPT000
	// * Test Description: Create a new compliance report, basic and generic by
	// default Administrator
	// *
	// */
	// @Test
	// public void RPT000() {
	// String rptTitle = TITLENAMEBASE + testSetup.getRandomNumber() + "RPT000";
	// System.out.format("\nRunning - RPT000 - Test Description: Create a new compliance report, basic and generic, %s\n",
	// rptTitle);
	//
	// loginPage.open();
	// loginPage.loginNormalAs(testSetup.getLoginUser(),
	// testSetup.getLoginPwd());
	//
	// complianceReportsPage.open();
	// complianceReportsPage.addNewPDReport(rptTitle, SQACUS);
	//
	// testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
	//
	// if ((complianceReportsPage.checkActionStatus(rptTitle,
	// testSetup.getLoginUser())))
	// assertTrue(complianceReportsPage.findExistingReport(rptTitle,
	// testSetup.getLoginUser()));
	// else
	// fail("\nTestcase RPT000 - Test Description: Create a new compliance report, basic and generic, failed.\n");
	//
	// complianceReportsPage.open();
	// complianceReportsPage.logout();
	// }

	/**
	 * Test Case ID: RPT000A Test Description: Create a compliance report by a
	 * user with Customer Utility Admin Role, survey owner
	 * 
	 */
	@Test
	public void RPT000A() {
		String rptTitle = SQACUSUAUSER + testSetup.getRandomNumber()
				+ "RPT000A";
		System.out
				.format("\nRunning - RPT000A - Test Description: Create a compliance report by a user with Customer Utility Admin Role, survey owner, %s\n",
						rptTitle);

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		complianceReportsPage.open();

		String surUnit = SQACUS + " - " + SQACUSLOC + "1" + " - "
				+ SQACUSLOC1SUR;

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQACUSUATAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSUA)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					SQACUSUA));
		else
			fail("\nTestcase RPT000A - Test Description: Create a compliance report by a user with Customer Utility Admin Role, survey owner, failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT000A1 Test Description: Create a compliance report by a
	 * user with Customer Utility Admin Role, supervisor survey
	 * 
	 */
	@Test
	public void RPT000A1() {
		String rptTitle = SQACUSUAUSER + testSetup.getRandomNumber()
				+ "RPT000A1";
		System.out
				.format("\nRunning - RPT000A1 - Test Description: Create a compliance report by a user with Customer Utility Admin Role, supervisor survey, %s\n",
						rptTitle);

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		complianceReportsPage.open();

		String surUnit = SQACUS + " - " + SQACUSLOC + "2" + " - "
				+ SQACUSLOC2SUR;

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQACUSSUTAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSUA)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					SQACUSUA));
		else
			fail("\nTestcase RPT000A1 - Test Description: Create a compliance report by a user with Customer Utility Admin Role, supervisor survey, failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT000A2 Test Description: Create a compliance report by a
	 * user with Customer Utility Admin Role, driver survey
	 * 
	 */
	@Test
	public void RPT000A2() {
		String rptTitle = SQACUSUAUSER + testSetup.getRandomNumber()
				+ "RPT000A2";
		System.out
				.format("\nRunning - RPT000A2 - Test Description: Create a compliance report by a user with Customer Utility Admin Role, driver survey, %s\n",
						rptTitle);

		complianceReportsPage.login(SQACUSUA, USERPASSWORD);
		complianceReportsPage.open();

		String surUnit = SQACUS + " - " + SQACUSLOC + "3" + " - "
				+ SQACUSLOC3SUR;

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQACUSDRTAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSUA)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					SQACUSUA));
		else
			fail("\nTestcase RPT000A2 - Test Description: Create a compliance report by a user with Customer Utility Admin Role, driver survey, failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT000B Test Description: Create a compliance report by a
	 * user with Customer Supervisor Role, survey owner
	 * 
	 */
	@Test
	public void RPT000B() {
		String rptTitle = SQACUSSUUSER + testSetup.getRandomNumber()
				+ "RPT000B";
		System.out
				.format("\nRunning - RPT000B - Test Description: Create a compliance report by a user with Customer Supervisor Role, survey owner, %s\n",
						rptTitle);

		complianceReportsPage.login(SQACUSSU, USERPASSWORD);
		complianceReportsPage.open();

		String surUnit = SQACUS + " - " + SQACUSLOC + "2" + " - "
				+ SQACUSLOC2SUR;

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQACUSSUTAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSSU)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					SQACUSSU));
		else
			fail("\nTestcase RPT000B - Test Description: Create a compliance report by a user with Customer Supervisor Role, survey owner, failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT000B1 Test Description: Create a compliance report by a
	 * user with Customer Supervisor Role, driver survey
	 * 
	 */
	@Test
	public void RPT000B1() {
		String rptTitle = SQACUSSUUSER + testSetup.getRandomNumber()
				+ "RPT000B1";
		System.out
				.format("\nRunning - RPT000B1 - Test Description: Create a compliance report by a user with Customer Supervisor Role, driver survey, %s\n",
						rptTitle);

		complianceReportsPage.login(SQACUSSU, USERPASSWORD);
		complianceReportsPage.open();

		String surUnit = "";

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQACUSDRTAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSSU)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					SQACUSSU));
		else
			fail("\nTestcase RPT000B1 - Test Description: Create a compliance report by a user with Customer Supervisor Role, driver survey, failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT000B2 Test Description: Create a compliance report by a
	 * user with Customer Supervisor Role, utility admin survey
	 * 
	 */
	@Test
	public void RPT000B2() {
		String rptTitle = SQACUSSUUSER + testSetup.getRandomNumber()
				+ "RPT000B2";
		System.out
				.format("\nRunning - RPT000B2 - Test Description: Create a compliance report by a user with Customer Supervisor Role, utility admin survey, %s\n",
						rptTitle);

		complianceReportsPage.login(SQACUSSU, USERPASSWORD);
		complianceReportsPage.open();

		String surUnit = "";

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQACUSUATAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSSU)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					SQACUSSU));
		else
			fail("\nTestcase RPT000B2 - Test Description: Create a compliance report by a user with Customer Supervisor Role, utility admin survey, failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT000C Test Description: Create a compliance report by a
	 * user with Administrator Role, survey owner
	 * 
	 */
	@Test
	public void RPT000C() {
		String rptTitle = SQAPICADUSER + testSetup.getRandomNumber()
				+ "RPT000C";
		System.out
				.format("\nRunning - RPT000C - Test Description: Create a compliance report by a user with Administrator Role, survey owner, %s\n",
						rptTitle);

		complianceReportsPage.login(SQAPICAD, USERPASSWORD);
		complianceReportsPage.open();

		String surUnit = "";

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICADTAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQAPICAD)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					SQAPICAD));
		else
			fail("\nTestcase RPT000C failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT000C1 Test Description: Create a compliance report by a
	 * user with Administrator Role, utility admin survey
	 * 
	 */
	@Test
	public void RPT000C1() {
		String rptTitle = SQAPICADUSER + testSetup.getRandomNumber()
				+ "RPT000C1";
		System.out
				.format("\nRunning - RPT000C1 - Test Description: Create a compliance report by a user with Administrator Role, utility admin survey, %s\n",
						rptTitle);

		complianceReportsPage.login(SQAPICAD, USERPASSWORD);
		complianceReportsPage.open();

		String surUnit = "";

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICUATAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQAPICAD)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					SQAPICAD));
		else
			fail("\nTestcase RPT000C1 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT000C2 Test Description: Create a compliance report by a
	 * user with Administrator Role, supervisor survey
	 * 
	 */
	@Test
	public void RPT000C2() {
		String rptTitle = SQAPICADUSER + testSetup.getRandomNumber()
				+ "RPT000C2";
		System.out
				.format("\nRunning - RPT000C2 - Test Description: Create a compliance report by a user with Administrator Role, supervisor survey, %s\n",
						rptTitle);

		complianceReportsPage.login(SQAPICAD, USERPASSWORD);
		complianceReportsPage.open();

		String surUnit = "";

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICSUTAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQAPICAD)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					SQAPICAD));
		else
			fail("\nTestcase RPT000C2 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT000C3 Test Description: Create a compliance report by a
	 * user with Administrator Role, driver survey
	 * 
	 */
	@Test
	public void RPT000C3() {
		String rptTitle = SQAPICADUSER + testSetup.getRandomNumber()
				+ "RPT000C3";
		System.out
				.format("\nRunning - RPT000C3 - Test Description: Create a compliance report by a user with Administrator Role, driver survey, %s\n",
						rptTitle);

		complianceReportsPage.login(SQAPICAD, USERPASSWORD);
		complianceReportsPage.open();

		String surUnit = "";

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICDRTAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQAPICAD)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					SQAPICAD));
		else
			fail("\nTestcase RPT000C3 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT000D Test Description: Create a compliance report by a
	 * user with Picarro Utility Admin Role, survey owner
	 * 
	 */
	@Test
	public void RPT000D() {
		String rptTitle = SQAPICUAUSER + testSetup.getRandomNumber()
				+ "RPT000D";
		System.out
				.format("\nRunning - RPT000D - Test Description: Create a compliance report by a user with Picarro Utility Admin Role, survey owner, %s\n",
						rptTitle);

		complianceReportsPage.login(SQAPICUA, USERPASSWORD);
		complianceReportsPage.open();

		String surUnit = "";

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICUATAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQAPICUA)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					SQAPICUA));
		else
			fail("\nTestcase RPT000D failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT000D1 Test Description: Create a compliance report by a
	 * user with Picarro Utility Admin Role, admin survey
	 * 
	 */
	@Test
	public void RPT000D1() {
		String rptTitle = SQAPICUAUSER + testSetup.getRandomNumber()
				+ "RPT000D1";
		System.out
				.format("\nRunning - RPT000D1 - Test Description: Create a compliance report by a user with Picarro Utility Admin Role, admin survey, %s\n",
						rptTitle);

		complianceReportsPage.login(SQAPICUA, USERPASSWORD);
		complianceReportsPage.open();

		String surUnit = "";

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICADTAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQAPICUA)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					SQAPICUA));
		else
			fail("\nTestcase RPT000D1 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT000D2 Test Description: Create a compliance report by a
	 * user with Picarro Utility Admin Role, supervisor survey
	 * 
	 */
	@Test
	public void RPT000D2() {
		String rptTitle = SQAPICUAUSER + testSetup.getRandomNumber()
				+ "RPT000D2";
		System.out
				.format("\nRunning - RPT000D2 - Test Description: Create a compliance report by a user with Picarro Utility Admin Role, supervisor survey, %s\n",
						rptTitle);

		complianceReportsPage.login(SQAPICUA, USERPASSWORD);
		complianceReportsPage.open();

		String surUnit = "";

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICSUTAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQAPICUA)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					SQAPICUA));
		else
			fail("\nTestcase RPT000D2 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT000D3 Test Description: Create a compliance report by a
	 * user with Picarro Utility Admin Role, driver survey
	 * 
	 */
	@Test
	public void RPT000D3() {
		String rptTitle = SQAPICUAUSER + testSetup.getRandomNumber()
				+ "RPT000D3";
		System.out
				.format("\nRunning - RPT000D3 - Test Description: Create a compliance report by a user with Picarro Utility Admin Role, driver survey, %s\n",
						rptTitle);

		complianceReportsPage.login(SQAPICUA, USERPASSWORD);
		complianceReportsPage.open();

		String surUnit = "";

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICDRTAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQAPICUA)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					SQAPICUA));
		else
			fail("\nTestcase RPT000D3 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT000E Test Description: Delete a compliance report by
	 * Administrator
	 * 
	 */
	@Test
	public void RPT000E() {
		String rptTitle = PICDFADMIN + testSetup.getRandomNumber() + "RPT000E";
		System.out
				.format("\nRunning - RPT000E - Test Description: Delete a compliance report by Administrator, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		String surUnit = "";

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICDRTAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					PICDFADMIN));
		else
			fail("\nTestcase RPT000E failed.\n");

		if (complianceReportsPage.deleteReport(rptTitle, PICDFADMIN))
			assertTrue(!complianceReportsPage.findExistingReport(rptTitle,
					PICDFADMIN));
		else
			fail("\nTestcase RPT000E failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT000F Test Description: copy an Administrator's
	 * compliance report by Administrator
	 * 
	 */
	@Test
	public void RPT000F() {
		String rptTitle = PICDFADMIN + testSetup.getRandomNumber() + "RPT000F";
		System.out
				.format("\nRunning - RPT000F - Test Description: copy an Administrator's compliance report by Administrator, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		String surUnit = "";

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICDRTAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					PICDFADMIN));
		else
			fail("\nTestcase RPT000F failed.\n");

		complianceReportsPage.copyReport(rptTitle, PICDFADMIN, rptTitle
				+ "COPY");

		if ((complianceReportsPage.checkActionStatus(rptTitle + "COPY",
				PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle
					+ "COPY", PICDFADMIN));
		else
			fail("\nTestcase RPT000F failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT000G Test Description: Create a compliance report by
	 * Administrator for a Picarro Survey
	 * 
	 */
	@Test
	public void RPT000G() {
		String rptTitle = PICDFADMIN + testSetup.getRandomNumber() + "RPT000G";
		System.out
				.format("\nRunning - RPT000G - Test Description: Create a compliance report by Administrator for a Picarro Survey, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		String surUnit = "";

		complianceReportsPage.addNewPDReport(rptTitle, "Picarro", surUnit,
				SQAPICDRTAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					PICDFADMIN));
		else
			fail("\nTestcase RPT000G failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT000H Test Description: Create a compliance report by
	 * Administrator for a customer Survey
	 * 
	 */
	@Test
	public void RPT000H() {
		String rptTitle = PICDFADMIN + testSetup.getRandomNumber() + "RPT000H";
		System.out
				.format("\nRunning - RPT000H - Test Description: Create a compliance report by Administrator for a customer Survey, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		String surUnit = "";

		complianceReportsPage.addNewPDReport(rptTitle, SQACUS, surUnit,
				SQACUSDRTAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					PICDFADMIN));
		else
			fail("\nTestcase RPT000H failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT000I Test Description: Create a compliance report by
	 * Administrator with more general options
	 * 
	 */
	@Test
	public void RPT000I() {
		String rptTitle = PICDFADMIN + " " + testSetup.getRandomNumber()
				+ "RPT000I";
		System.out
				.format("\nRunning - RPT000I - Test Description: Create a compliance report by Administrator with more general options, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("5");
		listBoundary.add("5");
		listBoundary.add("37.4353397926825");
		listBoundary.add("-121.84696197509766");
		listBoundary.add("37.33058362073965");
		listBoundary.add("-122.04883575439453");

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();
		Map<String, String> viewMap2 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "1");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "1");
		viewMap1.put(KEYBASEMAP, "Map");

		viewMap2.put(KEYVIEWNAME, "Second View");
		viewMap2.put(KEYLISA, "1");
		viewMap2.put(KEYFOV, "0");
		viewMap2.put(KEYBREADCRUMB, "0");
		viewMap2.put(KEYINDICATIONS, "0");
		viewMap2.put(KEYISOTOPICCAPTURE, "0");
		viewMap2.put(KEYANNOTATION, "0");
		viewMap2.put(KEYGAPS, "0");
		viewMap2.put(KEYASSETS, "0");
		viewMap2.put(KEYBOUNDARIES, "0");
		viewMap2.put(KEYBASEMAP, "Satellite");

		viewList.add(viewMap1);
		viewList.add(viewMap2);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "1");
		tableMap.put(KEYPCRA, "1");
		tablesList.add(tableMap);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle,
				testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "3",
				listBoundary, tablesList, "", SQAPICDRTAG, viewList);
		complianceReportsPage.addNewReport(rpt);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle,
				testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle,
						testSetup.getLoginUser()));
			} else
				fail("\nTestcase RPT000I failed.\n");
		} else
			fail("\nTestcase RPT000I failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT001 Test Description: Generate compliance report with
	 * all default values/filters selected and download it
	 * 
	 */
	@Test
	public void RPT001() {
		String rptTitle = "RPT001 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT001: Generate compliance report with all default values/filters selected and download it, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		String surUnit = "";
		// changed survey tag for time being---- remove the code later on and fix the tag value as per surveys available in SureyorConstants file
		String SQAPICSUTAG = "test 6/22";
		
		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICSUTAG);
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN))) {
			if (complianceReportsPage.validatePdfFiles(rptTitle,
					testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle,
						PICDFADMIN));
			} else
				fail("\nTestcase RPT001 failed.\n");
		} else
			fail("\nTestcase RPT001 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT003 Test Description: Generate compliance report by
	 * selecting custom boundary using date range and tag filters for more than
	 * one view and export the report
	 * 
	 */
	@Test
	public void RPT003() {
		String rptTitle = "RPT003 Report" + testSetup.getRandomNumber();
		
		System.out
				.format("\nRunning RPT003: Generate compliance report by selecting custom boundary using date range and tag filters for more than one view and export the report, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("10");
		listBoundary.add("10");
		listBoundary.add("37.42279859267848");
		listBoundary.add("-121.93553924560548");
		listBoundary.add("37.37493189292912");
		listBoundary.add("-122.08797454833983");

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();
		Map<String, String> viewMap2 = new HashMap<String, String>();
		Map<String, String> viewMap3 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "0");
		viewMap1.put(KEYFOV, "0");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "0");
		viewMap1.put(KEYISOTOPICCAPTURE, "0");
		viewMap1.put(KEYANNOTATION, "0");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "1");
		viewMap1.put(KEYBASEMAP, "Map");

		viewMap2.put(KEYVIEWNAME, "Second View");
		viewMap2.put(KEYLISA, "1");
		viewMap2.put(KEYFOV, "1");
		viewMap2.put(KEYBREADCRUMB, "1");
		viewMap2.put(KEYINDICATIONS, "1");
		viewMap2.put(KEYISOTOPICCAPTURE, "1");
		viewMap2.put(KEYANNOTATION, "0");
		viewMap2.put(KEYGAPS, "0");
		viewMap2.put(KEYASSETS, "0");
		viewMap2.put(KEYBOUNDARIES, "0");
		viewMap2.put(KEYBASEMAP, "Satellite");

		viewMap3.put(KEYVIEWNAME, "Third View");
		viewMap3.put(KEYLISA, "0");
		viewMap3.put(KEYFOV, "0");
		viewMap3.put(KEYBREADCRUMB, "0");
		viewMap3.put(KEYINDICATIONS, "1");
		viewMap3.put(KEYISOTOPICCAPTURE, "0");
		viewMap3.put(KEYANNOTATION, "1");
		viewMap3.put(KEYGAPS, "0");
		viewMap3.put(KEYASSETS, "1");
		viewMap3.put(KEYBOUNDARIES, "1");
		viewMap3.put(KEYBASEMAP, "Satellite");

		viewList.add(viewMap1);
		viewList.add(viewMap2);
		viewList.add(viewMap3);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);
		
		// changed survey tag for time being---- remove the code later on and fix the tag value as per surveys available in SureyorConstants file
		String SQAPICSUTAG = "test 6/22";

		ReportsCompliance rpt = new ReportsCompliance(rptTitle,
				testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "0",
				listBoundary, tablesList, "", SQAPICSUTAG, viewList);
		complianceReportsPage.addNewReport(rpt);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle,
				testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle,
						testSetup.getLoginUser()));
			} else
				fail("\nTestcase RPT003 failed.\n");
		} else
			fail("\nTestcase RPT003 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT003A Test Description: Generate compliance report using
	 * surveyor unit and tag filters for more than one view and download the
	 * report
	 * 
	 */
	@Test
	public void RPT003A() {
		String rptTitle = "RPT003A Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT003A: Generate compliance report using surveyor unit and tag filters for more than one view and download the report, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("10");
		listBoundary.add("10");
		listBoundary.add("37.42279859267848");
		listBoundary.add("-121.93553924560548");
		listBoundary.add("37.37493189292912");
		listBoundary.add("-122.08797454833983");

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();
		Map<String, String> viewMap2 = new HashMap<String, String>();
		Map<String, String> viewMap3 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "0");
		viewMap1.put(KEYFOV, "0");
		viewMap1.put(KEYBREADCRUMB, "0");
		viewMap1.put(KEYINDICATIONS, "0");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "1");
		viewMap1.put(KEYBASEMAP, "Map");

		viewMap2.put(KEYVIEWNAME, "Second View");
		viewMap2.put(KEYLISA, "1");
		viewMap2.put(KEYFOV, "1");
		viewMap2.put(KEYBREADCRUMB, "0");
		viewMap2.put(KEYINDICATIONS, "0");
		viewMap2.put(KEYISOTOPICCAPTURE, "0");
		viewMap2.put(KEYANNOTATION, "0");
		viewMap2.put(KEYGAPS, "0");
		viewMap2.put(KEYASSETS, "0");
		viewMap2.put(KEYBOUNDARIES, "0");
		viewMap2.put(KEYBASEMAP, "Satellite");

		viewMap3.put(KEYVIEWNAME, "Third View");
		viewMap3.put(KEYLISA, "0");
		viewMap3.put(KEYFOV, "0");
		viewMap3.put(KEYBREADCRUMB, "1");
		viewMap3.put(KEYINDICATIONS, "1");
		viewMap3.put(KEYISOTOPICCAPTURE, "0");
		viewMap3.put(KEYANNOTATION, "1");
		viewMap3.put(KEYGAPS, "0");
		viewMap3.put(KEYASSETS, "0");
		viewMap3.put(KEYBOUNDARIES, "0");
		viewMap3.put(KEYBASEMAP, "Map");

		viewList.add(viewMap1);
		viewList.add(viewMap2);
		viewList.add(viewMap3);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle,
				testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "0",
				listBoundary, tablesList, "", SQAPICSUTAG, viewList);
		complianceReportsPage.addNewReport(rpt);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle,
				testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle,
						testSetup.getLoginUser()));
			} else
				fail("\nTestcase RPT003A failed.\n");
		} else
			fail("\nTestcase RPT003A failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT004 Test Description: Generate Compliance Report and
	 * include Picarro Surveyor Unit filter
	 * 
	 */
	@Test
	public void RPT004() {
		String rptTitle = "RPT004 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT004: Generate Compliance Report and include Picarro Surveyor Unit filter, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("10");
		listBoundary.add("10");
		listBoundary.add("37.42279859267848");
		listBoundary.add("-121.93553924560548");
		listBoundary.add("37.37493189292912");
		listBoundary.add("-122.08797454833983");

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "0");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, "Map");

		viewList.add(viewMap1);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle,
				testSetup.getLoginUser(), "Picarro", TIMEZONECT, "0",
				listBoundary, tablesList, SQAPICLOC3SUR, SQAPICSUTAG, viewList);
		complianceReportsPage.addNewReport(rpt);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle,
				testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle,
						testSetup.getLoginUser()));
			} else
				fail("\nTestcase RPT004 failed.\n");
		} else
			fail("\nTestcase RPT004 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT005 Test Description: Generate report by providing tag
	 * filter
	 * 
	 */
	@Test
	public void RPT005() {
		String rptTitle = "RPT005 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT005: Generate report by providing tag filter, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("10");
		listBoundary.add("10");
		listBoundary.add("37.42279859267848");
		listBoundary.add("-121.93553924560548");
		listBoundary.add("37.37493189292912");
		listBoundary.add("-122.08797454833983");

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "0");
		viewMap1.put(KEYFOV, "0");
		viewMap1.put(KEYBREADCRUMB, "0");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "0");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, "Map");

		viewList.add(viewMap1);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle,
				testSetup.getLoginUser(), "Picarro", TIMEZONECT, "0",
				listBoundary, tablesList, "", SQAPICSUTAG, viewList);
		complianceReportsPage.addNewReport(rpt);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle,
				testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle,
						testSetup.getLoginUser()));
			} else
				fail("\nTestcase RPT005 failed.\n");
		} else
			fail("\nTestcase RPT005 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT006 Test Description: Generate compliance report using
	 * date range and surveyor unit filters for more than one view and download
	 * the report
	 * 
	 */
	@Test
	public void RPT006() {
		String rptTitle = "RPT006 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT006: Generate compliance report using date range and surveyor unit filters for more than one view and download the report, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("10");
		listBoundary.add("10");
		listBoundary.add("37.42279859267848");
		listBoundary.add("-121.93553924560548");
		listBoundary.add("37.37493189292912");
		listBoundary.add("-122.08797454833983");

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "0");
		viewMap1.put(KEYFOV, "0");
		viewMap1.put(KEYBREADCRUMB, "0");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "0");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, "Map");

		viewList.add(viewMap1);

		// Start and end date filter insertion remaining
		ReportsCompliance rpt = new ReportsCompliance(rptTitle,
				testSetup.getLoginUser(), "Picarro", TIMEZONECT, "0",
				listBoundary, tablesList, SQAPICLOC3SUR, SQAPICSUTAG, viewList);
		complianceReportsPage.addNewReport(rpt);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle,
				testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle,
						testSetup.getLoginUser()));
			} else
				fail("\nTestcase RPT006 failed.\n");
		} else
			fail("\nTestcase RPT006 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT007 Test Description: Copy and modify report from
	 * previously run reports
	 * 
	 */
	@Test
	public void RPT007() {
		String rptTitle = "RPT007 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT007: Copy and modify report from previously run reports, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		String surUnit = "";
		List<String> surTag = new ArrayList<String>();
		surTag.add("");

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICSUTAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					PICDFADMIN));
		else
			fail("\nNew Report Generation failed.\n");

		complianceReportsPage.copyReportAndModifyDetails(rptTitle, PICDFADMIN,
				rptTitle + "COPY", surUnit, surTag, false, surUnit);

		if ((complianceReportsPage.checkActionStatus(rptTitle + "COPY",
				PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle
					+ "COPY", PICDFADMIN));
		else
			fail("\nTestcase RPT007 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT008 Test Description: Generate Compliance Report and
	 * include Coverage Percentage of the assets
	 * 
	 */
	@Test
	public void RPT008() {
		String rptTitle = "RPT008 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT008: Generate Compliance Report and include Coverage Percentage of the assets, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("10");
		listBoundary.add("10");
		listBoundary.add("37.42279859267848");
		listBoundary.add("-121.93553924560548");
		listBoundary.add("37.37493189292912");
		listBoundary.add("-122.08797454833983");

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "0");
		tableMap.put(KEYISOANA, "0");
		tableMap.put(KEYPCA, "1");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "0");
		viewMap1.put(KEYFOV, "0");
		viewMap1.put(KEYBREADCRUMB, "0");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "1");
		viewMap1.put(KEYBASEMAP, "Map");

		viewList.add(viewMap1);

		// Start and end date filter insertion remaining
		ReportsCompliance rpt = new ReportsCompliance(rptTitle,
				testSetup.getLoginUser(), "Picarro", TIMEZONECT, "0",
				listBoundary, tablesList, SQAPICLOC3SUR, SQAPICSUTAG, viewList);
		complianceReportsPage.addNewReport(rpt);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle,
				testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle,
						testSetup.getLoginUser()));
			} else
				fail("\nTestcase RPT008 failed.\n");
		} else
			fail("\nTestcase RPT008 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT009 Test Description: Generate Compliance Report and
	 * include Coverage Percentage by area
	 * 
	 */
	@Test
	public void RPT009() {
		String rptTitle = "RPT009 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT009: Generate Compliance Report and include Coverage Percentage by area, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("10");
		listBoundary.add("10");
		listBoundary.add("37.42279859267848");
		listBoundary.add("-121.93553924560548");
		listBoundary.add("37.37493189292912");
		listBoundary.add("-122.08797454833983");

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "0");
		tableMap.put(KEYISOANA, "0");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "1");
		tablesList.add(tableMap);

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "0");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, "Map");

		viewList.add(viewMap1);

		// Start and end date filter insertion remaining
		ReportsCompliance rpt = new ReportsCompliance(rptTitle,
				testSetup.getLoginUser(), "Picarro", TIMEZONECT, "0",
				listBoundary, tablesList, SQAPICLOC3SUR, SQAPICSUTAG, viewList);
		complianceReportsPage.addNewReport(rpt);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle,
				testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle,
						testSetup.getLoginUser()));
			} else
				fail("\nTestcase RPT009 failed.\n");
		} else
			fail("\nTestcase RPT009 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT010 Generate reports showing gaps that are not covered
	 * 
	 */
	@Test
	public void RPT010() {
		String rptTitle = "RPT010 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT010: Generate reports showing gaps that are not covered, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("10");
		listBoundary.add("10");
		listBoundary.add("37.40418739795187");
		listBoundary.add("-121.97484970092772");
		listBoundary.add("37.385435182627226");
		listBoundary.add("-121.99742317199707");

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "0");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "0");
		viewMap1.put(KEYINDICATIONS, "0");
		viewMap1.put(KEYISOTOPICCAPTURE, "0");
		viewMap1.put(KEYANNOTATION, "0");
		viewMap1.put(KEYGAPS, "1");
		viewMap1.put(KEYASSETS, "0");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, "Map");

		viewList.add(viewMap1);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle,
				testSetup.getLoginUser(), "Picarro", TIMEZONECT, "0",
				listBoundary, tablesList, SQAPICLOC3SUR, SQAPICSUTAG, viewList);
		complianceReportsPage.addNewReport(rpt);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle,
				testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle,
						testSetup.getLoginUser()));
			} else
				fail("\nTestcase RPT010 failed.\n");
		} else
			fail("\nTestcase RPT010 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT011 Test Description: Check that report cannot be
	 * generated unless all filters are selected
	 * 
	 */
	@Test
	public void RPT011() {
		System.out
				.format("\nRunning RPT011: Check that report cannot be generated unless all filters are selected");

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();
		assertTrue(complianceReportsPage.checkBlankReportErrorTextPresent());

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT015 Test Description: Pagination - 10,25,50 and 100
	 * Reports selection on compliance report screen
	 * 
	 */
	@Test
	public void RPT015() {
		System.out
				.format("\nRunning RPT015: Pagination - 10,25,50 and 100 Reports selection on compliance report screen");

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();
		String paginationSetting25 = "25";
		String paginationSetting50 = "50";
		String paginationSetting100 = "100";

		assertTrue(complianceReportsPage
				.checkPaginationSetting(PAGINATIONSETTING));
		assertTrue(complianceReportsPage
				.checkPaginationSetting(paginationSetting25));
		assertTrue(complianceReportsPage
				.checkPaginationSetting(paginationSetting50));
		assertTrue(complianceReportsPage
				.checkPaginationSetting(paginationSetting100));

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT016 Test Description: Screen should not refresh while
	 * searching an in-progress report, as it completes
	 * 
	 */
	@Test
	public void RPT016() {
		String rptTitle = "RPT016 Report" + testSetup.getRandomNumber();
		String surUnit = "";
		System.out
				.format("\nRunning RPT016: Screen should not refresh while searching an in-progress report, as it completes, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();
		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICSUTAG);
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		assertTrue(complianceReportsPage.searchReport(rptTitle, PICDFADMIN));

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN))) {
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					PICDFADMIN));
		} else
			fail("\nNot able to generate the report\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT017 Test Description: Search invalid reports
	 * 
	 */
	@Test
	public void RPT017() {
		String rptTitle = "RPT017 Report";
		System.out.format("\nRunning RPT017: Search invalid reports, %s\n",
				rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		assertTrue(!complianceReportsPage.searchReport(rptTitle, PICDFADMIN));

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT019 Test Description: Picarro Administrator can delete
	 * the specified report
	 * 
	 */
	@Test
	public void RPT019() {
		String rptTitle = "RPT019 Report" + testSetup.getRandomNumber();
		System.out.format(
				"\nRunning RPT019: User can delete the specified report, %s\n",
				rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		String surUnit = "";

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICDRTAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle,
				testSetup.getLoginUser())))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					testSetup.getLoginUser()));
		else
			fail("\nNew Compliance report creation failed.\n");

		if (complianceReportsPage.deleteReport(rptTitle,
				testSetup.getLoginUser()))
			assertTrue(!complianceReportsPage.findExistingReport(rptTitle,
					testSetup.getLoginUser()));
		else
			fail("\nTestcase RPT019 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT019A Test Description: RPT019A Test Description:
	 * Customer Utility Administrator can delete the specified
	 * 
	 */
	@Test
	public void RPT019A() {
		String rptTitle = "RPT019A Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT019A: Customer Utility Administrator can delete the specified report, %s\n",
						rptTitle);

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		complianceReportsPage.open();

		String surUnit = SQACUS + " - " + SQACUSLOC + "1" + " - "
				+ SQACUSLOC1SUR;

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQACUSUATAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSUA)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					SQACUSUA));
		else
			fail("\nNew Compliance report creation failed.\n");

		if (complianceReportsPage.deleteReport(rptTitle, SQACUSUA))
			assertTrue(!complianceReportsPage.findExistingReport(rptTitle,
					SQACUSUA));
		else
			fail("\nTestcase RPT019A failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT019B Test Description: RPT019A Test Description:
	 * Supervisor can delete the specified
	 * 
	 */
	@Test
	public void RPT019B() {
		String rptTitle = "RPT019B Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT019B: Supervisor can delete the specified report, %s\n",
						rptTitle);

		complianceReportsPage.login(SQACUSSU, USERPASSWORD);
		complianceReportsPage.open();

		String surUnit = SQACUS + " - " + SQACUSLOC + "2" + " - "
				+ SQACUSLOC2SUR;

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQACUSSUTAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSSU)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					SQACUSSU));
		else
			fail("\nNew Compliance report creation failed.\n");

		if (complianceReportsPage.deleteReport(rptTitle, SQACUSSU))
			assertTrue(!complianceReportsPage.findExistingReport(rptTitle,
					SQACUSSU));
		else
			fail("\nTestcase RPT019B failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT020 Generate compliance report for provided custom
	 * boundary without having the indications, Isotopic Analysis tables data
	 * and download it
	 * 
	 */
	@Test
	public void RPT020() {
		String rptTitle = "RPT020 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT020: Generate compliance report for provided custom boundary without having the indications, Isotopic Analysis tables data and download it, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("10");
		listBoundary.add("10");
		listBoundary.add("37.40418739795187");
		listBoundary.add("-121.97484970092772");
		listBoundary.add("37.385435182627226");
		listBoundary.add("-121.99742317199707");

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "1");
		viewMap1.put(KEYASSETS, "0");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, "Map");

		viewList.add(viewMap1);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "0");
		tableMap.put(KEYISOANA, "0");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle,
				testSetup.getLoginUser(), "Picarro", TIMEZONECT, "0",
				listBoundary, tablesList, SQAPICLOC3SUR, SQAPICSUTAG, viewList);
		complianceReportsPage.addNewReport(rpt);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle,
				testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle,
						testSetup.getLoginUser()));
			} else
				fail("\nTestcase RPT020 failed.\n");
		} else
			fail("\nTestcase RPT020 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT022 Test Description: Duplicate report
	 * 
	 */
	@Test
	public void RPT022() {
		String rptTitle = "RPT022 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning RPT022: Duplicate report, %s\n", rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		String surUnit = "";

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICSUTAG);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					PICDFADMIN));
		else
			fail("\nNew Report Generation failed.\n");

		complianceReportsPage.copyReport(rptTitle, PICDFADMIN, rptTitle
				+ "COPY");

		if ((complianceReportsPage.checkActionStatus(rptTitle + "COPY",
				PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle
					+ "COPY", PICDFADMIN));
		else
			fail("\nTestcase RPT022 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT023 Test Description: Generate compliance report for
	 * provided custom boundary using date range filter for manual surveys and
	 * download it
	 * 
	 */
	@Test
	public void RPT023() {
		String rptTitle = "RPT023 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT023: Generate compliance report for provided custom boundary using date range filter for manual surveys and download it, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		String surUnit = "";
		List<String> surTag = new ArrayList<String>();
		surTag.add(SQAPICADMANUALTAG);
		String reportMode = "Manual";
		boolean changeMode = true;

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, surTag,
				changeMode, reportMode);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					PICDFADMIN));
		else
			fail("\nTestcase RPT023 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT024 Test Description: Generate report for same surveys
	 * but in different modes
	 * 
	 */
	@Test
	public void RPT024() {
		String rptTitle = "RPT024 S1 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT024: Generate report for same surveys but in different modes, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		String surUnit = "";
		List<String> surTag = new ArrayList<String>();
		surTag.add(SQAPICADTAG);
		surTag.add(SQAPICADSTNDTAG);
		surTag.add(SQAPICADRRTAG);
		String reportMode = "S1";
		boolean changeMode = true;

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, surTag,
				changeMode, reportMode);
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle,
				testSetup.getLoginUser())))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					testSetup.getLoginUser()));
		else
			fail("\nS1 report creation failed.\n");

		rptTitle = "RPT024 Standard Report" + testSetup.getRandomNumber();
		reportMode = "Standard";

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, surTag,
				changeMode, reportMode);
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle,
				testSetup.getLoginUser())))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					testSetup.getLoginUser()));
		else
			fail("\nStandard report creation failed.\n");

		rptTitle = "RPT024 Rapid Response Report" + testSetup.getRandomNumber();
		reportMode = "Rapid Response";

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, surTag,
				changeMode, reportMode);
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					PICDFADMIN));
		else
			fail("\nTestcase RPT024 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT025 Test Description: Generate report as Picarro Admin
	 * user for the survey done by any of the customer user
	 * 
	 */
	@Test
	public void RPT025() {
		String rptTitle = "RPT025 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT025: Generate report as Picarro Admin user for the survey done by any of the customer user, %s\n",
						rptTitle);

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("10");
		listBoundary.add("10");
		listBoundary.add("37.40418739795187");
		listBoundary.add("-121.97484970092772");
		listBoundary.add("37.385435182627226");
		listBoundary.add("-121.99742317199707");

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "1");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "1");
		viewMap1.put(KEYBASEMAP, "Map");

		viewList.add(viewMap1);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "1");
		tableMap.put(KEYPCRA, "1");
		tablesList.add(tableMap);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, SQACUSSU,
				CUSNAMEBASE, TIMEZONEET, "0", listBoundary, tablesList,
				SQACUSLOC1SUR, SQACUSUATAG, viewList);
		complianceReportsPage.addNewReport(rpt);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN))) {
			if (complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle,
						PICDFADMIN));
			} else
				fail("\nTestcase RPT025 failed.\n");
		} else
			fail("\nTestcase RPT025 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT026 Test Description: Generate compliance report as
	 * customer supervisor
	 * 
	 */
	@Test
	public void RPT026() {
		String rptTitle = "RPT026 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT026: Generate compliance report as customer supervisor, %s\n",
						rptTitle);

		loginPage.open();
		loginPage.loginNormalAs(SQACUSSU, USERPASSWORD);
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("10");
		listBoundary.add("10");
		listBoundary.add("37.40418739795187");
		listBoundary.add("-121.97484970092772");
		listBoundary.add("37.385435182627226");
		listBoundary.add("-121.99742317199707");

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "1");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "1");
		viewMap1.put(KEYBASEMAP, "Map");

		viewList.add(viewMap1);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "1");
		tableMap.put(KEYPCRA, "1");
		tablesList.add(tableMap);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, SQACUSSU,
				CUSNAMEBASE, TIMEZONECT, "0", listBoundary, tablesList,
				SQACUSLOC2SUR, SQACUSSUTAG, viewList);
		complianceReportsPage.addNewReport(rpt);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSSU))) {
			if (complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle,
						SQACUSSU));
			} else
				fail("\nTestcase RPT026 failed.\n");
		} else
			fail("\nTestcase RPT026 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT027 Test Description: Generate compliance report as
	 * customer admin
	 * 
	 */
	@Test
	public void RPT027() {
		String rptTitle = "RPT027 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT027: Generate compliance report as customer admin, survey owner, %s\n",
						rptTitle);

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("10");
		listBoundary.add("10");
		listBoundary.add("37.40418739795187");
		listBoundary.add("-121.97484970092772");
		listBoundary.add("37.385435182627226");
		listBoundary.add("-121.99742317199707");

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "1");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "1");
		viewMap1.put(KEYBASEMAP, "Map");

		viewList.add(viewMap1);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "1");
		tableMap.put(KEYPCRA, "1");
		tablesList.add(tableMap);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, SQACUSSU,
				CUSNAMEBASE, TIMEZONEET, "0", listBoundary, tablesList,
				SQACUSLOC1SUR, SQACUSUATAG, viewList);
		complianceReportsPage.addNewReport(rpt);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSUA))) {
			if (complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle,
						SQACUSUA));
			} else
				fail("\nTestcase RPT027 failed.\n");
		} else
			fail("\nTestcase RPT027 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT030 Test Description: Generate Manual report from
	 * existing reports having surveys of S1 or standard or Rapid Response types
	 * using copy feature
	 * 
	 */
	@Test
	public void RPT030() {
		String rptTitle = "RPT030 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT030: Generate Manual report from existing reports having surveys of S1 or standard or Rapid Response types using copy feature , %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		String surUnit = "";
		List<String> surTag = new ArrayList<String>();
		surTag.add(SQAPICADTAG);
		surTag.add(SQAPICADSTNDTAG);
		surTag.add(SQAPICADRRTAG);
		String reportMode = "Rapid Response";
		String changeReportMode = "Manual";
		List<String> manualSurTag = new ArrayList<String>();
		manualSurTag.add(SQAPICADMANUALTAG);
		boolean changeMode = true;

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, surTag,
				changeMode, reportMode);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					PICDFADMIN));
		else
			fail("\nNew Report Generation failed.\n");

		String newRptTitle = "COPY" + rptTitle;

		complianceReportsPage.copyReportAndModifyDetails(rptTitle, PICDFADMIN,
				newRptTitle, surUnit, manualSurTag, true, changeReportMode);

		if ((complianceReportsPage.checkActionStatus(newRptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(newRptTitle,
					PICDFADMIN));
		else
			fail("\nTestcase RPT030 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT031 Test Description: Generate S1 or standard or rapid
	 * response report from existing reports having survey of Manual type using
	 * copy feature
	 * 
	 */
	@Test
	public void RPT031() {
		String rptTitle = "RPT031 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT031: Generate S1 or standard or rapid response report from existing reports having survey of Manual type using copy feature , %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		String surUnit = "";
		List<String> surTag = new ArrayList<String>();
		surTag.add(SQAPICADTAG);
		surTag.add(SQAPICADSTNDTAG);
		surTag.add(SQAPICADRRTAG);
		String reportMode = "Manual";
		String changeReportMode = "Rapid Response";
		List<String> manualSurTag = new ArrayList<String>();
		manualSurTag.add(SQAPICADMANUALTAG);
		boolean changeMode = true;

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, manualSurTag,
				changeMode, reportMode);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					PICDFADMIN));
		else
			fail("\nNew Report Generation failed.\n");

		String newRptTitle = "COPY" + rptTitle;

		complianceReportsPage.copyReportAndModifyDetails(rptTitle, PICDFADMIN,
				newRptTitle, surUnit, surTag, true, changeReportMode);

		if ((complianceReportsPage.checkActionStatus(newRptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(newRptTitle,
					PICDFADMIN));
		else
			fail("\nTestcase RPT031 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT032 Test Description: Generate S1 or standard report
	 * from existing reports having survey of Rapid Response type using copy
	 * feature
	 * 
	 */
	@Test
	public void RPT032() {
		String rptTitle = "RPT032 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT032: Generate S1 or standard report from existing reports having survey of Rapid Response type using copy feature, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		String surUnit = "";
		List<String> surTag = new ArrayList<String>();
		surTag.add(SQAPICADRRTAG);
		String reportMode = "Rapid Response";
		String changeReportMode = "S1";
		boolean changeMode = true;

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, surTag,
				changeMode, reportMode);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					PICDFADMIN));
		else
			fail("\nNew Report Generation failed.\n");

		String newRptTitle = "COPY" + rptTitle;

		complianceReportsPage.copyReportAndModifyDetails(rptTitle, PICDFADMIN,
				newRptTitle, surUnit, surTag, true, changeReportMode);

		if ((complianceReportsPage.checkActionStatus(newRptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(newRptTitle,
					PICDFADMIN));
		else
			fail("\nTestcase RPT032 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT033 Test Description: Generate report having multiple
	 * surveys of S1, Standard, Operator and Rapid Response types in Rapid
	 * Response report mode
	 * 
	 */
	@Test
	public void RPT033() {
		String rptTitle = "RPT033 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT033: Generate report having multiple surveys of S1, Standard, Operator and Rapid Response types in Rapid Response report mode, %s\n",
						rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		String surUnit = "";
		List<String> surTag = new ArrayList<String>();
		surTag.add(SQAPICADTAG);
		surTag.add(SQAPICADSTNDTAG);
		surTag.add(SQAPICADRRTAG);
		String reportMode = "Rapid Response";
		boolean changeMode = true;

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, surTag,
				changeMode, reportMode);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					PICDFADMIN));
		else
			fail("\nTestcase RPT033 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT034 Test Description: Very small or big report area
	 * selection not allowed
	 * 
	 */
	// IN PROGRESS TEST SCRIPT
	// @Test
	public void RPT034() {
		System.out
				.format("\nRunning RPT034: Very small or big report area selection not allowed\n");

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();
		
		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("10");
		listBoundary.add("10");
		listBoundary.add("37.39924180865303");
		listBoundary.add("-121.98283195495604");
		listBoundary.add("37.395527866984104");
		listBoundary.add("-121.98600769042967");
		String actualMsg = complianceReportsPage.provideLatLongAtCustomBoundarySelectorWindow(listBoundary);
		System.out.println(actualMsg);
		// Assert.assertEquals(actualMsg, STRReportAreaTooSmallMsg);
		
		listBoundary.add(2, "37.42252593456307");
		listBoundary.add(3, "-121.83494567871095");
		listBoundary.add(4, "37.27989023941680");
		listBoundary.add(5, "-122.05415725708008");
		actualMsg = complianceReportsPage.provideLatLongAtCustomBoundarySelectorWindow(listBoundary);
		System.out.println(actualMsg);
		// Assert.assertEquals(actualMsg, STRReportAreaTooLargeMsg);

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT035 Test Description: Click on Cancel button present on
	 * compliance report screen
	 * 
	 */
	@Test
	public void RPT035() {
		System.out
				.format("\nRunning RPT035: Click on Cancel button present on compliance report screen\n");

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		assertTrue(complianceReportsPage.verifyCancelButtonFunctionality());

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT038 Test Description: Generate report having multiple
	 * surveys and verify Gaps for them
	 * 
	 */
	@Test
	public void RPT038() {
		String rptTitle = "RPT038 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT038: Generate report having multiple surveys and verify Gaps for them, %s\n",
						rptTitle);

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("20");
		listBoundary.add("20");
		listBoundary.add("37.40418739795187");
		listBoundary.add("-121.97484970092772");
		listBoundary.add("37.385435182627226");
		listBoundary.add("-121.99742317199707");

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "0");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "0");
		viewMap1.put(KEYINDICATIONS, "0");
		viewMap1.put(KEYISOTOPICCAPTURE, "0");
		viewMap1.put(KEYANNOTATION, "0");
		viewMap1.put(KEYGAPS, "1");
		viewMap1.put(KEYASSETS, "0");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, "Map");

		viewList.add(viewMap1);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		String surUnit = "";
		String exclusionRadius = "0";
		String strCustomer = "Picarro";
		List<String> surTag = new ArrayList<String>();
		surTag.add(SQAPICADTAG);
		surTag.add(SQAPICADSTNDTAG);
		surTag.add(SQAPICADRRTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, PICDFADMIN,
				strCustomer, TIMEZONEET, exclusionRadius, listBoundary,
				tablesList, surUnit, surTag, viewList);
		complianceReportsPage.addNewReportWithMultipleSurveysIncluded(rpt);
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN))) {
			if (complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle,
						PICDFADMIN));
			} else
				fail("\nTestcase RPT038 failed.\n");
		} else
			fail("\nTestcase RPT038 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT039 Test Description: Generate report having multiple
	 * surveys and provide exclusion radius
	 * 
	 */
	@Test
	public void RPT039() {
		String rptTitle = "RPT039 Report" + testSetup.getRandomNumber();
		System.out
				.format("\nRunning RPT039: Generate report having multiple surveys and provide exclusion radius, %s\n",
						rptTitle);

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("10");
		listBoundary.add("10");
		listBoundary.add("37.40418739795187");
		listBoundary.add("-121.97484970092772");
		listBoundary.add("37.385435182627226");
		listBoundary.add("-121.99742317199707");

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();
		Map<String, String> viewMap2 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "0");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, "Map");

		viewMap2.put(KEYVIEWNAME, "Second View");
		viewMap2.put(KEYLISA, "1");
		viewMap2.put(KEYFOV, "1");
		viewMap2.put(KEYBREADCRUMB, "1");
		viewMap2.put(KEYINDICATIONS, "1");
		viewMap2.put(KEYISOTOPICCAPTURE, "1");
		viewMap2.put(KEYANNOTATION, "1");
		viewMap2.put(KEYGAPS, "0");
		viewMap2.put(KEYASSETS, "0");
		viewMap2.put(KEYBOUNDARIES, "0");
		viewMap2.put(KEYBASEMAP, "Satellite");

		viewList.add(viewMap1);
		viewList.add(viewMap2);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		String surUnit = "";
		String exclusionRadius = "100";
		String strCustomer = "Picarro";
		List<String> surTag = new ArrayList<String>();
		surTag.add(SQAPICADTAG);
		surTag.add(SQAPICADSTNDTAG);
		surTag.add(SQAPICADRRTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, PICDFADMIN,
				strCustomer, TIMEZONEET, exclusionRadius, listBoundary,
				tablesList, surUnit, surTag, viewList);
		complianceReportsPage.addNewReportWithMultipleSurveysIncluded(rpt);
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN))) {
			if (complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findExistingReport(rptTitle,
						PICDFADMIN));
			} else
				fail("\nTestcase RPT039 failed.\n");
		} else
			fail("\nTestcase RPT039 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT044 Test Description: Verify "Already Added" message is
	 * displayed if user tries to add the same survey again
	 * 
	 */
	@Test
	public void RPT044() {
		System.out
				.format("\nRunning 44: Verify 'Already Added' message is displayed if user tries to add the same survey again\n");

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();
		complianceReportsPage.openNewComplianceReportPage();

		assertTrue(complianceReportsPage.verifySurveyAlreadyAdded("Picarro",
				SQAPICSUTAG));
		assertTrue(complianceReportsPage
				.deleteSurveyAndIncludeAgain(SQAPICSUTAG));

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: RPT045 Test Description: Verify "Already Added" message is
	 * displayed if user tries to add the same survey again using copy
	 * functionality
	 * 
	 */
	@Test
	public void RPT045() {
		System.out
				.format("\nRunning 45: Verify 'Already Added' message is displayed if user tries to add the same survey again using copy functionality\n");

		complianceReportsPage.login(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		complianceReportsPage.open();
		String rptTitle = "RPT045 Report" + testSetup.getRandomNumber();
		String surUnit = "";

		// rptTitle = "RPT001 manual";

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, SQAPICSUTAG);
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN))) {
			assertTrue(complianceReportsPage.findExistingReport(rptTitle,
					PICDFADMIN));
		} else
			fail("\nRPT045 Report generation failed\n");

		complianceReportsPage.clickOnCopyReport(rptTitle, PICDFADMIN);

		assertTrue(complianceReportsPage.verifySurveyAlreadyAdded("Picarro",
				SQAPICSUTAG));
		assertTrue(complianceReportsPage
				.deleteSurveyAndIncludeAgain(SQAPICSUTAG));

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}
}