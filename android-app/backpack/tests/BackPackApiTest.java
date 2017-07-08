package backpack.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import backpack.api.entities.Data;
import common.source.ApiCaller;
import common.source.BackPackApiInterface;
import common.source.BackPackSimulator;
import common.source.Log;
import common.source.TestContext;
import retrofit2.Call;
import retrofit2.Response;
import surveyor.scommon.source.BaseTest;
import surveyor.scommon.source.SurveyorTestRunner;

@RunWith(SurveyorTestRunner.class)
public class BackPackApiTest extends BaseTest {

	@Before
	public void setUp() throws Exception {
		BackPackSimulator.restartSimulator();
	}

	@Test
	public void testDataAPI() throws IOException {
		Log.info("Executing testDataAPI() test...");
		Log.info("Wait few seconds for data to be processed...");
		TestContext.INSTANCE.stayIdle(10);
		Data data = invokeGetV1DataApi();
		Log.info(String.format("Heatmap image data is: %s", data));
		assertTrue("Heatmap image invalid.", data.getData().getHeatmap().length() > 0);
		assertTrue("Heatmap image timestamp invalid.", data.getData().getHeatmapImageTime() > 0.0);
	}

	private Data invokeGetV1DataApi() throws IOException {
		Log.method("invokeGetDataApi");
		BackPackApiInterface apiInterface = ApiCaller.BackPackApiCall.createInterface();
		Call<Data> callData = apiInterface.getV1Data();
		Response<Data> response = callData.execute();
		if (!response.isSuccessful()) {
			Log.error(String.format("FAILED: %s", response.toString()));
			fail("Invalid response from API call.");
		}

		Log.info(String.format("API Response -> %s", response.body().toString()));
		return response.body();
	}

	@After
	public void tearDown() throws Exception {
		BackPackSimulator.stopSimulator();
	}
}
