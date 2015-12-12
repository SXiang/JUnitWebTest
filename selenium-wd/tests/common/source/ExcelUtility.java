package common.source;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class ExcelUtility {
	private XSSFSheet excelWorksheet;
	private XSSFWorkbook excelWorkbook;
	private org.apache.poi.ss.usermodel.Cell cellText;
	private XSSFRow row;
	private String excelFilePath;
   
    public void setExcelFile(String path) throws Exception {
    	try {
            this.excelFilePath = path;
    		FileInputStream excelFile = new FileInputStream(path);
            excelWorkbook = new XSSFWorkbook(excelFile);
    	} catch (Exception e) {
    		System.err.println("Class ExcelUtility | Method setExcelFile | Exception msg : "+e.getMessage());
    		//TestExecutionEngine.bResult = false;
    	}
	}
    
    public String getCellData(int rowNum, int colNum, String sheetName ) throws Exception{
        try {
        	excelWorksheet = excelWorkbook.getSheet(sheetName);
           	cellText = excelWorksheet.getRow(rowNum).getCell(colNum);
            String CellData = cellText.getStringCellValue();
            return CellData;
         } catch (Exception e) {
             System.err.println("Class ExcelUtility | Method getCellData | Exception msg : "+e.getMessage());
             //TestExecutionEngine.bResult = false;
             return"";
         }
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
				if  (this.getCellData(num,colNum,SheetName).equalsIgnoreCase(testCaseID)){
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
    			if (!testCaseID.equals(this.getCellData(i, colNum, sheetName))) {
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