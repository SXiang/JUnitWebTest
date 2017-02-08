package surveyor.scommon.generators;

import surveyor.scommon.entities.CustomerSurveyInfoEntity;

public class CustomerSurveyInfoBuilder {
	public static CustomerSurveyInfoEntity customer_WithReportShapeAsssessment_NoLisaBox10() {
		// Customer (Report ShapeFile + Assessment permission, NO LISA Box 1.0 permission)
		final Integer customerRowID = 10;
		final Integer locationRowID = 11;
		final Integer userRowID = 21;

		final Integer analyzerRowID = 6;
		final Integer surveyorRowID = 7;
		final Integer refGasBottleRowID = 4;

		final Integer db3AnalyzerRowID = 57;
		final Integer surveyRowID = 30;
		final Integer surveyRuntimeInSeconds = 30;

		return new CustomerSurveyInfoEntity(customerRowID, locationRowID, userRowID, analyzerRowID, surveyorRowID, refGasBottleRowID,
				db3AnalyzerRowID, surveyRuntimeInSeconds, surveyRowID);
	}
}
