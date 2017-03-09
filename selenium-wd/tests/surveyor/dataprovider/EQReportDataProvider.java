package surveyor.dataprovider;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

import surveyor.scommon.entities.CustomerSurveyInfoEntity;
import surveyor.scommon.generators.CustomerSurveyInfoBuilder;
import surveyor.scommon.generators.TestDataGenerator;

public class EQReportDataProvider extends ReportDataProvider {
	public static final String EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC561 = "dataProviderPageActionsEQReports_TC561";
	public static final String EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC562 = "dataProviderPageActionsEQReports_TC562";
	public static final String EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC566 = "dataProviderPageActionsEQReports_TC566";
	public static final String EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC651 = "dataProviderPageActionsEQReports_TC651";
	public static final String EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC655 = "dataProviderPageActionsEQReports_TC655";
	
	public EQReportDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsEQReports_TC561() {
		return new Object[][] {
			{ "TC561" /*TestCaseID*/, 4 /*userDataRowID*/,  2 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}
	@DataProvider
	public static Object[][] dataProviderPageActionsEQReports_TC562() {
		return new Object[][] {
			{ "TC562" /*TestCaseID*/, 4 /*userDataRowID*/,  3 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}
	@DataProvider
	public static Object[][] dataProviderPageActionsEQReports_TC566() {
		return new Object[][] {
			{ "TC566" /*TestCaseID*/, 2 /*userDataRowID*/,  1 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}
	@DataProvider
	public static Object[][] dataProviderPageActionsEQReports_TC651() {
		return new Object[][] {
			{ "TC651" /*TestCaseID*/, 2 /*userDataRowID*/,  4 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}
	@DataProvider
	public static Object[][] dataProviderPageActionsEQReports_TC655() {
		return new Object[][] {
			{ "TC655" /*TestCaseID*/, 2 /*userDataRowID*/,  -1 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}
}