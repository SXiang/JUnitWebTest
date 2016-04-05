/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEMT;
import java.util.ArrayList;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;
import surveyor.scommon.source.Coordinates;
import surveyor.scommon.source.EqReportsPage;
import surveyor.scommon.source.LatLongSelectionControl;
import surveyor.scommon.source.BaseReportsPageTest;
import surveyor.scommon.source.ReportsEQ;
import surveyor.scommon.source.SurveyorTestRunner;

/**
 * 
 * 
 */
@RunWith(SurveyorTestRunner.class)
public class EQReportsPageTest extends BaseReportsPageTest {
	private static EqReportsPage eqReportsPage = null;
	private static LatLongSelectionControl latLongSelectionControl = null;

	@BeforeClass
	public static void setupComplianceReportsPageTest() {
		initializePageObjects();

	}

	private static void initializePageObjects() {
		eqReportsPage = new EqReportsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, eqReportsPage);
		latLongSelectionControl = new LatLongSelectionControl(driver);
		PageFactory.initElements(driver, latLongSelectionControl);
	}

	/**
	 * Unit Test
	 * 
	 * 
	 */
	@Test
	public void ComplianceReportTest_VerifyNonEthaneReport() throws Exception {
		eqReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		eqReportsPage.open();
		String testCaseName = "EQUnitTest";

		List<List<Coordinates>> coordList = new ArrayList<List<Coordinates>>();
		List<Coordinates> listOfCords = new ArrayList<Coordinates>();
		List<Coordinates> listOfCords1 = new ArrayList<Coordinates>();
		listOfCords.add(0, new Coordinates(200, 200));
		listOfCords.add(1, new Coordinates(220, 300));
		listOfCords.add(2, new Coordinates(240, 400));
		listOfCords1.add(0, new Coordinates(100, 200));
		listOfCords1.add(1, new Coordinates(120, 300));
		listOfCords1.add(2, new Coordinates(140, 400));
		coordList.add(listOfCords);
		coordList.add(listOfCords1);

		String rptTitle = "Report" + testSetup.getRandomNumber();

		List<String> tagList = new ArrayList<String>();
		tagList.add("EQGPSoffset");

		ReportsEQ eqRpt = new ReportsEQ(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "", tagList, coordList);

		eqReportsPage.addNewReport(eqRpt);

		if ((eqReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseName))) {
			assertTrue(eqReportsPage.validatePdfFiles(rptTitle, testSetup.getDownloadPath()));
		}
		assertTrue(eqReportsPage.verifyDrivingSurveysTable(rptTitle, testSetup.getDownloadPath()));
		assertTrue(eqReportsPage.verifyEmissionQuantificationDataTable(rptTitle, testSetup.getDownloadPath()));
		assertTrue(eqReportsPage.verifyViewsImages(testSetup.getDownloadPath(), rptTitle, testCaseName, testCaseName));

	}

}