/**
 * 
 */
package surveyor.scommon.source;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.Log;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class PreferencesPage extends SurveyorBasePage {
	public static final String STRURLPath = "/Account/Preferences";
	public static final String STRPageTitle = "Edit User Preferences";
	public static final String STRPageContentText = "Edit User Preferences";	
		
	@FindBy(how = How.ID, using = "TimeZoneId")
	private WebElement dropDownTimeZone;
	
	@FindBy(how = How.ID, using = "LocationId")
	private WebElement dropDownLocation;
	
	@FindBy(how = How.ID, using = "buttonOk")
	private WebElement btnOk;
	
	
	
	
	/**
	 * @param driver
	 * @param baseURL
	 * @param testSetup
	 */
	public PreferencesPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);
		
		Log.info("\nThe Preferences Page URL is: " + this.strPageURL);
	}
	
	
	
	@Override
	public void open() {	
		driver.get(strPageURL);
		this.waitForPageToLoad();
	}



	
	public WebElement getDropDownTimeZone() {
		return this.dropDownTimeZone;
	}



	public WebElement getDropDownLocation() {
		return this.dropDownLocation;
	}



	@Override
	public void waitForPageLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(STRPageContentText);
            }
        });
    }



	public WebElement getSelectedTimeZone() {
		Select select= new Select(this.dropDownTimeZone);
		WebElement option = select.getFirstSelectedOption();
		return option;
	}



	public WebElement getSelectedLocation() {
		Select select= new Select(this.dropDownLocation);
		WebElement option = select.getFirstSelectedOption();
		return option;
	}



	public WebElement getBtnOk() {
		return this.btnOk;
	}



	public void setSelectedTimeZone(String newTimeZone) {
		Select select= new Select(this.dropDownTimeZone);
		select.selectByVisibleText(newTimeZone);
	}
}