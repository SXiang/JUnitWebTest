package surveyor.apitest.source;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import common.source.Log;
import common.source.PCubedApiInvoker;
import surveyor.scommon.source.SurveyorTestRunner;

@RunWith(SurveyorTestRunner.class)
public class AuthenticationApiTest extends BaseApiTest {

	@BeforeClass
	public static void beforeAuthenticationApiTestClass() {
		initializeTestObjects();
	}

	@Before
	public void beforeAuthenticationApiTest() {
		initializeTestObjects();
	}

	@Test
	public void testAuthentication_ValidVerificationTokenValidCreds_ShouldReturnValidResponse() throws IOException {
		Log.info("Executing API test -> testAuthentication_ValidVerificationTokenValidCreds_ShouldReturnValidResponse()...");
		String verificationToken = getVerificationToken();
		String loginResponse = executeLoginRequest(verificationToken);
		Log.info(loginResponse);
		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));
	}

	@Ignore     // Turned off till expected behaviour of redirection to Analyzer page is understood.
	public void testAuthentication_ValidVerificationTokenInvalidCreds_ShouldNotBeAbleToGotoReportsPage() throws IOException {
		Log.info("Executing API test -> testAuthentication_ValidVerificationTokenInvalidCreds_ShouldNotBeAbleToGotoReportsPage()...");
		String verificationToken = getVerificationToken();
		String loginResponse = executeLoginRequest(USERNAME, INVALID_PASSWORD, verificationToken);
		Log.info(loginResponse);
		assertTrue("NOT authenticated. Should be redirected to login page",
				verifyIsRedirectedToLoginPage(PCubedApiInvoker.successResponse(apiInvoker.getComplianceReportsPage())));
	}

	@Test
	public void testAuthentication_InvalidVerificationTokenValidCreds_ShouldReturnErrorResponse() throws IOException {
		Log.info("Executing API test -> testAuthentication_InvalidVerificationTokenValidCreds_ShouldReturnErrorResponse()...");
		String loginResponse = executeLoginRequest(INVALID_VERIFICATION_TOKEN);
		Log.info(loginResponse);
		assertTrue(loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));
	}
}