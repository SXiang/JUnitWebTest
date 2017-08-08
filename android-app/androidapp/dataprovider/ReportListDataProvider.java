package androidapp.dataprovider;

import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class ReportListDataProvider extends AndroidDataProvider {

	public static final String REPORT_LIST_DATA_PROVIDER_TC2429 = "dataProviderReportList_TC2429";
	public static final String REPORT_LIST_DATA_PROVIDER_TC2430 = "dataProviderReportList_TC2430";
	public static final String REPORT_LIST_DATA_PROVIDER_TC2431 = "dataProviderReportList_TC2431";
	public static final String REPORT_LIST_DATA_PROVIDER_TC2434 = "dataProviderReportList_TC2434";
	public static final String REPORT_LIST_DATA_PROVIDER_TC2435 = "dataProviderReportList_TC2435";
	public static final String REPORT_LIST_DATA_PROVIDER_TC2436 = "dataProviderReportList_TC2436";
	public static final String REPORT_LIST_DATA_PROVIDER_TC2437 = "dataProviderReportList_TC2437";
	public static final String REPORT_LIST_DATA_PROVIDER_TC2438 = "dataProviderReportList_TC2438";
	public static final String REPORT_LIST_DATA_PROVIDER_TC2439 = "dataProviderReportList_TC2439";

	public static final String LEAK_SCREEN_DATA_PROVIDER_TC2555 = "dataProviderPageAndroidApp_TC2555";


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

	@DataProvider
	public static Object[][] dataProviderReportList_TC2434() {
		return new Object[][] { { "TC2434" /* TestCaseID */, 6 /* userDataRowID */, 233 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderReportList_TC2435() {
		return new Object[][] { { "TC2435" /* TestCaseID */, 6 /* userDataRowID */, 234 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderReportList_TC2436() {
		return new Object[][] { { "TC2436" /* TestCaseID */, 6 /* userDataRowID */, 235 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderReportList_TC2437() {
		return new Object[][] { { "TC2437" /* TestCaseID */, 6 /* userDataRowID */, 246 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderReportList_TC2438() {
		return new Object[][] { { "TC2438" /* TestCaseID */, 6 /* userDataRowID */, 247 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderReportList_TC2439() {
		return new Object[][] { { "TC2439" /* TestCaseID */, 6 /* userDataRowID */, 248 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2555() {
		return new Object[][] { { "TC2555" /*TestCaseID*/, 6 /*userDataRowID*/,  255 /*reportDataRowID1*/, -1/*reportDataRowID2*/} };
	}

}