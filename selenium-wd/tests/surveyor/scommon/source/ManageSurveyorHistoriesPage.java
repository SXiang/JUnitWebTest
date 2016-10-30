/**
 * 
 */
package surveyor.scommon.source;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;

import common.source.Log;
import common.source.TestSetup;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;

import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author pmahajan
 * 
 */
public class ManageSurveyorHistoriesPage extends SurveyorBasePage {
	public static final String STRURLPath = "/Picarro/ManageSurveyorHistories";
	public static final String STRPageTitle = Resources.getResource(ResourceKeys.Layout_Nav_ManageSurveyorHistories);
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.ManageSurveyorHistories_PageTitle);
	public static final String STRNewPageContentText = Resources.getResource(ResourceKeys.ManageSurveyorHistory_PageTitle);

	private static final int DATETIME_COL_IDX = 5;

	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a")
	protected WebElement btnAddNewHistoryEntry;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Administrator')]")
	protected WebElement dropDownAdministrator;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Log out')]")
	protected WebElement linkLogOut;

	@FindBy(how = How.XPATH, using = "//*[@id='Note']")
	protected WebElement textareaNote;

	@FindBy(how = How.XPATH, using = "//*[@id='SurveyorUnitId']")
	protected WebElement dropDownSurveyorUnit;

	@FindBy(how = How.XPATH, using = "//*[@id='buttonOk']")
	protected WebElement btnOK;

	@FindBy(how = How.XPATH, using = "//*[@id='surveyor-history-form']/fieldset/div[3]/div[2]/a")
	protected WebElement btnCancel;

	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public ManageSurveyorHistoriesPage(WebDriver driver, String baseURL,
			TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);

		Log.info("\nThe Manage Surveyor Histories Page URL is: "
				+ this.strPageURL);
	}

	public ManageSurveyorHistoriesPage(WebDriver driver, String baseURL,
			TestSetup testSetup, String urlPath) {
		super(driver, testSetup, baseURL, baseURL + urlPath);
	}

	public void addNewHistoryNote(String surveyorUnit, String note) {
		Log.method("addNewHistoryNote", surveyorUnit, note);
		if (this.testSetup.isRunningDebug()) {
			Log.info(surveyorUnit);
			Log.info(note);
		}

		Log.clickElementInfo("Add New History Entry");
		this.btnAddNewHistoryEntry.click();
		this.waitForNewPageLoad();

		List<WebElement> options = this.dropDownSurveyorUnit.findElements(By
				.tagName("option"));
		for (WebElement option : options) {
			if (surveyorUnit.equals(option.getText().trim())){
				Log.info("Select Surveyor Unit - '"+surveyorUnit+"'");
				option.click();
				break;
			}
		}

		Log.info("Set note - '"+note+"'");
		this.textareaNote.sendKeys(note);

		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(5);

		Log.clickElementInfo("Ok");
		this.btnOK.click();
		this.waitForPageLoad();
	}

	public void addNewHistoryNote(String surveyorUnit, String locationName, String note) {
		Log.method("addNewHistoryNote", surveyorUnit, locationName, note);
		if (this.testSetup.isRunningDebug()) {
			Log.info(surveyorUnit);
			Log.info(locationName);
			Log.info(note);
		}

		Log.clickElementInfo("Add New History Entry");
		this.btnAddNewHistoryEntry.click();
		this.waitForNewPageLoad();

		List<WebElement> options = this.dropDownSurveyorUnit.findElements(By
				.tagName("option"));
		for (WebElement option : options) {
			if (surveyorUnit.equals(locationName + " - "
					+ option.getText().trim())){
				Log.info("Select Surveyor Unit - '"+surveyorUnit+"'");
				option.click();
				break;
			}
		}

		Log.info("Set Note - '"+note+"'");
		this.textareaNote.sendKeys(note);

		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(5);
		Log.clickElementInfo("Ok");
		this.btnOK.click();
		this.waitForPageLoad();
	}

	public void addNewHistoryNote(String surveyorUnit, String locationName, String customerName, String note) {
		Log.method("addNewHistoryNote", surveyorUnit, locationName, customerName, note);
		if (this.testSetup.isRunningDebug()) {
			Log.info(surveyorUnit);
			Log.info(locationName);
			Log.info(customerName);
			Log.info(note);
		}

		Log.clickElementInfo("Add New History Entry");
		this.btnAddNewHistoryEntry.click();
		this.waitForNewPageLoad();

		List<WebElement> options = this.dropDownSurveyorUnit.findElements(By
				.tagName("option"));
		for (WebElement option : options) {
			if (option
					.getText()
					.trim()
					.equalsIgnoreCase(
							customerName + " - " + locationName + " - "
									+ surveyorUnit)){
				Log.info("Select Surveyor Unit - '"+option.getText()+"'");
				option.click();
				break;
			}
		}

		Log.info("Set Note - '"+note +"'");
		this.textareaNote.sendKeys(note);

		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);

		Log.clickElementInfo("Ok");
		this.btnOK.click();
		this.waitForPageLoad();
	}

	public boolean findExistingHistoryNote(String customerName, String locationName, String surveyorName, String note) {
		Log.method("findExistingHistoryNote", customerName, locationName, surveyorName, note);
		Log.info(String.format("Find history note %s, customer = '%s', location = '%s', surveyor = '%s'", 
				note,customerName,locationName,surveyorName));
		setPagination(PAGINATIONSETTING_100);

		// Click on datetime column.
		this.clickOnColumnHeader(DATETIME_COL_IDX, 2 /*number of times to click*/);

		String customerNameXPath;
		String locationNameXPath;
		String surveyorNameXPath;
		String historyNoteXPath;

		WebElement customerNameCell;
		WebElement locationNameCell;
		WebElement surveyorNameCell;
		WebElement historyNoteCell;

		List<WebElement> rows = getTable().findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[1]";
			locationNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[2]";
			surveyorNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[3]";
			historyNoteXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[4]";

			customerNameCell = getTable().findElement(By.xpath(customerNameXPath));
			locationNameCell = getTable().findElement(By.xpath(locationNameXPath));
			surveyorNameCell = getTable().findElement(By.xpath(surveyorNameXPath));
			historyNoteCell = getTable().findElement(By.xpath(historyNoteXPath));

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

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				toNextPage();
				List<WebElement> newRows = getTable().findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}
		Log.error(String.format("History note not found: %s, customer = '%s', location = '%s', surveyor = '%s'", 
				note,customerName,locationName,surveyorName));
		return false;
	}
	
	public void clickOnAddNewHistoryEntryBtn() {
		Log.clickElementInfo("Add New History Entry");
		this.btnAddNewHistoryEntry.click();
	}
	
	public void clickOnCancelBtn() {
		Log.clickElementInfo("Cancel");
		this.btnCancel.click();
	}

	public WebElement getBtnAddNewHistoryEntry() {
		Log.clickElementInfo("Add New History Entry");
		return btnAddNewHistoryEntry;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

    @Override
	public void waitForPageLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(STRPageContentText);
            }
        });
    }

	public void waitForNewPageLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(STRNewPageContentText);
            }
        });
    }
}