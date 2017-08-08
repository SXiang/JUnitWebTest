package surveyor.scommon.mobile.source;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.source.Log;
import common.source.WebElementExtender;
import surveyor.scommon.entities.InvestigationEntity;

/**
 * @author sxiang
 *
 */

public class MobileInvestigatePage extends MobileBasePage {

	public static final String STRURLPath = "/Investigation/Investigate?boxId=%s";

	@FindBy(how = How.ID, using = "legend-dropdown")
	protected WebElement legendDropdown;
	@FindBy(how = How.ID, using = "map-legend-container")
	protected WebElement mapLegendContainer;
	
	@FindBy(how = How.ID, using = "complete-button")
	protected WebElement buttonComplete;
	@FindBy(how = How.ID, using = "pause-button")
	protected WebElement buttonPause;
	@FindBy(how = How.ID, using = "investigate-button")
	protected WebElement buttonInvestigate;
	
	@FindBy(how = How.ID, using = "geolocate")
	protected WebElement buttonFollow;
	@FindBy(how = How.ID, using = "directions-button")
	protected WebElement buttonDirections;
	
	@FindBy(how = How.CLASS_NAME, using = "ml-app-container")
	protected WebElement gmapContainer;
	
	@FindBy(how = How.ID, using = "addsource-button")
	protected WebElement buttonAddSource;
	@FindBy(how = How.ID, using = "addcgi-button")
	protected WebElement buttonAddCGI;

	@FindBy(how = How.XPATH, using = "//*[@class='modal-dialog ']//button[text()='Add Leak']")
	protected WebElement buttonAddLeak;
	@FindBy(how = How.XPATH, using = "//*[@class='modal-dialog ']//button[text()='Add Other Source']")
	protected WebElement buttonAddOtherSource;

	@FindBy(how = How.CSS, using = ".modal-dialog button.btn[ng-click='modalOptions.ok()']")
	protected WebElement confirmYes;
	protected By confirmYesBy = By.cssSelector(".modal-dialog button.btn[ng-click='modalOptions.ok()']");
	
	@FindBy(how = How.CSS, using = ".modal-dialog button.btn[ng-click='modalOptions.cancel()']")
	protected WebElement confirmNo;

	@FindBy(how = How.CSS, using = ".modal-dialog .modal-body button.list-group-item.active .glyphicon.glyphicon-map-marker")
	protected List<WebElement> linkToLeaks;
	
	@FindBy(how = How.CSS, using = ".modal-dialog .modal-body button.list-group-item.active .glyphicon.glyphicon-map-marker")
	protected WebElement linkToFirstLeak;
	
	@FindBy(how = How.CSS, using = ".modal-dialog .modal-body button.list-group-item.active .glyphicon.glyphicon-leaf")
	protected List<WebElement> linkToOtherSources;
	
	@FindBy(how = How.CSS, using = ".modal-dialog .modal-body button.list-group-item.active .glyphicon.glyphicon-leaf")
	protected WebElement linkToFirstOtherSource;
	
	@FindBy(how = How.XPATH, using = "//h3[contains(text(),'Investigation Markers')]")
	protected WebElement investigationMarkers;

	@FindBy(how = How.ID, using = "geolocation_marker")
	protected WebElement geoLocationMarker;
	
	protected String boxItemXPattern = "//*[@id='boxType']/ul[@class='dropdown-menu']/li/a[text()='%s ']";
	protected String boxMarkerXPattern = "//div[@class='list-group']/a[starts-with(text(), '%s')]";
	protected By mapKey = By.cssSelector(".map[id='map']>.ol-viewport > canvas");
	
	public MobileInvestigatePage(){
		super(STRURLPath);
		pageKey = By.cssSelector("[id='legend-dropdown'].dropdown-toggle");
		Log.info("The Investigate Page URL is: " + this.strPageURL);
	}
	
	public void open(String boxId) {
		strPageURL = String.format(strPageURL, boxId);
		driver.get(strPageURL);
		waitUntilPageLoad();
	}
	
	public void clickOnInvestigate(InvestigationEntity investigationEntity){
		waitForElementToBeClickable(buttonInvestigate);
		buttonInvestigate.click();
		investigationEntity.setInvestigationStatus(false);
		waitForElementToBeClickable(buttonAddSource);
	}
	
	public void clickOnAddSource(){
		buttonAddSource.click();
		waitForElementToBeClickable(buttonAddLeak);
	}

	public void clickOnAddCGI(){
		buttonAddSource.click();
		waitForElementToBeClickable(buttonAddCGI);
	}
	
	public MobileLeakSourcePage deleteLeaks(){
		MobileLeakSourcePage mobileLeakSourcePage;
		waitForElementToBeClickable(buttonAddLeak);
		while(WebElementExtender.isElementPresentAndDisplayed(linkToFirstLeak)){
			mobileLeakSourcePage = clickToOpenLeakDetail(linkToFirstLeak);
			mobileLeakSourcePage.clickDeleteButton();
			waitForElementToBeClickable(buttonAddLeak);
		}
		return waitForLeakSourcePage();
	}
	
	public MobileLeakSourcePage deleteOtherSources(){
		MobileLeakSourcePage mobileLeakSourcePage;
		waitForElementToBeClickable(buttonAddLeak);
		while(WebElementExtender.isElementPresentAndDisplayed(linkToFirstOtherSource)){
			mobileLeakSourcePage = clickToOpenLeakDetail(linkToFirstOtherSource);
			mobileLeakSourcePage.clickDeleteButton();
			waitForElementToBeClickable(buttonAddLeak);
		}
		return waitForLeakSourcePage();
	}	

    public MobileLeakSourcePage openLeakDetail(){
    	return clickToOpenLeakDetail(linkToFirstLeak);
    }

    public boolean isLeakShowing(){
    	return WebElementExtender.isElementPresentAndDisplayed(linkToFirstLeak);
    }

    public boolean isOtherSourceShowing(){
    	return WebElementExtender.isElementPresentAndDisplayed(linkToFirstOtherSource);
    }
    
    public MobileLeakSourcePage openOtherSourceDetail(){
    	return clickToOpenLeakDetail(linkToFirstOtherSource);
    }
    
	public MobileLeakSourcePage clickToOpenLeakDetail(WebElement leak){
		leak.click();
		return waitForLeakSourcePage();
	}
	public void clickOnMarkAsComplete(InvestigationEntity investigationEntity){
		clickOnMarkAsComplete(investigationEntity, true);
	}
	
	public MobileInvestigationPage clickOnMarkAsComplete(InvestigationEntity investigationEntity, boolean gasSourceFound){
		buttonComplete.click();
		if(WebElementExtender.waitForElementToBeDisplayed(timeout, driver, confirmYesBy)){
			if(gasSourceFound){
				confirmYes.click();
			}else{
				confirmNo.click();
			}
			investigationEntity.setSourceConfirmed(gasSourceFound);
		}
		investigationEntity.setInvestigationStatus(true);
		MobileInvestigationPage investigationPage = new MobileInvestigationPage();
		investigationPage.waitUntilPageLoad();
		return investigationPage; 
	}
	
	public MobileInvestigationPage clickOnPauseInvestigation(){
		buttonPause.click();
		WebElementExtender.waitForElementToBeClickable(timeout, driver, investigationMarkers);
		MobileInvestigationPage investigationPage = new MobileInvestigationPage();
		investigationPage.waitUntilPageLoad();
		return investigationPage; 
	}

	public void clickOnFollow(){
		buttonFollow.click();
		waitForAJAXCallsToComplete();
		waitUntilPageLoad(mapKey);
	}
	
	public void clickOnDirections(){
		buttonDirections.click();
		waitForElementToBeClickable(gmapContainer);
	}
	
	public MobileLeakSourcePage clickOnAddLeak(){
		buttonAddLeak.click();
		return waitForLeakSourcePage();
	}
	
	public MobileLeakSourcePage waitForLeakSourcePage(){
		MobileLeakSourcePage detailsPage = new MobileLeakSourcePage();
		detailsPage.waitUntilPageLoad();
		return detailsPage;
	}
	
	public MobileLeakSourcePage clickOnAddOtherSource(){
		buttonAddOtherSource.click();
		MobileLeakSourcePage otherSourcePage = new MobileLeakSourcePage();
		otherSourcePage.waitUntilPageLoad();
		return otherSourcePage;
	}
	
	public boolean isGeoLocationMarkerShowing(){
		return WebElementExtender.isElementPresentAndDisplayed(geoLocationMarker);
	}
	
	public boolean verifyScreenshotWithBaseline(String testCaseID, String name) throws IOException{
		return verifyScreenshotWithBaseline(testCaseID, name, new Rectangle(0,200,0,0));
	}
}