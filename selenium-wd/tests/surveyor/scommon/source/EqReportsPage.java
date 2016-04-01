package surveyor.scommon.source;

import static common.source.BaseHelper.matchSinglePattern;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataaccess.source.StoredProcComplianceAssessmentGetReportDrivingSurveys;
import surveyor.dataaccess.source.StoredProcEQAddedSurveys;
import surveyor.dataaccess.source.StoredProcEQGetEQData;
import surveyor.scommon.source.LatLongSelectionControl.ControlMode;
import common.source.BaseHelper;
import common.source.DBConnection;
import common.source.DateUtility;
import common.source.FileUtility;
import common.source.Log;
import common.source.PDFUtility;
import common.source.ProcessUtility;
import common.source.RegexUtility;
import common.source.TestSetup;

public class EqReportsPage extends ReportsBasePage {

	public static final String EQRPTURLPath = "/Reports/EQReports";
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.EqReports_PageTitle);
	public static final String STRNewPageContentText = Resources.getResource(ResourceKeys.EqReport_PageTitle);
	public static final String EQReportSSRS_EmissionsQuantificationReport = Resources.getResource(ResourceKeys.EQReportSSRS_EmissionsQuantificationReport);
	public static final String EQReportSSRS_EmissionsQuantificationData = Resources.getResource(ResourceKeys.EQReportSSRS_EmissionsQuantificationData);

	private String reportName;

	@FindBy(how = How.XPATH, using = "//*[@href='/Reports/EQReport']")
	protected WebElement btnNewEQRpt;

	@FindBy(how = How.XPATH, using = "//*[@id='btn-EQ-select-area']")
	protected WebElement btnSelectArea;

	@FindBy(how = How.XPATH, using = "//*[@id='report-title']")
	protected WebElement eqRptTtl;

	@FindBy(how = How.XPATH, using = "//*[@id='eq-selected-text']")
	protected WebElement eqRptArea;
	
	@FindBy(how = How.ID, using = "pdf")
	protected WebElement pdfImg;

	@FindBy(how = How.ID, using = "zip-file_tif")
	protected WebElement zipImg;
	
	private  LatLongSelectionControl latLongSelectionControl;
	
	private  final String CANVAS_X_PATH = "//*[@id=\"map\"]/div/canvas";

	public WebElement getBtnNewEQRpt() {
		return this.btnNewEQRpt;
	}

	public WebElement getSelectArea() {
		return this.btnSelectArea;
	}

	public WebElement getEqRptTtl() {
		return this.eqRptTtl;
	}

	public WebElement getEqRptArea() {
		return this.eqRptArea;
	}

	public EqReportsPage(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup, strBaseURL + EQRPTURLPath);

		Log.info("\nThe EQ Reports Page URL is: %s\n" + this.strPageURL);
		
		latLongSelectionControl = new LatLongSelectionControl(driver);
		PageFactory.initElements(driver, latLongSelectionControl);
	}

	/**
	 * Method to verify the Driving Surveys Table in SSRS
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyDrivingSurveysTable(String reportTitle, String actualPath) throws IOException {
		PDFUtility pdfUtility = new PDFUtility();
		DateUtility date = new DateUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = actualPath + "EQ-" + reportId.substring(0, 6) + ".pdf";
		reportName = "EQ-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(EQReportSSRS_EmissionsQuantificationReport);
		expectedReportString.add(EQReportSSRS_EmissionsQuantificationData);

		HashMap<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value)
				return false;
		}
		String surveyTable = RegexUtility.getStringInBetween(actualReportString, "in)", "Selected Driving Surveys");
		InputStream inputStream = new ByteArrayInputStream(surveyTable.getBytes());
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		ArrayList<String> lineList = new ArrayList<String>();
		try {
			StringBuilder lineBuilder = new StringBuilder();
			while ((line = bufferReader.readLine()) != null) {
				if ((line.length() > 2) && (!line.equals("ili"))) {
					lineBuilder.append(line.replaceAll("\\s", ""));
					if (line.matches(".* [ABCDEF]$")) {
						lineList.add(lineBuilder.toString());
						lineBuilder = new StringBuilder();
					}
				}
			}
			ArrayList<StoredProcEQAddedSurveys> reportSurveyList = StoredProcEQAddedSurveys.getReportDrivingSurveys(reportId);
			Iterator<StoredProcEQAddedSurveys> lineIterator = reportSurveyList.iterator();
			ArrayList<String> objToStringList = new ArrayList<String>();
			while (lineIterator.hasNext()) {
				StoredProcEQAddedSurveys storedProcObj = lineIterator.next();
				long duration = date.getDuration(storedProcObj.getStartDateTimeWithTZ(), storedProcObj.getEndDateTimeWithTZ(), true);
				String objToString = storedProcObj.getStartDateTimeWithTZ() + storedProcObj.getEndDateTimeWithTZ() + duration + storedProcObj.getUserName() + storedProcObj.getDescription() + storedProcObj.getAnalyzerId() + storedProcObj.getTag() + storedProcObj.getStabilityClass();
				objToStringList.add(objToString.trim().replaceAll("\\s", ""));
			}
			objToStringList.removeAll(lineList);
			if (objToStringList.size() == 0) {
				return true;
			}
		} finally {
			bufferReader.close();
		}
		return false;
	}

	/**
	 * Method to verify the Emission Quantification Data Table in SSRS
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyEmissionQuantificationDataTable(String reportTitle, String actualPath) throws IOException {
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = actualPath + "EQ-" + reportId.substring(0, 6) + ".pdf";
		reportName = "EQ-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		String emissionsTable = RegexUtility.getStringInBetween(actualReportString, "/ Leak", "St").trim();
		ArrayList<StoredProcEQGetEQData> storedProcSurveyList = StoredProcEQGetEQData.getEQData(reportId);
		if (emissionsTable == null && storedProcSurveyList == null) {
			return true;
		}
		InputStream inputStream = new ByteArrayInputStream(emissionsTable.getBytes());
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		ArrayList<StoredProcEQGetEQData> reportList = new ArrayList<StoredProcEQGetEQData>();
		try {
			while ((line = bufferReader.readLine()) != null) {
				StoredProcEQGetEQData eqDataObj = new StoredProcEQGetEQData();
				String remaining = line.trim();
				String[] splits = line.split("\\s+");
				eqDataObj.setName(splits[0].trim());
				remaining = remaining.substring(splits[0].length()).trim();
				eqDataObj.setEmissionRank(splits[1].trim());
				remaining = remaining.substring(splits[1].length()).trim();
				eqDataObj.setEmissionRate(Double.parseDouble(splits[2].trim()));
				remaining = remaining.substring(splits[2].length()).trim();
				eqDataObj.setRatePerLeak(Double.parseDouble(splits[splits.length - 1].trim()));
				remaining = remaining.substring(0, remaining.length() - splits[splits.length - 1].length()).trim();
				eqDataObj.setLeaksPerFt(Double.parseDouble(splits[splits.length - 2].trim()));
				remaining = remaining.substring(0, remaining.length() - splits[splits.length - 2].length()).trim();
				eqDataObj.setNumLeaks(Integer.parseInt(splits[splits.length - 3].trim()));
				remaining = remaining.substring(0, remaining.length() - splits[splits.length - 3].length()).trim();
				eqDataObj.setEmissionFactor(Double.parseDouble(splits[splits.length - 4].trim()));
				remaining = remaining.substring(0, remaining.length() - splits[splits.length - 4].length()).trim();
				eqDataObj.setLength(Integer.parseInt(splits[splits.length - 5].trim()));
				remaining = remaining.substring(0, remaining.length() - splits[splits.length - 5].length()).trim();
				eqDataObj.setConfideneceGroup(remaining.trim());
				reportList.add(eqDataObj);
			}
			Iterator<StoredProcEQGetEQData> storedProcSurveyListIte = storedProcSurveyList.iterator();
			while (storedProcSurveyListIte.hasNext()) {

				if (!storedProcSurveyListIte.next().isInList(reportList)) {
					return false;
				}
			}

		} finally {
			bufferReader.close();
		}
		return true;
	}
	
	public boolean validatePdfFiles(String reportTitle, String downloadPath) {
		String reportId;
		String reportName;
		DBConnection objDbConn = new DBConnection();

		try {
			reportId = objDbConn.getIdOfSpecifiedReportTitle(reportTitle, this.testSetup);
			reportId = reportId.substring(0, 6);
			reportName = "EQ-" + reportId;
			setReportName(reportName);
			BaseHelper.deCompressZipFile(reportName, downloadPath);
		} catch (Exception e) {
			Log.error(e.toString());
			return false;
		}
		String pdfFile1 = downloadPath + reportName + ".pdf";
		String pdfFile2 = downloadPath + reportName+"_EQ-View.pdf";			
		if (!BaseHelper.validatePdfFile(pdfFile1)&&!BaseHelper.validatePdfFile(pdfFile2))
			return false;
		return true;
	}

	public void setReportName(String reportTitle) {
		this.reportName = reportTitle;
	}
	
	@Override
	public void fillEqSpecific(Reports reports) {	
		ReportsEQ eqReports= (ReportsEQ)reports;
		getSelectArea().click();
		for(List<Coordinates> coordinates:eqReports.getListOfCords()){
		latLongSelectionControl.waitForModalDialogOpen()
								.switchMode(ControlMode.MapInteraction)
								.waitForMapImageLoad()
								.selectSegment(CANVAS_X_PATH, coordinates)
								.switchMode(ControlMode.Default);
								
		}
		latLongSelectionControl.clickOkButton();
		
	}
	
	@Override
	public void eqSpecificFileDownloads(String rptTitle, String testCaseID) {		
		Report objReport = Report.getReport(rptTitle);
		String reportId = objReport.getId();
		reportId = reportId.substring(0, 6);
		setReportName("EQ-" + reportId);
		clickOnPDFInReportViewer();
		waitForPDFFileDownload(getReportName());
		clickOnZIPInReportViewer();
		waitForReportTIFFileDownload(getReportName());
		
	
	}
	
	public void clickOnZIPInReportViewer() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", zipImg);
	}

	public void clickOnPDFInReportViewer() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", pdfImg);
	}
	
	public void waitForPDFFileDownload(String reportName) {
		waitForFileDownload(reportName + ".pdf", testSetup.getDownloadPath());
	}

	public void waitForReportTIFFileDownload(String reportName) {
		waitForFileDownload(reportName +"_EQ-View.pdf", testSetup.getDownloadPath());
	}


	@Override
	public void waitForPageLoad() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getPageSource().contains(STRPageContentText);
			}
		});
	}

	public void waitForNewPageLoad() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getPageSource().contains(STRNewPageContentText);
			}
		});
	}

	public void waitForEQRptButton() {
		new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getPageSource().contains(STRPageContentText);
			}
		});
	}

	public void clickOnNewEQReportBtn() {
		this.btnNewEQRpt.click();
	}

}
