package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.source.Log;

public class ReportView extends BaseEntity {
	private static final String CACHE_KEY = "REPORTVIEW.";

	private Object baseMapType_Id;
	private String showIndications;
	private Object reportId;
	private Integer viewNameOrder;
	private String showFov;
	private String showLisa;
	private Object id;
	private Object baseMapId;
	private String showVehiclePath;
	private String showAssets;
	private String showGaps;
	private String viewName;
	private String showBoundaries;
	private String showIsotopicCaptures;
	private String showAnnotations;

	public ReportView() {
		super();
	}

	public Object getBaseMapType_Id() {
		return baseMapType_Id;
	}

	public void setBaseMapType_Id(Object baseMapType_Id) {
		this.baseMapType_Id = baseMapType_Id;
	}

	public String getShowIndications() {
		return showIndications;
	}

	public void setShowIndications(String showIndications) {
		this.showIndications = showIndications;
	}

	public Object getReportId() {
		return reportId;
	}

	public void setReportId(Object reportId) {
		this.reportId = reportId;
	}

	public Integer getViewNameOrder() {
		return viewNameOrder;
	}

	public void setViewNameOrder(Integer viewNameOrder) {
		this.viewNameOrder = viewNameOrder;
	}

	public String getShowFov() {
		return showFov;
	}

	public void setShowFov(String showFov) {
		this.showFov = showFov;
	}

	public String getShowLisa() {
		return showLisa;
	}

	public void setShowLisa(String showLisa) {
		this.showLisa = showLisa;
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public Object getBaseMapId() {
		return baseMapId;
	}

	public void setBaseMapId(Object baseMapId) {
		this.baseMapId = baseMapId;
	}

	public String getShowVehiclePath() {
		return showVehiclePath;
	}

	public void setShowVehiclePath(String showVehiclePath) {
		this.showVehiclePath = showVehiclePath;
	}

	public String getShowAssets() {
		return showAssets;
	}

	public void setShowAssets(String showAssets) {
		this.showAssets = showAssets;
	}

	public String getShowGaps() {
		return showGaps;
	}

	public void setShowGaps(String showGaps) {
		this.showGaps = showGaps;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getShowBoundaries() {
		return showBoundaries;
	}

	public void setShowBoundaries(String showBoundaries) {
		this.showBoundaries = showBoundaries;
	}

	public String getShowIsotopicCaptures() {
		return showIsotopicCaptures;
	}

	public void setShowIsotopicCaptures(String showIsotopicCaptures) {
		this.showIsotopicCaptures = showIsotopicCaptures;
	}

	public String getShowAnnotations() {
		return showAnnotations;
	}

	public void setShowAnnotations(String showAnnotations) {
		this.showAnnotations = showAnnotations;
	}

	public static List<ReportView> getReportView(String reportId) {
		List<ReportView> objReportView = new ReportView().get(reportId);
		return objReportView;
	}

	public List<ReportView> get(String reportId) {
		ArrayList<ReportView> objReportViewList = new ArrayList<ReportView>();

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY + reportId)) {
			objReportViewList = (ArrayList<ReportView>) DBCache.INSTANCE.get(CACHE_KEY + reportId);
		} else {
			String SQL = "SELECT * FROM dbo.[ReportView] WHERE ReportId='" + reportId + "'";
			objReportViewList = load(SQL);
			DBCache.INSTANCE.set(CACHE_KEY + reportId, objReportViewList);

		}
		return objReportViewList;
	}

	public boolean isViewNameAndMapEqual(ReportView obj) {
		if (!this.getViewName().equalsIgnoreCase(obj.getViewName())) {
			return false;
		}
		if (!this.getBaseMapId().toString().equalsIgnoreCase(obj.getBaseMapId().toString())) {
			return false;
		}
		return true;
	}

	public boolean isViewNameAndMapInList(ArrayList<ReportView> list) {
		for (ReportView reportView : list) {
			if (this.isViewNameAndMapEqual(reportView)) {
				return true;
			}
		}
		return false;
	}

	private static ReportView loadFrom(ResultSet resultSet) {
		ReportView objReportView = new ReportView();
		try {
			objReportView.setBaseMapType_Id(resultSet.getObject("BaseMapType_Id"));
			objReportView.setShowIndications(resultSet.getString("ShowIndications"));
			objReportView.setReportId(resultSet.getObject("ReportId"));
			objReportView.setViewNameOrder(resultSet.getInt("ViewNameOrder"));
			objReportView.setShowFov(resultSet.getString("ShowFov"));
			objReportView.setShowLisa(resultSet.getString("ShowLisa"));
			objReportView.setId(resultSet.getObject("Id"));
			objReportView.setBaseMapId(resultSet.getObject("BaseMapId"));
			objReportView.setShowVehiclePath(resultSet.getString("ShowVehiclePath"));
			objReportView.setShowAssets(resultSet.getString("ShowAssets"));
			objReportView.setShowGaps(resultSet.getString("ShowGaps"));
			objReportView.setViewName(resultSet.getString("ViewName"));
			objReportView.setShowBoundaries(resultSet.getString("ShowBoundaries"));
			objReportView.setShowIsotopicCaptures(resultSet.getString("ShowIsotopicCaptures"));
			objReportView.setShowAnnotations(resultSet.getString("ShowAnnotations"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objReportView;
	}

	public ArrayList<ReportView> getAll() {
		String SQL = "SELECT * FROM dbo.[ReportView]";
		return load(SQL);
	}

	public ArrayList<ReportView> load(String SQL) {
		ArrayList<ReportView> objReportViewList = new ArrayList<ReportView>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				ReportView objReportView = loadFrom(resultSet);
				objReportViewList.add(objReportView);

			}
			// add to cache.
			DBCache.INSTANCE.set(CACHE_KEY + reportId, objReportViewList);

		} catch (SQLException e) {
			Log.error("Class ReportView | " + e.toString());
		}

		return objReportViewList;
	}
}
