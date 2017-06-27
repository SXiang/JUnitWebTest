package androidapp.dataprovider;

import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class ReportListDataProvider extends AndroidDataProvider {

	public static final String REPORT_LIST_DATA_PROVIDER_TC2429 = "dataProviderReportList_TC2429";
	public static final String REPORT_LIST_DATA_PROVIDER_TC2430 = "dataProviderReportList_TC2430";
	public static final String REPORT_LIST_DATA_PROVIDER_TC2431 = "dataProviderReportList_TC2431";

	public ReportListDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@DataProvider
	public static Object[][] dataProviderReportList_TC2429() {
		return new Object[][] { { "TC2429" /* TestCaseID */, 6 /* userDataRowID */, 230 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderReportList_TC2430() {
		return new Object[][] { { "TC2430" /* TestCaseID */, 6 /* userDataRowID */, 231 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderReportList_TC2431() {
		return new Object[][] { { "TC2431" /* TestCaseID */, 6 /* userDataRowID */, 232 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}
}