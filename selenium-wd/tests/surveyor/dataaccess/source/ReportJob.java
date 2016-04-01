package surveyor.dataaccess.source;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import common.source.Log;
 
public class ReportJob extends BaseEntity {
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
 
	public ReportJob get(String reportId, String reportJobId) {
		ReportJob objReportJob = null;
		
		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+id)) {
			objReportJob = (ReportJob)DBCache.INSTANCE.get(CACHE_KEY+id);
		} else {
			String SQL = "SELECT * FROM dbo.[ReportJob] WHERE ReportId='" + id + "' AND ReportJobId='" + id + "'";
			ArrayList<ReportJob> objReportJobList = load(SQL);
			if (objReportJobList!=null && objReportJobList.size()>0) {
				objReportJob = objReportJobList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + "_" + reportId + "_" + reportJobId, objReportJob);
			}
		}
		return objReportJob;
	}
 
	private static ReportJob loadFrom(ResultSet resultSet) {
		ReportJob objReportJob = new ReportJob();
		try {
			objReportJob.setDateStarted(resultSet.getDate("DateStarted"));
			objReportJob.setId(resultSet.getObject("Id"));
			objReportJob.setProcessingStarted(resultSet.getDate("ProcessingStarted"));
			objReportJob.setReportId(resultSet.getObject("ReportId"));
			objReportJob.setReportJobTypeId(resultSet.getObject("ReportJobTypeId"));
			objReportJob.setReportJobStatusId(resultSet.getObject("ReportJobStatusId"));
			objReportJob.setProcessingCompleted(resultSet.getDate("ProcessingCompleted"));
			objReportJob.setReportViewId(resultSet.getObject("ReportViewId"));
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
}
