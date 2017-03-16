package surveyor.scommon.source;

import static surveyor.scommon.source.SurveyorConstants.KEYPCA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCRA;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.openqa.selenium.WebDriver;

import common.source.ApiUtility;
import common.source.Log;
import common.source.TestSetup;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.entities.AssessmentReportEntity;
import surveyor.scommon.entities.BaseReportEntity;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;

/**
 * Assessment Reports Page model
 */
public class AssessmentReportsPage extends ReportsCommonPage {

	public static final String STRURLPath = "/Reports/AssessmentReports";
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.AssessmentReports_PageTitle);
	public static final String STRNewPageContentText = Resources.getResource(ResourceKeys.AssessmentReports_AddNew);
	public static final String STRCopyPageTitle = Resources.getResource(ResourceKeys.AssessmentReport_PageTitle);

	/**
	 * @param driver
	 * @param strBaseURL
	 * @param testSetup
	 */
	public AssessmentReportsPage(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup, STRURLPath, () -> getCommonResourceProvider());

		Log.info(String.format("\nThe Assessment Reports Page URL is: %s\n", this.strPageURL));
	}

	public String getFullReportName(String rptTitle) {
		return "AS" + "-" + getReportName(rptTitle);
	}

	@Override
	public void fillReportSpecific(BaseReportEntity reports) throws Exception {
		AssessmentReportEntity reportsCompliance = (AssessmentReportEntity) reports;

		// 1. Report general
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

		if (tablesList.get(0).get(KEYPCA).equalsIgnoreCase("1")) {
			selectPercentCoverageAssetCheckBox();
		}
		if (tablesList.get(0).get(KEYPCRA).equalsIgnoreCase("1")) {
			selectPercentCoverageReportArea();
		}

		// 5. Optional View layers
		List<Map<String, String>> viewLayersList = reportsCompliance.getViewLayersList();
		if (viewLayersList != null && viewLayersList.size() > 0) {
			handleOptionalDynamicViewLayersSection(viewLayersList);
		}
	}

	@Override
	public String getReportPrefix() {
		return "AS";
	}

	@Override
	protected void handleExtraAddSurveyInfoParameters(BaseReportEntity reports) {
		// No action in Assessment reports page.
	}

	@Override
	protected void handleExtraAddSurveyInfoParameters(SurveyModeFilter surveyModeFilter) {
		// No action in Assessment reports page.
	}

	@Override
	public void deleteReportWithApiCall(String reportId) {
		Log.method("deleteReportWithApiCall", reportId);
		ApiUtility.getApiResponse(String.format(ApiUtility.DELETE_ASSESSMENT_REPORTS_RELATIVE_URL, reportId));
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
