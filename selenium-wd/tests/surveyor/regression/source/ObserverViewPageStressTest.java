package surveyor.regression.source;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import common.source.Log;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.HomePage;
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

	public ObserverViewPageStressTest() throws IOException {
		super();
	}

	private static DriverViewPageActions driverViewPageAction;
	private static ArrayList<ObserverViewPageActions> observerViewPageActionList = new ArrayList<ObserverViewPageActions>();

	private static DriverViewPage driverViewPage;
	private static ArrayList<ObserverViewPage> observerViewPageList = new ArrayList<ObserverViewPage>();


	@BeforeClass
	public static void beforeTestClass() throws Exception {
		disposeProcesses();
		// Initialize needed at Class level for automation reports.
		initializePageActions();
	}

	@Before
	public void beforeTestMethod() {
		try {
			initializePageActionsList();
			initializePageActions();
			initializeObserverViewPageActionList();

			driverViewPageAction = new DriverViewPageActions(driver, baseURL, testSetup);
			driverViewPage = new DriverViewPage(driver, testSetup, baseURL);
			PageFactory.initElements(driver, driverViewPage);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void afterTestMethod() {
		try {
			afterTest();
			disposeProcesses();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initializeObserverViewPageActionList(){
		for(int i = observerViewPageActionList.size(); i < driverList.size(); i++){
			observerViewPageActionList.add(new ObserverViewPageActions(driverList.get(i), baseURLList.get(i), testSetupList.get(i)));
			// Initialize page objects.
			observerViewPageList.add(new ObserverViewPage(driverList.get(i), testSetupList.get(i), baseURLList.get(i)));
			PageFactory.initElements(driver, observerViewPageList.get(i));
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
		LoginPageActions.workingDataRow = null;
		loginPageActionList.get(index).login(EMPTY, userRowID); /* Picarro Admin */
	}

	private void loginAsDriver(int userRowID) throws Exception {
		loginPageAction.open(EMPTY, NOTSET);
		LoginPageActions.workingDataRow = null;
		loginPageAction.login(EMPTY, userRowID); /* Picarro Admin */
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
	public void TC428_LargeNumberOfConcurrentObserversObservingTheMap() throws Exception {
		Log.info("\nRunning TC428_LargeNumberOfConcurrentObserversObservingTheMap ...");
		int[] observers = {
				 USER_ROW_ID_PICARRO_ADMIN
//				,USER_ROW_ID_PICARRO_UTILITYADMIN
//				,USER_ROW_ID_PICARRO_SUPERVISOR
//				,USER_ROW_ID_PICARRO_SUPPORT
//				,USER_ROW_ID_AUTOMATION_ADMIN
				};

		addPageActionsForNewDrivers(observers.length - 1);
		initializeObserverViewPageActionList();
		
		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);
		startDrivingSurvey(ANALYZER3_REPLAY_ROW_ID, SURVEY_STANDARD1_ROW_ID, ONE_SECOND);
		
		for(int index = 0; index<observers.length; index++){
			loginAsObserver(observers[index], index);
		}
		
		for(int index = 0; index<observers.length; index++){
			homePageActionList.get(index).clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
			observerViewPageActionList.get(index).waitForConnectionToComplete(EMPTY, NOTSET);
		}
		
		for(int index = 0; index<observers.length; index++){
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
