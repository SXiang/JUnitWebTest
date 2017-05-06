package surveyor.unittest.source;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import common.source.Log;
import common.source.RegexUtility;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.source.BaseMapViewPage.FeatureInfo;

public class RegexUtilityTest {

	@BeforeClass
	public static void BeforeClass()	{
		// Initialize TestSetup to instantiate the TestContext.
		TestSetup testSetup = new TestSetup(false /* skip initialization */);
		String rootPath;
		try {
			rootPath = TestSetup.getRootPath();
			testSetup.loadTestProperties(rootPath);
		} catch (IOException e) {
			Log.error(e.getMessage());
		}
		testSetup.initialize();
		TestContext.INSTANCE.setTestSetup(testSetup);
	}

	@Test
	public void testFeatureInfoAllMatch() {
		Log.info("Running test - testFeatureInfoAllMatch() ...");
		String fInfoText = "Disposition : Natural GasClassification Confidence : ?95.00 %Methane Concentration : 2.15 ppmEthane Ratio : 6.78 ± 1.00 %Amplitude : 0.18 ppm";
		assertTrue(isFeatureInfoTextMatch(fInfoText));
	}

	@Test
	public void testFeatureInfoAllMatch_ReplaceInfo() {
		Log.info("Running test - testFeatureInfoAllMatch_ReplaceInfo() ...");
		String fInfoText = "Disposition : Natural GasClassification Confidence : ?95.00 %Methane Concentration : 2.15 ppmEthane Ratio : 6.78 ± 1.00 %Amplitude : 0.18 ppm";
		assertTrue(isFeatureInfoTextMatch(fInfoText, true /*modifyInfoText*/));
	}

	@Test
	public void testFeatureInfoSomeMatch() {
		Log.info("Running test - testFeatureInfoSomeMatch() ...");
		String fInfoText = "Classification Confidence : ?95.00 %Ethane Ratio : 6.78 ± 1.00 %Amplitude : 0.18 ppm";
		assertTrue(isFeatureInfoTextMatch(fInfoText));
	}

	@Test
	public void testFeatureInfoNoneMatch() {
		Log.info("Running test - testFeatureInfoNoneMatch() ...");
		String fInfoText = "NO_MATCHING_RECORDS";
		assertFalse(isFeatureInfoTextMatch(fInfoText));
	}

	private boolean isFeatureInfoTextMatch(String featureInfoText) {
		return isFeatureInfoTextMatch(featureInfoText, false /*modifyInfoText*/);
	}

	private boolean isFeatureInfoTextMatch(String featureInfoText, boolean modifyInfoText) {
		String[] regexPatternList = {
				String.format(RegexUtility.FEATURE_INFO_REGEX_WITH_PLACEHOLDER, Resources.getResource(ResourceKeys.Survey_amplitude).replace(" :", "")),
				String.format(RegexUtility.FEATURE_INFO_REGEX_WITH_PLACEHOLDER, Resources.getResource(ResourceKeys.Survey_EthaneRatio).replace(" :", "")),
				String.format(RegexUtility.FEATURE_INFO_REGEX_WITH_PLACEHOLDER, Resources.getResource(ResourceKeys.Survey_CH4).replace(" :", "")),
				String.format(RegexUtility.FEATURE_INFO_REGEX_WITH_PLACEHOLDER, Resources.getResource(ResourceKeys.Survey_ClassificationConfidence).replace(" :", "")),
				String.format(RegexUtility.FEATURE_INFO_REGEX_WITH_PLACEHOLDER, Resources.getResource(ResourceKeys.Survey_Disposition).replace(" :", ""))
		};

		boolean atleastOneMatch = false;
		for (String regexPattern : regexPatternList) {
			if (RegexUtility.matchesPattern(featureInfoText, regexPattern)) {
				List<String> matchingGroups = RegexUtility.getMatchingGroups(featureInfoText, regexPattern, true);
				String matchedText = matchingGroups.get(matchingGroups.size()-1);
				matchingGroups.forEach(t -> Log.info("Matching Group: " + t));
				Log.info(String.format("Matched Text=[%s]. Pattern=[%s]; Text=[%s]", matchedText, regexPattern, featureInfoText));
				if (modifyInfoText) {
					featureInfoText = featureInfoText.replace(matchedText, "");
				}

				atleastOneMatch = true;
			} else {
				String failureMsg = String.format("FAILED match. Pattern=[%s]; Text=[%s]", regexPattern, featureInfoText);
				Log.warn(failureMsg);
			}
		}

		return atleastOneMatch;
	}
}