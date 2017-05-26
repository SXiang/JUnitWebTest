package surveyor.apitest.source;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import common.source.Log;
import common.source.PCubedApiInvoker;
import retrofit2.Response;
import surveyor.api.entities.InvestigationReports;
import surveyor.scommon.source.SurveyorConstants;
import surveyor.scommon.source.SurveyorTestRunner;

@RunWith(SurveyorTestRunner.class)
public class ReportsApiTest extends BaseApiTest {

	private static final Integer DEFAULT_SIZE = 100;
	private static final Integer DEFAULT_START_IDX = 0;
	private static final String COMPLIANCE_REPORT_TYPE = "Compliance";

	@BeforeClass
	public static void beforeReportsApiTestClass() {
		initializeTestObjects();
	}

	@Before
	public void beforeReportsApiTest() {
		initializeTestObjects();
	}

	@Test
	public void testGetInvestigationReports_UserWithNoInvReports_ShouldReturnZeroResults() throws IOException {
		Log.info("Executing API test -> testGetInvestigationReports_UserWithNoInvReports_ShouldReturnZeroResults()...");
		String verificationToken = getVerificationToken();
		String loginResponse = executeLoginRequest(SurveyorConstants.SQAPGEUA, SurveyorConstants.USERPASSWORD, verificationToken);
		Log.info(loginResponse);
		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));

		Response<InvestigationReports> response = apiInvoker.getInvestigationReports(COMPLIANCE_REPORT_TYPE, DEFAULT_START_IDX, DEFAULT_SIZE);
		InvestigationReports investigationReports = PCubedApiInvoker.successResponse(response);
		assertTrue("investigationReports should NOT be null", investigationReports != null);
		assertTrue("No investigation reports should be returned.", investigationReports.getITotalRecords() == 0);
	}

	@Test
	public void testGetInvestigationReports_CustomerWithoutLicense_ShouldReturnNullResponse() throws IOException {
		Log.info("Executing API test -> testGetInvestigationReports_CustomerWithoutLicense_ShouldReturnNullResponse()...");
		String verificationToken = getVerificationToken();
		String loginResponse = executeLoginRequest("CusAdmUserWithNoLic@email.com", SurveyorConstants.USERPASSWORD, verificationToken);
		Log.info(loginResponse);
		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));
		Response<InvestigationReports> response = apiInvoker.getInvestigationReports(COMPLIANCE_REPORT_TYPE, DEFAULT_START_IDX, DEFAULT_SIZE);
		assertTrue("getInvestigationReports response should be null", response == null);

	}

	@Test
	public void testGetInvestigationReports_UserWithMultipleReports_ShouldReturnMultipleReports() throws IOException {
		Log.info("Executing API test -> testGetInvestigationReports_UserWithMultipleReports_ShouldReturnMultipleReports()...");
		String verificationToken = getVerificationToken();
		String loginResponse = executeLoginRequest(verificationToken);
		Log.info(loginResponse);
		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));

		Response<InvestigationReports> response = apiInvoker.getInvestigationReports(COMPLIANCE_REPORT_TYPE, DEFAULT_START_IDX, DEFAULT_SIZE);
		InvestigationReports investigationReports = PCubedApiInvoker.successResponse(response);
		Log.info(String.format("Returned investigation reports -> %s", investigationReports));
		assertTrue("investigationReports should NOT be null", investigationReports != null);
		assertTrue("investigationReports totalRecords should be > 0", investigationReports.getITotalRecords() > 0);
	}

	@Test
	public void testGetInvestigationReports_NonAuthenticatedCall_ShouldReturnNullResponse() throws IOException {
		Log.info("Executing API test -> testGetInvestigationReports_NonAuthenticatedCall_ShouldReturnNullResponse()...");
		Response<InvestigationReports> response = apiInvoker.getInvestigationReports(COMPLIANCE_REPORT_TYPE, DEFAULT_START_IDX, DEFAULT_SIZE);
		assertTrue("getInvestigationReports response should be null", response == null);
	}
}