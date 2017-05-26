package surveyor.apitest.source;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import common.source.Log;
import common.source.PCubedApiInvoker;
import retrofit2.Response;
import surveyor.api.entities.InvestigationReports;

public class ReportsApiTest extends BaseApiTest {

	private static final Integer DEFAULT_SIZE = 100;
	private static final Integer DEFAULT_START_IDX = 0;
	private static final String COMPLIANCE_REPORT_TYPE = "Compliance";

	@Test
	public void testGetInvestigationReports_AuthenticatedCall() throws IOException {
		Log.info("Executing API test -> testGetInvestigationReports_AuthenticatedCall()...");
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
	public void testGetInvestigationReports_NonAuthenticatedCall() throws IOException {
		Log.info("Executing API test -> testGetInvestigationReports_NonAuthenticatedCall()...");
		Response<InvestigationReports> response = apiInvoker.getInvestigationReports(COMPLIANCE_REPORT_TYPE, DEFAULT_START_IDX, DEFAULT_SIZE);
		assertTrue("getInvestigationReports response should be null", response == null);
	}
}