package surveyor.scommon.source;

import static surveyor.scommon.source.SurveyorConstants.CUSTOMER_PICARRO;
import static surveyor.scommon.source.SurveyorConstants.SHAPE_SELECTOR_ZOOMLEVEL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.entities.FacilityEQReportEntity;
import surveyor.scommon.entities.ReportCommonEntity;
import surveyor.scommon.entities.BaseReportEntity;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;
import surveyor.scommon.source.LatLongSelectionControl.ControlMode;
import common.source.Constants;
import common.source.Log;
import common.source.RetryUtil;
import common.source.TestSetup;
import common.source.WebElementExtender;

/**
 * FacilityEQ Reports Page model
 */
public class FacilityEQReportsPage extends ReportsCommonPage {

	public static final String STRURLPath = "/Reports/FacilityEQReports";
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.FacilityEQReports_PageTitle);
	public static final String STRNewPageContentText = Resources.getResource(ResourceKeys.FacilityEQReports_AddNew);
	public static final String STRCopyPageTitle = Resources.getResource(ResourceKeys.FacilityEQReport_PageTitle);

	@FindBy(id = "report-locationID")
	protected WebElement feqLocationSelector;

	@FindBy(id = "btn-facility-EQ-select-area")
	protected WebElement shapeSelectorBtn;

	@FindBy(id = "report-show-lisas")
	protected WebElement checkBoxShowLisas;

	@FindBy(how = How.XPATH, using = "//a[starts-with(@href,'/Reports/DeleteReport?reportType=FacilityEQReports')]")
	protected WebElement btnDeleteConfirm;
	protected String btnDeleteConfirmXpath = "//a[starts-with(@href,'/Reports/DeleteReport?reportType=FacilityEQReports')]";

	/**
	 * @param driver
	 * @param strBaseURL
	 * @param testSetup
	 */
	public FacilityEQReportsPage(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup, STRURLPath, () -> getCommonResourceProvider());

		Log.info(String.format("\nThe FacilityEQ Reports Page URL is: %s\n", this.strPageURL));
	}

	public String getFullReportName(String rptTitle) {
		return getReportPrefix() + "-" + getReportName(rptTitle);
	}

	@Override
	public WebElement getBtnDeleteConfirm() {
		return btnDeleteConfirm;
	}

	@Override
	public String getBtnDeleteConfirmXpath() {
		return btnDeleteConfirmXpath;
	}

	@Override
	public void fillReportSpecific(BaseReportEntity reports) {
		FacilityEQReportEntity reportsFacilityEQ = (FacilityEQReportEntity) reports;

		// 1. Change customer if specified.
		if (reportsFacilityEQ.getCustomer() != null && !reportsFacilityEQ.getCustomer().equalsIgnoreCase(CUSTOMER_PICARRO)) {
			Log.info("Select customer '"+reports.getCustomer()+"'");
			selectCustomer(reportsFacilityEQ.getCustomer());
			Boolean confirmed = getChangeCustomerDialog().confirmInChangeCustomerDialog();
			if (confirmed) {
				inputReportTitle(reportsFacilityEQ.getRptTitle());
			}
		}

		// 2. FacilityEQ Location Parameter
		if (!reportsFacilityEQ.getFacilityEQLocationParameter().isEmpty()) {
			selectFacilityEQLocationParameter(reportsFacilityEQ.getFacilityEQLocationParameter());
		}

		// 3. Shape Selector
		List<Coordinates> shapeCoordinates = reportsFacilityEQ.getShapeCoordinates();
		selectShape(shapeCoordinates);

		// 4. boolean showLisas
		boolean showLisas = reportsFacilityEQ.isShowLisas();
		selectShowLisasInvestigationMarker(showLisas);
	}

	@Override
	public String getReportPrefix() {
		return "FEQ";
	}

	protected void selectShowLisasInvestigationMarker(Boolean showLisas) {
		if (showLisas != null) {
			if (showLisas) {
				if (!checkBoxShowLisas.isSelected()) {
					Log.info(String.format("Click to select Show LISA Investigation Marker"));
					jsClick(checkBoxShowLisas);
				}
			} else {
				if (checkBoxShowLisas.isSelected()) {
					Log.info(String.format("Click to unselect Show LISA Investigation Marker"));
					jsClick(checkBoxShowLisas);
				}
			}
		}
	}

	protected void selectFacilityEQLocationParameter(String feqLocationParameter) {
		selectDropdownOptionByText(feqLocationSelector, feqLocationParameter);
	}

	protected void openShapeSelector() {
		Log.clickElementInfo("Open Line Segments Selector");
		// Try few times before failure
		boolean actionSuccess = RetryUtil.retryOnException(
				() -> { return clickShapeSelector();},
				() -> { return true; },
				Constants.THOUSAND_MSEC_WAIT_BETWEEN_RETRIES,
				Constants.DEFAULT_MAX_RETRIES, true /*takeScreenshotOnFailure*/);
		if (!actionSuccess) {
			Log.error(String.format("clickLineSegmentsSelector() executed %d times and resulted in exception.", Constants.DEFAULT_MAX_RETRIES));
		}
	}

	protected boolean clickShapeSelector() {
		Log.clickElementInfo("Click Shape Selector");
		this.shapeSelectorBtn.click();
		return WebElementExtender.verifyElementIsDisplayed(driver, latLongSelectionControl.getOkButton(), timeout);
	}

	protected void selectShape(List<Coordinates> shapeCoordinates) {
			if(shapeCoordinates.isEmpty())
				return;
			openShapeSelector();
			latLongSelectionControl.waitForModalDialogOpen();
			latLongSelectionControl.switchMode(ControlMode.MapInteraction);
			latLongSelectionControl.waitForMapImageLoad();
			waitForAJAXCallsToComplete();
			mapViewPage.setZoomLevel(SHAPE_SELECTOR_ZOOMLEVEL);
			latLongSelectionControl.selectCoordinates(shapeCoordinates);

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
		String elementXPath = "//*[@id='btn-facility-EQ-select-area']";
		refreshPageUntilElementFound(elementXPath);
	}
}
