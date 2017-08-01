package androidapp.screens.source;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import androidapp.entities.source.LeakListInfoEntity;
import androidapp.entities.source.OtherSourceListInfoEntity;
import common.source.Log;
import common.source.PollManager;
import common.source.RegexUtility;
import io.appium.java_client.pagefactory.AndroidFindBy;
import surveyor.scommon.mobile.source.LeakDataTypes.SourceType;

public class AndroidAddedSourceListDialog extends AndroidBaseScreen {

	private static final String CANCEL_BUTTON_UI_SELECTOR = "new UiSelector().text(\"Cancel\")";
	private static final String ADD_OTHER_SOURCES_BUTTON_UI_SELECTOR = "new UiSelector().text(\"Add Other Sources\")";
	private static final String ADD_LEAK_BUTTON_UI_SELECTOR = "new UiSelector().text(\"Add Leak\")";

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.widget.TextView[1]")
	@CacheLookup
	private WebElement headerElement;

	/****** Button elements ******/

	@AndroidFindBy(uiAutomator = ADD_LEAK_BUTTON_UI_SELECTOR)
	private WebElement addLeak;

	@AndroidFindBy(uiAutomator = ADD_OTHER_SOURCES_BUTTON_UI_SELECTOR)
	private WebElement addOtherSources;

	@AndroidFindBy(xpath = CANCEL_BUTTON_UI_SELECTOR)
	private WebElement cancel;

	/****** ListView elements ******/

	private static final String SOURCES_LIST_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.TextView[2]";
	@AndroidFindBy(xpath = SOURCES_LIST_XPATH)
	private List<WebElement> sourcesList;

	public AndroidAddedSourceListDialog(WebDriver driver) {
		super(driver);
	}

	public String getHeaderTitle() {
		return headerElement.getText();
	}

	public String getHeaderStatus() {
		Log.method("getHeaderStatus");
		String regexPattern = RegexUtility.INV_ADD_LEAK_STATUS_HEADER_REGEX;
		String headerTitle = getHeaderTitle();
		String headerStatus = "";
		if (RegexUtility.matchesPattern(headerTitle, regexPattern)) {
			List<String> matchingGroups = RegexUtility.getMatchingGroups(headerTitle, regexPattern, true);
			String matchedText = matchingGroups.get(matchingGroups.size()-1);
			matchingGroups.forEach(t -> Log.info("Matching Group: " + t));
			Log.info(String.format("Matched Text=[%s]. Pattern=[%s]; Text=[%s]", matchedText, regexPattern, headerTitle));
			if (matchingGroups.size()>2) {
				headerStatus = matchingGroups.get(2);
			}
		}

		return headerStatus;
	}

	@SuppressWarnings("unchecked")
	private void refetchSourcesList() {
		Log.method("refetchSourcesList");
		sourcesList = getAndroidDriver().findElementsByXPath(SOURCES_LIST_XPATH);
	}

	/****** Button Methods ******/

	public WebElement getAddLeakButton() {
		Log.method("getAddLeakButton");
		addLeak = getAndroidDriver().findElementByAndroidUIAutomator(ADD_LEAK_BUTTON_UI_SELECTOR);
		return addLeak;
	}

	public void clickOnAddLeak() {
		Log.method("clickOnAddLeak");
		tap(getAddLeakButton());
	}

	public WebElement getAddOtherSourcesButton() {
		Log.method("getAddOtherSourcesButton");
		addOtherSources = getAndroidDriver().findElementByAndroidUIAutomator(ADD_OTHER_SOURCES_BUTTON_UI_SELECTOR);
		return addOtherSources;
	}

	public void clickOnAddOtherSources() {
		Log.method("clickOnAddOtherSources");
		tap(getAddOtherSourcesButton());
	}

	public WebElement getCancelButton() {
		Log.method("getCancelButton");
		cancel = getAndroidDriver().findElementByAndroidUIAutomator(CANCEL_BUTTON_UI_SELECTOR);
		return cancel;
	}

	public void clickOnCancel() {
		Log.method("clickOnCancel");
		tap(getCancelButton());
	}

	public void clickOnFirstMatchingListItemOfType(SourceType sourceType) {
		Log.method("clickOnFirstMatchingListItemOfType", sourceType);
		refetchSourcesList();
		for (WebElement srcEl : sourcesList) {
			String text = srcEl.getText();
			List<String> textParts = RegexUtility.split(text, RegexUtility.VERTICAL_BAR_SPLIT_REGEX_PATTERN);
			if (textParts.size()==3) {
				if (sourceType.equals(SourceType.Leak)) {
					clickOnLeakListItem(srcEl, textParts);
					break;
				}
			} else {
				if (sourceType.equals(SourceType.OtherSource)) {
					clickOnOtherSourceListItem(srcEl, textParts);
					break;
				}
			}
		}
	}

	public void clickOnMatchingListItemOfTypeAtIndex(SourceType sourceType, Integer index) {
		Log.method("clickOnMatchingListItemOfTypeAtIndex", sourceType, index);
		int idx = 0;
		refetchSourcesList();
		for (WebElement srcEl : sourcesList) {
			String text = srcEl.getText();
			List<String> textParts = RegexUtility.split(text, RegexUtility.VERTICAL_BAR_SPLIT_REGEX_PATTERN);
			if (textParts.size()==3) {
				if (sourceType.equals(SourceType.Leak)) {
					if (idx == index) {
						clickOnLeakListItem(srcEl, textParts);
						break;
					}

					idx++;
				}
			} else {
				if (sourceType.equals(SourceType.OtherSource)) {
					if (idx == index) {
						clickOnOtherSourceListItem(srcEl, textParts);
						break;
					}

					idx++;
				}
			}
		}
	}

	/****** ListView Methods ******/

	public List<LeakListInfoEntity> getLeaksList() {
		Log.method("getLeaksList");
		List<Object> sources = getSourcesList(true /*fetchLeaks*/, false /*fetchOtherSources*/);
		return toLeakList(sources);
	}

	public List<OtherSourceListInfoEntity> getOtherSourcesList() {
		Log.method("getOtherSourcesList");
		List<Object> sources = getSourcesList(false /*fetchLeaks*/, true /*fetchOtherSources*/);
		return toOtherSourceList(sources);
	}

	public List<Object> getAllSourcesList() {
		Log.method("getAllSourcesList");
		return getSourcesList(true /*fetchLeaks*/, true /*fetchOtherSources*/);
	}

	/****** Other Methods ******/

	public boolean isListDisplayed() {
		Log.method("isListDisplayed");
		StringBuilder listShown = new StringBuilder();
		PollManager.poll(() -> {
				try {
					List<Object> allSourcesList = getAllSourcesList();
					boolean ret = allSourcesList!=null && allSourcesList.size()>0;
					if (ret) {
						listShown.append("true");
					}
				} catch (Exception ex) { /* Ignore errors. */ }
				return !listShown.toString().equalsIgnoreCase("true");
			}, 2 /*waitInMSecsBetweenPoll*/, 3 /*maxRetries*/);

		return listShown.toString().equalsIgnoreCase("true");
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("screenLoadCondition");
		List<Object> allSourcesList = getAllSourcesList();
		if (sourcesList == null || sourcesList.size()==0 || getAllSourcesList().size()==0) {
			return getAddOtherSourcesButton()!=null && getAddOtherSourcesButton().isDisplayed();
		}

		boolean addOtherSourcesButtonNotNull = getAddOtherSourcesButton()!=null;
		boolean addOtherSourcesButtonIsDisplayed = getAddOtherSourcesButton().isDisplayed();
		boolean addOtherSourcesButtonCheck = addOtherSourcesButtonNotNull && addOtherSourcesButtonIsDisplayed;
		boolean allSourcesListNotNull = allSourcesList!=null;
		boolean allSourcesListSizeGreaterThanZero = allSourcesList.size()>0;
		boolean allSourcesListCheck = allSourcesListNotNull && allSourcesListSizeGreaterThanZero;
		Log.info(String.format("addOtherSourcesButtonNotNull=[%b]; addOtherSourcesButtonIsDisplayed=[%b]; allSourcesListNotNull=[%b]; allSourcesListSizeGreaterThanZero=[%b]; allSourcesList.size()=[%d]",
				addOtherSourcesButtonNotNull, addOtherSourcesButtonIsDisplayed, allSourcesListNotNull, allSourcesListSizeGreaterThanZero, allSourcesList.size()));
		return addOtherSourcesButtonCheck && allSourcesListCheck;
	}

	@Override
	public Boolean screenAndDataLoadCondition() {
		Log.method("screenAndDataLoadCondition");
		List<Object> allSourcesList = getAllSourcesList();
		return (allSourcesList!=null) && (allSourcesList.size()>0);
	}

	private void clickOnLeakListItem(WebElement leakElement, List<String> textParts) {
		String id = textParts.get(0).trim();
		String time = textParts.get(1).trim();
		String address = textParts.get(2).trim();
		Log.info(String.format("Clicking on a matching Leak found -> [id=%s; time=%s; address=%s]", id, time, address));
		leakElement.click();
	}

	private void clickOnOtherSourceListItem(WebElement otherSourceElement, List<String> textParts) {
		String source = textParts.get(0).trim();
		String time = textParts.get(1).trim();
		Log.info(String.format("Clicking on a matching Other Source found -> [source=%s; time=%s]", source, time));
		otherSourceElement.click();
	}

	private List<LeakListInfoEntity> toLeakList(List<Object> sources) {
		return sources.stream()
			.map(e -> (LeakListInfoEntity)e)
			.collect(Collectors.toList());
	}

	private List<OtherSourceListInfoEntity> toOtherSourceList(List<Object> sources) {
		if (sources == null) {
			return null;
		}

		return sources.stream()
			.map(e -> (OtherSourceListInfoEntity)e)
			.collect(Collectors.toList());
	}

	private List<Object> getSourcesList(boolean fetchLeaks, boolean fetchOtherSources) {
		Log.method("getSourcesList");
		List<Object> retList = new ArrayList<Object>();
		refetchSourcesList();
		for (WebElement srcEl : sourcesList) {
			String text = srcEl.getText();
				if (text.contains("|")) {
				List<String> textParts = RegexUtility.split(text, RegexUtility.VERTICAL_BAR_SPLIT_REGEX_PATTERN);
				if (textParts.size()==3) {
					if (fetchLeaks) {
						String id = textParts.get(0).trim();
						String time = textParts.get(1).trim();
						String address = textParts.get(2).trim();
						Log.info(String.format("Creating new ListLeakInfoEntity -> [id=%s; time=%s; address=%s]", id, time, address));
						retList.add(new LeakListInfoEntity(id, time, address));
					}
				} else {
					if (fetchOtherSources) {
						String source = textParts.get(0).trim();
						String time = textParts.get(1).trim();
						Log.info(String.format("Creating new OtherSourceListInfoEntity -> [source=%s; time=%s]", source, time));
						retList.add(new OtherSourceListInfoEntity(source, time));
					}
				}
			}
		}

		return retList;
	}
}
