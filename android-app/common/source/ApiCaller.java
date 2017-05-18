package common.source;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiCaller {
	public static class BaseApiCall {
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

	public static class BackPackApiCall extends BaseApiCall {
		private final String BASE_URL = "http://localhost:3000";
		private BackPackApiInterface apiInterface;

		private BackPackApiInterface getInterface() {
			if (apiInterface == null) {
				Retrofit builder = createBuilder(BASE_URL);
				apiInterface = builder.create(BackPackApiInterface.class);
			}

			return apiInterface;
		}

		public static BackPackApiInterface createInterface() {
			return new BackPackApiCall().getInterface();
		}
	}

	public static class PCubedApiCall extends BaseApiCall {
		private PCubedApiInterface apiInterface;
		private String baseUrl;
		private String username;
		private String password;
		private String authToken;

		public static PCubedApiInterface createInterfaceForAuthentication(String baseUrl, String username, String password) {
			return new PCubedApiCall(baseUrl, username, password).getInterface(false /*isAuthenticated*/);
		}

		public static PCubedApiInterface getAuthenticatedInterface(String baseUrl, String token) {
			return new PCubedApiCall(baseUrl, token).getInterface(true /*isAuthenticated*/);
		}

		private PCubedApiCall(String baseUrl, String username, String password) {
			this.baseUrl = baseUrl;
			this.username = username;
			this.password = password;
		}

		private PCubedApiCall(String baseUrl, String token) {
			this.baseUrl = baseUrl;
			this.authToken = token;
		}

		private PCubedApiInterface getInterface(Boolean isAuthenticated) {
			if (apiInterface == null) {
				Retrofit builder = createBuilder(createClient(isAuthenticated), baseUrl);
				apiInterface = builder.create(PCubedApiInterface.class);
			}

			return apiInterface;
		}

		private OkHttpClient createClient(Boolean isAuthenticated) {
			OkHttpClient.Builder builder = new OkHttpClient.Builder();
			if (!isAuthenticated) {
				return builder.authenticator(createAuthenticator())
						.build();
			} else {
				return builder.addInterceptor(createInterceptor())
						.build();
			}
		}

		private Authenticator createAuthenticator() {
			return new Authenticator() {
				@Override
				public Request authenticate(Route route, Response response) throws IOException {
					return response.request().newBuilder()
						.header("Authorization", Credentials.basic(username, password))
						.build();
				}
			};
		}

		private Interceptor createInterceptor() {
			return new Interceptor() {
				@Override
				public Response intercept(Chain chain) throws IOException {
					Request originalRequest = chain.request();
					if (authToken == null) {
						return chain.proceed(originalRequest);
					}

					Request newRequest = originalRequest.newBuilder()
						.header("Token", authToken)
						.method(originalRequest.method(), originalRequest.body())
						.build();

					return chain.proceed(newRequest);
				}
			};
		}
	}
}