package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;
import common.source.TestSetup;
import surveyor.scommon.source.HomePage;

public class HomePageActions extends BasePageActions {

	public HomePageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL);
		initializePageObject(driver, new HomePage(driver, strBaseURL, testSetup));
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
	 * Executes verifyPageLoaded action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyPageLoaded(String data, Integer dataRowID) {
		logAction("HomePageActions.verifyPageLoaded", data, dataRowID);
		getHomePage().waitForPageLoad();
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
		getHomePage().clickOnViewAllDrivingSurveysLink();
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
		getHomePage().waitForFirstDrivingSurveyToBeCompleted();
		getHomePage().clickOnFirstMatchingDrivingSurvey(data);
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
		getHomePage().clickOnFirstOnlineSurveyorLink();
		return true;
	}

	/* Invoke action using specified ActionName */
	@Override
	public boolean invokeAction(String actionName, String data, Integer dataRowID) throws Exception {
		if (actionName.equals("clickById")) { return this.clickById(data, dataRowID); }
		else if (actionName.equals("clickByIdAndWait")) { return this.clickByIdAndWait(data, dataRowID); }
		else if (actionName.equals("clickByXPath")) { return this.clickByXPath(data, dataRowID); }
		else if (actionName.equals("clickByXPathAndWait")) { return this.clickByXPathAndWait(data, dataRowID); }
		else if (actionName.equals("clickOnViewAllDrivingSurveysLink")) { return this.clickOnViewAllDrivingSurveysLink(data, dataRowID); }
		else if (actionName.equals("clickOnFirstOnlineSurveyorLink")) { return this.clickOnFirstOnlineSurveyorLink(data, dataRowID); }
		else if (actionName.equals("clickOnFirstMatchingDrivingSurvey")) { return this.clickOnFirstMatchingDrivingSurvey(data, dataRowID); }
		else if (actionName.equals("insertTextById")) { return this.insertTextById(data, dataRowID); }
		else if (actionName.equals("insertTextByXPath")) { return this.insertTextByXPath(data, dataRowID); }
		else if (actionName.equals("open")) { return this.open(data, dataRowID); }
		else if (actionName.equals("selectDropDownByID")) { return this.selectDropDownByID(data, dataRowID); }
		else if (actionName.equals("selectDropDownByXPath")) { return this.selectDropDownByXPath(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByID")) { return this.selectRadioButtonByID(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByXPath")) { return this.selectRadioButtonByXPath(data, dataRowID); }
		else if (actionName.equals("sortRecordsBy")) { return this.sortRecordsBy(data, dataRowID); }
		else if (actionName.equals("verifyPageLoaded")) { return this.verifyPageLoaded(data, dataRowID); }

		return false;
	}

	public HomePage getHomePage() {
		return (HomePage)this.getPageObject();
	}
}
