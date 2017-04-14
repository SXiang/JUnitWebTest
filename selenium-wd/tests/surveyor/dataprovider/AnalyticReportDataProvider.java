package surveyor.dataprovider;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class AnalyticReportDataProvider extends ReportDataProvider {
	public static final String ANALYTIC_REPORT_DATA_PROVIDER_UNITTEST001 = "dataProviderAnalyticReports_UnitTest001";
	public static final String ANALYTIC_REPORT_DATA_PROVIDER_UNITTEST002 = "dataProviderAnalyticReports_UnitTest002";
	
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
}