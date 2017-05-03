package surveyor.dataprovider;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

import surveyor.scommon.generators.CustomerSurveyInfoBuilder;
import surveyor.scommon.generators.TestDataGenerator;

public class AnalyticReportDataProvider extends ReportDataProvider {
	public static final String ANALYTIC_REPORT_DATA_PROVIDER_UNITTEST001 = "dataProviderAnalyticReports_UnitTest001";
	public static final String ANALYTIC_REPORT_DATA_PROVIDER_UNITTEST002 = "dataProviderAnalyticReports_UnitTest002";
	public static final String ANALYTIC_REPORT_DATA_PROVIDER_TC2338 = "dataProviderAnalyticReports_TC2338";
	public static final String ANALYTIC_REPORT_DATA_PROVIDER_TC2339 = "dataProviderAnalyticReports_TC2339";
	public static final String ANALYTIC_REPORT_DATA_PROVIDER_TC2340 = "dataProviderAnalyticReports_TC2340";
	public static final String ANALYTIC_REPORT_DATA_PROVIDER_TC2341 = "dataProviderAnalyticReports_TC2341";
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
	public static Object[][] dataProviderAnalyticReports_TC2338() {
		return new Object[][] {
			{ "TC2338" /*TestCaseID*/, 4 /*userDataRowID*/,  210 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}
	
	@DataProvider
	public static Object[][] dataProviderAnalyticReports_TC2339() {
		return new Object[][] {
			{ "TC2339" /*TestCaseID*/, 4 /*userDataRowID*/,  211 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}
	
	@DataProvider
	public static Object[][] dataProviderAnalyticReports_TC2341() {
		return new Object[][] {
			{ "TC2341" /*TestCaseID*/, 4 /*userDataRowID*/,  213 /*reportDataRowID1*/, 214 /*reportDataRowID2*/, 215 /*reportDataRowID3*/, 216 /*reportDataRowID4*/}
		};
	}
	
	@DataProvider
	public static Object[][] dataProviderAnalyticReports_TC2340() {
		return new Object[][] {
			{ "TC2340" /*TestCaseID*/, 4 /*userDataRowID*/,  217 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}
}