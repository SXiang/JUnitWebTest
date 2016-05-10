/**
 * 
 */
package surveyor.scommon.source;

import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING_100;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import common.source.Log;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class ManageRefGasBottlesPage extends SurveyorBasePage {
	public static final String STRURLPATH = "/Picarro/ManageRefGasBottles";
	public static final String STRPAGETITLE = Resources
			.getResource(ResourceKeys.ManageRefGasBottles_PageTitle);
	public static final String STRPageContentText = Resources
			.getResource(ResourceKeys.ManageRefGasBottles_PageTitle);
	public static final String STRNewPageContentText = Resources
			.getResource(ResourceKeys.AddRefGasBottle_PageTitle);
	
	

	@FindBy(css = "a[class='btn btn-primary']")
	public WebElement btnAddNewRefGasBottle;

	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div[1]")
	private WebElement panelDupRgbError;
	private String panelDupRgbErrorXPath = "//*[@id='page-wrapper']/div/div[2]/div[1]";

	@FindBy(id = "BatchId")
	public WebElement inputLotNumber;
	
	public WebElement getEthMthRtoErr() {
		return this.inputEthMthRto;
	}

	@FindBy(how = How.XPATH, using = "//*[@id='EthaneToMethaneRatio-error']")
	private WebElement ethMthRtoErr;

	@FindBy(how = How.XPATH, using = "//*[@id='EthaneToMethaneRatio']")
	public WebElement inputEthMthRto;
	
	@FindBy(how = How.XPATH, using = "//*[@id='EthaneToMethaneRatio-error']")
	private WebElement ethMthRtoErr;

	
	@FindBy(how = How.XPATH, using = "//*[@id='IsotopicValue']")
	public WebElement inputIsoValue;

	@FindBy(how = How.XPATH, using = "//*[@id='SurveyorUnitId']")
	private WebElement dropdownSurveyor;

	@FindBy(id = "buttonOk")
	private WebElement btnOK;

	@FindBy(how = How.XPATH, using = "//*[@class='button-cancel btn btn-danger']")
	private WebElement btnCancel;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr")
	protected List<WebElement> rows;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[1]")
	protected WebElement tdLocationValue;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[2]")
	protected WebElement tdSurveyorValue;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[3]")
	protected WebElement tdAnalyzerValue;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[4]")
	protected WebElement tdLotNumValue;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[5]")
	protected WebElement tdIsoValue;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/thead/tr/th[2]")
	protected WebElement theadSurveyor;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/thead/tr/th[3]")
	protected WebElement theadAnalyzer;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/thead/tr/th[4]")
	protected WebElement theadLotNumber;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/thead/tr/th[5]")
	protected WebElement theadIsoValue;
	
	@FindBy(how = How.CSS, using = "label#BatchId-error")
	protected WebElement lotNumberError;

	@FindBy(how = How.CSS, using = "label#IsotopicValue-error")
	protected WebElement isotopicValueError;
	
	@FindBy(how = How.XPATH, using = "//*[@id='ref-gas-bottle-form']/fieldset/div[3]/label")
	protected WebElement ethMethRtoLbl;
	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 */
	public ManageRefGasBottlesPage(WebDriver driver, TestSetup testSetup,
			String strBaseURL) {
		super(driver, testSetup, strBaseURL, strBaseURL + STRURLPATH);

		Log.info("\nThe Manage Ref Gas Bottles Page URL is: %s\n" +
				this.strPageURL);
	}

	public ManageRefGasBottlesPage(WebDriver driver, TestSetup testSetup,
			String baseURL, String urlPath) {
		super(driver, testSetup, baseURL, baseURL + urlPath);
	}

	public void addNewRefGasBottle(String strLotNumber, String strIsoValue,
			String strCusName, String strLocName, String strSurveyor) {
		this.addRefGasBottle(strLotNumber, strIsoValue, "1", strCusName, strLocName, strSurveyor);
	}

	public void addNewRefGasBottle(String strLotNumber, String strIsoValue, String ethMthRto,
			String strCusName, String strLocName, String strSurveyor) {
		this.addRefGasBottle(strLotNumber, strIsoValue, ethMthRto, strCusName, strLocName, strSurveyor);
	}

	public void addRefGasBottle(String strLotNumber, String strIsoValue, String ethMthRto,
			String strCusName, String strLocName, String strSurveyor) {
		addRefGasBottle(strLotNumber, strIsoValue, ethMthRto, strCusName, strLocName, strSurveyor, true);
	}
	public void addRefGasBottle(String strLotNumber, String strIsoValue, String ethMthRto,
			String strCusName, String strLocName, String strSurveyor, boolean cancelIfError) {
		this.btnAddNewRefGasBottle.click();

		this.inputLotNumber.clear();
		this.inputLotNumber.sendKeys(strLotNumber);
		this.inputIsoValue.clear();
		this.inputIsoValue.sendKeys(strIsoValue);
		
		if ((ethMthRto != null) && (ethMthRto != "")) { 
			this.inputEthMthRto.clear();
			this.inputEthMthRto.sendKeys(ethMthRto);
		}
		
		List<WebElement> options = this.dropdownSurveyor.findElements(By
				.tagName("option"));
		for (WebElement option : options) {
			if (option
					.getText()
					.trim()
					.equalsIgnoreCase(
							strCusName + " - " + strLocName + " - "
									+ strSurveyor))
				option.click();
		}
		
		this.btnOK.click();

		if(!cancelIfError){
			return;
		}
		
		if (isElementPresent(this.panelDupRgbErrorXPath)) {
			WebElement panelError = driver.findElement(By
					.xpath(this.panelDupRgbErrorXPath));
			if (panelError
					.getText()
					.equalsIgnoreCase(
							Resources
									.getResource(ResourceKeys.Validation_SummaryTitle)))
				this.btnCancel.click();
		}

		this.waitForPageLoad();
	}

	public boolean addNewRefGasBottle(String strLotNumber, String strIsoValue, String ethMthRto,  
			String strCusName, String strLocName, String strSurveyor,
			boolean bFlag) {
		this.btnAddNewRefGasBottle.click();
		this.inputLotNumber.sendKeys(strLotNumber);
		this.inputIsoValue.clear();
		this.inputIsoValue.sendKeys(strIsoValue);
		
		if ((ethMthRto != null) && (ethMthRto != "")) { 
			this.inputEthMthRto.clear();
			this.inputEthMthRto.sendKeys(ethMthRto);
		}
		
		List<WebElement> options = this.dropdownSurveyor.findElements(By
				.tagName("option"));
		for (WebElement option : options) {
			if (option
					.getText()
					.trim()
					.equalsIgnoreCase(
							strCusName + " - " + strLocName + " - "
									+ strSurveyor))
				option.click();
		}

		this.btnOK.click();

		if (bFlag == false) {
			String att1 = this.inputLotNumber.getAttribute("required");
			String att2 = this.inputIsoValue.getAttribute("required");
			if (att1 != null && att2 != null) {

				return false;
			}
		}

		if (isElementPresent(this.panelDupRgbErrorXPath)) {
			WebElement panelError = driver.findElement(By
					.xpath(this.panelDupRgbErrorXPath));
			if (panelError.getText().equalsIgnoreCase(
					"Please fill out this field.")) {
				this.btnCancel.click();
				return false;
			}
		}
		this.waitForPageLoad();
		return true;
	}

	public boolean findExistingRefGasBottle(String strLotNumber,
			String strSurveyor) {
		setPagination(PAGINATIONSETTING_100);

		this.testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		String strSurveyorXPath;
		String strLotNumberXPath;
		WebElement surveyorCell;
		WebElement lotNumberCell;


		List<WebElement> rows = table.findElements(By.xpath(strTRXPath));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			strSurveyorXPath = strTRXPath + "[" + rowNum + "]/td[3]";
			strLotNumberXPath = strTRXPath + "[" + rowNum + "]/td[5]";

			surveyorCell = getTable().findElement(By.xpath(strSurveyorXPath));
			lotNumberCell = getTable().findElement(By.xpath(strLotNumberXPath));

			if (surveyorCell.getText().equalsIgnoreCase(strSurveyor)
					&& lotNumberCell.getText().equalsIgnoreCase(strLotNumber)) {
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();

				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());

				List<WebElement> newRows = getTable().findElements(By
						.xpath(strTRXPath));
				rowSize = newRows.size();
				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}

		return false;
	}

	public boolean findExistingRefGasBottle(String strLotNumber,
			String strSurveyor, String location, String customer) {
		setPagination(PAGINATIONSETTING_100);

		this.testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		String locationXPath;
		String strSurveyorXPath;
		String strLotNumberXPath;

		WebElement locationCell;
		WebElement surveyorCell;
		WebElement lotNumberCell;

		List<WebElement> rows = getTable().findElements(By.xpath(strTRXPath));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			locationXPath = strTRXPath + "[" + rowNum + "]/td[1]";
			strSurveyorXPath = strTRXPath + "[" + rowNum + "]/td[2]";
			strLotNumberXPath = strTRXPath + "[" + rowNum + "]/td[4]";

			locationCell = getTable().findElement(By.xpath(locationXPath));
			surveyorCell = getTable().findElement(By.xpath(strSurveyorXPath));
			lotNumberCell = getTable().findElement(By.xpath(strLotNumberXPath));

			if (locationCell.getText().trim().equalsIgnoreCase(location)
					&& surveyorCell.getText().trim()
							.equalsIgnoreCase(strSurveyor)
					&& lotNumberCell.getText().trim()
							.equalsIgnoreCase(strLotNumber)) {
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();

				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());

				List<WebElement> newRows = getTable().findElements(By
						.xpath(strTRXPath));
				rowSize = newRows.size();
				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}

		return false;
	}

	public WebElement getBtnAddNewRefGasBottle() {
		return this.btnAddNewRefGasBottle;
	}

	public WebElement getInputLotNumber(){
		return this.inputLotNumber;
	}
	
	public WebElement getInputIsoValue(){
		return this.inputIsoValue;
	}
	public WebElement getBtnOk() {
		return this.btnOk;
	}

	public WebElement getBtnCancel() {
		return this.btnCancel;
	}

	public void clickOnAddNewRefGasBottleBtn() {
		this.btnAddNewRefGasBottle.click();
	}

	public void clickOnCancelBtn() {
		this.btnCancel.click();
	}

	public String getLotNumberError(){
		return this.lotNumberError.getText().trim();
	}
	
	public String getIsotopicValueError(){
		return this.isotopicValueError.getText().trim();
	}
	
	public WebElement getDropdownSurveyor(){
		return this.dropdownSurveyor;
	}
	public WebElement getEthMthRtoErr() {
		return this.ethMthRtoErr;
	}

	public WebElement getEthMethRtoLbl() {
		return this.ethMethRtoLbl;
	}
	
	@Override
	public void waitForPageLoad() {
		(new WebDriverWait(driver, timeout))
				.until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver d) {
						return d.getPageSource().contains(STRPageContentText);
					}
				});
	}

	public void waitForNewPageLoad() {
		(new WebDriverWait(driver, timeout))
				.until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver d) {
						return d.getPageSource()
								.contains(STRNewPageContentText);
					}
				});
	}

	public List<String> getLotNumberList(boolean allPages, int paginationSize) {
		List<String> lotNumList = new ArrayList<String>();

		String pageSizeStr = String.valueOf(paginationSize);
		setPagination(pageSizeStr);
		waitForPageLoad();

		String lotNumXPath;
		WebElement lotNumCell;

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(pageSizeStr))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(pageSizeStr);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			lotNumXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[4]";
			lotNumCell = getTable().findElement(By.xpath(lotNumXPath));

			lotNumList.add(lotNumCell.getText().trim());

			if (rowNum == Integer.parseInt(pageSizeStr)
					&& !this.nextBtn.getAttribute("class").contains("disabled")
					&& allPages) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = getTable().findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(pageSizeStr))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(pageSizeStr);

				rowNum = 0;
			}
		}
		return lotNumList;
	}

	public boolean searchRefGasBottle(String locationName, String surveyorName,
			String analyzerName, String lotNum, String isoValue) {
		this.getInputSearch().sendKeys(lotNum);
		try {
			if (this.tdLotNumValue.getText().contentEquals(lotNum)) {
				if (this.tdLocationValue.getText().contentEquals(locationName)) {
					if (this.tdSurveyorValue.getText().contentEquals(
							surveyorName)) {
						if (this.tdAnalyzerValue.getText().contentEquals(
								analyzerName)) {
							if (this.tdIsoValue.getText().contentEquals(
									isoValue))
								return true;
						}
					}
				}
			}
		} catch (NoSuchElementException ne) {
			Log.info(ne.toString());
			return false;
		}
		return false;
	}

	public WebElement getTheadLotNumber() {
		return this.theadLotNumber;
	}

	public WebElement getTheadSurveyor() {
		return this.theadSurveyor;
	}

	public WebElement getTheadAnalyzer() {
		return this.theadAnalyzer;
	}

	public WebElement getTheadIsoValue() {
		return this.theadIsoValue;
	}
	
	public WebElement getInputEthMthRto() {
		return this.inputEthMthRto;
	}
	
	public List<String> getSurveyorList(boolean allPages, int paginationSize) {
		List<String> surveyorList = new ArrayList<String>();

		String pageSizeStr = String.valueOf(paginationSize);
		setPagination(pageSizeStr);
		waitForPageLoad();

		String surveyorXPath;
		WebElement surveyorCell;

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(pageSizeStr))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(pageSizeStr);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			surveyorXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[2]";
			surveyorCell = getTable().findElement(By.xpath(surveyorXPath));

			surveyorList.add(surveyorCell.getText().trim());

			if (rowNum == Integer.parseInt(pageSizeStr)
					&& !this.nextBtn.getAttribute("class").contains("disabled")
					&& allPages) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = getTable().findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(pageSizeStr))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(pageSizeStr);

				rowNum = 0;
			}
		}
		return surveyorList;
	}

	public List<String> getAnalyzerList(boolean allPages, int paginationSize) {
		List<String> analyzerList = new ArrayList<String>();

		String pageSizeStr = String.valueOf(paginationSize);
		setPagination(pageSizeStr);
		waitForPageLoad();

		String analyzerXPath;
		WebElement analyzerCell;

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(pageSizeStr))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(pageSizeStr);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			analyzerXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[3]";
			analyzerCell = getTable().findElement(By.xpath(analyzerXPath));

			analyzerList.add(analyzerCell.getText().trim());

			if (rowNum == Integer.parseInt(pageSizeStr)
					&& !this.nextBtn.getAttribute("class").contains("disabled")
					&& allPages) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = getTable().findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(pageSizeStr))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(pageSizeStr);

				rowNum = 0;
			}
		}
		return analyzerList;
	}

	public List<String> getIsoValueList(boolean allPages, int paginationSize) {
		List<String> isoValueList = new ArrayList<String>();

		String pageSizeStr = String.valueOf(paginationSize);
		setPagination(pageSizeStr);
		waitForPageLoad();

		String isoValueXPath;
		WebElement isoValueCell;

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(pageSizeStr))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(pageSizeStr);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			isoValueXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[5]";
			isoValueCell = getTable().findElement(By.xpath(isoValueXPath));

			isoValueList.add(isoValueCell.getText().trim());

			if (rowNum == Integer.parseInt(pageSizeStr)
					&& !this.nextBtn.getAttribute("class").contains("disabled")
					&& allPages) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = getTable().findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(pageSizeStr))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(pageSizeStr);

				rowNum = 0;
			}
		}
		return isoValueList;
	}
}