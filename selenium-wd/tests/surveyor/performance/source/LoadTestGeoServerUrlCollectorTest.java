package surveyor.performance.source;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import common.source.BrowserConsoleLogCollector;
import common.source.CryptoUtility;
import common.source.ExceptionUtility;
import common.source.Log;
import common.source.LogHelper;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;
import org.junit.Test;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.SurveyViewPageActions;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.SurveyViewPage;
import surveyor.regression.source.BaseMapViewTest;

@RunWith(SurveyorTestRunner.class)
public class LoadTestGeoServerUrlCollectorTest extends BaseMapViewTest {

	protected static final int USER_ROW_ID_SCALE_PICARRO_ADMIN = 10;

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

	/**
	 * Description: This is a load test data generator which can be used to collect GeoServer API calls logged to ServerLog.
	 * Notes: This test is intended to be run on P3Scale to gather GeoServer API urls.
	 *        Before running this test ensure DEBUG is set to true on P3Scale app node.
	 * Script: -
	 *	- - Goto survey view
	 *	- - Verify correct survey page was loaded.
	 *	- - Enable Asset and boundaries
	 *	- - Verify Assets and Boundaries are displayed on the map.
	 */
	@Test
	public void TC_DataGeneratorTestToLogGeoServerUrls_FromSurveyViewPage() throws Exception {
		Log.info("\nRunning TC_DataGeneratorTestToLogGeoServerUrls_FromSurveyViewPage ...");

		Map<String, String> browserConsoleErrorLogMap = new HashMap<>(); 	// stores list of errors seen in browser console window.
		Map<String, String> failedTagMatchMap = new HashMap<>();    		// stores list of surveyIds that failed survey tag verification.
		Map<String, String> failedAssetMap = new HashMap<>();       		// stores list of surveyIds that failed assets are shown verification.
		Map<String, String> failedBoundaryMap = new HashMap<>();    		// stores list of surveyIds that failed boundaries are shown verification.

		Map<String, String> surveyIdTagMap = getTestInputMap();

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_SCALE_PICARRO_ADMIN);   /* Admin on P3Scale */

		CryptoUtility cryptoUtility = new CryptoUtility();
		Set<Entry<String, String>> entrySet = surveyIdTagMap.entrySet();
		for (Entry<String, String> entry : entrySet) {
			try {
				String surveyId = entry.getKey();
				String surveyTagEncrypted = entry.getValue();
				String surveyTag = cryptoUtility.decrypt(surveyTagEncrypted);

				getSurveyViewPageAction().open(surveyId, NOTSET);
				getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
				if (!getSurveyViewPage().getTagLabelText().equals(String.format("Tag: %s", surveyTag))) {
					failedTagMatchMap.put(surveyId, surveyTagEncrypted);
				}

				getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
				getSurveyViewPageAction().turnOffUseAllPipes(EMPTY, NOTSET);
				getSurveyViewPageAction().turnOnUseAllPipes(EMPTY, NOTSET);
				getSurveyViewPageAction().turnOffUseAllBoundaries(EMPTY, NOTSET);
				getSurveyViewPageAction().turnOnUseAllBoundaries(EMPTY, NOTSET);

				getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
				getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);

				Boolean assetsShown = getSurveyViewPageAction().verifyAssetIsShownOnMap(EMPTY, NOTSET);
				if (!assetsShown) {
					Log.warn(String.format("Assets NOT shown for survey -> Id = %s", surveyId));
					failedAssetMap.put(surveyId, surveyTagEncrypted);
				}

				Boolean boundariesShown = getSurveyViewPageAction().verifyBoundariesIsShownOnMap(EMPTY, NOTSET);
				if (!boundariesShown) {
					Log.warn(String.format("Boundaries NOT shown for survey -> Id = %s", surveyId));
					failedBoundaryMap.put(surveyId, surveyTagEncrypted);
				}

				BrowserConsoleLogCollector consoleLogCollector = BrowserConsoleLogCollector.newInstance(getDriver()).redirectOutputToLog();
				if (consoleLogCollector.hasErrors()) {
					String errors = consoleLogCollector.getErrors();
					Log.error(String.format("Found errors in browser console log. Error -> %s", errors));
					browserConsoleErrorLogMap.put(surveyId, errors);
				}

			} catch (Exception ex) {
				Log.error(ExceptionUtility.getStackTraceString(ex));
			}
		}

		Log.info("browserConsoleErrorLogMap -> " + LogHelper.mapToString(browserConsoleErrorLogMap));
		Log.info("failedTagMatchMap -> " + LogHelper.mapToString(failedTagMatchMap));
		Log.info("failedAssetMap -> " + LogHelper.mapToString(failedAssetMap));
		Log.info("failedBoundaryMap -> " + LogHelper.mapToString(failedBoundaryMap));

		assertTrue("Found errors in browser console logs. Refer logs for error information.", failedTagMatchMap.size() == 0);
		assertTrue("Found surveys where tags did NOT match expected tag. Refer logs for error information.", failedTagMatchMap.size() == 0);
		assertTrue("Found surveys where assets were NOT loaded. Refer logs for error information.", failedAssetMap.size() == 0);
		assertTrue("Found surveys where boundaries were NOT loaded. Refer logs for error information.", failedBoundaryMap.size() == 0);
	}

	// SurveyId -> SurveyTag map. Survey tag encrypted to NOT store customer PII.
	public Map<String, String> getTestInputMap() {
		Map<String, String> surveyIdTagMap = new HashMap<>();
		surveyIdTagMap.put("D62993BC-4AA7-BDA7-42BA-39DEFFC56AB7", "9NRrMT4268me6sfNtBp+77yKfGBoWGsqUZ161HXSzZ8=");
		surveyIdTagMap.put("79A27AA6-D5C3-6D3A-9A1A-39DEFE96D282", "8PHMTfRzjqrlm6Et+cLMio6XY90SqaQMpnxtCIpaV2A=");
		surveyIdTagMap.put("FC8321A7-0ABC-A4D8-3CFD-39DEFE337403", "8PHMTfRzjqrlm6Et+cLMikHVxV3LeFncbdIusp32A7o=");
		surveyIdTagMap.put("B7BB517D-F84D-3EC8-9CA0-39DEF9407E22", "EiXN+YBTuqb+/9DzZ4c2AEHVxV3LeFncbdIusp32A7o=");
		surveyIdTagMap.put("84A0D701-ED43-1528-30ED-39DEFB12AB19", "4lilVAQvPK/EIHgse5lNLVZMQHzZn0NQrczT1dE4KvU=");
		surveyIdTagMap.put("274C32B5-56AE-2086-900F-39DF035DCDC3", "B7FDIuBPz5EQyEsAslmgnUHVxV3LeFncbdIusp32A7o=");
		surveyIdTagMap.put("603B4688-CD6F-9A3B-5AD2-39DF03D45959", "B7FDIuBPz5EQyEsAslmgnY6XY90SqaQMpnxtCIpaV2A=");
		surveyIdTagMap.put("987071BF-F663-640D-1E88-39DF0911D8A8", "48jHDegRvTLhD7sKHOy/Y46XY90SqaQMpnxtCIpaV2A=");
		surveyIdTagMap.put("CBE1DB62-384B-549D-363B-39DF08967FBF", "48jHDegRvTLhD7sKHOy/Y0HVxV3LeFncbdIusp32A7o=");
		surveyIdTagMap.put("B37DF13F-E4B6-A284-5E4B-39DF0DB6F4DE", "W/+d8bSIGl/83/U1wfPFuUHVxV3LeFncbdIusp32A7o=");
		surveyIdTagMap.put("E1EF70E4-B863-BBE7-44FE-39DEFAD3F868", "4lilVAQvPK/EIHgse5lNLbyKfGBoWGsqUZ161HXSzZ8=");
		surveyIdTagMap.put("D60083B1-6990-5066-6EAC-39DF0A19C261", "TmuGpQYpqoPlPJ3hfXAfEryKfGBoWGsqUZ161HXSzZ8=");
		surveyIdTagMap.put("D27106AC-07DB-48DA-25C4-39DF0E08F4FC", "W/+d8bSIGl/83/U1wfPFuY6XY90SqaQMpnxtCIpaV2A=");
		surveyIdTagMap.put("60F3A3D0-B958-2EE9-4B6E-39E1CF2C3CF9", "roBK34p4UBxWq9U0xXoz3w==");
		surveyIdTagMap.put("A4816C28-2BD5-D46D-AB8D-39DF04E570C4", "Nl5z+00aTBIcV5GJ31yXdryKfGBoWGsqUZ161HXSzZ8=");
		surveyIdTagMap.put("3865E8D1-C845-9F10-D36F-39E1C9FE8BDB", "+4cH0iZLZfDOiGaxXEzMWw==");
		surveyIdTagMap.put("2E75C6E8-2DD1-6BD6-673F-39E1CE5213A3", "OEEppzL4hN/PSkYFsCv+lg==");
		surveyIdTagMap.put("E56D4211-D46A-1178-B14D-39DF0FCBE255", "quNJKYP6vlF3nUek8/ic2VZMQHzZn0NQrczT1dE4KvU=");
		surveyIdTagMap.put("3406801C-7CE4-5E03-262B-39DF40470C5F", "WDao4UIBRxis85ijVUdv4g==");
		surveyIdTagMap.put("950D4E20-87D0-F45D-F1BE-39E1C92C1E1B", "IP4uH1V5BhJUXT7QzmIepQ==");
		surveyIdTagMap.put("85350691-6F20-559F-2E96-39E1CE28139D", "OEEppzL4hN/PSkYFsCv+lg==");
		surveyIdTagMap.put("1BB27666-1A77-AD96-8494-39DF0F8AE1D3", "quNJKYP6vlF3nUek8/ic2byKfGBoWGsqUZ161HXSzZ8=");
		surveyIdTagMap.put("F664C757-6CF8-DBB4-2EE8-39DF507EEA8B", "DkXz50tuWJCCx78XnVydQIPDpEVvy6zSzWjYJFbSR+s=");
		surveyIdTagMap.put("CEA67C62-26E4-44B7-C8D7-39E1CEC5530D", "//limildADyNj5FwB6tJhQ==");
		surveyIdTagMap.put("3799C7C8-B2F3-3202-1820-39DF402E3CCB", "WDao4UIBRxis85ijVUdv4g==");
		surveyIdTagMap.put("99E20522-8D50-A296-25D7-39DEF82F4ECB", "/RMpKR2uLR0zgRpNgmYmow==");
		surveyIdTagMap.put("79D5723F-6B8F-E54F-BDBC-39E1C998967F", "lXT0e6uefJGnH+LoVasH2g==");
		surveyIdTagMap.put("D383B25D-C242-977D-C447-39DF50A5F69C", "DkXz50tuWJCCx78XnVydQJbYQTolWD18eSYSTXa/aXg=");
		surveyIdTagMap.put("EE0BFEA7-57D3-FD5B-675D-39E18FEAA124", "YINWYutwWP9HzmUmbt4BEg==");
		surveyIdTagMap.put("D846ABAD-4504-0CEF-008F-39DEC2C9F066", "lc+S1rdjpI7eaM0w1bKOxQ==");
		surveyIdTagMap.put("85899F74-34B3-4023-8F0D-39D0C0B7F067", "q6uENvTjqwCfNPBW7KXNHg==");
		surveyIdTagMap.put("15994D05-13DF-7523-073D-39D0C5C8160B", "q6uENvTjqwCfNPBW7KXNHg==");
		surveyIdTagMap.put("62A4F15C-F5AF-C219-38DF-39D0C6DB3480", "xsWTb5IFlJQ+BE7zj4ceGbpvSav4Hor+08Z0W9foryw=");
		surveyIdTagMap.put("7F2FEDC5-AD1B-3F26-08CB-39D0C1E29803", "TlqzDvlqjmIicWC79oWo1w==");
		surveyIdTagMap.put("6F855BF6-7387-DDFE-E8DF-39D0167F0423", "uS/qVQdtnJMQpoCfUsuPMg==");
		surveyIdTagMap.put("8D7AFFE4-BB4B-719B-78DA-39D03575CBB2", "jfunvdJ3GyaHphXq2rF+0w==");
		surveyIdTagMap.put("B948F70C-0889-5565-A68D-39D0CAFD53FC", "Gyb4uZY5VWnF2NJfujSXwYcTVGBmLjYGlW7oAuyyA8U=");
		surveyIdTagMap.put("119E7D36-1C74-9B6C-EE46-39D078D8A92F", "Y7h0kZPSdbUI+aJvu8KnUw==");
		surveyIdTagMap.put("4EEAFD5F-CF4F-1AE6-7350-39D03AC51EA3", "jfunvdJ3GyaHphXq2rF+0w==");
		surveyIdTagMap.put("900A9C39-926D-B41F-3588-39D036BAE031", "jfunvdJ3GyaHphXq2rF+0w==");
		surveyIdTagMap.put("2E3BBF1F-A5DB-B9B2-5FFF-39D0655F472A", "/DuFhNAI8ELqgLvh+r0yUg==");
		surveyIdTagMap.put("5663108F-C853-12BA-5FC4-39D0115C8176", "uS/qVQdtnJMQpoCfUsuPMg==");
		surveyIdTagMap.put("55BCE5F1-C22A-4401-E917-39D07DA4D9C0", "9E5hXYtpT9DONQbKxIY8XMf4fASku9rGGgWxwIqA4wg=");
		surveyIdTagMap.put("A0097721-6B34-CE19-9FAC-39CFC44AB114", "uWSsnrJ3a9H2B6K9PE1xGw==");
		surveyIdTagMap.put("9192E4D5-07FC-25A6-AE57-39D065A061AE", "/DuFhNAI8ELqgLvh+r0yUg==");
		surveyIdTagMap.put("22C9B9C7-561F-DF07-DAE6-39D0A6B0F414", "CpM0TmIMWsKyS65Zop1OYg==");
		surveyIdTagMap.put("34F300CA-5373-ADF9-045C-39CFAC855BE8", "81AG7stFCZK0x4itfwu5NA==");
		surveyIdTagMap.put("C34ED131-D34E-7B83-5A03-39CFAC8CF9C1", "81AG7stFCZK0x4itfwu5NA==");
		surveyIdTagMap.put("90040B40-4E94-D34D-9D2E-39CFC96E7491", "uWSsnrJ3a9H2B6K9PE1xGw==");
		surveyIdTagMap.put("1B7C8ED0-9A1E-3772-D4FC-39D0A714FC14", "nXTUyaAzfaeySzgl8/NPDs8CS6+TROWRFvf5NjmJ4UU=");
		surveyIdTagMap.put("BAD22D19-8C4B-1EA9-A99F-39D0AD535EA3", "LlqTNyeyrvjsItl53eOIL8fJWQltrxxKVECgoUBP9og=");
		surveyIdTagMap.put("A03C9FBF-DC88-73B9-0B74-39D065DCF1A7", "/DuFhNAI8ELqgLvh+r0yUg==");
		surveyIdTagMap.put("7986783F-0F93-5727-6AE1-39D0CC356920", "DUSACqhHNeglVQyTEX/uCkEXYsJnxp2TX5GFtO2QbRE=");
		surveyIdTagMap.put("4FEE0B7A-7F48-6873-3EC3-39D0A1CC414E", "CpM0TmIMWsKyS65Zop1OYg==");
		surveyIdTagMap.put("83D32DE4-DEB2-E56A-8F75-39D06625B817", "zviecCuXKEFZfGvNksMXTw==");
		surveyIdTagMap.put("9EB39714-62FA-915D-B94C-39D0A842EC89", "o2Su9G8DEY5N2E8zBByPNA==");
		surveyIdTagMap.put("4DFE8183-DD6F-F3AD-EA90-39D0CB2DBDDD", "8ZnH411xho3MIuyFrLe8bocTVGBmLjYGlW7oAuyyA8U=");
		surveyIdTagMap.put("BC7BA30F-B1F5-6BE5-A3C3-39D0CC555C1E", "UoXwHumHsMl+K6sLqGm7KaqCHZUOOLNkGnzekCY2vok=");
		surveyIdTagMap.put("DB994D09-1A18-30C1-88DC-39CF6EEED3E5", "Y7h0kZPSdbUI+aJvu8KnUw==");
		surveyIdTagMap.put("0811E476-B974-2A56-4B2C-39D016B37358", "uS/qVQdtnJMQpoCfUsuPMg==");
		surveyIdTagMap.put("EB2A2C3D-5FA3-3EE5-D185-39DDE6CE6DB0", "HUP0JPTxV1GQe71w6GfzqQ==");
		surveyIdTagMap.put("0F902B7C-8817-58A4-3C51-39DD0EA1EFD1", "tR3mSXyGI/pG9mGddXfRLA==");
		surveyIdTagMap.put("AE6C4B3A-E3D2-EEE5-7437-39DDC262DCE3", "ob0cnwuRFrj0Bv26wbN1gw==");
		surveyIdTagMap.put("85213BF7-9DF1-EA89-4677-39DC7E371DDD", "YYex3l4ZfvAyLmLTYf/rew==");
		surveyIdTagMap.put("20730D83-D997-E638-5212-39DD09B6448B", "muLGSWLS7ve/IWlgTJIpVQ==");
		surveyIdTagMap.put("2C4B77A8-5A30-B6BE-D03B-39DE4824B473", "/Y84CmpvBCvK7N3gttkzVA==");
		surveyIdTagMap.put("9C1A4B55-2755-ADD5-0F6A-39DE339A6FD2", "PrDyAulUiBigtnsDcb6p2Q==");
		surveyIdTagMap.put("787CED3D-8083-A656-3149-39DDC78CCF0A", "5HCA/op6LB6Nr7Rbd12Thw==");
		surveyIdTagMap.put("01581A39-56E7-0BC4-857E-39DD044DE66D", "8DkyPE9lduGLMBhVWT0S6w==");
		surveyIdTagMap.put("5DB4F5B2-1BD5-9063-EC7F-39DD9E8AE573", "yn8s7ohVrx2zkZWbxaI0ZA==");
		surveyIdTagMap.put("DAB19E78-1D94-447A-33D0-39DDE1A19027", "3W+6hhC2LYTu8x/gHx6cgQ==");
		surveyIdTagMap.put("DA151892-1104-CC2C-85EF-39DDB82D7043", "GvkxbF4lt/Rkq3+RJSz7Ow==");
		surveyIdTagMap.put("4950A082-89F9-D690-05B1-39DC623FF62A", "0b2GABYicq9XBgGjPfEBPjY7dpWwQ3QR+5dymUxRSa0=");
		surveyIdTagMap.put("4C859DFC-3683-4037-C243-39DE4D5B7ECB", "TdOljf9m1wRN0NI04VqlKQ==");
		surveyIdTagMap.put("D26157E3-B915-0A7C-D455-39DDEB8918D0", "sHXxkLXGhZX4ZQlp9cBMrg==");
		surveyIdTagMap.put("BBDBD66E-4FDE-1C75-2F8D-39DE24535787", "uGyi4GfVATVHBnB40b5TTQ==");
		surveyIdTagMap.put("688E2FC4-BE65-3DC1-CB5C-39DE2966B14A", "n7Ok8ccM0cUyBY8rn0Seqg==");
		surveyIdTagMap.put("28768116-A21A-037A-D759-39DE2E8140F1", "GQHQGBf3vm1ZE72HMqgLgA==");
		surveyIdTagMap.put("E0CB1EF4-5A7C-4083-94C6-39DD0983594D", "y2H3lc32xt6cDhP5SunsvQ==");
		surveyIdTagMap.put("EB772E2F-03EB-06F0-DF28-39DDA3B36557", "Q+3xBKaJTfwF3jQ2me2M0g==");
		surveyIdTagMap.put("5E1BDED5-EC19-55F3-48BE-39DDDC15B777", "lFd7sqqkFL0wugOlAmgMYA==");
		surveyIdTagMap.put("3CEAA2E6-29C5-D996-D7D4-39DD99683820", "UYG2PLqMVyjrwRPtsohJdQ==");
		surveyIdTagMap.put("383150F5-5C2A-ED8B-4E69-39DD9A41928D", "7gt7TRwR8gu/Sm61rFT0Lw==");
		surveyIdTagMap.put("857E2D72-3098-532A-3FE4-39DC630D8A83", "0b2GABYicq9XBgGjPfEBPjY7dpWwQ3QR+5dymUxRSa0=");
		surveyIdTagMap.put("80EBD9B8-C4A6-D3E4-C932-39DC5DE786E7", "sH75uMS/FeIR3ghWF2rh7POeSng/MTn3NUK9A8WgQoU=");
		surveyIdTagMap.put("283059DB-74DE-FD99-2AFA-39DDE67CDEB2", "HUP0JPTxV1GQe71w6GfzqQ==");
		surveyIdTagMap.put("E9C7C793-8A21-4BFE-CB0E-39DDBD5224C3", "ob0cnwuRFrj0Bv26wbN1gw==");
		surveyIdTagMap.put("87A7F3B0-B1DC-2719-3150-39DC15108321", "ofgk9iLn9aJ2pV3oaimz3A==");
		surveyIdTagMap.put("9C1A2EC5-0388-33E7-B2C4-39DDEC7CB0B3", "HUP0JPTxV1GQe71w6GfzqQ==");
		surveyIdTagMap.put("69BD4263-C4FD-0645-2BDF-39DC11228C5D", "TEfmsrWZpIt7CfQKmp+f/A==");
		surveyIdTagMap.put("224F420E-14DB-179A-09FF-39CF83366399", "zLi5zq1xYe5+2L02qpdca4YKOrPr6tPLg104pX6HBfI=");
		surveyIdTagMap.put("65844C33-BE10-62D6-E4A7-39CF7DF936F8", "zLi5zq1xYe5+2L02qpdca4YKOrPr6tPLg104pX6HBfI=");
		surveyIdTagMap.put("0D9C9BED-2D3F-D667-8C5C-39CF834A7042", "zLi5zq1xYe5+2L02qpdca4YKOrPr6tPLg104pX6HBfI=");
		surveyIdTagMap.put("B2CD436A-97D8-D835-0205-39CFA71580C2", "Ar70rDVudvX5pu63lxE3IYYKOrPr6tPLg104pX6HBfI=");
		surveyIdTagMap.put("1BB5268E-9853-9DF5-98B5-39CFCA9997A6", "HC59mrDHblzZxL80t6JJyA==");
		surveyIdTagMap.put("6FBC5291-97D6-7D87-4D4E-39CFC61AE83D", "dt94gqzMw/1G8MPGsGwA0sDsRQXeyFDp8nTu4370nSw=");
		surveyIdTagMap.put("BDEAEC7B-64E2-778A-CF20-39CFCB753216", "HC59mrDHblzZxL80t6JJyA==");
		surveyIdTagMap.put("EC242AB6-3013-EB96-9EAF-39CFA66A2D72", "slzLpZQ62zPN32yX3dw5fr/gAc5amzGvQ68X01oGRY5OcVkoz0Vurib2SfThb08s");
		surveyIdTagMap.put("935721E3-5F4D-9D56-119C-39CFA69D07BA", "D7ec2cLmx5LWDjDSn+eUNAZe9qv3GkwNEBDVN68C373WOMW7DrAi+eEKb1Kg4Kfg");
		surveyIdTagMap.put("FF3E85D8-3400-A20E-E8F7-39CF8CA776CD", "DZ1gQLhbk2O8scZRmRHzCpaMldPnT+kn7xV3ZdguOck=");
		surveyIdTagMap.put("8C0894CC-3008-5585-DA2E-39CFABEAFC86", "hCye1s7rU9s1/6bBUwsYWA==");
		surveyIdTagMap.put("3457DC5A-DADB-71D8-8781-39CF7E379745", "QhrMtgdPp9/8UgzKY1XaExclG/BbUVFmJPk1XaLAEwc=");
		surveyIdTagMap.put("6C9279CF-466C-9EB1-8684-39CF882BD3BE", "aS78CZuBgSjRKqevE2DKRg==");
		surveyIdTagMap.put("52499206-154C-B00C-E8FF-39CF8C9C696E", "oMvUM6XN9j0nLnh4AaymQA==");
		surveyIdTagMap.put("C5DEC44A-E07E-BAE7-CD38-39CFB0EEE1EE", "LCXrybbFy8XGZjn3WgBm7g==");
		surveyIdTagMap.put("1B299E45-6B77-D9B5-30DE-39CFABFD0E89", "ei9LFT6vxw2z3MFofxVHK3vs6tr4u4eroPDf8eqkUeM=");
		surveyIdTagMap.put("A3A43CAE-34BB-CBB6-2C46-39CFCFA5C4B9", "Yvc/AxD+szL6aB3mUghtfIYKOrPr6tPLg104pX6HBfI=");
		surveyIdTagMap.put("7175CB3A-B48F-8905-0EA6-39CF88351944", "b4W/M/34oykNR/qGIvC2UQ==");
		surveyIdTagMap.put("B22C4D82-6427-B847-2659-39CF8CCEEA8C", "tJjG8XrdE4sR8vQ7oqbQu06pedrc3/6i9SEsDCoqjts=");
		surveyIdTagMap.put("FAECBE9C-861E-EBCA-B498-39CFCFCBF50D", "jaFx0E7CG2cEilNfbwHp0IGOTrvjlQ/MBFLoUiJD7W0=");
		surveyIdTagMap.put("8F3F3004-6527-20D9-D7B9-39CF87972FBC", "iGO3m4QLXY4n42xOiNwjuMi4t4FPFWwBiyP7XsWlJdw=");
		surveyIdTagMap.put("91D210F6-7EA7-377D-E7D5-39CF8CE75260", "5pj8Uda3D3VlDisQekJjJA==");
		surveyIdTagMap.put("AA7B9584-D4CF-962B-E23D-39CFA7565A55", "wWyXhuDFwUkldHAFA5KBWmRPeCTrZ+nrJJ0cFQunZ78=");
		surveyIdTagMap.put("8A857F8B-20B2-E391-8728-39CFB19AE462", "wWyXhuDFwUkldHAFA5KBWmRPeCTrZ+nrJJ0cFQunZ78=");
		surveyIdTagMap.put("51A67FCE-9186-C7BF-D22C-39CFB1C0822C", "v9eOsE37LVvs18N8AbdoOIYKOrPr6tPLg104pX6HBfI=");
		surveyIdTagMap.put("93C0526C-0293-994F-4932-39CFC5C71B94", "Guu6QcuwnXwrbgu0jWL5J4wimUzXNyW8tY0ojprGB7s=");
		surveyIdTagMap.put("47C0369B-E9C6-84B5-0A16-39CFD0367589", "CYCT4fbRDeCSfkQgMN/Ly06pedrc3/6i9SEsDCoqjts=");
		surveyIdTagMap.put("5F5402B7-EDDE-0A87-2C02-39CF8CC55101", "zEw7Wh5paozYcDpUrIW4EMO0f/jHJnCC1xhtwou7k94=");
		surveyIdTagMap.put("0A1EA52D-92C9-2019-93EB-39CFA16F0A52", "nz8fitbNXvsQV6yl76ib6Q==");
		surveyIdTagMap.put("21166B2A-5935-087F-9049-39CFC59EA709", "H4plbcsBCm5ybfwawl7OuIYKOrPr6tPLg104pX6HBfI=");
		surveyIdTagMap.put("5A6A9043-9403-68F6-8CC7-39DBEFA1AFB3", "Uv7Hzs/MIa/wbbJOS2/NpsDsRQXeyFDp8nTu4370nSw=");
		surveyIdTagMap.put("28448727-8BE7-487A-CFF6-39DBE575E36D", "zSPJV0D1nBq3y0XUZDlRAg==");
		surveyIdTagMap.put("878C3A29-E329-590C-C716-39DC0E77B693", "A7SdYmn/KAKVbimYz9Q14A==");
		surveyIdTagMap.put("C3A32042-2668-B311-973B-39DB5A5E26CD", "SHQ0aD7kRtyIXE/0da8nWV+cM0wcoHPL9s4U9Bxf2mQ=");
		surveyIdTagMap.put("3D19A760-E5F2-56A6-C88D-39DC0E8FF50D", "A7SdYmn/KAKVbimYz9Q14A==");
		surveyIdTagMap.put("64425F76-79F7-721C-DBDE-39DA39F2A5BF", "imthPUGblCrSex1LdkVmeQ==");
		surveyIdTagMap.put("41215540-2983-8288-B7DD-39DBE59F3AAB", "zSPJV0D1nBq3y0XUZDlRAg==");
		surveyIdTagMap.put("BBFDB953-865D-58B9-D17A-39DC099C0AD6", "DFysGNPry9w9geLT6GAyVcDsRQXeyFDp8nTu4370nSw=");
		surveyIdTagMap.put("75300013-1E5C-5BA4-91F7-39DBEFCF536E", "Uv7Hzs/MIa/wbbJOS2/NpsDsRQXeyFDp8nTu4370nSw=");
		surveyIdTagMap.put("7D9D4955-E9B3-93BF-7E57-39DC0972C101", "DFysGNPry9w9geLT6GAyVcDsRQXeyFDp8nTu4370nSw=");
		surveyIdTagMap.put("3F203125-982A-8C45-5222-39DAE69B2988", "nJ+HwOUvqlsHIjnKf1begA==");
		surveyIdTagMap.put("0210BCDE-B84F-C349-761D-39DA3F2D909A", "CCT1teJ1wLPtopXidpdoSg==");
		surveyIdTagMap.put("ECA2EBEB-D4DA-9CD5-1421-39DB5A7322E2", "SHQ0aD7kRtyIXE/0da8nWV+cM0wcoHPL9s4U9Bxf2mQ=");
		surveyIdTagMap.put("2A8AD79E-3C76-33AA-B3BB-39DAEF3DF5DC", "1kunaEsK6BX83Uk3I2tjVQ==");
		surveyIdTagMap.put("C8DC34F8-1147-B0B2-2991-39DBEFFA2073", "Uv7Hzs/MIa/wbbJOS2/NpsDsRQXeyFDp8nTu4370nSw=");
		surveyIdTagMap.put("2E97EAA1-1148-0C30-E45B-39DB5F51C3F5", "1Hh0KoTnZuhNAjRn2OtJl6EsrccvNKcJ0evjUDvT4jE=");
		surveyIdTagMap.put("1715C670-E2A2-E1E6-CCE8-39DAE9E34502", "QzAbEdPvTiCHWfIzCZxMlg==");
		surveyIdTagMap.put("B2AE2DAD-484C-E54E-1CC0-39DB5F7BBAC3", "1Hh0KoTnZuhNAjRn2OtJl6EsrccvNKcJ0evjUDvT4jE=");
		surveyIdTagMap.put("533F2535-F582-B225-0D47-39DBE5CC10F9", "zSPJV0D1nBq3y0XUZDlRAg==");
		surveyIdTagMap.put("3F5FCA92-F7CD-C7D0-CBFB-39DBE5F553AE", "RLXbNEc1B+S0//HETNWzvZpPDyVB2CTXuknkT+84olo=");
		surveyIdTagMap.put("281690A1-BF22-CC82-2E2B-39DA3A325AAF", "TiqaINmtQtAfMnFcwMDNEQ==");
		surveyIdTagMap.put("AE06A64C-DD77-30D0-DCEC-39DB5F8AE1F3", "1Hh0KoTnZuhNAjRn2OtJl6EsrccvNKcJ0evjUDvT4jE=");
		surveyIdTagMap.put("0863F183-6C6B-FF82-9936-39DBE56384A0", "zSPJV0D1nBq3y0XUZDlRAg==");
		surveyIdTagMap.put("6B905423-5998-528F-83A5-39DB5A3D858C", "SHQ0aD7kRtyIXE/0da8nWV+cM0wcoHPL9s4U9Bxf2mQ=");
		surveyIdTagMap.put("358EA141-345A-7270-0DDA-39DAE9B2B72C", "nJ+HwOUvqlsHIjnKf1begA==");
		surveyIdTagMap.put("4FD581D7-A498-F8F1-01D8-39DB84F7206A", "y2bJoeLnFfasCQ5g7Ku2Sw==");
		surveyIdTagMap.put("9AFC97D8-D658-E8BD-B2EB-39DBC8CA3BA9", "oKNWBboxGvwSM2DMCG/aUBDzOiqM+s1FiB+z9lmEJTU=");
		surveyIdTagMap.put("888ABF6E-D09E-7D2E-2C03-39DAE65E86A5", "EaYDM7L2w85pWNs49RgaQg==");
		surveyIdTagMap.put("D649B9D1-8746-AAE2-1946-39DBE5E011A8", "zSPJV0D1nBq3y0XUZDlRAg==");
		surveyIdTagMap.put("4CEF6EAD-F177-CF3B-F236-39DBC927246C", "oKNWBboxGvwSM2DMCG/aUEaXhyubTRD83S6yUfoScAU=");
		surveyIdTagMap.put("DF759FAC-E77F-0E83-E5C8-39E1433A9922", "6ScynEc0GG9JSOFQGd2a2A==");
		surveyIdTagMap.put("A31AFFCA-15C3-64FC-E712-39E122B88BDB", "a80/p+cLkTUsMSBXFULeeKJs5VjqWrWXwj3dgBBO3zw=");
		surveyIdTagMap.put("055FB55B-4280-B9AF-82EC-39E142FBC34C", "eknA4Pr4WfKfhFCeGHAb8g==");
		surveyIdTagMap.put("A6D3B91E-ACC5-767A-B5A2-39E1220EBF42", "y/1+TxaNc3WRXJTy0DPSBwQrjopS18PLitohJasl/lc=");
		surveyIdTagMap.put("4D495406-D6F7-87D7-0E23-39E11FA42FBB", "kff+AgaLDZe5SAlA9C7wFcDsRQXeyFDp8nTu4370nSw=");
		surveyIdTagMap.put("25567C9B-5B77-57A1-33E9-39E1240DF57F", "KAZbgtxahHjgQ1+kTcotpg==");
		surveyIdTagMap.put("5A18ED6A-1B6E-4453-F93D-39E11D64E7DE", "2XqNzGOcrVz3EeLel/zx18DsRQXeyFDp8nTu4370nSw=");
		surveyIdTagMap.put("4ADE8087-960C-37A6-2491-39E11DAD4819", "ddBwgcPXB23+5O+vLWU5SA==");
		surveyIdTagMap.put("01B31C74-7FFD-A41C-EACA-39E11EAD55C0", "H7Ih56qBw0xckkqLBldO+g==");
		surveyIdTagMap.put("301D3C35-1D97-AA02-D27D-39E11E85ACD5", "W6dHy9cKTXCL4AwoXAZMqA==");
		surveyIdTagMap.put("293199B0-C128-8C09-240A-39E11F785A4E", "jp094fo1MN4YPPM5Oeioww==");
		surveyIdTagMap.put("A8021392-4CCA-78AF-5321-39E14637D67F", "sDOcbxPcduJZQsLzHPsB0w==");
		surveyIdTagMap.put("9EAB6F75-6C97-DE02-C924-39E119032415", "0xhD33OSXIVMmDut/ii1hA==");
		surveyIdTagMap.put("48C7C585-6646-7F84-72E9-39E124659CF6", "roBK34p4UBxWq9U0xXoz3w==");
		surveyIdTagMap.put("D93E4C68-1B21-F164-79AC-39E11F4596E3", "jp094fo1MN4YPPM5Oeioww==");
		surveyIdTagMap.put("6C36DCBA-CE4B-CEA3-3545-39E1270797AC", "XjdyH5DajaglnWJYMRw/IA==");
		surveyIdTagMap.put("0339BCE7-1661-4757-2000-39E11F20DDC1", "+4cH0iZLZfDOiGaxXEzMWw==");
		surveyIdTagMap.put("002AF1A4-A8E1-366B-A551-39E11EF05D18", "IP4uH1V5BhJUXT7QzmIepQ==");
		surveyIdTagMap.put("3A71FA74-B3D4-6C08-6EC6-39E124493271", "KAZbgtxahHjgQ1+kTcotpg==");
		surveyIdTagMap.put("BD22BAE1-DB54-A17C-0DD5-39E1247FF9F5", "skj26kRgkSS/9y0/zdX4EsDsRQXeyFDp8nTu4370nSw=");
		surveyIdTagMap.put("36768829-95DE-D251-D68F-39E112F14EE4", "rgwRO7h0Ze02RoFODhMd3w==");
		surveyIdTagMap.put("A775F758-47DB-D4A0-9C27-39E118EF5090", "0xhD33OSXIVMmDut/ii1hA==");
		surveyIdTagMap.put("BEB1C8D9-E964-CCD7-889F-39DE47AD5329", "JH6y7ESZZQDJH75gSyehDc66CMKcUVjtNkVW7P/lhro=");
		surveyIdTagMap.put("E057AB48-13F1-E1D2-7CC0-39DE48FD84B7", "JH6y7ESZZQDJH75gSyehDdBPNzxKsjCtk1lrMMMovMKqgh2VDjizZBp83pAmNr6J");
		surveyIdTagMap.put("8238C4FE-C594-F60B-1325-39DA5006FD72", "yVZ8fkRZDGoww5uXvthFWA==");
		surveyIdTagMap.put("84AB681D-F063-C827-CA43-39DA5B2D09B0", "7MTFBQAMzE2ENFvvokm3XcDsRQXeyFDp8nTu4370nSw=");
		surveyIdTagMap.put("9EC7B907-384E-5F93-CE3C-39DE479C9ABB", "yEUpyERllaBFD9Qo5OXuLA==");
		surveyIdTagMap.put("A2ECF8E7-5F05-13E7-632C-39DA550A770C", "NK/FVinw54hR4sNp7UBtdg==");
		surveyIdTagMap.put("CEAC72A8-BA86-92D1-B0AC-39DA567CA38D", "ML7piug9+/M56B7AwWMp3cDsRQXeyFDp8nTu4370nSw=");
		surveyIdTagMap.put("8596DF4E-F279-7811-8058-39DA594EF20A", "lrzQODmuc2jLsNIQUSb43Q==");
		surveyIdTagMap.put("EAE00C69-FEAC-C429-6094-39DA5A0A7D4A", "ODEOgSjcd52e0DLRfoCZNQ==");
		surveyIdTagMap.put("AE205BB3-DA11-357D-2CFC-39DA5AD13FD7", "XUfPuBWrRK9Ujoe4ETU8IA==");
		surveyIdTagMap.put("CE714AD5-619D-69C3-BD24-39DBBE2410B0", "U+AsYc2ZBAtqREutAEpDJ7qTQ2sl/hRqbih2rDopZ/U=");
		surveyIdTagMap.put("A2880C61-2D2E-31BA-EA0C-39DA55F6309B", "br8ts2PuoUJWvvezrcQqfcDsRQXeyFDp8nTu4370nSw=");
		surveyIdTagMap.put("8AF5A753-6D35-70F9-F415-39DA59D8F1CA", "ODEOgSjcd52e0DLRfoCZNQ==");
		surveyIdTagMap.put("55D5E2F5-7B56-0301-EFCF-39DA5F58449D", "94WrFewlaPhpkVWT4w3WUQ==");
		surveyIdTagMap.put("D39839D8-6B9C-B9E2-1D24-39DA54254328", "azz6jiW/qsu9F3mJYTebjw==");
		surveyIdTagMap.put("CB2F4846-B03B-2A89-94A2-39DA5B0FE313", "fe4CJCqIB7MY0aZjPI0bGOoCtLAAZAAOnhTXB3TBCeU=");
		surveyIdTagMap.put("A86C4D19-0C17-DA28-54A0-39DE47255583", "FPHX1WnoUCpr3H9V8z4Qaw==");
		surveyIdTagMap.put("2CF589BD-9DA1-39D3-39D9-39DE47434B21", "yEUpyERllaBFD9Qo5OXuLA==");
		surveyIdTagMap.put("A732B04E-4F26-8DC5-A594-39DA531F9325", "Q4l6Oxym7o16cTLZKf9BYQ==");
		surveyIdTagMap.put("61EAD13D-4546-B8BC-83A1-39DA565A3361", "EtQzn92eGdEhFbBA2fZXoOoCtLAAZAAOnhTXB3TBCeU=");
		surveyIdTagMap.put("15F62DDE-7A8B-CEA4-C96F-39DA53E39B79", "azz6jiW/qsu9F3mJYTebjw==");
		surveyIdTagMap.put("C6987C91-A6AE-7C12-2BD9-39DA5BA2795C", "cfpd8q0tDS0/f6KdQtaIIsDsRQXeyFDp8nTu4370nSw=");
		surveyIdTagMap.put("4065D14D-D0DF-1D2A-DAA6-39DE474DF8FD", "yEUpyERllaBFD9Qo5OXuLA==");
		surveyIdTagMap.put("0D9DEF60-F641-5030-13DB-39DA560901F8", "9qCsDY3eCEvGLrbFxmB5tg==");
		surveyIdTagMap.put("7704AD27-96C4-404D-4E41-39DA569D12C6", "JKrCeK4RK5IZoNAjCSRkUDyLwId8HATdRdQIS8Q9IkU=");
		surveyIdTagMap.put("926FBC0B-B94C-6DC6-0F72-39DA5B6FDC22", "V8Mc4c18O/QjWLfJToymhcDsRQXeyFDp8nTu4370nSw=");
		surveyIdTagMap.put("974F9B7A-1129-F91D-3321-39DA5671A719", "Yg0WmM9WzkLJsAgDgycpEMDsRQXeyFDp8nTu4370nSw=");
		surveyIdTagMap.put("D0243B91-D73C-E22A-F170-39DE473C988F", "yEUpyERllaBFD9Qo5OXuLA==");
		surveyIdTagMap.put("9F6F8C8B-A2CB-4EDD-B705-39DA55ECCA7C", "8v7LYkiBBXucRhSYiRVhNkwvvELpyj4u1QepEOYkOr8=");
		surveyIdTagMap.put("12D42A1C-99B6-0AB1-6C28-39DA59CB11AA", "YtkbmH66K76oN8++QIMCPQ==");
		surveyIdTagMap.put("356DDE2F-5FDD-22FF-AD10-39D6B9B61524", "NuiViW+v+jt0TdOuNi5wXw==");
		surveyIdTagMap.put("57ACD890-D221-7C7F-D6BF-39D695028791", "xYln27rKWwo9x3MHzZE+RQ==");
		surveyIdTagMap.put("91668A6E-6C53-23B5-9087-39D6B3B69296", "Am/G1XK1S8vYtLd6yJf8Yg==");
		surveyIdTagMap.put("E25754AF-3F0F-EF28-D823-39D6AFB7CB00", "jcvUBSqFHi5y+f88K+TVAw==");
		surveyIdTagMap.put("D894C73F-7F7B-7488-D5D8-39D6AF3C4B51", "UeZumz8sxxsHdoapnu70+g==");
		surveyIdTagMap.put("ABE01529-8571-9835-D6CA-39D6B4D98BA5", "f+Wzmth9t5+jprDwzkkuXQ==");
		surveyIdTagMap.put("FF84A1E9-9F64-71C6-8BF5-39D6B4BC1827", "z7juEpRO3TCRW9O0SWweqQ==");
		surveyIdTagMap.put("D978CC2A-8DFB-28CB-6EE4-39D69543DB61", "sCdHmpSSgcyyY5yVN3t30Q==");
		surveyIdTagMap.put("01550FB2-D4C7-7D49-E710-39D6AF9DB5F1", "jcvUBSqFHi5y+f88K+TVAw==");
		surveyIdTagMap.put("33C642BA-181C-7704-D4BB-39D6B4508735", "z7juEpRO3TCRW9O0SWweqQ==");
		surveyIdTagMap.put("2D4B030B-16C0-3E64-37BF-39D6B8BE837F", "uQY8OLTEox7oKS3vpYa/+A==");
		surveyIdTagMap.put("931AD1AE-AC68-C342-290D-39D6B48309DF", "f+Wzmth9t5+jprDwzkkuXQ==");
		surveyIdTagMap.put("0F8C55D2-1A27-FAAD-6E1A-39D6B912D06C", "uQY8OLTEox7oKS3vpYa/+A==");
		surveyIdTagMap.put("BA3A8BAE-C058-AB47-2C8A-39D6B8D8C70C", "uQY8OLTEox7oKS3vpYa/+A==");
		surveyIdTagMap.put("7972C9FD-603B-C2DE-C02D-39D6B8F8EF91", "uQY8OLTEox7oKS3vpYa/+A==");
		surveyIdTagMap.put("E5ED70D8-ACA1-9855-9838-39D6B9638D85", "bK9Tk26uWBLtO44fjAi1jw==");
		surveyIdTagMap.put("8F9AE21C-06D9-F9F2-2BB2-39D6B9725E98", "bK9Tk26uWBLtO44fjAi1jw==");
		surveyIdTagMap.put("06F86AE5-6CBA-8744-CE17-39D695402370", "sCdHmpSSgcyyY5yVN3t30Q==");
		surveyIdTagMap.put("9C05330B-1CEB-74AC-196D-39D6B9035BE9", "uQY8OLTEox7oKS3vpYa/+A==");
		surveyIdTagMap.put("2DDDB869-A36C-CEC6-A562-39D6B9A886B8", "NuiViW+v+jt0TdOuNi5wXw==");
		surveyIdTagMap.put("B110C4BB-9C76-4223-8405-39D6B4AF444F", "z7juEpRO3TCRW9O0SWweqQ==");
		surveyIdTagMap.put("33160D02-9610-7360-50DA-39D6B9458A71", "bK9Tk26uWBLtO44fjAi1jw==");
		surveyIdTagMap.put("AACC907E-1256-F667-0DD6-39D6B95579DE", "bK9Tk26uWBLtO44fjAi1jw==");
		surveyIdTagMap.put("E7742CF1-791D-17B2-6E06-39D6B961201B", "bK9Tk26uWBLtO44fjAi1jw==");
		surveyIdTagMap.put("14B88F6D-B36A-4BC8-91BA-39D6B9C0B55E", "NuiViW+v+jt0TdOuNi5wXw==");
		surveyIdTagMap.put("B4A3A73F-4124-272F-BCB7-39D6B47D1F3D", "f+Wzmth9t5+jprDwzkkuXQ==");
		surveyIdTagMap.put("F3A57837-BD86-CC08-1B3B-39D6B9B3E9EC", "NuiViW+v+jt0TdOuNi5wXw==");
		surveyIdTagMap.put("F1D0A597-3329-A85B-E088-39D6B4821878", "f+Wzmth9t5+jprDwzkkuXQ==");
		surveyIdTagMap.put("FB5A2A89-58A4-6C2A-9359-39D6B4BA7F15", "z7juEpRO3TCRW9O0SWweqQ==");
		surveyIdTagMap.put("6C9163A5-2A74-60D1-00D2-39D6B9847975", "bK9Tk26uWBLtO44fjAi1jw==");
		surveyIdTagMap.put("B6067A71-A02C-CFA2-8BDA-39DF773B58CB", "L/TjspWp3WQp6T7jPmHLxKqCHZUOOLNkGnzekCY2vok=");
		surveyIdTagMap.put("3460FE56-AE59-E471-3297-39DEB810ABDE", "nDa1P+saGyjoo+TjC9hAKw==");
		surveyIdTagMap.put("B45E0222-F9C5-5605-FAB2-39DEB80AA31B", "233SOhHkYp+TKKHGrsiknw==");
		surveyIdTagMap.put("236F47C6-AD73-E5F0-D0D7-39E22A49A086", "rxrV357FA+u1DKa9lBlvWQ==");
		surveyIdTagMap.put("DFEB5E43-6FA0-9D2C-9F90-39D9E9982994", "qaIcmaxFwBA48Yn6pwMTjg==");
		surveyIdTagMap.put("0AB0DAEA-C57D-F030-2B5B-39E22A7968EB", "qrwMnysRXYysROnS7X7DUA==");
		surveyIdTagMap.put("DFC40704-51F1-A77D-F27A-39DEB8385F9D", "1DK2eiLNP2StkRoXQwzubQ==");
		surveyIdTagMap.put("109F7A5A-3DE6-DDA1-5A67-39DEB830775F", "XUimFuu508LSGsW6z7FV6Q==");
		surveyIdTagMap.put("B796B0CD-E692-2AAB-0722-39D9E9989B20", "Cx2q3EmSqrbocWzI9wqJjg==");
		surveyIdTagMap.put("229FDC50-A614-491A-2201-39D9E9BE1071", "iUthPJ/lPilm6D8TGw2FDA==");
		surveyIdTagMap.put("29EE2E5D-5642-BD54-2C44-39DEB7F01B5F", "lxxQcKNz6q/Yy1eo5nwa+w==");
		surveyIdTagMap.put("4EE0F37F-397A-F7C0-B45D-39DEB858A403", "DYgnU3M6uSIw9M8VheA7mg==");
		surveyIdTagMap.put("A156D8FF-A374-C601-B6B0-39DEB86D5647", "KnSIe2Y+h/T3VYXlzsKZ5Q==");
		surveyIdTagMap.put("57EA19D3-4B34-D7C0-E50D-39DEB8752CB8", "XvoZV8psgczf0RgvoneNAQ==");
		surveyIdTagMap.put("A796FC63-40D6-A64B-3F1D-39DEB96842B1", "MX7+iYQe5pbiwopbiXxpig==");
		surveyIdTagMap.put("7F0B3ED3-D565-1F73-7096-39DF7731FACB", "Wj65PdXG4sYFn1asvO8Pmg==");
		surveyIdTagMap.put("CAFE1BD9-E4F6-8D85-2721-39D9E9C4F7C5", "569uJtCp1RDPxwT2MDqLdQ==");
		surveyIdTagMap.put("2E31D9A4-2318-51FA-9D71-39DEB828F3C2", "/p90zq9Q3gcOQpUcj9eTFg==");
		surveyIdTagMap.put("F8B4396C-1097-645C-D97C-39DEB871220B", "XvoZV8psgczf0RgvoneNAQ==");
		surveyIdTagMap.put("44231B59-3090-A85A-5AF0-39D9E9BE459B", "EyA5tpSjaTxMrqtAfa3/BA==");
		surveyIdTagMap.put("3BC1D288-E20E-CFAE-FC2E-39DEB8532C19", "u7bquA4RgUpxqhIjb4zHKA==");
		surveyIdTagMap.put("F1FA6FB8-DAED-39DB-5FAE-39DEB8740262", "XvoZV8psgczf0RgvoneNAQ==");
		surveyIdTagMap.put("797E07EC-D2FA-1ADC-9211-39DEB96360D4", "lxxQcKNz6q/Yy1eo5nwa+w==");
		surveyIdTagMap.put("64842364-98A0-ED91-86D1-39D9E9C9010B", "Re+3bYWqQO4wfmZAVqPjvQ==");
		surveyIdTagMap.put("11DF635E-5FB2-A5E2-491F-39D9E9C92E74", "PqyKibwiOvQd1ggRKLzjZg==");
		surveyIdTagMap.put("869972CB-26C9-531D-21E9-39D9EA318DA7", "78ka7lxQhVTgISIypGjrQg==");
		surveyIdTagMap.put("73C99BAC-3807-9B09-8682-39D9EA8A7AD1", "No3LnkXZFLFDRh4z6k/wFQ==");
		surveyIdTagMap.put("A0A93C3F-DB60-9E42-85F6-39DEB856AB65", "+Jf4BItBkPfDv/bV7xGhcQ==");
		surveyIdTagMap.put("D88E2856-8CAA-7838-B10C-39DEB865C23B", "tj7R51Uqs3kWOs7j6C7w1g==");
		//surveyIdTagMap.put("321439C9-6870-9D6D-2509-39DEB93CAB3D", "C2MttACLviWnNMzjnggDAQ==");      // verify survey
		surveyIdTagMap.put("AF1F9720-0467-5D65-E800-39D79A0CC445", "4gq0lP4d84phjUewKarzon6u9Lfcx2SjOlBHyA2uykQ=");
		surveyIdTagMap.put("43FFBEC2-C7DA-4069-0942-39D7F6CF3F96", "IrLb+qjt0CdkvvhjO901Jw==");
		surveyIdTagMap.put("923C52C7-6BF2-95A5-9570-39D801129733", "d85Ckya9MoFdBZFNb7kL1A==");
		surveyIdTagMap.put("61E52D6C-EB93-6A43-DD5D-39D7FBF4BDB3", "k+j0wHIQOoUmZAIhLVYhqQ==");
		surveyIdTagMap.put("19E6530A-465A-1ECA-639B-39D7AEC7D7F4", "YMXfeQOpF2DO9L/pvymTkwC/zctVAJGz6Vd7leWEETM=");
		surveyIdTagMap.put("15FD98F9-FC83-A943-2FED-39D7A90783C3", "7dP4ueUgStHxi/qxYxhee+ZRqaZICNZghbAr+5ZgsWA=");
		surveyIdTagMap.put("C99BACB2-845D-E04A-1AA2-39D794EAB429", "4gq0lP4d84phjUewKarzosbzzJ1TnG3AZmgdP4+caPY=");
		surveyIdTagMap.put("B4F7EA85-F79D-58C8-D5E8-39D7F115CC34", "vvbiiMQ5xlksm5TNNIj/xA==");
		surveyIdTagMap.put("E7D1BE64-68AF-527D-3AF1-39D7F7A78AA1", "wSeQzfERW6Ovia+Zj/2mX8DsRQXeyFDp8nTu4370nSw=");
		surveyIdTagMap.put("AAB5461E-DFA3-A7A1-1473-39D7B3D5B51D", "7dP4ueUgStHxi/qxYxhee/XDVfr4ZLO2XIRzEzwPpv8=");
		surveyIdTagMap.put("7B195B34-38E2-F84A-EBE0-39D77ED1D983", "A+9h0UYZsA1OmXPjEhLdSA==");
		surveyIdTagMap.put("80777E48-2ADC-7D6A-5002-39D7EC5E2AF7", "aDr0MqWGUxKjdo5UdFg23Q==");
		surveyIdTagMap.put("1699298B-400A-2795-E5B5-39D7B919A041", "Vmc7Yfr/L6N+Nem6OC6d4g==");
		surveyIdTagMap.put("E8CF586A-95DC-E251-CC05-39D77E994609", "IrCDXkbjhDNuk8avp6A5xg==");
		surveyIdTagMap.put("FC5D3AE1-F174-8CCE-260A-39D7A4D6A898", "Exj4gSUdZT0p4x5A8i095w==");
		surveyIdTagMap.put("2E59B0BB-3A70-4219-E036-39D79B1D4540", "2gfEyarp2MO9y+xNA0d3bScnjvvwYv8v5oFhWo/NNNM=");
		surveyIdTagMap.put("C6DC426E-9A9F-23D6-2F69-39D7A45899FE", "Azohp2/g1nlb4F+hl5qSVidQVtxSl4zFG8t6UAfyAYs=");
		surveyIdTagMap.put("7A9B6660-E1F1-9C07-9ABC-39D7AFCA97E4", "E5+z8aPIsrXKhZyu14KvNtaYorvi2Qe21a3gJxeKrhM=");
		surveyIdTagMap.put("8F28AEEA-0B03-DD5E-D09C-39D7756E1451", "xw0Q05VMATtB8koazIP6mw==");
		surveyIdTagMap.put("680BD036-B2A0-B7A0-5E37-39D783B46940", "utMMykxi7K2nKjfxjncK/w==");
		surveyIdTagMap.put("8A3511A4-B392-E95D-3551-39D7A4C16ED3", "YMXfeQOpF2DO9L/pvymTk9xGDKHucCqaecI54PCO//c=");
		surveyIdTagMap.put("6B17E60D-D2B7-DDF2-D220-39D7F2CF46F5", "lykUMaAoUIHDU5hGKD+49w==");
		surveyIdTagMap.put("8BE35013-D7C4-2BCE-1F88-39D774F5EE59", "O6YC5tG4al7SmRVa+I4ZkA==");
		surveyIdTagMap.put("4ED94873-9899-6DDE-3E1E-39D77E671B08", "Paumk8BVWZC+z/keKeTHTg==");
		surveyIdTagMap.put("B04782EB-072D-BB14-185D-39D783D6FF76", "IfTIBp72ntukq/OB4vq82g==");
		surveyIdTagMap.put("7B5C1D2C-37BF-711A-5169-39D802B0D3D9", "PxuUIdyQhKungUpG3IeHSA==");
		surveyIdTagMap.put("E55A9BFC-2EC1-0720-C4AA-39D77FD7BC19", "AF+3P6fCMr4kzmNHQt5dvg==");
		surveyIdTagMap.put("A7AFCEEF-AD45-8CB7-79AE-39D7A8D87007", "7dP4ueUgStHxi/qxYxheewSAiPZ5NWOJszzNZLuQHIg=");
		surveyIdTagMap.put("40285DC5-207B-22E7-A02C-39D78384F37D", "DIJlHeTXz+giRi8HuBU+aw==");
		surveyIdTagMap.put("2D4EAC5F-DF9B-20F3-E430-39D7ECBEBAE1", "bR9bf7dFvvAq1KOllOFhRQ==");
		surveyIdTagMap.put("40718542-4038-B183-6BAE-39D31A308B48", "JJE9Ox7+RDJ5oKioyyY4Fg==");
		surveyIdTagMap.put("C5176773-40D8-3E95-BE34-39D31F0294AF", "y58kO+9Bp4KRLt1EwsJl1w==");
		surveyIdTagMap.put("674256E9-DB3E-88C0-A974-39D319D74124", "y58kO+9Bp4KRLt1EwsJl1w==");
		surveyIdTagMap.put("7713F3CF-804B-582E-B6DE-39D3B6360B11", "mjy1IUc/QfyqbbF9aoBrCLKKKQKjMlGUpTJrlRFIqfs=");
		surveyIdTagMap.put("C47A8A0F-D361-9741-3DA3-39D3BB569956", "mjy1IUc/QfyqbbF9aoBrCJ13TgmB2W2ji2Dl/broxr8=");
		surveyIdTagMap.put("32B910BD-9727-0621-264C-39D31967E216", "FYWqe1qlsNKunFS8cshz8Q==");
		surveyIdTagMap.put("0039DA14-CC96-7A49-AB5D-39D314585626", "FYWqe1qlsNKunFS8cshz8Q==");
		surveyIdTagMap.put("E1C26E70-8759-5A03-14AC-39D3A1A38526", "eGOiLPfWf2wUjOYYaxlq230gww9nibSj4AL5PpkNj+E=");
		surveyIdTagMap.put("6F6E519B-9D17-AE3C-4C7B-39D31E9AE9AB", "FYWqe1qlsNKunFS8cshz8Q==");
		surveyIdTagMap.put("745CFB2A-D1A7-5184-C58D-39D391F66E3E", "+eLr2G2LF+0e31fmDD0hRrYK97RgMuwxVj+njp9ZzoY=");
		surveyIdTagMap.put("02526EA3-2A0C-6A4D-9983-39D3C04E116F", "xeO1t7SzWNw/8dweko2RPSpm4A0diZ3bzYxSh7x8CTo=");
		surveyIdTagMap.put("7F9F2C31-8144-BF9A-68C8-39D378D80618", "xeO1t7SzWNw/8dweko2RPZMQO42ClYG2UgHyAAcu0bY=");
		surveyIdTagMap.put("3BBA6D64-C3BD-6C2F-0B69-39D39C961610", "Dj172wFzX+OBU0mrhKUTpKQGdDvqjo1TSKJrhtqzQNc=");
		surveyIdTagMap.put("56718F2F-19C9-012A-F2F0-39D36DE3007B", "ELms4hxzRYft0ra2IA9Dy72cH6v6vTGYYekeoSmr6tA=");
		surveyIdTagMap.put("064A606B-2C32-F1B9-B804-39D313F6BDE3", "Asf/qxqOGDH+BHyIpAEKqKMxzrJDC85ZlLvCvuiCS9g=");
		surveyIdTagMap.put("08645537-5647-8F8D-B5D1-39D3B611DCEE", "xeO1t7SzWNw/8dweko2RPXo6pX7bB3Yw/NeL/HJgzVM=");
		surveyIdTagMap.put("B0B58D4E-FBA6-BF8C-B42A-39D3BB36C176", "xeO1t7SzWNw/8dweko2RPWwSTUzPOcnO3Zh0a1nFFr8=");
		surveyIdTagMap.put("DA0895DE-F4D2-37CB-D95F-39D37D50FFF7", "rGxv/LkOzTFKlVwIm4p8ixb0JYQBR00Mlh++3jb1hbQ=");
		surveyIdTagMap.put("20EC4191-1EEF-B450-E1F0-39D3971829E2", "p8pcShQFrO5bjrkUNSqF3dz96QQL0dZ2iPccCwIt2oY=");
		surveyIdTagMap.put("F41DC05A-164E-7815-085E-39D3A16621B2", "NghHkdjylxWHFArMNUaQPMHFriqqhxgtjTw1APt9ePA=");
		surveyIdTagMap.put("AB1564DF-8DE0-A341-4D28-39D3730C5C55", "kUvN4ER+kG2sVcPMweSnjG8qYzpPD8mfGEZhenU8DL4=");
		surveyIdTagMap.put("5B6384E9-C4A9-F44C-C534-39D378788516", "xeO1t7SzWNw/8dweko2RPeYychL7BnCWNlhHYGZtNu4=");
		surveyIdTagMap.put("B7BA709C-08C1-CE2A-D36A-39D37D945B68", "rGxv/LkOzTFKlVwIm4p8iwV8Wxuh/1JzP+dXGC5PDkU=");
		surveyIdTagMap.put("483275A0-9EC8-A3CE-DC19-39D36EF3BBE3", "CqH7JgPSbZ1pq5dhgKcspa46HiQpxYaRqgIi3GYyAIA=");
		surveyIdTagMap.put("040C174E-9D37-333E-A40B-39D39254C910", "UvaCyBuQs/UBWXFxt1XejXx31FbIZ5IteC+hs7GvkcI=");
		surveyIdTagMap.put("271B730E-571F-7FFC-549C-39D39C3CB81B", "VF7S22UNXIAb0JXT9J6OyE8RiwqlipCumi2NRAVR7h0=");
		surveyIdTagMap.put("7D9E2B72-F248-EE96-DC74-39D3735BCA44", "DQ6mjFQhnyZhV49Ua7U3RPm3rleYWkIL6Di9jxlX6UY=");
		surveyIdTagMap.put("CC7864BC-C42E-3F0B-C052-39D39DAA30FB", "Dj172wFzX+OBU0mrhKUTpKQGdDvqjo1TSKJrhtqzQNc=");
		surveyIdTagMap.put("8799AD31-442E-AD0F-22E4-39D3975E4F58", "p8pcShQFrO5bjrkUNSqF3djVsfFTOiA91wckziX9sXk=");
		surveyIdTagMap.put("D65DA62F-6C6E-C6C5-0173-39D318696E8A", "rQr8nXGEgaerTg534yyr1cDsRQXeyFDp8nTu4370nSw=");
		surveyIdTagMap.put("4B62B50C-0B6C-DF73-F195-39D7CD610447", "BNncetLUAcF3gQ4aU1cHm/LJF8mIdpxB9pSansdcmHA=");
		surveyIdTagMap.put("CDACDED1-C65C-6AB0-4A46-39DBE5D0F5A8", "zVeKKAAIrSrt/HCJCglrd0fM4LNUZT22eo1/0Eis1XQ=");
		surveyIdTagMap.put("6174E287-28DB-FCDB-6D9F-39DBED2A3D65", "v4eWYH90Muo/CtsVlI2Hug==");
		surveyIdTagMap.put("CD679CF9-D254-ACA6-27B2-39DBE0463576", "SxZOjWJrPPOSKpeyV05OE8DsRQXeyFDp8nTu4370nSw=");
		surveyIdTagMap.put("4C4694E6-1B12-ED31-7A15-39DBCD2259E7", "0wTRI/LuIlm6N+yEoPFDjA==");
		surveyIdTagMap.put("A358A788-9D1B-FD9B-1B0A-39D7D316ED63", "4aPTBGLpJNELkM0/kE6YH030Hmb+vVWjNXjh42p2rjI=");
		surveyIdTagMap.put("7CA52855-F629-75B4-4F24-39D7D150124F", "4aPTBGLpJNELkM0/kE6YHzyl+2K6P+KNVKpFQd/ASZM=");
		surveyIdTagMap.put("46BC326E-C329-23ED-090C-39DC0B68BF75", "b7jakH6PB1CPYh05plf/s1tQOiVod3vdzLikZ1CcK/Y=");
		surveyIdTagMap.put("FA1EEF52-126A-E59F-31C0-39DBE14D53EF", "SxZOjWJrPPOSKpeyV05OE/l0mWponEZR9QKXPoiSL7w=");
		surveyIdTagMap.put("D724F706-1ACE-3F66-7F55-39DBE57621D4", "zVeKKAAIrSrt/HCJCglrd+hfYa8L4TFO5zC2Vaq5Suw=");
		surveyIdTagMap.put("3654B882-5E34-4AAC-F74C-39D7D2977140", "4aPTBGLpJNELkM0/kE6YH030Hmb+vVWjNXjh42p2rjI=");
		surveyIdTagMap.put("60E5E631-963C-F5FD-8E0F-39D7D1DB3A0E", "4aPTBGLpJNELkM0/kE6YHzyl+2K6P+KNVKpFQd/ASZM=");
		surveyIdTagMap.put("B4D050DB-328D-B335-CC03-39DC0473C3DB", "67jdfwL8RCzugTxWcZw7Jm1UnojRq0zf4FBFOAg5VZo=");
		surveyIdTagMap.put("6BFF5B49-420C-7A01-2A9D-39DC0996E94D", "WsT6bBtMAkVi8yfNqRhEVaxZuTx9BCS2eGv38uA7VmI=");
		surveyIdTagMap.put("711E4800-1B35-E902-B9DD-39DC0116AE27", "0NZShZ671pOjHbo9x1i58NBtKYaTzVKOvyumLdJjSxk=");
		surveyIdTagMap.put("1AA64776-F0A4-F5A2-D957-39DBDCFD0671", "ui4ORmWEJbe7luOS+d2cC5gkK6vFVBzrUabQQa+zcdM=");
		surveyIdTagMap.put("539D8B9E-DD2F-D204-2AAD-39DC105A1718", "kYLIJTR4Wa1DqshwWSOFGA==");
		surveyIdTagMap.put("62C1FD08-7221-B6D7-C1EA-39D7D36C4167", "4aPTBGLpJNELkM0/kE6YH030Hmb+vVWjNXjh42p2rjI=");
		surveyIdTagMap.put("05F9DAB1-AE5A-122C-D37E-39DBCD15E546", "ftrWsXihc6KbJSvDEmFWjQ==");
		surveyIdTagMap.put("A26286D4-B3B7-2B7F-8A9F-39DC01646BB5", "YZvGv30JoGEWgP1hujSvINBtKYaTzVKOvyumLdJjSxk=");
		surveyIdTagMap.put("4082FBDB-7924-A4EF-A132-39DC0A03D7C2", "WsT6bBtMAkVi8yfNqRhEVaxZuTx9BCS2eGv38uA7VmI=");
		surveyIdTagMap.put("CD59B5A5-2426-E6EA-87CD-39DC102A5B7E", "U2U6RY9ZiG6GYty4LsVfqcDsRQXeyFDp8nTu4370nSw=");
		surveyIdTagMap.put("51FC5DBE-E0EF-D216-D670-39DBC27C7FC7", "Vqb0yjxBZ8sHmLFOq33IAw==");
		surveyIdTagMap.put("68763E79-36EC-3AF3-4394-39DBD1200DDC", "WYRaAX7D8oRnP6eXm7ao3g==");
		surveyIdTagMap.put("B0A687A5-AE1A-40BF-19AD-39D7CCAE1DC4", "zAW01/hBhEB1tHgRlwP+yw==");
		surveyIdTagMap.put("399CFC1A-6741-B3D3-0D29-39DBD139EB37", "WYRaAX7D8oRnP6eXm7ao3g==");
		surveyIdTagMap.put("BEC7A0C6-F5EF-A4DA-BF85-39DC056EE8A9", "67jdfwL8RCzugTxWcZw7JoBP11NKorUyrDuUgPm5bC8gFHH8M17efuP5St3ZQN5Q");
		surveyIdTagMap.put("3B9EDE9D-6E71-CC8B-953D-39D7D038E4C5", "JhO7H8Wl7nsKdsZDYa0rLRJlS6ra7usPPjni9K62q5c=");
		surveyIdTagMap.put("EFF01752-8DA9-F220-EEB4-39DBDCEA9EF9", "ui4ORmWEJbe7luOS+d2cC+BqpkljxVTGOn3CCq7iB+w=");
		surveyIdTagMap.put("8FBFA95D-660D-00DF-47EE-39D7D6048BD1", "1zXNPJ/2jSmXgFS/Jle9cjCeAIxEVlly1nXfhJFjzxI=");
		return surveyIdTagMap;
	}
}