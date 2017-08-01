/**
 *
 */
package surveyor.scommon.entities;

import surveyor.scommon.source.ReportInvestigationsPage.IndicationStatus;

public class OtherSourceEntity extends InvestigationEntity{
	public static enum OtherLeakSourceType {
		OTHERNATURALSOURCE ("Other Natural Source"),
		SEWER ("Sewer"),
		CATCHBASIN ("Catch Basin"),
		LANDFILL ("Landfill"),
		SWAMP("Swamp"),
		CUSTOMER ("Customer"),
		OTHERENCLOSURE ("Other Enclosure");
		
		private String type;
		OtherLeakSourceType(String type){
			this.type = type;
		}
		public String toString(){
			return type;
		}
	};
	
	public OtherSourceEntity(String userName, int indicationNumber){
		setUserName(userName);
		setIndicationNumber(indicationNumber);
	}
	
	public void setDefaultTestData(){
		super.setDefaultTestData();
		setLeakSourceType(OtherLeakSourceType.LANDFILL.toString());
	}
	
	public void modifyTestData(){
		setLeakSourceType(OtherLeakSourceType.OTHERENCLOSURE.toString());
	}
	
	@Override
	public void setInvestigationStatus(boolean completed) {
			if(completed){
				if(isSourceAdded()){
					this.investigationStatus = IndicationStatus.FOUNDOTHERSOURCE.toString();//"Found Other Source";
				}else{
					this.investigationStatus = IndicationStatus.NOGASFOUND.toString();//"Leak Not Found";
				}
			}else{
				this.investigationStatus = IndicationStatus.INPROGRESS.toString();//"In Progress";
			}
	}
}