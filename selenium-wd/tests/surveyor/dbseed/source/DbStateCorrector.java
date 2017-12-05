package surveyor.dbseed.source;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import common.source.Log;

public class DbStateCorrector {
	private Connection connection = null;
	private List<TableName> tablesToCorrect = null;

	public enum TableName {
		CAPTUREEVENT ("CaptureEvent"),
		FIELDOFVIEW ("FieldOfView"),
		PEAK ("Peak"),
		SEGMENT ("Segment"),
		SURVEYRESULT ("SurveyResult");

		private final String name;

		TableName(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public static DbStateCorrector newInstance(Connection connection) {
		DbStateCorrector corrector = new DbStateCorrector();
		corrector.connection = connection;
		corrector.tablesToCorrect = new ArrayList<>();
		return corrector;
	}

	public Map<TableName, String> buildTableCleanupMap(String surveyId, String analyzerId) {
		Map<TableName, String> map = new HashMap<>();
		map.put(TableName.CAPTUREEVENT, String.format("DELETE FROM dbo.[CaptureEvent] WHERE SurveyId='%s' AND AnalyzerId='%s'", surveyId, analyzerId));
		map.put(TableName.FIELDOFVIEW, String.format("DELETE FROM dbo.[FieldOfView] WHERE SurveyId='%s' AND AnalyzerId='%s'", surveyId, analyzerId));
		map.put(TableName.PEAK, String.format("DELETE FROM dbo.[Peak] WHERE SurveyId='%s' AND AnalyzerId='%s'", surveyId, analyzerId));
		map.put(TableName.SEGMENT, String.format("DELETE FROM dbo.[Segment] WHERE SurveyId='%s'", surveyId));
		map.put(TableName.SURVEYRESULT, String.format("DELETE FROM dbo.[SurveyResult] WHERE SurveyId='%s'", surveyId));
		return map;
	}

	public DbStateCorrector append(TableName table) {
		if (!tablesToCorrect.contains(table)) {
			tablesToCorrect.add(table);
		}

		return this;
	}

	public void correctSurveySeedData(String surveyFileTag, String surveyId, String analyzerId) throws Exception {
		Log.method("correctSurveySeedData", surveyFileTag, surveyId, analyzerId);
		Map<TableName, String> tableCleanupMap = buildTableCleanupMap(surveyId, analyzerId);
		List<String> cleanupStatements = tablesToCorrect.stream()
			.map(tbl -> tableCleanupMap.get(tbl))
			.collect(Collectors.toList());
		executeCleanup(cleanupStatements);
		String[] surveysToFix = { surveyFileTag };
		DbSeedExecutor.executeSurveyDataSeed(surveysToFix);
	}

	public void executeCleanup(List<String> cleanupStatements) throws SQLException {
        try (Statement stmt = this.connection.createStatement())
        {
        	for (String cleanupStmt : cleanupStatements) {
        		Log.info(String.format("Cleanup Statement -> %s", cleanupStmt));
                stmt.executeUpdate(cleanupStmt);
			}
        }
	}
}