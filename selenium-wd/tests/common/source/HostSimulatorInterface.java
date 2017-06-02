package common.source;

import common.source.HostSimulatorEntities.ReplayStatus;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HostSimulatorInterface {
	@POST("/replay/stop")
	Call<ResponseBody> stopReplay();

	@POST("/stop")
	Call<ResponseBody> stop();

	@GET("/status")
	Call<ReplayStatus> getStatus();
}