package surveyor.dataprovider;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class FacilityEQReportDataProvider extends ReportDataProvider {
	public static final String FEQ_REPORT_PAGE_ACTION_DATA_PROVIDER_FEQUnitTest1 = "dataProviderPageActionsFEQReports_FEQUnitTest1";
	public FacilityEQReportDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsFEQReports_FEQUnitTest1() {
		return new Object[][] {
			{ "FEQUnitTest1" /*TestCaseID*/, 4 /*userDataRowID*/,  1 /*reportDataRowID1*/, -1 /*reportDataRowID2*/}
		};
	}
}