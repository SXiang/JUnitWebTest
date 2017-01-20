/**
 *
 */
package surveyor.scommon.source;

import static common.source.BaseHelper.matchSinglePattern;
import common.source.DateUtility;
import common.source.Downloader;
import common.source.FileUtility;
import common.source.NumberUtility;
import static surveyor.scommon.source.SurveyorConstants.CUSBOUNDARY;
import static surveyor.scommon.source.SurveyorConstants.ENDDATE;
import static surveyor.scommon.source.SurveyorConstants.REXCLUSIONRADIUS;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPHEIGHT;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPWIDTH;
import static surveyor.scommon.source.SurveyorConstants.KEYANNOTATION;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYBASEMAP;
import static surveyor.scommon.source.SurveyorConstants.KEYHIGHLIGHTLISAASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYHIGHLIGHTBOXASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYHIGHLIGHTGAPASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETBOXNUMBER;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARIES;
import static surveyor.scommon.source.SurveyorConstants.KEYBREADCRUMB;
import static surveyor.scommon.source.SurveyorConstants.KEYFOV;
import static surveyor.scommon.source.SurveyorConstants.KEYGAPS;
import static surveyor.scommon.source.SurveyorConstants.KEYGAPTB;
import static surveyor.scommon.source.SurveyorConstants.KEYINDICATIONS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDTB;
import static surveyor.scommon.source.SurveyorConstants.KEYISOANA;
import static surveyor.scommon.source.SurveyorConstants.KEYISOTOPICCAPTURE;
import static surveyor.scommon.source.SurveyorConstants.KEYLISA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCF;
import static surveyor.scommon.source.SurveyorConstants.KEYPCRA;
import static surveyor.scommon.source.SurveyorConstants.KEYVIEWNAME;
import static surveyor.scommon.source.SurveyorConstants.RNELAT;
import static surveyor.scommon.source.SurveyorConstants.RNELON;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;
import static surveyor.scommon.source.SurveyorConstants.REPORTMODES1;
import static surveyor.scommon.source.SurveyorConstants.STARTDATE;
import static surveyor.scommon.source.SurveyorConstants.SURVEYORUNIT;
import static surveyor.scommon.source.SurveyorConstants.RSWLAT;
import static surveyor.scommon.source.SurveyorConstants.RSWLON;
import static surveyor.scommon.source.SurveyorConstants.TAG;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;

import static surveyor.scommon.source.SurveyorConstants.KEYASSETCASTIRON;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETCOPPER;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETOTHERPLASTIC;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETPEPLASTIC;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETPROTECTEDSTEEL;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETUNPROTECTEDSTEEL;

import surveyor.scommon.entities.BaseReportEntity;
import surveyor.scommon.entities.BaseReportEntity.ReportModeFilter;
import surveyor.scommon.entities.BaseReportEntity.SSRSPdfFooterColumns;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;
import surveyor.scommon.entities.ComplianceReportEntity;
import surveyor.scommon.entities.ReportCommonEntity.EthaneFilter;
import surveyor.scommon.entities.ReportCommonEntity.LISAIndicationTableColumns;
import surveyor.scommon.entities.ReportsSurveyInfo.ColumnHeaders;
import surveyor.scommon.source.DataTablePage.TableColumnType;
import surveyor.scommon.source.LatLongSelectionControl.ControlMode;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.ArrayUtility;
import common.source.BaseHelper;
import common.source.CSVUtility;
import common.source.Log;
import common.source.LogCategory;
import common.source.LogHelper;
import common.source.PDFTableUtility;
import common.source.PDFTableUtility.PDFTable;
import common.source.TestSetup;
import common.source.TextUtility;
import common.source.WebElementExtender;
import sun.misc.BASE64Decoder;
import surveyor.dataaccess.source.BaseMapType;
import surveyor.dataaccess.source.DBCache;
import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.ReportView;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataaccess.source.StoredProcComplianceAssessmentGetReportDrivingSurveys;
import surveyor.dataaccess.source.StoredProcComplianceGetCoverage;
import surveyor.dataaccess.source.StoredProcComplianceGetCoverageForecast;
import surveyor.dataaccess.source.StoredProcComplianceGetEthaneCapture;
import surveyor.dataaccess.source.StoredProcComplianceGetGaps;
import surveyor.dataaccess.source.StoredProcComplianceGetIndications;
import surveyor.dataaccess.source.StoredProcComplianceGetIsotopics;
import surveyor.dataaccess.source.StoredProcLisaInvestigationShowIndication;
import surveyor.dataprovider.ReportDataProvider;
import common.source.PDFUtility;
import common.source.ProcessUtility;
import common.source.RegexUtility;
import common.source.ShapeFileUtility;
import common.source.SortHelper;
import common.source.TestContext;

/**
 * @author zlu
 *
 */
public class ComplianceReportsPage extends ReportsCommonPage {

	@FindBy(id = "report-survey-end-dt")
	protected WebElement checkBoxAllSurvey;

	@FindBy(id = "report-survey-end-dt")
	protected WebElement checkBoxStndSurvey;

	@FindBy(id = "report-survey-end-dt")
	protected WebElement checkBoxRRSurvey;

	@FindBy(id = "report-survey-end-dt")
	protected WebElement checkBoxManualSurvey;

	@FindBy(id = "report-survey-end-dt")
	protected WebElement checkBoxOperatorSurvey;

	@FindBy(how = How.XPATH, using = "//*[@id='Standard']")
	protected WebElement checkBoxStndRptMode;

	@FindBy(how = How.XPATH, using = "//*[@id='Rapid Response']")
	protected WebElement checkBoxRRRptMode;

	@FindBy(how = How.XPATH, using = "//*[@id='Manual']")
	protected WebElement checkBoxManualRptMode;

	@FindBy(how = How.XPATH, using = "//*[@id='surveyModal']/div/div/div[3]/a[1]")
	protected WebElement btnChangeRptMode;

	@FindBy(how = How.XPATH, using = "//*[@id='surveyModal']/div/div/div[3]/a[2]")
	protected WebElement btnCancelRptMode;

	@FindBy(how = How.ID, using = "report-LISA-opacity")
	protected WebElement inputLISAOpacity;

	@FindBy(id = "report-ethene-vehicle-exhaust")
	protected WebElement checkBoxVehicleExhaust;

	@FindBy(id = "report-ethene-biogenic-methane")
	protected WebElement checkBoxEtheneBiogeniceMethane;

	@FindBy(id = "report-ethene-possible-natural-gas")
	protected WebElement checkBoxPossibleNaturalGas;

	@FindBy(how = How.XPATH, using = "//*[@id='report-show-percent-coverage-forecast']")
	protected WebElement percentCoverForecast;

	@FindBy(id = "buttonInvestigator")
	protected WebElement btnAssignInvestigators;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[4]/a[4]")
	protected WebElement btnFirstInvestigateCompliance;

	@FindBy(how = How.XPATH, using = "//div[@id='datatableBoxes_info']")
	protected WebElement paginationInvestigationMsg;

	@FindBy(how = How.XPATH, using = "//*[@id='datatableBoxes_filter']/label/input")
	protected WebElement inputInvestigationSearchReport;

	@FindBy(how = How.XPATH, using = "//*[@id='datatableBoxes']/tbody/tr/td[1]")
	protected WebElement tdInvReportTitle;

	@FindBy(how = How.XPATH, using = "//*[@id='datatableBoxes']/tbody/tr/td[3]")
	protected WebElement tdInvReportCreatedBy;

	public WebElement getPercentCoverForecast() {
		return this.percentCoverForecast;
	}

	public WebElement getCheckBoxStndRptMode() {
		return this.checkBoxStndRptMode;
	}

	/**
	 * @param driver
	 * @param strBaseURL
	 * @param testSetup
	 */
	public ComplianceReportsPage(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup);
	}

	public void invokeInvestigationPDFFileDownload(String rptTitle) throws Exception {
		Log.method("invokeInvestigationPDFFileDownload", rptTitle);
		invokeFileDownload(rptTitle, ReportFileType.InvestigationPDF);
	}

	public void invokeInvestigationDataFileDownload(String rptTitle) throws Exception {
		Log.method("invokeInvestigationDataFileDownload", rptTitle);
		invokeFileDownload(rptTitle, ReportFileType.InvestigationCSV);
	}

	public void inputLISAOpacity(String lisaOpacity) {
		Log.info("Set LISA Opacity to '" + lisaOpacity + "'");
		this.inputLISAOpacity.clear();
		this.inputLISAOpacity.sendKeys(lisaOpacity);
	}

	public ReportModeFilter getReportMode(String reportMode) {
		ReportModeFilter mode = ReportModeFilter.Manual;
		if (reportMode.equalsIgnoreCase("standard")) {
			mode = ReportModeFilter.Standard;
		} else if (reportMode.equalsIgnoreCase("assessment")) {
			mode = ReportModeFilter.Assessment;
		} else if (reportMode.equalsIgnoreCase("eq")) {
			mode = ReportModeFilter.EQ;

		} else if (reportMode.equalsIgnoreCase("operator")) {
			mode = ReportModeFilter.Operator;
		} else if (reportMode.equalsIgnoreCase("manual")) {
			mode = ReportModeFilter.Manual;
		} else if (reportMode.equalsIgnoreCase("rr") || reportMode.replaceAll(" ", "").equalsIgnoreCase("RapidResponse")) {
			mode = ReportModeFilter.RapidResponse;
		}
		return mode;
	}

	public SurveyModeFilter getSurveyMode(String surveyMode) {
		SurveyModeFilter mode = SurveyModeFilter.Manual;
		if (surveyMode.equalsIgnoreCase("standard")) {
			mode = SurveyModeFilter.Standard;
		} else if (surveyMode.equalsIgnoreCase("operator")) {
			mode = SurveyModeFilter.Operator;
		} else if (surveyMode.equalsIgnoreCase("manual")) {
			mode = SurveyModeFilter.Manual;
		} else if (surveyMode.equalsIgnoreCase("rr") || surveyMode.replaceAll(" ", "").equalsIgnoreCase("RapidResponse")) {
			mode = SurveyModeFilter.RapidResponse;
		} else if (surveyMode.equalsIgnoreCase("all")) {
			mode = SurveyModeFilter.All;
		}
		return mode;
	}

	public boolean investigateReport(String rptTitle, String strCreatedBy) {
		Log.method("ComplianceReportsPage.investigateReport", rptTitle, strCreatedBy);
		setPagination(PAGINATIONSETTING);

		this.waitForPageLoad();

		String reportTitleXPath;
		String createdByXPath;
		String investigateImgXPath;

		WebElement rptTitleCell;
		WebElement createdByCell;
		WebElement investigateImg;

		List<WebElement> rows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[3]";

			rptTitleCell = getTable().findElement(By.xpath(reportTitleXPath));
			createdByCell = getTable().findElement(By.xpath(createdByXPath));

			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle)
					&& createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy)) {
				investigateImgXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]/a[4]/img";
				investigateImg = getTable().findElement(By.xpath(investigateImgXPath));
				Log.clickElementInfo("Investigate", ElementType.ICON);
				investigateImg.click();
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				Log.clickElementInfo("Next");
				toNextPage();
				this.waitForPageLoad();

				List<WebElement> newRows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				rowSize = newRows.size();
				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);

				rowNum = 0;
			}
		}

		return false;
	}

	public void clickOnFirstInvestigateComplianceBtn() {
		Log.clickElementInfo("Investigate of the first compliance report", ElementType.LINK);
		this.btnFirstInvestigateCompliance.click();
	}

	public boolean searchInvestigationReport(String reportTitle, String reportCreatedBy) {
		this.inputInvestigationSearchReport.sendKeys(reportTitle);
		if (this.tdInvReportTitle.getText().contentEquals(reportTitle)) {
			if (this.tdInvReportCreatedBy.getText().contentEquals(reportCreatedBy))
				return true;
		}
		return false;
	}

	public void waitForNumberOfInvestigationRecords(String actualMessage) {
		(new WebDriverWait(driver, timeout + 15)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return paginationInvestigationMsg.getText().substring(0, 16).trim().equals(actualMessage);
			}
		});
	}

	private void waitForInvestigationPageLoad() {
		waitForAJAXCallsToComplete();
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getPageSource().contains(LisaInvestigations_PageTitle);

			}
		});
	}

	public boolean checkSurveyModeDidNotChange(ReportModeFilter mode, String tag, ReportModeFilter newMode) throws Exception {
		this.selectReportMode(mode);
		// this.waitForConfirmReportModeChangePopupToClose();
		this.inputSurveyTag(tag);
		this.clickOnSearchSurveyButton();
		this.waitForSurveyTabletoLoad();
		this.selectSurveysAndAddToReport(false, 1);
		this.selectReportModeNoConfirm(newMode);
		this.waitForCancelChangeReportModeButton();
		this.btnChangeModeCancel.click();
		return isReportModeSelected(mode);
	}

	@Override
	public void complianceChangeMode(String rptTitleNew, boolean changeMode, ReportModeFilter strReportMode) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (strReportMode != null && changeMode) {
			selectReportMode(strReportMode);
			this.waitForCopyReportPagetoLoad();
			this.inputTitle.clear();
			js.executeScript("arguments[0].value='" + rptTitleNew + "';", inputTitle);
		} else {
			this.waitForCopyReportPagetoLoad();
			js.executeScript("window.scrollBy(0,250)", "");
			this.waitForDeleteSurveyButtonToLoad();
			js.executeScript("arguments[0].click();", this.btnDeleteSurvey);
		}
	}

	public void selectPercentCoverageForecastCheckBox() {
		SelectElement(checkBoxPCF);
	}

	@Override
	public void selectReportMode(ReportModeFilter mode) {
		selectReportModeNoConfirm(mode);
		confirmChangeRptMode();

	}

	public void selectReportModeNoConfirm(ReportModeFilter mode) {
		WebElement radioButton = checkBoxStndRptMode;
		switch (mode) {
		case Standard:
			radioButton = checkBoxStndRptMode;
			break;
		case RapidResponse:
			radioButton = checkBoxRRRptMode;
			break;
		case Manual:
			radioButton = checkBoxManualRptMode;
			break;
		default:
			break;
		}
		jsClick(radioButton);
	}

	public boolean isReportModeSelected(ReportModeFilter mode) {
		switch (mode) {
		case Standard:
			return checkBoxStndRptMode.isSelected();
		case RapidResponse:
			return checkBoxRRRptMode.isSelected();
		case Manual:
			return checkBoxManualRptMode.isSelected();
		default:
			return false;
		}

	}

	public void confirmChangeRptMode() {
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		if (this.btnChangeRptMode.isDisplayed()) {
			this.btnChangeRptMode.click();
			this.waitForConfirmReportModeChangePopupToClose();
		}
	}

	public void cancelChangeRptMode() {
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		if (this.btnCancelRptMode.isDisplayed()) {
			this.btnCancelRptMode.click();
			this.waitForConfirmReportModeChangePopupToClose();
		}
	}

	public boolean verifySurveysTableViaSurveyMode(boolean changeMode, ReportModeFilter strReportMode,
			SurveyModeFilter surveyModeFilter) throws IOException {
		Log.method("ComplianceReportsPage.verifySurveysTableViaSurveyMode", changeMode, strReportMode.name(),
				surveyModeFilter.name());
		boolean result = false;

		if (strReportMode != null && changeMode) {
			selectReportMode(strReportMode);

			if (surveyModeFilter != null && changeMode) {
				selectSurveyModeForSurvey(surveyModeFilter);
			}
			this.waitForSurveySearchButtonToLoad();
			this.getBtnSurveySearch().click();
			this.waitForSurveyTabletoLoad();

			WebElement tabledata = driver.findElement(By.id("datatableSurveys"));
			List<WebElement> Rows = tabledata.findElements(By.xpath("//*[@id='datatableSurveys']/tbody/tr"));
			for (int getrowvalue = 1; getrowvalue < Rows.size(); getrowvalue++) {
				List<WebElement> Columns = Rows.get(getrowvalue)
						.findElements(By.xpath("//*[@id='datatableSurveys']/tbody/tr/td[7]"));
				for (int getcolumnvalue = 0; getcolumnvalue < Columns.size(); getcolumnvalue++) {
					String cellValue = driver
							.findElement(By.xpath("//*[@id='datatableSurveys']/tbody/tr[" + getrowvalue + "]/td[7]"))
							.getText();
					if (cellValue.contains(" ")) {
						String str = cellValue.replaceAll("\\s+", "");

						if (surveyModeFilter.name().equalsIgnoreCase(str)) {
							result = true;
							break;
						}
					}
					if (surveyModeFilter.name().equalsIgnoreCase(cellValue)) {
						result = true;
						break;
					}
				}
			}
		}
		return result;
	}

	public void selectEthaneFilter(EthaneFilter ethaneFilter) {
		selectEthaneFilter(ethaneFilter, true);
	}

	public void unselectEthaneFilter(EthaneFilter ethaneFilter) {
		selectEthaneFilter(ethaneFilter, false);
	}

	private void selectEthaneFilter(EthaneFilter ethaneFilter, boolean select) {
		List<WebElement> elements = new ArrayList<WebElement>();
		switch (ethaneFilter) {
		case ExcludeVehicleExhaust:
			elements.add(checkBoxVehicleExhaust);
			break;
		case ExcludeBiogenicMethane:
			elements.add(checkBoxEtheneBiogeniceMethane);
			break;
		case ExcludePossibleNaturalGas:
			elements.add(checkBoxPossibleNaturalGas);
			break;
		case None:
			select = false;
		case All:
			elements.add(checkBoxVehicleExhaust);
			elements.add(checkBoxEtheneBiogeniceMethane);
			elements.add(checkBoxPossibleNaturalGas);
			break;
		default:
			break;
		}
		for (WebElement element : elements) {
			if (select)
				SelectElement(element);
			else
				UnselectCheckbox(element);
		}

	}

	public void validateInvestigatePDFFile() {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean verifyCoverageForecastValuesTableWithPreviousResult(String actualPath, String reportTitle)
			throws IOException {
		Log.method("ComplianceReportsPage.verifyCoverageForecastValuesTableWithPreviousResult", actualPath,
				reportTitle);
		PDFTableUtility pdfTableUtility = new PDFTableUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = actualPath + "CR-" + reportId.substring(0, 6) + ".pdf";
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		List<String[]> coverageForecast = pdfTableUtility.extractPDFTable(actualReport, PDFTable.COVERAGEFORECAST);
		List<String[]> coverageForecastTo70 = pdfTableUtility.extractPDFTable(actualReport,
				PDFTable.COVERAGEFORECASTTO70);

		boolean result = pdfTableUtility.areTablesEqual(coverageForecast, preCoverageForecast)
				&& pdfTableUtility.areTablesEqual(coverageForecastTo70, preCoverageForecastTo70);
		preCoverageForecast = coverageForecast;
		preCoverageForecastTo70 = coverageForecastTo70;
		return result;
	}

	/**
	 * Method to verify the show Coverage Forecast Table in SSRS
	 *
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyCoverageForecastValuesTable(String actualPath, String reportTitle) throws IOException {
		Log.method("ComplianceReportsPage.verifyCoverageForecastValuesTable", actualPath, reportTitle);
		return verifyCoverageForecastValuesTable(actualPath, reportTitle, true);
	}

	public boolean verifyCoverageForecastValuesTable(String actualPath, String reportTitle, boolean withPrediction)
			throws IOException {
		Log.method("ComplianceReportsPage.verifyCoverageForecastValuesTable", actualPath, reportTitle, withPrediction);
		PDFTableUtility pdfTableUtility = new PDFTableUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = actualPath + "CR-" + reportId.substring(0, 6) + ".pdf";
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		List<String[]> coverageForecast = pdfTableUtility.extractPDFTable(actualReport, PDFTable.COVERAGEFORECAST);
		List<String[]> coverageForecastTo70 = pdfTableUtility.extractPDFTable(actualReport,
				PDFTable.COVERAGEFORECASTTO70);
		preCoverageForecast = coverageForecast;
		preCoverageForecastTo70 = coverageForecastTo70;
		if (!withPrediction && !coverageForecastTo70.isEmpty()) {
			return false;
		}
		return verifyCoverageForecastValuesTableWithDBData(reportId, coverageForecast, coverageForecastTo70,
				withPrediction);
	}

	private boolean verifyCoverageForecastValuesTableWithDBData(String reportId, List<String[]> coverageForecast,
			List<String[]> coverageForecastTo70, boolean withPrediction) {
		int startIndex = 0;
		StoredProcComplianceGetCoverageForecast coverageForecastObj = new StoredProcComplianceGetCoverageForecast();
		String[] row = null;
		if (!coverageForecast.isEmpty()) {
			row = coverageForecast.get(startIndex);
			String precentageWithLisa = row[0].replaceFirst(ComplianceReportSSRS_PercentServiceCoverageWithLISAs, "")
					.trim();
			String precentageWithoutLisa = row[1]
					.replaceFirst(ComplianceReportSSRS_PercentServiceCoverageWithoutLISAs, "").trim();
			coverageForecastObj.setPercentageWithLisa(precentageWithLisa);
			coverageForecastObj.setPercentageWithoutLisa(precentageWithoutLisa);
		}
		if (!coverageForecastTo70.isEmpty()) {
			startIndex = 1;
			row = coverageForecastTo70.get(startIndex++);
			String precentageAdditional0 = row[1].replaceFirst(ComplianceReportSSRS_ProbabilitytoObtain70Coverage, "")
					.trim();
			row = coverageForecastTo70.get(startIndex++);
			String precentageAdditional1 = row[1].replaceFirst(ComplianceReportSSRS_ProbabilitytoObtain70Coverage, "")
					.trim();
			row = coverageForecastTo70.get(startIndex);
			String precentageAdditional2 = row[1].replaceFirst(ComplianceReportSSRS_ProbabilitytoObtain70Coverage, "")
					.trim();

			coverageForecastObj.setCoverageProbability0(precentageAdditional0);
			coverageForecastObj.setCoverageProbability1(precentageAdditional1);
			coverageForecastObj.setCoverageProbability2(precentageAdditional2);
		}
		StoredProcComplianceGetCoverageForecast storedForecastObj = StoredProcComplianceGetCoverageForecast
				.getCoverage(reportId);

		if (!storedForecastObj.isCoverageValuesEquals(coverageForecastObj, withPrediction)) {
			Log.info("Coverage Values data verification failed");
			return false;
		}
		Log.info("Coverage Forecast Values data verification passed");
		if (!storedForecastObj.isCoverageValuesFormated(coverageForecastObj, withPrediction)) {
			return false;
		}
		return true;
	}

	/**
	 * Method to verify Investigation PDF
	 *
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyInvestigationResultTable(String actualPath, String reportTitle) throws IOException {
		Log.method("ComplianceReportsPage.verifyInvestigationResultTable", actualPath, reportTitle);
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = actualPath + reportId.substring(0, 6) + ".pdf";
		String reportName = reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(LisaInvestigationReportSSRS_Lisa);
		expectedReportString.add(LisaInvestigationReportSSRS_Amplitude);
		expectedReportString.add(Constant_Status);
		expectedReportString.add(LisaInvestigationReportSSRS_Investigator);
		expectedReportString.add(LisaInvestigationReportSSRS_InvestigationReport);

		Map<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.info("Investigation Result table static text verification failed");
				return false;
			}
		}
		BufferedReader bufferReader = null;
		try {
			String investigationResultTable = RegexUtility.getStringInBetween(actualReportString,
					"LISA# Amplitude Status Investigation Date/Time Investigator Duration",
					"Investigation Marker ResultsLISA");
			InputStream inputStream = new ByteArrayInputStream(investigationResultTable.getBytes());
			bufferReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			ArrayList<String> lineList = new ArrayList<String>();
			while ((line = bufferReader.readLine()) != null) {
				if (!line.isEmpty()) {
					lineList.add(line.replaceAll("\\s+", "").trim());
				}
			}
			ArrayList<StoredProcLisaInvestigationShowIndication> lisaInvestigationfromSP = StoredProcLisaInvestigationShowIndication
					.getLisaInvestigation(reportId);
			Iterator<StoredProcLisaInvestigationShowIndication> lisaInvestigationIterator = lisaInvestigationfromSP
					.iterator();
			ArrayList<String> storedProcList = new ArrayList<String>();
			while (lisaInvestigationIterator.hasNext()) {
				StoredProcLisaInvestigationShowIndication entry = lisaInvestigationIterator.next();
				storedProcList.add(entry.toString().replaceAll("\\s+", "").trim());
			}
			if (!storedProcList.equals(lineList)) {
				Log.info("Investigation Result table data verification failed");
				return false;
			}
		} finally {
			bufferReader.close();
		}
		Log.info("Investigation Result table verification passed");
		return true;
	}

	public boolean verifyLISAInvestigationTable(String actualPath, String reportTitle)
			throws FileNotFoundException, IOException {
		Log.method("ComplianceReportsPage.verifyLISAInvestigationTable", actualPath, reportTitle);
		return verifyInvestigationTable(actualPath, reportTitle, Report.getReport(reportTitle).getId(), PDFTable.LISAINVESTIGATIONPDFTABLE);
	}

	public boolean verifyGAPInvestigationTable(String actualPath, String reportTitle)
			throws FileNotFoundException, IOException {
		Log.method("ComplianceReportsPage.verifyGAPInvestigationTable", actualPath, reportTitle);
		return verifyInvestigationTable(actualPath, reportTitle, Report.getReport(reportTitle).getId(), PDFTable.GAPINVESTIGATIONPDFTABLE);
	}

	public boolean verifyInvestigationTable(String actualPath, String reportTitle, String reportId, PDFTable pdfTable)
			throws FileNotFoundException, IOException {
		Log.method("ComplianceReportsPage.verifyInvestigationTable", actualPath, reportTitle, reportId, pdfTable);
		boolean validTable = true;
		String tableType = "LISA#";
		if(pdfTable.equals(PDFTable.GAPINVESTIGATIONPDFTABLE)){
			tableType = "Gap #";
		}
		String[] investigationTableHeader = {tableType, "Status", "Investigation Date/Time", "Investigator", "Duration"};
		String pdfFilename = this.getInvestigationPDFFileName(reportTitle, true /* includeExtension */);
		String pdfFilePath = Paths.get(TestContext.INSTANCE.getTestSetup().getDownloadPath(), pdfFilename).toString();
		PDFTableUtility pdfTableUtility = new PDFTableUtility();
		List<String[]> pdfTableList = pdfTableUtility.extractPDFTable(pdfFilePath, pdfTable);
		Log.info("Checking if Investigation table has the header expected");
		String[] actualTableHeader = pdfTableList.get(0);

		if(actualTableHeader.length != investigationTableHeader.length){
			Log.error(String.format("Actual table header is: %s, Expected table header is: %s", actualTableHeader.toString(), investigationTableHeader.toString()));
			validTable = false;
		}else{
			for(int i=0; i<actualTableHeader.length; i++){
				String actualHeader = actualTableHeader[i];
				String expectedHeader = investigationTableHeader[i];
				if(!expectedHeader.equalsIgnoreCase(actualHeader)){
					Log.error(String.format("Actual table header is: %s, Expected table header is: %s", actualHeader, expectedHeader));
					validTable = false;
				}
			}
		}

		return validTable;
	}

	public void waitForInvestigationPDFFileDownload(String reportName) {
		waitForFileDownload(reportName + "-Investigation.pdf", testSetup.getDownloadPath());
	}

	public void waitForInvestigationCSVFileDownload(String reportName) {
		waitForFileDownload(reportName + "-ReportInvestigations.pdf", testSetup.getDownloadPath());
	}

	public WebElement getCheckBoxVehicleExhaust() {
		return checkBoxVehicleExhaust;
	}

	public WebElement getCheckBoxEtheneBiogeniceMethane() {
		return checkBoxEtheneBiogeniceMethane;
	}

	public String getSoftwareVersionFromInvestigationPDF(String reportTitle, String downloadPath) {
		String pdfFilename = this.getInvestigationPDFFileName(reportTitle, true /* includeExtension */);
		return getSoftwareVersionFromPDF(() -> {return Paths.get(downloadPath, pdfFilename).toString();});
	}

	@Override
	public void fillReportSpecific(BaseReportEntity reports) {
		ComplianceReportEntity reportsCompliance = (ComplianceReportEntity) reports;

		// 1. Report general
		if (reportsCompliance.getEthaneFilter() != null) {
			selectEthaneFilter(reportsCompliance.getEthaneFilter());
		}

		if (reportsCompliance.getReportModeFilter() != null) {
			selectReportMode(reportsCompliance.getReportModeFilter());
		}

		if (reportsCompliance.getExclusionRadius() != null) {
			inputExclusionRadius(reportsCompliance.getExclusionRadius());
		}

		// 2. Area Selector
		if (isCustomBoundarySpecified(reportsCompliance)) {
			selectCustomBoundaryRadioButton();
			if (useCustomBoundaryLatLongSelector(reportsCompliance)) {
				fillCustomBoundaryUsingLatLongSelector(reportsCompliance);
			} else {
				fillCustomBoundaryTextFields(reportsCompliance.getNELat(), reportsCompliance.getNELong(),
						reportsCompliance.getSWLat(), reportsCompliance.getSWLong());
			}
		} else {
			fillCustomerBoundary(reportsCompliance);
		}

		inputImageMapHeight(reportsCompliance.getImageMapHeight());
		inputImageMapWidth(reportsCompliance.getImageMapWidth());

		// 3. Views
		addViews(reportsCompliance.getCustomer(), reportsCompliance.getViewList());

		// 4. Optional Tabular PDF Content
		List<Map<String, String>> tablesList = reportsCompliance.getTablesList();

		if (tablesList.get(0).get(KEYINDTB).equalsIgnoreCase("1")) {
			selectIndicationsTableCheckBox();
		}
		if (tablesList.get(0).get(KEYISOANA).equalsIgnoreCase("1")) {
			selectIsotopicAnalysisCheckBox();
		}
		if (tablesList.get(0).get(KEYGAPTB) != null) {
			if (tablesList.get(0).get(KEYGAPTB).equalsIgnoreCase("1")) {
				selectGapTableCheckBox();
			}
		}
		if (tablesList.get(0).get(KEYPCA).equalsIgnoreCase("1")) {
			selectPercentCoverageAssetCheckBox();
		}
		if (tablesList.get(0).get(KEYPCRA).equalsIgnoreCase("1")) {
			selectPercentCoverageReportArea();
		}
		if (tablesList.get(0).get(KEYPCF) != null) {
			if (tablesList.get(0).get(KEYPCF).equalsIgnoreCase("1")) {
				selectPercentCoverageForecastCheckBox();
			}
		}

		// 5. Optional View layers
		List<Map<String, String>> viewLayersList = reportsCompliance.getViewLayersList();
		if (viewLayersList != null && viewLayersList.size() > 0) {
			handleOptionalDynamicViewLayersSection(viewLayersList);
		}
	}

	public boolean areInvestigationTableColumnsSorted() {
		Log.method("areInvestigationTableColumnsSorted");
		if (!isAmplitudeColumnSorted()) {
			return false;
		}
		if (!isStatusColumnSorted()) {
			return false;
		}
		if (!isInvestigatorColumnSorted()) {
			return false;
		}
		return true;
	}

	public boolean isReportColumnSorted(String columnName, String type) {
		Log.method("isReportColumnSorted");
		HashMap<String, TableColumnType> columnMap = new HashMap<String, TableColumnType>();
		columnMap.put(columnName, TableColumnType.getTableColumnType(type));
		return checkTableSort("datatable_wrapper", columnMap, pagination, getPaginationOption(),
				SurveyorConstants.NUM_RECORDS_TOBEVERIFIED);
	}

	public boolean isAmplitudeColumnSorted() {
		Log.method("isAmplitudeColumnSorted");
		HashMap<String, TableColumnType> columnMap = new HashMap<String, TableColumnType>();
		columnMap.put(ComplianceReportSSRS_Amplitude.replaceFirst("\\(", " ("), TableColumnType.Number);
		return checkTableSort("datatableBoxes_wrapper", columnMap, pagination, getPaginationOption());
	}

	public boolean isStatusColumnSorted() {
		Log.method("isStatusColumnSorted");
		HashMap<String, TableColumnType> columnMap = new HashMap<String, TableColumnType>();
		columnMap.put(Constant_Status, TableColumnType.String);
		return checkTableSort("datatableBoxes_wrapper", columnMap, pagination, getPaginationOption());
	}

	public boolean isInvestigatorColumnSorted() {
		Log.method("isInvestigatorColumnSorted");
		HashMap<String, TableColumnType> columnMap = new HashMap<String, TableColumnType>();
		columnMap.put(Constant_Investigator, TableColumnType.String);
		return checkTableSort("datatableBoxes_wrapper", columnMap, pagination, getPaginationOption());
	}

	public WebElement getBtnAssignInvestigators() {
		return btnAssignInvestigators;
	}

	public String getLISAOpacity() {
		return this.inputLISAOpacity.getText();
	}

	public void waitForConfirmReportModeChangePopupToClose() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				(new WebDriverWait(driver, timeout + 15))
						.until(ExpectedConditions.presenceOfElementLocated(By.id("surveyModal")));
				WebElement confirmDeletePopupSection = d.findElement(By.id("surveyModal"));
				return confirmDeletePopupSection.getAttribute("style").contains("display:none")
						|| confirmDeletePopupSection.getAttribute("style").contains("display: none");
			}
		});
	}
}
