package common.source;

import retrofit2.Call;
import retrofit2.http.GET;
import backpack.api.entities.Data;

public interface BackPackApiInterface {
	@GET("api/v1.0/data")
	Call<Data> getV1Data();
}
