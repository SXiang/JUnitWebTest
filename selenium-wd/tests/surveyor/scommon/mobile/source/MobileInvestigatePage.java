package surveyor.scommon.mobile.source;

import java.awt.Rectangle;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
	@FindBy(how = How.CSS, using = ".modal-dialog button.btn[ng-click='modalOptions.cancel()']")
	protected WebElement confirmNo;
	
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
		buttonInvestigate.click();
		investigationEntity.setInvestigationStatus(false);
	}
	
	public void clickOnAddSource(){
		buttonAddSource.click();
	}
	
	public void clickOnMarkAsComplete(InvestigationEntity investigationEntity){
		clickOnMarkAsComplete(investigationEntity, true);
	}
	
	public void clickOnMarkAsComplete(InvestigationEntity investigationEntity, boolean gasSourceFound){
		buttonComplete.click();
		if(WebElementExtender.isElementPresentAndDisplayed(confirmYes)){
			if(gasSourceFound){
				confirmYes.click();
			}else{
				confirmNo.click();
			}
			investigationEntity.setSourceConfirmed(gasSourceFound);
		}
		investigationEntity.setInvestigationStatus(true);
	}

	public void clickOnFollow(){
		buttonFollow.click();
		waitUntilPageLoad(mapKey);
	}
	
	public void clickOnDirections(){
		buttonDirections.click();
	}
	
	public MobileLeakSourcePage clickOnAddLeak(){
		buttonAddLeak.click();
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
	
	public boolean verifyScreenshotWithBaseline(String testCaseID, String name) throws IOException{
		return verifyScreenshotWithBaseline(testCaseID, name, new Rectangle(0,200,0,0));
	}
}