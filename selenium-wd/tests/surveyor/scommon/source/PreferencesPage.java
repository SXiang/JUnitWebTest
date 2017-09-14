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
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.source.SurveyorConstants.UserLanguage;

/**
 * @author zlu
 *
 */
public class PreferencesPage extends SurveyorBasePage {
	public static final String STRURLPath = "/Account/Preferences";
	public static final String STRPageTitle = Resources.getResource(ResourceKeys.Dialog_EditUserPreferences);
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.Dialog_EditUserPreferences);	
		
	@FindBy(how = How.ID, using = "TimeZoneId")
	private WebElement dropDownTimeZone;
	
	@FindBy(how = How.ID, using = "LocationId")
	private WebElement dropDownLocation;

	@FindBy(how = How.ID, using = "CultureId")
	private WebElement dropDownCulture;

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

	public void setSelectedTimeZone(String newTimeZone) {
		Log.info(String.format("Select timezone %s", newTimeZone));
		Select select= new Select(this.dropDownTimeZone);
		select.selectByVisibleText(newTimeZone);
	}

	public WebElement getDropDownCulture() {
		return dropDownCulture;
	}

	public void setSelectedCulture(String culture) {
		Log.info(String.format("Select culture %s", culture));
		Select select= new Select(this.dropDownCulture);
		select.selectByVisibleText(culture);
	}

	public void setSelectedCulture(UserLanguage culture) {
		Log.info(String.format("Select culture %s", culture));
		selectDropdownOptionByValue(this.dropDownCulture, culture.toString());
	}
	
	public WebElement getBtnOk() {
		return this.btnOk;
	}
}