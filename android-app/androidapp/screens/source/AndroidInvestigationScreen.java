package androidapp.screens.source;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.source.Log;
import common.source.Timeout;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import androidapp.entities.source.InvestigationEntity;

public class AndroidInvestigationScreen extends AndroidBaseScreen {

	private static final String CHILD_TEXTVIEW_CLSNAME = "android.widget.TextView";

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")
	private List<WebElement> listViewElements;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.EditText")
	private WebElement searchEditView;

	public AndroidInvestigationScreen(WebDriver driver) {
		super(driver);
	}

	public void clickOnFirstInvestigation() {
		Log.method("clickOnFirstInvestigation");
		if (this.listViewElements != null) {
			WebElement webElement = this.listViewElements.get(0);
			if (webElement != null) {
				webElement.click();
			}
		}
	}

	public List<InvestigationEntity> getInvestigations() {
		Log.method("getInvestigations");
		List<InvestigationEntity> invList = new ArrayList<InvestigationEntity>();
		for (WebElement el : this.listViewElements) {
			List<WebElement> findElements = el.findElements(MobileBy.className(CHILD_TEXTVIEW_CLSNAME));
			if (findElements != null && findElements.size() > 1) {
				InvestigationEntity invEntity = new InvestigationEntity();
				invEntity.setReportTitle(findElements.get(0).getText());
				invEntity.setReportName(findElements.get(1).getText());
				invList.add(invEntity);
			}
		}

		return invList;
	}

	public void performSearch(String searchKeyword) {
		Log.method("enterSearchText", searchKeyword);
		getSearchEditView().sendKeys(searchKeyword);
		getSearchEditView().sendKeys(Keys.RETURN);
		waitForSearchResultsToLoad(searchKeyword);
	}

	public WebElement getSearchEditView() {
		return searchEditView;
	}

	@Override
	public Boolean screenLoadCondition() {
		return mainFrameLayout!=null && mainFrameLayout.isDisplayed() &&
				searchEditView!=null && searchEditView.isDisplayed();
	}

	private boolean isFirstEntryMatchingSearchKeyword(String searchKeyword) {
		Log.method("isFirstEntryMatchingSearchKeyword", searchKeyword);
		this.listViewElements = driver.findElements(By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup"));
		if (this.listViewElements != null && this.listViewElements.size() > 0) {
			String reportId = this.listViewElements.get(1).getText();
			return reportId.contains(searchKeyword);
		}

		return false;
	}

	private void waitForSearchResultsToLoad(String searchKeyword) {
		Log.method("waitForSearchResultsToLoad", searchKeyword);
		waitForScreenLoad(Timeout.ANDROID_APP_SEARCH_RESULTS_TIMEOUT, d -> isFirstEntryMatchingSearchKeyword(searchKeyword));
	}
}