package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.CUSUSERROLEDR;
import static surveyor.scommon.source.SurveyorConstants.REGBASEUSERNAME;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import common.source.FunctionUtil;
import common.source.Log;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
public class ObserverViewPageTest_Analytics2 extends BaseMapViewTest {

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

	public ObserverViewPageTest_Analytics2() throws IOException {
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
	 * Test Case ID: TC2346_OnlyPeaksAboveSurveyMinAmpAppearInAnalyticsSurveyMode
	 * Test Description: - Observer View - Only peaks above Survey Min Amplitude appear in Analytics Survey mode
	 * Script:
	 *	- - Log into UI as Picarro Admin
	 *	- Navigate to the Locations page
	 *	- Select a customer location and click Edit
	 *	- Set the Survey Min Amplitude level to a certain level (ex. 0.4)
	 *	- Click OK
	 *	- Have driver start an Analytics Survey
	 *	- Have driver drive to an area where indications are reliably found
	 *	- Click Display
	 *	- Turn on Indications and indications sub-options
	 * Results:
	 *	- - User can see Analytics survey going on in Observer View
	 *	- Only indications above Survey Min AMplitude level will appear in Observer View
	 */
	@Test
	public void TC2346_OnlyPeaksAboveSurveyMinAmpAppearInAnalyticsSurveyMode() throws Exception{
		Log.info("\nTestcase - TC2346_OnlyPeaksAboveSurveyMinAmpAppearInAnalyticsSurveyMode\n");

		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
		manageLocationPageActions.open(EMPTY, NOTSET);
		String surMinAmp = "0.4";
		manageLocationPageActions.getManageLocationsPage().editSurveyMinAmplitude(customerName,locationName,surMinAmp);
		getHomePage().logout();
		getLoginPage().loginNormalAs(userName, userPassword);

		/* Step 1. setup analyzer configuration */
		updateAnalyzerConfiguration(testEnvironmentAction, analyzerName, analyzerSharedKey, analyticSurveyRowId);

		/* Step 2: startAnalyzerSurvey */
		startAnalyzerSurvey(testEnvironmentAction, driverViewPageAction, db3DefnFile, db3File, analyticSurveyRowId, ONE_SECOND);

		/* Step 3: ObserverView and verifications */
		loginPageActionList.get(0).open(EMPTY, NOTSET);
		loginPageActionList.get(0).getLoginPage().loginNormalAs(observerName, userPassword);

		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzerName, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		testEnvironmentAction.idleForSeconds("300", NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifySurveyAmplitudes(surMinAmp, NOTSET));

		/* Step 4: stopAnalyzerSurvey */
		stopAnalyzerSurvey(testEnvironmentAction, driverViewPageAction,analyzerName, analyzerSharedKey, surveyorName);
		testEnvironmentAction.stopAnalyzer(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC2348_ObserverViewAnalyticsSurveyActiveIsDisplayed
	 * Test Description: - Observer View shows indication that Analytics Survey is being run
	 * Script:
	 *	- - Have driver log into tablet but not start survey
	 *	- Log into UI and navigate to Observer View for above analyzer
	 *	- Have driver start Analytics Survey
	 *	- Check Observer View
	 * Results:
	 *	- - In Observer View, "Analytics Survey Active" appears in bold green font at top left of map and "Mode: Analytics"
	 *		appears in the Survey Information block at top right
	 */
	@Test
	public void TC2348_ObserverViewAnalyticsSurveyActiveIsDisplayed() throws Exception{
		Log.info("\nTestcase - TC2348_ObserverViewAnalyticsSurveyActiveIsDisplayed\n");

		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);

		loginPageActionList.get(0).open(EMPTY, NOTSET);
		loginPageActionList.get(0).getLoginPage().loginNormalAs(observerName, userPassword);

		/* Step 1. setup analyzer configuration */
		updateAnalyzerConfiguration(testEnvironmentAction, analyzerName, analyzerSharedKey, analyticSurveyRowId);

		/* Step 2: startAnalyzerSurvey */
		startAnalyzerSurvey(testEnvironmentAction, driverViewPageAction, db3DefnFile, db3File, analyticSurveyRowId, ONE_SECOND);

		/* Step 3: ObserverView and verifications */
		loginPageActionList.get(0).getLoginPage().open();
		loginPageActionList.get(0).getLoginPage().loginNormalAs(observerName, userPassword);

		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzerName, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifyObserverViewPageIsOpened(EMPTY, NOTSET));
		observerViewPageActionList.get(0).getObserverViewPage().waitForAJAXCallsToComplete();
		assertTrue(observerViewPageActionList.get(0).verifyCorrectSurveyActiveMessageIsShownOnMap(EMPTY, NOTSET));

		/* Step 4: stopAnalyzerSurvey */
		stopAnalyzerSurvey(testEnvironmentAction, driverViewPageAction,analyzerName, analyzerSharedKey, surveyorName);
		testEnvironmentAction.stopAnalyzer(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC2351_ObserverViewAnalyticsSurveyModeHasNoCaptureOrRefGasFeatures
	 * Test Description: - Analytics Survey mode will not have the option of running Ref Gas Measurements or Captures
	 * Script:
	 *	- - Have driver begin an Analytics Survey
	 *	- Log into the UI
	 *	- Click on the Online link for the Surveyor running an Analytics survey
	 *	- Click on Display
	 * Results:
	 *	- -  User will be navigated to Observer View for the selected Surveyor
	 *	- The Display menu will not have an option for Analysis Results
	 */
	@Test
	public void TC2351_ObserverViewAnalyticsSurveyModeHasNoCaptureOrRefGasFeatures() throws Exception{
		Log.info("\nTestcase - TC2351_ObserverViewAnalyticsSurveyModeHasNoCaptureOrRefGasFeatures\n");

		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);

		/* Step 1. setup analyzer configuration */
		updateAnalyzerConfiguration(testEnvironmentAction, analyzerName, analyzerSharedKey, analyticSurveyRowId);

		/* Step 2: startAnalyzerSurvey */
		startAnalyzerSurvey(testEnvironmentAction, driverViewPageAction, db3DefnFile, db3File, analyticSurveyRowId, ONE_SECOND);

		/* Step 3: ObserverView and verifications */
		loginPageActionList.get(0).open(EMPTY, NOTSET);
		loginPageActionList.get(0).getLoginPage().loginNormalAs(observerName, userPassword);

		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzerName, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifyObserverViewPageIsOpened(EMPTY, NOTSET));
		observerViewPageActionList.get(0).getObserverViewPage().waitForAJAXCallsToComplete();
		observerViewPageActionList.get(0).getObserverViewPage().clickDisplayButton();
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchIsotopicAnalysisButtonIsNotVisible(EMPTY, NOTSET));

		/* Step 4: stopAnalyzerSurvey */
		stopAnalyzerSurvey(testEnvironmentAction, driverViewPageAction,analyzerName, analyzerSharedKey, surveyorName);
		testEnvironmentAction.stopAnalyzer(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC2356_ObserverViewNoFieldNotesOptionForAnalyticsSurveys
	 * Test Description: - Observer View - No Field Notes option for Analytics Surveys
	 * Script:
	 *	- - Have driver log into the tablet as Picarro Admin and start Analytics Survey
	 *	- Have driver collect several indications
	 *	- On observer View, click on one or more of the indications
	 *	- Click on Display
	 * Results:
	 *	- - Upon clicking an indication, a popup should appear with details about that leak.
	 *		The popup should not have a button for adding Field Notes
	 *	- The Display menu does not have an option for Field Notes
	 */
	@Test
	public void TC2356_ObserverViewNoFieldNotesOptionForAnalyticsSurveys() throws Exception{
		Log.info("\nTestcase - TC2356_ObserverViewNoFieldNotesOptionForAnalyticsSurveys\n");

		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);

		/* Step 1. setup analyzer configuration */
		updateAnalyzerConfiguration(testEnvironmentAction, analyzerName, analyzerSharedKey, analyticSurveyRowId);

		/* Step 2: startAnalyzerSurvey */
		startAnalyzerSurvey(testEnvironmentAction, driverViewPageAction, db3DefnFile, db3File, analyticSurveyRowId, ONE_SECOND);

		/* Step 3: ObserverView and verifications */
		loginPageActionList.get(0).open(EMPTY, NOTSET);
		loginPageActionList.get(0).getLoginPage().loginNormalAs(observerName, userPassword);

		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzerName, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().setZoomLevel(zoomLevelForIndication);
		testEnvironmentAction.idleForSeconds("200", NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().clickDisplayButton();
		assertFalse(observerViewPageActionList.get(0).verifyDisplaySwitchNotesButtonIsVisible(EMPTY, NOTSET));
		observerViewPageActionList.get(0).getObserverViewPage().clickDisplayButton();
		testEnvironmentAction.stopReplay(EMPTY, NOTSET);
		testEnvironmentAction.idleForSeconds("5", NOTSET);
		if(observerViewPageActionList.get(0).clickOnFirst3300IndicationShownOnMap(null, NOTSET)){
			observerViewPageActionList.get(0).waitForFeatureInfoPopupToOpen(EMPTY, NOTSET);
			assertTrue(observerViewPageActionList.get(0).getObserverViewPage().isSurveyModeDialogShown());
			observerViewPageActionList.get(0).getObserverViewPage().clickDisplayButton();
			testEnvironmentAction.idleForSeconds("5", NOTSET);
			assertFalse(observerViewPageActionList.get(0).verifyDisplaySwitchNotesButtonIsVisible(EMPTY, NOTSET));
			observerViewPageActionList.get(0).getObserverViewPage().clickDisplayButton();
		}
		observerViewPageActionList.get(0).getObserverViewPage().clickDisplayButton();
		testEnvironmentAction.idleForSeconds("5", NOTSET);
		assertFalse(observerViewPageActionList.get(0).verifyDisplaySwitchNotesButtonIsVisible(EMPTY, NOTSET));
		observerViewPageActionList.get(0).getObserverViewPage().clickDisplayButton();
		/* Step 4: stopAnalyzerSurvey */
		// post stopping replay, if method execution does not occur in allocated timeframe 'heartbeat is not received' error event can be thrown.
		// which could cause ElementNotFound exception to get thrown.
		FunctionUtil.warnOnError(() -> stopAnalyzerSurvey(testEnvironmentAction, driverViewPageAction,analyzerName, analyzerSharedKey, surveyorName));
		testEnvironmentAction.stopAnalyzer(EMPTY, NOTSET);
	}

}