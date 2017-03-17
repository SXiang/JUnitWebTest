/**
 *
 */
package surveyor.scommon.entities;

import surveyor.scommon.source.ReportInvestigationsPage.IndicationStatus;

public class OtherSourceEntity extends InvestigationEntity{
	public OtherSourceEntity(String userName, int indicationNumber){
		this("LISA", userName, indicationNumber);
	}
	public OtherSourceEntity(String boxType, String userName, int indicationNumber){
		setBoxType(boxType);
		setUserName(userName);
		setIndicationNumber(indicationNumber);
	}
	
	public void setDefaultTestData(){
		super.setDefaultTestData();
		setLeakSourceType("Landfill");
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