package androidapp.regression.source;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import androidapp.data.source.InvestigationReportDataVerifier;
import androidapp.entities.source.InvestigationEntity;
import androidapp.screens.source.AndroidInvestigateReportScreen;
import androidapp.screens.source.AndroidInvestigationScreen;
import common.source.BaseHelper;
import common.source.Log;
import common.source.LogHelper;
import common.source.TestContext;
import common.source.Timeout;
import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.StoredProcLisaInvestigationShowIndication;

public class BaseReportTest extends BaseAndroidTest {

	protected InvestigationReportDataVerifier invReportDataVerifier;

	public BaseReportTest() {
		invReportDataVerifier = InvestigationReportDataVerifier.newVerifier();
	}

	protected void clickOnFirstInvestigationReport(AndroidInvestigationScreen investigationScreen) throws Exception {
		investigationScreen.clickOnFirstInvestigation();
	}

	protected void navigateToInvestigationReportScreenWithDefaultCreds(AndroidInvestigationScreen investigationScreen) throws Exception {
		final String password = TestContext.INSTANCE.getTestSetup().getLoginPwd();
		navigateToInvestigationReportScreen(investigationScreen, password);
	}

	protected void navigateToInvestigationReportScreen(AndroidInvestigationScreen investigationScreen, String password) throws Exception {
		mapScreen.waitForScreenLoad();
		Log.info("Map screen loaded successfully!");

		mapScreen.clickOnInvestigate();
		initializeMapScreen();
		mapScreen.waitForLoginDialogToShow();
		mapScreen.enterPassword(password);
		mapScreen.clickOnSubmit();
		investigationScreen.waitForScreenLoad();
	}

	protected void searchForReportId(AndroidInvestigationScreen investigationScreen, String reportTitle) throws Exception {
		investigationScreen.performSearch(reportTitle);
	}

	protected boolean verifyExpectedMarkersShownOnInvestigationScreen(AndroidInvestigateReportScreen investigateReportScreen, final Boolean refetchItems, final Integer expectedInvestigationMarkers) {
		Log.method("verifyExpectedMarkersShownOnInvestigationScreen", investigateReportScreen, refetchItems, expectedInvestigationMarkers);
		if (refetchItems) {
			Log.info("Re-initializing the list view items on the screen");
			investigateReportScreen.reInitializeListItems();
		}

		Integer count = investigateReportScreen.getInvestigationMarkersCount();
		Log.info(String.format("Found %d investigation markers", count));
		return (count == expectedInvestigationMarkers);
	}

	protected boolean waitForExpectedMarkersToShowInList(AndroidInvestigateReportScreen investigateReportScreen, Integer expectedInvestigationMarkers) {
		Log.method("waitForExpectedMarkersToShowInList");
		(new WebDriverWait(getAndroidDriver(), Timeout.ANDROID_APP_SCREEN_LOAD_TIMEOUT)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				investigateReportScreen.reInitializeListItems();
				Integer count = investigateReportScreen.getInvestigationMarkersCount();
				Log.info(String.format("Found %d investigation markers", count));
				return (count == expectedInvestigationMarkers);
			}
		});

		return true;
	}

	protected boolean verifyReportsAssignedToUserAreShown(AndroidInvestigationScreen invScreen, String username) {
		Log.method("verifyReportsAssignedToUserAreShown", invScreen, username);
		List<InvestigationEntity> investigations = invScreen.getInvestigations();
		boolean match = investigations.stream()
			.allMatch(r -> {
				Report reportObj = Report.getReport(r.getReportTitle());
				if (reportObj == null) {
					Log.error(String.format("Incorrect report found in list. Incorrect report title=[%s]", r.getReportTitle()));
					return false;
				}

				String reportId = reportObj.getId();
				Log.info(String.format("Searching for assigned LISAs in report id='%s'", reportId));
				List<StoredProcLisaInvestigationShowIndication> lisaInvestigationfromSP = StoredProcLisaInvestigationShowIndication.getLisaInvestigation(reportId);
				if (lisaInvestigationfromSP != null && lisaInvestigationfromSP.size()>0) {
					Log.info(String.format("Investigated LISAs for reportId=[%s] -> %s", reportId,
						LogHelper.collectionToString(lisaInvestigationfromSP, "lisaInvestigationfromSP")));
					return lisaInvestigationfromSP.stream().anyMatch(s -> {
						if (s.getAssignedUserName()!=null) {
							if (!BaseHelper.isNullOrEmpty(s.getAssignedUserName().trim())) {
								Log.info(String.format("Matching assigned username. Expected=[%s], Actual=[%s]", username, s.getAssignedUserName()));
								return s.getAssignedUserName().equals(username);
							}
						}

						return true;
					});
				}


				Log.info(String.format("Found no assigned LISAs for this report id='%s'", reportId));
				return true;
			});

		return match;
	}
}