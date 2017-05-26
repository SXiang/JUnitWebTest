package surveyor.apitest.source;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import common.source.Log;
import common.source.PCubedApiInvoker;

public class AuthenticationApiTest extends BaseApiTest {

	@Test
	public void testAuthentication_ValidVerificationTokenValidCreds() throws IOException {
		Log.info("Executing API test -> testAuthentication_ValidVerificationTokenValidCreds()...");
		String verificationToken = getVerificationToken();
		String loginResponse = executeLoginRequest(verificationToken);
		Log.info(loginResponse);
		assertTrue(!loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));
	}

	@Ignore     // Turned off till expected behaviour of redirection to Analyzer page is understood.
	public void testAuthentication_ValidVerificationTokenInvalidCreds() throws IOException {
		Log.info("Executing API test -> testAuthentication_ValidVerificationTokenInvalidCreds()...");
		String verificationToken = getVerificationToken();
		String loginResponse = executeLoginRequest(USERNAME, INVALID_PASSWORD, verificationToken);
		Log.info(loginResponse);
		assertTrue("NOT authenticated. Should be redirected to login page",
				verifyIsRedirectedToLoginPage(PCubedApiInvoker.successResponse(apiInvoker.getComplianceReportsPage())));
	}

	@Test
	public void testAuthentication_InvalidVerificationTokenValidCreds() throws IOException {
		Log.info("Executing API test -> testAuthentication_InvalidVerificationTokenValidCreds()...");
		String loginResponse = executeLoginRequest(INVALID_VERIFICATION_TOKEN);
		Log.info(loginResponse);
		assertTrue(loginResponse.contains(LOGIN_FAIL_ERROR_MESSAGE));
	}
}