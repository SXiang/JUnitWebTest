/**
 * 
 */
package surveyor.scommon.source;

import java.util.List;

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
public class ManageAnalyzersPage extends BasePage {
	public static final String STRURLPath = "/Picarro/ManageAnalyzers";
	public static final String STRPageTitle = "Manage Analyzers - Surveyor";
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div/div[1]/div[1]/a")
	private WebElement btnAddNewAnalyzer;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Administrator')]")
	private WebElement dropDownAdministrator;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Log Out')]")
	private WebElement linkLogOut;
	
	@FindBy(how = How.XPATH, using = "//*[@id='SerialNumber']")
	private WebElement inputSerialNumber;
	
	@FindBy(how = How.XPATH, using = "//*[@id='SharedKey']")
	private WebElement inputSharedKey;
	
	@FindBy(how = How.XPATH, using = "//*[@id='SurveyorUnitId']")
	private WebElement dropDownSurveyor;
	
	@FindBy(how = How.XPATH, using = "//*[@id='buttonCustomerOk']")
	private WebElement btnOK;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody")
	private WebElement analyzerTB;	
	
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
	public ManageAnalyzersPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);
		
		System.out.println("\nThe Manager Analyzers Page URL is: " + this.strPageURL);
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
	
	public void addNewAnalyzer(String serialNumber, String sharedKey, String surveyor) {
		if (this.testSetup.isRunningDebug()) {
			System.out.println(serialNumber);
			System.out.println(sharedKey);
			System.out.println(surveyor);
		}
		
		this.btnAddNewAnalyzer.click();
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);
		
		this.inputSerialNumber.sendKeys(serialNumber);
		this.inputSharedKey.sendKeys(sharedKey);
		
		List<WebElement> options = this.dropDownSurveyor.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if(surveyor.equals(option.getText().trim()))
				option.click();		
		}		
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(8);
		
		this.btnOK.click();		
	}
	
	public void addNewAnalyzer(String serialNumber, String sharedKey, String surveyor, String customerName, String locationName) {
		if (this.testSetup.isRunningDebug()) {
			System.out.println(serialNumber);
			System.out.println(sharedKey);
			System.out.println(surveyor);
			System.out.println(customerName);
			System.out.println(locationName);
		}
		
		this.btnAddNewAnalyzer.click();
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);
		
		this.inputSerialNumber.sendKeys(serialNumber);
		this.inputSharedKey.sendKeys(sharedKey);
		
		List<WebElement> options = this.dropDownSurveyor.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if ((customerName + " - " + locationName + " - " + surveyor).equalsIgnoreCase(option.getText().trim())) {
				option.click();
			}
		}
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);
		
		this.btnOK.click();
	}
	
	public boolean findExistingAnalyzer(String customerName, String locationName, String surveyorName, String analyzerName) {
		paginationInput.sendKeys("100");
		
		//For time being, more generic code should be implemented for iterating the table elements
		List<WebElement> rows = analyzerTB.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowNum = 1;
		for (WebElement row : rows) {
			List<WebElement> cols = analyzerTB.findElements(By.xpath("//*[@id='datatable']/tbody/tr["+rowNum+"]/td"));
			
			int colNum = 1;
			for (WebElement col : cols) {
				if (colNum == 1 && col.getText().equalsIgnoreCase(customerName)) {
					String strLocationXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
					WebElement location = analyzerTB.findElement(By.xpath(strLocationXPath));
					if (location.getText().equalsIgnoreCase(locationName)) {
						String strSurveyorXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[3]";
						WebElement surveyor = analyzerTB.findElement(By.xpath(strSurveyorXPath));
						if (surveyor.getText().equalsIgnoreCase(surveyorName)) {
							String strAnalyzerXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[4]";
							WebElement analyzer = analyzerTB.findElement(By.xpath(strAnalyzerXPath));
							if (analyzer.getText().equalsIgnoreCase(analyzerName)) {
								if (testSetup.isRunningDebug())
									System.out.format("The analyzer found is: %s", analyzer.getText());
								return true;
							}	
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
	
	public void editExistingAnalyzer(String customerName, String locationName, String surveyorName) {
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}