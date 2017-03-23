package surveyor.scommon.mobile.source;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.source.Log;
import surveyor.scommon.entities.InvestigationEntity;

/**
 * @author sxiang
 *
 */

public class MobileInvestigationPage extends MobileBasePage {

	public static final String STRURLPath = "/Investigation/Investigations?reportId=%s&type=%s";

	@FindBy(how = How.CSS, using = "#boxType > button.btn")
	protected WebElement boxTypeDropdown;
	
	@FindBy(how = How.CSS, using = "#boxType > button.btn > #boxType_text")
	protected WebElement boxTypeText;

	protected String boxItemXPattern = "//*[@id='boxType']/ul[@class='dropdown-menu']/li/a[text()='%s ']";
	protected String boxMarkerXPattern = "//div[@class='list-group']/a[starts-with(text(), '%s')]";
	
	public MobileInvestigationPage(){
		super(STRURLPath);
		pageKey = By.cssSelector("[id='boxType'].dropdown");
		Log.info("The Investigations Page URL is: " + this.strPageURL);
	}
	
	public void open(String reportId, String type) {
		strPageURL = String.format(strPageURL, reportId+","+type);
		driver.get(strPageURL);
		waitUntilPageLoad();
	}
	
	public MobileInvestigatePage clickOnLisa(String lisaNumber){
		return clickOnLisa(lisaNumber, null);
	}
	
	public MobileInvestigatePage clickOnLisa(String lisaNumber, InvestigationEntity investigationEntity){
		String boxType = "LISA";
		selectDropdownItem(boxTypeDropdown, boxType);
		if(investigationEntity!=null){
			investigationEntity.setBoxType(boxType);
		}
		return clickOnMarker(lisaNumber);
	}

	public MobileInvestigatePage clickOnGap(String gapNumber){
		return clickOnGap(gapNumber, null);
	}
	
	public MobileInvestigatePage clickOnGap(String gapNumber, InvestigationEntity investigationEntity){
		String boxType = "Gap";
		selectDropdownItem(boxTypeDropdown, boxType);
		if(investigationEntity!=null){
			investigationEntity.setBoxType(boxType);
		}
		return clickOnMarker(gapNumber);
	}
	
	public boolean isBoxTypeSelected(String boxType){
		return getElementText(boxTypeText).equals(boxType);
	}
	
	public MobileInvestigatePage clickOnMarker(String boxNumber){
		WebElement markerLink = driver.findElement(By.xpath(String.format(boxMarkerXPattern, boxNumber)));
		markerLink.click();
		MobileInvestigatePage investigatePage = new MobileInvestigatePage();
		investigatePage.waitUntilPageLoad();
		return investigatePage;
	}
	
	public boolean isLisaShowing(String lisaNumber){
		String boxType = "LISA";
		selectDropdownItem(boxTypeDropdown, boxType);
		return isMarkerShowing(lisaNumber);
	}

	public boolean isGapShowing(String gapNumber){
		String boxType = "Gap";
		selectDropdownItem(boxTypeDropdown, boxType);
		return isMarkerShowing(gapNumber);
	}
	
	public boolean isMarkerShowing(String boxNumber){
		return isElementPresent(By.xpath(String.format(boxMarkerXPattern, boxNumber)));
	}
}