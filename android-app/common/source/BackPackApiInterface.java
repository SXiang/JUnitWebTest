package common.source;

import retrofit2.Call;
import retrofit2.http.GET;
import backpack.api.entities.Data;
import backpack.api.entities.SocketControlData;

public interface BackPackApiInterface {
	@GET("api/v1.0/data")
	Call<Data> getV1Data();

	@GET("api/v1.0/control?command=webSocket:0")
	Call<SocketControlData> pauseSocket();

	@GET("api/v1.0/control?command=webSocket:1")
	Call<SocketControlData> resumeSocket();
}