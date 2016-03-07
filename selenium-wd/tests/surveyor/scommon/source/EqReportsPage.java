package surveyor.scommon.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.Log;
import common.source.TestSetup;

public class EqReportsPage extends ReportsBasePage {

	public static final String EQRPTURLPath= "/Reports/EQReports";
	
	@FindBy(how = How.XPATH, using = "//*[@href='/Reports/EQReport']")
	protected WebElement btnNewEQRpt;
	
	@FindBy(how = How.XPATH, using = "//*[@id='btn-EQ-select-area']")
	protected WebElement btnSelectArea;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-title']")
	protected WebElement eqRptTtl;
	
	@FindBy(how = How.XPATH, using = "//*[@id='eq-selected-text']")
	protected WebElement eqRptArea;
	
	public WebElement getBtnNewEQRpt() {
		return this.btnNewEQRpt;
	}
	
	public WebElement getSelectArea() {
		return this.btnSelectArea;
	}

	public WebElement getEqRptTtl() {
		return this.eqRptTtl;
	}

	public WebElement getEqRptArea() {
		return this.eqRptArea;
	}

	public EqReportsPage(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup, strBaseURL + EQRPTURLPath);

		Log.info("\nThe EQ Reports Page URL is: %s\n" + this.strPageURL);
	}
	
	public void waitForEQRptButton() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return btnNewEQRpt.isDisplayed();
			}
		});
	}
	public void clickOnNewEQReportBtn() {
		this.btnNewEQRpt.click();
	}
	
		
	

}
