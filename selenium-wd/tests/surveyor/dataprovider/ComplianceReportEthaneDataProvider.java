package surveyor.dataprovider;

import static surveyor.scommon.source.SurveyorConstants.CUSDRVETHSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.CUSDRVETHRRTAG;
import static surveyor.scommon.source.SurveyorConstants.CUSDRVETHMNTAG;
import static surveyor.scommon.source.SurveyorConstants.ETHREXCLUSIONRADIUS;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPHEIGHT;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPWIDTH;
import static surveyor.scommon.source.SurveyorConstants.KEYANNOTATION;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETCASTIRON;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETCOPPER;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETOTHERPLASTIC;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETPEPLASTIC;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETPROTECTEDSTEEL;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETUNPROTECTEDSTEEL;
import static surveyor.scommon.source.SurveyorConstants.KEYBASEMAP;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARIES;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARYDISTRICT;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARYDISTRICTPLAT;
import static surveyor.scommon.source.SurveyorConstants.KEYBREADCRUMB;
import static surveyor.scommon.source.SurveyorConstants.KEYFOV;
import static surveyor.scommon.source.SurveyorConstants.KEYGAPS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDICATIONS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDTB;
import static surveyor.scommon.source.SurveyorConstants.KEYISOANA;
import static surveyor.scommon.source.SurveyorConstants.KEYISOTOPICCAPTURE;
import static surveyor.scommon.source.SurveyorConstants.KEYLISA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCRA;
import static surveyor.scommon.source.SurveyorConstants.KEYVIEWNAME;
import static surveyor.scommon.source.SurveyorConstants.PICADMNMANTAG;
import static surveyor.scommon.source.SurveyorConstants.ETHRNELAT;
import static surveyor.scommon.source.SurveyorConstants.ETHRNELON;
import static surveyor.scommon.source.SurveyorConstants.RSURENDDATE;
import static surveyor.scommon.source.SurveyorConstants.RSURSTARTDATE;
import static surveyor.scommon.source.SurveyorConstants.ETHRSWLAT;
import static surveyor.scommon.source.SurveyorConstants.ETHRSWLON;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDRTAG;
import static surveyor.scommon.source.SurveyorConstants.SQACUSRRTAG;
import static surveyor.scommon.source.SurveyorConstants.SQACUSMNTAG;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSU;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPTUA;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;
import static surveyor.scommon.source.SurveyorConstants.SQAPICSUP;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSURVEYOR;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONECTUA;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEET;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEETUA;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEMT;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEMTUA;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.Reports.EthaneFilter;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SurveyModeFilter;

public class ComplianceReportEthaneDataProvider extends SurveyorTestRunner {
	public static final String COMPLIANCE_ETHANE_REPORT_PROVIDER = "dataProviderEthaneComplianceReport";

	public ComplianceReportEthaneDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
	}

	/**********************************************************************
	 * #NOTE#: Password provided in the data provider will get printed in teamcity UI (run result) and therefore needs to be an encrypted string Use the CryptoUtility.encrypt() method to encrypt the
	 * password
	 **********************************************************************/

	@DataProvider
	public static Object[][] dataProviderEthaneComplianceReport() {

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		viewList.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "0", "1", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		tablesList.add(createOptionalTable("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"));
		List<String> tagList1 = new ArrayList<String>();
		tagList1.add(CUSDRVETHSTDTAG);
		List<String> tagList2 = new ArrayList<String>();
		tagList2.add(CUSDRVETHRRTAG);
		List<String> tagList3 = new ArrayList<String>();
		tagList3.add(CUSDRVETHMNTAG);



		return new Object[][] {				
				{ "1"/*index*/, SQAPICSUP/*strCreatedBy*/, "oeHwHqmv621dZ1MRE2BSdw=="/*password(encrypted)*/, "Picarro"/*customer*/, TIMEZONEPTUA/*timeZone*/, ETHREXCLUSIONRADIUS/*exclusionRadius*/, null/*surveyorUnit*/, null/*userName*/, null/*startDate*/,
					null/*endDate*/, null/*fovOpacity*/, null/*lisaOpacity*/, null/*geoFilter*/, ReportModeFilter.Standard/*reportMode*/, null/*surveyModeFilter*/, EthaneFilter.ExcludeVehicleExhaust/*ethaneFilter*/, createBoundaryList()/*listBoundary*/, tagList1/*tagList*/, tablesList/*tablesList*/, viewList/*viewList*/, null/*viewLayersList*/ },
				{ "2", SQAPICSUP, "oeHwHqmv621dZ1MRE2BSdw==", "Picarro", TIMEZONEPTUA, ETHREXCLUSIONRADIUS, null, null, null, null, null, null, null, ReportModeFilter.Standard, null, EthaneFilter.ExcludeBiogenicMethane, createBoundaryList(), tagList1, tablesList, viewList, null},
				{ "3", SQAPICSUP, "oeHwHqmv621dZ1MRE2BSdw==", "Picarro", TIMEZONEPTUA, ETHREXCLUSIONRADIUS, null, null, null, null, null, null, null, ReportModeFilter.Standard, null, EthaneFilter.Both, createBoundaryList(), tagList1, tablesList, viewList, null},
				{ "4", SQAPICSUP, "oeHwHqmv621dZ1MRE2BSdw==", "Picarro", TIMEZONEPTUA, ETHREXCLUSIONRADIUS, null, null, null, null, null, null, null, ReportModeFilter.Standard, null, EthaneFilter.None, createBoundaryList(), tagList1, tablesList, viewList, null},
				{ "5", SQAPICSUP, "oeHwHqmv621dZ1MRE2BSdw==", "Picarro", TIMEZONEPTUA, ETHREXCLUSIONRADIUS, null, null, null, null, null, null, null, ReportModeFilter.Manual, null, EthaneFilter.ExcludeVehicleExhaust, createBoundaryList(), tagList3, tablesList, viewList, null},
				{ "6", SQAPICSUP, "oeHwHqmv621dZ1MRE2BSdw==", "Picarro", TIMEZONEPTUA, ETHREXCLUSIONRADIUS, null, null, null, null, null, null, null, ReportModeFilter.Manual, null, EthaneFilter.ExcludeBiogenicMethane, createBoundaryList(), tagList3, tablesList, viewList, null},
				{ "7", SQAPICSUP, "oeHwHqmv621dZ1MRE2BSdw==", "Picarro", TIMEZONEPTUA, ETHREXCLUSIONRADIUS, null, null, null, null, null, null, null, ReportModeFilter.Manual, null, EthaneFilter.Both, createBoundaryList(), tagList3, tablesList, viewList, null},
				{ "8", SQAPICSUP, "oeHwHqmv621dZ1MRE2BSdw==", "Picarro", TIMEZONEPTUA, ETHREXCLUSIONRADIUS, null, null, null, null, null, null, null, ReportModeFilter.Manual, null, EthaneFilter.None, createBoundaryList(), tagList3, tablesList, viewList, null},
				{ "9", SQAPICSUP, "oeHwHqmv621dZ1MRE2BSdw==", "Picarro", TIMEZONEPTUA, ETHREXCLUSIONRADIUS, null, null, null, null, null, null, null, ReportModeFilter.RapidResponse, null, EthaneFilter.ExcludeVehicleExhaust, createBoundaryList(), tagList2, tablesList, viewList, null},
				{ "10", SQAPICSUP, "oeHwHqmv621dZ1MRE2BSdw==", "Picarro", TIMEZONEPTUA, ETHREXCLUSIONRADIUS, null, null, null, null, null, null, null, ReportModeFilter.RapidResponse, null, EthaneFilter.ExcludeBiogenicMethane, createBoundaryList(), tagList2, tablesList, viewList, null},
				{ "11", SQAPICSUP, "oeHwHqmv621dZ1MRE2BSdw==", "Picarro", TIMEZONEPTUA, ETHREXCLUSIONRADIUS, null, null, null, null, null, null, null, ReportModeFilter.RapidResponse, null, EthaneFilter.Both, createBoundaryList(), tagList2, tablesList, viewList, null},
				{ "12", SQAPICSUP, "oeHwHqmv621dZ1MRE2BSdw==", "Picarro", TIMEZONEPTUA, ETHREXCLUSIONRADIUS, null, null, null, null, null, null, null, ReportModeFilter.RapidResponse, null, EthaneFilter.None, createBoundaryList(), tagList2, tablesList, viewList, null},
																
		};

	}

	private static HashMap<String, String> createViewsMapTable(String viewName, String lisa, String fov, String breadcrumb, String indications, String isotopic, String annotation, String gap, String asset, String boundary, String map) {
		HashMap<String, String> viewMap = new HashMap<String, String>();
		viewMap.put(KEYVIEWNAME, viewName);
		viewMap.put(KEYLISA, lisa);
		viewMap.put(KEYFOV, fov);
		viewMap.put(KEYBREADCRUMB, breadcrumb);
		viewMap.put(KEYINDICATIONS, indications);
		viewMap.put(KEYISOTOPICCAPTURE, isotopic);
		viewMap.put(KEYANNOTATION, annotation);
		viewMap.put(KEYGAPS, gap);
		viewMap.put(KEYASSETS, asset);
		viewMap.put(KEYBOUNDARIES, boundary);
		viewMap.put(KEYBASEMAP, map);
		return viewMap;
	}

	private static List<String> createBoundaryList() {
		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add(ETHRNELAT);
		listBoundary.add(ETHRNELON);
		listBoundary.add(ETHRSWLAT);
		listBoundary.add(ETHRSWLON);
		return listBoundary;
	}

	private static HashMap<String, String> createOptionalTable(String indication, String isotopic, String pca, String pcra, String iron, String copper, String otherPlastic, String plastic, String protectedSteel, String unprotecetdSteel, String district, String districtPlat) {
		HashMap<String, String> tableMap = new HashMap<String, String>();
		tableMap.put(KEYINDTB, indication);
		tableMap.put(KEYISOANA, isotopic);
		tableMap.put(KEYPCA, pca);
		tableMap.put(KEYPCRA, pcra);
		tableMap.put(KEYASSETCASTIRON, iron);
		tableMap.put(KEYASSETCOPPER, copper);
		tableMap.put(KEYASSETOTHERPLASTIC, otherPlastic);
		tableMap.put(KEYASSETPEPLASTIC, plastic);
		tableMap.put(KEYASSETPROTECTEDSTEEL, protectedSteel);
		tableMap.put(KEYASSETUNPROTECTEDSTEEL, unprotecetdSteel);
		tableMap.put(KEYBOUNDARYDISTRICT, district);
		tableMap.put(KEYBOUNDARYDISTRICTPLAT, districtPlat);
		return tableMap;
	}

	

}