package androidapp.regression.source;

import java.io.IOException;
import java.util.List;

import androidapp.entities.source.InvestigationEntity;
import androidapp.screens.source.AndroidInvestigateReportScreen;
import androidapp.screens.source.AndroidInvestigationScreen;
import common.source.BaseHelper;
import common.source.Log;
import common.source.LogHelper;
import common.source.TestContext;
import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.StoredProcLisaInvestigationShowIndication;

public class BaseReportTest extends BaseAndroidTest {
	protected void clickOnFirstInvestigationReport(AndroidInvestigationScreen investigationScreen) throws IOException {
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
		TestContext.INSTANCE.stayIdle(2);    // even after screen has loaded, listview takes time to load all results.
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

	protected boolean verifyReportsShownHaveLisasAssignedToUser(AndroidInvestigationScreen invScreen, String username) {
		Log.method("verifyReportsShownHaveLisasAssignedToUser", invScreen, username);
		List<InvestigationEntity> investigations = invScreen.getInvestigations();
		boolean notMatch = investigations.stream()
			.anyMatch(r -> {
				Report reportObj = Report.getReport(r.getReportTitle());
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
								return !s.getAssignedUserName().equals(username);
							}
						}

						return false;
					});
				}

				Log.info(String.format("Found no assigned LISAs for this report id='%s'", reportId));
				return true;
			});

		return !notMatch;
	}
}