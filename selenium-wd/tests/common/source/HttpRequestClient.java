package common.source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

@SuppressWarnings("deprecation")
public class HttpRequestClient {

	public static void postRequest(String url, String requestBody) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(url);

			StringEntity input = new StringEntity(requestBody);
			input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				Log.error("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;
			Log.info("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				Log.info(output);
			}

			httpClient.getConnectionManager().shutdown();

		  } catch (MalformedURLException e) {
			e.printStackTrace();
		  } catch (IOException e) {
			e.printStackTrace();
		  }
	}
	
	/**
	 * Executes the unit tests for this class.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		testPostRequest_AutomationReportingAPI_Success();
	}	
	
	/**
	 * Test postRequest() method for success case. 
	 * ASSUMES Web API endpoint for reporting "http://localhost:59402/api/results" is ALIVE.
	 * NOTE: Use this ONLY when the Automation Reporting API endpoint is to be tested.
	 */
	private static void testPostRequest_AutomationReportingAPI_Success() {
		String url = "http://localhost:59402/api/results";
		String requestBody = "{\"HtmlString\":\"<html></html>\",\"Environment\":\"SQAAuto\",\"StartEpoch\":\"1455343445\",\"EndEpoch\":\"1455343446\"}";
		postRequest(url, requestBody);
	}
}

