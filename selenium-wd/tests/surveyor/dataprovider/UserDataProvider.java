package surveyor.dataprovider;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;
import surveyor.scommon.source.SurveyorTestRunner;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORDHASH;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDR;
import static surveyor.scommon.source.SurveyorConstants.SQAPICAD;
import static surveyor.scommon.source.SurveyorConstants.SQAPICSUP;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.SQAPGEUA;
import static surveyor.scommon.source.SurveyorConstants.SQAPGESU;
import static surveyor.scommon.source.SurveyorConstants.SQAPGEDR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSU;


public class UserDataProvider extends SurveyorTestRunner {
	public static final String USER_ROLE_INFO_PROVIDER = "dataProviderUserRoleInfo";
	public static final String USER_ADMIN_SUPPORT_PROVIDER = "dataProviderPicarroAdminSupportRoleInfo";

	public UserDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}
	
    @Override 
    public void run(RunNotifier notifier){
        super.run(notifier);
    }

	/**********************************************************************
	 * #NOTE#: Password provided in the data provider will get printed
	 * in teamcity UI (run result) and therefore needs to be an encrypted string
	 * Use the CryptoUtility.encrypt() method to encrypt the password 
	 **********************************************************************/
	
    @DataProvider
    public static Object[][] dataProviderUserRoleInfo() {
        // @formatter:off"
        return new String[][] {		// Username, Password(ENCRYPTED), Role, CustomerName, CustomerLocation
        	{ SQACUSUA,USERPASSWORDHASH,"Customer Utility Admin","sqacus","sqacusloc" },
        	{ SQACUSSU,USERPASSWORDHASH,"Customer Supervisor","sqacus","sqaTestloc" },
        	{ SQACUSDR,USERPASSWORDHASH,"Customer Driver","sqacus","sqacusloc" },
        	{ SQAPICAD,USERPASSWORDHASH,"Picarro Admin","Picarro","Santa Clara" },
        	{ SQAPICSUP,USERPASSWORDHASH,"Picarro Support","Picarro","Santa Clara" },
        	{ PICDFADMIN,USERPASSWORDHASH,"Picarro Admin","Picarro","Santa Clara" },
        	{ SQAPGEUA,USERPASSWORDHASH,"PGE Customer Utility Admin","PG&E","pge_SC" },
        	{SQAPGESU,USERPASSWORDHASH,"PGE Customer Supervisor","PG&E","pge_SC" },
        	{ SQAPGEDR,USERPASSWORDHASH,"PGE Customer Driver","PG&E","pge_SC" }
            };
        // @formatter:on
    }
    
    @DataProvider
    public static Object[][] dataProviderPicarroUserRoleInfo() {
        // @formatter:off"
        return new String[][] {		// Username, Password(ENCRYPTED), Role, CustomerName, CustomerLocation
        	{ SQAPICAD,USERPASSWORDHASH,"Picarro Admin","Picarro","Santa Clara" },
        	{ SQAPICSUP,USERPASSWORDHASH,"Picarro Support","Picarro","Santa Clara" }
            };
        // @formatter:on
    }
    
    @DataProvider
    public static Object[][] dataProviderPicarroAdminSupportRoleInfo() {
        // @formatter:off"
        return new String[][] {		
        		{ SQAPICSUP,USERPASSWORDHASH },
                { PICDFADMIN,USERPASSWORDHASH }
        	
            };
        // @formatter:on
    }
}