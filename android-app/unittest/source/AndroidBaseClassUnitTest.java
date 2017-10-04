package unittest.source;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import common.source.AdbInterface;
import common.source.BaseHelper;
import common.source.Log;
import common.source.LogHelper;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.StoredProcLisaInvestigationShowIndication;
import surveyor.dataaccess.source.User;

public class AndroidBaseClassUnitTest {

	private static final String REPORT_BELONGING_TO_SQACUS_CUSTOMER = "TC2202-f6698a814b6e4341a855";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Initialize TestSetup to instantiate the TestContext.
		TestSetup testSetup = new TestSetup(false /* skip initialization */);
		String rootPath;
		try {
			rootPath = TestSetup.getRootPath();
			testSetup.loadTestProperties(rootPath);
		} catch (IOException e) {
			Log.error(e.getMessage());
		}
		testSetup.initialize();
		TestContext.INSTANCE.setTestSetup(testSetup);

		AdbInterface.init(testSetup.getAdbLocation());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testVerifyReportsAssignedToCustomerAreShown() {
		String reportTitle = REPORT_BELONGING_TO_SQACUS_CUSTOMER;
		User user = User.getUser("sqacussu@email.com");
		List<String> usernames = User.getUsersForCustomer(user.getCustomerId()).stream()
			.map(u -> u.getUserName())
			.collect(Collectors.toList());
		Log.info(String.format("Usernames for customer are -> %s", LogHelper.listToString(usernames)));
		boolean match = testReportBelongsToCustomer(reportTitle, s -> usernames.contains(s.getAssignedUserName()));
		assertTrue("Expected to return TRUE. Found false instead.", match);
	}

	private boolean testReportBelongsToCustomer(String reportTitleToTest, Predicate<StoredProcLisaInvestigationShowIndication> matchPredicate) {
		Report reportObj = Report.getReport(reportTitleToTest);
		if (reportObj != null) {
			String reportId = reportObj.getId();
			Log.info(String.format("Searching for assigned LISAs in report id='%s'", reportId));
			List<StoredProcLisaInvestigationShowIndication> lisaInvestigationfromSP = StoredProcLisaInvestigationShowIndication.getLisaInvestigation(reportId);
			if (lisaInvestigationfromSP != null && lisaInvestigationfromSP.size()>0) {
				Log.info(String.format("Investigated LISAs for reportId=[%s] -> %s", reportId,
					LogHelper.collectionToString(lisaInvestigationfromSP, "lisaInvestigationfromSP")));
				return lisaInvestigationfromSP.stream().anyMatch(s -> {
					if (s.getAssignedUserName()!=null) {
						if (!BaseHelper.isNullOrEmpty(s.getAssignedUserName().trim())) {
							Log.info("Executing match predicate");
							return matchPredicate.test(s);
						}
					}

					return true;
				});
			}

			Log.info(String.format("Found no assigned LISAs for this report id='%s'", reportId));
			return true;
		} else {
			Log.info(String.format("Report shown on screen NOT found in database. Report appears to be deleted from DB, report title=[%s]", reportTitleToTest));
			return true;
		}

	}

}
