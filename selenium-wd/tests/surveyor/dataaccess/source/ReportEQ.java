package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.source.Log;

public class ReportEQ extends BaseEntity {
	private static final String CACHE_KEY = "REPORTEQ.";

	private Boolean showGaps;
	private Float fovOpacity;
	private Object reportId;
	private Float mapBufferArea;
	private Object locationId;
	private Boolean showLisa;
	private Boolean showBoundaries;
	private Float lisaOpacity;
	private Boolean showAssets;
	private Boolean showVehiclePath;
	private Boolean showIndications;
	private Boolean showIsotopicCaptures;
	private Boolean showFov;

	public ReportEQ() {
		super();
	}

	public ReportEQ(Boolean showGaps, Float fovOpacity, Object reportId, Float mapBufferArea, Object locationId, Boolean showLisa, Boolean showBoundaries,
			Float lisaOpacity, Boolean showAssets, Boolean showVehiclePath, Boolean showIndications, Boolean showIsotopicCaptures, Boolean showFov) {
		super();
		this.showGaps = showGaps;
		this.fovOpacity = fovOpacity;
		this.reportId = reportId;
		this.mapBufferArea = mapBufferArea;
		this.locationId = locationId;
		this.showLisa = showLisa;
		this.showBoundaries = showBoundaries;
		this.lisaOpacity = lisaOpacity;
		this.showAssets = showAssets;
		this.showVehiclePath = showVehiclePath;
		this.showIndications = showIndications;
		this.showIsotopicCaptures = showIsotopicCaptures;
		this.showFov = showFov;
	}

	public Boolean getShowGaps() {
		return showGaps;
	}

	public void setShowGaps(Boolean showGaps) {
		this.showGaps = showGaps;
	}

	public Float getFovOpacity() {
		return fovOpacity;
	}

	public void setFovOpacity(Float fovOpacity) {
		this.fovOpacity = fovOpacity;
	}

	public Object getReportId() {
		return reportId;
	}

	public void setReportId(Object reportId) {
		this.reportId = reportId;
	}

	public Float getMapBufferArea() {
		return mapBufferArea;
	}

	public void setMapBufferArea(Float mapBufferArea) {
		this.mapBufferArea = mapBufferArea;
	}

	public Object getLocationId() {
		return locationId;
	}

	public void setLocationId(Object locationId) {
		this.locationId = locationId;
	}

	public Boolean getShowLisa() {
		return showLisa;
	}

	public void setShowLisa(Boolean showLisa) {
		this.showLisa = showLisa;
	}

	public Boolean getShowBoundaries() {
		return showBoundaries;
	}

	public void setShowBoundaries(Boolean showBoundaries) {
		this.showBoundaries = showBoundaries;
	}

	public Float getLisaOpacity() {
		return lisaOpacity;
	}

	public void setLisaOpacity(Float lisaOpacity) {
		this.lisaOpacity = lisaOpacity;
	}

	public Boolean getShowAssets() {
		return showAssets;
	}

	public void setShowAssets(Boolean showAssets) {
		this.showAssets = showAssets;
	}

	public Boolean getShowVehiclePath() {
		return showVehiclePath;
	}

	public void setShowVehiclePath(Boolean showVehiclePath) {
		this.showVehiclePath = showVehiclePath;
	}

	public Boolean getShowIndications() {
		return showIndications;
	}

	public void setShowIndications(Boolean showIndications) {
		this.showIndications = showIndications;
	}

	public Boolean getShowIsotopicCaptures() {
		return showIsotopicCaptures;
	}

	public void setShowIsotopicCaptures(Boolean showIsotopicCaptures) {
		this.showIsotopicCaptures = showIsotopicCaptures;
	}

	public Boolean getShowFov() {
		return showFov;
	}

	public void setShowFov(Boolean showFov) {
		this.showFov = showFov;
	}

	public static ReportEQ getReportEQ(String reportId) {
		ReportEQ objReportEQ = new ReportEQ().get(reportId);
		return objReportEQ;
	}

	public ReportEQ get(String reportId) {
		ReportEQ objReportEQ = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+reportId)) {
			objReportEQ = (ReportEQ)DBCache.INSTANCE.get(CACHE_KEY+reportId);
		} else {
			String SQL = "SELECT * FROM dbo.[ReportEQ] WHERE ReportId='" + reportId + "'";
			ArrayList<ReportEQ> objReportEQList = load(SQL);
			if (objReportEQList!=null && objReportEQList.size()>0) {
				objReportEQ = objReportEQList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + reportId, objReportEQ);
			}
		}
		return objReportEQ;
	}

	private static ReportEQ loadFrom(ResultSet resultSet) {
		ReportEQ objReportEQ = new ReportEQ();
		try {
			objReportEQ.setShowGaps(resultSet.getBoolean("ShowGaps"));
			objReportEQ.setFovOpacity(getFloatColumnValue(resultSet,"FovOpacity"));
			objReportEQ.setReportId(resultSet.getObject("ReportId"));
			objReportEQ.setMapBufferArea(getFloatColumnValue(resultSet,"MapBufferArea"));
			objReportEQ.setLocationId(resultSet.getObject("LocationId"));
			objReportEQ.setShowLisa(resultSet.getBoolean("ShowLisa"));
			objReportEQ.setShowBoundaries(resultSet.getBoolean("ShowBoundaries"));
			objReportEQ.setLisaOpacity(getFloatColumnValue(resultSet,"LisaOpacity"));
			objReportEQ.setShowAssets(resultSet.getBoolean("ShowAssets"));
			objReportEQ.setShowVehiclePath(resultSet.getBoolean("ShowVehiclePath"));
			objReportEQ.setShowIndications(resultSet.getBoolean("ShowIndications"));
			objReportEQ.setShowIsotopicCaptures(resultSet.getBoolean("ShowIsotopicCaptures"));
			objReportEQ.setShowFov(resultSet.getBoolean("ShowFov"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objReportEQ;
	}

	public ArrayList<ReportEQ> getAll() {
		String SQL = "SELECT * FROM dbo.[ReportEQ]";
		return load(SQL);
	}

	public ArrayList<ReportEQ> load(String SQL) {
		ArrayList<ReportEQ> objReportEQList = new ArrayList<ReportEQ>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				ReportEQ objReportEQ = loadFrom(resultSet);
				objReportEQList.add(objReportEQ);

				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objReportEQ.getReportId(), objReportEQ);
			}

		} catch (SQLException e) {
			Log.error("Class ReportEQ | " + e.toString());
		}

		return objReportEQList;
	}
}
