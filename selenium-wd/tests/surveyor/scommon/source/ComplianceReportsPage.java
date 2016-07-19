/**
 * 
 */
package surveyor.scommon.source;

import static common.source.BaseHelper.matchSinglePattern;
import common.source.DateUtility;
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
import static surveyor.scommon.source.SurveyorConstants.KEYHIGHLIGHTGAPASSETS;
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

import surveyor.scommon.source.LatLongSelectionControl.ControlMode;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SSRSPdfFooterColumns;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.source.ReportsCompliance.EthaneFilter;
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
import common.source.TestContext;

/**
 * @author zlu
 *
 */
public class ComplianceReportsPage extends ReportsBasePage {
	private static final int CUSTOM_BOUNDARY_RADBUTTON_GROUP_IDX = 0;
	private static final int CUSTOMER_BOUNDARY_RADBUTTON_GROUP_IDX = 1;

	/*
	 * Base 64 String for the image appearing as <Pdf>. This string is part of
	 * all the reports and should not be considered for comparison
	 */
	private static final String BASE64_IGNORE = "iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAMAAACdt4HsAAAABGdBTUEAALGPC/xhBQAAAwBQTFRFAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQAAAwAACAEBDAIDFgQFHwUIKggLMggPOgsQ/w1x/Q5v/w5w9w9ryhBT+xBsWhAbuhFKUhEXUhEXrhJEuxJKwBJN1xJY8hJn/xJsyhNRoxM+shNF8BNkZxMfXBMZ2xRZlxQ34BRb8BRk3hVarBVA7RZh8RZi4RZa/xZqkRcw9Rdjihgsqxg99BhibBkc5hla9xli9BlgaRoapho55xpZ/hpm8xpfchsd+Rtibxsc9htgexwichwdehwh/hxk9Rxedx0fhh4igB4idx4eeR4fhR8kfR8g/h9h9R9bdSAb9iBb7yFX/yJfpCMwgyQf8iVW/iVd+iVZ9iVWoCYsmycjhice/ihb/Sla+ylX/SpYmisl/StYjisfkiwg/ixX7CxN9yxS/S1W/i1W6y1M9y1Q7S5M6S5K+i5S6C9I/i9U+jBQ7jFK/jFStTIo+DJO9zNM7TRH+DRM/jRQ8jVJ/jZO8DhF9DhH9jlH+TlI/jpL8jpE8zpF8jtD9DxE7zw9/z1I9j1A9D5C+D5D4D8ywD8nwD8n90A/8kA8/0BGxEApv0El7kM5+ENA+UNAykMp7kQ1+0RB+EQ+7EQ2/0VCxUUl6kU0zkUp9UY8/kZByUkj1Eoo6Usw9Uw3300p500t3U8p91Ez11Ij4VIo81Mv+FMz+VM0/FM19FQw/lQ19VYv/lU1/1cz7Fgo/1gy8Fkp9lor4loi/1sw8l0o9l4o/l4t6l8i8mAl+WEn8mEk52Id9WMk9GMk/mMp+GUj72Qg8mQh92Uj/mUn+GYi7WYd+GYj6mYc62cb92ch8Gce7mcd6Wcb6mcb+mgi/mgl/Gsg+2sg+Wog/moj/msi/mwh/m0g/m8f/nEd/3Ic/3Mb/3Qb/3Ua/3Ya/3YZ/3cZ/3cY/3gY/0VC/0NE/0JE/w5wl4XsJQAAAPx0Uk5TAAAAAAAAAAAAAAAAAAAAAAABCQsNDxMWGRwhJioyOkBLT1VTUP77/vK99zRpPkVmsbbB7f5nYabkJy5kX8HeXaG/11H+W89Xn8JqTMuQcplC/op1x2GZhV2I/IV+HFRXgVSN+4N7n0T5m5RC+KN/mBaX9/qp+pv7mZr83EX8/N9+5Nip1fyt5f0RQ3rQr/zo/cq3sXr9xrzB6hf+De13DLi8RBT+wLM+7fTIDfh5Hf6yJMx0/bDPOXI1K85xrs5q8fT47f3q/v7L/uhkrP3lYf2ryZ9eit2o/aOUmKf92ILHfXNfYmZ3a9L9ycvG/f38+vr5+vz8/Pv7+ff36M+a+AAAAAFiS0dEQP7ZXNgAAAj0SURBVFjDnZf/W1J5Fsf9D3guiYYwKqglg1hqplKjpdSojYizbD05iz5kTlqjqYwW2tPkt83M1DIm5UuomZmkW3bVrmupiCY1mCNKrpvYM7VlTyjlZuM2Y+7nXsBK0XX28xM8957X53zO55z3OdcGt/zi7Azbhftfy2b5R+IwFms7z/RbGvI15w8DdkVHsVi+EGa/ZZ1bYMDqAIe+TRabNv02OiqK5b8Z/em7zs3NbQO0GoD0+0wB94Ac/DqQEI0SdobIOV98Pg8AfmtWAxBnZWYK0vYfkh7ixsVhhMDdgZs2zc/Pu9HsVwc4DgiCNG5WQoJ/sLeXF8070IeFEdzpJh+l0pUB+YBwRJDttS3cheJKp9MZDMZmD5r7+vl1HiAI0qDtgRG8lQAlBfnH0/Miqa47kvcnccEK2/1NCIdJ96Ctc/fwjfAGwXDbugKgsLggPy+csiOZmyb4LiEOjQMIhH/YFg4TINxMKxxaCmi8eLFaLJVeyi3N2eu8OTctMzM9O2fjtsjIbX5ewf4gIQK/5gR4uGP27i5LAdKyGons7IVzRaVV1Jjc/PzjP4TucHEirbUjEOyITvQNNH+A2MLj0NYDAM1x6RGk5e9raiQSkSzR+XRRcUFOoguJ8NE2kN2XfoEgsUN46DFoDlZi0DA3Bwiyg9TzpaUnE6kk/OL7xgdE+KBOgKSkrbUCuHJ1bu697KDrGZEoL5yMt5YyPN9glo9viu96GtEKQFEO/34tg1omEVVRidBy5bUdJXi7R4SIxWJzPi1cYwMMV1HO10gqnQnLFygPEDxSaPPuYPlEiD8B3IIrqDevvq9ytl1JPjhhrMBdIe7zaHG5oZn5sQf7YirgJqrV/aWHLPnPCQYis2U9RthjawHIFa0NnZcpZbCMTbRmnszN3mz5EwREJmX7JrQ6nU0eyFvbtX2dyi42/yqcQf40fnIsUsfSBIJIixhId7OCA7aA8nR3sTfF4EHn3d5elaoeONBEXXR/hWdzgZvHMrMjXWwtVczxZ3nwdm76fBvJfAvtajUgKPfxO1VHHRY5f6PkJBCBwrQcSor8WFIQFgl5RFQw/RuWjwveDGjr16jVvT3UBmXPYgdw0jPFOyCgEem5fw06BMqTu/+AGMeJjtrA8aGRFhJpqEejvlvl2qeqJC2J3+nSRHwhWlyZXvTkrLSEhAQuRxoW5RXA9aZ/yESUkMrv7IpffIWXbhSW5jkVlhQUpHuxHdbQt0b6ZcWF4vdHB9MjWNs5cgsAatd0szvu9rguSmFxWUVZSUmM9ERocbarPfoQ4nETNtofiIvzDIpCFUJqzgPFYI+rVt3k9MH2ys0bOFw1qG+R6DDelnmuYAcGF38vyHKxE++M28BBu47PbrE5kR62UB6qzSFQyBtvVZfDdVdwF2tO7jsrugCK93Rxoi1mf+QHtgNOyo3bxgsEis9i+a3BAA8GWlwHNRlYmTdqkQ64DobhHwNuzl0mVctKGKhS5jGBfW5mdjgJAs0nbiP9KyCVUSyaAwAoHvSPXGYMDgjRGCq0qgykE64/WAffrP5bPVl6ToJeZFFJDMCkp+/BUjUpwYvORdXWi2IL8uDR2NjIdaYJAOy7UpnlqlqHW3A5v66CgbsoQb3PLT2MB1mR+BkWiqTvACAuOnivEwFn82TixYuxsWYTQN6u7hI6Qg3KWvtLZ6/xy2E+rrqmCHhfiIZCznMyZVqSAAV4u4Dj4GwmpiYBoYXxeKSWgLvfpRaCl6qV4EbK4MMNcKVt9TVZjCWnIcjcgAV+9K+yXLCY2TwyTk1OvrjD0I4027f2DAgdwSaNPZ0xQGFq+SAQDXPvMe/zPBeyRFokiPwyLdRUODZtozpA6GeMj9xxbB24l4Eo5Di5VtUMdajqHYHOwbK5SrAVz/mDUoqzj+wJSfsiwJzKvJhh3aQxdmjsnqdicGCgu097X3G/t7tDq2wiN5bD1zIOL1aZY8fTXZMFAtPwguYBHvl5Soj0j8VDSEb9vQGN5hbS06tUqapIuBuHDzoTCItS/ER+DiUpU5C964Ootk3cZj58cdsOhycz4pvvXGf23W3q7I4HkoMnLOkR0qKCUDo6h2TtWgAoXvYz/jXZH4O1MQIzltiuro0N/8x6fygsLmYHoVOEIItnATyZNg636V8Mm3eDcK2avzMh6/bSM6V5lNwCjLAVMlfjozevB5mjk7qF0aNR1x27TGsoLC3dx88uwOYQIGsY4PmvM2+mnyO6qVGL9sq1GqF1By6dE+VRThQX54RG7qESTUdAfns7M/PGwHs29WrI8t6DO6lWW4z8vES0l1+St5dCsl9j6Uzjs7OzMzP/fnbKYNQjlhcZ1lt0dYWkinJG9JeFtLIAAEGPIHqjoW3F0fpKRU0e9aJI9Cfo4/beNmwwGPTv3hhSnk4bf16JcOXH3yvY/CIJ0LlP5gO8A5nsHDs8PZryy7TRgCxnLq+ug2V7PS+AWeiCvZUx75RhZjzl+bRxYkhuPf4NmH3Z3PsaSQXfCkBhePuf8ZSneuOrfyBLEYrqchXcxPYEkwwg1Cyc4RPA7Oyvo6cQw2ujbhRRLDLXdimVVVQgUjBGqFy7FND2G7iMtwaE90xvnHr18BekUSHHhoe21vY+Za+yZZ9zR13d5crKs7JrslTiUsATFDD79t2zU8xhvRHIlP7xI61W+3CwX6NRd7WkUmK0SuVBMpHo5PnncCcrR3g+a1rTL5+mMJ/f1r1C1XZkZASITEttPCWmoUel6ja1PwiCrATxKfDgXfNR9lH9zMtxJIAZe7QZrOu1wng2hTGk7UHnkI/b39IgDv8kdCXb4aFnoDKmDaNPEITJZDKY/KEObR84BTqH1JNX+mLBOxCxk7W9ezvz5vVr4yvdxMvHj/X94BT11+8BxN3eJvJqPvvAfaKE6fpa3eQkFohaJyJzGJ1D6kmr+m78J7iMGV28oz0ygRHuUG1R6e3TqIXEVQHQ+9Cz0cYFRAYQzMMXLz6Vgl8VoO0lsMeMoPGpqUmdZfiCbPGr/PRF4i0je6PBaBSS/vjHN35hK+QnoTP+//t6Ny+Cw5qVHv8XF+mWyZITVTkAAAAASUVORK5CYII=";

	public static final String BOUNDARY_SELECTOR_CANVAS_X_PATH = "//*[@id=\"map\"]/div/canvas";
	public static final String STRURLPath = "/Reports/ComplianceReports";
	public static final String STRPageTitle = Resources.getResource(ResourceKeys.ComplianceReports_PageTitle);
	public static final String STRSurveyIncludedMsg = Resources.getResource(ResourceKeys.ComplianceReport_AlreadyAdded);
	public static final String ComplianceReport_SurveyMissingMessage = Resources
			.getResource(ResourceKeys.ComplianceReport_SurveyMissingMessage);
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.ComplianceReports_PageTitle);
	public static final String STRNewPageContentText = Resources.getResource(ResourceKeys.ComplianceReports_AddNew);
	public static final String STRCopyPageTitle = Resources.getResource(ResourceKeys.ComplianceReport_PageTitle);
	public static final String STRReportTitle = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_ComplianceReportSSRS);
	public static final String ComplianceReportSSRS_LISAInvestigationComplete = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_LISAInvestigationComplete);
	public static final String ComplianceReportSSRS_GAPInvestigationComplete = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_GAPInvestigationComplete);
	public static final String ComplianceReportSSRS_CGIInvestigationComplete = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_CGIInvestigationComplete);
	public static final String ComplianceReportSSRS_MapHeightWidth = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_MapHeightWidth);
	public static final String ComplianceReportSSRS_NELatNELong = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_NELatNELong);
	public static final String ComplianceReportSSRS_SWLatSWLong = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_SWLatSWLong);
	public static final String ComplianceReportSSRS_TimeZone = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_TimeZone);
	public static final String ComplianceReportSSRS_ShowCoverage = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_ShowCoverage);
	public static final String ComplianceReportSSRS_PercentCoverageAssets = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_PercentCoverageAssets);
	public static final String ComplianceReportSSRS_PercentCoverageForecast = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_PercentCoverageForecast);
	public static final String ComplianceReportSSRS_PercentCoverageReportArea = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_PercentCoverageReportArea);
	public static final String ComplianceReportSSRS_PercentServiceCoverageWithLISAs = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_PercentServiceCoveragewithLISAs);
	public static final String ComplianceReportSSRS_PercentServiceCoverageWithoutLISAs = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_PercentServiceCoverageWithoutLISAs);
	public static final String ComplianceReportSSRS_ProbabilitytoObtain70Coverage = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_ProbabilitytoObtain70Coverage);
	public static final String ComplianceReportSSRS_Asset = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_Asset);
	public static final String ComplianceReportSSRS_Boundary = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_Boundary);
	public static final String ComplianceReportSSRS_ViewTable = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_ViewTable);
	public static final String ComplianceReportSSRS_ViewName = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_ViewName);
	public static final String ComplianceReportSSRS_ShowLISAs = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_ShowLISAs);
	public static final String ComplianceReportSSRS_ShowFOV = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_ShowFOV);
	public static final String ComplianceReportSSRS_ShowBreadcrumb = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_ShowBreadcrumb);
	public static final String ComplianceReportSSRS_ShowIndications = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_ShowIndications);
	public static final String ComplianceReportSSRS_ShowIsotopicAnalyses = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_ShowIsotopicAnalyses);
	public static final String ComplianceReportSSRS_FieldNotes = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_FieldNotes);
	public static final String ComplianceReportSSRS_ShowGaps = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_ShowGaps);
	public static final String ComplianceReportSSRS_ShowAssets = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_ShowAssets);
	public static final String ComplianceReportSSRS_ShowBoundaries = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_ShowBoundaries);
	public static final String ComplianceReportSSRS_BaseMap = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_BaseMap);
	public static final String ComplianceReportSSRS_TotalLinearAssetCoverage = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_TotalLinearAssetCoverage);
	public static final String ReportSSRS_SelectedDrivingSurveys = Resources
			.getResource(ResourceKeys.ReportSSRS_SelectedDrivingSurveys);
	public static final String ComplianceReportSSRS_IsotopicAnalysisTable = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_IsotopicAnalysisTable);
	public static final String ComplianceReportSSRS_IndicationTable = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_IndicationTable);
	public static final String ComplianceReportSSRS_GapTable = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_GapTable);
	public static final String ComplianceReportSSRS_EthaneAnalysisTable = Resources
			.getResource(ResourceKeys.ComplianceReportSSRS_EthaneAnalysisTable);

	public static final String LisaInvestigationReportSSRS_Lisa = Resources
			.getResource(ResourceKeys.LisaInvestigationReportSSRS_Lisa);
	public static final String LisaInvestigationReportSSRS_Amplitude = Resources
			.getResource(ResourceKeys.LisaInvestigationReportSSRS_Amplitude);
	public static final String Constant_Status = Resources.getResource(ResourceKeys.Constant_Status);
	public static final String LisaInvestigationReportSSRS_Investigator = Resources
			.getResource(ResourceKeys.LisaInvestigationReportSSRS_Investigator);
	public static final String LisaInvestigationReportSSRS_InvestigationReport = Resources
			.getResource(ResourceKeys.LisaInvestigationReportSSRS_InvestigationReport);

	private static final String DELETE_POPUP_CONFIRM_BUTTON_XPATH = "//*[@id='deleteReportModal']/div/div/div[3]/a[1]";
	private static final String DELETE_POPUP_CANCEL_BUTTON_XPATH = "//*[@id='deleteReportModal']/div/div/div[3]/a[2]";

	public static final String RatioSdevMetaPattern = "\\+/\\-";
	
	public static List<String[]> preCoverageForecastTo70;
	public static List<String[]> preCoverageForecast;

	@FindBy(how = How.ID, using = "compliance-zip-pdf-download")
	protected WebElement zipImg;

	@FindBy(how = How.ID, using = "compliance-zip-shapefile-download")
	protected WebElement zipShape;

	@FindBy(how = How.ID, using = "compliance-zip-reportmeta-download")
	protected WebElement zipMeta;

	@FindBy(how = How.ID, using = "modalClose")
	protected WebElement modalClose;

	@FindBy(css = "#reportViewer > .modal-dialog button.close")
	protected WebElement modalX;
	
    @FindBy(css = "#ImageList > li.dynamic a[href*='DownloadReportView']")
    protected List<WebElement> pdfViews;

    @FindBy(css = "#ImageList > li.dynamic a[href*='DownloadReportView']")
    protected WebElement firstPdfView;
    
	@FindBy(name = "rdAreaMode")
	private List<WebElement> areaBoundaryRadioButtons;

	@FindBy(id = "btn-select-boundary")
	protected WebElement boundarySelectorBtn;

	@FindBy(id = "dvAreaMode_Customer")
	protected WebElement divCustomerBoundarySection;

	@FindBy(id = "dvAreaMode_Custom")
	protected WebElement divCustomBoundarySection;

	@FindBy(id = "report-survey-start-dt")
	protected WebElement startDatePicker;

	@FindBy(id = "report-survey-end-dt")
	protected WebElement checkBoxAllSurvey;

	@FindBy(id = "report-survey-end-dt")
	protected WebElement checkBoxStndSurvey;

	@FindBy(id = "report-survey-end-dt")
	protected WebElement checkBoxRRSurvey;

	@FindBy(id = "report-survey-end-dt")
	protected WebElement checkBoxManualSurvey;

	@FindBy(id = "report-survey-end-dt")
	protected WebElement checkBoxOperatorSurvey;

	@FindBy(id = "report-geo-filter")
	protected WebElement checkBoxGeoFilter;

	@FindBy(how = How.XPATH, using = "//table[@id='datatable']/tbody/tr")
	protected List<WebElement> numberofRecords;

	@FindBy(how = How.XPATH, using = "//a[starts-with(@href,'/Reports/DeleteReport?reportType=ComplianceReports')]")
	protected WebElement btnDeleteConfirm;
	protected String btnDeleteConfirmXpath = "//a[starts-with(@href,'/Reports/DeleteReport?reportType=ComplianceReports')]";

	@FindBy(how = How.XPATH, using = "//*[@id='Standard']")
	protected WebElement checkBoxStndRptMode;

	@FindBy(how = How.XPATH, using = "//*[@id='Rapid Response']")
	protected WebElement checkBoxRRRptMode;

	@FindBy(how = How.XPATH, using = "//*[@id='Manual']")
	protected WebElement checkBoxManualRptMode;

	@FindBy(how = How.XPATH, using = "//*[@id='surveyModal']/div/div/div[3]/a[1]")
	protected WebElement btnChangeRptMode;

	@FindBy(how = How.XPATH, using = "//*[@id='dvErrorText']/ul/li[1]")
	protected WebElement areaErrorText;

	@FindBy(how = How.XPATH, using = "//*[@id=resubmitReportModal']/div/div/div[3]/a[1]")
	protected WebElement btnResubmitReport;

	@FindBy(id = "resubmitReportModal")
	protected WebElement resubmitReportModal;

	@FindBy(how = How.XPATH, using = "//*[@id='resubmitReportModal']/div/div/div[3]/a[1]")
	protected WebElement btnProcessResubmit;

	@FindBy(how = How.XPATH, using = "//*[@id='dvErrorText']/ul/li[1]")
	protected WebElement assetErrorText;

	@FindBy(how = How.XPATH, using = "//*[@id='dvErrorText']/ul/li[2]")
	protected WebElement boundaryErrorText;

	@FindBy(id = "report-ethene-vehicle-exhaust")
	protected WebElement checkBoxVehicleExhaust;

	@FindBy(how = How.XPATH, using = "//*[@id='datatableViews']/thead/tr/th[7]/div")
	protected WebElement viewsAnalysesColumn;

	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[3]/div/div[11]/div/div/div/div[2]/div/label")
	protected WebElement tubularAnalysisOption;

	@FindBy(id = "report-ethene-biogenic-methane")
	protected WebElement checkBoxEtheneBiogeniceMethane;

	@FindBy(id = "report-ethene-possible-natural-gas")
	protected WebElement checkBoxPossibleNaturalGas;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[1]")
	protected WebElement fstRptTilNm;

	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a")
	protected WebElement newComplianceReportBtn;

	@FindBy(how = How.XPATH, using = "//*[@id='report-show-percent-coverage-report-area']")
	protected WebElement percentCoverReportArea;

	@FindBy(how = How.XPATH, using = "//*[@id='report-show-percent-coverage-forecast']")
	protected WebElement percentCoverForecast;

	@FindBy(how = How.XPATH, using = "//*[@id='report-asset-layers-d08fc87f-f979-4131-92a9-3d82f37f4bba']")
	protected WebElement rptFirstAsset;

	@FindBy(how = How.XPATH, using = "//*[@id='report-boundry-layers-Small Boundary']")
	protected WebElement rptSmallBoundary;

	public WebElement getNewComplianceReportBtn() {
		return this.newComplianceReportBtn;
	}

	public WebElement getPercentCoverReportArea() {
		return this.percentCoverReportArea;
	}

	public WebElement getPercentCoverForecast() {
		return this.percentCoverForecast;
	}

	public WebElement getRptFirstAsset() {
		return this.rptFirstAsset;
	}

	public WebElement getRptSmallBoundary() {
		return this.rptSmallBoundary;
	}

	public WebElement getCheckBoxStndRptMode() {
		return this.checkBoxStndRptMode;
	}

	private static LatLongSelectionControl latLongSelectionControl = null;

	public enum CustomerBoundaryType {
		District, DistrictPlat
	}

	public enum ComplianceReportButtonType {
		Delete("Delete"), Copy("Copy"), ReportViewer("ReportViewer"), Investigate("Investigate"), InvestigatePDF(
				"InvestigatePDF"), Resubmit("Resubmit"), Cancel("Cancel"), InProgressCopy(
						"InProgressCopy"), ReportErrorLabel("ReportErrorLabel");

		private final String name;

		ComplianceReportButtonType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public enum ReportViewerThumbnailType {
		InvestigationPDF("InvestigationPDF"), ComplianceTablePDF("ComplianceTablePDF"), ComplianceZipPDF(
				"ComplianceZipPDF "), ComplianceZipShape("ComplianceZipShape "), ComplianceZipMeta(
						"ComplianceZipMeta "), FirstView("FirstView "), SecondView("SecondView "), ThirdView(
								"ThirdView "), FourthView("FourthView "), FifthView("FifthView "), SixthView(
										"SixthView "), SeventhView("SeventhView");

		private final String name;

		ReportViewerThumbnailType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public enum ReportFileType {
		InvestigationPDF("InvestigationPDF"), PDF("PDF"), ZIP("ZIP"), MetaDataZIP("MetaDataZIP"), ShapeZIP(
				"ShapeZIP"), View("View");

		private final String name;

		ReportFileType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	/**
	 * @param driver
	 * @param strBaseURL
	 * @param testSetup
	 */
	public ComplianceReportsPage(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup, strBaseURL + STRURLPath);

		Log.info("\nThe Compliance Reports Page URL is: %s\n" + this.strPageURL);

		latLongSelectionControl = new LatLongSelectionControl(driver);
		PageFactory.initElements(driver, latLongSelectionControl);
	}

	@Override
	public void reportSpecificAddNewReport(String customer, String exclusionRadius, String boundary,
			String imageMapHeight, String imageMapWidth, String NELat, String NELong, String SWLat, String SWLong)
					throws Exception {
		inputExclusionRadius(exclusionRadius);

		this.inputNELat.sendKeys(NELat);
		this.inputNELong.sendKeys(NELong);
		this.inputSWLat.sendKeys(SWLat);
		this.inputSWLong.sendKeys(SWLong);

		this.inputViewLisa.click();
		this.inputViewFOV.click();
		this.inputViewBreadCrumb.click();
		this.inputViewInd.click();
		this.inputViewIso.click();
		this.inputViewAnno.click();
		this.inputViewGaps.click();
		this.inputViewAssets.click();
		this.inputViewBoundaries.click();

		inputImageMapHeight(imageMapHeight);
		inputImageMapWidth(imageMapWidth);

		selectViewLayerAssets(ReportDataProvider.getAllViewLayerAssetsForCustomer(customer));
		selectViewLayerBoundaries(ReportDataProvider.getAllViewLayerBoundariesForCustomer(customer));

		selectIndicationsTableCheckBox();
		this.checkBoxIsoAna.click();
		this.checkBoxPCA.click();
		this.checkBoxPCRA.click();
	}

	public void addNewPDReport(String reportTitle) throws Exception {
		this.addNewReport(reportTitle, null, TIMEZONEPT, REXCLUSIONRADIUS, CUSBOUNDARY, IMGMAPHEIGHT, IMGMAPWIDTH,
				RNELAT, RNELON, RSWLAT, RSWLON, SURVEYORUNIT, TAG, STARTDATE, ENDDATE, REPORTMODES1);
	}

	public void addNewPDReport(String reportTitle, String customer) throws Exception {
		this.addNewReport(reportTitle, customer, TIMEZONEPT, REXCLUSIONRADIUS, CUSBOUNDARY, IMGMAPHEIGHT, IMGMAPWIDTH,
				RNELAT, RNELON, RSWLAT, RSWLON, SURVEYORUNIT, TAG, STARTDATE, ENDDATE, REPORTMODES1);
	}

	public void addNewPDReport(String reportTitle, String surveyor, String tag) throws Exception {
		this.addNewReport(reportTitle, null, TIMEZONEPT, REXCLUSIONRADIUS, CUSBOUNDARY, IMGMAPHEIGHT, IMGMAPWIDTH,
				RNELAT, RNELON, RSWLAT, RSWLON, surveyor, tag, STARTDATE, ENDDATE, REPORTMODES1);
	}

	public void addNewPDReport(String reportTitle, String customer, String surveyor, String tag) throws Exception {
		this.addNewReport(reportTitle, customer, TIMEZONEPT, REXCLUSIONRADIUS, CUSBOUNDARY, IMGMAPHEIGHT, IMGMAPWIDTH,
				RNELAT, RNELON, RSWLAT, RSWLON, surveyor, tag, STARTDATE, ENDDATE, REPORTMODES1);
	}

	public void addNewPDReport(String reportTitle, String surveyor, List<String> tag, boolean changeMode,
			String reportMode) throws Exception {
		this.addNewReport(reportTitle, null, TIMEZONEPT, REXCLUSIONRADIUS, CUSBOUNDARY, IMGMAPHEIGHT, IMGMAPWIDTH,
				RNELAT, RNELON, RSWLAT, RSWLON, surveyor, tag, STARTDATE, ENDDATE, changeMode, reportMode);
	}

	public void addNewPDReport(String reportTitle, String customer, String surveyor, List<String> tag,
			boolean changeMode, String reportMode) throws Exception {
		this.addNewReport(reportTitle, customer, TIMEZONEPT, REXCLUSIONRADIUS, CUSBOUNDARY, IMGMAPHEIGHT, IMGMAPWIDTH,
				RNELAT, RNELON, RSWLAT, RSWLON, surveyor, tag, STARTDATE, ENDDATE, changeMode, reportMode);
	}

	public void addViews(String customer, List<Map<String, String>> viewList) {
		int rowNum;
		int colNum;
		String strBaseXPath;

		for (int i = 0; i < viewList.size(); i++) {
			rowNum = i + 1;
			Map<String, String> viewMap = viewList.get(i);
			if(viewMap==null){
				continue;
			}
			if (i != 0) {
				Log.clickElementInfo("Add Views");
				this.btnAddViews.click();
			}

			if (viewMap.get(KEYVIEWNAME) != null) {
				colNum = 2;
				strBaseXPath = getViewXPathByRowCol(rowNum, colNum);
				strBaseXPath = strBaseXPath + "[@type='text']";
				String viewName = viewMap.get(KEYVIEWNAME);
				Log.info("Set view name to '"+viewName);
				driver.findElement(By.xpath(strBaseXPath)).clear();
				driver.findElement(By.xpath(strBaseXPath)).sendKeys(viewName);
			}

			if (selectView(viewMap, KEYLISA)) {
				colNum = 3;
				Log.clickElementInfo("LISA", ElementType.CHECKBOX);
				strBaseXPath = getViewXPathByRowCol(rowNum, colNum);
				SelectCheckbox(driver.findElement(By.xpath(strBaseXPath + "[@type='checkbox']")));
			}

			if (selectView(viewMap, KEYFOV)) {
				colNum = 4;
				Log.clickElementInfo("FOV", ElementType.CHECKBOX);
				strBaseXPath = getViewXPathByRowCol(rowNum, colNum);
				SelectCheckbox(driver.findElement(By.xpath(strBaseXPath + "[@type='checkbox']")));
			}

			if (selectView(viewMap, KEYBREADCRUMB)) {
				colNum = 5;
				Log.clickElementInfo("BREADCRUMB", ElementType.CHECKBOX);
				strBaseXPath = getViewXPathByRowCol(rowNum, colNum);
				SelectCheckbox(driver.findElement(By.xpath(strBaseXPath + "[@type='checkbox']")));
			}

			if (selectView(viewMap, KEYINDICATIONS)) {
				colNum = 6;
				Log.clickElementInfo("INDICATIONS", ElementType.CHECKBOX);
				strBaseXPath = getViewXPathByRowCol(rowNum, colNum);
				SelectCheckbox(driver.findElement(By.xpath(strBaseXPath + "[@type='checkbox']")));
			}

			if (selectView(viewMap, KEYISOTOPICCAPTURE)) {
				colNum = 7;
				Log.clickElementInfo("ISOTOPICCAPTURE", ElementType.CHECKBOX);
				strBaseXPath = getViewXPathByRowCol(rowNum, colNum);
				SelectCheckbox(driver.findElement(By.xpath(strBaseXPath + "[@type='checkbox']")));
			}

			if (selectView(viewMap, KEYANNOTATION)) {
				colNum = 8;
				Log.clickElementInfo("ANNOTATION", ElementType.CHECKBOX);
				strBaseXPath = getViewXPathByRowCol(rowNum, colNum);
				SelectCheckbox(driver.findElement(By.xpath(strBaseXPath + "[@type='checkbox']")));
			}

			if (selectView(viewMap, KEYGAPS)) {
				colNum = 9;
				Log.clickElementInfo("GAPS", ElementType.CHECKBOX);
				strBaseXPath = getViewXPathByRowCol(rowNum, colNum);
				SelectCheckbox(driver.findElement(By.xpath(strBaseXPath + "[@type='checkbox']")));
			}

			if (selectView(viewMap, KEYASSETS)) {
				colNum = 10;
				Log.clickElementInfo("ASSETS", ElementType.CHECKBOX);
				strBaseXPath = getViewXPathByRowCol(rowNum, colNum);
				SelectCheckbox(driver.findElement(By.xpath(strBaseXPath + "[@type='checkbox']")));
			}

			if (selectView(viewMap, KEYHIGHLIGHTLISAASSETS)) {
				colNum = 11;
				Log.clickElementInfo("Highlight LISA Assets", ElementType.CHECKBOX);
				strBaseXPath = getViewXPathByRowCol(rowNum, colNum);
				SelectCheckbox(driver.findElement(By.xpath(strBaseXPath + "[@type='checkbox']")));
			}
			
			if (selectView(viewMap, KEYHIGHLIGHTGAPASSETS)) {
				colNum = 12;
				Log.clickElementInfo("Highlight GAP Assets", ElementType.CHECKBOX);
				strBaseXPath = getViewXPathByRowCol(rowNum, colNum);
				SelectCheckbox(driver.findElement(By.xpath(strBaseXPath + "[@type='checkbox']")));
			}
			
			if (selectView(viewMap, KEYBOUNDARIES)) {
				colNum = 13;
				Log.clickElementInfo("BOUNDARIES", ElementType.CHECKBOX);
				strBaseXPath = getViewXPathByRowCol(rowNum, colNum);
				SelectCheckbox(driver.findElement(By.xpath(strBaseXPath + "[@type='checkbox']")));
			}

			if (viewMap.get(KEYBASEMAP) != null) {
				if (rowNum == 1) {
					strBaseXPath = "//*[@id='datatableViews']/tbody/tr/td/select[contains(@class,'view-basemap')]";
				} else {
					strBaseXPath = "//*[@id='datatableViews']/tbody/tr[" + rowNum
							+ "]/td/select[contains(@class,'view-basemap')]";
				}
				WebElement dropdownBaseMap = driver.findElement(By.xpath(strBaseXPath));
				List<WebElement> options = dropdownBaseMap.findElements(By.tagName("option"));
				for (WebElement option : options) { 
					String thisMap = viewMap.get(KEYBASEMAP);
					if ((thisMap).equalsIgnoreCase(option.getText().trim())) {
						Log.info(String.format("Select base map - '%s'", thisMap));
						option.click();
						break;
					}
				}
			}
		}
	}

	private boolean selectView(Map<String, String> viewMap, String option){
		boolean select = false;
		String value = viewMap.get(option);
		if(value!=null
				&&value.equalsIgnoreCase("1")){
			select = true;
		}
		return select;
	}
	private String getViewXPathByRowCol(int rowNum, int colNum) {
		String strBaseXPath;
		if (rowNum == 1) {
			strBaseXPath = "//*[@id='datatableViews']/tbody/tr/td[" + colNum + "]/input";
		} else {
			strBaseXPath = "//*[@id='datatableViews']/tbody/tr[" + rowNum + "]/td[" + colNum + "]/input";
		}
		return strBaseXPath;
	}

	public boolean isShapeIconDisplayedInViewer() {
		return WebElementExtender.isElementPresentAndDisplayed(zipShape);
	}

	public boolean isMetadataIconDisplayedInViewer() {
		return WebElementExtender.isElementPresentAndDisplayed(zipMeta);
	}

	public boolean isReportZipIconDisplayedInViewer() {
		return WebElementExtender.isElementPresentAndDisplayed(zipImg);
	}

	public boolean isReportPDFIconDisplayedInViewer() {
		return WebElementExtender.isElementPresentAndDisplayed(pdfImg);
	}

	public void clickOnCloseReportViewer(String btn) {
		if(btn!=null&&btn.equalsIgnoreCase("XButton")){
		   modalX.click();
		}else{
		   modalClose.click();
		}
	}
	
	public void clickViewThumbnailImageByIndex(int viewIdx){
		jsClick(getViewThumbnailImageByIndex(viewIdx));
	}
	
	public WebElement getViewThumbnailImageByIndex(int viewIdx){
		if(firstPdfView.isDisplayed()){
		   return pdfViews.get(viewIdx-1);
		}
		return null;
	}	
	
	public void clickOnShapeZIPInReportViewer() {
		Log.clickElementInfo("Shape ZIP", ElementType.LINK);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", zipShape);
	}

	public void clickOnMetadataZIPInReportViewer() {
		Log.clickElementInfo("Meta ZIP", ElementType.LINK);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", zipMeta);
	}

	public void clickOnZIPInReportViewer() {
		Log.clickElementInfo("ZIP", ElementType.LINK);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", zipImg);
	}

	public void clickOnPDFInReportViewer() {
		Log.clickElementInfo("PDF", ElementType.LINK);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", pdfImg);
	}

	public void clickOnFirstInvestigateComplianceBtn() {
		Log.clickElementInfo("Investigate of the first compliance report", ElementType.LINK);
		this.btnFirstInvestigateCompliance.click();
	}

	public void clickLatLongMapSelectorBtn() {
		Log.clickElementInfo("Lat/Long Map Selector");
		this.latLongMapSelectorBtn.click();
	}

	public void clickBoundarySelectorBtn() {
		Log.clickElementInfo("Boundary Selector");
		this.boundarySelectorBtn.click();
	}

	public void clickOnConfirmInDeleteReportPopup() {
		WebElement confirmDelete = this.driver.findElement(By.xpath(DELETE_POPUP_CONFIRM_BUTTON_XPATH));
		Log.clickElementInfo("Confirm of deletion");
		confirmDelete.click();
	}

	public void clickOnCancelInDeleteReportPopup() {
		WebElement cancelDelete = this.driver.findElement(By.xpath(DELETE_POPUP_CANCEL_BUTTON_XPATH));
		Log.clickElementInfo("Cancel of deletion");
		cancelDelete.click();
	}

	public boolean clickComplianceReportButton(String rptTitle, String strCreatedBy,
			ComplianceReportButtonType buttonType) throws Exception {
		return checkComplianceReportButtonPresenceAndClick(rptTitle, strCreatedBy, buttonType, true,
				true /* By default confirm the action */);
	}

	public boolean clickComplianceReportButton(String rptTitle, String strCreatedBy,
			ComplianceReportButtonType buttonType, boolean confirmAction) throws Exception {
		return checkComplianceReportButtonPresenceAndClick(rptTitle, strCreatedBy, buttonType, true, confirmAction);
	}

	@Override
	public boolean handleFileDownloads(String rptTitle, String testCaseID) throws Exception {
		String reportName = "CR-" + getReportName(rptTitle);
		clickOnPDFInReportViewer();
		waitForPDFFileDownload(reportName);
		Log.info("SSRS zip file got downloaded");
		clickOnZIPInReportViewer();
		waitForReportZIPFileDownload(reportName);
		checkAndGenerateBaselineSSRSImage(reportName, testCaseID);
		String zipFileName = getReportPDFZipFileName(rptTitle, false /*includeextension*/);
		Log.info("PDF zip file got downloaded");
		try {
			BaseHelper.deCompressZipFile(zipFileName, testSetup.getDownloadPath());
		} catch (Exception e) {
			Log.error(e.toString());
			return false;
		}
		String unzipFolder = Paths.get(testSetup.getDownloadPath(), zipFileName).toString();
		checkAndGenerateBaselineViewImages(unzipFolder, testCaseID);

		if (zipMeta.isDisplayed()) {
			clickOnMetadataZIPInReportViewer();
			waitForMetadataZIPFileDownload(reportName);
			zipFileName = getReportMetaZipFileName(rptTitle, false /*includeextension*/);
			Log.info("Meta data zip file got downloaded");
			try {
				BaseHelper.deCompressZipFile(zipFileName, testSetup.getDownloadPath());
			} catch (Exception e) {
				Log.error(e.toString());
				return false;
			}
		}
		if (zipShape.isDisplayed()) {
			clickOnShapeZIPInReportViewer();
			waitForShapeZIPFileDownload(reportName);
			Log.info("Shape files zip file got downloaded");
			zipFileName = getReportShapeZipFileName(rptTitle, false /*includeextension*/);
			try {
				BaseHelper.deCompressZipFile(zipFileName, testSetup.getDownloadPath());
			} catch (Exception e) {
				Log.error(e.toString());
				return false;
			}
			if (testCaseID != null) {
				try {
					Path shapeUnzipFolder = Paths.get(testSetup.getDownloadPath(), zipFileName);
					checkAndGenerateBaselineShapeFiles(shapeUnzipFolder.toString(), testCaseID);
				}catch(Exception e){
					Log.error(e.toString());
					return false;
				}
			}
		}
		return true;
	}

	public String getReportName(String rptTitle) {
		Report objReport = Report.getReport(rptTitle);
		String reportId = objReport.getId();
		reportId = reportId.substring(0, 6);
		Log.info("The reportID of "+rptTitle+"' is '"+reportId+"'");
		return reportId;
	}

	public String getReportPDFZipFileName(String rptTitle, boolean includeExtension) {
		return getReportPDFZipFileName(rptTitle, 0, includeExtension);
	}

	public String getReportPDFZipFileName(String rptTitle, int zipIndex, boolean includeExtension) {
		String reportName = "CR-" + getReportName(rptTitle) + "-PDF";
		reportName = getZipFileNameWithIndex(reportName, zipIndex);
		if (includeExtension) {
			reportName += ".zip";
		}
		return reportName;
	}

	public String getReportMetaZipFileName(String rptTitle, boolean includeExtension) {
		return getReportMetaZipFileName(rptTitle, 0, includeExtension);
	}

	public String getReportMetaZipFileName(String rptTitle, int zipIndex, boolean includeExtension) {
		String reportName = "CR-" + getReportName(rptTitle) + "-Meta";
		reportName = getZipFileNameWithIndex(reportName, zipIndex);
		if (includeExtension) {
			reportName += ".zip";
		}
		return reportName;
	}

	public String getReportShapeZipFileName(String rptTitle, boolean includeExtension) {
		return getReportShapeZipFileName(rptTitle, 0, includeExtension);
	}

	public String getReportShapeZipFileName(String rptTitle, int zipIndex, boolean includeExtension) {
		String reportName = "CR-" + getReportName(rptTitle) + "-Shape";
		reportName = getZipFileNameWithIndex(reportName, zipIndex);
		if (includeExtension) {
			reportName += ".zip";
		}
		return reportName;
	}

	public String getReportPDFFileName(String rptTitle, boolean includeExtension) {
		String reportName = "CR-" + getReportName(rptTitle);
		if (includeExtension) {
			reportName += ".pdf";
		}
		return reportName;
	}

	public String getInvestigationPDFFileName(String rptTitle, boolean includeExtension) {
		String reportName = "IV-" + getReportName(rptTitle);
		if (includeExtension) {
			reportName += ".pdf";
		}
		return reportName;
	}

	public boolean checkAndGenerateBaselineShapeFiles(String unzipFolder, String testCaseID) throws Exception {
		boolean isGenerateBaselineShapeFiles = TestContext.INSTANCE.getTestSetup().isGenerateBaselineShapeFiles();
		Path unzipDirectory = Paths.get(unzipFolder);
		List<String> filesInDirectory = FileUtility.getFilesInDirectory(unzipDirectory, "*.shp,*.dbf,*.prj,*.shx");
		for (String filePath : filesInDirectory) {
			String newFilename = replaceReportIdWith(filePath, testCaseID);
			new File(filePath).renameTo(new File(newFilename));
			if(isGenerateBaselineShapeFiles){
			   generateBaselineShapeFile(testCaseID, newFilename);
			}
		}
		return isGenerateBaselineShapeFiles;
	}
	
	public boolean checkAndGenerateBaselineSSRSImage(String reportName, String testCaseID) throws Exception {
		boolean isGenerateBaselineSSRSImages = TestContext.INSTANCE.getTestSetup().isGenerateBaselineSSRSImages();
		if (isGenerateBaselineSSRSImages) {
			String htmlReportName = reportName + ".html";
			String htmlReportPath = Paths.get(testSetup.getDownloadPath(), htmlReportName).toString();
			reportName = reportName + ".pdf";
			File f = new File(htmlReportPath);
			convertPdfToHtml(reportName, htmlReportName);
			Document doc = Jsoup.parse(f, null, "");
			Elements elements = doc.select("img[src]");
			int pageCounter = 1;
			for (Element element : elements) {
				String base64String = element.attr("src").replace("data:image/png;base64,", "");
				if (!(base64String.equals(BASE64_IGNORE))) {
					String pathToActualImage = Paths.get(testSetup.getDownloadPath(), "Page_" + pageCounter + ".png").toString();
					createImageFromBASE64(base64String, pathToActualImage);
					generateBaselineSSRSImage(testCaseID, pathToActualImage);
					Files.delete(Paths.get(pathToActualImage));
					pageCounter++;
				}
			}
		}
		return isGenerateBaselineSSRSImages;
	}
	
	public boolean checkAndGenerateBaselineViewImages(String unzipFolder, String testCaseID) throws Exception {
		PDFUtility pdfUtility = new PDFUtility();
		boolean isGenerateBaselineViewImages = TestContext.INSTANCE.getTestSetup().isGenerateBaselineViewImages();
		if (isGenerateBaselineViewImages) {
			File downLoadedFolder = new File(unzipFolder);
			File[] listOfViews;
			if(downLoadedFolder.isFile()){
				listOfViews = new File[1];
				listOfViews[0] = downLoadedFolder;
			}else{
			    listOfViews = downLoadedFolder.listFiles();
			}
			int counter = 1;
			for (File file : listOfViews) {
				if (file.isFile()) {
					if (file.getName().contains("View")) {
						String actualView = Paths.get(unzipFolder, file.getName()).toString();
						String imageExtractFolder = pdfUtility.extractPDFImages(actualView, testCaseID);
						File folder = new File(imageExtractFolder);
						File[] listOfFiles = folder.listFiles();
						for (File extractedImeg : listOfFiles) {
							if (extractedImeg.isFile()) {
								BufferedImage image = ImageIO.read(extractedImeg);
								int width = image.getWidth();
								int height = image.getHeight();
								Rectangle rect = new Rectangle(0, 0, width, height - 40);
								image = cropImage(image, rect);
								String outputfile = Paths.get(TestSetup.getSystemTempDirectory(), testCaseID + "_View" + counter + ".png").toString();
								File croppedFile = new File(outputfile);
								ImageIO.write(image, "png", croppedFile);
								generateBaselineViewImage(testCaseID, outputfile, counter);
								counter++;
							}
						}
					}
				}
			}
		}
		return isGenerateBaselineViewImages;
	}

	protected void generateBaselinePerfFiles(String testCaseID, String reportId, String startTime, String endTime,
			Integer processingTimeInMs) throws IOException {
		String rootFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "data";
		String expectedDataFolderPath = Paths.get(rootFolder, "perf-metric" + File.separator + "report-job-metrics" + File.separator + testCaseID).toString();
		// Create the directory for test case if it does not exist.
		FileUtility.createDirectoryIfNotExists(expectedDataFolderPath);
		Path expectedFilePath = Paths.get(expectedDataFolderPath, String.format("%s.csv", testCaseID));
		String reportMetricString = String.format("%s,%s,%s,%d", reportId, startTime, endTime, processingTimeInMs);
		FileUtility.createOrWriteToExistingTextFile(expectedFilePath, reportMetricString);
	}

	protected void generateBaselineSSRSImage(String testCaseID, String imageFileFullPath) throws IOException {
		String rootFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "data";
		String expectedDataFolderPath = Paths.get(rootFolder, "test-expected-data" + File.separator + "ssrs-images" + File.separator + testCaseID).toString();
		// Create the directory for test case if it does not exist.
		FileUtility.createDirectoryIfNotExists(expectedDataFolderPath);
		String expectedFilename = FileUtility.getFileName(imageFileFullPath);
		Path expectedFilePath = Paths.get(expectedDataFolderPath, expectedFilename);
		FileUtils.copyFile(new File(imageFileFullPath), new File(expectedFilePath.toString()));
	}

	protected void generateBaselineShapeFile(String testCaseID, String shapeFileFullPath) throws Exception {
		if (!shapeFileFullPath.matches(".*\\.(dbf|prj|shp|shx)")) {
			return;
		}
		String rootFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "data";
		String expectedDataFolderPath = Paths.get(rootFolder, "test-expected-data" + File.separator + "shape-files" + File.separator + testCaseID).toString();
		// Create the directory for test case if it does not exist.
		FileUtility.createDirectoryIfNotExists(expectedDataFolderPath);
		String expectedFilename = FileUtility.getFileName(shapeFileFullPath);
		Path expectedFilePath = Paths.get(expectedDataFolderPath, expectedFilename);
		// Delete existing files in directory (if any).
		FileUtility.deleteFile(expectedFilePath);
		// Copy the file to the test case folder.
		FileUtils.copyFile(new File(shapeFileFullPath), new File(expectedFilePath.toString()));
	}

	@Override
	protected boolean handleFileDownloads(int rowNum) {
		// return true in BASE;
		// In DERIVED. Use this implementation. EQ might have a separate
		// implementation.
		String pdfImgXPath;
		String zipImgXPath;
		WebElement pdfImg;
		WebElement zipImg;
		pdfImgXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[4]/a[3]/img";
		pdfImg = getTable().findElement(By.xpath(pdfImgXPath));
		String srcPdfImg = pdfImg.getAttribute("src");
		zipImgXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[4]/a[4]/img";
		zipImg = getTable().findElement(By.xpath(zipImgXPath));
		String srcZipImg = zipImg.getAttribute("src");
		if (srcPdfImg.contains("pdf") && srcZipImg.contains("zip")) {
			Log.clickElementInfo("PDF",ElementType.ICON);
			pdfImg.click();
			waitForPDFFileDownload(getReportName());
			Log.clickElementInfo("ZIP",ElementType.ICON);
			zipImg.click();
			waitForReportZIPFileDownload(getReportName());
			return true;
		} else
			return false;
	}

	/**
	 * 
	 * @param rptTitle
	 * @param strCreatedBy
	 * @param buttonType
	 * @param clickButton
	 * @param confirmAction
	 *            - Confirms to complete action. For eg. if Delete button is
	 *            clicked: Click Confirm button if this is TRUE or click Cancel
	 *            when this flag is FALSE.
	 * @return
	 * @throws Exception
	 */

	public boolean checkComplianceReportButtonPresenceAndClick(String rptTitle, String strCreatedBy,
			ComplianceReportButtonType buttonType, boolean clickButton, boolean confirmAction) throws Exception {
		setPagination(PAGINATIONSETTING);
		this.waitForPageLoad();

		String reportTitleXPath;
		String createdByXPath;
		String buttonXPath;

		String rptTitleCellText;
		String createdByCellText;
		WebElement buttonImg;
		boolean removeDBCache = false;
		switch (buttonType) {
		case Delete:
			buttonXPath = "td[5]/a[1]";
			break;
		case Copy:
			buttonXPath = "td[5]/a[@title='Copy']";
			removeDBCache = true;
			break;
		case ReportViewer:
			buttonXPath = "td[5]/a[3]";
			break;
		case Investigate:
			buttonXPath = "td[5]/a[4]";
			break;
		case Resubmit:
			buttonXPath = "td[5]/a[@title='Resubmit']";
			removeDBCache = true;
			break;
		case InProgressCopy: // NOTE: When report is in-progress, Copy is the 1st button.
			buttonXPath = "td[5]/a[@title='Copy']";
			break;
		case Cancel: // NOTE: When cancel button is visible it is the 2nd button.
			buttonXPath = "td[5]/a[@title='Cancel Report']";
			break;
		case ReportErrorLabel: // 'Error Processing' label on report
			// cancelled or report error.
            buttonXPath = "td[5]/span";
            break;
		default:
			throw new Exception("ButtonType NOT supported.");
		}
		
		List<WebElement> rows = getTable().findElements(By.xpath("tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		Log.info(String.format("Looking for rptTitle=[%s], strCreatedBy=[%s]", rptTitle, strCreatedBy));

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "tr[" + rowNum + "]/td[1]";
			createdByXPath = "tr[" + rowNum + "]/td[3]";

			try{
				rptTitleCellText = getTable().findElement(By.xpath(reportTitleXPath)).getText().trim();
				createdByCellText = getTable().findElement(By.xpath(createdByXPath)).getText().trim();
			}catch(Exception e){
				Log.error("Failed to get text of report title/createdBy cells on row '"+rowNum+"' and will try again: "+e);
				rowNum--;
				continue;
			}
			Log.info(String.format("Found rptTitleCell.getText()=[%s], createdByCell.getText()=[%s]",
					rptTitleCellText, createdByCellText));
			if (rptTitleCellText.equalsIgnoreCase(rptTitle)
					&& createdByCellText.equalsIgnoreCase(strCreatedBy)) {
				try {
                    buttonXPath = "tr[" + rowNum + "]/"+ buttonXPath;
					buttonImg = getTable().findElement(By.xpath(buttonXPath));
					if (buttonImg.isDisplayed()) {
						if (clickButton) {
							if (buttonType != ComplianceReportButtonType.ReportErrorLabel) {
								Log.clickElementInfo(buttonType.toString());
								buttonImg.click();
								// If resubmit then wait for modal and confirm resubmit.
								if (buttonType == ComplianceReportButtonType.Resubmit) {
									this.waitForResubmitPopupToShow();
									Log.clickElementInfo("Confirm Resubmit");
									this.btnProcessResubmit.click();
									this.waitForPageLoad();
									this.waitForAJAXCallsToComplete();
								}
								if (buttonType == ComplianceReportButtonType.Delete) {
									this.waitForConfirmDeletePopupToShow();
									if (confirmAction) {
										Log.clickElementInfo("Confirm Delete");
										this.clickOnConfirmInDeleteReportPopup();
										this.waitForConfirmDeletePopupToClose();
									}
								}
								
								if(removeDBCache){
									   DBCache.INSTANCE.remove(Report.CACHE_KEY+rptTitle);
									}
							}
						}
						return true;
					}
					Log.error("Button image is not visible '"+buttonXPath+"'");
					return false;
				} catch (org.openqa.selenium.NoSuchElementException e) {
					Log.error("Button image not found '"+buttonXPath+"': "+e);
					return false;
				}
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();

				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());

				List<WebElement> newRows = getTable().findElements(By.xpath("tr"));
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

	public void inputImageMapWidth(String imageMapWidth) {
		if (imageMapWidth == null || imageMapWidth.equals("")) {
			return;
		}
		Log.info("Set image width to '"+imageMapWidth+"'");
		this.inputImgMapWidth.clear();
		this.inputImgMapWidth.sendKeys(imageMapWidth);
	}

	public void inputImageMapHeight(String imageMapHeight) {
		if (imageMapHeight == null || imageMapHeight.equals("")) {
			return;
		}
		Log.info("Set image height to '"+imageMapHeight+"'");
		this.inputImgMapHeight.clear();
		this.inputImgMapHeight.sendKeys(imageMapHeight);
	}

	public void inputExclusionRadius(String exclusionRadius) {
		Log.info("Set exclusion redius to '"+exclusionRadius+"'");
		this.inputExclusionRadius.clear();
		this.inputExclusionRadius.sendKeys(exclusionRadius);
	}

	public void inputFOVOpacity(String fovOpacity) {
		Log.info("Set FOV Opacity to '"+fovOpacity+"'");
		this.inputFOVOpacity.clear();
		this.inputFOVOpacity.sendKeys(fovOpacity);
	}

	public void inputLISAOpacity(String lisaOpacity) {
		Log.info("Set LISA Opacity to '"+lisaOpacity+"'");
		this.inputLISAOpacity.clear();
		this.inputLISAOpacity.sendKeys(lisaOpacity);
	}

	public void inputSurveyUsername(String username) {
		Log.info("Set survey username to '"+username+"'");
		this.userName.clear();
		this.userName.sendKeys(username);
	}

	public void fillCustomBoundaryTextFields(String neLat, String neLong, String swLat, String swLong) {
		if (neLat != null) {
			Log.info("Set NELat to '"+neLat+"'");
			this.inputNELat.clear();
			this.inputNELat.sendKeys(neLat);
		}
		if (neLong != null) {
			Log.info("Set NELong to '"+neLong+"'");
			this.inputNELong.clear();
			this.inputNELong.sendKeys(neLong);
		}
		if (swLat != null) {
			Log.info("Set SWLat to '"+swLat+"'");
			this.inputSWLat.clear();
			this.inputSWLat.sendKeys(swLat);
		}
		if (swLong != null) {
			Log.info("Set SWLong to '"+swLong+"'");
			this.inputSWLong.clear();
			this.inputSWLong.sendKeys(swLong);
		}
	}

	public ReportModeFilter getReportMode(String reportMode) {
		ReportModeFilter mode = ReportModeFilter.Manual;
		if (reportMode.equalsIgnoreCase("standard")) {
			mode = ReportModeFilter.Standard;
		} else if (reportMode.equalsIgnoreCase("assessment")) {
			mode = ReportModeFilter.Assessment;
		} else if (reportMode.equalsIgnoreCase("eq")) {
			mode = ReportModeFilter.EQ;
			
		} else if (reportMode.equalsIgnoreCase("operator")) {
			mode = ReportModeFilter.Operator;
		} else if (reportMode.equalsIgnoreCase("manual")) {
			mode = ReportModeFilter.Manual;
		} else if (reportMode.equalsIgnoreCase("rr")||reportMode.equalsIgnoreCase("RapidResponse")) {
			mode = ReportModeFilter.RapidResponse;
		}
		return mode;
	}

	public SurveyModeFilter getSurveyMode(String surveyMode) {
		SurveyModeFilter mode = SurveyModeFilter.Manual;
		if (surveyMode.equalsIgnoreCase("standard")) {
			mode = SurveyModeFilter.Standard;
		} else if (surveyMode.equalsIgnoreCase("operator")) {
			mode = SurveyModeFilter.Operator;
		} else if (surveyMode.equalsIgnoreCase("manual")) {
			mode = SurveyModeFilter.Manual;
		} else if (surveyMode.equalsIgnoreCase("rr")) {
			mode = SurveyModeFilter.RapidResponse;
		} else if (surveyMode.equalsIgnoreCase("all")){
			mode = SurveyModeFilter.All;
		}
		return mode;
	}
	private void handleOptionalDynamicViewLayersSection(List<Map<String, String>> viewLayersList) {
		if (viewLayersList != null && !viewLayersList.isEmpty()) {
			selectViewLayerAssets(viewLayersList.get(0));
			selectViewLayerBoundaries(viewLayersList.get(0));
		}
	}

	public boolean investigateReport(String rptTitle, String strCreatedBy) {
		setPagination(PAGINATIONSETTING);

		this.waitForPageLoad();

		String reportTitleXPath;
		String createdByXPath;
		String investigateImgXPath;

		WebElement rptTitleCell;
		WebElement createdByCell;
		WebElement investigateImg;

		List<WebElement> rows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[3]";

			rptTitleCell = getTable().findElement(By.xpath(reportTitleXPath));
			createdByCell = getTable().findElement(By.xpath(createdByXPath));

			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle)
					&& createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy)) {
				investigateImgXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]/a[4]/img";
				investigateImg = getTable().findElement(By.xpath(investigateImgXPath));
				Log.clickElementInfo("Investigate",ElementType.ICON);
				investigateImg.click();
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				Log.clickElementInfo("Next");
				this.nextBtn.click();

				this.waitForPageLoad();

				List<WebElement> newRows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
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

	public boolean resubmitReport(String rptTitle, String strCreatedBy) {
		setPagination(PAGINATIONSETTING);

		this.waitForPageLoad();

		String reportTitleXPath;
		String createdByXPath;
		String resubmitImgXPath;

		WebElement rptTitleCell;
		WebElement createdByCell;
		WebElement resubmitImg;

		List<WebElement> rows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[3]";

			rptTitleCell = getTable().findElement(By.xpath(reportTitleXPath));
			createdByCell = getTable().findElement(By.xpath(createdByXPath));

			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle)
					&& createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy)) {
				resubmitImgXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]/a[2]/img";
				resubmitImg = getTable().findElement(By.xpath(resubmitImgXPath));
				Log.clickElementInfo("Resubmit",ElementType.ICON);
				resubmitImg.click();

				this.waitForCopyReportPagetoLoad();
				this.waitForInputTitleToEnable();

				this.waitForOkButtonToEnable();
				Log.clickElementInfo("OK");
				clickOnOKButton();

				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				Log.clickElementInfo("Next");
				this.nextBtn.click();

				this.waitForPageLoad();

				List<WebElement> newRows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
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

	public boolean validatePdfFiles(String reportTitle, String downloadPath) {
		String reportName;
		String reportZipName;
		try {
			reportName = getReportPDFFileName(reportTitle, false /*includeExtension*/);
			reportZipName = getReportPDFZipFileName(reportTitle, false /*includeExtension*/);
			setReportName(reportName);
			BaseHelper.deCompressZipFile(reportZipName, downloadPath);
		} catch (Exception e) {
			Log.error(e.toString());
			return false;
		}

		String nameBase = reportTitle.trim().replaceAll(" ", "_");
		String pdfFile1;
		String pdfFile2;
		String pdfFile3;

		pdfFile1 = Paths.get(downloadPath, reportName + ".pdf").toString();
		pdfFile3 = Paths.get(downloadPath, reportZipName + File.separator + nameBase.replaceAll("_", "") + ".pdf").toString();

		if (BaseHelper.validatePdfFile(pdfFile1) && BaseHelper.validatePdfFile(pdfFile3)) {
			try {
				if (!BaseHelper.compareTwoFilesByContent(pdfFile1, pdfFile3))
					return false;
			} catch (IOException e) {
				Log.error(e.toString());
				return false;
			}
		} else
			return false;

		pdfFile2 = Paths.get(downloadPath, reportName + File.separator + nameBase.replaceAll("_", "") + "_First View.pdf").toString();

		if (!BaseHelper.validatePdfFile(pdfFile2))
			return false;

		return true;
	}

	public boolean validatePdfFiles(ReportsCompliance reportsCompliance, String downloadPath) {
		String reportName;
		String reportZipName;
		try {
			reportName = getReportPDFFileName(reportsCompliance.getRptTitle(), false /*includeExtension*/);
			reportZipName = getReportPDFZipFileName(reportsCompliance.getRptTitle(), false /*includeExtension*/);
			setReportName(reportName);
			BaseHelper.deCompressZipFile(reportZipName, downloadPath);
		} catch (Exception e) {
			Log.error(e.toString());
			return false;
		}

		String nameBase = reportsCompliance.getRptTitle().trim().replaceAll(" ", "_");
		String viewName;
		String pdfFile1;
		String pdfFile2;
		String pdfFile3;

		List<Map<String, String>> viewList = reportsCompliance.getViewList();

		pdfFile1 = Paths.get(downloadPath, reportName + ".pdf").toString();
		pdfFile3 = Paths.get(downloadPath, reportZipName + File.separator + nameBase.replaceAll("_", "") + ".pdf").toString();

		if (BaseHelper.validatePdfFile(pdfFile1) && BaseHelper.validatePdfFile(pdfFile3)) {
			try {
				if (!BaseHelper.compareTwoFilesByContent(pdfFile1, pdfFile3))
					return false;
			} catch (IOException e) {
				Log.error(e.toString());
				return false;
			}
		} else
			return false;

		for (int i = 0; i < viewList.size(); i++) {
			viewName = viewList.get(i).get(KEYVIEWNAME);
			pdfFile2 = Paths.get(downloadPath, reportZipName + File.separator + nameBase.replaceAll("_", "") + "_" + viewName + ".pdf").toString();
			if (!BaseHelper.validatePdfFile(pdfFile2)) {
				Log.info("PDF Validation failed for: " + pdfFile2);
				return false;
			}
		}

		return true;
	}

	/**
	 * Method to compare the report creation date with current date & Report
	 * creation date format with locale
	 * 
	 * @param actualPath
	 *            - actual path to the generated report
	 * @return boolean - true or false based on whether the report creation date
	 *         matches the current date and format matches the locale format
	 * @throws IOException
	 */

	public boolean validateReportCreationDate(String actualPath) throws IOException {
		String reportDate = null;
		String actualReport = actualPath + getReportName().trim() + ".pdf";
		PDFUtility pdfUtility = new PDFUtility();
		String actualReportString = pdfUtility.extractPDFText(actualReport, 0, 1);
		String[] lines = actualReportString.split("\\n");
		Pattern pattertoMatch = Pattern.compile("Report Creation Date");
		for (String line : lines) {
			String formatteLine = line.trim();
			if (pattertoMatch.matcher(line).find()) {
				Matcher matcher = pattertoMatch.matcher(formatteLine);
				matcher.find();
				reportDate = formatteLine.substring(matcher.end() + 1).trim();
			}
		}
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY hh:mm a zzz");
		String currentDate = dateFormat.format(new Date()).toString();
		if (DateUtility.compareDateTimeFormat(reportDate, true)
				&& (DateUtility.compareDates(currentDate.toString(), reportDate, true))) {
			return true;
		}
		Log.error("Date not match, expected: "+currentDate+", actual: "+reportDate);
		return false;
	}

	public boolean checkBlankReportErrorTextPresent() {
		openNewReportPage();
		this.clickOnOKButton();
		if (isElementPresent(strErrorText))
			return true;
		return false;
	}

	@Override
	public void modifyComplianceViews() {

		this.inputViewInd.click();
		this.inputViewIso.click();
		this.inputViewAnno.click();
	}

	@Override
	public void complianceChangeMode(String rptTitleNew, boolean changeMode, ReportModeFilter strReportMode) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (strReportMode != null && changeMode) {
			selectReportMode(strReportMode);
			this.waitForCopyReportPagetoLoad();
			this.inputTitle.clear();
			js.executeScript("arguments[0].value='" + rptTitleNew + "';", inputTitle);
		} else {
			this.waitForCopyReportPagetoLoad();
			js.executeScript("window.scrollBy(0,250)", "");
			this.waitForDeleteSurveyButtonToLoad();
			js.executeScript("arguments[0].click();", this.btnDeleteSurvey);
		}

	}

	public boolean deleteAllDrivingSurveys() {
		for (WebElement btnDelete : btnDeleteDrivingSurveys) {
			jsClick(btnDelete);
			this.waitForPageToLoad();
		}
		return btnDeleteDrivingSurveys.isEmpty();
	}

	public boolean deleteSurveyAndIncludeAgain(String surveyTag) {
		this.btnDeleteDrivingSurvey.click();
		this.waitForCopyReportPagetoLoad();

		try {
			driver.findElement(By.xpath(strFirstSurveyTag));
			return false;
		} catch (NoSuchElementException e) {
			if (surveyTag != "") {
				inputSurveyTag(surveyTag);
				this.btnSurveySearch.click();
				this.waitForSurveyTabletoLoad();
				this.checkboxSurFirst.click();
				this.btnAddSurveys.click();
			}

			if (isElementPresent(strFirstSurveyTag))
				return true;

			return false;
		}
	}

	public String provideLatLongAtCustomBoundarySelectorWindow(List<String> listBoundary) {
		String actualMsg = "";
		openNewReportPage();

		inputImageMapHeight(listBoundary.get(0));
		inputImageMapWidth(listBoundary.get(1));

		this.btnLatLongSelector.click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		String parentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);
				this.inputCustomNELat.clear();
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.inputCustomNELat.sendKeys(listBoundary.get(2));
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.inputCustomNELong.clear();
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.inputCustomNELong.sendKeys(listBoundary.get(3));
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.inputCustomSWLat.clear();
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.inputCustomSWLat.sendKeys(listBoundary.get(4));
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.inputCustomSWLong.clear();
				testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
				this.inputCustomSWLong.sendKeys(listBoundary.get(5));
				this.btnCustomOK.click();
				actualMsg = this.btnCustomOK.getText();
			}
		}
		// driver.close();
		// driver.switchTo().window(parentWindow);
		return actualMsg;
	}

	public void openCustomerBoundarySelector() {
		this.selectCustomerBoundaryRadioButton();
		this.waitForCustomerBoundarySectionToShow();
		this.clickBoundarySelectorBtn();
	}

	public void openCustomBoundarySelector() {
		this.selectCustomBoundaryRadioButton();
		this.waitForCustomBoundarySectionToShow();
		this.clickLatLongMapSelectorBtn();
	}

	public int getNumberofRecords() {
		List<WebElement> records = this.numberofRecords;
		return records.size();
	}

	public String getAreaErrorText() {
		return this.areaErrorText.getText();

	}

	public void selectPercentCoverageReportArea() {
		SelectCheckbox(checkBoxPCRA);
	}

	public void selectPercentCoverageAssetCheckBox() {
		SelectCheckbox(checkBoxPCA);
	}

	public void selectPercentCoverageForecastCheckBox() {
		SelectCheckbox(checkBoxPCF);
	}

	public void selectGapTableCheckBox() {
		SelectCheckbox(checkBoxGapTb);
	}

	public void selectIsotopicAnalysisCheckBox() {
		SelectCheckbox(checkBoxIsoAna);
	}

	public void selectIndicationsTableCheckBox() {
		SelectCheckbox(checkBoxIndTb);
	}

	public void selectCustomBoundaryRadioButton() {
		this.areaBoundaryRadioButtons.get(CUSTOM_BOUNDARY_RADBUTTON_GROUP_IDX).click();
	}

	public void selectCustomerBoundaryRadioButton() {
		jsClick(this.areaBoundaryRadioButtons.get(CUSTOMER_BOUNDARY_RADBUTTON_GROUP_IDX));
	}

	@Override
	public void selectReportMode(ReportModeFilter mode) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		switch (mode) {
		case Standard:
			js.executeScript("arguments[0].click();", checkBoxStndRptMode);
			break;
		case RapidResponse:
			js.executeScript("arguments[0].click();", checkBoxRRRptMode);
			break;
		case Manual:
			js.executeScript("arguments[0].click();", checkBoxManualRptMode);
			break;
		default:
			break;
		}
		confirmChangeRptMode();
	}

	public void confirmChangeRptMode(){
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		if (this.btnChangeRptMode.isDisplayed()) {
			this.btnChangeRptMode.click();
		}
	}
	public boolean verifySurveysTableViaSurveyMode(boolean changeMode, ReportModeFilter strReportMode,
			SurveyModeFilter surveyModeFilter) throws IOException {
		boolean result = false;

		if (strReportMode != null && changeMode) {
			selectReportMode(strReportMode);

			if (surveyModeFilter != null && changeMode) {
				selectSurveyModeForSurvey(surveyModeFilter);
			}
			this.waitForSurveySearchButtonToLoad();
			this.getBtnSurveySearch().click();
			this.waitForSurveyTabletoLoad();

			WebElement tabledata = driver.findElement(By.id("datatableSurveys"));
			List<WebElement> Rows = tabledata.findElements(By.xpath("//*[@id='datatableSurveys']/tbody/tr"));
			for (int getrowvalue = 1; getrowvalue < Rows.size(); getrowvalue++) {
				List<WebElement> Columns = Rows.get(getrowvalue)
						.findElements(By.xpath("//*[@id='datatableSurveys']/tbody/tr/td[5]"));
				for (int getcolumnvalue = 0; getcolumnvalue < Columns.size(); getcolumnvalue++) {
					String cellValue = driver
							.findElement(By.xpath("//*[@id='datatableSurveys']/tbody/tr[" + getrowvalue + "]/td[5]"))
							.getText();
					if (cellValue.contains(" ")) {
						String str = cellValue.replaceAll("\\s+", "");

						if (surveyModeFilter.name().equalsIgnoreCase(str)) {
							result = true;
							break;
						}
					}
					if (surveyModeFilter.name().equalsIgnoreCase(cellValue)) {
						result = true;
						break;
					}
				}
			}
		}
		return result;
	}

	public void selectEthaneFilter(EthaneFilter ethaneFilter) {
		selectEthaneFilter(ethaneFilter, true);
	}
	
	public void unselectEthaneFilter(EthaneFilter ethaneFilter) {
		selectEthaneFilter(ethaneFilter, false);
	}
	
	private void selectEthaneFilter(EthaneFilter ethaneFilter, boolean select){
		List<WebElement> elements = new ArrayList<WebElement>();
		switch (ethaneFilter) {
		case ExcludeVehicleExhaust:
			elements.add(checkBoxVehicleExhaust);
			break;
		case ExcludeBiogenicMethane:
			elements.add(checkBoxEtheneBiogeniceMethane);
			break;
		case ExcludePossibleNaturalGas:
			elements.add(checkBoxPossibleNaturalGas);
			break;
		case None:
			select = false;
		case All:
			elements.add(checkBoxVehicleExhaust);
			elements.add(checkBoxEtheneBiogeniceMethane);
			elements.add(checkBoxPossibleNaturalGas);
			break;
		default:
			break;
		}
		for(WebElement element:elements){
			if(select)
				SelectCheckbox(element);
			else
				UnselectCheckbox(element);
		}
		
	}

	public void selectViewLayerAssets(Map<String, String> viewLayerMap) {
		for (Entry<String, String> entry : viewLayerMap.entrySet()) {
			String key = entry.getKey(); // Key is Asset/Boundary Id
			String value = entry.getValue(); // Value is Asset/Boundary{Prefix}
												// followed by name of
												// Asset/Boundary
			if (value.startsWith(ReportsCompliance.ASSET_PREFIX)) {
				// Asset key.
				List<WebElement> assetElements = getViewLayerAssetCheckboxes(key);
				if (assetElements.size() > 0) {
					SelectCheckbox(assetElements.get(0));
				}
			}
		}
	}

	public void selectViewLayerBoundaries(Map<String, String> viewLayerMap) {
		for (Entry<String, String> entry : viewLayerMap.entrySet()) {
			String value = entry.getValue(); // Value is Asset/Boundary{Prefix}
												// followed by name of
												// Asset/Boundary
			if (value.startsWith(ReportsCompliance.BOUNDARY_PREFIX)) {
				// Boundary key.
				value = value.replace(ReportsCompliance.BOUNDARY_PREFIX, "");
				List<WebElement> boundaryElements = getViewLayerBoundaryCheckboxes(value);
				if (boundaryElements.size() > 0) {
					SelectCheckbox(boundaryElements.get(0));
				}
			}
		}
	}

	public List<WebElement> getViewLayerAssetCheckboxes(String key) {
		String elementId = String.format("report-asset-layers-%s", key);
		List<WebElement> assetElements = driver.findElements(By.id(elementId));
		return assetElements;
	}

	public List<WebElement> getViewLayerBoundaryCheckboxes(String value) {
		String elementId = String.format("report-boundry-layers-%s", value);
		List<WebElement> boundaryElements = driver.findElements(By.id(elementId));
		return boundaryElements;
	}

	public void selectAnyCustomerBoundary(CustomerBoundaryType type) {
		// TODO open the Boundary selector and click on any customer boundary.
		// Could provide a search by boundary name.
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateInvestigatePDFFile() {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean verifyComplianceReportButton(String rptTitle, String strCreatedBy,
			ComplianceReportButtonType buttonType) throws Exception {
		return checkComplianceReportButtonPresenceAndClick(rptTitle, strCreatedBy, buttonType, false,
				false /* confirmAction */);
	}

	/**
	 * Verifies that the customer boundary name auto-complete list contains the
	 * specified entries.
	 */
	public boolean verifyCustomerBoundaryLatLongSelectorAutoCompleteListContains(String boundaryFilterType, String customerBoundaryName,
			List<String> autocompleteListEntries) {
		openCustomerBoundarySelector();
		latLongSelectionControl.waitForModalDialogOpen()
			.switchMode(ControlMode.MapInteraction)
			.waitForMapImageLoad();
		latLongSelectionControl.selectCustomerBoundaryType(boundaryFilterType);

		// Type customer boundary name and verify the autocomplete list. If not
		// all entries shown, return false.
		if (!latLongSelectionControl.verifyCustomerBoundaryAutoCompleteListContains(
				customerBoundaryName, autocompleteListEntries)) {
			return false;
		}

		// Click Ok to close the lat long selector.
		latLongSelectionControl.switchMode(ControlMode.Default).clickOkButton();

		return true;
	}

	/**
	 * Method to verify the static text
	 * 
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyComplianceReportStaticText(ReportsCompliance reportsCompliance) throws IOException {
		return verifyComplianceReportStaticText(reportsCompliance, testSetup.getDownloadPath());
	}

	/**
	 * Method to verify the static text
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyComplianceReportStaticText(ReportsCompliance reportsCompliance, String actualPath) throws IOException {
		Log.info("Calling verifyComplianceReportStaticText()...");
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportsCompliance.rptTitle);
		String reportId = reportObj.getId();
		String actualReport = actualPath + "CR-" + reportId.substring(0, 6) + ".pdf";
		setReportName("CR-" + reportId);
		setReportName(getReportName());
		String actualReportString = pdfUtility.extractPDFText(actualReport, 0, 1);
		actualReportString = RegexUtility.removeSpecialChars(actualReportString);
		Log.info(String.format("PDF Text Content : %s", actualReportString));
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(STRReportTitle);
		expectedReportString.add(RegexUtility.removeSpecialChars(ComplianceReportSSRS_LISAInvestigationComplete));
		expectedReportString.add(ComplianceReportSSRS_GAPInvestigationComplete);
		expectedReportString.add(ComplianceReportSSRS_CGIInvestigationComplete);
		expectedReportString.add(ComplianceReportSSRS_MapHeightWidth);
		if (isCustomBoundarySpecified(reportsCompliance)) {
			if (!BaseHelper.isNullOrEmpty(reportsCompliance.getNELat()) && !BaseHelper.isNullOrEmpty(reportsCompliance.getNELong())) {
				expectedReportString.add(ComplianceReportSSRS_NELatNELong);
			} 
			if (!BaseHelper.isNullOrEmpty(reportsCompliance.getSWLat()) && !BaseHelper.isNullOrEmpty(reportsCompliance.getSWLong())) {
				expectedReportString.add(ComplianceReportSSRS_SWLatSWLong);
			} 
		} else {
			if (!BaseHelper.isNullOrEmpty(reportsCompliance.getCustomerBoundaryName())) {
				expectedReportString.add(ComplianceReportSSRS_Boundary);
			} 
		}

		Log.info(String.format("Expected Strings in PDF Text Content : %s", LogHelper.strListToString(expectedReportString)));

		HashMap<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value)
				return false;
		}
		return true;

	}

	/**
	 * Method to verify the static text
	 * 
	 * @param reportTitle
	 * @param expectedReportString
	 * @return
	 * @throws IOException
	 */
	public boolean verifyComplianceReportContainsText(String reportTitle, List<String> expectedReportString)
			throws IOException {
		String actualPath = testSetup.getDownloadPath();
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = actualPath + "CR-" + reportId.substring(0, 6) + ".pdf";
		setReportName("CR-" + reportId);
		setReportName(getReportName());
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		HashMap<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value)
				return false;
		}
		return true;

	}

	/**
	 * Method to verify the Show Coverage Table in SSRS
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @param userInput
	 * @return
	 * @throws IOException
	 */
	public boolean verifyShowCoverageTable(String actualPath, String reportTitle) throws IOException {
		Log.info("Verifying Show Coverage Table");
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, "CR-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_ShowCoverage);
		expectedReportString.add(ComplianceReportSSRS_PercentCoverageAssets);
		expectedReportString.add(ComplianceReportSSRS_PercentCoverageForecast);
		expectedReportString.add(ComplianceReportSSRS_PercentCoverageReportArea);

		HashMap<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.info("Show Coverage Table verification failed");
				return false;
			}
		}
		Log.info("Show Coverage Table verification passed");
		return true;
	}

	public boolean verifyCoverageForecastValuesTableWithPreviousResult(String actualPath, String reportTitle)
			throws IOException {
		Log.info("Verifying Coverage Forecast Values Table");

		PDFTableUtility pdfTableUtility = new PDFTableUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = actualPath + "CR-" + reportId.substring(0, 6) + ".pdf";
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		List<String[]> coverageForecast = pdfTableUtility.extractPDFTable(actualReport, PDFTable.COVERAGEFORECAST);
		List<String[]> coverageForecastTo70 = pdfTableUtility.extractPDFTable(actualReport,
				PDFTable.COVERAGEFORECASTTO70);

		boolean result = pdfTableUtility.areTablesEqual(coverageForecast, preCoverageForecast)
				&& pdfTableUtility.areTablesEqual(coverageForecastTo70, preCoverageForecastTo70);
		preCoverageForecast = coverageForecast;
		preCoverageForecastTo70 = coverageForecastTo70;
		return result;
	}

	/**
	 * Method to verify the show Coverage Forecast Table in SSRS
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyCoverageForecastValuesTable(String actualPath, String reportTitle) throws IOException {
		return verifyCoverageForecastValuesTable(actualPath, reportTitle, true);
	}

	public boolean verifyCoverageForecastValuesTable(String actualPath, String reportTitle, boolean withPrediction)
			throws IOException {
		Log.info("Verifying Coverage Forecast Values Table");
		PDFTableUtility pdfTableUtility = new PDFTableUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = actualPath + "CR-" + reportId.substring(0, 6) + ".pdf";
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		List<String[]> coverageForecast = pdfTableUtility.extractPDFTable(actualReport, PDFTable.COVERAGEFORECAST);
		List<String[]> coverageForecastTo70 = pdfTableUtility.extractPDFTable(actualReport,
				PDFTable.COVERAGEFORECASTTO70);
		preCoverageForecast = coverageForecast;
		preCoverageForecastTo70 = coverageForecastTo70;
		if (!withPrediction && !coverageForecastTo70.isEmpty()) {
			return false;
		}
	    return verifyCoverageForecastValuesTableWithDBData(reportId, coverageForecast, coverageForecastTo70, withPrediction);
	}
	private boolean verifyCoverageForecastValuesTableWithDBData(String reportId, List<String[]> coverageForecast, List<String[]> coverageForecastTo70, boolean withPrediction){
		int startIndex = 0;
		StoredProcComplianceGetCoverageForecast coverageForecastObj = new StoredProcComplianceGetCoverageForecast();
		String[] row = null;
		if(!coverageForecast.isEmpty()){
      		row = coverageForecast.get(startIndex);
      		String precentageWithLisa = row[0].replaceFirst(ComplianceReportSSRS_PercentServiceCoverageWithLISAs,"").trim();
      		String precentageWithoutLisa = row[1].replaceFirst(ComplianceReportSSRS_PercentServiceCoverageWithoutLISAs,"").trim();
      		coverageForecastObj.setPercentageWithLisa(precentageWithLisa);		
      		coverageForecastObj.setPercentageWithoutLisa(precentageWithoutLisa);
		}
		if(!coverageForecastTo70.isEmpty()){
			startIndex = 1;
			row = coverageForecastTo70.get(startIndex++);
			String precentageAdditional0 = row[1].replaceFirst(ComplianceReportSSRS_ProbabilitytoObtain70Coverage,"").trim();
			row = coverageForecastTo70.get(startIndex++);
			String precentageAdditional1 = row[1].replaceFirst(ComplianceReportSSRS_ProbabilitytoObtain70Coverage,"").trim();
			row = coverageForecastTo70.get(startIndex);
			String precentageAdditional2 = row[1].replaceFirst(ComplianceReportSSRS_ProbabilitytoObtain70Coverage,"").trim();

			coverageForecastObj.setCoverageProbability0(precentageAdditional0);
			coverageForecastObj.setCoverageProbability1(precentageAdditional1);
			coverageForecastObj.setCoverageProbability2(precentageAdditional2);
	}
		StoredProcComplianceGetCoverageForecast storedForecastObj = StoredProcComplianceGetCoverageForecast
				.getCoverage(reportId);


		if (!storedForecastObj.isCoverageValuesEquals(coverageForecastObj, withPrediction)) {
			Log.info("Coverage Values data verification failed");
			return false;
		}
		Log.info("Coverage Forecast Values data verification passed");
		if (!storedForecastObj.isCoverageValuesFormated(coverageForecastObj, withPrediction)) {
			return false;
		}
		return true;
	}

	/**
	 * Method to verify the Coverage Values Table in SSRS
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */

	public boolean verifyCoverageValuesTable(String actualPath, String reportTitle, Map<String, String> userSelection)
			throws IOException {
		Log.info("Verifying Coverage Values Table");
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, "CR-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		Log.info("Actual PDF text is:");
		Log.info(actualReportString);
		String nextLine = RegexUtility.getNextLineAfterPattern(actualReportString, "Coverage Values");
		List<String> matches = RegexUtility.split(nextLine.trim(), "%");
		StoredProcComplianceGetCoverage coverageReportObj = new StoredProcComplianceGetCoverage();
		String PCA = null; 
		String PCRA = null;
		StoredProcComplianceGetCoverage storedProcObj = StoredProcComplianceGetCoverage.getCoverage(reportId);
		List<String> expectedReportString = new ArrayList<String>();
		if (userSelection.get(KEYPCA).equals("1")) {
			PCA = matches.get(0).replaceAll("[\\D+]", "");
			coverageReportObj.setPercentCoverageAssets(PCA);
			expectedReportString.add(ComplianceReportSSRS_TotalLinearAssetCoverage);
		}
		if (userSelection.get(KEYPCRA).equals("1")) {
			PCRA = matches.get(1).replaceAll("[\\D+]", "");
			coverageReportObj.setPercentCoverageReportArea(PCRA);
			expectedReportString.add(ComplianceReportSSRS_PercentCoverageReportArea);
		}

		Log.info(String.format("Matching expected report strings-[%s], with actual PDF text.", 
				LogHelper.arrayToString(expectedReportString.toArray(new String[expectedReportString.size()])) ));
		HashMap<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.info("Coverage Values data verification failed");
				return false;
			}
		}

		Log.info("Verifying isCoverageValuesEquals()..."); 
		Log.info(String.format("storedProcObj.toString() -> %s", storedProcObj.toString())); 
		Log.info(String.format("coverageReportObj.toString() -> %s", coverageReportObj.toString())); 
		if (!storedProcObj.isCoverageValuesEquals(coverageReportObj)) {
			Log.info("Coverage Values data verification failed");
			return false;
		}
		Log.info("Verifying isCoverageValuesFormated()..."); 
		if (!storedProcObj.isCoverageValuesFormated(coverageReportObj)) {
			return false;
		}
		Log.info("Coverage Values data verification passed");
		return true;
	}

	/**
	 * Method to verify the Layers Table in SSRS
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @param userInput
	 * @return
	 * @throws IOException
	 */
	public boolean verifyLayersTable(String actualPath, String reportTitle, Map<String, String> userInput)
			throws IOException {
		Log.info("Verifying Layers Table");
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, "CR-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_Asset);
		expectedReportString.add(ComplianceReportSSRS_Boundary);
		expectedReportString.add(KEYASSETCASTIRON);
		expectedReportString.add(KEYASSETCOPPER);
		expectedReportString.add(KEYASSETOTHERPLASTIC);
		expectedReportString.add(KEYASSETPEPLASTIC);
		expectedReportString.add(KEYASSETPROTECTEDSTEEL);
		expectedReportString.add(KEYASSETUNPROTECTEDSTEEL);

		HashMap<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.info("Layers Table data verification failed");
				return false;
			}
		}
		Log.info("Layers Table data verification passed");
		return true;

	}

	/**
	 * Method to verify the Views Table in SSRS
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @param userInput
	 * @return
	 * @throws IOException
	 */

	public boolean verifyViewsTable(String actualPath, String reportTitle, List<Map<String, String>> userInput)
			throws IOException {
		Log.info("Verifying Report Views Table");
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, "CR-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_ViewTable);
		expectedReportString.add(ComplianceReportSSRS_ViewName);
		expectedReportString.add(ComplianceReportSSRS_ShowLISAs);
		expectedReportString.add(ComplianceReportSSRS_ShowFOV);
		expectedReportString.add(ComplianceReportSSRS_ShowBreadcrumb);
		expectedReportString.add(ComplianceReportSSRS_ShowIndications);
		expectedReportString.add(ComplianceReportSSRS_ShowIsotopicAnalyses);
		expectedReportString.add(ComplianceReportSSRS_FieldNotes);
		expectedReportString.add(ComplianceReportSSRS_ShowGaps);
		expectedReportString.add(ComplianceReportSSRS_ShowAssets);
		expectedReportString.add(ComplianceReportSSRS_ShowBoundaries);
		expectedReportString.add(ComplianceReportSSRS_BaseMap);
		String viewTable = RegexUtility.getStringInBetween(actualReportString, "Selected Views", "View Table");
		InputStream inputStream = new ByteArrayInputStream(viewTable.getBytes());
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		ArrayList<ReportView> viewListInReport = new ArrayList<ReportView>();
		try {
			while ((line = bufferReader.readLine()) != null) {
				if (line.length() > 3) {
					ReportView viewObj = new ReportView();
					String[] split = line.split("\\s+");
					viewObj.setBaseMapId(BaseMapType.getBaseMapTypeId(split[split.length - 1].trim()));
					viewObj.setViewName(line.replace(split[split.length - 1], "").trim());
					viewListInReport.add(viewObj);
				}
			}

		} finally {
			bufferReader.close();
		}

		List<ReportView> dbObjList = ReportView.getReportView(reportId);
		for (ReportView viewObj : dbObjList) {
			if (!viewObj.isViewNameAndMapInList(viewListInReport)) {
				Log.info("Views Table data verification failed");
				return false;
			}

		}
		Log.info("Views Table data verification passed");
		return true;
	}

	/**
	 * Method to verify the Driving Surveys Table in SSRS
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyDrivingSurveysTable(String actualPath, String reportTitle) throws IOException {
		Log.info("Verifying Driving Surveys Table");
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, "CR-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ReportSSRS_SelectedDrivingSurveys);
		HashMap<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.info("Driving survey table static text verification failed");
				return false;
			}
		}
		String surveyTable;
		if (RegexUtility.getStringInBetween(actualReportString, "Indication Table", "Surveyor Date") != null) {
			surveyTable = (RegexUtility.getStringInBetween(actualReportString, "Indication Table", "Surveyor Date")).trim().replaceAll("//s+", "").replace("#", "").replace("LISA ", "");
			if (surveyTable.contains("Gap Table")) {
				// TODO: DEFECT in parsing. SKIP check for this case.
				Log.warn("SKIPPING Driving survey verification. The case of Driving Survey table and Gap table PDF parsing is currently NOT supported!!!");
				return true;
			}
		} else {
			surveyTable = (RegexUtility.getStringInBetween(actualReportString, "Selected Driving Surveys", " Layers")).trim().replaceAll("//s+", "").replace("#", "").replace("LISA ", "");
		}
		surveyTable = surveyTable.replaceAll(System.lineSeparator(), "");
		String datePattern = RegexUtility.getReportRegexDatePattern(true);
		String drivingSurveysLinePattern = datePattern+" *"+datePattern;
		surveyTable = surveyTable.replaceAll("("+drivingSurveysLinePattern+")", System.lineSeparator()+"$1");
		String[] lines =  surveyTable.split(System.lineSeparator());
		Log.info("Driving survey table contains "+(lines.length-1)+" records");
		ArrayList<StoredProcComplianceAssessmentGetReportDrivingSurveys> listFromStoredProc = StoredProcComplianceAssessmentGetReportDrivingSurveys
					.getReportDrivingSurveys(reportId);
		for(int i=1;i<lines.length;i++){
			boolean validLine = false;
			String expectedLine = "";
			String actualLine = lines[i].replaceAll(" ", "");
			Log.info("Looking for driving survey '"+actualLine+"' in DB");
			for(StoredProcComplianceAssessmentGetReportDrivingSurveys survey:listFromStoredProc){
				expectedLine = survey.toString().replaceAll(" ", "");
				if(actualLine.equalsIgnoreCase(expectedLine)){
					validLine = true;
					break;
				}
			}
			if(!validLine){
				Log.error(String.format("Driving survey in PDF is not found, '%s'",actualLine));
				return false;
			}
		}
		Log.info("Driving survey table verification passed");
		return true;
	}

	/**
	 * Method to verify the Ethane Capture Table in SSRS
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyEthaneCaptureTable(String actualPath, String reportTitle) throws IOException {
		Log.info("Verifying Ethane Capture Table");
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = actualPath + "CR-" + reportId.substring(0, 6) + ".pdf";
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_EthaneAnalysisTable);
		HashMap<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.info("Ethane Capture table static text verification failed");
				return false;
			}
		}
		BufferedReader bufferReader = null;
		try {
			String ethaneCaptureTable = RegexUtility.getStringInBetween(actualReportString,
					"Surveyor Date/Time Result Ethane/Methane Ratio and Uncertainty(%) Field Notes",
					"Ethane Analysis Table");
			InputStream inputStream = new ByteArrayInputStream(ethaneCaptureTable.getBytes());
			bufferReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			ArrayList<String> lineList = new ArrayList<String>();
			StringBuilder lineBuilder = new StringBuilder();
			while ((line = bufferReader.readLine()) != null) {
				lineBuilder.append(line);
				if (line.contains("+/-")) {
					lineList.add(lineBuilder.toString().replaceAll("\\s+", "").replace("+/-", ""));
					lineBuilder = new StringBuilder();
				}
			}
			ArrayList<StoredProcComplianceGetEthaneCapture> ethaneCapturfromSP = StoredProcComplianceGetEthaneCapture
					.getReportEthaneCapture(reportId);
			Iterator<StoredProcComplianceGetEthaneCapture> captureEntryIterator = ethaneCapturfromSP.iterator();
			ArrayList<String> storedProcList = new ArrayList<String>();
			while (captureEntryIterator.hasNext()) {
				StoredProcComplianceGetEthaneCapture entry = captureEntryIterator.next();
				storedProcList.add(entry.toString().replaceAll("\\s+", "").replace("0.0", "0"));
			}
			if (!storedProcList.equals(lineList)) {
				Log.info("Ethane Capture table data verification failed");
				return false;
			}
		} finally {
			bufferReader.close();
		}
		Log.info("Ethane capture table verification passed");
		return true;
	}

	/**
	 * Method to verify Investigation PDF
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyInvestigationResultTable(String actualPath, String reportTitle) throws IOException {
		Log.info("Verifying Investigation Result Table");
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = actualPath + reportId.substring(0, 6) + ".pdf";
		String reportName = reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(LisaInvestigationReportSSRS_Lisa);
		expectedReportString.add(LisaInvestigationReportSSRS_Amplitude);
		expectedReportString.add(Constant_Status);
		expectedReportString.add(LisaInvestigationReportSSRS_Investigator);
		expectedReportString.add(LisaInvestigationReportSSRS_InvestigationReport);

		HashMap<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.info("Investigation Result table static text verification failed");
				return false;
			}
		}
		BufferedReader bufferReader = null;
		try {
			String investigationResultTable = RegexUtility.getStringInBetween(actualReportString,
					"LISA# Amplitude Status Investigation Date/Time Investigator Duration",
					"Investigation Marker ResultsLISA");
			InputStream inputStream = new ByteArrayInputStream(investigationResultTable.getBytes());
			bufferReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			ArrayList<String> lineList = new ArrayList<String>();
			while ((line = bufferReader.readLine()) != null) {
				if (!line.isEmpty()) {
					lineList.add(line.replaceAll("\\s+", "").trim());
				}
			}
			ArrayList<StoredProcLisaInvestigationShowIndication> lisaInvestigationfromSP = StoredProcLisaInvestigationShowIndication
					.getLisaInvestigation(reportId);
			Iterator<StoredProcLisaInvestigationShowIndication> lisaInvestigationIterator = lisaInvestigationfromSP
					.iterator();
			ArrayList<String> storedProcList = new ArrayList<String>();
			while (lisaInvestigationIterator.hasNext()) {
				StoredProcLisaInvestigationShowIndication entry = lisaInvestigationIterator.next();
				storedProcList.add(entry.toString().replaceAll("\\s+", "").trim());
			}
			if (!storedProcList.equals(lineList)) {
				Log.info("Investigation Result table data verification failed");
				return false;
			}
		} finally {
			bufferReader.close();
		}
		Log.info("Investigation Result table verification passed");
		return true;
	}

	public void verifyMetaDataFiles() {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyMetaDataFilesData() {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean verifyReportSurveyMetaDataFile(String actualPath, String reportTitle)
			throws FileNotFoundException, IOException {
		Log.info("Verifying Report survey meta data file");
		CSVUtility csvUtility = new CSVUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String metaDataZipFileName = getReportMetaZipFileName(reportTitle, false /*includeExtension*/);
		String pathToMetaDataUnZip = actualPath;
		String unZipFolder = File.separator + metaDataZipFileName;
		if(!actualPath.endsWith(unZipFolder))
			pathToMetaDataUnZip += unZipFolder;
		
		String pathToCsv = pathToMetaDataUnZip + File.separator + "CR-" + reportId.substring(0, 6) + "-ReportSurvey.csv";
		String reportName = "CR-" + reportId;

		if (actualPath.endsWith("-ReportSurvey.csv")) {
			pathToCsv = actualPath;
		}

		setReportName(reportName);
		List<HashMap<String, String>> csvRows = csvUtility.getAllRows(pathToCsv);
		Iterator<HashMap<String, String>> csvIterator = csvRows.iterator();
		List<StoredProcComplianceAssessmentGetReportDrivingSurveys> reportList = new ArrayList<StoredProcComplianceAssessmentGetReportDrivingSurveys>();
		while (csvIterator.hasNext()) {
			StoredProcComplianceAssessmentGetReportDrivingSurveys reportDrivingObj = new StoredProcComplianceAssessmentGetReportDrivingSurveys();
			HashMap<String, String> csvRow = csvIterator.next();
			if (!csvRow.get("ReportId").trim().toLowerCase().equals(reportId.trim().toLowerCase())) {
				return false;
			}
			if (!csvRow.get("ReportName").trim().equals(getReportName().trim().substring(0, 9))) {
				return false;
			}
			reportDrivingObj.setStartDateTimeWithTZ(csvRow.get("SurveyStartDateTime").trim());
			reportDrivingObj.setEndDateTimeWithTZ(csvRow.get("SurveyEndDateTime").trim());
			reportDrivingObj.setUserName(csvRow.get("UserName").trim());
			reportDrivingObj.setDescription(csvRow.get("Surveyor").trim());
			reportDrivingObj.setAnalyzerId(csvRow.get("Analyzer").trim());
			reportDrivingObj.setTag(csvRow.get("Tag").trim());
			reportDrivingObj.setStabilityClass(csvRow.get("StabilityClass").trim());
			reportList.add(reportDrivingObj);
		}
		ArrayList<StoredProcComplianceAssessmentGetReportDrivingSurveys> listFromStoredProc = StoredProcComplianceAssessmentGetReportDrivingSurveys
				.getReportDrivingSurveys(reportId);
		Iterator<StoredProcComplianceAssessmentGetReportDrivingSurveys> reportIterator = reportList.iterator();
		while (reportIterator.hasNext()) {
			StoredProcComplianceAssessmentGetReportDrivingSurveys obj = reportIterator.next();
			if (!obj.isInList(listFromStoredProc)) {
				Log.info("Report survey meta data file verification failed");
				return false;
			}
		}
		Log.info("Report survey meta data file verification passed");
		return true;
	}

	public boolean verifyIsotopicMetaDataFile(String actualPath, String reportTitle)
			throws FileNotFoundException, IOException {
		Log.info("Verifying Report Isotopic meta data file");
		CSVUtility csvUtility = new CSVUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String pathToMetaDataUnZip = actualPath;
		String metaDataZipFileName = getReportMetaZipFileName(reportTitle, false /*includeExtension*/);
		String unZipFolder = File.separator + metaDataZipFileName;
		if(!actualPath.endsWith(unZipFolder))
			pathToMetaDataUnZip += unZipFolder;
		
		String pathToCsv = pathToMetaDataUnZip + File.separator + "CR-" + reportId.substring(0, 6) + "-ReportIsotopicCapture.csv";
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		List<HashMap<String, String>> csvRows = csvUtility.getAllRows(pathToCsv);
		Iterator<HashMap<String, String>> csvIterator = csvRows.iterator();
		List<StoredProcComplianceGetIsotopics> reportList = new ArrayList<StoredProcComplianceGetIsotopics>();
		while (csvIterator.hasNext()) {
			StoredProcComplianceGetIsotopics reportIsoObj = new StoredProcComplianceGetIsotopics();
			HashMap<String, String> csvRow = csvIterator.next();
			if (!csvRow.get("ReportId").trim().equalsIgnoreCase(reportId.trim())) {
				Log.info("Isotopic meta data file verification failed");
				return false;
			}
			if (!csvRow.get("ReportName").trim().equalsIgnoreCase(getReportName().trim().substring(0, 9))) {
				Log.info("Isotopic meta data file verification failed");
				return false;
			}
			reportIsoObj.setDateTime(csvRow.get("AnalysisDateTime").trim());
			reportIsoObj.setSurveyorUnitName(csvRow.get("Surveyor").trim());
			reportIsoObj.setDisposition(csvRow.get("Result").trim());
			String[] deltaUncertainty = csvRow.get("ValueUncertainty").split(RatioSdevMetaPattern);
			reportIsoObj.setDelta(Float.parseFloat(deltaUncertainty[0].trim()));
			reportIsoObj.setUncertainty(Float.parseFloat(deltaUncertainty[1].trim()));
			reportIsoObj.setText(csvRow.get("FieldNotes").trim());
			reportList.add(reportIsoObj);
		}
		ArrayList<StoredProcComplianceGetIsotopics> storedPodList = StoredProcComplianceGetIsotopics
				.getReportIsotopics(reportId);

		for (StoredProcComplianceGetIsotopics reportListObj : reportList) {
			if (!reportListObj.isInList(storedPodList)) {
				Log.info("Isotopic meta data file verification failed");
				return false;
			}
		}
		Log.info("Isotopic meta data file verification passed");
		return true;
	}

	public boolean verifyEthaneCaptureMetaDataFile(String actualPath, String reportTitle)
			throws FileNotFoundException, IOException {
		return verifyEthaneCaptureMetaDataFile(actualPath, reportTitle, Report.getReport(reportTitle).getId());
	}

	public boolean verifyEthaneCaptureMetaDataFile(String actualPath, String reportTitle, String reportId)
			throws FileNotFoundException, IOException {
		CSVUtility csvUtility = new CSVUtility();
		String metaDataZipFileName = getReportMetaZipFileName(reportTitle, false /*includeExtension*/);
		String pathToMetaDataUnZip = actualPath + File.separator + metaDataZipFileName;
		String pathToCsv = pathToMetaDataUnZip + File.separator + "CR-" + reportId.substring(0, 6) + "-ReportEthaneCapture.csv";
		String reportName = "CR-" + reportId;

		if (actualPath.endsWith("-ReportEthaneCapture.csv")) {
			pathToCsv = actualPath;
		}
		setReportName(reportName);
		List<HashMap<String, String>> csvRows = csvUtility.getAllRows(pathToCsv);
		Iterator<HashMap<String, String>> csvIterator = csvRows.iterator();
		List<StoredProcComplianceGetEthaneCapture> reportList = new ArrayList<StoredProcComplianceGetEthaneCapture>();
		while (csvIterator.hasNext()) {
			StoredProcComplianceGetEthaneCapture ethaneCapture = new StoredProcComplianceGetEthaneCapture();
			HashMap<String, String> csvRow = csvIterator.next();
			if (!csvRow.get("ReportId").trim().equalsIgnoreCase(reportId.trim())) {
				return false;
			}
			if (!csvRow.get("ReportName").trim().equalsIgnoreCase(getReportName().trim().substring(0, 9))) {
				return false;
			}
			ethaneCapture.setDateTime(csvRow.get("AnalysisDateTime").trim());
			ethaneCapture.setSurveyorUnitName(csvRow.get("Surveyor").trim());
			ethaneCapture.setDisposition(csvRow.get("Result").trim());

			String[] valueUncertainty = csvRow.get("ValueUncertainty").trim().split(RatioSdevMetaPattern);
			ethaneCapture.setEthaneRatio(Float.parseFloat(valueUncertainty[0].trim()));
			ethaneCapture.setEthaneRatioSdev(Float.parseFloat(valueUncertainty[1].trim()));
			ethaneCapture.setText(csvRow.get("FieldNotes").trim());

			reportList.add(ethaneCapture);

		}
		ArrayList<StoredProcComplianceGetEthaneCapture> listFromStoredProc = StoredProcComplianceGetEthaneCapture
				.getReportEthaneCapture(reportId);
		Iterator<StoredProcComplianceGetEthaneCapture> reportIterator = reportList.iterator();
		while (reportIterator.hasNext()) {
			StoredProcComplianceGetEthaneCapture testEthaneCapture = reportIterator.next();
			Log.debug("Ethane capture in meta: " + testEthaneCapture);
			if (!testEthaneCapture.isInList(listFromStoredProc)) {
				Log.warn("Ethane capture not found in db? " + testEthaneCapture);
				return false;
			}
		}
		return true;
	}

	public boolean verifyLISASMetaDataFile(String actualPath, String reportTitle)
			throws FileNotFoundException, IOException {
		Log.info("Verifying LISA Meta data file");
		return verifyLISASMetaDataFile(actualPath, reportTitle, Report.getReport(reportTitle).getId());
	}

	public boolean verifyLISASMetaDataFile(String actualPath, String reportTitle, String reportId)
			throws FileNotFoundException, IOException {
		CSVUtility csvUtility = new CSVUtility();
		String pathToMetaDataUnZip = actualPath;
		String metaDataZipFileName = getReportMetaZipFileName(reportTitle, false /*includeExtension*/);
		String unZipFolder = File.separator + metaDataZipFileName;
		if(!actualPath.endsWith(unZipFolder))
			pathToMetaDataUnZip += unZipFolder;
		
		String pathToCsv = pathToMetaDataUnZip + File.separator + "CR-" + reportId.substring(0, 6) + "-ReportLISAS.csv";
		String reportName = "CR-" + reportId;

		if (actualPath.endsWith("-ReportLISAS.csv")) {
			pathToCsv = actualPath;
		}
		setReportName(reportName);
		List<HashMap<String, String>> csvRows = csvUtility.getAllRows(pathToCsv);
		Iterator<HashMap<String, String>> csvIterator = csvRows.iterator();
		List<StoredProcComplianceGetIndications> reportList = new ArrayList<StoredProcComplianceGetIndications>();
		while (csvIterator.hasNext()) {
			StoredProcComplianceGetIndications reportIndObj = new StoredProcComplianceGetIndications();
			HashMap<String, String> csvRow = csvIterator.next();
			if (!csvRow.get("ReportId").trim().equalsIgnoreCase(reportId.trim())) {
				Log.info("LISA Meta data file verification failed");
				return false;
			}
			if (!csvRow.get("ReportName").trim().equalsIgnoreCase(getReportName().trim().substring(0, 9))) {
				Log.info("LISA Meta data file verification failed");
				return false;
			}
			reportIndObj.setPeakNumber(csvRow.get("LisaNumber").trim());
			reportIndObj.setSurveyorUnitName(csvRow.get("Surveyor").trim());
			reportIndObj.setDateTime(csvRow.get("LISADateTime").trim());

			double amp = Math.round(Float.parseFloat((csvRow.get("Amplitude")).trim()) * 100.0) / 100.0;
			reportIndObj.setAmplitude((float) amp);
			double cH4 = Math.round(Float.parseFloat((csvRow.get("Concentration")).trim()) * 100.0) / 100.0;
			reportIndObj.setCh4((float) cH4);
			reportIndObj.setText(csvRow.get("FieldNotes").trim());

			// Covert csv ratio+/sdev to db ratio and sdev - it changed for
			// indication
			String ethaneMethaneRatioUncertainty = csvRow.get("EthaneMethaneRatioUncertainty").trim();
			reportIndObj.setAggregatedEthaneToMethaneRatio(ethaneMethaneRatioUncertainty);
			String aggregatedClassificationconfidence = "N/A";
			try{
			    int aggregatedClassificationconfidenceFloat = (int) (Float.parseFloat(csvRow.get("ConfidenceInDisposition").trim()) * 100);
			    aggregatedClassificationconfidence = aggregatedClassificationconfidenceFloat + "%";
			}catch(Exception e){
				Log.warn(e.toString());
			}
			reportIndObj.setAggregatedClassificationConfidence(aggregatedClassificationconfidence);
			reportList.add(reportIndObj);
		}

		ArrayList<StoredProcComplianceGetIndications> storedPodList = StoredProcComplianceGetIndications
				.getReportIndications(reportId);

		for (StoredProcComplianceGetIndications reportListObj : reportList) {
			if (!reportListObj.isInList(storedPodList)) {
				Log.info("LISA Meta data file verification failed");
				return false;
			}
		}
		Log.info("LISA Meta data file verification passed");
		return true;
	}

	public boolean verifyThumbnailInReportViewer(ReportViewerThumbnailType compliancezipmeta) {
		switch (compliancezipmeta) {
		case ComplianceTablePDF:
			return this.isReportPDFIconDisplayedInViewer();
		case ComplianceZipMeta:
			return this.isMetadataIconDisplayedInViewer();
		case ComplianceZipPDF:
			return this.isReportZipIconDisplayedInViewer();
		case ComplianceZipShape:
			return this.isShapeIconDisplayedInViewer();
		default:
			break;
		}
		return false;
	}

	private String getIsotopicValue(String isotopicUncertaintyValue) {
		List<String> split = RegexUtility.split(isotopicUncertaintyValue, "+/-");
		if (split != null && split.size() == 2) {
			return split.get(0);
		}
		return "";
	}

	private String getUncertaintyPercent(String isotopicUncertaintyValue) {
		List<String> split = RegexUtility.split(isotopicUncertaintyValue, "+/-");
		if (split != null && split.size() == 2) {
			return split.get(1);
		}
		return "";
	}

	public boolean verifyIsotopicValueIsFormattedCorrectly(String isotopicUncertaintyValue) {
		String isotopicValue = getIsotopicValue(isotopicUncertaintyValue);

		// Valid values:
		// -100 <= IsotopicValue <= 0
		// (2 or less decimal places)
		if (isotopicValue.isEmpty()) {
			return false;
		}
		// check values.
		Float isoValue = Float.valueOf(isotopicValue);
		if (!NumberUtility.isInRange(isoValue, -100.0F, 0.0F)) {
			return false;
		}
		// check decimal format.
		Integer decimalCount = NumberUtility.decimalsInNumber(isotopicValue);
		if (decimalCount > 2) {
			Log.info(String.format("Isotopic value:[%s] NOT in format {00[.00]}. " + "Found more than 2 decimal places",
					isotopicValue));
			return false;
		}

		return true;
	}

	public boolean verifyUncertaintyValueIsFormattedCorrectly(String isotopicUncertaintyValue) {
		String uncertaintyValue = getUncertaintyPercent(isotopicUncertaintyValue);

		// Value values:
		// 0.0 <= Uncertainty <= 1.00
		// (2 or less decimal places)
		if (uncertaintyValue.isEmpty()) {
			return false;
		}
		// check values.
		Float uncertainty = Float.valueOf(uncertaintyValue);
		if (!NumberUtility.isInRange(uncertainty, 0.0F, 1.0F)) {
			return false;
		}
		// check decimal format.
		Integer decimalCount = NumberUtility.decimalsInNumber(uncertaintyValue);
		if (decimalCount > 2) {
			Log.info(String.format(
					"Uncertainty value:[%s] NOT in format {00[.00]}. " + "Found more than 2 decimal places",
					uncertaintyValue));
			return false;
		}
		return true;
	}
	/**
	 * Method to verify the Ethane Analysis Table in SSRS
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyEthaneAnalysisTable(String actualPath, String reportTitle) throws IOException {
		Log.info("Calling verifyEthaneAnalysisTable() ...");
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, "CR-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_EthaneAnalysisTable);
		Log.info(String.format("PDF Text Content : %s", actualReportString));
		Log.info(String.format("Expected Strings in PDF Text Content : %s", LogHelper.strListToString(expectedReportString)));
		
		HashMap<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.info("Ethane Analysis table verification failed");
				return false;
			}
		}
		String isoTable = RegexUtility.getStringInBetween(actualReportString, "Surveyor Date/Time Result",ComplianceReportSSRS_EthaneAnalysisTable);
		Log.info(String.format("Extracted Ethane Analysis Table : %s", isoTable));
		if (isoTable != null) {
			InputStream inputStream = new ByteArrayInputStream(isoTable.getBytes());
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			try {
				ArrayList<String> reportEthaneList = new ArrayList<String>();
				while ((line = bufferReader.readLine()) != null) {
					if (!line.trim().startsWith("Ethane/Methane Ratio and Uncertainty")) {
						line = line.replaceAll(" +", " ").trim();
						reportEthaneList.add(line);
					}
				}
				
				Log.info(String.format("ReportEthaneCapture ArrayList Values : %s", LogHelper.strListToString(reportEthaneList)));
				ArrayList<StoredProcComplianceGetEthaneCapture> storedProcEthaneList = StoredProcComplianceGetEthaneCapture
						.getReportEthaneCapture(reportId);
				Iterator<StoredProcComplianceGetEthaneCapture> lineIterator = storedProcEthaneList.iterator();
				ArrayList<String> storedProcConvStringList = new ArrayList<String>();
				while (lineIterator.hasNext()) {
					StoredProcComplianceGetEthaneCapture objStoredProc = lineIterator.next();
					String objAsString = objStoredProc.toString();
					storedProcConvStringList.add(objAsString.trim());
				}

				Log.info(String.format("Checking in ReportEthaneCapture ArrayList, StoredProcConvStringList Values : %s", 
						LogHelper.strListToString(storedProcConvStringList)));
				if (!reportEthaneList.equals(storedProcConvStringList)) {
					Log.info(String.format("EthaneCapture Analysis table verification failed, Expected: '%s', Actual: '%s'",storedProcConvStringList,reportEthaneList));
					return false;
				}
			} finally {
				bufferReader.close();
			}
		}
		Log.info("Ethane Analysis table verification passed");
		return true;

	}
	
	/**
	 * Method to verify the Isotopic Analysis Table in SSRS
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyIsotopicAnalysisTable(String actualPath, String reportTitle) throws IOException {
		Log.info("Calling verifyIsotopicAnalysisTable() ...");
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, "CR-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_IsotopicAnalysisTable);
		Log.info(String.format("PDF Text Content : %s", actualReportString));
		Log.info(String.format("Expected Strings in PDF Text Content : %s", LogHelper.strListToString(expectedReportString)));
		
		HashMap<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.info("Isotopic Analysis table verification failed");
				return false;
			}
		}
		String isoTable = RegexUtility.getStringInBetween(actualReportString, "Surveyor Date/Time Result", " Layers");
		Log.info(String.format("Extracted Isotopic Analysis Table : %s", isoTable));
		if (isoTable != null) {
			InputStream inputStream = new ByteArrayInputStream(isoTable.getBytes());
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			try {
				ArrayList<String> reportIsotopicList = new ArrayList<String>();
				while ((line = bufferReader.readLine()) != null) {
					if (!line.trim().startsWith("Isotopic Value/ Uncertainty")) {
						line = line.replaceAll(" +", " ").trim();
						reportIsotopicList.add(line);
					}
				}
				
				Log.info(String.format("ReportIsotopic ArrayList Values : %s", LogHelper.strListToString(reportIsotopicList)));
				ArrayList<StoredProcComplianceGetIsotopics> storedProcIsotopicList = StoredProcComplianceGetIsotopics
						.getReportIsotopics(reportId);
				Iterator<StoredProcComplianceGetIsotopics> lineIterator = storedProcIsotopicList.iterator();
				ArrayList<String> storedProcConvStringList = new ArrayList<String>();
				while (lineIterator.hasNext()) {
					StoredProcComplianceGetIsotopics objStoredProc = lineIterator.next();
					String objAsString = objStoredProc.toString();
					storedProcConvStringList.add(objAsString.trim());
				}

				Log.info(String.format("Checking in ReportIsotopic ArrayList, StoredProcConvStringList Values : %s", 
						LogHelper.strListToString(storedProcConvStringList)));
				if (!reportIsotopicList.equals(storedProcConvStringList)) {
					Log.info("Isotopic Analysis table verification failed");
					return false;
				}
			} finally {
				bufferReader.close();
			}
		}
		Log.info("Isotopic Analysis table verification passed");
		return true;

	}

	/**
	 * Method to verify SSRS PDF has the expected strings.
	 * 
	 * @param actualPath
	 *            - path of the SSRS PDF
	 * @param reportTitle
	 *            - report title
	 * @param expectedReportString
	 *            - List of strings that are expected to be found in PDF text
	 * @return
	 * @throws IOException
	 */
	public boolean verifySSRSPDFContainsText(String actualPath, String reportTitle, List<String> expectedReportString)
			throws IOException {
		Log.info("Verifying SSRS PDF contains expected strings...");
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, "CR-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		HashMap<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.info("Did NOT find match for all expected Strings!");
				return false;
			}
		}
		Log.info("All expected strings were found in the PDF text.");
		return true;
	}

	/**
	 * Method to verify the Indication Table in SSRS
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyIndicationTable(String actualPath, String reportTitle) throws IOException {
		Log.info("Verifying Indication Table");
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, "CR-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_IndicationTable);
		Log.info(String.format("PDF Text Content : %s", actualReportString));
		Log.info(String.format("Expected Strings in PDF Text Content : %s", LogHelper.strListToString(expectedReportString)));
		
		HashMap<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.info("Indication table static text verification failed");
				return false;
			}
		}
		
		ArrayList<String> indicationTables =  (ArrayList<String>) RegexUtility.getStringsInBetween(actualReportString, "Disposition Confidence in Disposition", "Software Version");
		String indicationTable = "";
		for(String table:indicationTables){
			indicationTable += System.lineSeparator() + table;
		}
		InputStream inputStream = new ByteArrayInputStream(indicationTable.getBytes());
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		ArrayList<String> reportIndicationsList = new ArrayList<String>();
		String extraLines = "";
		try {
			while ((line = bufferReader.readLine()) != null) {
				if (line.trim().matches(RegexUtility.INDICATION_TABLE_LINE_REGEX_PATTERN)){
					ArrayUtility.appendToLastString(reportIndicationsList, extraLines.replaceAll(" ", ""));
					reportIndicationsList.add(line.replaceAll("\\?", "").trim()
							.replace("+/-", "").replace("0.0 ", "").trim().replaceAll(" ", "").replace(">=", ""));
					extraLines = "";
				}else if(!reportIndicationsList.isEmpty() && line.trim().matches(RegexUtility.FIELD_NOTE_LINE_REGEX_PATTERN)){
					extraLines += line.trim();
				}
			}
		} finally {
			bufferReader.close();
		}
		ArrayUtility.appendToLastString(reportIndicationsList, extraLines.replaceAll(" ", ""));
		Log.info(String.format("ReportIndications ArrayList Values : %s", LogHelper.strListToString(reportIndicationsList)));

		ArrayList<StoredProcComplianceGetIndications> storedProcIndicationsList = StoredProcComplianceGetIndications
					.getReportIndications(reportId);
		Iterator<StoredProcComplianceGetIndications> lineIterator = storedProcIndicationsList.iterator();
		ArrayList<String> storedProcConvStringList = new ArrayList<String>();
		while (lineIterator.hasNext()) {
				StoredProcComplianceGetIndications objStoredProc = lineIterator.next();
				String objAsString = objStoredProc.toString();
				storedProcConvStringList.add(objAsString.replace("0.0 ", "0").replaceAll("\\s+", "").trim().replace("+/-", ""));
		}

		Log.info(String.format("Checking in ReportIndications ArrayList, StoredProcConvStringList Values : %s", 
					LogHelper.strListToString(storedProcConvStringList)));
		if (!reportIndicationsList.equals(storedProcConvStringList)) {
				Log.info("Indication data table verification failed");
				return false;
		}

		Log.info("Indication table verification passed");
		return true;
	}

	/**
	 * Method to verify the Gaps Table in SSRS
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyGapsTable(String actualPath, String reportTitle) throws IOException {
		Log.info("Verifying Gaps Table");
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, "CR-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = "CR-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_GapTable);
		HashMap<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.info("Gaps Table verification failed");
				return false;
			}
		}
		InputStream inputStream = new ByteArrayInputStream(actualReportString.getBytes());
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		try {
			ArrayList<String> reportGapsList = new ArrayList<String>();
			while ((line = bufferReader.readLine()) != null) {
				if (line.trim().matches("^\\? \\w+\\d+.*")) {
					reportGapsList.add(line.replaceAll("\\?", "").replaceAll("\\s", "").trim());
				}
			}
			ArrayList<StoredProcComplianceGetGaps> storedProcGapsList = StoredProcComplianceGetGaps
					.getReportGaps(reportId);
			Iterator<StoredProcComplianceGetGaps> lineIterator = storedProcGapsList.iterator();
			ArrayList<String> storedProcConvStringList = new ArrayList<String>();
			while (lineIterator.hasNext()) {
				StoredProcComplianceGetGaps objStoredProc = lineIterator.next();
				String objAsString = objStoredProc.toString();
				storedProcConvStringList.add(objAsString.trim());
			}
			if (!reportGapsList.equals(storedProcConvStringList)) {
				Log.info("Gaps Table verification failed");
				return false;
			}
		} finally {
			bufferReader.close();
		}
		Log.info("Gaps Table verification passed");
		return true;
	}

	/*
	 * EXAMPLES: 1 of 32.3.0.e4394dfSoftware Version:AutomationAdminReport
	 * Author:5/12/2016 4:02 PM PDTDate Printed: 2 of 32.3.0.e4394dfSoftware
	 * Version:AutomationAdminReport Author:5/12/2016 4:02 PM PDTDate Printed: 3
	 * of 32.3.0.e4394dfSoftware Version:AutomationAdminReport Author:5/12/2016
	 * 4:02 PM PDTDate Printed:
	 */

	/**
	 * Method to verify footer text in SSRS PDF
	 * 
	 * @param actualPath
	 *            - Path to PDF
	 * @param reportTitle
	 *            - Report title.
	 * @return
	 * @throws Exception
	 */
	public boolean verifySSRSPDFFooter(String actualPath, String reportTitle, String expectedSoftwareVersion,
			String expectedReportAuthor) throws Exception {
		Log.info("Verifying SSRS PDF footer...");
		String reportPDFFilename = getReportPDFFileName(reportTitle, true /* includeExtension */);
		return verifyPDFFooter(actualPath, reportPDFFilename, expectedSoftwareVersion, expectedReportAuthor);
	}
	
	public boolean verifyPDFFooter(String actualPath, String pdfFilename, String expectedSoftwareVersion,
			String expectedReportAuthor) throws Exception {
		String actualReport = Paths.get(actualPath,pdfFilename).toString();
		PDFUtility pdfUtility = new PDFUtility();
		String actualReportString = pdfUtility.extractPDFText(actualReport);

		String doubleSpace = "  ";
		String datePrinted = Resources.getResource(ResourceKeys.ReportSSRS_DatePrinted);
		String reportAuthor = Resources.getResource(ResourceKeys.ReportSSRS_ReportAuthor);
		String softwareVersion = Resources.getResource(ResourceKeys.ReportSSRS_SoftwareVersion);

		// Parse and get the footer values in the Map List.
		List<HashMap<SSRSPdfFooterColumns, String>> footerValues = new ArrayList<HashMap<SSRSPdfFooterColumns, String>>();
		List<String> allStringsInBetween = new ArrayList<String>();
		// Split into lines to prevent match errors for newline characters.
		List<String> lines = RegexUtility.split(actualReportString, RegexUtility.NEWLINE_SPLIT_REGEX_PATTERN);
		for (String line : lines) {
			List<String> stringsInBetween = RegexUtility.getStringsInBetween(line, doubleSpace, datePrinted);
			allStringsInBetween.addAll(stringsInBetween);
		}
		if (allStringsInBetween != null) {
			for (String lineText : allStringsInBetween) {
				if (!BaseHelper.isNullOrEmpty(lineText)) {
					// Replace 'Software Version:' and 'Report Author:' by seperator-'|'
					// Resulting string will be in this format: -> <software_version>|<user>|<date>
					lineText = lineText.replace(softwareVersion, "|");
					lineText = lineText.replace(reportAuthor, "|");
					List<String> lineParts = RegexUtility.split(lineText,
							RegexUtility.VERTICAL_BAR_SPLIT_REGEX_PATTERN);
					if (lineParts == null || lineParts.size() != 3) {
						throw new Exception(
								"SSRS PDF footer NOT in expected format. CHECK if values in footer have changed.");
					}

					HashMap<SSRSPdfFooterColumns, String> lineMap = new HashMap<SSRSPdfFooterColumns, String>();
					lineMap.put(SSRSPdfFooterColumns.SoftwareVersion, lineParts.get(0).substring(1));
					lineMap.put(SSRSPdfFooterColumns.ReportUser, lineParts.get(1));
					lineMap.put(SSRSPdfFooterColumns.ReportDate, lineParts.get(2));

					footerValues.add(lineMap);
				}
			}
		}

		// Verify each values in List with expected values.
		Integer idx = 1;
		for (HashMap<SSRSPdfFooterColumns, String> map : footerValues) {
			String actualSoftwareVersion = map.get(SSRSPdfFooterColumns.SoftwareVersion);
			Log.info(String.format("Comparing item-%d, actual Software Version value-'%s' with expected value='%s'",
					idx, actualSoftwareVersion, expectedSoftwareVersion));
			if (!actualSoftwareVersion.startsWith(expectedSoftwareVersion)) {
				Log.info("Match=FALSE");
				return false;
			}

			String actualReportAuthor = map.get(SSRSPdfFooterColumns.ReportUser);
			Log.info(String.format("Comparing item-%d, actual Report Author value-'%s' with expected value='%s'", idx,
					actualReportAuthor, expectedReportAuthor));
			if (!actualReportAuthor.equalsIgnoreCase(expectedReportAuthor)) {
				Log.info("Match=FALSE");
				return false;
			}
			
			String actualReportDate = map.get(SSRSPdfFooterColumns.ReportDate);
			Log.info(String.format("Comparing item-%d, actual Date value-'%s' with today's date",
					idx, actualReportDate));
			if (!(new DateUtility()).verifyDateMatchesToday(actualReportDate)) {
				Log.info("Match=FALSE");
				return false;
			}
		}

		return true;
	}

	/**
	 * Method to verify the images in SSRS
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @param expectedImage
	 * @return
	 * @throws IOException
	 */
	public boolean verifySSRSImages(String actualPath, String reportTitle, String testCase)
			throws IOException, InterruptedException {
		Log.info("Verifying Images in SSRS");
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String reportNameWithoutExt = "CR-" + reportId.substring(0, 6);
		String reportName = reportNameWithoutExt + ".pdf";
		String htmlReportName = reportNameWithoutExt + ".html";
		String htmlReportPath = Paths.get(actualPath, htmlReportName).toString();
		File f = new File(htmlReportPath);
		if (!convertPdfToHtml(reportName, htmlReportName)) {
			Log.info("Image verification failed");
			return false;
		}
		Document doc = Jsoup.parse(f, null, "");
		Elements elements = doc.select("img[src]");
		int pageCounter = 1;

		for (Element element : elements) {
			String base64String = element.attr("src").replace("data:image/png;base64,", "");
			if (!(base64String.equals(BASE64_IGNORE))) {
				String pathToActualImage = Paths.get(testSetup.getDownloadPath(), testCase + "Page_" + pageCounter + ".png").toString();
				createImageFromBASE64(base64String, pathToActualImage);
				String pathToBaseImage = Paths
						.get(TestSetup.getRootPath(), "\\selenium-wd\\data\\test-expected-data\\ssrs-images").toString()
						+ "\\" + testCase + "\\" + "Page_" + pageCounter + ".png";
				boolean generateBaseline = TestContext.INSTANCE.getTestSetup().isGenerateBaselineSSRSImages();
				if (!verifyActualImageWithBase(pathToActualImage, pathToBaseImage, generateBaseline)) {
					Files.delete(Paths.get(pathToActualImage));
					Log.info("Image verification failed");
					return false;
				}
				Files.delete(Paths.get(pathToActualImage));
				pageCounter++;
			}

		}
		Log.info("Image verification passed");
		return true;
	}

	public boolean convertPdfToHtml(String reportName, String htmlFile) throws IOException {
		String workingBatFile = null;
		String libFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib";
		String batFile = Paths.get(libFolder, "ConvertPDFToHTML.bat").toString();
		workingBatFile = Paths.get(libFolder, TestSetup.getUUIDString() + "_ConvertPDFToHTML.bat").toString();
		Files.copy(Paths.get(batFile), Paths.get(workingBatFile));
		Hashtable<String, String> parameters = new Hashtable<String, String>();
		parameters.put("%DOWNLOAD_DIR%", testSetup.getDownloadPath());
		parameters.put("%HTML_FILE%", htmlFile);
		parameters.put("%EXE_DIR%", libFolder);
		parameters.put("%PDF_FILE%", reportName);
		// Update the working copy.
		FileUtility.updateFile(workingBatFile, parameters);
		String command = "cd \"" + libFolder + "\" && " + workingBatFile;
		Log.info("Executing replay script. Command -> " + command);
		ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ true);
		// Delete the working copy of the defn file.
		Files.delete(Paths.get(workingBatFile));
		return true;
	}

	public boolean createImageFromBASE64(String base64String, String actualImage) throws IOException {
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] imageByte;
		imageByte = decoder.decodeBuffer(base64String);
		ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
		BufferedImage image = ImageIO.read(bis);
		ImageIO.write(image, "png", new File(actualImage));
		return true;
	}

	// NOTE (2016/05/18): As per test case steps we care more about checking the order of views in SSRS PDF.
	// The step - "verify views are in correct sequence" are refering to sequence of views in SSRS PDF,
	// which should be in the same order that the user has added the views from New Compliance Report page.
	// (DE1973) tracks the issue of order in compliance viewer is NOT the same as the input order by user.
	// Once (DE1973) is fixed we could utilize this method for checking correct order in compliance viewer.
	public boolean verifyViewsInComplianceViewerAreInCorrectSequence(List<String> viewNamesList) {
		List<WebElement> thumbnailImages = driver.findElements(By.xpath("//*[@id='ImageList']/li"));
		if (viewNamesList != null && viewNamesList.size() > 0) {
			Integer numViews = viewNamesList.size();
			Log.info(String.format("Found %d views in Compliance viewer.", thumbnailImages.size()));
			if (thumbnailImages != null && thumbnailImages.size() > 0) {
				Integer numThumbnails = thumbnailImages.size();
				// Loop from end. The thumbnails in the end are the view images.
				for (int i = numThumbnails; i >= 1 && numViews > 0; i--) {
					WebElement viewLabel = driver.findElement(By.xpath("//*[@id='ImageList']/li[" + String.valueOf(i) + "]/div/a/p"));
					Log.info(String.format("View[%d] label is: %s", i, viewLabel.getText()));
					// Check the view labels are in order.
					if (!viewNamesList.get(numViews - 1).equals(viewLabel.getText())) {
						return false;
					}
					numViews--;
				}
			}
		}
		return true;
	}

	public boolean verifyViewsInSSRSPDFAreInCorrectSequence(List<String> expectedViewNamesList, String reportTitle) throws IOException {
		Log.info(String.format("Expected views are: %s", LogHelper.strListToString(expectedViewNamesList)));
		
		List<String> actualViewNamesList = getViewNamesFromSSRSPdfViewTable(reportTitle);
		
		// Returned list has 2 columns. 0-th column is the view name.
		Log.info(String.format("Actual views found in SSRS PDF are: %s", LogHelper.strListToString(actualViewNamesList)));
		// Verify lists contain the same elements in the same order.
		return actualViewNamesList.equals(expectedViewNamesList);
	}

	public List<String> getViewNamesFromSSRSPdfViewTable(String reportTitle) throws IOException {
		String pdfFilename = this.getReportPDFFileName(reportTitle, true /*includeExtension*/);
		String pdfFilePath = Paths.get(TestContext.INSTANCE.getTestSetup().getDownloadPath(), pdfFilename).toString();

		PDFUtility pdfUtility = new PDFUtility();
		String actualReportString = pdfUtility.extractPDFText(pdfFilePath);
		
		List<String> actualViewNamesList = new ArrayList<String>();
		String viewTable = RegexUtility.getStringInBetween(actualReportString, "Selected Views", "View Table");

		InputStream inputStream = new ByteArrayInputStream(viewTable.getBytes());
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		try {
			while ((line = bufferReader.readLine()) != null) {
				if (line.length() > 3) {
					String[] split = line.split("\\s+");
					String viewName = line.replace(split[split.length - 1], "").trim();
					actualViewNamesList.add(viewName);
				}
			}

		} finally {
			bufferReader.close();
		}
		return actualViewNamesList;
	}

	/**
	 * Wrapper to verify the Views Images
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @param expectedImage
	 * @return
	 * @throws IOException
	 */

	public boolean verifyAllViewsImages(String actualPath, String reportTitle, String testCase, int numberOfViews)
			throws IOException {
		for (int numberViews = 1; numberViews <= numberOfViews; numberViews++) {
			if (!verifyViewsImages(actualPath, reportTitle, testCase,
					new NumberUtility().getOrdinalNumberString(numberViews) + " View")) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Method to verify the Views Images
	 * 
	 * @param actualPath
	 * @param reportTitle
	 * @param expectedImage
	 * @return
	 * @throws IOException
	 */
	public boolean verifyViewsImages(String actualPath, String reportTitle, String testCase, String viewName) throws IOException{
		return verifyViewsImages(actualPath, reportTitle,testCase, viewName, true);
	}
	public boolean verifyViewsImages(String actualPath, String reportTitle, String testCase, String viewName, boolean inZipFolder) throws IOException {
		PDFUtility pdfUtility = new PDFUtility();
		String reportName = getReportPDFFileName(reportTitle, false /*includeExtension*/);
		String reportZipName = getReportPDFZipFileName(reportTitle, false /*includeExtension*/);
		String actualReport = null;
		if(inZipFolder)	{	
			actualReport = Paths.get(actualPath, reportZipName + File.separator + reportTitle.replaceAll("\\s+", "") + "_" + viewName + ".pdf").toString();
		}else{
		    actualReport = Paths.get(actualPath, reportName + "_" + viewName + ".pdf").toString();
		}
		setReportName(reportName);
		String imageExtractFolder = pdfUtility.extractPDFImages(actualReport, testCase);
		File folder = new File(imageExtractFolder);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile()){
				BufferedImage image = ImageIO.read(file);
				int width = image.getWidth();
				int height = image.getHeight();
				Rectangle rect = new Rectangle(0, 0, width, height - 40);
				image = cropImage(image, rect);
				String actualViewPath = Paths.get(TestSetup.getSystemTempDirectory(), file.getName().replace(".pdf", "") + ".png").toString();
				File outputfile = new File(actualViewPath);
				ImageIO.write(image, "png", outputfile);
				String baseViewFile = "";
				if(inZipFolder){
					baseViewFile = Paths.get(TestSetup.getRootPath(), "\\selenium-wd\\data\\test-expected-data\\views-images").toString() + File.separator + testCase + File.separator + "View" + new NumberUtility().getOrdinalNumber(file.getName()) + ".png";
				}else{
					baseViewFile = Paths.get(TestSetup.getRootPath(), "\\selenium-wd\\data\\test-expected-data\\views-images").toString() + File.separator + testCase + File.separator + viewName + ".png";
				}				
				
				boolean generateBaseline = TestContext.INSTANCE.getTestSetup().isGenerateBaselineViewImages();
				if (!verifyActualImageWithBase(actualViewPath, baseViewFile, generateBaseline)) {
					Files.delete(Paths.get(actualViewPath));
					return false;
				}
				Files.delete(Paths.get(actualViewPath));
			}
		}
		return true;
	}

	public boolean verifyShapeFilesWithBaselines(String reportTitle, String testCaseID) throws Exception {
		return verifyShapeFilesWithBaselines(reportTitle, testCaseID, 0);
	}

	public boolean verifyShapeFilesWithBaselines(String reportTitle, String testCaseID, int zipIndex) throws Exception {
		Log.info(String.format(
				"Calling verifyShapeFilesWithBaselines() -> reportTitle=[%s], testCaseID=[%s], downloadIndex=[%d]",
				reportTitle, testCaseID, zipIndex));
		String shapeZipFileName = getReportShapeZipFileName(reportTitle, zipIndex, false /* includeExtension */);
		BaseHelper.deCompressZipFile(shapeZipFileName, testSetup.getDownloadPath());
		if (checkAndGenerateBaselineShapeFiles(TestContext.INSTANCE.getTestSetup().getDownloadPath() + shapeZipFileName, testCaseID)) {
			Log.info("Shape Files created as a baseline for '" + testCaseID
					+ "', verification will be done on your next test run");
			return true;
		}

		String actualDataFolderPath = Paths.get(testSetup.getDownloadPath(), shapeZipFileName).toString();
		String rootFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "data";
		String expectedDataFolderPath = rootFolder + File.separator + "test-expected-data" + File.separator
				+ "shape-files" + File.separator + testCaseID;

		// Verify files in both directories are the same.
		if (!FileUtility.compareFilesInDirectories(actualDataFolderPath, expectedDataFolderPath)) {
			return false;
		}

		// Assert all shape files in the folders are the same.
		ShapeFileUtility shapeFileUtility = new ShapeFileUtility();
		shapeFileUtility.assertDirectoryEquals(actualDataFolderPath, expectedDataFolderPath);
		
		return true;
	}

	public void verifyShapeFilesData() {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String removeReportId(String oriName){
		return replaceReportIdWith(oriName, "");
	}
	public String replaceReportIdWith(String oriName, String replaceWith){
		String CR_FilenamePattern = "(CR\\-)[A-Z0-9]{6}([\\.\\-])";
		Path currPath = Paths.get(oriName);
		String currFileName = currPath.getFileName().toString();
		String currFileDirectory = currPath.getParent().toString();
		String newFileName = currFileName.replaceAll(CR_FilenamePattern, "$1"+replaceWith+"$2");
		return Paths.get(currFileDirectory, newFileName).toString();
	}
	/**
	 * 1. Verify that the ZIP file has a PDF for report and 1 PDF for each view
	 * added in the Report. 2. Verify expected content in the PDF report. 3.
	 * Verify there are images present in the view PDFs.
	 */
	public void verifyReportPDFZIPFiles() {
		try {
			// Some checks to implement.
			// Zip folder will have the maps for the specified boundary
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyDownloadTriggeredForThumbnail(ReportViewerThumbnailType compliancezipmeta) {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void waitForCustomBoundarySectionToShow() {
		WebElement dvAreaModeCustom = this.divCustomBoundarySection;
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return !dvAreaModeCustom.getAttribute("style").contains("display:none");
			}
		});
	}

	private void waitForResubmitPopupToShow() {
		WebElement resubmitPopupSection = this.driver.findElement(By.id("resubmitReportModal"));
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return resubmitPopupSection.getAttribute("style").contains("display:block")
						|| resubmitPopupSection.getAttribute("style").contains("display: block");
			}
		});
	}

	private void waitForResubmitPopupToClose() {
		WebElement resubmitPopupSection = this.driver.findElement(By.id("resubmitReportModal"));
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return resubmitPopupSection.getAttribute("style").contains("display:none")
						|| resubmitPopupSection.getAttribute("style").contains("display: none");
			}
		});
	}

	public void waitForConfirmDeletePopupToShow() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				(new WebDriverWait(driver, timeout + 15))
						.until(ExpectedConditions.presenceOfElementLocated(By.id("deleteReportModal")));
				WebElement confirmDeletePopupSection = d.findElement(By.id("deleteReportModal"));
				return confirmDeletePopupSection.getAttribute("style").contains("display:block")
						|| confirmDeletePopupSection.getAttribute("style").contains("display: block");
			}
		});
	}

	public void waitForConfirmDeletePopupToClose() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				(new WebDriverWait(driver, timeout + 15))
						.until(ExpectedConditions.presenceOfElementLocated(By.id("deleteReportModal")));
				WebElement confirmDeletePopupSection = d.findElement(By.id("deleteReportModal"));
				return confirmDeletePopupSection.getAttribute("style").contains("display:none")
						|| confirmDeletePopupSection.getAttribute("style").contains("display: none");
			}
		});
	}

	private void waitForCustomerBoundarySectionToShow() {
		WebElement dvAreaModeCustomer = this.divCustomerBoundarySection;
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return !dvAreaModeCustomer.getAttribute("style").contains("display:none")
						&& !dvAreaModeCustomer.getAttribute("style").contains("display: none");
			}
		});
	}

	@Override
	public void waitForPageLoad() {
		waitForAJAXCallsToComplete();
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getPageSource().contains(STRPageContentText);
			}
		});
	}

	public void waitForReportGenerationtoComplete() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return btnReportViewer.isDisplayed();
			}
		});
	}

	public void waitForViewFileDownload(String reportName, String viewName) {
		waitForFileDownload(reportName + "_" + viewName + ".pdf", testSetup.getDownloadPath());
	}

	public void waitForMetadataZIPFileDownload(String reportName) {
		waitForMetadataZIPFileDownload(reportName, 0);
	}

	public void waitForMetadataZIPFileDownload(String reportName, int zipIndex) {
		reportName = getZipFileNameWithIndex(reportName, zipIndex);
		waitForFileDownload(reportName + "-Meta.zip", testSetup.getDownloadPath());
	}

	public void waitForPDFFileDownload(String reportName) {
		waitForFileDownload(reportName + ".pdf", testSetup.getDownloadPath());
	}

	public void waitForReportZIPFileDownload(String reportName) {
		waitForReportZIPFileDownload(reportName, 0);
	}

	public void waitForReportZIPFileDownload(String reportName, int zipIndex) {
		reportName = getZipFileNameWithIndex(reportName, zipIndex);
		waitForFileDownload(reportName + "-PDF.zip", testSetup.getDownloadPath());
	}

	public void waitForShapeZIPFileDownload(String reportName) {
		waitForShapeZIPFileDownload(reportName, 0);
	}

	public void waitForShapeZIPFileDownload(String reportName, int zipIndex) {
		reportName = getZipFileNameWithIndex(reportName, zipIndex);
		waitForFileDownload(reportName + "-Shape.zip", testSetup.getDownloadPath());
	}

	private String getZipFileNameWithIndex(String name, int zipIndex){
		return zipIndex==0?name:name+" ("+zipIndex+")";
	}
	public void waitForShapeZipFileDownload() {
		try {
			throw new Exception("Not implemented");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void waitForResubmitButton() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return btnResubmitReport.isEnabled();
			}
		});
	}

	public WebElement getBtnResubmitReport() {
		return this.btnResubmitReport;
	}

	public WebElement getAssetErrorText() {
		return this.assetErrorText;
	}

	public WebElement getBoundaryErrorText() {
		return this.boundaryErrorText;
	}

	public WebElement getCheckBoxVehicleExhaust() {
		return checkBoxVehicleExhaust;
	}

	public WebElement getViewsAnalysesColumn() {
		return this.viewsAnalysesColumn;
	}

	public WebElement getTubularAnalysisOption() {
		return this.tubularAnalysisOption;
	}

	public WebElement getCheckBoxEtheneBiogeniceMethane() {
		return checkBoxEtheneBiogeniceMethane;
	}

	public WebElement getFstRptTilNm() {
		return fstRptTilNm;
	}

	@Override
	public WebElement getTable() {
		refreshPageUntilElementFound(DATA_TABLE_XPATH);
		this.waitForPageLoad();
		this.table = driver.findElement(By.xpath(DATA_TABLE_XPATH));
		return super.getTable();
	}

	public List<String> getSelectedSurveyTableValuesForColumn(ColumnHeaders column) {
		By tableContextBy = By.id("datatableSurveys_wrapper");
		WebElement tableContext = driver.findElement(tableContextBy);
		DataTablePage surveyTable = DataTablePage.getDataTablePage(driver, tableContext, 
				this.testSetup, this.strBaseURL, this.strPageURL);
		return surveyTable.getRecords(column.getName(), -1 /*numRecords*/);
	}

	public List<String[]> getSSRSPDFTableValues(PDFTable pdfTable, String reportTitle) throws IOException {
		String pdfFilename = this.getReportPDFFileName(reportTitle, true /*includeExtension*/);
		String pdfFilePath = Paths.get(TestContext.INSTANCE.getTestSetup().getDownloadPath(), pdfFilename).toString();
		PDFTableUtility pdfTableUtility = new PDFTableUtility();
		List<String[]> pdfTableList = pdfTableUtility.extractPDFTable(pdfFilePath, pdfTable);
		Log.info("Checking if Array values returned has header...");
		if (ArrayUtility.listValuesHasHeader(pdfTableList)) {
			Log.info("Found header in returned array values. Skipping header...");
			Log.info(String.format("Extracted tables values from PDF (before skipping header) : %s", LogHelper.listOfArrayToString(pdfTableList)),
					LogCategory.SSRSPdfContent);
			pdfTableList = ArrayUtility.getListValuesSkipHeader(pdfTableList);
		}
		Log.info(String.format("Extracted tables values from PDF : %s", LogHelper.listOfArrayToString(pdfTableList)),
				LogCategory.SSRSPdfContent);
		return pdfTableList;
	}
 
	@Override
	public void fillReportSpecific(Reports reports) {
		ReportsCompliance reportsCompliance = (ReportsCompliance) reports;

		// 1. Report general
		/* Temp solution to enable lisa table on 3200 - Unselect Exclude Possible Natural Gas by default*/
		unselectEthaneFilter(EthaneFilter.ExcludePossibleNaturalGas);
		if (reportsCompliance.getEthaneFilter() != null) {
			selectEthaneFilter(reportsCompliance.getEthaneFilter());
		}

		if (reportsCompliance.reportModeFilter != null) {
			selectReportMode(reportsCompliance.reportModeFilter);
		}

		if (reportsCompliance.getExclusionRadius() != null) {
			inputExclusionRadius(reportsCompliance.getExclusionRadius());
		}

		// 2. Area Selector
		if (isCustomBoundarySpecified(reportsCompliance)) {
			if (useCustomBoundaryLatLongSelector(reportsCompliance)) {
				fillCustomBoundaryUsingLatLongSelector(reportsCompliance);
			} else {
				fillCustomBoundaryTextFields(reportsCompliance.getNELat(), reportsCompliance.getNELong(),
						reportsCompliance.getSWLat(), reportsCompliance.getSWLong());
			}
		} else {
			fillCustomerBoundary(reportsCompliance);
		}

		inputImageMapHeight(reportsCompliance.getImageMapHeight());
		inputImageMapWidth(reportsCompliance.getImageMapWidth());

		// 3. Views
		addViews(reportsCompliance.getCustomer(), reportsCompliance.getViewList());

		// 4. Optional Tabular PDF Content
		List<Map<String, String>> tablesList = reportsCompliance.getTablesList();
		if (tablesList.get(0).get(KEYINDTB).equalsIgnoreCase("1")) {
			selectIndicationsTableCheckBox();
		}
		if (tablesList.get(0).get(KEYISOANA).equalsIgnoreCase("1")) {
			selectIsotopicAnalysisCheckBox();
		}
		if (tablesList.get(0).get(KEYGAPTB) != null) {
			if (tablesList.get(0).get(KEYGAPTB).equalsIgnoreCase("1")) {
				selectGapTableCheckBox();
			}
		}
		if (tablesList.get(0).get(KEYPCA).equalsIgnoreCase("1")) {
			selectPercentCoverageAssetCheckBox();
		}
		if (tablesList.get(0).get(KEYPCRA).equalsIgnoreCase("1")) {
			selectPercentCoverageReportArea();
		}
		if (tablesList.get(0).get(KEYPCF) != null) {
			if (tablesList.get(0).get(KEYPCF).equalsIgnoreCase("1")) {
				selectPercentCoverageForecastCheckBox();
			}
		}

		// 5. Optional View layers
		List<Map<String, String>> viewLayersList = reportsCompliance.getViewLayersList();
		if (viewLayersList != null && viewLayersList.size() > 0) {
			handleOptionalDynamicViewLayersSection(viewLayersList);
		}

	}

	private void fillCustomerBoundary(ReportsCompliance reportsCompliance) {
		fillCustomerBoundary(reportsCompliance.getCustomerBoundaryFilterType().toString(),
				reportsCompliance.getCustomerBoundaryName());
	}

	public void fillCustomerBoundary(String customerBoundaryFilterType, String customerBoundaryName) {
		openCustomerBoundarySelector();
		latLongSelectionControl.waitForModalDialogOpen();
		latLongSelectionControl.switchMode(ControlMode.MapInteraction);
		latLongSelectionControl.waitForMapImageLoad();
		latLongSelectionControl.selectCustomerBoundaryType(customerBoundaryFilterType);
		latLongSelectionControl.waitForElementToBeEnabled(latLongSelectionControl.getCustomerBoundaryTextField());
		latLongSelectionControl.setCustomerBoundaryName(customerBoundaryName);
		latLongSelectionControl.switchMode(ControlMode.Default);
		latLongSelectionControl.clickOkButton();
		latLongSelectionControl.waitForModalDialogToClose();
	}

	private boolean useCustomBoundaryLatLongSelector(ReportsCompliance reportsCompliance) {
		return reportsCompliance.getLatLongXOffset() > 0 && reportsCompliance.getLatLongYOffset() > 0
				&& reportsCompliance.getLatLongRectWidth() > 0 && reportsCompliance.getLatLongRectHeight() > 0;
	}

	private boolean isCustomBoundarySpecified(ReportsCompliance reportsCompliance) {
		boolean useSelector = false;
		if (reportsCompliance != null) {
			boolean textFieldsSpecified = !BaseHelper.isNullOrEmptyOrZero(reportsCompliance.getNELat())
					&& !BaseHelper.isNullOrEmptyOrZero(reportsCompliance.getNELong())
					&& !BaseHelper.isNullOrEmptyOrZero(reportsCompliance.getSWLat())
					&& !BaseHelper.isNullOrEmptyOrZero(reportsCompliance.getSWLong());
			boolean latLongFieldsSpecified = useCustomBoundaryLatLongSelector(reportsCompliance);
			useSelector = textFieldsSpecified || latLongFieldsSpecified;
		}
		return useSelector;
	}

	private void fillCustomBoundaryUsingLatLongSelector(ReportsCompliance reportsCompliance) {
		openCustomBoundarySelector();
		latLongSelectionControl.waitForModalDialogOpen().switchMode(ControlMode.MapInteraction).waitForMapImageLoad()
				.drawSelectorRectangle(ReportsCompliance.CANVAS_X_PATH, reportsCompliance.getLatLongXOffset(),
						reportsCompliance.getLatLongYOffset(), reportsCompliance.getLatLongRectWidth(),
						reportsCompliance.getLatLongRectHeight())
				.switchMode(ControlMode.Default).clickOkButton().waitForModalDialogToClose();
	}

	@Override
	public void addViewDetails(String customer, String boundary) throws Exception {
		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "1");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "1");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);

		addViews(customer, viewList);

		if (boundary != null) {
			this.inputViewBoundaries.click();

			selectViewLayerBoundaries(ReportDataProvider.getAllViewLayerBoundariesForCustomer(customer));
		}
	}

	@Override
	protected void handleExtraAddSurveyInfoParameters(Reports reports) {
		SurveyModeFilter surveyModeFilter = ((ReportsCompliance) reports).surveyModeFilter;
		if (surveyModeFilter != null) {
			selectSurveyModeForSurvey(surveyModeFilter);
		}
	}

	@Override
	protected void handleExtraAddSurveyInfoParameters(SurveyModeFilter surveyModeFilter) {
		if (surveyModeFilter != null) {
			selectSurveyModeForSurvey(surveyModeFilter);
		}
	}

	public void selectSurveyModeForSurvey(SurveyModeFilter surveyModeFilter) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		switch (surveyModeFilter) {
		case All:
			js.executeScript("arguments[0].click();", this.inputSurModeFilterAll);
			break;
		case Standard:
			js.executeScript("arguments[0].click();", this.inputSurModeFilterStd);
			break;
		case Operator:
			js.executeScript("arguments[0].click();", this.inputSurModeFilterOperator);
			break;
		case RapidResponse:
			js.executeScript("arguments[0].click();", this.inputSurModeFilterRapidResponse);
			break;
		case Manual:
			js.executeScript("arguments[0].click();", this.inputSurModeFilterManual);
			break;
		default:
			break;
		}
	}

	@Override
	public void addOtherDetails(String customer, String exclusionRadius, String boundary, String imageMapHeight,
			String imageMapWidth, String NELat, String NELong, String SWLat, String SWLong, String surUnit,
			List<String> tagList, String startDate, String endDate, boolean changeMode, String strReportMode)
					throws Exception {
		if (this.isElementPresent(btnChangeModeXPath)) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", btnChangeMode);
		}

		if (changeMode) {
			if (strReportMode.contentEquals(Resources.getResource(ResourceKeys.Constant_Standard)))
				this.radioBtnStndMode.click();
			else if (strReportMode.contentEquals("Rapid Response"))
				this.radioBtnRRmode.click();
			else if (strReportMode.contentEquals(Resources.getResource(ResourceKeys.Constant_Manual)))
				this.radioBtnManualMode.click();
		}

		inputExclusionRadius(exclusionRadius);
		inputImageMapWidth(imageMapWidth);
		inputImageMapHeight(imageMapHeight);

		this.inputNELat.sendKeys(NELat);
		this.inputNELong.sendKeys(NELong);
		this.inputSWLat.sendKeys(SWLat);
		this.inputSWLong.sendKeys(SWLong);

		this.checkBoxIndTb.click();
		this.checkBoxIsoAna.click();
		this.checkBoxPCA.click();
		this.checkBoxPCRA.click();

		selectViewLayerAssets(ReportDataProvider.getAllViewLayerAssetsForCustomer(customer));
	}

	@Override
	public void addReportSpecificSurveys(String customer, String NELat, String NELong, String SWLat, String SWLong,
			List<Map<String, String>> views) {
		fillCustomBoundaryTextFields(NELat, NELong, SWLat, SWLong);
		addViews(customer, views);
	}

	@Override
	public String getUrlString() {
		return STRURLPath;
	}

	@Override
	public String getNewPageString() {
		return STRNewPageContentText;
	}

	@Override
	public String getStrPageText() {
		return STRPageContentText;
	}

	@Override
	public String getStrCopyPageText() {
		return STRCopyPageTitle;
	}

	@Override
	public WebElement getBtnDeleteConfirm() {
		return btnDeleteConfirm;
	}

	@Override
	public String getBtnDeleteConfirmXpath() {
		return btnDeleteConfirmXpath;
	}

	@Override
	public String getSTRSurveyIncludedMsg() {
		return STRSurveyIncludedMsg;
	}

	@Override
	public String getSurveyMissingMessage() {
		return ComplianceReport_SurveyMissingMessage;
	}
}
