package common.source;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
		CSVParser parser = new CSVParser(new FileReader(fileAbsolutePath),CSVFormat.EXCEL.withHeader());
		Map<String,Integer> headerMap=parser.getHeaderMap();
		ArrayList<String> headers=new ArrayList<String>();
		for(String key:headerMap.keySet()){
			headers.add(key);
		}
		parser.close();	
		return headers;			
	}
	
	/*
	 * This method returns all rows in the csv file except headings
	 * rows are returned as key(column heading), value pairs
	 * @param - absolute path to the csv file
	 * @return - list of hashmaps, hashmap per record
	 */
	public List<HashMap<String,String>> getAllRows(String fileAbsolutePath) throws FileNotFoundException, IOException{
		List<HashMap<String,String>> rowsList=new ArrayList<HashMap<String,String>>();
		CSVParser parser = new CSVParser(new FileReader(fileAbsolutePath),CSVFormat.EXCEL.withHeader());
		Map<String,Integer> headerMap=parser.getHeaderMap();
		List<CSVRecord> rows=parser.getRecords();
		Iterator<CSVRecord> rowIterator=rows.iterator();
		while(rowIterator.hasNext()){
			CSVRecord currentRow=rowIterator.next();
			HashMap<String, String> rowMap=new HashMap<String, String>();
			for(String key:headerMap.keySet()){
				rowMap.put(key, currentRow.get(key));				
			}
			rowsList.add(rowMap);
			
		}
		parser.close();	
		return rowsList;	
	}
	
		
	public static void main(String[] args) throws Exception {
		
		CSVUtility csv=new CSVUtility();
		List<String> output=csv.getHeadings(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\CR-F5D2F4-Report.csv");
		System.out.println(output.toString());
		List<HashMap<String,String>> outputMap=	csv.getAllRows(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\CR-F5D2F4-Report.csv");
		for(int i=0;i<outputMap.size();i++){
		HashMap<String, String> map=outputMap.get(i);
		 for(Map.Entry<String,String> entry:map.entrySet()){
			 System.out.print(entry.getKey()+" : "+entry.getValue()+"\n");
		 }
		}

		List<String> output1=csv.getHeadings(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\CR-F5D2F4-ReportIsotopic.csv");
		System.out.println(output1.toString());
		List<HashMap<String,String>> outputMap1=csv.getAllRows(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\CR-F5D2F4-ReportIsotopic.csv");
		for(int i=0;i<outputMap1.size();i++){
		HashMap<String, String> map=outputMap1.get(i);
		 for(Map.Entry<String,String> entry:map.entrySet()){
			 System.out.print(entry.getKey()+" : "+entry.getValue()+"\n");
		 }
		}
		
		List<String> output2=csv.getHeadings(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\CR-F5D2F4-ReportGAP.csv");
		System.out.println(output2.toString());
		List<HashMap<String,String>> outputMap2=csv.getAllRows(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\CR-F5D2F4-ReportGAP.csv");
		for(int i=0;i<outputMap2.size();i++){
		HashMap<String, String> map=outputMap2.get(i);
		 for(Map.Entry<String,String> entry:map.entrySet()){
			 System.out.print(entry.getKey()+" : "+entry.getValue()+"\n");
		 }
		}
		
		List<String> output3=csv.getHeadings(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\CR-F5D2F4-ReportLISAS.csv");
		System.out.println(output3.toString());
		List<HashMap<String,String>> outputMap3=csv.getAllRows(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\CR-F5D2F4-ReportLISAS.csv");
		for(int i=0;i<outputMap3.size();i++){
		HashMap<String, String> map=outputMap3.get(i);
		 for(Map.Entry<String,String> entry:map.entrySet()){
			 System.out.print(entry.getKey()+" : "+entry.getValue()+"\n");
		 }
		}
		
		List<String> output4=csv.getHeadings(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\CR-F5D2F4-ReportSurvey.csv");
		System.out.println(output4.toString());
		List<HashMap<String,String>> outputMap4=csv.getAllRows(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\CR-F5D2F4-ReportSurvey.csv");
		for(int i=0;i<outputMap4.size();i++){
		HashMap<String, String> map=outputMap4.get(i);
		 for(Map.Entry<String,String> entry:map.entrySet()){
			 System.out.print(entry.getKey()+" : "+entry.getValue()+"\n");
		 }
		}
		
		List<String> output5=csv.getHeadings(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\onlyHeadings.csv");
		System.out.println(output5.toString());
		List<HashMap<String,String>> outputMap5=csv.getAllRows(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\onlyHeadings.csv");
		for(int i=0;i<outputMap5.size();i++){
		HashMap<String, String> map=outputMap5.get(i);
		 for(Map.Entry<String,String> entry:map.entrySet()){
			 System.out.print(entry.getKey()+" : "+entry.getValue()+"\n");
		 }
		}
		
		List<String> output6=csv.getHeadings(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\empty.csv");
		System.out.println(output6.toString());
		List<HashMap<String,String>> outputMap6=csv.getAllRows(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\csvutility-tests\\empty.csv");
		for(int i=0;i<outputMap6.size();i++){
		HashMap<String, String> map=outputMap6.get(i);
		 for(Map.Entry<String,String> entry:map.entrySet()){
			 System.out.print(entry.getKey()+" : "+entry.getValue()+"\n");
		 }
		}
	}

}
