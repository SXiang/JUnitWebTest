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
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody")
	private WebElement surveyorTB;	
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable_length']/label/select")
	private WebElement paginationInput;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Next')]")
	private WebElement nextBtn;	
	
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
		
		this.inputSurveyorDesc.sendKeys(surveyorDesc);
		
		List<WebElement> options = this.dropDownLocation.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if(location.equals(option.getText().trim()))
				option.click();		
		}
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(5);		
		
		this.btnOK.click();
	}
	
	public void addNewSurveyor(String surveyorDesc, String locationName, String customerName) {
		if (this.testSetup.isRunningDebug()) {
			System.out.println(surveyorDesc);
			System.out.println(locationName);
			System.out.println(customerName);
		}
		
		this.btnAddNewSurveyor.click();
		
		this.inputSurveyorDesc.sendKeys(surveyorDesc);
		
		List<WebElement> options = this.dropDownLocation.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if ((customerName + " - " + locationName).equalsIgnoreCase(option.getText().trim()))
				option.click();		
		}
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);		
		
		this.btnOK.click();
	}	
	
	public boolean findExistingSurveyor(String customerName, String locationName, String surveyorName) {
		paginationInput.sendKeys("100");
		
		//For time being, more generic code should be implemented for iterating the table elements
		List<WebElement> rows = surveyorTB.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowNum = 1;
		for (WebElement row : rows) {
			List<WebElement> cols = surveyorTB.findElements(By.xpath("//*[@id='datatable']/tbody/tr["+rowNum+"]/td"));
			
			int colNum = 1;
			for (WebElement col : cols) {
				if (colNum == 1 && col.getText().equalsIgnoreCase(customerName)) {
					String strLocationXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
					WebElement location = surveyorTB.findElement(By.xpath(strLocationXPath));
					if (location.getText().equalsIgnoreCase(locationName)) {
						String strSurveyorXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[3]";
						WebElement surveyor = surveyorTB.findElement(By.xpath(strSurveyorXPath));
						if (surveyor.getText().equalsIgnoreCase(surveyorName)) {
							if (testSetup.isRunningDebug())
								System.out.format("The surveyor found is: %s",  surveyor.getText());
							return true;
						}
					}
				}
				
				colNum = colNum + 1;
			}
			
			if (rowNum == 100 && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				rowNum = 1;
			}
			else {
				rowNum = rowNum + 1;
			}
		}
		
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}