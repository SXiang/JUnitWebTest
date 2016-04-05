package surveyor.dataaccess.source;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import common.source.DateUtility;
import common.source.Log;
 
public class ReportJob extends BaseEntity {
	public static final String COLNAME_ID = "Id";
	public static final String COLNAME_DATE_STARTED = "DateStarted";
	public static final String COLNAME_REPORT_VIEW_ID = "ReportViewId";
	public static final String COLNAME_PROCESSING_COMPLETED = "ProcessingCompleted";
	public static final String COLNAME_REPORT_JOB_STATUS_ID = "ReportJobStatusId";
	public static final String COLNAME_REPORT_JOB_TYPE_ID = "ReportJobTypeId";
	public static final String COLNAME_REPORT_ID = "ReportId";
	public static final String COLNAME_PROCESSING_STARTED = "ProcessingStarted";

	private static final String CACHE_KEY = "REPORTJOB.";
 
	private Date dateStarted;
	private Object id;
	private Date processingStarted;
	private Object reportId;
	private Object reportJobTypeId;
	private Object reportJobStatusId;
	private Date processingCompleted;
	private Object reportViewId;
 
	public ReportJob() {
		super();
	}
 
	public Date getDateStarted() {
		return dateStarted;
	}
 
	public void setDateStarted(Date dateStarted) {
		this.dateStarted = dateStarted;
	}
 
	public Object getId() {
		return id;
	}
 
	public void setId(Object id) {
		this.id = id;
	}
 
	public Date getProcessingStarted() {
		return processingStarted;
	}
 
	public void setProcessingStarted(Date processingStarted) {
		this.processingStarted = processingStarted;
	}
 
	public Object getReportId() {
		return reportId;
	}
 
	public void setReportId(Object reportId) {
		this.reportId = reportId;
	}
 
	public Object getReportJobTypeId() {
		return reportJobTypeId;
	}
 
	public void setReportJobTypeId(Object reportJobTypeId) {
		this.reportJobTypeId = reportJobTypeId;
	}
 
	public Object getReportJobStatusId() {
		return reportJobStatusId;
	}
 
	public void setReportJobStatusId(Object reportJobStatusId) {
		this.reportJobStatusId = reportJobStatusId;
	}
 
	public Date getProcessingCompleted() {
		return processingCompleted;
	}
 
	public void setProcessingCompleted(Date processingCompleted) {
		this.processingCompleted = processingCompleted;
	}
 
	public Object getReportViewId() {
		return reportViewId;
	}
 
	public void setReportViewId(Object reportViewId) {
		this.reportViewId = reportViewId;
	}
 
	public static ReportJob getReportJob(String reportId, String reportJobId) {
		ReportJob objReportJob = new ReportJob().get(reportId, reportJobId);
		return objReportJob;
	}

	public static ArrayList<ReportJob> getReportJobs(String reportId) {
		return new ReportJob().get(reportId);
	}

	public ReportJob get(String reportId, String reportJobId) {
		ReportJob objReportJob = null;
		
		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+id)) {
			objReportJob = (ReportJob)DBCache.INSTANCE.get(CACHE_KEY+id);
		} else {
			String SQL = "SELECT * FROM dbo.[ReportJob] WHERE ReportId='" + reportId + "' AND ReportJobId='" + reportJobId + "'";
			ArrayList<ReportJob> objReportJobList = load(SQL);
			if (objReportJobList!=null && objReportJobList.size()>0) {
				objReportJob = objReportJobList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + "_" + reportId + "_" + reportJobId, objReportJob);
			}
		}
		return objReportJob;
	}

	public ArrayList<ReportJob> get(String reportId) {
		String SQL = "SELECT * FROM dbo.[ReportJob] WHERE ReportId='" + reportId + "'";
		return load(SQL);
	}

	private static ReportJob loadFrom(ResultSet resultSet) {
		ReportJob objReportJob = new ReportJob();
		try {
			objReportJob.setDateStarted(resultSet.getDate(COLNAME_DATE_STARTED));
			objReportJob.setId(resultSet.getObject(COLNAME_ID));
			objReportJob.setProcessingStarted(getDateColumnValue(resultSet,COLNAME_PROCESSING_STARTED));
			objReportJob.setReportId(resultSet.getObject(COLNAME_REPORT_ID));
			objReportJob.setReportJobTypeId(resultSet.getObject(COLNAME_REPORT_JOB_TYPE_ID));
			objReportJob.setReportJobStatusId(resultSet.getObject(COLNAME_REPORT_JOB_STATUS_ID));
			objReportJob.setProcessingCompleted(getDateColumnValue(resultSet,COLNAME_PROCESSING_COMPLETED));
			objReportJob.setReportViewId(resultSet.getObject(COLNAME_REPORT_VIEW_ID));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objReportJob;
	}
	
	public ArrayList<ReportJob> getAll() {
		String SQL = "SELECT * FROM dbo.[ReportJob]";
		return load(SQL);
	}
 
	public ArrayList<ReportJob> load(String SQL) {
		ArrayList<ReportJob> objReportJobList = new ArrayList<ReportJob>();
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);
			
			while (resultSet.next()) {
				ReportJob objReportJob = loadFrom(resultSet);
				objReportJobList.add(objReportJob);
				
				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + "_" + objReportJob.getReportId() + "_" + objReportJob.getReportJobTypeId(), objReportJob);
			}
			
		} catch (SQLException e) {
			Log.error("Class ReportJob | " + e.toString());
		}
		
		return objReportJobList;
	}

	public boolean isColumnNull(String columnName) throws Exception {
		if (columnName.equals(COLNAME_DATE_STARTED)) {
			return getDateStarted().equals(DateUtility.DATE_MINVALUE);
		} else if (columnName.equals(COLNAME_ID)) {
			return getId() == null || getId().toString().isEmpty();
		} else if (columnName.equals(COLNAME_PROCESSING_STARTED)) {
			return getProcessingStarted().equals(DateUtility.DATE_MINVALUE);
		} else if (columnName.equals(COLNAME_REPORT_ID)) {
			return getReportId() == null || getReportId().toString().isEmpty();
		} else if (columnName.equals(COLNAME_REPORT_JOB_TYPE_ID)) {
			return getReportJobTypeId() == null || getReportJobTypeId().toString().isEmpty();
		} else if (columnName.equals(COLNAME_REPORT_JOB_STATUS_ID)) {
			return getReportJobStatusId() == null || getReportJobStatusId().toString().isEmpty();
		} else if (columnName.equals(COLNAME_PROCESSING_COMPLETED)) {
			return getProcessingCompleted().equals(DateUtility.DATE_MINVALUE);
		} else if (columnName.equals(COLNAME_REPORT_VIEW_ID)) {
			return getReportViewId() == null || getReportViewId().toString().isEmpty();
		}
		throw new Exception(String.format("Column name-[%s] not recognized", columnName));
	}
}
