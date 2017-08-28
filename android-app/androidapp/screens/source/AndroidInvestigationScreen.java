package androidapp.screens.source;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import common.source.Log;
import common.source.LogHelper;
import common.source.MobileActions;
import common.source.MobileActions.KeyCode;
import common.source.MobileActions.SwipeDirection;
import common.source.Timeout;
import io.appium.java_client.pagefactory.AndroidFindBy;
import androidapp.entities.source.InvestigationEntity;

public class AndroidInvestigationScreen extends AndroidBaseScreen {
	private static final String LIST_VIEW_ELEMENTS_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView";
	private static final String CONTAINER_VIEW_GROUP_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.ScrollView[1]";
	private static final Integer SWIPE_DELTA = 600;

	@AndroidFindBy(xpath = LIST_VIEW_ELEMENTS_XPATH)
	private List<WebElement> listViewElements;

	@AndroidFindBy(xpath = CONTAINER_VIEW_GROUP_XPATH)
	@CacheLookup
	private WebElement containerViewGroup;

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

	public void clickOnFirstInvestigation() throws Exception {
		Log.method("clickOnFirstInvestigation");
		getFirstRowViewGroup();
		selectRowGroup(firstRowViewGroup);
	}

	public void tapOnFirstInvestigation() throws Exception {
		Log.method("tapOnFirstInvestigation");
		getFirstRowViewGroup();
		tap(firstRowViewGroup);
	}

	public List<InvestigationEntity> getInvestigations() {
		Log.method("getInvestigations");
		return getInvestigations(true /*isFirstPage*/);
	}

	private List<InvestigationEntity> getInvestigations(boolean isFirstPage) {
		Log.method("getInvestigations", isFirstPage);
		List<InvestigationEntity> invList = new ArrayList<InvestigationEntity>();
		if (this.listViewElements != null) {
			int size = this.listViewElements.size();
			if (size>1) {
				int startIdx = 0;
				if (isFirstPage) {
					startIdx = 1;
				}

				for (int i = startIdx; (i < size) && ((i + 1) < size); i+=2) {
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

	public List<InvestigationEntity> getInvestigations(Integer numberOfPages) throws Exception {
		Log.method("getInvestigations", numberOfPages);
		List<InvestigationEntity> invList = new ArrayList<InvestigationEntity>();
		for (int i = 0; i < numberOfPages; i++) {
			if (i > 0) {
				scrollToNextPage();
				refreshListViewElements();
			}

			getInvestigations(i == 0).stream().forEach(inv -> {
				if (!hasInvestigationEntityInList(invList, inv)) {
					invList.add(inv);
				}
			});
		}

		return invList;
	}

	public void performSearch(String searchKeyword) throws Exception {
		Log.method("performSearch", searchKeyword);
		// Search using only first 9 characters. This workaround is for preventing queued responses in app from taking long time. Perf issue being resolved in product.
		if (searchKeyword.length()>9) {
			searchKeyword = searchKeyword.substring(0, 9);
		}

		WebElement srchEditView = getSearchEditView();
		srchEditView.click();
		sendKeys(srchEditView, searchKeyword);
		waitForSearchResultsToLoad(searchKeyword);
	}

	public WebElement getSearchEditView() {
		return searchEditView;
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("AndroidInvestigationScreen.screenLoadCondition");
		boolean searchEditViewShown = searchEditView!=null && searchEditView.isDisplayed();
		Log.info(String.format("searchEditViewShown=[%b]", searchEditViewShown));
		return searchEditViewShown && waitForProgressComplete();
	}

	@Override
	protected Integer getScreenLoadTimeout() {
		return Timeout.ANDROID_APP_SCREEN_LOAD_TIMEOUT * 2;
	}

	public void scrollToNextPage() throws Exception {
		Log.method("scrollToNextPage");
		MobileActions.newAction(getAndroidDriver()).swipeFromCenter(this.containerViewGroup, SwipeDirection.UP, SWIPE_DELTA);
	}

	public void scrollToPreviousPage() throws Exception {
		Log.method("scrollToPreviousPage");
		MobileActions.newAction(getAndroidDriver()).swipeFromCenter(this.containerViewGroup, SwipeDirection.DOWN, SWIPE_DELTA);
	}

	public void waitForResultsToLoad() {
		Log.method("waitForResultsToLoad");
		waitForScreenLoad(Timeout.ANDROID_APP_RESULTS_TIMEOUT * 2, d -> isFirstRowPresent());
	}

	private void getFirstRowViewGroup() {
		firstRowViewGroup = getAndroidDriver().findElementByXPath("//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]");
	}

	private void selectRowGroup(WebElement rowGroup) throws Exception {
		tap(rowGroup);
		press(rowGroup);
		clickAndPressKey(rowGroup, KeyCode.KEYCODE_ENTER);
	}

	private boolean hasInvestigationEntityInList(List<InvestigationEntity> list, InvestigationEntity entity) {
		return list.stream().anyMatch(inv -> inv.getReportTitle().equalsIgnoreCase(entity.getReportTitle()));
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

	private boolean isFirstRowPresent() {
		Log.method("isFirstRowPresent");
		firstRowReportTitle = getAndroidDriver().findElementByXPath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView[1]");
		return firstRowReportTitle != null && firstRowReportTitle.isDisplayed();
	}

	private void refreshListViewElements() {
		Log.method("refreshListViewElements");
		this.listViewElements = getAndroidDriver().findElementsByXPath(LIST_VIEW_ELEMENTS_XPATH);
	}

	private void waitForSearchResultsToLoad(String searchKeyword) {
		Log.method("waitForSearchResultsToLoad", searchKeyword);
		waitForScreenLoad(Timeout.ANDROID_APP_SEARCH_RESULTS_TIMEOUT * 3, d -> waitForProgressComplete() && isFirstEntryMatchingSearchKeyword(searchKeyword));
	}
}