package surveyor.regression.source;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import common.source.Log;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.SurveyorTestRunner;

import surveyor.scommon.actions.ObserverViewPageActions;
import surveyor.scommon.source.ObserverViewPage;
import surveyor.scommon.source.PageObjectFactory;

/*
 * **** NOTES ****:
 *  1. Action based tests that work on MapView (Survey, Observer, Driver) can derive from BaseMapViewTest.
 *  2. If any of the tests do NOT use TestEnvironment actions for starting Analyzer and simulator then
 *  they should follow this convention to start simulator:
 *    Mark the test as TC*_SimulatorTest_* and it will be detected as Simulator based test and will trigger
 *    installation of Simulator pre-requisites before running the test.
 *
 */
@RunWith(SurveyorTestRunner.class)
public class ObserverViewPageTest_Analytics extends BaseMapViewTest {

	// Change this: When test defaulted to Analyzer 1.
	private DriverViewPageActions driverViewPageAction;
	private ArrayList<ObserverViewPageActions> observerViewPageActionList = new ArrayList<ObserverViewPageActions>();

	private ArrayList<ObserverViewPage> observerViewPageList = new ArrayList<ObserverViewPage>();
	private DriverViewPage driverViewPage;

	public ObserverViewPageTest_Analytics() throws IOException {
		super();
	}

	@BeforeClass
	public static void beforeTestClass() {
		initializeTestObjects();
	}

	@Before
	public void beforeTestMethod() {
		try {
			initializeTestObjects();
			initializePageObjects();
			initializePageActionsList();
			initializeBasePageActions();
			initializeObserverViewPageActionList();
			driverViewPageAction = new DriverViewPageActions(getDriver(), getBaseURL(), getTestSetup());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initializePageObjects() {
		PageObjectFactory pageObjectFactory = new PageObjectFactory();

		driverViewPage = pageObjectFactory.getDriverViewPage();
		PageFactory.initElements(getDriver(), driverViewPage);

		LoginPage loginPage = pageObjectFactory.getLoginPage();
		setLoginPage(loginPage);
		PageFactory.initElements(getDriver(), loginPage);
	}

	private void initializeObserverViewPageActionList(){
		for(int i = observerViewPageActionList.size(); i < driverList.size(); i++){
			observerViewPageActionList.add(new ObserverViewPageActions(driverList.get(i), baseURLList.get(i), testSetupList.get(i)));
			// Initialize page objects.
			observerViewPageList.add(new ObserverViewPage(driverList.get(i), baseURLList.get(i), testSetupList.get(i)));
			PageFactory.initElements(getDriver(), observerViewPageList.get(i));
		}
	}

	private void startDrivingSurvey(Integer analyzerRowId, Integer surveyRowId, Integer idleTimeInSeconds) throws Exception {
		startDrivingSurvey(driverViewPageAction, analyzerRowId, surveyRowId, idleTimeInSeconds);
	}

	private void stopSurveyAndAnalyzer() {
		stopSurveyAndAnalyzer(driverViewPageAction);
	}

	private void loginAsObserver(int userRowID) throws Exception {
		loginAsObserver(userRowID, 0);
	}

	private void loginAsObserver(int userRowID, int index) throws Exception {
		loginPageActionList.get(index).open(EMPTY, NOTSET);
		LoginPageActions.workingDataRow.set(null);
		loginPageActionList.get(index).login(EMPTY, userRowID); /* Picarro Admin */
	}

	private void loginAsDriver(int userRowID) throws Exception {
		loginPageAction.get().open(EMPTY, NOTSET);
		LoginPageActions.workingDataRow.set(null);
		loginPageAction.get().login(EMPTY, userRowID); /* Picarro Admin */
	}

	/**
	 * Test Case ID: TC2369_ObserverView_IndicationsAndLISAButtonsAreNotPresentInDisplayMenu
	 *	SCRIPT:
	 *	- Have driver start an Analytics Survey
	 *	- Log into the UI
	 *	- Click on the Online link for the above Surveyor
	 *	- Click Display
	 *
	 *	VERIFICATION:
	 *	- User is taken to Dashboard
	 *	- User is taken to Observer View for the active survey
	 *	- 8 Hour History, Concentration Chart, WindRose and FOV buttons are present. Indications, LISAs, Analysis and Field Notes buttons are not present
	**/
	@Test
	public void TC2369_ObserverView_IndicationsAndLISAButtonsAreNotPresentInDisplayMenu() throws Exception{
		Log.info("\nTestcase - TC2369_ObserverView_IndicationsAndLISAButtonsAreNotPresentInDisplayMenu\n");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ETH_ANALYZER1_REPLAY_ROW_ID, SURVEY_ANALYTICS1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_DRIVER);

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifyObserverViewPageIsOpened(EMPTY, NOTSET));

		observerViewPageActionList.get(0).getObserverViewPage().waitForAJAXCallsToComplete();
		assertTrue(observerViewPageActionList.get(0).verifyCorrectAnalyticsSurveyActiveMessageIsShownOnMap(EMPTY, NOTSET));

		observerViewPageActionList.get(0).clickOnDisplayButton(EMPTY, NOTSET);
		// assert visible buttons in Display menu
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitch8HourHistoryButtonIsVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchWindroseButtonIsVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchFovsButtonIsVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchConcentrationChartButtonIsVisible(EMPTY, NOTSET));
		// assert hidden buttons in Display menu
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchIndicationsButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchLisasButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchIsotopicAnalysisButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchNotesButtonIsNotVisible(EMPTY, NOTSET));

		stopSurveyAndAnalyzer();
	}
}