package surveyor.scommon.mobile.source;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.source.Log;

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
	private String apartmentNumber = "1";
	
	@FindBy(how = How.CSS, using = "#leak_details input[ng-model='leak.AddressStreetName']")
	protected WebElement inputStreetName;
	private String streetName = "Patrick Henry Dr";
	
	@FindBy(how = How.CSS, using = "#leak_details input[ng-model='leak.AddressCity']")
	protected WebElement inputCity;
	private String city = "Santa Clara";
	
	@FindBy(how = How.CSS, using = "#leak_details input[ng-model='leak.AddressState']")
	protected WebElement inputState;
	private String state = "CA";
	
	@FindBy(how = How.CSS, using = "#leak_details input[ng-model='leak.MapNumber']")
	protected WebElement inputMapNumber;
	private String mapNumber = "1";
	
	@FindBy(how = How.CSS, using = "#leak_details input[ng-model='leak.SurfaceReading']")
	protected WebElement inputSurfaceReading;
	private String surfaceReading = "1";
	
	@FindBy(how = How.CSS, using = "[preselected-item='leak.SurfaceReadingUnitTypeId'] button[data-toggle='dropdown']")
	protected WebElement surfaceReadingUnitDropdown;
	private String surfaceReadingUnit = "PPM";
	
	@FindBy(how = How.CSS, using = "#leak_details input[ng-model='leak.BarholeReading']")
	protected WebElement inputBarholeReading;
	private String barholeReading = "2";
	
	@FindBy(how = How.CSS, using = "[preselected-item='leak.BarholeReadingUnitTypeId'] button[data-toggle='dropdown']")
	protected WebElement barholeReadingUnitDropdown;
	private String barholeReadingUnit = "PPM";
	
	@FindBy(how = How.CSS, using = "[preselected-item='leak.LeakTypeId'] button[data-toggle='dropdown']")
	protected WebElement leakTypeDropdown;
	private String leakType = "Above Ground";
	
	@FindBy(how = How.CSS, using = "#leak_details input[ng-model='leak.LeakGrade']")
	protected WebElement inputLeakGrade;
	private String leakGrade = "2";
	
	@FindBy(how = How.CSS, using = "[preselected-item='leak.LeakLocationTypeId'] button[data-toggle='dropdown']")
	protected WebElement leakLocationTypeDropdown;
	private String leakLocationType = "Service";
	
	@FindBy(how = How.CSS, using = "#leak_details input[ng-model='leak.PipeMaterialType']")
	protected WebElement inputPipeMaterialType;
	private String pipeMaterialType = "Any";
	
	@FindBy(how = How.CSS, using = "#leak_details input[ng-model='leak.IsPavedWallToWall']")
	protected WebElement checkboxPaved;
	private boolean pavedWallToWall =  true;
	
	@FindBy(how = How.CSS, using = "[preselected-item='leak.SurfaceOverLeakTypeId'] button[data-toggle='dropdown']")
	protected WebElement surfaceOverLeakDropdown;
	private String surfaceOverLeak = "Concrete";
	
	@FindBy(how = How.CSS, using = "#leak_details input[ng-model='leak.MeterNumber']")
	protected WebElement inputMeterNumber;
	private String meterNumber = "9";
	
	@FindBy(how = How.CSS, using = "#leak_details textarea[ng-model='leak.LocationRemarks']")
	protected WebElement textareaLeakLocationRemarks;
	private String leakLocationRemarks = "Location Remarks: SQAAuto test remarks";
	
	@FindBy(how = How.CSS, using = "#leak_details textarea[ng-model='leak.Notes']")
	protected WebElement textareaAdditionalNotes;
	private String additionalNotes = "AdditionalNotes: SQAAuto test notes";

	@FindBy(how = How.CSS, using = "[preselected-item='leak.LeakSourceTypeId'] button[data-toggle='dropdown']")
	protected WebElement leakSourceTypeDropdown;
	private String leakSourceType = "Landfill";
	
	@FindBy(how = How.CSS, using = ".modal-dialog .modal-footer > a.btn[ng-click='ok()']")
	protected WebElement buttonOk;
	@FindBy(how = How.CSS, using = ".modal-dialog .modal-footer > a.btn[ng-click='cancel()']")
	protected WebElement buttonCancel;

	@FindBy(how = How.CSS, using = ".modal-dialog .modal-header > button.close[ng-click='cancel()']")
	protected WebElement buttonCloseAddSource;

	@FindBy(how = How.XPATH, using = ".modal-dialog .row .list-group-item-text")
	protected WebElement investigateTime;
	private String investigationTime = "";
	
	public MobileLeakSourcePage(){
		super(STRURLPath);
		pageKey = By.cssSelector("#leak_details.form-group");
		Log.info("The LeakSource Page URL is: " + this.strPageURL);
	}
	

	public void addLeakDetails(){
		setStreetNumber(this.streetNumber);
		setApartmentNumber(this.apartmentNumber);
		setStreetName(this.streetName);
		setCity(this.city);
		setState(this.state);
		setMapNumber(this.mapNumber);
		setSurfaceReading(this.surfaceReading);
		setSurfaceReadingUnit(this.surfaceReadingUnit);
		setBarholeReading(this.barholeReading);
		setBarholeReadingUnit(this.barholeReadingUnit);
		setLeakType(this.leakType);
		setLeakGrade(this.leakGrade);
		setLeakLocationType(this.leakLocationType);
		setPipeMaterialType(this.pipeMaterialType);
		setPavedWallToWall(this.pavedWallToWall);
		setSurfaceOverLeak(this.surfaceOverLeak);
		setMeterNumber(this.meterNumber);
		setLeakLocationRemarks(this.leakLocationRemarks);
		setAdditionalNotes(this.additionalNotes);
		
		clickOkButton();
	}

	public void addOtherSource(){
		setLeakSourceType(this.leakSourceType);
		setAdditionalNotes(this.additionalNotes);
		
		clickOkButton();
	}

	
	public String getLeakSourceType() {
		return leakSourceType;
	}


	public void setLeakSourceType(String leakSourceType) {
		this.leakSourceType = leakSourceType;
		selectDropdownItem(leakSourceTypeDropdown, leakSourceType);
	}

	public void clickOkButton() {
		this.buttonOk.click();
		waitForPageToLoad();
		this.investigationTime = getElementText(investigateTime);
	}


	public void clickCancelButton() {
		this.buttonCancel.click();
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


	public String getApartmentNumber() {
		return apartmentNumber;
	}


	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
		inputApartmentNumber.sendKeys(apartmentNumber);
	}


	public String getStreetName() {
		return streetName;
	}


	public void setStreetName(String streetName) {
		this.streetName = streetName;
		inputStreetName.sendKeys(streetName);
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
		inputCity.sendKeys(city);
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
		inputState.sendKeys(state);
	}


	public String getMapNumber() {
		return mapNumber;
	}


	public void setMapNumber(String mapNumber) {
		this.mapNumber = mapNumber;
		inputMapNumber.sendKeys(mapNumber);
	}


	public String getSurfaceReading() {
		return surfaceReading;
	}


	public void setSurfaceReading(String surfaceReading) {
		this.surfaceReading = surfaceReading;
		inputSurfaceReading.sendKeys(surfaceReading);
	}


	public String getSurfaceReadingUnit() {
		return surfaceReadingUnit;
	}


	public void setSurfaceReadingUnit(String surfaceReadingUnit) {
		this.surfaceReadingUnit = surfaceReadingUnit;
		selectDropdownItem(surfaceReadingUnitDropdown, surfaceReadingUnit);
	}


	public String getBarholeReading() {
		return barholeReading;
	}


	public void setBarholeReading(String barholeReading) {
		this.barholeReading = barholeReading;
		inputBarholeReading.sendKeys(barholeReading);
	}


	public String getBarholeReadingUnit() {
		return barholeReadingUnit;
	}


	public void setBarholeReadingUnit(String barholeReadingUnit) {
		this.barholeReadingUnit = barholeReadingUnit;
		selectDropdownItem(barholeReadingUnitDropdown, barholeReadingUnit);
	}


	public String getLeakType() {
		return leakType;
	}


	public void setLeakType(String leakType) {
		this.leakType = leakType;
		selectDropdownItem(leakTypeDropdown, leakType);
	}


	public String getLeakGrade() {
		return leakGrade;
	}


	public void setLeakGrade(String leakGrade) {
		this.leakGrade = leakGrade;
		inputLeakGrade.sendKeys(leakGrade);
	}


	public String getLeakLocationType() {
		return leakLocationType;
	}


	public void setLeakLocationType(String leakLocationType) {
		this.leakLocationType = leakLocationType;
		selectDropdownItem(leakLocationTypeDropdown, leakLocationType);
	}


	public String getPipeMaterialType() {
		return pipeMaterialType;
	}


	public void setPipeMaterialType(String pipeMaterialType) {
		this.pipeMaterialType = pipeMaterialType;
		inputPipeMaterialType.sendKeys(pipeMaterialType);
	}


	public boolean isPavedWallToWall() {
		return pavedWallToWall;
	}


	public void setPavedWallToWall(boolean pavedWallToWall) {
		this.pavedWallToWall = pavedWallToWall;
		if(checkboxPaved.isSelected() != pavedWallToWall){
			checkboxPaved.click();
		}
	}


	public String getSurfaceOverLeak() {
		return surfaceOverLeak;
	}


	public void setSurfaceOverLeak(String surfaceOverLeak) {
		this.surfaceOverLeak = surfaceOverLeak;
		selectDropdownItem(surfaceOverLeakDropdown, surfaceOverLeak);
	}


	public String getMeterNumber() {
		return meterNumber;
	}


	public void setMeterNumber(String meterNumber) {
		this.meterNumber = meterNumber;
		inputMeterNumber.sendKeys(meterNumber);
	}


	public String getLeakLocationRemarks() {
		return leakLocationRemarks;
	}


	public void setLeakLocationRemarks(String leakLocationRemarks) {
		this.leakLocationRemarks = leakLocationRemarks;
		textareaLeakLocationRemarks.sendKeys(leakLocationRemarks);
	}


	public String getAdditionalNotes() {
		return additionalNotes;
	}


	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
		textareaAdditionalNotes.sendKeys(additionalNotes);
	}


	public boolean verifyPDFLeakDetials(List<String> list) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean verifyMetaLeakDetials(List<String> list) {
		// TODO Auto-generated method stub
		return false;
	}

}