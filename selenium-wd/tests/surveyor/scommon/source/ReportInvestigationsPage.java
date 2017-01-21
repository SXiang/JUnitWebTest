/**
 *
 */
package surveyor.scommon.source;

import static common.source.BaseHelper.matchSinglePattern;
import common.source.DateUtility;
import common.source.Downloader;
import common.source.FileUtility;
import common.source.NumberUtility;
import static surveyor.scommon.source.SurveyorConstants.CUSBOUNDARY;
import static surveyor.scommon.source.SurveyorConstants.ENDDATE;
import static surveyor.scommon.source.SurveyorConstants.REXCLUSIONRADIUS;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPHEIGHT;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPWIDTH;
import static surveyor.scommon.source.SurveyorConstants.KEYANNOTATION;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYBASEMAP;
import static surveyor.scommon.source.SurveyorConstants.KEYHIGHLIGHTLISAASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYHIGHLIGHTBOXASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYHIGHLIGHTGAPASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETBOXNUMBER;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARIES;
import static surveyor.scommon.source.SurveyorConstants.KEYBREADCRUMB;
import static surveyor.scommon.source.SurveyorConstants.KEYFOV;
import static surveyor.scommon.source.SurveyorConstants.KEYGAPS;
import static surveyor.scommon.source.SurveyorConstants.KEYGAPTB;
import static surveyor.scommon.source.SurveyorConstants.KEYINDICATIONS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDTB;
import static surveyor.scommon.source.SurveyorConstants.KEYISOANA;
import static surveyor.scommon.source.SurveyorConstants.KEYISOTOPICCAPTURE;
import static surveyor.scommon.source.SurveyorConstants.KEYLISA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCF;
import static surveyor.scommon.source.SurveyorConstants.KEYPCRA;
import static surveyor.scommon.source.SurveyorConstants.KEYVIEWNAME;
import static surveyor.scommon.source.SurveyorConstants.RNELAT;
import static surveyor.scommon.source.SurveyorConstants.RNELON;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;
import static surveyor.scommon.source.SurveyorConstants.REPORTMODES1;
import static surveyor.scommon.source.SurveyorConstants.STARTDATE;
import static surveyor.scommon.source.SurveyorConstants.SURVEYORUNIT;
import static surveyor.scommon.source.SurveyorConstants.RSWLAT;
import static surveyor.scommon.source.SurveyorConstants.RSWLON;
import static surveyor.scommon.source.SurveyorConstants.TAG;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;

import static surveyor.scommon.source.SurveyorConstants.KEYASSETCASTIRON;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETCOPPER;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETOTHERPLASTIC;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETPEPLASTIC;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETPROTECTEDSTEEL;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETUNPROTECTEDSTEEL;

import surveyor.scommon.source.DataTablePage.TableColumnType;
import surveyor.scommon.source.LatLongSelectionControl.ControlMode;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SSRSPdfFooterColumns;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.source.ReportsCompliance.EthaneFilter;
import surveyor.scommon.source.ReportsCompliance.LISAIndicationTableColumns;
import surveyor.scommon.source.ReportsSurveyInfo.ColumnHeaders;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.ArrayUtility;
import common.source.BaseHelper;
import common.source.CSVUtility;
import common.source.Log;
import common.source.LogCategory;
import common.source.LogHelper;
import common.source.PDFTableUtility;
import common.source.PDFTableUtility.PDFTable;
import common.source.TestSetup;
import common.source.TextUtility;
import common.source.WebElementExtender;
import sun.misc.BASE64Decoder;
import surveyor.dataaccess.source.BaseMapType;
import surveyor.dataaccess.source.DBCache;
import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.ReportView;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataaccess.source.StoredProcComplianceAssessmentGetReportDrivingSurveys;
import surveyor.dataaccess.source.StoredProcComplianceGetCoverage;
import surveyor.dataaccess.source.StoredProcComplianceGetCoverageForecast;
import surveyor.dataaccess.source.StoredProcComplianceGetEthaneCapture;
import surveyor.dataaccess.source.StoredProcComplianceGetGaps;
import surveyor.dataaccess.source.StoredProcComplianceGetIndications;
import surveyor.dataaccess.source.StoredProcComplianceGetIsotopics;
import surveyor.dataaccess.source.StoredProcLisaInvestigationShowIndication;
import surveyor.dataprovider.ReportDataProvider;
import common.source.PDFUtility;
import common.source.ProcessUtility;
import common.source.RegexUtility;
import common.source.ShapeFileUtility;
import common.source.SortHelper;
import common.source.TestContext;

/**
 * @author sxiang
 *
 */
public class ReportInvestigationsPage extends ReportsBasePage {

	public static final String STRURLPath = "/Reports/Investigations?reportId=%s";
	protected String pagination = "100";

	@FindBy(how = How.CSS, using = "#boxType > button.btn")
	protected WebElement boxTypeDropdown;
	
	@FindBy(how = How.CSS, using = "#boxType > button.btn > #boxType_text")
	protected WebElement boxTypeText;

	@FindBy(how = How.ID, using = "buttonAssignPeaks")
	protected WebElement buttonAssignPeaks;
	
	@FindBy(how = How.ID, using = "buttonAssignInvestigator")
	protected WebElement buttonAssignInvestigator;
	
	@FindBy(how = How.CSS, using = "div[id='myModal'] select[id='User']")
	protected WebElement assignToUserDropdown;

	@FindBy(how = How.ID, using = "modal-okay-btn")
	protected WebElement buttonAssignOk;
	
	@FindBy(how = How.ID, using = "modal-cancel-btn")
	protected WebElement buttonAssignCancel;

	protected String checkBoxXPattern = "//*[@id='datatableBoxes']//td[text()='%s']/../td/input[@type='checkbox']";
	protected String itemStatusXPattern = "//*[@id='datatableBoxes']//td[text()='%s']/../td[3]";
	protected String itemValueXPattern = "//*[@id='datatableBoxes']//td[text()='%s']/../td[2]";
	protected String itemDateXPattern = "//*[@id='datatableBoxes']//td[text()='%s']/../td[4]";
	
	public static enum LisaStatus {
		FOUNDGASLEAK ("Found Gas Leak"),
		INPROGRESS ("In Progress"),
		NOTINVESTIGATED ("Not Investigated"),
		FOUNDOTHERSOURCE ("Found Other Source");

		private String status;
		
		LisaStatus(String status){
			this.status = status;
		}

		public String toString(){
			return status;
		}

	};
	
	/**
	 * @param driver
	 * @param strBaseURL
	 * @param testSetup
	 */
	public ReportInvestigationsPage(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup, strBaseURL + STRURLPath);
		Log.info(String.format("\nThe Compliance Reports Page URL is: %s\n", this.strPageURL));
		PageFactory.initElements(driver, this);
	}
	
	public void open(String reportId) {
		strPageURL = String.format(strPageURL, reportId);
		driver.get(strPageURL);
		this.waitForPageToLoad();
	}
	
	public boolean selectLisa(String lisaNumber){
		String boxType = "LISA";
		selectDropdownItem(boxTypeDropdown, boxType);
		return selectBox(lisaNumber);
	}

	public boolean selectGap(String gapNumber){
		String boxType = "Gap";
		selectDropdownItem(boxTypeDropdown, boxType);
		return selectBox(gapNumber);
	}
	
	public boolean selectBox(String boxNumber){
		performSearch(boxNumber);
		WebElement checkBox = driver.findElement(By.xpath(String.format(checkBoxXPattern, boxNumber)));
		if(!checkBox.isSelected()){
			checkBox.click();
			waitForPageToLoad();
		}
		performSearch("");
		return checkBox.isSelected();
	}
	
	public boolean assignPeaks(String username){
		buttonAssignPeaks.click();
		boolean userSelected = selectDropdownOption(assignToUserDropdown, username);
		buttonAssignOk.click();
		return userSelected;
	}
	
	public String getLisaStatus(String lisaNumber){
		String boxType = "LISA";
		selectDropdownItem(boxTypeDropdown, boxType);
		return getItemStatus(lisaNumber);
	}
	
	public String getGapStatus(String gapNumber){
		String boxType = "Gap";
		selectDropdownItem(boxTypeDropdown, boxType);
		return getItemStatus(gapNumber);
	}
	
	public String getItemStatus(String itemNumber){
		WebElement itemStatus = driver.findElement(By.xpath(String.format(itemStatusXPattern, itemNumber)));
		String statusText = getElementText(itemStatus);
		return statusText;
	}

	public String getLisaDate(String lisaNumber){
		String boxType = "LISA";
		selectDropdownItem(boxTypeDropdown, boxType);
		return getItemStatus(lisaNumber);
	}
	
	public String getGapDate(String gapNumber){
		String boxType = "Gap";
		selectDropdownItem(boxTypeDropdown, boxType);
		return getItemDate(gapNumber);
	}
	
	public String getItemDate(String itemNumber){
	WebElement itemDate = driver.findElement(By.xpath(String.format(itemDateXPattern, itemNumber)));
		String dateValue = getElementText(itemDate);
		return dateValue;
	}
	public String getLisaValue(String lisaNumber){
		String boxType = "LISA";
		selectDropdownItem(boxTypeDropdown, boxType);
		WebElement itemValue = driver.findElement(By.xpath(String.format(itemValueXPattern, lisaNumber)));
		String value = getElementText(itemValue);
		return value;
	}
	
	public boolean isLisaValueSearchable(String lisaValue){
		String boxType = "LISA";
		selectDropdownItem(boxTypeDropdown, boxType);
		performSearch(lisaValue);
		boolean valueMatch = true;
		List<WebElement> itemValues = driver.findElements(By.xpath("//*[@id='datatableBoxes']//td[2]"));
		for(WebElement item:itemValues){
			String value = getElementText(item);
			if(!value.equals(lisaValue)){
				Log.error("Lisa valeu '"+lisaValue+"' is not searchable or invalid");
				return false;
			}
		}
		return true;
	}
}
