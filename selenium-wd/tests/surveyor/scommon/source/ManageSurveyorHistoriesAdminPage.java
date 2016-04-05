/**
 * 
 */
package surveyor.scommon.source;

import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.source.TestSetup;

/**
 * @author pmahajan
 * 
 */
public class ManageSurveyorHistoriesAdminPage extends
		ManageSurveyorHistoriesPage {
	public static final String STRURLPATH = "/Picarro/ManageSurveyorHistories";

	/**
	 * 
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 */
	public ManageSurveyorHistoriesAdminPage(WebDriver driver,
			TestSetup testSetup, String strBaseURL) {
		super(driver, strBaseURL, testSetup, STRURLPATH);

		System.out.format(
				"\nThe Manage Surveyor Histories Admin Page URL is: %s\n",
				strBaseURL + STRURLPATH);
	}

	public boolean findExistingSurveyorHistoryNote(String strNote,
			String strSurveyorName, String strLocationName,
			String strCustomerName) {
		setPagination(PAGINATIONSETTING);

		this.testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		String strLocationXPath;
		String strSurveyorXPath;
		String strNoteXPath;
		String strCustomerXPath;

		WebElement locationCell;
		WebElement surveyorCell;
		WebElement customerCell;
		WebElement noteCell;

		List<WebElement> rows = table.findElements(By.xpath(strTRXPath));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			strCustomerXPath = strTRXPath + "[" + rowNum + "]/td[1]";
			strLocationXPath = strTRXPath + "[" + rowNum + "]/td[2]";
			strSurveyorXPath = strTRXPath + "[" + rowNum + "]/td[3]";
			strNoteXPath = strTRXPath + "[" + rowNum + "]/td[4]";

			customerCell = table.findElement(By.xpath(strCustomerXPath));
			locationCell = table.findElement(By.xpath(strLocationXPath));
			surveyorCell = table.findElement(By.xpath(strSurveyorXPath));
			noteCell = table.findElement(By.xpath(strNoteXPath));

			if ((customerCell.getText().trim())
					.equalsIgnoreCase(strCustomerName)
					&& (locationCell.getText().trim())
							.equalsIgnoreCase(strLocationName)
					&& (surveyorCell.getText().trim())
							.equalsIgnoreCase(strSurveyorName)
					&& (noteCell.getText().trim()).equalsIgnoreCase(strNote)) {
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();

				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());

				List<WebElement> newRows = table.findElements(By
						.xpath(strTRXPath));
				rowSize = newRows.size();
				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);

				rowNum = 0;
			}
		}
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}