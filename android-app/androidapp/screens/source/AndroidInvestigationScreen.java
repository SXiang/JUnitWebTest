package androidapp.screens.source;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import common.source.Log;
import common.source.LogHelper;
import common.source.MobileActions;
import common.source.TestContext;
import common.source.MobileActions.KeyCode;
import common.source.Timeout;
import io.appium.java_client.pagefactory.AndroidFindBy;
import androidapp.entities.source.InvestigationEntity;

public class AndroidInvestigationScreen extends AndroidBaseScreen {

	private static final String CHILD_TEXTVIEW_CLSNAME = "android.widget.TextView";

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView")
	@CacheLookup
	private List<WebElement> listViewElements;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.EditText")
	@CacheLookup
	private WebElement searchEditView;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]")
	@CacheLookup
	private WebElement firstRowViewGroup;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView[2]")
	@CacheLookup
	private WebElement firstRowReportTitle;

	public AndroidInvestigationScreen(WebDriver driver) {
		super(driver);
	}

	public void clickOnFirstInvestigation() {
		Log.method("clickOnFirstInvestigation");
		firstRowViewGroup = getAndroidDriver().findElementByXPath("//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]");
		tap(firstRowViewGroup);
	}

	public List<InvestigationEntity> getInvestigations() {
		Log.method("getInvestigations");
		List<InvestigationEntity> invList = new ArrayList<InvestigationEntity>();
		if (this.listViewElements != null) {
			int size = this.listViewElements.size();
			if (size>1) {
				for (int i = 1; i < size; i+=2) {
					InvestigationEntity invEntity = new InvestigationEntity();
					invEntity.setReportTitle(this.listViewElements.get(i).getText());
					invEntity.setReportName(this.listViewElements.get(i+1).getText());
					invList.add(invEntity);
				}
			}
		}

		Log.info(String.format("Found list rows -> %s", LogHelper.collectionToString(invList, "invList")));
		return invList;
	}

	public void performSearch(String searchKeyword) throws Exception {
		Log.method("enterSearchText", searchKeyword);
		sendKeys(getSearchEditView(), searchKeyword);
		MobileActions.newAction().pressKey(KeyCode.KEYCODE_ENTER);
		TestContext.INSTANCE.stayIdle(3);
		waitForSearchResultsToLoad(searchKeyword);
	}

	public WebElement getSearchEditView() {
		return searchEditView;
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("screenLoadCondition");
		boolean searchEditViewShown = searchEditView!=null && searchEditView.isDisplayed();
		Log.info(String.format("searchEditViewShown=[%b]", searchEditViewShown));
		return searchEditViewShown;
	}

	@SuppressWarnings("unchecked")
	private boolean isFirstEntryMatchingSearchKeyword(String searchKeyword) {
		Log.method("isFirstEntryMatchingSearchKeyword", searchKeyword);
		firstRowReportTitle = getAndroidDriver().findElementByXPath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView[1]");
		if (firstRowReportTitle != null) {
			String reportId = firstRowReportTitle.getText();
			Log.method(String.format("Found reportTitle element. Searching for-[%s], found-[%s]. Match = [%b]", searchKeyword, reportId, reportId.contains(searchKeyword)));
			return reportId.contains(searchKeyword);
		}

		Log.method("Match = [%b]", false);
		return false;
	}

	private void waitForSearchResultsToLoad(String searchKeyword) {
		Log.method("waitForSearchResultsToLoad", searchKeyword);
		waitForScreenLoad(Timeout.ANDROID_APP_SEARCH_RESULTS_TIMEOUT, d -> isFirstEntryMatchingSearchKeyword(searchKeyword));
	}
}