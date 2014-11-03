/**
 * 
 */
package surveyor.scommon.source;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.source.BaseHelper;
import common.source.TestSetup;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class ComplianceReportsPage extends ReportsBasePage {
	public static final String STRURLPath = "/Reports/ComplianceReports";
	public static final String STRPageTitle = "Compliance Reports - Surveyor";

	/**
	 * @param driver
	 * @param strBaseURL
	 * @param testSetup
	 */
	public ComplianceReportsPage(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup, strBaseURL + STRURLPath);
		
		System.out.format("\nThe Compliance Reports Page URL is: %s\n", this.strPageURL);
	}
	
	//more generic interface and implementation, more details will be added into the reports objects
	public void addNewReport(Reports reportsCompliance) {
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		this.btnNewComplianceRpt.click();
		
		this.inputTitle.clear();
		this.inputTitle.sendKeys(reportsCompliance.getRptTitle());
		
		if (reportsCompliance.getCustomer() != null && reportsCompliance.getCustomer()  != "Picarro") {
			List<WebElement> optionsCustomer = this.dropdownCustomer.findElements(By.tagName("option"));
			for (WebElement option : optionsCustomer) {
				if ((reportsCompliance.getCustomer()).equalsIgnoreCase(option.getText().trim())) {
					option.click();
				}
			}
			
			if (this.isElementPresent(btnChangeModeXPath)) {
				JavascriptExecutor js = (JavascriptExecutor)driver; 
				js.executeScript("arguments[0].click();", btnChangeMode);  				
				
				//temporary bypass the issue DE456
				this.inputTitle.clear();
				this.inputTitle.sendKeys(reportsCompliance.getRptTitle());
			}
		}
		
		List<WebElement> optionsTZ = this.cBoxTimezone.findElements(By.tagName("option"));
		for (WebElement option : optionsTZ) {
			if ((reportsCompliance.getTimeZone()).equalsIgnoreCase(option.getText().trim())) {
				option.click();
			}
		}		

		this.inputExclusionRadius.clear();
		this.inputExclusionRadius.sendKeys(reportsCompliance.getExclusionRadius());
		//this.inputReportModeS1.click();
		
		//this.inputCusBoundary.click();
		
		this.inputImgMapHeight.sendKeys(reportsCompliance.getImageMapHeight());
		this.inputImgMapWidth.sendKeys(reportsCompliance.getImageMapWidth());
		this.inputNELat.sendKeys(reportsCompliance.getNELat());
		this.inputNELong.sendKeys(reportsCompliance.getNELong());
		this.inputSWLat.sendKeys(reportsCompliance.getSWLat());
		this.inputSWLong.sendKeys(reportsCompliance.getSWLong());
		
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
		
		if (reportsCompliance.getSurveyorUnit() != "") {
			List<WebElement> optionsSU = this.cbSurUnit.findElements(By.tagName("option"));
			for (WebElement option : optionsSU) {
				if ((reportsCompliance.getSurveyorUnit()).equalsIgnoreCase(option.getText().trim())) {
					option.click();
				}
			}
		}
		
		if (reportsCompliance.getTag() != "") {
			List<WebElement> optionsTAG = this.cbTag.findElements(By.tagName("option"));
			for (WebElement optionTAG : optionsTAG) {
				if ((reportsCompliance.getTag()).equalsIgnoreCase(optionTAG.getText().trim())) {
					optionTAG.click();
				}
			}
		}
		
		if (testSetup.isRunningDebug())
			testSetup.slowdownInSeconds(3);
		
//		this.cbStartDate.sendKeys(startDate);
//		this.cbEndDate.sendKeys(endDate);
		//this.inputSurModeFilterS1.click();
		
		this.btnSruveySearch.click();
		this.checkboxSurFirst.click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		this.btnAddSurveys.click();
		
		addViews(reportsCompliance.getViewList());
		
		this.btnOK.click();
	}
	
	private void addViews(List<Map<String, String>> viewList) {
		int rowNum; 
		int colNum;  
		String strBaseXPath;
		
		for (int i = 0; i < viewList.size(); i++) {
			if (i != 0) {
				this.btnAddViews.click();
			}
			
			rowNum = i + 1;
			if (viewList.get(i).get(KEYVIEWNAME) != null) {
				colNum = 2;
				strBaseXPath = "//*[@id='datatableViews']/tbody/tr["+rowNum+"]/td["+colNum+"]/input";
				driver.findElement(By.xpath(strBaseXPath)).clear();
				driver.findElement(By.xpath(strBaseXPath)).sendKeys(viewList.get(i).get(KEYVIEWNAME));
			}
			
			if (viewList.get(i).get(KEYLISA).equalsIgnoreCase("1")) {
				colNum = 3;
				strBaseXPath = "//*[@id='datatableViews']/tbody/tr["+rowNum+"]/td["+colNum+"]/input";
				driver.findElement(By.xpath(strBaseXPath)).click();
			}
			
			if (viewList.get(i).get(KEYFOV).equalsIgnoreCase("1")) {
				colNum = 4;
				strBaseXPath = "//*[@id='datatableViews']/tbody/tr["+rowNum+"]/td["+colNum+"]/input";
				driver.findElement(By.xpath(strBaseXPath)).click();
			}
			
			if (viewList.get(i).get(KEYBREADCRUMB).equalsIgnoreCase("1")) {
				colNum = 5;
				strBaseXPath = "//*[@id='datatableViews']/tbody/tr["+rowNum+"]/td["+colNum+"]/input";
				driver.findElement(By.xpath(strBaseXPath)).click();
			}
			
			if (viewList.get(i).get(KEYINDICATIONS).equalsIgnoreCase("1")) {
				colNum = 6;
				strBaseXPath = "//*[@id='datatableViews']/tbody/tr["+rowNum+"]/td["+colNum+"]/input";
				driver.findElement(By.xpath(strBaseXPath)).click();
			}
			
			if (viewList.get(i).get(KEYISOTOPICCAPTURE).equalsIgnoreCase("1")) {
				colNum = 7;
				strBaseXPath = "//*[@id='datatableViews']/tbody/tr["+rowNum+"]/td["+colNum+"]/input";
				driver.findElement(By.xpath(strBaseXPath)).click();
			}
			
			if (viewList.get(i).get(KEYANNOTATION).equalsIgnoreCase("1")) {
				colNum = 8;
				strBaseXPath = "//*[@id='datatableViews']/tbody/tr["+rowNum+"]/td["+colNum+"]/input";
				driver.findElement(By.xpath(strBaseXPath)).click();
			}
			
			if (viewList.get(i).get(KEYGAPS).equalsIgnoreCase("1")) {
				colNum = 9;
				strBaseXPath = "//*[@id='datatableViews']/tbody/tr["+rowNum+"]/td["+colNum+"]/input";
				driver.findElement(By.xpath(strBaseXPath)).click();
			}
			
			if (viewList.get(i).get(KEYASSETS).equalsIgnoreCase("1")) {
				colNum = 10;
				strBaseXPath = "//*[@id='datatableViews']/tbody/tr["+rowNum+"]/td["+colNum+"]/input";
				driver.findElement(By.xpath(strBaseXPath)).click();
			}			
			
			if (viewList.get(i).get(KEYBOUNDARIES).equalsIgnoreCase("1")) {
				colNum = 11;
				strBaseXPath = "//*[@id='datatableViews']/tbody/tr["+rowNum+"]/td["+colNum+"]/input";
				driver.findElement(By.xpath(strBaseXPath)).click();
			}
			
			colNum = 12;
			strBaseXPath = "//*[@id='datatableViews']/tbody/tr["+rowNum+"]/td["+colNum+"]/select";
			WebElement dropdownBaseMap = driver.findElement(By.xpath(strBaseXPath));
			
			List<WebElement> options = dropdownBaseMap.findElements(By.tagName("option"));
			for (WebElement option : options) {
				if ((viewList.get(i).get(KEYBASEMAP)).equalsIgnoreCase(option.getText().trim())) {
					option.click();
				}
			}
		}
	}
	
	//Nov. 1, 2014, this is obsolete and try not to call this for generating a compliance report.
	//Temporary solution for now and should pass the params by a data structure
	private void addNewReport(String title, String customer, String timeZone, String exclusionRadius, String boundary, String imageMapHeight, String imageMapWidth, 
			String NELat, String NELong, String SWLat, String SWLong, String surUnit, String tag, String startDate, String endDate, String surModeFilter) {
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		this.btnNewComplianceRpt.click();
		
		this.inputTitle.clear();
		this.inputTitle.sendKeys(title);
		
		if (customer != null && customer != "Picarro") {
			List<WebElement> optionsCustomer = this.dropdownCustomer.findElements(By.tagName("option"));
			for (WebElement option : optionsCustomer) {
				if ((customer).equalsIgnoreCase(option.getText().trim())) {
					option.click();
				}
			}
			
			if (this.isElementPresent(btnChangeModeXPath)) {
				JavascriptExecutor js = (JavascriptExecutor)driver; 
				js.executeScript("arguments[0].click();", btnChangeMode);  				
				
				//temporary bypass the issue DE456
				this.inputTitle.clear();
				this.inputTitle.sendKeys(title);
			}
		}
		
		List<WebElement> optionsTZ = this.cBoxTimezone.findElements(By.tagName("option"));
		for (WebElement option : optionsTZ) {
			if ((timeZone).equalsIgnoreCase(option.getText().trim())) {
				option.click();
			}
		}		

		this.inputExclusionRadius.clear();
		this.inputExclusionRadius.sendKeys(exclusionRadius);
		//this.inputReportModeS1.click();
		
		//this.inputCusBoundary.click();
		
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
		
		if (surUnit != "") {
			List<WebElement> optionsSU = this.cbSurUnit.findElements(By.tagName("option"));
			for (WebElement option : optionsSU) {
				if ((surUnit).equalsIgnoreCase(option.getText().trim())) {
					option.click();
				}
			}
		}
		
		if (tag != "") {
			List<WebElement> optionsTAG = this.cbTag.findElements(By.tagName("option"));
			for (WebElement optionTAG : optionsTAG) {
				if ((tag).equalsIgnoreCase(optionTAG.getText().trim())) {
					optionTAG.click();
				}
			}
		}
		
		if (testSetup.isRunningDebug())
			testSetup.slowdownInSeconds(3);
		
//		this.cbStartDate.sendKeys(startDate);
//		this.cbEndDate.sendKeys(endDate);
		//this.inputSurModeFilterS1.click();
		
		this.btnSruveySearch.click();
		this.checkboxSurFirst.click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		this.btnAddSurveys.click();
		
		this.inputViewLisa.click();
		this.inputViewFOV.click();
		this.inputViewBreadCrumb.click();
		this.inputViewInd.click();
		this.inputViewIso.click();
		this.inputViewAnno.click();
		this.inputViewAssets.click();
		this.inputViewBoundaries.click();
		//this.inputViewBaseMap.click();
		
		this.btnOK.click();
	}
	
	public boolean checkActionStatus(String rptTitle, String strCreatedBy) {
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		setPagination(PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		String reportTitleXPath;
		String createdByXPath;
		String pdfImgXPath;
		String zipImgXPath;
		
		WebElement rptTitleCell;
		WebElement createdByCell;
		WebElement pdfImg;
		WebElement zipImg;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			createdByXPath   = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
						
			rptTitleCell = table.findElement(By.xpath(reportTitleXPath));
			createdByCell = table.findElement(By.xpath(createdByXPath));
			
			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle) && createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy)) {
				long startTime = System.currentTimeMillis();
				long elapsedTime = 0;
				boolean bContinue = true;
				
				while (bContinue) {
					try {
						pdfImgXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[4]/a[3]/img";
						pdfImg = table.findElement(By.xpath(pdfImgXPath));
						String srcPdfImg = pdfImg.getAttribute("src");
						
						zipImgXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[4]/a[4]/img";
						zipImg = table.findElement(By.xpath(zipImgXPath));
						String srcZipImg = zipImg.getAttribute("src");
						
						if (srcPdfImg.contains("pdf") && srcZipImg.contains("zip")) {
							pdfImg.click();
							
							testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
							zipImg.click();
							
							testSetup.slowdownInSeconds(15);
							
							return true;
						}
						else
							return false;
					}
					catch (org.openqa.selenium.NoSuchElementException e) {
						elapsedTime = System.currentTimeMillis() - startTime;
						if (elapsedTime >= (ACTIONTIMEOUT * 1000)) {
							return false;
						}
						
						continue;
					}
				}
			}
			
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				
				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				rowSize = newRows.size();
				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);
				
				rowNum = 0;
			}
		}
		
		return false;
	}
		
	public void addNewPDReport(String reportTitle) {
		this.addNewReport(reportTitle, null, TIMEZONE, EXCLUSIONRADIUS, CUSBOUNDARY, IMGMAPHEIGHT, IMGMAPWIDTH, 
				NELAT, NELON, SWLAT, SWLON, SURVEYORUNIT, TAG, STARTDATE, ENDDATE, REPORTMODES1);
	}
	
	public void addNewPDReport(String reportTitle, String customer) {
		this.addNewReport(reportTitle, customer, TIMEZONE, EXCLUSIONRADIUS, CUSBOUNDARY, IMGMAPHEIGHT, IMGMAPWIDTH, 
				NELAT, NELON, SWLAT, SWLON, SURVEYORUNIT, TAG, STARTDATE, ENDDATE, REPORTMODES1);
	}
		
	public void addNewPDReport(String reportTitle, String surveyor, String tag) {
		this.addNewReport(reportTitle, null, TIMEZONE, EXCLUSIONRADIUS, CUSBOUNDARY, IMGMAPHEIGHT, IMGMAPWIDTH, 
				NELAT, NELON, SWLAT, SWLON, surveyor, tag, STARTDATE, ENDDATE, REPORTMODES1);
	}
		
	public void addNewPDReport(String reportTitle, String customer, String surveyor, String tag) {
		this.addNewReport(reportTitle, customer, TIMEZONE, EXCLUSIONRADIUS, CUSBOUNDARY, IMGMAPHEIGHT, IMGMAPWIDTH, 
				NELAT, NELON, SWLAT, SWLON, surveyor, tag, STARTDATE, ENDDATE, REPORTMODES1);
	}
	
	public boolean findExistingReport(String rptTitle, String strCreatedBy) { 
		setPagination(PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		String reportTitleXPath;
		String createdByXPath;
		
		WebElement rptTitleCell;
		WebElement createdByCell;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);		
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			
			rptTitleCell = table.findElement(By.xpath(reportTitleXPath));
			createdByCell = table.findElement(By.xpath(createdByXPath));
			
			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle) && createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy)) {				
				return true;
			}
			
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				
				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				rowSize = newRows.size();
				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);
				
				rowNum = 0;
			}			
		}
		
		return false;
	}
	
	public boolean deleteReport(String rptTitle, String strCreatedBy) {
		setPagination(PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		String reportTitleXPath;
		String createdByXPath;
		String deleteImgXPath;
		
		WebElement rptTitleCell;
		WebElement createdByCell;
		WebElement deleteImg;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);		
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			
			rptTitleCell = table.findElement(By.xpath(reportTitleXPath));
			createdByCell = table.findElement(By.xpath(createdByXPath));
			
			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle) && createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy)) {
				deleteImgXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[4]/a[1]/img";
				deleteImg = table.findElement(By.xpath(deleteImgXPath));
				
				deleteImg.click();
				
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				
				if (this.isElementPresent(btnDeleteReportXPath)) {
					JavascriptExecutor js = (JavascriptExecutor)driver; 
					js.executeScript("arguments[0].click();", btnDeleteReport);
					
					if (this.isElementPresent(errorMsgDeleteCompliacneReportXPath)) {
						this.btnReturnToHomePage.click();
						return false;
					}
					else
						return true;
				}
				else
					return false;
			}
			
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				
				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				rowSize = newRows.size();
				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);
				
				rowNum = 0;
			}			
		}
		
		return false;
	}
	
	public boolean copyReport(String rptTitle, String strCreatedBy, String rptTitleNew) {
		setPagination(PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		String reportTitleXPath;
		String createdByXPath;
		String copyImgXPath;
		
		WebElement rptTitleCell;
		WebElement createdByCell;
		WebElement copyImg;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);		
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			
			rptTitleCell = table.findElement(By.xpath(reportTitleXPath));
			createdByCell = table.findElement(By.xpath(createdByXPath));
			
			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle) && createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy)) {
				copyImgXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[4]/a[2]/img";
				copyImg = table.findElement(By.xpath(copyImgXPath));
				
				copyImg.click();
				
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				
				this.inputTitle.clear();
				this.inputTitle.sendKeys(rptTitleNew);
				
				this.btnOK.click();
				
				return true;
			}
			
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				
				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				rowSize = newRows.size();
				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);
				
				rowNum = 0;
			}			
		}
		
		return false;		

	}
	
	public boolean validatePdfFiles(ReportsCompliance reportsCompliance, String downloadPath) {
		try {
			BaseHelper.deCompressZipFile(reportsCompliance.getRptTitle(), downloadPath);
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		String nameBase = reportsCompliance.getRptTitle();
		String viewName;
		String pdfFile1;
		String pdfFile2;
		String pdfFile3;
		
		List<Map<String, String>> viewList = reportsCompliance.getViewList();
		
		pdfFile1 = downloadPath + nameBase + ".pdf";
		pdfFile3 = downloadPath + nameBase + File.separator + nameBase + ".pdf";
		
		if (BaseHelper.validatePdfFile(pdfFile1) && BaseHelper.validatePdfFile(pdfFile3)) {
			try {
				if (!BaseHelper.compareTwoFilesByContent(pdfFile1, pdfFile3))
					return false;
			} 
			catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		else
			return false;
		
		for (int i = 0; i < viewList.size(); i++) {
			viewName = viewList.get(i).get(KEYVIEWNAME);
			pdfFile2 = downloadPath + nameBase + File.separator + nameBase + "_" + viewName + ".pdf";
			
			if (!BaseHelper.validatePdfFile(pdfFile2))
				return false;
		}
		
		return true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}