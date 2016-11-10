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
import surveyor.scommon.source.SurveyorTestRunner;

import surveyor.scommon.actions.ObserverViewPageActions;
import surveyor.scommon.source.ObserverViewPage;

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
public class ObserverViewPageStressTest extends BaseMapViewTest {

	private static DriverViewPageActions driverViewPageAction;
	private static ArrayList<ObserverViewPageActions> observerViewPageActionList = new ArrayList<ObserverViewPageActions>();

	private static DriverViewPage driverViewPage;
	private static ArrayList<ObserverViewPage> observerViewPageList = new ArrayList<ObserverViewPage>();

	public ObserverViewPageStressTest() throws IOException {
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
			initializePageActionsList();
			initializeBasePageActions();
			initializeObserverViewPageActionList();

			driverViewPageAction = new DriverViewPageActions(getDriver(), getBaseURL(), getTestSetup());
			driverViewPage = new DriverViewPage(getDriver(), getBaseURL(), getTestSetup());
			PageFactory.initElements(getDriver(), driverViewPage);

		} catch (Exception e) {
			e.printStackTrace();
		}
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

	private void loginAsObserver(int userRowID, int index) throws Exception {
		loginPageActionList.get(index).open(EMPTY, NOTSET);
		LoginPageActions.workingDataRow.set(null);
		loginPageActionList.get(index).login(EMPTY, userRowID); /* Picarro Admin */
	}

	private void loginAsDriver(int userRowID) throws Exception {
		getLoginPageAction().open(EMPTY, NOTSET);
		LoginPageActions.workingDataRow.set(null);
		getLoginPageAction().login(EMPTY, userRowID); /* Picarro Admin */
	}

	/**
	 * Test Case ID: TC428_LargeNumberOfConcurrentObserversObservingTheMap
	 * Test Description: Large number of concurrent observers observing the map
	 * Script:
	 * 	- create 5 or 10 or 15 observers
	 * 	- login as observer and observe the survey in map/satellite view  (check how far we can stretch)
	 * Results:
	 * 	- Observers can successfully observe the survey
	 */
	@Test /* Need a DB3 running longer time for more observers to be enabled in this test */
	public void TC242_LargeNumberOfConcurrentObserversObservingTheMap() throws Exception {
		Log.info("\nRunning TC242_LargeNumberOfConcurrentObserversObservingTheMap ...");
		int[] observers = {
				 USER_ROW_ID_PICARRO_ADMIN
				,USER_ROW_ID_PICARRO_UTILITYADMIN
//				,USER_ROW_ID_PICARRO_SUPERVISOR
//				,USER_ROW_ID_PICARRO_SUPPORT
//				,USER_ROW_ID_AUTOMATION_ADMIN
				};

		addPageActionsForNewDrivers(observers.length - 1);
		initializeObserverViewPageActionList();

		for(int index = 0; index<observers.length; index++){
			loginAsObserver(observers[index], index);
		}
		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);
		startDrivingSurvey(ANALYZER3_REPLAY_ROW_ID, SURVEY_STANDARD1_ROW_ID, ONE_SECOND);

		for(int index = 0; index<observers.length; index++){
			homePageList.get(index).open(getLiveObservePath());
		}

		for(int index = 0; index<observers.length; index++){
			observerViewPageActionList.get(index).waitForConnectionToComplete(EMPTY, NOTSET);
			observerViewPageActionList.get(index).clickOnMapButton(EMPTY, NOTSET);
			observerViewPageActionList.get(index).turnOnMapView(EMPTY, NOTSET);
			observerViewPageActionList.get(index).showCurtainView(EMPTY, NOTSET);
			observerViewPageActionList.get(index).clickOnCurtainReturnButton(EMPTY, NOTSET);
			assertTrue(observerViewPageActionList.get(index).verifyMapViewIsShown(EMPTY, NOTSET));
		}

		for(int index = 0; index<observers.length; index++){
			observerViewPageActionList.get(index).clickOnMapButton(EMPTY, NOTSET);
			observerViewPageActionList.get(index).turnOnSatelliteView(EMPTY, NOTSET);
			observerViewPageActionList.get(index).showCurtainView(EMPTY, NOTSET);
			observerViewPageActionList.get(index).clickOnCurtainReturnButton(EMPTY, NOTSET);
			assertTrue(observerViewPageActionList.get(index).verifySatelliteViewIsShown(EMPTY, NOTSET));
		}

		stopSurveyAndAnalyzer();
	}
}