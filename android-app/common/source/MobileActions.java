package common.source;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class MobileActions {
	private static final String INPUT_KEYEVENT_CURSOR_AT_END = "input keyevent 123";

	private MobileDriver<?> mobileDriver;

	private final static String[] ESCAPE_CHARS = {"\"", "'", "(", ")", "&", "<", ">", ";", "*", "|", "~", "$"};

	private MobileActions() {
	}

	public static class Coordinates {
		public int x;
		public int y;

		public Coordinates(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
		}
	}

	public static class CoordinatesPair {
		public int x1;
		public int y1;
		public int x2;
		public int y2;

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
		}
	}

	private MobileActions(MobileDriver<?> driver) {
		this.mobileDriver = driver;
	}

	public enum SwipeDirection {
		UP ("UP"),
		DOWN ("DOWN"),
		LEFT ("LEFT"),
		RIGHT ("RIGHT");

		private final String name;

		SwipeDirection(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public enum ZoomDirection {
		VERTICAL ("VERTICAL"),
		HORIZONTAL ("HORIZONTAL");

		private final String name;

		ZoomDirection(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
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
		TAG_LAST_KEYCODE ("TAG_LAST_KEYCODE", 85),
		KEYCODE_ESCAPE ("KEYCODE_ESCAPE", 111);

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

	public AndroidDriver<?> getAndroidDriver() {
		return (AndroidDriver<?>)this.mobileDriver;
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

	public void press(WebElement element) {
		Log.method("press", element);
		new TouchAction(this.mobileDriver).press(element).perform();
	}

	public void sendKeys(WebElement element, String text) throws Exception {
		Log.method("sendKeys", element, text);
		element.click();
		text = escapeText(text);
		AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), String.format("input text \"%s\"", text));
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

	public void undoText(String text) throws Exception {
		Log.method("undoText", text);
		AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), INPUT_KEYEVENT_CURSOR_AT_END);
		for (int i = 0; i < text.length(); i++) {
			pressKey(KeyCode.KEYCODE_DEL);
		}
	}

	public void removeSdcardFile(String sdcardFileName) throws Exception {
		Log.method("removeSdcardFile", sdcardFileName);
		AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), String.format("rm -f /sdcard/%s", sdcardFileName));
	}

	public void takeScreenshot(String screenshotFileNameWithoutExt) throws Exception {
		Log.method("takeScreenshot", screenshotFileNameWithoutExt);
		AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), String.format("screencap /sdcard/%s.png", screenshotFileNameWithoutExt));
	}

	public void swipe(Integer x1, Integer y1, Integer x2, Integer y2) throws Exception {
		Log.method("swipe", x1, y1, x2, y2);
		AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), String.format("input swipe %d %d %d %d", x1, y1, x2, y2));
	}

	/**
	 * Executes swipe action using android driver.
	 */
	public void swipeFromCenter(SwipeDirection direction, int delta, int duration) {
		Log.method("swipeUpFromCenter", direction, delta, duration);
		CoordinatesPair pair = getSwipeFromCenterCoordinatesForDirection(direction, delta);
		getAndroidDriver().swipe(pair.x1, pair.y1, pair.x2, pair.y2, duration);
	}

	/**
	 * Executes swipe action using adb.
	 */
	public void swipeFromCenter(SwipeDirection direction, int delta) throws Exception {
		Log.method("swipeFromCenterUsingDeviceAction", direction, delta);
		CoordinatesPair pair = getSwipeFromCenterCoordinatesForDirection(direction, delta);
		swipe(pair.x1, pair.y1, pair.x2, pair.y2);
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

	// pinch/zoom methods in appium java-client to be deprecated. These do NOT function as expected with java-client.
	// Tracked in appium defect - https://github.com/appium/java-client/issues/424
	// currently keeping this method as 'private' until appium issue is fixed.
	private void zoomIn(WebElement element, ZoomDirection direction, int delta) {
		Log.method("zoomIn", direction, delta);
		Coordinates center = getElementCenterCoordinate(element);
		int negDelta = -1 * delta;
		Log.method("Getting finger1 action ...");
		TouchAction fingerAction1 = getFingerAction(element, center, direction, -20/*pad*/, 0 /*startPos*/, negDelta /*moveBy*/);
		Log.method("Getting finger2 action ...");
		TouchAction fingerAction2 = getFingerAction(element, center, direction, 20/*pad*/, 0 /*startPos*/, delta /*moveBy*/);
		performMultiTouchAction(fingerAction1, fingerAction2);
	}

	// pinch/zoom methods in appium java-client to be deprecated. These do NOT function as expected with java-client.
	// Tracked in appium defect - https://github.com/appium/java-client/issues/424
	// currently keeping this method as 'private' until appium issue is fixed.
	private void zoomOut(WebElement element, ZoomDirection direction, int delta) {
		Log.method("zoomOut", direction, delta);
		Coordinates center = getElementCenterCoordinate(element);
		int negDelta = -1 * delta;
		Log.method("Getting finger1 action ...");
		TouchAction fingerAction1 = getFingerAction(element, center, direction, -20/*pad*/, negDelta /*startPos*/, delta /*moveBy*/);
		Log.method("Getting finger2 action ...");
		TouchAction fingerAction2 = getFingerAction(element, center, direction, 20/*pad*/, delta /*startPos*/, negDelta /*moveBy*/);
		performMultiTouchAction(fingerAction1, fingerAction2);
	}

	private void performMultiTouchAction(TouchAction fingerAction1, TouchAction fingerAction2) {
		Log.method("performMultiTouchAction", fingerAction1, fingerAction2);
		MultiTouchAction mTouchAction = new MultiTouchAction(getAndroidDriver());
		mTouchAction.add(fingerAction1).add(fingerAction2).perform();
	}

	private TouchAction getFingerAction(WebElement element, Coordinates center, ZoomDirection direction, int pad, int startPos, int moveBy) {
		Log.method("getFingerAction", element, center, direction, pad, startPos, moveBy);
		// appium will offset the coordinates using topLeft coordinates of the element. apply offset to each coordinate.
		int offsetX = element.getLocation().getX();
		int offsetY = element.getLocation().getY();
		TouchAction fingerAction = new TouchAction(getAndroidDriver());
		if (direction.equals(ZoomDirection.VERTICAL)) {
			Log.info(String.format("Press -> [%d, %d].. Move to -> [%d, %d]", center.x - offsetX, center.y+pad+moveBy - offsetY, 0 - offsetX, moveBy - offsetY));
			fingerAction.press(element, center.x - offsetX, center.y+pad+moveBy - offsetY).waitAction(2000).moveTo(element, 0 - offsetX, moveBy - offsetY).release();
		} else {
			Log.info(String.format("Press -> [%d, %d].. Move to -> [%d, %d]", center.x+pad+startPos - offsetX, center.y - offsetY, moveBy, 0 - offsetY));
			fingerAction.press(element, center.x+pad+startPos - offsetX, center.y - offsetY).waitAction(2000).moveTo(element, moveBy - offsetX, 0 - offsetY).release();
		}

		return fingerAction;
	}

	private String escapeText(String input) {
		String escInput = input;
		for (String ch : ESCAPE_CHARS) {
			escInput = escInput.replace(ch, "\\" + ch);
		}

		return escInput;
	}

	private CoordinatesPair getSwipeFromCenterCoordinatesForDirection(SwipeDirection direction, int delta) {
		Log.method("getSwipeFromCenterCoordinatesForDirection", direction, delta);
		CoordinatesPair pair = null;
		if (direction.equals(SwipeDirection.UP)) {
			pair = getSwipeFromCenterCoordinates(delta /*upY*/, 0 /*downY*/, 0 /*leftX*/, 0 /*rightX*/);
		} else if (direction.equals(SwipeDirection.DOWN)) {
			pair = getSwipeFromCenterCoordinates(0 /*upY*/, delta /*downY*/, 0 /*leftX*/, 0 /*rightX*/);
		} else if (direction.equals(SwipeDirection.LEFT)) {
			pair = getSwipeFromCenterCoordinates(0 /*upY*/, 0 /*downY*/, delta /*leftX*/, 0 /*rightX*/);
		} else if (direction.equals(SwipeDirection.RIGHT)) {
			pair = getSwipeFromCenterCoordinates(0 /*upY*/, 0 /*downY*/, 0 /*leftX*/, delta /*rightX*/);
		}

		Log.info("Returning coordinate pair -> " + pair.toString());
		return pair;
	}

	private CoordinatesPair getSwipeFromCenterCoordinates(int upY, int downY, int leftX, int rightX) {
		Log.method("getSwipeFromCenterCoordinates", upY, downY, leftX, rightX);
		CoordinatesPair pair = new CoordinatesPair();
		Coordinates center = getCenterCoordinate();
		int centerX = center.x;
		int centerY = center.y;
		pair.x1 = centerX;
		pair.y1 = centerY;
		pair.x2 = centerX-leftX+rightX;
		pair.y2 = centerY-upY+downY;

		Log.info("Returning coordinate pair -> " + pair.toString());
		return pair;
	}

	private Coordinates getCenterCoordinate() {
		Dimension size = getAndroidDriver().manage().window().getSize();
		int centerX = (int)(size.getWidth()/2);
		int centerY = (int)(size.getHeight()/2);
		return new Coordinates(centerX, centerY);
	}

	private Coordinates getElementCenterCoordinate(WebElement element) {
		int width = element.getSize().getWidth();
		int height = element.getSize().getHeight();
		int x = element.getLocation().getX();
		int y = element.getLocation().getY();
		int centerX = x + (int)(width/2);
		int centerY = y + (int)(height/2);
		return new Coordinates(centerX, centerY);
	}
}