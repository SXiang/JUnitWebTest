package surveyor.scommon.mobile.source;

import java.util.HashMap;
import java.util.Map;

import surveyor.dataprovider.DataGenerator;
import surveyor.dataprovider.DataGenerator.Address;
import surveyor.scommon.mobile.source.LeakDataTypes.LeakLocationType;
import surveyor.scommon.mobile.source.LeakDataTypes.LeakPipeMaterialType;
import surveyor.scommon.mobile.source.LeakDataTypes.LeakSourceType;
import surveyor.scommon.mobile.source.LeakDataTypes.LeakType;
import surveyor.scommon.mobile.source.LeakDataTypes.ReadingUnitType;
import surveyor.scommon.mobile.source.LeakDataTypes.SurfaceOverLeakType;

public class LeakDataGenerator {

	public static class DataKey {
		public static final String USE_CURRENT_LOCATION = "USE_CURRENT_LOCATION";
		public static final String LEAK_SOURCE_TYPE = "LEAK_SOURCE_TYPE";
		public static final String LEAK_LOCATION_TYPE = "LEAK_LOCATION_TYPE";
		public static final String LEAK_TYPE = "LEAK_TYPE";
		public static final String SURFACE_READING_UNIT = "SURFACE_READING_UNIT";
		public static final String BARHOLE_READING_UNIT = "BARHOLE_READING_UNIT";
		public static final String SURFACE_OVERLEAK_TYPE = "SURFACE_OVERLEAK_TYPE";
		public static final String PIPE_MATERIAL_TYPE = "PIPE_MATERIAL_TYPE";
		public static final String STREET_NUMBER = "STREET_NUMBER";
		public static final String STREET_NAME = "STREET_NAME";
		public static final String APARTMENT_NUMBER = "APARTMENT_NUMBER";
		public static final String CITY = "CITY";
		public static final String STATE = "STATE";
		public static final String MAP_NUMBER = "MAP_NUMBER";
		public static final String SURFACE_READING = "SURFACE_READING";
		public static final String BARHOLE_READING = "BARHOLE_READING";
		public static final String LEAK_GRADE = "LEAK_GRADE";
		public static final String METER_NUMBER = "METER_NUMBER";
		public static final String LOCATION_REMARKS = "LOCATION_REMARKS";
		public static final String ADDITIONAL_NOTES = "ADDITIONAL_NOTES";
		public static final String IS_PAVED_WALL2WALL = "IS_PAVED_WALL2WALL";
	}

	public static LeakDataBuilder newBuilder() {
		return new LeakDataBuilder();
	}

	public static class LeakDataBuilder {
		private LeakSourceType sourceType;
		private LeakLocationType locationType;
		private LeakType leakType;
		private ReadingUnitType surfaceReadingUnit;
		private ReadingUnitType barholeReadingUnit;
		private SurfaceOverLeakType surfaceOverLeakType;
		private LeakPipeMaterialType pipeMaterialType;
		private String streetNumber;
		private String streetName;
		private String aptNumber;
		private String city;
		private String state;
		private String mapNumber;
		private String surfaceReading;
		private String barholeReading;
		private String leakGrade;
		private String meterNumber;
		private String locationRemarks;
		private String additionalNotes;
		private Boolean isPavedWallToWall;
		private Boolean useCurrentLocation;

		public LeakDataBuilder generateDefaultValues() {
			Address address = DataGenerator.getAddress();
			this.setSourceType(LeakSourceType.Gas)
				.setUseCurrentLocation(true)
			 	.setStreetNumber(address.getStreetNumber())
			 	.setAptNumber(DataGenerator.getNumberBetween(1, 999).toString())
			 	.setStreetName(address.getStreetName())
			 	.setCity(address.getCity())
			 	.setState(address.getState())
			 	.setMapNumber(DataGenerator.getNumberBetween(1, 999).toString())
			 	.setSurfaceReading(DataGenerator.getNumberBetween(1, 999).toString())
			 	.setSurfaceReadingUnit(ReadingUnitType.PPM)
			 	.setBarholeReading(DataGenerator.getNumberBetween(1, 999).toString())
			 	.setBarholeReadingUnit(ReadingUnitType.PPM)
			 	.setLeakType(LeakType.Above_Ground)
			 	.setLeakGrade(DataGenerator.getNumberBetween(1, 999).toString())
			 	.setLocationType(LeakLocationType.Service)
			 	.setPipeMaterialType(LeakPipeMaterialType.CastIron)
			 	.setIsPavedWallToWall(true)
			 	.setSurfaceOverLeakType(SurfaceOverLeakType.Concrete)
			 	.setMeterNumber(DataGenerator.getNumberBetween(1, 999).toString())
			 	.setLocationRemarks(DataGenerator.getRandomText(20, 100))
			 	.setAdditionalNotes(DataGenerator.getRandomText(20, 100));
			return this;
		}

		public LeakDataBuilder setGeneratedValues() {
			return null;
		}

		public LeakSourceType getSourceType() {
			return sourceType;
		}

		public LeakDataBuilder setSourceType(LeakSourceType sourceType) {
			this.sourceType = sourceType;
			return this;
		}

		public LeakLocationType getLocationType() {
			return locationType;
		}

		public LeakDataBuilder setLocationType(LeakLocationType locationType) {
			this.locationType = locationType;
			return this;
		}

		public LeakType getLeakType() {
			return leakType;
		}

		public LeakDataBuilder setLeakType(LeakType leakType) {
			this.leakType = leakType;
			return this;
		}

		public ReadingUnitType getSurfaceReadingUnit() {
			return surfaceReadingUnit;
		}

		public LeakDataBuilder setSurfaceReadingUnit(ReadingUnitType surfaceReadingUnit) {
			this.surfaceReadingUnit = surfaceReadingUnit;
			return this;
		}

		public ReadingUnitType getBarholeReadingUnit() {
			return barholeReadingUnit;
		}

		public LeakDataBuilder setBarholeReadingUnit(ReadingUnitType barholeReadingUnit) {
			this.barholeReadingUnit = barholeReadingUnit;
			return this;
		}

		public SurfaceOverLeakType getSurfaceOverLeakType() {
			return surfaceOverLeakType;
		}

		public LeakDataBuilder setSurfaceOverLeakType(SurfaceOverLeakType surfaceOverLeakType) {
			this.surfaceOverLeakType = surfaceOverLeakType;
			return this;
		}

		public LeakPipeMaterialType getPipeMaterialType() {
			return pipeMaterialType;
		}

		public LeakDataBuilder setPipeMaterialType(LeakPipeMaterialType pipeMaterialType) {
			this.pipeMaterialType = pipeMaterialType;
			return this;
		}

		public String getStreetNumber() {
			return streetNumber;
		}

		public LeakDataBuilder setStreetNumber(String streetNumber) {
			this.streetNumber = streetNumber;
			return this;
		}

		public String getStreetName() {
			return streetName;
		}

		public LeakDataBuilder setStreetName(String streetName) {
			this.streetName = streetName;
			return this;
		}

		public String getAptNumber() {
			return aptNumber;
		}

		public LeakDataBuilder setAptNumber(String aptNumber) {
			this.aptNumber = aptNumber;
			return this;
		}

		public String getCity() {
			return city;
		}

		public LeakDataBuilder setCity(String city) {
			this.city = city;
			return this;
		}

		public String getState() {
			return state;
		}

		public LeakDataBuilder setState(String state) {
			this.state = state;
			return this;
		}

		public String getMapNumber() {
			return mapNumber;
		}

		public LeakDataBuilder setMapNumber(String mapNumber) {
			this.mapNumber = mapNumber;
			return this;
		}

		public String getSurfaceReading() {
			return surfaceReading;
		}

		public LeakDataBuilder setSurfaceReading(String surfaceReading) {
			this.surfaceReading = surfaceReading;
			return this;
		}

		public String getBarholeReading() {
			return barholeReading;
		}

		public LeakDataBuilder setBarholeReading(String barholeReading) {
			this.barholeReading = barholeReading;
			return this;
		}

		public String getLeakGrade() {
			return leakGrade;
		}

		public LeakDataBuilder setLeakGrade(String leakGrade) {
			this.leakGrade = leakGrade;
			return this;
		}

		public String getMeterNumber() {
			return meterNumber;
		}

		public LeakDataBuilder setMeterNumber(String meterNumber) {
			this.meterNumber = meterNumber;
			return this;
		}

		public String getLocationRemarks() {
			return locationRemarks;
		}

		public LeakDataBuilder setLocationRemarks(String locationRemarks) {
			this.locationRemarks = locationRemarks;
			return this;
		}

		public String getAdditionalNotes() {
			return additionalNotes;
		}

		public LeakDataBuilder setAdditionalNotes(String additionalNotes) {
			this.additionalNotes = additionalNotes;
			return this;
		}

		public Boolean getIsPavedWallToWall() {
			return isPavedWallToWall;
		}

		public LeakDataBuilder setIsPavedWallToWall(Boolean isPavedWallToWall) {
			this.isPavedWallToWall = isPavedWallToWall;
			return this;
		}

		public Boolean getUseCurrentLocation() {
			return useCurrentLocation;
		}

		public LeakDataBuilder setUseCurrentLocation(Boolean useCurrentLocation) {
			this.useCurrentLocation = useCurrentLocation;
			return this;
		}

		public Map<String, Object> toMap() {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(DataKey.LEAK_SOURCE_TYPE, getSourceType());
			map.put(DataKey.LEAK_LOCATION_TYPE, getLocationType());
			map.put(DataKey.LEAK_TYPE, getLeakType());
			map.put(DataKey.SURFACE_READING_UNIT, getSurfaceReadingUnit());
			map.put(DataKey.BARHOLE_READING_UNIT, getBarholeReadingUnit());
			map.put(DataKey.SURFACE_OVERLEAK_TYPE, getSurfaceOverLeakType());
			map.put(DataKey.PIPE_MATERIAL_TYPE, getPipeMaterialType());
			map.put(DataKey.STREET_NUMBER, getStreetNumber());
			map.put(DataKey.STREET_NAME, getStreetName());
			map.put(DataKey.APARTMENT_NUMBER, getAptNumber());
			map.put(DataKey.CITY, getCity());
			map.put(DataKey.STATE, getState());
			map.put(DataKey.MAP_NUMBER, getMapNumber());
			map.put(DataKey.SURFACE_READING, getSurfaceReading() );
			map.put(DataKey.BARHOLE_READING, getBarholeReading() );
			map.put(DataKey.LEAK_GRADE, getLeakGrade());
			map.put(DataKey.METER_NUMBER, getMeterNumber());
			map.put(DataKey.LOCATION_REMARKS, getLocationRemarks());
			map.put(DataKey.ADDITIONAL_NOTES, getAdditionalNotes());
			map.put(DataKey.IS_PAVED_WALL2WALL, getIsPavedWallToWall());
			map.put(DataKey.USE_CURRENT_LOCATION, getUseCurrentLocation());
			return map;
		}
	}
}