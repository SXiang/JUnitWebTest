package surveyor.dataprovider;

import static surveyor.scommon.source.SurveyorConstants.CUSDRVSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.EXCLUSIONRADIUS;
import static surveyor.scommon.source.SurveyorConstants.PICADMMANTAG;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDRTAG;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSU;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPTUA;
import static surveyor.scommon.source.SurveyorConstants.SQAPICSUP;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSURVEYOR;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONECTUA;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEETUA;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEMTUA;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORDHASH;

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

public class ComplianceReportDataProvider extends ReportDataProvider {
	public static final String COMPLIANCE_REPORT_PROVIDER = "dataProviderComplianceReport";

	public ComplianceReportDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
	}

	/**********************************************************************
	 * #NOTE#: Password provided in the data provider will get printed in teamcity UI (run result) and therefore needs to be an encrypted string Use the CryptoUtility.encrypt() method to encrypt the
	 * password
	 * @throws Exception 
	 **********************************************************************/

	@DataProvider
	public static Object[][] dataProviderComplianceReport() throws Exception {

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList1.add(createViewsMapTable("Second View", "1", "1", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList1.add(createViewsMapTable("Third View", "0", "0", "1", "1", "1", "1", "0", "0", "0", "None"));
		List<Map<String, String>> tablesList1 = new ArrayList<Map<String, String>>();
		tablesList1.add(createOptionalTabularPDFContent("1", "1", "1", "1"));
		List<Integer> assetRowIDs1 = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs1 = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList1 = new ArrayList<Map<String, String>>();
		viewLayerList1.add(createOptionalViewLayersContent(assetRowIDs1, boundaryRowIDs1));
		List<String> tagList1 = new ArrayList<String>();
		tagList1.add(SQACUSDRTAG);

		List<Map<String, String>> viewList2 = new ArrayList<Map<String, String>>();
		viewList2.add(createViewsMapTable("First View", "0", "0", "0", "1", "0", "1", "0", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList2.add(createViewsMapTable("Second View", "1", "1", "0", "1", "1", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList2 = new ArrayList<Map<String, String>>();
		tablesList2.add(createOptionalTabularPDFContent("1", "1", "0", "0"));
		List<Integer> assetRowIDs2 = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList2 = new ArrayList<Map<String, String>>();
		viewLayerList2.add(createViewLayerAssetsContent(assetRowIDs2));
		List<String> tagList2 = new ArrayList<String>();
		tagList2.add(PICADMNSTDTAG);

		List<Map<String, String>> viewList3 = new ArrayList<Map<String, String>>();
		viewList3.add(createViewsMapTable("First View", "0", "0", "0", "1", "0", "1", "0", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList3 = new ArrayList<Map<String, String>>();
		tablesList3.add(createOptionalTabularPDFContent("1", "1", "0", "0"));
		List<Integer> assetRowIDs3 = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList3 = new ArrayList<Map<String, String>>();
		viewLayerList3.add(createViewLayerAssetsContent(assetRowIDs3));
		List<String> tagList3 = new ArrayList<String>();
		tagList3.add(PICADMNSTDTAG);

		List<Map<String, String>> viewList4 = new ArrayList<Map<String, String>>();
		viewList4.add(createViewsMapTable("First View", "0", "0", "0", "1", "1", "1", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList4 = new ArrayList<Map<String, String>>();
		tablesList4.add(createOptionalTabularPDFContent("1", "1", "0", "0"));
		List<Map<String, String>> viewLayerList4 = null;
		List<String> tagList4 = new ArrayList<String>();
		tagList4.add(PICADMNSTDTAG);
		
		List<Map<String, String>> viewList5 = new ArrayList<Map<String, String>>();
		viewList5.add(createViewsMapTable("First View", "0", "0", "0", "1", "1", "1", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList5.add(createViewsMapTable("Second View", "1", "1", "0", "1", "1", "0", "0", "1", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList5 = new ArrayList<Map<String, String>>();
		tablesList5.add(createOptionalTabularPDFContent("1", "1", "0", "0"));
		List<Integer> assetRowIDs5 = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList5 = new ArrayList<Map<String, String>>();
		viewLayerList5.add(createViewLayerAssetsContent(assetRowIDs5));
		List<String> tagList5 = new ArrayList<String>();
		tagList5.add(PICADMNSTDTAG);
		
		List<Map<String, String>> viewList6 = new ArrayList<Map<String, String>>();
		viewList6.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList6 = new ArrayList<Map<String, String>>();
		tablesList6.add(createOptionalTabularPDFContent("0", "0", "1", "0"));
		List<Integer> assetRowIDs6 = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList6 = new ArrayList<Map<String, String>>();
		viewLayerList6.add(createViewLayerAssetsContent(assetRowIDs6));
		List<String> tagList6 = new ArrayList<String>();
		tagList6.add(PICADMNSTDTAG);
		
		List<Map<String, String>> viewList7 = new ArrayList<Map<String, String>>();
		viewList7.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList7 = new ArrayList<Map<String, String>>();
		tablesList7.add(createOptionalTabularPDFContent("1", "1", "0", "0"));
		List<Integer> assetRowIDs7 = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList7 = new ArrayList<Map<String, String>>();
		viewLayerList7.add(createViewLayerAssetsContent(assetRowIDs7));
		List<String> tagList7 = new ArrayList<String>();
		tagList7.add(PICADMNSTDTAG);
		
		List<Map<String, String>> viewList8 = new ArrayList<Map<String, String>>();
		viewList8.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList8 = new ArrayList<Map<String, String>>();
		tablesList8.add(createOptionalTabularPDFContent("1", "1", "0", "0"));
		List<Integer> assetRowIDs8 = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList8 = new ArrayList<Map<String, String>>();
		viewLayerList8.add(createViewLayerAssetsContent(assetRowIDs8));
		List<String> tagList8 = new ArrayList<String>();
		tagList8.add(PICADMNSTDTAG);
		
		List<Map<String, String>> viewList9 = new ArrayList<Map<String, String>>();
		viewList9.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList9 = new ArrayList<Map<String, String>>();
		tablesList9.add(createOptionalTabularPDFContent("0", "0", "0", "0"));
		List<Integer> assetRowIDs9 = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList9 = new ArrayList<Map<String, String>>();
		viewLayerList9.add(createViewLayerAssetsContent(assetRowIDs9));
		List<String> tagList9 = new ArrayList<String>();
		tagList9.add(PICADMNSTDTAG);
		
		List<Map<String, String>> viewList10 = new ArrayList<Map<String, String>>();
		viewList10.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList10 = new ArrayList<Map<String, String>>();
		tablesList10.add(createOptionalTabularPDFContent("1", "1", "0", "0"));
		List<Integer> assetRowIDs10 = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList10 = new ArrayList<Map<String, String>>();
		viewLayerList10.add(createViewLayerAssetsContent(assetRowIDs10));
		List<String> tagList10 = new ArrayList<String>();
		tagList10.add(PICADMMANTAG);
		
		List<Map<String, String>> viewList11 = new ArrayList<Map<String, String>>();
		viewList11.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList11 = new ArrayList<Map<String, String>>();
		tablesList11.add(createOptionalTabularPDFContent("1", "1", "0", "0"));
		List<Integer> assetRowIDs11 = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList11 = new ArrayList<Map<String, String>>();
		viewLayerList11.add(createViewLayerAssetsContent(assetRowIDs11));
		List<String> tagList11 = new ArrayList<String>();
		tagList11.add(CUSDRVSTDTAG);
		
		List<Map<String, String>> viewList12 = new ArrayList<Map<String, String>>();
		viewList12.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList12.add(createViewsMapTable("Second View", "0", "0", "1", "1", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList12.add(createViewsMapTable("Third View", "1", "1", "0", "0", "1", "0", "0", "0", "0", "None"));
		viewList12.add(createViewsMapTable("Fourth View", "0", "1", "0", "0", "0", "0", "1", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList12 = new ArrayList<Map<String, String>>();
		tablesList12.add(createOptionalTabularPDFContent("1", "1", "0", "0"));
		List<Map<String, String>> viewLayerList12 = null;
		List<String> tagList12 = new ArrayList<String>();
		tagList12.add(CUSDRVSTDTAG);
		
		List<Map<String, String>> viewList13 = new ArrayList<Map<String, String>>();
		viewList13.add(createViewsMapTable("First View", "1", "1", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList13.add(createViewsMapTable("Second View", "0", "0", "1", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList13 = new ArrayList<Map<String, String>>();
		tablesList13.add(createOptionalTabularPDFContent("1", "0", "0", "0"));
		List<Map<String, String>> viewLayerList13 = null;
		List<String> tagList13 = new ArrayList<String>();
		tagList13.add(CUSDRVSTDTAG);
		
		List<Map<String, String>> viewList14 = new ArrayList<Map<String, String>>();
		viewList14.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList14.add(createViewsMapTable("Second View", "1", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList14.add(createViewsMapTable("Third View", "1", "1", "1", "1", "1", "1", "1", "1", "1", "None"));
		List<Map<String, String>> tablesList14 = new ArrayList<Map<String, String>>();
		tablesList14.add(createOptionalTabularPDFContent("1", "1", "1", "1"));
		List<Integer> assetRowIDs14 = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs14 = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList14 = new ArrayList<Map<String, String>>();
		viewLayerList14.add(createOptionalViewLayersContent(assetRowIDs14, boundaryRowIDs14));
		List<String> tagList14 = new ArrayList<String>();
		tagList14.add(SQACUSDRTAG);
		
		List<Map<String, String>> viewList15 = new ArrayList<Map<String, String>>();
		viewList15.add(createViewsMapTable("First View", "1", "1", "0", "1", "0", "0", "0", "1", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList15.add(createViewsMapTable("Second View", "0", "0", "1", "1", "0", "0", "0", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList15.add(createViewsMapTable("Third View", "0", "1", "0", "0", "0", "0", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList15 = new ArrayList<Map<String, String>>();
		tablesList15.add(createOptionalTabularPDFContent("1", "1", "1", "1"));
		List<Integer> assetRowIDs15 = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList15 = new ArrayList<Map<String, String>>();
		viewLayerList15.add(createViewLayerAssetsContent(assetRowIDs15));
		List<String> tagList15 = new ArrayList<String>();
		tagList15.add(SQACUSDRTAG);
		
		List<Map<String, String>> viewList16 = new ArrayList<Map<String, String>>();
		viewList16.add(createViewsMapTable("First View", "1", "1", "0", "1", "1", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList16.add(createViewsMapTable("Second View", "1", "1", "0", "1", "1", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList16 = new ArrayList<Map<String, String>>();
		tablesList16.add(createOptionalTabularPDFContent("1", "1", "0", "0"));
		List<Map<String, String>> viewLayerList16 = null;
		List<String> tagList16 = new ArrayList<String>();
		tagList16.add(CUSDRVSTDTAG);
		
		List<Map<String, String>> viewList17 = new ArrayList<Map<String, String>>();
		viewList17.add(createViewsMapTable("First View", "1", "1", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList17 = new ArrayList<Map<String, String>>();
		tablesList17.add(createOptionalTabularPDFContent("1", "0", "0", "0"));
		List<Map<String, String>> viewLayerList17 = null;
		List<String> tagList17 = new ArrayList<String>();
		tagList17.add(CUSDRVSTDTAG); // include 8 hr survey
		
		List<Map<String, String>> viewList18 = new ArrayList<Map<String, String>>();
		viewList18.add(createViewsMapTable("First View", "0", "0", "0", "0", "1", "0", "0", "1", "1", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList18.add(createViewsMapTable("Second View", "1", "1", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList18.add(createViewsMapTable("Third View", "0", "0", "1", "1", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList18 = new ArrayList<Map<String, String>>();
		tablesList18.add(createOptionalTabularPDFContent("1", "1", "0", "0"));
		List<Integer> assetRowIDs18 = Arrays.asList(8, 9);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs18 = Arrays.asList(3);	  // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList18 = new ArrayList<Map<String, String>>();
		viewLayerList18.add(createOptionalViewLayersContent(assetRowIDs18, boundaryRowIDs18));
		List<String> tagList18 = new ArrayList<String>();
		tagList18.add(SQACUSDRTAG);
		
		List<Map<String, String>> viewList19 = new ArrayList<Map<String, String>>();
		viewList19.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList19.add(createViewsMapTable("Second View", "1", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList19.add(createViewsMapTable("Third View", "1", "1", "1", "1", "1", "1", "1", "1", "1", "None"));
		viewList19.add(createViewsMapTable("Fourth View", "1", "1", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList19.add(createViewsMapTable("Fifth View", "1", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList19.add(createViewsMapTable("Sixsth View", "1", "1", "0", "0", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList19.add(createViewsMapTable("Seventh View", "0", "0", "1", "1", "1", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList19 = new ArrayList<Map<String, String>>();
		tablesList19.add(createOptionalTabularPDFContent("1", "1", "0", "0"));
		List<Integer> assetRowIDs19 = Arrays.asList(8, 9);  	// Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs19 = Arrays.asList(3); 		// Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList19 = new ArrayList<Map<String, String>>();
		viewLayerList19.add(createOptionalViewLayersContent(assetRowIDs19, boundaryRowIDs19));
		List<String> tagList19 = new ArrayList<String>();
		tagList19.add(SQACUSDRTAG);
		
		List<Map<String, String>> viewList20 = new ArrayList<Map<String, String>>();
		viewList20.add(createViewsMapTable("First View", "1", "0", "0", "1", "1", "0", "0", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		List<Map<String, String>> tablesList20 = new ArrayList<Map<String, String>>();
		tablesList20.add(createOptionalTabularPDFContent("1", "1", "0", "0"));
		List<Integer> assetRowIDs20 = Arrays.asList(8, 9);    // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList20 = new ArrayList<Map<String, String>>();
		viewLayerList20.add(createViewLayerAssetsContent(assetRowIDs20));
		List<String> tagList20 = new ArrayList<String>();
		tagList20.add(SQACUSDRTAG); //include a survey with no LISAS
		
		List<Map<String, String>> viewList21 = new ArrayList<Map<String, String>>();
		viewList21.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList21.add(createViewsMapTable("Second View", "0", "0", "1", "1", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList21.add(createViewsMapTable("Third View", "1", "1", "0", "0", "1", "0", "0", "0", "0", "None"));		
		List<Map<String, String>> tablesList21 = new ArrayList<Map<String, String>>();
		tablesList21.add(createOptionalTabularPDFContent("1", "0", "0", "0"));
		List<Integer> assetRowIDs21 = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs21 = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList21 = new ArrayList<Map<String, String>>();
		viewLayerList21.add(createOptionalViewLayersContent(assetRowIDs21, boundaryRowIDs21));
		List<String> tagList21 = new ArrayList<String>();
		tagList21.add(CUSDRVSTDTAG); 
		
		List<Map<String, String>> viewList22 = new ArrayList<Map<String, String>>();
		viewList22.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList22.add(createViewsMapTable("Second View", "1", "0", "1", "1", "0", "0", "1", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList22.add(createViewsMapTable("Third View", "1", "1", "0", "1", "1", "0", "1", "0", "0", "None"));		
		List<Map<String, String>> tablesList22 = new ArrayList<Map<String, String>>();
		tablesList22.add(createOptionalTabularPDFContent("1", "0", "0", "0"));
		List<Integer> assetRowIDs22 = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs22 = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList22 = new ArrayList<Map<String, String>>();
		viewLayerList22.add(createOptionalViewLayersContent(assetRowIDs22, boundaryRowIDs22));
		List<String> tagList22 = new ArrayList<String>();
		tagList22.add(CUSDRVSTDTAG); 
		
		List<Map<String, String>> viewList23 = new ArrayList<Map<String, String>>();
		viewList23.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		List<Map<String, String>> tablesList23 = new ArrayList<Map<String, String>>();
		tablesList23.add(createOptionalTabularPDFContent("1", "1", "0", "0"));
		List<Map<String, String>> viewLayerList23 = null;
		List<String> tagList23 = new ArrayList<String>();
		tagList23.add(CUSDRVSTDTAG); 

		return new Object[][] {				
				{ "1"/*index*/, SQAPICSUP/*strCreatedBy*/, USERPASSWORDHASH/*password(encrypted)*/, "Picarro"/*customer*/, TIMEZONEPTUA/*timeZone*/, EXCLUSIONRADIUS/*exclusionRadius*/, null/*surveyorUnit*/, null/*userName*/, null/*startDate*/,
					null/*endDate*/, null/*fovOpacity*/, null/*lisaOpacity*/, null/*geoFilter*/, null/*reportMode*/, null/*surveyModeFilter*/, null/*ethaneFilter*/, createBoundaryList()/*listBoundary*/, tagList1/*tagList*/, tablesList1/*tablesList*/, viewList1/*viewList*/, viewLayerList1/*viewLayersList*/ },
				{ "2", SQAPICSUP, USERPASSWORDHASH, "Picarro", TIMEZONEPTUA, EXCLUSIONRADIUS, null, null, null, null, null, null, null, null, null, null, createBoundaryList(), tagList2, tablesList2, viewList2, viewLayerList2 }, // include date range
				{ "3", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONECTUA, EXCLUSIONRADIUS, PICADMNSURVEYOR, null, null, null, null, null, null, null, SurveyModeFilter.All, null, createBoundaryList(), tagList3, tablesList3, viewList3, viewLayerList3 },
				{ "4", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEETUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList4, tablesList4, viewList4, viewLayerList4 }, //include dates
				{ "5", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONECTUA, "0", PICADMNSURVEYOR, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList5, tablesList5, viewList5, viewLayerList5 }, //include dates
				{ "6", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEPTUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList6, tablesList6, viewList6, viewLayerList6 },
				{ "7", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEPTUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList7, tablesList7, viewList7, viewLayerList7 },
				{ "8", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEPTUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList8, tablesList8, viewList8, viewLayerList8 },
				{ "9", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEETUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList9, tablesList9, viewList9, viewLayerList9 },
				{ "10", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEMTUA, "0", null, null, null, null, null, null, null, ReportModeFilter.Manual, SurveyModeFilter.Manual, null, createBoundaryList(), tagList10, tablesList10, viewList10, viewLayerList10 },
				{ "11", PICDFADMIN, USERPASSWORDHASH, "sqacus", TIMEZONEMTUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList11, tablesList11, viewList11, viewLayerList11 },
				{ "12", SQAPICSUP, USERPASSWORDHASH, "sqacus", TIMEZONEPTUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList12, tablesList12, viewList12, viewLayerList12 },
				{ "13", SQACUSSU, USERPASSWORDHASH, "sqacus", TIMEZONEPTUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList13, tablesList13, viewList13, viewLayerList13 },
				{ "14", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEPTUA, "0", null, null, null, null, null, null, null, null, null, null, createBoundaryList(), tagList14, tablesList14, viewList14, viewLayerList14 },
				{ "15", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEPTUA, "0", null, null, null, null, null, null, null, null, null, null, createBoundaryList(), tagList15, tablesList15, viewList15, viewLayerList15 },//Include Date filter for 30 days
				{ "16", SQACUSSU, USERPASSWORDHASH, "sqacus", TIMEZONECTUA, "150", null, null, null, null, null, null, true, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList16, tablesList16, viewList16, viewLayerList16 },
				{ "17", SQACUSSU, USERPASSWORDHASH, "sqacus", TIMEZONEETUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList17, tablesList17, viewList17, viewLayerList17 },
				{ "18", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEPTUA, "0", null, null, null, null, null, null, null, null, null, null, createBoundaryList(), tagList18, tablesList18, viewList18, viewLayerList18 },
				{ "19", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEPTUA, "0", null, null, null, null, null, null, null, null, null, null, createBoundaryList(), tagList19, tablesList19, viewList19, viewLayerList19 },
				{ "20", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEMTUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList20, tablesList20, viewList20, viewLayerList20 }, //include dates
				{ "21", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEPTUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList21, tablesList21, viewList21, viewLayerList21 } ,
				{"22", PICDFADMIN, USERPASSWORDHASH, "Picarro", TIMEZONEPTUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList22, tablesList22, viewList22, viewLayerList22 },
				{"23", SQACUSSU, USERPASSWORDHASH, "sqacus", TIMEZONEPTUA, "0", null, null, null, null, null, null, null, null, SurveyModeFilter.Standard, null, createBoundaryList(), tagList23, tablesList23, viewList23, viewLayerList23 }
		};
	}
}