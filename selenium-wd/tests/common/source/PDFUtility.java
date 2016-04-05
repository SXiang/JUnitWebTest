package common.source;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import org.apache.pdfbox.util.PDFTextStripper;
import org.testng.Assert;

public class PDFUtility {
	
	/*
	 * Extracts text from all pages from specified PDF.
	 * Returns the extracted text.
	 * 
	 */
	public String extractPDFText(String pdfFilePath) throws IOException {
		
		return extractPDFText(pdfFilePath, -1 /*startPage*/, -1 /*endPage*/);
	}

	/*
	 * Extracts text from specified pages from specified PDF.
	 * Returns the extracted text.
	 * 
	 */
	public String extractPDFText(String pdfFilePath, int startPage, int endPage) throws IOException {
		
		FileInputStream inputStream = null;
		PDDocument pdDocument = null;
		StringWriter stringWriter = new StringWriter(); 
		try
		{
			inputStream = new FileInputStream(pdfFilePath);
			PDFParser pdfParser = new PDFParser(inputStream);
			pdfParser.parse();
			pdDocument = pdfParser.getPDDocument();
			
			PDFTextStripper pdfTextStripper = new PDFTextStripper();
			if (startPage > 0) {
				pdfTextStripper.setStartPage(startPage);
			}
			if (endPage > 0) {
				pdfTextStripper.setEndPage(endPage);
			}
			pdfTextStripper.writeText(pdDocument, stringWriter);
		}
		finally
		{
			if (pdDocument != null) {
				pdDocument.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		}
		
		return stringWriter.toString();
	}

	/*
	 * Extracts images from all pages from specified PDF and save them to a temporary output folder.
	 * Returns the path of the output folder where the images are saved.
	 * 
	 * @pdfFilePath - input PDF file to be read.
	 * @outputDirectoryPrefix - prefix appended to output directory. Leave blank or EMPTY for NO prefix.
	 * 
	 */
	public String extractPDFImages(String pdfFilePath, String outputDirectoryPrefix) throws IOException {
		return extractPDFImages(pdfFilePath, outputDirectoryPrefix, -1, -1);
	}
	
	/*
	 * Extracts images from the specified pages from specified PDF and save them to a temporary output folder.
	 * Returns the path of the output folder where the images are saved.
	 * 
	 * @pdfFilePath - input PDF file to be read.
	 * @outputDirectoryPrefix - prefix appended to output directory. Leave blank or EMPTY for NO prefix.
	 * @startPage - start page to begin reading from. If -1 is specified all pages are read.
	 * @endPage - end page to end reading at. If -1 is specified all pages are read.
	 * 
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String extractPDFImages(String pdfFilePath, String outputDirectoryPrefix, int startPage, int endPage) throws IOException {
	
		FileInputStream inputStream = null;
		PDDocument pdDocument = null;
		
		String tempDirectory = TestSetup.getSystemTempDirectory();
		String uniqueString = TestSetup.getUUIDString();
		if (outputDirectoryPrefix != null && outputDirectoryPrefix != "") {
			uniqueString = outputDirectoryPrefix + uniqueString; 
		}
		Path uniqueDirectory = Paths.get(tempDirectory, uniqueString);
		Files.createDirectory(uniqueDirectory);
		
		try
		{
			inputStream = new FileInputStream(pdfFilePath);
			PDFParser pdfParser = new PDFParser(inputStream);
			pdfParser.parse();
			pdDocument = pdfParser.getPDDocument();
			
			List<PDPage> allPages = pdDocument.getDocumentCatalog().getAllPages();
			Integer pageNum = 0;
			for (PDPage pdPage : allPages) {
				pageNum++;
				
				boolean isValidPage = false;
				if ((startPage == -1) || (endPage == -1)) {
					isValidPage = true;
				} else if ((pageNum >= startPage) && (pageNum <= endPage)) {
					isValidPage = true;
				}
				
				if (isValidPage) {
					PDResources resources = pdPage.getResources();
					Map<String, PDXObjectImage> images = resources.getImages();
					Set<String> keySet = images.keySet();
					for (String key : keySet) {
						PDXObjectImage pdxObjectImage = images.get(key);
						Path filePath = Paths.get(uniqueDirectory.toString(), pageNum.toString() + "_" + TestSetup.getUUIDString() + ".jpg");
						pdxObjectImage.write2file(new File(filePath.toString()));
					}
				}
			}
		}
		finally
		{
			if (pdDocument != null) {
				pdDocument.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		}
		
		return uniqueDirectory.toString();
	}
	
	/**
	 * Executes the unit tests for this class.
	 * @param args
	 */
	public static void main(String[] args) {
		Path pdfDirectory;
		List<String> pdfFilesInDirectory = null;
		HashMap<String, String> expectedTextMap = new HashMap<String, String>();
		expectedTextMap.put("CustomerSupervisorReportTC739195603.pdf", "Customer Supervisor Report TC739 195603");
		expectedTextMap.put("IV-12BF45.pdf", "IV-12BF45");
		expectedTextMap.put("IV-1505B7.pdf", "IV-1505B7");
		expectedTextMap.put("IV-6AD1AB.pdf", "IV-6AD1AB");
		expectedTextMap.put("LISAassetexcl0.pdf", "LISA asset excl 0");
		expectedTextMap.put("Lisaonly.pdf", "CR-C7F852");
		expectedTextMap.put("sqatest-shape.pdf", "CR-957A38");
		expectedTextMap.put("TC155.pdf", "NE Lat & NE Long 37.44794 X -122.18017");
		expectedTextMap.put("TC156.pdf", "CR-FED2EE");
		expectedTextMap.put("TC193.pdf", "Boundary IF00000000000000037623  DistrictPlat-3347-A7");
		expectedTextMap.put("TestReportPGE-nolisa.pdf", "Boundary IF00000000000000037365  DistrictPlat-3279-J6");
		expectedTextMap.put("TestReportPGE-surveynotpartofplat.pdf", "CR-E8B8AD");

		HashMap<String, String> filesWithNoTextMap = new HashMap<String, String>();
		filesWithNoTextMap.put("CustomerSupervisorReportTC739195603_First View.pdf", "");
		filesWithNoTextMap.put("CustomerSupervisorReportTC739195603_Second View.pdf", "");
		filesWithNoTextMap.put("CustomerSupervisorReportTC739195603_Third View.pdf", "");
		filesWithNoTextMap.put("LISAassetexcl0_View 1.pdf", "");
		filesWithNoTextMap.put("Lisaonly_View 1.pdf", "");
		filesWithNoTextMap.put("sqatest-shape_View 1.pdf", "");
		filesWithNoTextMap.put("TC155_View 1.pdf", "");
		filesWithNoTextMap.put("TC156_View 1.pdf", "");
		filesWithNoTextMap.put("TC193_View 1.pdf", "");
		filesWithNoTextMap.put("TestReportPGE-nolisa_View 1.pdf", "");
		filesWithNoTextMap.put("TestReportPGE-surveynotpartofplat_View 1.pdf", "");
		
		HashMap<String, Integer> expectedImageCountMap = new HashMap<String, Integer>();
		expectedImageCountMap.put("CustomerSupervisorReportTC739195603.pdf", 41);
		expectedImageCountMap.put("CustomerSupervisorReportTC739195603_First View.pdf", 1);
		expectedImageCountMap.put("IV-12BF45.pdf", 17);
		expectedImageCountMap.put("IV-1505B7.pdf", 5);
		expectedImageCountMap.put("IV-6AD1AB.pdf", 23);
		expectedImageCountMap.put("LISAassetexcl0.pdf", 36);
		expectedImageCountMap.put("Lisaonly.pdf", 58);

		HashMap<String, Integer> firstPageImageCountMap = new HashMap<String, Integer>();
		firstPageImageCountMap.put("CustomerSupervisorReportTC739195603.pdf", 5);
		firstPageImageCountMap.put("CustomerSupervisorReportTC739195603_First View.pdf", 1);
		firstPageImageCountMap.put("IV-12BF45.pdf", 17);
		firstPageImageCountMap.put("IV-1505B7.pdf", 5);
		firstPageImageCountMap.put("IV-6AD1AB.pdf", 23);
		firstPageImageCountMap.put("LISAassetexcl0.pdf", 5);
		firstPageImageCountMap.put("Lisaonly.pdf", 5);		

		HashMap<String, Integer> lastPageImageCountMap = new HashMap<String, Integer>();
		lastPageImageCountMap.put("CustomerSupervisorReportTC739195603.pdf", 1);
		lastPageImageCountMap.put("CustomerSupervisorReportTC739195603_First View.pdf", 1);
		lastPageImageCountMap.put("IV-12BF45.pdf", 17);
		lastPageImageCountMap.put("IV-1505B7.pdf", 5);
		lastPageImageCountMap.put("IV-6AD1AB.pdf", 23);
		lastPageImageCountMap.put("LISAassetexcl0.pdf", 1);
		lastPageImageCountMap.put("Lisaonly.pdf", 1);		

		HashMap<String, Integer> zeroImageCountMap = new HashMap<String, Integer>();
		zeroImageCountMap.put("Text_plus_TABLE_PDF.pdf", 0);
		zeroImageCountMap.put("TextONLY_PDF.pdf", 0);

		HashMap<String, Integer> lastPageNumberMap = new HashMap<String, Integer>();
		lastPageNumberMap.put("CustomerSupervisorReportTC739195603.pdf", 3);
		lastPageNumberMap.put("CustomerSupervisorReportTC739195603_First View.pdf", 1);
		lastPageNumberMap.put("IV-12BF45.pdf", 1);
		lastPageNumberMap.put("IV-1505B7.pdf", 1);
		lastPageNumberMap.put("IV-6AD1AB.pdf", 1);
		lastPageNumberMap.put("LISAassetexcl0.pdf", 4);
		lastPageNumberMap.put("Lisaonly.pdf", 3);
		lastPageNumberMap.put("TC155.pdf", 3);
		lastPageNumberMap.put("TestReportPGE-surveynotpartofplat.pdf", 3);
		
		PDFUtility pdfUtility = new PDFUtility();

		try {
			pdfDirectory = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data\\test-data\\pdfutility-tests");
			pdfFilesInDirectory = FileUtility.getFilesInDirectory(pdfDirectory);

			for (String filePath : pdfFilesInDirectory) {
				String filename = Paths.get(filePath).getFileName().toString();
				
				if (expectedTextMap.containsKey(filename)) {
					pdfUtility.testExtractPDFText_validPDF(Paths.get(filePath).toString(), 
							expectedTextMap.get(filename));
					pdfUtility.testExtractPDFText_validPDF_firstPage(Paths.get(filePath).toString(), 
							expectedTextMap.get(filename));
					if (lastPageNumberMap.containsKey(filename) && lastPageNumberMap.containsKey(lastPageNumberMap)) {
						pdfUtility.testExtractPDFText_validPDF_lastPage(Paths.get(filePath).toString(), lastPageNumberMap.get(filename), 
								expectedTextMap.get(filename));
						pdfUtility.testExtractPDFText_validPDF_innerPages(Paths.get(filePath).toString(), 1, lastPageNumberMap.get(filename), 
								expectedTextMap.get(filename));
					}
				} else if (filesWithNoTextMap.containsKey(filename)) {
					pdfUtility.testExtractPDFText_invalidPDF(Paths.get(filePath).toString(), 
							filesWithNoTextMap.get(filename));					
				}
				
				if (expectedImageCountMap.containsKey(filename)) {
					pdfUtility.testExtractImages_validPDF(Paths.get(filePath).toString(), 
							expectedImageCountMap.get(filename));
					if (firstPageImageCountMap.containsKey(filename)) {
						pdfUtility.testExtractImages_validPDF_firstPage(Paths.get(filePath).toString(), 
								firstPageImageCountMap.get(filename));
					}
					if (lastPageImageCountMap.containsKey(filename) && lastPageNumberMap.containsKey(filename)) {
						pdfUtility.testExtractImages_validPDF_lastPage(Paths.get(filePath).toString(), lastPageNumberMap.get(filename), 
								lastPageImageCountMap.get(filename));
					}
					if (lastPageNumberMap.containsKey(filename)) {
						pdfUtility.testExtractImages_validPDF_innerPages(Paths.get(filePath).toString(), 1, lastPageNumberMap.get(filename), 
								expectedImageCountMap.get(filename));
					}
				} else if (zeroImageCountMap.containsKey(filename)) {
					pdfUtility.testExtractImages_invalidPDF(Paths.get(filePath).toString(), 
							zeroImageCountMap.get(filename));
				}
			}
		} catch (IOException e) {
			Log.error(e.toString());
		}
	}

	private void testExtractPDFText_validPDF(String filePath, String expectedText) throws IOException {
		String pdfText = extractPDFText(filePath);
		Assert.assertTrue(pdfText.contains(expectedText), String.format("Text - '%s' NOT found in file - %s", expectedText, filePath));
		Log.info("Completed test - testExtractPDFText_validPDF() for file - " + filePath);
	}

	private void testExtractPDFText_invalidPDF(String filePath, String expectedText) throws IOException {
		String pdfText = extractPDFText(filePath);
		Assert.assertTrue(pdfText.contains(expectedText), String.format("Text - '%s' NOT found in file - %s", expectedText, filePath));
		Log.info("Completed test - testExtractPDFText_invalidPDF() for file - " + filePath);
	}
	
	private void testExtractPDFText_validPDF_firstPage(String filePath, String expectedText) throws IOException {
		String pdfText = extractPDFText(filePath, 1, 1);
		Assert.assertTrue(pdfText.contains(expectedText), String.format("Text - '%s' NOT found in file - %s", expectedText, filePath));
		Log.info("Completed test - testExtractPDFText_validPDF_firstPage() for file - " + filePath);
	}

	private void testExtractPDFText_validPDF_lastPage(String filePath, int endPage, String expectedText) throws IOException {
		String pdfText = extractPDFText(filePath, endPage, endPage);
		Assert.assertTrue(pdfText.contains(expectedText), String.format("Text - '%s' NOT found in file - %s", expectedText, filePath));
		Log.info("Completed test - testExtractPDFText_validPDF_lastPage() for file - " + filePath);
	}

	private void testExtractPDFText_validPDF_innerPages(String filePath, int startPage, int endPage, String expectedText) throws IOException {
		String pdfText = extractPDFText(filePath, startPage, endPage);
		Assert.assertTrue(pdfText.contains(expectedText), String.format("Text - '%s' NOT found in file - %s", expectedText, filePath));
		Log.info("Completed test - testExtractPDFText_validPDF_innerPages() for file - " + filePath);
	}

	private void testExtractImages_validPDF(String filePath, int expectedImageCount) throws IOException {
		String imageDirectory = extractPDFImages(filePath, Paths.get(filePath).getFileName().toString() + "_");
		List<String> filesInDirectory = FileUtility.getFilesInDirectory(Paths.get(imageDirectory));
		int actualImageCount = (filesInDirectory != null) ? filesInDirectory.size() : 0;
		Assert.assertTrue(actualImageCount == expectedImageCount, String.format("Expected - '%d' files in PDF - %s. Found - '%d' files", 
				expectedImageCount, filePath, actualImageCount));
		FileUtility.deleteDirectoryAndFiles(Paths.get(imageDirectory));
		Log.info("Completed test - testExtractImages_validPDF() for file - " + filePath);
	}

	private void testExtractImages_invalidPDF(String filePath, int expectedImageCount) throws IOException {
		String imageDirectory = extractPDFImages(filePath, Paths.get(filePath).getFileName().toString() + "_");
		List<String> filesInDirectory = FileUtility.getFilesInDirectory(Paths.get(imageDirectory));
		int actualImageCount = (filesInDirectory != null) ? filesInDirectory.size() : 0;
		Assert.assertTrue(actualImageCount == expectedImageCount, String.format("Expected - '%d' files in PDF - %s. Found - '%d' files", 
				expectedImageCount, filePath, actualImageCount));
		FileUtility.deleteDirectoryAndFiles(Paths.get(imageDirectory));
		Log.info("Completed test - testExtractImages_invalidPDF() for file - " + filePath);
	}
	
	private void testExtractImages_validPDF_firstPage(String filePath, int expectedImageCount) throws IOException {
		String imageDirectory = extractPDFImages(filePath, Paths.get(filePath).getFileName().toString() + "_", 1, 1);
		List<String> filesInDirectory = FileUtility.getFilesInDirectory(Paths.get(imageDirectory));
		int actualImageCount = (filesInDirectory != null) ? filesInDirectory.size() : 0;
		Assert.assertTrue(actualImageCount == expectedImageCount, String.format("Expected - '%d' files in PDF - %s. Found - '%d' files", 
				expectedImageCount, filePath, actualImageCount));
		FileUtility.deleteDirectoryAndFiles(Paths.get(imageDirectory));
		Log.info("Completed test - testExtractImages_validPDF_firstPage() for file - " + filePath);
	}

	private void testExtractImages_validPDF_lastPage(String filePath, int endPage, int expectedImageCount) throws IOException {
		String imageDirectory = extractPDFImages(filePath, Paths.get(filePath).getFileName().toString() + "_", endPage, endPage);
		List<String> filesInDirectory = FileUtility.getFilesInDirectory(Paths.get(imageDirectory));
		int actualImageCount = (filesInDirectory != null) ? filesInDirectory.size() : 0;
		Assert.assertTrue(actualImageCount == expectedImageCount, String.format("Expected - '%d' files in PDF - %s. Found - '%d' files", 
				expectedImageCount, filePath, actualImageCount));
		FileUtility.deleteDirectoryAndFiles(Paths.get(imageDirectory));
		Log.info("Completed test - testExtractImages_validPDF_lastPage() for file - " + filePath);
	}

	private void testExtractImages_validPDF_innerPages(String filePath, int startPage, int endPage, int expectedImageCount) throws IOException {
		String imageDirectory = extractPDFImages(filePath, Paths.get(filePath).getFileName().toString() + "_", startPage, endPage);
		List<String> filesInDirectory = FileUtility.getFilesInDirectory(Paths.get(imageDirectory));
		int actualImageCount = (filesInDirectory != null) ? filesInDirectory.size() : 0;
		Assert.assertTrue(actualImageCount == expectedImageCount, String.format("Expected - '%d' files in PDF - %s. Found - '%d' files", 
				expectedImageCount, filePath, actualImageCount));
		FileUtility.deleteDirectoryAndFiles(Paths.get(imageDirectory));
		Log.info("Completed test - testExtractImages_validPDF_innerPages() for file - " + filePath);
	}
}
