package surveyor.scommon.entities;

import java.util.List;
import java.util.Map;

public class AssessmentReportEntity extends ReportCommonEntity {

	protected String lisaOpacity;

	public AssessmentReportEntity() {
		super();
	}

	public AssessmentReportEntity(String rptTitle, String loggedInUser, String customer, String timeZone,
			String exclusionRadius, List<String> listBoundary, List<Map<String, String>> tablesList, String surveyorUnit,
			List<String> tagsList, List<Map<String, String>> viewList, List<Map<String, String>> viewLayersList) {
		super(rptTitle, loggedInUser, customer, timeZone,exclusionRadius, listBoundary, tablesList, surveyorUnit,
				tagsList, viewList, viewLayersList);
	}
}