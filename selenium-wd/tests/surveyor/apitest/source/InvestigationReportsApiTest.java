package surveyor.apitest.source;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import common.source.Log;
import common.source.PCubedApiInterface;
import common.source.PCubedApiInvoker;
import common.source.PCubedApiInvoker.PCubedApiCall;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import surveyor.api.entities.InvestigationReports;
import surveyor.scommon.source.SurveyorConstants;
import surveyor.scommon.source.SurveyorTestRunner;

@RunWith(SurveyorTestRunner.class)
public class InvestigationReportsApiTest extends BaseApiTest {

	private static final Integer DEFAULT_SIZE = 100;
	private static final Integer DEFAULT_START_IDX = 0;
	private static final String COMPLIANCE_REPORT_TYPE = "Compliance";
	
	private static final String REPORT_ID = "93d4f0ea-9dc8-ab1d-0645-39df6ded672e";
	private static final String BOX_TYPE = "Indication";
//	private static final long BOUNDS = ;
	
	private static final String BOX_ID = "ce823be3-960b-431c-1439-39df6ded8c72";
	
	@BeforeClass
	public static void beforeReportsApiTestClass() {
		initializeTestObjects();
	}

	@Before
	public void beforeReportsApiTest() {
		initializeTestObjects();
	}

//	@Test
//	public void testGetInvestigationReports_UserWithNoInvReports_ShouldReturnZeroResults() throws IOException {
//		Log.info("Executing API test -> testGetInvestigationReports_UserWithNoInvReports_ShouldReturnZeroResults()...");
//		String verificationToken = getVerificationToken();
//		String loginResponse = executeLoginRequest(SurveyorConstants.SQAPGEUA, SurveyorConstants.USERPASSWORD, verificationToken);
//		Log.info(loginResponse);
//		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));
//
//		Response<InvestigationReports> response = apiInvoker.getInvestigationReports(COMPLIANCE_REPORT_TYPE, DEFAULT_START_IDX, DEFAULT_SIZE);
//		InvestigationReports investigationReports = PCubedApiInvoker.successResponse(response);
//		assertTrue("investigationReports should NOT be null", investigationReports != null);
//		assertTrue("No investigation reports should be returned.", investigationReports.getITotalRecords() == 0);
//	}
//
//	@Test
//	public void testGetInvestigationReports_CustomerWithoutLicense_ShouldReturnNullResponse() throws IOException {
//		Log.info("Executing API test -> testGetInvestigationReports_CustomerWithoutLicense_ShouldReturnNullResponse()...");
//		String verificationToken = getVerificationToken();
//		String loginResponse = executeLoginRequest("CusAdmUserWithNoLic@email.com", SurveyorConstants.USERPASSWORD, verificationToken);
//		Log.info(loginResponse);
//		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));
//		Response<InvestigationReports> response = apiInvoker.getInvestigationReports(COMPLIANCE_REPORT_TYPE, DEFAULT_START_IDX, DEFAULT_SIZE);
//		assertTrue("getInvestigationReports response should be null", response == null);
//
//	}
//
//	@Test
//	public void testGetInvestigationReports_UserWithMultipleReports_ShouldReturnMultipleReports() throws IOException {
//		Log.info("Executing API test -> testGetInvestigationReports_UserWithMultipleReports_ShouldReturnMultipleReports()...");
//		String verificationToken = getVerificationToken();
//		String loginResponse = executeLoginRequest(verificationToken);
//		Log.info(loginResponse);
//		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));
//
//		Response<InvestigationReports> response = apiInvoker.getInvestigationReports(COMPLIANCE_REPORT_TYPE, DEFAULT_START_IDX, DEFAULT_SIZE);
//		InvestigationReports investigationReports = PCubedApiInvoker.successResponse(response);
//		Log.info(String.format("Returned investigation reports -> %s", investigationReports));
//		assertTrue("investigationReports should NOT be null", investigationReports != null);
//		assertTrue("investigationReports totalRecords should be > 0", investigationReports.getITotalRecords() > 0);
//	}
//
//	@Test
//	public void testGetInvestigationReports_NonAuthenticatedCall_ShouldReturnNullResponse() throws IOException{
//		Log.info("Executing API test -> testGetInvestigationReports_NonAuthenticatedCall_ShouldReturnNullResponse()...");
//		Response<InvestigationReports> response = apiInvoker.getInvestigationReports(COMPLIANCE_REPORT_TYPE, DEFAULT_START_IDX, DEFAULT_SIZE);
//		assertTrue("getInvestigationReports response should be null", response == null);
//	}
//
//// Test Method #1
//	
//	@Test
//	public void testGetBoxesByReportId_ValidReportId_ValidBoxType_ValidBounds() throws IOException{
//		Log.info("Executing API test --> testGetBoxesByReportId()...");
//		String verificationToken = getVerificationToken();
//		String loginResponse = executeLoginRequest(SurveyorConstants.SQAPGEUA, SurveyorConstants.USERPASSWORD, verificationToken);
//		Log.info(loginResponse);
//		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));
//
//		Response<InvestigationBoxInfo> response = apiInvoker.getBoxesByReportId();
//		InvestigationBoxInfo investigationBoxInfo = PCubedApiInvoker.successResponse(response);
//		PCubedApiInterface apiInterface = PCubedApiCall.createInterface(null);
//		Call<InvestigationBoxInfo> invBoxInfoCall = apiInterface.getBoxesByReportId(REPORT_ID, BOX_TYPE);
//		invBoxInfoCall.execute();
//		
//		Log.info(String.format("Executing API test -> testGetBoxesByReportId_ValidReportId_ValidBoxType_ValidBounds()..."));
//		assertTrue("testGetBoxesByReportId_ValidReportId_ValidBoxType_ValidBounds() returns correct list", investigationBoxInfo!=null);		
//	}
//	
//	@Test
//	public void testGetBoxesByReportId_ValidReportId_ValidBoxType_InvalidBounds() throws IOException{
//		Log.info("Executing API test --> testGetBoxesByReportId()...");
//		String verificationToken = getVerificationToken();
//		String loginResponse = executeLoginRequest(SurveyorConstants.SQAPGEUA, SurveyorConstants.USERPASSWORD, verificationToken);
//		Log.info(loginResponse);
//		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));
//				
//		Response<InvestigationBoxInfo> response = apiInvoker.getBoxesByReportId();
//		InvestigationBoxInfo investigationBoxInfo = PCubedApiInvoker.successResponse(response);
//		PCubedApiInterface apiInterface = PCubedApiCall.createInterface(loginResponse);
//		Call<InvestigationBoxInfo> invBoxInfoCall = apiInterface.getBoxesByReportId(baseUrl, loginResponse);
//		invBoxInfoCall.execute();
//		
//		Log.info(String.format("Executing API test -> testGetBoxesByReportId_ValidReportId_ValidBoxType_ValidBounds()..."));
//		assertFalse("testGetBoxesByReportId_ValidReportId_ValidBoxType_ValidBounds() returns correct list", investigationBoxInfo!=null);	
//	}
//	
//	@Test
//	public void testGetBoxesByReportId_ValidReportId_InvalidBoxType_ValidBounds() throws IOException{
//		Log.info("Executing API test --> testGetBoxesByReportId()...");
//		String verificationToken = getVerificationToken();
//		String loginResponse = executeLoginRequest(SurveyorConstants.SQAPGEUA, SurveyorConstants.USERPASSWORD, verificationToken);
//		Log.info(loginResponse);
//		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));
//				
//		Response<InvestigationBoxInfo> response = apiInvoker.getBoxesByReportId();
//		InvestigationBoxInfo investigationBoxInfo = PCubedApiInvoker.successResponse(response);
//		PCubedApiInterface apiInterface = PCubedApiCall.createInterface(loginResponse);
//		Call<InvestigationBoxInfo> invBoxInfoCall = apiInterface.getBoxesByReportId(baseUrl, loginResponse);
//		invBoxInfoCall.execute();
//		
//		Log.info(String.format("Executing API test -> testGetBoxesByReportId_ValidReportId_ValidBoxType_ValidBounds()..."));
//		assertFalse("testGetBoxesByReportId_ValidReportId_ValidBoxType_ValidBounds() returns correct list", investigationBoxInfo!=null);		
//	}
//	
//	@Test
//	public void testGetBoxesByReportId_InvalidReportId_ValidBoxType_ValidBounds() throws IOException{
//		Log.info("Executing API test --> testGetBoxesByReportId()...");
//		String verificationToken = getVerificationToken();
//		String loginResponse = executeLoginRequest(SurveyorConstants.SQAPGEUA, SurveyorConstants.USERPASSWORD, verificationToken);
//		Log.info(loginResponse);
//		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));
//				
//		Response<InvestigationBoxInfo> response = apiInvoker.getBoxesByReportId();
//		InvestigationBoxInfo investigationBoxInfo = PCubedApiInvoker.successResponse(response);
//		PCubedApiInterface apiInterface = PCubedApiCall.createInterface(loginResponse);
//		Call<InvestigationBoxInfo> invBoxInfoCall = apiInterface.getBoxesByReportId(baseUrl, loginResponse);
//		invBoxInfoCall.execute();
//		
//		Log.info(String.format("Executing API test -> testGetBoxesByReportId_ValidReportId_ValidBoxType_ValidBounds()..."));
//		assertFalse("testGetBoxesByReportId_ValidReportId_ValidBoxType_ValidBounds() returns correct list", investigationBoxInfo!=null);		
//	}
//	
//	@Test
//	public void testGetBoxesByReportId_ValidReportId_InvalidBoxType_InvalidBounds() throws IOException{
//		Log.info("Executing API test --> testGetBoxesByReportId()...");
//		String verificationToken = getVerificationToken();
//		String loginResponse = executeLoginRequest(SurveyorConstants.SQAPGEUA, SurveyorConstants.USERPASSWORD, verificationToken);
//		Log.info(loginResponse);
//		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));
//				
//		Response<InvestigationBoxInfo> response = apiInvoker.getBoxesByReportId();
//		InvestigationBoxInfo investigationBoxInfo = PCubedApiInvoker.successResponse(response);
//		PCubedApiInterface apiInterface = PCubedApiCall.createInterface(loginResponse);
//		Call<InvestigationBoxInfo> invBoxInfoCall = apiInterface.getBoxesByReportId(baseUrl, loginResponse);
//		invBoxInfoCall.execute();
//		
//		Log.info(String.format("Executing API test -> testGetBoxesByReportId_ValidReportId_ValidBoxType_ValidBounds()..."));
//		assertFalse("testGetBoxesByReportId_ValidReportId_ValidBoxType_ValidBounds() returns correct list", investigationBoxInfo!=null);		
//	}
//	
//	@Test
//	public void testGetBoxesByReportId_InvalidReportId_InvalidBoxType_ValidBounds() throws IOException{
//		Log.info("Executing API test --> testGetBoxesByReportId()...");
//		String verificationToken = getVerificationToken();
//		String loginResponse = executeLoginRequest(SurveyorConstants.SQAPGEUA, SurveyorConstants.USERPASSWORD, verificationToken);
//		Log.info(loginResponse);
//		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));
//				
//		Response<InvestigationBoxInfo> response = apiInvoker.getBoxesByReportId();
//		InvestigationBoxInfo investigationBoxInfo = PCubedApiInvoker.successResponse(response);
//		PCubedApiInterface apiInterface = PCubedApiCall.createInterface(loginResponse);
//		Call<InvestigationBoxInfo> invBoxInfoCall = apiInterface.getBoxesByReportId(baseUrl, loginResponse);
//		invBoxInfoCall.execute();
//		
//		Log.info(String.format("Executing API test -> testGetBoxesByReportId_ValidReportId_ValidBoxType_ValidBounds()..."));
//		assertFalse("testGetBoxesByReportId_ValidReportId_ValidBoxType_ValidBounds() returns correct list", investigationBoxInfo!=null);		
//	}
//	
//	@Test
//	public void testGetBoxesByReportId_InvalidReportId_ValidBoxType_InvalidBounds() throws IOException{
//		Log.info("Executing API test --> testGetBoxesByReportId()...");
//		String verificationToken = getVerificationToken();
//		String loginResponse = executeLoginRequest(SurveyorConstants.SQAPGEUA, SurveyorConstants.USERPASSWORD, verificationToken);
//		Log.info(loginResponse);
//		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));
//				
//		Response<InvestigationBoxInfo> response = apiInvoker.getBoxesByReportId();
//		InvestigationBoxInfo investigationBoxInfo = PCubedApiInvoker.successResponse(response);
//		PCubedApiInterface apiInterface = PCubedApiCall.createInterface(loginResponse);
//		Call<InvestigationBoxInfo> invBoxInfoCall = apiInterface.getBoxesByReportId(baseUrl, loginResponse);
//		invBoxInfoCall.execute();
//		
//		Log.info(String.format("Executing API test -> testGetBoxesByReportId_ValidReportId_ValidBoxType_ValidBounds()..."));
//		assertFalse("testGetBoxesByReportId_ValidReportId_ValidBoxType_ValidBounds() returns correct list", investigationBoxInfo!=null);		
//	}
//	
//	@Test
//	public void testGetBoxesByReportId_InvalidReportId_InvalidBoxType_InvalidBounds() throws IOException{
//		Log.info("Executing API test --> testGetBoxesByReportId()...");
//		String verificationToken = getVerificationToken();
//		String loginResponse = executeLoginRequest(SurveyorConstants.SQAPGEUA, SurveyorConstants.USERPASSWORD, verificationToken);
//		Log.info(loginResponse);
//		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));
//				
//		Response<InvestigationBoxInfo> response = apiInvoker.getBoxesByReportId();
//		InvestigationBoxInfo investigationBoxInfo = PCubedApiInvoker.successResponse(response);
//		PCubedApiInterface apiInterface = PCubedApiCall.createInterface(loginResponse);
//		Call<InvestigationBoxInfo> invBoxInfoCall = apiInterface.getBoxesByReportId(loginResponse, loginResponse);
//		invBoxInfoCall.execute();
//		
//		Log.info(String.format("Executing API test -> testGetBoxesByReportId_ValidReportId_ValidBoxType_ValidBounds()..."));
//		assertFalse("testGetBoxesByReportId_ValidReportId_ValidBoxType_ValidBounds() returns correct list", investigationBoxInfo!=null);		
//	}
	
//	Test Method #2
	
	@Test
	public void testGetLeakListByBox_ValidBoxId() throws IOException{
		Log.info("Executing API test --> testGetLeakListByBox_ValidBoxId()...");
		String verificationToken = getVerificationToken();
		String loginResponse = executeLoginRequest(SurveyorConstants.PICDFADMIN, SurveyorConstants.USERPASSWORD, verificationToken);
		Log.info(loginResponse);
		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));
        
		Response<InvestigationBoxInfo> response = apiInvoker.getLeakListByBox(BOX_ID);
		InvestigationBoxInfo investigationBoxInfo = PCubedApiInvoker.successResponse(response);
		// call report guid and bring up correct box somehow
		PCubedApiInterface apiInterface = PCubedApiCall.createInterface(baseUrl);
		Call<InvestigationBoxInfo> invBoxInfoCall = apiInterface.getLeakListByBox(BOX_ID);
		invBoxInfoCall.execute();
		
//		Response<InvestigationBoxInfo> response = apiInvoker.getLeakListByBox();
//		InvestigationBoxInfo investigationBoxInfo = PCubedApiInvoker.successResponse(response);		
//		
		Log.info(String.format("Executing API test -> testGetLeakListByBox_ValidBoxId()..."));
		assertTrue(investigationBoxInfo!=null);
		
	}

//	@Test
//	public void testGetLeakListByBox_InvalidBoxId() throws IOException{
//		Log.info("Executing API test --> testGetLeakListByBox_InvalidBoxId()...");
//		String verificationToken = getVerificationToken();
//		String loginResponse = executeLoginRequest(SurveyorConstants.SQAPGEUA, SurveyorConstants.USERPASSWORD, verificationToken);
//		Log.info(loginResponse);
//		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));
//		
//		Response<InvestigationBoxInfo> response = apiInvoker.getLeakListByBox();
//		InvestigationBoxInfo investigationBoxInfo = PCubedApiInvoker.successResponse(response);
//		PCubedApiInterface apiInterface = PCubedApiCall.createInterface(loginResponse);
//		Call<InvestigationBoxInfo> invBoxInfoCall = apiInterface.getLeakListByBox(null);
//		invBoxInfoCall.execute();
//		
//		Log.info(String.format("Executing API test --> testGetLeakListByBox_InvalidBoxId()..."));
//		assertFalse("getLeakListByBox() returns null results", investigationBoxInfo!=null);
//	}
}