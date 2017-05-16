package surveyor.regression.source;

import org.junit.Test;
import common.source.Log;
import surveyor.scommon.entities.FeatureInfoEntity;
import surveyor.scommon.source.BaseMapViewPage.FeatureInfo;

import static org.junit.Assert.*;
import java.io.IOException;

/**
 * This class contains survey view Analytics tests.
 * @author spulikkal
 *
 */
public class SurveyViewPageTest_EQ extends BaseSurveyViewPageTest {

	public SurveyViewPageTest_EQ() throws IOException {
		super();
	}

	/**
	 *	Test Case ID: TC1070_SurveyViewInSatelliteView_PicarroSupport
	 *	Test Case Description:  Survey View as Picarro support - View EQ Survey in satellite view when GIS and display options are ON
	 *	SCRIPT:
	 *	-  On Home Page, login as Picarro support role and click on Driving Surveys -> View Survey (EQ Survey)
	 *	- Click on Map and turn Satellite View ON
	 *	- Click on Display button
	 *		All options ON
	 *	- Click on GIS
	 *		All options ON
	 *	RESULT:
	 *	-  Survey Information is displayed in satellite view - Tag, EQ Mode (bold green text), Stability Class, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- The "EQ Mode Active" message in bold green font should not obscure any part of the survey details nor the map
	 *	- User  should see Breadcrumb, FOV, Indications and LISA
	 *	- All pipes and boundaries data are displayed
	 */
	@Test
	public void TC1070_SurveyViewInSatelliteView_PicarroSupport() throws Exception {
		Log.info("\nRunning TC1070_SurveyViewInSatelliteView_PicarroSupport ...");
	}

	/**
	 *	Test Case ID: TC1077_SurveyViewInMapView_CustomerDriver
	 *	Test Case Description:   	Survey View Customer Driver role- View EQ Survey in map view when GIS and display options are ON
	 *	SCRIPT:
	 *	- On Home Page by login as Customer Driver and  click on Driving Surveys -> View Survey (EQ Survey)
	 *	- Click on Map and turn Map View ON
	 *	- Click on Display button
	 *		All options ON
	 *	- Click on GIS
	 *		All options ON
	 *	Indication - ON
	 *	All other options - OFF
	 *	RESULT:
	 *	- Survey Information is displayed in map view - Tag, EQ Mode (bold green text), Stability Class, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- The "EQ Mode Active" message in bold green font should be visible at top left of the map
	 *	- Display should show only FOV option.
	 *	- User  should see Breadcrumb, FOV.
	 *	- User should not see LISA or Indications on the survey view.
	 *	- All pipes and boundaries data are displayed
	 */
	@Test
	public void TC1077_SurveyViewInMapView_CustomerDriver() throws Exception {
		Log.info("\nRunning TC1077_SurveyViewInMapView_CustomerDriver ...");
	}
}