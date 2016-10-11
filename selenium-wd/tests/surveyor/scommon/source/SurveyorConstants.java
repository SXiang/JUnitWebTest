/**
 *
 */
package surveyor.scommon.source;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import common.source.RegexUtility;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataprovider.ConstantDataProvider;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;

/**
 * @author zlu
 *
 */
public final class SurveyorConstants {
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

	private static final String CUSTOMERS_WITH_ASSETS = "Picarro";     // Comma seperated list of Customers that have assets in Seed script.

	public static final String CUSTOMER_PICARRO = "Picarro";
	public static final String CUSTOMER_SQACUS = "sqacus";
	public static final String CUSTOMER_SQATEST = "sqaTest";
	public static final String CUSTOMER_PGE = "PG&E";

	public static final String CUSTOMERNAMEPREFIX = "regcus";
	public static final String CUSTOMERSTATUS = Resources.getResource(ResourceKeys.Constant_Enabled);
	public static final String EULASTRING = "Testing eula string, TBD";
	public static final String REGBASEUSERNAME = "@email.com";

	public static final String PICNAMEPREFIX = "sqapic";
	public static final String REGBASEPICUSERNAME = "@picarro.com";

	public static final String CUSNAMEBASE = CUSTOMER_SQACUS;
	public static final String CUSNAMEBASELOC = "sqacusloc";

	public static final String SQATESTLOC = "sqaTestloc";
	public static final String PGESCLOC = "pge_SC";

	public static final String SQACUS = CUSTOMER_SQACUS;
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
	public static final String DRIVER1PIC = "driver1@picarro.com";
	public static final String DRIVER2PIC = "driver2@picarro.com";
	public static final String PICDR = "picdr@picarro.com";

	public static final String SQAPICLOCSUR = "sqapiclocsur";
	public static final String SQAPICLOC0SUR = "sqapicloc0sur";
	public static final String SQAPICLOC1SUR = "sqapicloc1sur";
	public static final String SQAPICLOC2SUR = "sqapicloc2sur";
	public static final String SQAPICLOC3SUR = "sqapicloc3sur";
	public static final String SQAPICLOC4SUR = "Software Car";
	public static final String SQACUSLOCSUR = "White Dodge";
	public static final String SQACUSLOCANZ = "FDDS2037";
	public static final String SQACUSLOCANZ1 = "FDDS2037-1";
	public static final String SQACUSLOCANZ2 = "FDDS2037-2";
	public static final String SQACUSLOC0SUR = "sqacusloc0sur";
	public static final String SQACUSLOC1SUR = "sqacusloc1sur";
	public static final String SQACUSLOC2SUR = "sqacusloc2sur";
	public static final String SQACUSLOC3SUR = "sqacusloc3sur";

	public static final String FEDS2015ANZ = "FEDS2015";
	public static final String FEDS2050ANZ = "FEDS2050";
	public static final String FEDS2055ANZ = "FEDS2055";

	public static final String RFADS2004ANZ = "RFADS2004";
	public static final String RFADS2004PICARRO = "RFADS2004-PICARRO";

	public static final String SQAETHCUST= CUSTOMER_PGE;
	public static final String SQAETHSUR="Black Dodge-P3300";
	public static final String SQAETHLOC= "PGE-Santa Clara";

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

	public static final String TITLENAMEBASE = "sqacrpt";
	public static final String TIMEZONE = "Pacific Standard Time";
	public static final String EXCLUSIONRADIUS = "50";
	public static final String EXCLUSIONRADIUS_0 = "0";
	public static final String EXCLUSIONRADIUS_100 = "100";
	public static final String EXCLUSIONRADIUS_150 = "150";
	public static final String REPORTMODES1 = "S1";
	public static final String REPORTMODES = "Standard";

	public static final String CUSBOUNDARY = Resources.getResource(ResourceKeys.ComplianceReport_CustomBoundary);
	public static final String IMGMAPHEIGHT = "8.5";
	public static final String IMGMAPWIDTH = "11";

	public static final String NELAT = "37.421980615353675";
	public static final String NELON = "-121.93639755249023";
	public static final String SWLAT = "37.39566424530531";
	public static final String SWLON = "-122.01141357421875";

	// Area in the range --> 1.25 sqkms - 1.5 sqkms, which includes survey data for stnd-pic
	// Area of this selection = 138131.88282872934 sqkms
	public static final String NELAT_SMALL = "37.420000994238805";
	public static final String NELON_SMALL = "-121.97592854325212";
	public static final String SWLAT_SMALL = "37.4167629940449";
	public static final String SWLON_SMALL = "-121.98026299302019";

	// Area in the range --> less than 5 sqms
	public static final String NELAT_XSMALL = "37.420000994238805";
	public static final String NELON_XSMALL = "-121.97592854325212";
	public static final String SWLAT_XSMALL = "37.420000994238705";
	public static final String SWLON_XSMALL = "-121.97592854325312";

	// Area in the range --> grate than 15 sqkms
	public static final String NELAT_XLARGE = "37.420000994238805";
	public static final String NELON_XLARGE = "-121.97592854325212";
	public static final String SWLAT_XLARGE = "39.4167629940449";
	public static final String SWLON_XLARGE = "-123.98026299302019";

	public static final int  X_OFFSET = 150;
	public static final int Y_OFFSET = 150;
	public static final int RECT_WIDTH = 100;
	public static final int RECT_HEIGHT = 100;

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

	public static final String TIMEZONEPT = ConstantDataProvider.getConstantDataProvider().getCellData("Timezone", "Timezone", 1);
	public static final String TIMEZONEMT = ConstantDataProvider.getConstantDataProvider().getCellData("Timezone", "Timezone", 2);
	public static final String TIMEZONECT = ConstantDataProvider.getConstantDataProvider().getCellData("Timezone", "Timezone", 3);
	public static final String TIMEZONEET = ConstantDataProvider.getConstantDataProvider().getCellData("Timezone", "Timezone", 4);

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
	public static final String KEYHIGHLIGHTLISAASSETS = "Highlight LISA Assets";
	public static final String KEYHIGHLIGHTBOXASSETS = "Highlight Box Assets";
	public static final String KEYHIGHLIGHTGAPASSETS = "Highlight GAP Assets";
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
	public static final String KEYGAPTB = "Gap Table";
	public static final String KEYPCA = "Percent Coverage Assets";
	public static final String KEYPCF = "Percent Coverage Forecast";
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

	public static final String ETHMTHRTOLABEL = Resources.getResource(ResourceKeys.Constant_EthaneToMethaneRatio );
	public static final String ETHMTHERRGRTMSG = Resources.getResource(ResourceKeys.Validation_EnterValueGreaterThanOrEqual).replace("{0}", "1");
	public static final String ETHMTHERRLESSMSG = Resources.getResource(ResourceKeys.Validation_EnterValueLessThanOrEqual).replace("{0}", "99");

	/*
	 * Survey View constants
	 */

	public static final String SURVEY_PASSED = Resources.getResource(ResourceKeys.Survey_Passed);
	public static final String ISOTOPIC_CAPTURE_NATURAL_GAS = Resources.getResource(ResourceKeys.CaptureAnalysisDispositionTypes_Natural_Gas);
	public static final String ISOTOPIC_CAPTURE_NOT_NATURAL_GAS = Resources.getResource(ResourceKeys.CaptureAnalysisDispositionTypes_Not_Natural_Gas);
	public static final String ISOTOPIC_CAPTURE_CANCELLED= Resources.getResource(ResourceKeys.CaptureAnalysisDispositionTypes_User_Cancellation);
	public static final String REFGAS_CAPTURE_PASSED = Resources.getResource(ResourceKeys.ReferenceGasCaptureDescription_Isotopic_Reference_Pass);

	/*
	 * Driving survey tag for Compliance Reports
	 */
	public static final String PICADMNSTDTAG = "stnd";
	public static final String PICADMNRRTAG = "rr";
	public static final String PICADMNOPTAG = "op";
	public static final String PICADMNMANTAG = "man";
	public static final String PICADMMANTAG = "pic";
	public static final String CUSDRVSTDTAG = "stnd";
	public static final String CUSDRVSTDTAG3200 = "stnd-pic";
	public static final String CUSDRVRRTAG = "rr";
	public static final String CUSDRVRAPIDTAG = "rapid";
	public static final String CUSDRVOPTAG = "op";
	public static final String CUSDRVETHSTDTAG= "EthaneStnd";
	public static final String CUSDRVETHRRTAG= "EthaneRR";
	public static final String CUSDRVETHOPTAG="EthaneOpertor1";
	public static final String CUSDRVETHMNTAG= "EthaneManual";
	public static final String PICADMNSTDTAG2 = "stnd-pic";
	public static final String PICADMNSTDTAG2_STARTEPOCH = "1450134967.928";
	public static final String PICADMNSTDTAG2_STARTEPOCH_MINUS_EPSILON = "1450134967.878";
	public static final String PICADMNSTDTAG2_ENDEPOCH = "1450136588.925";
	public static final String PICADMNSTDTAG2_ENDEPOCH_PLUS_EPSILON = "1450136588.975";
	public static final double EPSILON = 0.05;
	public static final String PICADMNRRTAG2 = "rr-pic";
	public static final String PICADMNOPTAG2 = "op-pic";
	public static final String PICADMNMANTAG2 = "man-pic";
	public static final String CUSDRVSTDTAG2 = "stnd-sqacudr";
	public static final String CUSDRVRRTAG2 = "rr-sqacudr";
	public static final String CUSDRVOPTAG2 = "op-sqacudr";

	/*
	 * Long driving surveys.
	 */
	public static final String PIC8HR01TAG = "8HourSurvey01";
	public static final String PIC8HR02TAG = "8HourSurvey02";
	public static final String PIC8HR03TAG = "8HourSurvey03";
	public static final String PIC8HR04TAG = "8HourSurvey04";
	public static final String PIC8HR05TAG = "8HourSurvey05";
	public static final String PIC8HR06TAG = "8HourSurvey06";
	public static final String PIC8HR07TAG = "8HourSurvey07";
	public static final String PIC8HR08TAG = "8HourSurvey08";
	public static final String PIC8HR09TAG = "8HourSurvey09";
	public static final String PIC8HR10TAG = "8HourSurvey10";
	public static final String PIC8HR11TAG = "8HourSurvey11";
	public static final String PIC8HR12TAG = "8HourSurvey12";
	public static final String PICLESS4HRTAG = "LessThan4Hour";
	public static final String PICGREATER4HRTAG = "GreaterThan4Hour";

	public static final String PIC8HR01_DATAFILE = "8HourSurvey-1";
	public static final String PIC8HR02_DATAFILE = "8HourSurvey-2";
	public static final String PIC8HR03_DATAFILE = "8HourSurvey-3";
	public static final String PIC8HR04_DATAFILE = "8HourSurvey-4";
	public static final String PIC8HR05_DATAFILE = "8HourSurvey-5";
	public static final String PIC8HR06_DATAFILE = "8HourSurvey-6";
	public static final String PIC8HR07_DATAFILE = "8HourSurvey-7";
	public static final String PIC8HR08_DATAFILE = "8HourSurvey-8";
	public static final String PIC8HR09_DATAFILE = "8HourSurvey-9";
	public static final String PIC8HR10_DATAFILE = "8HourSurvey-10";
	public static final String PIC8HR11_DATAFILE = "8HourSurvey-11";
	public static final String PIC8HR12_DATAFILE = "8HourSurvey-12";
	public static final String PICLESS4HR_DATAFILE = "LessThan4Hour-1";
	public static final String PICGREATER4HR_DATAFILE = "GreaterThan4Hour-1";

	/*
	 * Simulator Analyzers
	 */
	public static final String SIMAUTO_ANALYZER1 = "SimAuto-Analyzer1";
	public static final String SIMAUTO_ANALYZER2 = "SimAuto-Analyzer2";
	public static final String SIMAUTO_ANALYZER3 = "SimAuto-Analyzer3";
	public static final String SIMAUTO_ANALYZER4 = "SimAuto-Analyzer4";
	public static final String SIMAUTO_ANALYZER5 = "SimAuto-Analyzer5";

	/*
	 * Simulator SurveyorUnits
	 */
	public static final String SIMAUTO_SURVEYOR1 = "SimAuto-Surveyor1";
	public static final String SIMAUTO_SURVEYOR2 = "SimAuto-Surveyor2";
	public static final String SIMAUTO_SURVEYOR3 = "SimAuto-Surveyor3";
	public static final String SIMAUTO_SURVEYOR4 = "SimAuto-Surveyor4";
	public static final String SIMAUTO_SURVEYOR5 = "SimAuto-Surveyor5";

	/*
	 * Surveyor Units
	 */
	public static final String SURVEYOR_SOFTWARECAR2037PIC = "SoftwareCar_2037_picarro";
	public static final String SURVEYOR_SOFTWARECAR2037CUST = "SoftwareCar_2037_cust";
	public static final String SURVEYOR_SOFTWARECAR2037TESTCUST = "SoftwareCar_2037_Testcust";
	public static final String SURVEYOR_PGEFEDS2015 = "PGE-FEDS2015";
	public static final String SURVEYOR_SILVERNISSANROGUE = "Silver Nissan Rogue";
	public static final String SURVEYOR_PICPROD10 = "Picarro Production #10";
	public static final String SURVEYOR_LIGHTBLUEESC = "Light Blue Escape";
	public static final String SURVEYOR_BLACKDODGE3300 = "BlackDodgeP3300";
	public static final String SURVEYOR_IGPSCAR = "iGPS car";


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
	public static final String PICARROLOC="Santa Clara";
	public static final String DEFAULTLOC="Default";

	public static final Integer DEFAULT_LOCATION_DATAROWID = 6;

	public static final String REQUIRED_FIELD_VAL_MESSAGE = "This field is required.";

	/*
	 * Error messages for compliance report
	 */
	public static final String CR_CF_AREAINVALID_MESSAGE=Resources.getResource(ResourceKeys.ComplianceReport_CoverageForecastAreaInvalidMessage);
	public static final String CR_CF_ASSETSINVALID_MESSAGE=Resources.getResource(ResourceKeys.ComplianceReport_CoverageForecastAssetsInvalidMessage);
	public static final String CR_CF_FORECASTBOUNDARYINVALID_MESSAGE=Resources.getResource(ResourceKeys.ComplianceReport_CoverageForecastBoundaryInvalidMessage);
	public static final String CR_SURVEYMISSING_MESSAGE=Resources.getResource(ResourceKeys.ComplianceReport_SurveyMissingMessage);
	public static final String CR_NOCOVERAGEFORECASTAVAILABLE_MESSAGE = "No Coverage Forecast Available";
	public static final String DIALOG_SELECTEDAREALSTOOLARGE_MESSAGE = Resources.getResource(ResourceKeys.Dialog_SelectedArealsTooLarge);
	public static final String DIALOG_SELECTEDAREALSTOOSMALL_MESSAGE = Resources.getResource(ResourceKeys.Dialog_SelectedArealsTooSmall);
	public static final String CR_BOUNDARYMINSIZE_MESSAGE = Resources.getResource(ResourceKeys.ComplianceReport_BoundaryMinSizeMessage);

	/*
	 * Analyzer and surveyor constants.
	 */
	public static final String ANALYZER_ALREADY_ASSOCIATED_ERROR = Resources.getResource(ResourceKeys.ManageAnalyzer_AlreadyAssociatedError);
	public static final String CONSTANT_CUSTOMER = Resources.getResource(ResourceKeys.Constant_Customer);
	public static final String CONSTANT_SURVEYOR = Resources.getResource(ResourceKeys.Constant_Surveyor);
	public static final String CONSTANT_LOCATION = Resources.getResource(ResourceKeys.Constant_Location);

	/*
	 * Num Records for functionality checking
	 */
	public static final int NUM_RECORDS_TOBEVERIFIED = 200;

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

	public enum SurveyModeType {
		Standard ("Standard"),
		RapidResponse ("RapidResponse"),
		Manual ("Manual"),
		Operator ("Operator"),
		Assessment ("Assessment"),
		EQ ("EQ");

		private final String name;

		SurveyModeType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public enum LicensedFeatures {
		ASSESSMENT ("Assessment"),
		ASSETBOX ("Asset Box Highlight"),
		CURTAINVIEW ("Curtain View"),
		CUSTOMCOLORS ("Custom Colors"),
		EQ ("EQ"),
		FLEETMAPVIEW ("FleetMap View"),
		GISLAYERS ("GIS Layers"),
		LISAASSETHIGHLIGHT("LISA Asset Highlight"),
		LISABOX10 ("LISA Box 1.0"),
		MANUAL ("Manual"),
		MOBILEVIEW ("Mobile View"),
		OBSERVERVIEW ("Observer View"),
		OPACITYFINETUNING ("Opacity Fine-Tuning"),
		OPERATOR ("Operator"),
		PERCENTCOVERAGE ("Percent Coverage"),
		RAPIDRESPONSE ("Rapid Response"),
		REPORTMETADATA ("Report Metadata"),
		REPORTSHAPEFILE ("Report ShapeFile"),
		SURVEYPROTOCOLFORECAST ("Survey Protocol Forecast");

		private final String name;

		LicensedFeatures(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
		
		public static LicensedFeatures[] values(LicensedFeatures[] excluds){
			if(excluds==null||excluds.length==0){
				return values();
			}
			List<LicensedFeatures>  lflist = new LinkedList<LicensedFeatures>(Arrays.asList(LicensedFeatures.values()));
			lflist.removeAll(Arrays.asList(excluds));
			LicensedFeatures[] lfs = new LicensedFeatures[lflist.size()];
			lfs = lflist.toArray(lfs);
			return lfs;
		}
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

	public static enum ReportColorOption {
		LIGHTBLUE(0),
		BLUE(1),
		LIGHTGREEN(2),
		GREEN(3),
		PINK(4),
		RED(5),
		YELLOW(6),
		ORANGE(7),
		LIGHTPURPLE(8),
		PURPLE(9);

		private final int index;
		ReportColorOption (int index){
			this.index =  index;
		}
		public int toIndex(){
			return index;
		}
	}
	public enum Environment {
		SQAAuto ("CI-SQAAuto", 1),
		Staging ("CI-STG", 2),
		P3Scale ("CI-P3Scale", 3);

		private final String name;
		private final Integer index;

		Environment(String nm) {
			this(nm, -1);
		}

		Environment(String nm, Integer idx) {
			name = nm;
			index = idx;
		}

		public Integer getIndex() {
			return index;
		}

		public String toString() {
			return this.name;
		}

		public static Environment getEnvironment(String environmentName) {
			Environment environment = Environment.P3Scale;
			Environment[] values = Environment.values();
			for (Environment env : values) {
				if (env.toString().equals(environmentName)) {
					environment = env;
					break;
				}
			}
			return environment;
		}
	}

	public static List<String> getCustomersWithAssets() {
		return RegexUtility.split(CUSTOMERS_WITH_ASSETS, RegexUtility.COMMA_SPLIT_REGEX_PATTERN);
	}
}
