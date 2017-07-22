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
import common.source.RegexUtility;
import io.appium.java_client.pagefactory.AndroidFindBy;
import surveyor.scommon.mobile.source.LeakDataTypes.SourceType;

public class AndroidAddedSourceListDialog extends AndroidBaseScreen {

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.widget.TextView[1]")
	@CacheLookup
	private WebElement headerElement;

	/****** Button elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]")
	@CacheLookup
	private WebElement addLeak;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[2]")
	@CacheLookup
	private WebElement addOtherSources;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[3]")
	@CacheLookup
	private WebElement cancel;

	/****** ListView elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.TextView[2]")
	@CacheLookup
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

	/****** Button Methods ******/

	public WebElement getAddLeakButton() {
		Log.method("getAddLeakButton");
		return addLeak;
	}

	public void clickOnAddLeak() {
		Log.method("clickOnAddLeak");
		tap(getAddLeakButton());
	}

	public WebElement getAddOtherSourcesButton() {
		Log.method("getAddOtherSourcesButton");
		return addOtherSources;
	}

	public void clickOnAddOtherSources() {
		Log.method("clickOnAddOtherSources");
		tap(getAddOtherSourcesButton());
	}

	public WebElement getCancelButton() {
		Log.method("getCancelButton");
		return cancel;
	}

	public void clickOnCancel() {
		Log.method("clickOnCancel");
		tap(getCancelButton());
	}

	public void clickOnFirstMatchingListItemOfType(SourceType sourceType) {
		Log.method("clickOnFirstMatchingListItemOfType", sourceType);
		for (WebElement srcEl : sourcesList) {
			String text = srcEl.getText();
			List<String> textParts = RegexUtility.split(text, RegexUtility.VERTICAL_BAR_SPLIT_REGEX_PATTERN);
			if (textParts.size()==3) {
				if (sourceType.equals(SourceType.Leak)) {
					String id = textParts.get(0).trim();
					String time = textParts.get(1).trim();
					String address = textParts.get(2).trim();
					Log.info(String.format("Clicking on a matching Leak found -> [id=%s; time=%s; address=%s]", id, time, address));
					srcEl.click();
					break;
				}
			} else {
				String source = textParts.get(0).trim();
				String time = textParts.get(1).trim();
				Log.info(String.format("Clicking on a matching Other Source found -> [source=%s; time=%s]", source, time));
				srcEl.click();
				break;
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

	private List<LeakListInfoEntity> toLeakList(List<Object> sources) {
		return sources.stream()
			.map(e -> (LeakListInfoEntity)e)
			.collect(Collectors.toList());
	}

	private List<OtherSourceListInfoEntity> toOtherSourceList(List<Object> sources) {
		return sources.stream()
			.map(e -> (OtherSourceListInfoEntity)e)
			.collect(Collectors.toList());
	}

	private List<Object> getSourcesList(boolean fetchLeaks, boolean fetchOtherSources) {
		Log.method("getSourcesList");
		List<Object> retList = new ArrayList<Object>();
		for (WebElement srcEl : sourcesList) {
			String text = srcEl.getText();
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

		return retList;
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("screenLoadCondition");
		return getAddOtherSourcesButton()!=null && getAddOtherSourcesButton().isDisplayed();
	}
}
