/**
 *
 */
package surveyor.scommon.entities;

import java.util.ArrayList;
import java.util.List;

import surveyor.scommon.source.ReportInvestigationsPage.IndicationStatus;

public class LeakDetailEntity extends InvestigationEntity{
		private String streetNumber;
		private String apartmentNumber;
		private String streetName;
		private String city;
		private String state;
		private String mapNumber;
		private String surfaceReading;
		private String surfaceReadingUnit;
		private String barholeReading;
		private String barholeReadingUnit;
		private String leakType;
		private String leakGrade;
		private String leakLocationType;
		private String pipeMaterialType;
		private boolean pavedWallToWall;
		private String surfaceOverLeak;
		private String meterNumber;
		private String leakLocationRemarks;
		private String latitude;
		private String longitude;
		
		private String reportId="null";
		private String reportName="null";
		private String totalInvestigationDuration="null";
		private String foundDateTime="null";

		public static enum LeakLocation {
			OTHER ("Other"),
			MAIN ("Main"),
			SERVICE ("Service"),
			SERVICET ("Service T"),
			SERVICEBRANCH("Service Branch"),
			RISER ("Riser"),
			METERSET ("Meter Set"),
			CUSTOMEREQUIPMENT ("Customer Equipment"),
			SEWERMANHOLE ("Sewer Manhole"),
			CATCHBASIN ("Catch Basin"),
			SUBSTRUCTURE ("Substructure");

			private String location;
			LeakLocation(String location){
				this.location = location;
			}
			public String toString(){
				return location;
			}
		};

		public static enum ReadingUnit {
			PPM ("PPM"),
			LEL ("LEL"),
			PERCENTGAS ("% Gas");
			private String unit;
			ReadingUnit(String unit){
				this.unit = unit;
			}
			public String toString(){
				return unit;
			}
		};

		public static enum SurfaceOverLeak {
			ABOVEGROUND ("Above Ground"),
		    CONCRETE ("Concrete"),
			UNSURFACE ("Un surfaced"),
			TARCOMPONENT ("Tar Component"),
			INSUBSTRUCTURE ("In Substructure"),
			OTHER ("Other");

			private String surface;
			SurfaceOverLeak(String surface){
				this.surface = surface;
			}
			public String toString(){
				return surface;
			}
		};

		public static enum LeakType {
			ABOVEGROUND ("Above Ground"),
		    BELOWGROUND ("Below Ground");

			private String type;
			LeakType(String type){
				this.type = type;
			}
			public String toString(){
				return type;
			}
		};

		public enum LeakPipeMaterialType {
			CastIron ("Cast Iron"),
			Copper ("Copper"),
			OtherPlastic ("Other Plastic"),
			PEPlastic ("PE Plastic"),
			ProtectedSteel ("Protected Steel"),
			UnprotectedSteel ("Un-protected Steel");

			private final String name;

			LeakPipeMaterialType(String nm) {
				name = nm;
			}

			public String toString() {
				return this.name;
			}
		}

		public LeakDetailEntity(String userName, int indicationNumber){
			setUserName(userName);
			setIndicationNumber(indicationNumber);
		}

		public void setDefaultTestData(){
			super.setDefaultTestData();
			setLeakSourceType("Gas");
			streetNumber = "3105";
			apartmentNumber = "1";
			streetName = "Patrick Henry Dr";
			city = "Santa Clara";
			state = "CA";
			mapNumber = "1";
			surfaceReading = "1";
			surfaceReadingUnit = ReadingUnit.PPM.toString();
			barholeReading = "2";
			barholeReadingUnit = ReadingUnit.PPM.toString();;
			leakType = LeakType.ABOVEGROUND.toString();
			leakGrade = "2";
			leakLocationType = LeakLocation.SERVICE.toString();;
			pipeMaterialType = LeakPipeMaterialType.CastIron.toString();
			pavedWallToWall =  true;
			surfaceOverLeak = SurfaceOverLeak.CONCRETE.toString();;
			meterNumber = "9";
			leakLocationRemarks = "SQAAuto test remarks";
		}

		public void modifyTestData(){
			streetNumber = "3105_1";
			apartmentNumber = "1_1";
			streetName = "Patrick Henry Dr_1";
			city = "Santa Clara_1";
			state = "CA";
			mapNumber = "2";
			surfaceReading = "1";
			surfaceReadingUnit = ReadingUnit.PERCENTGAS.toString();;
			barholeReading = "2";
			barholeReadingUnit = ReadingUnit.LEL.toString();;
			leakType = LeakType.BELOWGROUND.toString();;
			leakGrade = "3";
			leakLocationType = LeakLocation.METERSET.toString();
			pipeMaterialType = LeakPipeMaterialType.PEPlastic.toString();;
			pavedWallToWall =  false;
			surfaceOverLeak = SurfaceOverLeak.TARCOMPONENT.toString();
			meterNumber = "10";
			leakLocationRemarks = "SQAAuto test remarks - modified";
		}
		public String getStreetNumber() {
			return streetNumber;
		}
		public void setStreetNumber(String streetNumber) {
			this.streetNumber = streetNumber;
		}
		public String getApartmentNumber() {
			return apartmentNumber;
		}
		public void setApartmentNumber(String apartmentNumber) {
			this.apartmentNumber = apartmentNumber;
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
		public String getMapNumber() {
			return mapNumber;
		}
		public void setMapNumber(String mapNumber) {
			this.mapNumber = mapNumber;
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
		public String getBarholeReading() {
			return barholeReading;
		}
		public void setBarholeReading(String barholeReading) {
			this.barholeReading = barholeReading;
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
		public String getLeakLocationType() {
			return leakLocationType;
		}
		public void setLeakLocationType(String leakLocationType) {
			this.leakLocationType = leakLocationType;
		}
		public String getPipeMaterialType() {
			return pipeMaterialType;
		}
		public void setPipeMaterialType(String pipeMaterialType) {
			this.pipeMaterialType = pipeMaterialType;
		}
		public boolean isPavedWallToWall() {
			return pavedWallToWall;
		}
		public void setPavedWallToWall(boolean pavedWallToWall) {
			this.pavedWallToWall = pavedWallToWall;
		}
		public String getSurfaceOverLeak() {
			return surfaceOverLeak;
		}
		public void setSurfaceOverLeak(String surfaceOverLeak) {
			this.surfaceOverLeak = surfaceOverLeak;
		}
		public String getMeterNumber() {
			return meterNumber;
		}
		public void setMeterNumber(String meterNumber) {
			this.meterNumber = meterNumber;
		}
		public String getLeakLocationRemarks() {
			return leakLocationRemarks;
		}
		public void setLeakLocationRemarks(String leakLocationRemarks) {
			this.leakLocationRemarks = leakLocationRemarks;
		}
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

		@Override
		public void setInvestigationStatus(boolean completed) {
				if(completed){
					if(isSourceAdded()){
						this.investigationStatus = IndicationStatus.FOUNDGASLEAK.toString();//"Found Gas Leak";
					}else if(isSourceConfirmed()){
						this.investigationStatus = IndicationStatus.FOUNDOTHERSOURCE.toString(); //"Found Other Source";
					}else{
						this.investigationStatus = IndicationStatus.NOGASFOUND.toString(); //"Leak Not Found";
					}
				}else{
					this.investigationStatus = IndicationStatus.INPROGRESS.toString();//"In Progress";
				}
		}

		public void setInvestigationStatus(IndicationStatus indicationStatus) {
			this.investigationStatus = indicationStatus.toString();
		}

		public List<String> toPDFLeakDetails(){
			List<String> leakDetails = new ArrayList<String>();
			String header = getIndicationNumber()+" "+investigationStatus+" .* "+userName+" [0-9]+ days [0-9]{2}:[0-9]{2}:[0-9]{2}";
			String line1 = "Source: "+leakSourceType+" Date/Time: .*";
			String line2 = "Investigator: "+userName+"Latitude: (\\d+\\.\\d+)?, Longitude: \\-?(\\d+\\.\\d+)?, Precison: (.*)m";
			String line3 = "Leak Grade: "+leakGrade+"Address: "+
						streetNumber+" "+streetName+", ("+apartmentNumber+"+, )?"+city+", "+state;
			String line4 = "Map Number: "+mapNumber+"Surface Reading: "+surfaceReading+" \\("+surfaceReadingUnit+"\\)";
			String line5 = "Barhole Reading: "+barholeReading+" \\("+barholeReadingUnit+"\\)";
			String line6 = "Paved Wall-To-Wall: "+pavedWallToWall;
			String line7 = "Pipe Material Type: "+pipeMaterialType+"Leak Location: "+leakLocationType;
			String line8 = "Surface Over Leak: "+surfaceOverLeak;
			String line9 = "Meter Number: "+meterNumber;
			String line10 = "Leak Location Remarks: "+leakLocationRemarks;
			String line11 = "Additional Notes: "+additionalNotes;
			leakDetails.add(header);
			leakDetails.add(line1);
			leakDetails.add(line2);
			leakDetails.add(line3);
			leakDetails.add(line4);
			leakDetails.add(line5);
			leakDetails.add(line6);
			leakDetails.add(line7);
			leakDetails.add(line8);
			leakDetails.add(line9);
			leakDetails.add(line10);
			leakDetails.add(line11);

			return leakDetails;
		}

		public String[][] toCSVLeakDetails(){
			return toCSVLeakDetails(true /*ignoreLocation*/);
		}

		public String[][] toCSVLeakDetails(boolean ignoreLocation){
			String[][] csvRow ={
					{boxType+"Number", "ReportId", "ReportName", "InvestigationStatus", "TotalInvestigationDuration", 
					"Investigator", "FoundDateTime","LeakCoordinates", "GpsPrecision", "LeakType", 
					"AddressStreetNumber", "AddressApartmentNumber", "AddressStreetName", "AddressCity","AddressState",
					"MapNumber", "SurfaceReading", "BarholeReading", "LeakGrade", "LeakLocationType", 
					"PipeMaterialType", "IsPavedWallToWall", "SurfaceOverLeakType","MeterNumber", "LocationRemarks", 
					"Notes", "LeakSourceType"},
					{boxType+" "+indicationNumber, reportId, reportName, investigationStatus, totalInvestigationDuration, 
					userName, foundDateTime, ignoreLocation ? "" : latitude+","+longitude, "m", leakType,
					streetNumber, apartmentNumber, streetName, city, state, 
					mapNumber, surfaceReading+" ("+surfaceReadingUnit+")", barholeReading+" ("+barholeReadingUnit+")", leakGrade, leakLocationType, 
					pipeMaterialType, String.valueOf(pavedWallToWall), surfaceOverLeak, meterNumber, leakLocationRemarks, 
					additionalNotes, leakSourceType}
			};
			return csvRow;
		}
	}