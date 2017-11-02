/**
 *
 */
package surveyor.unittest.source;


import static org.junit.Assert.*;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import surveyor.dataaccess.source.CustomerWithGisDataPool;
import surveyor.dataaccess.source.SurveyorUnit;
import surveyor.dataaccess.source.Analyzer;
import surveyor.dataaccess.source.Analyzer.CapabilityType;
import surveyor.regression.source.BaseMapViewTest;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.SurveyorTestRunner;
import common.source.BaseHelper;
import common.source.Log;
import common.source.TestContext;

@RunWith(SurveyorTestRunner.class)
public class BaseUnitTest extends BaseMapViewTest {
	private static final String automationReportingApiEndpoint = "http://localhost:63087";
	private static String CustomerNamePrefix = "AutomationSeedCustomer";
	private static Integer CUSTOMERS_NEEDED_BY_TESTS = 3;

	private static LoginPage loginPage;

	@BeforeClass
	public static void beforeTestClass() {
		initializeTestObjects();

		TestContext.INSTANCE.getTestSetup().setAutomationReportingApiEndpoint(automationReportingApiEndpoint);

		releaseCustomersNeededByTestsFromPool();
	}

	@Before
	public void beforeTestMethod() {
		PageObjectFactory pageObjectFactory = new PageObjectFactory();

		loginPage = pageObjectFactory.getLoginPage();
		setLoginPage(loginPage);
		PageFactory.initElements(getDriver(), loginPage);
	}

	/**
	 * Test Description: Verify a GIS customer is fetched correctly from pool.
	 */
	@Test
	public void createTestAccountWithGisCustomer_CapabilityTypeAndReleaseVerification() throws Exception{
		Log.info("\nTestcase - fetchNewGisCustomer_DefaultCustomer_LicensedFeaturesNotSpecified\n");

		Map<String, String> testAccount = createTestAccountWithGisCustomer("UNITTEST_TC", CapabilityType.Ethane);
		String userName = testAccount.get("userName");
		String userPassword = testAccount.get("userPassword");
		String customerName = testAccount.get("customerName");
		String locationName = testAccount.get("locationName");
		String analyzerSharedKey = testAccount.get("analyzerSharedKey");
		String analyzerName = testAccount.get("analyzerName");
		String analyzerType = testAccount.get("analyzerType");
		String surveyorName = testAccount.get("surveyorName");
		String customerId = testAccount.get("customerId");

		assertTrue("Username should NOT be EMPTY!", userName != null);
		assertTrue("Password should NOT be EMPTY!", userPassword != null);
		assertTrue("CustomerName should NOT be EMPTY!", customerName != null);
		assertTrue("LocationName should NOT be EMPTY!", locationName != null);
		assertTrue("AnalyzerSharedKey should NOT be EMPTY!", analyzerSharedKey != null);
		assertTrue("AnalyzerName should NOT be EMPTY!", analyzerName != null);
		assertTrue("AnalyzerType should NOT be EMPTY!", analyzerType != null);
		assertTrue("SurveyorName should NOT be EMPTY!", surveyorName != null);
		assertTrue("CustomerId should NOT be EMPTY!", customerId != null);

		List<Analyzer> analyzersForCustomer = Analyzer.getAnalyzersForCustomer(customerId);
		boolean allMatch = analyzersForCustomer.stream()
			.allMatch(a -> a.getCapabilityTypes().stream().allMatch(ct -> ct.equals(CapabilityType.Ethane)));

		assertTrue("CapabilityType for all customer Analyzers should be 'Ethane'.", allMatch);

		CustomerWithGisDataPool.releaseCustomer(customerName);
		analyzersForCustomer = Analyzer.getAnalyzersForCustomer(customerId);
		assertTrue("No Analyzers should be present for the Customer once released.", analyzersForCustomer == null || analyzersForCustomer.size()==0);

		List<SurveyorUnit> surveyorUnits = SurveyorUnit.getSurveyorUnitsByCustomer(customerName);
		assertTrue("No SurveyorUnits should be present for the Customer once released.", surveyorUnits == null || surveyorUnits.size()==0);
	}

	private static void releaseCustomersNeededByTestsFromPool() {
		IntStream.range(0, CUSTOMERS_NEEDED_BY_TESTS)
			.forEach(i -> {
				Integer padTimes = 5;
				Integer n = i + 1;
				while (n > 0) {
					n = n / 10;
					padTimes--;
				}

				Integer idx = i + 1;
				String customerName = CustomerNamePrefix + BaseHelper.prependStringWithChar(String.valueOf(idx), '0', padTimes);
				try {
					CustomerWithGisDataPool.releaseCustomer(customerName);
				} catch (Exception e) {
					fail(e.getMessage());
				}
			});
	}
}
