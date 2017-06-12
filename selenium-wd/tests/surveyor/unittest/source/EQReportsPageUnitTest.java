package surveyor.unittest.source;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;
import org.junit.Test;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.EQReportsPage;

@RunWith(SurveyorTestRunner.class)
public class EQReportsPageUnitTest  extends BaseReportsPageActionTest {

	private static EQReportsPage eqReportsPage;

	@BeforeClass
	public static void beforeTestClass() throws Exception {
		initializeTestObjects();

		eqReportsPage = new EQReportsPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(), eqReportsPage);
	}

	@Test
	public void verifyEmissionsQuantificationTable() throws IOException {
		final String reportTitle = "TC532-4c986b42ed1e4b47b655";
		final String actualPath = "C:\\Users\\spulikkal\\Downloads";
		assertTrue(eqReportsPage.verifyEmissionsQuantificationTable(actualPath, reportTitle));
	}
}
