package surveyor.scommon.source;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.Log;
import common.source.OLMapUtility;
import common.source.TestSetup;

public class BaseDrivingViewPage extends BaseMapViewPage {
	private static final String IMG_DATA_VAR_NAME = "imgData";
	private static final String IMG_DATA_DATA_0 = "return imgData.data[0];";
	private static final String IMG_DATA_DATA_1 = "return imgData.data[1];";
	private static final String IMG_DATA_DATA_2 = "return imgData.data[2];";
	private static final String[] GreenRGBPixels = new String[] { "21", "255", "0" };
	private static final String[] RedRGBPixels = new String[] { "255", "2", "0" };
	private static final String[] GreyRGBPixels = new String[] { "153", "153", "153" };

	private static final Integer PIXEL_DIFF_400 = 400;
	private static final Integer VALUE_700 = 700;

	public static final String STATUS_PRESSURE_CANVAS_CTX = "test_ctx = $(\"#status_pressure_canvas\")[0].getContext('2d');";
	public static final String STATUS_WARM_CANVAS_CTX = "test_ctx = $(\"#status_warm_canvas\")[0].getContext('2d');";
	public static final String STATUS_TEMP_CANVAS_CTX = "test_ctx = $(\"#status_temp_canvas\")[0].getContext('2d');";
	public static final String STATUS_FLOW_CANVAS_CTX = "test_ctx = $(\"#status_flow_canvas\")[0].getContext('2d');";
	public static final String STATUS_GPS_CANVAS_CTX = "test_ctx = $(\"#status_gps_canvas\")[0].getContext('2d');";
	public static final String STATUS_IGPS_CANVAS_CTX = "test_ctx = $(\"#status_igps_canvas\")[0].getContext('2d');";
	public static final String STATUS_ANEMOMETER_CANVAS_CTX = "test_ctx = $(\"#status_anemometer_canvas\")[0].getContext('2d');";
	private static final String CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA = "centerX = 40;centerY = 40;fontY = 12;paddingY = 5;rectWidth = 1;rectHeight = 1;var imgData=test_ctx.getImageData(centerX,centerY-fontY-paddingY,rectWidth,rectHeight);";

	private static final String RGB_PIXELS_IMAGE_DATA = "var IMG_WIDTH=80;var IMG_HEIGHT=74;var imgData=test_ctx.getImageData(0,0,IMG_WIDTH,IMG_HEIGHT);";
	//private static final String

	@FindBy(id = "header_info_box_upper_left")
	private WebElement divHeaderInfoBox;

	@FindBy(id = "bottom_button_position_follow")
	private WebElement positionButton;

	@FindBy(id = "bottom_button_status")
	private WebElement statusButton;

	@FindBy(id = "no_analyzer")
	private WebElement divNoAnalyzer;

	@FindBy(id = "canvas_rose")
	private WebElement windRose;

	@FindBy(id = "canvas_rose_arrow")
	private WebElement windRoseArrow;

	public BaseDrivingViewPage(WebDriver driver, TestSetup testSetup, String strBaseURL, String strPageURL) {
		super(driver, testSetup, strBaseURL, strPageURL);
	}

	public BaseDrivingViewPage clickHeaderInfoBox() {
		Log.clickElementInfo("HeaderInfo", ElementType.DIVISION);
		this.divHeaderInfoBox.click();
		return this;
	}

	public BaseDrivingViewPage clickPositionButton() {
		Log.clickElementInfo("Position");
		this.positionButton.click();
		return this;
	}

	public BaseDrivingViewPage clickStatusButton() {
		Log.clickElementInfo("Status");
		this.statusButton.click();
		return this;
	}

	public String getTagLabelText() {
		return getElementInnerText(driver.findElement(By.id("tag")));
	}

	public String getSurveyModeLabelText() {
		return getElementInnerText(driver.findElement(By.id("surveyMode")));
	}

	public String getSurveyStatusLabelText() {
		return getElementInnerText(driver.findElement(By.id("headerInfoStatus")));
	}

	public String getDriverLabelText() {
		return getElementInnerText(driver.findElement(By.id("driver")));
	}

	public String getStabilityClassLabelText() {
		return getElementInnerText(driver.findElement(By.id("stabilityClass")));
	}

	public WebElement getTimeElapsedLabel() {
		return driver.findElement(By.id("timeElapsed"));
	}

	public String getTimeElapsedLabelText() {
		return getElementInnerText(driver.findElement(By.id("timeElapsed")));
	}

	public WebElement getTimeLabel() {
		return driver.findElement(By.id("currentTime"));
	}

	public String getTimeLabelText() {
		return getElementInnerText(driver.findElement(By.id("currentTime")));
	}

	public WebElement getTimeRemainingLabel() {
		return driver.findElement(By.id("timeRemaining"));
	}

	public String getTimeRemainingLabelText() {
		return getElementInnerText(driver.findElement(By.id("timeRemaining")));
	}

	public String getSurveyorLabelText() {
		return getElementInnerText(driver.findElement(By.id("surveyorAnalyzer")));
	}

	public String getZoomLevelLabelText() {
		return getElementInnerText(driver.findElement(By.id("zoomLevel")));
	}

	public String getAnalyzerLabelText() {
		return getElementInnerText(driver.findElement(By.id("headerInfoStatus")));
	}

	public boolean isWindRoseShown() {
		boolean isShown = true;
		if ((this.windRose.getAttribute("class").contains("ng-hide")) && (this.windRoseArrow.getAttribute("class").contains("ng-hide"))) {
			Log.error("WindRose is not shown");
			isShown = false;
		}
		Log.info("WindRose is shown");
		return isShown;
	}

	public boolean isStatusButtonGreen() {
		return this.statusButton.getAttribute("class").equalsIgnoreCase("bottom_button standard_icon");
	}

	public boolean isStatusButtonGreenWithPlus() {
		return this.statusButton.getAttribute("class").equalsIgnoreCase("bottom_button standard_icon open");
	}

	public boolean isStatusButtonOpen() {
		return this.statusButton.getAttribute("class").equalsIgnoreCase("bottom_button standard_icon");
	}

	public boolean isStatusButtonRed() {
		return this.statusButton.getAttribute("class").equalsIgnoreCase("bottom_button standard_icon trouble");
	}

	public boolean isPositionButtonSelected() {
		return this.positionButton.getAttribute("class").equalsIgnoreCase("bottom_button standard_icon on");
	}

	public boolean isPositionButtonGreen() {
		return this.positionButton.getAttribute("class").equalsIgnoreCase("bottom_button standard_icon on");
	}

	public boolean togglePositionButton(boolean turnOn) throws IllegalArgumentException {
		boolean isSelected = isPositionButtonGreen();
		if ((isSelected && !turnOn) || (!isSelected && turnOn)) {
			clickPositionButton();
		}
		return isPositionButtonGreen();
	}

	public boolean isPressureButtonRed() {
		return new OLMapUtility(driver).areRedPixelsGreaterThanGreenOnButton(STATUS_PRESSURE_CANVAS_CTX + RGB_PIXELS_IMAGE_DATA, IMG_DATA_VAR_NAME, PIXEL_DIFF_400);
	}

	public boolean isPressureButtonGreen() {
		return new OLMapUtility(driver).areGreenPixelsGreaterThanRedOnButton(STATUS_PRESSURE_CANVAS_CTX + RGB_PIXELS_IMAGE_DATA, IMG_DATA_VAR_NAME, PIXEL_DIFF_400);
	}

	public boolean isHBTempButtonRed() {
		return new OLMapUtility(driver).areRedPixelsGreaterThanGreenOnButton(STATUS_WARM_CANVAS_CTX + RGB_PIXELS_IMAGE_DATA, IMG_DATA_VAR_NAME, PIXEL_DIFF_400);
	}

	public boolean isHBTempButtonGreen() {
		return new OLMapUtility(driver).areGreenPixelsGreaterThanRedOnButton(STATUS_WARM_CANVAS_CTX + RGB_PIXELS_IMAGE_DATA, IMG_DATA_VAR_NAME, PIXEL_DIFF_400);
	}

	public boolean isWBTempButtonRed() {
		return new OLMapUtility(driver).areRedPixelsGreaterThanGreenOnButton(STATUS_TEMP_CANVAS_CTX + RGB_PIXELS_IMAGE_DATA, IMG_DATA_VAR_NAME, PIXEL_DIFF_400);
	}

	public boolean isWBTempButtonGreen() {
		return new OLMapUtility(driver).areGreenPixelsGreaterThanRedOnButton(STATUS_TEMP_CANVAS_CTX + RGB_PIXELS_IMAGE_DATA, IMG_DATA_VAR_NAME, PIXEL_DIFF_400);
	}

	public boolean isRedArcShownOnFlowButton() {
		return new OLMapUtility(driver).isRedArcShownOnButton(STATUS_FLOW_CANVAS_CTX + RGB_PIXELS_IMAGE_DATA, IMG_DATA_VAR_NAME);
	}

	public boolean isFlowButtonRed() {
		return new OLMapUtility(driver).areRedPixelsGreaterThanGreenOnButton(STATUS_FLOW_CANVAS_CTX + RGB_PIXELS_IMAGE_DATA, IMG_DATA_VAR_NAME, PIXEL_DIFF_400);
	}

	public boolean isFlowButtonGreen() {
		return new OLMapUtility(driver).areGreenPixelsGreaterThanRedOnButton(STATUS_FLOW_CANVAS_CTX + RGB_PIXELS_IMAGE_DATA, IMG_DATA_VAR_NAME, PIXEL_DIFF_400);
	}

	public boolean isFlowButtonGrey() {
		OLMapUtility olMapUtility = new OLMapUtility(driver);
		boolean redPixelsGreater = olMapUtility.areRedPixelsGreaterThanGreenOnButton(STATUS_FLOW_CANVAS_CTX + RGB_PIXELS_IMAGE_DATA, IMG_DATA_VAR_NAME, PIXEL_DIFF_400);
		boolean greenPixelsGreater = olMapUtility.areGreenPixelsGreaterThanRedOnButton(STATUS_FLOW_CANVAS_CTX + RGB_PIXELS_IMAGE_DATA, IMG_DATA_VAR_NAME, PIXEL_DIFF_400);
		boolean rgbCountsLesser = olMapUtility.areRGBPixelCountsLesserThanValue(STATUS_FLOW_CANVAS_CTX + RGB_PIXELS_IMAGE_DATA, IMG_DATA_VAR_NAME, VALUE_700);
		return rgbCountsLesser && !redPixelsGreater && !greenPixelsGreater;
	}

	public boolean isGPSButtonRed() {
		OLMapUtility olMapUtility = new OLMapUtility(driver);
		return olMapUtility.isButtonRed(STATUS_GPS_CANVAS_CTX + RGB_PIXELS_IMAGE_DATA, IMG_DATA_VAR_NAME);
	}

	public boolean isGPSButtonYellow() {
		OLMapUtility olMapUtility = new OLMapUtility(driver);
		return olMapUtility.isButtonYellow(STATUS_GPS_CANVAS_CTX + RGB_PIXELS_IMAGE_DATA, IMG_DATA_VAR_NAME);
	}

	public boolean isGPSButtonBlue() {
		OLMapUtility olMapUtility = new OLMapUtility(driver);
		return olMapUtility.isButtonBlue(STATUS_GPS_CANVAS_CTX + RGB_PIXELS_IMAGE_DATA, IMG_DATA_VAR_NAME);
	}

	public boolean isGPSButtonGreen() {
		OLMapUtility olMapUtility = new OLMapUtility(driver);
		return olMapUtility.isButtonGreen(STATUS_GPS_CANVAS_CTX + RGB_PIXELS_IMAGE_DATA, IMG_DATA_VAR_NAME);
	}

	public boolean isiGPSButtonRed() {
		OLMapUtility olMapUtility = new OLMapUtility(driver);
		return olMapUtility.isButtonRed(STATUS_IGPS_CANVAS_CTX + RGB_PIXELS_IMAGE_DATA, IMG_DATA_VAR_NAME);
	}

	public boolean isiGPSButtonBlue() {
		OLMapUtility olMapUtility = new OLMapUtility(driver);
		return olMapUtility.isButtonBlue(STATUS_IGPS_CANVAS_CTX + RGB_PIXELS_IMAGE_DATA, IMG_DATA_VAR_NAME);
	}

	public boolean isiGPSButtonYellow() {
		OLMapUtility olMapUtility = new OLMapUtility(driver);
		return olMapUtility.isButtonYellow(STATUS_IGPS_CANVAS_CTX + RGB_PIXELS_IMAGE_DATA, IMG_DATA_VAR_NAME);
	}

	public boolean isiGPSButtonGreen() {
		OLMapUtility olMapUtility = new OLMapUtility(driver);
		return olMapUtility.isButtonGreen(STATUS_IGPS_CANVAS_CTX + RGB_PIXELS_IMAGE_DATA, IMG_DATA_VAR_NAME);
	}

	public boolean isAnemometerButtonRed() {
		Object pixelRed = ((JavascriptExecutor) driver)
				.executeScript(STATUS_ANEMOMETER_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_0);
		Object pixelGreen = ((JavascriptExecutor) driver)
				.executeScript(STATUS_ANEMOMETER_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_1);
		Object pixelBlue = ((JavascriptExecutor) driver)
				.executeScript(STATUS_ANEMOMETER_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_2);
		return pixelRed.toString().equals(RedRGBPixels[0]) && pixelGreen.toString().equals(RedRGBPixels[1])
				&& pixelBlue.toString().equals(RedRGBPixels[2]);
	}

	public boolean isAnemometerButtonGreen() {
		Object pixelRed = ((JavascriptExecutor) driver)
				.executeScript(STATUS_ANEMOMETER_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_0);
		Object pixelGreen = ((JavascriptExecutor) driver)
				.executeScript(STATUS_ANEMOMETER_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_1);
		Object pixelBlue = ((JavascriptExecutor) driver)
				.executeScript(STATUS_ANEMOMETER_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_2);
		return pixelRed.toString().equals(GreenRGBPixels[0]) && pixelGreen.toString().equals(GreenRGBPixels[1])
				&& pixelBlue.toString().equals(GreenRGBPixels[2]);
	}

	/**
	 * Verifies that the page is done Connecting and Connecting element is
	 * hidden.
	 */
	public void waitForConnectionComplete() {
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				if(!divNoAnalyzer.getAttribute("class").equalsIgnoreCase("cssFade ng-hide")){
					jsClick(divNoAnalyzer);
				}
				return divNoAnalyzer.getAttribute("class").equalsIgnoreCase("cssFade ng-hide");
			}
		});
	}

	/**
	 * Verifies that the page is done Connecting dialog is shown.
	 */
	public boolean isConnectionCompleteDialogShown() {
		if (divNoAnalyzer != null && divNoAnalyzer.isDisplayed()) {
			return divNoAnalyzer.getAttribute("class").equalsIgnoreCase("cssFade");
		}
		return false;
	}
}
