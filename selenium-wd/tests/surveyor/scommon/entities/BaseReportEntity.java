/**
 *
 */
package surveyor.scommon.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BaseReportEntity {

	/**
	 *
	 */
	static {
		populateGuidMaps();
		populateComplexBoundaryNames();
	}

	public static Map<String, SurveyModeFilter> SurveyModeFilterGuids;
	public static Map<String, ReportModeFilter> ReportSurveyModeFilterGuids;
	public static Map<String, ReportType> ReportTypeGuids;
	public static Map<String, ReportJobType> ReportJobTypeGuids;
	public static Map<ReportJobType, String> ReportJobTypeReverseGuids;

	public static List<String> ComplexBoundaryNames;

	private String rptTitle;
	protected String strCreatedBy;
	private String customer;
	protected String timeZone;

	private List<ReportsSurveyInfo> surveyInfoList;

	// TODO: Deprecate these data members. Replaced with List<ReportsSurveyInfo>
	protected String surveyorUnit;
	protected List<String> tagList;
	protected String startDate;
	protected String endDate;
	protected String userName;
	protected Boolean geoFilter = false;

	public enum SurveyModeFilter {
		All ("All"),
		Standard ("Standard"),
		Operator ("Operator"),
		RapidResponse ("Rapid Response"),
		Manual ("Manual"),
		EQ ("EQ"),
		Assessment ("Assessment"),
		Analytics ("Analytics");

		private final String name;

		SurveyModeFilter(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public enum ReportModeFilter {
		All ("All"),
		Standard ("Standard"),
		Operator ("Operator"),
		RapidResponse ("Rapid Response"),
		Manual ("Manual"),
		EQ ("EQ"),
		Assessment ("Assessment"),
		Analytics ("Analytics");

		private final String name;
		ReportModeFilter(String nm) {
			name = nm;
		}
		public String toString() {
			return this.name;
		}
	}

	public enum ReportType {
		FACILITYEQ ("FacilityEQ"),
		ASSESSMENT ("Assessment"),
		EQ ("EQ"),
		INVESTIGATION ("Investigation"),
		COMPLIANCE ("Compliance");

		private final String name;

		ReportType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public enum ReportJobType {
		Map ("Map"),
		SSRS ("SSRS"),
		DataGeneration ("DataGeneration"),
		EQMap ("EQMap"),
		EQSSRS ("EQSSRS"),
		EQDataGeneration ("EQDataGeneration"),
		ShapeFile ("ShapeFile"),
		ReportMeta ("ReportMeta"),
		PercentCoverageForecast ("PercentCoverageForecast"),
		Zip ("Zip"),
		AssetBoxHighlight ("AssetBoxHighlight"),
		FacilityEQDataGeneration ("FacilityEQDataGeneration"),
		FacilityEQMap ("FacilityEQMap"),
		FacilityEQConcentrationChart ("FacilityEQConcentrationChart"),
		FacilityEQReportMeta ("FacilityEQReportMeta"),
		FTP ("FTP"),
		AnalyticsRanking ("AnalyticsRanking"),
		LISAAssetHighlight ("LISAAssetHighlight");

		private final String name;

		ReportJobType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}


	public enum ReportStatusType {
		Pending ("Pending"),
		Processing ("Processing"),
		Complete ("Complete"),
		Failed ("Failed"),
		Zipping ("Zipping");

		private final String name;

		ReportStatusType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
 	}

	public enum ReportJobStatusType {
		Pending ("Pending"),
		Processing ("Processing"),
		Complete ("Complete"),
		Failed ("Failed");

		private final String name;

		ReportJobStatusType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
 	}

	public enum SSRSPdfFooterColumns {
		SoftwareVersion ("SoftwareVersion"),
		ReportUser ("ReportUser"),
		ReportDate ("ReportDate");

		private final String name;

		SSRSPdfFooterColumns(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public enum SearchAreaPreference {
		LISAS ("LISAs"),
		ASSETBOXES ("Asset Boxes");

		private final String name;

		SearchAreaPreference(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public BaseReportEntity(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit, List<String> tagList) {
		this(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, null, null, null, null, tagList);
	}

	public BaseReportEntity(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit, List<String> tagList, String startDate, String endDate) {
		this(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, null, startDate, endDate, null, tagList);
	}

	public BaseReportEntity(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit, String userName, String startDate, String endDate, boolean geoFilter) {
		this(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, userName, startDate, endDate, geoFilter, null);
	}

	public BaseReportEntity(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit, String startDate, String endDate, boolean geoFilter) {
		this(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, null, startDate, endDate, geoFilter, null);
	}

	public BaseReportEntity(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit, List<String> tagList, String startDate, String endDate, boolean geoFilter) {
		this(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, null, startDate, endDate, geoFilter, tagList);
	}

	public BaseReportEntity(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit,String userName,  String startDate, String endDate,  List<String> tagList) {
		this(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, userName, startDate, endDate, null, tagList);
	}

	public BaseReportEntity(){
	}

	public BaseReportEntity(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit, String userName, String startDate, String endDate, Boolean geoFilter, List<String> tagList){
		this.rptTitle = rptTitle;
		this.strCreatedBy = strCreatedBy;
		this.setCustomer(customer);
		this.timeZone = timeZone;
		this.geoFilter = geoFilter;

		this.surveyorUnit = surveyorUnit;
		this.userName = userName;
		this.tagList = tagList;

		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getRptTitle() {
		return this.rptTitle;
	}

	public String getCustomer() {
		return this.customer;
	}

	public String getTimeZone() {
		return this.timeZone;
	}

	public String getSurveyorUnit() {
		return this.surveyorUnit;
	}

	public String getSurveyStartDate() {
		return this.startDate;
	}

	public String getSurveyEndDate() {
		return this.endDate;
	}

	public List<String> getTagList() {
		return this.tagList;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getStrCreatedBy() {
		return this.strCreatedBy;
	}

	public String getUsername() {
		return this.userName;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public Boolean getGeoFilter() {
		return geoFilter;
	}

	public void setRptTitle(String rptTitle) {
		this.rptTitle = rptTitle;
	}

	public void setStrCreatedBy(String strCreatedBy) {
		this.strCreatedBy = strCreatedBy;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	// TODO --> Deprecate these. To be replaced by List<ReportsSurveyInfo>

	public void setSurveyorUnit(String surveyorUnit) {
		this.surveyorUnit = surveyorUnit;
	}

	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setGeoFilter(Boolean geoFilter) {
		this.geoFilter = geoFilter;
	}

	public List<ReportsSurveyInfo> getSurveyInfoList() {
		return surveyInfoList;
	}

	public void setSurveyInfoList(List<ReportsSurveyInfo> surveyInfoList) {
		this.surveyInfoList = surveyInfoList;
	}

	private static void populateComplexBoundaryNames() {
		ComplexBoundaryNames = Collections.synchronizedList(new ArrayList<String>());
		ComplexBoundaryNames.add("Leak Survey Area|BEAUMONT HP|96e5987c-a415-4ea8-8776-152b5fb35720");
		ComplexBoundaryNames.add("Leak Survey Area|HP/ LD SURVEY 1|b5de8d18-2593-4c23-b5f1-bd5b0fa2df2a");
		ComplexBoundaryNames.add("Leak Survey Area|HP/ LD SURVEY 10|d7dbec72-94a9-40db-8ff8-4fe486e02c35");
		ComplexBoundaryNames.add("Leak Survey Area|HP/ LD SURVEY 11|78823769-abd5-4150-af5b-e3ba9c66c3bb");
		ComplexBoundaryNames.add("Leak Survey Area|HP/ LD SURVEY 12|b2734a6c-6bf4-4d3b-a9fd-9409cd4e10f1");
		ComplexBoundaryNames.add("Leak Survey Area|HP/ LD SURVEY 13|a77ad1ed-7455-4e51-9f9d-d5a99d857948");
		ComplexBoundaryNames.add("Leak Survey Area|HP/ LD SURVEY 14|f339fa5e-0322-4287-988f-80b9921b8ba4");
		ComplexBoundaryNames.add("Leak Survey Area|HP/ LD SURVEY 15|806bdaf5-4626-4e5d-ab74-6f28b1a1e4b2");
		ComplexBoundaryNames.add("Leak Survey Area|HP/ LD SURVEY 16|a4d271f4-8934-4dfc-a01a-6cad85097417");
		ComplexBoundaryNames.add("Leak Survey Area|HP/ LD SURVEY 17|a3cef2c7-4880-4a64-ad13-049fdd174d20");
		ComplexBoundaryNames.add("Leak Survey Area|HP/ LD SURVEY 2a|ba3e4fd5-10ae-4672-b553-187b6d41ba42");
		ComplexBoundaryNames.add("Leak Survey Area|HP/ LD SURVEY 3|a64e6856-a297-49ee-868b-cf59c00ce9a7");
		ComplexBoundaryNames.add("Leak Survey Area|HP/ LD SURVEY 4|fead3f2b-c571-443b-a829-4a3016311b0f");
		ComplexBoundaryNames.add("Leak Survey Area|HP/ LD SURVEY 5|8c252368-04d1-4664-8398-e02e757cd7e5");
		ComplexBoundaryNames.add("Leak Survey Area|HP/ LD SURVEY 6|975bc2aa-8d12-4ae6-a7bf-747913189309");
		ComplexBoundaryNames.add("Leak Survey Area|HP/ LD SURVEY 7|841c7bc0-db2a-4f99-b7b2-322f7b566ad0");
		ComplexBoundaryNames.add("Leak Survey Area|HP/ LD SURVEY 8|a131516f-600a-438c-96d9-ca508cca610f");
		ComplexBoundaryNames.add("Leak Survey Area|HP/ LD SURVEY 9|0b2d4ec5-0539-4651-bbf4-5b0a31c9b884");
		ComplexBoundaryNames.add("Leak Survey Area|LAREDO HP|42305b57-8e08-44df-be60-b315a25d0b92");
		ComplexBoundaryNames.add("Leak Survey Area|O30720800|21134dc5-510b-46c4-a82a-5f08524304d3");
		ComplexBoundaryNames.add("Leak Survey Area|O30840704|168287b8-1a7e-4cab-bcd1-3a9abcf61392");
		ComplexBoundaryNames.add("Leak Survey Area|O30840728|65f3df89-7088-4dc3-959f-1257cc09daee");
		ComplexBoundaryNames.add("Leak Survey Area|O30840752|491eb8bd-86ee-4ec3-859c-9df66eb22fec");
		ComplexBoundaryNames.add("Leak Survey Area|O30960704|01ad0bd5-cd9c-4c08-abaf-d3069232a622");
		ComplexBoundaryNames.add("Leak Survey Area|O31080752|209dd281-12a4-4f89-bb99-d19e0256a223");
		ComplexBoundaryNames.add("Leak Survey Area|O31200688|dbdde5aa-af9b-46ff-8666-2715c69224c3");
		ComplexBoundaryNames.add("Leak Survey Area|O31200704|f5f9b8b9-91d1-4eea-bbca-f143991b7d8c");
		ComplexBoundaryNames.add("Leak Survey Area|O31320696|7dacfaa7-d6f4-4d2a-b18b-92094b2e2e03");
		ComplexBoundaryNames.add("Leak Survey Area|O31320720|2a98332d-474f-4bf1-a97f-8e71da5aa213");
		ComplexBoundaryNames.add("Leak Survey Area|O31320728|0be7806a-d833-45c0-a0c7-ffebba66bae3");
		ComplexBoundaryNames.add("Leak Survey Area|O31320736|38a8263a-d9bf-42fb-ad69-17b190f8f7ca");
		ComplexBoundaryNames.add("Leak Survey Area|O31440688|18d4b312-9057-4935-b9ca-005c4dd7a464");
		ComplexBoundaryNames.add("Leak Survey Area|O31440696|323386ad-72bf-4dc2-bdab-2df90a99e6ac");
		ComplexBoundaryNames.add("Leak Survey Area|O31440704|5a6c7d73-94a1-4972-a402-3fdb64988c7a");
		ComplexBoundaryNames.add("Leak Survey Area|O31440712|3080d44e-665d-4f35-a455-8262d1d0dbb8");
		ComplexBoundaryNames.add("Leak Survey Area|O31440728|fbd2cc7b-a3d9-4c78-82bf-19b3de82e641");
		ComplexBoundaryNames.add("Leak Survey Area|O31560688|9eb55af4-b37e-48b7-acc5-834ac8d7bc58");
		ComplexBoundaryNames.add("Leak Survey Area|O31560696|4499b204-c1cf-4b51-8857-b74aba33278e");
		ComplexBoundaryNames.add("Leak Survey Area|O31560704|b47b6782-715b-43c1-9314-04db5038744c");
		ComplexBoundaryNames.add("Leak Survey Area|O31560712|c1b9cd4d-90e2-4cf4-8294-f9ccfcbe7909");
		ComplexBoundaryNames.add("Leak Survey Area|O31560720|b161c704-8094-4531-b853-1c1fb1a09d84");
		ComplexBoundaryNames.add("Leak Survey Area|O31560736|8a3a07ed-15fa-4da5-b0f4-2414cd0d3903");
		ComplexBoundaryNames.add("Leak Survey Area|O31560752|7a7e3a52-1668-4b23-8bbd-b8a141ae1f49");
		ComplexBoundaryNames.add("Leak Survey Area|O31680712|c1db920a-1921-48b3-b72b-43c6ba4ab19b");
		ComplexBoundaryNames.add("Leak Survey Area|O31680720|532af40a-44b0-43e2-a78b-affe74cf441c");
		ComplexBoundaryNames.add("Leak Survey Area|O31800696|36c340b7-054e-45b5-bf32-7c18cdabe5ab");
		ComplexBoundaryNames.add("Leak Survey Area|O31800704|f91d70e8-9661-476d-8024-48a762c03f71");
		ComplexBoundaryNames.add("Leak Survey Area|O31800720|04b53067-8132-4774-ba7a-8fa4a419f39a");
		ComplexBoundaryNames.add("Leak Survey Area|O31920704|7d696f97-5675-4427-a388-803b9014b002");
		ComplexBoundaryNames.add("Leak Survey Area|O31920848|e838d2c9-b506-41d9-8ce7-431ad1619c69");
		ComplexBoundaryNames.add("Leak Survey Area|SUGARLAND SEC 1|31440640-66f6-4e84-b57f-3a67e52ca761");
		ComplexBoundaryNames.add("Leak Survey Area|TYLER HP|abf5b91e-6556-45bc-8a2b-f57e94197884");
	}

	/**
	 * Populate map with guids from SurveyModeType.
	 * NOTE: These guids need to be in sync with Ids used in [SurveyModeType] table.
	 */
	private static void populateGuidMaps() {
		SurveyModeFilterGuids = Collections.synchronizedMap(new HashMap<String, SurveyModeFilter>());
		ReportSurveyModeFilterGuids = Collections.synchronizedMap(new HashMap<String, ReportModeFilter>());
		ReportJobTypeGuids = Collections.synchronizedMap(new HashMap<String, ReportJobType>());
		ReportTypeGuids = Collections.synchronizedMap(new HashMap<String, ReportType>());
		ReportJobTypeReverseGuids = Collections.synchronizedMap(new HashMap<ReportJobType, String>());

		SurveyModeFilterGuids.put("0514B92A-39AE-4111-AF16-4495440EC319", SurveyModeFilter.Assessment);
		SurveyModeFilterGuids.put("4901E67A-4C00-4436-ADC0-9CFB277BB310", SurveyModeFilter.RapidResponse);
		SurveyModeFilterGuids.put("4901E67A-4C00-4436-ADC0-9CFB277BB311", SurveyModeFilter.Manual);
		SurveyModeFilterGuids.put("4901E67A-4C00-4436-ADC0-9CFB277BB312", SurveyModeFilter.Operator);
		SurveyModeFilterGuids.put("E9DD9F53-E5CB-45B3-9517-9DC8E0276C6D", SurveyModeFilter.EQ);
		SurveyModeFilterGuids.put("B310238A-A5AE-4E94-927B-F0F165E24522", SurveyModeFilter.Standard);
		SurveyModeFilterGuids.put("7EA32138-5FA9-40C8-AC7F-2175054D2359", SurveyModeFilter.Analytics);

		ReportSurveyModeFilterGuids.put("0514B92A-39AE-4111-AF16-4495440EC319", ReportModeFilter.Assessment);
		ReportSurveyModeFilterGuids.put("4901E67A-4C00-4436-ADC0-9CFB277BB310", ReportModeFilter.RapidResponse);
		ReportSurveyModeFilterGuids.put("4901E67A-4C00-4436-ADC0-9CFB277BB311", ReportModeFilter.Manual);
		ReportSurveyModeFilterGuids.put("4901E67A-4C00-4436-ADC0-9CFB277BB312", ReportModeFilter.Operator);
		ReportSurveyModeFilterGuids.put("E9DD9F53-E5CB-45B3-9517-9DC8E0276C6D", ReportModeFilter.EQ);
		ReportSurveyModeFilterGuids.put("B310238A-A5AE-4E94-927B-F0F165E24522", ReportModeFilter.Standard);
		ReportSurveyModeFilterGuids.put("7EA32138-5FA9-40C8-AC7F-2175054D2359", ReportModeFilter.Analytics);

		ReportTypeGuids.put("00000000-0000-0000-0001-000000000000", ReportType.COMPLIANCE);
		ReportTypeGuids.put("00000000-0000-0000-0002-000000000000", ReportType.INVESTIGATION);
		ReportTypeGuids.put("00000000-0000-0000-0005-000000000000", ReportType.EQ);
		ReportTypeGuids.put("00000000-0000-0000-0006-000000000000", ReportType.ASSESSMENT);
		ReportTypeGuids.put("00000000-0000-0000-0007-000000000000", ReportType.FACILITYEQ);

		ReportJobTypeGuids.put("00000000-0000-0000-0001-000000000000", ReportJobType.Map);
		ReportJobTypeGuids.put("00000000-0000-0000-0002-000000000000", ReportJobType.SSRS);
		ReportJobTypeGuids.put("00000000-0000-0000-0003-000000000000", ReportJobType.DataGeneration);
		ReportJobTypeGuids.put("00000000-0000-0000-0004-000000000000", ReportJobType.EQMap);
		ReportJobTypeGuids.put("00000000-0000-0000-0005-000000000000", ReportJobType.EQSSRS);
		ReportJobTypeGuids.put("00000000-0000-0000-0006-000000000000", ReportJobType.EQDataGeneration);
		ReportJobTypeGuids.put("00000000-0000-0000-0007-000000000000", ReportJobType.ShapeFile);
		ReportJobTypeGuids.put("00000000-0000-0000-0008-000000000000", ReportJobType.ReportMeta);
		ReportJobTypeGuids.put("00000000-0000-0000-0009-000000000000", ReportJobType.PercentCoverageForecast);
		ReportJobTypeGuids.put("00000000-0000-0000-0010-000000000000", ReportJobType.Zip);
		ReportJobTypeGuids.put("00000000-0000-0000-0011-000000000000", ReportJobType.AssetBoxHighlight);
		ReportJobTypeGuids.put("00000000-0000-0000-0012-000000000000", ReportJobType.FacilityEQDataGeneration);
		ReportJobTypeGuids.put("00000000-0000-0000-0013-000000000000", ReportJobType.FacilityEQMap);
		ReportJobTypeGuids.put("00000000-0000-0000-0014-000000000000", ReportJobType.FacilityEQConcentrationChart);
		ReportJobTypeGuids.put("00000000-0000-0000-0015-000000000000", ReportJobType.FacilityEQReportMeta);
		ReportJobTypeGuids.put("00000000-0000-0000-0016-000000000000", ReportJobType.FTP);
		ReportJobTypeGuids.put("00000000-0000-0000-0017-000000000000", ReportJobType.AnalyticsRanking);
		ReportJobTypeGuids.put("00000000-0000-0000-0018-000000000000", ReportJobType.LISAAssetHighlight);

		ReportJobTypeReverseGuids.put(ReportJobType.Map, "00000000-0000-0000-0001-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.SSRS, "00000000-0000-0000-0002-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.DataGeneration, "00000000-0000-0000-0003-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.EQMap, "00000000-0000-0000-0004-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.EQSSRS, "00000000-0000-0000-0005-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.EQDataGeneration, "00000000-0000-0000-0006-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.ShapeFile, "00000000-0000-0000-0007-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.ReportMeta, "00000000-0000-0000-0008-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.PercentCoverageForecast, "00000000-0000-0000-0009-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.Zip, "00000000-0000-0000-0010-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.AssetBoxHighlight, "00000000-0000-0000-0011-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.FacilityEQDataGeneration, "00000000-0000-0000-0012-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.FacilityEQMap, "00000000-0000-0000-0013-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.FacilityEQConcentrationChart, "00000000-0000-0000-0014-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.FacilityEQReportMeta, "00000000-0000-0000-0015-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.FTP, "00000000-0000-0000-0016-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.AnalyticsRanking, "00000000-0000-0000-0017-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.LISAAssetHighlight, "00000000-0000-0000-0018-000000000000");
	}
}