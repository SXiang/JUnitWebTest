package androidapp.screens.source;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import common.source.BaseHelper;
import common.source.Log;
import common.source.LogHelper;
import common.source.PollManager;
import common.source.TestContext;
import common.source.Timeout;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.StoredProcLisaInvestigationShowIndication;
import androidapp.entities.source.InvestigationEntity;
import androidapp.entities.source.InvestigationMarkerEntity;

public class AndroidInvestigateReportScreen extends AndroidBaseScreen {

	private static final String CHILD_TEXTVIEW_CLSNAME = "android.widget.TextView";

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")
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
		List<InvestigationMarkerEntity> investigationMarkers = getInvestigationMarkers();
		int len = investigationMarkers.size();
		int idx = -1;
		if (investigationMarkers != null && len > 0) {
			for (int i = 1; i <= len; i++) {
				WebElement elemMarkerStatus = getAndroidDriver().findElementByXPath(String.format("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[%d]/android.widget.TextView[2]", i));
				String status = elemMarkerStatus.getText();
				if (markerStatuses.contains(status)) {
					idx = i;
					Log.info(String.format("Found matching marker for status='%s' at index=[%d].. Tap marker.", status, idx));
					elemMarkerStatus.click();
					break;
				}
			}
		}

		return idx;
	}

	public void clickOnInvestigationMarkerType() {
		Log.method("clickOnInvestigationMarkerType");
		markerTypeSelector.click();
	}

	public InvestigationMarkerEntity getFirstInvestigationMarker() {
		Log.method("getFirstInvestigationMarker");
		InvestigationMarkerEntity invEntity = new InvestigationMarkerEntity();
		invEntity.setLisaNumber(firstRowLisaNumber.getText());
		invEntity.setInvestigationStatus(firstRowInvestigationStatus.getText());
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
				invEntity.setLisaNumber(findElements.get(0).getText());
				invEntity.setInvestigationStatus(findElements.get(1).getText());
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

	public boolean verifyLisasForReportAreShown(String reportTitle) {
		Log.method("verifyLisasForReportAreShown", reportTitle);
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		Log.info(String.format("Getting assigned LISAs for report id='%s'", reportId));
		List<InvestigationMarkerEntity> invMarkers = getInvestigationMarkers();
		List<StoredProcLisaInvestigationShowIndication> lisaInvestigationfromSP = StoredProcLisaInvestigationShowIndication.getLisaInvestigation(reportId);
		boolean match = lisaInvestigationfromSP.stream()
			.allMatch(sp -> {
				return invMarkers.stream().anyMatch(s -> {
					String[] split = s.getLisaNumber().split("-");
					String lisaNum = split[split.length-1].trim();
					Log.info(String.format("Matching boxNumber from storedproc-[%d] with on screen lisa marker-'%s'; lisa number='%s'",
							sp.getBoxNumber(), s.getLisaNumber(), lisaNum));
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
		Log.method("screenLoadCondition");
		// This intentional wait is to prevent appium from polling for elements while 'loading investigations..' screen is shown.
		TestContext.INSTANCE.stayIdle(3);
		return investigationMarkersContainerView.isDisplayed() && markerTypeSelector.isDisplayed();
	}
}