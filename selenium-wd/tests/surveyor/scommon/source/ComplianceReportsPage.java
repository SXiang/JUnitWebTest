/**
 * 
 */
package surveyor.scommon.source;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class ComplianceReportsPage extends ReportsBasePage implements Reports {
	public static final String STRURLPath = "/Reports/ComplianceReports";
	public static final String STRPageTitle = "Compliance Reports - Surveyor";
	
	private String rptTitle;

	/**
	 * @param driver
	 * @param strBaseURL
	 * @param testSetup
	 */
	public ComplianceReportsPage(WebDriver driver, String strBaseURL, TestSetup testSetup, String title) {
		super(driver, strBaseURL, testSetup, strBaseURL + STRURLPath);
		
		System.out.println("\nThe Manager Users Page URL is: " + this.strPageURL);
		
		this.rptTitle = title;
		System.out.format("\nThe report title is: %s", this.rptTitle);
	}
	
	//Temporary solution for now and should pass the params by a data structure
	public void addNewReport(String title, String timeZone, String exclusionRadius, String boundary, String imageMapHeight, String imageMapWidth, 
			String NELat, String NELong, String SWLat, String SWLong, String surUnit, String tag, String startDate, String endDate, String surModeFilter) {
		this.btnNewComplianceRpt.click();
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);
		
		this.inputTitle.sendKeys(title);
		
		List<WebElement> optionsTZ = this.cBoxTimezone.findElements(By.tagName("option"));
		for (WebElement option : optionsTZ) {
			if ((timeZone).equalsIgnoreCase(option.getText().trim())) {
				option.click();
			}
		}		

		this.inputExclusionRadius.clear();
		this.inputExclusionRadius.sendKeys(exclusionRadius);
		this.inputReportModeS1.click();
		this.inputCusBoundary.click();
		
		this.inputImgMapHeight.sendKeys(imageMapHeight);
		this.inputImgMapWidth.sendKeys(imageMapWidth);
		this.inputNELat.sendKeys(NELat);
		this.inputNELong.sendKeys(NELong);
		this.inputSWLat.sendKeys(SWLat);
		this.inputSWLong.sendKeys(SWLong);
		
		this.checkBoxIndTb.click();
		this.checkBoxIsoAna.click();
		this.checkBoxPCA.click();
		this.checkBoxPCRA.click();
		
		this.checkBoxOtherPla.click();
		this.checkBoxPEPla.click();
		this.checkBoxProtectedSteel.click();
		this.checkBoxUnProtectedSteel.click();
		this.checkBoxCastIron.click();
		this.checkBoxCopper.click();
		
//		this.checkBoxLevel1.click();
//		this.checkBoxLevel6.click();
		
		List<WebElement> optionsSU = this.cbSurUnit.findElements(By.tagName("option"));
		for (WebElement option : optionsSU) {
			if ((surUnit).equalsIgnoreCase(option.getText().trim())) {
				option.click();
			}
		}
		
		List<WebElement> optionsTAG = this.cbTag.findElements(By.tagName("option"));
		for (WebElement option : optionsTAG) {
			if ((tag).equalsIgnoreCase(option.getText().trim())) {
				option.click();
			}
		}
		
		if (testSetup.isRunningDebug())
			testSetup.slowdownInSeconds(3);
		
//		this.cbStartDate.sendKeys(startDate);
//		this.cbEndDate.sendKeys(endDate);
		this.inputSurModeFilterS1.click();
		
		this.btnSruveySearch.click();
		this.checkboxSurFirst.click();
		this.btnAddSurveys.click();
		
		if (testSetup.isRunningDebug())
			testSetup.slowdownInSeconds(3);
		
		this.inputViewLisa.click();
		this.inputViewFOV.click();
		this.inputViewBreadCrumb.click();
		this.inputViewInd.click();
		this.inputViewIso.click();
		this.inputViewAnno.click();
		this.inputViewAssets.click();
		this.inputViewBoundaries.click();
		this.inputViewBaseMap.click();
		
		if (testSetup.isRunningDebug())
			testSetup.slowdownInSeconds(3);
		
		this.btnOK.click();
		
		if (testSetup.isRunningDebug())
			testSetup.slowdownInSeconds(3);
		
//		if (this.checkActionStatus(this.getReportTitle(),  testSetup.getLoginUser()))
//			return true;
//		
//		return false;
	}
	
	public boolean checkActionStatus(String rptTitle, String strCreatedBy) {
		paginationInput.sendKeys("100");
		
		String reportTitleXPath;
		String createdByXPath;
		String errorSpanXPath;
		String actionStatusXPath;
		String pdfImgXPath;
		
		WebElement rptTitleCell = null;
		WebElement createdByCell = null;
		WebElement errorSpan = null;
		WebElement actionStatus = null;
		WebElement pdfImg = null;
		
		List<WebElement> rows = rptTB.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < 100)
			loopCount = rowSize;
		else
			loopCount = 100; 
		
		driver.manage().timeouts().implicitlyWait(this.testSetup.getImplicitlySpecialWaitTimeOutinSeconds(),TimeUnit.SECONDS);
		
		for (int i = 1; i < loopCount; i++) {
			actionStatusXPath = "//*[@id='datatable']/tbody/tr["+i+"]/td[4]/img";
			
			long startTime = System.currentTimeMillis();
			long elapsedTime = 0;
			
			boolean bContinue = true;
			while (bContinue) {
				try {
					actionStatus = driver.findElement(By.xpath(actionStatusXPath));
					
					elapsedTime = System.currentTimeMillis() - startTime;
					if (elapsedTime >= (ACTIONTIMEOUT * 1000)) {
						driver.manage().timeouts().implicitlyWait(testSetup.getImplicitlyWaitTimeOutInSeconds(), TimeUnit.SECONDS);
						return false;
					}
				}
				catch (org.openqa.selenium.NoSuchElementException e) {
					bContinue = false;
					continue;
				}
			}
		}

		driver.manage().timeouts().implicitlyWait(testSetup.getImplicitlyWaitTimeOutInSeconds(), TimeUnit.SECONDS);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath =  "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			createdByXPath =    "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			errorSpanXPath =    "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[4]/span";
			actionStatusXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[4]/img";
			pdfImgXPath =       "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[4]/a[3]/img";
			
			if (testSetup.isRunningDebug())
				testSetup.slowdownInSeconds(3);
			
			rptTitleCell = rptTB.findElement(By.xpath(reportTitleXPath));
			createdByCell = rptTB.findElement(By.xpath(createdByXPath));
			
			if (rptTitleCell.getText().equalsIgnoreCase(rptTitle) && createdByCell.getText().equalsIgnoreCase(strCreatedBy)) {
				try {
					pdfImg = rptTB.findElement(By.xpath(pdfImgXPath));
					String src = pdfImg.getAttribute("src");
					
					if (testSetup.isRunningDebug())
						System.out.print("The src is: " + src);
					
					if (src.contains("pdf"))
						return true;
				}
				catch (Exception e) {
					System.out.format("Exception on report generation: \n%s", e.getMessage());
					return false;
				}
				
				return true;
			}
			
			if (rowNum == 100 && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				rowNum = 1;
			}
			else {
				rowNum = rowNum + 1;
			}			
		}
		
		return false;
	}
	
	//Temporary solution for now and should pass the params by a data structure	
	public void addNewPDReport() {
		this.addNewReport(this.getReportTitle(), TIMEZONE, EXCLUSIONRADIUS, CUSBOUNDARY, IMGMAPHEIGHT, IMGMAPWIDTH, 
				NELAT, NELON, SWLAT, SWLON, SURVEYORUNIT, TAG, STARTDATE, ENDDATE, REPORTMODES1);
	}
	
	public String getReportTitle() {
		return this.rptTitle;
	}
	
	public boolean findExistingReport(String rptTitle, String strCreatedBy) { 
		paginationInput.sendKeys("100");
		
		String reportTitleXPath;
		String createdByXPath;
		WebElement rptTitleCell;
		WebElement createdByCell;
		
		List<WebElement> rows = rptTB.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < 100)
			loopCount = rowSize;
		else
			loopCount = 100;		
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			
			rptTitleCell = rptTB.findElement(By.xpath(reportTitleXPath));
			createdByCell = rptTB.findElement(By.xpath(createdByXPath));
			
			if (rptTitleCell.getText().equalsIgnoreCase(rptTitle) && createdByCell.getText().equalsIgnoreCase(strCreatedBy)) {
				if (testSetup.isRunningDebug())
					System.out.format("\nThe report found is: %s", rptTitleCell.getText());				
				return true;
			}
			
			if (rowNum == 100 && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				List<WebElement> newRows = rptTB.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				rowSize = newRows.size();
				if (rowSize < 100)
					loopCount = rowSize;
				else
					loopCount = 100;
				rowNum = 1;
			}			
		}
		
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
}