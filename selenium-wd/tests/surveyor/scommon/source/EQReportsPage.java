package surveyor.scommon.source;

import static surveyor.scommon.source.SurveyorConstants.CUSTOMER_PICARRO;
import static surveyor.scommon.source.SurveyorConstants.LINE_SELECTOR_ZOOMLEVEL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.entities.EQReportEntity;
import surveyor.scommon.entities.ReportCommonEntity;
import surveyor.scommon.entities.BaseReportEntity;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;
import surveyor.scommon.source.LatLongSelectionControl.ControlMode;
import common.source.Log;
import common.source.TestSetup;

/**
 * Assessment Reports Page model
 */
public class EQReportsPage extends ReportsCommonPage {

	public static final String STRURLPath = "/Reports/EQReports";
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.EQReports_PageTitle);
	public static final String STRNewPageContentText = Resources.getResource(ResourceKeys.EQReports_AddNew);
	public static final String STRCopyPageTitle = Resources.getResource(ResourceKeys.EQReport_PageTitle);

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

	protected void clickLineSegmentsSelectorBtn() {
		Log.clickElementInfo("Line Segments Selector");
		this.lineSegmentsSelectorBtn.click();
	}
	
	protected void selectLineSegments(List<List<Coordinates>> lineSegments) {
			clickLineSegmentsSelectorBtn();
			latLongSelectionControl.waitForModalDialogOpen();
			latLongSelectionControl.switchMode(ControlMode.MapInteraction);
			latLongSelectionControl.waitForMapImageLoad();
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
}
