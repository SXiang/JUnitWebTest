package surveyor.dataprovider;

import static surveyor.scommon.source.SurveyorConstants.CUSDRVSTDTAG3200;
import static surveyor.scommon.source.SurveyorConstants.CUSDRVSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.EXCLUSIONRADIUS;
import static surveyor.scommon.source.SurveyorConstants.PICADMMANTAG;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDRTAG;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSU;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;
import static surveyor.scommon.source.SurveyorConstants.SQAPICSUP;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSURVEYOR;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONECT;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEET;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEMT;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORDHASH;
import static surveyor.scommon.source.SurveyorConstants.CUSDRVSTDTAG2;
import static surveyor.scommon.source.SurveyorConstants.CUSDRVOPTAG2;
import static surveyor.scommon.source.SurveyorConstants.CUSTOMER_SQACUS;
import static surveyor.scommon.source.SurveyorConstants.EXCLUSIONRADIUS_100;
import static surveyor.scommon.source.SurveyorConstants.EXCLUSIONRADIUS_150;
import static surveyor.scommon.source.SurveyorConstants.EXCLUSIONRADIUS_0;
import static surveyor.scommon.source.SurveyorConstants.PIC8HR01TAG;
import static surveyor.scommon.source.SurveyorConstants.PIC8HR02TAG;
import static surveyor.scommon.source.SurveyorConstants.PIC8HR03TAG;
import static surveyor.scommon.source.SurveyorConstants.PIC8HR04TAG;
import static surveyor.scommon.source.SurveyorConstants.PIC8HR05TAG;
import static surveyor.scommon.source.SurveyorConstants.PIC8HR06TAG;
import static surveyor.scommon.source.SurveyorConstants.PIC8HR07TAG;
import static surveyor.scommon.source.SurveyorConstants.PIC8HR08TAG;
import static surveyor.scommon.source.SurveyorConstants.PIC8HR09TAG;
import static surveyor.scommon.source.SurveyorConstants.PIC8HR10TAG;
import static surveyor.scommon.source.SurveyorConstants.PIC8HR11TAG;
import static surveyor.scommon.source.SurveyorConstants.PIC8HR12TAG;
import static surveyor.scommon.source.SurveyorConstants.PICLESS4HRTAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.source.ReportsCompliance.EthaneFilter;

public class ComplianceReportDataProvider extends ReportDataProvider {
	public static final String COMPLIANCE_REPORT_PROVIDER = "dataProviderComplianceReport";
	public static final String COMPLIANCE_REPORT_LOAD_TESTS_PROVIDER = "dataProviderLoadTestsComplianceReport";
	public static final String COMPLIANCE_REPORT_PROVIDER_SET11 = "dataProviderComplianceReport11";
	public static final String COMPLIANCE_REPORT_PROVIDER_SET11_INVESTIGATION = "dataProviderComplianceReport11NoDownload";

	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC231 = "dataProviderPageActionsComplianceReports_TC231";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC235 = "dataProviderPageActionsComplianceReports_TC235";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC237 = "dataProviderPageActionsComplianceReports_TC237";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC509 = "dataProviderPageActionsComplianceReports_TC509";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC510 = "dataProviderPageActionsComplianceReports_TC510";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC514 = "dataProviderPageActionsComplianceReports_TC514";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC520 = "dataProviderPageActionsComplianceReports_TC520";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC521 = "dataProviderPageActionsComplianceReports_TC521";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC526 = "dataProviderPageActionsComplianceReports_TC526";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC678 = "dataProviderPageActionsComplianceReports_TC678";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC680 = "dataProviderPageActionsComplianceReports_TC680";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC681 = "dataProviderPageActionsComplianceReports_TC681";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC689 = "dataProviderPageActionsComplianceReports_TC689";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC690 = "dataProviderPageActionsComplianceReports_TC690";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC698 = "dataProviderPageActionsComplianceReports_TC698";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC699 = "dataProviderPageActionsComplianceReports_TC699";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC700 = "dataProviderPageActionsComplianceReports_TC700";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC701 = "dataProviderPageActionsComplianceReports_TC701";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC706 = "dataProviderPageActionsComplianceReports_TC706";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC719 = "dataProviderPageActionsComplianceReports_TC719";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC722 = "dataProviderPageActionsComplianceReports_TC722";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC736 = "dataProviderPageActionsComplianceReports_TC736";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC737 = "dataProviderPageActionsComplianceReports_TC737";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC786 = "dataProviderPageActionsComplianceReports_TC786";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC798 = "dataProviderPageActionsComplianceReports_TC798";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC204 = "dataProviderPageActionsComplianceReports_TC204";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC210 = "dataProviderPageActionsComplianceReports_TC210";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC227 = "dataProviderPageActionsComplianceReports_TC227";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1522 = "dataProviderPageActionsComplianceReports_TC1522";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1523 = "dataProviderPageActionsComplianceReports_TC1523";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1524 = "dataProviderPageActionsComplianceReports_TC1524";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1525 = "dataProviderPageActionsComplianceReports_TC1525";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1608 = "dataProviderPageActionsComplianceReports_TC1608";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1532 = "dataProviderPageActionsComplianceReports_TC1532";
	// ComplianceReportsPageTest3
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC12 = "dataProviderPageActionsComplianceReports_TC12";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC13 = "dataProviderPageActionsComplianceReports_TC13";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1319 = "dataProviderPageActionsComplianceReports_TC1319";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1320 = "dataProviderPageActionsComplianceReports_TC1320";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1339 = "dataProviderPageActionsComplianceReports_TC1339";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1340 = "dataProviderPageActionsComplianceReports_TC1340";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1352 = "dataProviderPageActionsComplianceReports_TC1352";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1363 = "dataProviderPageActionsComplianceReports_TC1363";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1364 = "dataProviderPageActionsComplianceReports_TC1364";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1365 = "dataProviderPageActionsComplianceReports_TC1365";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1366 = "dataProviderPageActionsComplianceReports_TC1366";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1367 = "dataProviderPageActionsComplianceReports_TC1367";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1370 = "dataProviderPageActionsComplianceReports_TC1371";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1371 = "dataProviderPageActionsComplianceReports_TC1371";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1373 = "dataProviderPageActionsComplianceReports_TC1373";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1389 = "dataProviderPageActionsComplianceReports_TC1389";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1394 = "dataProviderPageActionsComplianceReports_TC1394";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1395 = "dataProviderPageActionsComplianceReports_TC1395";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC14 = "dataProviderPageActionsComplianceReports_TC14";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC149 = "dataProviderPageActionsComplianceReports_TC149";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1490 = "dataProviderPageActionsComplianceReports_TC1490";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1491 = "dataProviderPageActionsComplianceReports_TC1491";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1496 = "dataProviderPageActionsComplianceReports_TC1496";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1497 = "dataProviderPageActionsComplianceReports_TC1497";

	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1255 = "dataProviderPageActionsComplianceReports_TC1255";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1256 = "dataProviderPageActionsComplianceReports_TC1256";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1274 = "dataProviderPageActionsComplianceReports_TC1274";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1298 = "dataProviderPageActionsComplianceReports_TC1298";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1314 = "dataProviderPageActionsComplianceReports_TC1314";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1353 = "dataProviderPageActionsComplianceReports_TC1353";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1354 = "dataProviderPageActionsComplianceReports_TC1354";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1355 = "dataProviderPageActionsComplianceReports_TC1355";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1356 = "dataProviderPageActionsComplianceReports_TC1356";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1357 = "dataProviderPageActionsComplianceReports_TC1357";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1384 = "dataProviderPageActionsComplianceReports_TC1384";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1498 = "dataProviderPageActionsComplianceReports_TC1498";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1499 = "dataProviderPageActionsComplianceReports_TC1499";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC15 = "dataProviderPageActionsComplianceReports_TC15";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1500 = "dataProviderPageActionsComplianceReports_TC1500";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1501 = "dataProviderPageActionsComplianceReports_TC1501";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1538 = "dataProviderPageActionsComplianceReports_TC1538";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1539 = "dataProviderPageActionsComplianceReports_TC1539";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1567 = "dataProviderPageActionsComplianceReports_TC1567";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1570 = "dataProviderPageActionsComplianceReports_TC1570";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1572 = "dataProviderPageActionsComplianceReports_TC1572";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1577 = "dataProviderPageActionsComplianceReports_TC1577";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1578 = "dataProviderPageActionsComplianceReports_TC1578";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1611 = "dataProviderPageActionsComplianceReports_TC1611";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1612 = "dataProviderPageActionsComplianceReports_TC1612";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1635 = "dataProviderPageActionsComplianceReports_TC1635";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC165 = "dataProviderPageActionsComplianceReports_TC165";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1730 = "dataProviderPageActionsComplianceReports_TC1730";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1798 = "dataProviderPageActionsComplianceReports_TC1798";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC188 = "dataProviderPageActionsComplianceReports_TC188";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC193 = "dataProviderPageActionsComplianceReports_TC193";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC194 = "dataProviderPageActionsComplianceReports_TC194";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC199 = "dataProviderPageActionsComplianceReports_TC199";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC200 = "dataProviderPageActionsComplianceReports_TC200";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC201 = "dataProviderPageActionsComplianceReports_TC201";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC205 = "dataProviderPageActionsComplianceReports_TC205";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC206 = "dataProviderPageActionsComplianceReports_TC206";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC207 = "dataProviderPageActionsComplianceReports_TC207";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC208 = "dataProviderPageActionsComplianceReports_TC208";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC209 = "dataProviderPageActionsComplianceReports_TC209";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC211 = "dataProviderPageActionsComplianceReports_TC211";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC236 = "dataProviderPageActionsComplianceReports_TC236";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC238 = "dataProviderPageActionsComplianceReports_TC238";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC243 = "dataProviderPageActionsComplianceReports_TC243";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC522 = "dataProviderPageActionsComplianceReports_TC522";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC524 = "dataProviderPageActionsComplianceReports_TC524";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC525 = "dataProviderPageActionsComplianceReports_TC525";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC529 = "dataProviderPageActionsComplianceReports_TC529";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC579 = "dataProviderPageActionsComplianceReports_TC579";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC622 = "dataProviderPageActionsComplianceReports_TC622";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC624 = "dataProviderPageActionsComplianceReports_TC624";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC679 = "dataProviderPageActionsComplianceReports_TC679";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC682 = "dataProviderPageActionsComplianceReports_TC682";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC704 = "dataProviderPageActionsComplianceReports_TC704";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC705 = "dataProviderPageActionsComplianceReports_TC705";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC709 = "dataProviderPageActionsComplianceReports_TC709";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC710 = "dataProviderPageActionsComplianceReports_TC710";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC711 = "dataProviderPageActionsComplianceReports_TC711";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC713 = "dataProviderPageActionsComplianceReports_TC713";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC715 = "dataProviderPageActionsComplianceReports_TC715";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC716 = "dataProviderPageActionsComplianceReports_TC716";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC718 = "dataProviderPageActionsComplianceReports_TC718";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC721 = "dataProviderPageActionsComplianceReports_TC721";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC723 = "dataProviderPageActionsComplianceReports_TC723";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC727 = "dataProviderPageActionsComplianceReports_TC727";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC771 = "dataProviderPageActionsComplianceReports_TC771";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC779 = "dataProviderPageActionsComplianceReports_TC779";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC781 = "dataProviderPageActionsComplianceReports_TC781";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC787 = "dataProviderPageActionsComplianceReports_TC787";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC788 = "dataProviderPageActionsComplianceReports_TC788";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC789 = "dataProviderPageActionsComplianceReports_TC789";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC790 = "dataProviderPageActionsComplianceReports_TC790";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC791 = "dataProviderPageActionsComplianceReports_TC791";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC792 = "dataProviderPageActionsComplianceReports_TC792";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC793 = "dataProviderPageActionsComplianceReports_TC793";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC794 = "dataProviderPageActionsComplianceReports_TC794";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC795 = "dataProviderPageActionsComplianceReports_TC795";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC796 = "dataProviderPageActionsComplianceReports_TC796";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC799 = "dataProviderPageActionsComplianceReports_TC799";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1960 = "dataProviderPageActionsComplianceReports_TC1960";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2017 = "dataProviderPageActionsComplianceReports_TC2017";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2018 = "dataProviderPageActionsComplianceReports_TC2018";

	/* Asset box */
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC927 = "dataProviderPageActionsComplianceReports_TC927";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2197 = "dataProviderPageActionsComplianceReports_TC2197";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2198 = "dataProviderPageActionsComplianceReports_TC2198";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2199 = "dataProviderPageActionsComplianceReports_TC2199";
	public static final String COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2200 = "dataProviderPageActionsComplianceReports_TC2200";


	public ComplianceReportDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
	}

	/**********************************************************************
	 * #NOTE#:
	 * 1. This data provider is used for Compliance report large data set load tests (eg. 8-hour/100-hour survey reports)
	 * 2. Tests using this dataprovider are intended to be executed in Perf/Load Environment (P3Scale)
	 * 3. Password provided in the data provider will get printed in teamcity UI (run result) and therefore needs to be an encrypted string
	 *    Use the CryptoUtility.encrypt() method to encrypt the password.
	 *
	 * @throws Exception
	 **********************************************************************/
	@DataProvider
	public static Object[][] dataProviderLoadTestsComplianceReport() throws Exception {

		List<Map<String, String>> viewList17 = new ArrayList<Map<String, String>>();
		viewList17.add(createViewsMapTable("First View", "1", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList17 = new ArrayList<Map<String, String>>();
		tablesList17.add(createOptionalTabularPDFContent("1", "0", "0", "0", "0", "0"));
		List<Map<String, String>> viewLayerList17 = null;
		List<String> tagList17 = new ArrayList<String>();
		tagList17.add(PIC8HR01TAG); // 8-hr survey.

		List<Map<String, String>> viewList19 = new ArrayList<Map<String, String>>();
		viewList19.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "0", "1", "1", "0", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList19.add(createViewsMapTable("Second View", "1", "1", "1", "1", "1", "1", "0", "1", "1", "0", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList19.add(createViewsMapTable("Third View", "1", "1", "1", "1", "1", "1", "0", "1", "1", "0", "1", "1", "0", "None"));
		viewList19.add(createViewsMapTable("Fourth View", "1", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList19.add(createViewsMapTable("Fifth View", "1", "1", "1", "1", "1", "1", "0", "1", "1", "0", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList19.add(createViewsMapTable("Sixth View", "1", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList19.add(createViewsMapTable("Seventh View", "0", "0", "1", "1", "1", "0", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList19 = new ArrayList<Map<String, String>>();
		tablesList19.add(createOptionalTabularPDFContent("1", "1", "0", "0", "0", "0"));
		List<Integer> assetRowIDs19 = Arrays.asList(8, 9); // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs19 = Arrays.asList(3); // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList19 = new ArrayList<Map<String, String>>();
		viewLayerList19.add(createOptionalViewLayersContent(assetRowIDs19, boundaryRowIDs19));
		List<String> tagList19 = new ArrayList<String>();
		tagList19.add(PIC8HR01TAG);		// 100-hr surveys.
		tagList19.add(PIC8HR02TAG);
		tagList19.add(PIC8HR03TAG);
		tagList19.add(PIC8HR04TAG);
		tagList19.add(PIC8HR05TAG);
		tagList19.add(PIC8HR06TAG);
		tagList19.add(PIC8HR07TAG);
		tagList19.add(PIC8HR08TAG);
		tagList19.add(PIC8HR09TAG);
		tagList19.add(PIC8HR10TAG);
		tagList19.add(PIC8HR11TAG);
		tagList19.add(PIC8HR12TAG);
		tagList19.add(PICLESS4HRTAG);

		return new Object[][] {
			{ "17", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEET, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createMapAndBoundaryListForLoadTests(), tagList17, tablesList17, viewList17, viewLayerList17 },
			{ "19", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEPT, "0", null, null, null, null, null, null, null, null, null, null, createMapAndBoundaryListForLoadTests(), tagList19, tablesList19, viewList19, viewLayerList19 }
		};
	}

	/**********************************************************************
	 * #NOTE#: Password provided in the data provider will get printed in teamcity UI (run result) and
	 * therefore needs to be an encrypted string Use the CryptoUtility.encrypt() method to encrypt the
	 * password
	 *
	 * @throws Exception
	 **********************************************************************/

	@DataProvider
	public static Object[][] dataProviderComplianceReport() throws Exception {

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList1.add(createViewsMapTable("Second View", "1", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList1.add(createViewsMapTable("Third View", "0", "0", "1", "1", "1", "1", "0", "0", "0", "0", "0", "0", "0", "None"));
		List<Map<String, String>> tablesList1 = new ArrayList<Map<String, String>>();
		tablesList1.add(createOptionalTabularPDFContent("1", "1", "1", "1", "0", "0"));
		List<Integer> assetRowIDs1 = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs1 = Arrays.asList(3, 4); // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList1 = new ArrayList<Map<String, String>>();
		viewLayerList1.add(createOptionalViewLayersContent(assetRowIDs1, boundaryRowIDs1));
		List<String> tagList1 = new ArrayList<String>();
		tagList1.add(SQACUSDRTAG);

		List<Map<String, String>> viewList2 = new ArrayList<Map<String, String>>();
		viewList2.add(createViewsMapTable("First View", "0", "0", "0", "1", "0", "1", "0", "1", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList2.add(createViewsMapTable("Second View", "1", "1", "0", "1", "1", "0", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList2 = new ArrayList<Map<String, String>>();
		tablesList2.add(createOptionalTabularPDFContent("1", "1", "0", "0", "0", "0"));
		List<Integer> assetRowIDs2 = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList2 = new ArrayList<Map<String, String>>();
		viewLayerList2.add(createViewLayerAssetsContent(assetRowIDs2));
		List<String> tagList2 = new ArrayList<String>();
		tagList2.add(PICADMNSTDTAG);

		List<Map<String, String>> viewList3 = new ArrayList<Map<String, String>>();
		viewList3.add(createViewsMapTable("First View", "0", "0", "0", "1", "0", "1", "0", "1", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList3 = new ArrayList<Map<String, String>>();
		tablesList3.add(createOptionalTabularPDFContent("1", "1", "0", "0", "0", "0"));
		List<Integer> assetRowIDs3 = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList3 = new ArrayList<Map<String, String>>();
		viewLayerList3.add(createViewLayerAssetsContent(assetRowIDs3));
		List<String> tagList3 = new ArrayList<String>();
		tagList3.add(PICADMNSTDTAG);

		List<Map<String, String>> viewList4 = new ArrayList<Map<String, String>>();
		viewList4.add(createViewsMapTable("First View", "0", "0", "0", "1", "1", "1", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList4 = new ArrayList<Map<String, String>>();
		tablesList4.add(createOptionalTabularPDFContent("1", "1", "0", "0", "0", "0"));
		List<Map<String, String>> viewLayerList4 = null;
		List<String> tagList4 = new ArrayList<String>();
		tagList4.add(PICADMNSTDTAG);

		List<Map<String, String>> viewList5 = new ArrayList<Map<String, String>>();
		viewList5.add(createViewsMapTable("First View", "0", "0", "0", "1", "1", "1", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList5.add(createViewsMapTable("Second View", "1", "1", "0", "1", "1", "0", "0", "1", "0", "0", "1", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList5 = new ArrayList<Map<String, String>>();
		tablesList5.add(createOptionalTabularPDFContent("1", "1", "0", "0", "0", "0"));
		List<Integer> assetRowIDs5 = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList5 = new ArrayList<Map<String, String>>();
		viewLayerList5.add(createViewLayerAssetsContent(assetRowIDs5));
		List<String> tagList5 = new ArrayList<String>();
		tagList5.add(PICADMNSTDTAG);

		List<Map<String, String>> viewList6 = new ArrayList<Map<String, String>>();
		viewList6.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "0", "0", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList6 = new ArrayList<Map<String, String>>();
		tablesList6.add(createOptionalTabularPDFContent("0", "0", "1", "0", "0", "0"));
		List<Integer> assetRowIDs6 = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList6 = new ArrayList<Map<String, String>>();
		viewLayerList6.add(createViewLayerAssetsContent(assetRowIDs6));
		List<String> tagList6 = new ArrayList<String>();
		tagList6.add(PICADMNSTDTAG);

		List<Map<String, String>> viewList7 = new ArrayList<Map<String, String>>();
		viewList7.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "0", "0", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList7 = new ArrayList<Map<String, String>>();
		tablesList7.add(createOptionalTabularPDFContent("1", "1", "0", "0", "0", "0"));
		List<Integer> assetRowIDs7 = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList7 = new ArrayList<Map<String, String>>();
		viewLayerList7.add(createViewLayerAssetsContent(assetRowIDs7));
		List<String> tagList7 = new ArrayList<String>();
		tagList7.add(PICADMNSTDTAG);

		List<Map<String, String>> viewList8 = new ArrayList<Map<String, String>>();
		viewList8.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "0", "0", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList8 = new ArrayList<Map<String, String>>();
		tablesList8.add(createOptionalTabularPDFContent("1", "1", "0", "0", "0", "0"));
		List<Integer> assetRowIDs8 = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList8 = new ArrayList<Map<String, String>>();
		viewLayerList8.add(createViewLayerAssetsContent(assetRowIDs8));
		List<String> tagList8 = new ArrayList<String>();
		tagList8.add(PICADMNSTDTAG);

		List<Map<String, String>> viewList9 = new ArrayList<Map<String, String>>();
		viewList9.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "0", "0", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList9 = new ArrayList<Map<String, String>>();
		tablesList9.add(createOptionalTabularPDFContent("0", "0", "0", "0", "0", "0"));
		List<Integer> assetRowIDs9 = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList9 = new ArrayList<Map<String, String>>();
		viewLayerList9.add(createViewLayerAssetsContent(assetRowIDs9));
		List<String> tagList9 = new ArrayList<String>();
		tagList9.add(PICADMNSTDTAG);

		List<Map<String, String>> viewList10 = new ArrayList<Map<String, String>>();
		viewList10.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "0", "0", "1", "1","0",  Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList10 = new ArrayList<Map<String, String>>();
		tablesList10.add(createOptionalTabularPDFContent("1", "1", "0", "0", "0", "0"));
		List<Integer> assetRowIDs10 = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList10 = new ArrayList<Map<String, String>>();
		viewLayerList10.add(createViewLayerAssetsContent(assetRowIDs10));
		List<String> tagList10 = new ArrayList<String>();
		tagList10.add(PICADMMANTAG);

		List<Map<String, String>> viewList11 = new ArrayList<Map<String, String>>();
		viewList11.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList11 = new ArrayList<Map<String, String>>();
		tablesList11.add(createOptionalTabularPDFContent("1", "1", "0", "0", "0", "0"));
		List<Integer> assetRowIDs11 = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList11 = new ArrayList<Map<String, String>>();
		viewLayerList11.add(createViewLayerAssetsContent(assetRowIDs11));
		List<String> tagList11 = new ArrayList<String>();
		tagList11.add(PICADMNSTDTAG);

		List<Map<String, String>> viewList12 = new ArrayList<Map<String, String>>();
		viewList12.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList12.add(createViewsMapTable("Second View", "0", "0", "1", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList12.add(createViewsMapTable("Third View", "1", "1", "0", "0", "1", "0", "0", "0", "0", "0", "0", "0", "0", "None"));
		viewList12.add(createViewsMapTable("Fourth View", "0", "1", "0", "0", "0", "0", "1", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList12 = new ArrayList<Map<String, String>>();
		tablesList12.add(createOptionalTabularPDFContent("1", "1", "0", "0", "0", "0"));
		List<Map<String, String>> viewLayerList12 = null;
		List<String> tagList12 = new ArrayList<String>();
		tagList12.add(CUSDRVSTDTAG);

		List<Map<String, String>> viewList13 = new ArrayList<Map<String, String>>();
		viewList13.add(createViewsMapTable("First View", "1", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList13.add(createViewsMapTable("Second View", "0", "0", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList13 = new ArrayList<Map<String, String>>();
		tablesList13.add(createOptionalTabularPDFContent("1", "0", "0", "0", "0", "0"));
		List<Map<String, String>> viewLayerList13 = null;
		List<String> tagList13 = new ArrayList<String>();
		tagList13.add(CUSDRVSTDTAG);

		List<Map<String, String>> viewList14 = new ArrayList<Map<String, String>>();
		viewList14.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList14.add(createViewsMapTable("Second View", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList14.add(createViewsMapTable("Third View", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "0", "None"));
		List<Map<String, String>> tablesList14 = new ArrayList<Map<String, String>>();
		tablesList14.add(createOptionalTabularPDFContent("1", "1", "1", "1", "0", "0"));
		List<Integer> assetRowIDs14 = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs14 = Arrays.asList(3, 4); // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList14 = new ArrayList<Map<String, String>>();
		viewLayerList14.add(createOptionalViewLayersContent(assetRowIDs14, boundaryRowIDs14));
		List<String> tagList14 = new ArrayList<String>();
		tagList14.add(SQACUSDRTAG);

		List<Map<String, String>> viewList15 = new ArrayList<Map<String, String>>();
		viewList15.add(createViewsMapTable("First View", "1", "1", "0", "1", "0", "0", "0", "1", "0", "0", "1", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList15.add(createViewsMapTable("Second View", "0", "0", "1", "1", "0", "0", "0", "1", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList15.add(createViewsMapTable("Third View", "0", "1", "0", "0", "0", "0", "1", "1", "0", "0", "0", "1", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList15 = new ArrayList<Map<String, String>>();
		tablesList15.add(createOptionalTabularPDFContent("1", "1", "1", "1", "0", "0"));
		List<Integer> assetRowIDs15 = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList15 = new ArrayList<Map<String, String>>();
		viewLayerList15.add(createViewLayerAssetsContent(assetRowIDs15));
		List<String> tagList15 = new ArrayList<String>();
		tagList15.add(SQACUSDRTAG);

		List<Map<String, String>> viewList16 = new ArrayList<Map<String, String>>();
		viewList16.add(createViewsMapTable("First View", "1", "1", "0", "1", "1", "0", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList16.add(createViewsMapTable("Second View", "1", "1", "0", "1", "1", "0", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList16 = new ArrayList<Map<String, String>>();
		tablesList16.add(createOptionalTabularPDFContent("1", "1", "0", "0", "0", "0"));
		List<Map<String, String>> viewLayerList16 = null;
		List<String> tagList16 = new ArrayList<String>();
		tagList16.add(CUSDRVSTDTAG);

		List<Map<String, String>> viewList17 = new ArrayList<Map<String, String>>();
		viewList17.add(createViewsMapTable("First View", "1", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList17 = new ArrayList<Map<String, String>>();
		tablesList17.add(createOptionalTabularPDFContent("1", "0", "0", "0", "0", "0"));
		List<Map<String, String>> viewLayerList17 = null;
		List<String> tagList17 = new ArrayList<String>();
		tagList17.add(PIC8HR01TAG); // 8-hr survey with similar test data included in 'dataProviderLoadTestsComplianceReport'.
									// Same test data included here because of additional verifications that happen in this dataprovider.

		List<Map<String, String>> viewList18 = new ArrayList<Map<String, String>>();
		viewList18.add(createViewsMapTable("First View", "0", "0", "0", "0", "1", "0", "0", "1", "1", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList18.add(createViewsMapTable("Second View", "1", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList18.add(createViewsMapTable("Third View", "0", "0", "1", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList18 = new ArrayList<Map<String, String>>();
		tablesList18.add(createOptionalTabularPDFContent("1", "1", "0", "0", "0", "0"));
		List<Integer> assetRowIDs18 = Arrays.asList(8, 9); // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs18 = Arrays.asList(3); // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList18 = new ArrayList<Map<String, String>>();
		viewLayerList18.add(createOptionalViewLayersContent(assetRowIDs18, boundaryRowIDs18));
		List<String> tagList18 = new ArrayList<String>();
		tagList18.add(SQACUSDRTAG);

		List<Map<String, String>> viewList19 = new ArrayList<Map<String, String>>();
		viewList19.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "0", "1", "1", "0", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList19.add(createViewsMapTable("Second View", "1", "1", "1", "1", "1", "1", "0", "1", "1", "0", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList19.add(createViewsMapTable("Third View", "1", "1", "1", "1", "1", "1", "0", "1", "1", "0", "1", "1", "0", "None"));
		viewList19.add(createViewsMapTable("Fourth View", "1", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList19.add(createViewsMapTable("Fifth View", "1", "1", "1", "1", "1", "1", "0", "1", "1", "0", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList19.add(createViewsMapTable("Sixth View", "1", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList19.add(createViewsMapTable("Seventh View", "0", "0", "1", "1", "1", "0", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList19 = new ArrayList<Map<String, String>>();
		tablesList19.add(createOptionalTabularPDFContent("1", "0", "0", "0", "0", "0"));
		List<Integer> assetRowIDs19 = Arrays.asList(8, 9); // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs19 = Arrays.asList(3); // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList19 = new ArrayList<Map<String, String>>();
		viewLayerList19.add(createOptionalViewLayersContent(assetRowIDs19, boundaryRowIDs19));
		List<String> tagList19 = new ArrayList<String>();
		tagList19.add(PIC8HR01TAG);		// 100-hr surveys. Similar test data included in 'dataProviderLoadTestsComplianceReport' for execution on P3Scale.
		tagList19.add(PIC8HR02TAG); 	// Same test data included here because of additional verifications that happen in this dataprovider.
		tagList19.add(PIC8HR03TAG);
		tagList19.add(PIC8HR04TAG);
		tagList19.add(PIC8HR05TAG);
		tagList19.add(PIC8HR06TAG);
		tagList19.add(PIC8HR07TAG);
		tagList19.add(PIC8HR08TAG);
		tagList19.add(PIC8HR09TAG);
		tagList19.add(PIC8HR10TAG);
		tagList19.add(PIC8HR11TAG);
		tagList19.add(PIC8HR12TAG);
		tagList19.add(PICLESS4HRTAG);

		List<Map<String, String>> viewList20 = new ArrayList<Map<String, String>>();
		viewList20.add(createViewsMapTable("First View", "1", "0", "0", "1", "1", "0", "0", "1", "0", "0", "1", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList20 = new ArrayList<Map<String, String>>();
		tablesList20.add(createOptionalTabularPDFContent("1", "1", "0", "0", "0", "0"));
		List<Integer> assetRowIDs20 = Arrays.asList(8, 9); // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList20 = new ArrayList<Map<String, String>>();
		viewLayerList20.add(createViewLayerAssetsContent(assetRowIDs20));
		List<String> tagList20 = new ArrayList<String>();
		tagList20.add(SQACUSDRTAG); // include a survey with no LISAS

		List<Map<String, String>> viewList21 = new ArrayList<Map<String, String>>();
		viewList21.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList21.add(createViewsMapTable("Second View", "0", "0", "1", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList21.add(createViewsMapTable("Third View", "1", "1", "0", "0", "1", "0", "0", "0", "0", "0", "0", "0", "0", "None"));
		List<Map<String, String>> tablesList21 = new ArrayList<Map<String, String>>();
		tablesList21.add(createOptionalTabularPDFContent("1", "0", "0", "0", "0", "0"));
		List<Integer> assetRowIDs21 = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs21 = Arrays.asList(3, 4); // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList21 = new ArrayList<Map<String, String>>();
		viewLayerList21.add(createOptionalViewLayersContent(assetRowIDs21, boundaryRowIDs21));
		List<String> tagList21 = new ArrayList<String>();
		tagList21.add(CUSDRVSTDTAG3200);

		List<Map<String, String>> viewList22 = new ArrayList<Map<String, String>>();
		viewList22.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList22.add(createViewsMapTable("Second View", "1", "0", "1", "1", "0", "0", "1", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList22.add(createViewsMapTable("Third View", "1", "1", "0", "1", "1", "0", "1", "0", "0", "0", "0", "0", "0", "None"));
		List<Map<String, String>> tablesList22 = new ArrayList<Map<String, String>>();
		tablesList22.add(createOptionalTabularPDFContent("1", "0", "0", "0", "0", "0"));
		List<Integer> assetRowIDs22 = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs22 = Arrays.asList(3, 4); // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList22 = new ArrayList<Map<String, String>>();
		viewLayerList22.add(createOptionalViewLayersContent(assetRowIDs22, boundaryRowIDs22));
		List<String> tagList22 = new ArrayList<String>();
		tagList22.add(CUSDRVSTDTAG3200);

		List<Map<String, String>> viewList23 = new ArrayList<Map<String, String>>();
		viewList23.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList23 = new ArrayList<Map<String, String>>();
		tablesList23.add(createOptionalTabularPDFContent("1", "1", "0", "0", "0", "0"));
		List<Map<String, String>> viewLayerList23 = null;
		List<String> tagList23 = new ArrayList<String>();
		tagList23.add(CUSDRVSTDTAG);

		return new Object[][] {
				{ "1"/* index */, SQAPICSUP/* strCreatedBy */, USERPASSWORDHASH/* password(encrypted) */, "Picarro"/* customer */, TIMEZONEPT/* timeZone */, EXCLUSIONRADIUS/* exclusionRadius */, null/* surveyorUnit */, null/* userName */, null/* startDate */, null/* endDate */, null/* fovOpacity */, null/* lisaOpacity */, null/* geoFilter */, null/* reportMode */, null/* surveyModeFilter */, null/* ethaneFilter */, createMapAndBoundaryList()/* listBoundary */, tagList1/* tagList */,
						tablesList1/* tablesList */, viewList1/* viewList */, viewLayerList1/* viewLayersList */ },
				{ "2", SQAPICSUP, USERPASSWORDHASH, "Picarro", TIMEZONEPT, EXCLUSIONRADIUS, null, null, null, null, null, null, null, null, null, null, createMapAndBoundaryList(), tagList2, tablesList2, viewList2, viewLayerList2 }, // include date range
				{ "3", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONECT, EXCLUSIONRADIUS, PICADMNSURVEYOR, null, null, null, null, null, null, null, SurveyModeFilter.All, null, createMapAndBoundaryList(), tagList3, tablesList3, viewList3, viewLayerList3 },
				{ "4", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEET, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createMapAndBoundaryList(), tagList4, tablesList4, viewList4, viewLayerList4 }, // include dates
				{ "5", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONECT, "0", PICADMNSURVEYOR, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createMapAndBoundaryList(), tagList5, tablesList5, viewList5, viewLayerList5 }, // include dates
				{ "6", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEPT, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createMapAndBoundaryList(), tagList6, tablesList6, viewList6, viewLayerList6 },
				{ "7", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEPT, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createMapAndBoundaryList(), tagList7, tablesList7, viewList7, viewLayerList7 },
				{ "8", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEPT, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createMapAndBoundaryList(), tagList8, tablesList8, viewList8, viewLayerList8 },
				{ "9", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEET, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createMapAndBoundaryList(), tagList9, tablesList9, viewList9, viewLayerList9 },
				{ "10", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEMT, "0", null, null, null, null, null, null, null, ReportModeFilter.Manual, SurveyModeFilter.Manual, null, createMapAndBoundaryList(), tagList10, tablesList10, viewList10, viewLayerList10 },
				{ "11", PICDFADMIN, USERPASSWORDHASH, "sqacus", TIMEZONEMT, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createMapAndBoundaryList(), tagList11, tablesList11, viewList11, viewLayerList11 },
				{ "12", SQAPICSUP, USERPASSWORDHASH, "sqacus", TIMEZONEPT, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createMapAndBoundaryList(), tagList12, tablesList12, viewList12, viewLayerList12 },
				{ "13", SQACUSSU, USERPASSWORDHASH, "sqacus", TIMEZONEPT, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createMapAndBoundaryList(), tagList13, tablesList13, viewList13, viewLayerList13 },
				{ "14", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEPT, "0", null, null, null, null, null, null, null, null, null, null, createMapAndBoundaryList(), tagList14, tablesList14, viewList14, viewLayerList14 },
				{ "15", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEPT, "0", null, null, null, null, null, null, null, null, null, null, createMapAndBoundaryList(), tagList15, tablesList15, viewList15, viewLayerList15 }, // Include Date filter for 30 days
				{ "16", SQACUSSU, USERPASSWORDHASH, "sqacus", TIMEZONECT, "150", null, null, null, null, null, null, true, null, SurveyModeFilter.Standard, null, createMapAndBoundaryList(), tagList16, tablesList16, viewList16, viewLayerList16 },
				{ "17", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEET, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createMapAndBoundaryListForLoadTests(), tagList17, tablesList17, viewList17, viewLayerList17 },
				{ "18", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEPT, "0", null, null, null, null, null, null, null, null, null, null, createMapAndBoundaryList(), tagList18, tablesList18, viewList18, viewLayerList18 },
				{ "19", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEPT, "0", null, null, null, null, null, null, null, null, null, null, createMapAndBoundaryListForLoadTests(), tagList19, tablesList19, viewList19, viewLayerList19 },
				{ "20", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEMT, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createMapAndBoundaryList(), tagList20, tablesList20, viewList20, viewLayerList20 }, // include dates
				{ "21", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEPT, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createMapAndBoundaryList(), tagList21, tablesList21, viewList21, viewLayerList21 },
				{ "22", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEPT, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createMapAndBoundaryList(), tagList22, tablesList22, viewList22, viewLayerList22 },
				{ "23", SQACUSSU, USERPASSWORDHASH, "sqacus", TIMEZONEPT, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createMapAndBoundaryList(), tagList23, tablesList23, viewList23, viewLayerList23 }
				};
	}

	@DataProvider
	public static Object[][] dataProviderComplianceReport11() throws Exception {

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "1", "1", "1", "0", "0", "1", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList1 = new ArrayList<Map<String, String>>();
		tablesList1.add(createOptionalTabularPDFContent("0", "0", "0", "0", "0", "0"));
		List<String> tagList1 = new ArrayList<String>();
		tagList1.add(CUSDRVOPTAG2);
		tagList1.add(CUSDRVSTDTAG2);

		List<Map<String, String>> viewList2 = new ArrayList<Map<String, String>>();
		viewList2.add(createViewsMapTable("First View", "1", "1", "0", "1", "1", "1", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList2.add(createViewsMapTable("Second View", "1", "1", "0", "1", "1", "1", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList2 = new ArrayList<Map<String, String>>();
		tablesList2.add(createOptionalTabularPDFContent("1", "1", "0", "0", "0", "0"));
		List<String> tagList2 = new ArrayList<String>();
		tagList2.add(CUSDRVOPTAG2);
		tagList2.add(CUSDRVSTDTAG2);

		List<Map<String, String>> viewList3 = new ArrayList<Map<String, String>>();
		viewList3.add(createViewsMapTable("First View", "0", "0", "0", "0", "1", "1", "0", "1", "1", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList3 = new ArrayList<Map<String, String>>();
		tablesList3.add(createOptionalTabularPDFContent("1", "1", "0", "0", "0", "0"));
		List<Integer> assetRowIDs23 = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs23 = Arrays.asList(3, 4); // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList3 = new ArrayList<Map<String, String>>();
		viewLayerList3.add(createOptionalViewLayersContent(assetRowIDs23, boundaryRowIDs23));
		List<String> tagList3 = new ArrayList<String>();
		tagList3.add(CUSDRVSTDTAG3200);

		return new Object[][] {
				{ "1"/* index */, SQAPICSUP/* strCreatedBy */, USERPASSWORDHASH/* password(encrypted) */, CUSTOMER_SQACUS/* customer */,
					TIMEZONEPT/* timeZone */, EXCLUSIONRADIUS_100/* exclusionRadius */, null/* surveyorUnit */, null/* userName */, null/* startDate */,
					null/* endDate */, null/* fovOpacity */, null/* lisaOpacity */, null/* geoFilter */, null/* reportMode */, null/* surveyModeFilter */,
					null/* ethaneFilter */, createMapAndBoundaryList()/* listBoundary */, tagList1/* tagList */,
						tablesList1/* tablesList */, viewList1/* viewList */, null/* viewLayersList */ },
				{ "2", SQAPICSUP, USERPASSWORDHASH, CUSTOMER_SQACUS, TIMEZONEPT, EXCLUSIONRADIUS_150, null, null, null, null, null,
							null, null, null, null, null, createMapAndBoundaryList(), tagList2, tablesList2, viewList2, null },
				{ "3", SQAPICSUP, USERPASSWORDHASH, "Picarro", TIMEZONEPT, EXCLUSIONRADIUS_0, null, null, null, null, null,
								null, null, null, null, null, createMapAndBoundaryList(), tagList3, tablesList3, viewList3, viewLayerList3 },
				};

	}

	@DataProvider
	public static Object[][] dataProviderComplianceReport11NoDownload() throws Exception {

		List<Map<String, String>> viewList4 = new ArrayList<Map<String, String>>();
		viewList4.add(createViewsMapTable("First View", "0", "1", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList4 = new ArrayList<Map<String, String>>();
		tablesList4.add(createOptionalTabularPDFContent("0", "0", "0", "0", "0", "0"));
		List<String> tagList4 = new ArrayList<String>();
		tagList4.add(CUSDRVOPTAG2);

		List<Map<String, String>> viewList5 = new ArrayList<Map<String, String>>();
		viewList5.add(createViewsMapTable("First View", "1", "1", "0", "1", "1", "1", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList5 = new ArrayList<Map<String, String>>();
		tablesList5.add(createOptionalTabularPDFContent("1", "1", "0", "0", "0", "0"));
		List<String> tagList5 = new ArrayList<String>();
		tagList5.add(CUSDRVOPTAG2);
		tagList5.add(CUSDRVSTDTAG2);


		return new Object[][] {
				{ "4", SQAPICSUP, USERPASSWORDHASH, CUSTOMER_SQACUS, TIMEZONEPT, EXCLUSIONRADIUS_150, null, null, null, null, null,
									null, null, null, null, EthaneFilter.All, createMapAndBoundaryList(), tagList4, tablesList4, viewList4, null },
				{ "5", SQAPICSUP, USERPASSWORDHASH, CUSTOMER_SQACUS, TIMEZONEPT, EXCLUSIONRADIUS_0, null, null, null, null, null,
										null, null, null, null, null, createMapAndBoundaryList(), tagList5, tablesList5, viewList5, null },
				{ "6", SQAPICSUP, USERPASSWORDHASH, CUSTOMER_SQACUS, TIMEZONEPT, EXCLUSIONRADIUS_0, null, null, null, null, null,
											null, null, null, null, null, createMapAndBoundaryList(), tagList5, tablesList5, viewList5, null },
				{ "7", SQAPICSUP, USERPASSWORDHASH, CUSTOMER_SQACUS, TIMEZONEPT, EXCLUSIONRADIUS_0, null, null, null, null, null,
												null, null, null, null, null, createMapAndBoundaryList(), tagList5, tablesList5, viewList5, null },};
			}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC231() {
		return new Object[][] { { "TC231" /* TestCaseID */, 6 /* userDataRowID */, 14 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC235() {
		return new Object[][] { { "TC235" /* TestCaseID */, 6 /* userDataRowID */, 15 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC237() {
		return new Object[][] { { "TC237" /* TestCaseID */, 6 /* userDataRowID */, 16 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC509() {
		return new Object[][] { { "TC509" /* TestCaseID */, 6 /* userDataRowID */, 17 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC510() {
		return new Object[][] { { "TC510" /* TestCaseID */, 6 /* userDataRowID */, 18 /* reportDataRowID1 */, 19/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC514() {
		return new Object[][] { { "TC514" /* TestCaseID */, 6 /* userDataRowID */, 20 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC520() {
		return new Object[][] { { "TC520" /* TestCaseID */, 5 /* userDataRowID */, 21 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC521() {
		return new Object[][] { { "TC521" /* TestCaseID */, 6 /* userDataRowID */, 22 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC526() {
		return new Object[][] { { "TC526" /* TestCaseID */, 6 /* userDataRowID */, 23 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	// Repeat the test, but in the Views section, select only FOV
	// Repeat the test, but in the Views section, select only Breadcrumb
	// Repeat the test, but in the Views section, select only Gaps
	// Repeat the test, but in the Views section, select only Assets
	// Repeat the test, but in the Views section, select all five of the features named above
	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC678() {
		return new Object[][] { { "TC678" /* TestCaseID */, 6 /* userDataRowID */, 24 /* reportDataRowID1 */, -1/* reportDataRowID2 */ }, { "TC678" /* TestCaseID */, 6 /* userDataRowID */, 25 /* reportDataRowID1 */, -1/* reportDataRowID2 */ }, { "TC678" /* TestCaseID */, 6 /* userDataRowID */, 26 /* reportDataRowID1 */, -1/* reportDataRowID2 */ },
			{ "TC678" /* TestCaseID */, 6 /* userDataRowID */, 27 /* reportDataRowID1 */, -1/* reportDataRowID2 */ },
			{ "TC678" /* TestCaseID */, 6 /* userDataRowID */, 29 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	// Repeat the test with FOVs, Breadcrumbs, Assets and Gaps (Assets are selected, asset types must also be selected)
	// Repeat the test with different combinations such as FOV and Breadcrumb
	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC680() {
		return new Object[][] { { "TC680" /* TestCaseID */, 6 /* userDataRowID */, 30 /* reportDataRowID1 */, -1/* reportDataRowID2 */ },
			{ "TC680" /* TestCaseID */, 6 /* userDataRowID */, 31 /* reportDataRowID1 */, -1/* reportDataRowID2 */ },
			{ "TC680" /* TestCaseID */, 6 /* userDataRowID */, 32 /* reportDataRowID1 */, -1/* reportDataRowID2 */ },
			{ "TC680" /* TestCaseID */, 6 /* userDataRowID */, 33 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	// Repeat the test with different customer selection (Shape file export should remain standard)
	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC681() {
		return new Object[][] { { "TC681" /* TestCaseID */, 6 /* userDataRowID */, 35 /* reportDataRowID1 */, -1/* reportDataRowID2 */ }
				// TODO: Currently SQACUS customer does NOT have assets in database.
				// ,{ "TC681" /*TestCaseID*/, 5 /*userDataRowID*/, 36 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
		};
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC689() {
		return new Object[][] { { "TC689" /* TestCaseID */, 6 /* userDataRowID */, 37 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC690() {
		return new Object[][] { { "TC690" /* TestCaseID */, 6 /* userDataRowID */, 38 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC698() {
		return new Object[][] { { "TC698" /* TestCaseID */, 6 /* userDataRowID */, 39 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC699() {
		return new Object[][] { { "TC699" /* TestCaseID */, 6 /* userDataRowID */, 40 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC700() {
		return new Object[][] { { "TC700" /* TestCaseID */, 6 /* userDataRowID */, 41 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC701() {
		return new Object[][] { { "TC701" /* TestCaseID */, 6 /* userDataRowID */, 42 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC706() {
		return new Object[][] { { "TC706" /* TestCaseID */, 6 /* userDataRowID */, 43 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC719() {
		return new Object[][] { { "TC719" /* TestCaseID */, 6 /* userDataRowID */, 44 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC722() {
		return new Object[][] { { "TC722" /* TestCaseID */, 6 /* userDataRowID */, 45 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC736() {
		return new Object[][] { { "TC736" /* TestCaseID */, 6 /* userDataRowID */, 46 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC737() {
		return new Object[][] { { "TC737" /* TestCaseID */, 6 /* userDataRowID */, 47 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC786() {
		return new Object[][] { { "TC786" /* TestCaseID */, 6 /* userDataRowID */, 48 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC788() {
		return new Object[][] { { "TC788" /* TestCaseID */, 6 /* userDataRowID */, 128 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC790() {
		return new Object[][] { { "TC790" /* TestCaseID */, 6 /* userDataRowID */, 129 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC798() {
		return new Object[][] { { "TC798" /* TestCaseID */, 6 /* userDataRowID */, -1 /* NOT USED - reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC204() {
		return new Object[][] { { "TC204" /* TestCaseID */, 6 /* userDataRowID */, 79 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC210() {
		return new Object[][] { { "TC210" /* TestCaseID */, 6 /* userDataRowID */, 80/* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC227() {
		return new Object[][] { { "TC227" /* TestCaseID */, 6 /* userDataRowID */, 80/* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1522() {
		return new Object[][] { { "TC1522" /* TestCaseID */, 2 /* userDataRowID */, 83/* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1523() {
		return new Object[][] { { "TC1523" /* TestCaseID */, 1/* userDataRowID */, 81/* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1524() {
		return new Object[][] { { "TC1524" /* TestCaseID */, 2/* userDataRowID */, 82/* reportDataRowID1 */, 81/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1525() {
		return new Object[][] { { "TC1525" /* TestCaseID */, 2/* userDataRowID */, 82/* reportDataRowID1 */, 81/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1608() {
		return new Object[][] { { "TC1608" /* TestCaseID */, 6/* userDataRowID */, 84/* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1255() {
		return new Object[][] { { "TC1255" /* TestCaseID */, 6 /* userDataRowID */, -1 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1256() {
		return new Object[][] { { "TC1256" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1274() {
		return new Object[][] { { "TC1274" /* TestCaseID */, 6 /* userDataRowID */, -1 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1298() {
		return new Object[][] { { "TC1298" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1314() {
		return new Object[][] { { "TC1314" /* TestCaseID */, 4 /* userDataRowID */, 130 /* reportDataRowID1 */, 131 /* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1353() {
		return new Object[][] { { "TC1353" /* TestCaseID */, 6 /* userDataRowID*/, 116/* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1354() {
		return new Object[][] { { "TC1354" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1355() {
		return new Object[][] { { "TC1355" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1356() {
		return new Object[][] { { "TC1356" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1357() {
		return new Object[][] { { "TC1357" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1384() {
		return new Object[][] { { "TC1384" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1498() {
		return new Object[][] { { "TC1498" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1499() {
		return new Object[][] { { "TC1499" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC15() {
		return new Object[][] { { "TC15" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1500() {
		return new Object[][] { { "TC1500" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1501() {
		return new Object[][] { { "TC1501" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1538() {
		return new Object[][] { { "TC1538" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1539() {
		return new Object[][] { { "TC1539" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1567() {
		return new Object[][] { { "TC1567" /* TestCaseID */, 6 /* userDataRowID */, 112 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1570() {
		return new Object[][] { { "TC1570" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1572() {
		return new Object[][] { { "TC1572" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1577() {
		return new Object[][] { { "TC1577" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1578() {
		return new Object[][] { { "TC1578" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1611() {
		return new Object[][] { { "TC1611" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1612() {
		return new Object[][] { { "TC1612" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1635() {
		return new Object[][] { { "TC1635" /* TestCaseID */, 1 /* userDataRowID */, 86 /* reportDataRowID1 */, 87/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC165() {
		return new Object[][] { { "TC165" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1730() {
		return new Object[][] { { "TC1730" /* TestCaseID */, 1 /* userDataRowID */, 88 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1798() {
		return new Object[][] { { "TC1798" /* TestCaseID */, 2 /* userDataRowID */, 89 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC188() {
		return new Object[][] { { "TC188" /* TestCaseID */, 1 /* userDataRowID */, 90 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC193() {
		return new Object[][] { { "TC193" /* TestCaseID */, 4 /* userDataRowID */, 91 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC194() {
		return new Object[][] { { "TC194" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC199() {
		return new Object[][] { { "TC199" /* TestCaseID */, 4 /* userDataRowID */, 92 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC200() {
		return new Object[][] { { "TC200" /* TestCaseID */, 4 /* userDataRowID */, 93 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC201() {
		return new Object[][] { { "TC201" /* TestCaseID */, 4 /* userDataRowID */, 94 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC205() {
		return new Object[][] { { "TC205" /* TestCaseID */, 4 /* userDataRowID */, 95 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC206() {
		return new Object[][] { { "TC206" /* TestCaseID */, 4 /* userDataRowID */, 96 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC207() {
		return new Object[][] { { "TC207" /* TestCaseID */, 4 /* userDataRowID */, 125 /* reportDataRowID1 - [TODO:CHANGE THIS] */, 126/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC208() {
		return new Object[][] { { "TC208" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC209() {
		return new Object[][] { { "TC209" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC211() {
		return new Object[][] { { "TC211" /* TestCaseID */, 4 /* userDataRowID */, 97 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC236() {
		return new Object[][] { { "TC236" /* TestCaseID */, 4 /* userDataRowID */, 98 /* reportDataRowID1 */, 99/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC238() {
		return new Object[][] { { "TC238" /* TestCaseID */, 4 /* userDataRowID */, 127 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC243() {
		return new Object[][] { { "TC243" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC522() {
		return new Object[][] { { "TC522" /* TestCaseID */, 4 /* userDataRowID */, 100 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC524() {
		return new Object[][] { { "TC524" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC525() {
		return new Object[][] { { "TC525" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC529() {
		return new Object[][] { { "TC529" /* TestCaseID */, 4 /* userDataRowID */, 101 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC579() {
		return new Object[][] { { "TC579" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC622() {
		return new Object[][] { { "TC622" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC624() {
		return new Object[][] { { "TC624" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC679() {
		return new Object[][] { { "TC679" /* TestCaseID */, 4 /* userDataRowID */, 102 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC682() {
		return new Object[][] { { "TC682" /* TestCaseID */, 4 /* userDataRowID */, 103 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC704() {
		return new Object[][] { { "TC704" /* TestCaseID */, 4 /* userDataRowID */, 104 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC705() {
		return new Object[][] { { "TC705" /* TestCaseID */, 4 /* userDataRowID */, 105 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC709() {
		return new Object[][] { { "TC709" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC710() {
		return new Object[][] { { "TC710" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC711() {
		return new Object[][] { { "TC711" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC713() {
		return new Object[][] { { "TC713" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC715() {
		return new Object[][] { { "TC715" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC716() {
		return new Object[][] { { "TC716" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC718() {
		return new Object[][] { { "TC718" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC721() {
		return new Object[][] { { "TC721" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC723() {
		return new Object[][] { { "TC723" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC727() {
		return new Object[][] { { "TC727" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC771() {
		return new Object[][] { { "TC771" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC779() {
		return new Object[][] { { "TC779" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC781() {
		return new Object[][] { { "TC781" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC787() {
		return new Object[][] { { "TC787" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC789() {
		return new Object[][] { { "TC789" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC791() {
		return new Object[][] { { "TC791" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC792() {
		return new Object[][] { { "TC792" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC793() {
		return new Object[][] { { "TC793" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC794() {
		return new Object[][] { { "TC794" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC795() {
		return new Object[][] { { "TC795" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC796() {
		return new Object[][] { { "TC796" /* TestCaseID */, 0 /* userDataRowID - [TODO:CHANGE THIS] */, 0 /* reportDataRowID1 - [TODO:CHANGE THIS] */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC799() {
		return new Object[][] { { "TC799" /* TestCaseID */, 6 /* userDataRowID */, -1 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1532() {
		return new Object[][] { { "TC1532" /* TestCaseID */, 6 /* userDataRowID */, 85 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	// Start of ComplianceReportsPageTest3.java
	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC12() {
		return new Object[][] { { "TC12" /* TestCaseID */, 4 /* userDataRowID */, 72 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC13() {
		return new Object[][] { // US needed, 2, 8
				{ "TC13" /* TestCaseID */, 4 /* userDataRowID */, 73 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1319() {
		return new Object[][] { { "TC1319" /* TestCaseID */, 5 /* userDataRowID */, 50 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1320() {
		return new Object[][] { // Blocked by customer assets, boundaries
				{ "TC1320" /* TestCaseID */, 1 /* userDataRowID */, 51 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1339() {
		return new Object[][] { // UA needed, 1,7
				{ "TC1339" /* TestCaseID */, 4 /* userDataRowID */, 58 /* reportDataRowID1 */, 75/* reportDataRowID2 */, 76/* reportDataRowID3 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1340() {
		return new Object[][] { // UA needed, 1,7
				{ "TC1340" /* TestCaseID */, 4 /* userDataRowID */, 71 /* reportDataRowID1 */, 77 /* reportDataRowID2 */, 78 /* reportDataRowID3 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1352() {
		return new Object[][] { // UA needed, 1,7
				{ "TC1352" /* TestCaseID */, 4 /* userDataRowID */, 62 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1363() {
		return new Object[][] { // UA needed, 1,7
				{ "TC1363" /* TestCaseID */, 4 /* userDataRowID */, 59 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1364() {
		return new Object[][] { { "TC1364" /* TestCaseID */, 5 /* userDataRowID */, 52 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1365() {
		return new Object[][] { { "TC1365" /* TestCaseID */, 4 /* userDataRowID */, 53 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1366() {
		return new Object[][] { { "TC1366" /* TestCaseID */, 4 /* userDataRowID */, 60 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1367() {
		return new Object[][] { // Csu needed, 2,8
				{ "TC1367" /* TestCaseID */, 4 /* userDataRowID */, 54 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1370() {
		return new Object[][] { // UA needed, 1,7
				{ "TC1370" /* TestCaseID */, 4 /* userDataRowID */, 63 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1371() {
		return new Object[][] { { "TC1371" /* TestCaseID */, 4 /* userDataRowID */, 64 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1373() {
		return new Object[][] { { "TC1373" /* TestCaseID */, 4 /* userDataRowID */, 65 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1389() {
		return new Object[][] { { "TC1389" /* TestCaseID */, 4 /* userDataRowID */, 66 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1394() {
		return new Object[][] { { "TC1394" /* TestCaseID */, 2 /* userDataRowID */, 67 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1395() {
		return new Object[][] { { "TC1395" /* TestCaseID */, 1 /* userDataRowID */, 68 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC14() {
		return new Object[][] { // UA required, 1, 7
				{ "TC14" /* TestCaseID */, 4 /* userDataRowID */, 69 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC149() {
		return new Object[][] { { "TC149" /* TestCaseID */, 4 /* userDataRowID */, 70/* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1490() {
		return new Object[][] { { "TC1490" /* TestCaseID */, 4 /* userDataRowID */, -1 /* NOT a candidate of automated tests - reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1491() {
		return new Object[][] { { "TC1491" /* TestCaseID */, 4 /* userDataRowID */, 55 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1496() {
		return new Object[][] { { "TC1496" /* TestCaseID */, 4 /* userDataRowID */, 56 /* NOT USED - reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1497() {
		return new Object[][] { { "TC1497" /* TestCaseID */, 4 /* userDataRowID */, 57 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}
	// End of ComplianceReportsPageTest3.java

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC1960() {
		return new Object[][] { { "TC1960" /* TestCaseID */, 6 /* userDataRowID */, 117 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC2017() {
		return new Object[][] { { "TC2017" /* TestCaseID */, 6 /* userDataRowID */, 118 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC2018() {
		return new Object[][] { { "TC2018" /* TestCaseID */, 6 /* userDataRowID */, 119 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	/* Asset box */
	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC927() {
		return new Object[][] { { "TC927" /* TestCaseID */, 6 /* userDataRowID */, 120 /* NOT USED - reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC2197() {
		return new Object[][] { { "TC2197" /* TestCaseID */, 6 /* userDataRowID */, 121 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}
	// End of ComplianceReportsPageTest3.java

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC2198() {
		return new Object[][] { { "TC2198" /* TestCaseID */, 6 /* userDataRowID */, 122 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC2199() {
		return new Object[][] { { "TC2199" /* TestCaseID */, 6 /* userDataRowID */, 123 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

	@DataProvider
	public static Object[][] dataProviderPageActionsComplianceReports_TC2200() {
		return new Object[][] { { "TC2200" /* TestCaseID */, 6 /* userDataRowID */, 124 /* reportDataRowID1 */, -1/* reportDataRowID2 */ } };
	}

}