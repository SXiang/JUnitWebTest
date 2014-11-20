/**
 * 
 */
package surveyor.scommon.source;

/**
 * @author zlu
 *
 */
public final class SurveyorConstants {
	/**
	 * 
	 */
	private SurveyorConstants() {
	}
	
	public static final String BASECUSTOMERNAME = "Cus";
	public static final String BASELOCATIONNAME = "Loc";
	public static final String BASESURVEYORNAME = "Sur";
	public static final String BASEANALYZERNAME = "";
	public static final String ANALYZERSHAREDKEY = "sqa#Picarro$0";	
	
	public static final String BASEUSERNAME = "SQA@picarro.com";
	public static final String USERPASSWORD = "sqa#Picarro$0";
	
	public static final int CUSTOMERNUM = 50; //Should be set less than 100 otherwise need review the code
	public static final int LOCATIONNUM = 5; //Should be set less than 100 otherwise need review the code
	public static final int SURVEYORNUM = 1; //Should be set less than 100 otherwise need review the code
	public static final int ANALYZERNUM = 1; //Should be set less than 100 otherwise need review the code
	public static final int USERNUM = 5;    //Should be set less than 100 otherwise need review the code
	
	public static final String CUSTOMERNAMEPREFIX = "regcus";
	public static final String CUSTOMERSTATUS = "Enabled";
	public static final String EULASTRING = "Testing eula string, TBD";
	public static final String REGBASEUSERNAME = "@email.com";
	
	public static final String PICNAMEPREFIX = "sqapic";
	public static final String REGBASEPICUSERNAME = "@picarro.com";
	
	public static final String CUSNAMEBASE = "sqacus";
	public static final String CUSNAMEBASELOC = "sqacusloc";
	public static final String CUSNAMEBASESUR = "sqacuslocsur";
	public static final String CUSNAMEBASEANA = "sqacuslocsurana";
	
	public static final String SQACUS = "sqacus";
	public static final String SQACUSLOC = "sqacusloc";
	public static final String SQAPIC = "sqapic";
	public static final String SQAPICLOC = "sqapicloc";
	
	public static final String USERROLEADMIN = "Administrator";
	public static final String CUSUSERROLEUA = "Utility Administrator";
	public static final String CUSUSERROLESU = "Supervisor";
	public static final String CUSUSERROLEDR = "Driver";
	
	public static final String PICDFADMIN = "Administrator";
	public static final String SQACUSUAUSER = "sqacusua";
	public static final String SQACUSSUUSER = "sqacussu";
	public static final String SQACUSDRUSER = "sqacusdr";
	public static final String SQAPICADUSER = "sqapicad";
	public static final String SQAPICUAUSER = "sqapicua";
	public static final String SQAPICSUUSER = "sqapicsu";
	public static final String SQAPICDRUSER = "sqapicdr";
	
	public static final String SQACUSUA = "sqacusua@email.com";
	public static final String SQACUSSU = "sqacussu@email.com";
	public static final String SQACUSDR = "sqacusdr@email.com";
	public static final String SQAPICAD = "sqapicad@picarro.com";
	public static final String SQAPICUA = "sqapicua@picarro.com";
	public static final String SQAPICSU = "sqapicsu@picarro.com";
	public static final String SQAPICDR = "sqapicdr@picarro.com";
	
	public static final String SQAPICLOCSUR = "sqapiclocsur";
	public static final String SQAPICLOC0SUR = "sqapicloc0sur";
	public static final String SQAPICLOC1SUR = "sqapicloc1sur";
	public static final String SQAPICLOC2SUR = "sqapicloc2sur";
	public static final String SQAPICLOC3SUR = "sqapicloc3sur";
	public static final String SQACUSLOCSUR = "sqacuslocsur";
	public static final String SQACUSLOC0SUR = "sqacusloc0sur";
	public static final String SQACUSLOC1SUR = "sqacusloc1sur";
	public static final String SQACUSLOC2SUR = "sqacusloc2sur";
	public static final String SQACUSLOC3SUR = "sqacusloc3sur";
	
	public static final String SQACUSUATAG = "dmcs1-sqacusua";
	public static final String SQACUSSUTAG = "dmcs1-sqacussu";
	public static final String SQACUSDRTAG = "dmcs1-sqacusdr";
	public static final String SQAPICADTAG = "dmcs1-sqapicad";
	public static final String SQAPICUATAG = "dmcs1-sqapicua";
	public static final String SQAPICSUTAG = "dmcs1-sqapicsu";
	public static final String SQAPICDRTAG = "dmcs1-sqapicdr";
	
	public static final String RGBNAMEBASE = "rgb";
	
	public static String STRFEEDBACK = "This is sqa test feedback message";
	
	public static final String TITLENAMEBASE = "sqacrpt";
	public static final String TIMEZONE = "Pacific Standard Time";
	public static final String EXCLUSIONRADIUS = "3";
	public static final String REPORTMODES1 = "S1";
	
	public static final String CUSBOUNDARY = "Custom Boundary";
	public static final String IMGMAPHEIGHT = "5";
	public static final String IMGMAPWIDTH = "5";
	public static final String NELAT = "37.4353397926825";
	public static final String NELON = "-121.84696197509766";
	public static final String SWLAT = "37.33058362073965";
	public static final String SWLON = "-122.04883575439453";
	
	public static final String SURVEYORUNIT = "sqacus - sqacusloc - sqacuslocsur";
	public static final String TAG = "dmcs1-sqa01";
	
	public static final String STARTDATE = "01/01/2014";
	public static final String ENDDATE = "08/14/2014";
	
	public static final int ACTIONTIMEOUT = 300; //in seconds
	
	//public static final String PAGINATIONSETTING = "100";
	public static final String PAGINATIONSETTING = "10"; //Selenium WebDriver issues so set it to 10 only for now
	
	public static final String USERENABLED = "Enabled";
	public static final String USERDISABLED = "Disabled";
	
	public static final String CUSTOMERENABLED = "Enabled";
	public static final String CUSTOMERDISABLED = "Disabled";
	
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
	
	public static final String DUPLICATIONERROR = "Please, correct the following errors:";
	public static final String PWVALUEERROR = "Please enter the same value again.";
	public static final String EMAILINVALID = "The Email is not a valid , please enter valid email address";
	public static final String BLANKFIELDERROR = "This field is required.";
	public static final String PWDSAMEVALUE = "Please enter the same value again.";
	public static final String EMAILTOOLONG = "Please enter less than 50 characters.";
	
	public static final String NOMATCHINGSEARCH = "No matching records found";
}