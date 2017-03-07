package surveyor.regression.source;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import surveyor.dataprovider.ComplianceReportEthaneDataProvider;
import surveyor.scommon.entities.BaseReportEntity.ReportModeFilter;
import surveyor.scommon.entities.BaseReportEntity.SearchAreaPreference;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;
import surveyor.scommon.entities.ReportCommonEntity.EthaneFilter;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.SurveyorTestRunner;

/**
 * Reports Data provider tests using LatLongSelectionControl.
 *
 */
@RunWith(SurveyorTestRunner.class)
public class LatLongSelectionReportsPageTest2 extends BaseComplianceReportPageProviderTest {

	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects();

		createTestCaseMap();
	}

	@Before
	public void beforeTest() {
		initializeTestObjects();

		initializePageObjects(new ComplianceReportsPage(getDriver(), getBaseURL(), getTestSetup()));
	}

	// Using data provider 2 test cases-generate reports with ethane checkbox selection using LatLongSelection control.
	// (TC1642, TC1737)

	@Test
	@UseDataProvider(value = ComplianceReportEthaneDataProvider.COMPLIANCE_ETHANE_REPORT_PROVIDER_02, location = ComplianceReportEthaneDataProvider.class)
	public void ComplianceReportTest_VerifyEthaneSTDRRReport(String index, String strCreatedBy, String password, String cutomer, String timeZone, String exclusionRadius, String surveyorUnit, String userName, String startDate, String endDate, String fovOpacity, String lisaOpacity, Boolean geoFilter, ReportModeFilter reportMode, SurveyModeFilter surveyModeFilter, EthaneFilter ethaneFilter, List<String> listBoundary, List<String> tagList, List<Map<String, String>> tablesList,
			List<Map<String, String>> viewList, List<Map<String, String>> viewLayersList, SearchAreaPreference srchAreaPref) throws Exception {
		executeVerifyEthaneReportTest(index, strCreatedBy, password, cutomer, timeZone,
				exclusionRadius, surveyorUnit, userName, startDate, endDate,
				fovOpacity, lisaOpacity, geoFilter, reportMode,
				surveyModeFilter, ethaneFilter, listBoundary, tagList,
				tablesList, viewList, viewLayersList, srchAreaPref);
	}
}