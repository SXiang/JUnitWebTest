package backpack.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import backpack.api.entities.Data;
import backpack.api.entities.SocketControlData;
import common.source.ApiCaller;
import common.source.BackPackApiInterface;
import common.source.BackPackAnalyzer;
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
		BaseTest.initializeTestObjects();
		BackPackAnalyzer.restartSimulator();
	}

	@Test
	public void testDataAPI() throws IOException {
		Log.info("Executing testDataAPI() test...");
		Log.info("Wait few seconds for data to be processed...");
		TestContext.INSTANCE.stayIdle(10);
		Data data = invokeGetV1DataApi();
		Log.info(String.format("Heatmap image data is: %s", data));
		assertTrue("Heatmap image invalid.", data.getData().getHeatmap().length() > 0);
		assertTrue("Heatmap image timestamp invalid.", data.getData().getHeatmapImageTime() >= 0.0);
	}

	@Test
	public void testPauseResumeSocketAPI() throws IOException {
		Log.info("Executing testResumeSocketAPI() test...");
		SocketControlData controlData = pauseResumeSockets(false /*isResume*/);
		Log.info(String.format("Socket control data onPause is: %s", controlData));
		assertTrue("Socket control data onPause is invalid.", controlData.getWebSocketEnabled() == 0.0);
		controlData = pauseResumeSockets(true /*isResume*/);
		Log.info(String.format("Socket control data onResume is: %s", controlData));
		assertTrue("Socket control data onResume is invalid.", controlData.getWebSocketEnabled() == 1.0);
	}

	@Ignore
	// Use this test to verify pause/resume socket on backpack analyzer.
	public void testPauseResumeBackPackAnalyzerAPI() throws IOException {
		Log.info("Executing testPauseResumeBackPackAnalyzerAPI() test...");
		SocketControlData controlData = pauseResumeSockets(TestContext.INSTANCE.getTestSetup().getBackPackServerIpAddress(), false /*isResume*/);
		Log.info(String.format("Socket control data onPause is: %s", controlData));
		assertTrue("Socket control data onPause is invalid.", controlData.getWebSocketEnabled() == 0.0);
		controlData = pauseResumeSockets(TestContext.INSTANCE.getTestSetup().getBackPackServerIpAddress(), true /*isResume*/);
		Log.info(String.format("Socket control data onResume is: %s", controlData));
		assertTrue("Socket control data onResume is invalid.", controlData.getWebSocketEnabled() == 1.0);
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

	private SocketControlData pauseResumeSockets(boolean isResume) throws IOException {
		Log.method("pauseResumeSockets", isResume);
		return pauseResumeSockets(null /*analyzerUrl*/, isResume);
	}

	private SocketControlData pauseResumeSockets(String analyzerUrl, boolean isResume) throws IOException {
		Log.method("pauseResumeSockets", analyzerUrl, isResume);
		BackPackApiInterface apiInterface = null;
		if (analyzerUrl == null) {
			apiInterface = ApiCaller.BackPackApiCall.createInterface();
		} else {
			apiInterface = ApiCaller.BackPackApiCall.createInterface(analyzerUrl);
		}

		Call<SocketControlData> controlData = null;
		if (!isResume) {
			controlData = apiInterface.pauseSocket();
		} else {
			controlData = apiInterface.resumeSocket();
		}

		Response<SocketControlData> response = controlData.execute();
		if (!response.isSuccessful()) {
			Log.error(String.format("FAILED: %s", response.toString()));
			fail("Invalid response from API call.");
		}

		Log.info(String.format("API Response -> %s", response.body().toString()));
		return response.body();
	}

	@After
	public void tearDown() throws Exception {
		BackPackAnalyzer.stopSimulator();
	}
}
