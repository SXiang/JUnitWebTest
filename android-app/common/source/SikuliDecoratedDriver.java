package common.source;

import java.awt.Rectangle;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.sikuli.api.DefaultScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenRegion;

import androidapp.screens.source.AndroidBaseScreen;

public class SikuliDecoratedDriver {
	private static final double DEFAULT_SIMILARITY_SCORE = 0.95;
	private AndroidBaseScreen screen;
	private WebDriver driver;

	private SikuliDecoratedDriver(WebDriver driver, AndroidBaseScreen screen) {
		this.driver = driver;
		this.screen = screen;
	}

	public static SikuliDecoratedDriver getInstance(WebDriver driver, AndroidBaseScreen screen) {
		return new SikuliDecoratedDriver(driver, screen);
	}

	public Rectangle getImageBounds(File imageFile, Integer waitTimeInSeconds) {
		Log.method("findImageBounds", imageFile, waitTimeInSeconds);
		DefaultScreenRegion screenRegion = new DefaultScreenRegion(screen);
		screenRegion.setScore(DEFAULT_SIMILARITY_SCORE);

		ImageTarget imgTarget = new ImageTarget(imageFile);
		ScreenRegion foundRegion = screenRegion.wait(imgTarget, waitTimeInSeconds * 1000);

		if (foundRegion != null) {
			return foundRegion.getBounds();
		}

		return null;
	}
}