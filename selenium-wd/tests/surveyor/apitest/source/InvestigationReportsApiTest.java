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
import surveyor.scommon.source.SurveyorConstants;
import surveyor.scommon.source.SurveyorTestRunner;

@RunWith(SurveyorTestRunner.class)
public class InvestigationReportsApiTest extends BaseApiTest {


	private static final String BOX_ID = "a7bc56eb-1225-6dae-8cba-39dff621fd33";
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