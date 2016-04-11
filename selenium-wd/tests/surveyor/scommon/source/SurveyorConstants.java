/**
 * 
 */
package surveyor.scommon.source;

import java.util.HashMap;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.source.Reports.ReportJobType;
import surveyor.scommon.source.Reports.ReportModeFilter;

/**
 * @author zlu
 *
 */
public final class SurveyorConstants {
	/**
	 * 
	 */
	private SurveyorConstants() {		
		populateGuidMaps();
	}
	
	public static HashMap<String, SurveyModeFilter> SurveyModeFilterGuids = new HashMap<String, SurveyModeFilter>();
	public static HashMap<String, ReportModeFilter> ReportSurveyModeFilterGuids = new HashMap<String, ReportModeFilter>();
	public static HashMap<String, ReportJobType> ReportJobTypeGuids = new HashMap<String, ReportJobType>();
	
	public static final String BASECUSTOMERNAME = "Cus";
	public static final String BASELOCATIONNAME = "Loc";
	public static final String BASESURVEYORNAME = "Sur";
	public static final String BASEANALYZERNAME = "";
	public static final String ANALYZERSHAREDKEY = "sqa#Picarro$0";

	public static final String BASEUSERNAME = "SQA@picarro.com";
	public static final String USERPASSWORD = "sqa#Picarro$0";

	public static final int CUSTOMERNUM = 50; // Should be set less than 100 otherwise need review the code
	public static final int LOCATIONNUM = 5; // Should be set less than 100 otherwise need review the code
	public static final int SURVEYORNUM = 1; // Should be set less than 100 otherwise need review the code
	public static final int ANALYZERNUM = 1; // Should be set less than 100 otherwise need review the code
	public static final int USERNUM = 5; // Should be set less than 100 otherwise need review the code

	public static final String CUSTOMERNAMEPREFIX = "regcus";
	public static final String CUSTOMERSTATUS = Resources.getResource(ResourceKeys.Constant_Enabled);
	public static final String EULASTRING = "Testing eula string, TBD";
	public static final String REGBASEUSERNAME = "@email.com";

	public static final String PICNAMEPREFIX = "sqapic";
	public static final String REGBASEPICUSERNAME = "@picarro.com";

	public static final String CUSNAMEBASE = "sqacus";
	public static final String CUSNAMEBASELOC = "sqacusloc";
	
	public static final String SQACUS = "sqacus";
	public static final String SQACUSLOC = "sqacusloc";
	public static final String SQAPIC = "sqapic";
	public static final String SQAPICLOC = "sqapicloc";

	public static final String USERROLEADMIN = "Administrator";
	public static final String CUSUSERROLEUA = "Utility Administrator";
	public static final String CUSUSERROLESU = "Supervisor";
	public static final String CUSUSERROLEDR = Resources.getResource(ResourceKeys.Constant_Driver);
	public static final String PICUSERROLESUP = "Picarro Support";

	public static final String ADMINISTRATORUSER = "Administrator";    // NOTE: This user is required for cases where the data pushed into environment has been pushed as Administrator User.
	public static final String PICDFADMIN = "AutomationAdmin";
	public static final String USERPASSWORDHASH ="oeHwHqmv621dZ1MRE2BSdw==";
	public static final String PICADMINPSWD = "sqa#Picarro$0";
	public static final String SQACUSUAUSER = "sqacusua";
	public static final String SQACUSSUUSER = "sqacussu";
	public static final String SQACUSDRUSER = "sqacusdr";
	public static final String SQAPICADUSER = "sqapicad";
	public static final String SQAPICUAUSER = "sqapicua";
	public static final String SQAPICSUUSER = "sqapicsu";
	public static final String SQAPICDRUSER = "sqapicdr";

	public static final String SQACUSUA = "sqacusua@email.com";
	public static final String SQACUSSU = "sqacussu@email.com";
	public static final String SQACUSDR = "sqacusdr1@email.com";
	public static final String SQAPICAD = "sqapicad@picarro.com";
	public static final String SQAPICSUP = "sqapicsup@picarro.com";
	public static final String SQAPICUA = "sqapicua@picarro.com";
	public static final String SQAPICSU = "sqapicsu@picarro.com";
	public static final String SQAPICSU1 = "sqapicsu1@picarro.com";
	public static final String SQAPICDR = "sqapicdr@picarro.com";
	public static final String SQAPGEUA = "sqapgeua@email.com";
	public static final String SQAPGESU = "sqapgesu@email.com";
	public static final String SQAPGEDR = "sqapgedr1@email.com";
	

	public static final String SQAPICLOCSUR = "sqapiclocsur";
	public static final String SQAPICLOC0SUR = "sqapicloc0sur";
	public static final String SQAPICLOC1SUR = "sqapicloc1sur";
	public static final String SQAPICLOC2SUR = "sqapicloc2sur";
	public static final String SQAPICLOC3SUR = "sqapicloc3sur";
	public static final String SQAPICLOC4SUR = "Software Car";
	public static final String SQACUSLOCSUR = "White Dodge";
	public static final String SQACUSLOCANZ = "FDDS2037";
	public static final String SQACUSLOC0SUR = "sqacusloc0sur";
	public static final String SQACUSLOC1SUR = "sqacusloc1sur";
	public static final String SQACUSLOC2SUR = "sqacusloc2sur";
	public static final String SQACUSLOC3SUR = "sqacusloc3sur";

	public static final String SQAETHCUST= "PG&E";
	public static final String SQAETHSUR="Ethane Car";
	public static final String SQAETHLOC= "PG&E - Menlo Park";
	
	public static final String SQAPICLOCSURANA = "sqapiclocsurana";
	public static final String SQAPICLOC0SURANA = "sqapicloc0surana";
	public static final String SQAPICLOC1SURANA = "sqapicloc1surana";
	public static final String SQAPICLOC2SURANA = "sqapicloc2surana";
	public static final String SQAPICLOC3SURANA = "sqapicloc3surana";
	public static final String SQAPICLOC4SURANA = "FDDS2038";
	public static final String SQACUSLOCSURANA = "sqacuslocsurana";
	public static final String SQACUSLOC0SURANA = "sqacusloc0surana";
	public static final String SQACUSLOC1SURANA = "sqacusloc1surana";
	public static final String SQACUSLOC2SURANA = "sqacusloc2surana";
	public static final String SQACUSLOC3SURANA = "sqacusloc3surana";

	public static final String SQACUSUATAG = "dmcs1-sqacusua";
	public static final String SQACUSSUTAG = "dmcs1-sqacussu";
	public static final String SQACUSDRTAG = "stnd";
	public static final String SQACUSDRTAG2 = "stnd-sqacudr";
	public static final String SQACUSRRTAG = "Rapid";
	public static final String SQACUSMNTAG= "Manual";
	
	
	public static final String SQAPICADTAG = "dmcs1-sqapicad";
	public static final String SQAPICUATAG = "dmcs1-sqapicua";
	public static final String SQAPICSUTAG = "dmcs1-sqapicsu";
	public static final String SQAPICDRTAG = "dmcs1-sqapicdr";
	public static final String SQACRPTTAG = "tc56";
	public static final String RGBNAMEBASE = "rgb";

	public static String STRFEEDBACK = "This is sqa test feedback message";

	public static final String TITLENAMEBASE = "sqacrpt";
	public static final String TIMEZONE = "Pacific Standard Time";
	public static final String EXCLUSIONRADIUS = "50";
	public static final String REPORTMODES1 = "S1";
	public static final String REPORTMODES = "Standard";

	public static final String CUSBOUNDARY = Resources.getResource(ResourceKeys.ComplianceReport_CustomBoundary);
	public static final String IMGMAPHEIGHT = "8.5";
	public static final String IMGMAPWIDTH = "11";
	
	public static final String NELAT = "37.421980615353675";
	public static final String NELON = "121.93639755249023";
	public static final String SWLAT = "37.39566424530531";
	public static final String SWLON = "122.01141357421875";

	public static final String SURVEYORUNIT = "sqacus - sqacusloc - sqacuslocsur";
	public static final String TAG = "dmcs1-sqa01";

	public static final String STARTDATE = "01/01/2014";
	public static final String ENDDATE = "08/14/2014";

	public static final int ACTIONTIMEOUT = 300; // in seconds

	public static final String PAGINATIONSETTING = "10"; // Selenium WebDriver issues so set it to 10 only for now
	public static final String PAGINATIONSETTING_25 = "25";
	public static final String PAGINATIONSETTING_50 = "50";
	public static final String PAGINATIONSETTING_100 = "100";

	public static final String USERENABLED = Resources.getResource(ResourceKeys.Constant_Enabled);
	public static final String USERDISABLED = Resources.getResource(ResourceKeys.Constant_Disabled);

	public static final String CUSTOMERENABLED = Resources.getResource(ResourceKeys.Constant_Enabled);
	public static final String CUSTOMERDISABLED = Resources.getResource(ResourceKeys.Constant_Disabled);

	public static final String TIMEZONEPT = "Pacific Time (US and Canada)";
	public static final String TIMEZONEMT = "Mountain Time (US and Canada)";
	public static final String TIMEZONECT = "Central Time (US and Canada)";
	public static final String TIMEZONEET = "Eastern Time (US and Canada)";

	public static final String TIMEZONEPTUA = "Pacific Standard Time";
	public static final String TIMEZONEMTUA = "Mountain Standard Time (Mexico)";
	public static final String TIMEZONECTUA = "Central Standard Time";
	public static final String TIMEZONEETUA = "Eastern Standard Time";

	public static final String KEYVIEWNAME = "ViewName";
	public static final String KEYLISA = "Lisa";
	public static final String KEYFOV = "FOV";
	public static final String KEYBREADCRUMB = "Bread Crumb";
	public static final String KEYINDICATIONS = "Indications";
	public static final String KEYISOTOPICCAPTURE = "Isotopic Capture";
	public static final String KEYANNOTATION = "Annotation";
	public static final String KEYGAPS = "Gaps";
	public static final String KEYASSETS = "Assets";
	public static final String KEYBOUNDARIES = "Boundaries";
	public static final String KEYBASEMAP = "Base Map";
	public static final String KEYASSETCASTIRON = "Cast Iron";
	public static final String KEYASSETCOPPER = "Copper";
	public static final String KEYASSETOTHERPLASTIC = "Other Plastic";
	public static final String KEYASSETPEPLASTIC = "PE Plastic";
	public static final String KEYASSETPROTECTEDSTEEL = "Un-protected Steel";
	public static final String KEYASSETUNPROTECTEDSTEEL = "Protected Steel";
	public static final String KEYBOUNDARYDISTRICT = "Small Boundary";
	public static final String KEYBOUNDARYDISTRICTPLAT = "Big Boundary";
	public static final String KEYRSBSAMPLRPT= "AutomationTestReport";
	public static final String KEYCPSAMPLRPT= "test1234";
	public static final String KEYINDCLR = "Indication Color";
	
	public static final String KEYINDTB = "Indications Table";
	public static final String KEYISOANA = "Isotopic Analysis";
	public static final String KEYPCA = "Percent Coverage Assets";
	public static final String KEYPCRA = "Percent Coverage Report Area";

	public static final String SQAPICADRRTAG = "dmcrapidresponse-sqapic-admin";
	public static final String SQAPICADSTNDTAG = "dmcstandard-sqapic-admin";
	public static final String SQAPICADMANUALTAG = "dmcmanual-sqapic-admin";

	public static final String DUPLICATIONERROR = Resources.getResource(ResourceKeys.ManageUser_UserNameAlreadyExists);
	public static final String PWVALUEERROR = Resources.getResource(ResourceKeys.Validation_EnterSameValueAgain);
	public static final String EMAILINVALID = Resources.getResource(ResourceKeys.ManageUser_EmailNotValid);	
	public static final String BLANKFIELDERROR = Resources.getResource(ResourceKeys.Validation_Required);
	public static final String PWDSAMEVALUE = Resources.getResource(ResourceKeys.Validation_EnterSameValueAgain);
	public static final String EMAILTOOLONG = Resources.getResource(ResourceKeys.Validation_EnterNoMoreThan).replace("{0}", "50");
	public static final String PASSWORDTOOLONG = Resources.getResource(ResourceKeys.Validation_EnterNoMoreThan).replace("{0}", "15");
	public static final String NOMATCHINGRECORDS = Resources.getResource("Constant_NoMatchingRecordsFound");		
	public static final String NOMATCHINGSEARCH = Resources.getResource(ResourceKeys.Constant_NoMatchingRecordsFound);
	public static final String DRIVINGSURVEYSEXPORTSURVEY = "SurveyExport";
	public static final String DRIVINGSURVEYSEXPORTPEAKS = "PeakExport";
	public static final String DRIVINGSURVEYSEXPORTANALYSIS = "AnalysisExport";
	public static final String DRIVINGSURVEYSSTNDMODE = "Standard";
	public static final String LOGINTITLE = "Login";
	public static final String HOMETITLE = "Home - Surveyor";
	public static final String HOMEDHEADER = "Dashboard";
	public static final String SUBTITLE = " - Surveyor";
	public static final String DRIVINGSURVEYTITLE = "Measurement Sessions - Surveyor";
	public static final String DRIVINGSURVEYHEADER = "Driving Surveys";

	
	/*
	 * Driving survey tag for Compliance Reports
	 */
	public static final String PICADMNSTDTAG = "stnd";
	public static final String PICADMNRRTAG = "rr";
	public static final String PICADMNOPTAG = "op";
	public static final String PICADMNMANTAG = "man";
	public static final String CUSDRVSTDTAG = "stnd";
	public static final String CUSDRVRRTAG = "rr";
	public static final String CUSDRVOPTAG = "op";
	public static final String CUSDRVETHSTDTAG= "ethane-standard";
	public static final String CUSDRVETHRRTAG= "ethane-rr";
	public static final String CUSDRVETHMNTAG= "ethane-manual";
	public static final String PICADMNSTDTAG2 = "stnd-pic";
	public static final String PICADMNRRTAG2 = "rr-pic";
	public static final String PICADMNOPTAG2 = "op-pic";
	public static final String PICADMNMANTAG2 = "man-pic";
	public static final String CUSDRVSTDTAG2 = "stnd-sqacudr";
	public static final String CUSDRVRRTAG2 = "rr-sqacudr";
	public static final String CUSDRVOPTAG2 = "op-sqacudr";
	
		
	/*
	 * Compliance Report Surveyor Units
	 */
	public static final String PICADMNSURVEYOR = "Picarro - Santa Clara - Software Car";
	public static final String SQACUSSURVEYOR = "White Dodge";
	public static final String PICADMNSURVEYORSHORT = "Software Car";

	/*
	 * Lat Long for Reporting
	 */
	public static final String RNELAT = "37.42060";
	public static final String RNELON = "-121.97250";
	public static final String RSWLAT = "37.41570";
	public static final String RSWLON = "-121.98390";

	public static final String REXCLUSIONRADIUS = "3";

	/*
	 * Lat Long for Ethane Reporting
	 */
	public static final String ETHRNELAT = "37.42324";
	public static final String ETHRNELON = "-121.93172";
	public static final String ETHRSWLAT = "37.40177";
	public static final String ETHRSWLON = "-121.99004";

	public static final String ETHREXCLUSIONRADIUS = "3";

	/*
	 * Start & end date for survey
	 */
	public static final String RSURSTARTDATE = "12/14/2015 3:00:00 PM";
	public static final String RSURENDDATE = "12/14/2015 5:00:00 PM";

	/*
	 * Survey modes for Compliance report
	 */
	public static final String RSUVMODEALL = "All";
	public static final String RSUVMODESTD = "Standard";
	public static final String RSUVMODEOP = "Operator";
	public static final String RSUVMODERR = "Rapid Response";
	public static final String RSUVMODEMANUAL = "Manual";
	
	public static final String SQACUSSULOC="Santa Clara";
	public static final String DEFAULTLOC="Default";

	public static final String REQUIRED_FIELD_VAL_MESSAGE = "This field is required.";	
	
	/*
	 * Timeout constants
	 */
	public static final int SECONDS_10 = 10;
	
	/*
	 * Other string constants
	 */
	public static final String UNKNOWN_TEXT = "UnknownText";
	/*
	 * enum for list of constants
	 */
	public static enum LicensedFeatures {
		GAPGRID, REPORTMETADATA, ASSESSMENT, EQ, 
		LISABOX, SURVEYFORECASE, REPORTSHAPEFILE
	}
	
	public static enum TopNavMenuItem {
		PREFERENCES ("Preferences"), 
		CHANGEPASSWORD ("Change Password"), 
		RELEASENOTES ("Release Notes"), 
		MANUAL ("Manual"), 
		LOGOUT ("Log out");

		private final String text;
		TopNavMenuItem(String text){
			this.text = text;
		}

		public String toString(){
			return text;
		}
	};

	/**
	 * Populate map with guids from SurveyModeType.
	 * NOTE: These guids need to be in sync with Ids used in [SurveyModeType] table.
	 */
	private void populateGuidMaps() {
		SurveyModeFilterGuids.put("0514B92A-39AE-4111-AF16-4495440EC319", SurveyModeFilter.Assessment);
		SurveyModeFilterGuids.put("4901E67A-4C00-4436-ADC0-9CFB277BB310", SurveyModeFilter.RapidResponse);
		SurveyModeFilterGuids.put("4901E67A-4C00-4436-ADC0-9CFB277BB311", SurveyModeFilter.Manual);
		SurveyModeFilterGuids.put("4901E67A-4C00-4436-ADC0-9CFB277BB312", SurveyModeFilter.Operator);
		SurveyModeFilterGuids.put("E9DD9F53-E5CB-45B3-9517-9DC8E0276C6D", SurveyModeFilter.EQ);
		SurveyModeFilterGuids.put("B310238A-A5AE-4E94-927B-F0F165E24522", SurveyModeFilter.Standard);

		ReportSurveyModeFilterGuids.put("0514B92A-39AE-4111-AF16-4495440EC319", ReportModeFilter.Assessment);
		ReportSurveyModeFilterGuids.put("4901E67A-4C00-4436-ADC0-9CFB277BB310", ReportModeFilter.RapidResponse);
		ReportSurveyModeFilterGuids.put("4901E67A-4C00-4436-ADC0-9CFB277BB311", ReportModeFilter.Manual);
		ReportSurveyModeFilterGuids.put("4901E67A-4C00-4436-ADC0-9CFB277BB312", ReportModeFilter.Operator);
		ReportSurveyModeFilterGuids.put("E9DD9F53-E5CB-45B3-9517-9DC8E0276C6D", ReportModeFilter.EQ);
		ReportSurveyModeFilterGuids.put("B310238A-A5AE-4E94-927B-F0F165E24522", ReportModeFilter.Standard);
		
		ReportJobTypeGuids.put("00000000-0000-0000-0001-000000000000", ReportJobType.Map);
		ReportJobTypeGuids.put("00000000-0000-0000-0002-000000000000", ReportJobType.SSRS);
		ReportJobTypeGuids.put("00000000-0000-0000-0003-000000000000", ReportJobType.DataGeneration);
		ReportJobTypeGuids.put("00000000-0000-0000-0004-000000000000", ReportJobType.EQMap);
		ReportJobTypeGuids.put("00000000-0000-0000-0005-000000000000", ReportJobType.EQSSRS);
		ReportJobTypeGuids.put("00000000-0000-0000-0006-000000000000", ReportJobType.EQDataGeneration);
		ReportJobTypeGuids.put("00000000-0000-0000-0007-000000000000", ReportJobType.ShapeFile);
		ReportJobTypeGuids.put("00000000-0000-0000-0008-000000000000", ReportJobType.ReportMeta);
		ReportJobTypeGuids.put("00000000-0000-0000-0009-000000000000", ReportJobType.PercentCoverageForecast);
	}
	
	public static enum UserTimezone {
		PACIFIC ("Pacific Standard Time"),
		MOUNTAIN ("Mountain Standard Time"),
		CENTRAL ("Central Standard Time"),
		EASTERN ("Eastern Standard Time");

		private final String text;
		UserTimezone(String text){
			this.text = text;
		}

		public String toString(){
			return text;
		}
	};
}


