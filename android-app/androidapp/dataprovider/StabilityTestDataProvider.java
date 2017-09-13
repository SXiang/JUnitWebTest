package androidapp.dataprovider;

import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class StabilityTestDataProvider extends AndroidDataProvider {

	public static final String STABILITY_TEST_DATA_PROVIDER_TC2811 = "dataProviderStabilityTest_TC2811";

	public StabilityTestDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@DataProvider
	public static Object[][] dataProviderStabilityTest_TC2811() {
		return new Object[][] { { "TC2811" /* TestCaseID */, 16 /* userDataRowID */, 271 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}
}
