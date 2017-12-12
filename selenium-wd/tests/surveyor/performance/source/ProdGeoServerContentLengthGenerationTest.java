package surveyor.performance.source;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.CSVUtility;
import common.source.FileUtility;
import common.source.LoadTestExecutor;
import common.source.Log;
import common.source.RegexUtility;
import common.source.TestSetup;
import common.source.LoadTestExecutor.HttpMethod;
import common.source.LoadTestExecutor.TestResult;
import surveyor.dataprovider.LoadGeoServerAPIContentLengthGenerationDataProvider;
import surveyor.scommon.source.BaseTest;
import surveyor.scommon.source.SurveyorTestRunner;

@RunWith(SurveyorTestRunner.class)
public class ProdGeoServerContentLengthGenerationTest extends BaseTest {

	private static Integer ITERATIONS = 5;
	private static LoadTestExecutor loadTestExecutor = null;

	@BeforeClass
	public static void beforeClass() throws IOException {
		loadTestExecutor = LoadTestExecutor.newExecutor();
	}

	@Test
	public void dummyTest() {
		Log.info("Dummy test run...");
	}

	@Test
	@UseDataProvider(value = LoadGeoServerAPIContentLengthGenerationDataProvider.LOAD_TEST_LOW_FREQUENCY_GEO_SERVER_API_CONTENT_LENGTH_GENERATION_PROVIDER, location = LoadGeoServerAPIContentLengthGenerationDataProvider.class)
	public void generateLowFrequencyLoadTestContentLengths(String testCaseName, String apiURL, String contentType, String username, String password, HttpMethod method, Integer concurrentRequests, Integer requestsInOneSession, Integer numPrimingRuns, Integer expectedResponseContentLength) throws IOException {
		Log.method("ProdGeoServerContentLengthGenerationTest.generateLowFrequencyLoadTestContentLengths", testCaseName, apiURL, contentType, username, password, method, concurrentRequests,
				requestsInOneSession, numPrimingRuns, expectedResponseContentLength);

		executeTest(testCaseName, apiURL, contentType, username, password, method, concurrentRequests, requestsInOneSession,
				numPrimingRuns, expectedResponseContentLength);
	}

	@Test
	@UseDataProvider(value = LoadGeoServerAPIContentLengthGenerationDataProvider.LOAD_TEST_MEDIUM_FREQUENCY_GEO_SERVER_API_CONTENT_LENGTH_GENERATION_PROVIDER, location = LoadGeoServerAPIContentLengthGenerationDataProvider.class)
	public void generateMediumFrequencyLoadTestContentLengths(String testCaseName, String apiURL, String contentType, String username, String password, HttpMethod method, Integer concurrentRequests, Integer requestsInOneSession, Integer numPrimingRuns, Integer expectedResponseContentLength) throws IOException {
		Log.method("ProdGeoServerContentLengthGenerationTest.generateMediumFrequencyLoadTestContentLengths", testCaseName, apiURL, contentType, username, password, method, concurrentRequests,
				requestsInOneSession, numPrimingRuns, expectedResponseContentLength);

		executeTest(testCaseName, apiURL, contentType, username, password, method, concurrentRequests, requestsInOneSession,
				numPrimingRuns, expectedResponseContentLength);
	}

	@Test
	@UseDataProvider(value = LoadGeoServerAPIContentLengthGenerationDataProvider.LOAD_TEST_HIGH_FREQUENCY_API_GEO_SERVER_API_CONTENT_LENGTH_GENERATION_PROVIDER, location = LoadGeoServerAPIContentLengthGenerationDataProvider.class)
	public void generateHighFrequencyLoadTestContentLengths(String testCaseName, String apiURL, String contentType, String username, String password, HttpMethod method, Integer concurrentRequests, Integer requestsInOneSession, Integer numPrimingRuns, Integer expectedResponseContentLength) throws IOException {
		Log.method("ProdGeoServerContentLengthGenerationTest.generateHighFrequencyLoadTestContentLengths", testCaseName, apiURL, contentType, username, password, method, concurrentRequests,
				requestsInOneSession, numPrimingRuns, expectedResponseContentLength);

		executeTest(testCaseName, apiURL, contentType, username, password, method, concurrentRequests, requestsInOneSession,
				numPrimingRuns, expectedResponseContentLength);
	}

	public static Integer extractContentLength(String line) {
		Log.method("extractContentLength", line);
		final String REGEX_PATTERN = "Document Length:\\s+(\\d+)\\s+bytes";
		List<String> groups = RegexUtility.getMatchingGroups(line, REGEX_PATTERN);
		Log.info("Match = " + groups.get(1));
		return Integer.valueOf(groups.get(1));
	}

	private void executeTest(String testCaseName, String apiURL, String contentType, String username, String password,
			HttpMethod method, Integer concurrentRequests, Integer requestsInOneSession, Integer numPrimingRuns,
			Integer expectedResponseContentLength) throws IOException {
		List<String> resultFiles = new ArrayList<>();
		List<Integer> contentLengths = new ArrayList<>();
		for (int i = 0; i < ITERATIONS; i++) {
			Log.info(String.format("[Iteration - %d] Test - %s", i+1, testCaseName));

			// 1. Run test and verify pass.
			TestResult testResult = loadTestExecutor.executeTest(testCaseName, apiURL, contentType, username, password, method, concurrentRequests, requestsInOneSession,
					false /*verifyContentLength*/, false /*postResultsToAutomationDB*/, numPrimingRuns, expectedResponseContentLength);
			assertTrue(String.format("Load test failed. Test result file '%s' NOT found.", loadTestExecutor.getTestResultFile()), testResult != null);
			Log.info(String.format("Test result -> %s", testResult.toString()));
			assertTrue("Load Test status = [FAIL]. Refer errors in logs.", testResult.isTestPass());

			// 2. Extract content length from response log.
			String resultFile = testResult.getTestResultData().getOutputResultFile();
			Log.info(String.format("Extracting content length from result file -> %s", resultFile));
			List<String> fileLines = FileUtility.readFileLinesToList(resultFile, FileUtility.ENCODING_UTF16LE);
			final Integer contentLen = fileLines.stream()
				.filter(l -> l.startsWith("Document Length:"))
				.map(l -> extractContentLength(l))
				.collect(Collectors.toList()).get(0);

			// 3. If mismatch with previously recorded lengths log FAIL.
			Log.info("Verifying content lengths match ...");
			boolean allMatch = contentLengths.stream().allMatch(len -> len.equals(contentLen));

			resultFiles.add(resultFile);
			contentLengths.add(contentLen);

			if (!allMatch) {
				for (int j = 0; j < resultFiles.size(); j++) {
					String resFile = resultFiles.get(j);
					Integer cLen = contentLengths.get(j);
					Log.info(String.format("Content Length=[%d], Result file-'%s'. ", cLen, resFile));
				}

				Log.error(String.format("Encountered a content length MISMATCH. NOT matching content length value=[%d]. Result file-'%s'", contentLen, resultFile));
			}

			assertTrue("All content lengths do NOT match. Refer logs.", allMatch);
		}

		// 4. Finally write results to csv result file.
		List<String> fileLines = new ArrayList<>();
		fileLines.add("apiname,contentlength,resultfilepath,username,password,geoserverurl");
		for (int j = 0; j < resultFiles.size(); j++) {
			String resFile = resultFiles.get(j);
			Integer cLen = contentLengths.get(j);

			List<String> lineParts = new ArrayList<>();
			lineParts.add(testCaseName);
			lineParts.add(String.valueOf(cLen));
			lineParts.add(resFile);
			lineParts.add(username);
			lineParts.add(password);
			lineParts.add(apiURL);
			fileLines.add(CSVUtility.createCsvString(lineParts));
		}

		String csvResultsFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "data" + File.separator + "perf-metric";
		String csvResultsFile = String.format("%s-contentlengths.csv", testCaseName);
		String csvResultFilePath = Paths.get(csvResultsFolder, csvResultsFile).toString();
		Log.info(String.format("Writing to csv result file -> %s", csvResultFilePath));
		FileUtility.writeToFile(csvResultFilePath, fileLines.toArray(new String[fileLines.size()]));
	}
}
