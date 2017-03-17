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

		public LeakDetailEntity(String userName, int indicationNumber){
			this("LISA", userName, indicationNumber);
		}
		public LeakDetailEntity(String boxType, String userName, int indicationNumber){
			setBoxType(boxType);
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
			surfaceReadingUnit = "PPM";
			barholeReading = "2";
			barholeReadingUnit = "PPM";
			leakType = "Above Ground";
			leakGrade = "2";
			leakLocationType = "Service";
			pipeMaterialType = "Any";
			pavedWallToWall =  true;
			surfaceOverLeak = "Concrete";
			meterNumber = "9";
			leakLocationRemarks = "SQAAuto test remarks";
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
		
		public List<String> toPDFLeakDetails(){
			List<String> leakDetails = new ArrayList<String>();
			String header = getIndicationNumber()+" "+investigationStatus+" .* "+userName+" [0-9]+ days [0-9]{2}:[0-9]{2}:[0-9]{2}";
			String line1 = "Source: "+leakSourceType+" Date/Time: .*";
			String line2 = "Investigator: "+userName+"Latitude: , Longitude: , Precison: m";
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
			String[][] csvRow ={
					{boxType+"Number", "InvestigationStatus", "Investigator", "LeakCoordinates", "GpsPrecision",
					"LeakType", "AddressStreetNumber", "AddressApartmentNumber", "AddressStreetName", "MapNumber",
					"SurfaceReading", "BarholeReading", "LeakGrade", "LeakLocationType", "PipeMaterialType",
					"IsPavedWallToWall", "SurfaceOverLeakType","MeterNumber", "LocationRemarks", "Notes",
					"LeakSourceType"},
					{boxType+" "+indicationNumber, investigationStatus, userName, "", "m",
					leakType, streetNumber, apartmentNumber, streetName, mapNumber,
					surfaceReading+" ("+surfaceReadingUnit+")", barholeReading+" ("+barholeReadingUnit+")", leakGrade, leakLocationType, pipeMaterialType,
					String.valueOf(pavedWallToWall), surfaceOverLeak, meterNumber, leakLocationRemarks, additionalNotes,
					leakSourceType}
			};
			return csvRow;
		}
	}