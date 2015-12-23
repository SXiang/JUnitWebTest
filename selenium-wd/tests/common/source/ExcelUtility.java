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
	
    private String getCellData(int rowNum, int colNum, String sheetName, ValueType valueType) throws Exception{
        try {
        	String cellData = null;
        	String key = formMapKey(rowNum, colNum, sheetName);
        	if (cellDataMap.containsKey(key)) {
        		return cellDataMap.get(key);
        	}        	
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
            // cache cellData
            cellDataMap.put(key, cellData);
            return cellData;
         } catch (Exception e) {
             //System.out.println("Class ExcelUtility | Method getCellData | Exception msg : "+e.getMessage());
             //TestExecutionEngine.bResult = false;
             return "<ERROR>";
         }
     }

	public void setExcelFile(String path) throws Exception {
    	try {
            this.excelFilePath = path;
    		FileInputStream excelFile = new FileInputStream(path);
            excelWorkbook = new XSSFWorkbook(excelFile);
    	} catch (Exception e) {
    		//System.out.println("Class ExcelUtility | Method setExcelFile | Exception msg : "+e.getMessage());
    		//TestExecutionEngine.bResult = false;
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
			System.err.println("Class ExcelUtility | Method getRowCount | Exception msg : "+e.getMessage());
			//TestExecutionEngine.bResult = false;
		}
		return num;
	}
	
	public int getRowContains(String testCaseID, int colNum, String SheetName) throws Exception{
		int num=0;	
		try {
		    //ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int rowCount = this.getRowCount(SheetName);
			for (; num<rowCount; num++){
				if  (this.getIntegerCellData(num,colNum,SheetName).equalsIgnoreCase(testCaseID)){
					break;
				}
			}       			
		} catch (Exception e) {
			System.err.println("Class ExcelUtility | Method getRowContains | Exception msg : "+e.getMessage());
			//TestExecutionEngine.bResult = false;
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

    		/*
    		excelWorksheet = excelWorkbook.getSheet(sheetName);
    		int number=excelWorksheet.getLastRowNum()+1;
    		return number;
    		*/
		} catch (Exception e) {
			System.err.println("Class ExcelUtility | Method getRowContains | Exception msg : "+e.getMessage());
			//TestExecutionEngine.bResult = false;
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
			excelWorkbook.write(fileOut);
			//fileOut.flush();
			fileOut.close();
			excelWorkbook = new XSSFWorkbook(new FileInputStream(this.excelFilePath));
		 } catch(Exception e) {
			 //TestExecutionEngine.bResult = false;
         }
	}
}