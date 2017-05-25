package common.source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.jackson.JacksonConverterFactory;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class PCubedApiInvoker {

	private String baseUrl = null;

	private static Boolean isAuthenticationRequest = false;
	private static Map<String, String> verificationTokens = Collections.synchronizedMap(new HashMap<String, String>());

	public PCubedApiInvoker(String baseUrl) {
		this.baseUrl = baseUrl;
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

	public static class PCubedCookieJar implements CookieJar {
		private static List<Cookie> cookies;
		private static List<Cookie> authenticationCookies;

		@Override
		public List<Cookie> loadForRequest(HttpUrl arg0) {
			List<Cookie> cookies = getCookies();
			if (cookies != null && cookies.size()>0) {
				List<Cookie> authCookies = getAuthenticatedCookies();
				if (authCookies != null && authCookies.size()>0) {
					List<Cookie> newCookies = new ArrayList<Cookie>();
					cookies.forEach(c -> newCookies.add(c));
					authCookies.forEach(c -> newCookies.add(c));
					return newCookies;
				}

				return cookies;
			}

			return new ArrayList<Cookie>();
		}

		@Override
		public void saveFromResponse(HttpUrl url, List<Cookie> cookiesList) {
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
		PCubedApiInterface apiInterface = PCubedApiCall.createInterface(baseUrl);
		Call<ResponseBody> loginCall = apiInterface.getRequestToken();
		return loginCall.execute();
	}

	public Response<ResponseBody> login(String username, String password, String requestVerificationToken) throws IOException {
		PCubedApiInterface apiInterface = PCubedApiCall.createInterface(baseUrl);
		Call<ResponseBody> loginCall = apiInterface.login(username, password, requestVerificationToken);
		return loginCall.execute();
	}

	public Response<ResponseBody> getInvestigationReports(String reportType, Integer startIdx, Integer size) throws IOException {
		PCubedApiInterface apiInterface = PCubedApiCall.createInterface(baseUrl);
		Call<ResponseBody> loginCall = apiInterface.getInvestigationReports(reportType, startIdx, size);
		return loginCall.execute();
	}
}