package surveyor.scommon.actions;

import java.util.List;

public class MethodParams {
	
	private String methodData;
	private List<Integer> dataRowIDList;
	private String testCaseSheetName;
	private Integer testStepNumber;
	private Integer dataRowID;
	private boolean result;
	
	public MethodParams(String testCaseSheetName, Integer testStepNumber, String methodData, List<Integer> dataRowIDList) {
		this.testCaseSheetName = testCaseSheetName;
		this.testStepNumber = testStepNumber;
		this.methodData = methodData;
		this.dataRowIDList = dataRowIDList;
	}
	
	public String getMethodData() {
		return methodData;
	}
	public void setMethodData(String methodData) {
		this.methodData = methodData;
	}
	public List<Integer> getDataRowIDList() {
		return dataRowIDList;
	}
	public void setDataRowIDList(List<Integer> dataRowIDList) {
		this.dataRowIDList = dataRowIDList;
	}
	public String getTestCaseSheetName() {
		return testCaseSheetName;
	}
	public void setTestCaseSheetName(String testCaseSheetName) {
		this.testCaseSheetName = testCaseSheetName;
	}
	public Integer getTestStepNumber() {
		return testStepNumber;
	}
	public void setTestStepNumber(Integer testStepNumber) {
		this.testStepNumber = testStepNumber;
	}
	public boolean getResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}

	public Integer getDataRowID() {
		return dataRowID;
	}

	public void setDataRowID(Integer dataRowID) {
		this.dataRowID = dataRowID;
	}
}
