package surveyor.dataprovider;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class AnalyticReportDataProvider extends ReportDataProvider {
	public static final String ANALYTIC_REPORT_DATA_PROVIDER_UNITTEST001 = "dataProviderAnalyticReports_UnitTest001";
	public static final String ANALYTIC_REPORT_DATA_PROVIDER_UNITTEST002 = "dataProviderAnalyticReports_UnitTest002";
	public static final String ANALYTIC_REPORT_DATA_PROVIDER_TC2373 = "dataProviderAnalyticReports_TC2373";
	public static final String ANALYTIC_REPORT_DATA_PROVIDER_TC2383 = "dataProviderAnalyticReports_TC2383";
	public static final String ANALYTIC_REPORT_DATA_PROVIDER_TC2341 = "dataProviderAnalyticReports_TC2341";
	public static final String ANALYTIC_REPORT_DATA_PROVIDER_TC2418 = "dataProviderAnalyticReports_TC2418";
	public static final String ANALYTIC_REPORT_DATA_PROVIDER_TC2389 = "dataProviderAnalyticReports_TC2389";
	public static final String ANALYTIC_REPORT_DATA_PROVIDER_TC2401 = "dataProviderAnalyticReports_TC2401";

	public AnalyticReportDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
	}

	@DataProvider
	public static Object[][] dataProviderAnalyticReports_UnitTest001() {
		return new Object[][] {
			{ "ARUnitTest001" /*TestCaseID*/, 4 /*userDataRowID*/,  208 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAnalyticReports_UnitTest002() {
		return new Object[][] {
			{ "ARUnitTest001" /*TestCaseID*/, 4 /*userDataRowID*/,  209 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAnalyticReports_TC2373() {
		return new Object[][] {
			{ "TC2373" /*TestCaseID*/, 6 /*userDataRowID*/,  210 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAnalyticReports_TC2383() {
		return new Object[][] {
			{ "TC2383" /*TestCaseID*/, 4 /*userDataRowID*/,  211 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAnalyticReports_TC2341() {
		return new Object[][] {
			{ "TC2341" /*TestCaseID*/, 4 /*userDataRowID*/,  213 /*reportDataRowID1*/, 214 /*reportDataRowID2*/, 215 /*reportDataRowID3*/, 216 /*reportDataRowID4*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAnalyticReports_TC2418() {
		return new Object[][] {
			{ "TC2418" /*TestCaseID*/, 6 /*userDataRowID*/,  219 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAnalyticReports_TC2389() {
		return new Object[][] {
		{ "TC2389" /*TestCaseID*/, 6 /*userDataRowID*/,  220 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAnalyticReports_TC2401() {
		return new Object[][] {
		{ "TC2401" /*TestCaseID*/, 6 /*userDataRowID*/,  221 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}
}
