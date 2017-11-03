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
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-SUGARLAND SEC 1|17ebfaad-3d00-473e-8ea9-8497ff487743");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31440728|00A913EF-AEA3-4FD7-8A44-ECCB11854980");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31320720|02F897B6-07D3-42DC-93E8-F8B8672C36A3");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-HP/ LD SURVEY 13|0A4E33D6-F18C-49BC-B4CF-200A3D5EDC69");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31200704|17482A7B-5650-4361-9000-B403932517B6");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-HP/ LD SURVEY 3|182E5B88-3359-4412-8FCB-1BC04F59FE68");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O30840704|18854B21-0805-46A4-9E38-C3B3AEE64837");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31560712|1FA750EE-A8DA-4438-87B9-809BA5AC7485");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O30840752|21093670-6795-4959-AB3D-863891BBA372");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31680712|2496FEBA-BDAE-4334-8C3B-57EBB3BDECB8");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31320696|272AD8DD-1CDF-4424-80D7-D584B67AE700");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31800696|274CE8AA-FA42-4CCA-90CF-695146345364");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31680720|278BEFB5-6AB5-4AA9-8134-9AC967180D0C");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-BEAUMONT HP|2AEAA35C-BBE4-4840-81CA-C7A795040BFE");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31440696|32E882DA-B34C-415E-A7B4-9798EB06E763");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31320736|360C261B-FA0C-492E-A414-AD67463E6814");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31200688|37D977D1-E820-4928-97D3-B5CA6282DDC2");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-HP/ LD SURVEY 11|3847F66F-867E-43D8-83F9-F9B703FBBBEE");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31560696|39B82B9C-4992-4736-9497-D916DDD6CB85");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31560720|3ED3FF1D-50EE-4565-882D-85750271C3D5");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-HP/ LD SURVEY 12|4B967C3F-5BFD-4E57-A068-E339F0099409");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31440704|52BC9172-1156-4EE5-8750-8A235CA7EC51");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-HP/ LD SURVEY 15|5E1AA6BD-9C24-4039-A505-48D080D787EC");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-HP/ LD SURVEY 6|6433536D-9D24-4D24-8FB4-58B534037FBB");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31560704|697D8884-8D44-40F4-9067-C2A09C2F19FB");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-HP/ LD SURVEY 9|6FFBD5ED-A92D-4181-8727-762394ED8713");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31560736|7BF2034B-163F-49C9-AC3D-84EFF183AD9A");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O30960704|7DCCD227-7A33-401F-92B4-2DCD9CA44B91");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31440688|92AB47B8-910B-4883-B1F0-65C8D741426A");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31800720|9E4F05AA-1AE9-4A10-A445-EEC2B2335B45");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-HP/ LD SURVEY 10|9E9AA9D5-C4CD-4043-9EFF-E84D8AE9B22C");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31440712|9EE036FB-1B21-4D42-9B20-64FA536CA100");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-HP/ LD SURVEY 7|A6298821-2B0E-460F-9414-3A5A223290AB");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31920704|AA3BD112-70BE-4E17-AB1C-93B5DFE4E1D1");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-HP/ LD SURVEY 17|AAA4BA5E-F7F1-495D-B86F-12FD1B8178EE");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31920848|AB330758-E326-46A7-9FB2-C70487D5BCFB");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-HP/ LD SURVEY 14|B07CA23F-B3D6-4DA7-94E9-BCFEAD8E9F78");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-HP/ LD SURVEY 16|B97D2C99-221D-4BB1-8C34-8695B3188B67");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-LAREDO HP|BCC3AA24-4C7D-4519-9E60-56803D273AFB");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O30840728|BE5D115D-93BF-48AF-AA7C-32A55208217A");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-HP/ LD SURVEY 5|D58FCED1-E527-4F62-8AB8-44140D96211B");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31560752|D734C85E-B968-4A8A-BC35-DABD95189CA7");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-HP/ LD SURVEY 1|DE320771-1A0B-470E-B5F9-2AB22B7AD355");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-HP/ LD SURVEY 4|DF11C0B3-867E-443C-94F6-2D3D9080BEB8");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31800704|E73F9375-394A-48B2-9252-22C4D6D9B2ED");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31320728|E87F91DE-6150-4E76-8968-318A98043C17");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31080752|E95A1542-A13C-4E53-AD75-96ABCDBD30E3");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-HP/ LD SURVEY 8|EA6DB438-58B7-40A4-BB2D-89EFE172D331");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-TYLER HP|F07A7A0C-C857-415B-A956-039FD0625908");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-HP/ LD SURVEY 2a|F46854AD-BB8C-4079-AE58-A803FC59A71C");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O31560688|F61533EC-AF68-4E3A-A8C3-A536E67A6120");
		ComplexBoundaryNames.add("Leak Survey Area|LeakSurveyMap-O30720800|9D5E66A8-C51D-4ABC-A291-C374F76CBDAF");
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