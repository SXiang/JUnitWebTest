/**
 * 
 */
package surveyor.scommon.source;

import java.util.List;
import java.util.Map;

/**
 * @author zlu
 *
 */
public class ReportsCompliance extends Reports {
	// This is mapping the compliance report object
	// It is ongoing and more details will be added when needed

	// temporary leave some fields here and will be moved to base class later

	// private Map<String, String> viewHashMap;

	/**
	 * 
	 */
	public ReportsCompliance(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary, List<Map<String, String>> tablesList, String surveyorUnit, String tag, List<Map<String, String>> viewList) {
		super(rptTitle, strCreatedBy, customer, timeZone, exclusionRadius, listBoundary, tablesList, surveyorUnit, tag, viewList);
	}

	public ReportsCompliance(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary, List<Map<String, String>> tablesList, String surveyorUnit, String tag, String startDate, String endDate, List<Map<String, String>> viewList, String surveyMode) {
		super(rptTitle, strCreatedBy, customer, timeZone, exclusionRadius, listBoundary, tablesList, surveyorUnit, tag, startDate, endDate, viewList, surveyMode);
	}

	public ReportsCompliance(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary, List<Map<String, String>> tablesList, String surveyorUnit, String tag, String startDate, String endDate, List<Map<String, String>> viewList, String surveyMode, String reportMode) {
		super(rptTitle, strCreatedBy, customer, timeZone, exclusionRadius, listBoundary, tablesList, surveyorUnit, tag, startDate, endDate, viewList, surveyMode, reportMode);
	}

	public ReportsCompliance(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary, List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList, List<Map<String, String>> viewList) {
		super(rptTitle, strCreatedBy, customer, timeZone, exclusionRadius, listBoundary, tablesList, surveyorUnit, tagList, viewList);

	}
	
	public ReportsCompliance(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary, List<Map<String, String>> tablesList,
			String surveyorUnit, String tag, List<Map<String, String>> viewList, List<Map<String, String>> viewLayersList) {
		super(rptTitle, strCreatedBy, customer, timeZone, exclusionRadius, listBoundary, tablesList, surveyorUnit, tag, viewList, viewLayersList);
	}

	

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
