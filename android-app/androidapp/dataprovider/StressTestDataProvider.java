package androidapp.dataprovider;

import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class StressTestDataProvider extends AndroidDataProvider {

	public static final String STRESS_TEST_DATA_PROVIDER_TC2870 = "dataProviderStressTest_TC2870";
	public static final String STRESS_TEST_DATA_PROVIDER_TC2871 = "dataProviderStressTest_TC2871";

	public StressTestDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@DataProvider
	public static Object[][] dataProviderStressTest_TC2870() {
		return new Object[][] { { "TC2870" /* TestCaseID */, 16 /* userDataRowID */, 286 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderStressTest_TC2871() {
		return new Object[][] { { "TC2871" /* TestCaseID */, 16 /* userDataRowID */, 287 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}
}