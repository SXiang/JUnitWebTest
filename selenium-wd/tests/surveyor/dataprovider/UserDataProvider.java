package surveyor.dataprovider;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;
import surveyor.scommon.source.SurveyorTestRunner;

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
        	{ "sqacusua@email.com","oeHwHqmv621dZ1MRE2BSdw==","Customer Utility Admin","sqacus","sqacusloc" },
        	{ "sqacussu@email.com","oeHwHqmv621dZ1MRE2BSdw==","Customer Supervisor","sqacus","sqaTestloc" },
        	{ "sqacusdr1@email.com","oeHwHqmv621dZ1MRE2BSdw==","Customer Driver","sqacus","sqacusloc" },
        	{ "sqapicad@picarro.com","oeHwHqmv621dZ1MRE2BSdw==","Picarro Admin","Picarro","Santa Clara" },
        	{ "sqapicsup@picarro.com","oeHwHqmv621dZ1MRE2BSdw==","Picarro Support","Picarro","Santa Clara" },
        	{ "Administrator","6NZYEwkq24lm9xBekD04sg==","Picarro Admin","Picarro","Santa Clara" },
        	{ "sqapgeua@email.com","oeHwHqmv621dZ1MRE2BSdw==","PGE Customer Utility Admin","PG&E","pge_SC" },
        	{ "sqapgesu@email.com","oeHwHqmv621dZ1MRE2BSdw==","PGE Customer Supervisor","PG&E","pge_SC" },
        	{ "sqapgedr1@email.com","oeHwHqmv621dZ1MRE2BSdw==","PGE Customer Driver","PG&E","pge_SC" }
            };
        // @formatter:on
    }
    
    @DataProvider
    public static Object[][] dataProviderPicarroUserRoleInfo() {
        // @formatter:off"
        return new String[][] {		// Username, Password(ENCRYPTED), Role, CustomerName, CustomerLocation
        	{ "sqapicad@picarro.com","oeHwHqmv621dZ1MRE2BSdw==","Picarro Admin","Picarro","Santa Clara" },
        	{ "sqapicsup@picarro.com","oeHwHqmv621dZ1MRE2BSdw==","Picarro Support","Picarro","Santa Clara" }
            };
        // @formatter:on
    }
    
    @DataProvider
    public static Object[][] dataProviderPicarroAdminSupportRoleInfo() {
        // @formatter:off"
        return new String[][] {		// Username, Password(NonENCRYPTED)
        	//{ "sqapicsup@picarro.com","sqa#Picarro$0" },
        	//{ "Administrator","FastLane!911" }
        		{ "sqapicsup@picarro.com","oeHwHqmv621dZ1MRE2BSdw==" },
                { "Administrator","6NZYEwkq24lm9xBekD04sg==" }
        	
            };
        // @formatter:on
    }
}