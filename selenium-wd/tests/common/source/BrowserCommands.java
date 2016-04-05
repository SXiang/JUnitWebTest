package common.source;

public class BrowserCommands {	
	public static void goBack() {
		TestContext.INSTANCE.getDriver().navigate().back();
	}
	
	public static void goForward() {
		TestContext.INSTANCE.getDriver().navigate().forward();
	}	

	public static void refresh() {
		TestContext.INSTANCE.getDriver().navigate().refresh();
	}	
}
