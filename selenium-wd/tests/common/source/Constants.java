package common.source;

public class Constants {
	// Test Execution Excel Sheet Column Numbers
	public static final int Excel_TestCases_Col_ID = 0;	
	public static final int Excel_TestCases_Col_RallyID = 1;
	public static final int Excel_TestCases_Col_Name = 2;
	public static final int Excel_TestCases_Col_UserRowIDs = 3;
	public static final int Excel_TestCases_Col_Enabled = 4;
	public static final int Excel_TestCases_Col_RunResult = 5;
	
	public static final int Excel_TestCaseSteps_Col_ID = 0;	
	public static final int Excel_TestCaseSteps_Col_TestCaseStep = 1;
	public static final int Excel_TestCaseSteps_Col_PageObject = 2;
	public static final int Excel_TestCaseSteps_Col_Action = 3;
	public static final int Excel_TestCaseSteps_Col_WebElement = 4;
	public static final int Excel_TestCaseSteps_Col_TestData = 5;
	public static final int Excel_TestCaseSteps_Col_TestDataRowIDs = 6;
	public static final int Excel_TestCaseSteps_Col_Result = 7;
		
	// Test Execution Excel Sheets
	public static final String[] Excel_Sheets_TestCases = {"TestCases-DriverView", "TestCases-Reports"};
	
	public static final String TestCasesExcelFile = "TestCases.xlsx";
	public static final String TestCaseDataExcelFile = "TestCaseData.xlsx";

	public static final String Path_OR = "OR.txt";
	public static final String KEYWORD_FAIL = "FAIL";
	public static final String KEYWORD_PASS = "PASS";
}
