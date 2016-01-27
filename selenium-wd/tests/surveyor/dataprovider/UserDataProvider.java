package surveyor.dataprovider;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class UserDataProvider {

    @DataProvider
    public static Object[][] dataProviderUserRoleInfo() {
        // @formatter:off
        return new String[][] {		// Username, Password, Role
        	{ "sqacusua@email.com","sqa#Picarro$0","Customer Utility Admin" },
        	{ "sqacussu@email.com","sqa#Picarro$0","Customer Supervisor" },
        	{ "sqacusdr1@email.com","sqa#Picarro$0","Customer Driver" },
        	{ "sqapicad@picarro.com","sqa#Picarro$0","Picarro Admin" },
        	{ "sqapicsu1@picarro.com","sqa#Picarro$0","Picarro Support" },
        	{ "Administrator","FastLane!911","Picarro Admin" },
        	{ "sqapgeua@email.com","sqa#Picarro$0","PGE Customer Utility Admin" },
        	{ "sqapgesu@email.com","sqa#Picarro$0","PGE Customer Supervisor" },
        	{ "sqapgedr1@email.com","sqa#Picarro$0","PGE Customer Driver" }
            };
        // @formatter:on
    }
}