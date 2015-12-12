package surveyor.scommon.actions;

import java.lang.reflect.Method;
import java.util.Properties;

import common.source.Constants;
import common.source.ExcelUtility;

public class ActionsExecutionEngine {
	public Properties OR;
	public Method method[];
	
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

	private boolean bResult;
	
	private ExcelUtility excelUtility = null;

	public ActionsExecutionEngine() {
		excelUtility = new ExcelUtility();
	}
	
	public void startExecution() throws Exception {
		String testCaseExcelPath = "";
		excelUtility.setExcelFile(testCaseExcelPath);
		
    	for (String testCaseSheetName : Constants.Excel_Sheets_TestCases) {
    		int iTotalTestCases = excelUtility.getRowCount(testCaseSheetName);
    		String testStepsSheetName = testCaseSheetName + "-TestSteps";
    		for (int iTestcase=1; iTestcase < iTotalTestCases; iTestcase++) {
    			bResult = true;
    			testCaseID = excelUtility.getCellData(iTestcase, Constants.Excel_TestCases_Col_ID, testCaseSheetName);
    			testCaseRallyID = excelUtility.getCellData(iTestcase, Constants.Excel_TestCases_Col_RallyID, testCaseSheetName);
    			testCaseName = excelUtility.getCellData(iTestcase, Constants.Excel_TestCases_Col_Name, testCaseSheetName);
    			testCaseUserRowIDs = excelUtility.getCellData(iTestcase, Constants.Excel_TestCases_Col_UserRowIDs, testCaseSheetName);
    			testCaseEnabled = excelUtility.getCellData(iTestcase, Constants.Excel_TestCases_Col_Enabled, testCaseSheetName);
    			testCaseRunResult = excelUtility.getCellData(iTestcase, Constants.Excel_TestCases_Col_RunResult, testCaseSheetName);
    			
    			if (testCaseEnabled.equalsIgnoreCase("true")) {
    				System.out.println(String.format("START Executing Test Case ID = {0}", testCaseID));
    				
    				testCaseStep = excelUtility.getRowContains(testCaseID, Constants.Excel_TestCaseSteps_Col_ID, testStepsSheetName);
    				testCaseLastStep = excelUtility.getTestStepsCount(testCaseID, Constants.Excel_TestCaseSteps_Col_ID, testCaseStep, testStepsSheetName);

    				bResult=true;

    				for (;testCaseStep < testCaseLastStep; testCaseStep++) {

    					testStepID = excelUtility.getCellData(testCaseStep, Constants.Excel_TestCaseSteps_Col_ID, testStepsSheetName);
        				testStepStep = excelUtility.getCellData(testCaseStep, Constants.Excel_TestCaseSteps_Col_TestCaseStep, testStepsSheetName);
        				testStepPageObject = excelUtility.getCellData(testCaseStep, Constants.Excel_TestCaseSteps_Col_PageObject, testStepsSheetName);
        				testStepAction = excelUtility.getCellData(testCaseStep, Constants.Excel_TestCaseSteps_Col_Action, testStepsSheetName);
        				testStepWebElement = excelUtility.getCellData(testCaseStep, Constants.Excel_TestCaseSteps_Col_WebElement, testStepsSheetName);
        				testStepTestData = excelUtility.getCellData(testCaseStep, Constants.Excel_TestCaseSteps_Col_TestData, testStepsSheetName);
        				testStepTestDataRowIDs = excelUtility.getCellData(testCaseStep, Constants.Excel_TestCaseSteps_Col_TestDataRowIDs, testStepsSheetName);
        				testStepResult = excelUtility.getCellData(testCaseStep, Constants.Excel_TestCaseSteps_Col_Result, testStepsSheetName);
        				
        				System.out.println(String.format("RUNNING Test Step[ID={0}]-'{1}'", testStepID, testStepStep));
        				System.out.println(String.format("START Executing action-'{0}' on page-'{1}' with test data-'{2}' for rowIDs-'{3}'", 
        						testStepAction, testStepPageObject, testStepTestData, testStepTestDataRowIDs));
        				
    		    		executeAction(testCaseStep, testStepPageObject, testStepAction, testStepTestData, testStepTestDataRowIDs, testStepsSheetName);

    					if (bResult==false) {
    						excelUtility.setCellData(Constants.KEYWORD_FAIL, testCaseStep, Constants.Excel_TestCaseSteps_Col_Result, testCaseSheetName);
            				System.out.println(String.format("FAILURE while executing action-'{0}' on page-'{1}' with test data-'{2}' for rowIDs-'{3}'", 
            						testStepAction, testStepPageObject, testStepTestData, testStepTestDataRowIDs));
    						break;
    					}						

        				System.out.println(String.format("FINISHED executing action-'{0}' on page-'{1}' with test data-'{2}' for rowIDs-'{3}'", 
        						testStepAction, testStepPageObject, testStepTestData, testStepTestDataRowIDs));
    				}
    				
    				if (bResult==true) {
    					excelUtility.setCellData(Constants.KEYWORD_PASS, iTestcase, Constants.Excel_TestCases_Col_RunResult, testCaseSheetName);
    					System.out.println(String.format("FINISHED Executing Test Case ID = {0}", testCaseID));
    				} else {					
    					excelUtility.setCellData(Constants.KEYWORD_FAIL, iTestcase, Constants.Excel_TestCases_Col_RunResult, testCaseSheetName);
    					System.out.println(String.format("FAILURE while Executing Test Case ID = {0}", testCaseID));
    				}
    			}
    		}
		}
    }	
 
	 private void executeAction(int testStep, String pageObjectName, String actionName, String testData, 
			 String testDataRowIDs, String testStepsSheetName) throws Exception {

	    IPageActions pageActions = PageActionsFactory.getPageAction(pageObjectName);
		method = pageActions.getClass().getMethods();	
		for (int i=0; i< method.length; i++) {
			if(method[i].getName().equals(actionName)){
				method[i].invoke(pageActions, testData, testDataRowIDs);
				if (bResult==true) {
					excelUtility.setCellData(Constants.KEYWORD_PASS, testStep, Constants.Excel_TestCaseSteps_Col_Result, testStepsSheetName);
					break;
				} else {
					excelUtility.setCellData(Constants.KEYWORD_FAIL, testStep, Constants.Excel_TestCaseSteps_Col_Result, testStepsSheetName);
					break;
				}
			}
		}
	 }
}
