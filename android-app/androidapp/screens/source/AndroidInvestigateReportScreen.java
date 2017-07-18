package androidapp.screens.source;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import common.source.Log;
import common.source.Timeout;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import androidapp.entities.source.InvestigationMarkerEntity;

public class AndroidInvestigateReportScreen extends AndroidBaseScreen {

	private static final String CHILD_TEXTVIEW_CLSNAME = "android.widget.TextView";

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")
	private List<WebElement> listViewElements;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView[1]")
	@CacheLookup
	private WebElement firstRowReportId;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView[2]")
	@CacheLookup
	private WebElement firstRowInvestigationStatus;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.TextView[2]")
	@CacheLookup
	private WebElement noInvestigationMarkersFoundTextView;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView[1]")
	@CacheLookup
	private WebElement investigationMarkersContainerView;

	@AndroidFindBy(xpath = "//android.widget.ScrollView[1]/android.view.ViewGroup[1]/android.widget.Spinner[1]")
	@CacheLookup
	private WebElement markerTypeSelector;

	public AndroidInvestigateReportScreen(WebDriver driver) {
		super(driver);
	}

	public void clickOnFirstInvestigationMarker() {
		Log.method("clickOnFirstInvestigationMarker");
		if (this.firstRowReportId != null) {
			firstRowReportId.click();
		}
	}

	public void clickOnInvestigationMarkerType() {
		Log.method("clickOnInvestigationMarkerType");
		markerTypeSelector.click();
	}

	public InvestigationMarkerEntity getFirstInvestigationMarker() {
		Log.method("getFirstInvestigationMarker");
		InvestigationMarkerEntity invEntity = new InvestigationMarkerEntity();
		invEntity.setReportTitle(firstRowReportId.getText());
		invEntity.setReportName(firstRowInvestigationStatus.getText());
		return invEntity;
	}

	public Integer getInvestigationMarkersCount() {
		Log.method("getInvestigationReportsCount");
		return (this.listViewElements == null) ? 0 : this.listViewElements.size();
	}

	public List<InvestigationMarkerEntity> getInvestigationMarkers() {
		Log.method("getInvestigationReports");
		List<InvestigationMarkerEntity> invReportList = new ArrayList<InvestigationMarkerEntity>();
		for (WebElement el : this.listViewElements) {
			List<WebElement> findElements = el.findElements(MobileBy.className(CHILD_TEXTVIEW_CLSNAME));
			if (findElements != null && findElements.size() > 1) {
				InvestigationMarkerEntity invEntity = new InvestigationMarkerEntity();
				invEntity.setReportTitle(findElements.get(0).getText());
				invEntity.setReportName(findElements.get(1).getText());
				invReportList.add(invEntity);
			}
		}

		return invReportList;
	}

	@SuppressWarnings("unchecked")
	public void reInitializeListItems() {
		this.listViewElements = getAndroidDriver().findElementsByXPath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup");
	}

	public boolean verifyNoInvestigationMarkersFoundInReport() {
		Log.method("verifyNoInvestigationMarkersFoundInReport");
		return noInvestigationMarkersFoundTextView.isDisplayed();
	}

	@Override
	protected Integer getScreenLoadTimeout() {
		return Timeout.ANDROID_APP_SCREEN_LOAD_TIMEOUT * 2;
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("screenLoadCondition");
		return investigationMarkersContainerView!=null && investigationMarkersContainerView.isDisplayed()
				&& markerTypeSelector!=null && markerTypeSelector.isDisplayed();
	}
}