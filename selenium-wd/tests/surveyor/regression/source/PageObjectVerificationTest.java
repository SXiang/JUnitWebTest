package surveyor.regression.source;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
import common.source.OLMapUtility;
import common.source.TestSetup;
import common.source.OLMapUtility.IconColor;
import surveyor.scommon.source.LatLongSelectionControl;
import surveyor.scommon.source.LatLongSelectionControl.ControlMode;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.DriverViewPage.CloudCover;
import surveyor.scommon.source.DriverViewPage.SolarRadiation;
import surveyor.scommon.source.DriverViewPage.SurveyTime;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.DriverViewPage.Wind;

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

	public PageObjectVerificationTest() {
		complianceReportsPage = new ComplianceReportsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, complianceReportsPage);

		manageLocationsPage = new ManageLocationsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageLocationsPage);
		
		driverViewPage = new DriverViewPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver, driverViewPage);
		
		latLongSelectionControl = new LatLongSelectionControl(driver);
		PageFactory.initElements(driver, latLongSelectionControl);
	}

	/**
	 * Test Case ID: <None>
	 * NOTE: This is a test method to demonstrate the usage of LatLongSelector Control for lat/long selection.
	 *  Actual automation tests that use LatLongSelector can use this method as a reference.
	 */
	@Test
	public void ReferenceOnly_LatLongSelectorControl_LatLongAreaSelectorTest() {
		System.out.format("\nRunning ReferenceOnly_LatLongSelectorControl_LatLongAreaSelectorTest... \n");

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();
		complianceReportsPage.waitForPageLoad();

		complianceReportsPage.clickOnNewComplianceReportBtn();
		complianceReportsPage.openLatLongAreaSelector();

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

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();
		complianceReportsPage.waitForPageLoad();

		complianceReportsPage.clickOnNewComplianceReportBtn();
		complianceReportsPage.openCustomerBoundarySelector();
		
		latLongSelectionControl.waitForModalDialogOpen()
		.switchMode(ControlMode.MapInteraction)
		.waitForMapImageLoad()
		.drawSelectorRectangle(CANVAS_X_PATH, X_OFFSET, Y_OFFSET, RECT_WIDTH, RECT_HEIGHT)
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
	
		manageLocationsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
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
	 * NOTE: This is a test method to demonstrate the usage of OLMapUtility functions.
	 *  Actual automation tests that use OLMapUtility can use this method as a reference.
	 */
	@Test
	public void ReferenceOnly_SimulatorTest_OLMapFunctionality() {
		Log.info("Running ReferenceOnly_SimulatorTest_OLMapFunctionality");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		TestSetup.replayDB3Script(REPLAY_DB3_DEFN_FILE, SURVEYOR_DB3);

		driverViewPage.open();
		driverViewPage.waitForPageLoad();
		driverViewPage.waitForConnectionComplete();
		
		Log.info("Clicking on MODE button");
		driverViewPage.clickModeButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		// Start Driving Survey. Survey Time: Day, Solar Radiation: Overcast, Wind: Calm, Survey Type: Standard 
		String tag = testSetup.getFixedSizePseudoRandomString(13) + "_TEST";
		driverViewPage.startDrivingSurvey(tag, SurveyTime.Day, SolarRadiation.Overcast, Wind.Calm, CloudCover.LessThan50, SurveyType.Standard);

		// Zoom out twice so that the indications will still keep showing in the view.
		driverViewPage.clickZoomOutButton();
		driverViewPage.clickZoomOutButton();
		
		// Let the test run for a few seconds.
		testSetup.slowdownInSeconds(5 * testSetup.getSlowdownInSeconds());

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
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		driverViewPage.stopDrivingSurvey();
	}

}
