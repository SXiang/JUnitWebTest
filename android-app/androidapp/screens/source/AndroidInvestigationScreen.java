package androidapp.screens.source;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.source.Log;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import androidapp.entities.source.InvestigationEntity;

public class AndroidInvestigationScreen extends AndroidBaseScreen {

	private static final String CHILD_TEXTVIEW_CLSNAME = "android.widget.TextView";

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")
	private List<WebElement> listViewElements;

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

	@Override
	public Boolean screenLoadCondition() {
		return mainFrameLayout!=null && mainFrameLayout.isDisplayed();
	}
}