package androidapp.entities.source;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class LeakInfoEntity {
	private String latitude;
	private String longitude;
	private String streetNum;
	private String aptNum;
	private String streetName;
	private String city;
	private String state;
	private String mapNum;
	private String surfaceReading;
	private String surfaceReadingUnit;
	private String barHoleReading;
	private String barholeReadingUnit;
	private String leakType;
	private String leakGrade;
	private String locationType;
	private String pipeMaterialType;
	private String surfaceOverleakType;
	private String meterNum;
	private String locationRemarks;
	private Boolean isPavedWallToWall;
	private String additionalNotes;

	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getStreetNum() {
		return streetNum;
	}
	public void setStreetNum(String streetNum) {
		this.streetNum = streetNum;
	}
	public String getAptNum() {
		return aptNum;
	}
	public void setAptNum(String aptNum) {
		this.aptNum = aptNum;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMapNum() {
		return mapNum;
	}
	public void setMapNum(String mapNum) {
		this.mapNum = mapNum;
	}
	public String getSurfaceReading() {
		return surfaceReading;
	}
	public void setSurfaceReading(String surfaceReading) {
		this.surfaceReading = surfaceReading;
	}
	public String getSurfaceReadingUnit() {
		return surfaceReadingUnit;
	}
	public void setSurfaceReadingUnit(String surfaceReadingUnit) {
		this.surfaceReadingUnit = surfaceReadingUnit;
	}
	public String getBarHoleReading() {
		return barHoleReading;
	}
	public void setBarHoleReading(String barHoleReading) {
		this.barHoleReading = barHoleReading;
	}
	public String getBarholeReadingUnit() {
		return barholeReadingUnit;
	}
	public void setBarholeReadingUnit(String barholeReadingUnit) {
		this.barholeReadingUnit = barholeReadingUnit;
	}
	public String getLeakType() {
		return leakType;
	}
	public void setLeakType(String leakType) {
		this.leakType = leakType;
	}
	public String getLeakGrade() {
		return leakGrade;
	}
	public void setLeakGrade(String leakGrade) {
		this.leakGrade = leakGrade;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public String getPipeMaterialType() {
		return pipeMaterialType;
	}
	public void setPipeMaterialType(String pipeMaterialType) {
		this.pipeMaterialType = pipeMaterialType;
	}
	public String getSurfaceOverleakType() {
		return surfaceOverleakType;
	}
	public void setSurfaceOverleakType(String surfaceOverleakType) {
		this.surfaceOverleakType = surfaceOverleakType;
	}
	public String getMeterNum() {
		return meterNum;
	}
	public void setMeterNum(String meterNum) {
		this.meterNum = meterNum;
	}
	public String getLocationRemarks() {
		return locationRemarks;
	}
	public void setLocationRemarks(String locationRemarks) {
		this.locationRemarks = locationRemarks;
	}
	public Boolean getIsPavedWallToWall() {
		return isPavedWallToWall;
	}
	public void setIsPavedWallToWall(Boolean isPavedWallToWall) {
		this.isPavedWallToWall = isPavedWallToWall;
	}
	public String getAdditionalNotes() {
		return additionalNotes;
	}
	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}

	public int compareTo(LeakInfoEntity other) {
		return CompareToBuilder.reflectionCompare(this, other);
	}
}
