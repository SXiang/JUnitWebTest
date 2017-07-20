package surveyor.apitest.source;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.fasterxml.jackson.databind.JsonMappingException;

import common.source.Log;
import common.source.PCubedApiInterface;
import common.source.PCubedApiInvoker;
import common.source.PCubedApiInvoker.PCubedApiCall;
import retrofit2.Call;
import retrofit2.Response;
import surveyor.api.entities.InvestigationBoxInfo;
import surveyor.api.entities.InvestigationReportBoxInfos;
import surveyor.api.entities.InvestigationReports;
import surveyor.api.entities.Payload;
import surveyor.scommon.source.SurveyorConstants;
import surveyor.scommon.source.SurveyorTestRunner;

@RunWith(SurveyorTestRunner.class)
public class InvestigationReportsApiTest extends BaseApiTest {

	private static final Integer DEFAULT_SIZE = 100;
	private static final Integer DEFAULT_START_IDX = 0;
	private static final String COMPLIANCE_REPORT_TYPE = "Compliance";
	
	private static final String REPORT_ID = "838042ec-7415-7a65-78ad-39e04757485d";
	private static final String INVALID_REPORT_ID = "900000ea-9dc8-ab1d-0000-39df6ded672e";

	private static final String BOX_TYPE = "Indication";
	private static final String INVALID_BOX_TYPE = "invalid";
	
	private static final String BOX_ID = "bff32d7d-cf93-fed9-514b-39e047ef53a9";
	private static final String INVALID_BOX_ID = "ab7c56eb-1225-6dae-8cba-39dff621fd33";

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
	public void testGetInvestigationReports_NonAuthenticatedCall_ShouldReturnNullResponse() throws IOException{
		Log.info("Executing API test -> testGetInvestigationReports_NonAuthenticatedCall_ShouldReturnNullResponse()...");
		Response<InvestigationReports> response = apiInvoker.getInvestigationReports(COMPLIANCE_REPORT_TYPE, DEFAULT_START_IDX, DEFAULT_SIZE);
		assertTrue("getInvestigationReports response should be null", response == null);
	}
	
	@Test (expected = JsonMappingException.class)
	public void testGetBoxesByReportId_ValidReportId_ValidBoxType_ValidBounds() throws IOException{
		Log.info("Executing API test --> testGetBoxesByReportId()...");
		String verificationToken = getVerificationToken();
		String loginResponse = executeLoginRequest(SurveyorConstants.PICDFADMIN, SurveyorConstants.USERPASSWORD, verificationToken);
		Log.info(loginResponse);
		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));
		
		Payload payload = new Payload(37.396346133189255, 37.39198194353354, -121.9866943359375, -121.981201171875);		

		Response<InvestigationReportBoxInfos> response = apiInvoker.getBoxesByReportId(REPORT_ID, BOX_TYPE, payload);
		InvestigationReportBoxInfos investigationReportBoxInfos = PCubedApiInvoker.successResponse(response);
		PCubedApiInterface apiInterface = PCubedApiCall.createInterface(baseUrl);
		Call<InvestigationReportBoxInfos> invRepBoxInfosCall = apiInterface.getBoxesByReportId(REPORT_ID, BOX_TYPE, payload);
		invRepBoxInfosCall.execute();

		Log.info(String.format("Executing API test -> testGetBoxesByReportId_ValidReportId_ValidBoxType_ValidBounds()..."));
		assertTrue("testGetBoxesByReportId_ValidReportId_ValidBoxType_ValidBounds() returns correct list", investigationReportBoxInfos!=null);
	}

	@Test (expected = JsonMappingException.class)
	public void testGetBoxesByReportId_ValidReportId_ValidBoxType_InvalidBounds() throws IOException{
		Log.info("Executing API test --> testGetBoxesByReportId()...");
		String verificationToken = getVerificationToken();
		String loginResponse = executeLoginRequest(SurveyorConstants.PICDFADMIN, SurveyorConstants.USERPASSWORD, verificationToken);
		Log.info(loginResponse);
		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));

		Payload invalidPayload = new Payload(-6.0, 10.9, 55.8, 1235.6);
		
		Response<InvestigationReportBoxInfos> response = apiInvoker.getBoxesByReportId(REPORT_ID, BOX_TYPE, invalidPayload);
		InvestigationReportBoxInfos investigationReportBoxInfos = PCubedApiInvoker.successResponse(response);

		Log.info(String.format("Executing API test -> testGetBoxesByReportId_ValidReportId_ValidBoxType_InvalidBounds()..."));
		assertTrue("testGetBoxesByReportId_ValidReportId_ValidBoxType_InvalidBounds() returns correct list", investigationReportBoxInfos==null);
	}

	@Test
	public void testGetBoxesByReportId_ValidReportId_InvalidBoxType_ValidBounds() throws IOException{
		Log.info("Executing API test --> testGetBoxesByReportId()...");
		String verificationToken = getVerificationToken();
		String loginResponse = executeLoginRequest(SurveyorConstants.PICDFADMIN, SurveyorConstants.USERPASSWORD, verificationToken);
		Log.info(loginResponse);
		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));

		Payload payload = new Payload(37.396346133189255, 37.39198194353354, -121.9866943359375, -121.981201171875);

		Response<InvestigationReportBoxInfos> response = apiInvoker.getBoxesByReportId(REPORT_ID, INVALID_BOX_TYPE, payload);
		InvestigationReportBoxInfos investigationReportBoxInfos = PCubedApiInvoker.successResponse(response);

		Log.info(String.format("Executing API test -> testGetBoxesByReportId_ValidReportId_InvalidBoxType_ValidBounds()..."));
		assertTrue("testGetBoxesByReportId_ValidReportId_InvalidBoxType_ValidBounds() returns correct list", investigationReportBoxInfos==null);
	}

	@Test (expected = JsonMappingException.class)
	public void testGetBoxesByReportId_InvalidReportId_ValidBoxType_ValidBounds() throws IOException{
		Log.info("Executing API test --> testGetBoxesByReportId()...");
		String verificationToken = getVerificationToken();
		String loginResponse = executeLoginRequest(SurveyorConstants.PICDFADMIN, SurveyorConstants.USERPASSWORD, verificationToken);
		Log.info(loginResponse);
		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));

		Payload payload = new Payload(37.396346133189255, 37.39198194353354, -121.9866943359375, -121.981201171875);


		Response<InvestigationReportBoxInfos> response = apiInvoker.getBoxesByReportId(INVALID_REPORT_ID, BOX_TYPE, payload);
		InvestigationReportBoxInfos investigationReportBoxInfos = PCubedApiInvoker.successResponse(response);

		Log.info(String.format("Executing API test -> testGetBoxesByReportId_InvalidReportId_ValidBoxType_ValidBounds()..."));
		assertTrue("testGetBoxesByReportId_InvalidReportId_ValidBoxType_ValidBounds() returns correct list", investigationReportBoxInfos==null);
	}

	@Test
	public void testGetBoxesByReportId_ValidReportId_InvalidBoxType_InvalidBounds() throws IOException{
		Log.info("Executing API test --> testGetBoxesByReportId()...");
		String verificationToken = getVerificationToken();
		String loginResponse = executeLoginRequest(SurveyorConstants.PICDFADMIN, SurveyorConstants.USERPASSWORD, verificationToken);
		Log.info(loginResponse);
		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));

		Payload invalidPayload = new Payload(-35.0, 110.9, 5555.8, 12345.6);

		Response<InvestigationReportBoxInfos> response = apiInvoker.getBoxesByReportId(REPORT_ID, INVALID_BOX_TYPE, invalidPayload);
		InvestigationReportBoxInfos investigationReportBoxInfos = PCubedApiInvoker.successResponse(response);

		Log.info(String.format("Executing API test -> testGetBoxesByReportId_ValidReportId_InvalidBoxType_InvalidBounds()..."));
		assertTrue("testGetBoxesByReportId_ValidReportId_InvalidBoxType_InvalidBounds() returns correct list", investigationReportBoxInfos==null);
	}

	@Test
	public void testGetBoxesByReportId_InvalidReportId_InvalidBoxType_ValidBounds() throws IOException{
		Log.info("Executing API test --> testGetBoxesByReportId()...");
		String verificationToken = getVerificationToken();
		String loginResponse = executeLoginRequest(SurveyorConstants.PICDFADMIN, SurveyorConstants.USERPASSWORD, verificationToken);
		Log.info(loginResponse);
		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));

		Payload payload = new Payload(37.396346133189255, 37.39198194353354, -121.9866943359375, -121.981201171875);

		Response<InvestigationReportBoxInfos> response = apiInvoker.getBoxesByReportId(INVALID_REPORT_ID, INVALID_BOX_TYPE, payload);
		InvestigationReportBoxInfos investigationReportBoxInfos = PCubedApiInvoker.successResponse(response);

		Log.info(String.format("Executing API test -> testGetBoxesByReportId_InvalidReportId_InvalidBoxType_ValidBounds()..."));
		assertTrue("testGetBoxesByReportId_InvalidReportId_InvalidBoxType_ValidBounds() returns correct list", investigationReportBoxInfos==null);
	}

	@Test (expected = JsonMappingException.class)
	public void testGetBoxesByReportId_InvalidReportId_ValidBoxType_InvalidBounds() throws IOException{
		Log.info("Executing API test --> testGetBoxesByReportId()...");
		String verificationToken = getVerificationToken();
		String loginResponse = executeLoginRequest(SurveyorConstants.PICDFADMIN, SurveyorConstants.USERPASSWORD, verificationToken);
		Log.info(loginResponse);
		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));

		Payload invalidPayload = new Payload(-35.0, 110.9, 5555.8, 12345.6);

		Response<InvestigationReportBoxInfos> response = apiInvoker.getBoxesByReportId(INVALID_REPORT_ID, BOX_TYPE, invalidPayload);
		InvestigationReportBoxInfos investigationReportBoxInfos = PCubedApiInvoker.successResponse(response);

		Log.info(String.format("Executing API test -> testGetBoxesByReportId_InvalidReportId_ValidBoxType_InvalidBounds()..."));
		assertTrue("testGetBoxesByReportId_InvalidReportId_ValidBoxType_InvalidBounds() returns correct list", investigationReportBoxInfos==null);
	}

	@Test
	public void testGetBoxesByReportId_InvalidReportId_InvalidBoxType_InvalidBounds() throws IOException{
		Log.info("Executing API test --> testGetBoxesByReportId()...");
		String verificationToken = getVerificationToken();
		String loginResponse = executeLoginRequest(SurveyorConstants.PICDFADMIN, SurveyorConstants.USERPASSWORD, verificationToken);
		Log.info(loginResponse);
		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));

		Payload invalidPayload = new Payload(-35.0, 110.9, 5555.8, 12345.6);

		Response<InvestigationReportBoxInfos> response = apiInvoker.getBoxesByReportId(INVALID_REPORT_ID, INVALID_BOX_TYPE, invalidPayload);
		InvestigationReportBoxInfos investigationReportBoxInfos = PCubedApiInvoker.successResponse(response);

		Log.info(String.format("Executing API test -> testGetBoxesByReportId_InvalidReportId_InvalidBoxType_InvalidBounds()..."));
		assertTrue("testGetBoxesByReportId_InvalidReportId_InvalidBoxType_InvalidBounds() returns correct list", investigationReportBoxInfos==null);
	}
		
	@Test
	public void testGetLeakListByBox_ValidBoxId() throws IOException{
		Log.info("Executing API test --> testGetLeakListByBox_ValidBoxId()...");
		String verificationToken = getVerificationToken();
		String loginResponse = executeLoginRequest(SurveyorConstants.PICDFADMIN, SurveyorConstants.USERPASSWORD, verificationToken);
		Log.info(loginResponse);
		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));

		Response<InvestigationBoxInfo> response = apiInvoker.getLeakListByBox(BOX_ID);
		InvestigationBoxInfo investigationBoxInfo = PCubedApiInvoker.successResponse(response);
		PCubedApiInterface apiInterface = PCubedApiCall.createInterface(baseUrl);
		Call<InvestigationBoxInfo> invBoxInfoCall = apiInterface.getLeakListByBox(BOX_ID);
		invBoxInfoCall.execute();

		Log.info(String.format("Executing API test -> testGetLeakListByBox_ValidBoxId()..."));
		assertTrue(investigationBoxInfo!=null);
	}
	

	@Test
	public void testGetLeakListByBox_InvalidBoxId() throws IOException{
		Log.info("Executing API test --> testGetLeakListByBox_InvalidBoxId()...");
		String verificationToken = getVerificationToken();
		String loginResponse = executeLoginRequest(SurveyorConstants.PICDFADMIN, SurveyorConstants.USERPASSWORD, verificationToken);
		Log.info(loginResponse);
		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));

		Response<InvestigationBoxInfo> response = apiInvoker.getLeakListByBox(INVALID_BOX_ID);
		InvestigationBoxInfo investigationBoxInfo = PCubedApiInvoker.successResponse(response);


		Log.info(String.format("Executing API test --> testGetLeakListByBox_InvalidBoxId()..."));
		assertTrue("getLeakListByBox() returns null results", investigationBoxInfo == null);
	}
}