package common.source;

import java.awt.Point;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;


public class PDFTableUtility extends PDFUtility{

	protected String pageFooterPattern = "^Report Author:.*$|^Date Printed:.*$|^.*@picarro.com$";
	protected String pageHeaderPattern = "";
	protected String ignorableLinePattern = "";

	// All the pdf tables should be defined in this enum
	public static enum PDFTable {		
		LISAINVESTIGATIONTABLE ("Lisa Investigation Table",2),
		LISAINDICATIONTABLE ("Disposition"+wordSeparator+"Confidence in Disposition (%)"+wordSeparator+"Field Notes",0,"",true,-1,10),
		COMPLIANCEREPORTSUMMARYTABLE ("Map Height & Width:.*",0,"",false,6),
		COVERAGEFORECAST(".*Percent Service Coverage with LISAs.*",0,"",false,1),
		COVERAGEFORECASTTO70(".*Probability to Obtain 70% Coverage",0,"",true,4),
		DRIVINGSURVEYTABLE("Indication Table",0,"LISA",true,-1), 
		ISOTOPICANALYSISTABLE("Surveyor"+wordSeparator+"Date/Time"+wordSeparator+"Result"+wordSeparator+"Isotopic Value/ Uncertainty(Å"+wordSeparator+"Field Notes",1," Layers",true,-1),  
		VIEWSTABLE (".*\\| (Map|Satellite|None)\\s?",0,"View Table",false);
		
		private final String tableID;	          //1. tableID, indicator of start of a table, required
		private final int startLine;              //2. num of lines  after 'tableID' - inclusive, optional, default to 0
		private final String tableEndLinePattern; //3. tableEndLinePattern, indicator of end of a table, optional, default to ""
		private final boolean hasTableHeader;     //4. With table header or without table header
		private final int numRows;                //5. num of rows within the table if it's positive, otherwise the size of table is unknown.
		private final int numFields;              //6. num of fields within a table row if it's positive, otherwise it's is unknown.
		
		// Table from the line matching the tableID to the end of file
		PDFTable(String tableID){
			this(tableID,0);
		}

		// Table from 'startLines' lines after the line matching the tableID to the end of file
		PDFTable(String tableID, int startLine){
			this(tableID,startLine,"");
		}

		// Table from the line matching the tableID to the line matching the tableEndLinePattern(exclusive)
		PDFTable(String tableID, String tableEndLinePattern){
			this(tableID,0,tableEndLinePattern);
		}

		// Table from 'startLines' lines after the line matching the tableID to the line matching the tableEndLinePattern(exclusive)
		PDFTable(String tableID,  int startLine, String tableEndLinePattern){
			this(tableID, startLine, tableEndLinePattern, true);
		}
		// Table from 'startLines' lines after the line matching the tableID to the line matching the tableEndLinePattern(exclusive)
		// With table header or without table header
		PDFTable(String tableID,  int startLine, String tableEndLinePattern, boolean hasTableHeader){
			this(tableID, startLine, tableEndLinePattern, true,-1);
		}
		PDFTable(String tableID,  int startLine, String tableEndLinePattern, boolean hasTableHeader, int numRows){
			this(tableID, startLine, tableEndLinePattern, hasTableHeader, numRows, 0);
		}
		PDFTable(String tableID,  int startLine, String tableEndLinePattern, boolean hasTableHeader, int numRows, int numFields){
			this.tableID = tableID;
			this.startLine = startLine;
			this.tableEndLinePattern = tableEndLinePattern;
			this.hasTableHeader = hasTableHeader;
			this.numRows = numRows;
			this.numFields = numFields;
		}

		public String gettableID(){
			return tableID;
		}
		public int getStartLine(){
			return startLine;
		}
		public String getTableEndLinePattern(){
			return tableEndLinePattern;
		}
		public boolean hasTableHeader(){
			return hasTableHeader;
		}
		public int getNumRows(){
			return numRows;
		}
	};


	public PDFTableUtility(){
		super(true);
	}

	/*
	 * Extracts table contents from a pdf file, returns a list of array of strings, the table is specified by a enum PDFTable
	 */
	public List<String[]> extractPDFTable(String pdfFilePath, PDFTable pTable) throws IOException {
		return extractPDFTable(pdfFilePath, pTable, -1);
	}
	/*
	 * Extracts 'numLines' rows from a table in a pdf file, the table is specified by a enum PDFTable
	 */
	public List<String[]> extractPDFTable(String pdfFilePath, PDFTable pTable, int numLines) throws IOException {
		String pdfString = extractPDFText(pdfFilePath);
		return extractTableContents(pdfString, pTable, numLines);
	}

	/*
	 * Get table contents from a pdf string, returns a list of array of strings. the table is specified by the enum PDFTable
	 */
	public List<String[]> extractTableContents(String pdfString, PDFTable pTable) throws IOException {
		return  extractTableContents(pdfString, pTable, -1);
	}

	/*
	 * Extracts table contents from pdfString - with wordSeparator.
	 * Returns table contents as a list of a list of string
	 * 
	 * @pdfString - input PDF contents.
	 * @pTable - a enum PDFTable defined: 1. tableID, 2. line index after 'tableID' - inclusive, 3. tableEndLinePattern
	 * @maxNumLines - max number of lines to be extracted, all records will be included if it's a negative number
	 * 
	 */
	public List<String[]> extractTableContents(String pdfString, PDFTable pTable, int maxNumLines) throws IOException {
		String[] pdfLines = pdfString.split(System.lineSeparator());
		String tableID = pTable.gettableID();
		String tableEndLinePattern = pTable.getTableEndLinePattern();
		boolean hasTableHeader = pTable.hasTableHeader();
		int numTableNameWords = tableID.split(wordSeparatorPattern).length;
		int startLine = pTable.getStartLine();
		int numTableRows = pTable.getNumRows();
		if(maxNumLines<0||maxNumLines>numTableRows){
			maxNumLines = numTableRows;
		}
		int maxWordNumLine = 2;
		String header = "";
		int numLines = 0;        		
		List<String[]> pdfTable = new ArrayList<String[]>();
		for(int i=0,j=0; i<pdfLines.length; i++){
			String line = trimTableRow(pdfLines[i]);        	
			int numWords = line.split(wordSeparatorPattern).length;
			int combinedLine = 0;
			int validNumFields = pTable.numFields;
			while(numWords<numTableNameWords){
				if(++combinedLine%maxWordNumLine==1){
					line += wordSeparator;
				}
				if(i+combinedLine>=pdfLines.length){
					break;
				}
				line += trimTableRow(pdfLines[i+combinedLine]);
				numWords = line.split(wordSeparatorPattern).length;
			}
			if(RegexUtility.equalsOrMatches(line,tableID)){     
				i += combinedLine;

				for(j=i+startLine; j<pdfLines.length ; j++){
					if(maxNumLines>0&&numLines>=maxNumLines){
						return pdfTable;
					}
					if(j!=i){
						line = trimTableRow(pdfLines[j]);
					}
					if(numLines==0&&hasTableHeader){
						header = line; // Remember the header, it may appear in the next page
						pdfTable.add(getTableRow(line));
					}else if(line.equals(header)){ //Ignore header line
						continue;
					}else if(isLineIgnorable(line)){ // Ignore others - pageheader, pagefooter and comstom patterns
						if(line.matches(pageFooterPattern)){ // expecting records from the next page
							break;
						}else{ // continue on this page
							continue;
						}
					}else if(line.matches(tableEndLinePattern)){
						return pdfTable;
					}else{
						String[] row = getTableRow(line);
						if(row.length==validNumFields-1){
								int lineIndex = j+1;
								String lastField = "";
								while(lineIndex < pdfLines.length &&
										(!pdfLines[lineIndex].startsWith(pdfParagraphEnd+pdfParagraphStart))){
									lastField += pdfLines[lineIndex++];
								}
								j = lineIndex-1;
								row = getTableRow(line+wordSeparator+lastField);
						}
						if(validNumFields==0||row.length==validNumFields){
							pdfTable.add(row);
							validNumFields = row.length;
						}else{
							continue;
						}
					}
					numLines++;
				}
				i = j++;
			}
		}
		printTableContents(pdfTable);
		return pdfTable;
	}

	private String trimTableRow(String line){
		String nelPattern = "[\\u0085]*";
		line = line.replaceAll(nelPattern, "");
		line = line.replaceAll(pdfParagraphEnd, "");
		line = line.replaceAll(pdfParagraphStart, "");
		return line;
	}
	
	private String[] getTableRow(String line){
		line = trimTableRow(line);		
		String[] cells = line.split(wordSeparatorPattern);
		for(int i=0; i<cells.length; i++){
			cells[i] = cells[i].trim();
		}
		return cells;
	}

	/*
	 * Ignore page header, footer and other lines specified
	 */
	private boolean isLineIgnorable(String line){
		return line==null||line.isEmpty()
				||line.matches(pageFooterPattern)
				||line.matches(pageHeaderPattern)
				||line.matches(ignorableLinePattern);
	}


	/*
	 * Extracts column data from a table.
	 * Returns column contents as an array of strings
	 * 
	 * @pdfString - input PDF contents.
	 * @pTable - a enum PDFTable defined: 1. tableID, 2. line index after 'tableID' - inclusive, 3. tableEndLinePattern
	 * @columnID - the column id in the first row of table - the header
	 */
	public String[] getColumn(String filePath, PDFTable pTable, String columnID) throws IOException{
		List<String[]> pdfTable = extractPDFTable(filePath,pTable);
		int colIndex = getColumnIndex(pdfTable, columnID);
		return getColumn(pdfTable, colIndex);
	}

	/**
	 * 
	 * @param pdfTable
	 * @param columnID
	 * @return Index number of the Column (the value in the first row)
	 */
	public int getColumnIndex(List<String[]> pdfTable, String columnID){
		int colIndex = 0;
		String[] header = pdfTable.get(0);
		for(int i=0;i<header.length;i++){
			if(header[i].equals(columnID)){
				colIndex = i;
			}
		}
		return colIndex;
	}	

	/*
	 * Extracts column data from a table.
	 * Returns column contents as an array of strings
	 * 
	 * @pdfString - input PDF contents.
	 * @pTable - a enum PDFTable defined: 1. tableID, 2. line index after 'tableID' - inclusive, 3. tableEndLinePattern
	 * @colIndex - the column index of table - 0 based
	 */
	public String[] getColumn(String filePath, PDFTable pTable, int colIndex) throws IOException{
		return getColumn(extractPDFTable(filePath,pTable),colIndex);
	}

	/*
	 * Extracts column data from a table.
	 * Returns column contents as an array of strings
	 * 
	 * @pdfTable - List<String[] returned by PDFUtility.java - with wordSeparator
	 * @colIndex - the column index of table - 0 based
	 */
	public String[] getColumn(List<String[]> pdfTable, int colIndex){
		String[] colValue = new String[pdfTable.size()];
		for(int i=0;i<colValue.length;i++){
			try{
				colValue[i] = pdfTable.get(i)[colIndex];
			}catch(Exception e){
				colValue[i] = "";
			}
		}
		return colValue;
	}	

	/*
	 * Extracts row data from a table.
	 * Returns row contents as an array of strings
	 * 
	 * @pdfString - input PDF contents.
	 * @pTable - a enum PDFTable defined: 1. tableID, 2. line index after 'tableID' - inclusive, 3. tableEndLinePattern
	 * @rowID - the row id of table
	 */	
	public String[] getRow(String filePath, PDFTable pTable, String rowID) throws IOException{
		List<String[]> pdfTable = extractPDFTable(filePath,pTable);
		int rowIndex = getRowIndex(pdfTable, rowID);
		return getRow(pdfTable, rowIndex);
	}

	/**
	 * 
	 * @param pdfTable
	 * @param rowID
	 * @return Index of the row with the rowID(the value in the first column)
	 */
	public int getRowIndex(List<String[]> pdfTable, String rowID){
		int rowIndex = 0;
		for(int i=0;i<pdfTable.size();i++){
			if(pdfTable.get(i)[0].equals(rowID)){
				rowIndex = i;
			}
		}
		return rowIndex;
	}	

	/*
	 * Extracts row data from a table.
	 * Returns row contents as an array of strings
	 * 
	 * @pdfString - input PDF contents.
	 * @pTable - a enum PDFTable defined: 1. tableID, 2. line index after 'tableID' - inclusive, 3. tableEndLinePattern
	 * @rowIndex - the row index of table - 0 based
	 */	
	public String[] getRow(String filePath, PDFTable pTable, int rowIndex) throws IOException{
		return getRow(extractPDFTable(filePath,pTable),rowIndex);
	}

	/*
	 * Extracts row data from a table.
	 * Returns row contents as an array of strings
	 * 
	 * @pdfTable - List<String[] returned by PDFUtility.java - with wordSeparator
	 * @rowIndex - the row index of table - 0 based
	 */	
	public String[] getRow(List<String[]> pdfTable, int rowIndex){
		return pdfTable.get(rowIndex);
	}

	/*
	 * Extracts cell value from a table.
	 * Returns the cell value as a string
	 * 
	 * @pdfString - input PDF contents.
	 * @pTable - a enum PDFTable defined: 1. tableID, 2. line index after 'tableID' - inclusive, 3. tableEndLinePattern
	 * @rowIndex - the row index of table - 0 based
	 * @colIndex - the column index of table - 0 based
	 */		
	public String getCell(String filePath, PDFTable pTable, int rowIndex, int colIndex) throws IOException{
		return getCell(filePath, pTable, new Point(rowIndex, colIndex));
	}
	public String getCell(String filePath, PDFTable pTable, Point pt) throws IOException{
		return getCell(extractPDFTable(filePath,pTable), pt);
	}

	/*
	 * Extracts cell value from a table.
	 * Returns the cell value as a string
	 * 
	 * @pdfTable - List<String[] returned by PDFUtility.java - with wordSeparator
	 * @rowIndex - the row index of table - 0 based
	 * @colIndex - the column index of table - 0 based
	 */		
	public String getCell(List<String[]> ptTable, int rowIndex, int colIndex){
		return getCell(ptTable, new Point(rowIndex, colIndex));
	}
	public String getCell(List<String[]> ptTable,Point pt){
		String value = "";
		try{
			value = ptTable.get(pt.x)[pt.y];
		}catch(Exception e){

		}
		return value;
	}


	public void printTableContents(List<String[]> table){
		for(String[] tr:table){
			String row = "";
			for(String td:tr){
			   row += String.format("\t|%1$-10s",td);
			}
			Log.info(row);
		}
	}
	/**
	 * Executes the unit tests for this class.
	 * @param args
	 */
	public static void main(String[] args) {		
		Path pdfDirectory;
		List<String> pdfFilesInDirectory = null;		

		HashMap<String, String[][]> expectedTableMap = new HashMap<String, String[][]>();
		HashMap<String, PDFTable> expectedPDFTableMap = new HashMap<String, PDFTable>();

		String fileName = "IV-12BF45.pdf";
		expectedTableMap.put(fileName, new String[][]{
			{"Lisa#","Amplitude","Investigated","Leak Found","Investigation Date/Time","Investigator"},
			{"1","6.42","","","",""},{"2","2.41","","","",""},{"3","1.74","","","",""},{"4","0.37","","","",""},
			{"5","0.34","","","",""},{"6","0.12","","","",""},{"7","0.08","","","",""},{"8","0.08","","","",""}});
		expectedPDFTableMap.put(fileName, PDFTable.LISAINVESTIGATIONTABLE);

		fileName = "TC517Report538321.pdf";
		expectedTableMap.put(fileName, new String[][]{
			{"Map Height & Width:","8.50 X 11.00 in"},
			{"Time Zone:", "Pacific Standard Time"},
			{"Exclusion Radius:","0 m"},
			{"Report Mode:", "Standard"},
			{"NE Lat & NE Long","37.42060 X -121.97250"},
			{"SW Lat & SW Long","37.41570 X -121.98390"}});
		expectedPDFTableMap.put(fileName, PDFTable.COMPLIANCEREPORTSUMMARYTABLE);

		fileName = "TestReportPGE-surveynotpartofplat.pdf";
		expectedTableMap.put(fileName, new String[][]{
			{"0%Percent Service Coverage with LISAs","0%Percent Service Coverage Without LISAs"}
		});
		expectedPDFTableMap.put(fileName, PDFTable.COVERAGEFORECAST);

		fileName = "TestReportPGE-nolisa.pdf";
		expectedTableMap.put(fileName, new String[][]{
			{"Additional Surveys","Probability to Obtain 70% Coverage"},
			{"0","0%"},{"1","4%"},{"2","24%"}
		});		
		expectedPDFTableMap.put(fileName, PDFTable.COVERAGEFORECASTTO70);	

		fileName = "CR-E6522E.pdf";
		expectedTableMap.put(fileName, new String[][]{
			{"Disposition", "Confidence in Disposition (%)", "Field Notes"},
			{"", "1", "Software Car", "12/14/2015 3:26 PM PST", "12.3", "15.56", "N/A", "Possible Natural Gas", "N/A", "1. 15.6/12.30"},
			{"", "2", "Software Car", "12/14/2015 3:28 PM PST", "9.44", "9.44", "N/A", "Possible Natural Gas", "N/A", "1. 13.7/8.202. 13.0/7.973. 9.4/9.44"},
			{"", "3", "Software Car", "12/14/2015 3:28 PM PST", "3.02", "6.01", "N/A", "Possible Natural Gas", "N/A", "1. 6.0/3.02"},
			{"", "4", "Software Car", "12/14/2015 3:27 PM PST", "0.4", "2.77", "N/A", "Possible Natural Gas", "N/A",""},
		});
		expectedPDFTableMap.put(fileName,PDFTable.LISAINDICATIONTABLE);	

		PDFTableUtility pdfTableUtility = new PDFTableUtility();

		try {
			pdfDirectory = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data\\test-data\\pdfutility-tests");
			pdfFilesInDirectory = FileUtility.getFilesInDirectory(pdfDirectory);

			for (String filePath : pdfFilesInDirectory) {
				String filename = Paths.get(filePath).getFileName().toString();				                
				if (expectedTableMap.containsKey(filename)) {
					Log.info("\nStart of Test PDF File "+filename+" - Table: "+expectedPDFTableMap.get(filename));
					//1. test extract contents of a PDF table
					pdfTableUtility.testExtractPDFTable(Paths.get(filePath).toString(), 
							expectedTableMap.get(filename),expectedPDFTableMap.get(filename));				
					Log.info("Verified table '"+expectedPDFTableMap.get(filename)+ "' in file '"+expectedTableMap.get(filename)+"'");
					//2. test extract a column from a PDF table
					pdfTableUtility.testExtractPDFTable_getColumn(Paths.get(filePath).toString(), 
							expectedTableMap.get(filename),expectedPDFTableMap.get(filename));				
					Log.info("Verified getCoumn from table '"+expectedPDFTableMap.get(filename)+ "' in file '"+expectedTableMap.get(filename)+"'");
					//3. test extract a row from a PDF table
					pdfTableUtility.testExtractPDFTable_getRow(Paths.get(filePath).toString(), 
							expectedTableMap.get(filename),expectedPDFTableMap.get(filename));				
					Log.info("Verified getRow from table '"+expectedPDFTableMap.get(filename)+ "' in file '"+expectedTableMap.get(filename)+"'");
					//4. test extract a cell from a PDF table
					pdfTableUtility.testExtractPDFTable_getCell(Paths.get(filePath).toString(), 
							expectedTableMap.get(filename),expectedPDFTableMap.get(filename));				
					Log.info("Verified getCell from table '"+expectedPDFTableMap.get(filename)+ "' in file '"+expectedTableMap.get(filename)+"'");
					Log.info("\nEnd of Test PDF File "+filename+" - Table: "+expectedPDFTableMap.get(filename));
				}

			}

		} catch (IOException e) {
			Log.error(e.toString());
		}
	}

	private void testExtractPDFTable(String filePath, String[][] expectedTable, PDFTable pTable) throws IOException {
		String pdfText = extractPDFText(filePath);
		List<String[]> actualTable1 = extractPDFTable(filePath, pTable);
		List<String[]> actualTable2 = extractTableContents(pdfText, pTable);
		for(int i=0; i<expectedTable.length;i++){
			for(int j=0; j<expectedTable[i].length;j++){
				String cell1="",cell2="";
				try{
					cell1 = actualTable1.get(i)[j];
					cell2 = actualTable2.get(i)[j];
				}catch(Exception e){

				}
				Log.info("["+i+","+j+"]: "+expectedTable[i][j]+" - "+cell1+" - "+cell2);
				Assert.assertTrue(cell1.equals(expectedTable[i][j])||cell1.matches(expectedTable[i][j]));
				Assert.assertTrue(cell2.equals(expectedTable[i][j])||cell2.matches(expectedTable[i][j]));
			}
		}
	}

	private void testExtractPDFTable_getColumn(String filePath, String[][] expectedTable, PDFTable pTable) throws IOException {
		// Retrieve values of column 0, based on name and index
		int colNum = 1;
		Point field = new Point(0,colNum);
		String colName = expectedTable[field.x][field.y];

		List<String[]> pdfTable = extractPDFTable(filePath,pTable);
		String[] colValue1 = getColumn(filePath, pTable,colName);
		Log.info("Column '"+colName+"': "+Arrays.toString(colValue1));

		String[] colValue2 = getColumn(filePath, pTable, colNum);
		Log.info("Column '"+colName+"': "+Arrays.toString(colValue2));

		Assert.assertEquals(colValue1, colValue2);

		for(int i=0; i<expectedTable.length;i++){
			Assert.assertTrue(colValue1[i].equals(expectedTable[i][colNum])||colValue1[i].matches(expectedTable[i][colNum])); 
		}

		if(pTable.equals(PDFTable.LISAINDICATIONTABLE)){
			int index = 0;
			if(pTable.hasTableHeader)
				index = 1;
			Assert.assertTrue(SortHelper.isNumberSortedASC(colValue1, index));
			Assert.assertTrue(SortHelper.isNumberSortedASC(colValue2, index));
		}
		// Retrieve values of a random column, based on name and index
		colNum = expectedTable[0].length/2;
		field = new Point(0,colNum);
		colName = expectedTable[field.x][field.y];

		colValue2 = getColumn(filePath, pTable, colNum);
		if(colNum == getColumnIndex(pdfTable, colName) ){
			colValue1 = getColumn(filePath, pTable,colName);
			Assert.assertEquals(colValue1, colValue2);
		}
		
		for(int i=0; i<expectedTable.length;i++){
			Assert.assertTrue(colValue2[i].equals(expectedTable[i][colNum])||colValue2[i].matches(expectedTable[i][colNum]));
		}
	}  

	private void testExtractPDFTable_getRow(String filePath, String[][] expectedTable, PDFTable pTable) throws IOException {
		List<String[]> pdfTable = extractPDFTable(filePath,pTable);
		// Retrieve a row
		int rowNum = expectedTable.length/2;
		String[] rowValue = getRow(filePath, pTable, rowNum);
		for(int i=0; i<expectedTable[rowNum].length;i++){
			String cell = "";
			try{
				cell = rowValue[i].trim();
			}catch(Exception e){

			}
			String expectedValue = expectedTable[rowNum][i];
			Assert.assertTrue(cell.equals(expectedValue)||cell.matches(expectedValue)); 
		}

		// Retrieve a row by rowID
		String rowID = expectedTable[rowNum][0];
		if(rowNum == getRowIndex(pdfTable, rowID)){
			rowValue = getRow(filePath, pTable, rowID);
			for(int i=0; i<expectedTable[rowNum].length;i++){
				String cell = "";
				try{
					cell = rowValue[i].trim();
				}catch(Exception e){

				}
				String expectedValue = expectedTable[rowNum][i];
				Assert.assertTrue(cell.equals(expectedValue)||cell.matches(expectedValue)); 
			}	
		}
	}

	private void testExtractPDFTable_getCell(String filePath, String[][] expectedTable, PDFTable pTable) throws IOException {
		int rowNum = expectedTable.length/2;
		int colNum = expectedTable[0].length/2;
		// Retrieve value from the first field of a row
		Point cell = new Point(rowNum,0);

		String value1 = getCell(filePath, pTable,cell);	
		String value2 = getCell(filePath, pTable,rowNum, 0);

		Assert.assertEquals(value1, value2);
		Assert.assertTrue(value1.equals(expectedTable[rowNum][0])||value1.matches(expectedTable[rowNum][0]));

		// Retrieve value from a random field of a row
		cell = new Point(rowNum,colNum);

		value1 = getCell(filePath, pTable,cell);	
		value2 = getCell(filePath, pTable,rowNum, colNum);

		Assert.assertEquals(value1, value2);
		Assert.assertTrue(value1.equals(expectedTable[rowNum][colNum])||value1.matches(expectedTable[rowNum][colNum]));
	}
	
	public boolean areTablesEqual(List<String[]> currentTable, List<String[]> expectedTable){
	         if(currentTable.size()!=expectedTable.size()){
	        	 return false;
	         }
	         for(int i=0; i< currentTable.size(); i++){
	        	 if(!Arrays.equals(currentTable.get(i), expectedTable.get(i))){
	        		 return false;
	        	 }
	         }
	         return true;
	}
}
