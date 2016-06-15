package surveyor.scommon.source;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import common.source.Log;
import common.source.WebElementExtender;

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

	@FindBy(id = "boundary-feature-class")	
	private WebElement filterByTypeDropDown;
	private String filterByTypeId = "boundary-feature-class";
	
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

	@FindBy(id = "zoom-level")	
	private WebElement zoomLevel;

	@FindBy(id = "info")	
	private WebElement selectionInfo;

	@FindBy(id = "myModal")	
	private WebElement mapModalDialog;
	
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
		Log.info("Set boundary name to '"+boundaryName+"'");
		selectByNameTextField.sendKeys(boundaryName);
		this.waitForAutoCompleteListToOpen();

		if (!WebElementExtender.checkElementsListContains(driver, "//*[@id='ui-id-1']/li", autocompleteListEntries)) {
			return false;
		}
		
		this.clickOnAutoCompleteListEntry(1);   // click on first entry in autocomplete list.
		this.waitForAutoCompleteListToClose();
		return true;
	}

	/**
	 * Set value to selectByName Text field.
	 *
	 * @return the LatLongSelectionControl class instance.
	 */
	public LatLongSelectionControl setCustomerBoundaryName(String name) {
		Log.info("Set customer boundary name '"+name+"'");
		selectByNameTextField.sendKeys(name);
		this.waitForAutoCompleteListToOpen();
		this.clickOnAutoCompleteListEntry(1);   // click on first entry in autocomplete list.
		this.waitForAutoCompleteListToClose();
		return this;
	}

	/**
	 * Set value to filterByType Drop Down List field.
	 *
	 * @return the LatLongSelectionControl class instance.
	 */
	public LatLongSelectionControl selectCustomerBoundaryType(String filterByTypeValue) {
		Log.info("Select customer boundary type '"+filterByTypeValue+"'");
		new Select(filterByTypeDropDown).selectByVisibleText(filterByTypeValue);
		return this;
	}
	
	/**
	 * Clicks on the specified entry in the autocomplete list box.
	 * @param entryIdx - 1-based index in the list.
	 */
	public void clickOnAutoCompleteListEntry(Integer entryIdx) {
		WebElement element = this.driver.findElement(By.xpath(String.format("//ul[@id='ui-id-1']/li[%d]", entryIdx)));
		element.click();
	}
	
	/**
	 * Waits for the autocomplete list box to be opened.
	 *
	 * @return the LatLongSelectionControl class instance.
	 */
	public LatLongSelectionControl waitForAutoCompleteListToOpen() {
		Log.info("Waiting for auto-complete list to open.");
		(new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-id-1")));
		(new WebDriverWait(driver, timeout * 3)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				WebElement autoCompleteList = d.findElement(By.id("ui-id-1"));
				return !autoCompleteList.getAttribute("style").contains("display:none") && 
						!autoCompleteList.getAttribute("style").contains("display: none");
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
				WebElement autoCompleteList = d.findElement(By.id("ui-id-1"));
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
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement myModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myModal")));
		(new WebDriverWait(driver, timeout * 3)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return !myModal.getAttribute("style").contains("display:none") && !myModal.getAttribute("style").contains("display: none");
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
		WebElement myModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myModal")));
		(new WebDriverWait(driver, timeout * 3)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return myModal.getAttribute("style").contains("display:none") || myModal.getAttribute("style").contains("display: none");
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
	public LatLongSelectionControl selectSegment(String canvasXPath, List<Coordinates> coordinates) {
		WebElement canvas = driver.findElement(By.xpath(canvasXPath));
		if (canvas != null && canvas.isDisplayed()) 
		{
			Log.info("[LatLongSelectionControl]: Found canvas element");
		}
		Log.info("[LatLongSelectionControl]: Performing click Action on the canvas element");
		Actions builder = new Actions(driver);

		for (int i =0; i < coordinates.size(); i++)
		{
			Coordinates cord = coordinates.get(i);
			builder.moveToElement(canvas, cord.getX(), cord.getY())
			.click()
			.build()
			.perform();

			if (i == coordinates.size()-1)
			{
				builder.moveToElement(canvas, cord.getX(), cord.getY())
				.doubleClick()
				.build()
				.perform();

			}
		}
		return this;
	}
}