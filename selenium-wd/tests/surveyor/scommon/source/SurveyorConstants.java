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
	
	public static final String CUSTOMERNAMEPREFIX = "RegCus";
	public static final String CUSTOMERSTATUS = "Enabled";
	public static final String EULASTRING = "Testing";
	public static final String REGBASEUSERNAME = "@email.com";
	
	public static final String PICNAMEPREFIX = "sqapic";
	public static final String REGBASEPICUSERNAME = "@picarro.com";
	
	public static final String CUSNAMEBASE = "sqacus";
	public static final String CUSNAMEBASELOC = "sqacusloc";
	public static final String CUSNAMEBASESUR = "sqacuslocsur";
	public static final String CUSNAMEBASEANA = "sqacuslocsurana";
	
	public static final String USERROLEADMIN = "Administrator";
	public static final String CUSUSERROLEUA = "Utility Administrator";
	public static final String CUSUSERROLESU = "Supervisor";
	public static final String CUSUSERROLEDR = "Driver";
	
	public static final String PICDFADMIN = "Administrator";
	public static final String SQACUSUA = "sqacusua@email.com";
	public static final String SQACUSSU = "sqacussu@email.com";
	public static final String SQACUSDR = "sqacusdr@email.com";
	
	public static final String SQAPICAD = "sqapicad@picarro.com";
	public static final String SQAPICUA = "sqapicua@picarro.com";
	public static final String SQAPICSU = "sqapicsu@picarro.com";
	public static final String SQAPICDR = "sqapicdr@picarro.com";
	
	public static final String SQACUSUATAG = "dmcs1-sqacusua";
	public static final String SQACUSSUTAG = "dmcs1-sqacussu";
	public static final String SQACUSDRTAG = "dmcs1-sqacusdr";
	
	public static final String SQAPICADTAG = "dmcs1-sqapicad";
	public static final String SQAPICUATAG = "dmcs1-sqapicua";
	public static final String SQAPICSUTAG = "dmcs1-sqapicsu";
	public static final String SQAPICDRTAG = "dmcs1-sqapicdr";
	
	public static final String RGBNAMEBASE = "rgb";
	
	public static String STRFEEDBACK = "This is sqa test feedback message";
}
