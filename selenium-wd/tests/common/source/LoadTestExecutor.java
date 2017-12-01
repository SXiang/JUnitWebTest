package common.source;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class LoadTestExecutor {
	private static final String AB_INSTALLER_ARTIFACTORY_PATH = "test-artifacts/apache-benchmark/httpd-2.4.29-Win64-VC15.zip";
	private static final String INSTALL_LOAD_TESTING_TOOLS_CMD = "Install-LoadTestingTools.cmd";
	private static final String EXECUTE_LOAD_APITEST_CMD = "Execute-LoadAPITest.cmd";
	private static final String LOAD_TEST_RESULT_FORMAT = "LoadTest-%d-%s.result";
	private static final String LoadPerformanceFolder = "LoadPerformance";
	private static final String LoadStatAPITestCaseResultIdLabel = "LoadStatAPITestCaseResultId";
	private static final String OutputResultFileLabel = "OutputResultFile";
	private static final String OutputResultDataFileLabel = "OutputResultDataFile";
	private static final Integer DEFAULT_WAIT_BETWEEN_POLL_IN_MSEC = 1000;
	private static final Integer MAX_RETRIES_IN_POLL = 5;

	public static final String LABEL_MATCH_REGEX_WITH_PLACEHOLDER = "%s\\s+=\\s+(.+)";

	private String workingFolder;
	private String automationReportingAPIBaseUrl;
	private String abExeFolder;
	private String outputFolder;
	private Long runTriggerId;

	private String executingTestCase;

	public enum HttpMethod {
		GET ("GET"),
		POST ("POST");

		private final String name;

		HttpMethod(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public static class TestResult {
		private boolean testPass;
		private TestResultData testResultData;

		public boolean isTestPass() {
			return testPass;
		}
		public void setTestPass(boolean testPass) {
			this.testPass = testPass;
		}
		public TestResultData getTestResultData() {
			return testResultData;
		}
		public void setTestResultData(TestResultData testResultData) {
			this.testResultData = testResultData;
		}
		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
		}
	}

	public static class TestResultData {
		private Long loadStatAPITestCaseResultId;
		private String outputResultFile;
		private String outputResultDataFile;

		public Long getLoadStatAPITestCaseResultId() {
			return loadStatAPITestCaseResultId;
		}
		public void setLoadStatAPITestCaseResultId(Long loadStatAPITestCaseResultId) {
			this.loadStatAPITestCaseResultId = loadStatAPITestCaseResultId;
		}
		public String getOutputResultFile() {
			return outputResultFile;
		}
		public void setOutputResultFile(String outputResultFile) {
			this.outputResultFile = outputResultFile;
		}
		public String getOutputResultDataFile() {
			return outputResultDataFile;
		}
		public void setOutputResultDataFile(String outputResultDataFile) {
			this.outputResultDataFile = outputResultDataFile;
		}
		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
		}
	}

	public static LoadTestExecutor newExecutor() throws IOException {
		return getDefaultExecutor();
	}

	private static LoadTestExecutor getDefaultExecutor() throws IOException {
		LoadTestExecutor testExecutor = new LoadTestExecutor();
		testExecutor.setWorkingFolder(TestSetup.getRootPath());
		testExecutor.setAutomationReportingAPIBaseUrl(TestContext.INSTANCE.getTestSetup().getAutomationReportingApiEndpoint());
		testExecutor.setAbExeFolder(TestContext.INSTANCE.getTestSetup().getPathToABExe());
		Long runUUID = TestContext.INSTANCE.getTestSetup().getRunUUID();
		testExecutor.setRunTriggerId(runUUID != null ? runUUID : 0L);
		testExecutor.setOutputFolder(TestContext.INSTANCE.getTestSetup().getDefaultLogFolder());
		testExecutor.ensureLoadTestingToolsPresent();
		return testExecutor;
	}

	private void ensureLoadTestingToolsPresent() throws IOException {
		String apiCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib" + File.separator + LoadPerformanceFolder;
		String apiCmdFullPath = apiCmdFolder + File.separator + INSTALL_LOAD_TESTING_TOOLS_CMD;
		String artifactoryBaseUrl = TestContext.INSTANCE.getTestSetup().getArtifactoryBaseUrl();
		String artifactoryAPIKey = TestContext.INSTANCE.getTestSetup().getArtifactoryAPIKey();
		String artifactoryRepository = TestContext.INSTANCE.getTestSetup().getArtifactoryRepository();
		String installFolder = TestContext.INSTANCE.getTestSetup().getAbRootFolder();
		String command = "cd \"" + apiCmdFolder + "\" && " + apiCmdFullPath +
				String.format(" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\"",
						getWorkingFolder(),
						artifactoryBaseUrl,
						artifactoryAPIKey,
						artifactoryRepository,
						AB_INSTALLER_ARTIFACTORY_PATH,
						installFolder );

		String scrubbedCommand = command.replace(artifactoryAPIKey, "<api-key-hidden>");
		Log.info("Ensuring load testing tools present. Command -> " + scrubbedCommand);
		ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ true);
	}

	public TestResult executeTest(String testCaseName, String apiURL, String contentType, String username, String password, HttpMethod method,
			Integer concurrentRequests, Integer requestsInOneSession, Integer numPrimingRuns, Integer expectedResponseContentLength) throws IOException {
		String apiCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib" + File.separator + LoadPerformanceFolder;
		String apiCmdFullPath = apiCmdFolder + File.separator + EXECUTE_LOAD_APITEST_CMD;
		String command = "cd \"" + apiCmdFolder + "\" && " + apiCmdFullPath +
				String.format(" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" %d %d %d %d %d %d \"%s\" \"%s\"",
						getWorkingFolder(),
						getAutomationReportingAPIBaseUrl(),
						getAbExeFolder(),
						apiURL,
						method.toString(),
						contentType,
						username,
						password,
						1,   /*UseBasicAuthentication*/
						expectedResponseContentLength,
						concurrentRequests,
						requestsInOneSession,
						numPrimingRuns,
						getRunTriggerId(),
						testCaseName,
						getOutputFolder());
		Log.info("Executing load test. Command -> " + command);
		setExecutingTestCase(testCaseName);
		ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ true);
		return getTestResult();
	}

	public String getWorkingFolder() {
		return workingFolder;
	}

	public void setWorkingFolder(String workingFolder) {
		this.workingFolder = workingFolder;
	}

	public String getAutomationReportingAPIBaseUrl() {
		return automationReportingAPIBaseUrl;
	}

	public void setAutomationReportingAPIBaseUrl(String automationReportingAPIBaseUrl) {
		this.automationReportingAPIBaseUrl = automationReportingAPIBaseUrl;
	}

	public String getAbExeFolder() {
		return abExeFolder;
	}

	public void setAbExeFolder(String abExeFolder) {
		this.abExeFolder = abExeFolder;
	}

	public Long getRunTriggerId() {
		return runTriggerId;
	}

	public void setRunTriggerId(Long runTriggerId) {
		this.runTriggerId = runTriggerId;
	}

	public String getOutputFolder() {
		return outputFolder;
	}

	public void setOutputFolder(String outputFolder) {
		this.outputFolder = outputFolder;
	}

	public String getExecutingTestCase() {
		return executingTestCase;
	}

	public void setExecutingTestCase(String executingTestCase) {
		this.executingTestCase = executingTestCase;
	}

	public String getTestResultFile() {
		return Paths.get(getOutputFolder(), String.format(LOAD_TEST_RESULT_FORMAT, getRunTriggerId(), getExecutingTestCase())).toString();
	}

	private TestResult getTestResult() throws IOException {
		PollManager.poll(() -> !testResultReady(), DEFAULT_WAIT_BETWEEN_POLL_IN_MSEC, MAX_RETRIES_IN_POLL);
		if (testResultReady()) {
			TestResult testResult = new TestResult();
			List<String> fileLines = FileUtility.readFileLinesToList(getTestResultFile());
			TestResultData testResultData = new TestResultData();
			fileLines.stream().forEach(line -> setResultDataProperty(testResultData, line));
			testResult.setTestResultData(testResultData);
			testResult.setTestPass(true);
			return testResult;
		}

		return null;
	}

	private void setResultDataProperty(TestResultData testResultData, String line) {
		boolean match = false;
		List<String> matchingGroups = RegexUtility.getMatchingGroups(line, String.format(LABEL_MATCH_REGEX_WITH_PLACEHOLDER, LoadStatAPITestCaseResultIdLabel));
		if (matchingGroups != null && matchingGroups.size() > 1) {
			testResultData.setLoadStatAPITestCaseResultId(Long.valueOf(matchingGroups.get(1)));
			match = true;
		}

		if (!match) {
			matchingGroups = RegexUtility.getMatchingGroups(line, String.format(LABEL_MATCH_REGEX_WITH_PLACEHOLDER, OutputResultFileLabel));
			if (matchingGroups != null && matchingGroups.size() > 1) {
				testResultData.setOutputResultFile(matchingGroups.get(1));
				match = true;
			}
		}

		if (!match) {
			matchingGroups = RegexUtility.getMatchingGroups(line, String.format(LABEL_MATCH_REGEX_WITH_PLACEHOLDER, OutputResultDataFileLabel));
			if (matchingGroups != null && matchingGroups.size() > 1) {
				testResultData.setOutputResultDataFile(matchingGroups.get(1));
			}
		}
	}

	private boolean testResultReady() {
		return FileUtility.fileExists(getTestResultFile());
	}
}
