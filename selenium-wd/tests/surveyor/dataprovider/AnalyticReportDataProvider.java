package surveyor.dataprovider;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

import surveyor.scommon.generators.CustomerSurveyInfoBuilder;
import surveyor.scommon.generators.TestDataGenerator;

public class AnalyticReportDataProvider extends ReportDataProvider {
	public static final String ANALYTIC_REPORT_DATA_PROVIDER_UNITTEST001 = "dataProviderAnalyticReports_UnitTest001";
	public static final String ANALYTIC_REPORT_DATA_PROVIDER_UNITTEST002 = "dataProviderAnalyticReports_UnitTest002";
	public static final String ANALYTIC_REPORT_DATA_PROVIDER_TC2373 = "dataProviderAnalyticReports_TC2373";
	public static final String ANALYTIC_REPORT_DATA_PROVIDER_TC2383 = "dataProviderAnalyticReports_TC2383";
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
}