package androidapp.dataprovider;

import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class AndroidErrorCasesDataProvider extends AndroidDataProvider {
	public static final String ERROR_CASES_DATA_PROVIDER_TC2723 = "dataProviderAndroidApp_TC2723";
	public static final String ERROR_CASES_DATA_PROVIDER_TC2724 = "dataProviderAndroidApp_TC2724";
	public static final String ERROR_CASES_DATA_PROVIDER_TC2725 = "dataProviderAndroidApp_TC2725";
	public static final String ERROR_CASES_DATA_PROVIDER_TC2730 = "dataProviderAndroidApp_TC2730";
	public static final String ERROR_CASES_DATA_PROVIDER_TC2732 = "dataProviderAndroidApp_TC2732";
	public static final String ERROR_CASES_DATA_PROVIDER_TC2733 = "dataProviderAndroidApp_TC2733";

	public AndroidErrorCasesDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2723() {
		return new Object[][] {
			{ "TC2723" /*TestCaseID*/, 18 /* userDataRowID */,  -1 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2724() {
		return new Object[][] {
			{ "TC2724" /*TestCaseID*/, 18 /* userDataRowID */,  -1 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2725() {
		return new Object[][] {
			{ "TC2725" /*TestCaseID*/, 18 /* userDataRowID */,  -1 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2730() {
		return new Object[][] {
			{ "TC2730" /*TestCaseID*/, 6 /* userDataRowID */,  -1 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2732() {
		return new Object[][] {
			{ "TC2732" /*TestCaseID*/, 18 /* userDataRowID */,  -1 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2733() {
		return new Object[][] {
			{ "TC2733" /*TestCaseID*/, 6 /* userDataRowID */,  -1 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}
}