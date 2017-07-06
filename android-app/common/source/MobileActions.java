package common.source;

import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;

public class MobileActions {
	private MobileDriver<?> mobileDriver;

	private final static String[] ESCAPE_CHARS = {"\"", "'", "(", ")", "&", "<", ">", ";", "*", "|", "~", "$"};

	private MobileActions() {
	}

	private MobileActions(MobileDriver<?> driver) {
		this.mobileDriver = driver;
	}

	public enum KeyCode {
		KEYCODE_UNKNOWN ("KEYCODE_UNKNOWN", 0),
		KEYCODE_SOFT_RIGHT ("KEYCODE_SOFT_RIGHT", 2),
		KEYCODE_HOME ("KEYCODE_HOME", 3),
		KEYCODE_BACK ("KEYCODE_BACK", 4),
		KEYCODE_CALL ("KEYCODE_CALL", 5),
		KEYCODE_ENDCALL ("KEYCODE_ENDCALL", 6),
		KEYCODE_0 ("KEYCODE_0", 7),
		KEYCODE_1 ("KEYCODE_1", 8),
		KEYCODE_2 ("KEYCODE_2", 9),
		KEYCODE_3 ("KEYCODE_3", 10),
		KEYCODE_4 ("KEYCODE_4", 11),
		KEYCODE_5 ("KEYCODE_5", 12),
		KEYCODE_6 ("KEYCODE_6", 13),
		KEYCODE_7 ("KEYCODE_7", 14),
		KEYCODE_8 ("KEYCODE_8", 15),
		KEYCODE_9 ("KEYCODE_9", 16),
		KEYCODE_STAR ("KEYCODE_STAR", 17),
		KEYCODE_POUND ("KEYCODE_POUND", 18),
		KEYCODE_DPAD_UP ("KEYCODE_DPAD_UP", 19),
		KEYCODE_DPAD_DOWN ("KEYCODE_DPAD_DOWN", 20),
		KEYCODE_DPAD_LEFT ("KEYCODE_DPAD_LEFT", 21),
		KEYCODE_DPAD_RIGHT ("KEYCODE_DPAD_RIGHT", 22),
		KEYCODE_DPAD_CENTER ("KEYCODE_DPAD_CENTER", 23),
		KEYCODE_VOLUME_UP ("KEYCODE_VOLUME_UP", 24),
		KEYCODE_VOLUME_DOWN ("KEYCODE_VOLUME_DOWN", 25),
		KEYCODE_POWER ("KEYCODE_POWER", 26),
		KEYCODE_CAMERA ("KEYCODE_CAMERA", 27),
		KEYCODE_CLEAR ("KEYCODE_CLEAR", 28),
		KEYCODE_A ("KEYCODE_A", 29),
		KEYCODE_B ("KEYCODE_B", 30),
		KEYCODE_C ("KEYCODE_C", 31),
		KEYCODE_D ("KEYCODE_D", 32),
		KEYCODE_E ("KEYCODE_E", 33),
		KEYCODE_F ("KEYCODE_F", 34),
		KEYCODE_G ("KEYCODE_G", 35),
		KEYCODE_H ("KEYCODE_H", 36),
		KEYCODE_I ("KEYCODE_I", 37),
		KEYCODE_J ("KEYCODE_J", 38),
		KEYCODE_K ("KEYCODE_K", 39),
		KEYCODE_L ("KEYCODE_L", 40),
		KEYCODE_M ("KEYCODE_M", 41),
		KEYCODE_N ("KEYCODE_N", 42),
		KEYCODE_O ("KEYCODE_O", 43),
		KEYCODE_P ("KEYCODE_P", 44),
		KEYCODE_Q ("KEYCODE_Q", 45),
		KEYCODE_R ("KEYCODE_R", 46),
		KEYCODE_S ("KEYCODE_S", 47),
		KEYCODE_T ("KEYCODE_T", 48),
		KEYCODE_U ("KEYCODE_U", 49),
		KEYCODE_V ("KEYCODE_V", 50),
		KEYCODE_W ("KEYCODE_W", 51),
		KEYCODE_X ("KEYCODE_X", 52),
		KEYCODE_Y ("KEYCODE_Y", 53),
		KEYCODE_Z ("KEYCODE_Z", 54),
		KEYCODE_COMMA ("KEYCODE_COMMA", 55),
		KEYCODE_PERIOD ("KEYCODE_PERIOD", 56),
		KEYCODE_ALT_LEFT ("KEYCODE_ALT_LEFT", 57),
		KEYCODE_ALT_RIGHT ("KEYCODE_ALT_RIGHT", 58),
		KEYCODE_SHIFT_LEFT ("KEYCODE_SHIFT_LEFT", 59),
		KEYCODE_SHIFT_RIGHT ("KEYCODE_SHIFT_RIGHT", 60),
		KEYCODE_TAB ("KEYCODE_TAB", 61),
		KEYCODE_SPACE ("KEYCODE_SPACE", 62),
		KEYCODE_SYM ("KEYCODE_SYM", 63),
		KEYCODE_EXPLORER ("KEYCODE_EXPLORER", 64),
		KEYCODE_ENVELOPE ("KEYCODE_ENVELOPE", 65),
		KEYCODE_ENTER ("KEYCODE_ENTER", 66),
		KEYCODE_DEL ("KEYCODE_DEL", 67),
		KEYCODE_GRAVE ("KEYCODE_GRAVE", 68),
		KEYCODE_MINUS ("KEYCODE_MINUS", 69),
		KEYCODE_EQUALS ("KEYCODE_EQUALS", 70),
		KEYCODE_LEFT_BRACKET ("KEYCODE_LEFT_BRACKET", 71),
		KEYCODE_RIGHT_BRACKET ("KEYCODE_RIGHT_BRACKET", 72),
		KEYCODE_BACKSLASH ("KEYCODE_BACKSLASH", 73),
		KEYCODE_SEMICOLON ("KEYCODE_SEMICOLON", 74),
		KEYCODE_APOSTROPHE ("KEYCODE_APOSTROPHE", 75),
		KEYCODE_SLASH ("KEYCODE_SLASH", 76),
		KEYCODE_AT ("KEYCODE_AT", 77),
		KEYCODE_NUM ("KEYCODE_NUM", 78),
		KEYCODE_HEADSETHOOK ("KEYCODE_HEADSETHOOK", 79),
		KEYCODE_FOCUS ("KEYCODE_FOCUS", 80),
		KEYCODE_PLUS ("KEYCODE_PLUS", 81),
		KEYCODE_MENU ("KEYCODE_MENU", 82),
		KEYCODE_NOTIFICATION ("KEYCODE_NOTIFICATION", 83),
		KEYCODE_SEARCH ("KEYCODE_SEARCH", 84),
		TAG_LAST_KEYCODE ("TAG_LAST_KEYCODE", 85);

		private final String name;
		private final Integer codeValue;

		KeyCode(String nm) {
			this(nm, -1);
		}

		KeyCode(String nm, Integer code) {
			name = nm;
			codeValue = code;
		}

		public Integer getCode() {
			return codeValue;
		}


		public String toString() {
			return this.name;
		}
	}

	public static MobileActions newAction() {
		return new MobileActions();
	}

	public static MobileActions newAction(MobileDriver<?> driver) {
		return new MobileActions(driver);
	}

	public void tap(WebElement element) {
		Log.method("tap", element);
		new TouchAction(this.mobileDriver).tap(element).perform();
	}

	public void sendKeys(WebElement element, String text) throws Exception {
		Log.method("sendKeys", element, text);
		element.click();
		text = escapeText(text);
		AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), String.format("input text %s", text));
	}

	public void slideBy(WebElement slider, WebElement sliderContainer, Float percValue) {
		Log.method("slideBy");
		int width = sliderContainer.getSize().getWidth();
		int x = slider.getLocation().getX();
		int y = slider.getLocation().getY();
		int percentX = (int)((width * percValue)/100);
		int actualX = sliderContainer.getLocation().getX() + percentX;
		final float CORRECTION_FACTOR = 1.4f;
		int moveToX = (int)((actualX - x)/CORRECTION_FACTOR);   // Appium appears to move the element by a value larger than moveToX value provided. Adding this adjustment factor to workaround Appium likely buggy behaviour.
		Log.info(String.format("SLIDER : Top LEFT=(%d, %d)", slider.getLocation().getX(), slider.getLocation().getY()));
		Log.info(String.format("SLIDER : Width=%d; Height=%d", slider.getSize().getWidth(), slider.getSize().getHeight()));
		Log.info(String.format("x=%d; y=%d; actualX=%d, width=%d", x, y, actualX, width));
		Log.info(String.format("Moving slider by %f percent from start. percentX=%d; ", percValue, percentX));
		Log.info(String.format("Performing action slideBy -> press(%d, %d) -> moveTo(%d, %d)", x, y, moveToX, 0));
		new TouchAction(this.mobileDriver).press(x, y).moveTo(moveToX, 0).release().perform();
	}

	private String escapeText(String input) {
		String escInput = input;
		for (String ch : ESCAPE_CHARS) {
			escInput = escInput.replace(ch, "\\" + ch);
		}

		return escInput;
	}

	public void takeScreenshot(String screenshotFileNameWithoutExt) throws Exception {
		Log.method("takeScreenshot", screenshotFileNameWithoutExt);
		AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), String.format("screencap /sdcard/%s.png", screenshotFileNameWithoutExt));
	}

	public void swipe(Integer top, Integer left, Integer bottom, Integer right) throws Exception {
		Log.method("swipe", top, left, bottom, right);
		AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), String.format("input swipe %d %d %d %d", top, left, bottom, right));
	}

	public void clickAndPressKey(WebElement element, KeyCode keyCode) throws Exception {
		Log.method("clickAndPressKey", element, keyCode);
		element.click();
		pressKey(keyCode);
	}

	public void pressKey(KeyCode keyCode) throws Exception {
		Log.method("pressKey", keyCode);
		AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), String.format("input keyevent %d", keyCode.getCode()));
	}
}