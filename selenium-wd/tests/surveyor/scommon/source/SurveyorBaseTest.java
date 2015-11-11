/**
 * 
 */
package surveyor.scommon.source;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class SurveyorBaseTest {
	public static WebDriver driver;
	public static TestSetup testSetup;
	public static String baseURL;
	public static String screenShotsDir;
	public static boolean debug;

	public static LoginPage loginPage;
	public static HomePage homePage;

	// JUnit does NOT give a good way to detect which TestClass is executing.
	// So we watch for the Test method under execution and install simulator pre-reqs
	// if the test under execution is a Simulator test.
	// NOTE that all simulator tests MUST follow this naming pattern: TC*_SimulatorTest_* 
	@Rule
	public TestWatcher watcher = new TestWatcher() {
		@Override
		protected void starting(Description description) {
			System.out.println("Started executing " + description.getClassName() + "." + description.getMethodName() + "() test...");
			if (isExecutingSimulatorTestMethod(description.getMethodName())) {
				System.out.println("Installing simulator pre-reqs. Start Analyzer and Replay DB3 script.");
				try {
					TestSetup.setupSimulatorPreReqs();
					TestSetup.startAnalyzer();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}
		
		@Override
		protected void finished(Description description) {
			System.out.println("Finished executing " + description.getClassName() + "." + description.getMethodName() + "() test...");
			if (isExecutingSimulatorTestMethod(description.getMethodName())) {
				System.out.println("Stop Analyzer.");
				TestSetup.stopAnalyzer();
			}
		}
	};
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testSetup = new TestSetup();
		driver = testSetup.getDriver();
		baseURL = testSetup.getBaseUrl();
		screenShotsDir = "./screenshots/";
		debug = testSetup.isRunningDebug();
		
		System.out.println("debuggug null - driver:***:" +driver);
		driver.manage().deleteAllCookies();
		
		loginPage = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  loginPage);
		
		homePage = new HomePage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  homePage);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		homePage.open();
		
		if (!driver.getTitle().equalsIgnoreCase("Login"))
			homePage.logout();
		
		driver.quit();		
	}

	private static boolean isExecutingSimulatorTestMethod(String methodName) {
		String[] nameParts = methodName.split("\\_");
		if (nameParts != null && nameParts.length > 1)
		{
			if (nameParts[1].equalsIgnoreCase("SimulatorTest")) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
}