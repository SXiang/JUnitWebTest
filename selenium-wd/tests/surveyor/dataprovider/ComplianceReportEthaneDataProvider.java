package surveyor.dataprovider;

import static surveyor.scommon.source.SurveyorConstants.CUSDRVETHSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.CUSDRVETHRRTAG;
import static surveyor.scommon.source.SurveyorConstants.CUSDRVETHMNTAG;
import static surveyor.scommon.source.SurveyorConstants.ETHREXCLUSIONRADIUS;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;
import static surveyor.scommon.source.SurveyorConstants.SQAPICSUP;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORDHASH;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.entities.BaseReportEntity.ReportModeFilter;
import surveyor.scommon.entities.BaseReportEntity.SearchAreaPreference;
import surveyor.scommon.entities.ReportCommonEntity.EthaneFilter;

public class ComplianceReportEthaneDataProvider extends ReportDataProvider {
	public static final String COMPLIANCE_ETHANE_REPORT_PROVIDER_01 = "dataProviderEthaneComplianceReport01";
	public static final String COMPLIANCE_ETHANE_REPORT_PROVIDER_02 = "dataProviderEthaneComplianceReport02";
	public static final String COMPLIANCE_ETHANE_MANUAL_REPORT_PROVIDER = "dataProviderEthaneManualComplianceReport";

	public ComplianceReportEthaneDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
	}

	/**********************************************************************
	 * #NOTE#: Password provided in the data provider will get printed in teamcity UI (run result) and therefore needs to be an encrypted string Use the new CryptoUtility().encrypt() method to encrypt the
	 * password
	 * @throws Exception
	 **********************************************************************/

	@DataProvider
	public static Object[][] dataProviderEthaneComplianceReport01() throws Exception {

		SearchAreaPreference srchAreaPref = SearchAreaPreference.ASSETBOXES;
		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		viewList.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "0", "1", "1", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		tablesList.add(createOptionalTabularPDFContent("1", "1", "1", "1","1","0"));
		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

		List<String> tagList1 = new ArrayList<String>();
		tagList1.add(CUSDRVETHSTDTAG);
		List<String> tagList2 = new ArrayList<String>();
		tagList2.add(CUSDRVETHRRTAG);
		List<String> tagList3 = new ArrayList<String>();
		tagList3.add(CUSDRVETHMNTAG);

		return new Object[][] {
				{ "1"/*index*/, SQAPICSUP/*strCreatedBy*/, USERPASSWORDHASH/*password(encrypted)*/, "Picarro"/*customer*/, TIMEZONEPT/*timeZone*/, ETHREXCLUSIONRADIUS/*exclusionRadius*/, null/*surveyorUnit*/, null/*userName*/, null/*startDate*/,
					null/*endDate*/, null/*fovOpacity*/, null/*lisaOpacity*/, null/*geoFilter*/, ReportModeFilter.Standard/*reportMode*/, null/*surveyModeFilter*/, EthaneFilter.ExcludeVehicleExhaust/*ethaneFilter*/, createMapAndBoundaryList(false /*includeCustomBoundary*/)/*listBoundary*/, tagList1/*tagList*/, tablesList/*tablesList*/, viewList/*viewList*/, viewLayerList/*viewLayersList*/, srchAreaPref },
					{ "4", SQAPICSUP, USERPASSWORDHASH, "Picarro", TIMEZONEPT, ETHREXCLUSIONRADIUS, null, null, null, null, null, null, null, ReportModeFilter.Standard, null, EthaneFilter.None, createMapAndBoundaryList(false /*includeCustomBoundary*/), tagList1, tablesList, viewList, viewLayerList, srchAreaPref},
					{ "9", SQAPICSUP, USERPASSWORDHASH, "Picarro", TIMEZONEPT, ETHREXCLUSIONRADIUS, null, null, null, null, null, null, null, ReportModeFilter.RapidResponse, null, EthaneFilter.ExcludeVehicleExhaust, createMapAndBoundaryList(false /*includeCustomBoundary*/), tagList2, tablesList, viewList, viewLayerList, srchAreaPref},
					{ "10", SQAPICSUP, USERPASSWORDHASH, "Picarro", TIMEZONEPT, ETHREXCLUSIONRADIUS, null, null, null, null, null, null, null, ReportModeFilter.RapidResponse, null, EthaneFilter.ExcludeBiogenicMethane, createMapAndBoundaryList(false /*includeCustomBoundary*/), tagList2, tablesList, viewList, viewLayerList, srchAreaPref},
					{ "11", SQAPICSUP, USERPASSWORDHASH, "Picarro", TIMEZONEPT, ETHREXCLUSIONRADIUS, null, null, null, null, null, null, null, ReportModeFilter.RapidResponse, null, EthaneFilter.All, createMapAndBoundaryList(false /*includeCustomBoundary*/), tagList2, tablesList, viewList, viewLayerList, srchAreaPref},
					{ "12", SQAPICSUP, USERPASSWORDHASH, "Picarro", TIMEZONEPT, ETHREXCLUSIONRADIUS, null, null, null, null, null, null, null, ReportModeFilter.RapidResponse, null, EthaneFilter.None, createMapAndBoundaryList(false /*includeCustomBoundary*/), tagList2, tablesList, viewList, viewLayerList, srchAreaPref},
		};
	}

	@DataProvider
	public static Object[][] dataProviderEthaneComplianceReport02() throws Exception {

		SearchAreaPreference srchAreaPref = SearchAreaPreference.ASSETBOXES;
		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		viewList.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "0", "1", "1", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		tablesList.add(createOptionalTabularPDFContent("1", "1", "1", "1","1","0"));
		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

		List<String> tagList1 = new ArrayList<String>();
		tagList1.add(CUSDRVETHSTDTAG);
		List<String> tagList2 = new ArrayList<String>();
		tagList2.add(CUSDRVETHRRTAG);
		List<String> tagList3 = new ArrayList<String>();
		tagList3.add(CUSDRVETHMNTAG);

		return new Object[][] {
			{ "2", SQAPICSUP, USERPASSWORDHASH, "Picarro", TIMEZONEPT, ETHREXCLUSIONRADIUS, null, null, null, null, null, null, null, ReportModeFilter.Standard, null, EthaneFilter.ExcludeBiogenicMethane, createMapAndBoundaryList(false /*includeCustomBoundary*/), tagList1, tablesList, viewList, viewLayerList, srchAreaPref},
			{ "3", SQAPICSUP, USERPASSWORDHASH, "Picarro", TIMEZONEPT, ETHREXCLUSIONRADIUS, null, null, null, null, null, null, null, ReportModeFilter.Standard, null, EthaneFilter.All, createMapAndBoundaryList(false /*includeCustomBoundary*/), tagList1, tablesList, viewList, viewLayerList, srchAreaPref}
		};
	}

	/**********************************************************************
	 * #NOTE#: Password provided in the data provider will get printed in teamcity UI (run result) and therefore needs to be an encrypted string Use the CryptoUtility.encrypt() method to encrypt the
	 * password
	 * @throws Exception
	 **********************************************************************/

	@DataProvider
	public static Object[][] dataProviderEthaneManualComplianceReport() throws Exception {

		SearchAreaPreference srchAreaPref = SearchAreaPreference.ASSETBOXES;
		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		viewList.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "0", "1", "1", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		tablesList.add(createOptionalTabularPDFContent("1", "1", "1", "1","1","0"));
		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

		List<String> tagList3 = new ArrayList<String>();
		tagList3.add(CUSDRVETHMNTAG);

		return new Object[][] {
				{ "5"/*index*/, SQAPICSUP/*strCreatedBy*/, USERPASSWORDHASH/*password(encrypted)*/, "Picarro"/*customer*/, TIMEZONEPT/*timeZone*/, ETHREXCLUSIONRADIUS/*exclusionRadius*/, null/*surveyorUnit*/, null/*userName*/, null/*startDate*/,
					null/*endDate*/, null/*fovOpacity*/, null/*lisaOpacity*/, null/*geoFilter*/, ReportModeFilter.Manual/*reportMode*/, null/*surveyModeFilter*/, EthaneFilter.ExcludeVehicleExhaust/*ethaneFilter*/, createMapAndBoundaryList(false /*includeCustomBoundary*/)/*listBoundary*/, tagList3/*tagList*/, tablesList/*tablesList*/, viewList/*viewList*/, viewLayerList/*viewLayersList*/, srchAreaPref },
					{ "6", SQAPICSUP, USERPASSWORDHASH, "Picarro", TIMEZONEPT, ETHREXCLUSIONRADIUS, null, null, null, null, null, null, null, ReportModeFilter.Manual, null, EthaneFilter.ExcludeBiogenicMethane, createMapAndBoundaryList(false /*includeCustomBoundary*/), tagList3, tablesList, viewList, viewLayerList, srchAreaPref},
					{ "7", SQAPICSUP, USERPASSWORDHASH, "Picarro", TIMEZONEPT, ETHREXCLUSIONRADIUS, null, null, null, null, null, null, null, ReportModeFilter.Manual, null, EthaneFilter.All, createMapAndBoundaryList(false /*includeCustomBoundary*/), tagList3, tablesList, viewList, viewLayerList, srchAreaPref},
					{ "8", SQAPICSUP, USERPASSWORDHASH, "Picarro", TIMEZONEPT, ETHREXCLUSIONRADIUS, null, null, null, null, null, null, null, ReportModeFilter.Manual, null, EthaneFilter.None, createMapAndBoundaryList(false /*includeCustomBoundary*/), tagList3, tablesList, viewList, viewLayerList, srchAreaPref},
		};
	}
}