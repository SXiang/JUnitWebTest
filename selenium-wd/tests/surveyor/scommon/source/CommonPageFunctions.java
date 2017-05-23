package surveyor.scommon.source;

import java.util.List;

import common.source.BaseHelper;
import common.source.Log;
import common.source.LogHelper;
import common.source.TestContext;
import surveyor.scommon.entities.ServerLogEntity;

public class CommonPageFunctions {
	public static List<ServerLogEntity> getServerLog(String searchTerm, Integer numRecords) {
		Log.method("getServerLog", searchTerm, numRecords);
		PageObjectFactory pageObjectFactory = new PageObjectFactory();

		// store current user/pwd for later login. Server log retrieval happens with PicAdmin creds.
		String loginUser = TestContext.INSTANCE.getLoggedInUser();
		String loginPwd = TestContext.INSTANCE.getLoggedInPassword();

		LoginPage loginPage = pageObjectFactory.getLoginPage();
		loginPage.open();
		loginPage.loginNormalAs(TestContext.INSTANCE.getTestSetup().getLoginUser(), TestContext.INSTANCE.getTestSetup().getLoginPwd());

		ServerLogPage serverLogPage = pageObjectFactory.getServerLogPage();
		serverLogPage.open();
		serverLogPage.waitForPageLoad();
		List<ServerLogEntity> matchingLogs = serverLogPage.getMatchingLogs(searchTerm, numRecords);

		// restore logged-in context.
		if (!BaseHelper.isNullOrEmpty(loginUser)) {
			loginPage.open();
			loginPage.loginNormalAs(loginUser, loginPwd);
		}

		Log.info(String.format("Found matching logs -> %s", LogHelper.collectionToString(matchingLogs, "ServerLogEntities")));
		return matchingLogs;
	}
}
