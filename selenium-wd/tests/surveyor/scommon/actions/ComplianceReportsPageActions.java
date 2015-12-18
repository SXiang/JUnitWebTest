package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.source.TestSetup;
import surveyor.scommon.source.ComplianceReportsPage;

public class ComplianceReportsPageActions extends BasePageActions {
	public static final int Excel_TestData_Report_Col_ID = 0;	
	public static final int Excel_TestData_Report_Col_2 = 1;
	public static final int Excel_TestData_Report_Col_3 = 2;
	public static final int Excel_TestData_Report_Col_4 = 3;
	public static final int Excel_TestData_Report_Col_5 = 4;
	public static final int Excel_TestData_Report_Col_6 = 5;
	
	private ComplianceReportsPage complianceReportsPage = null;

	public ComplianceReportsPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL);
		complianceReportsPage = new ComplianceReportsPage(driver, strBaseURL, testSetup);
		PageFactory.initElements(driver, complianceReportsPage);
	}
	
	/* START - Individual actions on the Page*/
	
	// Manage Reports Page + sub-actions
	public boolean openManageReports(String data, Integer dataRowID) {
		return false;
	}

	public boolean performSearch(String searchKeyword, Integer dataRowID) {
		return false;
	}

	public boolean setRecordsPerPage(String numRecords, Integer dataRowID) {
		return false;
	}

	public boolean navigateToPageNo(String pageNumber, Integer dataRowID) {
		return false;
	}

	public boolean navigateToFirstPage(String data, Integer dataRowID) {
		return false;
	}

	public boolean navigateToLastPage(String data, Integer dataRowID) {
		return false;
	}

	public boolean navigateToNextPage(String data, Integer dataRowID) {
		return false;
	}

	public boolean navigateToPrevPage(String data, Integer dataRowID) {
		return false;
	}

	public boolean open(String data, Integer dataRowID) {
		return false;
	}

	// Add Report Page + sub-actions
	public boolean openAddNewReports(String data, Integer dataRowID) {
		// If ManageReports page is NOT already open, then open it first.
		
		return false;
	}
	
	public boolean submitNewReport(String data, Integer dataRowID) {
		return false;
	}

	/* END - Individual actions on the Page*/

	/* START - Actions pertaining to Test DataSource*/
	public boolean addNewReport(String data, Integer dataRowID) {
		return false;
	}
	
	public boolean deleteReport(String data, Integer dataRowID) {
		return false;
	}

	public boolean copyReport(String data, Integer dataRowID) {
		return false;
	}

	public boolean copyReportModifyDetails(String data, Integer dataRowID) {
		return false;
	}

	public boolean findReport(String data, Integer dataRowID) {
		return false;
	}

	public boolean resubmitReport(String data, Integer dataRowID) {
		return false;
	}

	public boolean investigateReport(String data, Integer dataRowID) {
		return false;
	}

	public boolean validateInvestigatePDFReport(String data, Integer dataRowID) {
		return false;
	}

	public boolean validatePDFFiles(String data, Integer dataRowID) {
		return false;
	}
	/* END - Actions pertaining to Test DataSource*/

	/* Invoke action using specified ActionName */
	@Override
	public boolean invokeAction(String actionName, String data, Integer dataRowID) {
		if (actionName.equals("addNewReport")) { return this.addNewReport(data, dataRowID); }
		else if (actionName.equals("clickById")) { return this.clickById(data, dataRowID); }
		else if (actionName.equals("clickByIdAndWait")) { return this.clickByIdAndWait(data, dataRowID); }
		else if (actionName.equals("clickByXPath")) { return this.clickByXPath(data, dataRowID); }
		else if (actionName.equals("clickByXPathAndWait")) { return this.clickByXPathAndWait(data, dataRowID); }
		else if (actionName.equals("copyReport")) { return this.copyReport(data, dataRowID); }
		else if (actionName.equals("copyReportModifyDetails")) { return this.copyReportModifyDetails(data, dataRowID); }
		else if (actionName.equals("deleteReport")) { return this.deleteReport(data, dataRowID); }
		else if (actionName.equals("findReport")) { return this.findReport(data, dataRowID); }
		else if (actionName.equals("insertTextById")) { return this.insertTextById(data, dataRowID); }
		else if (actionName.equals("insertTextByXPath")) { return this.insertTextByXPath(data, dataRowID); }
		else if (actionName.equals("investigateReport")) { return this.investigateReport(data, dataRowID); }
		else if (actionName.equals("navigateToFirstPage")) { return this.navigateToFirstPage(data, dataRowID); }
		else if (actionName.equals("navigateToLastPage")) { return this.navigateToLastPage(data, dataRowID); }
		else if (actionName.equals("navigateToNextPage")) { return this.navigateToNextPage(data, dataRowID); }
		else if (actionName.equals("navigateToPageNo")) { return this.navigateToPageNo(data, dataRowID); }
		else if (actionName.equals("navigateToPrevPage")) { return this.navigateToPrevPage(data, dataRowID); }
		else if (actionName.equals("open")) { return this.open(data, dataRowID); }
		else if (actionName.equals("openAddNewReports")) { return this.openAddNewReports(data, dataRowID); }
		else if (actionName.equals("openManageReports")) { return this.openManageReports(data, dataRowID); }
		else if (actionName.equals("performSearch")) { return this.performSearch(data, dataRowID); }
		else if (actionName.equals("resubmitReport")) { return this.resubmitReport(data, dataRowID); }
		else if (actionName.equals("selectDropDownByID")) { return this.selectDropDownByID(data, dataRowID); }
		else if (actionName.equals("selectDropDownByXPath")) { return this.selectDropDownByXPath(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByID")) { return this.selectRadioButtonByID(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByXPath")) { return this.selectRadioButtonByXPath(data, dataRowID); }
		else if (actionName.equals("setRecordsPerPage")) { return this.setRecordsPerPage(data, dataRowID); }
		else if (actionName.equals("submitNewReport")) { return this.submitNewReport(data, dataRowID); }
		else if (actionName.equals("validateInvestigatePDFReport")) { return this.validateInvestigatePDFReport(data, dataRowID); }
		else if (actionName.equals("validatePDFFiles")) { return this.validatePDFFiles(data, dataRowID); }
		return false;
	}
}
