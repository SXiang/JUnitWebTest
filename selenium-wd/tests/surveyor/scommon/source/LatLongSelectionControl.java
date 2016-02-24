package surveyor.scommon.source;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
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
   @CacheLookup
    private WebElement filterByTypeDropDown;
    
    @FindBy(id = "boundary-search-text")
    @CacheLookup
    private WebElement selectByNameTextField;    
    
    @FindBy(id = "button-map-dialog-ok")
  //  @CacheLookup
    private WebElement okButton;

    @FindBy(id = "button-map-dialog-cancel")
    @CacheLookup
    private WebElement cancelButton;

    @FindBy(id = "latitude")
    @CacheLookup
    private WebElement latitude;

    @FindBy(id = "longitude")
    @CacheLookup
    private WebElement longitude;

    @FindBy(id = "zoom-level")
    @CacheLookup
    private WebElement zoomLevel;

    @FindBy(id = "info")
    @CacheLookup
    private WebElement selectionInfo;

    @FindBy(id = "myModal")
    @CacheLookup
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
        okButton.click();
        return this;
    }

    /**
     * Click on Cancel Button.
     *
     * @return the LatLongSelectionControl class instance.
     */
    public LatLongSelectionControl clickCancelButton() {
        cancelButton.click();
        return this;
    }

    /**
     * Set value to selectByName Text field.
     *
     * @return the LatLongSelectionControl class instance.
     */
    public LatLongSelectionControl setTitleTextField(String name) {
    	selectByNameTextField.sendKeys(name);
        return this;
    }

    /**
     * Set value to filterByType Drop Down List field.
     *
     * @return the LatLongSelectionControl class instance.
     */
    public LatLongSelectionControl setFilterByTypeDropDownListField(String filterByTypeValue) {
        new Select(filterByTypeDropDown).selectByVisibleText(filterByTypeValue);
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
		(new WebDriverWait(driver, timeout * 3)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
        		Object imageData = ((JavascriptExecutor)d).executeScript(GET_BOUNDARY_SELECTOR_CANVAS_IMAGE_DATA_JS_FUNCTION + 
        				GET_BOUNDARY_SELECTOR_CANVAS_IMAGE_DATA_JS_FUNCTION_CALL);
                return (imageData == null);
            }
        });
		return this;
	}
}