package surveyor.unittest.source;

import org.junit.Test;
import org.junit.Assert;

import common.source.Log;
import common.source.SystemUtility;

public class SystemUtilityUnitTest {
	
	@Test
	public void AppDataFolderPathTest() {
		String appDataFolder = SystemUtility.getAppDataFolder();
		Log.info(String.format("AppDataFolder is: %s", appDataFolder));
		String osLower = System.getProperty("os.name").toLowerCase();

		Assert.assertTrue(appDataFolder != null);
		if (osLower.contains("win")) {
			Assert.assertTrue(appDataFolder.contains("AppData"));
			Assert.assertTrue(appDataFolder.contains("Local"));
		}
	}
}
