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
	private static final String GET_API_RESPONSE_CMD = "GetAPIResponse.cmd";
	
	public ApiUtility() {
	}

	public static String getApiResponse(String apiRelativePath) {
		String loginUser = TestContext.INSTANCE.getLoggedInUser();
		String loginPwd = TestContext.INSTANCE.getLoggedInUserPassword();
		return getApiResponse(apiRelativePath, loginUser, loginPwd);
	}
		
	public static String getApiResponse(String apiRelativePath, String loginUser, String loginPwd) {
		String responseText = "";
		try {
			String baseUrl = TestContext.INSTANCE.getBaseUrl();
			String workingFolder = TestSetup.getExecutionPath(TestSetup.getRootPath());
			String libFolder = workingFolder + "lib";
			String apiCmdFullPath = libFolder + File.separator + GET_API_RESPONSE_CMD;

			String workingApiCmdFile = TestSetup.getUUIDString() + "_" + GET_API_RESPONSE_CMD;
			String workingApiCmdFullPath = Paths.get(libFolder + File.separator, workingApiCmdFile).toString();

			// Create a copy of the get api response cmd file.
			Files.copy(Paths.get(apiCmdFullPath), Paths.get(workingApiCmdFullPath));

			// Update the working copy.
			Hashtable<String, String> placeholderMap = new Hashtable<String, String>();
			placeholderMap.put("%WORKING_DIR%", workingFolder);
			placeholderMap.put("%1%", baseUrl);
			placeholderMap.put("%2%", apiRelativePath);
			placeholderMap.put("%3%", loginUser);
			placeholderMap.put("%4%", loginPwd);
			FileUtility.updateFile(workingApiCmdFullPath, placeholderMap);

			// Execute update config cmd.
			responseText = executeGetApiResponseCmd(workingApiCmdFile);
			
			// Delete the working copy of the update config cmd file.
			Files.delete(Paths.get(workingApiCmdFullPath));
		} catch (IOException e) {
			Log.error(e.toString());
		}
		
		return responseText;
	}
	
	/**
	 * Executes the specified api and returns the response string.
	 * If invalid API is provided returns NULL.
	 * 
	 * @param workingApiCmdFile
	 * @return
	 */
	private static String executeGetApiResponseCmd(String workingApiCmdFile) {
		String responseText = null;
		// Execute get api response script from the contained folder.
		try {
			String apiCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib";
			String apiCmdFileFullPath = apiCmdFolder + File.separator + workingApiCmdFile;
			String command = "cd \"" + apiCmdFolder + "\" && " + apiCmdFileFullPath;
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
		String VALID_REPORT_TITLE = "cc17066cba3d4f77b654";
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
