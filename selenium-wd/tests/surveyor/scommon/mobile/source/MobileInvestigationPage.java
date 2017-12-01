package surveyor.scommon.mobile.source;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.source.Log;
import surveyor.scommon.entities.InvestigationEntity;
import surveyor.scommon.source.ReportInvestigationsPage.IndicationStatus;

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

	public MobileInvestigatePage clickOnLisa(String reportName, int lisaNumber){
		return clickOnLisa(reportName, lisaNumber, null);
	}
	
	public MobileInvestigatePage clickOnLisa(String lisaNumber){
		return clickOnLisa(lisaNumber, null, null);
	}
	
	public MobileInvestigatePage clickOnLisa(String lisaNumber, IndicationStatus status){
		return clickOnLisa(lisaNumber, status, null);
	}	

	public MobileInvestigatePage clickOnLisa(String reportName, int lisaNumber, InvestigationEntity investigationEntity){
		return clickOnLisa(reportName+"-LISA-"+lisaNumber, investigationEntity);
	}

	public MobileInvestigatePage clickOnLisa(String lisaNumber, InvestigationEntity investigationEntity){
		return clickOnLisa(lisaNumber, null, investigationEntity);
	}
	
	public MobileInvestigatePage clickOnLisa(String lisaNumber, IndicationStatus status, InvestigationEntity investigationEntity){
		String boxType = "LISA";
		selectDropdownItem(boxTypeDropdown, boxType);
		String lisaItem = lisaNumber;
		if(status!=null){
			lisaItem = lisaNumber +" ("+status+")";
		}
		if(investigationEntity!=null){
			investigationEntity.setBoxType(boxType);
		}
		return clickOnMarker(lisaItem);
	}

	public MobileInvestigatePage clickOnGap(String reportName, int gapNumber){
		return clickOnGap(reportName, gapNumber, null);
	}
	
	public MobileInvestigatePage clickOnGap(String gapNumber){
		return clickOnGap(gapNumber, null, null);
	}
	
	public MobileInvestigatePage clickOnGap(String gapNumber, IndicationStatus status){
		return clickOnGap(gapNumber, status, null);
	}

	public MobileInvestigatePage clickOnGap(String reportName, int lisaNumber, InvestigationEntity investigationEntity){
		return clickOnGap(reportName+"-Gap-"+lisaNumber, investigationEntity);
	}
	
	public MobileInvestigatePage clickOnGap(String gapNumber, InvestigationEntity investigationEntity){
		return clickOnGap(gapNumber, null, investigationEntity);
	}
	
	public MobileInvestigatePage clickOnGap(String gapNumber, IndicationStatus status, InvestigationEntity investigationEntity){
		String boxType = "Gap";
		selectDropdownItem(boxTypeDropdown, boxType);
		String gapItem = gapNumber;
		if(status!=null){
			gapItem = gapNumber +" ("+status+")";
		}
		if(investigationEntity!=null){
			investigationEntity.setBoxType(boxType);
		}
		return clickOnMarker(gapItem);
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

	public boolean isLisaShowing(String lisaNumber, IndicationStatus status){
		String lisaItem = lisaNumber +" ("+status+")";
		return isLisaShowing(lisaItem);
	}
	
	public boolean isLisaShowing(String lisaItem){
		String boxType = "LISA";
		selectDropdownItem(boxTypeDropdown, boxType);
		return isMarkerShowing(lisaItem);
	}

	public boolean isGapShowing(String gapNumber, IndicationStatus status){
		String gapItem = gapNumber +" ("+status+")";
		return isLisaShowing(gapItem);
	}
	
	public boolean isGapShowing(String gapItem){
		String boxType = "Gap";
		selectDropdownItem(boxTypeDropdown, boxType);
		return isMarkerShowing(gapItem);
	}
	
	public boolean isMarkerShowing(String boxNumber){
		return isElementPresent(By.xpath(String.format(boxMarkerXPattern, boxNumber)));
	}
}