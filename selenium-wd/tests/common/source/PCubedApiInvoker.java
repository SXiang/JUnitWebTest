package common.source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.jackson.JacksonConverterFactory;
import surveyor.api.entities.InvestigationBoxInfo;
import surveyor.api.entities.InvestigationReportBoxInfos;
import surveyor.api.entities.InvestigationReports;
import surveyor.api.entities.Payload;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class PCubedApiInvoker {

	private String baseUrl = null;

	private static Boolean isAuthenticationRequest = false;
	private static Map<String, String> verificationTokens = Collections.synchronizedMap(new HashMap<String, String>());

	public PCubedApiInvoker(String baseUrl) {
		this.baseUrl = baseUrl;
		init();
	}

	private void init() {
		verificationTokens.clear();
		PCubedCookieJar.setCookies(null);
		PCubedCookieJar.setAuthenticationCookies(null);
	}

	public static class BaseWebHostCall {
		protected Retrofit createBuilder(String baseUrl) {
			return createDefaultBuilder(baseUrl)
					.build();
		}

		protected Retrofit createBuilder(OkHttpClient client, String baseUrl) {
			return createDefaultBuilder(baseUrl)
				    .client(client)
				    .build();
		}

		private Builder createDefaultBuilder(String baseUrl) {
			return new Retrofit.Builder()
				    .baseUrl(baseUrl)
				    .addConverterFactory(JacksonConverterFactory.create());
		}
	}

	public static class PCubedApiCall extends BaseWebHostCall {
		private PCubedApiInterface apiInterface;
		private String baseUrl;

		public PCubedApiCall(String baseUrl) {
			this.baseUrl = baseUrl;
		}

		private PCubedApiInterface getInterface(String baseUrl) {
			if (apiInterface == null) {
				OkHttpClient httpClient = new OkHttpClient.Builder()
					.cookieJar(new PCubedCookieJar())
					.addInterceptor(createRequestVerificationInterceptor())
					.build();
				Retrofit builder = createBuilder(httpClient, baseUrl);
				apiInterface = builder.create(PCubedApiInterface.class);
			}

			return apiInterface;
		}

		private Interceptor createRequestVerificationInterceptor() {
			return new Interceptor() {
				@Override
				public okhttp3.Response intercept(Chain chain) throws IOException {
					Request originalRequest = chain.request();
					Request newRequest = originalRequest;
					String verificationToken = getRequestVerificationToken(baseUrl);
					if (verificationToken != null) {
						String cookieValue = String.format("__RequestVerificationToken=%s;", verificationToken);
						Log.info(String.format("[Cookie] -> %s", cookieValue));
						newRequest = originalRequest.newBuilder()
							.addHeader("Cookie", cookieValue)
							.method(originalRequest.method(), originalRequest.body())
							.build();
					}

					return chain.proceed(newRequest);
				}
			};
		}

		public static PCubedApiInterface createInterface(String baseUrl) {
			return new PCubedApiCall(baseUrl).getInterface(baseUrl);
		}
	}

	public static class PCubedCookieJar implements CookieJar {
		private static List<Cookie> cookies;
		private static List<Cookie> authenticationCookies;

		@Override
		public List<Cookie> loadForRequest(HttpUrl url) {
			Log.method("loadForRequest", url);
			List<Cookie> cookies = getCookies();
			if (cookies != null && cookies.size()>0) {
				List<Cookie> authCookies = getAuthenticatedCookies();
				if (authCookies != null && authCookies.size()>0) {
					List<Cookie> newCookies = new ArrayList<Cookie>();
					cookies.forEach(c -> newCookies.add(c));
					authCookies.forEach(c -> newCookies.add(c));
					return newCookies;
				}

				Log.info(String.format("[LoadForRequest-Cookies] -> %s", LogHelper.collectionToString(cookies, "cookies")));
				return cookies;
			}

			return new ArrayList<Cookie>();
		}

		@Override
		public void saveFromResponse(HttpUrl url, List<Cookie> cookiesList) {
			Log.method("saveFromResponse", url, LogHelper.collectionToString(cookies, "cookies"));
			if (PCubedApiInvoker.isAuthenticationRequest()) {
				this.setAuthenticationCookies(cookiesList);
			} else {
				this.setCookies(cookiesList);
			}
		}

		public static List<Cookie> getAuthenticatedCookies() {
			return authenticationCookies;
		}

		public static void setAuthenticationCookies(List<Cookie> authCookies) {
			PCubedCookieJar.authenticationCookies = authCookies;
		}

		public static List<Cookie> getCookies() {
			return cookies;
		}

		public static void setCookies(List<Cookie> cookies) {
			PCubedCookieJar.cookies = cookies;
		}
	}

	public static String getRequestVerificationToken(String baseUrl) {
		if (verificationTokens.containsKey(baseUrl)) {
			return verificationTokens.get(baseUrl);
		}

		return null;
	}

	public void setRequestVerificationToken(String requestVerificationToken) {
		if (!verificationTokens.containsKey(baseUrl)) {
			verificationTokens.put(baseUrl, requestVerificationToken);
		}
	}

	public static Boolean isAuthenticationRequest() {
		return isAuthenticationRequest;
	}

	public static void setIsAuthenticationRequest(Boolean authenticated) {
		isAuthenticationRequest = authenticated;
	}

	public Response<ResponseBody> requestVerificationToken() throws IOException {
		Log.method("requestVerificationToken");
		PCubedApiInterface apiInterface = PCubedApiCall.createInterface(baseUrl);
		Call<ResponseBody> loginCall = apiInterface.getRequestToken();
		return loginCall.execute();
	}

	public Response<ResponseBody> login(String username, String password, String requestVerificationToken) throws IOException {
		Log.method("login", username, "<HIDDEN>", requestVerificationToken);
		PCubedApiInterface apiInterface = PCubedApiCall.createInterface(baseUrl);
		Call<ResponseBody> loginCall = apiInterface.login(username, password, requestVerificationToken);
		return loginCall.execute();
	}

	public Response<InvestigationReports> getInvestigationReports(String reportType, Integer startIdx, Integer size) throws IOException {
		Log.method("getInvestigationReports", reportType, startIdx, size);
		PCubedApiInterface apiInterface = PCubedApiCall.createInterface(baseUrl);
		try {
			Call<InvestigationReports> invReportsCall = apiInterface.getInvestigationReports(reportType, startIdx, size);
			return invReportsCall.execute();
		} catch (JsonParseException ex) {
			Log.error("Error parsing response. Possible reasons -> 1) Non JSON response returned. 2) Non-authenticated call returns non-json response.");
		}

		return null;
	}

	public Response<ResponseBody> getComplianceReportsPage() throws IOException {
		Log.method("getComplianceReportsPage");
		PCubedApiInterface apiInterface = PCubedApiCall.createInterface(baseUrl);
		Call<ResponseBody> cmpRptsCall = apiInterface.getComplianceReportsPage();
		return cmpRptsCall.execute();

	}

	public static <T> T successResponse(Response<T> response) {
			if (response!= null && response.isSuccessful()) {
				return  response.body();
			}
		return null;
	}

	public Response<InvestigationBoxInfo> getLeakListByBox(String boxId) throws IOException {
		Log.method("getLeakListByBox");
		PCubedApiInterface apiInterface = PCubedApiCall.createInterface(baseUrl);
		try {
			Call<InvestigationBoxInfo> invBoxInfoCall = apiInterface.getLeakListByBox(boxId);
				return invBoxInfoCall.execute();
		} catch (JsonParseException ex) {
			Log.error("Error parsing response. Possible reasons -> 1) Non JSON response returned. 2) Non-authenticated call returns non-json response.");
		}

		return null;
	}

	public Response<InvestigationReportBoxInfos> getBoxesByReportId(String reportId, String type, Payload body) throws IOException {
		Log.method("getBoxesByReportId");
		PCubedApiInterface apiInterface = PCubedApiCall.createInterface(baseUrl);
		try{
			Call<InvestigationReportBoxInfos> invRepBoxInfosCall = apiInterface.getBoxesByReportId(reportId, type, body);
			return invRepBoxInfosCall.execute();
		} catch (JsonParseException ex) {
			Log.error("Error parsing response. Possible reasons -> 1) Non JSON response returned. 2) Non-authenticated call returns non-json response.");
		}

		return null;
	}
}