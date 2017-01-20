package surveyor.scommon.mobile.source;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.source.Log;

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
	
	protected String boxItemXPattern = "//*[@id='boxType']/ul[@class='dropdown-menu']/li/a[text()='%s ']";
	protected String boxMarkerXPattern = "//div[@class='list-group']/a[starts-with(text(), '%s')]";
	
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
	
	public void clickOnInvestigate(){
		buttonInvestigate.click();
	}
	
	public void clickOnAddSource(){
		buttonAddSource.click();
	}
	
	public void clickOnMarkAsComplete(){
		buttonComplete.click();
	}

	public void clickOnFollow(){
		buttonFollow.click();
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
}