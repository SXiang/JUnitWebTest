package surveyor.scommon.actions;

public class BasePageActions implements IPageActions {
	public boolean clickById(String elementID, String dataRowIDs) {
		return false;
	}

	public boolean clickByXPath(String elementXPath, String dataRowIDs) {
		return false;
	}

	public boolean clickByIdAndWait(String elementID, String dataRowIDs) {
		return false;
	}

	public boolean clickByXPathAndWait(String elementXPath, String dataRowIDs) {
		return false;
	}

	public boolean insertTextById(String elementID, String dataRowIDs) {
		return false;
	}

	public boolean insertTextByXPath(String elementXPath, String dataRowIDs) {
		return false;
	}

	public boolean selectDropDownByID(String elementXPath, String dataRowIDs) {
		return false;
	}

	public boolean selectDropDownByXPath(String elementXPath, String dataRowIDs) {
		return false;
	}

	public boolean selectRadioButtonByID(String elementXPath, String dataRowIDs) {
		return false;
	}

	public boolean selectRadioButtonByXPath(String elementXPath, String dataRowIDs) {
		return false;
	}
}