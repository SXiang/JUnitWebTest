package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.source.TestSetup;
import surveyor.scommon.actions.data.ReportsBaseDataReader;
import surveyor.scommon.actions.data.ReportsBaseDataReader.ReportsBaseDataRow;
import surveyor.scommon.actions.data.ReportsCommonDataReader;
import surveyor.scommon.actions.data.ReportsCommonDataReader.ReportsCommonDataRow;
import surveyor.scommon.source.ReportsBasePage;

public class BaseReportsPageActions extends BasePageActions {
	private ReportsBaseDataReader dataReader;
	public BaseReportsPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL);
	}

	public void initializePageObject(WebDriver driver, ReportsBasePage pageObj) {
		setPageObject(pageObj);
		PageFactory.initElements(driver, getPageObject());
	}

	public ReportsBasePage getReportsBasePageObject() {
		return (ReportsBasePage) getPageObject();
	}

	public void setPageObject(ReportsBasePage reportsBasePage) {
		this.pageObject.set(reportsBasePage);
	}
	
	public ReportsBaseDataReader getDataReader() {
		return dataReader;
	}

	public void setDataReader(ReportsBaseDataReader dataReader) {
		this.dataReader = dataReader;
	}
	
	public ReportsBaseDataRow getReportsDataRow(Integer dataRowID) throws Exception {
		throw new Exception("This method to be implemented by derived class");
	}

	public ReportsBaseDataRow getWorkingReportsDataRow() throws Exception {
		throw new Exception("This method to be implemented by derived class");
	}

	public void setWorkingReportsDataRow(ReportsBaseDataRow dataRow) throws Exception {
		throw new Exception("This method to be implemented by derived class");
	}
}