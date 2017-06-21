package common.source;

import okhttp3.OkHttpClient;
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
}