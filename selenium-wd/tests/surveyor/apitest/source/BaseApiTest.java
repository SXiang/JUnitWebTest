package surveyor.apitest.source;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import common.source.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;

import common.source.PCubedApiInvoker;
import okhttp3.ResponseBody;
import retrofit2.Response;
import surveyor.scommon.source.BaseTest;

public class BaseApiTest extends BaseTest {
	protected static final String USERNAME = "AutomationAdmin";
	protected static final String PASSWORD = "sqa#Picarro$0";
	protected static final String BASE_URL = "https://p3sqaauto.picarro.com";

	protected static final String LOGIN_FAIL_ERROR_MESSAGE = "error has occured and we have automatically logged this information";
	protected static final String INVALID_VERIFICATION_TOKEN = "INVALID_VERIFICATION_TOKEN";
	protected static final String INVALID_PASSWORD = "INVALID_PASSWORD";

	protected PCubedApiInvoker apiInvoker = null;

	@Before
	public void beforeTestSetup() {
		apiInvoker = new PCubedApiInvoker(BASE_URL);
	}

	protected String getVerificationToken() throws IOException {
		Response<ResponseBody> requestVerificationToken = apiInvoker.requestVerificationToken();
		Document document = Jsoup.parse(requestVerificationToken.body().string());
		Elements inputTags = document.getElementsByTag("input");
		String verificationToken = null;
		for (int i = 0; i < inputTags.size(); i++) {
			Element element = inputTags.get(i);
			if (element.attr("name").equals("__RequestVerificationToken")) {
				verificationToken = element.val();
			}
		}

		return verificationToken;
	}

	protected boolean verifyIsRedirectedToLoginPage(ResponseBody response) throws IOException {
		Log.method("verifyIsRedirectedToLoginPage", response);
		Log.method("RESPONSE -> " + response.string());
		Document document = Jsoup.parse(response.string());
		boolean loginInputFound = false;
		boolean pwdInputFound = false;
		Elements inputTags = document.getElementsByTag("input");
		if (inputTags != null) {
			Log.info("Found inputTags");
			for (int i = 0; i < inputTags.size(); i++) {
				Element element = inputTags.get(i);
				Log.info("NAME=" + element.attr("name"));
				if (element.attr("name").equals("Username")) {
					loginInputFound = true;
				}
				if (element.attr("name").equals("Password")) {
					pwdInputFound = true;
				}
			}
		}

		return loginInputFound && pwdInputFound;
	}

	protected String executeLoginRequest(String verificationToken) throws IOException {
		return executeLoginRequest(USERNAME, PASSWORD, verificationToken);
	}

	protected String executeLoginRequest(String username, String password, String verificationToken) throws IOException {
		apiInvoker.setRequestVerificationToken(verificationToken);
		PCubedApiInvoker.setIsAuthenticationRequest(true);
		Response<ResponseBody> response = apiInvoker.login(USERNAME, PASSWORD, verificationToken);
		assertTrue(response.isSuccessful());
		Log.info(String.format("Response code = %d", response.code()));
		PCubedApiInvoker.setIsAuthenticationRequest(false);
		return response.body().string();
	}
}