package surveyor.scommon.androidapp.source;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.source.Log;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import surveyor.scommon.androidapp.entities.InvestigateReportEntity;

public class AndroidInvestigateReportScreen extends AndroidBaseScreen {

	private static final String CHILD_TEXTVIEW_CLSNAME = "android.widget.TextView";

	@AndroidFindBy(className = "android.widget.ScrollView")
	private WebElement scrollableView;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")
	private List<WebElement> listViewElements;

	public AndroidInvestigateReportScreen(WebDriver driver) {
		super(driver);
	}

	public void clickOnFirstInvestigationReport() {
		Log.method("clickOnFirstInvestigationReport");
		if (this.listViewElements != null) {
			WebElement webElement = this.listViewElements.get(0);
			if (webElement != null) {
				webElement.click();
			}
		}
	}

	public List<InvestigateReportEntity> getInvestigationReports() {
		Log.method("getInvestigationReports");
		List<InvestigateReportEntity> invReportList = new ArrayList<InvestigateReportEntity>();
		for (WebElement el : this.listViewElements) {
			List<WebElement> findElements = el.findElements(MobileBy.className(CHILD_TEXTVIEW_CLSNAME));
			if (findElements != null && findElements.size() > 1) {
				InvestigateReportEntity invEntity = new InvestigateReportEntity();
				invEntity.setReportTitle(findElements.get(0).getText());
				invEntity.setReportName(findElements.get(1).getText());
				invReportList.add(invEntity);
			}
		}

		return invReportList;
	}

	@Override
	public Boolean screenLoadCondition() {
		return mainFrameLayout!=null && mainFrameLayout.isDisplayed();
	}
}
