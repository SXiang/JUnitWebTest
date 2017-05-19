package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.CUSUSERROLEDR;
import static surveyor.scommon.source.SurveyorConstants.REGBASEUSERNAME;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import common.source.Log;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.dataaccess.source.Analyzer.CapabilityType;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.actions.ObserverViewPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
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
public class ObserverViewPageTest_EQ extends BaseMapViewTest {

	private DriverViewPageActions driverViewPageAction;
	private ArrayList<ObserverViewPageActions> observerViewPageActionList = new ArrayList<ObserverViewPageActions>();
	private ManageLocationPageActions manageLocationPageActions;
	private TestEnvironmentActions testEnvironmentAction = ActionBuilder.createTestEnvironmentAction();
	private ArrayList<ObserverViewPage> observerViewPageList = new ArrayList<ObserverViewPage>();
	private DriverViewPage driverViewPage;
	private static Map<String, String> testAccount;
	private static String userName, observerName;
	private static String userPassword;
	private static String customerName;
	private static String locationName;
	private static String analyzerName;
	private static String analyzerSharedKey;
	private static String surveyorName;
	private int analyticSurveyRowId = 62;
	private int zoomLevelForIndication = 13;
	private String db3DefnFile = "replay-db3-eth.defn";
	private String db3File = "AnalyticsSurvey-RFADS2024-03.db3";
	
	public ObserverViewPageTest_EQ() throws IOException {
		super();
	}

	@BeforeClass
	public static void beforeTestClass() {
		initializeTestObjects();
	}

	@AfterClass
	public static void afterClass() {
		if(testAccount!=null && testAccount.get("customerId")!=null){
			cleanUpGisData(testAccount.get("customerId"));
		}
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
			manageLocationPageActions = new ManageLocationPageActions(getDriver(), getBaseURL(), getTestSetup());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(testAccount == null){
			testAccount = createTestAccount("Analytics_ObserverView", CapabilityType.Ethane);
			observerName = testAccount.get("userName");
			userPassword = testAccount.get("userPassword");
			customerName = testAccount.get("customerName");
			locationName = testAccount.get("locationName");
			analyzerSharedKey = testAccount.get("analyzerSharedKey");
			analyzerName = testAccount.get("analyzerName");
			surveyorName = testAccount.get("surveyorName");
			userName = getTestSetup().getFixedSizeRandomNumber(6)+"td" + REGBASEUSERNAME;
			addTestUser(customerName, userName, userPassword, CUSUSERROLEDR, locationName);
			pushGisData(testAccount.get("customerId"));
		}
		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
		manageLocationPageActions.open(EMPTY, NOTSET);
		manageLocationPageActions.getManageLocationsPage().editSurveyMinAmplitude(customerName,locationName,"0.035");
	}

	private void initializePageObjects() {
		PageObjectFactory pageObjectFactory = new PageObjectFactory();

		driverViewPage = pageObjectFactory.getDriverViewPage();
		PageFactory.initElements(getDriver(), driverViewPage);

		LoginPage loginPage = pageObjectFactory.getLoginPage();
		setLoginPage(loginPage);
		PageFactory.initElements(getDriver(), loginPage);
		
		HomePage homePage = pageObjectFactory.getHomePage();
		setHomePage(homePage);
		PageFactory.initElements(getDriver(), homePage);
	}

	private void initializeObserverViewPageActionList(){
		for(int i = observerViewPageActionList.size(); i < driverList.size(); i++){
			observerViewPageActionList.add(new ObserverViewPageActions(driverList.get(i), baseURLList.get(i), testSetupList.get(i)));
			// Initialize page objects.
			observerViewPageList.add(new ObserverViewPage(driverList.get(i), baseURLList.get(i), testSetupList.get(i)));
			PageFactory.initElements(getDriver(), observerViewPageList.get(i));
		}
	}
	
	/**
	 * Test Case ID: TC1060_
	 * Test Description: -  	Observe Active and Inactive EQ Survey - Map View
	 * Script:
	 *	-On Home Page, click on Picarro Surveyors -> Online
	 *	- Click on Map and turn Map View ON
	 *	- Click on Display button
	 *	- Ask driver to stop the EQ survey
	 *		(user still driving the car)
	 * Results:
	 *	- - Survey Information is displayed in map view - Tag, Mode, Time, EQ Mode Active (bold green text), Driver Info, Elapsed time, Remaining Time, Zoom level, Surveyor and analyzer info
	 *	- The "EQ Mode Active" message in bold green font should not obscure any part of the survey details nor the map
	 *	- Car icon is displayed in red color. Breadcrumb will  be displayed in blue color
	 *	- "Isotopic Analysis" and "Field Notes" toggle buttons should not be present
	 *	- Wind Rose, Concentration Chart, Indications, Lisa and FOV toggle buttons should be present
	 *	- Curtain View button should not be present
	 *	- Car icon is displayed in grey color. Breadcrumb will be displayed in black color
	 *	- Survey Inactive message will be displayed
	 */
	@Ignore /* Depend on US4438 */
	public void TC1060_() throws Exception{
		Log.info("\nTestcase - TC1060_\n");
	}
	
	/**
	 * Test Case ID: TC1067_
	 * Test Description: - Observer View - EQ Survey as customer driver - Satellite - FoV, Wind Rose and Concentration Chart ON - Position and  GIS OFF
	 * Script:
	 *	-On Home Page, click on Picarro Surveyors -> Online
	 *	- Click on Map and turn Satellite View ON
	 *	- Click Display
	 *		Wind Rose - ON
	 *		Concentration Chart - ON
	 *		FoV - ON
	 *	- Click GIS - All options OFF
	 *	- Click on Position button (to disable it)
	 * Results:
	 *	- - Survey Information is displayed in satellite view - Tag, Mode, Time, EQ Mode Active (bold green text), Driver Info, Elapsed time, Remaining Time, Zoom level, Surveyor and analyzer info
	 *	- Display should show FOV, windrose and concentration chart options only.
	 *	- Non-Picarro user should not see indication and Lisa information
	 *	- Wind rose, concentration chart, Car position, Indication, FoV  and Breadcrumb is present on map in satellite view
	 *	- Map is not centered on car's position
	 *	- LISA,Indication Assets, Boundaries data will not be displayed on map in satellite view
	 */
	@Ignore /* Depend on US4438 */
	public void TC1067_() throws Exception{
		Log.info("\nTestcase - TC1067_\n");
	}
}