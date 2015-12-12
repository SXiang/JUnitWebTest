package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.source.TestSetup;
import surveyor.scommon.source.DriverViewPage;

public class DriverViewPageActions {
	private DriverViewPage driverViewPage = null;

	public DriverViewPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		driverViewPage = new DriverViewPage(driver, testSetup, strBaseURL);
		PageFactory.initElements(driver, driverViewPage);
	}

	public boolean clickOnCurtainArrowDownButton(String testData, String dataRowIDs) {
		return true;
	}

	public boolean clickOnCurtainArrowLeftButton(String testData, String dataRowIDs) {
		return true;
	}

	public boolean clickOnCurtainArrowRightButton(String testData, String dataRowIDs) {
		return true;
	}

	public boolean clickOnCurtainArrowUpButton(String testData, String dataRowIDs) {
		return true;
	}

	public boolean clickOnCurtainReturnButton(String testData, String dataRowIDs) {
		return true;
	}

	public boolean clickOnCurtainZoomInButton(String testData, String dataRowIDs) {
		return true;
	}

	public boolean clickOnCurtainZoomOutButton(String testData, String dataRowIDs) {
		return true;
	}

	public boolean clickOnDisplayButton(String testData, String dataRowIDs) {
		return true;
	}

	public boolean clickOnGisButton(String testData, String dataRowIDs) {
		return true;
	}

	public boolean clickOnMapButton(String testData, String dataRowIDs) {
		return true;
	}

	public boolean clickOnModeButton(String testData, String dataRowIDs) {
		return true;
	}

	public boolean clickOnPositionButton(String testData, String dataRowIDs) {
		return true;
	}

	public boolean clickOnStatusButton(String testData, String dataRowIDs) {
		return true;
	}

	public boolean hideCurtainView(String testData, String dataRowIDs) {
		return true;
	}

	public boolean showCurtainView(String testData, String dataRowIDs) {
		return true;
	}

	/**
	 * 
	 * @param surveyData - Semicolon seperated value for survey data (tag, SurveyTime, SolarRadiation, Wind, SurveyType)
	 * @param dataRowIDs
	 * @return
	 */
	public boolean startDrivingSurvey(String surveyData, String dataRowIDs) {
		return true;
	}

	public boolean stopDrivingSurvey(String testData, String dataRowIDs) {
		return true;
	}

	public boolean turnOnMapView(String testData, String dataRowIDs) {
		return true;
	}

	public boolean turnOnSatelliteView(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyAnemometerButtonIsGreen(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyAnemometerButtonIsRed(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyDisplaySwitchIsOff(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyDisplaySwitchIsOn(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyDriverLabelEquals(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyFlowButtonIsGreen(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyFlowButtonIsRed(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyGisSwitchIsOff(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyGisSwitchIsOn(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyGPSButtonIsGreen(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyGPSButtonIsRed(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyHBTempButtonIsGreen(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyHBTempButtonIsRed(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyPositionButtonIsGreen(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyPositionButtonIsNotSelected(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyPositionButtonIsSelected(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyPressureButtonIsGreen(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyPressureButtonIsRed(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyRefBottleMeasButtonIsDisabled(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyRefBottleMeasButtonIsEnabled(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyStabilityClassLabelEquals(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyStartEQSurveyButtonIsDisabled(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyStartEQSurveyButtonIsEnabled(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyStartIsotopicCaptureButtonIsDisabled(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyStartIsotopicCaptureButtonIsEnabled(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyStartSurveyButtonIsDisabled(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyStartSurveyButtonIsEnabled(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyStopDrivingSurveyButtonIsDisabled(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyStopDrivingSurveyButtonIsEnabled(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifySurveyModeLabelEquals(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifySurveyorLabelEquals(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifySurveyStatusLabelEquals(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifySystemShutdownButtonIsDisabled(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifySystemShutdownButtonIsEnabled(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyTagLabelEquals(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyTimeElapsedLabelEquals(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyTimeRemainingLabelEquals(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyWBTempButtonIsGreen(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyWBTempButtonIsRed(String testData, String dataRowIDs) {
		return true;
	}

	public boolean verifyZoomLevelLabelEquals(String testData, String dataRowIDs) {
		return true;
	}

}
