package surveyor.scommon.source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

import common.source.Log;
import common.source.RegexUtility;
import common.source.SortHelper;
import common.source.TestSetup;
import common.source.WebElementExtender;

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
		setPagination(pagination);
		waitForTableToLoad();
		
		int colIdx = getColumnIndex(columnName);
		List<String> columnValues = new ArrayList<String>();
		int numFound = 0;
		boolean done = false;
		do{			
			for(WebElement row: tableRow){
				List<WebElement> field = row.findElements(By.cssSelector("td"));
				columnValues.add(field.get(colIdx).getText());
            	numFound++;
				if(numRecords>-1 && numFound >= numRecords){
					done = true;
					break;
				}
			}
		}while(!done&&toNextPage());		
        return columnValues;
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
	 * Find row in this data table, page by page
	 * 
	 * @param filter
	 *            - conditions for matching a row, columnName:value pairs
	 * 
	 * @return matched row as a WebElement
	 */
	public WebElement getMatchingRow(Map<String, String> filter) {
		Map<String, List<String>> indexedMap = new HashMap<String, List<String>>();
		for (Entry<String, String> entry : filter.entrySet()) {
			List<String> value = new ArrayList<String>();
			value.add(entry.getValue());
			indexedMap.put(entry.getKey(), value);
		}
		return getMatchingRowOptionalInput(indexedMap);
	}

	public WebElement getMatchingRowOptionalInput(Map<String, List<String>> filter) {
		setPagination(pagination);
		waitForTableToLoad();
		Map<Integer, List<String>> indexedMap = new HashMap<Integer, List<String>>();
		for (Entry<String, List<String>> entry : filter.entrySet()) {
			indexedMap.put(getColumnIndex(entry.getKey().toString()), entry.getValue());
		}
		do {
			for (WebElement row : tableRow) {
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
	public boolean isTableSortedAsc(HashMap<String, TableColumnType> cloumnMap, String str, List<WebElement> paginationOption, WebElement dataTable) {
		return isTableSortedAsc(cloumnMap, str, paginationOption, dataTable, -1);
	}
	public boolean isTableSortedAsc(HashMap<String, TableColumnType> cloumnMap, String str, List<WebElement> paginationOption, WebElement dataTable, int numRecords) {
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
	public boolean isTableSortedDesc(HashMap<String, TableColumnType> cloumnMap, String str, List<WebElement> paginationOption, WebElement dataTable) {
		return isTableSortedDesc(cloumnMap, str, paginationOption, dataTable, -1);
	}
	public boolean isTableSortedDesc(HashMap<String, TableColumnType> cloumnMap, String str, List<WebElement> paginationOption, WebElement dataTable, int numRecords) {
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
		for (int i = 0; i < tableHeader.size(); i++) {
			String columnText = tableHeader.get(i).getText();
			if (columnText != null && columnText.trim().equals(columnName)) {
				return i;
			}
		}
		Log.warn("Not found: Column '" + columnName + "'");
		return -1;
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
		waitForPageLoad();
		(new WebDriverWait(driver, timeout + 30)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return WebElementExtender.isElementPresentAndDisplayed(dataTable);
			}
		});
	}
}