/**
 * 
 */
package surveyor.scommon.source;

import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING_100;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.source.Log;
import common.source.TestSetup;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.source.DataTablePage.TableColumnType;

/**
 * @author zlu
 *
 */
public class ManageRefGasBottlesAdminPage extends ManageRefGasBottlesPage {
	public static final String STRURLPATH = "/Admin/ManageRefGasBottles";
	public static final String Constant_Customer = Resources.getResource(ResourceKeys.Constant_Customer);
	public static final String Constant_Location = Resources.getResource(ResourceKeys.Constant_Location);
	public static final String Constant_Surveyor = Resources.getResource(ResourceKeys.Constant_Surveyor);
	public static final String Constant_Analyzer = Resources.getResource(ResourceKeys.Constant_Analyzer);
	public static final String Constant_LotNumber = Resources.getResource(ResourceKeys.Constant_LotNumber);
	public static final String Constant_IsotopicValue = Resources.getResource(ResourceKeys.Constant_IsotopicValue);
	public static final String Constant_EthaneToMethaneRatio = Resources.getResource(ResourceKeys.Constant_EthaneToMethaneRatio);
	public static final String Constant_DateTime = Resources.getResource(ResourceKeys.Constant_DateTime);
	protected String pagination = "100";
	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 */
	public ManageRefGasBottlesAdminPage(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, testSetup, strBaseURL, STRURLPATH);
		
		Log.info("\nThe Manage Reference Gas Bottles Admin Page URL is: %s\n" + strBaseURL + STRURLPATH);
	}
	
	public boolean findExistingRefGasBottle(String strLotNumber, String strSurveyor, String location) {
		Log.info(String.format("Find RefGas bottle lot = '%s', surveyor = '%s', location = '%s'",
				strLotNumber, strSurveyor, location));
		setPagination(PAGINATIONSETTING_100);
		
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
			locationXPath = strTRXPath + "["+rowNum+"]/td[1]";
			strSurveyorXPath = strTRXPath + "["+rowNum+"]/td[2]";
			strLotNumberXPath = strTRXPath + "["+rowNum+"]/td[4]";
			
			locationCell = getTable().findElement(By.xpath(locationXPath));
			surveyorCell = getTable().findElement(By.xpath(strSurveyorXPath));
			lotNumberCell = getTable().findElement(By.xpath(strLotNumberXPath));
			
			if (locationCell.getText().trim().equalsIgnoreCase(location) && surveyorCell.getText().trim().equalsIgnoreCase(strSurveyor) 
					&& lotNumberCell.getText().trim().equalsIgnoreCase(strLotNumber)) {				
				return true;
			}
			
			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				Log.clickElementInfo("Next");
				toNextPage();
				
				List<WebElement> newRows = getTable().findElements(By.xpath(strTRXPath));
				rowSize = newRows.size();
				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);
				
				rowNum = 0;
			}
		}
		Log.error(String.format("RefGas bottle not found: lot = '%s', surveyor = '%s', location = '%s'",
				strLotNumber, strSurveyor, location));		
		return false;
	}
	
	public boolean areTableColumnsSorted(){

		if(!isLocationColumnSorted()){
			return false;
		}
		if(!isSurveyorColumnSorted()){
			return false;
		}
		if(!isAnlyzerColumnSorted()){
			return false;
		}
		if(!isLotNumberColumnSorted()){
			return false;
		}
		
		if(!isIsoValueColumnSorted()){
		return false;
	}
		
		if(!isEToMRatioColumnSorted()){
		return false;
	}
		if(!isDateTimeColumnSorted()){
			return false;
		}
		
		return true;
	}
	
	public boolean isCustomerColumnSorted(){
		HashMap<String, TableColumnType> columnMap = new HashMap<String, TableColumnType>();
		columnMap.put(Constant_Customer, TableColumnType.String);
		return checkTableSort("datatable_wrapper", columnMap, pagination, getPaginationOption());
	}
	
	public boolean isLocationColumnSorted(){
		HashMap<String, TableColumnType> columnMap = new HashMap<String, TableColumnType>();
		columnMap.put(Constant_Location, TableColumnType.String);
		return checkTableSort("datatable_wrapper", columnMap, pagination, getPaginationOption());
	}
	
	public boolean isSurveyorColumnSorted(){
		HashMap<String, TableColumnType> columnMap = new HashMap<String, TableColumnType>();
		columnMap.put(Constant_Surveyor, TableColumnType.String);
		return checkTableSort("datatable_wrapper", columnMap, pagination, getPaginationOption());
	}
	
	public boolean isAnlyzerColumnSorted(){
		HashMap<String, TableColumnType> columnMap = new HashMap<String, TableColumnType>();
		columnMap.put(Constant_Analyzer, TableColumnType.String);
		return checkTableSort("datatable_wrapper", columnMap, pagination, getPaginationOption());
	}
	
	public boolean isLotNumberColumnSorted(){
		HashMap<String, TableColumnType> columnMap = new HashMap<String, TableColumnType>();
		columnMap.put(Constant_LotNumber, TableColumnType.String);
		return checkTableSort("datatable_wrapper", columnMap, pagination, getPaginationOption());
	}

	public boolean isIsoValueColumnSorted(){
		HashMap<String, TableColumnType> columnMap = new HashMap<String, TableColumnType>();
		columnMap.put(Constant_IsotopicValue, TableColumnType.Number);
		return checkTableSort("datatable_wrapper", columnMap, pagination, getPaginationOption());
	}
	public boolean isEToMRatioColumnSorted(){
		HashMap<String, TableColumnType> columnMap = new HashMap<String, TableColumnType>();
		columnMap.put(Constant_EthaneToMethaneRatio, TableColumnType.Number);
		return checkTableSort("datatable_wrapper", columnMap, pagination, getPaginationOption());
	}
	public boolean isDateTimeColumnSorted(){
		HashMap<String, TableColumnType> columnMap = new HashMap<String, TableColumnType>();
		columnMap.put(Constant_DateTime, TableColumnType.Date);
		return checkTableSort("datatable_wrapper", columnMap, pagination, getPaginationOption());
	}
	
}