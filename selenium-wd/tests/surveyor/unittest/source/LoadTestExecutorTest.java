package surveyor.unittest.source;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.Test;

import common.source.LoadTestExecutor;
import common.source.Log;
import common.source.LoadTestExecutor.HttpMethod;
import common.source.LoadTestExecutor.TestResult;
import common.source.TestSetup;

public class LoadTestExecutorTest {

	// NOTE: This is local test endpoint. Before running unit tests from this class ensure that this endpoint is alive.
	private static final String automationReportingApiEndpoint = "http://localhost:63087";
	//private static final String automationReportingApiEndpoint = "http://20.20.152.36:8082";
	private static TestSetup testSetup = null;

	private static LoadTestExecutor loadTestExecutor = null;

	@BeforeClass
	public static void beforeClass() throws IOException {
		testSetup = new TestSetup();
		testSetup.setAutomationReportingApiEndpoint(automationReportingApiEndpoint);
		loadTestExecutor = LoadTestExecutor.newExecutor();
	}

	// NOTE: This test executes API on prod geoserver. Ensure you have have to prod geoserver before running this test.
	@Test
	public void verifyExecuteTest() throws IOException {
		String testCaseName = "GeoServer-DriverViewAPI-01";
		String apiURL = "http://30.30.150.198:8080/geoserver/PICARRO/ows?service=WFS&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=PICARRO:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-122.003173828125+37.4138003506629%2c+-121.9921875+37.4138003506629%2c+-121.9921875+37.4050737501769%2c+-122.003173828125+37.4050737501769%2c+-122.003173828125+37.4138003506629)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		String contentType = "application/x-www-form-urlencoded";
		String requestBody = "username=PICARRO_VIEWER&password=PICARRO_VIEWER";
		HttpMethod method = HttpMethod.POST;
		Integer concurrentRequests = 100;
		Integer requestsInOneSession = 10;
		Integer numPrimingRuns = 1;
		Integer expectedResponseContentLength = 224299;
		TestResult testResult = loadTestExecutor.executeTest(testCaseName, apiURL, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength);
		assertTrue(String.format("Load test failed. Test result file '%s' NOT found.", loadTestExecutor.getTestResultFile()), testResult != null);
		Log.info(String.format("Test result -> %s", testResult.toString()));
	}
}
