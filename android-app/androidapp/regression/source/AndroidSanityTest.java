package androidapp.regression.source;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import common.source.Log;
import common.source.TestContext;

public class AndroidSanityTest extends BaseAndroidTest {

	@Before
	public void beforeTest() throws Exception {
		initializeAppiumTest();
	}

	@Ignore   // Sample test to check Appium driver creation. Use for debugging.
	public void testMobileDriverLaunch() throws IOException {
		Log.info("Executing testMobileDriverLaunch ...");
		assertTrue("Appium Driver was NOT initialized correctly.", appiumDriver != null);
	}

	@Test
	public void testSettingsScreenAndElementsShownOnMapScreen() throws Exception {
		Log.info("Executing testSettingsScreenAndElementsShownOnMapScreen ...");

		String backpackAddress = TestContext.INSTANCE.getTestSetup().getBackPackServerIpAddress();
		String picServerAddress = TestContext.INSTANCE.getTestSetup().getBaseUrl();
		String username = TestContext.INSTANCE.getTestSetup().getLoginUser();

		mainLoginScreen.saveSettings(backpackAddress, picServerAddress, username);

		executeWithBackPackDataProcessesPaused(obj -> {
			assertTrue("Map screen loaded successfully!", mapScreen.waitForScreenLoad());
			String amplitudeText = mapScreen.getAmplitudeText();
			Log.info(String.format("amplitudeText = [%s]", amplitudeText));
			assertTrue("Amplitude text should NOT be empty.", amplitudeText.length() > 2);

			Float maxValue = Float.valueOf(mapScreen.getMaxText().replace("Max:", "").trim());
			Log.info(String.format("maxValue = [%f]", maxValue));
			assertTrue("Max value should be positive.", maxValue > 0.0f);
			return true;
		});
	}
}