package common.source;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Hashtable;

import org.testng.Assert;

import surveyor.scommon.source.SurveyorConstants;

public class ApiUtility {

	public static final String REPORTS_GET_REPORT_STAT_API_RELATIVE_URL = "Reports/GetReportStat?reportTitle=%s";
	public static final String ENVIRONMENT_BUILD_API_RELATIVE_URL = "api/EnvironmentBuilds?environmentName=%s";
	public static final String DELETE_MEASUREMENT_SESSION_RELATIVE_URL = "Home/DeleteSession/%s";
	public static final String DELETE_COMPLIANCE_REPORTS_RELATIVE_URL = "/Reports/DeleteReport?reportType=ComplianceReports&reportId=%s";
	public static final String DELETE_ASSESSMENT_REPORTS_RELATIVE_URL = "/Reports/DeleteReport?reportType=AssessmentReports&reportId=%s";
	public static final String DELETE_EQ_REPORTS_RELATIVE_URL = "/Reports/DeleteReport?reportType=EQReports&reportId=%s";
	private static final String GET_API_RESPONSE_CMD = "GetAPIResponse.cmd";
	private static final String GET_AUTOMATION_API_RESPONSE_CMD = "Get-ReportingAPIResponse.cmd";

	public ApiUtility() {
	}

	public static String getApiResponse(String apiRelativePath) {
		String loginUser = TestContext.INSTANCE.getLoggedInUser();
		String loginPwd = TestContext.INSTANCE.getLoggedInPassword();

		// If API is invoked before login in UI, use default credentials.
		if (BaseHelper.isNullOrEmpty(loginUser) || BaseHelper.isNullOrEmpty(loginPwd)) {
			loginUser = TestContext.INSTANCE.getTestSetup().getLoginUser();
			loginPwd = TestContext.INSTANCE.getTestSetup().getLoginPwd();
		}

		return getApiResponse(apiRelativePath, loginUser, loginPwd);
	}

	public static String getApiResponse(String apiRelativePath, String loginUser, String loginPwd) {
		String responseText = "";
		try {
			String baseUrl = TestContext.INSTANCE.getBaseUrl();
			String workingFolder = TestSetup.getRootPath();
			String filePathWithCommand = GET_API_RESPONSE_CMD + String.format(" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\"",
							workingFolder, baseUrl, apiRelativePath, loginUser, loginPwd);
			responseText = executeGetApiResponseCmd(filePathWithCommand);
		} catch (IOException e) {
			Log.error(e.toString());
		}

		return responseText;
	}

	/**
	 * Executes GET request on specified automation API and returns the response.
	 *
	 * @param apiRelativePath - API relative url
	 * @return - API response
	 */
	public static String getAutomationApiResponse(String apiRelativePath) {
		String responseText = "";
		try {
			String workingFolder = TestSetup.getRootPath();
			String automationApiUrl = TestContext.INSTANCE.getTestSetup().getAutomationReportingApiEndpoint();
			String filePathWithCommand = GET_AUTOMATION_API_RESPONSE_CMD + String.format(" \"%s\" \"%s\" \"%s\"",
							workingFolder, automationApiUrl, apiRelativePath);
			responseText = executeGetApiResponseCmd(filePathWithCommand);
		} catch (IOException e) {
			Log.error(e.toString());
		}

		return responseText;
	}

	/**
	 * Executes the specified api and returns the response string.
	 * If invalid API is provided returns NULL.
	 *
	 * @param apiCmdFileWithArgs
	 * @return
	 */
	private static String executeGetApiResponseCmd(String apiCmdFileWithArgs) {
		String responseText = null;
		// Execute get api response script from the contained folder.
		try {
			String apiCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib";
			String apiCmdFileFullPathWithArgs = apiCmdFolder + File.separator + apiCmdFileWithArgs;
			String command = "cd \"" + apiCmdFolder + "\" && " + apiCmdFileFullPathWithArgs;
			Log.info("Executing get api response script. Command -> " + command);
			ProcessOutputInfo processOutputInfo = ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ true);
			responseText = processOutputInfo.getOutput();
			responseText = extractApiResponse(responseText);
		} catch (IOException e) {
			Log.error(e.toString());
		}
		return responseText;
	}

	private static String extractApiResponse(String responseText) {
		String response = null;
		int openBracketIdx = responseText.indexOf("{");
		int openBracketIdx2 = -1;
		int closeBracketIdx = -1;
		if (openBracketIdx > 0) {
			if (responseText.length() > openBracketIdx+1) {
				openBracketIdx2 = responseText.indexOf("{", openBracketIdx+1);
				closeBracketIdx = responseText.lastIndexOf("}");
			}
		}
		if (openBracketIdx2 != -1) {
			if (closeBracketIdx > openBracketIdx2 && (responseText.length() > closeBracketIdx+1)) {
				response = responseText.substring(openBracketIdx2, closeBracketIdx+1);
			}
		}
		return response;
	}

	public static void main(String[] args) {
		// Initialize TestSetup to instantiate the TestContext.
		TestSetup testSetup = new TestSetup(false /* skip initialization */);
		String rootPath;
		try {
			rootPath = TestSetup.getRootPath();
			testSetup.loadTestProperties(rootPath);
		} catch (IOException e) {
			Log.error(e.getMessage());
		}
		testSetup.initialize();
		TestContext.INSTANCE.setTestSetup(testSetup);

		// NOTE: Before running the tests replace this constant with a valid report title on the environment
		String VALID_REPORT_TITLE = "RPTTemplate-49068c5c2a7e4b699f71StandardStandard";
		String INVALID_REPORT_TITLE = "InvalidReportTitle";
		String VALID_API_URL_FORMAT = REPORTS_GET_REPORT_STAT_API_RELATIVE_URL;
		String INVALID_API_URL_FORMAT = "Reports/InvalidApi?invalidQS=%s";

		try {
			Log.info("Running test - testGetApiResponse_ValidApiSuccessCase_ReturnsResponse() ...");
			testGetApiResponse_ValidApi_ReturnsResponse(VALID_API_URL_FORMAT, VALID_REPORT_TITLE);
			Log.info("Running test - testGetApiResponse_ValidApiIncorrectParam_ReturnsError() ...");
			testGetApiResponse_ValidApiIncorrectParam_ReturnsError(VALID_API_URL_FORMAT, INVALID_REPORT_TITLE);
			Log.info("Running test - testGetApiResponse_InvalidApi_ReturnsError() ...");
			testGetApiResponse_InvalidApi_ReturnsError(INVALID_API_URL_FORMAT, INVALID_REPORT_TITLE);
		} finally {
			testSetup.getDriver().quit();
		}
	}

	private static void testGetApiResponse_ValidApi_ReturnsResponse(String apiUrl, String reportTitle) {
		String loginUser = SurveyorConstants.PICDFADMIN;
		String loginPwd = SurveyorConstants.PICADMINPSWD;
		String apiRelativeUrl = String.format(apiUrl, reportTitle);
		String responseText = ApiUtility.getApiResponse(apiRelativeUrl, loginUser, loginPwd);
		Assert.assertTrue(responseText.contains("ReportTitle") && responseText.contains(reportTitle));
	}

	private static void testGetApiResponse_ValidApiIncorrectParam_ReturnsError(String apiUrl, String reportTitle) {
		String loginUser = SurveyorConstants.PICDFADMIN;
		String loginPwd = SurveyorConstants.PICADMINPSWD;
		String apiRelativeUrl = String.format(apiUrl, reportTitle);
		String responseText = ApiUtility.getApiResponse(apiRelativeUrl, loginUser, loginPwd);
		Assert.assertTrue(responseText.contains("Report NOT found"));
	}

	private static void testGetApiResponse_InvalidApi_ReturnsError(String apiUrl, String reportTitle) {
		String loginUser = SurveyorConstants.PICDFADMIN;
		String loginPwd = SurveyorConstants.PICADMINPSWD;
		String apiRelativeUrl = String.format(apiUrl, reportTitle);
		String responseText = ApiUtility.getApiResponse(apiRelativeUrl, loginUser, loginPwd);
		Assert.assertTrue(responseText==null);
	}
}
