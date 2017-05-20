package common.source;

import common.source.HostSimulatorEntities.ReplayStatus;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HostSimulatorInterface {
	@POST("/replay/stop")
	void stopReplay();

	@POST("/stop")
	void stop();

	@GET("/status")
	Call<ReplayStatus> getStatus();
}