package androidapp.regression.source;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import androidapp.data.source.InvestigationReportDataVerifier;
import androidapp.entities.source.InvestigationEntity;
import androidapp.screens.source.AndroidInvestigateReportScreen;
import androidapp.screens.source.AndroidInvestigationScreen;
import common.source.BaseHelper;
import common.source.ExceptionUtility;
import common.source.Log;
import common.source.LogHelper;
import common.source.TestContext;
import common.source.Timeout;
import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.StoredProcLisaInvestigationShowIndication;
import surveyor.dataaccess.source.User;

public class BaseReportTest extends BaseAndroidTest {

	private static final int PAGES_TO_SCROLL_IN_INVESTIGATION_REPORTS_SCREEN = 3 /*numberOfPages*/;
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

	protected void resetInvestigationReportScreenResults(AndroidInvestigationScreen invScreen) {
		IntStream.rangeClosed(1, PAGES_TO_SCROLL_IN_INVESTIGATION_REPORTS_SCREEN-1).forEach(i -> {
			try {
				invScreen.scrollToPreviousPage();
			} catch (Exception e) {
				Log.error(String.format("Scroll to previous page failed! Exception -> %s", ExceptionUtility.getStackTraceString(e)));
			}
		});
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

	protected boolean verifyReportsAssignedToUserAndSameCustomerUserAreShown(AndroidInvestigationScreen invScreen, String username) throws Exception {
		Log.method("verifyReportsAssignedToUserAndSameCustomerUserAreShown", invScreen, username);
		return verifyReportsAssignedToUserAndSameCustomerUserAreShown(invScreen, username, PAGES_TO_SCROLL_IN_INVESTIGATION_REPORTS_SCREEN);
	}

	protected boolean verifyReportsAssignedToUserAndSameCustomerUserAreShown(AndroidInvestigationScreen invScreen, String username, Integer numberOfPages) throws Exception {
		Log.method("verifyReportsAssignedToUserAndSameCustomerUserAreShown", invScreen, username, numberOfPages);
		User user = User.getUser(username);
		List<String> usernames = User.getUsersForCustomer(user.getCustomerId()).stream()
			.map(u -> u.getUserName())
			.collect(Collectors.toList());
		Log.info(String.format("Usernames for customer are -> %s", LogHelper.listToString(usernames)));
		return verifyReportsAssignedToUserAreShown(invScreen, username, numberOfPages, s -> usernames.contains(s.getAssignedUserName()));
	}

	protected boolean verifyReportsAssignedToUserAreShown(AndroidInvestigationScreen invScreen, String username) throws Exception {
		return verifyReportsAssignedToUserAreShown(invScreen, username, 1 /*numberOfPages*/, s -> s.getAssignedUserName().equals(username));
	}

	private boolean verifyReportsAssignedToUserAreShown(AndroidInvestigationScreen invScreen, String username, Integer numberOfPages,
			Predicate<StoredProcLisaInvestigationShowIndication> matchPredicate) throws Exception {
		Log.method("verifyReportsAssignedToUserAreShown", invScreen, username);
		List<InvestigationEntity> investigations = invScreen.getInvestigations(numberOfPages);
		boolean match = investigations.stream()
			.allMatch(r -> {
				Report reportObj = Report.getReport(r.getReportTitle());
				if (reportObj != null) {
					String reportId = reportObj.getId();
					Log.info(String.format("Searching for assigned LISAs in report id='%s'", reportId));
					List<StoredProcLisaInvestigationShowIndication> lisaInvestigationfromSP = StoredProcLisaInvestigationShowIndication.getLisaInvestigation(reportId);
					if (lisaInvestigationfromSP != null && lisaInvestigationfromSP.size()>0) {
						Log.info(String.format("Investigated LISAs for reportId=[%s] -> %s", reportId,
							LogHelper.collectionToString(lisaInvestigationfromSP, "lisaInvestigationfromSP")));
						return lisaInvestigationfromSP.stream().anyMatch(s -> {
							if (s.getAssignedUserName()!=null) {
								if (!BaseHelper.isNullOrEmpty(s.getAssignedUserName().trim())) {
									Log.info("Executing match predicate");
									return matchPredicate.test(s);
								}
							}

							return true;
						});
					}

					Log.info(String.format("Found no assigned LISAs for this report id='%s'", reportId));
					return true;
				} else {
					Log.info(String.format("Report shown on screen NOT found in database. Report appears to be deleted from DB, report title=[%s]", r.getReportTitle()));
					return true;
				}
			});

		return match;
	}
}