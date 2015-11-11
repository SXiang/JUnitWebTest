package surveyor.regression.source;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import common.source.TestSetup;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.DriverViewPage.DisplaySwitchType;
import surveyor.scommon.source.DriverViewPage.GisSwitchType;
import surveyor.scommon.source.DriverViewPage.MapSwitchType;
import surveyor.scommon.source.SurveyorBaseTest;

/*
 * **** IMPORTANT ****:
 *  As a convention for detecting Simulator based tests we are using this naming convention for Simulator tests.
 *  Any tests named TC*_SimulatorTest_* will be detected as Simulator based test and will trigger
 *  installation of Simulator pre-requisites before running the test.
 * 
 */
public class DriverViewPageTest extends SurveyorBaseTest {

	private static DriverViewPage driverViewPage;
	private static final String SURVEYOR_DB3 = "Surveyor.db3";
	private static final String SURVEY02_DB3 = "Survey02.db3";
	private static final String INSTR_WARMING_DEFN_FILE = "instr_warming.defn";
	private static final String INSTR_READY_DEFN_FILE = "instr_ready.defn";
	private static final String REPLAY_DB3_DEFN_FILE = "replay-db3.defn";

	public DriverViewPageTest() {
		driverViewPage = new DriverViewPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver, driverViewPage);
	}

	/**
	 * Test Case ID: TC1094_SimulatorTest_VerifyInstrumentReady_PicAdmin
	 * Script: -  	
	 * 1. Log in to driver view 
	 * 2. Click on Mode button 
	 * 3. Click on Display button 
	 * 4. Click on Map button 
	 * 5. Click on GIS button 
	 * 6. Click on Status button
	 * Results: - 
	 * 1. Position button is by default selected and car icon is displayed in grey color. Breadcrumb will be displayed in grey if user is driving the car 
	 * 2. Start Driving Survey and System Shutdown buttons are enabled. Start Isotopic Capture, Reference Bottle Measurement buttons are  not present 
 	 * 3. All the Display options like 8 Hour History, Breadcrumb, Indications, LISA, FoV, Concentration Chart, WindRose, Field Notes and Isotopic Analysis are ON 
	 * 4. Satellite View is by default ON and Map view is OFF 
	 * 5. All Asset types and boundaries level are OFF 
	 * 6. Status is green and all the gauges present on expanding are green
	 */
	@Test
	public void TC1094_SimulatorTest_VerifyInstrumentReady_PicAdmin() {
		System.out.println("Running TC1094_SimulatorTest_VerifyInstrumentReady_PicAdmin");

		TestSetup.replayDB3Script(INSTR_READY_DEFN_FILE);

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		driverViewPage.open();
		driverViewPage.waitForPageLoad();
		
		System.out.println("Clicking on MODE button");
		driverViewPage.clickModeButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		// Verify 1.
		assertTrue(driverViewPage.isPositionButtonGreen());

		// Verify 2.
		assertTrue(driverViewPage.getSystemShutdownButton().isDisplayed());
		assertTrue(driverViewPage.isSystemShutdownButtonEnabled());
		
		System.out.println("Clicking on DISPLAY button");
		driverViewPage.clickDisplayButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		// Verify 3.
		assertTrue(driverViewPage.isDisplaySwitchOn(DisplaySwitchType.EightHourHistory));
		assertTrue(driverViewPage.isDisplaySwitchOn(DisplaySwitchType.ConcentrationChart));
		assertTrue(driverViewPage.isDisplaySwitchOn(DisplaySwitchType.FOVs));
		assertTrue(driverViewPage.isDisplaySwitchOn(DisplaySwitchType.Indications));
		assertTrue(driverViewPage.isDisplaySwitchOn(DisplaySwitchType.IsotopicAnalysis));
		assertTrue(driverViewPage.isDisplaySwitchOn(DisplaySwitchType.Lisas));
		assertTrue(driverViewPage.isDisplaySwitchOn(DisplaySwitchType.Notes));
		assertTrue(driverViewPage.isDisplaySwitchOn(DisplaySwitchType.WindRose));
		
		System.out.println("Clicking on MAP button");
		driverViewPage.clickMapButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		// Verify 4.
		assertTrue(driverViewPage.isMapSwitchOn(MapSwitchType.Satellite));
		assertTrue(driverViewPage.isMapSwitchOff(MapSwitchType.Map));

		System.out.println("Clicking on GIS button");
		driverViewPage.clickGisButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		// Verify 5.
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.BoundariesDistrict));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.BoundariesDistrictPlat));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.MaterialTypeCastIron));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.MaterialTypeCopper));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.MaterialTypeOtherPlastic));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.MaterialTypePEPlastic));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.MaterialTypeProtectedSteel));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.MaterialTypeUnprotectedSteel));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.UseAllBoundaries));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.UseAllPipes));

		assertTrue(driverViewPage.isStatusButtonGreen());

		System.out.println("Clicking on STATUS button");
		driverViewPage.clickStatusButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		// Verify 6.
		assertTrue(driverViewPage.isStatusButtonOpen());
	}
}
