package surveyor.scommon.actions;

import java.util.List;
import org.openqa.selenium.WebDriver;
import common.source.TestSetup;
import surveyor.scommon.actions.data.AssessmentReportDataReader;
import surveyor.scommon.actions.data.AssessmentReportDataReader.AssessmentReportsDataRow;
import surveyor.scommon.actions.data.ReportViewsDataReader.ReportViewsDataRow;
import surveyor.scommon.entities.ComplianceReportEntity;
import surveyor.scommon.source.AssessmentReportsPage;

/**
 * TODO: Consolidate page actions from ComplianceReportsPageActions, AssessmentReportsPageActions
 *
 * @author spulikkal
 */
public class AssessmentReportsPageActions extends ReportCommonPageActions {

	private AssessmentReportDataReader dataReader = null;
	public static ThreadLocal<ComplianceReportEntity> workingReportsComp = new ThreadLocal<ComplianceReportEntity>();      		// Stores the ReportsCompliance object from createNewReport action
	public static ThreadLocal<AssessmentReportsDataRow> workingDataRow = new ThreadLocal<AssessmentReportsDataRow>();   // Stores the workingDataRow from createNewReport action
	public static ThreadLocal<List<ReportViewsDataRow>> workingReportViewsDataRows = new ThreadLocal<List<ReportViewsDataRow>>();    // Stores the dataRows for views created in createNewReport action.

	public AssessmentReportsPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup);
		initializePageObject(driver, new AssessmentReportsPage(driver, strBaseURL, testSetup));
		setDataReader(new AssessmentReportDataReader(this.excelUtility));
	}

	// Note: Not thread-safe.
	public static void clearStoredObjects() {
		workingReportsComp.set(null);
		workingDataRow.set(null);
		workingReportViewsDataRows.set(null);
	}

	/* START - Actions on the Page*/

	/* Invoke action using specified ActionName */
	@Override
	public boolean invokeAction(String actionName, String data, Integer dataRowID) throws Exception {
		return false;
	}

	public AssessmentReportDataReader getDataReader() {
		if (dataReader == null) {
			setDataReader(new AssessmentReportDataReader(this.excelUtility));
		}
		return dataReader;
	}

	public void setDataReader(AssessmentReportDataReader dataReader) {
		this.dataReader = dataReader;
	}

	public AssessmentReportsPage getAssessmentReportsPage() {
		return (AssessmentReportsPage)this.getPageObject();
	}
}