package surveyor.scommon.mobile.source;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import common.source.BaseHelper;
import common.source.EnumUtility;
import common.source.FileUtility;
import surveyor.dataprovider.DataGenerator;
import surveyor.dataprovider.DataGenerator.Address;
import surveyor.scommon.entities.LeakDetailEntity;
import surveyor.scommon.mobile.source.LeakDataTypes.IndicationType;
import surveyor.scommon.mobile.source.LeakDataTypes.LeakLocationType;
import surveyor.scommon.mobile.source.LeakDataTypes.LeakPipeMaterialType;
import surveyor.scommon.mobile.source.LeakDataTypes.LeakSourceType;
import surveyor.scommon.mobile.source.LeakDataTypes.LeakType;
import surveyor.scommon.mobile.source.LeakDataTypes.ReadingUnitType;
import surveyor.scommon.mobile.source.LeakDataTypes.SurfaceOverLeakType;
import surveyor.scommon.source.ReportInvestigationsPage.IndicationStatus;

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
			 	.setLocationRemarks(DataGenerator.getRandomText(20, 200))
			 	.setAdditionalNotes(DataGenerator.getRandomText(20, 200));
			return this;
		}

		public LeakDataBuilder generateRandomValuesWithNulls() {
			Address address = DataGenerator.getAddress();

			int randInt = new Random().nextInt(1000) + 1;

			Boolean valueSet = false;
			if (randInt % 2 == 0) {
				this.setUseCurrentLocation(true);
				this.setStreetName(address.getStreetName());
				this.setSurfaceReading(DataGenerator.getNumberBetween(1, 999).toString());
				this.setIsPavedWallToWall(true);
				this.setLeakType(LeakType.Above_Ground);
				valueSet = true;
			}

			if (randInt % 3 == 0) {
				this.setCity(address.getCity());
				this.setSurfaceReadingUnit(ReadingUnitType.PPM);
				this.setSurfaceOverLeakType(SurfaceOverLeakType.Concrete);
				this.setLeakGrade(DataGenerator.getNumberBetween(1, 999).toString());
				valueSet = true;
			}

			if (randInt % 4 == 0) {
				this.setStreetNumber(address.getStreetNumber());
				this.setState(address.getState());
				this.setBarholeReading(DataGenerator.getNumberBetween(1, 999).toString());
				this.setLocationType(LeakLocationType.Service);
				this.setMeterNumber(DataGenerator.getNumberBetween(1, 999).toString());
				valueSet = true;
			}

			if (randInt % 5 == 0) {
				this.setAptNumber(DataGenerator.getNumberBetween(1, 999).toString());
				this.setMapNumber(DataGenerator.getNumberBetween(1, 999).toString());
				this.setBarholeReadingUnit(ReadingUnitType.PPM);
				this.setPipeMaterialType(LeakPipeMaterialType.CastIron);
				// TBD: Increase the text length to be greater than 250 post product defect DE3384 is fixed
				this.setLocationRemarks(DataGenerator.getRandomText(200, 249));
				valueSet = true;
			}

			if (!valueSet || (randInt % 6 == 0)) {
				// TBD: Increase the text length to be greater than 250 post product defect DE3384 is fixed
				this.setAdditionalNotes(DataGenerator.getRandomText(200, 249));
			}

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

		public LeakDetailEntity toLeakDetailEntity(LeakSourceType leakSourceType, IndicationStatus indicationStatus, IndicationType indicationType, Integer indicationNumber, String username,
				String latitude, String longitude) {
			LeakDetailEntity entity = new LeakDetailEntity(username, indicationNumber);
			entity.setStreetNumber(this.getStreetNumber());
			entity.setApartmentNumber(this.getAptNumber());
			entity.setStreetName(this.getStreetName());
			entity.setCity(this.getCity());
			entity.setState(this.getState());
			entity.setMapNumber(this.getMapNumber());
			entity.setSurfaceReading(this.getSurfaceReading());
			entity.setSurfaceReadingUnit(this.getSurfaceReadingUnit().toString());
			entity.setBarholeReading(this.getBarholeReading());
			entity.setBarholeReadingUnit(this.getBarholeReadingUnit().toString());
			entity.setLeakType(this.getLeakType().toString());
			entity.setLeakGrade(this.getLeakGrade());
			entity.setLeakLocationType(this.getLocationType().toString());
			entity.setPipeMaterialType(this.getPipeMaterialType().toString());
			entity.setPavedWallToWall(this.getIsPavedWallToWall());
			entity.setSurfaceOverLeak(this.getSurfaceOverLeakType().toString());
			entity.setMeterNumber(this.getMeterNumber());
			entity.setLeakLocationRemarks(this.getLocationRemarks());
			entity.setBoxType(indicationType.toString());
			entity.setLeakSourceType(leakSourceType.toString());
			entity.setInvestigationStatus(indicationStatus);
			entity.setAdditionalNotes(getAdditionalNotes());
			entity.setLatitude(latitude);
			entity.setLongitude(longitude);
			return entity;
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

		public void toFile(String filePath) throws IOException {
			StringBuilder builder = new StringBuilder();
			Map<String, Object> valuesMap = toMap();
			for (String key : valuesMap.keySet()) {
				builder.append(String.format("%s = %s", key, valuesMap.get(key)));
				builder.append(BaseHelper.getLineSeperator());
			}

			if (FileUtility.fileExists(filePath)) {
				FileUtility.deleteFile(Paths.get(filePath));
			}

			FileUtility.writeToFile(filePath, builder.toString());
		}

		public void fromFile(String filePath) throws IOException {
			List<String> fileLines = FileUtility.readFileLinesToList(filePath);
			for (String line: fileLines) {
				int idx = line.indexOf('=');
				String key = line.substring(0, idx).trim();
				String value = line.substring(idx+1, line.length()).trim();
				if (key.equals(DataKey.LEAK_SOURCE_TYPE)) { setSourceType(EnumUtility.fromName(value, () -> LeakSourceType.values()));  }
				else if (key.equals(DataKey.LEAK_LOCATION_TYPE)) { setLocationType(EnumUtility.fromName(value, () -> LeakLocationType.values())); }
				else if (key.equals(DataKey.LEAK_TYPE)) { setLeakType(EnumUtility.fromName(value, () -> LeakType.values())); }
				else if (key.equals(DataKey.SURFACE_READING_UNIT)) { setSurfaceReadingUnit(EnumUtility.fromName(value, () -> ReadingUnitType.values())); }
				else if (key.equals(DataKey.BARHOLE_READING_UNIT)) { setBarholeReadingUnit(EnumUtility.fromName(value, () -> ReadingUnitType.values())); }
				else if (key.equals(DataKey.SURFACE_OVERLEAK_TYPE)) { setSurfaceOverLeakType(EnumUtility.fromName(value, () -> SurfaceOverLeakType.values())); }
				else if (key.equals(DataKey.PIPE_MATERIAL_TYPE)) { setPipeMaterialType(EnumUtility.fromName(value, () -> LeakPipeMaterialType.values())); }
				else if (key.equals(DataKey.STREET_NUMBER)) { setStreetNumber(value); }
				else if (key.equals(DataKey.STREET_NAME)) { setStreetName(value); }
				else if (key.equals(DataKey.APARTMENT_NUMBER)) { setAptNumber(value); }
				else if (key.equals(DataKey.CITY)) { setCity(value); }
				else if (key.equals(DataKey.STATE)) { setState(value); }
				else if (key.equals(DataKey.MAP_NUMBER)) { setMapNumber(value); }
				else if (key.equals(DataKey.SURFACE_READING)) { setSurfaceReading(value); }
				else if (key.equals(DataKey.BARHOLE_READING)) { setBarholeReading(value); }
				else if (key.equals(DataKey.LEAK_GRADE)) { setLeakGrade(value); }
				else if (key.equals(DataKey.METER_NUMBER)) { setMeterNumber(value); }
				else if (key.equals(DataKey.LOCATION_REMARKS)) { setLocationRemarks(value); }
				else if (key.equals(DataKey.ADDITIONAL_NOTES)) { setAdditionalNotes(value); }
				else if (key.equals(DataKey.IS_PAVED_WALL2WALL)) { setIsPavedWallToWall(Boolean.valueOf(value)); }
				else if (key.equals(DataKey.USE_CURRENT_LOCATION)) { setUseCurrentLocation(Boolean.valueOf(value)); }
			}
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
		}
	}
}