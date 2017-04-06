package surveyor.scommon.source;

import org.openqa.selenium.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import common.source.Log;
import common.source.LogHelper;
import common.source.NumberUtility;
import common.source.Timeout;
import common.source.WebElementExtender;
import common.source.WebElementFunctionUtil;

public class LatLongSelectionControl extends BaseControl {
	public enum ControlMode {
		MapInteraction,
		Default
	}

	private static final String GET_BOUNDARY_SELECTOR_CANVAS_IMAGE_DATA_JS_FUNCTION = "function getBoundarySelectorCanvasImageData(){var imgData=null;var mapFrame=window.frames[1];"
			+ "if(mapFrame){frameDoc=mapFrame.document;if(frameDoc){divEl=frameDoc.getElementById(\"map\");"
			+ "if(divEl){canvasElement=divEl.getElementsByClassName(\"ol-unselectable\")[0];ctx=canvasElement.getContext('2d');"
			+ "imgData=ctx.getImageData(0,0,canvasElement.width,canvasElement.height);}}};"
			+ "return imgData;};";

	private static final String GET_BOUNDARY_SELECTOR_CANVAS_IMAGE_DATA_JS_FUNCTION_CALL = "return getBoundarySelectorCanvasImageData();";

	private static final String BOUNDARY_NAME_DROPDOWNLIST_UL_ID = "ui-id-1";
	private static final String BOUNDARY_NAME_DROPDOWNLIST_UL_XPATH = "//ul[@id='" + BOUNDARY_NAME_DROPDOWNLIST_UL_ID + "']";

	@FindBy(id = "boundary-feature-class")
	private WebElement filterByTypeDropDown;
	public By filterByTypeId = By.id("boundary-feature-class");

	@FindBy(id = "boundary-search-text")
	private WebElement selectByNameTextField;

	@FindBy(id = "button-map-dialog-ok")
	private WebElement okButton;

	@FindBy(id = "button-map-dialog-cancel")
	private WebElement cancelButton;

	@FindBy(id = "latitude")
	private WebElement latitude;

	@FindBy(id = "longitude")
	private WebElement longitude;

	@FindBy(id = "legend-container")
	private WebElement legendContainer;

	@FindBy(id = "zoom-level")
	private WebElement zoomLevel;

	@FindBy(id = "info")
	private WebElement selectionInfo;

	@FindBy(id = "myModal")
	private WebElement mapModalDialog;
	private By mpaModalDialogBy = By.id("myModal");
	
	@FindBy(id = "map")
	private WebElement canvas;

	@FindBy(id = "DefaultLatitude")
	private WebElement defaultLatitudeInput;

	@FindBy(id = "DefaultLongitude")
	private WebElement defaultLongitudeInput;

	public LatLongSelectionControl(WebDriver driver) {
		super(driver);
	}

	/**
	 * Returns the latitude text.
	 */
	public String getLatitude() {
		return latitude.getText();
	}

	/**
	 * Returns the longitude text.
	 */
	public String getLongitude() {
		return longitude.getText();
	}

	/**
	 * Returns the zoom level text.
	 */
	public String getZoomLevel() {
		return zoomLevel.getText();
	}

	/**
	 * Returns the selection info text.
	 */
	public String getSelectionInfo() {
		return selectionInfo.getText();
	}

	/**
	 * Draws a selector rectangle of specified width and height from the specified offset.
	 *
	 * @return the LatLongSelectionControl class instance.
	 */
	public LatLongSelectionControl drawSelectorRectangle(String canvasXPath, int xOffset, int yOffset, int width, int height) {
		WebElement canvas = driver.findElement(By.xpath(canvasXPath));
		if (canvas != null && canvas.isDisplayed()) {
			Log.info("[LatLongSelectionControl]: Found canvas element");
		}

		Log.info("[LatLongSelectionControl]: Performing draw rectangle action on the canvas element");
		Actions builder = new Actions(driver);
		builder.moveToElement(canvas, xOffset, yOffset)
		.keyDown(Keys.SHIFT)
		.clickAndHold(canvas)
		.moveByOffset(xOffset + width, yOffset + height)
		.release()
		.keyUp(Keys.SHIFT)
		.build()
		.perform();

		return this;
	}

	/**
	 * Click on the specified (x,y) offset in the canvas.
	 *
	 * @return the LatLongSelectionControl class instance.
	 */
	public LatLongSelectionControl selectLatLong(String canvasXPath, int xOffset, int yOffset) {
		WebElement canvas = driver.findElement(By.xpath(canvasXPath));
		if (canvas != null && canvas.isDisplayed()) {
			Log.info("[LatLongSelectionControl]: Found canvas element");
		}

		Log.info("[LatLongSelectionControl]: Performing click Action on the canvas element");
		Actions builder = new Actions(driver);
		builder.moveToElement(canvas, xOffset, yOffset)
		.moveByOffset(xOffset, yOffset)
		.keyDown(Keys.SHIFT)
		.click()
		.keyUp(Keys.SHIFT)
		.build()
		.perform();

		return this;
	}


	/**
	 * Switches between interactions on web elements on the map iframe vs interaction on the web elements in container page.
	 *
	 * REMARKS:
	 * We need the 2 modes since this control has elements from both an iframe (which has the map) as well as container document.
	 * When interacting with map use the MapInteraction mode.
	 * When interacting with web elements other than the Map (for eg. OK/Cancel buttons) use the default mode.
	 *
	 * @return the LatLongSelectionControl class instance.
	 */
	public LatLongSelectionControl switchMode(ControlMode mode) {
		Log.info("Switch map modal to '"+mode+"'");
		switch (mode)
		{
		case MapInteraction:
			driver.switchTo().frame("target");
			break;
		case Default:
			driver.switchTo().defaultContent();
			break;
		}

		return this;
	}

	/**
	 * Returns the Customer Boundary text field web element.
	 */
	public WebElement getCustomerBoundaryTextField() {
		return this.selectByNameTextField;
	}

	/**
	 * Click on OK Button.
	 *
	 * @return the LatLongSelectionControl class instance.
	 */
	public LatLongSelectionControl clickOkButton() {
		Log.clickElementInfo("OK", "in Lat/Long Selection control");
		okButton.click();
		return this;
	}

	/**
	 * Click on Cancel Button.
	 *
	 * @return the LatLongSelectionControl class instance.
	 */
	public LatLongSelectionControl clickCancelButton() {
		Log.clickElementInfo("Cancel", "in Lat/Long Secection control");
		cancelButton.click();
		return this;
	}

	/**
	 * Verifies that the customer boundary name auto-complete list contains the specified entries.
	 *
	 * @return the LatLongSelectionControl class instance.
	 */
	public boolean verifyCustomerBoundaryAutoCompleteListContains(String boundaryName, List<String> autocompleteListEntries) {
		Log.method("verifyCustomerBoundaryAutoCompleteListContains", boundaryName, LogHelper.listToString(autocompleteListEntries));
		return selectFromNameDropdownList(boundaryName, autocompleteListEntries);
	}

	/**
	 * Set value to selectByName Text field.
	 *
	 * @return the LatLongSelectionControl class instance.
	 */
	public LatLongSelectionControl setCustomerBoundaryName(String name) {
		Log.method("setCustomerBoundaryName", name);
		selectFromNameDropdownList(name, Arrays.asList(new String[] {name}));
		return this;
	}

	/**
	 * Set value to selectByName Text field. Return whether or not boundary name was set successfully.
	 *
	 * @return whether or not boundary name was set successfully.
	 */
	public boolean setVerifyCustomerBoundaryName(String name) {
		Log.method("setVerifyCustomerBoundaryName", name);
		return setVerifyCustomerBoundaryName(name, Arrays.asList(new String[] {name}));
	}

	/**
	 * Set value to selectByName Text field. Return whether or not boundary name was set successfully.
	 *
	 * @return whether or not boundary name was set successfully.
	 */
	public boolean setVerifyCustomerBoundaryName(String name, List<String> autoCompleteList) {
		Log.method("setVerifyCustomerBoundaryName", name, autoCompleteList);
		return selectFromNameDropdownList(name, autoCompleteList);
	}

	/**
	 * Set value to filterByType Drop Down List field.
	 *
	 * @return the LatLongSelectionControl class instance.
	 */
	public LatLongSelectionControl selectCustomerBoundaryType(String filterByTypeValue) {
		Log.info("Select customer boundary type '"+filterByTypeValue+"'");
		waitForElementToBeEnabled(getFilterByTypeDropDown());
		selectDropdownOption(getFilterByTypeDropDown(), filterByTypeValue);
		return this;
	}



	/**
	 * Clicks on the specified entry in the autocomplete list box.
	 * @param entryIdx - 1-based index in the list.
	 */
	public void clickOnAutoCompleteListEntry(Integer entryIdx) {
		WebElement element = this.driver.findElement(By.xpath(String.format(BOUNDARY_NAME_DROPDOWNLIST_UL_XPATH + "/li[%d]", entryIdx)));
		element.click();
	}

	/**
	 * Returns the list of menu items shown in auto-complete list.
	 * @return
	 */
	public List<String> getAutoCompleteListEntries() {
		WebElement autoCompleteList = this.driver.findElement(By.id(BOUNDARY_NAME_DROPDOWNLIST_UL_ID));
		List<WebElement> listElements = WebElementFunctionUtil.tryFindElements(autoCompleteList, p -> p.findElements(By.xpath("li[@class='ui-menu-item']")));
		return listElements.stream()
			.map(e -> e.getText())
			.collect(Collectors.toList());
	}

	/**
	 * Waits for the autocomplete list box to be opened.
	 *
	 * @return the LatLongSelectionControl class instance.
	 */
	public LatLongSelectionControl waitForAutoCompleteListToOpen() {
		Log.info("Waiting for auto-complete list to open.");
		(new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOfElementLocated(By.id(BOUNDARY_NAME_DROPDOWNLIST_UL_ID)));
		(new WebDriverWait(driver, timeout * 3)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				WebElement autoCompleteList = d.findElement(By.id(BOUNDARY_NAME_DROPDOWNLIST_UL_ID));
				String elementStyle = autoCompleteList.getAttribute("style");
				return !elementStyle.contains("display:none") &&
						!elementStyle.contains("display: none");
			}
		});
		return this;
	}

	/**
	 * Waits for the autocomplete list box to be closed.
	 *
	 * @return the LatLongSelectionControl class instance.
	 */
	public LatLongSelectionControl waitForAutoCompleteListToClose() {
		Log.info("Waiting for auto-complete list to close.");
		(new WebDriverWait(driver, timeout * 3)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				WebElement autoCompleteList = d.findElement(By.id(BOUNDARY_NAME_DROPDOWNLIST_UL_ID));
				return !autoCompleteList.getAttribute("style").contains("display:block") &&
						!autoCompleteList.getAttribute("style").contains("display: block");
			}
		});
		return this;
	}

	/**
	 * Waits for the modal map dialog to be opened.
	 *
	 * @return the LatLongSelectionControl class instance.
	 */
	public LatLongSelectionControl waitForModalDialogOpen() {
		Log.info("Wait for map modal dialog to open.");
		WebDriverWait wait = new WebDriverWait(driver, timeout * 3);
		wait.until(ExpectedConditions.presenceOfElementLocated(mpaModalDialogBy));
		(new WebDriverWait(driver, timeout * 3)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return !mapModalDialog.getAttribute("style").contains("display:none") && !mapModalDialog.getAttribute("style").contains("display: none");
			}
		});
		return this;
	}

	/**
	 * Waits for the modal map dialog to close.
	 *
	 * @return the LatLongSelectionControl class instance.
	 */
	public LatLongSelectionControl waitForModalDialogToClose() {
		Log.info("Wait for map modal dialog to close.");
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.presenceOfElementLocated(mpaModalDialogBy));
		(new WebDriverWait(driver, timeout * 3)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return mapModalDialog.getAttribute("style").contains("display:none") || mapModalDialog.getAttribute("style").contains("display: none");
			}
		});
		return this;
	}

	/**
	 * Waits for the image to be loaded in the modal map dialog.
	 *
	 * @return the LatLongSelectionControl class instance.
	 */
	public LatLongSelectionControl waitForMapImageLoad() {
		// Wait for image data on the canvas to be present.
		Log.info("Wait for map image to load.");
		(new WebDriverWait(driver, timeout * 3)).until(new ExpectedCondition<Boolean>() {
			String jScript = GET_BOUNDARY_SELECTOR_CANVAS_IMAGE_DATA_JS_FUNCTION +
					GET_BOUNDARY_SELECTOR_CANVAS_IMAGE_DATA_JS_FUNCTION_CALL;
			public Boolean apply(WebDriver d) {
				Object imageData = ((JavascriptExecutor)d).executeScript(jScript);
				return (imageData == null);
			}
		});
		return this;
	}

	/**
	 *
	 *
	 * @return the LatLongSelectionControl class instance.
	 */
	public LatLongSelectionControl selectSegment(List<Coordinates> gpsPosition) {
		if (canvas != null && canvas.isDisplayed())
		{
			Log.info("[LatLongSelectionControl]: Found canvas element");
		}
		Log.info("[LatLongSelectionControl]: Performing click Action on the canvas element");
		Actions builder = new Actions(driver);
		List<Point> coordinates = getScreenCoordinates(gpsPosition);
		for (int i =0; i < coordinates.size(); i++)
		{
			Point coord = coordinates.get(i);
			builder.moveToElement(canvas, coord.x, coord.y)
			.click()
			.build()
			.perform();

			if (i == coordinates.size()-1)
			{
				builder.moveToElement(canvas, coord.x, coord.y)
				.doubleClick()
				.build()
				.perform();

			}
		}
		return this;
	}

	/**
	 *
	 *
	 * @return the screen coordinates for the input gps positions.
	 */
	public List<Point> getScreenCoordinates(List<Coordinates> gpsPosition) {
		List<Point> coordinates = new ArrayList<Point>();

		Dimension dimension = canvas.getSize();
		int div = 1, buttomdiv = 100;
		int legendHight = legendContainer.getSize().height;

		Coordinates lt = getGPSPosition(canvas, 0+div, legendHight+div);
		Coordinates rb = getGPSPosition(canvas, dimension.width-div, dimension.height-buttomdiv);
		double longWidth = Math.abs(rb.getX() - lt.getX());
		double latHeight = Math.abs(rb.getY() - lt.getY());

		double longRatio = (dimension.width-2*div) / longWidth;
		double latRatio = (dimension.height-legendHight-div-buttomdiv) / latHeight;

		for (int i =0; i < gpsPosition.size(); i++)
		{
			Coordinates coord = gpsPosition.get(i);
			int x = (int) ((coord.getX() - lt.getX()) * longRatio);
			int y = (int) ((coord.getY() - lt.getY())* latRatio);
			coordinates.add(new Point(Math.abs(x)+div, Math.abs(y)+legendHight+div));
		}
		return coordinates;
	}

	public Coordinates getGPSPosition(WebElement canvas, int x, int y){
		Actions builder = new Actions(driver);
		builder.moveToElement(canvas, x, y).build().perform();
		return new Coordinates(NumberUtility.getDoubleValueOf(getLongitude()), NumberUtility.getDoubleValueOf(getLatitude()));
	}

	public WebElement getFilterByTypeDropDown() {
		return filterByTypeDropDown;
	}

	public boolean verifyNoBoundaryNameSearchResult() {
		Log.method("verifyNoBoundaryNameSearchResult");
		By noResultBy = By.xpath(BOUNDARY_NAME_DROPDOWNLIST_UL_XPATH + "//div[text()='no results...']");
		WebElement noResultDiv =  WebElementExtender.findElementIfExists(driver, noResultBy);
		boolean noResult = WebElementExtender.isElementPresentAndDisplayed(noResultDiv);
		return noResult;
	}

	protected boolean selectDropdownOption(WebElement dropdown, String option){
		boolean selected = false;
		int numTry = 0;
		By optBy = By.xpath("option[text()='"+option.trim()+"']");
		do{
			try{
				WebElement opt =  dropdown.findElement(optBy);
				opt.click();
				selected = opt.isSelected();
			}catch(Exception e){
				numTry++;
				Log.error("Failed to select option '"+option+"'");
			}
		}while(!selected&&numTry<5);
		return selected;
	}

	private boolean selectFromNameDropdownList(String boundaryName, List<String> autocompleteListEntries) {
		WebElementExtender.waitForElementToBeClickable(Timeout.TEN * 6 /*timeout*/, driver, selectByNameTextField);
		waitForElementToBeEnabled(selectByNameTextField);
		WebElementExtender.sendKeys(selectByNameTextField, boundaryName);
		this.waitForAutoCompleteListToOpen();

		if (!WebElementExtender.checkElementsListContains(driver, BOUNDARY_NAME_DROPDOWNLIST_UL_XPATH + "//li", autocompleteListEntries)) {
			return false;
		}

		this.clickOnAutoCompleteListEntry(1);   // click on first entry in autocomplete list.
		this.waitForAutoCompleteListToClose();
		return true;
	}
}