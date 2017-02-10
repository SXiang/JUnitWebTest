package surveyor.scommon.generators;

import surveyor.scommon.entities.CustomerSurveyInfoEntity;

public class CustomerSurveyInfoBuilder {
	public static CustomerSurveyInfoEntity customer_WithReportShapeAsssessment_NoLisaBox10_NewMethaneAnalyzerStndSurvey() {
		// Customer (Report ShapeFile + Assessment permission, NO LISA Box 1.0 permission)
		final Integer customerRowID = 10;
		final Integer locationRowID = 11;
		final Integer userRowID = 21;

		final Integer analyzerRowID = 6;
		final Integer surveyorRowID = 7;
		final Integer refGasBottleRowID = 4;

		final Integer db3AnalyzerRowID = 31;       // New Analyzer from pool.
		final Integer surveyRowID = 30;            // NOTE: To create a different type of survey, SurveyRowID can be updated in builder.
		final Integer surveyRuntimeInSeconds = 20;

		return new CustomerSurveyInfoEntity(customerRowID, locationRowID, userRowID, analyzerRowID, surveyorRowID, refGasBottleRowID,
				db3AnalyzerRowID, surveyRuntimeInSeconds, surveyRowID);
	}

	public static CustomerSurveyInfoEntity customer_WithReportShapeAsssessment_NoLisaBox10_MethaneSurveyTC1434() {
		CustomerSurveyInfoEntity surveyInfoEntity = CustomerSurveyInfoBuilder.customer_WithReportShapeAsssessment_NoLisaBox10_NewMethaneAnalyzerStndSurvey();
		surveyInfoEntity.setSurveyRowID(57);
		return surveyInfoEntity;
	}

	public static CustomerSurveyInfoEntity customer_WithReportShapeAsssessment_NoLisaBox10_MethaneSurveyTC1435() {
		CustomerSurveyInfoEntity surveyInfoEntity = CustomerSurveyInfoBuilder.customer_WithReportShapeAsssessment_NoLisaBox10_NewMethaneAnalyzerStndSurvey();
		surveyInfoEntity.setSurveyRowID(58);
		return surveyInfoEntity;
	}

	public static CustomerSurveyInfoEntity customer_WithReportShapeAsssessment_NoLisaBox10_MethaneSurveyTC1588() {
		CustomerSurveyInfoEntity surveyInfoEntity = CustomerSurveyInfoBuilder.customer_WithReportShapeAsssessment_NoLisaBox10_NewMethaneAnalyzerStndSurvey();
		surveyInfoEntity.setSurveyRowID(59);
		return surveyInfoEntity;
	}

	public static CustomerSurveyInfoEntity customer_WithReportShapeAsssessment_NoLisaBox10_MethaneSurveyTC1594() {
		CustomerSurveyInfoEntity surveyInfoEntity = CustomerSurveyInfoBuilder.customer_WithReportShapeAsssessment_NoLisaBox10_NewMethaneAnalyzerStndSurvey();
		surveyInfoEntity.setSurveyRowID(60);
		return surveyInfoEntity;
	}
}
