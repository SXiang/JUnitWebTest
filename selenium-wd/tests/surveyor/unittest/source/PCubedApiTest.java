package surveyor.unittest.source;

import static org.junit.Assert.*;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import common.source.Log;
import common.source.PCubedApiInvoker;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class PCubedApiTest {

	private static final String USERNAME = "AutomationAdmin";
	private static final String PASSWORD = "sqa#Picarro$0";
	private static final String BASE_URL = "https://p3sqaauto.picarro.com";
	private static final Integer DEFAULT_SIZE = 100;
	private static final Integer DEFAULT_START_IDX = 0;
	private static final String COMPLIANCE_REPORT_TYPE = "Compliance";

	@Test
	public void testAPICalls() throws IOException {
		PCubedApiInvoker apiInvoker = new PCubedApiInvoker(BASE_URL);

		Log.info("INVOKING requestVerificationToken()...");
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

		Log.info(verificationToken);
		apiInvoker.setRequestVerificationToken(verificationToken);

		apiInvoker.setIsAuthenticated(true);

		Log.info("INVOKING login()...");
		Response<ResponseBody> response = apiInvoker.login(USERNAME, PASSWORD, verificationToken);
		assertTrue(response.isSuccessful());
		String responseString = response.body().string();
		Log.info(responseString);
		assertTrue(!responseString.contains("error has occured"));

		Log.info("INVOKING getInvestigationReports()...");
		response = apiInvoker.getInvestigationReports(COMPLIANCE_REPORT_TYPE, DEFAULT_START_IDX, DEFAULT_SIZE);
		assertTrue(response.isSuccessful());
		responseString = response.body().string();
		Log.info(responseString);
		assertTrue(!responseString.contains("error has occured"));
	}
}
