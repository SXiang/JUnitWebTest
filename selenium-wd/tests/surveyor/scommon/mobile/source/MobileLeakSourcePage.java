package surveyor.scommon.mobile.source;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.source.CollectionsUtil;
import common.source.Log;
import surveyor.scommon.entities.InvestigationEntity;
import surveyor.scommon.entities.LeakDetailEntity;
import surveyor.scommon.entities.OtherSourceEntity;
import surveyor.scommon.entities.OtherSourceEntity.OtherLeakSourceType;

/**
 * @author sxiang
 *
 */

public class MobileLeakSourcePage extends MobileBasePage {

	public static final String STRURLPath = "/Investigation/Investigate?boxId=%s";

	@FindBy(how = How.CSS, using = "#leak_details input[ng-model='leak.AddressStreetNumber']")
	protected WebElement inputStreetNumber;

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

	public boolean verifyLeakDetails(LeakDetailEntity leakDetails){
		boolean isCorrect = true;
		isCorrect &= getInputStreetNumber().equalsIgnoreCase(leakDetails.getStreetNumber());
		isCorrect &= getInputApartmentNumber().equalsIgnoreCase(leakDetails.getApartmentNumber());
		isCorrect &= getInputStreetName().equalsIgnoreCase(leakDetails.getStreetName());
		isCorrect &= getInputCity().equalsIgnoreCase(leakDetails.getCity());
		isCorrect &= getInputState().equalsIgnoreCase(leakDetails.getState());
		isCorrect &= getInputMapNumber().equalsIgnoreCase(leakDetails.getMapNumber());
		isCorrect &= getInputSurfaceReading().equalsIgnoreCase(leakDetails.getSurfaceReading());
		isCorrect &= getSurfaceReadingUnitDropdown().equalsIgnoreCase(leakDetails.getSurfaceReadingUnit());
		isCorrect &= getInputBarholeReading().equalsIgnoreCase(leakDetails.getBarholeReading());
		isCorrect &= getBarholeReadingUnitDropdown().equalsIgnoreCase(leakDetails.getBarholeReadingUnit());
		isCorrect &= getLeakTypeDropdown().equalsIgnoreCase(leakDetails.getLeakType());
		isCorrect &= getInputLeakGrade().equalsIgnoreCase(leakDetails.getLeakGrade());
		isCorrect &= getLeakLocationTypeDropdown().equalsIgnoreCase(leakDetails.getLeakLocationType());
		isCorrect &= getInputPipeMaterialType().equalsIgnoreCase(leakDetails.getPipeMaterialType());
		isCorrect &= isCheckboxPaved() == leakDetails.isPavedWallToWall();
		isCorrect &= getSurfaceOverLeakDropdown().equalsIgnoreCase(leakDetails.getSurfaceOverLeak());
		isCorrect &= getInputMeterNumber().equalsIgnoreCase(leakDetails.getMeterNumber());
		isCorrect &= getTextareaLeakLocationRemarks().equalsIgnoreCase(leakDetails.getLeakLocationRemarks());
		isCorrect &= getTextareaAdditionalNotes().equalsIgnoreCase(leakDetails.getAdditionalNotes());

		clickCancelButton();
		return isCorrect;
	}

	public boolean verifyOtherSource(OtherSourceEntity otherSoruce){
		boolean isCorrect = true;
		isCorrect &= getLeakSourceTypeDropdown().equalsIgnoreCase(otherSoruce.getLeakSourceType());
		isCorrect &= getTextareaAdditionalNotes().equalsIgnoreCase(otherSoruce.getAdditionalNotes());
		clickCancelButton();
		return isCorrect;
	}

	public boolean verifyOtherSourceTypeOptions(){
		boolean isCorrect = true;
		List<WebElement> listItems = leakSourceTypeDropdown.findElements(By.xpath("../ul[@class='dropdown-menu']/li/a"));
		List<String> options = listItems.stream().map((WebElement e) -> getElementInnerText(e)).filter((String s) -> !s.trim().isEmpty()).collect(Collectors.toList());
		for(OtherLeakSourceType lst:OtherLeakSourceType.values()){
			if(!(isCorrect &= options.contains(lst.toString()))){
				Log.warn("Options in leakSourceType include: "+options);
				Log.warn(lst + " is not an option in the dropdown");
			}
		}
		return isCorrect;
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


	/* Getter */
	public String getStreetNumber() {
		return getElementInputValue(inputStreetNumber);
	}

	public String getInputStreetNumber() {
		return getElementInputValue(inputStreetNumber);
	}

	public String getInputApartmentNumber() {
		return getElementInputValue(inputApartmentNumber);
	}

	public String getInputStreetName() {
		return getElementInputValue(inputStreetName);
	}

	public String getInputCity() {
		return getElementInputValue(inputCity);
	}

	public String getInputState() {
		return getElementInputValue(inputState);
	}

	public String getInputMapNumber() {
		return getElementInputValue(inputMapNumber);
	}

	public String getInputSurfaceReading() {
		return getElementInputValue(inputSurfaceReading);
	}

	public String getSurfaceReadingUnitDropdown() {
		return getDropdownSelectedItem(surfaceReadingUnitDropdown);
	}

	public String getInputBarholeReading() {
		return getElementInputValue(inputBarholeReading);
	}

	public String getBarholeReadingUnitDropdown() {
		return getDropdownSelectedItem(barholeReadingUnitDropdown);
	}

	public String getLeakTypeDropdown() {
		return getDropdownSelectedItem(leakTypeDropdown);
	}

	public String getInputLeakGrade() {
		return getElementInputValue(inputLeakGrade);
	}

	public String getLeakLocationTypeDropdown() {
		return getDropdownSelectedItem(leakLocationTypeDropdown);
	}

	public String getInputPipeMaterialType() {
		return getElementInputValue(inputPipeMaterialType);
	}

	public boolean isCheckboxPaved() {
		return checkboxPaved.isSelected();
	}

	public String getSurfaceOverLeakDropdown() {
		return getDropdownSelectedItem(surfaceOverLeakDropdown);
	}

	public String getInputMeterNumber() {
		return getElementInputValue(inputMeterNumber);
	}

	public String getTextareaLeakLocationRemarks() {
		return getElementInputValue(textareaLeakLocationRemarks);
	}

	public String getTextareaAdditionalNotes() {
		return getElementInputValue(textareaAdditionalNotes);
	}

	public String getLeakSourceTypeDropdown() {
		return getDropdownSelectedItem(leakSourceTypeDropdown);
	}

	public String getInvestigateTime() {
		return getElementInputValue(investigateTime);
	}

	/* Setter */
	public void setStreetNumber(String streetNumber) {
		inputTextValue(inputStreetNumber, streetNumber);
	}

	public void setApartmentNumber(String apartmentNumber) {
		inputTextValue(inputApartmentNumber, apartmentNumber);
	}

	public void setStreetName(String streetName) {
		inputTextValue(inputStreetName, streetName);
	}

	public void setCity(String city) {
		inputTextValue(inputCity, city);
	}

	public void setState(String state) {
		inputTextValue(inputState, state);
	}

	public void setMapNumber(String mapNumber) {
		inputTextValue(inputMapNumber, mapNumber);
	}

	public void setSurfaceReading(String surfaceReading) {
		inputTextValue(inputSurfaceReading, surfaceReading);
	}

	public void setSurfaceReadingUnit(String surfaceReadingUnit) {
		selectDropdownItem(surfaceReadingUnitDropdown, surfaceReadingUnit);
	}

	public void setBarholeReading(String barholeReading) {
		inputTextValue(inputBarholeReading, barholeReading);
	}

	public void setBarholeReadingUnit(String barholeReadingUnit) {
		selectDropdownItem(barholeReadingUnitDropdown, barholeReadingUnit);
	}

	public void setLeakType(String leakType) {
		selectDropdownItem(leakTypeDropdown, leakType);
	}

	public void setLeakGrade(String leakGrade) {
		inputTextValue(inputLeakGrade, leakGrade);
	}

	public void setLeakLocationType(String leakLocationType) {
		selectDropdownItem(leakLocationTypeDropdown, leakLocationType);
	}

	public void setPipeMaterialType(String pipeMaterialType) {
		inputTextValue(inputPipeMaterialType, pipeMaterialType);
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
		inputTextValue(inputMeterNumber, meterNumber);
	}

	public void setLeakLocationRemarks(String leakLocationRemarks) {
		inputTextValue(textareaLeakLocationRemarks, leakLocationRemarks);
	}

	public void setAdditionalNotes(String additionalNotes) {
		inputTextValue(textareaAdditionalNotes, additionalNotes);
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