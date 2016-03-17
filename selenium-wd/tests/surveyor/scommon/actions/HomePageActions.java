package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.source.TestSetup;
import surveyor.scommon.source.HomePage;

public class HomePageActions extends BasePageActions {
	private HomePage homePage = null;
	
	public HomePageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL);
		homePage = new HomePage(driver, strBaseURL, testSetup);
		PageFactory.initElements(driver, homePage);
	}

	/**
	 * Executes open action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean open(String data, Integer dataRowID) {
		logAction("HomePageActions.open", data, dataRowID);
		return true;
	}
 
	/**
	 * Executes clickOnViewAllDrivingSurveysLink action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnViewAllDrivingSurveysLink(String data, Integer dataRowID) {
		logAction("HomePageActions.clickOnViewAllDrivingSurveysLink", data, dataRowID);
		homePage.clickOnViewAllDrivingSurveysLink();
		return true;
	}
 
	/**
	 * Executes clickOnFirstMatchingDrivingSurvey action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnFirstMatchingDrivingSurvey(String data, Integer dataRowID) {
		logAction("HomePageActions.clickOnFirstMatchingDrivingSurvey", data, dataRowID);
		homePage.waitForFirstDrivingSurveyToBeCompleted();
		homePage.clickOnFirstMatchingDrivingSurvey(data);
		return true;
	}

	/**
	 * Executes clickOnFirstOnlineSurveyorLink action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnFirstOnlineSurveyorLink(String data, Integer dataRowID) {
		logAction("HomePageActions.clickOnFirstOnlineSurveyorLink", data, dataRowID);
		homePage.clickOnFirstOnlineSurveyorLink();
		return true;
	}
 
	/* Invoke action using specified ActionName */
	@Override
	public boolean invokeAction(String actionName, String data, Integer dataRowID) throws Exception {
		if (actionName.equals("clickById")) { return this.clickById(data, dataRowID); }
		else if (actionName.equals("clickByIdAndWait")) { return this.clickByIdAndWait(data, dataRowID); }
		else if (actionName.equals("clickByXPath")) { return this.clickByXPath(data, dataRowID); }
		else if (actionName.equals("clickByXPathAndWait")) { return this.clickByXPathAndWait(data, dataRowID); }
		else if (actionName.equals("insertTextById")) { return this.insertTextById(data, dataRowID); }
		else if (actionName.equals("insertTextByXPath")) { return this.insertTextByXPath(data, dataRowID); }
		else if (actionName.equals("open")) { return this.open(data, dataRowID); }
		else if (actionName.equals("selectDropDownByID")) { return this.selectDropDownByID(data, dataRowID); }
		else if (actionName.equals("selectDropDownByXPath")) { return this.selectDropDownByXPath(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByID")) { return this.selectRadioButtonByID(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByXPath")) { return this.selectRadioButtonByXPath(data, dataRowID); }
		else if (actionName.equals("clickOnViewAllDrivingSurveysLink")) { return this.clickOnViewAllDrivingSurveysLink(data, dataRowID); }
		else if (actionName.equals("clickOnFirstOnlineSurveyorLink")) { return this.clickOnFirstOnlineSurveyorLink(data, dataRowID); }
		else if (actionName.equals("clickOnFirstMatchingDrivingSurvey")) { return this.clickOnFirstMatchingDrivingSurvey(data, dataRowID); }

		return false;
	}
}
