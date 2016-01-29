package surveyor.dataprovider;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class UserDataProvider {

	public static final String USER_ROLE_INFO_PROVIDER = "dataProviderUserRoleInfo";
	
    @DataProvider
    public static Object[][] dataProviderUserRoleInfo() {
        // @formatter:off"
        return new String[][] {		// Username, Password, Role, CustomerName, CustomerLocation
        	{ "sqacusua@email.com","sqa#Picarro$0","Customer Utility Admin","sqacus","sqacusloc" },
        	{ "sqacussu@email.com","sqa#Picarro$0","Customer Supervisor","sqacus","sqaTestloc" },
        	{ "sqacusdr1@email.com","sqa#Picarro$0","Customer Driver","sqacus","sqacusloc" },
        	{ "sqapicad@picarro.com","sqa#Picarro$0","Picarro Admin","Picarro","Santa Clara" },
        	{ "sqapicsup@picarro.com","sqa#Picarro$0","Picarro Support","Picarro","Santa Clara" },
        	{ "Administrator","FastLane!911","Picarro Admin","Picarro","Santa Clara" },
        	{ "sqapgeua@email.com","sqa#Picarro$0","PGE Customer Utility Admin","PG&E","pge_SC" },
        	{ "sqapgesu@email.com","sqa#Picarro$0","PGE Customer Supervisor","PG&E","pge_SC" },
        	{ "sqapgedr1@email.com","sqa#Picarro$0","PGE Customer Driver","PG&E","pge_SC" }
            };
        // @formatter:on
    }
}