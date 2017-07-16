package androidapp.screens.source;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import androidapp.entities.source.LeakListInfoEntity;
import common.source.Log;
import common.source.RegexUtility;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidAddedLeakListDialog extends AndroidBaseScreen {

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
	private List<WebElement> leaksList;

	public AndroidAddedLeakListDialog(WebDriver driver) {
		super(driver);
	}

	public String getHeaderTitle() {
		return headerElement.getText();
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

	/****** ListView Methods ******/

	public List<LeakListInfoEntity> getLeaksList() {
		Log.method("getLeaksList");
		List<LeakListInfoEntity> retList = new ArrayList<LeakListInfoEntity>();
		for (WebElement leakEl : leaksList) {
			String text = leakEl.getText();
			List<String> textParts = RegexUtility.split(text, RegexUtility.VERTICAL_BAR_SPLIT_REGEX_PATTERN);
			String id = textParts.get(0).trim();
			String time = textParts.get(1).trim();
			String address = textParts.get(2).trim();
			Log.info(String.format("Creating new ListLeakInfoEntity -> [id=%s; time=%s; address=%s]", id, time, address));
			retList.add(new LeakListInfoEntity(id, time, address));
		}

		return retList;
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("screenLoadCondition");
		return getAddOtherSourcesButton()!=null && getAddOtherSourcesButton().isDisplayed();
	}
}
