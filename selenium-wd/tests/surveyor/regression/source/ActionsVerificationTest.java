package surveyor.regression.source;

import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.source.SurveyorBaseTest;

public class ActionsVerificationTest extends SurveyorBaseTest {
	private static final String SURVEYOR_NAME = "SimAuto-Surveyor1";
	private static final String ANALYZER_SERIAL_NUMBER = "SimAuto-Analyzer1";
	private LoginPageActions loginPageAction;
	private DriverViewPageActions driverViewPageAction;
	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;
	private static final String AdminUser = "Administrator";
	private static final String AdminPass = "FastLane!911";

	public ActionsVerificationTest() {
		WebDriver webDriver = TestContext.INSTANCE.getDriver();
		TestSetup testSetup = TestContext.INSTANCE.getTestSetup();
		String baseURL = testSetup.getBaseUrl();
		loginPageAction = new LoginPageActions(webDriver, baseURL, testSetup);
		driverViewPageAction = new DriverViewPageActions(webDriver, baseURL,testSetup);
	}
	
	@Test
	public void TC_SimulatorTest_DriverViewInstrumentReady() {
		try {
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(AdminUser + ":" + AdminPass, NOTSET);
			driverViewPageAction.startSimulatorScript(EMPTY, 1);
			driverViewPageAction.open(EMPTY,NOTSET);
			driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);
			
			assertTrue(driverViewPageAction.verifyPositionButtonIsGreen(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyStartSurveyButtonIsEnabled(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifySystemShutdownButtonIsEnabled(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyStartIsotopicCaptureButtonIsEnabled(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyRefBottleMeasButtonIsEnabled(EMPTY,NOTSET));
			
			driverViewPageAction.startDrivingSurvey(EMPTY, 3);
			
			driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);
			assertTrue(driverViewPageAction.verifyStartIsotopicCaptureButtonIsEnabled(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyRefBottleMeasButtonIsEnabled(EMPTY,NOTSET));

			driverViewPageAction.clickOnDisplayButton(EMPTY,NOTSET);
			
			driverViewPageAction.verifyDisplaySwitchIsOn("EightHourHistory",NOTSET);
			driverViewPageAction.verifyDisplaySwitchIsOn("ConcentrationChart",NOTSET);
			driverViewPageAction.verifyDisplaySwitchIsOn("FOVs",NOTSET);
			driverViewPageAction.verifyDisplaySwitchIsOn("Indications",NOTSET);
			driverViewPageAction.verifyDisplaySwitchIsOn("IsotopicAnalysis",NOTSET);
			driverViewPageAction.verifyDisplaySwitchIsOn("Lisas",NOTSET);
			driverViewPageAction.verifyDisplaySwitchIsOn("Notes",NOTSET);
			driverViewPageAction.verifyDisplaySwitchIsOn("WindRose",NOTSET);
			
			driverViewPageAction.clickOnMapButton(EMPTY,NOTSET);
			
			assertTrue(driverViewPageAction.verifyMapSwitchOn("Satellite",NOTSET));
			assertTrue(driverViewPageAction.verifyMapSwitchOff("Map",NOTSET));
			
			driverViewPageAction.clickOnGisButton(EMPTY,NOTSET);

			assertTrue(driverViewPageAction.verifyGisSwitchIsOff("BoundariesDistrict",NOTSET));
			assertTrue(driverViewPageAction.verifyGisSwitchIsOff("BoundariesDistrictPlat",NOTSET));
			assertTrue(driverViewPageAction.verifyGisSwitchIsOff("MaterialTypeCastIron",NOTSET));
			assertTrue(driverViewPageAction.verifyGisSwitchIsOff("MaterialTypeCopper",NOTSET));
			assertTrue(driverViewPageAction.verifyGisSwitchIsOff("MaterialTypeOtherPlastic",NOTSET));
			assertTrue(driverViewPageAction.verifyGisSwitchIsOff("MaterialTypePEPlastic",NOTSET));
			assertTrue(driverViewPageAction.verifyGisSwitchIsOff("MaterialTypeProtectedSteel",NOTSET));
			assertTrue(driverViewPageAction.verifyGisSwitchIsOff("MaterialTypeUnprotectedSteel",NOTSET));
			assertTrue(driverViewPageAction.verifyGisSwitchIsOff("UseAllBoundaries",NOTSET));
			assertTrue(driverViewPageAction.verifyGisSwitchIsOff("UseAllPipes",NOTSET));
			
			assertTrue(driverViewPageAction.verifyStatusButtonIsGreen(EMPTY,NOTSET));
			
			driverViewPageAction.clickOnStatusButton(EMPTY,NOTSET);
			
			assertTrue(driverViewPageAction.verifyStatusButtonIsExpanded(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyPressureButtonIsGreen(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyHBTempButtonIsGreen(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyWBTempButtonIsGreen(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyFlowButtonIsGreen(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyGPSButtonIsGreen(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyAnemometerButtonIsGreen(EMPTY,NOTSET));

		} catch (Exception e) {
			Log.error(e.toString());
		}
	}

	@Test
	public void TC_SimulatorTest_DriverViewStartDrivingSurvey() {
		try {
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(AdminUser + ":" + AdminPass, NOTSET);
			driverViewPageAction.startSimulatorScript(EMPTY, 3);
			driverViewPageAction.open(EMPTY,NOTSET);
			driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);
			
			String surveyTag = testSetup.getFixedSizePseudoRandomString(13) + "_TEST";
			String surveyArg = String.format("%s,%s,%s,%s,%s,%s", 
					surveyTag, "Day", "Overcast", "Calm", "LessThan50", "Standard");
			driverViewPageAction.startDrivingSurvey(surveyArg, 3);
			driverViewPageAction.clickOnHeaderInfoBox(EMPTY,NOTSET);
			
			assertTrue(driverViewPageAction.verifySurveyInfoModeLabelEquals("Mode: Standard",NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoSurveyStatusLabelEquals("Survey Active",NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoStabilityClassLabelEquals("Stability Class: C",NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoTimeElapsedLabelStartsWith("Elapsed: 00:",NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoTimeRemainingLabelStartsWith("Remaining: 0",NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoSurveyorLabelEquals("Surveyor: " + SURVEYOR_NAME + " - " + ANALYZER_SERIAL_NUMBER,NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoZoomLevelLabelEquals("Zoom Level: 19",NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoTagLabelEquals("Tag: " + surveyTag, NOTSET));

			driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);
			
			assertTrue(driverViewPageAction.verifyStopDrivingSurveyButtonIsEnabled(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyStartIsotopicCaptureButtonIsEnabled(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyRefBottleMeasButtonIsEnabled(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifySystemShutdownButtonIsEnabled(EMPTY,NOTSET));

		} catch (Exception e) {
			Log.error(e.toString());
		}
	}

	@Test
	public void TC_SimulatorTest_DriverViewStopDrivingSurvey() {
		try {
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(AdminUser + ":" + AdminPass, NOTSET);
			driverViewPageAction.startSimulatorScript(EMPTY, 3);
			driverViewPageAction.open(EMPTY,NOTSET);
			driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);
			
			assertTrue(driverViewPageAction.verifyStartSurveyButtonIsEnabled(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifySystemShutdownButtonIsEnabled(EMPTY,NOTSET));

			String surveyTag = testSetup.getFixedSizePseudoRandomString(13) + "_TEST";
			String surveyArg = String.format("%s,%s,%s,%s,%s,%s", 
					surveyTag, "Night", "Overcast", "Calm", "MoreThan50", "Standard");

			driverViewPageAction.startDrivingSurvey(surveyArg, 3);
			
			driverViewPageAction.clickOnMapButton(EMPTY,NOTSET);
			driverViewPageAction.turnOnMapView(EMPTY,NOTSET);
			
			driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);

			assertTrue(driverViewPageAction.verifyStopDrivingSurveyButtonIsEnabled(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyStartIsotopicCaptureButtonIsEnabled(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyRefBottleMeasButtonIsEnabled(EMPTY,NOTSET));

			driverViewPageAction.stopDrivingSurvey(EMPTY,NOTSET);
			
			driverViewPageAction.clickOnHeaderInfoBox(EMPTY,NOTSET);
			
			assertTrue(driverViewPageAction.verifySurveyInfoSurveyStatusLabelEquals("Survey Inactive",NOTSET));

		} catch (Exception e) {
			Log.error(e.toString());
		}
	}

	@Test
	public void TC_SimulatorTest_TC1147_DriverViewSurveyVerification() {
		try {
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(AdminUser + ":" + AdminPass, NOTSET);
			driverViewPageAction.startSimulatorScript(EMPTY,3);
			driverViewPageAction.open(EMPTY,NOTSET);
			driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);
			driverViewPageAction.startDrivingSurvey(EMPTY, 3);
			driverViewPageAction.clickOnMapButton(EMPTY,NOTSET);
			driverViewPageAction.turnOnMapView(EMPTY,NOTSET);
			
			driverViewPageAction.clickOnDisplayButton(EMPTY,NOTSET);
			driverViewPageAction.turnOnLisas(EMPTY,NOTSET);
			driverViewPageAction.turnOnNotes(EMPTY,NOTSET);
			driverViewPageAction.turnOffEightHourHistory(EMPTY,NOTSET);
			driverViewPageAction.turnOffConcentrationChart(EMPTY,NOTSET);
			driverViewPageAction.turnOffFOVs(EMPTY,NOTSET);
			driverViewPageAction.turnOffIndications(EMPTY,NOTSET);
			driverViewPageAction.turnOffIsotopicAnalysis(EMPTY,NOTSET);
			driverViewPageAction.turnOffWindRose(EMPTY,NOTSET);
			
			driverViewPageAction.clickOnGisButton(EMPTY,NOTSET);
			driverViewPageAction.turnOffBoundariesDistrict(EMPTY,NOTSET);
			driverViewPageAction.turnOffBoundariesDistrictPlat(EMPTY,NOTSET);
			driverViewPageAction.turnOffMaterialTypeCopper (EMPTY,NOTSET);
			driverViewPageAction.turnOffMaterialTypeCastIron(EMPTY,NOTSET);
			driverViewPageAction.turnOffMaterialTypeOtherPlastic(EMPTY,NOTSET);
			driverViewPageAction.turnOffMaterialTypePEPlastic(EMPTY,NOTSET);
			driverViewPageAction.turnOffMaterialTypeProtectedSteel(EMPTY,NOTSET);
			driverViewPageAction.turnOffMaterialTypeUnprotectedSteel(EMPTY,NOTSET);
			driverViewPageAction.turnOffUseAllBoundaries(EMPTY,NOTSET);
			driverViewPageAction.turnOnUseAllPipes(EMPTY,NOTSET);
			
			driverViewPageAction.clickOnPositionButton(EMPTY,NOTSET);
			
			assertTrue(driverViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyFOVIsNotShownOnMap(EMPTY,NOTSET));
			
			driverViewPageAction.clickOnHeaderInfoBox(EMPTY,NOTSET);
			assertTrue(driverViewPageAction.verifySurveyInfoTagLabelEquals("Tag: " + driverViewPageAction.getDataReader().getDataRow(3).surveyTag,NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoModeLabelEquals("Mode: " + driverViewPageAction.getDataReader().getDataRow(3).surveyType,NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoTimeElapsedLabelStartsWith("Elapsed: 00:",NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoSurveyStatusLabelEquals("Survey Active",NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoDriverLabelEquals("Driver: " + AdminUser,NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoTimeRemainingLabelStartsWith("Remaining: 0",NOTSET));
			driverViewPageAction.verifySurveyInfoZoomLevelLabelEquals("Zoom Level: 19",NOTSET);
			driverViewPageAction.verifySurveyInfoSurveyorLabelEquals("Surveyor: " + SURVEYOR_NAME + " - " + ANALYZER_SERIAL_NUMBER,NOTSET);
			
			// Analyzer info shows up in Observer view. NA here.
			//driverViewPageAction.verifySurveyInfoAnalyzerLabelEquals("Analyzer: ",NOTSET);
			
			driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);
			driverViewPageAction.stopDrivingSurvey(EMPTY,NOTSET);
		} catch (Exception e) {
			Log.error(e.toString());
		}
	}
}
