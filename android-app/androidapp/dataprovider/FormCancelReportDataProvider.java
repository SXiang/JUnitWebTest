package androidapp.dataprovider;

import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

import surveyor.scommon.source.SurveyorTestRunner;

public class FormCancelReportDataProvider extends SurveyorTestRunner {

	public static final String REPORT_DATA_PROVIDER_TC2682 = "dataProviderAndroidApp_TC2682";
	public static final String REPORT_DATA_PROVIDER_TC2683 = "dataProviderAndroidApp_TC2683";
	public static final String REPORT_DATA_PROVIDER_TC2684 = "dataProviderAndroidApp_TC2684";

	public FormCancelReportDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2682() {
		return new Object[][] {
			{ "TC2682" /*TestCaseID*/, 18 /*userDataRowID*/,  256 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2683() {
		return new Object[][] {
			{ "TC2683" /*TestCaseID*/, 18 /*userDataRowID*/,  257 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2684() {
		return new Object[][] {
			{ "TC2684" /*TestCaseID*/, 18 /*userDataRowID*/,  258 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}
}