package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.source.TestSetup;
import surveyor.scommon.source.ComplianceReportsPage;

public class ComplianceReportsPageActions extends BasePageActions {
	private ComplianceReportsPage complianceReportsPage = null;

	public ComplianceReportsPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		complianceReportsPage = new ComplianceReportsPage(driver, strBaseURL, testSetup);
		PageFactory.initElements(driver, complianceReportsPage);
	}
	
	/* START - Individual actions on the Page*/
	
	// Manage Reports Page + sub-actions
	public boolean openManageReports(String testData, String dataRowIDs) {
		return false;
	}

	public boolean performSearch(String searchKeyword, String dataRowIDs) {
		return false;
	}

	public boolean setRecordsPerPage(String numRecords, String dataRowIDs) {
		return false;
	}

	public boolean navigateToPageNo(String pageNumber, String dataRowIDs) {
		return false;
	}

	public boolean navigateToFirstPage(String testData, String dataRowIDs) {
		return false;
	}

	public boolean navigateToLastPage(String testData, String dataRowIDs) {
		return false;
	}

	public boolean navigateToNextPage(String testData, String dataRowIDs) {
		return false;
	}

	public boolean navigateToPrevPage(String testData, String dataRowIDs) {
		return false;
	}

	// Add Report Page + sub-actions
	public boolean openAddNewReports(String testData, String dataRowIDs) {
		// If ManageReports page is NOT already open, then open it first.
		
		return false;
	}
	
	public boolean submitNewReport(String testData, String dataRowIDs) {
		return false;
	}

	/* END - Individual actions on the Page*/

	/* START - Actions pertaining to Test DataSource*/
	public boolean addNewReport(String testData, String dataRowIDs) {
		return false;
	}
	
	public boolean deleteReport(String testData, String dataRowIDs) {
		return false;
	}

	public boolean copyReport(String testData, String dataRowIDs) {
		return false;
	}

	public boolean copyReportModifyDetails(String testData, String dataRowIDs) {
		return false;
	}

	public boolean findReport(String testData, String dataRowIDs) {
		return false;
	}

	public boolean resubmitReport(String testData, String dataRowIDs) {
		return false;
	}

	public boolean investigateReport(String testData, String dataRowIDs) {
		return false;
	}

	public boolean validateInvestigatePDFReport(String testData, String dataRowIDs) {
		return false;
	}

	public boolean validatePDFFiles(String testData, String dataRowIDs) {
		return false;
	}
	/* END - Actions pertaining to Test DataSource*/
}
