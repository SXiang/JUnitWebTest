package surveyor.scommon.source;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.source.Log;
import common.source.WebElementExtender;
import common.source.BasePage.ElementType;

public class ChangeCustomerDialogControl extends BaseControl {
	@FindBy(how = How.XPATH, using = "//*[@id='customerModal']/div/div/div[3]/a[1]")
	protected WebElement btnChangeCustomer;
	protected String btnChangeCustomerXPath = "//*[@id='customerModal']/div/div/div[3]/a[1]";
	
	@FindBy(how = How.XPATH, using = "//*[@id='customerModal']/div/div/div[3]/a[2]")
	protected WebElement btnCancelChangeCustomer;
	protected String btnCancelChangeCustomerXPath = "//*[@id='customerModal']/div/div/div[3]/a[2]";
	
	public ChangeCustomerDialogControl(WebDriver driver) {
		super(driver);
	}

	public boolean confirmInChangeCustomerDialog() {
		return confirmInChangeCustomerDialog(true);
	}
	
	public boolean confirmInChangeCustomerDialog(boolean confirm) {
		if (WebElementExtender.findElementBy(driver, By.xpath(btnChangeCustomerXPath))) {
			Log.clickElementInfo("Confirm Change Customer",ElementType.LINK);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			if (confirm) {
			   js.executeScript("arguments[0].click();", btnChangeCustomer);
			} else {
				WebElementExtender.executeScript(btnCancelChangeCustomer, driver, "arguments[0].click();");
			}
			return true;
		}
		return false;
	}
}