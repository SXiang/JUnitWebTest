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

import common.source.TestSetup;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author pmahajan
 * 
 */
public class ManageSurveyorHistoriesPage extends SurveyorBasePage {
	public static final String STRURLPath = "/Picarro/ManageSurveyorHistories";
	public static final String STRPageTitle = "Manage Surveyor Histories - Surveyor";

	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a")
	protected WebElement btnAddNewHistoryEntry;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Administrator')]")
	protected WebElement dropDownAdministrator;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Log Out')]")
	protected WebElement linkLogOut;

	@FindBy(how = How.XPATH, using = "//*[@id='Note']")
	protected WebElement textareaNote;

	@FindBy(how = How.XPATH, using = "//*[@id='SurveyorUnitId']")
	protected WebElement dropDownSurveyorUnit;

	@FindBy(how = How.XPATH, using = "//*[@id='buttonCustomerOk']")
	protected WebElement btnOK;

	@FindBy(how = How.XPATH, using = "//*[@id='surveyor-history-form']/fieldset/div[3]/div[2]/a")
	protected WebElement btnCancel;

	// add more @FindBy here later

	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public ManageSurveyorHistoriesPage(WebDriver driver, String baseURL,
			TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);

		System.out.println("\nThe Manager Surveyor Histories Page URL is: "
				+ this.strPageURL);
	}

	public ManageSurveyorHistoriesPage(WebDriver driver, String baseURL,
			TestSetup testSetup, String urlPath) {
		super(driver, testSetup, baseURL, baseURL + urlPath);
	}

	public LoginPage logout() {
		this.dropDownAdministrator.click();

		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(1);

		this.linkLogOut.click();

		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);

		LoginPage loginPage = new LoginPage(this.driver, this.strBaseURL,
				this.testSetup);

		return loginPage;
	}

	public void addNewHistoryNote(String surveyorUnit, String note) {
		if (this.testSetup.isRunningDebug()) {
			System.out.println(surveyorUnit);
			System.out.println(note);
		}

		this.btnAddNewHistoryEntry.click();

		List<WebElement> options = this.dropDownSurveyorUnit.findElements(By
				.tagName("option"));
		for (WebElement option : options) {
			if (surveyorUnit.equals(option.getText().trim()))
				option.click();
		}

		this.textareaNote.sendKeys(note);

		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(5);

		this.btnOK.click();
	}

	public void addNewHistoryNote(String surveyorUnit, String locationName,
			String note) {
		if (this.testSetup.isRunningDebug()) {
			System.out.println(surveyorUnit);
			System.out.println(locationName);
			System.out.println(note);
		}

		this.btnAddNewHistoryEntry.click();

		List<WebElement> options = this.dropDownSurveyorUnit.findElements(By
				.tagName("option"));
		for (WebElement option : options) {
			if (surveyorUnit.equals(locationName + " - "
					+ option.getText().trim()))
				option.click();
		}

		this.textareaNote.sendKeys(note);

		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(5);

		this.btnOK.click();
	}

	public void addNewHistoryNote(String surveyorUnit, String locationName,
			String customerName, String note) {
		if (this.testSetup.isRunningDebug()) {
			System.out.println(surveyorUnit);
			System.out.println(locationName);
			System.out.println(customerName);
			System.out.println(note);
		}

		this.btnAddNewHistoryEntry.click();

		List<WebElement> options = this.dropDownSurveyorUnit.findElements(By
				.tagName("option"));
		for (WebElement option : options) {
			if (option
					.getText()
					.trim()
					.equalsIgnoreCase(
							customerName + " - " + locationName + " - "
									+ surveyorUnit))
				option.click();
		}

		this.textareaNote.sendKeys(note);

		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);

		this.btnOK.click();
	}

	public boolean findExistingHistoryNote(String customerName,
			String locationName, String surveyorName, String note) {
		setPagination(PAGINATIONSETTING);

		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

		String customerNameXPath;
		String locationNameXPath;
		String surveyorNameXPath;
		String historyNoteXPath;

		WebElement customerNameCell;
		WebElement locationNameCell;
		WebElement surveyorNameCell;
		WebElement historyNoteCell;

		List<WebElement> rows = table.findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[1]";
			locationNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[2]";
			surveyorNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[3]";
			historyNoteXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[4]";

			customerNameCell = table.findElement(By.xpath(customerNameXPath));
			locationNameCell = table.findElement(By.xpath(locationNameXPath));
			surveyorNameCell = table.findElement(By.xpath(surveyorNameXPath));
			historyNoteCell = table.findElement(By.xpath(historyNoteXPath));

			if ((customerNameCell.getText().trim())
					.equalsIgnoreCase(customerName)
					&& (locationNameCell.getText().trim())
							.equalsIgnoreCase(locationName)
					&& (surveyorNameCell.getText().trim())
							.equalsIgnoreCase(surveyorName)
					&& (historyNoteCell.getText().trim())
							.equalsIgnoreCase(note)) {
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);

				rowNum = 0;
			}
		}
		return false;
	}
	
	public void clickOnAddNewHistoryEntryBtn() {
		this.btnAddNewHistoryEntry.click();
	}
	
	public void clickOnCancelBtn() {
		this.btnCancel.click();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}