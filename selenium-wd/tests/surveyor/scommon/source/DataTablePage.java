package surveyor.scommon.source;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.BasePage;
import common.source.Log;
import common.source.RegexUtility;
import common.source.TestSetup;
import common.source.WebElementExtender;

public class DataTablePage extends BasePage{

    protected SearchContext searchContext;
    protected String pagination = "100";
    
    @FindBy(css=".dataTables_length select.input-sm > option")
    private List<WebElement> paginationOption;
    
    @FindBy(css=".table.dataTable")
    private WebElement dataTable;
    @FindBy(css=".table.dataTable > tbody >tr")
    private List<WebElement> tableRow;
    @FindBy(css=".table.dataTable > thead > tr > th")
    private List<WebElement> tableHeader;
    
    @FindBy(css=".paginate_button.next")
    private WebElement nextButton;
    @FindBy(css=".paginate_button.previous")
    private WebElement previousButton;
    @FindBy(css=".paginate_button.last")
    private WebElement lastButton;
    @FindBy(css=".paginate_button.first")
    private WebElement firstButton;
    @FindBy(css=".paginate_button.current")
    private WebElement currentButton;
    
    
	private DataTablePage(WebDriver driver,  TestSetup testSetup, String strBaseURL, String strPageURL) {
		super(driver, testSetup, strBaseURL, strPageURL);
	}
	
	/**
	 * 
	 * @param driver - the search context will be the root of the document
	 * @return instance of this page
	 */
	public static DataTablePage getDataTablePage(WebDriver driver, TestSetup testSetup, String strBaseURL, String strPageURL) {
		return getDataTablePage(driver, driver, testSetup, strBaseURL, strPageURL);
	}
	
	/**
	 * 
	 * @param driver
	 * @param searchContext - The root WebElement of this datatable
	 * @return instance of this page
	 */
	public static DataTablePage getDataTablePage(WebDriver driver, SearchContext searchContext,
			TestSetup testSetup, String strBaseURL, String strPageURL) {
		DataTablePage tablePage = new DataTablePage(driver, testSetup, strBaseURL, strPageURL);
		tablePage.searchContext = searchContext;		
		ElementLocatorFactory elf = new DefaultElementLocatorFactory(searchContext);
		PageFactory.initElements(elf,tablePage);
		return tablePage;		
	}

	//**************** Test methods ***********************
	/**
     * Find records in this data table, page by page
	 * @param filter - conditions for matching a row, columnName:value pairs
	 * @return true if found
	 */
	public boolean hasRecord(Map<String,List<String>> filter){
    	return hasRecord(filter,true);
    }
	public boolean hasRecord(Map<String,List<String>> filter, boolean match){
    	return findRecords(filter,1,match) > 0;
    }
	
	/**
     * Find records in this data table, page by page
	 * @param filter - conditions for matching a row, columnName:value pairs
	 * @return total number of matched records, -1 if not found
	 */
    public int findRecords(Map<String,List<String>> filter){
    	return findRecords(filter,-1);
    }

    /**
     * Find records in this data table, page by page
     * @param filter - conditions for matching a row, columnName:value pairs
     * @param numRecords - the max number of records to be matched, search for all if it's -1
     * @return number of matched records
     */
	public int findRecords(Map<String,List<String>>  filter, int numRecords){
		return findRecords(filter, numRecords, true);
	}
	public int findRecords(Map<String,List<String>>  filter, int numRecords, boolean match){
		setPagination(pagination);
		waitForTableToLoad();
		Map<Integer,List<String>> indexedMap = new HashMap<Integer, List<String>>();
		for(Entry<String,List<String>>  entry:filter.entrySet()){
			indexedMap.put(getColumnIndex(entry.getKey().toString()), entry.getValue());
		}	
		
		int numFound = 0;
		do{			
			for(WebElement row:tableRow){
                if(match==rowMatches(row, indexedMap)){
                	Log.warn("Record found: '"+row.getText()+"'");
                	numFound++;
                }
				if(numRecords>-1 && numFound >= numRecords){
					break;
				}
			}
		}while(toNextPage());		
        return numFound;
   }

	

	
	
	
	//******************** Table Helpers ****************************
	/**
	 * To find matches by equals or matches
	 * @param row
	 * @param filter
	 * @return true if matches
	 */
	public boolean rowMatches(WebElement row, Map<Integer,List<String>>  filter){
		List<WebElement> field = row.findElements(By.cssSelector("td"));
		for(Entry<Integer,List<String>>entry:(filter.entrySet())){
			String value = field.get(entry.getKey()).getText();
			List<String> expectedValues = entry.getValue();
			boolean fieldMatches = false;
			for(String expectedValue:expectedValues){
			    if(RegexUtility.equalsOrMatches(value,expectedValue)){
			    	fieldMatches = true;
				    break;
			    }
			}
			if(!fieldMatches){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * To find the index number of a column
	 * @param columnName 
	 * @return index of the column
	 */
	public int getColumnIndex(String columnName){
		for(int i=0;i<tableHeader.size();i++){
			String columnText = tableHeader.get(i).getText();
			if(columnText!=null&&columnText.trim().equals(columnName)){
				return i;
			}
		}
		Log.warn("Not found: Column '"+columnName+"'");
		return -1;
	}
	
	/**
	 * Navigate to next page of this table
	 * @return
	 */
	public boolean toNextPage(){
		return toPage(nextButton);
	}
	
	/**
	 * Navigate to another page of this table
	 * @param pageNavButton - actual button will be clicked
	 * @return true if button clicked
	 */
	public boolean toPage(WebElement pageNavButton){
		if(!pageNavButton.getAttribute("class").contains("disabled")) {
			pageNavButton.click();
			waitForTableToLoad();
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Set pagination for this data table
	 * @param str - the num string of the pagination option
	 */
	public void setPagination(String str) {
		for (WebElement option : paginationOption) {
			if (str.equals(option.getText().trim())) {
				option.click();
			}
		}
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