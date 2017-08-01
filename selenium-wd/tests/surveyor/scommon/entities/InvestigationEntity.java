/**
 *
 */
package surveyor.scommon.entities;

import surveyor.scommon.source.ReportInvestigationsPage.IndicationStatus;

public abstract class InvestigationEntity {
	protected String leakSourceType;
	protected String boxType;
	protected String investigationTime = "";
	protected String userName = "";
	protected int indicationNumber;
	public String investigationStatus;
	protected String additionalNotes;
	protected boolean sourceAdded;
	protected boolean sourceConfirmed;

	
	public String getBoxType() {
		return boxType;
	}
	public void setBoxType(String boxType) {
		this.boxType = boxType;
	}
	
	public void setDefaultTestData(){
		investigationStatus = IndicationStatus.NOTINVESTIGATED.toString();
		additionalNotes = "SQAAuto test notes";
	}

	
	public boolean isSourceConfirmed() {
		return sourceConfirmed;
	}
	public void setSourceConfirmed(boolean sourceConfirmed) {
		this.sourceConfirmed = sourceConfirmed;
	}
	public boolean isSourceAdded() {
		return sourceAdded;
	}
	public void setSourceAdded(boolean sourceAdded) {
		this.sourceAdded = sourceAdded;
	}
	public String getAdditionalNotes() {
		return additionalNotes;
	}
	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}
	public String getLeakSourceType() {
		return leakSourceType;
	}
	public void setLeakSourceType(String leakSourceType) {
		this.leakSourceType = leakSourceType;
	}
	public String getInvestigationTime() {
		return investigationTime;
	}
	public void setInvestigationTime(String investigationTime) {
		this.investigationTime = investigationTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getIndicationNumber() {
		return indicationNumber;
	}
	public void setIndicationNumber(int indicationNumber) {
		this.indicationNumber = indicationNumber;
	}
	public String getInvestigationStatus() {
		return investigationStatus;
	}
	public abstract void setInvestigationStatus(boolean completed);

}