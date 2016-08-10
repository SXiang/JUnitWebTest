package surveyor.scommon.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.source.TestContext;
import surveyor.scommon.source.LoginPage;

public class PageObjectFactory {
	private ThreadLocal<LoginPage> threadLocalLoginPage = new ThreadLocal<LoginPage>() {
	    @Override 
	    protected LoginPage initialValue() {
	    	return createNewLoginPage();
	    }
	};

	private ThreadLocal<HomePage> threadLocalHomePage = new ThreadLocal<HomePage>() {
	    @Override 
	    protected HomePage initialValue() {
	    	return createNewHomePage();
	    }
	};

	protected LoginPage createNewLoginPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		LoginPage loginPage = new LoginPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, loginPage);
		return loginPage;
	}
	
	protected HomePage createNewHomePage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		HomePage homePage = new HomePage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, homePage);
		return homePage;
	}

	public LoginPage getLoginPage() {
		return threadLocalLoginPage.get();
	}

	public HomePage getHomePage() {
		return threadLocalHomePage.get();
	}
}