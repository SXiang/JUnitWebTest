package surveyor.regression.source;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.SurveyViewPageActions;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.SurveyViewPage;

public class BaseSurveyViewPageTest extends BaseMapViewTest {

	public BaseSurveyViewPageTest() throws IOException {
	}

	protected static final String SAMPLE_SURVEY_FIELD_NOTES1 = "Ref Gas Canceled";
	protected static final String SURVEY_INFO_SURVEYOR = "Surveyor: Software Car";
	protected static final String SURVEY_INFO_ANALYZER = "Analyzer: FDDS2038";
	protected static final String SURVEY_INFO_SURVEYOR_2 = "Surveyor: Nissan Rogue - Picarro";
	protected static final String SURVEY_INFO_ANALYZER_2 = "Analyzer: FEDS2055-PICARRO";
	protected static final String SURVEY_INFO_SURVEYOR_3 = "Surveyor: SoftwareCar_2037_cust";
	protected static final String SURVEY_INFO_ANALYZER_3 = "Analyzer: FDDS2037-1";
	protected static final String SURVEY_INFO_SURVEYOR_4 = "Surveyor: SimAuto-EQSrvUnit-Eth1";
	protected static final String SURVEY_INFO_ANALYZER_4 = "Analyzer: SimAuto-EQAnalyzer-Eth1";
	protected static final String SURVEY_INFO_SURVEYOR_5 = "Surveyor: iGPS car";
	protected static final String SURVEY_INFO_ANALYZER_5 = "Analyzer: RFADS2004-PICARRO";
	protected static final String SURVEY_INFO_SURVEYOR_6 = "Surveyor: SimAuto-EQSrvUnitSqaCus-Eth1";
	protected static final String SURVEY_INFO_ANALYZER_6 = "Analyzer: SimAuto-EQAnalyzer-SqaCus-Eth1";
	protected static final String SURVEY_INFO_ASSESSMENT_SURVEYOR = "Surveyor: Picarro Production #10";
	protected static final String SURVEY_INFO_ASSESSMENT_ANALYZER = "Analyzer: FEDS2055";
	protected static final String SURVEY_INFO_MODE_PREFIX = "Mode: ";
	protected static final String SURVEY_INFO_DRIVER_PREFIX = "Driver: ";
	protected static final String SURVEY_INFO_STABILITY_CLASS_B = "Stability Class: B";
	protected static final String TEST_SURVEY_STANDARD1_ID = "c8782024-cc65-b53a-5317-39d4b4f4731f";
	protected static final String TEST_SURVEY_STANDARD1_TAG = "stnd-pic";
	protected static final String TEST_SURVEY_STANDARD1_TYPE = "Standard";
	protected static final String TEST_SURVEY_STANDARD1_USERNAME = "Administrator";
	protected static final String TEST_SURVEY_ANALYTICS1_ID = "acc61f00-87f7-31f0-8906-39de52bc5ec5";
	protected static final String TEST_SURVEY_ANALYTICS1_TAG = "AnalyticsTagA";
	protected static final String TEST_SURVEY_ANALYTICS1_TYPE = "Analytics";
	protected static final String TEST_SURVEY_ANALYTICS1_USERNAME = "picdr@picarro.com";
	protected static final String TEST_SURVEY_ASSESSMENT1_ID = "028f8fb7-8ba3-a44b-f778-39d626cd322b";
	protected static final String TEST_SURVEY_ASSESSMENT1_TAG = "assessment";
	protected static final String TEST_SURVEY_ASSESSMENT1_TYPE = "Assessment";
	protected static final String TEST_SURVEY_ASSESSMENT1_USERNAME = "driver1@picarro.com";
	protected static final String TEST_SURVEY_RAPID_RESP_ID = "04B2FEDA-EE18-A49E-3208-39D4B50F48FE";
	protected static final String TEST_SURVEY_RAPID_RESP_TAG = "rr-pic";
	protected static final String TEST_SURVEY_RAPID_RESP_TYPE = "Rapid Response";
	protected static final String TEST_SURVEY_RAPID_RESP_USERNAME = "Administrator";
	protected static final String TEST_SURVEY_MANUAL1_ID = "4D7BF7AC-6CC2-9B18-1894-39D4B546927B";
	protected static final String TEST_SURVEY_MANUAL1_TAG = "man-pic";
	protected static final String TEST_SURVEY_MANUAL1_TYPE = "Manual";
	protected static final String TEST_SURVEY_MANUAL1_USERNAME = "Administrator";
	// Use this Manual survey for verifying Field notes are present.
	protected static final String TEST_SURVEY_MANUAL2_ID = "2278D26F-8D69-B070-56FD-39D4B552F8F2";
	protected static final String TEST_SURVEY_MANUAL2_TAG = "man-pic";
	protected static final String TEST_SURVEY_MANUAL2_TYPE = "Manual";
	protected static final String TEST_SURVEY_MANUAL2_USERNAME = "Administrator";
	protected static final String TEST_SURVEY_OPERATOR1_ID = "1556ac85-a125-0347-2a02-39d4b529c6bd";
	protected static final String TEST_SURVEY_OPERATOR1_TAG = "op-pic";
	protected static final String TEST_SURVEY_OPERATOR1_TYPE = "Operator";
	protected static final String TEST_SURVEY_OPERATOR1_USERNAME = "Administrator";

	// Ethane specific constants.
	protected static final String ETHANE_SAMPLE_FIELD_NOTES = "3.6 big leak location";
	protected static final String ETHANE_SURVEY_INFO_SURVEYOR = "Surveyor: Green-Escape";
	protected static final String ETHANE_SURVEY_INFO_ANALYZER = "Analyzer: FEDS2059";
	protected static final String TEST_ETHANE_SURVEY_STANDARD_ID = "183fcb51-e968-bb81-0c98-39d666857ae7";
	protected static final String TEST_ETHANE_SURVEY_STANDARD_TAG = "ethane night sf b1";
	protected static final String TEST_ETHANE_SURVEY_STANDARD_TYPE = "Standard";
	protected static final String TEST_ETHANE_SURVEY_STANDARD_USERNAME = "picscdr@picarro.com";

	protected static final String TEST_SURVEY_EQ_ID = "c7f7018c-2497-4818-a996-e8ad120526ab";
	protected static final String TEST_SURVEY_EQ_TAG = "MenloNight11_17EQ02";
	protected static final String TEST_SURVEY_EQ_TYPE = "EQ";
	protected static final String TEST_SURVEY_EQ_USERNAME = "picdr@picarro.com";
	protected static final String TEST_SURVEY_EQ_ID_SQACUS = "38b7995b-0e28-4f37-84a8-104b7f6f4a1a";
	protected static final String TEST_SURVEY_EQ_USERNAME_SQACUS = "sqacusdr1@email.com";
		
	protected static final String EMPTY = "";
	protected static final Integer NOTSET = -1;

	private static ThreadLocal<DriverViewPageActions> driverViewPageAction = new ThreadLocal<DriverViewPageActions>();
	private static ThreadLocal<SurveyViewPageActions> surveyViewPageAction = new ThreadLocal<SurveyViewPageActions>();
	private static ThreadLocal<SurveyViewPage> surveyViewPage = new ThreadLocal<SurveyViewPage>();

	@BeforeClass
	public static void beforeTestClass() throws Exception {
		initializeTestObjects();

		// Initialization needed at class level for automation reports.
		initializeBasePageActions();
	}

	@Before
	public void beforeTestMethod() {
		try {
			initializeTestObjects();

			initializeBasePageActions();

			setDriverViewPageAction(new DriverViewPageActions(getDriver(), getBaseURL(), getTestSetup()));
			setSurveyViewPageAction(new SurveyViewPageActions(getDriver(), getBaseURL(),getTestSetup()));

			// Initialize page objects.
			PageObjectFactory pageObjectFactory = new PageObjectFactory();
			setSurveyViewPage(pageObjectFactory.getSurveyViewPage());
			PageFactory.initElements(getDriver(), getSurveyViewPage());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static DriverViewPageActions getDriverViewPageAction() {
		return driverViewPageAction.get();
	}

	private static void setDriverViewPageAction(DriverViewPageActions driverViewPgAction) {
		driverViewPageAction.set(driverViewPgAction);
	}

	protected static SurveyViewPageActions getSurveyViewPageAction() {
		return surveyViewPageAction.get();
	}

	private static void setSurveyViewPageAction(SurveyViewPageActions surveyViewPgAction) {
		surveyViewPageAction.set(surveyViewPgAction);
	}

	protected static SurveyViewPage getSurveyViewPage() {
		return surveyViewPage.get();
	}

	private static void setSurveyViewPage(SurveyViewPage surveyViewPg) {
		surveyViewPage.set(surveyViewPg);
	}
}