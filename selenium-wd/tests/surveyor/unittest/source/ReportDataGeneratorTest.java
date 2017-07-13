package surveyor.unittest.source;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.source.Log;
import surveyor.scommon.mobile.source.ReportDataGenerator;
import surveyor.scommon.source.BaseTest;

public class ReportDataGeneratorTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseTest.initializeTestObjects();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseTest.logoutQuitDriver();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateInvestigationReport() throws Exception {
		String testCaseID = "DUMMY TC_ID";
		final Integer userDataRowID = 6;
		final Integer mobileUserDataRowID = 16;
		final Integer reportDataRowID = 134;
		String reportName = ReportDataGenerator.newSingleUseGenerator().createReportAndAssignLisasToUser(testCaseID, userDataRowID, mobileUserDataRowID, reportDataRowID).getReportName();
		Log.info(String.format("Generated report [name=%s] and assigned lisas to user", reportName));
		assertTrue(reportName != null && reportName != "");
	}
}