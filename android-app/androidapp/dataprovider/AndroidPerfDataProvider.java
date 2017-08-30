package androidapp.dataprovider;

import org.junit.runners.model.InitializationError;
import com.tngtech.java.junit.dataprovider.DataProvider;

import surveyor.scommon.source.SurveyorTestRunner;

public class AndroidPerfDataProvider extends SurveyorTestRunner {

	public static final String REPORT_DATA_PROVIDER_PERF_TC2734_1 = "dataProviderAndroidApp_TC2734_1";
	public static final String REPORT_DATA_PROVIDER_PERF_TC2734_2 = "dataProviderAndroidApp_TC2734_2";
	public static final String REPORT_DATA_PROVIDER_PERF_TC2734_3 = "dataProviderAndroidApp_TC2734_3";

	public AndroidPerfDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2734_1() {
		return new Object[][] {
			{ "TC2734-1" /*TestCaseID*/, 6 /*userDataRowID*/,  268 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2734_2() {
		return new Object[][] {
			{ "TC2734-2" /*TestCaseID*/, 6 /*userDataRowID*/,  269 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2734_3() {
		return new Object[][] {
			{ "TC2734-3" /*TestCaseID*/, 6 /*userDataRowID*/,  270 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}
}