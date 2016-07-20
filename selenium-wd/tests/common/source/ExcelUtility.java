package common.source;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class ExcelUtility {
	public enum ValueType {
		String,
		Numeric,
		Boolean, Integer
	}
	
	private XSSFSheet excelWorksheet;
	private XSSFWorkbook excelWorkbook;
	private org.apache.poi.ss.usermodel.Cell cellText;
	private XSSFRow row;
	private String excelFilePath;
	private HashMap<String, String> cellDataMap = new HashMap<String, String>();
   
	private static String formMapKey(int rowNum, int colNum, String sheetName) {
		return String.format("%d:%d:%s", rowNum, colNum, sheetName);
	}
	
	private static String formMapKey(int rowId, String colName, String sheetName) {
		return String.format("%s:%s:%s", rowId, colName, sheetName);
	}	
	
    private String getCellData(int rowNum, int colNum, String sheetName, ValueType valueType) throws Exception{
        try {
        	String cellData = null;
        	String key = formMapKey(rowNum, colNum, sheetName);
        	if (cellDataMap.containsKey(key)) {
        		return cellDataMap.get(key);
        	}
        	cellData = retriveCellData(rowNum, colNum, sheetName, valueType);
            // cache cellData
            cellDataMap.put(key, cellData);
            return cellData;
         } catch (Exception e) {
             return "<ERROR>";
         }
     }

    private String retriveCellData(int rowNum, int colNum, String sheetName, ValueType valueType) throws Exception{
    	String cellData = null;
    	excelWorksheet = excelWorkbook.getSheet(sheetName);
       	cellText = excelWorksheet.getRow(rowNum).getCell(colNum);
       	if (valueType == ValueType.String) {
       		cellData = cellText.getStringCellValue();
       	} else if (valueType == ValueType.Integer) {
       		Double cellValue = cellText.getNumericCellValue();
       		cellData = String.valueOf(cellValue.intValue());
       	} else if (valueType == ValueType.Numeric) {
       		cellData = String.valueOf(cellText.getNumericCellValue());
       	} else if (valueType == ValueType.Boolean) {
       		cellData = String.valueOf(cellText.getBooleanCellValue());
       	} 
       	return cellData;
    }
    
    private int getRowIndex(int rowId, String sheetName){
    	int columnIndex = 0;
    	excelWorksheet = excelWorkbook.getSheet(sheetName);
    	int numRows = excelWorksheet.getLastRowNum()+1;
    	for(int i=1; i<numRows; i++){
    		int id = (int) excelWorksheet.getRow(i).getCell(columnIndex).getNumericCellValue();
    		if(rowId == id){
    			return i;
    		}
    	}
        return -1;
    }
    
    private int getColumnIndex(String colName, String sheetName){
    	int headerIndex = 0;
    	excelWorksheet = excelWorkbook.getSheet(sheetName);
       	row = excelWorksheet.getRow(headerIndex);
       	int rowSize = row.getLastCellNum();
       	for(int i=0; i<rowSize; i++){
       		String name = row.getCell(i).getStringCellValue();
       		if(colName.equalsIgnoreCase(name)){
       			return i;
       		}
       	}
       	return -1;
    }
    
    public String getExcelFilePath(){
    	return excelFilePath;
    }
    public String getCellData(int rowId, String colName, String sheetName, ValueType valueType){
        try {
        	String cellData = null;
        	String key = formMapKey(rowId, colName, sheetName);
        	if (cellDataMap.containsKey(key)) {
        		return cellDataMap.get(key);
        	}
        	int colNum = getColumnIndex(colName, sheetName);
        	int rowNum = getRowIndex(rowId,sheetName);
        	
        	cellData = retriveCellData(rowNum, colNum, sheetName, valueType);

            cellDataMap.put(key, cellData);
            return cellData;
         } catch (Exception e) {
             return "<ERROR>";
         }    	
    }
    
	public void setExcelFile(String path) {
    	try {
            this.excelFilePath = path;
    		FileInputStream excelFile = new FileInputStream(path);
            excelWorkbook = new XSSFWorkbook(excelFile);
    	} catch (Exception e) {
    		Log.warn(String.format("Error setting Excel file - '%s'", path));
    	}
	}

    public String getCellData(int rowNum, int colNum, String sheetName ) throws Exception{
        String cellData = getCellData(rowNum, colNum, sheetName, ValueType.String);
        // Certain cells can either have String or Numeric value. 
        // Fallback to retrieving numeric value if String values cannot be retrieved from cell.
        if (cellData.equals("<ERROR>")) {
        	cellData = getIntegerCellData(rowNum, colNum, sheetName);
    	}
        return cellData;
     }
    
    public String getNumericCellData(int rowNum, int colNum, String sheetName ) throws Exception{
    	String cellData = getCellData(rowNum, colNum, sheetName, ValueType.Numeric);
    	if (cellData.equals("<ERROR>")) {
        	cellData = "";
        }        
    	return cellData;
    }

    public String getIntegerCellData(int rowNum, int colNum, String sheetName ) throws Exception{
    	String cellData = getCellData(rowNum, colNum, sheetName, ValueType.Integer);
    	if (cellData.equals("<ERROR>")) {
        	cellData = "";
        }        
    	return cellData;
    }

    public String getBooleanCellData(int rowNum, int colNum, String sheetName ) throws Exception{
    	String cellData = getCellData(rowNum, colNum, sheetName, ValueType.Boolean);
    	if (cellData.equals("<ERROR>")) {
        	cellData = "";
        }        
    	return cellData;
	}

	public int getRowCount(String sheetName) {
		int num=0;
		try {
			excelWorksheet = excelWorkbook.getSheet(sheetName);
			num=excelWorksheet.getLastRowNum()+1;
		} catch (Exception e) {
			Log.error("Class ExcelUtility | Method getRowCount | Exception msg : "+e.getMessage());
		}
		return num;
	}
	
	public int getRowContains(String testCaseID, int colNum, String SheetName) throws Exception{
		int num=0;	
		try {
			int rowCount = this.getRowCount(SheetName);
			for (; num<rowCount; num++){
				if  (this.getIntegerCellData(num,colNum,SheetName).equalsIgnoreCase(testCaseID)){
					break;
				}
			}       			
		} catch (Exception e) {
			Log.error("Class ExcelUtility | Method getRowContains | Exception msg : "+e.getMessage());
		}
		return num;
	}
	
	public int getTestStepsCount(String testCaseID, int colNum, int testCasesStartIdx, String sheetName) throws Exception{
		try {
    		for (int i=testCasesStartIdx;i<=this.getRowCount(sheetName);i++) {
    			if (!testCaseID.equals(this.getIntegerCellData(i, colNum, sheetName))) {
    				int number = i;
    				return number;      				
    			}
			}
    		
    		// If we did NOT find the last test step then return the startIdx itself.
    		return testCasesStartIdx;
		} catch (Exception e) {
			Log.error("Class ExcelUtility | Method getRowContains | Exception msg : "+e.getMessage());
			return 0;
        }
	}
	
	@SuppressWarnings("static-access")
	public void setCellData(String result, int rowNum, int colNum, String sheetName) throws Exception    {
		try {
			excelWorksheet = excelWorkbook.getSheet(sheetName);
			row = excelWorksheet.getRow(rowNum);
			cellText = row.getCell(colNum, row.RETURN_BLANK_AS_NULL);
			if (cellText == null) {
				cellText = row.createCell(colNum);
				cellText.setCellValue(result);
			} else {
				cellText.setCellValue(result);
			}
			FileOutputStream fileOut = new FileOutputStream(this.excelFilePath);
			try {
				excelWorkbook.write(fileOut);
			} finally {
				fileOut.close();
			}
			excelWorkbook = new XSSFWorkbook(new FileInputStream(this.excelFilePath));
		 } catch(Exception e) {
         }
	}
}