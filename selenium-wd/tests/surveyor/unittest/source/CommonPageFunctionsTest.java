package surveyor.unittest.source;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import surveyor.scommon.entities.ServerLogEntity;
import surveyor.scommon.source.BaseTest;
import surveyor.scommon.source.CommonPageFunctions;
import surveyor.scommon.source.SurveyorConstants;

public class CommonPageFunctionsTest {

	@BeforeClass
	public static void beforeTestClass() throws Exception {
		BaseTest.initializeTestObjects();
	}

	@Test
	public void testGetServerLog() {
		// NOTE: This unit test is using perf test data on P3Scale. To run on another environment using environment specific test data.
		final String searchTerm = "report id=EF92A895-3C5B-905E-8857-39DE246AB17E";
		final String expectedErrorMsg = SurveyorConstants.MORE_ASSETS_THAN_SUPPORTED_ERROR_MSG;
		final int numRecords = 10;
		List<ServerLogEntity> serverLog = CommonPageFunctions.getServerLog(searchTerm, numRecords);
		assertTrue(serverLog.stream()
			.allMatch(e -> e.getMessage().contains(expectedErrorMsg)));
	}
}