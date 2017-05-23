package surveyor.scommon.source;

import static common.source.BaseHelper.matchSinglePattern;
import static surveyor.scommon.source.SurveyorConstants.CUSTOMER_PICARRO;
import static surveyor.scommon.source.SurveyorConstants.KEYVIEWNAME;
import static surveyor.scommon.source.SurveyorConstants.LINE_SELECTOR_ZOOMLEVEL;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataaccess.source.StoredProcEQGetEQData;
import surveyor.scommon.entities.EQReportEntity;
import surveyor.scommon.entities.EQReportEntity.EmissionsQuantificationTableColumns;
import surveyor.scommon.entities.ReportCommonEntity;
import surveyor.scommon.actions.ActionArguments;
import surveyor.scommon.entities.BaseReportEntity;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;
import surveyor.scommon.source.LatLongSelectionControl.ControlMode;
import common.source.ArrayUtility;
import common.source.Constants;
import common.source.Log;
import common.source.LogHelper;
import common.source.NumberUtility;
import common.source.PDFUtility;
import common.source.RetryUtil;
import common.source.SortHelper;
import common.source.TestSetup;
import common.source.WebElementExtender;
import common.source.PDFTableUtility.PDFTable;

/**
 * Assessment Reports Page model
 */
public class EQReportsPage extends ReportsCommonPage {

	public static final String STRURLPath = "/Reports/EQReports";
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.EQReports_PageTitle);
	public static final String STRNewPageContentText = Resources.getResource(ResourceKeys.EQReports_AddNew);
	public static final String STRCopyPageTitle = Resources.getResource(ResourceKeys.EQReport_PageTitle);
	public static final String EQReportSSRS_EmissionsQuantificationTable = Resources.getResource(ResourceKeys.EQReportSSRS_EmissionsQuantificationReport);
	@FindBy(id = "report-locationID")
	protected WebElement eqLocationSelector;

	@FindBy(id = "btn-EQ-select-area")
	protected WebElement lineSegmentsSelectorBtn;
	/**
	 * @param driver
	 * @param strBaseURL
	 * @param testSetup
	 */
	public EQReportsPage(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup, STRURLPath, () -> getCommonResourceProvider());

		Log.info(String.format("\nThe EQ Reports Page URL is: %s\n", this.strPageURL));
	}

	public String getFullReportName(String rptTitle) {
		return getReportPrefix() + "-" + getReportName(rptTitle);
	}

	@Override
	public void fillReportSpecific(BaseReportEntity reports) {
		EQReportEntity reportsEQ = (EQReportEntity) reports;

		// 1. Change customer if specified.
		if (reportsEQ.getCustomer() != null && !reportsEQ.getCustomer().equalsIgnoreCase(CUSTOMER_PICARRO)) {
			Log.info("Select customer '"+reports.getCustomer()+"'");
			selectCustomer(reportsEQ.getCustomer());
			Boolean confirmed = getChangeCustomerDialog().confirmInChangeCustomerDialog();
			if (confirmed) {
				inputReportTitle(reportsEQ.getRptTitle());
			}
		}

		// 2. EQ Location Parameter
		if (!reportsEQ.getEQLocationParameter().isEmpty()) {
			selectEQLocationParameter(reportsEQ.getEQLocationParameter());
		}

		// 3. Line Selector
		List<List<Coordinates>> lineSegments = reportsEQ.getLineSegments();
		selectLineSegments(lineSegments);
	}

	@Override
	public String getReportPrefix() {
		return "EQ";
	}

	protected void selectEQLocationParameter(String eqLocationParameter) {
		selectDropdownItem(eqLocationSelector, eqLocationParameter);
	}

	protected void openLineSegmentsSelector() {
		Log.clickElementInfo("Open Line Segments Selector");
		// Try few times before failure
		boolean actionSuccess = RetryUtil.retryOnException(
				() -> { return clickLineSegmentsSelector();},
				() -> { return true; },
				Constants.THOUSAND_MSEC_WAIT_BETWEEN_RETRIES,
				Constants.DEFAULT_MAX_RETRIES, true /*takeScreenshotOnFailure*/);
		if (!actionSuccess) {
			Log.error(String.format("clickLineSegmentsSelector() executed %d times and resulted in exception.", Constants.DEFAULT_MAX_RETRIES));
		}
	}
	
	protected boolean clickLineSegmentsSelector() {
		Log.clickElementInfo("Click Line Segments Selector");
		this.lineSegmentsSelectorBtn.click();
		return WebElementExtender.verifyElementIsDisplayed(driver, latLongSelectionControl.getOkButton(), timeout);
	}

	protected void selectLineSegments(List<List<Coordinates>> lineSegments) {
			if(lineSegments.isEmpty())
				return;
			openLineSegmentsSelector();
			latLongSelectionControl.waitForModalDialogOpen();
			latLongSelectionControl.switchMode(ControlMode.MapInteraction);
			latLongSelectionControl.waitForMapImageLoad();
			waitForAJAXCallsToComplete();
			mapViewPage.setZoomLevel(LINE_SELECTOR_ZOOMLEVEL);
			for(List<Coordinates> line:lineSegments){
				latLongSelectionControl.selectSegment(line);
			}
			latLongSelectionControl.switchMode(ControlMode.Default).clickOkButton();
			latLongSelectionControl.waitForModalDialogToClose();
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

	private static ResourceProvider getCommonResourceProvider() {
		return new ResourceProvider(() -> {
			Map<String, String> resxMap = new HashMap<String, String>();
			resxMap.put(ResourceTable.Key_PageText, STRPageContentText);
			resxMap.put(ResourceTable.Key_NewPageText, STRNewPageContentText);
			resxMap.put(ResourceTable.Key_CopyPageText, STRCopyPageTitle);
			return new ResourceTable(resxMap);
		});
	}

	@Override
	public void openNewReportPage() {
		waitUntilPresenceOfElementLocated(By.xpath(strBtnNewReport));
		Log.clickElementInfo("New Report Button");
		jsClick(this.btnNewReport);
		String elementXPath = "//*[@id='btn-EQ-select-area']";
		refreshPageUntilElementFound(elementXPath);
	}
	
	/**
	 * Method to verify the Emissions Quantification Data Table in SSRS
	 *
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyEmissionsQuantificationTable(String actualPath, String reportTitle) throws IOException {
		Log.method("EQReportsPage.verifyEmissionsQuantificationTable", actualPath, reportTitle);
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, getReportPrefix() + "-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = getReportPrefix() + "-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(EQReportSSRS_EmissionsQuantificationTable);
		Log.info(String.format("PDF Text Content : %s", actualReportString));
		Log.info(String.format("Expected Strings in PDF Text Content : %s",
				LogHelper.strListToString(expectedReportString)));

		Map<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.error("Emissions Quantification table static text verification failed");
				return false;
			}
		}

		List<String[]> emissionsQuantificationTblList = getSSRSPDFTableValues(PDFTable.EQDATATABLE, reportTitle);
		
		ArrayList<StoredProcEQGetEQData> storedProcEQDataList = StoredProcEQGetEQData.getEQData(reportId);
		Iterator<StoredProcEQGetEQData> lineIterator = storedProcEQDataList.iterator();
		ArrayList<String> storedProcConvStringList = new ArrayList<String>();
		while (lineIterator.hasNext()) {
			StoredProcEQGetEQData objStoredProc = lineIterator.next();
			String objAsString = objStoredProc.toString();
			storedProcConvStringList
					.add(objAsString.replaceAll("\\s+", "").trim());
		}

		Log.info(String.format("Checking in EmissionsQuantification ArrayList, StoredProcConvStringList Values : %s",
				LogHelper.strListToString(storedProcConvStringList)));
		if(emissionsQuantificationTblList.size() != storedProcConvStringList.size()){
			Log.error("Emissions Quantification data table verification failed");
			Log.error("Expect "+storedProcConvStringList.size()+" records, found "+emissionsQuantificationTblList.size()+" records in PDF");
			return false;
		}
		for(String[] row:emissionsQuantificationTblList){
			String tableRow="";
			for(String field:row){
				tableRow += field;
			}
			tableRow = tableRow.replaceAll("\\s+", "").trim();
			if(!storedProcConvStringList.contains(tableRow)){
				Log.error("Emissions Quantification data table verification failed");
				return false;
			}
		}

		EmissionsQuantificationTableColumns tableColumn = EmissionsQuantificationTableColumns.SegmentRank;
		List<String> tableValuesList = ArrayUtility.getColumnStringList(emissionsQuantificationTblList, tableColumn.getIndex());
		if (!SortHelper.isNumberSortedASC(tableValuesList.toArray(new String[tableValuesList.size()]))) {
			Log.error("Segment rank  present in emissions quantification table are not in sequential order");
			return false;
		}

		tableColumn = EmissionsQuantificationTableColumns.EmissionRate;
		tableValuesList = ArrayUtility.getColumnStringList(emissionsQuantificationTblList, tableColumn.getIndex());
		if (!SortHelper.isNumberSortedDESC(tableValuesList.toArray(new String[tableValuesList.size()]))) {
			Log.error("Segment emission ratse present in emissions quantification table are not in sequential(DESC) order");
			return false;
		}

		Log.info("Emissions Quantification table verification passed");
		return true;
	}
}
