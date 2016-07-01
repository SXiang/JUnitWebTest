package surveyor.regression.source;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.SurveyViewPageActions;
import surveyor.scommon.source.SurveyViewPage;

public class BaseSurveyViewPageTest extends BaseMapViewTest {

	public BaseSurveyViewPageTest() throws IOException {
	}

	protected static final String SAMPLE_SURVEY_FIELD_NOTES1 = "Ref Gas Canceled";
	protected static final String SURVEY_INFO_SURVEYOR = "Surveyor: Software Car";
	protected static final String SURVEY_INFO_ANALYZER = "Analyzer: FDDS2038";
	protected static final String SURVEY_INFO_ASSESSMENT_SURVEYOR = "Surveyor: Picarro Production #10";
	protected static final String SURVEY_INFO_ASSESSMENT_ANALYZER = "Analyzer: FEDS2055";
	protected static final String SURVEY_INFO_MODE_PREFIX = "Mode: ";
	protected static final String SURVEY_INFO_DRIVER_PREFIX = "Driver: ";
	protected static final String SURVEY_INFO_STABILITY_CLASS_B = "Stability Class: B";
	protected static final String TEST_SURVEY_STANDARD1_ID = "c8782024-cc65-b53a-5317-39d4b4f4731f";
	protected static final String TEST_SURVEY_STANDARD1_TAG = "stnd-pic";
	protected static final String TEST_SURVEY_STANDARD1_TYPE = "Standard";
	protected static final String TEST_SURVEY_STANDARD1_USERNAME = "Administrator";
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

	protected static final String EMPTY = "";
	protected static final Integer NOTSET = -1;
	
	protected static DriverViewPageActions driverViewPageAction;
	protected static SurveyViewPageActions surveyViewPageAction;
	protected static SurveyViewPage surveyViewPage;
	
	@BeforeClass
	public static void beforeTestClass() throws Exception {
		disposeProcesses();
		
		// Initialization needed at class level for automation reports.
		initializePageActions();
	}

	@Before
	public void beforeTestMethod() {
		try {
			initializePageActions();
			
			driverViewPageAction = new DriverViewPageActions(driver, baseURL, testSetup);
			surveyViewPageAction = new SurveyViewPageActions(driver, baseURL,testSetup);

			// Initialize page objects.
			surveyViewPage = new SurveyViewPage(driver, testSetup, baseURL);
			PageFactory.initElements(driver, surveyViewPage);

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@After
    public void afterTestMethod() {
		try {
			afterTest();
			
			disposeProcesses();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
