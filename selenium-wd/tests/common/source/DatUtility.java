package common.source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Assert;
import org.junit.Test;

public class DatUtility {
	private String csvPath=null;
	CSVUtility csv=new CSVUtility();

	/*
	 * This method returns all the headings in  file as a list
	 * @param
	 * @return headers- csv file headings as a list
	 * @throws
	 */

	public  List<String> getHeadings() {
		List<String> listOfHeading = Collections.synchronizedList(new ArrayList<String>());
		try {
			listOfHeading = csv.getHeadings(csvPath);
		} catch (IOException e) {
			Log.error(e.toString());
		}
		return listOfHeading;
	}

	/*
	 * This method returns all rows in the  file except headings
	 * rows are returned as key(column heading), value pairs
	 * @param 
	 * @return - list of hashmaps, hashmap per record
	 * @throws - FileNotFoundException, IOException 
	 */


	public List<Map<String,String>> getAllRows() throws FileNotFoundException, IOException {
		List<Map<String,String>> rowsList= Collections.synchronizedList(new ArrayList<Map<String,String>>());

		try{
			rowsList = csv.getAllRows(csvPath);
		} catch (IOException e){
			Log.error(e.toString());
		}
		return rowsList;
	}

	/*
	 * This method converts .dat file into csv file except headings
	 * @param -filepath
	 * @throws - IOException 
	 */
	public  void convertDATtoCSV(String filePath) throws IOException {

		String workingFile = TestSetup.getUUIDString() + "_" + Paths.get(filePath).getFileName();
		String workingFullPath = Paths.get(TestContext.INSTANCE.getTestSetup().getDownloadPath(), workingFile).toString();

		File writeFile = new File(workingFullPath);

		String lineText = null;
		BufferedReader buffReader = new BufferedReader(new FileReader(filePath));
		BufferedWriter buffWriter = new BufferedWriter(new FileWriter(writeFile));
		try {
			String[] lineData;
			while ((lineText = buffReader.readLine()) != null) {
				lineData = lineText.trim().split("\\s+");
				lineText = String.join(",", lineData);
				buffWriter.write(lineText);
				buffWriter.newLine();
			}
		} finally {
			buffWriter.close();
			buffReader.close();
		}		
		Files.copy(Paths.get(workingFullPath), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

		String newFileName = Paths.get(filePath).getFileName().toString().substring(0, Paths.get(filePath).getFileName().toString().indexOf("."));
		csvPath = Paths.get(TestContext.INSTANCE.getTestSetup().getDownloadPath(), newFileName+".csv").toString();

		File csvFile = new File (csvPath);

		writeFile.renameTo(csvFile);
	}

	public static void readPeakExport() throws IOException {

		DatUtility dUtil = new DatUtility();
		dUtil.convertDATtoCSV(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\datUtility-tests\\PeakExport.dat");
		List<String> headings1 = dUtil.getHeadings();
		for (String heading1:headings1){
			List<Map<String,String>> rows1=dUtil.getAllRows();
			Map<String, String> map1 = Collections.synchronizedMap(new HashMap<String, String>());
			for(int i=0; i<rows1.size(); i++){
				map1 =rows1.get(i);
				for(Map.Entry<String,String> entry:map1.entrySet()){
					Assert.assertTrue((entry.getKey() != null) && (entry.getKey() != ""));
					Assert.assertTrue((entry.getValue() != null) && (entry.getValue() != ""));
				}
			}
		}
	}

	public static void readAnalysisExport() throws IOException {

		DatUtility dUtil = new DatUtility();
		dUtil.convertDATtoCSV(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\datUtility-tests\\AnalysisExport.dat");
		List<String> headings3 = dUtil.getHeadings();
		for (String heading3:headings3){
			List<Map<String,String>> rows3=dUtil.getAllRows();
			Map<String, String> map3 = Collections.synchronizedMap(new HashMap<String, String>());
			for(int i=0; i<rows3.size(); i++){
				map3 =rows3.get(i);
				for(Map.Entry<String,String> entry:map3.entrySet()){
					Assert.assertTrue((entry.getKey() != null) && (entry.getKey() != ""));
					Assert.assertTrue((entry.getValue() != null) && (entry.getValue() != ""));
				} 
			}
		}
	}

	public static void readSurveyExport() throws IOException {

		DatUtility dUtil = new DatUtility();
		dUtil.convertDATtoCSV(TestSetup.getExecutionPath(TestSetup.getRootPath())+ "data\\test-data\\datUtility-tests\\SurveyExport.dat");
		List<String> headings2 = dUtil.getHeadings();
		for (String heading2:headings2){
			List<Map<String,String>> rows2=dUtil.getAllRows();
			Map<String, String> map2 = Collections.synchronizedMap(new HashMap<String, String>());
			for(int i=0; i<rows2.size(); i++){
				map2 =rows2.get(i);
				for(Map.Entry<String,String> entry:map2.entrySet()){
					Assert.assertTrue((entry.getKey() != null) && (entry.getKey() != ""));
					Assert.assertTrue((entry.getValue() != null) && (entry.getValue() != ""));
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Log.info("Running test - readSurveyExport() ...");
		readSurveyExport();
		Log.info("Running test - readAnalysisExport() ...");
		readAnalysisExport();
		Log.info("Running test - readPeakExport() ...");
		readPeakExport();
	}
}