package surveyor.dataaccess.source;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.source.Log;

public class StoredProcLisaInvestigationLeaksByPeakId extends BaseEntity {
	private String pipeMaterialType;
	private String foundDateTime;
	private String leakSourceType;
	private String mapNumber;
	private String barholeReading;
	private String surfaceReading;
	private String addressStreetName;
	private String addressCity;
	private String addressStreetNumber;
	private String investigator;
	private String locationRemarks;
	private Float leakLatitude;
	private Boolean isPavedWallToWall;
	private Float leakLongitude;
	private String addressApartmentNumber;
	private String surfaceOverLeakType;
	private String notes;
	private String meterNumber;
	private String leakLocationType;
	private String addressState;
	private Float gpsPrecision;

	public StoredProcLisaInvestigationLeaksByPeakId() {
		super();
	}

	public StoredProcLisaInvestigationLeaksByPeakId(String pipeMaterialType, String foundDateTime, String leakSourceType, String mapNumber, String barholeReading,
			String surfaceReading, String addressStreetName, String addressCity, String addressStreetNumber, String investigator, String locationRemarks,
			Float leakLatitude, Boolean isPavedWallToWall, Float leakLongitude, String addressApartmentNumber, String surfaceOverLeakType, String notes,
			String meterNumber, String leakLocationType, String addressState, Float gpsPrecision) {
		super();
		this.pipeMaterialType = pipeMaterialType;
		this.foundDateTime = foundDateTime;
		this.leakSourceType = leakSourceType;
		this.mapNumber = mapNumber;
		this.barholeReading = barholeReading;
		this.surfaceReading = surfaceReading;
		this.addressStreetName = addressStreetName;
		this.addressCity = addressCity;
		this.addressStreetNumber = addressStreetNumber;
		this.investigator = investigator;
		this.locationRemarks = locationRemarks;
		this.leakLatitude = leakLatitude;
		this.isPavedWallToWall = isPavedWallToWall;
		this.leakLongitude = leakLongitude;
		this.addressApartmentNumber = addressApartmentNumber;
		this.surfaceOverLeakType = surfaceOverLeakType;
		this.notes = notes;
		this.meterNumber = meterNumber;
		this.leakLocationType = leakLocationType;
		this.addressState = addressState;
		this.gpsPrecision = gpsPrecision;
	}

	public String getPipeMaterialType() {
		return pipeMaterialType;
	}

	public void setPipeMaterialType(String pipeMaterialType) {
		this.pipeMaterialType = pipeMaterialType;
	}

	public String getFoundDateTime() {
		return foundDateTime;
	}

	public void setFoundDateTime(String foundDateTime) {
		this.foundDateTime = foundDateTime;
	}

	public String getLeakSourceType() {
		return leakSourceType;
	}

	public void setLeakSourceType(String leakSourceType) {
		this.leakSourceType = leakSourceType;
	}

	public String getMapNumber() {
		return mapNumber;
	}

	public void setMapNumber(String mapNumber) {
		this.mapNumber = mapNumber;
	}

	public String getBarholeReading() {
		return barholeReading;
	}

	public void setBarholeReading(String barholeReading) {
		this.barholeReading = barholeReading;
	}

	public String getSurfaceReading() {
		return surfaceReading;
	}

	public void setSurfaceReading(String surfaceReading) {
		this.surfaceReading = surfaceReading;
	}

	public String getAddressStreetName() {
		return addressStreetName;
	}

	public void setAddressStreetName(String addressStreetName) {
		this.addressStreetName = addressStreetName;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressStreetNumber() {
		return addressStreetNumber;
	}

	public void setAddressStreetNumber(String addressStreetNumber) {
		this.addressStreetNumber = addressStreetNumber;
	}

	public String getInvestigator() {
		return investigator;
	}

	public void setInvestigator(String investigator) {
		this.investigator = investigator;
	}

	public String getLocationRemarks() {
		return locationRemarks;
	}

	public void setLocationRemarks(String locationRemarks) {
		this.locationRemarks = locationRemarks;
	}

	public Float getLeakLatitude() {
		return leakLatitude;
	}

	public void setLeakLatitude(Float leakLatitude) {
		this.leakLatitude = leakLatitude;
	}

	public Boolean getIsPavedWallToWall() {
		return isPavedWallToWall;
	}

	public void setIsPavedWallToWall(Boolean isPavedWallToWall) {
		this.isPavedWallToWall = isPavedWallToWall;
	}

	public Float getLeakLongitude() {
		return leakLongitude;
	}

	public void setLeakLongitude(Float leakLongitude) {
		this.leakLongitude = leakLongitude;
	}

	public String getAddressApartmentNumber() {
		return addressApartmentNumber;
	}

	public void setAddressApartmentNumber(String addressApartmentNumber) {
		this.addressApartmentNumber = addressApartmentNumber;
	}

	public String getSurfaceOverLeakType() {
		return surfaceOverLeakType;
	}

	public void setSurfaceOverLeakType(String surfaceOverLeakType) {
		this.surfaceOverLeakType = surfaceOverLeakType;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getMeterNumber() {
		return meterNumber;
	}

	public void setMeterNumber(String meterNumber) {
		this.meterNumber = meterNumber;
	}

	public String getLeakLocationType() {
		return leakLocationType;
	}

	public void setLeakLocationType(String leakLocationType) {
		this.leakLocationType = leakLocationType;
	}

	public String getAddressState() {
		return addressState;
	}

	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}

	public Float getGpsPrecision() {
		return gpsPrecision;
	}

	public void setGpsPrecision(Float gpsPrecision) {
		this.gpsPrecision = gpsPrecision;
	}

	public static List<StoredProcLisaInvestigationLeaksByPeakId> getLisaInvestigationLeaksByPeakId(String reportId, String boxId) {
		return new StoredProcLisaInvestigationLeaksByPeakId().load(reportId, boxId);
	}

	private static StoredProcLisaInvestigationLeaksByPeakId loadFrom(ResultSet resultSet) {
		StoredProcLisaInvestigationLeaksByPeakId objLisaInvestigation_LeaksByPeakId = new StoredProcLisaInvestigationLeaksByPeakId();
		try {
			objLisaInvestigation_LeaksByPeakId.setPipeMaterialType(resultSet.getString("PipeMaterialType"));
			objLisaInvestigation_LeaksByPeakId.setFoundDateTime(resultSet.getString("FoundDateTime"));
			if (resultSet.wasNull()) {
				objLisaInvestigation_LeaksByPeakId.setFoundDateTime(" ");
			}

			objLisaInvestigation_LeaksByPeakId.setLeakSourceType(resultSet.getString("LeakSourceType"));
			objLisaInvestigation_LeaksByPeakId.setMapNumber(resultSet.getString("MapNumber"));
			objLisaInvestigation_LeaksByPeakId.setBarholeReading(resultSet.getString("BarholeReading"));
			objLisaInvestigation_LeaksByPeakId.setSurfaceReading(resultSet.getString("SurfaceReading"));
			objLisaInvestigation_LeaksByPeakId.setAddressStreetName(resultSet.getString("AddressStreetName"));
			objLisaInvestigation_LeaksByPeakId.setAddressCity(resultSet.getString("AddressCity"));
			objLisaInvestigation_LeaksByPeakId.setAddressStreetNumber(resultSet.getString("AddressStreetNumber"));
			objLisaInvestigation_LeaksByPeakId.setInvestigator(resultSet.getString("Investigator"));
			objLisaInvestigation_LeaksByPeakId.setLocationRemarks(resultSet.getString("LocationRemarks"));
			objLisaInvestigation_LeaksByPeakId.setLeakLatitude(getFloatColumnValue(resultSet,"LeakLatitude"));
			objLisaInvestigation_LeaksByPeakId.setIsPavedWallToWall(resultSet.getBoolean("IsPavedWallToWall"));
			objLisaInvestigation_LeaksByPeakId.setLeakLongitude(getFloatColumnValue(resultSet,"LeakLongitude"));
			objLisaInvestigation_LeaksByPeakId.setAddressApartmentNumber(resultSet.getString("AddressApartmentNumber"));
			objLisaInvestigation_LeaksByPeakId.setSurfaceOverLeakType(resultSet.getString("SurfaceOverLeakType"));
			objLisaInvestigation_LeaksByPeakId.setNotes(resultSet.getString("Notes"));
			objLisaInvestigation_LeaksByPeakId.setMeterNumber(resultSet.getString("MeterNumber"));
			objLisaInvestigation_LeaksByPeakId.setLeakLocationType(resultSet.getString("LeakLocationType"));
			objLisaInvestigation_LeaksByPeakId.setAddressState(resultSet.getString("AddressState"));
			objLisaInvestigation_LeaksByPeakId.setGpsPrecision(getFloatColumnValue(resultSet,"GpsPrecision"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objLisaInvestigation_LeaksByPeakId;
	}

	public ArrayList<StoredProcLisaInvestigationLeaksByPeakId> load(String reportId, String boxId) {
		ArrayList<StoredProcLisaInvestigationLeaksByPeakId> objLisaInvestigation_LeaksByPeakIdList = new ArrayList<StoredProcLisaInvestigationLeaksByPeakId>();

		try {
			CallableStatement proc_stmt = connection.prepareCall("{ call LisaInvestigation_LeaksByPeakId(?, ?) }");
			proc_stmt.setString(1, reportId);
			proc_stmt.setString(2, boxId);
			resultSet = proc_stmt.executeQuery();
			while (resultSet.next()) {
				StoredProcLisaInvestigationLeaksByPeakId objReport = loadFrom(resultSet);
				objLisaInvestigation_LeaksByPeakIdList.add(objReport);
			}

		} catch (SQLException e) {
			Log.error("Class LisaInvestigation_LeaksByPeakId | " + e.toString());
		}

		return objLisaInvestigation_LeaksByPeakIdList;
	}
}
