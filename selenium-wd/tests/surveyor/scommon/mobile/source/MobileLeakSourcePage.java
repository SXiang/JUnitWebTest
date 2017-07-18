package surveyor.scommon.mobile.source;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.source.Log;
import surveyor.scommon.entities.InvestigationEntity;
import surveyor.scommon.entities.LeakDetailEntity;
import surveyor.scommon.entities.OtherSourceEntity;

/**
 * @author sxiang
 *
 */

public class MobileLeakSourcePage extends MobileBasePage {

	public static final String STRURLPath = "/Investigation/Investigate?boxId=%s";

	@FindBy(how = How.CSS, using = "#leak_details input[ng-model='leak.AddressStreetNumber']")
	protected WebElement inputStreetNumber;
	private String streetNumber = "3105";
	
	@FindBy(how = How.CSS, using = "#leak_details input[ng-model='leak.AddressApartmentNumber']")
	protected WebElement inputApartmentNumber;

	@FindBy(how = How.CSS, using = "#leak_details input[ng-model='leak.AddressStreetName']")
	protected WebElement inputStreetName;

	@FindBy(how = How.CSS, using = "#leak_details input[ng-model='leak.AddressCity']")
	protected WebElement inputCity;

	@FindBy(how = How.CSS, using = "#leak_details input[ng-model='leak.AddressState']")
	protected WebElement inputState;

	@FindBy(how = How.CSS, using = "#leak_details input[ng-model='leak.MapNumber']")
	protected WebElement inputMapNumber;

	@FindBy(how = How.CSS, using = "#leak_details input[ng-model='leak.SurfaceReading']")
	protected WebElement inputSurfaceReading;

	@FindBy(how = How.CSS, using = "[preselected-item='leak.SurfaceReadingUnitTypeId'] button[data-toggle='dropdown']")
	protected WebElement surfaceReadingUnitDropdown;

	@FindBy(how = How.CSS, using = "#leak_details input[ng-model='leak.BarholeReading']")
	protected WebElement inputBarholeReading;

	@FindBy(how = How.CSS, using = "[preselected-item='leak.BarholeReadingUnitTypeId'] button[data-toggle='dropdown']")
	protected WebElement barholeReadingUnitDropdown;

	@FindBy(how = How.CSS, using = "[preselected-item='leak.LeakTypeId'] button[data-toggle='dropdown']")
	protected WebElement leakTypeDropdown;

	@FindBy(how = How.CSS, using = "#leak_details input[ng-model='leak.LeakGrade']")
	protected WebElement inputLeakGrade;

	@FindBy(how = How.CSS, using = "[preselected-item='leak.LeakLocationTypeId'] button[data-toggle='dropdown']")
	protected WebElement leakLocationTypeDropdown;

	@FindBy(how = How.CSS, using = "#leak_details input[ng-model='leak.PipeMaterialType']")
	protected WebElement inputPipeMaterialType;

	@FindBy(how = How.CSS, using = "#leak_details input[ng-model='leak.IsPavedWallToWall']")
	protected WebElement checkboxPaved;
	
	@FindBy(how = How.CSS, using = "[preselected-item='leak.SurfaceOverLeakTypeId'] button[data-toggle='dropdown']")
	protected WebElement surfaceOverLeakDropdown;

	@FindBy(how = How.CSS, using = "#leak_details input[ng-model='leak.MeterNumber']")
	protected WebElement inputMeterNumber;

	@FindBy(how = How.CSS, using = "#leak_details textarea[ng-model='leak.LocationRemarks']")
	protected WebElement textareaLeakLocationRemarks;

	@FindBy(how = How.CSS, using = "#leak_details textarea[ng-model='leak.Notes']")
	protected WebElement textareaAdditionalNotes;

	@FindBy(how = How.CSS, using = "[preselected-item='leak.LeakSourceTypeId'] button[data-toggle='dropdown']")
	protected WebElement leakSourceTypeDropdown;

	@FindBy(how = How.CSS, using = ".modal-dialog .modal-footer > a.btn[ng-click='ok()']")
	protected WebElement buttonOk;
	@FindBy(how = How.CSS, using = ".modal-dialog .modal-footer > a.btn[ng-click='cancel()']")
	protected WebElement buttonCancel;
	@FindBy(how = How.CSS, using = ".modal-dialog .modal-footer > a.btn[ng-click='delete()']")
	protected WebElement buttonDelete;
	@FindBy(how = How.CSS, using = ".modal-dialog .modal-footer > button[ng-click='modalOptions.ok()']")
	protected WebElement buttonConfirmDelete;
	
	@FindBy(how = How.CSS, using = ".modal-dialog .modal-header > button.close[ng-click='cancel()']")
	protected WebElement buttonCloseAddSource;

	@FindBy(how = How.CSS, using = ".modal-dialog .row .list-group-item-text")
	protected WebElement investigateTime;

	public enum InvestigateAction {DELETE, OK, CANCEL};
	
	public MobileLeakSourcePage(){
		super(STRURLPath);
		pageKey = By.cssSelector("#leak_details.form-group");
		Log.info("The LeakSource Page URL is: " + this.strPageURL);
	}

	public void addLeakDetails(LeakDetailEntity leakDetails){
		addLeakDetails(leakDetails, InvestigateAction.OK);
	}
	
	public void addLeakDetails(LeakDetailEntity leakDetails, InvestigateAction action){
		setStreetNumber(leakDetails.getStreetNumber());
		setApartmentNumber(leakDetails.getApartmentNumber());
		setStreetName(leakDetails.getStreetName());
		setCity(leakDetails.getCity());
		setState(leakDetails.getState());
		setMapNumber(leakDetails.getMapNumber());
		setSurfaceReading(leakDetails.getSurfaceReading());
		setSurfaceReadingUnit(leakDetails.getSurfaceReadingUnit());
		setBarholeReading(leakDetails.getBarholeReading());
		setBarholeReadingUnit(leakDetails.getBarholeReadingUnit());
		setLeakType(leakDetails.getLeakType());
		setLeakGrade(leakDetails.getLeakGrade());
		setLeakLocationType(leakDetails.getLeakLocationType());
		setPipeMaterialType(leakDetails.getPipeMaterialType());
		setPavedWallToWall(leakDetails.isPavedWallToWall());
		setSurfaceOverLeak(leakDetails.getSurfaceOverLeak());
		setMeterNumber(leakDetails.getMeterNumber());
		setLeakLocationRemarks(leakDetails.getLeakLocationRemarks());
		setAdditionalNotes(leakDetails.getAdditionalNotes());
		switch(action){
		case OK:
			clickOkButton(leakDetails);
			break;
		case DELETE:
			clickDeleteButton();
			break;
		case CANCEL:
			clickCancelButton();			
		}
	}

	public void addOtherSource(OtherSourceEntity sourceDetails){
		setLeakSourceType(sourceDetails.getLeakSourceType());
		setAdditionalNotes(sourceDetails.getAdditionalNotes());
		
		clickOkButton(sourceDetails);
	}

	public void setLeakSourceType(String leakSourceType) {
		selectDropdownItem(leakSourceTypeDropdown, leakSourceType);
	}

	public void clickOkButton(InvestigationEntity investigationEntity) {
		this.buttonOk.click();
		waitForPageToLoad();
		investigationEntity.setSourceAdded(true);
		investigationEntity.setInvestigationTime(getElementText(investigateTime));
	}


	public void clickCancelButton() {
		this.buttonCancel.click();
		waitForPageToLoad();
	}

	public void clickDeleteButton() {
		waitForElementToBeClickable(buttonDelete);
		this.buttonDelete.click();
		waitForElementToBeClickable(buttonConfirmDelete);
		this.buttonConfirmDelete.click();
		waitForPageToLoad();
	}
	
	public void closeAddSourceDialog(){
		buttonCloseAddSource.click();
		waitForPageToLoad();
	}


	/* Getter and Setter */
	public String getStreetNumber() {
		return streetNumber;
	}


	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
		inputStreetNumber.sendKeys(streetNumber);
	}

	public void setApartmentNumber(String apartmentNumber) {
		inputApartmentNumber.sendKeys(apartmentNumber);
	}

	public void setStreetName(String streetName) {
		inputStreetName.sendKeys(streetName);
	}

	public void setCity(String city) {
		inputCity.sendKeys(city);
	}

	public void setState(String state) {
		inputState.sendKeys(state);
	}

	public void setMapNumber(String mapNumber) {
		inputMapNumber.sendKeys(mapNumber);
	}

	public void setSurfaceReading(String surfaceReading) {
		inputSurfaceReading.sendKeys(surfaceReading);
	}

	public void setSurfaceReadingUnit(String surfaceReadingUnit) {
		selectDropdownItem(surfaceReadingUnitDropdown, surfaceReadingUnit);
	}

	public void setBarholeReading(String barholeReading) {
		inputBarholeReading.sendKeys(barholeReading);
	}

	public void setBarholeReadingUnit(String barholeReadingUnit) {
		selectDropdownItem(barholeReadingUnitDropdown, barholeReadingUnit);
	}

	public void setLeakType(String leakType) {
		selectDropdownItem(leakTypeDropdown, leakType);
	}

	public void setLeakGrade(String leakGrade) {
		inputLeakGrade.sendKeys(leakGrade);
	}

	public void setLeakLocationType(String leakLocationType) {
		selectDropdownItem(leakLocationTypeDropdown, leakLocationType);
	}

	public void setPipeMaterialType(String pipeMaterialType) {
		inputPipeMaterialType.sendKeys(pipeMaterialType);
	}

	public void setPavedWallToWall(boolean pavedWallToWall) {
		if(checkboxPaved.isSelected() != pavedWallToWall){
			checkboxPaved.click();
		}
	}

	public void setSurfaceOverLeak(String surfaceOverLeak) {
		selectDropdownItem(surfaceOverLeakDropdown, surfaceOverLeak);
	}

	public void setMeterNumber(String meterNumber) {
		inputMeterNumber.sendKeys(meterNumber);
	}

	public void setLeakLocationRemarks(String leakLocationRemarks) {
		textareaLeakLocationRemarks.sendKeys(leakLocationRemarks);
	}

	public void setAdditionalNotes(String additionalNotes) {
		textareaAdditionalNotes.sendKeys(additionalNotes);
	}


	public boolean verifyPDFLeakDetails(List<String> expectedPDFLeakDetails, List<String> leakDetails) {
		for(int i=0;i<leakDetails.size();i++){
			String actual = leakDetails.get(i).toLowerCase();
			String expect = expectedPDFLeakDetails.get(i).toLowerCase();
			if(!actual.matches(expect)){
				Log.error("Leak detail in PDF table: "+actual);
				Log.error("Leak detail expected: "+expect);
				return false;
			}
		}
		return true;
	}

	public boolean verifyMetaLeakDetails(String[][] expects, Map<String, String> lisaInvestigationDetails) {
		
		for(int i=0;i<expects[0].length; i++) {
			String actual = lisaInvestigationDetails.get(expects[0][i]).toLowerCase().trim();
			String expect = expects[1][i].toLowerCase();
			if(!actual.equals(expect)){
				Log.error("Leak detail in PDF table: "+expects[0][i]+" = "+actual);
				Log.error("Leak detail expected: "+expects[0][i]+" = "+expect);
				return false;
		}
	}
	return true;
}
}