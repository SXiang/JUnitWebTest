package surveyor.dataprovider;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

import surveyor.scommon.entities.CustomerSurveyInfoEntity;
import surveyor.scommon.generators.CustomerSurveyInfoBuilder;
import surveyor.scommon.generators.TestDataGenerator;

public class EQReportDataProvider extends ReportDataProvider {
	public static final String EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1437 = "dataProviderPageActionsEQReports_TC1437";

	public EQReportDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsEQReports_TC1437() {
		return new Object[][] {
			{ "TC1437" /*TestCaseID*/, 6 /*userDataRowID*/,  3 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}
}