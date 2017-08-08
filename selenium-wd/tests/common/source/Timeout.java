package common.source;

public class Timeout {
	public static Integer TEN = 10;
	public static Integer ANALYZER_READY_DEFAULT_TIMEOUT = 120;
	public static Integer ANALYZER_READY_LONG_TIMEOUT = 1200;

	public static Integer ANDROID_APP_FIRST_APP_LOAD_TIMEOUT = 240;     // very first time if test is running on fresh install, packager can take longer time in CI.
	public static Integer ANDROID_APP_SCREEN_LOAD_TIMEOUT = 20;
	public static Integer ANDROID_APP_SHOW_MODAL_TIMEOUT = 5;
	public static Integer ANDROID_APP_RESULTS_TIMEOUT = 15;
	public static Integer ANDROID_APP_SEARCH_RESULTS_TIMEOUT = 15;
	public static Integer ANDROID_APP_IMPLICIT_WAIT_TIMEOUT = 1;
	public static Integer ANDROID_APP_SCREEN_ELEMENT_CHANGE_TIMEOUT = 3;
	public static Integer ANDROID_ELEMENT_READY_TIMEOUT = 10;
}
