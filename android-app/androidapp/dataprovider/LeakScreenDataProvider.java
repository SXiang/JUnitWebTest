package androidapp.dataprovider;

import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class LeakScreenDataProvider extends AndroidDataProvider {

	public LeakScreenDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	public static final String LEAK_SCREEN_DATA_PROVIDER_TC2440 = "dataProviderPageAndroidApp_TC2440";
	public static final String LEAK_SCREEN_DATA_PROVIDER_TC2441 = "dataProviderPageAndroidApp_TC2441";
	public static final String LEAK_SCREEN_DATA_PROVIDER_TC2543 = "dataProviderPageAndroidApp_TC2543";
	public static final String LEAK_SCREEN_DATA_PROVIDER_TC2545 = "dataProviderPageAndroidApp_TC2545";
	public static final String LEAK_SCREEN_DATA_PROVIDER_TC2546 = "dataProviderPageAndroidApp_TC2546";
	public static final String LEAK_SCREEN_DATA_PROVIDER_TC2547 = "dataProviderPageAndroidApp_TC2547";
	public static final String LEAK_SCREEN_DATA_PROVIDER_TC2555 = "dataProviderPageAndroidApp_TC2555";


	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2440() {
		return new Object[][] {
			{ "TC2440" /*TestCaseID*/, 0 /*userDataRowID - [TODO:CHANGE THIS]*/,  0 /*reportDataRowID1 - [TODO:CHANGE THIS]*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2441() {
		return new Object[][] {
			{ "TC2441" /*TestCaseID*/, 0 /*userDataRowID - [TODO:CHANGE THIS]*/,  0 /*reportDataRowID1 - [TODO:CHANGE THIS]*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2543() {
		return new Object[][] {
			{ "TC2543" /*TestCaseID*/, 0 /*userDataRowID - [TODO:CHANGE THIS]*/,  0 /*reportDataRowID1 - [TODO:CHANGE THIS]*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2545() {
		return new Object[][] {
			{ "TC2545" /*TestCaseID*/, 0 /*userDataRowID - [TODO:CHANGE THIS]*/,  0 /*reportDataRowID1 - [TODO:CHANGE THIS]*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2546() {
		return new Object[][] {
			{ "TC2546" /*TestCaseID*/, 0 /*userDataRowID - [TODO:CHANGE THIS]*/,  0 /*reportDataRowID1 - [TODO:CHANGE THIS]*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2547() {
		return new Object[][] {
			{ "TC2547" /*TestCaseID*/, 0 /*userDataRowID - [TODO:CHANGE THIS]*/,  0 /*reportDataRowID1 - [TODO:CHANGE THIS]*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2555() {
		return new Object[][] {
			{ "TC2555" /*TestCaseID*/, 0 /*userDataRowID - [TODO:CHANGE THIS]*/,  0 /*reportDataRowID1 - [TODO:CHANGE THIS]*/, -1/*reportDataRowID2*/}
		};
	}
}