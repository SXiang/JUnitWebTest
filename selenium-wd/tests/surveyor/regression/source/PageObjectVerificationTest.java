package surveyor.regression.source;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import common.source.OLMapUtility;
import common.source.TestSetup;
import common.source.OLMapUtility.IconColor;
import surveyor.scommon.source.BoundarySelectionControl;
import surveyor.scommon.source.BoundarySelectionControl.ControlMode;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.DriverViewPage.SolarRadiation;
import surveyor.scommon.source.DriverViewPage.SurveyTime;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.DriverViewPage.Wind;

public class PageObjectVerificationTest extends SurveyorBaseTest {
	
	private static final String SURVEYOR_DB3 = "Surveyor.db3";
	private static final String REPLAY_DB3_DEFN_FILE = "replay-db3.defn";

	private static final int RECT_X = 50;
	private static final int RECT_Y = 50;
	private static final int RECT_WIDTH = 100;
	private static final int RECT_HEIGHT = 100;
	private static final String CANVAS_X_PATH = "//*[@id=\"map\"]/div/canvas";

	private static ComplianceReportsPage complianceReportsPage = null;
	private static DriverViewPage driverViewPage = null;
	private static BoundarySelectionControl boundarySelectionControl = null;

	public PageObjectVerificationTest() {
		complianceReportsPage = new ComplianceReportsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, complianceReportsPage);
		
		driverViewPage = new DriverViewPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver, driverViewPage);
		
		boundarySelectionControl = new BoundarySelectionControl(driver);
		PageFactory.initElements(driver, boundarySelectionControl);
	}

	/**
	 * Test Case ID: <None>
	 * NOTE: This is a test method to demonstrate the usage of BoundarySelector Control for lat/long selection.
	 *  Actual automation tests that use BoundarySelector can use this method as a reference.
	 */
	@Test
	public void ReferenceOnly_BoundarySelectorControl_LatLongAreaSelectorTest() {
		System.out.format("\nRunning ReferenceOnly_BoundarySelectorControl_LatLongAreaSelectorTest... \n");

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();
		complianceReportsPage.waitForPageLoad();

		complianceReportsPage.clickOnNewComplianceReportBtn();
		complianceReportsPage.openLatLongAreaSelector();

		boundarySelectionControl.waitForModalDialogOpen()
				.switchMode(ControlMode.MapInteraction)
				.waitForMapImageLoad()
				.drawSelectorRectangle(CANVAS_X_PATH, RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT)
				.switchMode(ControlMode.Default)
				.clickOkButton();
	}

	/**
	 * Test Case ID: <None>
	 * NOTE: This is a test method to demonstrate the usage of BoundarySelector Control for Customer Boundary selection.
	 *  Actual automation tests that use BoundarySelector can use this method as a reference.
	 */
	@Test
	public void ReferenceOnly_BoundarySelectorControl_CustomerBoundarySelectorTest() {
		System.out.format("\nRunning ReferenceOnly_BoundarySelectorControl_CustomerBoundarySelectorTest... \n");

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();
		complianceReportsPage.waitForPageLoad();

		complianceReportsPage.clickOnNewComplianceReportBtn();
		complianceReportsPage.openCustomerBoundarySelector();
		
		boundarySelectionControl.waitForModalDialogOpen()
			.switchMode(ControlMode.MapInteraction)
			.waitForMapImageLoad()
			.drawSelectorRectangle(CANVAS_X_PATH, RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT)
			.switchMode(ControlMode.Default)
			.clickOkButton();
	}

	/**
	 * Test Case ID: <None>
	 * NOTE: This is a test method to demonstrate the usage of OLMapUtility functions.
	 *  Actual automation tests that use OLMapUtility can use this method as a reference.
	 */
	@Test
	public void ReferenceOnly_SimulatorTest_OLMapFunctionality() {
		System.out.println("Running ReferenceOnly_SimulatorTest_OLMapFunctionality");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		TestSetup.replayDB3Script(REPLAY_DB3_DEFN_FILE, SURVEYOR_DB3);

		driverViewPage.open();
		driverViewPage.waitForPageLoad();
		driverViewPage.waitForConnectionComplete();
		
		System.out.println("Clicking on MODE button");
		driverViewPage.clickModeButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		// Start Driving Survey. Survey Time: Day, Solar Radiation: Overcast, Wind: Calm, Survey Type: Standard 
		String tag = testSetup.getFixedSizePseudoRandomString(13) + "_TEST";
		driverViewPage.startDrivingSurvey(tag, SurveyTime.Day, SolarRadiation.Overcast, Wind.Calm, SurveyType.Standard);

		// Let the test run for a few seconds.
		testSetup.slowdownInSeconds(20 * testSetup.getSlowdownInSeconds());
		
		// Call the various OLMapUtility methods.
		OLMapUtility mapUtility = new OLMapUtility(driver);
		boolean chartDataShowingOnMap = mapUtility.isConcentrationChartDataShowingOnMap();
		System.out.println("chartDataShowingOnMap = " + chartDataShowingOnMap);

		boolean indicationsShownOnMap = mapUtility.isIndicationsShownOnMap();
		System.out.println("indicationsShownOnMap = " + indicationsShownOnMap);

		boolean breadcrumbShownOnMap = mapUtility.isBreadcrumbShownOnMap();
		System.out.println("breadcrumbShownOnMap = " + breadcrumbShownOnMap);

		boolean fovShownOnMap = mapUtility.isFOVShownOnMap();
		System.out.println("fovShownOnMap = " + fovShownOnMap);

		boolean lisaShownOnMap = mapUtility.isLISAShownOnMap();
		System.out.println("lisaShownOnMap = " + lisaShownOnMap);

		boolean crossHairIconShownOnMap = mapUtility.isCrossHairIconShownOnMap(IconColor.Gray);
		System.out.println("[GRAY] crossHairIconShownOnMap = " + crossHairIconShownOnMap);

		crossHairIconShownOnMap = mapUtility.isCrossHairIconShownOnMap(IconColor.Red);
		System.out.println("[RED] crossHairIconShownOnMap = " + crossHairIconShownOnMap);
		
		crossHairIconShownOnMap = mapUtility.isCrossHairIconShownOnMap(IconColor.White);
		System.out.println("[WHITE] crossHairIconShownOnMap = " + crossHairIconShownOnMap);
				
		Integer linksCount = mapUtility.getIndicationsLinksCount();
		System.out.println("INDICATIONS linksCount = " + linksCount);

		Integer nodesCount = mapUtility.getIndicationsNodesCount();
		System.out.println("INDICATIONS nodesCount = " + nodesCount);

		String nodesText = mapUtility.getIndicationsNodesText();
		System.out.println("INDICATIONS nodesText = " + nodesText);

		System.out.println("Getting Asset Coordinates...");
		List<Object> assetCoordinates = mapUtility.getAssetCoordinates();
		if (assetCoordinates != null)
		{
			System.out.println("assetCoordinates NOT NULL. COUNT=" + assetCoordinates.size());
			System.out.println("Coordinates are:");
			for (Object obj : assetCoordinates) {
				System.out.println(obj);
			}
		}

		System.out.println("Getting Boundary Coordinates...");
		List<Object> boundaryCoordinates = mapUtility.getBoundariesCoordinates();
		if (boundaryCoordinates != null)
		{
			System.out.println("boundaryCoordinates NOT NULL. COUNT=" + boundaryCoordinates.size());
			System.out.println("Coordinates are:");
			for (Object obj : boundaryCoordinates) {
				System.out.println(obj);
			}
		}

		System.out.println("Getting BreadCrumb Coordinates...");
		List<Object> breadcrumbCoordinates = mapUtility.getBreadCrumbCoordinates();
		if (breadcrumbCoordinates != null)
		{
			System.out.println("breadcrumbCoordinates NOT NULL. COUNT=" + breadcrumbCoordinates.size());
			System.out.println("Coordinates are:");
			for (Object obj : breadcrumbCoordinates) {
				System.out.println(obj);
			}
		}
		
		System.out.println("Getting ConcentrationChart Image Data...");
		List<Object> concentrationChartData = mapUtility.getConcentrationChartImageData();
		if (concentrationChartData != null)
		{
			System.out.println("concentrationChartData NOT NULL. COUNT=" + concentrationChartData.size());
			System.out.println("Data points are:");
			for (Object obj : concentrationChartData) {
				System.out.println(obj);
			}
		}

		System.out.println("Getting FovCoordinates Coordinates...");
		List<Object> fovCoordinates = mapUtility.getFOVCoordinates();
		if (fovCoordinates != null)
		{
			System.out.println("fovCoordinates NOT NULL. COUNT=" + fovCoordinates.size());
			System.out.println("Coordinates are:");
			for (Object obj : fovCoordinates) {
				System.out.println(obj);
			}
		}

		System.out.println("Getting LISA Coordinates...");
		List<Object> lisaCoordinates = mapUtility.getLISACoordinates();
		if (lisaCoordinates != null)
		{
			System.out.println("lisaCoordinates NOT NULL. COUNT=" + lisaCoordinates.size());
			System.out.println("Coordinates are:");
			for (Object obj : lisaCoordinates) {
				System.out.println(obj);
			}
		}

		System.out.println("Clicking on MODE button");
		driverViewPage.clickModeButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		driverViewPage.stopDrivingSurvey();
	}

}
