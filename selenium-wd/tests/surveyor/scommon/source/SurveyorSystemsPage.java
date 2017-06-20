/**
 *
 */
package surveyor.scommon.source;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.Log;
import common.source.LogHelper;
import common.source.TestSetup;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataaccess.source.SurveyorUnit;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING_100;

/**
 * @author zlu
 *
 */
public class SurveyorSystemsPage extends SurveyorBasePage {
	public static final String STRURLPath = "/Home/SurveyorSystems";
	public static final String STRPageTitle = Resources.getResource(ResourceKeys.Constant_Surveyors) + " - " + Resources.getResource(ResourceKeys.Constant_Surveyors);
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.Constant_Surveyors);

	@FindBy(how = How.XPATH, using = "//*[@id='datatable_filter']/label/input")
	protected WebElement txtSurveyorSearch;

    @FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr")
   	protected List<WebElement> tableRows;

    @FindBy(how = How.XPATH, using = "//*[@id='datatable']")
   	protected WebElement surveyorsTable;

	public enum SurveyorStatusType {
		Online,
		Offline
	}

	public enum ColumnHeaders {
		Surveyor ("Surveyor", 1),
		Analyzer ("Analyzer", 2),
		Build ("Build", 3),
		Status ("Status", 4);

		private final Integer index;
		private final String name;

		ColumnHeaders(String nm, Integer idx) {
			name = nm;
			index = idx;
		}

		public String getName() {
			return this.name;
		}

		public Integer getIndex() {
			return this.index;
		}
	}

	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 */
	public SurveyorSystemsPage(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, testSetup, strBaseURL, strBaseURL + STRURLPath);
	}

	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public SurveyorSystemsPage(WebDriver driver, TestSetup testSetup,
			String strBaseURL, String strPageURL) {
		super(driver, testSetup, strBaseURL, strBaseURL + STRURLPath);
	}

	public boolean checkSurveyorStatus(String surveyorName, SurveyorStatusType surveyorStatus) {
		setPagination(PAGINATIONSETTING_100);

		String surveyorXPath;
		String statusXPath;

		String statusString = "Offline";
		switch (surveyorStatus) {
		case Online:
			statusString = "Online";
			break;
		case Offline:
			statusString = "Offline";
			break;
		default:
			break;
		}

		WebElement surveyorNameCell;
		WebElement statusCell;

		List<WebElement> rows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {

			surveyorXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			statusXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[4]";

			surveyorNameCell = getTable().findElement(By.xpath(surveyorXPath));
			statusCell = getTable().findElement(By.xpath(statusXPath));

			if (surveyorNameCell.getText().trim().equalsIgnoreCase(surveyorName) &&
					statusCell.getText().trim().equalsIgnoreCase(statusString)) {
				Log.info("Found entry at row=" + rowNum);
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				toNextPage();
				List<WebElement> newRows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

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

	public List<WebElement> getTableRows() {
		return tableRows;
	}

	public WebElement getSurveyorsTable() {
		return surveyorsTable;
	}

    public WebElement getTxtSurveyorSearch() {
		return txtSurveyorSearch;
	}

	public boolean verifyCustomerSpecificSurveyorsAreShown(String customerName) {
		Log.method("SurveyorSystemsPage.verifyCustomerSpecificSurveyorsAreShown", customerName);
		By tableContextBy = By.id("datatable_wrapper");
		WebElement tableContext = driver.findElement(tableContextBy);
		DataTablePage surveyTable = DataTablePage.getDataTablePage(driver, tableContext, this.testSetup, this.strBaseURL, this.strPageURL);

		final List<String> surveyors = surveyTable.getRecords(ColumnHeaders.Surveyor.getName(), -1 /* numRecords */);
		int countSrvSystemsOnPage = surveyors == null ? 0 : surveyors.size();
		Log.info(String.format("Count of SurveyorSystems on page=[%d]", countSrvSystemsOnPage));
		Log.info(LogHelper.collectionToString(surveyors, "SurveyorSystems on Page"));

		final List<SurveyorUnit> surveyorUnitsInDB = SurveyorUnit.getSurveyorUnitsByCustomer(customerName);
		int countSrvUnitsInDB = surveyorUnitsInDB == null ? 0 : surveyorUnitsInDB.size();
		Log.info(String.format("Count of SurveyorUnits in DB=[%d]", countSrvUnitsInDB));
		Log.info(LogHelper.collectionToString(surveyorUnitsInDB, "SurveyorUnits in DB"));

		if (countSrvUnitsInDB == 0 && countSrvSystemsOnPage != 0) {
			Log.error(String.format("Found 0 surveyor units for customer in DB. %d surveyor systems displayed on page.", countSrvSystemsOnPage));
			return false;
		}

		final List<String> srvUnitsInDB = surveyorUnitsInDB.stream().map(su -> su.getDescription())
			.collect(Collectors.toList());
		boolean notMatch = surveyors.stream()
			.anyMatch(s -> !srvUnitsInDB.contains(s));
		return !notMatch;
	}

	public void waitForDataTabletoLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return getSurveyorsTable().isDisplayed();
            }
        });
    }

	@Override
	public void waitForPageLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(STRPageContentText);
            }
        });
    }
}
