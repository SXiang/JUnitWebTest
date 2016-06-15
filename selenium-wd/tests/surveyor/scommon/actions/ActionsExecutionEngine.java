package surveyor.scommon.actions;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import common.source.Constants;
import common.source.ExcelUtility;
import common.source.ExceptionUtility;
import common.source.Log;
import common.source.TestContext;
import surveyor.scommon.source.SurveyorBaseTest;

/**
 * 
 * @author spulikkal
 */
public class ActionsExecutionEngine implements IMethodObserver {
	private static final String CLASS_NAME = "ActionsExecutionDriver";
	private static final String DATA_FOLDER = "data";
	private static final String TEST_CASES_XLSX = "TestCases.xlsx";
	public Properties OR;
	
	private String testCaseID;	
	private String testCaseRallyID;
	private String testCaseName;
	private String testCaseUserRowIDs;
	private String testCaseEnabled;
	private String testCaseRunResult;
	
	private String testStepID;	
	private String testStepStep;
	private String testStepPageObject;
	private String testStepAction;
	private String testStepWebElement;
	private String testStepTestData;
	private String testStepTestDataRowIDs;
	private String testStepResult;
	
	private int testCaseStep;
	private int testCaseLastStep;
	
	private String[] testGroupsToExecute;

	private boolean bResult;
	
	private ExcelUtility excelUtility = null;
	private IMethodInvoker methodInvoker = null;

	public ActionsExecutionEngine() {
		excelUtility = new ExcelUtility();
		methodInvoker = PageActionsFactory.getMethodInvoker();
		methodInvoker.registerObserver(this);
	}

	public void executeTestCase(String sheetName, Integer testID) throws Exception {
		String testCaseExcelPath = TestContext.INSTANCE.getExecutionPath() + DATA_FOLDER + File.separator + TEST_CASES_XLSX;
		excelUtility.setExcelFile(testCaseExcelPath);
		Boolean foundMatchingTestCase = false;
		
    	for (String testCaseSheetName : Constants.Excel_Sheets_TestCases) {
    		if (testCaseSheetName.equalsIgnoreCase(sheetName)) {  // Found match.
	    		if (isExecutableTestGroup(testCaseSheetName)) {    		
		    		Log.info("Reading test case sheet: " + testCaseSheetName);
		    		int iTotalTestCases = excelUtility.getRowCount(testCaseSheetName);
		    		String testStepsSheetName = testCaseSheetName + "-TestSteps";
		    		for (int iTestcase=1; iTestcase < iTotalTestCases; iTestcase++) {
		    			bResult = true;
		    			testCaseID = excelUtility.getIntegerCellData(iTestcase, Constants.Excel_TestCases_Col_ID, testCaseSheetName);
		    			testCaseRallyID = excelUtility.getCellData(iTestcase, Constants.Excel_TestCases_Col_RallyID, testCaseSheetName);
		    			testCaseName = excelUtility.getCellData(iTestcase, Constants.Excel_TestCases_Col_Name, testCaseSheetName);
		    			testCaseUserRowIDs = excelUtility.getCellData(iTestcase, Constants.Excel_TestCases_Col_UserRowIDs, testCaseSheetName);
		    			testCaseEnabled = excelUtility.getBooleanCellData(iTestcase, Constants.Excel_TestCases_Col_Enabled, testCaseSheetName);
		    			testCaseRunResult = excelUtility.getCellData(iTestcase, Constants.Excel_TestCases_Col_RunResult, testCaseSheetName);

		    			Log.info(String.format("testCaseID=[%s], testCaseRallyID=[%s], testCaseUserRowIDs=[%s], testCaseName=[%s]", 
		    					testCaseID, testCaseRallyID, testCaseUserRowIDs, testCaseName));
		    			beforeTestSetup(testCaseID, testCaseRallyID, testCaseUserRowIDs, testCaseName);
		    			
		    			if (testID == Integer.valueOf(testCaseID)) {	// found match.
		    				foundMatchingTestCase = true;
		    				Log.info(String.format("Found matching test case: [TestSheetName=%s], [TestCaseID=%d]", sheetName, testID ));
		        			if (testCaseEnabled.equalsIgnoreCase("true")) {
			    				executeTestCaseAction(testCaseSheetName, testStepsSheetName, iTestcase);
			    			}
		    			}
		    			
		    			afterTestTearDown();
		    		}
				}
    		}
    	}
    	
    	if (!foundMatchingTestCase) {
    		Log.warn(String.format("NOT found matching test case: [TestSheetName=%s], [TestCaseID=%d]", sheetName, testID ));
    	}
    }

	private void beforeTestSetup(String testCaseID, String testCaseRallyID, String testCaseUserRowIDs,
			String testCaseName) throws Exception {
		PageActionsStore.INSTANCE.clearStore();
		SurveyorBaseTest.setUpBeforeClass();
		SurveyorBaseTest.reportTestStarting(CLASS_NAME, testCaseRallyID + " - " + testCaseName, testCaseID + " : " + testCaseName);
	}

	private void afterTestTearDown() throws Exception {
		SurveyorBaseTest.reportTestFinished(CLASS_NAME);
		SurveyorBaseTest.tearDownAfterClass();
	}

	private void executeTestCaseAction(String testCaseSheetName, String testStepsSheetName, int iTestcase) throws Exception {
		Log.info(String.format("START Executing Test Case ID = %s", testCaseID));
		
		testCaseStep = excelUtility.getRowContains(testCaseID, Constants.Excel_TestCaseSteps_Col_ID, testStepsSheetName);
		testCaseLastStep = excelUtility.getTestStepsCount(testCaseID, Constants.Excel_TestCaseSteps_Col_ID, testCaseStep, testStepsSheetName);

		bResult=true;

		for (;testCaseStep < testCaseLastStep; testCaseStep++) {

			testStepID = excelUtility.getIntegerCellData(testCaseStep, Constants.Excel_TestCaseSteps_Col_ID, testStepsSheetName);
			testStepStep = excelUtility.getCellData(testCaseStep, Constants.Excel_TestCaseSteps_Col_TestCaseStep, testStepsSheetName);
			testStepPageObject = excelUtility.getCellData(testCaseStep, Constants.Excel_TestCaseSteps_Col_PageObject, testStepsSheetName);
			testStepAction = excelUtility.getCellData(testCaseStep, Constants.Excel_TestCaseSteps_Col_Action, testStepsSheetName);
			testStepWebElement = excelUtility.getCellData(testCaseStep, Constants.Excel_TestCaseSteps_Col_WebElement, testStepsSheetName);
			testStepTestData = excelUtility.getCellData(testCaseStep, Constants.Excel_TestCaseSteps_Col_TestData, testStepsSheetName);
			testStepTestDataRowIDs = excelUtility.getCellData(testCaseStep, Constants.Excel_TestCaseSteps_Col_TestDataRowIDs, testStepsSheetName);
			testStepResult = excelUtility.getCellData(testCaseStep, Constants.Excel_TestCaseSteps_Col_Result, testStepsSheetName);
			
			Log.info(String.format("RUNNING Test Step[ID=%s]-'%s'", testStepID, testStepStep));
			Log.info(String.format("START Executing action-'%s' on page-'%s' with test data-'%s' for rowIDs-'%s'", 
					testStepAction, testStepPageObject, testStepTestData, testStepTestDataRowIDs));
			
			try {
				bResult = executeAction(testCaseStep, testStepPageObject, testStepAction, testStepWebElement, testStepTestData, testStepTestDataRowIDs, testStepsSheetName);
			} catch (Exception e) {
				SurveyorBaseTest.reportTestFailed(e);
				Log.error(String.format("Error executing action - [%s]. Exception details: %s", testStepAction, ExceptionUtility.getStackTraceString(e)));
			}
			
			if (bResult==false) {
				String failureMessage = String.format("FAILURE while executing action-'%s' on page-'%s' with test data-'%s' for rowIDs-'%s'", 
						testStepAction, testStepPageObject, testStepTestData, testStepTestDataRowIDs);
				excelUtility.setCellData(Constants.KEYWORD_FAIL + " : " + failureMessage, testCaseStep, Constants.Excel_TestCaseSteps_Col_Result, testCaseSheetName);
				SurveyorBaseTest.reportTestError(failureMessage);
				Log.info(failureMessage);
				break;
			}						

			Log.info(String.format("FINISHED executing action-'%s' on page-'%s' with test data-'%s' for rowIDs-'%s'", 
					testStepAction, testStepPageObject, testStepTestData, testStepTestDataRowIDs));
		}
		
		if (bResult==true) {
			excelUtility.setCellData(Constants.KEYWORD_PASS, iTestcase, Constants.Excel_TestCases_Col_RunResult, testCaseSheetName);
			Log.info(String.format("FINISHED Executing Test Case ID = %s", testCaseID));
		} else {					
			excelUtility.setCellData(Constants.KEYWORD_FAIL, iTestcase, Constants.Excel_TestCases_Col_RunResult, testCaseSheetName);
			Log.info(String.format("FAILURE while Executing Test Case ID = %s", testCaseID));
		}
	}	

	public void startExecution() throws Exception {
		String testCaseExcelPath = TestContext.INSTANCE.getExecutionPath() + DATA_FOLDER + File.separator + TEST_CASES_XLSX;
		excelUtility.setExcelFile(testCaseExcelPath);
		
    	for (String testCaseSheetName : Constants.Excel_Sheets_TestCases) {
    		if (isExecutableTestGroup(testCaseSheetName)) {    		
	    		Log.info("Reading test case sheet: " + testCaseSheetName);
	    		int iTotalTestCases = excelUtility.getRowCount(testCaseSheetName);
	    		String testStepsSheetName = testCaseSheetName + "-TestSteps";
	    		for (int iTestcase=1; iTestcase < iTotalTestCases; iTestcase++) {
	    			bResult = true;

	    			testCaseID = excelUtility.getIntegerCellData(iTestcase, Constants.Excel_TestCases_Col_ID, testCaseSheetName);
	    			testCaseRallyID = excelUtility.getCellData(iTestcase, Constants.Excel_TestCases_Col_RallyID, testCaseSheetName);
	    			testCaseName = excelUtility.getCellData(iTestcase, Constants.Excel_TestCases_Col_Name, testCaseSheetName);
	    			testCaseUserRowIDs = excelUtility.getCellData(iTestcase, Constants.Excel_TestCases_Col_UserRowIDs, testCaseSheetName);
	    			testCaseEnabled = excelUtility.getBooleanCellData(iTestcase, Constants.Excel_TestCases_Col_Enabled, testCaseSheetName);
	    			testCaseRunResult = excelUtility.getCellData(iTestcase, Constants.Excel_TestCases_Col_RunResult, testCaseSheetName);
	    			
	    			if (testCaseEnabled.equalsIgnoreCase("true")) {
	    				Log.info(String.format("testCaseID=[%s], testCaseRallyID=[%s], testCaseUserRowIDs=[%s], testCaseName=[%s]", 
		    					testCaseID, testCaseRallyID, testCaseUserRowIDs, testCaseName));
		    			beforeTestSetup(testCaseID, testCaseRallyID, testCaseUserRowIDs, testCaseName);
	    				executeTestCaseAction(testCaseSheetName, testStepsSheetName, iTestcase);
		    			afterTestTearDown();
	    			}	    			
	    		}
			}
    	}
    }

	 private boolean isExecutableTestGroup(String testCaseSheetName) {
		if (this.getTestGroupsToExecute() == null) {
			return true;   // If no group specified run all Groups.
		} else {
			// Group specified. Check for valid group.
			for (String group : testGroupsToExecute) {
				if (testCaseSheetName.equals(group)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean executeAction(int testStep, String pageObjectName, String actionName, String webElement, String testData, 
			 String testDataRowIDs, String testStepsSheetName) throws Exception {
		// If WebElement info is provided use that as testData.
		if (!ActionArguments.isEmpty(webElement)) {
			testData = webElement;
		}
		 
		List<Integer> dataRowIDList = parseDataRowsIDs(testDataRowIDs);		 
		IActions pageActions = PageActionsFactory.getAction(pageObjectName);
		Log.info("Executing Action Type = " + pageActions.getClass().getName());
		
		// Invoke method using MethodInvoker. Results are printed for each run in updateResult(..).
		MethodParams methodParam = new MethodParams(testStepsSheetName, testStep, testData, dataRowIDList);
		return methodInvoker.invokeMethod(pageActions, actionName, methodParam);
	 }

	private List<Integer> parseDataRowsIDs(String testDataRowIDs) {
		ArrayList<Integer> intList = new ArrayList<Integer>();
		String[] parts = null;
		if (testDataRowIDs != null && !testDataRowIDs.isEmpty()) {
			// Case 1: Range (for eg. 1..8)
			if (testDataRowIDs.contains("..")) {
				// Handle cases where user has specified 2 or more dots.
				while (testDataRowIDs.contains("..")) {
					// get start and end
					testDataRowIDs = testDataRowIDs.replace("..", ".");
				}
				parts = testDataRowIDs.split("\\.");
				if (parts != null && parts.length == 2) {
					int start = Integer.valueOf(parts[0]);
					int end = Integer.valueOf(parts[1]);
					for (int i = start; i <= end; i++) {
						intList.add(i);
					}
				}
			} else {	
				// Case 2: CSV values (for eg. 1,4,58,132)
				// Case 3: Single value (for eg. 4)
				parts = testDataRowIDs.split(",");
				for (String part : parts) {
					intList.add(Integer.valueOf(part));
				}	
			}
		}
		
		return intList;
	}

	@Override
	public void updateResult(MethodParams methodParam) {
		Log.info(String.format("Executed action-'%d' from %s : DataRowID=[%d] : Result=[%b]", methodParam.getTestStepNumber(),
				methodParam.getTestCaseSheetName(), methodParam.getDataRowID(), methodParam.getResult()));
	}

	public String[] getTestGroupsToExecute() {
		return testGroupsToExecute;
	}

	public void setTestGroupsToExecute(String[] testGroupsToExecute) {
		this.testGroupsToExecute = testGroupsToExecute;
	}
}
