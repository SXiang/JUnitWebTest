/**
 * 
 */
package surveyor.scommon.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.source.Log;
import common.source.TestSetup;

/**
 * @author pmahajan
 * 
 */
public class ManageReleaseNotesPage extends SurveyorBasePage {
	public static final String STRURLPath = "/Picarro/ManageReleaseNotes";
	public static final String STRPageTitle = "Manage Release Notes - Surveyor";

	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a")
	protected WebElement btnAddNewReleaseNote;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Administrator')]")
	protected WebElement dropDownAdministrator;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Log out')]")
	protected WebElement linkLogOut;

	@FindBy(how = How.XPATH, using = "//*[@id='buttonOk']")
	protected WebElement btnOK;

	@FindBy(how = How.XPATH, using = "//*[@id='release-note-form']/fieldset/div[3]/div[2]/a")
	protected WebElement btnCancel;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[3]/a")
	protected WebElement btnEditNote;

	// add more @FindBy here later

	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public ManageReleaseNotesPage(WebDriver driver, String baseURL,
			TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);

		Log.info("\nThe Manager Surveyor Histories Page URL is: "
				+ this.strPageURL);
	}

	public ManageReleaseNotesPage(WebDriver driver, String baseURL,
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
	
	public void clickOnAddNewReleaseNoteBtn() {
		this.btnAddNewReleaseNote.click();
	}
	
	public void clickOnFirstEditNoteBtn() {
		this.btnAddNewReleaseNote.click();
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