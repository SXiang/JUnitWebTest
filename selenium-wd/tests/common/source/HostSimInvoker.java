package common.source;

import java.io.IOException;

import common.source.HostSimulatorEntities.ReplayStatus;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class HostSimInvoker {
	public void stopReplay() {
		HostSimulatorInterface simulatorInterface = HostSimulatorCall.createInterface();
		simulatorInterface.stopReplay();
	}

	public void stop() {
		HostSimulatorInterface simulatorInterface = HostSimulatorCall.createInterface();
		simulatorInterface.stop();
	}

	public ReplayStatus getStatus() throws IOException {
		HostSimulatorInterface simulatorInterface = HostSimulatorCall.createInterface();
		Call<ReplayStatus> callStatus = simulatorInterface.getStatus();
		Response<ReplayStatus> response = callStatus.execute();
		if (response.isSuccessful()) {
			return response.body();
		}

		return null;
	}

	public static class BaseHostCall {
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

	public static class HostSimulatorCall extends BaseHostCall {
		private final String BASE_URL = "http://localhost:8080";
		private HostSimulatorInterface apiInterface;

		private HostSimulatorInterface getInterface() {
			if (apiInterface == null) {
				Retrofit builder = createBuilder(BASE_URL);
				apiInterface = builder.create(HostSimulatorInterface.class);
			}

			return apiInterface;
		}

		public static HostSimulatorInterface createInterface() {
			return new HostSimulatorCall().getInterface();
		}
	}}
