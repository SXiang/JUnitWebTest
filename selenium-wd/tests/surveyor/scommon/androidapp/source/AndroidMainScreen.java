package surveyor.scommon.androidapp.source;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidMainScreen extends AndroidBaseScreen {

	@AndroidFindBy(className = "android.widget.ScrollView")
	public WebElement scrollableView;

	@AndroidFindBy(className = "android.view.ViewGroup")
	public List<WebElement> mainViewGroups;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/content\")")
	public WebElement mainFrameLayout;
}
