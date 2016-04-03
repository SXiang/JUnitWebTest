/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static surveyor.scommon.source.SurveyorConstants.CUSNAMEBASE;
import static surveyor.scommon.source.SurveyorConstants.KEYANNOTATION;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYBASEMAP;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARIES;
import static surveyor.scommon.source.SurveyorConstants.KEYBREADCRUMB;
import static surveyor.scommon.source.SurveyorConstants.KEYFOV;
import static surveyor.scommon.source.SurveyorConstants.KEYGAPS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDICATIONS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDTB;
import static surveyor.scommon.source.SurveyorConstants.KEYISOANA;
import static surveyor.scommon.source.SurveyorConstants.KEYISOTOPICCAPTURE;
import static surveyor.scommon.source.SurveyorConstants.KEYLISA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCRA;
import static surveyor.scommon.source.SurveyorConstants.KEYVIEWNAME;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.SQACUS;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC1SUR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC2SUR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSU;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSUUSER;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSUTAG;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUATAG;
import static surveyor.scommon.source.SurveyorConstants.SQAPICADMANUALTAG;
import static surveyor.scommon.source.SurveyorConstants.SQAPICADRRTAG;
import static surveyor.scommon.source.SurveyorConstants.SQAPICADSTNDTAG;
import static surveyor.scommon.source.SurveyorConstants.SQAPICADTAG;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDRTAG;
import static surveyor.scommon.source.SurveyorConstants.SQAPICSUP;

import static surveyor.scommon.source.SurveyorConstants.SQAPICDRTAG;
import static surveyor.scommon.source.SurveyorConstants.SQAPICLOC3SUR;
import static surveyor.scommon.source.SurveyorConstants.SQAPICSUTAG;

import static surveyor.scommon.source.SurveyorConstants.SQACRPTTAG;
import static surveyor.scommon.source.SurveyorConstants.SURVEYORUNIT;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONECT;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEET;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPHEIGHT;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPWIDTH;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;
import static surveyor.scommon.source.SurveyorConstants.EXCLUSIONRADIUS;
import static surveyor.scommon.source.SurveyorConstants.RNELAT;
import static surveyor.scommon.source.SurveyorConstants.RNELON;
import static surveyor.scommon.source.SurveyorConstants.RSWLAT;
import static surveyor.scommon.source.SurveyorConstants.RSWLON;
import static surveyor.scommon.source.SurveyorConstants.REPORTMODES;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.RSURSTARTDATE;
import static surveyor.scommon.source.SurveyorConstants.RSURENDDATE;
import static surveyor.scommon.source.SurveyorConstants.RSUVMODESTD;
import static surveyor.scommon.source.SurveyorConstants.RSUVMODEOP;
import static surveyor.scommon.source.SurveyorConstants.RSUVMODERR;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSURVEYOR;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEMT;
import static surveyor.scommon.source.SurveyorConstants.PICADMNMANTAG;
import static surveyor.scommon.source.SurveyorConstants.PICADMNRRTAG;
import static surveyor.scommon.source.SurveyorConstants.CUSDRVSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.PICADMNOPTAG;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETCASTIRON;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETCOPPER;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETOTHERPLASTIC;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETPEPLASTIC;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETPROTECTEDSTEEL;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETUNPROTECTEDSTEEL;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARYDISTRICT;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARYDISTRICTPLAT;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEETUA;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONECTUA;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPTUA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.CryptoUtility;
import common.source.Log;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.scommon.source.Coordinates;
import surveyor.scommon.source.EqReportsPage;
import surveyor.scommon.source.LatLongSelectionControl;
import surveyor.scommon.source.ComplianceReportsPage.ComplianceReportButtonType;
import surveyor.scommon.source.ReportsCompliance.SurveyModeFilter;
import surveyor.scommon.source.ReportsEQ;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.dataprovider.ComplianceReportDataProvider;

/**
 * 
 * 
 */
@RunWith(SurveyorTestRunner.class)
public class EQReportsPageTest extends BaseReportsPageTest {
	private static EqReportsPage eqReportsPage = null;
	private static LatLongSelectionControl latLongSelectionControl=null;
	
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
		String testCaseName="EQUnitTest";
		
		List<List<Coordinates>> coordList = new ArrayList<List<Coordinates>>();
		List <Coordinates> listOfCords = new ArrayList <Coordinates>();
		List <Coordinates> listOfCords1 = new ArrayList <Coordinates>();
		listOfCords.add(0, new Coordinates(200,200));
		listOfCords.add(1, new Coordinates(220,300));
		listOfCords.add(2, new Coordinates(240,400));
		listOfCords1.add(0, new Coordinates(100,200));
		listOfCords1.add(1, new Coordinates(120,300));
		listOfCords1.add(2, new Coordinates(140,400));
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