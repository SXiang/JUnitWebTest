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
	public static final String EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC532 = "dataProviderPageActionsEQReports_TC532";
	public static final String EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC535 = "dataProviderPageActionsEQReports_TC535";
	public static final String EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC537 = "dataProviderPageActionsEQReports_TC537";
	public static final String EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC542 = "dataProviderPageActionsEQReports_TC542";
	public static final String EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC544 = "dataProviderPageActionsEQReports_TC544";
	public static final String EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC554 = "dataProviderPageActionsEQReports_TC554";
	public static final String EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC556 = "dataProviderPageActionsEQReports_TC556";
	public static final String EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC557 = "dataProviderPageActionsEQReports_TC557";
	public static final String EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC558 = "dataProviderPageActionsEQReports_TC558";
	public static final String EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC559 = "dataProviderPageActionsEQReports_TC559";
	public static final String EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2419 = "dataProviderPageActionsEQReports_TC2419";
	public static final String EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2407 = "dataProviderPageActionsEQReports_TC2407";
	public static final String EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2409 = "dataProviderPageActionsEQReports_TC2409";
	
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
	@DataProvider
	public static Object[][] dataProviderPageActionsEQReports_TC532() {
		return new Object[][] {
			{ "TC532" /*TestCaseID*/, 2 /*userDataRowID*/,  5 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}
	
	@DataProvider
	public static Object[][] dataProviderPageActionsEQReports_TC535() {
		return new Object[][] {
			{ "TC535" /*TestCaseID*/, 2 /*userDataRowID*/,  6 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}
	@DataProvider
	public static Object[][] dataProviderPageActionsEQReports_TC537() {
		return new Object[][] {
			{ "TC537" /*TestCaseID*/, 1 /*userDataRowID*/,  7 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}
	@DataProvider
	public static Object[][] dataProviderPageActionsEQReports_TC542() {
		return new Object[][] {
			{ "TC542" /*TestCaseID*/, 11 /*userDataRowID*/,  8 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}
	@DataProvider
	public static Object[][] dataProviderPageActionsEQReports_TC544() {
		return new Object[][] {
			{ "TC544" /*TestCaseID*/, 4 /*userDataRowID*/,  9 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}
	@DataProvider
	public static Object[][] dataProviderPageActionsEQReports_TC554() {
		return new Object[][] {
			{ "TC554" /*TestCaseID*/, 11 /*userDataRowID*/,  10 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}
	
	@DataProvider
	public static Object[][] dataProviderPageActionsEQReports_TC556() {
		return new Object[][] {
			{ "TC556" /*TestCaseID*/, 4 /*userDataRowID*/,  11 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}
	@DataProvider
	public static Object[][] dataProviderPageActionsEQReports_TC557() {
		return new Object[][] {
			{ "TC557" /*TestCaseID*/, 2 /*userDataRowID*/, 12 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}
	@DataProvider
	public static Object[][] dataProviderPageActionsEQReports_TC558() {
		return new Object[][] {
			{ "TC558" /*TestCaseID*/, 2 /*userDataRowID*/,  13 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}
	@DataProvider
	public static Object[][] dataProviderPageActionsEQReports_TC559() {
		return new Object[][] {
			{ "TC559" /*TestCaseID*/, 18 /*userDataRowID*/,  14 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}
	@DataProvider
	public static Object[][] dataProviderPageActionsEQReports_TC2419() {
		return new Object[][] {
			{ "TC2419" /*TestCaseID*/, 4 /*userDataRowID*/,  15 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}
	
	@DataProvider
	public static Object[][] dataProviderPageActionsEQReports_TC2407() {
		return new Object[][] {
			{ "TC2407" /*TestCaseID*/, 4 /*userDataRowID*/,  16 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsEQReports_TC2409() {
		return new Object[][] {
			{ "TC2409" /*TestCaseID*/, 4 /*userDataRowID*/,  17 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}

}