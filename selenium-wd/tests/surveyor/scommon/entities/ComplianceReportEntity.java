package surveyor.scommon.entities;

import java.util.List;
import java.util.Map;

public class ComplianceReportEntity extends ReportCommonEntity {

	protected String lisaOpacity;

	public ComplianceReportEntity() {
		super();
	}

	public ComplianceReportEntity(String rptTitle, String strCreatedBy, String customer, String timeZone,
			String exclusionRadius, List<String> listBoundary, List<Map<String, String>> tablesList, String surveyorUnit,
			List<String> tagList, List<Map<String, String>> viewList, List<Map<String, String>> viewLayersList) {
		super(rptTitle, strCreatedBy, customer, timeZone, exclusionRadius, listBoundary, tablesList, surveyorUnit,
				tagList, viewList, viewLayersList);
	}

	public ComplianceReportEntity(String rptTitle, String strCreatedBy, String customer, String timeZone,
			String exclusionRadius, List<String> listBoundary, List<Map<String, String>> tablesList, String surveyorUnit,
			List<String> tagList, List<Map<String, String>> viewList) {
		super(rptTitle, strCreatedBy, customer, timeZone, exclusionRadius, listBoundary, tablesList, surveyorUnit,
				tagList, viewList);
	}

	public ComplianceReportEntity(String rptTitle, String strCreatedBy, String customer, String timeZone,
			String exclusionRadius, List<String> listBoundary, List<Map<String, String>> tablesList, String surveyorUnit,
			List<String> tagList, List<Map<String, String>> viewList, ReportModeFilter reportMode) {
		super(rptTitle, strCreatedBy, customer, timeZone, exclusionRadius, listBoundary, tablesList, surveyorUnit,
				tagList, viewList, reportMode);
	}

	public ComplianceReportEntity(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius,
			List<String> listBoundary, List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList,
			String startDate, String endDate, List<Map<String, String>> viewList, SurveyModeFilter surveyMode) {
		super(rptTitle, strCreatedBy, customer, timeZone, exclusionRadius, listBoundary, tablesList,
				surveyorUnit, tagList, startDate, endDate, viewList, surveyMode);
	}

	public ComplianceReportEntity(String rptTitle, String strCreatedBy, String customer, String timeZone,
			String exclusionRadius, String surveyorUnit, String userName, String startDate, String endDate,
			String fovOpacity, String lisaOpacity, Boolean geoFilter, ReportModeFilter reportMode,
			SurveyModeFilter surveyModeFilter, EthaneFilter ethaneFilter, List<String> listBoundary,
			List<String> tagList, List<Map<String, String>> tablesList, List<Map<String, String>> viewList,
			List<Map<String, String>> viewLayersList) {
		super(rptTitle, strCreatedBy, customer, timeZone, exclusionRadius, surveyorUnit, userName, startDate, endDate,
				fovOpacity, geoFilter, reportMode, surveyModeFilter, ethaneFilter, listBoundary,
				tagList, tablesList, viewList, viewLayersList);
		this.lisaOpacity=lisaOpacity;
	}

	public ComplianceReportEntity(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius,
			List<String> listBoundary, List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList,
			String startDate, String endDate, List<Map<String, String>> viewList, SurveyModeFilter surveyMode,
			ReportModeFilter reportMode) {
		super(rptTitle, strCreatedBy, customer, timeZone, exclusionRadius, listBoundary, tablesList, surveyorUnit, tagList,
				startDate, endDate, viewList, surveyMode, reportMode);
	}

	public ComplianceReportEntity(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius,
			List<String> listBoundary, List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList,
			String startDate, String endDate, List<Map<String, String>> viewList, SurveyModeFilter surveyMode, Boolean geoFilter) {
		super(rptTitle, strCreatedBy, customer, timeZone, exclusionRadius, listBoundary, tablesList, surveyorUnit, tagList,
				startDate, endDate, viewList, surveyMode, geoFilter);
	}

	public String getLisaOpacity() {
		return this.lisaOpacity;
	}

	public void setLisaOpacity(String lisaOpacity) {
		this.lisaOpacity = lisaOpacity;
	}
}