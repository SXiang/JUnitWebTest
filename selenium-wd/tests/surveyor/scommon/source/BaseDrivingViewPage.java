package surveyor.scommon.source;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.Log;
import common.source.TestSetup;

public class BaseDrivingViewPage extends BaseMapViewPage {
	private static final String IMG_DATA_DATA_0 = "return imgData.data[0];";
	private static final String IMG_DATA_DATA_1 = "return imgData.data[1];";
	private static final String IMG_DATA_DATA_2 = "return imgData.data[2];";
	private static final String[] GreenRGBPixels = new String[] { "21", "255", "0" };
	private static final String[] RedRGBPixels = new String[] { "255", "2", "0" };

	public static final String STATUS_PRESSURE_CANVAS_CTX = "test_ctx = $(\"#status_pressure_canvas\")[0].getContext('2d');";
	public static final String STATUS_WARM_CANVAS_CTX = "test_ctx = $(\"#status_warm_canvas\")[0].getContext('2d');";
	public static final String STATUS_TEMP_CANVAS_CTX = "test_ctx = $(\"#status_temp_canvas\")[0].getContext('2d');";
	public static final String STATUS_FLOW_CANVAS_CTX = "test_ctx = $(\"#status_flow_canvas\")[0].getContext('2d');";
	public static final String STATUS_GPS_CANVAS_CTX = "test_ctx = $(\"#status_gps_canvas\")[0].getContext('2d');";
	public static final String STATUS_ANEMOMETER_CANVAS_CTX = "test_ctx = $(\"#status_anemometer_canvas\")[0].getContext('2d');";
	private static final String CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA = "centerX = 40;centerY = 40;fontY = 12;paddingY = 5;rectWidth = 1;rectHeight = 1;var imgData=test_ctx.getImageData(centerX,centerY-fontY-paddingY,rectWidth,rectHeight);";

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
		return driver.findElement(By.id("tag")).getText();
	}

	public String getSurveyModeLabelText() {
		return driver.findElement(By.id("surveyMode")).getText();
	}

	public String getSurveyStatusLabelText() {
		return driver.findElement(By.id("headerInfoStatus")).getText();
	}

	public String getDriverLabelText() {
		return driver.findElement(By.id("driver")).getText();
	}

	public String getStabilityClassLabelText() {
		return driver.findElement(By.id("stabilityClass")).getText();
	}

	public WebElement getTimeElapsedLabel() {
		return driver.findElement(By.id("timeElapsed"));
	}

	public String getTimeElapsedLabelText() {
		return driver.findElement(By.id("timeElapsed")).getText();
	}

	public WebElement getTimeLabel() {
		return driver.findElement(By.id("currentTime"));
	}	

	public String getTimeLabelText() {
		return driver.findElement(By.id("currentTime")).getText();
	}	
	
	public WebElement getTimeRemainingLabel() {
		return driver.findElement(By.id("timeRemaining"));
	}

	public String getTimeRemainingLabelText() {
		return driver.findElement(By.id("timeRemaining")).getText();
	}

	public String getSurveyorLabelText() {
		return driver.findElement(By.id("surveyorAnalyzer")).getText();
	}

	public String getZoomLevelLabelText() {
		return driver.findElement(By.id("zoomLevel")).getText();
	}

	public String getAnalyzerLabelText() {
		return driver.findElement(By.id("headerInfoStatus")).getText();
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
		Object pixelRed = ((JavascriptExecutor) driver)
				.executeScript(STATUS_PRESSURE_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_0);
		Object pixelGreen = ((JavascriptExecutor) driver)
				.executeScript(STATUS_PRESSURE_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_1);
		Object pixelBlue = ((JavascriptExecutor) driver)
				.executeScript(STATUS_PRESSURE_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_2);
		return pixelRed.toString().equals(RedRGBPixels[0]) && pixelGreen.toString().equals(RedRGBPixels[1])
				&& pixelBlue.toString().equals(RedRGBPixels[2]);
	}

	public boolean isPressureButtonGreen() {
		Object pixelRed = ((JavascriptExecutor) driver)
				.executeScript(STATUS_PRESSURE_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_0);
		Object pixelGreen = ((JavascriptExecutor) driver)
				.executeScript(STATUS_PRESSURE_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_1);
		Object pixelBlue = ((JavascriptExecutor) driver)
				.executeScript(STATUS_PRESSURE_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_2);
		return pixelRed.toString().equals(GreenRGBPixels[0]) && pixelGreen.toString().equals(GreenRGBPixels[1])
				&& pixelBlue.toString().equals(GreenRGBPixels[2]);
	}

	public boolean isHBTempButtonRed() {
		Object pixelRed = ((JavascriptExecutor) driver)
				.executeScript(STATUS_WARM_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_0);
		Object pixelGreen = ((JavascriptExecutor) driver)
				.executeScript(STATUS_WARM_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_1);
		Object pixelBlue = ((JavascriptExecutor) driver)
				.executeScript(STATUS_WARM_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_2);
		return pixelRed.toString().equals(RedRGBPixels[0]) && pixelGreen.toString().equals(RedRGBPixels[1])
				&& pixelBlue.toString().equals(RedRGBPixels[2]);
	}

	public boolean isHBTempButtonGreen() {
		Object pixelRed = ((JavascriptExecutor) driver)
				.executeScript(STATUS_WARM_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_0);
		Object pixelGreen = ((JavascriptExecutor) driver)
				.executeScript(STATUS_WARM_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_1);
		Object pixelBlue = ((JavascriptExecutor) driver)
				.executeScript(STATUS_WARM_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_2);
		return pixelRed.toString().equals(GreenRGBPixels[0]) && pixelGreen.toString().equals(GreenRGBPixels[1])
				&& pixelBlue.toString().equals(GreenRGBPixels[2]);
	}

	public boolean isWBTempButtonRed() {
		Object pixelRed = ((JavascriptExecutor) driver)
				.executeScript(STATUS_TEMP_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_0);
		Object pixelGreen = ((JavascriptExecutor) driver)
				.executeScript(STATUS_TEMP_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_1);
		Object pixelBlue = ((JavascriptExecutor) driver)
				.executeScript(STATUS_TEMP_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_2);
		return pixelRed.toString().equals(RedRGBPixels[0]) && pixelGreen.toString().equals(RedRGBPixels[1])
				&& pixelBlue.toString().equals(RedRGBPixels[2]);
	}

	public boolean isWBTempButtonGreen() {
		Object pixelRed = ((JavascriptExecutor) driver)
				.executeScript(STATUS_TEMP_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_0);
		Object pixelGreen = ((JavascriptExecutor) driver)
				.executeScript(STATUS_TEMP_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_1);
		Object pixelBlue = ((JavascriptExecutor) driver)
				.executeScript(STATUS_TEMP_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_2);
		return pixelRed.toString().equals(GreenRGBPixels[0]) && pixelGreen.toString().equals(GreenRGBPixels[1])
				&& pixelBlue.toString().equals(GreenRGBPixels[2]);
	}

	public boolean isFlowButtonRed() {
		Object pixelRed = ((JavascriptExecutor) driver)
				.executeScript(STATUS_FLOW_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_0);
		Object pixelGreen = ((JavascriptExecutor) driver)
				.executeScript(STATUS_FLOW_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_1);
		Object pixelBlue = ((JavascriptExecutor) driver)
				.executeScript(STATUS_FLOW_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_2);
		return pixelRed.toString().equals(RedRGBPixels[0]) && pixelGreen.toString().equals(RedRGBPixels[1])
				&& pixelBlue.toString().equals(RedRGBPixels[2]);
	}

	public boolean isFlowButtonGreen() {
		Object pixelRed = ((JavascriptExecutor) driver)
				.executeScript(STATUS_FLOW_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_0);
		Object pixelGreen = ((JavascriptExecutor) driver)
				.executeScript(STATUS_FLOW_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_1);
		Object pixelBlue = ((JavascriptExecutor) driver)
				.executeScript(STATUS_FLOW_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_2);
		return pixelRed.toString().equals(GreenRGBPixels[0]) && pixelGreen.toString().equals(GreenRGBPixels[1])
				&& pixelBlue.toString().equals(GreenRGBPixels[2]);
	}

	public boolean isGPSButtonRed() {
		Object pixelRed = ((JavascriptExecutor) driver)
				.executeScript(STATUS_GPS_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_0);
		Object pixelGreen = ((JavascriptExecutor) driver)
				.executeScript(STATUS_GPS_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_1);
		Object pixelBlue = ((JavascriptExecutor) driver)
				.executeScript(STATUS_GPS_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_2);
		return pixelRed.toString().equals(RedRGBPixels[0]) && pixelGreen.toString().equals(RedRGBPixels[1])
				&& pixelBlue.toString().equals(RedRGBPixels[2]);
	}

	public boolean isGPSButtonGreen() {
		Object pixelRed = ((JavascriptExecutor) driver)
				.executeScript(STATUS_GPS_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_0);
		Object pixelGreen = ((JavascriptExecutor) driver)
				.executeScript(STATUS_GPS_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_1);
		Object pixelBlue = ((JavascriptExecutor) driver)
				.executeScript(STATUS_GPS_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_2);
		return pixelRed.toString().equals(GreenRGBPixels[0]) && pixelGreen.toString().equals(GreenRGBPixels[1])
				&& pixelBlue.toString().equals(GreenRGBPixels[2]);
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
