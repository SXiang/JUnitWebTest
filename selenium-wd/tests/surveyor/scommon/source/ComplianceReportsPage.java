/**
 *
 */
package surveyor.scommon.source;

import static common.source.BaseHelper.matchSinglePattern;
import common.source.DateUtility;
import common.source.NumberUtility;

import static surveyor.scommon.source.SurveyorConstants.CUSTOMER_PICARRO;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETBOXNUMBER;
import static surveyor.scommon.source.SurveyorConstants.KEYGAPTB;
import static surveyor.scommon.source.SurveyorConstants.KEYINDTB;
import static surveyor.scommon.source.SurveyorConstants.KEYISOANA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCF;
import static surveyor.scommon.source.SurveyorConstants.KEYPCRA;
import static surveyor.scommon.source.SurveyorConstants.KEYVIEWNAME;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETCASTIRON;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETCOPPER;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETOTHERPLASTIC;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETPEPLASTIC;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETPROTECTEDSTEEL;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETUNPROTECTEDSTEEL;

import surveyor.scommon.entities.BaseReportEntity;
import surveyor.scommon.entities.BaseReportEntity.ReportModeFilter;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;
import surveyor.scommon.entities.ComplianceReportEntity;
import surveyor.scommon.entities.ReportCommonEntity;
import surveyor.scommon.entities.ReportCommonEntity.EthaneFilter;
import surveyor.scommon.entities.ReportCommonEntity.LISAIndicationTableColumns;
import surveyor.scommon.source.DataTablePage.TableColumnType;
import surveyor.scommon.source.LatLongSelectionControl.ControlMode;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.ArrayUtility;
import common.source.BaseHelper;
import common.source.CSVUtility;
import common.source.Log;
import common.source.LogHelper;
import common.source.PDFTableUtility;
import common.source.PDFTableUtility.PDFTable;
import common.source.TestSetup;
import common.source.TextUtility;
import common.source.WebElementExtender;
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
import surveyor.dataaccess.source.StoredProcComplianceGetIndications;
import surveyor.dataaccess.source.StoredProcComplianceGetIsotopics;
import surveyor.dataaccess.source.StoredProcLisaInvestigationShowIndication;
import surveyor.parsers.source.SSRSIsotopicAnalysisTableParser;
import common.source.PDFUtility;
import common.source.RegexUtility;
import common.source.SortHelper;
import common.source.TestContext;

/**
 * @author zlu
 *
 */
public class ComplianceReportsPage extends ReportsCommonPage {

	public static final String STRURLPath = "/Reports/ComplianceReports";
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.ComplianceReports_PageTitle);
	public static final String STRNewPageContentText = Resources.getResource(ResourceKeys.ComplianceReports_AddNew);
	public static final String STRCopyPageTitle = Resources.getResource(ResourceKeys.ComplianceReport_PageTitle);

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

	@FindBy(name = "report-survey-mode-type")
	private List<WebElement> reportSurveyModeTypeRadiobuttonList;

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

	@FindBy(id = "report-assethighlighting")
	protected WebElement highlightAlgorithmDropdown;

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

		super(driver, strBaseURL, testSetup, STRURLPath, () -> getCommonResourceProvider());

		Log.info(String.format("\nThe Compliance Reports Page URL is: %s\n", this.strPageURL));
	}

	public void invokeInvestigationPDFFileDownload(String rptTitle) throws Exception {
		Log.method("invokeInvestigationPDFFileDownload", rptTitle);
		invokeFileDownload(rptTitle, ReportFileType.InvestigationPDF);
	}

	public void invokeInvestigationDataFileDownload(String rptTitle) throws Exception {
		Log.method("invokeInvestigationDataFileDownload", rptTitle);
		invokeFileDownload(rptTitle, ReportFileType.InvestigationCSV);
	}

	private void selectHighlightingAlgorithmDropdown(String value) {
		Log.method("selectAlgorithmDropdown", value);
		WebElementExtender.selectDropdownValue(highlightAlgorithmDropdown, value);
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

	/**
	 * Method to verify Investigation PDF
	 *
	 * @param rptTitle
	 * @param strCreatedBy
	 * @param buttonType
	 * @param clickButton
	 * @param confirmAction
	 *            - Confirms to complete action. For eg. if Delete button is
	 *            clicked: Click Confirm button if this is TRUE or click Cancel
	 *            when this flag is FALSE.
	 * @return
	 * @throws Exception
	 */

	public boolean checkButtonOnReportsPageAndClick(String rptTitle, String strCreatedBy,
			ReportsButtonType buttonType, boolean clickButton, boolean confirmAction) throws Exception {
		Log.method("ComplianceReportsPage.checkComplianceReportButtonPresenceAndClick", rptTitle, strCreatedBy,
				buttonType.name(), clickButton, confirmAction);

		setPagination(PAGINATIONSETTING);
		this.waitForPageLoad();

		String reportTitleXPath;
		String createdByXPath;
		String buttonXPath;

		String rptTitleCellText;
		String createdByCellText;
		WebElement buttonImg;
		boolean removeDBCache = false;
		switch (buttonType) {
		case Delete:
			buttonXPath = "td[5]/a[1]";
			break;
		case Copy:
			buttonXPath = "td[5]/a[@title='Copy']";
			removeDBCache = true;
			break;
		case ReportViewer:
			buttonXPath = "td[5]/a[3]";
			break;
		case Investigate:
			buttonXPath = "td[5]/a[4]";
			break;
		case Resubmit:
			buttonXPath = "td[5]/a[@title='Resubmit']";
			removeDBCache = true;
			break;
		case InProgressCopy: // NOTE: When report is in-progress, Copy is the
								// 1st button.
			buttonXPath = "td[5]/a[@title='Copy']";
			break;
		case Cancel: // NOTE: When cancel button is visible it is the 2nd
						// button.
			buttonXPath = "td[5]/a[@title='Cancel Report']";
			break;
		case ReportErrorLabel: // 'Error Processing' label on report
			// cancelled or report error.
			buttonXPath = "td[5]/span";
			break;
		default:
			throw new Exception("ButtonType NOT supported.");
		}

		List<WebElement> rows = getTable().findElements(By.xpath("tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		Log.info(String.format("Looking for rptTitle=[%s], strCreatedBy=[%s]", rptTitle, strCreatedBy));

		final int MAX_PAGES_TO_MOVE_AHEAD = 3;
		int pageCounter = 0;
		for (int rowNum = 1, numRetry = 0; rowNum <= loopCount && pageCounter < MAX_PAGES_TO_MOVE_AHEAD; rowNum++) {
			reportTitleXPath = "tr[" + rowNum + "]/td[1]";
			createdByXPath = "tr[" + rowNum + "]/td[3]";

			try {
				rptTitleCellText = getTable().findElement(By.xpath(reportTitleXPath)).getText().trim();
				createdByCellText = getTable().findElement(By.xpath(createdByXPath)).getText().trim();
			} catch (Exception e) {
				if (numRetry++ < 10) {
					Log.error("Failed to get text of report title/createdBy cells on row '" + rowNum
							+ "' and will try again: " + e);
					rowNum--;
					continue;
				} else {
					Log.error("Failed to get text of report title/createdBy cells on row '" + rowNum + "': " + e);
					return false;
				}
			}
			Log.info(String.format("Found rptTitleCell.getText()=[%s], createdByCell.getText()=[%s]", rptTitleCellText,
					createdByCellText));
			if (rptTitleCellText.equalsIgnoreCase(rptTitle) && createdByCellText.equalsIgnoreCase(strCreatedBy)) {
				try {
					buttonXPath = "tr[" + rowNum + "]/" + buttonXPath;
					buttonImg = getTable().findElement(By.xpath(buttonXPath));
					if (buttonImg.isDisplayed()) {
						if (clickButton) {
							if (buttonType != ReportsButtonType.ReportErrorLabel) {
								Log.clickElementInfo(buttonType.toString());
								buttonImg.click();
								// If resubmit then wait for modal and confirm
								// resubmit.
								if (buttonType == ReportsButtonType.Resubmit) {
									this.waitForResubmitPopupToShow();
									Log.clickElementInfo("Confirm Resubmit");
									this.btnProcessResubmit.click();
									this.waitForPageLoad();
									this.waitForAJAXCallsToComplete();
								}
								if (buttonType == ReportsButtonType.Delete) {
									this.waitForConfirmDeletePopupToShow();
									if (confirmAction) {
										Log.clickElementInfo("Confirm Delete");
										this.clickOnConfirmInDeleteReportPopup();
										this.waitForConfirmDeletePopupToClose();
									}
								}

								if (removeDBCache) {
									DBCache.INSTANCE.remove(Report.CACHE_KEY + rptTitle);
								}
							}
						}
						return true;
					}
					Log.error("Button image is not visible '" + buttonXPath + "'");
					return false;
				} catch (org.openqa.selenium.NoSuchElementException e) {
					Log.error("Button image not found '" + buttonXPath + "': " + e);
					return false;
				}
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				toNextPage();
				pageCounter++;

				List<WebElement> newRows = getTable().findElements(By.xpath("tr"));
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

	public void inputImageMapWidth(String imageMapWidth) {
		if (imageMapWidth == null || imageMapWidth.equals("")) {
			return;
		}
		Log.info("Set image width to '" + imageMapWidth + "'");
		sendKeysToElement(inputImgMapWidth, imageMapWidth);
	}

	public void inputImageMapHeight(String imageMapHeight) {
		if (imageMapHeight == null || imageMapHeight.equals("")) {
			return;
		}
		Log.info("Set image height to '" + imageMapHeight + "'");
		sendKeysToElement(inputImgMapHeight, imageMapHeight);
	}

	public void inputExclusionRadius(String exclusionRadius) {
		Log.info("Set exclusion radius to '" + exclusionRadius + "'");
		this.inputExclusionRadius.clear();
		this.inputExclusionRadius.sendKeys(exclusionRadius);
	}

	public void inputFOVOpacity(String fovOpacity) {
		Log.info("Set FOV Opacity to '" + fovOpacity + "'");
		this.inputFOVOpacity.clear();
		this.inputFOVOpacity.sendKeys(fovOpacity);
	}

	public void inputLISAOpacity(String lisaOpacity) {
		Log.info("Set LISA Opacity to '" + lisaOpacity + "'");
		this.inputLISAOpacity.clear();
		this.inputLISAOpacity.sendKeys(lisaOpacity);
	}

	public void inputSurveyUsername(String username) {
		Log.info("Set survey username to '" + username + "'");
		this.userName.clear();
		this.userName.sendKeys(username);
	}

	public void fillCustomBoundaryTextFields(String neLat, String neLong, String swLat, String swLong) {
		if (neLat != null) {
			Log.info("Set NELat to '" + neLat + "'");
			this.inputNELat.clear();
			this.inputNELat.sendKeys(neLat);
		}
		if (neLong != null) {
			Log.info("Set NELong to '" + neLong + "'");
			this.inputNELong.clear();
			this.inputNELong.sendKeys(neLong);
		}
		if (swLat != null) {
			Log.info("Set SWLat to '" + swLat + "'");
			this.inputSWLat.clear();
			this.inputSWLat.sendKeys(swLat);
		}
		if (swLong != null) {
			Log.info("Set SWLong to '" + swLong + "'");
			this.inputSWLong.clear();
			this.inputSWLong.sendKeys(swLong);
		}
	}

	/**
	 * Get the selected Report Mode.
	 */
	public ReportModeFilter getReportModeFilter() {
		for (WebElement radElement : reportSurveyModeTypeRadiobuttonList) {
			if (radElement.isSelected()) {
				Map<String, ReportModeFilter> reportSurveyModeFilterGuids = BaseReportEntity.ReportSurveyModeFilterGuids;
				return reportSurveyModeFilterGuids.entrySet().stream()
					.filter(e -> e.getKey().equalsIgnoreCase(radElement.getAttribute("value")))
					.map(e -> e.getValue())
					.findFirst().orElse(ReportModeFilter.All);
			}
		}
		return ReportModeFilter.All;
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

	public String getFullReportName(String rptTitle) {
		return "CR" + "-" + getReportName(rptTitle);
	}

	protected void handleOptionalDynamicViewLayersSection(List<Map<String, String>> viewLayersList) {
		if (viewLayersList != null && !viewLayersList.isEmpty()) {
			selectViewLayerAssets(viewLayersList.get(0));
			selectViewLayerBoundaries(viewLayersList.get(0));
		}
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

	public boolean resubmitReport(String rptTitle, String strCreatedBy) {
		Log.method("ComplianceReportsPage.resubmitReport", rptTitle, strCreatedBy);
		setPagination(PAGINATIONSETTING);

		this.waitForPageLoad();

		String reportTitleXPath;
		String createdByXPath;
		String resubmitImgXPath;

		WebElement rptTitleCell;
		WebElement createdByCell;
		WebElement resubmitImg;

		List<WebElement> rows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		final int MAX_PAGES_TO_MOVE_AHEAD = 3;
		int pageCounter = 0;
		for (int rowNum = 1; rowNum <= loopCount && pageCounter < MAX_PAGES_TO_MOVE_AHEAD; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[3]";

			rptTitleCell = getTable().findElement(By.xpath(reportTitleXPath));
			createdByCell = getTable().findElement(By.xpath(createdByXPath));

			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle)
					&& createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy)) {
				resubmitImgXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]/a[2]/img";
				resubmitImg = getTable().findElement(By.xpath(resubmitImgXPath));
				Log.clickElementInfo("Resubmit", ElementType.ICON);
				resubmitImg.click();

				this.waitForCopyReportPagetoLoad();
				this.waitForInputTitleToEnable();

				this.waitForOkButtonToEnable();
				Log.clickElementInfo("OK");
				clickOnOKButton();

				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				Log.clickElementInfo("Next");
				toNextPage();
				pageCounter++;
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

	public boolean validatePdfFiles(String reportTitle, String downloadPath) {
		Log.method("ComplianceReportsPage.validatePdfFiles", reportTitle, downloadPath);
		String reportName;
		String reportZipName;
		try {
			reportName = getReportPDFFileName(reportTitle, false /* includeExtension */);
			reportZipName = getReportPDFZipFileName(reportTitle, false /* includeExtension */);
			setReportName(reportName);
			BaseHelper.deCompressZipFile(reportZipName, downloadPath);
		} catch (Exception e) {
			Log.error(e.toString());
			return false;
		}

		String nameBase = reportTitle.trim().replaceAll(" ", "_");
		String pdfFile1;
		String pdfFile2;
		String pdfFile3;

		pdfFile1 = Paths.get(downloadPath, reportName + ".pdf").toString();
		pdfFile3 = Paths.get(downloadPath, reportZipName + File.separator + nameBase.replaceAll("_", "") + ".pdf")
				.toString();

		if (BaseHelper.validatePdfFile(pdfFile1) && BaseHelper.validatePdfFile(pdfFile3)) {
			try {
				if (!BaseHelper.compareTwoFilesByContent(pdfFile1, pdfFile3))
					return false;
			} catch (IOException e) {
				Log.error(e.toString());
				return false;
			}
		} else
			return false;

		pdfFile2 = Paths
				.get(downloadPath, reportName + File.separator + nameBase.replaceAll("_", "") + "_First View.pdf")
				.toString();

		if (!BaseHelper.validatePdfFile(pdfFile2))
			return false;

		return true;
	}

	public boolean validatePdfFiles(ComplianceReportEntity reportsCompliance, String downloadPath) {
		Log.method("ComplianceReportsBasePage.validatePdfFiles", reportsCompliance, downloadPath);
		String reportName;
		String reportZipName;
		try {
			reportName = getReportPDFFileName(reportsCompliance.getRptTitle(), false /* includeExtension */);
			reportZipName = getReportPDFZipFileName(reportsCompliance.getRptTitle(), false /* includeExtension */);
			setReportName(reportName);
			BaseHelper.deCompressZipFile(reportZipName, downloadPath);
		} catch (Exception e) {
			Log.error(e.toString());
			return false;
		}

		String nameBase = RegexUtility.replaceSpecialChars(reportsCompliance.getRptTitle().trim());
		String viewName;
		String pdfFile1;
		String pdfFile2;
		String pdfFile3;

		List<Map<String, String>> viewList = reportsCompliance.getViewList();

		pdfFile1 = Paths.get(downloadPath, reportName + ".pdf").toString();
		pdfFile3 = Paths.get(downloadPath, reportZipName + File.separator + nameBase + ".pdf").toString();
		if (BaseHelper.validatePdfFile(pdfFile1) && BaseHelper.validatePdfFile(pdfFile3)) {
			try {
				if (!BaseHelper.compareTwoFilesByContent(pdfFile1, pdfFile3))
					return false;
			} catch (IOException e) {
				Log.error(e.toString());
				return false;
			}
		} else
			return false;

		for (int i = 0; i < viewList.size(); i++) {
			viewName = viewList.get(i).get(KEYVIEWNAME);
			pdfFile2 = Paths.get(downloadPath, reportZipName + File.separator + nameBase + "_" + viewName + ".pdf")
					.toString();
			if (!BaseHelper.validatePdfFile(pdfFile2)) {
				Log.info("PDF Validation failed for: " + pdfFile2);
				return false;
			}
		}

		return true;
	}

	public boolean checkBlankReportErrorTextPresentAndRequiredFieldsHighlighted() {
		openNewReportPage();
		this.clickOnOKButton();
		boolean done = false;
		if (done = isElementPresent(strErrorText)) {
			done &= isHighlightedInRed(inputTitle);
			done &= isHighlightedInRed(inputNELat);
			done &= isHighlightedInRed(inputNELong);
			done &= isHighlightedInRed(inputSWLat);
			done &= isHighlightedInRed(inputNELong);
			for (WebElement view : dataTableViews) {
				done &= isHighlightedInRed(view);
			}
		}
		return done;
	}

	public boolean isHighlightedInRed(WebElement element) {
		String background = "background: rgb(255, 206, 206)";
		String border = "border: 1px solid red;";
		boolean highlighted = true;
		String value = element.getAttribute("style");
		if (value == null) {
			return false;
		} else if (value.contains("border: ")) {
			highlighted &= value.contains(border);
		}

		highlighted &= value.contains(background);
		return highlighted;
	}

	@Override
	public void modifyReportViews() {
		this.inputViewInd.click();
		this.inputViewIso.click();
		this.inputViewAnno.click();
	}

	@Override
	public void reportChangeMode(String rptTitleNew, boolean changeMode, ReportModeFilter strReportMode) {
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

	public boolean deleteAllDrivingSurveys() {
		for (WebElement btnDelete : btnDeleteDrivingSurveys) {
			jsClick(btnDelete);
			this.waitForPageToLoad();
		}
		return btnDeleteDrivingSurveys.isEmpty();
	}

	public boolean deleteDrivingSurveyByTag(String tag) {
		String xpathToDeleteSurveyBtnByTag = String.format(deleteSurveyBtnByTagParameter, tag);
		WebElement btnDelete = driver.findElement(By.xpath(xpathToDeleteSurveyBtnByTag));
		jsClick(btnDelete);
		this.waitForPageToLoad();
		return true;
	}

	public boolean deleteSurveyAndIncludeAgain(String surveyTag) {
		this.btnDeleteDrivingSurvey.click();
		this.waitForCopyReportPagetoLoad();

		try {
			driver.findElement(By.xpath(strFirstSurveyTag));
			return false;
		} catch (NoSuchElementException e) {
			if (surveyTag != "") {
				inputSurveyTag(surveyTag);
				this.btnSurveySearch.click();
				this.waitForSurveyTabletoLoad();
				this.checkboxSurFirst.click();
				this.btnAddSurveys.click();
			}

			if (isElementPresent(strFirstSurveyTag))
				return true;

			return false;
		}
	}

	public String provideLatLongAtCustomBoundarySelectorWindow(List<String> listBoundary) {
		String actualMsg = "";
		openNewReportPage();

		inputImageMapHeight(listBoundary.get(0));
		inputImageMapWidth(listBoundary.get(1));

		this.btnLatLongSelector.click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		String parentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);
				this.inputCustomNELat.clear();
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.inputCustomNELat.sendKeys(listBoundary.get(2));
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.inputCustomNELong.clear();
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.inputCustomNELong.sendKeys(listBoundary.get(3));
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.inputCustomSWLat.clear();
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.inputCustomSWLat.sendKeys(listBoundary.get(4));
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.inputCustomSWLong.clear();
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.inputCustomSWLong.sendKeys(listBoundary.get(5));
				this.btnCustomOK.click();
				actualMsg = this.btnCustomOK.getText();
			}
		}
		// driver.close();
		// driver.switchTo().window(parentWindow);
		return actualMsg;
	}

	public void openCustomerBoundarySelector() {
		this.selectCustomerBoundaryRadioButton();
		this.waitForCustomerBoundarySectionToShow();
		this.clickBoundarySelectorBtn();
	}

	public void openCustomBoundarySelector() {
		this.selectCustomBoundaryRadioButton();
		this.waitForCustomBoundarySectionToShow();
		this.clickLatLongMapSelectorBtn();
	}

	public int getNumberofRecords() {
		List<WebElement> records = this.numberofRecords;
		return records.size();
	}

	public String getAreaErrorText() {
		return this.areaErrorText.getText();

	}

	public void selectPercentCoverageReportArea() {
		SelectElement(checkBoxPCRA);
	}

	public void selectPercentCoverageAssetCheckBox() {
		SelectElement(checkBoxPCA);
	}

	public void selectPercentCoverageForecastCheckBox() {
		SelectElement(checkBoxPCF);
	}

	public void selectGapTableCheckBox() {
		SelectElement(checkBoxGapTb);
	}

	public void selectIsotopicAnalysisCheckBox() {
		SelectElement(checkBoxIsoAna);
	}

	public void selectIndicationsTableCheckBox() {
		SelectElement(checkBoxIndTb);
	}

	public void selectCustomBoundaryRadioButton() {
		this.customBoundaryRadioButton.click();
	}

	public void selectCustomerBoundaryRadioButton() {
		jsClick(this.customerBoundaryRadioButton);
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

	public void selectViewLayerAssets(Map<String, String> viewLayerMap) {
		for (Entry<String, String> entry : viewLayerMap.entrySet()) {
			String key = entry.getKey(); // Key is Asset/Boundary Id
			String value = entry.getValue(); // Value is Asset/Boundary{Prefix}
												// followed by name of
												// Asset/Boundary
			if (value.startsWith(ComplianceReportEntity.ASSET_PREFIX)) {
				// Asset key.
				WebElement assetElement = getViewLayerAssetCheckbox(key);
				if (assetElement != null) {
					SelectElement(assetElement);
				}
			} else if (value.startsWith(ComplianceReportEntity.ASSET_ALL_PREFIX)) {
				SelectElement(checkboxViewLayerAllAssets);
			}
		}
	}

	public void selectViewLayerBoundaries(Map<String, String> viewLayerMap) {
		for (Entry<String, String> entry : viewLayerMap.entrySet()) {
			String value = entry.getValue(); // Value is Asset/Boundary{Prefix}
												// followed by name of
												// Asset/Boundary
			if (value.startsWith(ComplianceReportEntity.BOUNDARY_PREFIX)) {
				// Boundary key.
				value = value.replace(ComplianceReportEntity.BOUNDARY_PREFIX, "");
				WebElement boundaryElement = getViewLayerBoundaryCheckbox(value);
				if (boundaryElement != null) {
					SelectElement(boundaryElement);
				}
			} else if (value.startsWith(ComplianceReportEntity.BOUNDARY_ALL_PREFIX)) {
				SelectElement(checkboxViewLayerAllBoundaries);
			}
		}
	}

	public WebElement getViewLayerAssetCheckbox(String key) {
		return WebElementExtender.findElementIfExists(driver, String.format("report-asset-layers-%s", key));
	}

	public WebElement getViewLayerBoundaryCheckbox(String value) {
		return WebElementExtender.findElementIfExists(driver, String.format("report-boundry-layers-%s", value));
	}

	public void selectAnyCustomerBoundary(CustomerBoundaryType type) {
		// TODO open the Boundary selector and click on any customer boundary.
		// Could provide a search by boundary name.
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateInvestigatePDFFile() {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean verifyComplianceReportButton(String rptTitle, String strCreatedBy,
			ReportsButtonType buttonType) throws Exception {
		Log.method("ComplianceReportsPage.verifyComplianceReportButton", rptTitle, strCreatedBy, buttonType.name());
		return checkButtonOnReportsPageAndClick(rptTitle, strCreatedBy, buttonType, false,
				false /* confirmAction */);
	}

	/**
	 * Verifies that the customer boundary name auto-complete list contains the
	 * specified entries.
	 */
	public boolean verifyCustomerBoundaryLatLongSelectorAutoCompleteListContains(String boundaryFilterType,
			String customerBoundaryName, List<String> autocompleteListEntries) {
		Log.method("ComplianceReportsPage.verifyCustomerBoundaryLatLongSelectorAutoCompleteListContains",
				boundaryFilterType, customerBoundaryName, LogHelper.listToString(autocompleteListEntries));
		openCustomerBoundarySelector();
		latLongSelectionControl.waitForModalDialogOpen();
		latLongSelectionControl.switchMode(ControlMode.MapInteraction);
		latLongSelectionControl.selectCustomerBoundaryType(boundaryFilterType);

		// Type customer boundary name and verify the autocomplete list. If not
		// all entries shown, return false.
		if (!latLongSelectionControl.verifyCustomerBoundaryAutoCompleteListContains(customerBoundaryName,
				autocompleteListEntries)) {
			return false;
		}

		// Click Ok to close the lat long selector.
		latLongSelectionControl.switchMode(ControlMode.Default).clickOkButton();

		return true;
	}

	/**
	 * Method to verify the static text
	 *
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyComplianceReportStaticText(ComplianceReportEntity reportsCompliance) throws IOException {
		Log.method("ComplianceReportsPage.verifyComplianceReportStaticText", reportsCompliance);
		return verifyComplianceReportStaticText(reportsCompliance, testSetup.getDownloadPath());
	}

	/**
	 * Method to verify the static text
	 *
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyComplianceReportStaticText(ComplianceReportEntity reportsCompliance, String actualPath)
			throws IOException {
		Log.method("ComplianceReportsBasePage.verifyComplianceReportStaticText", reportsCompliance, actualPath);
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportsCompliance.getRptTitle());
		String reportId = reportObj.getId();
		String actualReport = actualPath + "CR-" + reportId.substring(0, 6) + ".pdf";
		setReportName("CR-" + reportId);
		setReportName(getReportName());
		String actualReportString = pdfUtility.extractPDFText(actualReport, 0, 1);
		actualReportString = RegexUtility.removeSpecialChars(actualReportString);
		Log.info(String.format("PDF Text Content : %s", actualReportString));
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(STRReportTitle);
		expectedReportString.add(RegexUtility.removeSpecialChars(ComplianceReportSSRS_LISAInvestigationComplete));
		expectedReportString.add(ComplianceReportSSRS_GAPInvestigationComplete);
		expectedReportString.add(ComplianceReportSSRS_CGIInvestigationComplete);
		expectedReportString.add(ComplianceReportSSRS_MapHeightWidth);
		if (isCustomBoundarySpecified(reportsCompliance)) {
			if (!BaseHelper.isNullOrEmpty(reportsCompliance.getNELat())
					&& !BaseHelper.isNullOrEmpty(reportsCompliance.getNELong())) {
				expectedReportString.add(ComplianceReportSSRS_NELatNELong);
			}
			if (!BaseHelper.isNullOrEmpty(reportsCompliance.getSWLat())
					&& !BaseHelper.isNullOrEmpty(reportsCompliance.getSWLong())) {
				expectedReportString.add(ComplianceReportSSRS_SWLatSWLong);
			}
		} else {
			if (!BaseHelper.isNullOrEmpty(reportsCompliance.getCustomerBoundaryName())) {
				expectedReportString.add(ComplianceReportSSRS_Boundary);
			}
		}

		Log.info(String.format("Expected Strings in PDF Text Content : %s",
				LogHelper.strListToString(expectedReportString)));

		Map<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value)
				return false;
		}
		return true;

	}

	/**
	 * Method to verify the static text
	 *
	 * @param reportTitle
	 * @param expectedReportString
	 * @return
	 * @throws IOException
	 */
	public boolean verifyReportContainsText(String reportTitle, List<String> expectedReportString)
			throws IOException {
		Log.method("ComplianceReportsPage.verifyComplianceReportContainsText", reportTitle,
				LogHelper.listToString(expectedReportString));
		String actualPath = testSetup.getDownloadPath();
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = actualPath + "CR-" + reportId.substring(0, 6) + ".pdf";
		setReportName("CR-" + reportId);
		setReportName(getReportName());
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		Map<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value)
				return false;
		}
		return true;

	}

	/**
	 * Method to verify the Show Coverage Table in SSRS
	 *
	 * @param actualPath
	 * @param reportTitle
	 * @param userInput
	 * @return
	 * @throws IOException
	 */
	public boolean verifyShowCoverageTable(String actualPath, String reportTitle) throws IOException {
		Log.method("ComplianceReportsBasePage.verifyShowCoverageTable", actualPath, reportTitle);
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, "CR-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_ShowCoverage);
		expectedReportString.add(ComplianceReportSSRS_PercentCoverageAssets);
		expectedReportString.add(ComplianceReportSSRS_PercentCoverageForecast);
		expectedReportString.add(ComplianceReportSSRS_PercentCoverageReportArea);

		Map<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.info("Show Coverage Table verification failed");
				return false;
			}
		}
		Log.info("Show Coverage Table verification passed");
		return true;
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
	 * Method to verify the Coverage Values Table in SSRS
	 *
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */

	public boolean verifyCoverageValuesTable(String actualPath, String reportTitle, Map<String, String> userSelection)
			throws IOException {
		Log.method("ComplianceReportsBasePage.verifyCoverageValuesTable", actualPath, reportTitle,
				LogHelper.mapToString(userSelection));
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, "CR-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		Log.info("Actual PDF text is:");
		Log.info(actualReportString);
		String nextLine = RegexUtility.getNextLineAfterPattern(actualReportString, "Coverage Values");
		List<String> matches = RegexUtility.split(nextLine.trim(), "%");
		StoredProcComplianceGetCoverage coverageReportObj = new StoredProcComplianceGetCoverage();
		String PCA = null;
		String PCRA = null;
		StoredProcComplianceGetCoverage storedProcObj = StoredProcComplianceGetCoverage.getCoverage(reportId);
		List<String> expectedReportString = new ArrayList<String>();
		int matchIndex = 0;
		if (userSelection.get(KEYPCA).equals("1")) {
			PCA = matches.get(matchIndex++).replaceAll("[\\D+]", "");
			coverageReportObj.setPercentCoverageAssets(PCA);
			expectedReportString.add(ComplianceReportSSRS_TotalLinearAssetCoverage);
		}
		if (userSelection.get(KEYPCRA).equals("1")) {
			PCRA = matches.get(matchIndex).replaceAll("[\\D+]", "");
			coverageReportObj.setPercentCoverageReportArea(PCRA);
			expectedReportString.add(ComplianceReportSSRS_PercentCoverageReportArea);
		}

		Log.info(String.format("Matching expected report strings-[%s], with actual PDF text.",
				LogHelper.arrayToString(expectedReportString.toArray(new String[expectedReportString.size()]))));
		Map<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.info("Coverage Values data verification failed");
				return false;
			}
		}

		Log.info("Verifying isCoverageValuesEquals()...");
		Log.info(String.format("storedProcObj.toString() -> %s", storedProcObj.toString()));
		Log.info(String.format("coverageReportObj.toString() -> %s", coverageReportObj.toString()));
		if (!storedProcObj.isCoverageValuesEquals(coverageReportObj)) {
			Log.info("Coverage Values data verification failed");
			return false;
		}
		Log.info("Verifying isCoverageValuesFormated()...");
		if (!storedProcObj.isCoverageValuesFormated(coverageReportObj)) {
			return false;
		}
		Log.info("Coverage Values data verification passed");
		return true;
	}

	/**
	 * Method to verify the Layers Table in SSRS
	 *
	 * @param actualPath
	 * @param reportTitle
	 * @param userInput
	 * @return
	 * @throws IOException
	 */
	public boolean verifyLayersTable(String actualPath, String reportTitle, Map<String, String> userInput)
			throws IOException {
		Log.method("ComplianceReportsPage.verifyLayersTable", actualPath, reportTitle,
				LogHelper.mapToString(userInput));
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, "CR-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_Asset);
		expectedReportString.add(ComplianceReportSSRS_Boundary);
		expectedReportString.add(KEYASSETCASTIRON);
		expectedReportString.add(KEYASSETCOPPER);
		expectedReportString.add(KEYASSETOTHERPLASTIC);
		expectedReportString.add(KEYASSETPEPLASTIC);
		expectedReportString.add(KEYASSETPROTECTEDSTEEL);
		expectedReportString.add(KEYASSETUNPROTECTEDSTEEL);

		Map<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.info("Layers Table data verification failed");
				return false;
			}
		}
		Log.info("Layers Table data verification passed");
		return true;

	}

	/**
	 * Method to verify the Views Table in SSRS
	 *
	 * @param actualPath
	 * @param reportTitle
	 * @param viewsList
	 * @return
	 * @throws IOException
	 */

	public boolean verifyViewsTable(String actualPath, String reportTitle, List<Map<String, String>> viewsList)
			throws IOException {
		Log.method("ComplianceReportsPage.verifyViewsTable", actualPath, reportTitle,
				LogHelper.mapListToString(viewsList));
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, "CR-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_ViewTable);
		expectedReportString.add(ComplianceReportSSRS_ViewName);
		expectedReportString.add(ComplianceReportSSRS_ShowLISAs);
		expectedReportString.add(ComplianceReportSSRS_ShowFOV);
		expectedReportString.add(ComplianceReportSSRS_ShowBreadcrumb);
		expectedReportString.add(ComplianceReportSSRS_ShowIndications);
		expectedReportString.add(ComplianceReportSSRS_ShowIsotopicAnalyses);
		expectedReportString.add(ComplianceReportSSRS_FieldNotes);
		expectedReportString.add(ComplianceReportSSRS_ShowGaps);
		expectedReportString.add(ComplianceReportSSRS_ShowAssets);
		expectedReportString.add(ComplianceReportSSRS_ShowHighlightLISAAssets);
		expectedReportString.add(ComplianceReportSSRS_ShowHighlightGAPAssets);
		expectedReportString.add(ComplianceReportSSRS_ShowBoundaries);
		expectedReportString.add(ComplianceReportSSRS_BaseMap);

		// Look for AssetBoxNumber static string if there is a view with AssetBox.
		boolean assetBxNumViewPresent = false;
		for (Map<String, String> viewMap : viewsList) {
			if (selectView(viewMap, KEYASSETBOXNUMBER)) {
				assetBxNumViewPresent = true;
				break;
			}
		}
		if (assetBxNumViewPresent) {
			expectedReportString.add(ComplianceReportSSRS_ShowAssetBoxNumber);
		}

		String textWithoutLineEndings = actualReportString.replace("\r\n", "");
		Map<String, Boolean> patternMatches = matchSinglePattern(textWithoutLineEndings, expectedReportString);
		for (Boolean value : patternMatches.values()) {
			if (!value) {
				Log.info("Views Table static text verification failed");
				return false;
			}
		}

		InputStream inputStream = new ByteArrayInputStream(actualReportString.getBytes());
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));

		String line = null;
		ArrayList<ReportView> viewListInReport = new ArrayList<ReportView>();
		Log.info("Matching lines to check if it is a Views table row.");
		try {
			while ((line = bufferReader.readLine()) != null) {
				List<String> matchingGroups = RegexUtility.getMatchingGroups(line.trim(), RegexUtility.VIEWS_TABLE_LINE_REGEX_PATTERN);
				if (matchingGroups != null && matchingGroups.size() > 0) {
					line = matchingGroups.get(0);
					Log.info(String.format("Matched line as a table row! Line = [%s]", line));
					ReportView viewObj = new ReportView();
					String[] split = line.split("\\s+");
					viewObj.setBaseMapId(BaseMapType.getBaseMapTypeId(split[split.length - 1].trim()));
					viewObj.setViewName(line.replace(split[split.length - 1], "").trim());
					viewListInReport.add(viewObj);
				}
			}
		} finally {
			bufferReader.close();
		}

		List<ReportView> dbObjList = ReportView.getReportView(reportId);
		for (ReportView viewObj : dbObjList) {
			if (!viewObj.isViewNameAndMapInList(viewListInReport)) {
				Log.info("Views Table data verification failed");
				return false;
			}

		}

		Log.info("Views Table data verification passed");
		return true;
	}

	/**
	 * Method to verify the Driving Surveys Table in SSRS
	 *
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyDrivingSurveysTable(String actualPath, String reportTitle) throws IOException {
		Log.method("ComplianceReportsPage.verifyDrivingSurveysTable", actualPath, reportTitle);
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, "CR-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ReportSSRS_SelectedDrivingSurveys);
		Map<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.info("Driving survey table static text verification failed");
				return false;
			}
		}
		String surveyTable;
		String endWith = "Surveyor Date";
		if (RegexUtility.getStringInBetween(actualReportString, "Indication Table", endWith) != null) {
			surveyTable = (RegexUtility.getStringInBetween(actualReportString, "Indication Table", "Surveyor Date"))
					.trim().replaceAll("//s+", "").replace("#", "").replace("LISA ", "");
			if (surveyTable.contains("Gap Table")) {
				// TODO: DEFECT in parsing. SKIP check for this case.
				Log.warn(
						"SKIPPING Driving survey verification. The case of Driving Survey table and Gap table PDF parsing is currently NOT supported!!!");
				return true;
			}
		} else {
			endWith = " Layers";
			surveyTable = (RegexUtility.getStringInBetween(actualReportString, "Selected Driving Surveys", endWith))
					.trim().replaceAll("//s+", "").replace("#", "").replace("LISA ", "");
		}

		surveyTable = surveyTable.replaceAll(System.lineSeparator(), "");
		String datePattern = RegexUtility.getReportRegexDatePattern(true);
		String drivingSurveysLinePattern = datePattern + " *" + datePattern;
		surveyTable = surveyTable.replaceAll("(" + drivingSurveysLinePattern + ")", System.lineSeparator() + "$1");
		String[] lines = surveyTable.split(System.lineSeparator());
		Log.info("Driving survey table contains " + (lines.length - 1) + " records");
		ArrayList<StoredProcComplianceAssessmentGetReportDrivingSurveys> listFromStoredProc = StoredProcComplianceAssessmentGetReportDrivingSurveys
				.getReportDrivingSurveys(reportId);
		for (int i = 1; i < lines.length; i++) {
			boolean validLine = false;
			String expectedLine = "";
			String actualLine = lines[i].replaceAll(" ", "");
			// Log.info("Looking for driving survey '" + actualLine + "' in
			// DB");
			for (StoredProcComplianceAssessmentGetReportDrivingSurveys survey : listFromStoredProc) {
				expectedLine = survey.toString().replaceAll(" ", "");
				Log.info("Driving survey line in DB = [" + expectedLine + "]");
				if (actualLine.startsWith(expectedLine)) {
					Log.info("Found match for driving survey in DB.");
					validLine = true;
					break;
				}
			}
			if (!validLine) {
				Log.error(String.format("Driving survey in PDF is not found in DB, '%s'", actualLine));
				return false;
			}
		}
		Log.info("Driving survey table verification passed");
		return true;
	}

	/**
	 * Method to verify the Ethane Capture Table in SSRS
	 *
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyEthaneCaptureTable(String actualPath, String reportTitle) throws IOException {
		Log.method("ComplianceReportsPage.verifyEthaneCaptureTable", actualPath, reportTitle);
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = actualPath + "CR-" + reportId.substring(0, 6) + ".pdf";
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_EthaneAnalysisTable);
		Map<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.info("Ethane Capture table static text verification failed");
				return false;
			}
		}
		BufferedReader bufferReader = null;
		try {
			String ethaneCaptureTable = RegexUtility.getStringInBetween(actualReportString,
					"Surveyor Date/Time Result Ethane/Methane Ratio and Uncertainty(%) Field Notes",
					"Ethane Analysis Table");
			InputStream inputStream = new ByteArrayInputStream(ethaneCaptureTable.getBytes());
			bufferReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			ArrayList<String> lineList = new ArrayList<String>();
			StringBuilder lineBuilder = new StringBuilder();
			while ((line = bufferReader.readLine()) != null) {
				lineBuilder.append(line);
				if (line.contains("+/-")) {
					lineList.add(lineBuilder.toString().replaceAll("\\s+", "").replace("+/-", ""));
					lineBuilder = new StringBuilder();
				}
			}
			ArrayList<StoredProcComplianceGetEthaneCapture> ethaneCapturfromSP = StoredProcComplianceGetEthaneCapture
					.getReportEthaneCapture(reportId);
			Iterator<StoredProcComplianceGetEthaneCapture> captureEntryIterator = ethaneCapturfromSP.iterator();
			ArrayList<String> storedProcList = new ArrayList<String>();
			while (captureEntryIterator.hasNext()) {
				StoredProcComplianceGetEthaneCapture entry = captureEntryIterator.next();
				storedProcList.add(entry.toString().replaceAll("\\s+", "").replace("0.0", "0"));
			}
			if (!storedProcList.equals(lineList)) {
				Log.info("Ethane Capture table data verification failed");
				return false;
			}
		} finally {
			bufferReader.close();
		}
		Log.info("Ethane capture table verification passed");
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

	public void verifyMetaDataFiles() {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyMetaDataFilesData() {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean verifyReportSurveyMetaDataFile(String actualPath, String reportTitle)
			throws FileNotFoundException, IOException {
		Log.method("ComplianceReportsPage.verifyReportSurveyMetaDataFile", actualPath, reportTitle);
		CSVUtility csvUtility = new CSVUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String metaDataZipFileName = getReportMetaZipFileName(reportTitle, false /* includeExtension */);
		String pathToMetaDataUnZip = actualPath;
		String unZipFolder = File.separator + metaDataZipFileName;
		if (!actualPath.endsWith(unZipFolder))
			pathToMetaDataUnZip += unZipFolder;

		String pathToCsv = pathToMetaDataUnZip + File.separator + "CR-" + reportId.substring(0, 6)
				+ "-ReportSurvey.csv";
		String reportName = "CR-" + reportId;

		if (actualPath.endsWith("-ReportSurvey.csv")) {
			pathToCsv = actualPath;
		}

		setReportName(reportName);
		List<Map<String, String>> csvRows = csvUtility.getAllRows(pathToCsv);
		Iterator<Map<String, String>> csvIterator = csvRows.iterator();
		List<StoredProcComplianceAssessmentGetReportDrivingSurveys> reportList = new ArrayList<StoredProcComplianceAssessmentGetReportDrivingSurveys>();
		while (csvIterator.hasNext()) {
			StoredProcComplianceAssessmentGetReportDrivingSurveys reportDrivingObj = new StoredProcComplianceAssessmentGetReportDrivingSurveys();
			Map<String, String> csvRow = csvIterator.next();
			if (!csvRow.get("ReportId").trim().toLowerCase().equals(reportId.trim().toLowerCase())) {
				return false;
			}
			if (!csvRow.get("ReportName").trim().equals(getReportName().trim().substring(0, 9))) {
				return false;
			}
			reportDrivingObj.setPreferredStartDateTimeWithTZ(csvRow.get("SurveyStartDateTime").trim());
			reportDrivingObj.setPreferredEndDateTimeWithTZ(csvRow.get("SurveyEndDateTime").trim());
			reportDrivingObj.setUserName(csvRow.get("UserName").trim());
			reportDrivingObj.setDescription(csvRow.get("Surveyor").trim());
			reportDrivingObj.setAnalyzerId(csvRow.get("Analyzer").trim());
			reportDrivingObj.setTag(csvRow.get("Tag").trim());
			reportDrivingObj.setStabilityClass(csvRow.get("StabilityClass").trim());
			reportList.add(reportDrivingObj);
		}
		ArrayList<StoredProcComplianceAssessmentGetReportDrivingSurveys> listFromStoredProc = StoredProcComplianceAssessmentGetReportDrivingSurveys
				.getReportDrivingSurveys(reportId);
		Iterator<StoredProcComplianceAssessmentGetReportDrivingSurveys> reportIterator = reportList.iterator();
		while (reportIterator.hasNext()) {
			StoredProcComplianceAssessmentGetReportDrivingSurveys obj = reportIterator.next();
			if (!obj.isInList(listFromStoredProc)) {
				Log.info("Report survey meta data file verification failed");
				return false;
			}
		}
		Log.info("Report survey meta data file verification passed");
		return true;
	}

	public boolean verifyIsotopicMetaDataFile(String actualPath, String reportTitle)
			throws FileNotFoundException, IOException {
		Log.method("ComplianceReportsPage.verifyIsotopicMetaDataFile", actualPath, reportTitle);
		CSVUtility csvUtility = new CSVUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String pathToMetaDataUnZip = actualPath;
		String metaDataZipFileName = getReportMetaZipFileName(reportTitle, false /* includeExtension */);
		String unZipFolder = File.separator + metaDataZipFileName;
		if (!actualPath.endsWith(unZipFolder))
			pathToMetaDataUnZip += unZipFolder;

		String pathToCsv = pathToMetaDataUnZip + File.separator + "CR-" + reportId.substring(0, 6)
				+ "-ReportIsotopicCapture.csv";
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		List<Map<String, String>> csvRows = csvUtility.getAllRows(pathToCsv);
		Iterator<Map<String, String>> csvIterator = csvRows.iterator();
		List<StoredProcComplianceGetIsotopics> reportList = new ArrayList<StoredProcComplianceGetIsotopics>();
		while (csvIterator.hasNext()) {
			StoredProcComplianceGetIsotopics reportIsoObj = new StoredProcComplianceGetIsotopics();
			Map<String, String> csvRow = csvIterator.next();
			if (!csvRow.get("ReportId").trim().equalsIgnoreCase(reportId.trim())) {
				Log.info("Isotopic meta data file verification failed");
				return false;
			}
			if (!csvRow.get("ReportName").trim().equalsIgnoreCase(getReportName().trim().substring(0, 9))) {
				Log.info("Isotopic meta data file verification failed");
				return false;
			}
			reportIsoObj.setDateTime(csvRow.get("AnalysisDateTime").trim());
			reportIsoObj.setSurveyorUnitName(csvRow.get("Surveyor").trim());
			reportIsoObj.setDisposition(csvRow.get("Result").trim());
			String[] deltaUncertainty = csvRow.get("ValueUncertainty").split(RatioSdevMetaPattern);
			reportIsoObj.setDelta(Float.parseFloat(deltaUncertainty[0].trim()));
			reportIsoObj.setUncertainty(Float.parseFloat(deltaUncertainty[1].trim()));
			reportIsoObj.setText(csvRow.get("FieldNotes").trim());
			reportList.add(reportIsoObj);
		}
		ArrayList<StoredProcComplianceGetIsotopics> storedPodList = StoredProcComplianceGetIsotopics
				.getReportIsotopics(reportId);

		for (StoredProcComplianceGetIsotopics reportListObj : reportList) {
			if (!reportListObj.isInList(storedPodList)) {
				Log.info("Isotopic meta data file verification failed");
				return false;
			}
		}
		Log.info("Isotopic meta data file verification passed");
		return true;
	}

	public boolean verifyEthaneCaptureMetaDataFile(String actualPath, String reportTitle)
			throws FileNotFoundException, IOException {
		Log.method("ComplianceReportsPage.verifyEthaneCaptureMetaDataFile", actualPath, reportTitle);
		return verifyEthaneCaptureMetaDataFile(actualPath, reportTitle, Report.getReport(reportTitle).getId());
	}

	public boolean verifyEthaneCaptureMetaDataFile(String actualPath, String reportTitle, String reportId)
			throws FileNotFoundException, IOException {
		Log.method("ComplianceReportsPage.verifyEthaneCaptureMetaDataFile", actualPath, reportTitle, reportId);

		CSVUtility csvUtility = new CSVUtility();
		String metaDataZipFileName = getReportMetaZipFileName(reportTitle, false /* includeExtension */);
		String pathToMetaDataUnZip = actualPath + File.separator + metaDataZipFileName;
		String pathToCsv = pathToMetaDataUnZip + File.separator + "CR-" + reportId.substring(0, 6)
				+ "-ReportEthaneCapture.csv";
		String reportName = "CR-" + reportId;

		if (actualPath.endsWith("-ReportEthaneCapture.csv")) {
			pathToCsv = actualPath;
		}
		setReportName(reportName);
		List<Map<String, String>> csvRows = csvUtility.getAllRows(pathToCsv);
		Iterator<Map<String, String>> csvIterator = csvRows.iterator();
		List<StoredProcComplianceGetEthaneCapture> reportList = new ArrayList<StoredProcComplianceGetEthaneCapture>();
		while (csvIterator.hasNext()) {
			StoredProcComplianceGetEthaneCapture ethaneCapture = new StoredProcComplianceGetEthaneCapture();
			Map<String, String> csvRow = csvIterator.next();
			if (!csvRow.get("ReportId").trim().equalsIgnoreCase(reportId.trim())) {
				return false;
			}
			if (!csvRow.get("ReportName").trim().equalsIgnoreCase(getReportName().trim().substring(0, 9))) {
				return false;
			}
			ethaneCapture.setDateTime(csvRow.get("AnalysisDateTime").trim());
			ethaneCapture.setSurveyorUnitName(csvRow.get("Surveyor").trim());
			ethaneCapture.setDisposition(csvRow.get("Result").trim());

			String[] valueUncertainty = csvRow.get("ValueUncertainty").trim().split(RatioSdevMetaPattern);
			ethaneCapture.setEthaneRatio(Float.parseFloat(valueUncertainty[0].trim()));
			ethaneCapture.setEthaneRatioSdev(Float.parseFloat(valueUncertainty[1].trim()));
			ethaneCapture.setText(csvRow.get("FieldNotes").trim());

			reportList.add(ethaneCapture);

		}
		ArrayList<StoredProcComplianceGetEthaneCapture> listFromStoredProc = StoredProcComplianceGetEthaneCapture
				.getReportEthaneCapture(reportId);
		Iterator<StoredProcComplianceGetEthaneCapture> reportIterator = reportList.iterator();
		while (reportIterator.hasNext()) {
			StoredProcComplianceGetEthaneCapture testEthaneCapture = reportIterator.next();
			Log.debug("Ethane capture in meta: " + testEthaneCapture);
			if (!testEthaneCapture.isInList(listFromStoredProc)) {
				Log.warn("Ethane capture not found in db? " + testEthaneCapture);
				return false;
			}
		}
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

	public boolean verifyLISASMetaDataFile(String actualPath, String reportTitle)
			throws FileNotFoundException, IOException {
		Log.method("ComplianceReportsPage.verifyLISASMetaDataFile", actualPath, reportTitle);
		return verifyLISASMetaDataFile(actualPath, reportTitle, Report.getReport(reportTitle).getId());
	}

	public boolean verifyLISASMetaDataFile(String actualPath, String reportTitle, String reportId)
			throws FileNotFoundException, IOException {
		Log.method("ComplianceReportsPage.verifyLISASMetaDataFile", actualPath, reportTitle, reportId);
		CSVUtility csvUtility = new CSVUtility();
		String pathToMetaDataUnZip = actualPath;
		String metaDataZipFileName = getReportMetaZipFileName(reportTitle, false /* includeExtension */);
		String unZipFolder = File.separator + metaDataZipFileName;
		if (!actualPath.endsWith(unZipFolder))
			pathToMetaDataUnZip += unZipFolder;

		String pathToCsv = pathToMetaDataUnZip + File.separator + "CR-" + reportId.substring(0, 6) + "-ReportLISAS.csv";
		String reportName = "CR-" + reportId;
		if (actualPath.endsWith("-ReportLISAS.csv")) {
			pathToCsv = actualPath;
		}
		setReportName(reportName);
		List<Map<String, String>> csvRows = csvUtility.getAllRows(pathToCsv);
		Iterator<Map<String, String>> csvIterator = csvRows.iterator();
		List<StoredProcComplianceGetIndications> reportList = new ArrayList<StoredProcComplianceGetIndications>();
		while (csvIterator.hasNext()) {
			StoredProcComplianceGetIndications reportIndObj = new StoredProcComplianceGetIndications();
			Map<String, String> csvRow = csvIterator.next();
			if (!csvRow.get("ReportId").trim().equalsIgnoreCase(reportId.trim())) {
				Log.info("ReportId does NOT match. LISA Meta data file verification failed");
				return false;
			}
			if (!csvRow.get("ReportName").trim().equalsIgnoreCase(getReportName().trim().substring(0, 9))) {
				Log.info("ReportName does NOT match. LISA Meta data file verification failed");
				return false;
			}
			reportIndObj.setPeakNumber(csvRow.get("LISANumber").trim().replaceAll("LISA", ""));
			reportIndObj.setSurveyorUnitName(csvRow.get("Surveyor").trim());
			reportIndObj.setDateTime(csvRow.get("LISADateTime").trim());

			double amp = Math.round(Float.parseFloat((csvRow.get("Amplitude")).trim()) * 100.0) / 100.0;
			reportIndObj.setAmplitude((float) amp);
			double cH4 = Math.round(Float.parseFloat((csvRow.get("Concentration")).trim()) * 100.0) / 100.0;
			reportIndObj.setCh4((float) cH4);
			reportIndObj.setText(csvRow.get("FieldNotes").trim());

			// Covert csv ratio+/sdev to db ratio and sdev - it changed for
			// indication
			String ethaneMethaneRatioUncertainty = csvRow.get("EthaneMethaneRatioUncertainty").trim();
			reportIndObj.setAggregatedEthaneToMethaneRatio(ethaneMethaneRatioUncertainty);
			String aggregatedClassificationconfidence = "N/A";
			try {
				int aggregatedClassificationconfidenceFloat = (int) (Float
						.parseFloat(csvRow.get("ConfidenceInDisposition").trim()) * 100);
				aggregatedClassificationconfidence = aggregatedClassificationconfidenceFloat + "%";
			} catch (Exception e) {
				Log.warn(e.toString());
			}
			reportIndObj.setAggregatedClassificationConfidence(aggregatedClassificationconfidence);
			reportList.add(reportIndObj);
		}

		ArrayList<StoredProcComplianceGetIndications> storedPodList = StoredProcComplianceGetIndications
				.getReportIndications(reportId);

		for (StoredProcComplianceGetIndications reportListObj : reportList) {
			if (!reportListObj.isInList(storedPodList)) {
				Log.info(String.format(
						"LISA Meta data file verification failed. Report object from database -> [%s] NOT found in CSV.",
						reportListObj.toString()));
				return false;
			}
		}
		Log.info("LISA Meta data file verification passed");
		return true;
	}

	public boolean verifyThumbnailInReportViewer(ReportViewerThumbnailType compliancezipmeta) {
		switch (compliancezipmeta) {
		case ComplianceTablePDF:
			return this.isReportPDFIconDisplayedInViewer();
		case ComplianceZipMeta:
			return this.isMetadataIconDisplayedInViewer();
		case ComplianceZipPDF:
			return this.isReportZipIconDisplayedInViewer();
		case ComplianceZipShape:
			return this.isShapeIconDisplayedInViewer();
		default:
			break;
		}
		return false;
	}

	private String getIsotopicValue(String isotopicUncertaintyValue) {
		List<String> split = RegexUtility.split(isotopicUncertaintyValue, "+/-");
		if (split != null && split.size() == 2) {
			return split.get(0);
		}
		return "";
	}

	private String getUncertaintyPercent(String isotopicUncertaintyValue) {
		List<String> split = RegexUtility.split(isotopicUncertaintyValue, "+/-");
		if (split != null && split.size() == 2) {
			return split.get(1);
		}
		return "";
	}

	public boolean verifyIsotopicValueIsFormattedCorrectly(String isotopicUncertaintyValue) {
		Log.method("ComplianceReportsPage.verifyIsotopicValueIsFormattedCorrectly", isotopicUncertaintyValue);

		String isotopicValue = getIsotopicValue(isotopicUncertaintyValue);

		// Valid values:
		// -100 <= IsotopicValue <= 0
		// (2 or less decimal places)
		if (isotopicValue.isEmpty()) {
			return false;
		}
		// check values.
		Float isoValue = Float.valueOf(isotopicValue);
		if (!NumberUtility.isInRange(isoValue, -100.0F, 0.0F)) {
			return false;
		}
		// check decimal format.
		Integer decimalCount = NumberUtility.decimalsInNumber(isotopicValue);
		if (decimalCount > 2) {
			Log.info(String.format("Isotopic value:[%s] NOT in format {00[.00]}. " + "Found more than 2 decimal places",
					isotopicValue));
			return false;
		}

		return true;
	}

	public boolean verifyUncertaintyValueIsFormattedCorrectly(String isotopicUncertaintyValue) {
		Log.method("ComplianceReportsPage.verifyUncertaintyValueIsFormattedCorrectly", isotopicUncertaintyValue);
		String uncertaintyValue = getUncertaintyPercent(isotopicUncertaintyValue);

		// Value values:
		// 0.0 <= Uncertainty <= 1.00
		// (2 or less decimal places)
		if (uncertaintyValue.isEmpty()) {
			return false;
		}
		// check values.
		Float uncertainty = Float.valueOf(uncertaintyValue);
		if (!NumberUtility.isInRange(uncertainty, 0.0F, 1.0F)) {
			return false;
		}
		// check decimal format.
		Integer decimalCount = NumberUtility.decimalsInNumber(uncertaintyValue);
		if (decimalCount > 2) {
			Log.info(String.format(
					"Uncertainty value:[%s] NOT in format {00[.00]}. " + "Found more than 2 decimal places",
					uncertaintyValue));
			return false;
		}
		return true;
	}

	/**
	 * Method to verify the Ethane Analysis Table in SSRS
	 *
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyEthaneAnalysisTable(String actualPath, String reportTitle) throws IOException {
		Log.method("ComplianceReportsPage.verifyEthaneAnalysisTable", actualPath, reportTitle);
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, "CR-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_EthaneAnalysisTable);
		Log.info(String.format("PDF Text Content : %s", actualReportString));
		Log.info(String.format("Expected Strings in PDF Text Content : %s",
				LogHelper.strListToString(expectedReportString)));

		Map<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.info("Ethane Analysis table verification failed");
				return false;
			}
		}

		if (actualReportString != null) {
			InputStream inputStream = new ByteArrayInputStream(actualReportString.getBytes());
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			try {
				ArrayList<String> reportEthaneList = new ArrayList<String>();
				while ((line = bufferReader.readLine()) != null) {
					if (!line.trim().matches(RegexUtility.INDICATION_TABLE_LINE_REGEX_PATTERN)) {
						List<String> matchingGroups = RegexUtility.getMatchingGroups(line.trim(),
								RegexUtility.ISOTOPIC_ANALYSIS_TABLE_LINE_REGEX_PATTERN);
						if (matchingGroups != null && matchingGroups.size() > 0) {
							line = matchingGroups.get(0);
							line = line.replaceAll(" +", " ").trim();
							reportEthaneList.add(line);
						}
					}
				}

				Log.info(String.format("ReportEthaneCapture ArrayList Values : %s",
						LogHelper.strListToString(reportEthaneList)));
				ArrayList<StoredProcComplianceGetEthaneCapture> storedProcEthaneList = StoredProcComplianceGetEthaneCapture
						.getReportEthaneCapture(reportId);
				Iterator<StoredProcComplianceGetEthaneCapture> lineIterator = storedProcEthaneList.iterator();
				ArrayList<String> storedProcConvStringList = new ArrayList<String>();
				while (lineIterator.hasNext()) {
					StoredProcComplianceGetEthaneCapture objStoredProc = lineIterator.next();
					String objAsString = objStoredProc.toString();
					storedProcConvStringList.add(objAsString.trim());
				}

				Log.info(
						String.format("Checking in ReportEthaneCapture ArrayList, StoredProcConvStringList Values : %s",
								LogHelper.strListToString(storedProcConvStringList)));
				if (!reportEthaneList.equals(storedProcConvStringList)) {
					Log.info(String.format(
							"EthaneCapture Analysis table verification failed, Expected: '%s', Actual: '%s'",
							storedProcConvStringList, reportEthaneList));
					return false;
				}
			} finally {
				bufferReader.close();
			}
		}
		Log.info("Ethane Analysis table verification passed");
		return true;

	}

	/**
	 * Method to verify the Isotopic Analysis Table in SSRS
	 *
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyIsotopicAnalysisTable(String actualPath, String reportTitle) throws IOException {
		Log.method("ComplianceReportsPage.verifyIsotopicAnalysisTable", actualPath, reportTitle);
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, "CR-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_IsotopicAnalysisTable);
		Log.info(String.format("PDF Text Content : %s", actualReportString));
		Log.info(String.format("Expected Strings in PDF Text Content : %s",
				LogHelper.strListToString(expectedReportString)));

		Map<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.info("Isotopic Analysis table verification failed");
				return false;
			}
		}

		if (actualReportString != null) {
			List<String> reportIsotopicList = new SSRSIsotopicAnalysisTableParser().parse(actualReportString, null);
			Log.info(String.format("ReportIsotopic ArrayList Values : %s",
					LogHelper.strListToString(reportIsotopicList)));
			ArrayList<StoredProcComplianceGetIsotopics> storedProcIsotopicList = StoredProcComplianceGetIsotopics
					.getReportIsotopics(reportId);
			Iterator<StoredProcComplianceGetIsotopics> lineIterator = storedProcIsotopicList.iterator();
			ArrayList<String> storedProcConvStringList = new ArrayList<String>();
			while (lineIterator.hasNext()) {
				StoredProcComplianceGetIsotopics objStoredProc = lineIterator.next();
				String objAsString = objStoredProc.toString();
				storedProcConvStringList.add(objAsString.trim());
			}

			Log.info(String.format("Checking in ReportIsotopic ArrayList, StoredProcConvStringList Values : %s",
					LogHelper.strListToString(storedProcConvStringList)));
			if (!reportIsotopicList.equals(storedProcConvStringList)) {
				Log.info("Isotopic Analysis table verification failed");
				return false;
			}

		}
		Log.info("Isotopic Analysis table verification passed");
		return true;
	}

	/**
	 * Method to verify SSRS PDF has the expected strings.
	 *
	 * @param actualPath
	 *            - path of the SSRS PDF
	 * @param reportTitle
	 *            - report title
	 * @param expectedReportString
	 *            - List of strings that are expected to be found in PDF text
	 * @return
	 * @throws IOException
	 */
	public boolean verifySSRSPDFContainsText(String actualPath, String reportTitle, List<String> expectedReportString)
			throws IOException {
		Log.method("ComplianceReportsPage.verifySSRSPDFContainsText", actualPath, reportTitle,
				LogHelper.listToString(expectedReportString));
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, "CR-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		Map<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.info("Did NOT find match for all expected Strings!");
				return false;
			}
		}
		Log.info("All expected strings were found in the PDF text.");
		return true;
	}

	/**
	 * Method to verify the Indication Table in SSRS
	 *
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyIndicationTable(String actualPath, String reportTitle) throws IOException {
		Log.method("ComplianceReportsPage.verifyIndicationTable", actualPath, reportTitle);
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, "CR-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_IndicationTable);
		Log.info(String.format("PDF Text Content : %s", actualReportString));
		Log.info(String.format("Expected Strings in PDF Text Content : %s",
				LogHelper.strListToString(expectedReportString)));

		Map<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.error("Indication table static text verification failed");
				return false;
			}
		}

		String matchStartString = "Disposition Confidence in Disposition";
		String matchEndString = "Software Version";
		List<String> indicationTables = RegexUtility.getStringsInBetween(actualReportString, matchStartString,
				matchEndString);
		String indicationTable = "";
		for (String table : indicationTables) {
			indicationTable += System.lineSeparator() + table;
		}

		Log.info(String.format("Extracted values between '%s' and '%s' are: %s", matchStartString, matchEndString,
				indicationTable));

		InputStream inputStream = new ByteArrayInputStream(indicationTable.getBytes());
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		ArrayList<String> reportIndicationsList = new ArrayList<String>();
		String extraLines = "";
		try {
			while ((line = bufferReader.readLine()) != null) {
				line = TextUtility.removeNonAsciiSpecialChars(line);
				Log.info(String.format("Matching line to check if it is table row. Line text=[%s]", line));
				if (line.trim().matches(RegexUtility.INDICATION_TABLE_LINE_REGEX_PATTERN)) {
					if (!line.trim().matches(RegexUtility.SSRS_PDF_PAGE_FOOTER_PATTERN)) {
						Log.info("Matched line as a table row!");
						ArrayUtility.appendToLastString(reportIndicationsList, extraLines.replaceAll(" ", ""));
						reportIndicationsList.add(line.replaceAll("\\?", "").trim().replace("+/-", "")
								.replace("0.0 ", "").trim().replaceAll(" ", "").replace(">=", ""));
						extraLines = "";
					}
				} else if (!reportIndicationsList.isEmpty()
						&& line.trim().matches(RegexUtility.FIELD_NOTE_LINE_REGEX_PATTERN)) {
					extraLines += line.trim();
				}
			}
		} finally {
			bufferReader.close();
		}
		ArrayUtility.appendToLastString(reportIndicationsList, extraLines.replaceAll(" ", ""));
		Log.info(String.format("ReportIndications ArrayList Values : %s",
				LogHelper.strListToString(reportIndicationsList)));

		ArrayList<StoredProcComplianceGetIndications> storedProcIndicationsList = StoredProcComplianceGetIndications
				.getReportIndications(reportId);
		Iterator<StoredProcComplianceGetIndications> lineIterator = storedProcIndicationsList.iterator();
		ArrayList<String> storedProcConvStringList = new ArrayList<String>();
		while (lineIterator.hasNext()) {
			StoredProcComplianceGetIndications objStoredProc = lineIterator.next();
			String objAsString = objStoredProc.toString();
			storedProcConvStringList
					.add(objAsString.replace("0.0 ", "0").replaceAll("\\s+", "").trim().replace("+/-", ""));
		}

		Log.info(String.format("Checking in ReportIndications ArrayList, StoredProcConvStringList Values : %s",
				LogHelper.strListToString(storedProcConvStringList)));
		if (!reportIndicationsList.equals(storedProcConvStringList)) {
			Log.error("Indication data table verification failed");
			return false;
		}

		List<String[]> lisasIndicationTblList = getSSRSPDFTableValues(PDFTable.LISAINDICATIONTABLE, reportTitle);
		LISAIndicationTableColumns tableColumn = LISAIndicationTableColumns.valueOf("LISANum");
		List<String> tableValuesList = ArrayUtility.getColumnStringList(lisasIndicationTblList, tableColumn.getIndex());
		if (!SortHelper.isNumberSortedASC(tableValuesList.toArray(new String[tableValuesList.size()]))) {
			Log.error("Lisa numbers present in indications table are not in sequential order");
			return false;
		}

		Log.info("Indication table verification passed");
		return true;
	}

	public void waitForInvestigationPDFFileDownload(String reportName) {
		waitForFileDownload(reportName + "-Investigation.pdf", testSetup.getDownloadPath());
	}

	public void waitForInvestigationCSVFileDownload(String reportName) {
		waitForFileDownload(reportName + "-ReportInvestigations.csv", testSetup.getDownloadPath());
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
	public void fillReportSpecific(BaseReportEntity reports) throws Exception {
		ComplianceReportEntity reportsCompliance = (ComplianceReportEntity) reports;

		// 1. Change customer if specified.
		if (reportsCompliance.getCustomer() != null && !reportsCompliance.getCustomer().equalsIgnoreCase(CUSTOMER_PICARRO)) {
			Log.info("Select customer '"+reports.getCustomer()+"'");
			selectCustomer(reportsCompliance.getCustomer());
			Boolean confirmed = getChangeCustomerDialog().confirmInChangeCustomerDialog();
			if (confirmed) {
				inputReportTitle(reportsCompliance.getRptTitle());
			}
		}

		// 2. Report general
		if (reportsCompliance.getEthaneFilter() != null) {
			selectEthaneFilter(reportsCompliance.getEthaneFilter());
		}

		if (reportsCompliance.getReportModeFilter() != null) {
			selectReportMode(reportsCompliance.getReportModeFilter());
		}

		if (reportsCompliance.getExclusionRadius() != null) {
			inputExclusionRadius(reportsCompliance.getExclusionRadius());
		}

		// 3. Area Selector
		if (isCustomBoundarySpecified(reportsCompliance)) {
			selectCustomBoundaryRadioButton();
			if (useCustomBoundaryLatLongSelector(reportsCompliance)) {
				fillCustomBoundaryUsingLatLongSelector(reportsCompliance);
			} else {
				fillCustomBoundaryTextFields(reportsCompliance.getNELat(), reportsCompliance.getNELong(),
						reportsCompliance.getSWLat(), reportsCompliance.getSWLong());
			}
		} else {
			if (isComplexBoundary(reportsCompliance)) {
				fillCustomBoundaryUsingHiddenFields(reportsCompliance);
			} else {
				fillCustomerBoundary(reportsCompliance);
			}
		}

		inputImageMapHeight(reportsCompliance.getImageMapHeight());
		inputImageMapWidth(reportsCompliance.getImageMapWidth());

		// 4. SearchAreaPreference and Views
		selectSearchPreferenceArea(reportsCompliance);
		addViews(reportsCompliance.getCustomer(), reportsCompliance.getViewList());

		// 5. Optional Tabular PDF Content
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

		// 6. Optional View layers
		List<Map<String, String>> viewLayersList = reportsCompliance.getViewLayersList();
		if (viewLayersList != null && viewLayersList.size() > 0) {
			handleOptionalDynamicViewLayersSection(viewLayersList);
		}
	}

	@Override
	protected void handleExtraAddSurveyInfoParameters(BaseReportEntity reports) {
		SurveyModeFilter surveyModeFilter = ((ReportCommonEntity) reports).getSurveyModeFilter();
		if (surveyModeFilter != null) {
			selectSurveyModeForSurvey(surveyModeFilter);
		}
	}

	@Override
	protected void handleExtraAddSurveyInfoParameters(SurveyModeFilter surveyModeFilter) {
		if (surveyModeFilter != null) {
			selectSurveyModeForSurvey(surveyModeFilter);
		}
	}

	@Override
	public String getReportPrefix() {
		return "CR";
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

	private void selectSearchPreferenceArea(ComplianceReportEntity reportsCompliance) {
		if (reportsCompliance.getSearchAreaPreference() != null) {
			selectHighlightingAlgorithmDropdown(reportsCompliance.getSearchAreaPreference().toString());
		}
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
		return this.inputLISAOpacity.getAttribute("value");
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

	private static ResourceProvider getCommonResourceProvider() {
		return new ResourceProvider(() -> {
			Map<String, String> resxMap = new HashMap<String, String>();
			resxMap.put(ResourceTable.Key_PageText, STRPageContentText);
			resxMap.put(ResourceTable.Key_NewPageText, STRNewPageContentText);
			resxMap.put(ResourceTable.Key_CopyPageText, STRCopyPageTitle);
			return new ResourceTable(resxMap);
		});
	}
}