package androidapp.regression.source;

import java.io.IOException;

import androidapp.screens.source.AndroidInvestigateReportScreen;
import androidapp.screens.source.AndroidInvestigationScreen;
import common.source.Log;
import common.source.TestContext;

public class BaseReportTest extends BaseAndroidTest {
	protected void clickOnFirstInvestigationReport(AndroidInvestigationScreen investigationScreen) throws IOException {
		investigationScreen.clickOnFirstInvestigation();
	}

	protected void navigateToInvestigationReportScreenWithDefaultCreds(AndroidInvestigationScreen investigationScreen) throws Exception {
		final String password = TestContext.INSTANCE.getTestSetup().getLoginPwd();
		mapScreen.clickOnInvestigate();
		initializeMapScreen();
		mapScreen.waitForLoginDialogToShow();
		mapScreen.enterPassword(password);
		mapScreen.clickOnSubmit();
		investigationScreen.waitForScreenLoad();
	}

	protected void navigateToMapScreenUsingDefaultCreds() throws Exception {
		final String backpackAddress = TestContext.INSTANCE.getTestSetup().getBackPackServerIpAddress();
		final String picServerAddress = TestContext.INSTANCE.getTestSetup().getBaseUrl();
		final String username = TestContext.INSTANCE.getTestSetup().getLoginUser();

		settingsScreen.saveSettings(backpackAddress, picServerAddress, username);
		mapScreen.waitForScreenLoad();
		Log.info("Map screen loaded successfully!");
	}

	protected void searchForReportId(AndroidInvestigationScreen investigationScreen, String suffixRptId) throws Exception {
		investigationScreen.performSearch(suffixRptId);
	}

	protected boolean verifyExpectedMarkersShownOnInvestigationScreen(AndroidInvestigateReportScreen investigateReportScreen, final Boolean refetchItems, final Integer expectedInvestigationMarkers) {
		if (refetchItems) {
			Log.info("Re-initializing the list view items on the screen");
			investigateReportScreen.reInitializeListItems();
		}

		Integer count = investigateReportScreen.getInvestigationMarkersCount();
		Log.info(String.format("Found %d investigation markers", count));
		return (count == expectedInvestigationMarkers);
	}
}