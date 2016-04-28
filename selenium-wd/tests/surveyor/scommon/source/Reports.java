/**
 * 
 */
package surveyor.scommon.source;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jetty.util.log.Log;

import common.source.TestContext;
import common.source.TestSetup;
import surveyor.scommon.source.Reports.ReportJobType;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SurveyModeFilter;


/**
 * @author zlu
 *
 */
public class Reports {
	
	/**
	 * 
	 */
	static {		
		populateGuidMaps();
	}
	
	public static HashMap<String, SurveyModeFilter> SurveyModeFilterGuids;
	public static HashMap<String, ReportModeFilter> ReportSurveyModeFilterGuids;
	public static HashMap<String, ReportJobType> ReportJobTypeGuids;
	public static HashMap<ReportJobType, String> ReportJobTypeReverseGuids;
	
	protected String rptTitle;
	protected String strCreatedBy;
	private String customer;
	protected String timeZone;
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
		Assessment ("Assessment");
		
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
		Assessment ("Assessment");
		
		private final String name;

		ReportModeFilter(String nm) {
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
		PercentCoverageForecast ("PercentCoverageForecast");
		
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

	public Reports(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit, List<String> tagList) {
		this(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, null, null, null, null, tagList);
	}
	
	public Reports(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit, List<String> tagList, String startDate, String endDate) {
		this(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, null, startDate, endDate, null, tagList);
	}

	public Reports(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit, String userName, String startDate, String endDate, boolean geoFilter) {
		this(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, userName, startDate, endDate, geoFilter, null);
	}
	
	public Reports(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit, String startDate, String endDate, boolean geoFilter) {
		this(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, null, startDate, endDate, geoFilter, null);
	}
	
	public Reports(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit, List<String> tagList, String startDate, String endDate, boolean geoFilter) {
		this(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, null, startDate, endDate, geoFilter, tagList);
	}
	
	public Reports(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit,String userName,  String startDate, String endDate,  List<String> tagList) {
		this(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, userName, startDate, endDate, null, tagList);
	}
	
	public Reports(){
		
	}
	
	public Reports(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit, String userName, String startDate, String endDate, Boolean geoFilter, List<String> tagList){
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

	/**
	 * Populate map with guids from SurveyModeType.
	 * NOTE: These guids need to be in sync with Ids used in [SurveyModeType] table.
	 */
	private static void populateGuidMaps() {
		SurveyModeFilterGuids = new HashMap<String, SurveyModeFilter>(); 
		ReportSurveyModeFilterGuids = new HashMap<String, ReportModeFilter>(); 
		ReportJobTypeGuids = new HashMap<String, ReportJobType>(); 
		ReportJobTypeReverseGuids = new HashMap<ReportJobType, String>();
		
		SurveyModeFilterGuids.put("0514B92A-39AE-4111-AF16-4495440EC319", SurveyModeFilter.Assessment);
		SurveyModeFilterGuids.put("4901E67A-4C00-4436-ADC0-9CFB277BB310", SurveyModeFilter.RapidResponse);
		SurveyModeFilterGuids.put("4901E67A-4C00-4436-ADC0-9CFB277BB311", SurveyModeFilter.Manual);
		SurveyModeFilterGuids.put("4901E67A-4C00-4436-ADC0-9CFB277BB312", SurveyModeFilter.Operator);
		SurveyModeFilterGuids.put("E9DD9F53-E5CB-45B3-9517-9DC8E0276C6D", SurveyModeFilter.EQ);
		SurveyModeFilterGuids.put("B310238A-A5AE-4E94-927B-F0F165E24522", SurveyModeFilter.Standard);

		ReportSurveyModeFilterGuids.put("0514B92A-39AE-4111-AF16-4495440EC319", ReportModeFilter.Assessment);
		ReportSurveyModeFilterGuids.put("4901E67A-4C00-4436-ADC0-9CFB277BB310", ReportModeFilter.RapidResponse);
		ReportSurveyModeFilterGuids.put("4901E67A-4C00-4436-ADC0-9CFB277BB311", ReportModeFilter.Manual);
		ReportSurveyModeFilterGuids.put("4901E67A-4C00-4436-ADC0-9CFB277BB312", ReportModeFilter.Operator);
		ReportSurveyModeFilterGuids.put("E9DD9F53-E5CB-45B3-9517-9DC8E0276C6D", ReportModeFilter.EQ);
		ReportSurveyModeFilterGuids.put("B310238A-A5AE-4E94-927B-F0F165E24522", ReportModeFilter.Standard);
		
		ReportJobTypeGuids.put("00000000-0000-0000-0001-000000000000", ReportJobType.Map);
		ReportJobTypeGuids.put("00000000-0000-0000-0002-000000000000", ReportJobType.SSRS);
		ReportJobTypeGuids.put("00000000-0000-0000-0003-000000000000", ReportJobType.DataGeneration);
		ReportJobTypeGuids.put("00000000-0000-0000-0004-000000000000", ReportJobType.EQMap);
		ReportJobTypeGuids.put("00000000-0000-0000-0005-000000000000", ReportJobType.EQSSRS);
		ReportJobTypeGuids.put("00000000-0000-0000-0006-000000000000", ReportJobType.EQDataGeneration);
		ReportJobTypeGuids.put("00000000-0000-0000-0007-000000000000", ReportJobType.ShapeFile);
		ReportJobTypeGuids.put("00000000-0000-0000-0008-000000000000", ReportJobType.ReportMeta);
		ReportJobTypeGuids.put("00000000-0000-0000-0009-000000000000", ReportJobType.PercentCoverageForecast);

		ReportJobTypeReverseGuids.put(ReportJobType.Map, "00000000-0000-0000-0001-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.SSRS, "00000000-0000-0000-0002-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.DataGeneration, "00000000-0000-0000-0003-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.EQMap, "00000000-0000-0000-0004-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.EQSSRS, "00000000-0000-0000-0005-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.EQDataGeneration, "00000000-0000-0000-0006-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.ShapeFile, "00000000-0000-0000-0007-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.ReportMeta, "00000000-0000-0000-0008-000000000000");
		ReportJobTypeReverseGuids.put(ReportJobType.PercentCoverageForecast, "00000000-0000-0000-0009-000000000000");
	}
	
	public static void main(String[] args) {
	}
}
