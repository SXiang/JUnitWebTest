/**
 * 
 */
package surveyor.scommon.source;

import java.util.List;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.FindBy;

import common.source.BasePage;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class ManageSurveyorPage extends BasePage {
	public static final String STRURLPath = "/Picarro/ManageSurveyors";
	public static final String STRPageTitle = "Manage Surveyors - Surveyor";
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div/div[1]/div[1]/a")
	private WebElement btnAddNewSurveyor;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Administrator')]")
	private WebElement dropDownAdministrator;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Log Out')]")
	private WebElement linkLogOut;
	
	@FindBy(how = How.XPATH, using = "//*[@id='Description']")
	private WebElement inputSurveyorDesc;
	
	@FindBy(how = How.XPATH, using = "//*[@id='LocationId']")
	private WebElement dropDownLocation;
	
	@FindBy(how = How.XPATH, using = "//*[@id='buttonCustomerOk']")
	private WebElement btnOK;
	
	//add more @FindBy here later
	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public ManageSurveyorPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);
		
		System.out.println("\nThe Manager Surveyor Page URL is: " + this.strPageURL);
	}
	
	public LoginPage logout() {
		this.dropDownAdministrator.click();
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(1);
		
		this.linkLogOut.click();
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);
		
		LoginPage loginPage = new LoginPage(this.driver, this.strBaseURL, this.testSetup);
		
		return loginPage;
	}
	
	public void addNewSurveyor(String surveyorDesc, String location) {
		if (this.testSetup.isRunningDebug()) {
			System.out.println(surveyorDesc);
			System.out.println(location);
		}
		
		this.btnAddNewSurveyor.click();
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);
		
		this.inputSurveyorDesc.sendKeys(surveyorDesc);
		this.dropDownLocation.sendKeys(location);
		
//		Select select = new Select(this.dropDownLocation);
//		List <WebElement> items =  select.getOptions();
//		
//		for (int i = 0; i < items.size(); i++) {
//			System.out.println("\nThe item text is: " + items.get(i).getText());
//			System.out.println("The i is: " + Integer.toString(i));
//			
//			if (items.get(i).getText().contains(location)) {
//				select.selectByIndex(i);
//				break;
//			}
//		}		
		
		this.btnOK.click();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
