/**
 * 
 */
package surveyor.scommon.source;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.FindBy;
import org.w3c.dom.Document;

import common.source.BasePage;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class ManageCustomersPage extends BasePage {
	public static final String STRURLPath = "/Picarro/ManageCustomers";
	public static final String STRPageTitle = "Manage Customers - Surveyor";
	
	//@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div/div[1]/div[1]/a")
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a")
	private WebElement btnAddNewCustomer;
	
	//@FindBy(how = How.XPATH, using = "//a[contains(@href, '#')]")
	//@FindBy(how = How.XPATH, using = "/html/body/div[1]/div/ul/li")
	//@FindBy(how = How.XPATH, using = "/html/body/div[1]/div/ul/li/a")
	@FindBy(how = How.XPATH, using = "//*[@id='wrapper']/nav/ul/li/a")
	private WebElement dropDownAdministrator;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Log Out')]")
	private WebElement linkLogOut;
	
	@FindBy(how = How.XPATH, using = "//*[@id='Name']")
	private WebElement inputCustomerName;
	
	@FindBy(how = How.XPATH, using = "//*[@id='Eula']")
	private WebElement textAreaEula;
	
	@FindBy(how = How.XPATH, using = "//*[@id='buttonCustomerOk']")
	private WebElement okButton;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody")
	private WebElement customerTB;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable_length']/label/select")
	private WebElement paginationInput;
	
	//@FindBy(how = How.XPATH, using = "//a[contains(text(),'Next')]")
	@FindBy(how = How.XPATH, using = "//*[@id='datatable_next']")
	private WebElement nextBtn;
	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public ManageCustomersPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);
		
		System.out.println("\nThe Manager Customers Page URL is: " + this.strPageURL);
	}
	
	public LoginPage logout() {
		this.dropDownAdministrator.click();
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);
		
		this.linkLogOut.click();
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);
		
		LoginPage loginPage = new LoginPage(this.driver, this.strBaseURL, this.testSetup);
		
		return loginPage;
	}
	
	public void addNewCustomer(String customerName, String eula) {
		if (this.testSetup.isRunningDebug()) {
			System.out.println(customerName);
			System.out.println(eula);
			System.out.println();
		}
		
		this.btnAddNewCustomer.click();
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);
		
		this.inputCustomerName.sendKeys(customerName);
		this.textAreaEula.sendKeys(eula);
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);
		
		this.okButton.click();
	}
	
	public boolean findExistingCustomer(String customerName) {
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		paginationInput.sendKeys("100");
		
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		String customerNameXPath;
		WebElement customerNameCell;
		
		//More generic code should be implemented for iterating the table elements
		List<WebElement> rows = customerTB.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < 100)
			loopCount = rowSize;
		else
			loopCount = 100;
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			customerNameCell = customerTB.findElement(By.xpath(customerNameXPath));
			
			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName))
				return true;
			
			if (rowNum == 100 && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = customerTB.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				
				rowSize = newRows.size();
				
				if (rowSize < 100)
					loopCount = rowSize;
				else
					loopCount = 100;
				
				rowNum = 1;
			}			
		}
		
		return false;
	}

	public void editExistingCustomerName(String customerName, String newCustomerName) {
		paginationInput.sendKeys("100");
		
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		String customerNameXPath;
		WebElement customerNameCell;
		
		//More generic code should be implemented for iterating the table elements
		List<WebElement> rows = customerTB.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < 100)
			loopCount = rowSize;
		else
			loopCount = 100;
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			customerNameCell = customerTB.findElement(By.xpath(customerNameXPath));
			
			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName)) {
				String strEditXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[3]/a";
				//WebElement editBtn = customerTB.findElement(By.xpath(strEditXPath));
				//editBtn.click();
				
				WebElement editBtn = driver.findElement(By.xpath(strEditXPath));
				JavascriptExecutor jsexe = (JavascriptExecutor)driver;
				jsexe.executeScript("arguments[0].click();", editBtn);

				this.inputCustomerName.clear();
				this.inputCustomerName.sendKeys(newCustomerName);
				this.textAreaEula.clear();
				this.textAreaEula.sendKeys(newCustomerName + ": Testing");
				this.okButton.click();
				
				return;
			}
			
			if (rowNum == 100 && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = customerTB.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				
				rowSize = newRows.size();
				
				if (rowSize < 100)
					loopCount = rowSize;
				else
					loopCount = 100;
				
				rowNum = 1;
			}			
		}
	}	
	
//	public void editExistingCustomerName(String customerName, String newCustomerName) {
//		paginationInput.sendKeys("100");
//		
//		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
//		
//		//More generic code should be implemented for iterating the table elements
//		List<WebElement> rows = customerTB.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
//		
//		int rowNum = 1;
//		for (WebElement row : rows) {
//			if (rowNum > rows.size())
//				break;
//			
//			List<WebElement> cols = row.findElements(By.xpath("//*[@id='datatable']/tbody/tr["+rowNum+"]/td"));
//			
//			int colNum = 1;
//			for (WebElement col : cols) {
//				if (testSetup.isRunningDebug()) {
//					System.out.println("Row "+ rowNum + " Col " + colNum + ": " + col.getText());
//				}
//				
//				if (colNum == 1 && col.getText().equalsIgnoreCase(customerName)) {
//					String strEditXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[3]/a";
//					WebElement editBtn = driver.findElement(By.xpath(strEditXPath));
//					editBtn.click();
//					
//					if (testSetup.isRunningDebug()) {
//						testSetup.slowdownInSeconds(3);
//					}
//
//					this.inputCustomerName.clear();
//					this.inputCustomerName.sendKeys(newCustomerName);
//					this.okButton.click();
//					
//					rowNum = rows.size();
//					break;
//				}
//				
//				colNum = colNum + 1;
//			}
//			
//			rowNum = rowNum + 1;
//		}
//	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}