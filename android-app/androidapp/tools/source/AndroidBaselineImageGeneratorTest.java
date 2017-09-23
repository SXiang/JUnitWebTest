package androidapp.tools.source;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.support.PageFactory;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import androidapp.dataprovider.LeakScreenDataProvider;
import androidapp.entities.source.InvestigationMarkerEntity;
import androidapp.regression.source.AndroidLeakScreenTestBase;
import androidapp.screens.source.AndroidAddLeakSourceFormDialog;
import androidapp.screens.source.AndroidAddOtherSourceFormDialog;
import androidapp.screens.source.AndroidAddSourceDialog;
import androidapp.screens.source.AndroidAddedSourceListDialog;
import androidapp.screens.source.AndroidConfirmationDialog;
import androidapp.screens.source.AndroidInvestigateMapScreen;
import androidapp.screens.source.AndroidInvestigateReportScreen;
import androidapp.screens.source.AndroidInvestigationScreen;
import androidapp.screens.source.AndroidMarkerTypeListControl;
import androidapp.screens.source.AndroidMarkerTypeListControl.MarkerType;
import androidapp.screens.source.ScreenVerifier;
import common.source.BackPackAnalyzer;
import common.source.BaselineImages;
import common.source.Log;
import common.source.Screenshotter;
import common.source.TestContext;
import common.source.Timeout;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import surveyor.dataaccess.source.Box;
import surveyor.dataaccess.source.Report;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.data.UserDataReader.UserDataRow;

public class AndroidBaselineImageGeneratorTest extends AndroidLeakScreenTestBase {
	private static String reportTitleToUseInTC;

	protected AndroidInvestigationScreen investigationScreen;
	protected AndroidInvestigateReportScreen investigateReportScreen;
	protected AndroidMarkerTypeListControl markerTypeDialog;

	protected AndroidInvestigateMapScreen investigateMapScreen;
	protected AndroidAddSourceDialog addSourceDialog;
	protected AndroidConfirmationDialog confirmationDialog;

	protected AndroidAddLeakSourceFormDialog addLeakSourceFormDialog;
	protected AndroidAddedSourceListDialog addedSourcesListDialog;

	protected AndroidAddOtherSourceFormDialog addOtherSourceFormDialog;

	private static LoginPageActions loginPageAction;

	private static ThreadLocal<Boolean> appiumTestInitialized = new ThreadLocal<Boolean>();

	@Rule
	public TestName testName = new TestName();

	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects(false);
	}

	@Before
	public void beforeTest() throws Exception {
		initializePageActions();
		initializeTestDriver();
		initializeTestScreenObjects();
		if (!TestContext.INSTANCE.getTestSetup().isRunningOnBackPackAnalyzer()) {
			BackPackAnalyzer.restartSimulator();
		}

		startTestRecording(testName.getMethodName());
	}

	@After
	public void afterTest() throws Exception {
		stopTestRecording(testName.getMethodName());
		if (!TestContext.INSTANCE.getTestSetup().isRunningOnBackPackAnalyzer()) {
			BackPackAnalyzer.stopSimulator();
		}
	}

	/**
	 * Initializes the page action objects.
	 * @throws Exception
	 */
	protected static void initializePageActions() throws Exception {
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
	}

	/**
	 * Generates baseline images for TC2432
	 * Update 'reportTitleToUseInTC' if currently used report NOT in database.
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2432, location = LeakScreenDataProvider.class)
	public void generateBaselineImages_TC2432 (
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning generateBaselineImages_TC2432 ...");

		/* ################### Use correct report title before test execution ################### */
		reportTitleToUseInTC = "TC2432-cfbd8c6fc3eb43908a12";

		// marker status = 'Not Investigated'
		resetInvestigationStatusOnMarkers(reportTitleToUseInTC, 0 /*boxTypeId=LISA*/, 0 /*investigationStatusTypeId=NotInvestigated*/);

		UserDataRow userDataRow = loginPageAction.getDataRow(userDataRowID);

		navigateToMapScreen(true /*waitForMapScreenLoad*/, userDataRow.username);
		executeWithBackPackDataProcessesPaused(obj -> {
			navigateToInvestigationReportScreen(investigationScreen, userDataRow.password);
			searchForReportId(investigationScreen, reportTitleToUseInTC);
			initializeInvestigationScreen();
			return true;
		});

		clickOnFirstInvestigationReport(investigationScreen);

		List<InvestigationMarkerEntity> investigationMarkers = new ArrayList<InvestigationMarkerEntity>();
		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			investigateReportScreen.getInvestigationMarkers().stream()
				.forEach(m -> investigationMarkers.add(m));
			return true;
		});

        final int lastMarkerNum = getMarkerNumber(investigationMarkers, investigationMarkers.size()-1);

		// Capture baseline images for NotInvestigated LISAs
        for (int i = 0; i < investigationMarkers.size(); i++) {
            investigateReportScreen.clickOnMarkerAtIndex(i + 1);
            final int markerNum = getMarkerNumber(investigationMarkers, i);
            executeWithBackPackDataProcessesPaused(obj -> {
                investigateMapScreen.waitForScreenLoad();
                investigateMapScreen.clickOnFollow();
                TestContext.INSTANCE.stayIdle(10);
                Screenshotter.captureWebDriverScreenshot(getAndroidDriver(), String.format(BaselineImages.ImageFile.LisaScreenshotWithIndexPlaceholder, markerNum));

                if (markerNum == lastMarkerNum) {
            		// marker status = 'In Progress'
            		resetInvestigationStatusOnMarkers(reportTitleToUseInTC, 0 /*boxTypeId=LISA*/, 1 /*investigationStatusTypeId=In Progress*/);
                }

                investigateMapScreen.clickOnFooterInvestigate();
                investigationScreen.waitForScreenLoad();
                searchForReportId(investigationScreen, reportTitleToUseInTC);
                initializeInvestigationScreen();
                return true;
            });

            clickOnFirstInvestigationReport(investigationScreen);
            executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
                investigateReportScreen.waitForScreenLoad();
                initializeInvestigateReportScreen();
                return true;
            });
        }

		// Capture baseline images for In-Progress LISAs
        for (int i = 0; i < investigationMarkers.size(); i++) {
            investigateReportScreen.clickOnMarkerAtIndex(i + 1);
            final int markerNum = getMarkerNumber(investigationMarkers, i);
            executeWithBackPackDataProcessesPaused(obj -> {
                investigateMapScreen.waitForScreenLoad();
                investigateMapScreen.clickOnFollow();
                TestContext.INSTANCE.stayIdle(10);
                Screenshotter.captureWebDriverScreenshot(getAndroidDriver(), String.format(BaselineImages.ImageFile.LisaInProgressScreenshotWithIndexPlaceholder, markerNum));

                if (markerNum == lastMarkerNum) {
            		// marker status = 'Found Gas Leak'
            		resetInvestigationStatusOnMarkers(reportTitleToUseInTC, 0 /*boxTypeId=LISA*/, 2 /*investigationStatusTypeId=Found Gas Leak*/);
                }

                investigateMapScreen.clickOnFooterInvestigate();
                investigationScreen.waitForScreenLoad();
                searchForReportId(investigationScreen, reportTitleToUseInTC);
                initializeInvestigationScreen();
                return true;
            });

            clickOnFirstInvestigationReport(investigationScreen);
            executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
                investigateReportScreen.waitForScreenLoad();
                initializeInvestigateReportScreen();
                return true;
            });
        }

		// Capture baseline images for investigation Complete LISAs
        for (int i = 0; i < investigationMarkers.size(); i++) {
            investigateReportScreen.clickOnMarkerAtIndex(i + 1);
            final int markerNum = getMarkerNumber(investigationMarkers, i);
            executeWithBackPackDataProcessesPaused(obj -> {
                investigateMapScreen.waitForScreenLoad();
                investigateMapScreen.clickOnFollow();
                TestContext.INSTANCE.stayIdle(10);
                Screenshotter.captureWebDriverScreenshot(getAndroidDriver(), String.format(BaselineImages.ImageFile.LisaFoundGasLeakScreenshotWithIndexPlaceholder, markerNum));
                investigateMapScreen.clickOnFooterInvestigate();
                investigationScreen.waitForScreenLoad();
                searchForReportId(investigationScreen, reportTitleToUseInTC);
                initializeInvestigationScreen();
                return true;
            });

            clickOnFirstInvestigationReport(investigationScreen);
            executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
                investigateReportScreen.waitForScreenLoad();
                initializeInvestigateReportScreen();
                return true;
            });
        }
	}

	/**
	 * Generates baseline images for TC2639.
	 * Update 'reportTitleToUseInTC' if currently used report NOT in database.
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2639, location = LeakScreenDataProvider.class)
	public void generateBaselineImages_TC2639(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning generateBaselineImages_TC2639 ...");

		/* ################### Use correct report title before test execution ################### */
		reportTitleToUseInTC = "TC2639-f4afc63fb645486d8ac1";

		// marker status = 'Not Investigated'
		resetInvestigationStatusOnMarkers(reportTitleToUseInTC, 0 /*boxTypeId=LISA*/, 0 /*investigationStatusTypeId=NotInvestigated*/);

		UserDataRow userDataRow = loginPageAction.getDataRow(userDataRowID);

		navigateToMapScreen(true /*waitForMapScreenLoad*/, userDataRow.username);
		executeWithBackPackDataProcessesPaused(obj -> {
			navigateToInvestigationReportScreen(investigationScreen, userDataRow.password);
			searchForReportId(investigationScreen, reportTitleToUseInTC);
			initializeInvestigationScreen();
			return true;
		});

		clickOnFirstInvestigationReport(investigationScreen);

		List<InvestigationMarkerEntity> investigationMarkers = new ArrayList<InvestigationMarkerEntity>();
		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			investigateReportScreen.getInvestigationMarkers().stream()
				.forEach(m -> investigationMarkers.add(m));
			return true;
		});

        final int lastMarkerNum = getMarkerNumber(investigationMarkers, investigationMarkers.size()-1);

		// Capture baseline images for NotInvestigated LISAs
        for (int i = 0; i < investigationMarkers.size(); i++) {
            investigateReportScreen.clickOnMarkerAtIndex(i + 1);
            final int markerNum = getMarkerNumber(investigationMarkers, i);
            executeWithBackPackDataProcessesPaused(obj -> {
                investigateMapScreen.waitForScreenLoad();
                investigateMapScreen.clickOnFollow();
                TestContext.INSTANCE.stayIdle(10);
                Screenshotter.captureWebDriverScreenshot(getAndroidDriver(), String.format(BaselineImages.ImageFile.AssetBoxScreenshotWithIndexPlaceholder, markerNum));

                if (markerNum == lastMarkerNum) {
            		// marker status = 'In Progress'
            		resetInvestigationStatusOnMarkers(reportTitleToUseInTC, 0 /*boxTypeId=LISA*/, 1 /*investigationStatusTypeId=In Progress*/);
                }

                investigateMapScreen.clickOnFooterInvestigate();
                investigationScreen.waitForScreenLoad();
                searchForReportId(investigationScreen, reportTitleToUseInTC);
                initializeInvestigationScreen();
                return true;
            });

            clickOnFirstInvestigationReport(investigationScreen);
            executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
                investigateReportScreen.waitForScreenLoad();
                initializeInvestigateReportScreen();
                return true;
            });
        }

		// Capture baseline images for In-Progress LISAs
        for (int i = 0; i < investigationMarkers.size(); i++) {
            investigateReportScreen.clickOnMarkerAtIndex(i + 1);
            final int markerNum = getMarkerNumber(investigationMarkers, i);
            executeWithBackPackDataProcessesPaused(obj -> {
                investigateMapScreen.waitForScreenLoad();
                investigateMapScreen.clickOnFollow();
                TestContext.INSTANCE.stayIdle(10);
                Screenshotter.captureWebDriverScreenshot(getAndroidDriver(), String.format(BaselineImages.ImageFile.AssetBoxInProgressScreenshotWithIndexPlaceholder, markerNum));

                if (markerNum == lastMarkerNum) {
            		// marker status = 'Found Gas Leak'
            		resetInvestigationStatusOnMarkers(reportTitleToUseInTC, 0 /*boxTypeId=LISA*/, 2 /*investigationStatusTypeId=Found Gas Leak*/);
                }

                investigateMapScreen.clickOnFooterInvestigate();
                investigationScreen.waitForScreenLoad();
                searchForReportId(investigationScreen, reportTitleToUseInTC);
                initializeInvestigationScreen();
                return true;
            });

            clickOnFirstInvestigationReport(investigationScreen);
            executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
                investigateReportScreen.waitForScreenLoad();
                initializeInvestigateReportScreen();
                return true;
            });
        }

		// Capture baseline images for investigation Complete LISAs
        for (int i = 0; i < investigationMarkers.size(); i++) {
            investigateReportScreen.clickOnMarkerAtIndex(i + 1);
            final int markerNum = getMarkerNumber(investigationMarkers, i);
            executeWithBackPackDataProcessesPaused(obj -> {
                investigateMapScreen.waitForScreenLoad();
                investigateMapScreen.clickOnFollow();
                TestContext.INSTANCE.stayIdle(10);
                Screenshotter.captureWebDriverScreenshot(getAndroidDriver(), String.format(BaselineImages.ImageFile.AssetBoxFoundGasLeakScreenshotWithIndexPlaceholder, markerNum));
                investigateMapScreen.clickOnFooterInvestigate();
                investigationScreen.waitForScreenLoad();
                searchForReportId(investigationScreen, reportTitleToUseInTC);
                initializeInvestigationScreen();
                return true;
            });

            clickOnFirstInvestigationReport(investigationScreen);
            executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
                investigateReportScreen.waitForScreenLoad();
                initializeInvestigateReportScreen();
                return true;
            });
        }
	}

	/**
	 * Generates baseline images for TC2445
	 * Update 'reportTitleToUseInTC' if currently used report NOT in database.
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2445, location = LeakScreenDataProvider.class)
	public void generateBaselineImages_TC2445(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning generateBaselineImages_TC2445 ...");

		/* ################### Use correct report title before test execution ################### */
		reportTitleToUseInTC = "TC2445-b7a49906651547a8828e";

		// marker status = 'Not Investigated'
		resetInvestigationStatusOnMarkers(reportTitleToUseInTC, 0 /*boxTypeId=LISA*/, 0 /*investigationStatusTypeId=NotInvestigated*/);

		UserDataRow userDataRow = loginPageAction.getDataRow(userDataRowID);

		navigateToMapScreen(true /*waitForMapScreenLoad*/, userDataRow.username);
		executeWithBackPackDataProcessesPaused(obj -> {
			navigateToInvestigationReportScreen(investigationScreen, userDataRow.password);
			searchForReportId(investigationScreen, reportTitleToUseInTC);
			initializeInvestigationScreen();
			return true;
		});

		clickOnFirstInvestigationReport(investigationScreen);

		List<InvestigationMarkerEntity> investigationMarkers = new ArrayList<InvestigationMarkerEntity>();
		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			investigateReportScreen.getInvestigationMarkers().stream()
				.forEach(m -> investigationMarkers.add(m));
			return true;
		});

        final int lastMarkerNum = getMarkerNumber(investigationMarkers, investigationMarkers.size()-1);

		// Capture baseline images for NotInvestigated LISAs
        for (int i = 0; i < investigationMarkers.size(); i++) {
            investigateReportScreen.clickOnMarkerAtIndex(i + 1);
            final int markerNum = getMarkerNumber(investigationMarkers, i);
            executeWithBackPackDataProcessesPaused(obj -> {
                investigateMapScreen.waitForScreenLoad();
                investigateMapScreen.clickOnFollow();
                TestContext.INSTANCE.stayIdle(10);
                Screenshotter.captureWebDriverScreenshot(getAndroidDriver(), String.format(BaselineImages.ImageFile.LisaScreenshotWithIndexPlaceholder, markerNum));

                if (markerNum == lastMarkerNum) {
            		// marker status = 'Found Other Source'
            		resetInvestigationStatusOnMarkers(reportTitleToUseInTC, 0 /*boxTypeId=LISA*/, 3 /*investigationStatusTypeId=Found Other Source*/);
                }

                investigateMapScreen.clickOnFooterInvestigate();
                investigationScreen.waitForScreenLoad();
                searchForReportId(investigationScreen, reportTitleToUseInTC);
                initializeInvestigationScreen();
                return true;
            });

            clickOnFirstInvestigationReport(investigationScreen);
            executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
                investigateReportScreen.waitForScreenLoad();
                initializeInvestigateReportScreen();
                return true;
            });
        }

		// Capture baseline images for investigation Complete LISAs - Found Other Source
        for (int i = 0; i < investigationMarkers.size(); i++) {
            investigateReportScreen.clickOnMarkerAtIndex(i + 1);
            final int markerNum = getMarkerNumber(investigationMarkers, i);
            executeWithBackPackDataProcessesPaused(obj -> {
                investigateMapScreen.waitForScreenLoad();
                investigateMapScreen.clickOnFollow();
                TestContext.INSTANCE.stayIdle(10);
                Screenshotter.captureWebDriverScreenshot(getAndroidDriver(), String.format(BaselineImages.ImageFile.LisaHighlightScreenshotWithIndexPlaceholder, markerNum));
                investigateMapScreen.clickOnFooterInvestigate();
                investigationScreen.waitForScreenLoad();
                searchForReportId(investigationScreen, reportTitleToUseInTC);
                initializeInvestigationScreen();
                return true;
            });

            clickOnFirstInvestigationReport(investigationScreen);
            executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
                investigateReportScreen.waitForScreenLoad();
                initializeInvestigateReportScreen();
                return true;
            });
        }
	}

	/**
	 * Generates baseline images for TC2640
	 * Update 'reportTitleToUseInTC' if currently used report NOT in database.
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2640, location = LeakScreenDataProvider.class)
	public void generateBaselineImages_TC2640(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning generateBaselineImages_TC2640 ...");

		/* ################### Use correct report title before test execution ################### */
		reportTitleToUseInTC = "TC2640-2d998e26cba64dc29492";

		// marker status = 'Not Investigated'
		resetInvestigationStatusOnMarkers(reportTitleToUseInTC, 1 /*boxTypeId=Gap*/, 0 /*investigationStatusTypeId=NotInvestigated*/);

		UserDataRow userDataRow = loginPageAction.getDataRow(userDataRowID);

		navigateToMapScreen(true /*waitForMapScreenLoad*/, userDataRow.username);
		executeWithBackPackDataProcessesPaused(obj -> {
			navigateToInvestigationReportScreen(investigationScreen, userDataRow.password);
			searchForReportId(investigationScreen, reportTitleToUseInTC);
			initializeInvestigationScreen();
			return true;
		});

		clickOnFirstInvestigationReport(investigationScreen);

		List<InvestigationMarkerEntity> investigationMarkers = new ArrayList<InvestigationMarkerEntity>();
		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			investigateReportScreen.clickOnInvestigationMarkerType();
			markerTypeDialog.selectMarkerType(MarkerType.Gap);
			investigateReportScreen.waitForMarkerTypeGapToBeSelected();
			initializeInvestigateReportScreen();
			investigateReportScreen.getInvestigationMarkers().stream()
				.forEach(m -> investigationMarkers.add(m));
			return true;
		});

		final int lastMarkerNum = getMarkerNumber(investigationMarkers, investigationMarkers.size()-1);

		// Capture baseline images for NotInvestigated Gaps
        for (int i = 0; i < investigationMarkers.size(); i++) {
            investigateReportScreen.clickOnMarkerAtIndex(i + 1);
            final int markerNum = getMarkerNumber(investigationMarkers, i);
            executeWithBackPackDataProcessesPaused(obj -> {
                investigateMapScreen.waitForScreenLoad();
                investigateMapScreen.clickOnFollow();
                TestContext.INSTANCE.stayIdle(10);
                Screenshotter.captureWebDriverScreenshot(getAndroidDriver(), String.format(BaselineImages.ImageFile.GapScreenshotWithIndexPlaceholder, markerNum));

                if (markerNum == lastMarkerNum) {
            		// marker status = 'In Progress'
            		resetInvestigationStatusOnMarkers(reportTitleToUseInTC, 1 /*boxTypeId=Gap*/, 1 /*investigationStatusTypeId=In Progress*/);
                }

                investigateMapScreen.clickOnFooterInvestigate();
                investigationScreen.waitForScreenLoad();
                searchForReportId(investigationScreen, reportTitleToUseInTC);
                initializeInvestigationScreen();
                return true;
            });

            clickOnFirstInvestigationReport(investigationScreen);
            executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
                investigateReportScreen.waitForScreenLoad();
                investigateReportScreen.clickOnInvestigationMarkerType();
                markerTypeDialog.selectMarkerType(MarkerType.Gap);
                investigateReportScreen.waitForMarkerTypeGapToBeSelected();
                initializeInvestigateReportScreen();
                return true;
            });
        }

		// Capture baseline images for In-Progress Gaps
        for (int i = 0; i < investigationMarkers.size(); i++) {
            investigateReportScreen.clickOnMarkerAtIndex(i + 1);
            final int markerNum = getMarkerNumber(investigationMarkers, i);
            executeWithBackPackDataProcessesPaused(obj -> {
                investigateMapScreen.waitForScreenLoad();
                investigateMapScreen.clickOnFollow();
                TestContext.INSTANCE.stayIdle(10);
                Screenshotter.captureWebDriverScreenshot(getAndroidDriver(), String.format(BaselineImages.ImageFile.GapInProgressScreenshotWithIndexPlaceholder, markerNum));

                if (markerNum == lastMarkerNum) {
            		// marker status = 'Found Gas Leak'
            		resetInvestigationStatusOnMarkers(reportTitleToUseInTC, 1 /*boxTypeId=Gap*/, 2 /*investigationStatusTypeId=Found Gas Leak*/);
                }

                investigateMapScreen.clickOnFooterInvestigate();
                investigationScreen.waitForScreenLoad();
                searchForReportId(investigationScreen, reportTitleToUseInTC);
                initializeInvestigationScreen();
                return true;
            });

            clickOnFirstInvestigationReport(investigationScreen);
            executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
                investigateReportScreen.waitForScreenLoad();
                investigateReportScreen.clickOnInvestigationMarkerType();
                markerTypeDialog.selectMarkerType(MarkerType.Gap);
                investigateReportScreen.waitForMarkerTypeGapToBeSelected();
                initializeInvestigateReportScreen();
                return true;
            });
        }

		// Capture baseline images for investigation Complete Gaps
        for (int i = 0; i < investigationMarkers.size(); i++) {
            investigateReportScreen.clickOnMarkerAtIndex(i + 1);
            final int markerNum = getMarkerNumber(investigationMarkers, i);
            executeWithBackPackDataProcessesPaused(obj -> {
                investigateMapScreen.waitForScreenLoad();
                investigateMapScreen.clickOnFollow();
                TestContext.INSTANCE.stayIdle(10);
                Screenshotter.captureWebDriverScreenshot(getAndroidDriver(), String.format(BaselineImages.ImageFile.GapFoundGasLeakScreenshotWithIndexPlaceholder, markerNum));
                investigateMapScreen.clickOnFooterInvestigate();
                investigationScreen.waitForScreenLoad();
                searchForReportId(investigationScreen, reportTitleToUseInTC);
                initializeInvestigationScreen();
                return true;
            });

            clickOnFirstInvestigationReport(investigationScreen);
            executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
                investigateReportScreen.waitForScreenLoad();
                investigateReportScreen.clickOnInvestigationMarkerType();
                markerTypeDialog.selectMarkerType(MarkerType.Gap);
                investigateReportScreen.waitForMarkerTypeGapToBeSelected();
                initializeInvestigateReportScreen();
                return true;
            });
        }
    }

	private void initializeTestScreenObjects() {
		initializeInvestigationScreen();
		initializeInvestigateReportScreen();
		initializeInvestigateMapScreen();
		initializeAddSourceDialog();
		initializeAddLeakSourceFormDialog();
		initializeAndroidAddedLeakListDialog();
		initializeMarkerTypeDialog();
		initializeAddOtherSourceFormDialog();
		initializeConfirmationDialog();
	}

	@Override
	protected void initializeInvestigateReportScreen() {
		investigateReportScreen = new AndroidInvestigateReportScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigateReportScreen);
	}

	@Override
	protected void initializeInvestigationScreen() {
		investigationScreen = new AndroidInvestigationScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigationScreen);
	}

	@Override
	protected void initializeInvestigateMapScreen() {
		investigateMapScreen = new AndroidInvestigateMapScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigateMapScreen);
	}

	@Override
	protected void initializeAddSourceDialog() {
		addSourceDialog = new AndroidAddSourceDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), addSourceDialog);
	}

	@Override
	protected void initializeMarkerTypeDialog() {
		markerTypeDialog = new AndroidMarkerTypeListControl(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), markerTypeDialog);
	}

	@Override
	protected void initializeAddLeakSourceFormDialog() {
		addLeakSourceFormDialog = new AndroidAddLeakSourceFormDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), addLeakSourceFormDialog);
	}

	@Override
	protected void initializeAndroidAddedLeakListDialog() {
		addedSourcesListDialog = new AndroidAddedSourceListDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), addedSourcesListDialog);
	}

	@Override
	protected void initializeAddOtherSourceFormDialog() {
		addOtherSourceFormDialog = new AndroidAddOtherSourceFormDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), addOtherSourceFormDialog);
	}

	private void initializeConfirmationDialog() {
		confirmationDialog = new AndroidConfirmationDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), confirmationDialog);
	}

	private void installApkStartAppiumDriver() throws MalformedURLException, IOException {
		initializeAppiumDriver();
		installLaunchApp(AndroidActivities.MAIN_ACTIVITY);
	}

	private void initializeTestDriver() throws MalformedURLException, IOException, Exception {
		boolean isAppiumTestInitialized = appiumTestInitialized.get() != null && appiumTestInitialized.get();
		if (!isAppiumTestInitialized) {
			initializeAppiumTest();
			appiumTestInitialized.set(true);
		} else {
			installApkStartAppiumDriver();
		}
	}

	private void resetInvestigationStatusOnMarkers(String reportTitle, Integer boxTypeId, Integer investigationStatusTypeId) {
		String reportId = Report.getReport(reportTitle).getId();
		new Box().updateInvestigationStatusTypeId(reportId, boxTypeId , investigationStatusTypeId);
	}
}