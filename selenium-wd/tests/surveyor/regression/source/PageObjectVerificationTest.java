package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONECT;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.CryptoUtility;
import common.source.DateUtility;
import common.source.Log;
import common.source.OLMapUtility;
import common.source.TestSetup;
import common.source.OLMapUtility.BreadcrumbColor;
import common.source.OLMapUtility.IconColor;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataprovider.UserDataProvider;
import surveyor.scommon.source.Coordinates;
import surveyor.scommon.source.LatLongSelectionControl;
import surveyor.scommon.source.LatLongSelectionControl.ControlMode;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.PreferencesPage;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.DriverViewPage.CloudCover;
import surveyor.scommon.source.DriverViewPage.SolarRadiation;
import surveyor.scommon.source.DriverViewPage.SurveyTime;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.DriverViewPage.Wind;
import surveyor.scommon.source.EqReportsPage;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;


@RunWith(SurveyorTestRunner.class)
public class PageObjectVerificationTest extends SurveyorBaseTest {

	private static final String SURVEYOR_DB3 = "Surveyor.db3";
	private static final String REPLAY_DB3_DEFN_FILE = "replay-db3.defn";
	
	private static final int X_OFFSET = 100;
	private static final int Y_OFFSET = 100;
	private static final int RECT_WIDTH = 100;
	private static final int RECT_HEIGHT = 100;
	private static final String CANVAS_X_PATH = "//*[@id=\"map\"]/div/canvas";

	private static ComplianceReportsPage complianceReportsPage = null;
	private static DriverViewPage driverViewPage = null;
	private static LatLongSelectionControl latLongSelectionControl = null;
	private static ManageLocationsPage manageLocationsPage = null;
	private static EqReportsPage eqReportsPage = null;
	private static PreferencesPage preferencesPage;

	public PageObjectVerificationTest() {
		complianceReportsPage = new ComplianceReportsPage(driver, getBaseURL(), getTestSetup());
		PageFactory.initElements(driver, complianceReportsPage);

		manageLocationsPage = new ManageLocationsPage(driver, getBaseURL(), getTestSetup());
		PageFactory.initElements(driver, manageLocationsPage);

		driverViewPage = new DriverViewPage(driver, getTestSetup(), getBaseURL());
		PageFactory.initElements(driver, driverViewPage);

		latLongSelectionControl = new LatLongSelectionControl(driver);
		PageFactory.initElements(driver, latLongSelectionControl);

		eqReportsPage = new EqReportsPage(driver, getBaseURL(), getTestSetup());
		PageFactory.initElements(driver, eqReportsPage);

		preferencesPage = new PreferencesPage(driver, getBaseURL(), getTestSetup());
		PageFactory.initElements(driver, preferencesPage);
	}

	/**
	 * Test Case ID: <None>
	 * NOTE: This is a test method to demonstrate the usage of LatLongSelector Control for lat/long selection.
	 *  Actual automation tests that use LatLongSelector can use this method as a reference.
	 */
	@Test
	public void ReferenceOnly_LatLongSelectorControl_LatLongAreaSelectorTest() {
		System.out.format("\nRunning ReferenceOnly_LatLongSelectorControl_LatLongAreaSelectorTest... \n");

		complianceReportsPage.login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		complianceReportsPage.open();
		complianceReportsPage.waitForPageLoad();

		complianceReportsPage.clickOnNewComplianceReportBtn();
		complianceReportsPage.openCustomBoundarySelector();

		latLongSelectionControl.waitForModalDialogOpen()
		.switchMode(ControlMode.MapInteraction)
		.waitForMapImageLoad()
		.drawSelectorRectangle(CANVAS_X_PATH, X_OFFSET, Y_OFFSET, RECT_WIDTH, RECT_HEIGHT)
		.switchMode(ControlMode.Default)
		.clickOkButton();
	}

	/**
	 * Test Case ID: <None>
	 * NOTE: This is a test method to demonstrate the usage of LatLongSelector Control for Customer Boundary selection.
	 *  Actual automation tests that use LatLongSelector can use this method as a reference.
	 */
	@Test
	public void ReferenceOnly_LatLongSelectorControl_CustomerLatLongSelectorTest() {
		System.out.format("\nRunning ReferenceOnly_LatLongSelectorControl_CustomerLatLongSelectorTest... \n");

		complianceReportsPage.login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		complianceReportsPage.open();
		complianceReportsPage.waitForPageLoad();

		complianceReportsPage.clickOnNewComplianceReportBtn();
		complianceReportsPage.waitForNewPageLoad();
		complianceReportsPage.openCustomerBoundarySelector();

		String boundaryName = "Level 2-AA";
		latLongSelectionControl.waitForModalDialogOpen()
			.switchMode(ControlMode.MapInteraction)
			.waitForMapImageLoad()
			.selectCustomerBoundaryType(ReportsCompliance.CustomerBoundaryFilterType.SmallBoundary.toString())
			.setCustomerBoundaryName(boundaryName)
			.switchMode(ControlMode.Default)
			.clickOkButton()
			.waitForModalDialogToClose();
	}

	/**
	 * Test Case ID: <None>
	 * NOTE: This is a test method to demonstrate the usage of LatLongSelector Control for Lat/Long selection.
	 *  Actual automation tests that use LatLongSelector can use this method as a reference.
	 */
	@Test
	public void ReferenceOnly_LatLongSelectorControl_LatLongSelectorTest() {
		System.out.format("\nRunning ReferenceOnly_LatLongSelectorControl_LatLongSelectorTest... \n");

		manageLocationsPage.login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		manageLocationsPage.open();
		manageLocationsPage.waitForPageLoad();
		manageLocationsPage.clickOnAddNewLocationBtn();
		manageLocationsPage.waitForNewPageLoad();

		manageLocationsPage.clickOnLatLongSelectorBtn();

		latLongSelectionControl.waitForModalDialogOpen()
		.switchMode(ControlMode.MapInteraction)
		.waitForMapImageLoad()
		.selectLatLong(CANVAS_X_PATH, X_OFFSET, Y_OFFSET)
		.switchMode(ControlMode.Default)
		.clickOkButton()
		.waitForModalDialogToClose();

		String locationLatitudeText = manageLocationsPage.getLocationLatitudeText();
		Log.info("locationLatitudeText=" + locationLatitudeText);
		assertTrue(!locationLatitudeText.isEmpty());

		String locationLongitudeText = manageLocationsPage.getLocationLongitudeText();
		Log.info("locationLongitudeText=" + locationLongitudeText);
		assertTrue(!locationLongitudeText.isEmpty());
	}

	/**
	 * Test Case ID: <None>
	 * NOTE: This is a test method to test EQ methods in driver view.
	 */
	@Test
	public void ReferenceOnly_SimulatorTest_TestEQMethods() {
		Log.info("Running ReferenceOnly_SimulatorTest_TestEQMethods");

		getLoginPage().open();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		TestSetup.replayDB3Script(REPLAY_DB3_DEFN_FILE, SURVEYOR_DB3);

		driverViewPage.open();
		driverViewPage.waitForPageLoad();
		driverViewPage.waitForConnectionComplete();

		Log.info("Clicking on MODE button");
		driverViewPage.clickModeButton();
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());

		assertTrue(driverViewPage.isStartEQSurveyButtonEnabled());
		assertTrue(driverViewPage.isStartEQSurveyButtonVisible());

		// Start EQ Driving Survey. Survey Time: Day, Solar Radiation: Overcast, Wind: Calm
		String tag = getTestSetup().getFixedSizePseudoRandomString(13) + "_TEST";
		driverViewPage.startEQDrivingSurvey(tag, SurveyTime.Day, SolarRadiation.Overcast, Wind.Calm, CloudCover.LessThan50);

		// Let the survey run for a few seconds.
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());

		assertTrue(driverViewPage.isEQModeDialogShown());
		assertTrue(driverViewPage.verifyEQModeDialogMessageEquals(Resources.getResource(ResourceKeys.Dialog_EQModeActive)));
		
		Log.info("Clicking on MODE button");
		driverViewPage.clickModeButton();
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());

		assertTrue(!driverViewPage.isStartEQSurveyButtonVisible());
		assertTrue(driverViewPage.isStopDrivingSurveyButtonVisible());

		TestSetup.stopAnalyzer();
	}

	/**
	 * Test Case ID: <None>
	 * NOTE: This is a test method to test buttons visibility on driver view.
	 */
	@Test
	public void ReferenceOnly_SimulatorTest_ButtonsVisibleVerification() {
		Log.info("Running ReferenceOnly_SimulatorTest_CarIconColorVerification");

		getLoginPage().open();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		TestSetup.replayDB3Script(REPLAY_DB3_DEFN_FILE, SURVEYOR_DB3);

		driverViewPage.open();
		driverViewPage.waitForPageLoad();
		driverViewPage.waitForConnectionComplete();

		Log.info("Clicking on MODE button");
		driverViewPage.clickModeButton();
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());

		assertTrue(driverViewPage.isStartSurveyButtonVisible());
		assertTrue(driverViewPage.isSystemShutdownButtonVisible());

		// Start Driving Survey. Survey Time: Day, Solar Radiation: Overcast, Wind: Calm, Survey Type: Standard 
		String tag = getTestSetup().getFixedSizePseudoRandomString(13) + "_TEST";
		driverViewPage.startDrivingSurvey(tag, SurveyTime.Day, SolarRadiation.Overcast, Wind.Calm, CloudCover.LessThan50, SurveyType.Standard);

		// Let the survey run for a few seconds.
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());

		Log.info("Clicking on MODE button");
		driverViewPage.clickModeButton();
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());

		assertTrue(!driverViewPage.isStartSurveyButtonVisible());
		assertTrue(driverViewPage.isStopDrivingSurveyButtonVisible());

		TestSetup.stopAnalyzer();
	}

	/**
	 * Test Case ID: <None>
	 * NOTE: This is a test method to test car icon color is showing correctly.
	 */
	@Test
	public void ReferenceOnly_SimulatorTest_CarIconColorVerification() {
		Log.info("Running ReferenceOnly_SimulatorTest_CarIconColorVerification");

		getLoginPage().open();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		TestSetup.replayDB3Script(REPLAY_DB3_DEFN_FILE, SURVEYOR_DB3);

		driverViewPage.open();
		driverViewPage.waitForPageLoad();
		driverViewPage.waitForConnectionComplete();

		Log.info("Clicking on MODE button");
		driverViewPage.clickModeButton();
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());

		// Start Driving Survey. Survey Time: Day, Solar Radiation: Overcast, Wind: Calm, Survey Type: Standard 
		String tag = getTestSetup().getFixedSizePseudoRandomString(13) + "_TEST";
		driverViewPage.startDrivingSurvey(tag, SurveyTime.Day, SolarRadiation.Overcast, Wind.Calm, CloudCover.LessThan50, SurveyType.Standard);

		// Let the survey run for a few seconds.
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());
		// check car icon shown is RED
		OLMapUtility mapUtility = new OLMapUtility(this.driver);
		assertTrue(mapUtility.isCrossHairIconShownOnMap(IconColor.Red));

		Log.info("Clicking on MODE button");
		driverViewPage.clickModeButton();
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());
		driverViewPage.stopDrivingSurvey();

		// Wait for a few seconds.
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());
		// check car icon shown is GRAY
		assertTrue(mapUtility.isCrossHairIconShownOnMap(IconColor.Gray));

		TestSetup.stopAnalyzer();
	}

	/**
	 * Test Case ID: <None>
	 * NOTE: This is a test method to test breadcrumb color is showing correctly.
	 */
	@Test
	public void ReferenceOnly_SimulatorTest_BreadcrumbColorVerification() {
		Log.info("Running ReferenceOnly_SimulatorTest_BreadcrumbColorVerification");

		getLoginPage().open();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		TestSetup.replayDB3Script(REPLAY_DB3_DEFN_FILE, SURVEYOR_DB3);

		driverViewPage.open();
		driverViewPage.waitForPageLoad();
		driverViewPage.waitForConnectionComplete();

		Log.info("Clicking on MODE button");
		driverViewPage.clickModeButton();
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());

		// Start Driving Survey. Survey Time: Day, Solar Radiation: Overcast, Wind: Calm, Survey Type: Standard 
		String tag = getTestSetup().getFixedSizePseudoRandomString(13) + "_TEST";
		driverViewPage.startDrivingSurvey(tag, SurveyTime.Day, SolarRadiation.Overcast, Wind.Calm, CloudCover.LessThan50, SurveyType.Standard);

		// Let the survey run for a few seconds.
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());
		// check breadcrumb color shown is BLUE
		OLMapUtility mapUtility = new OLMapUtility(this.driver);
		assertTrue(mapUtility.isBreadcrumbShownOnMap(BreadcrumbColor.Blue));

		Log.info("Clicking on MODE button");
		driverViewPage.clickModeButton();
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());
		driverViewPage.stopDrivingSurvey();

		// Wait for a few seconds.
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());
		// check breadcrumb color shown is GRAY
		assertTrue(mapUtility.isBreadcrumbShownOnMap(BreadcrumbColor.Gray));

		TestSetup.stopAnalyzer();
	}

	/**
	 * Test Case ID: <None>
	 * NOTE: This is a test method to test the DateTime.isTimeTickingBackward & isTimeTickingForward methods.
	 * @throws InterruptedException 
	 */
	@Test
	public void ReferenceOnly_SimulatorTest_DatetimeTicksTest() throws InterruptedException {
		Log.info("Running ReferenceOnly_SimulatorTest_DatetimeTicksTest");

		getLoginPage().open();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		TestSetup.replayDB3Script(REPLAY_DB3_DEFN_FILE, SURVEYOR_DB3);

		driverViewPage.open();
		driverViewPage.waitForPageLoad();
		driverViewPage.waitForConnectionComplete();

		Log.info("Clicking on MODE button");
		driverViewPage.clickModeButton();
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());

		// Start Driving Survey. Survey Time: Day, Solar Radiation: Overcast, Wind: Calm, Survey Type: Standard 
		String tag = getTestSetup().getFixedSizePseudoRandomString(13) + "_TEST";
		driverViewPage.startDrivingSurvey(tag, SurveyTime.Day, SolarRadiation.Overcast, Wind.Calm, CloudCover.LessThan50, SurveyType.Standard);

		// Open Header box and check the survey information.
		driverViewPage.clickHeaderInfoBox();
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());

		Log.info("ELAPSED:[" + driverViewPage.getTimeElapsedLabelText() + "]");
		assertTrue(DateUtility.isTimeTickingForward(driverViewPage.getTimeElapsedLabel()));

		Log.info("REMAINING:[" + driverViewPage.getTimeRemainingLabelText() + "]");
		assertTrue(DateUtility.isTimeTickingBackward(driverViewPage.getTimeRemainingLabel()));

		Log.info("TIME:[" + driverViewPage.getTimeLabelText() + "]");
		assertTrue(DateUtility.isTimeTickingForward(driverViewPage.getTimeLabel()));

		Log.info("Clicking on MODE button");
		driverViewPage.clickModeButton();
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());
		driverViewPage.stopDrivingSurvey();

		TestSetup.stopAnalyzer();
	}


	/**
	 * Test Case ID: <None>
	 * NOTE: This is a test method to demonstrate the usage of OLMapUtility functions.
	 *  Actual automation tests that use OLMapUtility can use this method as a reference.
	 */
	@Test
	public void ReferenceOnly_SimulatorTest_OLMapFunctionality() {
		Log.info("Running ReferenceOnly_SimulatorTest_OLMapFunctionality");

		getLoginPage().open();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		TestSetup.replayDB3Script(REPLAY_DB3_DEFN_FILE, SURVEYOR_DB3);

		driverViewPage.open();
		driverViewPage.waitForPageLoad();
		driverViewPage.waitForConnectionComplete();

		Log.info("Clicking on MODE button");
		driverViewPage.clickModeButton();
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());

		// Start Driving Survey. Survey Time: Day, Solar Radiation: Overcast, Wind: Calm, Survey Type: Standard 
		String tag = getTestSetup().getFixedSizePseudoRandomString(13) + "_TEST";
		driverViewPage.startDrivingSurvey(tag, SurveyTime.Day, SolarRadiation.Overcast, Wind.Calm, CloudCover.LessThan50, SurveyType.Standard);

		// Zoom out twice so that the indications will still keep showing in the view.
		driverViewPage.clickZoomOutButton();
		driverViewPage.clickZoomOutButton();

		// Let the test run for a few seconds.
		getTestSetup().slowdownInSeconds(5 * getTestSetup().getSlowdownInSeconds());

		// Stopping the replay before capturing OLMapUtility objects.
		// This is necessary to click on correct co-ordinates.
		Log.info("Stopping replay...");
		TestSetup.stopReplay();

		// Call the various OLMapUtility methods.
		OLMapUtility mapUtility = new OLMapUtility(driver);

		String mapCanvasXPath = "//*[@id='map']/div/canvas";
		boolean clickFirstIndicationOnMap = mapUtility.clickFirstIndicationOnMap(mapCanvasXPath);
		Log.info("clickFirstIndicationOnMap = " + clickFirstIndicationOnMap);

		driverViewPage.waitForFieldNotesDialogToOpen();

		Log.info("Entering dummy text in field notes");
		String fieldNote = "Dummy field note";
		driverViewPage.setFieldNotesTextField(fieldNote);

		Log.info("Clicking on Field notes Save button");
		driverViewPage.clickFieldNotesSaveButton();

		Log.info("Waiting for field notes dialog to close");
		driverViewPage.waitForFieldNotesDialogToClose();

		boolean isFieldNotesShown = mapUtility.isFieldNoteShown(fieldNote);
		Log.info("isFieldNotesShown = " + isFieldNotesShown);

		boolean chartDataShowingOnMap = mapUtility.isConcentrationChartDataShowingOnMap();
		Log.info("chartDataShowingOnMap = " + chartDataShowingOnMap);

		boolean indicationsShownOnMap = mapUtility.isIndicationsShownOnMap();
		Log.info("indicationsShownOnMap = " + indicationsShownOnMap);

		boolean breadcrumbShownOnMap = mapUtility.isBreadcrumbShownOnMap();
		Log.info("breadcrumbShownOnMap = " + breadcrumbShownOnMap);

		boolean fovShownOnMap = mapUtility.isFOVShownOnMap();
		Log.info("fovShownOnMap = " + fovShownOnMap);

		boolean lisaShownOnMap = mapUtility.isLISAShownOnMap();
		Log.info("lisaShownOnMap = " + lisaShownOnMap);

		boolean crossHairIconShownOnMap = mapUtility.isCrossHairIconShownOnMap(IconColor.Gray);
		Log.info("[GRAY] crossHairIconShownOnMap = " + crossHairIconShownOnMap);

		crossHairIconShownOnMap = mapUtility.isCrossHairIconShownOnMap(IconColor.Red);
		Log.info("[RED] crossHairIconShownOnMap = " + crossHairIconShownOnMap);

		crossHairIconShownOnMap = mapUtility.isCrossHairIconShownOnMap(IconColor.White);
		Log.info("[WHITE] crossHairIconShownOnMap = " + crossHairIconShownOnMap);

		Integer linksCount = mapUtility.getIndicationsLinksCount();
		Log.info("INDICATIONS linksCount = " + linksCount);

		Integer nodesCount = mapUtility.getIndicationsNodesCount();
		Log.info("INDICATIONS nodesCount = " + nodesCount);

		String nodesText = mapUtility.getIndicationsNodesText();
		Log.info("INDICATIONS nodesText = " + nodesText);

		Log.info("Getting Asset Coordinates...");
		List<Object> assetCoordinates = mapUtility.getAssetCoordinates();
		if (assetCoordinates != null)
		{
			Log.info("assetCoordinates NOT NULL. COUNT=" + assetCoordinates.size());
			Log.info("Coordinates are:");
			for (Object obj : assetCoordinates) {
				Log.info(obj.toString());
			}
		}

		Log.info("Getting Boundary Coordinates...");
		List<Object> boundaryCoordinates = mapUtility.getBoundariesCoordinates();
		if (boundaryCoordinates != null)
		{
			Log.info("boundaryCoordinates NOT NULL. COUNT=" + boundaryCoordinates.size());
			Log.info("Coordinates are:");
			for (Object obj : boundaryCoordinates) {
				Log.info(obj.toString());
			}
		}

		Log.info("Getting BreadCrumb Coordinates...");
		List<Object> breadcrumbCoordinates = mapUtility.getBreadCrumbCoordinates();
		if (breadcrumbCoordinates != null)
		{
			Log.info("breadcrumbCoordinates NOT NULL. COUNT=" + breadcrumbCoordinates.size());
			Log.info("Coordinates are:");
			for (Object obj : breadcrumbCoordinates) {
				Log.info(obj.toString());
			}
		}

		Log.info("Getting ConcentrationChart Image Data...");
		List<Object> concentrationChartData = mapUtility.getConcentrationChartImageData();
		if (concentrationChartData != null)
		{
			Log.info("concentrationChartData NOT NULL. COUNT=" + concentrationChartData.size());
			Log.info("Data points are:");
			for (Object obj : concentrationChartData) {
				Log.info(obj.toString());
			}
		}

		Log.info("Getting FovCoordinates Coordinates...");
		List<Object> fovCoordinates = mapUtility.getFOVCoordinates();
		if (fovCoordinates != null)
		{
			Log.info("fovCoordinates NOT NULL. COUNT=" + fovCoordinates.size());
			Log.info("Coordinates are:");
			for (Object obj : fovCoordinates) {
				Log.info(obj.toString());
			}
		}

		Log.info("Getting LISA Coordinates...");
		List<Object> lisaCoordinates = mapUtility.getLISACoordinates();
		if (lisaCoordinates != null)
		{
			Log.info("lisaCoordinates NOT NULL. COUNT=" + lisaCoordinates.size());
			Log.info("Coordinates are:");
			for (Object obj : lisaCoordinates) {
				Log.info(obj.toString());
			}
		}

		Log.info("Clicking on MODE button");
		driverViewPage.clickModeButton();
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());

		driverViewPage.stopDrivingSurvey();

		TestSetup.stopAnalyzer();
	}

	/**
	 * Test Case ID: <None>
	 * NOTE: This is a test method to demonstrate the usage of LatLongSelector Control for segment selection.
	 *  Actual automation tests that use EQ select area can use this method as a reference.
	 */
	@Test
	public void ReferenceOnly_EQSegmentSelectorTest() {
		System.out.format("\nRunning ReferenceOnly_EQSegmentSelectorTest... \n");

		eqReportsPage.login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		eqReportsPage.open();

		eqReportsPage.clickOnNewEQReportBtn();
		eqReportsPage.waitForNewPageLoad();

		eqReportsPage.getSelectArea().click();

		List <Coordinates> listOfCords = new ArrayList <Coordinates>();
		listOfCords.add(0, new Coordinates(200,200));
		listOfCords.add(1, new Coordinates(220,300));
		listOfCords.add(2, new Coordinates(240,400));

		latLongSelectionControl.waitForModalDialogOpen()
								.switchMode(ControlMode.MapInteraction)
								.waitForMapImageLoad()
								.selectSegment(CANVAS_X_PATH, listOfCords)
								.switchMode(ControlMode.Default)
								.clickOkButton();

		eqReportsPage.waitForPageToLoad();
		assertTrue(eqReportsPage.getEqRptArea().getAttribute("value").contains(Resources.getResource(ResourceKeys.Dialog_LineSelected)));
	}

	/**
	 * Use this test to change the default language for all users in automation to a specific language.
	 * Change the 'cultureString' variable to appropriate dropdown text value to change culture for all
	 * users specified in the dataprovider.
	 */
	@Test
	@UseDataProvider(value = UserDataProvider.DATA_PROVIDER_ALL_USERS, location = UserDataProvider.class)
	public void ChangeDefaultLanguageForAllUsers(String username, String password, 
			String role, String customerName, String customerLocation) {
		Log.info("\nRunning - ChangeDefaultLanguageForAllUsers ...\n");
		
		// Change this value to dropdown culture value in Preferences page to set culture for users. 
		String cultureString = "English (United States)";
		
		password = CryptoUtility.decrypt(password);
		
		getLoginPage().open();
		getLoginPage().loginNormalAs(username, password);

		getHomePage().waitForPageLoad();
		getHomePage().getDropDownLoginUser().click();
		getHomePage().getLinkPreference().click();

		preferencesPage.waitForPageLoad();
		preferencesPage.setSelectedCulture(cultureString);
		preferencesPage.getBtnOk().click();
		getHomePage().waitForPageLoad();
	}
}
