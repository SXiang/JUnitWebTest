package common.source;

import java.awt.Rectangle;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.sikuli.api.DefaultScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.Screen;
import org.sikuli.api.ScreenRegion;

import androidapp.screens.source.AndroidBaseScreen;

public class SikuliDecoratedDriver {
	private static final double DEFAULT_SIMILARITY_SCORE = 0.95;

	private double similarityScore = DEFAULT_SIMILARITY_SCORE;
	private Screen screen;
	private WebDriver driver;

	private SikuliDecoratedDriver(WebDriver driver, Screen screen) {
		this.driver = driver;
		this.screen = screen;
	}

	public static SikuliDecoratedDriver getInstance(WebDriver driver, Screen screen) {
		return new SikuliDecoratedDriver(driver, screen);
	}

	public Rectangle getImageBounds(File imageFile, Integer waitTimeInSeconds) {
		Log.method("findImageBounds", imageFile, waitTimeInSeconds);
		DefaultScreenRegion screenRegion = new DefaultScreenRegion(screen);
		screenRegion.setScore(this.similarityScore);

		ImageTarget imgTarget = new ImageTarget(imageFile);
		ScreenRegion foundRegion = screenRegion.wait(imgTarget, waitTimeInSeconds * 1000);

		if (foundRegion != null) {
			return foundRegion.getBounds();
		}

		return null;
	}

	public void setSimilarityScore(double similarityScore) {
		this.similarityScore = similarityScore;
	}
}