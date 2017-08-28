package androidapp.screens.source;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import common.source.Log;
import common.source.LogHelper;
import common.source.Timeout;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.StoredProcLisaInvestigationShowIndication;
import androidapp.entities.source.InvestigationMarkerEntity;

public class AndroidInvestigateReportScreen extends AndroidBaseScreen {
	private static final String MARKER_STATUS_XPATH_WITH_IDX_PLACEHOLDER = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[%d]/android.widget.TextView[2]";
	private static final String MARKER_NAME_XPATH_WITH_IDX_PLACEHOLDER = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[%d]/android.widget.TextView[1]";
	private static final String CHILD_TEXTVIEW_CLSNAME = "android.widget.TextView";
	private static final String LIST_ITEMS_XPATH = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup";

	@AndroidFindBy(xpath = LIST_ITEMS_XPATH)
	private List<WebElement> listViewElements;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView[1]")
	@CacheLookup
	private WebElement firstRowLisaNumber;

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
		if (this.firstRowLisaNumber != null) {
			firstRowLisaNumber.click();
		}
	}

	public int clickFirstMarkerMatchingStatus(List<String> markerStatuses) {
		Log.method("clickFirstMarkerMatchingStatus", LogHelper.collectionToString(markerStatuses, "markerStatuses"));
		return clickMarkerMatchingStatusAtIndex(markerStatuses, 0 /*index*/);
	}

	public int clickMarkerMatchingStatusAtIndex(List<String> markerStatuses, Integer index) {
		Log.method("clickFirstMarkerMatchingStatus", LogHelper.collectionToString(markerStatuses, "markerStatuses"), index);
		List<InvestigationMarkerEntity> investigationMarkers = getInvestigationMarkers();
		int len = investigationMarkers.size();
		int idx = -1;
		int clickIdx = -1;
		if (investigationMarkers != null && len > 0) {
			for (int i = 1; i <= len; i++) {
				WebElement elemMarkerStatus = getAndroidDriver().findElementByXPath(String.format(MARKER_STATUS_XPATH_WITH_IDX_PLACEHOLDER, i));
				String status = elemMarkerStatus.getText();
				if (markerStatuses.contains(status)) {
					idx = i;
					clickIdx++;
					if (clickIdx == index) {
						Log.info(String.format("Found matching marker for status='%s' at index=[%d], clickIndex=[%d].. Tapping marker.", status, idx, clickIdx));
						elemMarkerStatus.click();
						break;
					}
				}
			}
		}

		return idx;
	}

	public int clickLastMarkerMatchingStatus(List<String> markerStatuses) {
		Log.method("clickLastMarkerMatchingStatus", LogHelper.collectionToString(markerStatuses, "markerStatuses"));
		return clickMarkerMatchingStatusAtIndexFromEnd(markerStatuses, 0 /*indexFromEnd*/);
	}

	public int clickMarkerMatchingStatusAtIndexFromEnd(List<String> markerStatuses, Integer indexFromEnd) {
		Log.method("clickLastMarkerMatchingStatus", LogHelper.collectionToString(markerStatuses, "markerStatuses"), indexFromEnd);
		List<InvestigationMarkerEntity> investigationMarkers = getInvestigationMarkers();
		int len = investigationMarkers.size();
		int idx = -1;
		int clickIdxFromEnd = -1;
		if (investigationMarkers != null && len > 0) {
			for (int i = len-1; i >= 0; i--) {
				WebElement elemMarkerStatus = getAndroidDriver().findElementByXPath(String.format(MARKER_STATUS_XPATH_WITH_IDX_PLACEHOLDER, i));
				String status = elemMarkerStatus.getText();
				if (markerStatuses.contains(status)) {
					idx = i;
					clickIdxFromEnd++;
					if (clickIdxFromEnd == indexFromEnd) {
						Log.info(String.format("Found matching marker (from end) for status='%s' at index=[%d], clickIndexFromEnd=[%d].. Tapping marker.", status, idx, clickIdxFromEnd));
						elemMarkerStatus.click();
						break;
					}
				}
			}
		}

		return idx;
	}


	public void clickOnInvestigationMarkerType() {
		Log.method("clickOnInvestigationMarkerType");
		markerTypeSelector.click();
	}

	public void clickOnMarkerAtIndex(int index) {
		Log.method("clickOnMarkerAtIndex", index);
		WebElement element = getAndroidDriver().findElementByXPath(String.format(MARKER_NAME_XPATH_WITH_IDX_PLACEHOLDER, index));
		String markerName = element.getText();
		Log.info(String.format("Found matching marker='%s' at index=[%d].. Tap marker.", markerName, index));
		element.click();
	}

	public InvestigationMarkerEntity getFirstInvestigationMarker() {
		Log.method("getFirstInvestigationMarker");
		InvestigationMarkerEntity invEntity = new InvestigationMarkerEntity();
		invEntity.setMarkerNumber(firstRowLisaNumber.getText());
		invEntity.setInvestigationStatus(firstRowInvestigationStatus.getText());
		return invEntity;
	}

	public Integer getInvestigationMarkersCount() {
		Log.method("getInvestigationReportsCount");
		return (this.listViewElements == null) ? 0 : this.listViewElements.size();
	}

	public List<InvestigationMarkerEntity> getInvestigationMarkers() {
		Log.method("getInvestigationReports");
		reInitializeListItems();
		List<InvestigationMarkerEntity> invMarkersList = new ArrayList<InvestigationMarkerEntity>();
		for (WebElement el : this.listViewElements) {
			List<WebElement> findElements = el.findElements(MobileBy.className(CHILD_TEXTVIEW_CLSNAME));
			if (findElements != null && findElements.size() > 1) {
				InvestigationMarkerEntity invEntity = new InvestigationMarkerEntity();
				invEntity.setMarkerNumber(findElements.get(0).getText());
				invEntity.setInvestigationStatus(findElements.get(1).getText());
				invMarkersList.add(invEntity);
			}
		}

		Log.info(LogHelper.collectionToString(invMarkersList, "Investigation markers from screen"));
		return invMarkersList;
	}

	@SuppressWarnings("unchecked")
	public void reInitializeListItems() {
		this.listViewElements = getAndroidDriver().findElementsByXPath(LIST_ITEMS_XPATH);
	}

	public boolean verifyNoInvestigationMarkersFoundInReport() {
		Log.method("verifyNoInvestigationMarkersFoundInReport");
		return noInvestigationMarkersFoundTextView.isDisplayed();
	}

	public boolean verifyMarkersForReportAreShown(String reportTitle) {
		Log.method("verifyMarkersForReportAreShown", reportTitle);
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		Log.info(String.format("Getting assigned LISAs for report id='%s'", reportId));
		List<InvestigationMarkerEntity> invMarkers = getInvestigationMarkers();
		List<StoredProcLisaInvestigationShowIndication> investigationMarkersFromSP = StoredProcLisaInvestigationShowIndication.getLisaInvestigation(reportId);
		boolean match = invMarkers.stream()
			.allMatch(s -> {
				return investigationMarkersFromSP.stream().anyMatch(sp -> {
					String[] split = s.getMarkerNumber().split("-");
					String lisaNum = split[split.length-1].trim();
					Log.info(String.format("Matching boxNumber from storedproc-[%d] with on screen marker-'%s'; marker number='%s'",
							sp.getBoxNumber(), s.getMarkerNumber(), lisaNum));
					return lisaNum.equals(String.valueOf(sp.getBoxNumber()));
				});
			});

		return match;
	}

	@Override
	protected Integer getScreenLoadTimeout() {
		return Timeout.ANDROID_APP_SCREEN_LOAD_TIMEOUT * 2;
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("AndroidInvestigateReportScreen.screenLoadCondition");
		return investigationMarkersContainerView.isDisplayed() && markerTypeSelector.isDisplayed() && waitForProgressComplete();
	}
}