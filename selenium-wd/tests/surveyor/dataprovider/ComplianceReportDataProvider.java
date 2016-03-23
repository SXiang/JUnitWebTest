package surveyor.dataprovider;

import static surveyor.scommon.source.SurveyorConstants.CUSDRVSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.EXCLUSIONRADIUS;
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
import static surveyor.scommon.source.SurveyorConstants.RNELAT;
import static surveyor.scommon.source.SurveyorConstants.RNELON;
import static surveyor.scommon.source.SurveyorConstants.RSURENDDATE;
import static surveyor.scommon.source.SurveyorConstants.RSURSTARTDATE;
import static surveyor.scommon.source.SurveyorConstants.RSWLAT;
import static surveyor.scommon.source.SurveyorConstants.RSWLON;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDRTAG;
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

public class ComplianceReportDataProvider extends ReportDataProvider {
	public static final String COMPLIANCE_REPORT_PROVIDER = "dataProviderComplianceReport";

	public ComplianceReportDataProvider(Class<?> klass) throws InitializationError {
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
	public static Object[][] dataProviderComplianceReport() {

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList1.add(createViewsMapTable("Second View", "1", "1", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList1.add(createViewsMapTable("Third View", "0", "0", "1", "1", "1", "1", "0", "0", "0", "None"));
		List<Map<String, String>> tablesList1 = new ArrayList<Map<String, String>>();
		tablesList1.add(createOptionalTable("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"));
		List<String> tagList1 = new ArrayList<String>();
		tagList1.add(SQACUSDRTAG);

		List<Map<String, String>> viewList2 = new ArrayList<Map<String, String>>();
		viewList2.add(createViewsMapTable("First View", "0", "0", "0", "1", "0", "1", "0", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList2.add(createViewsMapTable("Second View", "1", "1", "0", "1", "1", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList2 = new ArrayList<Map<String, String>>();
		tablesList2.add(createOptionalTable("1", "1", "0", "0", "1", "1", "1", "1", "1", "1", "0", "0"));
		List<String> tagList2 = new ArrayList<String>();
		tagList2.add(PICADMNSTDTAG);

		List<Map<String, String>> viewList3 = new ArrayList<Map<String, String>>();
		viewList3.add(createViewsMapTable("First View", "0", "0", "0", "1", "0", "1", "0", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList3 = new ArrayList<Map<String, String>>();
		tablesList3.add(createOptionalTable("1", "1", "0", "0", "1", "1", "1", "1", "1", "1", "0", "0"));
		List<String> tagList3 = new ArrayList<String>();
		tagList3.add(PICADMNSTDTAG);

		List<Map<String, String>> viewList4 = new ArrayList<Map<String, String>>();
		viewList4.add(createViewsMapTable("First View", "0", "0", "0", "1", "1", "1", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList4 = new ArrayList<Map<String, String>>();
		tablesList4.add(createOptionalTable("1", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"));
		List<String> tagList4 = new ArrayList<String>();
		tagList4.add(PICADMNSTDTAG);
		
		List<Map<String, String>> viewList5 = new ArrayList<Map<String, String>>();
		viewList5.add(createViewsMapTable("First View", "0", "0", "0", "1", "1", "1", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList5.add(createViewsMapTable("Second View", "1", "1", "0", "1", "1", "0", "0", "1", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList5 = new ArrayList<Map<String, String>>();
		tablesList5.add(createOptionalTable("1", "1", "0", "0", "1", "1", "1", "1", "1", "1", "0", "0"));
		List<String> tagList5 = new ArrayList<String>();
		tagList5.add(PICADMNSTDTAG);
		
		List<Map<String, String>> viewList6 = new ArrayList<Map<String, String>>();
		viewList6.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList6 = new ArrayList<Map<String, String>>();
		tablesList6.add(createOptionalTable("0", "0", "1", "0", "1", "1", "1", "1", "1", "1", "0", "0"));
		List<String> tagList6 = new ArrayList<String>();
		tagList6.add(PICADMNSTDTAG);
		
		List<Map<String, String>> viewList7 = new ArrayList<Map<String, String>>();
		viewList7.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList7 = new ArrayList<Map<String, String>>();
		tablesList7.add(createOptionalTable("1", "1", "0", "0", "1", "1", "1", "1", "1", "1", "0", "0"));
		List<String> tagList7 = new ArrayList<String>();
		tagList7.add(PICADMNSTDTAG);
		
		List<Map<String, String>> viewList8 = new ArrayList<Map<String, String>>();
		viewList8.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList8 = new ArrayList<Map<String, String>>();
		tablesList8.add(createOptionalTable("1", "1", "0", "0", "1", "1", "1", "1", "1", "1", "0", "0"));
		List<String> tagList8 = new ArrayList<String>();
		tagList8.add(PICADMNSTDTAG);
		
		List<Map<String, String>> viewList9 = new ArrayList<Map<String, String>>();
		viewList9.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList9 = new ArrayList<Map<String, String>>();
		tablesList9.add(createOptionalTable("0", "0", "0", "0", "1", "1", "1", "1", "1", "1", "0", "0"));
		List<String> tagList9 = new ArrayList<String>();
		tagList9.add(PICADMNSTDTAG);
		
		List<Map<String, String>> viewList10 = new ArrayList<Map<String, String>>();
		viewList10.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList10 = new ArrayList<Map<String, String>>();
		tablesList10.add(createOptionalTable("1", "1", "0", "0", "1", "1", "1", "1", "1", "1", "0", "0"));
		List<String> tagList10 = new ArrayList<String>();
		tagList10.add(PICADMNMANTAG);
		
		List<Map<String, String>> viewList11 = new ArrayList<Map<String, String>>();
		viewList11.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList11 = new ArrayList<Map<String, String>>();
		tablesList11.add(createOptionalTable("1", "1", "0", "0", "1", "1", "1", "1", "1", "1", "0", "0"));
		List<String> tagList11 = new ArrayList<String>();
		tagList11.add(CUSDRVSTDTAG);
		
		List<Map<String, String>> viewList12 = new ArrayList<Map<String, String>>();
		viewList12.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList12.add(createViewsMapTable("Second View", "0", "0", "1", "1", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList12.add(createViewsMapTable("Third View", "1", "1", "0", "0", "1", "0", "0", "0", "0", "None"));
		viewList12.add(createViewsMapTable("Fourth View", "0", "1", "0", "0", "0", "0", "1", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList12 = new ArrayList<Map<String, String>>();
		tablesList12.add(createOptionalTable("1", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"));
		List<String> tagList12 = new ArrayList<String>();
		tagList12.add(CUSDRVSTDTAG);
		
		List<Map<String, String>> viewList13 = new ArrayList<Map<String, String>>();
		viewList13.add(createViewsMapTable("First View", "1", "1", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList13.add(createViewsMapTable("Second View", "0", "0", "1", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList13 = new ArrayList<Map<String, String>>();
		tablesList13.add(createOptionalTable("1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"));
		List<String> tagList13 = new ArrayList<String>();
		tagList13.add(CUSDRVSTDTAG);
		
		List<Map<String, String>> viewList14 = new ArrayList<Map<String, String>>();
		viewList14.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList14.add(createViewsMapTable("Second View", "1", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList14.add(createViewsMapTable("Third View", "1", "1", "1", "1", "1", "1", "1", "1", "1", "None"));
		List<Map<String, String>> tablesList14 = new ArrayList<Map<String, String>>();
		tablesList14.add(createOptionalTable("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"));
		List<String> tagList14 = new ArrayList<String>();
		tagList14.add(SQACUSDRTAG);
		
		List<Map<String, String>> viewList15 = new ArrayList<Map<String, String>>();
		viewList15.add(createViewsMapTable("First View", "1", "1", "0", "1", "0", "0", "0", "1", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList15.add(createViewsMapTable("Second View", "0", "0", "1", "1", "0", "0", "0", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList15.add(createViewsMapTable("Third View", "0", "1", "0", "0", "0", "0", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList15 = new ArrayList<Map<String, String>>();
		tablesList15.add(createOptionalTable("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "0"));
		List<String> tagList15 = new ArrayList<String>();
		tagList15.add(SQACUSDRTAG);
		
		List<Map<String, String>> viewList16 = new ArrayList<Map<String, String>>();
		viewList16.add(createViewsMapTable("First View", "1", "1", "0", "1", "1", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList16.add(createViewsMapTable("Second View", "1", "1", "0", "1", "1", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList16 = new ArrayList<Map<String, String>>();
		tablesList16.add(createOptionalTable("1", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"));
		List<String> tagList16 = new ArrayList<String>();
		tagList16.add(CUSDRVSTDTAG);
		
		List<Map<String, String>> viewList17 = new ArrayList<Map<String, String>>();
		viewList17.add(createViewsMapTable("First View", "1", "1", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList17 = new ArrayList<Map<String, String>>();
		tablesList17.add(createOptionalTable("1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"));
		List<String> tagList17 = new ArrayList<String>();
		tagList17.add(CUSDRVSTDTAG); // include 8 hr survey
		
		List<Map<String, String>> viewList18 = new ArrayList<Map<String, String>>();
		viewList18.add(createViewsMapTable("First View", "0", "0", "0", "0", "1", "0", "0", "1", "1", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList18.add(createViewsMapTable("Second View", "1", "1", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList18.add(createViewsMapTable("Third View", "0", "0", "1", "1", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList18 = new ArrayList<Map<String, String>>();
		tablesList18.add(createOptionalTable("1", "1", "0", "0", "1", "1", "0", "0", "0", "0", "1", "0"));
		List<String> tagList18 = new ArrayList<String>();
		tagList18.add(SQACUSDRTAG);
		
		List<Map<String, String>> viewList19 = new ArrayList<Map<String, String>>();
		viewList19.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList19.add(createViewsMapTable("Second View", "1", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList19.add(createViewsMapTable("Third View", "1", "1", "1", "1", "1", "1", "1", "1", "1", "None"));
		viewList19.add(createViewsMapTable("Fourth View", "1", "1", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList19.add(createViewsMapTable("Fifth View", "1", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList19.add(createViewsMapTable("Sixsth View", "1", "1", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList19.add(createViewsMapTable("Seventh View", "0", "0", "1", "1", "1", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList19 = new ArrayList<Map<String, String>>();
		tablesList19.add(createOptionalTable("1", "1", "0", "0", "1", "1", "0", "0", "0", "0", "1", "0"));
		List<String> tagList19 = new ArrayList<String>();
		tagList19.add(SQACUSDRTAG);
		
		List<Map<String, String>> viewList20 = new ArrayList<Map<String, String>>();
		viewList20.add(createViewsMapTable("First View", "1", "0", "0", "1", "1", "0", "0", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList20 = new ArrayList<Map<String, String>>();
		tablesList20.add(createOptionalTable("1", "1", "0", "0", "1", "1", "0", "0", "0", "0", "0", "0"));
		List<String> tagList20 = new ArrayList<String>();
		tagList20.add(SQACUSDRTAG); //include a survey with no LISAS
		
		List<Map<String, String>> viewList21 = new ArrayList<Map<String, String>>();
		viewList21.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList21.add(createViewsMapTable("Second View", "0", "0", "1", "1", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList21.add(createViewsMapTable("Third View", "1", "1", "0", "0", "1", "0", "0", "0", "0", "None"));		
		List<Map<String, String>> tablesList21 = new ArrayList<Map<String, String>>();
		tablesList21.add(createOptionalTable("1", "0", "0", "0", "1", "1", "1", "1", "1", "1", "1", "1"));
		List<String> tagList21 = new ArrayList<String>();
		tagList21.add(CUSDRVSTDTAG); 
		
		List<Map<String, String>> viewList22 = new ArrayList<Map<String, String>>();
		viewList22.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList22.add(createViewsMapTable("Second View", "1", "0", "1", "1", "0", "0", "1", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList22.add(createViewsMapTable("Third View", "1", "1", "0", "1", "1", "0", "1", "0", "0", "None"));		
		List<Map<String, String>> tablesList22 = new ArrayList<Map<String, String>>();
		tablesList22.add(createOptionalTable("1", "0", "0", "0", "1", "1", "1", "1", "1", "1", "1", "1"));
		List<String> tagList22 = new ArrayList<String>();
		tagList22.add(CUSDRVSTDTAG); 
		
		List<Map<String, String>> viewList23 = new ArrayList<Map<String, String>>();
		viewList23.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList23 = new ArrayList<Map<String, String>>();
		tablesList23.add(createOptionalTable("1", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"));
		List<String> tagList23 = new ArrayList<String>();
		tagList23.add(CUSDRVSTDTAG); 


		return new Object[][] {				
				{ "1"/*index*/, SQAPICSUP/*strCreatedBy*/, "oeHwHqmv621dZ1MRE2BSdw=="/*password(encrypted)*/, "Picarro"/*customer*/, TIMEZONEPTUA/*timeZone*/, EXCLUSIONRADIUS/*exclusionRadius*/, null/*surveyorUnit*/, null/*userName*/, null/*startDate*/,
					null/*endDate*/, null/*fovOpacity*/, null/*lisaOpacity*/, null/*geoFilter*/, null/*reportMode*/, null/*surveyModeFilter*/, null/*ethaneFilter*/, createBoundaryList()/*listBoundary*/, tagList1/*tagList*/, tablesList1/*tablesList*/, viewList1/*viewList*/, null/*viewLayersList*/ },
				{ "2", SQAPICSUP, "oeHwHqmv621dZ1MRE2BSdw==", "Picarro", TIMEZONEPTUA, EXCLUSIONRADIUS, null, null, null, null, null, null, null, null, null, null, createBoundaryList(), tagList2, tablesList2, viewList2, null }, // include date range
				{ "3", PICDFADMIN, "6NZYEwkq24lm9xBekD04sg==", "Picarro", TIMEZONECTUA, EXCLUSIONRADIUS, PICADMNSURVEYOR, null, null, null, null, null, null, null, SurveyModeFilter.All, null, createBoundaryList(), tagList3, tablesList3, viewList3, null },
				{ "4", PICDFADMIN, "6NZYEwkq24lm9xBekD04sg==", "Picarro", TIMEZONEETUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList4, tablesList4, viewList4, null }, //include dates
				{ "5", PICDFADMIN, "6NZYEwkq24lm9xBekD04sg==", "Picarro", TIMEZONECTUA, "0", PICADMNSURVEYOR, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList5, tablesList5, viewList5, null }, //include dates
				{ "6", PICDFADMIN, "6NZYEwkq24lm9xBekD04sg==", "Picarro", TIMEZONEPTUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList6, tablesList6, viewList6, null },
				{ "7", PICDFADMIN, "6NZYEwkq24lm9xBekD04sg==", "Picarro", TIMEZONEPTUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList7, tablesList7, viewList7, null },
				{ "8", PICDFADMIN, "6NZYEwkq24lm9xBekD04sg==", "Picarro", TIMEZONEPTUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList8, tablesList8, viewList8, null },
				{ "9", PICDFADMIN, "6NZYEwkq24lm9xBekD04sg==", "Picarro", TIMEZONEETUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList9, tablesList9, viewList9, null },
				{ "10", PICDFADMIN, "6NZYEwkq24lm9xBekD04sg==", "Picarro", TIMEZONEMTUA, "0", null, null, null, null, null, null, null, ReportModeFilter.Manual, SurveyModeFilter.Manual, null, createBoundaryList(), tagList10, tablesList10, viewList10, null },
				{ "11", PICDFADMIN, "6NZYEwkq24lm9xBekD04sg==", "sqacus", TIMEZONEMTUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList11, tablesList11, viewList11, null },
				{ "12", SQAPICSUP, "oeHwHqmv621dZ1MRE2BSdw==", "sqacus", TIMEZONEPTUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList12, tablesList12, viewList12, null },
				{ "13", SQACUSSU, "oeHwHqmv621dZ1MRE2BSdw==", "sqacus", TIMEZONEPTUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList13, tablesList13, viewList13, null },
				{ "14", PICDFADMIN, "6NZYEwkq24lm9xBekD04sg==", "Picarro", TIMEZONEPTUA, "0", null, null, null, null, null, null, null, null, null, null, createBoundaryList(), tagList14, tablesList14, viewList14, null },
				{ "15", PICDFADMIN, "6NZYEwkq24lm9xBekD04sg==", "Picarro", TIMEZONEPTUA, "0", null, null, null, null, null, null, null, null, null, null, createBoundaryList(), tagList15, tablesList15, viewList15, null },//Include Date filter for 30 days
				{ "16", SQACUSSU, "oeHwHqmv621dZ1MRE2BSdw==", "sqacus", TIMEZONECTUA, "150", null, null, null, null, null, null, true, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList16, tablesList16, viewList16, null },
				{ "17", SQACUSSU, "oeHwHqmv621dZ1MRE2BSdw==", "sqacus", TIMEZONEETUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList17, tablesList17, viewList17, null },
				{ "18", PICDFADMIN, "6NZYEwkq24lm9xBekD04sg==", "Picarro", TIMEZONEPTUA, "0", null, null, null, null, null, null, null, null, null, null, createBoundaryList(), tagList18, tablesList18, viewList18, null },
				{ "19", PICDFADMIN, "6NZYEwkq24lm9xBekD04sg==", "Picarro", TIMEZONEPTUA, "0", null, null, null, null, null, null, null, null, null, null, createBoundaryList(), tagList19, tablesList19, viewList19, null },
				{ "20", PICDFADMIN, "6NZYEwkq24lm9xBekD04sg==", "Picarro", TIMEZONEMTUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList20, tablesList20, viewList20, null }, //include dates
				{ "21", SQACUSSU, "oeHwHqmv621dZ1MRE2BSdw==", "sqacus", TIMEZONEPTUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList21, tablesList21, viewList21, null } ,
				{"22", SQACUSSU, "oeHwHqmv621dZ1MRE2BSdw==", "sqacus", TIMEZONEPTUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList22, tablesList22, viewList22, null },
				{"23", SQACUSSU, "oeHwHqmv621dZ1MRE2BSdw==", "sqacus", TIMEZONEPTUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList23, tablesList23, viewList23, null }

		};

	}

}