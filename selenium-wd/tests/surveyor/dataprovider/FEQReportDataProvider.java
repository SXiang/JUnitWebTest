package surveyor.dataprovider;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

import surveyor.scommon.entities.CustomerSurveyInfoEntity;
import surveyor.scommon.generators.CustomerSurveyInfoBuilder;
import surveyor.scommon.generators.TestDataGenerator;

public class FEQReportDataProvider extends ReportDataProvider {
	public static final String FEQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2408 = "dataProviderPageActionsFacilityEQReports_TC2408";
	public static final String FEQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2410 = "dataProviderPageActionsFacilityEQReports_TC2410";

	public FEQReportDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsFacilityEQReports_TC2408() {
		return new Object[][] {
				{ "TC2408" /*TestCaseID*/,  4 /*userDataRowID*/,  2 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}
	
	@DataProvider
	public static Object[][] dataProviderPageActionsFacilityEQReports_TC2410() {
		return new Object[][] {
				{ "TC2410" /*TestCaseID*/,  4 /*userDataRowID*/,  3 /*reportDataRowID1*/, 4 /*reportDataRowID2*/}
		};
	}
}