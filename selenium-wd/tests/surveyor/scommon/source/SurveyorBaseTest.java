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
public class SurveyorBaseTest extends BaseTest{

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
		testSetup = new TestSetup();
		BaseTest.initializeTestObjects();
	}
	
}