package common.source;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class CSVUtility {
	
	/*
	 * This method returns all the headings in csv file as a list
	 * @param fileAbsolutePath- absolute path to the csv file
	 * @return headers- csv file headings as a list
	 * @throws FileNotFoundException, IOException
	 */
	
	public List<String> getHeadings(String fileAbsolutePath) throws FileNotFoundException, IOException{
		List<String> headers= Collections.synchronizedList(new ArrayList<String>());
		FileReader fileReader = new FileReader(fileAbsolutePath);
		CSVParser parser = new CSVParser(fileReader,CSVFormat.EXCEL.withHeader());
		try {
			Map<String,Integer> headerMap=parser.getHeaderMap();
			for(String key:headerMap.keySet()){
				headers.add(key);
			}
		} finally {
			fileReader.close();
			parser.close();
		}
		return headers;			
	}

	/*
	 * This method returns the number of rows specified in the csv file (except header row) 
	 * Rows are returned as key(column heading), value pairs
	 * @param - absolute path to the csv file
	 * @return - list of hashmaps, hashmap per record
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String,String>> getTopRows(String fileAbsolutePath, Integer rowsToFetch) throws FileNotFoundException, IOException{
		List<HashMap<String, String>> rowsList= Collections.synchronizedList(new ArrayList<HashMap<String,String>>());
		FileReader fileReader = new FileReader(fileAbsolutePath);
		CSVParser parser = new CSVParser(fileReader,CSVFormat.EXCEL.withHeader());
		try {
			Map<String,Integer> headerMap=parser.getHeaderMap();
			List<CSVRecord> rows=parser.getRecords();
			Iterator<CSVRecord> rowIterator=rows.iterator();
			int count = 0;
			while(rowIterator.hasNext() && count<rowsToFetch){
				CSVRecord currentRow=rowIterator.next();
				HashMap<String, String> rowMap = (HashMap<String, String>)Collections.synchronizedMap(new HashMap<String, String>());
				for(String key:headerMap.keySet()){
					rowMap.put(key, currentRow.get(key));				
				}
				rowsList.add(rowMap);
				count++;
			}
		} finally {
			fileReader.close();
			parser.close();
		}
		return rowsList;	
	}

	/*
	 * This method returns all rows in the csv file except headings
	 * rows are returned as key(column heading), value pairs
	 * @param - absolute path to the csv file
	 * @return - list of hashmaps, hashmap per record
	 */
	public List<HashMap<String,String>> getAllRows(String fileAbsolutePath) throws FileNotFoundException, IOException{
		return getTopRows(fileAbsolutePath, Integer.MAX_VALUE);	
	}
	
	public static String createCsvString(List<String> values) {
		String csvString = "";
		if (values != null) {
			csvString = String.join(",", values.toArray(new String[values.size()]));
		}
		return csvString;
	}
		
	public static void main(String[] args) throws Exception {
		
		CSVUtility csv=new CSVUtility();
		List<String> output=csv.getHeadings(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\CR-F5D2F4-Report.csv");
		Log.info(output.toString());
		List<HashMap<String,String>> outputMap=	csv.getAllRows(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\CR-F5D2F4-Report.csv");
		for(int i=0;i<outputMap.size();i++){
		HashMap<String, String> map=outputMap.get(i);
		 for(Map.Entry<String,String> entry:map.entrySet()){
			 Log.info(entry.getKey()+" : "+entry.getValue()+"\n");
		 }
		}

		List<String> output1=csv.getHeadings(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\CR-F5D2F4-ReportIsotopic.csv");
		Log.info(output1.toString());
		List<HashMap<String,String>> outputMap1=csv.getAllRows(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\CR-F5D2F4-ReportIsotopic.csv");
		for(int i=0;i<outputMap1.size();i++){
		HashMap<String, String> map=outputMap1.get(i);
		 for(Map.Entry<String,String> entry:map.entrySet()){
			 Log.info(entry.getKey()+" : "+entry.getValue()+"\n");
		 }
		}
		
		List<String> output2=csv.getHeadings(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\CR-F5D2F4-ReportGAP.csv");
		Log.info(output2.toString());
		List<HashMap<String,String>> outputMap2=csv.getAllRows(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\CR-F5D2F4-ReportGAP.csv");
		for(int i=0;i<outputMap2.size();i++){
		HashMap<String, String> map=outputMap2.get(i);
		 for(Map.Entry<String,String> entry:map.entrySet()){
			 Log.info(entry.getKey()+" : "+entry.getValue()+"\n");
		 }
		}
		
		List<String> output3=csv.getHeadings(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\CR-F5D2F4-ReportLISAS.csv");
		Log.info(output3.toString());
		List<HashMap<String,String>> outputMap3=csv.getAllRows(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\CR-F5D2F4-ReportLISAS.csv");
		for(int i=0;i<outputMap3.size();i++){
		HashMap<String, String> map=outputMap3.get(i);
		 for(Map.Entry<String,String> entry:map.entrySet()){
			 Log.info(entry.getKey()+" : "+entry.getValue()+"\n");
		 }
		}
		
		List<String> output4=csv.getHeadings(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\CR-F5D2F4-ReportSurvey.csv");
		Log.info(output4.toString());
		List<HashMap<String,String>> outputMap4=csv.getAllRows(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\CR-F5D2F4-ReportSurvey.csv");
		for(int i=0;i<outputMap4.size();i++){
		HashMap<String, String> map=outputMap4.get(i);
		 for(Map.Entry<String,String> entry:map.entrySet()){
			 Log.info(entry.getKey()+" : "+entry.getValue()+"\n");
		 }
		}
		
		List<String> output5=csv.getHeadings(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\onlyHeadings.csv");
		Log.info(output5.toString());
		List<HashMap<String,String>> outputMap5=csv.getAllRows(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\onlyHeadings.csv");
		for(int i=0;i<outputMap5.size();i++){
		HashMap<String, String> map=outputMap5.get(i);
		 for(Map.Entry<String,String> entry:map.entrySet()){
			 Log.info(entry.getKey()+" : "+entry.getValue()+"\n");
		 }
		}
		
		List<String> output6=csv.getHeadings(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\empty.csv");
		Log.info(output6.toString());
		List<HashMap<String,String>> outputMap6=csv.getAllRows(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\empty.csv");
		for(int i=0;i<outputMap6.size();i++){
		HashMap<String, String> map=outputMap6.get(i);
		 for(Map.Entry<String,String> entry:map.entrySet()){
			 Log.info(entry.getKey()+" : "+entry.getValue()+"\n");
		 }
		}
	}
}
