package surveyor.scommon.source;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.CollectionsUtil;
import common.source.Log;
import common.source.LogHelper;
import common.source.RegexUtility;
import common.source.SortHelper;
import common.source.TestSetup;
import common.source.Timeout;
import common.source.WebElementExtender;
import common.source.WebElementFunctionUtil;

public class DataTablePage extends SurveyorBasePage {

	protected SearchContext searchContext;
	protected String pagination = "100";

	@FindBy(css = ".dataTables_length select.input-sm > option")
	private List<WebElement> paginationOption;

	@FindBy(css = ".table.dataTable")
	private WebElement dataTable;
	@FindBy(css = ".table.dataTable > tbody >tr")
	private List<WebElement> tableRow;
	@FindBy(css = ".table.dataTable > thead > tr > th")
	private List<WebElement> tableHeader;

	@FindBy(css = ".paginate_button.next")
	private WebElement nextButton;
	@FindBy(css = ".paginate_button.previous")
	private WebElement previousButton;
	@FindBy(css = ".paginate_button.last")
	private WebElement lastButton;
	@FindBy(css = ".paginate_button.first")
	private WebElement firstButton;
	@FindBy(css = ".paginate_button.current")
	private WebElement currentButton;

	public enum TableColumnType {
		Number("Number"), String("String"), Date("Date");
		private final String name;

		TableColumnType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}

		public static TableColumnType getTableColumnType(String type){
			if(type.equalsIgnoreCase("Number")){
				return Number;
			}else if(type.equalsIgnoreCase("String")){
				return String;
			}else if(type.equalsIgnoreCase("Date")){
				return Date;
			}else{
				return String;
			}
		}
	}

	private DataTablePage(WebDriver driver, TestSetup testSetup, String strBaseURL, String strPageURL) {
		super(driver, testSetup, strBaseURL, strPageURL);
	}

	/**
	 *
	 * @param driver
	 * @param searchContext
	 *            - The root WebElement of this datatable
	 * @return instance of this page
	 */
	public static DataTablePage getDataTablePage(WebDriver driver, SearchContext searchContext, TestSetup testSetup, String strBaseURL, String strPageURL) {
		DataTablePage tablePage = new DataTablePage(driver, testSetup, strBaseURL, strPageURL);
		tablePage.searchContext = searchContext;
		ElementLocatorFactory elf = new DefaultElementLocatorFactory(searchContext);
		PageFactory.initElements(elf, tablePage);
		return tablePage;
	}

	// **************** Test methods ***********************
	/**
	 * Find records in this data table, page by page
	 *
	 * @param filter
	 *            - conditions for matching a row, columnName:value pairs
	 * @return true if found
	 */
	public boolean hasRecord(Map<String,List<String>> filter){
    	return hasRecord(filter,true);
    }
	public boolean hasRecord(Map<String,List<String>> filter, boolean match){
    	return findRecords(filter,1,match) > 0;
    }

	/**
     * Get all records for specified column in this data table, page by page
	 * @param columnName - name of the column to get values for.
     * @param numRecords - the max number of records to be matched, search for all if it's -1
	 * @return all the records in the specified column in the table.
	 */
    public List<String> getRecords(String columnName, Integer numRecords){
    	Map<String, List<String>> records = getRecords(Arrays.asList(columnName), null /*tableContext*/, numRecords);
    	if (records.containsKey(columnName)) {
    		return records.get(columnName);
    	}

    	return null;
	}

	/**
     * Get all records for specified column names in this data table, page by page
	 * @param columnNames - list of column names to get values for.
     * @param numRecords - the max number of records to be matched, search for all if it's -1
	 * @return all the records in the specified columns of the table.
	 */
	public Map<String, List<String>> getRecords(List<String> columnNames, WebElement tableContext, Integer numRecords) {
		setPagination(pagination);
		if (tableContext == null) {
			waitForTableToLoad();
		}

		List<Integer> colIndices = getColumnIndices(columnNames);
		Map<Integer, String> colIdxNmMap = CollectionsUtil.toMap(colIndices, columnNames);
		Map<String, List<String>> recordsMap = Collections.synchronizedMap(new HashMap<String, List<String>>());
		int numFound = 0;
		boolean done = false;
		do {
			if (tableContext != null) {
				this.tableRow = tableContext.findElements(By.xpath("tbody/tr"));
			}

			Integer tableSize = this.tableRow.size();
			for(WebElement row: tableRow){
				List<WebElement> field = WebElementFunctionUtil.waitAndTryFindElements(row, driver, Timeout.TEN,
						(parentEl) -> parentEl.findElements(By.cssSelector("td")));
				colIndices.forEach(idx -> CollectionsUtil.populateListMap(recordsMap, colIdxNmMap.get(idx), getElementText(field.get(idx))));
				numFound++;
				if(numRecords>-1 && ((numFound >= tableSize) || (numFound >= numRecords))){
					done = true;
					break;
				}
			}

		} while (!done&&toNextPage());

		return recordsMap;
	}

	/**
	 * Find records in this data table, page by page
	 *
	 * @param filter
	 *            - conditions for matching a row, columnName:value pairs
	 * @return total number of matched records, -1 if not found
	 */
	public int findRecords(Map<String, List<String>> filter) {
		return findRecords(filter, -1);
	}

	/**
	 * Find records in this data table, page by page
	 *
	 * @param filter
	 *            - conditions for matching a row, columnName:value pairs
	 * @param numRecords
	 *            - the max number of records to be matched, search for all if it's -1
	 * @return number of matched records
	 */
	public int findRecords(Map<String, List<String>> filter, int numRecords) {
		return findRecords(filter, numRecords, true);
	}

	public int findRecords(Map<String, List<String>> filter, int numRecords, boolean match) {
		setPagination(pagination);
		waitForTableToLoad();
		Map<Integer, List<String>> indexedMap = new HashMap<Integer, List<String>>();
		for (Entry<String, List<String>> entry : filter.entrySet()) {
			indexedMap.put(getColumnIndex(entry.getKey().toString()), entry.getValue());
		}
		int numFound = 0;
		do {
			for (WebElement row : tableRow) {
				if (match == rowMatches(row, indexedMap)) {
					Log.warn("Record found: '" + row.getText() + "'");
					numFound++;
				}
				if (numRecords > -1 && numFound >= numRecords) {
					break;
				}
			}
		} while (toNextPage());
		return numFound;
	}

	/**
	 * Performs specified action on the matching element specified in By condition.
	 * @param elementAction
	 * @param matchFilters
	 * @param elementBy
	 * @return
	 */
	public boolean actionOnMatchingRow(By elementBy, Map<String, String> matchFilters, WebElement tableContext, Boolean applyPagination, Predicate<WebElement> elementAction) {
		Log.method("actionOnMatchingRow", elementBy, LogHelper.mapToString(matchFilters));
		WebElement row = this.getMatchingRow(matchFilters, tableContext, applyPagination);
		if (row != null) {
			WebElement element = row.findElement(elementBy);
			if (elementAction.test(element)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Find row in this data table, page by page
	 *
	 * @param filter
	 *            - conditions for matching a row, columnName:value pairs
	 *
	 * @return matched row as a WebElement
	 */
	public WebElement getMatchingRow(Map<String, String> filter) {
		Log.method("getMatchingRow", LogHelper.mapToString(filter));
		return getMatchingRow(filter, null /*tableContext*/, true /*applyPagination*/);
	}

	public WebElement getMatchingRow(Map<String, String> filter, WebElement tableContext, Boolean applyPagination) {
		Log.method("getMatchingRow", LogHelper.mapToString(filter));
		Map<String, List<String>> indexedMap = new HashMap<String, List<String>>();
		for (Entry<String, String> entry : filter.entrySet()) {
			List<String> value = new ArrayList<String>();
			value.add(entry.getValue());
			indexedMap.put(entry.getKey(), value);
		}
		return getMatchingRowOptionalInput(indexedMap, tableContext, applyPagination);
	}

	private WebElement getMatchingRowOptionalInput(Map<String, List<String>> filter, WebElement tableContext, Boolean applyPagination) {
		Log.method("getMatchingRowOptionalInput", LogHelper.mapToString(filter), applyPagination);
		if (applyPagination) {
			setPagination(pagination);
		}

		// If no table context provided, wait for table to load.
		if (tableContext == null) {
			waitForTableToLoad();
		}

		Map<Integer, List<String>> indexedMap = new HashMap<Integer, List<String>>();
		for (Entry<String, List<String>> entry : filter.entrySet()) {
			indexedMap.put(getColumnIndex(tableContext, entry.getKey().toString()), entry.getValue());
		}
		do {
			// If table context is provided, fetch rows from the table context.
			if (tableContext != null) {
				this.tableRow = tableContext.findElements(By.xpath("tbody/tr"));
			}

			for (WebElement row : this.tableRow) {
				if (rowMatches(row, indexedMap)) {
					return row;
				}
			}
		} while (toNextPage());
		return null;
	}

	//******************** Table Helpers ****************************
	/**
	 * To find matches by equals or matches
	 *
	 * @param row
	 * @param filter
	 * @return true if matches
	 */
	public boolean rowMatches(WebElement row, Map<Integer, List<String>> filter) {
		List<WebElement> field = row.findElements(By.cssSelector("td"));
		for (Entry<Integer, List<String>> entry : (filter.entrySet())) {
			String value = field.get(entry.getKey()).getText();
			List<String> expectedValues = entry.getValue();
			boolean fieldMatches = false;
			for (String expectedValue : expectedValues) {
				if (RegexUtility.equalsOrMatches(value, expectedValue)) {
					fieldMatches = true;
					break;
				}
			}
			if (!fieldMatches) {
				return false;
			}
		}
		return true;
	}

	/**
	 * check whether data in table columns are sorted Ascending
	 *
	 * @param cloumnMap
	 * @return
	 */
	public boolean isTableSortedAsc(HashMap<String, TableColumnType> cloumnMap, String str, List<WebElement> paginationOption, WebElement tableContext) {
		return isTableSortedAsc(cloumnMap, str, paginationOption, tableContext, -1);
	}

	public boolean isTableSortedAsc(HashMap<String, TableColumnType> cloumnMap, String str, List<WebElement> paginationOption, WebElement tableContext, int numRecords) {
		for (Entry<String, TableColumnType> entry : (cloumnMap.entrySet())) {
			TableColumnType columnType = cloumnMap.get(entry.getKey().trim());
			List<String> values = getRecords(entry.getKey().trim(), numRecords ).stream().map(String::toLowerCase).collect(Collectors.toList());
			if (columnType == TableColumnType.Date) {
				return SortHelper.isDateSortedASC(values.stream().toArray(String[]::new));
			}else if (columnType == TableColumnType.String){
				return SortHelper.isStringSortedASC(values.stream().toArray(String[]::new));
			}else if (columnType == TableColumnType.Number) {
				return SortHelper.isNumberSortedASC(values.stream().toArray(String[]::new));
			}
		}
		return false;
	}

	/**
	 * check whether data in table columns are sorted Descending
	 *
	 * @param cloumnMap
	 * @return
	 */
	public boolean isTableSortedDesc(HashMap<String, TableColumnType> cloumnMap, String str, List<WebElement> paginationOption, WebElement tableContext) {
		return isTableSortedDesc(cloumnMap, str, paginationOption, tableContext, -1);
	}
	public boolean isTableSortedDesc(HashMap<String, TableColumnType> cloumnMap, String str, List<WebElement> paginationOption, WebElement tableContext, int numRecords) {
		for (Entry<String, TableColumnType> entry : (cloumnMap.entrySet())) {
			TableColumnType columnType = cloumnMap.get(entry.getKey().trim());
			List<String> values = getRecords(entry.getKey().trim(), numRecords).stream().map(String::toLowerCase).collect(Collectors.toList());
			if (columnType == TableColumnType.Date) {
				return SortHelper.isDateSortedDESC(values.stream().toArray(String[]::new));
			}else if (columnType == TableColumnType.String){
				return SortHelper.isStringSortedDESC(values.stream().toArray(String[]::new));
			}else if (columnType == TableColumnType.Number) {
				return SortHelper.isNumberSortedDESC(values.stream().toArray(String[]::new));
			}
		}
		return false;
	}

	/**
	 * To find the index number of a column
	 *
	 * @param columnName
	 * @return index of the column
	 */
	public int getColumnIndex(String columnName) {
		return getColumnIndex(null /*tableContext*/, columnName);
	}

	public int getColumnIndex(WebElement tableContext, String columnName) {
		List<Integer> colIndices = getColumnIndices(tableContext, Arrays.asList(columnName));
		if (colIndices != null && colIndices.size()>0) {
			return colIndices.get(0);
		}

		return -1;
	}

	private List<Integer> getColumnIndices(List<String> columnNames) {
		return getColumnIndices(null /*tableContext*/, columnNames);
	}

	private List<Integer> getColumnIndices(WebElement tableContext, List<String> columnNames) {
		List<Integer> indices = null;
		if (columnNames != null && columnNames.size() > 0) {
			indices = new ArrayList<Integer>(columnNames.size());
			if (tableContext != null) {
				tableHeader = tableContext.findElements(By.xpath("thead/tr/th"));
			}

			for (String colName : columnNames) {
				boolean foundColName = false;
				for (int i = 0; i < tableHeader.size(); i++) {
					String columnText = tableHeader.get(i).getText();
					if (columnText != null && columnText.trim().equals(colName)) {
						foundColName = true;
						indices.add(i);
						break;
					}
				}

				if (!foundColName) {
					Log.warn("Not found: Column '" + colName + "'");
				}
			}
		}

		return indices;
	}

	/**
	 * Navigate to next page of this table
	 *
	 * @return
	 */
	public boolean toNextPage() {
		Log.clickElementInfo("Next");
		return toPage(nextButton);
	}

	/**
	 * Navigate to another page of this table
	 *
	 * @param pageNavButton
	 *            - actual button will be clicked
	 * @return true if button clicked
	 */
	public boolean toPage(WebElement pageNavButton) {
		int numTry = 0 ;
		boolean done = false;
		while (!done && numTry++ < 3 && !pageNavButton.getAttribute("class").contains("disabled")) {
			try{
				pageNavButton.click();
				done = true;
			}catch(WebDriverException e){
				Log.warn("pageNavButton is not clickable: '"+e+"', will try again");
			}finally{
				waitForTableToLoad();
			}
		}
		if(!done)
			Log.error("Page navigation button is disabled/not clickable");
		return done;
	}

	/**
	 * Wait for this data table to be loaded
	 */
	public void waitForTableToLoad() {
		Log.method("waitForTableToLoad");
		waitForPageLoad();
		waitForAJAXCallsToComplete();
		(new WebDriverWait(driver, timeout + 30)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return WebElementExtender.isElementPresentAndDisplayed(dataTable);
			}
		});
	}
}