package surveyor.scommon.source;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.Log;
import common.source.TestSetup;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.entities.ServerLogEntity;

public class ServerLogPage extends SurveyorBasePage {
	public static final String STRURLPath = "/Picarro/ServerLog";
	public static final String STRPageTitle = Resources.getResource(ResourceKeys.ServerLog_PageTitle);

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']")
	private WebElement tableServerLogs;

	public ServerLogPage(WebDriver driver, String baseUrl, TestSetup testSetup) {
		super(driver, testSetup, baseUrl, STRURLPath);
	}

	public List<ServerLogEntity> getMatchingLogs(String searchTerm, Integer numRecords) {
		Log.method("getMatchingLogs", searchTerm);
		performSearch(searchTerm);
		DataTablePage dataTable = buildDataTablePage(By.id("datatable"));
		List<String> columnNames = Arrays.asList(ServerLogEntity.COL_LEVEL, ServerLogEntity.COL_MESSAGE,
				ServerLogEntity.COL_EXCEPTION, ServerLogEntity.COL_DATE_TIME);
		Map<String, List<String>> records = dataTable.getRecords(columnNames, getServerLogTable(), numRecords);
		return ServerLogEntity.toList(records);
	}

	public WebElement getServerLogTable() {
		return tableServerLogs;
	}

	@Override
	public void waitForPageLoad() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return isPageTitleMatch(d.getTitle(),STRPageTitle);
			}
		});
	}
}