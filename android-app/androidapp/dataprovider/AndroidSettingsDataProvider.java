package androidapp.dataprovider;

import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class AndroidSettingsDataProvider extends AndroidDataProvider {

	public static final String SETTINGS_DATA_PROVIDER_TC2443 = "dataProviderSettings_TC2443";
	public static final String SETTINGS_DATA_PROVIDER_TC2444 = "dataProviderSettings_TC2443";
	public static final String SETTINGS_DATA_PROVIDER_TC2551 = "dataProviderSettings_TC2551";

	public AndroidSettingsDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@DataProvider
	public static Object[][] dataProviderSettings_TC2443() {
		return new Object[][] { { "TC2443" /* TestCaseID */, 16 /* userDataRowID */} };
	}

	@DataProvider
	public static Object[][] dataProviderSettings_TC2444() {
		return new Object[][] { { "TC2444" /* TestCaseID */, 16 /* userDataRowID */} };
	}

	@DataProvider
	public static Object[][] dataProviderSettings_TC2551() {
		return new Object[][] { { "TC2551" /* TestCaseID */, 16 /* userDataRowID */} };
	}
}