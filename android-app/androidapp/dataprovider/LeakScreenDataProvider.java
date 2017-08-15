package androidapp.dataprovider;

import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class LeakScreenDataProvider extends AndroidDataProvider {

	public LeakScreenDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	public static final String LEAK_SCREEN_DATA_PROVIDER_TC2440 = "dataProviderAndroidApp_TC2440";
	public static final String LEAK_SCREEN_DATA_PROVIDER_TC2441 = "dataProviderAndroidApp_TC2441";
	public static final String LEAK_SCREEN_DATA_PROVIDER_TC2543 = "dataProviderAndroidApp_TC2543";
	public static final String LEAK_SCREEN_DATA_PROVIDER_TC2545 = "dataProviderAndroidApp_TC2545";
	public static final String LEAK_SCREEN_DATA_PROVIDER_TC2546 = "dataProviderAndroidApp_TC2546";
	public static final String LEAK_SCREEN_DATA_PROVIDER_TC2547 = "dataProviderAndroidApp_TC2547";
	public static final String LEAK_SCREEN_DATA_PROVIDER_TC2555 = "dataProviderAndroidApp_TC2555";
	public static final String LEAK_SCREEN_DATA_PROVIDER_TC2432 = "dataProviderAndroidApp_TC2432";
	public static final String LEAK_SCREEN_DATA_PROVIDER_TC2639 = "dataProviderAndroidApp_TC2639";
	public static final String LEAK_SCREEN_DATA_PROVIDER_TC2640 = "dataProviderAndroidApp_TC2640";
	public static final String LEAK_SCREEN_DATA_PROVIDER_TC2676 = "dataProviderAndroidApp_TC2676";
	public static final String LEAK_SCREEN_DATA_PROVIDER_TC2677 = "dataProviderAndroidApp_TC2677";
	public static final String LEAK_SCREEN_DATA_PROVIDER_TC2681 = "dataProviderAndroidApp_TC2681";
	public static final String LEAK_SCREEN_DATA_PROVIDER_TC2687 = "dataProviderAndroidApp_TC2687";

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2440() {
		return new Object[][] {
			{ "TC2440" /*TestCaseID*/, 6 /*userDataRowID*/,  249 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2441() {
		return new Object[][] {
			{ "TC2441" /*TestCaseID*/, 6 /*userDataRowID*/,  250 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2543() {
		return new Object[][] {
			{ "TC2543" /*TestCaseID*/, 6 /*userDataRowID*/,  251 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2545() {
		return new Object[][] {
			{ "TC2545" /*TestCaseID*/, 6 /*userDataRowID*/,  252 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2546() {
		return new Object[][] {
			{ "TC2546" /*TestCaseID*/, 6 /*userDataRowID*/,  253 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2547() {
		return new Object[][] {
			{ "TC2547" /*TestCaseID*/, 6 /*userDataRowID*/,  254 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2555() {
		return new Object[][] {
			{ "TC2555" /*TestCaseID*/, 6 /*userDataRowID*/,  255 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2676() {
		return new Object[][] {
			{ "TC2676" /*TestCaseID*/, 6 /*userDataRowID*/,  259 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2677() {
		return new Object[][] {
			{ "TC2677" /*TestCaseID*/, 6 /*userDataRowID*/,  260 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2681() {
		return new Object[][] {
			{ "TC2681" /*TestCaseID*/, 6 /*userDataRowID*/,  261 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2687() {
		return new Object[][] {
			{ "TC2687" /*TestCaseID*/, 0 /*userDataRowID,  0 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2432() {
		return new Object[][] {
			{ "TC2432" /*TestCaseID*/, 6 /*userDataRowID*/,  262 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2639() {
		return new Object[][] {
			{ "TC2639" /*TestCaseID*/, 6 /*userDataRowID*/,  263 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderAndroidApp_TC2640() {
		return new Object[][] {
			{ "TC2640" /*TestCaseID*/, 6 /*userDataRowID*/,  264 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}
}