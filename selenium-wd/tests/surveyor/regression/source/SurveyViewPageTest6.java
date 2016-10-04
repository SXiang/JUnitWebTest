package surveyor.regression.source;

import org.junit.Ignore;
import org.junit.Test;

import surveyor.scommon.source.SurveyViewPage;
import static org.junit.Assert.*;
import common.source.Log;
import static surveyor.scommon.source.SurveyorConstants.ISOTOPIC_CAPTURE_CANCELLED;
import static surveyor.scommon.source.SurveyorConstants.ISOTOPIC_CAPTURE_NOT_NATURAL_GAS;

import java.io.IOException;

public class SurveyViewPageTest6 extends BaseSurveyViewPageTest {

	public SurveyViewPageTest6() throws IOException {
		super();
	}

	/* * Test Case ID: TC1227_StartDrivingSurvey_CurtainView
	 * Script:
	 *	- 1. Login to driver view 
	 *	- 2. Click on Mode -> Start Driving Survey 
	 *	- 3. Provide survey tag, select  Survey Time: Day Solar Radiation: Overcast Wind: Calm Survey Type: Standard 
	 *	- 4. Click on Start Survey button
	 *	- 5. Click on Curtain view button - (Position ON by default)
	 *	- 6. Click Up - Click Down - Click Left - Click Right 
	 *	- 7. Click Zoom In - Click Zoom Out
	 *	- 8. Click on Return
	 *	- 9. Turn OFF Position
	 *	- 10. Click on Curtain view button - (Position OFF now) - 
	 *	- 11. Click Up - Click Down - Click Left - Click Right
	 *	- 12. Click Zoom In - Click Zoom Out
	 * Results:
	 *	- 1. Red color cursor will move along with car position and blue spikes are displayed 
	 *	- 2. View will be tilted/rotated as instructed
	 *	- 3. User will be zoomed in or out as instructed 
	 *	- 4. User will be returned to Driver View page
	 *	- 5. User will be navigated to Curtain View again
	 *	- 6. Map will move up, down, left, right as instructed instead of tilting/rotating
	 *	- 7. User will be zoomed in or out as instructed
	 */
	@Ignore /*TBD*/
	public void TC1227_StartDrivingSurvey_CurtainView() throws Exception {
		Log.info("\nRunning TC1227_StartDrivingSurvey_CurtainView");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}

	/* * Test Case ID: TC2078_FleetMapLinkNotPresentWithoutLicense
	 * Script:
	 * - Log in as utility admin or supervisor
	 * - On home page, click on Fleet map
	 * - Log in as Picarro admin
	 * - Navigate to Manage Customer page and click on Edit button of above customer (Eg, CNP)
	 * - Disable "Fleet Map" privilege
	 * - Log in as utility admin or supervisor
	 * - Provide Fleet map URL directly
	 * Results:
	 *- Fleet map link is present 
	 * - Fleet Map page showing locations of customer's surveyor vehicles. If customer has no vehicles, Fleet Map should show default map
	 * - Fleet Map link is not present
	 * - User is navigated to Home page and not navigated to Fleet Map page
	 */
	@Ignore /*TBD*/
	public void TC2078_FleetMapLinkNotPresentWithoutLicense() throws Exception {
		Log.info("\nRunning TC2078_FleetMapLinkNotPresentWithoutLicense");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
	}

	/* * Test Case ID: TC2079_UserNotAbleToNavigateToObserverViewOnFleetMapScreenWithoutLicense
	 * Script:
	 * - Log in as utility admin or supervisor
	 * - On home page, click on Fleet map
	 * - Log in as Picarro admin
	 * - Navigate to Manage Customer page and click on Edit button of above customer (Eg, CNP)
	 * - Disable "Fleet Map" privilege
	 * - Log in as utility admin or supervisor
	 * - Provide Fleet map URL directly
	 * Results:
	 *- Fleet map link is present 
	 * - Fleet Map page showing locations of customer's surveyor vehicles. If customer has no vehicles, Fleet Map should show default map
	 * - Fleet Map link is not present
	 * - User is navigated to Home page and not navigated to Fleet Map page
	 */
	@Ignore /*TBD*/
	public void TC2079_UserNotAbleToNavigateToObserverViewOnFleetMapScreenWithoutLicense() throws Exception {
		Log.info("\nRunning TC2079_UserNotAbleToNavigateToObserverViewOnFleetMapScreenWithoutLicense");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
	}

	/* * Test Case ID: TC2080_ObserverLinknotPresentWithoutLicense
	 * Script:
	 * - Log in as utility admin or supervisor
	 * - On home page, Surveyor section present on dashboard
	 * - Click on Online link present next to online Surveyor in Active Surveyors section
	 * - Log in as Picarro admin
	 * - Navigate to Manage Customer page and click on Edit button of above customer (Eg, CNP)
	 * - Disable "Observer View" privilege
	 * - Log in as utility admin or supervisor
	 * - On home page, Surveyor section present on dashboard
	 * Results:
	 * - Online link will be available for all online surveyors only. Offline surveyor will not have any clickable link
	 * - User is navigated to Observer view
	 * - Online status is present but its not clickable
	 */
	@Ignore /*TBD*/
	public void TC2080_ObserverLinknotPresentWithoutLicense() throws Exception {
		Log.info("\nRunning TC2080_ObserverLinknotPresentWithoutLicense");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
	}

	/* * Test Case ID: TC2098_DriverCanSelectOperatorRROrmanulModesWithLicense
	 * Script:
	 * - Log into the UI as Picarro Admin and navigate to the Manage Customers page
	 * - Select a customer and click the "Edit" button
	 * - Check the Rapid Response checkbox and click OK
	 * - Log into the tablet as a customer driver role user
	 * - Click on the mode button
	 * - Click on the Start Survey button
	 * - Fill out the details and select Rapid Response as the survey type
	 * - Repeat with Operator mode
	 * - Repeat with Manual mode, but user must be either Picarro or customer admin user
	 * Results:
	 * - User will see a list of Customers
	 * - User will see a list of features enabled/disabled for customer
	 * - Driver user will be taken to Driver View
	 * - Mode button will bring up menu with two or three selections, including "Start Survey"
	 * - Popup will appear where user can add tag, select environmental conditions and survey mode. Rapid Response will be among the selections
	 * - Survey will start
	 */
	@Ignore /*TBD*/
	public void TC2098_DriverCanSelectOperatorRROrmanulModesWithLicense() throws Exception {
		Log.info("\nRunning TC2098_DriverCanSelectOperatorRROrmanulModesWithLicense");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
	}

	/* * Test Case ID: TC2099_DriverCanNotSelectOperatorRROrmanulModesWithoutLicense
	 * Script:
	 * - Log into the UI as Picarro Admin and navigate to the Manage Customers page
	 * - Confirm that customer does not have Rapid Response, Operator, or Manual modes enabled
	 * - Log into the tablet as a customer admin user
	 * - Click on the mode button
	 * - Click on the Start Survey button
	 * Results:
	 * - User will see a list of Customers
	 * - User will see Rapid Response, Operator and Manual survey modes unchecked
	 * - Mode button will bring up two or three buttons, including "Start Survey"
	 * - Popup will appear where user can select environmental conditions and survey modes. Rapid Response, Operator and Manual will not be among the selections. (Standard will always be available, and depending on the customer's tier, Assessment may also be available)
	 */
	@Ignore /*TBD*/
	public void TC2099_DriverCanNotSelectOperatorRROrmanulModesWithoutLicense() throws Exception {
		Log.info("\nRunning TC2099_DriverCanNotSelectOperatorRROrmanulModesWithoutLicense");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
	}

	/* * Test Case ID: TC2132_CurtainViewNotAvailableWithoutLicense
	 * Script:
	 * - Log into Driver View
	 * - Click Mode -> Start Survey
	 * - Fill out survey details and click "Start Survey"
	 * - Check bottom panel for Curtain View button
	 * Results:
	 * - Curtain View button should not be present
	 */
	@Ignore /*TBD*/
	public void TC2132_CurtainViewNotAvailableWithoutLicense() throws Exception {
		Log.info("\nRunning TC2132_CurtainViewNotAvailableWithoutLicense");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
	}

	/* * Test Case ID: TC2134_CurtainViewNotAvailableWithoutLicense
	 * Script:
	 * - Log into Driver View
	 * - Click on Driving Surveys link at left
	 * - Select a survey
	 * - Check bottom panel for Curtain View button
	 * Results:
	 * - User is navigated to selected survey
	 * - Curtain View button should not be present
	 */
	@Ignore /*TBD*/
	public void TC2134_CurtainViewNotAvailableWithoutLicense() throws Exception {
		Log.info("\nRunning TC2134_CurtainViewNotAvailableWithoutLicense");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
	}
}
