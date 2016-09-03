/**
 * 
 */
package surveyor.scommon.source;

import org.junit.BeforeClass;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */

public class SurveyorBaseTest extends BaseTest {

	protected static final String SQAPICAD_AND_SQAPICSUP = "sqapicad@picarro.com,sqapicsup@picarro.com";
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		if (!TestSetup.isParallelBuildEnabled()) {
			TestSetup.stopChromeProcesses();
		}
		initializeTestObjects();
	}

	public static void initializeTestObjects(){
		BaseTest.setTestSetup(new TestSetup());
		BaseTest.initializeTestObjects();
	}
}