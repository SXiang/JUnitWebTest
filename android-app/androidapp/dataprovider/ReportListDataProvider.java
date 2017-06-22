package androidapp.dataprovider;

import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class ReportListDataProvider extends AndroidDataProvider {

	public static final String REPORT_LIST_DATA_PROVIDER_TC2429 = "dataProviderReportList_TC2429";

	public ReportListDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@DataProvider
	public static Object[][] dataProviderReportList_TC2429() {
		return new Object[][] { { "TC231" /* TestCaseID */, 6 /* userDataRowID */, 14 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}
}