/**
 *
 */
package surveyor.scommon.source;

import static common.source.BaseHelper.matchSinglePattern;
import common.source.DateUtility;
import common.source.Downloader;
import common.source.FileUtility;
import common.source.FunctionUtil;
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
import static surveyor.scommon.source.SurveyorConstants.KEYASSETBOXNUMBER;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARIES;
import static surveyor.scommon.source.SurveyorConstants.KEYBREADCRUMB;
import static surveyor.scommon.source.SurveyorConstants.KEYFOV;
import static surveyor.scommon.source.SurveyorConstants.KEYGAPS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDICATIONS;
import static surveyor.scommon.source.SurveyorConstants.KEYISOTOPICCAPTURE;
import static surveyor.scommon.source.SurveyorConstants.KEYLISA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCA;
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

import surveyor.scommon.entities.BaseReportEntity;
import surveyor.scommon.entities.BaseReportEntity.SSRSPdfFooterColumns;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;
import surveyor.scommon.entities.ReportCommonEntity;
import surveyor.scommon.entities.ReportCommonEntity.CustomerBoundaryFilterType;
import surveyor.scommon.entities.ReportCommonEntity.LISAIndicationTableColumns;
import surveyor.scommon.entities.ReportsSurveyInfo.ColumnHeaders;
import surveyor.scommon.source.DataTablePage.TableColumnType;
import surveyor.scommon.source.LatLongSelectionControl.ControlMode;
import surveyor.scommon.source.ReportsCommonPage.ReportFileType;

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
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
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
import common.source.Constants;
import common.source.Log;
import common.source.LogCategory;
import common.source.LogHelper;
import common.source.PDFTableUtility;
import common.source.PDFTableUtility.PDFTable;
import common.source.TestSetup;
import common.source.TextUtility;
import common.source.Timeout;
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
import surveyor.dataaccess.source.StoredProcComplianceGetEthaneCapture;
import surveyor.dataaccess.source.StoredProcComplianceGetGaps;
import surveyor.dataaccess.source.StoredProcComplianceGetIndications;
import surveyor.dataaccess.source.StoredProcComplianceGetIsotopics;
import surveyor.dataprovider.ReportDataProvider;
import surveyor.parsers.source.SSRSIsotopicAnalysisTableParser;
import surveyor.parsers.source.SSRSViewNamesParser;
import surveyor.parsers.source.SSRSViewNamesParser.ViewNamesParserAlgorithm;
import common.source.PDFUtility;
import common.source.ProcessUtility;
import common.source.RegexUtility;
import common.source.RetryUtil;
import common.source.ShapeFileUtility;
import common.source.SortHelper;
import common.source.TestContext;

public class ReportsCommonPage extends ReportsBasePage {

	protected static final String COL_HEADER_UPLOAD_STATUS = "Upload Status";

	private static final Integer COL_IDX_REPORT_TITLE = 1;
	private static final Integer COL_IDX_REPORT_NAME = 2;
	private static final Integer COL_IDX_CREATED_BY = 4;
	private static final Integer COL_IDX_DATE = 5;
	private static final Integer COL_IDX_ACTION = 6;
	private static final Integer COL_IDX_UPLOAD_STATUS = 7;

	private static final int CUSTOM_BOUNDARY_RADBUTTON_GROUP_IDX = 0;
	private static final int CUSTOMER_BOUNDARY_RADBUTTON_GROUP_IDX = 1;

	private static final String XPATH_DELETE_MODAL_REPORT_NAME = "//*[@id='delete-report-name']";
	private static final String XPATH_DELETE_MODAL_DELETE_WARNING = "//*[@id='deleteReportModal']/div/div/div[2]/p[2]";
	private static final String XPATH_DELETE_MODAL_DELETE_CONFIRMATION = "//*[@id='deleteReportModal']/div/div/div[2]/p[3]";

	private static final String PDF_FILE_DOWNLOAD_URL = "Reports/ViewReportPdf?reportId=%s&ReportType=Compliance";
	private static final String EQ_PDF_FILE_DOWNLOAD_URL = "Reports/ViewReportPdf?reportId=%s&ReportType=EQ";
	private static final String INVESTIGATION_PDF_FILE_DOWNLOAD_URL = "Reports/ViewReportPdf?reportId=%s&ReportType=Investigation";
	private static final String INVESTIGATION_CSV_FILE_DOWNLOAD_URL = "../Reports/DownloadInvestigationData?reportId=%s";
	private static final String PDF_ZIP_FILE_DOWNLOAD_URL = "Reports/DownloadPdf?reportId=%s&ReportType=Compliance";
	private static final String META_ZIP_FILE_DOWNLOAD_URL = "Reports/DownloadReportMeta?reportId=%s&ReportType=Compliance";
	private static final String SHAPE_ZIP_FILE_DOWNLOAD_URL = "Reports/DownloadShapefile?reportId=%s&ReportType=Compliance";
	private static final String VIEW_FILE_DOWNLOAD_URL = "Reports/DownloadReportView?id=%s";

	/*
	 * Base 64 String for the image appearing as <Pdf>. This string is part of
	 * all the reports and should not be considered for comparison
	 */
	private static final String BASE64_IGNORE = "iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAMAAACdt4HsAAAABGdBTUEAALGPC/xhBQAAAwBQTFRFAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQAAAwAACAEBDAIDFgQFHwUIKggLMggPOgsQ/w1x/Q5v/w5w9w9ryhBT+xBsWhAbuhFKUhEXUhEXrhJEuxJKwBJN1xJY8hJn/xJsyhNRoxM+shNF8BNkZxMfXBMZ2xRZlxQ34BRb8BRk3hVarBVA7RZh8RZi4RZa/xZqkRcw9Rdjihgsqxg99BhibBkc5hla9xli9BlgaRoapho55xpZ/hpm8xpfchsd+Rtibxsc9htgexwichwdehwh/hxk9Rxedx0fhh4igB4idx4eeR4fhR8kfR8g/h9h9R9bdSAb9iBb7yFX/yJfpCMwgyQf8iVW/iVd+iVZ9iVWoCYsmycjhice/ihb/Sla+ylX/SpYmisl/StYjisfkiwg/ixX7CxN9yxS/S1W/i1W6y1M9y1Q7S5M6S5K+i5S6C9I/i9U+jBQ7jFK/jFStTIo+DJO9zNM7TRH+DRM/jRQ8jVJ/jZO8DhF9DhH9jlH+TlI/jpL8jpE8zpF8jtD9DxE7zw9/z1I9j1A9D5C+D5D4D8ywD8nwD8n90A/8kA8/0BGxEApv0El7kM5+ENA+UNAykMp7kQ1+0RB+EQ+7EQ2/0VCxUUl6kU0zkUp9UY8/kZByUkj1Eoo6Usw9Uw3300p500t3U8p91Ez11Ij4VIo81Mv+FMz+VM0/FM19FQw/lQ19VYv/lU1/1cz7Fgo/1gy8Fkp9lor4loi/1sw8l0o9l4o/l4t6l8i8mAl+WEn8mEk52Id9WMk9GMk/mMp+GUj72Qg8mQh92Uj/mUn+GYi7WYd+GYj6mYc62cb92ch8Gce7mcd6Wcb6mcb+mgi/mgl/Gsg+2sg+Wog/moj/msi/mwh/m0g/m8f/nEd/3Ic/3Mb/3Qb/3Ua/3Ya/3YZ/3cZ/3cY/3gY/0VC/0NE/0JE/w5wl4XsJQAAAPx0Uk5TAAAAAAAAAAAAAAAAAAAAAAABCQsNDxMWGRwhJioyOkBLT1VTUP77/vK99zRpPkVmsbbB7f5nYabkJy5kX8HeXaG/11H+W89Xn8JqTMuQcplC/op1x2GZhV2I/IV+HFRXgVSN+4N7n0T5m5RC+KN/mBaX9/qp+pv7mZr83EX8/N9+5Nip1fyt5f0RQ3rQr/zo/cq3sXr9xrzB6hf+De13DLi8RBT+wLM+7fTIDfh5Hf6yJMx0/bDPOXI1K85xrs5q8fT47f3q/v7L/uhkrP3lYf2ryZ9eit2o/aOUmKf92ILHfXNfYmZ3a9L9ycvG/f38+vr5+vz8/Pv7+ff36M+a+AAAAAFiS0dEQP7ZXNgAAAj0SURBVFjDnZf/W1J5Fsf9D3guiYYwKqglg1hqplKjpdSojYizbD05iz5kTlqjqYwW2tPkt83M1DIm5UuomZmkW3bVrmupiCY1mCNKrpvYM7VlTyjlZuM2Y+7nXsBK0XX28xM8957X53zO55z3OdcGt/zi7Azbhftfy2b5R+IwFms7z/RbGvI15w8DdkVHsVi+EGa/ZZ1bYMDqAIe+TRabNv02OiqK5b8Z/em7zs3NbQO0GoD0+0wB94Ac/DqQEI0SdobIOV98Pg8AfmtWAxBnZWYK0vYfkh7ixsVhhMDdgZs2zc/Pu9HsVwc4DgiCNG5WQoJ/sLeXF8070IeFEdzpJh+l0pUB+YBwRJDttS3cheJKp9MZDMZmD5r7+vl1HiAI0qDtgRG8lQAlBfnH0/Miqa47kvcnccEK2/1NCIdJ96Ctc/fwjfAGwXDbugKgsLggPy+csiOZmyb4LiEOjQMIhH/YFg4TINxMKxxaCmi8eLFaLJVeyi3N2eu8OTctMzM9O2fjtsjIbX5ewf4gIQK/5gR4uGP27i5LAdKyGons7IVzRaVV1Jjc/PzjP4TucHEirbUjEOyITvQNNH+A2MLj0NYDAM1x6RGk5e9raiQSkSzR+XRRcUFOoguJ8NE2kN2XfoEgsUN46DFoDlZi0DA3Bwiyg9TzpaUnE6kk/OL7xgdE+KBOgKSkrbUCuHJ1bu697KDrGZEoL5yMt5YyPN9glo9viu96GtEKQFEO/34tg1omEVVRidBy5bUdJXi7R4SIxWJzPi1cYwMMV1HO10gqnQnLFygPEDxSaPPuYPlEiD8B3IIrqDevvq9ytl1JPjhhrMBdIe7zaHG5oZn5sQf7YirgJqrV/aWHLPnPCQYis2U9RthjawHIFa0NnZcpZbCMTbRmnszN3mz5EwREJmX7JrQ6nU0eyFvbtX2dyi42/yqcQf40fnIsUsfSBIJIixhId7OCA7aA8nR3sTfF4EHn3d5elaoeONBEXXR/hWdzgZvHMrMjXWwtVczxZ3nwdm76fBvJfAvtajUgKPfxO1VHHRY5f6PkJBCBwrQcSor8WFIQFgl5RFQw/RuWjwveDGjr16jVvT3UBmXPYgdw0jPFOyCgEem5fw06BMqTu/+AGMeJjtrA8aGRFhJpqEejvlvl2qeqJC2J3+nSRHwhWlyZXvTkrLSEhAQuRxoW5RXA9aZ/yESUkMrv7IpffIWXbhSW5jkVlhQUpHuxHdbQt0b6ZcWF4vdHB9MjWNs5cgsAatd0szvu9rguSmFxWUVZSUmM9ERocbarPfoQ4nETNtofiIvzDIpCFUJqzgPFYI+rVt3k9MH2ys0bOFw1qG+R6DDelnmuYAcGF38vyHKxE++M28BBu47PbrE5kR62UB6qzSFQyBtvVZfDdVdwF2tO7jsrugCK93Rxoi1mf+QHtgNOyo3bxgsEis9i+a3BAA8GWlwHNRlYmTdqkQ64DobhHwNuzl0mVctKGKhS5jGBfW5mdjgJAs0nbiP9KyCVUSyaAwAoHvSPXGYMDgjRGCq0qgykE64/WAffrP5bPVl6ToJeZFFJDMCkp+/BUjUpwYvORdXWi2IL8uDR2NjIdaYJAOy7UpnlqlqHW3A5v66CgbsoQb3PLT2MB1mR+BkWiqTvACAuOnivEwFn82TixYuxsWYTQN6u7hI6Qg3KWvtLZ6/xy2E+rrqmCHhfiIZCznMyZVqSAAV4u4Dj4GwmpiYBoYXxeKSWgLvfpRaCl6qV4EbK4MMNcKVt9TVZjCWnIcjcgAV+9K+yXLCY2TwyTk1OvrjD0I4027f2DAgdwSaNPZ0xQGFq+SAQDXPvMe/zPBeyRFokiPwyLdRUODZtozpA6GeMj9xxbB24l4Eo5Di5VtUMdajqHYHOwbK5SrAVz/mDUoqzj+wJSfsiwJzKvJhh3aQxdmjsnqdicGCgu097X3G/t7tDq2wiN5bD1zIOL1aZY8fTXZMFAtPwguYBHvl5Soj0j8VDSEb9vQGN5hbS06tUqapIuBuHDzoTCItS/ER+DiUpU5C964Ootk3cZj58cdsOhycz4pvvXGf23W3q7I4HkoMnLOkR0qKCUDo6h2TtWgAoXvYz/jXZH4O1MQIzltiuro0N/8x6fygsLmYHoVOEIItnATyZNg636V8Mm3eDcK2avzMh6/bSM6V5lNwCjLAVMlfjozevB5mjk7qF0aNR1x27TGsoLC3dx88uwOYQIGsY4PmvM2+mnyO6qVGL9sq1GqF1By6dE+VRThQX54RG7qESTUdAfns7M/PGwHs29WrI8t6DO6lWW4z8vES0l1+St5dCsl9j6Uzjs7OzMzP/fnbKYNQjlhcZ1lt0dYWkinJG9JeFtLIAAEGPIHqjoW3F0fpKRU0e9aJI9Cfo4/beNmwwGPTv3hhSnk4bf16JcOXH3yvY/CIJ0LlP5gO8A5nsHDs8PZryy7TRgCxnLq+ug2V7PS+AWeiCvZUx75RhZjzl+bRxYkhuPf4NmH3Z3PsaSQXfCkBhePuf8ZSneuOrfyBLEYrqchXcxPYEkwwg1Cyc4RPA7Oyvo6cQw2ujbhRRLDLXdimVVVQgUjBGqFy7FND2G7iMtwaE90xvnHr18BekUSHHhoe21vY+Za+yZZ9zR13d5crKs7JrslTiUsATFDD79t2zU8xhvRHIlP7xI61W+3CwX6NRd7WkUmK0SuVBMpHo5PnncCcrR3g+a1rTL5+mMJ/f1r1C1XZkZASITEttPCWmoUel6ja1PwiCrATxKfDgXfNR9lH9zMtxJIAZe7QZrOu1wng2hTGk7UHnkI/b39IgDv8kdCXb4aFnoDKmDaNPEITJZDKY/KEObR84BTqH1JNX+mLBOxCxk7W9ezvz5vVr4yvdxMvHj/X94BT11+8BxN3eJvJqPvvAfaKE6fpa3eQkFohaJyJzGJ1D6kmr+m78J7iMGV28oz0ygRHuUG1R6e3TqIXEVQHQ+9Cz0cYFRAYQzMMXLz6Vgl8VoO0lsMeMoPGpqUmdZfiCbPGr/PRF4i0je6PBaBSS/vjHN35hK+QnoTP+//t6Ny+Cw5qVHv8XF+mWyZITVTkAAAAASUVORK5CYII=";

	public static final String BOUNDARY_SELECTOR_CANVAS_X_PATH = "//*[@id=\"map\"]/div/canvas";
	public static final String STRSurveyIncludedMsg = Resources.getResource(ResourceKeys.ComplianceReport_AlreadyAdded);
	public static final String ComplianceReport_SurveyMissingMessage = Resources.getResource(ResourceKeys.ComplianceReport_SurveyMissingMessage);
	public static final String STRReportTitle = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ComplianceReportSSRS);
	public static final String ComplianceReportSSRS_LISAInvestigationComplete = Resources.getResource(ResourceKeys.ComplianceReportSSRS_LISAInvestigationComplete);
	public static final String ComplianceReportSSRS_GAPInvestigationComplete = Resources.getResource(ResourceKeys.ComplianceReportSSRS_GAPInvestigationComplete);
	public static final String ComplianceReportSSRS_CGIInvestigationComplete = Resources.getResource(ResourceKeys.ComplianceReportSSRS_CGIInvestigationComplete);
	public static final String ComplianceReportSSRS_MapHeightWidth = Resources.getResource(ResourceKeys.ComplianceReportSSRS_MapHeightWidth);
	public static final String ComplianceReportSSRS_NELatNELong = Resources.getResource(ResourceKeys.ComplianceReportSSRS_NELatNELong);
	public static final String ComplianceReportSSRS_SWLatSWLong = Resources.getResource(ResourceKeys.ComplianceReportSSRS_SWLatSWLong);
	public static final String ComplianceReportSSRS_TimeZone = Resources.getResource(ResourceKeys.ComplianceReportSSRS_TimeZone);
	public static final String ComplianceReportSSRS_ShowCoverage = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ShowCoverage);
	public static final String ComplianceReportSSRS_PercentCoverageAssets = Resources.getResource(ResourceKeys.ComplianceReportSSRS_PercentCoverageAssets);
	public static final String ComplianceReportSSRS_PercentCoverageForecast = Resources.getResource(ResourceKeys.ComplianceReportSSRS_PercentCoverageForecast);
	public static final String ComplianceReportSSRS_PercentCoverageReportArea = Resources.getResource(ResourceKeys.ComplianceReportSSRS_PercentCoverageReportArea);
	public static final String ComplianceReportSSRS_PercentServiceCoverageWithLISAs = Resources.getResource(ResourceKeys.ComplianceReportSSRS_PercentServiceCoveragewithLISAs);
	public static final String ComplianceReportSSRS_PercentServiceCoverageWithoutLISAs = Resources.getResource(ResourceKeys.ComplianceReportSSRS_PercentServiceCoverageWithoutLISAs);
	public static final String ComplianceReportSSRS_ProbabilitytoObtain70Coverage = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ProbabilitytoObtain70Coverage);
	public static final String ComplianceReportSSRS_Asset = Resources.getResource(ResourceKeys.ComplianceReportSSRS_Asset);
	public static final String ComplianceReportSSRS_Boundary = Resources.getResource(ResourceKeys.ComplianceReportSSRS_Boundary);
	public static final String ComplianceReportSSRS_ViewTable = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ViewTable);
	public static final String ComplianceReportSSRS_ViewName = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ViewName);
	public static final String ComplianceReportSSRS_ShowLISAs = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ShowLISAs);
	public static final String ComplianceReportSSRS_ShowFOV = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ShowFOV);
	public static final String ComplianceReportSSRS_ShowBreadcrumb = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ShowBreadcrumb);
	public static final String ComplianceReportSSRS_ShowIndications = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ShowIndications);
	public static final String ComplianceReportSSRS_ShowIsotopicAnalyses = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ShowIsotopicAnalyses);
	public static final String ComplianceReportSSRS_FieldNotes = Resources.getResource(ResourceKeys.ComplianceReportSSRS_FieldNotes);
	public static final String ComplianceReportSSRS_ShowGaps = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ShowGaps);
	public static final String ComplianceReportSSRS_ShowHighlightLISAAssets = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ShowHighlightLISAAssets);
	public static final String ComplianceReportSSRS_ShowHighlightGAPAssets = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ShowHighlightGAPAssets);
	public static final String ComplianceReportSSRS_ShowAssets = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ShowAssets);
	public static final String ComplianceReportSSRS_ShowAssetBoxNumber = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ShowAssetBoxNumber);
	public static final String ComplianceReportSSRS_ShowBoundaries = Resources.getResource(ResourceKeys.ComplianceReportSSRS_ShowBoundaries);
	public static final String ComplianceReportSSRS_BaseMap = Resources.getResource(ResourceKeys.ComplianceReportSSRS_BaseMap);
	public static final String ComplianceReportSSRS_TotalLinearAssetCoverage = Resources.getResource(ResourceKeys.ComplianceReportSSRS_TotalLinearAssetCoverage);
	public static final String ReportSSRS_SelectedDrivingSurveys = Resources.getResource(ResourceKeys.ReportSSRS_SelectedDrivingSurveys);
	public static final String ComplianceReportSSRS_IsotopicAnalysisTable = Resources.getResource(ResourceKeys.ComplianceReportSSRS_IsotopicAnalysisTable);
	public static final String ComplianceReportSSRS_IndicationTable = Resources.getResource(ResourceKeys.ComplianceReportSSRS_IndicationTable);
	public static final String ComplianceReportSSRS_GapTable = Resources.getResource(ResourceKeys.ComplianceReportSSRS_GapTable);
	public static final String ComplianceReportSSRS_EthaneAnalysisTable = Resources.getResource(ResourceKeys.ComplianceReportSSRS_EthaneAnalysisTable);
	public static final String ComplianceReport_ChangeModeWarning = Resources.getResource(ResourceKeys.ComplianceReport_ChangeModeWarning);
	public static final String Dialog_ProceedMessage = Resources.getResource(ResourceKeys.Dialog_ProceedMessage);
	
	public static final String LisaInvestigationReportSSRS_Lisa = Resources.getResource(ResourceKeys.LisaInvestigationReportSSRS_Lisa);
	public static final String LisaInvestigationReportSSRS_Amplitude = Resources.getResource(ResourceKeys.LisaInvestigationReportSSRS_Amplitude);
	public static final String Constant_Status = Resources.getResource(ResourceKeys.Constant_Status);
	public static final String LisaInvestigationReportSSRS_Investigator = Resources.getResource(ResourceKeys.LisaInvestigationReportSSRS_Investigator);
	public static final String _HEADERS_Investigator = Resources.getResource(ResourceKeys._HEADERS_Investigator);
	public static final String _HEADERS_Duration = Resources.getResource(ResourceKeys._HEADERS_Duration);
	public static final String LisaInvestigationReportSSRS_InvestigationReport = Resources.getResource(ResourceKeys.LisaInvestigationReportSSRS_InvestigationReport);
	public static final String LisaInvestigations_PageTitle = Resources.getResource(ResourceKeys.LisaInvestigations_PageTitle);

	public static final String ComplianceReportSSRS_LISA_Number = Resources.getResource(ResourceKeys.ComplianceReportSSRS_LISA_Number);
	public static final String ComplianceReportSSRS_Amplitude = Resources.getResource(ResourceKeys.ComplianceReportSSRS_Amplitude);
	public static final String Constant_Analyzer = Resources.getResource(ResourceKeys.Constant_Analyzer);
	public static final String Constant_AnalyzerType = Resources.getResource(ResourceKeys.Constant_AnalyzerType);
	public static final String Constant_Investigator = Resources.getResource(ResourceKeys.Constant_Investigator);
	public static final String Constant_Date = Resources.getResource(ResourceKeys.Constant_Date);
	public static final String ComplianceReport_LicenseMissing = Resources.getResource(ResourceKeys.ComplianceReport_LicenseMissing);

	
	private static final String OK_lICENSE_MISSING_BUTTON_XPATH = "//*[@id='licenseMissingModal']/div/div/div[3]/a";
	private static final String DELETE_POPUP_CONFIRM_BUTTON_XPATH = "//*[@id='deleteReportModal']/div/div/div[3]/a[1]";
	private static final String DELETE_POPUP_CANCEL_BUTTON_XPATH = "//*[@id='deleteReportModal']/div/div/div[3]/a[2]";
	protected static final String deleteSurveyBtnByTagParameter = "//label[contains(@id,'surveytag') and text()='%s']/../../../p/button";
	public static final String RatioSdevMetaPattern = "\\+/\\-";

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

	@FindBy(css = "#ImageList > li .image a[href*='DownloadReportView']")
	protected List<WebElement> pdfViews;

	@FindBy(css = "#ImageList > li .image a[href*='DownloadReportView']")
	protected WebElement firstPdfView;

	@FindBy(name = "rdAreaMode")
	private List<WebElement> areaBoundaryRadioButtons;

	@FindBy(css = "input[name='rdAreaMode'][value='Custom']")
	protected WebElement customBoundaryRadioButton;

	@FindBy(css = "input[name='rdAreaMode'][value='Customer']")
	protected WebElement customerBoundaryRadioButton;

	@FindBy(css = "input[name='rdAreaMode'][value='FreeForm']")
	private WebElement freeFormBoundaryRadioButton;

	@FindBy(id = "btn-select-boundary")
	protected WebElement boundarySelectorBtn;

	@FindBy(id = "dvAreaMode_Customer")
	protected WebElement divCustomerBoundarySection;

	@FindBy(id = "dvAreaMode_Custom")
	protected WebElement divCustomBoundarySection;

	@FindBy(id = "boundary-selected-id")
	private WebElement inputBoundarySelectedId;

	@FindBy(id = "boundary-selected-text")
	private WebElement inputBoundarySelectedText;

	@FindBy(id = "report-survey-start-dt")
	protected WebElement startDatePicker;

	@FindBy(id = "report-survey-end-dt")
	protected WebElement checkBoxAllSurvey;

	@FindBy(id = "report-geo-filter")
	protected WebElement checkBoxGeoFilter;

	@FindBy(how = How.XPATH, using = "//table[@id='datatable']/tbody/tr")
	protected List<WebElement> numberofRecords;

	@FindBy(how = How.XPATH, using = "//table[@id='datatableSurveys']/tbody/tr")
	protected List<WebElement> numberofSurveyRecords;

	@FindBy(how = How.XPATH, using = "//a[starts-with(@href,'/Reports/DeleteReport?reportType=ComplianceReports')]")
	protected WebElement btnDeleteConfirm;
	protected String btnDeleteConfirmXpath = "//a[starts-with(@href,'/Reports/DeleteReport?reportType=ComplianceReports')]";

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

	@FindBy(how = How.XPATH, using = "//*[@id='datatableViews']/tbody/tr/th[7]/div")
	protected WebElement viewsAnalysesColumn;

	@FindBy(how = How.XPATH, using = "//*[@id='datatableViews']/tbody/tr/th[8]/div")
	protected WebElement viewsFieldNoteColumn;
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[3]/div/div[11]/div/div/div/div[2]/div/label")
	protected WebElement tubularAnalysisOption;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[1]")
	protected WebElement fstRptTilNm;

	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a")
	protected WebElement newReportBtn;

	@FindBy(how = How.XPATH, using = "//*[@id='report-show-percent-coverage-report-area']")
	protected WebElement percentCoverReportArea;

	@FindBy(how = How.XPATH, using = "//*[@id='report-asset-layers-d08fc87f-f979-4131-92a9-3d82f37f4bba']")
	protected WebElement rptFirstAsset;

	@FindBy(how = How.XPATH, using = "//*[@id='report-boundry-layers-Small Boundary']")
	protected WebElement rptSmallBoundary;

	@FindBy(how = How.XPATH, using = "//a[@onclick='cancelSurveyModal()']")
	protected WebElement btnChangeModeCancel;

	@FindBy(id = "report-assethighlighting")
	protected WebElement highlightLisaAssetDropdown;

	public static final String REPORT_ASSET_SELECTALL_CHKBX_ID = "report-asset-selectall";
	public static final String REPORT_BOUNDRY_SELECTALL_CHKBX_ID = "report-boundry-selectall";

	@FindBy(id = REPORT_ASSET_SELECTALL_CHKBX_ID)
	protected WebElement checkboxViewLayerAllAssets;

	@FindBy(id = REPORT_BOUNDRY_SELECTALL_CHKBX_ID)
	protected WebElement checkboxViewLayerAllBoundaries;

	@FindBy(id = "boundary-selected-text")
	protected WebElement boundarySelectedText;

	public WebElement getBoundarySelectedText() {
		return this.boundarySelectedText;
	}

	public WebElement getNewReportBtn() {
		return this.newReportBtn;
	}

	public WebElement getPercentCoverReportArea() {
		return this.percentCoverReportArea;
	}

	public WebElement getRptFirstAsset() {
		return this.rptFirstAsset;
	}

	public WebElement getRptSmallBoundary() {
		return this.rptSmallBoundary;
	}

	protected LatLongSelectionControl latLongSelectionControl = null;
	protected BaseMapViewPage mapViewPage = null;
	protected String pagination = "100";

	public enum CustomerBoundaryType {
		District, DistrictPlat
	}

	public enum ReportsButtonType {
		Delete("Delete"), Copy("Copy"), ReportViewer("ReportViewer"), Investigate("Investigate"), InvestigatePDF(
				"InvestigatePDF"), Resubmit("Resubmit"), Cancel(
						"Cancel"), InProgressCopy("InProgressCopy"), ReportErrorLabel("ReportErrorLabel");

		private final String name;

		ReportsButtonType(String nm) {
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
								"ThirdView "), FourthView("FourthView "), FifthView(
										"FifthView "), SixthView("SixthView "), SeventhView("SeventhView");

		private final String name;

		ReportViewerThumbnailType(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	public enum ReportFileType {
		InvestigationPDF("InvestigationPDF"), InvestigationCSV("InvestigationCSV"), PDF("PDF"), EQPDF("EQPDF"), ZIP("ZIP"), MetaDataZIP(
				"MetaDataZIP"), ShapeZIP("ShapeZIP"), View("View"), EQView("EQ-View");

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
	public ReportsCommonPage(WebDriver driver, String strBaseURL, TestSetup testSetup, String strPageURL,
			Supplier<ResourceProvider> resxSupplier) {
		super(driver, strBaseURL, testSetup, strBaseURL + strPageURL);

		// Set the resource provider on super class.
		resxProvider = resxSupplier.get();

		latLongSelectionControl = new LatLongSelectionControl(driver);
		PageFactory.initElements(driver, latLongSelectionControl);
		mapViewPage = new BaseMapViewPage(driver, testSetup,  strBaseURL,strPageURL);
		PageFactory.initElements(driver, mapViewPage);
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

	public void addViews(String customer, List<Map<String, String>> viewList){
		addViews(customer, viewList, false);
	}

	public void addViews(String customer, List<Map<String, String>> viewList, boolean isAnalyticsReport) {
		Log.method("ReportsCommonPage.addViews", customer, LogHelper.mapListToString(viewList));
		int rowNum;
		int colNum;
		String strBaseXPath;

		for (int i = 0; i < viewList.size(); i++) {
			rowNum = i + 1;
			Map<String, String> viewMap = viewList.get(i);
			if (viewMap == null) {
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
				Log.info("Set view name to '" + viewName);
				driver.findElement(By.xpath(strBaseXPath)).clear();
				driver.findElement(By.xpath(strBaseXPath)).sendKeys(viewName);
			}

			if (selectView(viewMap, KEYLISA)) {
				colNum = 3;
				Log.clickElementInfo("LISA", ElementType.CHECKBOX);
				strBaseXPath = getViewsTableInputElementXpath("view-showlisa", rowNum);
				SelectElement(driver.findElement(By.xpath(strBaseXPath + "[@type='checkbox']")));
			}

			if (selectView(viewMap, KEYFOV)) {
				colNum = 4;
				Log.clickElementInfo("FOV", ElementType.CHECKBOX);
				strBaseXPath = getViewsTableInputElementXpath("view-showfov", rowNum);
				SelectElement(driver.findElement(By.xpath(strBaseXPath + "[@type='checkbox']")));
			}

			if (selectView(viewMap, KEYBREADCRUMB)) {
				colNum = 5;
				Log.clickElementInfo("BREADCRUMB", ElementType.CHECKBOX);
				strBaseXPath = getViewsTableInputElementXpath("view-showbreadcrumb", rowNum);
				SelectElement(driver.findElement(By.xpath(strBaseXPath + "[@type='checkbox']")));
			}

			if (selectView(viewMap, KEYINDICATIONS)) {
				colNum = 6;
				Log.clickElementInfo("INDICATIONS", ElementType.CHECKBOX);
				strBaseXPath = getViewsTableInputElementXpath("view-showindication", rowNum);
				SelectElement(driver.findElement(By.xpath(strBaseXPath + "[@type='checkbox']")));
			}

			// Non Analytics
			if(!isAnalyticsReport){
				if (selectView(viewMap, KEYISOTOPICCAPTURE)) {
					colNum = 7;
					Log.clickElementInfo("ISOTOPICCAPTURE", ElementType.CHECKBOX);
					strBaseXPath = getViewsTableInputElementXpath("view-showisotopic", rowNum);
					SelectElement(driver.findElement(By.xpath(strBaseXPath + "[@type='checkbox']")));
				}

				if (selectView(viewMap, KEYANNOTATION)) {
					colNum = 8;
					Log.clickElementInfo("ANNOTATION", ElementType.CHECKBOX);
					strBaseXPath = getViewsTableInputElementXpath("view-showannotation", rowNum);
					SelectElement(driver.findElement(By.xpath(strBaseXPath + "[@type='checkbox']")));
				}
			}

			if (selectView(viewMap, KEYGAPS)) {
				colNum = 9;
				Log.clickElementInfo("GAPS", ElementType.CHECKBOX);
				strBaseXPath = getViewsTableInputElementXpath("view-showgap", rowNum);
				SelectElement(driver.findElement(By.xpath(strBaseXPath + "[@type='checkbox']")));
			}

			if (selectView(viewMap, KEYASSETS)) {
				colNum = 10;
				Log.clickElementInfo("ASSETS", ElementType.CHECKBOX);
				strBaseXPath = getViewsTableInputElementXpath("view-showasset", rowNum);
				SelectElement(driver.findElement(By.xpath(strBaseXPath + "[@type='checkbox']")));
			}

			if (selectView(viewMap, KEYHIGHLIGHTLISAASSETS)) {
				colNum = 11;
				Log.clickElementInfo("Highlight LISA Assets", ElementType.CHECKBOX);
				strBaseXPath = getViewsTableInputElementXpath("view-highlightlisaasset", rowNum);
				SelectElement(driver.findElement(By.xpath(strBaseXPath + "[@type='checkbox']")));

 				// Check Asset Box Number
 				if (selectView(viewMap, KEYASSETBOXNUMBER)) {
 					colNum = 13;
 					Log.clickElementInfo("Asset Box Number", ElementType.CHECKBOX);
 					strBaseXPath = getViewsTableInputElementXpath("view-showassetboxasset", rowNum);
					WebElement boxNumberElement = driver.findElement(By.xpath(strBaseXPath + "[@type='checkbox']"));
					WebElementExtender.waitForElementToBeClickable(Timeout.TEN, driver, boxNumberElement);
					SelectElement(boxNumberElement);
 				}
			}

			if (selectView(viewMap, KEYHIGHLIGHTGAPASSETS)) {
				colNum = 12;
				Log.clickElementInfo("Highlight GAP Assets", ElementType.CHECKBOX);
				strBaseXPath = getViewsTableInputElementXpath("view-highlightgapasset", rowNum);
				SelectElement(driver.findElement(By.xpath(strBaseXPath + "[@type='checkbox']")));
			}

			if (selectView(viewMap, KEYBOUNDARIES)) {
				Log.clickElementInfo("BOUNDARIES", ElementType.CHECKBOX);
				strBaseXPath = getViewsTableInputElementXpath("view-showboundry", rowNum);
				SelectElement(driver.findElement(By.xpath(strBaseXPath + "[@type='checkbox']")));
			}

			if (viewMap.get(KEYBASEMAP) != null && !viewMap.get(KEYBASEMAP).trim().equals("")) {
				strBaseXPath = getViewsTableSelectElementXpath("view-basemap", rowNum);
				WebElement dropdownBaseMap = driver.findElement(By.xpath(strBaseXPath));
				String thisMap = viewMap.get(KEYBASEMAP);
				Log.info(String.format("Select base map - '%s'", thisMap));
				selectDropdownOption(dropdownBaseMap, thisMap);
			}
		}
	}

	private String getViewsTableInputElementXpath(String className, Integer rowNum) {
		String strXPath = String.format("//*[@id='datatableViews']/tbody/tr/td/input[contains(@class,'%s')]", className);
		if (rowNum > 1) {
			strXPath = String.format("//*[@id='datatableViews']/tbody/tr[%d]/td/input[contains(@class,'%s')]",
					rowNum, className);
		}

		return strXPath;
	}

	private String getViewsTableSelectElementXpath(String className, Integer rowNum) {
		String strXPath = String.format("//*[@id='datatableViews']/tbody/tr/td/select[contains(@class,'%s')]", className);
		if (rowNum > 1) {
			strXPath = String.format("//*[@id='datatableViews']/tbody/tr[%d]/td/select[contains(@class,'%s')]",
					rowNum, className);
		}

		return strXPath;
	}

	protected boolean selectView(Map<String, String> viewMap, String option) {
		boolean select = false;
		String value = viewMap.get(option);
		if (value != null && value.equalsIgnoreCase("1")) {
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
		if (btn != null && btn.equalsIgnoreCase("XButton")) {
			modalX.click();
		} else {
			modalClose.click();
		}
	}

	public void clickViewThumbnailImageByIndex(int viewIdx) {
		jsClick(getViewThumbnailImageByIndex(viewIdx));
	}

	public WebElement getViewThumbnailImageByIndex(int viewIdx) {
		if (firstPdfView.isDisplayed()) {
			return pdfViews.get(viewIdx - 1);
		}
		return null;
	}

	public void clickOnShapeZIPInReportViewer() {
		Log.clickElementInfo("Shape ZIP", ElementType.LINK);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", zipShape);
	}

	public void invokePDFFileDownload(String rptTitle) throws Exception {
		Log.method("invokePDFFileDownload", rptTitle);
		invokeFileDownload(rptTitle, ReportFileType.PDF);
	}

	public void invokeEQPDFFileDownload(String rptTitle) throws Exception {
		Log.method("invokeEQPDFFileDownload", rptTitle);
		invokeFileDownload(rptTitle, ReportFileType.EQPDF);
	}

	public void invokePDFZipFileDownload(String rptTitle) throws Exception {
		Log.method("invokePDFZipFileDownload", rptTitle);
		invokeFileDownload(rptTitle, ReportFileType.ZIP);
	}

	public void invokeMetaZipFileDownload(String rptTitle) throws Exception {
		Log.method("invokeMetaZipFileDownload", rptTitle);
		invokeFileDownload(rptTitle, ReportFileType.MetaDataZIP);
	}

	public void invokeShapeZipFileDownload(String rptTitle) throws Exception {
		Log.method("invokeShapeZipFileDownload", rptTitle);
		invokeFileDownload(rptTitle, ReportFileType.ShapeZIP);
	}

	public void invokeViewFileDownload(String reportName, String viewName, Integer viewIdx) throws Exception {
		Log.method("invokeViewFileDownload", reportName, viewName, viewIdx);
		if (firstPdfView.isDisplayed()) {
			String downloadFileRelativeUrl = pdfViews.get(viewIdx - 1).getAttribute("href");
			downloadFileRelativeUrl = downloadFileRelativeUrl.replace(TestContext.INSTANCE.getBaseUrl(),"");
			String outputFileName = reportName + "_" + viewName + ".pdf";
			String outputFileFullPath = Paths.get(testSetup.getDownloadPath(), outputFileName).toString();
			Downloader.downloadFile(downloadFileRelativeUrl, outputFileFullPath);
		}
	}

	protected void invokeFileDownload(String rptTitle, ReportFileType fileType) throws Exception {
		Log.method("invokePDFFileDownload", rptTitle, fileType);
		String reportId = Report.getReport(rptTitle).getId();
		String downloadFileRelativeUrl = null;
		String outputFileName = null;

		if (fileType == ReportFileType.PDF) {
			downloadFileRelativeUrl = String.format(PDF_FILE_DOWNLOAD_URL, reportId);
			outputFileName = getReportPDFFileName(rptTitle, true /* includeExtension */);
		} else if (fileType == ReportFileType.EQPDF) {
			downloadFileRelativeUrl = String.format(EQ_PDF_FILE_DOWNLOAD_URL, reportId);
			outputFileName = getReportPDFFileName(rptTitle, true /* includeExtension */);
		}else if (fileType == ReportFileType.InvestigationPDF) {
			downloadFileRelativeUrl = String.format(INVESTIGATION_PDF_FILE_DOWNLOAD_URL, reportId);
			outputFileName = getInvestigationPDFFileName(rptTitle, true /* includeExtension */);
		} else if (fileType == ReportFileType.InvestigationCSV) {
			downloadFileRelativeUrl = String.format(INVESTIGATION_CSV_FILE_DOWNLOAD_URL, reportId);
			outputFileName = getInvestigationCSVFileName(rptTitle, true /* includeExtension */);
		} else if (fileType == ReportFileType.ZIP) {
			downloadFileRelativeUrl = String.format(PDF_ZIP_FILE_DOWNLOAD_URL, reportId);
			outputFileName = getReportPDFZipFileName(rptTitle, true /* includeExtension */);
		} else if (fileType == ReportFileType.MetaDataZIP) {
			downloadFileRelativeUrl = String.format(META_ZIP_FILE_DOWNLOAD_URL, reportId);
			outputFileName = getReportMetaZipFileName(rptTitle, true /* includeExtension */);
		} else if (fileType == ReportFileType.ShapeZIP) {
			downloadFileRelativeUrl = String.format(SHAPE_ZIP_FILE_DOWNLOAD_URL, reportId);
			outputFileName = getReportShapeZipFileName(rptTitle, true /* includeExtension */);
		} else {
			throw new Exception(
					String.format("FileType-'%s' not handled by invokeFileDownload() method.", fileType.toString()));
		}

		String outputFileFullPath = Paths.get(testSetup.getDownloadPath(), outputFileName).toString();
		Downloader.downloadFile(downloadFileRelativeUrl, outputFileFullPath);
	}

	public void clickLatLongMapSelectorBtn() {
		Log.clickElementInfo("Lat/Long Map Selector");
		this.latLongMapSelectorBtn.click();
	}

	public void clickBoundarySelectorBtn() {
		Log.clickElementInfo("Boundary Selector");
		for(int i=0;i<Constants.DEFAULT_MAX_RETRIES; i++){
			try{
				this.boundarySelectorBtn.click();
				return;
			}catch(Exception e){
				Log.warn("Try "+(i+1) + ":Failed to click boundary selector button: "+e);
			}
		}
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
	
	public void clickOnOKMissingLicensePopup() {
		WebElement OKMissingLicense = this.driver.findElement(By.xpath(OK_lICENSE_MISSING_BUTTON_XPATH));
		Log.clickElementInfo("Confirm of deletion");
		OKMissingLicense.click();
	}
	public boolean clickOnButtonInReportPage(String rptTitle, String strCreatedBy,
			ReportsButtonType buttonType) throws Exception {
		return checkButtonOnReportsPageAndClick(rptTitle, strCreatedBy, buttonType, true,
				true /* By default confirm the action */);
	}

	public boolean clickComplianceReportButton(String rptTitle, String strCreatedBy,
			ReportsButtonType buttonType, boolean confirmAction) throws Exception {
		return checkButtonOnReportsPageAndClick(rptTitle, strCreatedBy, buttonType, true, confirmAction);
	}

	@Override
	public boolean handleFileDownloads(String rptTitle, String testCaseID) throws Exception {
		Log.method("ReportsCommonPage.handleFileDownloads", rptTitle, testCaseID);
		String reportName = getReportPrefix() + "-" + getReportName(rptTitle);
		invokePDFFileDownload(rptTitle);
		waitForPDFFileDownload(reportName);
		Log.info("SSRS zip file got downloaded");
		invokePDFZipFileDownload(rptTitle);
		waitForReportZIPFileDownload(reportName);
		checkAndGenerateBaselineSSRSImage(reportName, testCaseID);
		String zipFileName = getReportPDFZipFileName(rptTitle, false /* includeextension */);
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
			invokeMetaZipFileDownload(rptTitle);
			waitForMetadataZIPFileDownload(reportName);
			zipFileName = getReportMetaZipFileName(rptTitle, false /* includeextension */);
			Log.info("Meta data zip file got downloaded");
			try {
				BaseHelper.deCompressZipFile(zipFileName, testSetup.getDownloadPath());
			} catch (Exception e) {
				Log.error(e.toString());
				return false;
			}
		}
		if (zipShape.isDisplayed()) {
			invokeShapeZipFileDownload(rptTitle);
			waitForShapeZIPFileDownload(reportName);
			Log.info("Shape files zip file got downloaded");
			zipFileName = getReportShapeZipFileName(rptTitle, false /* includeextension */);
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
				} catch (Exception e) {
					Log.error(e.toString());
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public String getReportName(String rptTitle) {
		Report objReport = Report.getReport(rptTitle);
		String reportId = objReport.getId();
		reportId = reportId.substring(0, 6);
		Log.info("The reportID of " + rptTitle + "' is '" + reportId + "'");
		return reportId;
	}

	public String getReportPDFZipFileName(String rptTitle, boolean includeExtension) {
		return getReportPDFZipFileName(rptTitle, 0, includeExtension);
	}

	public String getReportMetaZipFileName(String rptTitle, boolean includeExtension) {
		return getReportMetaZipFileName(rptTitle, 0, includeExtension);
	}

	public String getReportShapeZipFileName(String rptTitle, boolean includeExtension) {
		return getReportShapeZipFileName(rptTitle, 0, includeExtension);
	}

	public String getReportPDFZipFileName(String rptTitle, int zipIndex, boolean includeExtension) {
		String reportName = getReportPrefix() + "-" + getReportName(rptTitle) + "-PDF";
		reportName = getZipFileNameWithIndex(reportName, zipIndex);
		if (includeExtension) {
			reportName += ".zip";
		}
		return reportName;
	}

	public String getReportMetaZipFileName(String rptTitle, int zipIndex, boolean includeExtension) {
		String reportName = getReportPrefix() + "-" + getReportName(rptTitle) + "-Meta";
		reportName = getZipFileNameWithIndex(reportName, zipIndex);
		if (includeExtension) {
			reportName += ".zip";
		}
		return reportName;
	}

	public String getReportShapeZipFileName(String rptTitle, int zipIndex, boolean includeExtension) {
		String reportName = getReportPrefix() + "-" + getReportName(rptTitle) + "-Shape";
		reportName = getZipFileNameWithIndex(reportName, zipIndex);
		if (includeExtension) {
			reportName += ".zip";
		}
		return reportName;
	}

	@Override
	public String getReportPDFFileName(String rptTitle, boolean includeExtension) {
		String reportName = getReportPrefix() + "-" + getReportName(rptTitle);
		if (includeExtension) {
			reportName += ".pdf";
		}
		return reportName;
	}

	public String getInvestigationPDFFileName(String rptTitle, boolean includeExtension) {
		String reportName = getReportPrefix() + "-" + getReportName(rptTitle);
		if (includeExtension) {
			reportName += "-Investigation.pdf";
		}
		return reportName;
	}

	public String getInvestigationCSVFileName(String rptTitle, boolean includeExtension) {
		String reportName = getReportPrefix() + "-" + getReportName(rptTitle);
		if (includeExtension) {
			reportName += "-ReportInvestigations.csv";
		}
		return reportName;
	}

	public boolean checkAndGenerateBaselineShapeFiles(String unzipFolder, String testCaseID) throws Exception {
		Log.method("ReportsCommonPage.checkAndGenerateBaselineShapeFiles", unzipFolder, testCaseID);
		boolean isGenerateBaselineShapeFiles = TestContext.INSTANCE.getTestSetup().isGenerateBaselineShapeFiles();
		Path unzipDirectory = Paths.get(unzipFolder);
		List<String> filesInDirectory = FileUtility.getFilesInDirectory(unzipDirectory, "*.shp,*.dbf,*.prj,*.shx");
		for (String filePath : filesInDirectory) {
			String newFilename = replaceReportIdWith(filePath, testCaseID);
			new File(filePath).renameTo(new File(newFilename));
			if (isGenerateBaselineShapeFiles) {
				generateBaselineShapeFile(testCaseID, newFilename);
			}
		}
		return isGenerateBaselineShapeFiles;
	}

	public boolean checkAndGenerateBaselineSSRSImage(String reportName, String testCaseID) throws Exception {
		Log.method("ReportsCommonPage.checkAndGenerateBaselineSSRSImage", reportName, testCaseID);
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
					String pathToActualImage = Paths.get(testSetup.getDownloadPath(), "Page_" + pageCounter + ".png")
							.toString();
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
		Log.method("ReportsCommonPage.checkAndGenerateBaselineViewImages", unzipFolder, testCaseID);
		PDFUtility pdfUtility = new PDFUtility();
		boolean isGenerateBaselineViewImages = TestContext.INSTANCE.getTestSetup().isGenerateBaselineViewImages();
		if (isGenerateBaselineViewImages) {
			File downLoadedFolder = new File(unzipFolder);
			File[] listOfViews;
			if (downLoadedFolder.isFile()) {
				listOfViews = new File[1];
				listOfViews[0] = downLoadedFolder;
			} else {
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
								String outputfile = Paths.get(TestSetup.getSystemTempDirectory(),
										testCaseID + "_View" + counter + ".png").toString();
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
		String expectedDataFolderPath = Paths
				.get(rootFolder, "perf-metric" + File.separator + "report-job-metrics" + File.separator + testCaseID)
				.toString();
		// Create the directory for test case if it does not exist.
		FileUtility.createDirectoryIfNotExists(expectedDataFolderPath);
		Path expectedFilePath = Paths.get(expectedDataFolderPath, String.format("%s.csv", testCaseID));
		String reportMetricString = String.format("%s,%s,%s,%d", reportId, startTime, endTime, processingTimeInMs);
		FileUtility.createOrWriteToExistingTextFile(expectedFilePath, reportMetricString);
	}

	protected void generateBaselineSSRSImage(String testCaseID, String imageFileFullPath) throws IOException {
		String rootFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "data";
		String expectedDataFolderPath = Paths
				.get(rootFolder, "test-expected-data" + File.separator + "ssrs-images" + File.separator + testCaseID)
				.toString();
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
		String expectedDataFolderPath = Paths
				.get(rootFolder, "test-expected-data" + File.separator + "shape-files" + File.separator + testCaseID)
				.toString();
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
		pdfImgXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[" + getColumnIndex(COL_HEADER_ACTION) + "]/a[3]/img";
		pdfImg = getTable().findElement(By.xpath(pdfImgXPath));
		String srcPdfImg = pdfImg.getAttribute("src");
		zipImgXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[" + getColumnIndex(COL_HEADER_ACTION) + "]/a[4]/img";
		zipImg = getTable().findElement(By.xpath(zipImgXPath));
		String srcZipImg = zipImg.getAttribute("src");
		if (srcPdfImg.contains("pdf") && srcZipImg.contains("zip")) {
			Log.clickElementInfo("PDF", ElementType.ICON);
			pdfImg.click();
			waitForPDFFileDownload(getReportName());
			Log.clickElementInfo("ZIP", ElementType.ICON);
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

	public boolean checkButtonOnReportsPageAndClick(String rptTitle, String strCreatedBy,
			ReportsButtonType buttonType, boolean clickButton, boolean confirmAction) throws Exception {
		Log.method("ReportsCommonPage.checkButtonOnReportsPageAndClick", rptTitle, strCreatedBy,
				buttonType.name(), clickButton, confirmAction);

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
			buttonXPath = "td[" + getColumnIndex(COL_HEADER_ACTION) + "]/a[1]";
			break;
		case Copy:
			buttonXPath = "td[" + getColumnIndex(COL_HEADER_ACTION) + "]/a[@title='Copy']";
			removeDBCache = true;
			break;
		case ReportViewer:
			buttonXPath = "td[" + getColumnIndex(COL_HEADER_ACTION) + "]/a[3]";
			break;
		case Investigate:
			buttonXPath = "td[" + getColumnIndex(COL_HEADER_ACTION) + "]/a[4]";
			break;
		case Resubmit:
			buttonXPath = "td[" + getColumnIndex(COL_HEADER_ACTION) + "]/a[@title='Resubmit']";
			removeDBCache = true;
			break;
		case InProgressCopy: // NOTE: When report is in-progress, Copy is the
								// 1st button.
			buttonXPath = "td[" + getColumnIndex(COL_HEADER_ACTION) + "]/a[@title='Copy']";
			break;
		case Cancel: // NOTE: When cancel button is visible it is the 2nd
						// button.
			buttonXPath = "td[" + getColumnIndex(COL_HEADER_ACTION) + "]/a[@title='Cancel Report']";
			break;
		case ReportErrorLabel: // 'Error Processing' label on report
			// cancelled or report error.
			buttonXPath = "td[" + getColumnIndex(COL_HEADER_ACTION) + "]/span";
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

		final int MAX_PAGES_TO_MOVE_AHEAD = 3;
		int pageCounter = 0;
		for (int rowNum = 1, numRetry = 0; rowNum <= loopCount && pageCounter < MAX_PAGES_TO_MOVE_AHEAD; rowNum++) {
			reportTitleXPath = "tr[" + rowNum + "]/td[" + getColumnIndex(COL_HEADER_REPORT_TITLE) + "]";
			createdByXPath = "tr[" + rowNum + "]/td[" + getColumnIndex(COL_HEADER_CREATED_BY)  + "]";

			try {
				rptTitleCellText = getTable().findElement(By.xpath(reportTitleXPath)).getText().trim();
				createdByCellText = getTable().findElement(By.xpath(createdByXPath)).getText().trim();
			} catch (Exception e) {
				if (numRetry++ < 10) {
					Log.error("Failed to get text of report title/createdBy cells on row '" + rowNum
							+ "' and will try again: " + e);
					rowNum--;
					continue;
				} else {
					Log.error("Failed to get text of report title/createdBy cells on row '" + rowNum + "': " + e);
					return false;
				}
			}
			Log.info(String.format("Found rptTitleCell.getText()=[%s], createdByCell.getText()=[%s]", rptTitleCellText,
					createdByCellText));
			if (rptTitleCellText.equalsIgnoreCase(rptTitle) && createdByCellText.equalsIgnoreCase(strCreatedBy)) {
				try {
					buttonXPath = "tr[" + rowNum + "]/" + buttonXPath;
					buttonImg = getTable().findElement(By.xpath(buttonXPath));
					if (buttonImg.isDisplayed()) {
						if (clickButton) {
							if (buttonType != ReportsButtonType.ReportErrorLabel) {
								Log.clickElementInfo(buttonType.toString());
								buttonImg.click();
								// If resubmit then wait for modal and confirm
								// resubmit.
								if (buttonType == ReportsButtonType.Resubmit) {
									this.waitForResubmitPopupToShow();
									Log.clickElementInfo("Confirm Resubmit");
									this.btnProcessResubmit.click();
									this.waitForPageLoad();
									this.waitForAJAXCallsToComplete();
								}
								if (buttonType == ReportsButtonType.Delete) {
									this.waitForConfirmDeletePopupToShow();
									if (confirmAction) {
										Log.clickElementInfo("Confirm Delete");
										this.clickOnConfirmInDeleteReportPopup();
										this.waitForConfirmDeletePopupToClose();
									}
								}

								if (removeDBCache) {
									DBCache.INSTANCE.remove(Report.CACHE_KEY + rptTitle);
								}
							}
						}
						return true;
					}
					Log.error("Button image is not visible '" + buttonXPath + "'");
					return false;
				} catch (org.openqa.selenium.NoSuchElementException e) {
					Log.error("Button image not found '" + buttonXPath + "': " + e);
					return false;
				}
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
				toNextPage();
				pageCounter++;

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
		Log.info("Set image width to '" + imageMapWidth + "'");
		sendKeysToElement(inputImgMapWidth, imageMapWidth);
	}

	public void inputImageMapHeight(String imageMapHeight) {
		if (imageMapHeight == null || imageMapHeight.equals("")) {
			return;
		}
		Log.info("Set image height to '" + imageMapHeight + "'");
		sendKeysToElement(inputImgMapHeight, imageMapHeight);
	}

	public void inputExclusionRadius(String exclusionRadius) {
		Log.info("Set exclusion radius to '" + exclusionRadius + "'");
		this.inputExclusionRadius.clear();
		this.inputExclusionRadius.sendKeys(exclusionRadius);
	}

	public void inputFOVOpacity(String fovOpacity) {
		Log.info("Set FOV Opacity to '" + fovOpacity + "'");
		this.inputFOVOpacity.clear();
		this.inputFOVOpacity.sendKeys(fovOpacity);
	}

	public void inputSurveyUsername(String username) {
		Log.info("Set survey username to '" + username + "'");
		this.userName.clear();
		this.userName.sendKeys(username);
	}

	public boolean isComplexBoundary(ReportCommonEntity reportsCompliance) {
		return isComplexBoundary(reportsCompliance.getCustomerBoundaryFilterType(), reportsCompliance.getCustomerBoundaryName());
	}

	private boolean isComplexBoundary(CustomerBoundaryFilterType boundaryFilterType, String boundaryName) {
		return BaseReportEntity.ComplexBoundaryNames.stream()
			.anyMatch(b -> isComplexCustomerBoundaryMatch(boundaryFilterType, boundaryName, b));
	}

	private boolean isComplexCustomerBoundaryMatch(ReportCommonEntity reportsCompliance, String complexBoundary) {
		return isComplexCustomerBoundaryMatch(reportsCompliance.getCustomerBoundaryFilterType(), reportsCompliance.getCustomerBoundaryName(), complexBoundary);
	}

	private boolean isComplexCustomerBoundaryMatch(CustomerBoundaryFilterType boundaryFilterType, String boundaryName, String complexBoundary) {
		List<String> parts = RegexUtility.split(complexBoundary, RegexUtility.VERTICAL_BAR_SPLIT_REGEX_PATTERN);
		return boundaryFilterType.toString().equals(parts.get(0)) && boundaryName.equals(parts.get(1));
	}

	private String getComplexCustomerBoundaryId(ReportCommonEntity reportsCompliance, String complexBoundary) {
		return RegexUtility.split(complexBoundary, RegexUtility.VERTICAL_BAR_SPLIT_REGEX_PATTERN).get(2);
	}

	protected void fillCustomBoundaryUsingHiddenFields(ReportCommonEntity reportsCompliance) throws Exception {
		this.selectCustomerBoundaryRadioButton();
		this.waitForCustomerBoundarySectionToShow();
		Optional<String> boundaryId = BaseReportEntity.ComplexBoundaryNames.stream()
				.filter(b -> isComplexCustomerBoundaryMatch(reportsCompliance, b))
				.map(b -> getComplexCustomerBoundaryId(reportsCompliance, b))
				.findFirst();

		if (!boundaryId.isPresent()) {
			throw new Exception(String.format("No boundary ID present for complex CustomerBoundary - '%s'. ",
					reportsCompliance.getCustomerBoundaryName()));
		}

		Log.info(String.format("Found boundary id - '%s' for complex CustomerBoundary - '%s'. ",
				boundaryId.get(), reportsCompliance.getCustomerBoundaryName()));

		Log.info("Setting boundarySelectedId value in input ...");
		this.setInputBoundarySelectedIdValue(boundaryId.get());
		Log.info("Setting boundarySelectedText value in input ...");
		this.setInputBoundarySelectedTextValue(reportsCompliance.getCustomerBoundaryName());
	}

	public void fillCustomBoundaryTextFields(String neLat, String neLong, String swLat, String swLong) {
		if (neLat != null) {
			Log.info("Set NELat to '" + neLat + "'");
			this.inputNELat.clear();
			this.inputNELat.sendKeys(neLat);
		}
		if (neLong != null) {
			Log.info("Set NELong to '" + neLong + "'");
			this.inputNELong.clear();
			this.inputNELong.sendKeys(neLong);
		}
		if (swLat != null) {
			Log.info("Set SWLat to '" + swLat + "'");
			this.inputSWLat.clear();
			this.inputSWLat.sendKeys(swLat);
		}
		if (swLong != null) {
			Log.info("Set SWLong to '" + swLong + "'");
			this.inputSWLong.clear();
			this.inputSWLong.sendKeys(swLong);
		}
	}

	protected void handleOptionalDynamicViewLayersSection(List<Map<String, String>> viewLayersList) {
		if (viewLayersList != null && !viewLayersList.isEmpty()) {
			selectViewLayerAssets(viewLayersList.get(0));
			selectViewLayerBoundaries(viewLayersList.get(0));
		}
	}

	public boolean resubmitReport(String rptTitle, String strCreatedBy) {
		Log.method("ReportsCommonPage.resubmitReport", rptTitle, strCreatedBy);
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

		final int MAX_PAGES_TO_MOVE_AHEAD = 3;
		int pageCounter = 0;
		for (int rowNum = 1; rowNum <= loopCount && pageCounter < MAX_PAGES_TO_MOVE_AHEAD; rowNum++) {
			reportTitleXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[" + getColumnIndex(COL_HEADER_REPORT_TITLE) + "]";
			createdByXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[" + getColumnIndex(COL_HEADER_CREATED_BY) + "]";

			rptTitleCell = getTable().findElement(By.xpath(reportTitleXPath));
			createdByCell = getTable().findElement(By.xpath(createdByXPath));

			if (rptTitleCell.getText().trim().equalsIgnoreCase(rptTitle)
					&& createdByCell.getText().trim().equalsIgnoreCase(strCreatedBy)) {
				resubmitImgXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[" + getColumnIndex(COL_HEADER_ACTION) + "]/a[2]/img";
				resubmitImg = getTable().findElement(By.xpath(resubmitImgXPath));
				Log.clickElementInfo("Resubmit", ElementType.ICON);
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
				toNextPage();
				pageCounter++;
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
		Log.method("ReportsCommonPage.validatePdfFiles", reportTitle, downloadPath);
		String reportName;
		String reportZipName;
		try {
			reportName = getReportPDFFileName(reportTitle, false /* includeExtension */);
			reportZipName = getReportPDFZipFileName(reportTitle, false /* includeExtension */);
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
		pdfFile3 = Paths.get(downloadPath, reportZipName + File.separator + nameBase.replaceAll("_", "") + ".pdf")
				.toString();

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

		pdfFile2 = Paths
				.get(downloadPath, reportName + File.separator + nameBase.replaceAll("_", "") + "_First View.pdf")
				.toString();

		if (!BaseHelper.validatePdfFile(pdfFile2))
			return false;

		return true;
	}

	public boolean validatePdfFiles(ReportCommonEntity reportsEntity, String downloadPath) {
		Log.method("ReportsCommonPage.validatePdfFiles", reportsEntity, downloadPath);
		String reportName;
		String reportZipName;
		try {
			reportName = getReportPDFFileName(reportsEntity.getRptTitle(), false /* includeExtension */);
			reportZipName = getReportPDFZipFileName(reportsEntity.getRptTitle(), false /* includeExtension */);
			setReportName(reportName);
			BaseHelper.deCompressZipFile(reportZipName, downloadPath);
		} catch (Exception e) {
			Log.error(e.toString());
			return false;
		}

		String nameBase = RegexUtility.replaceSpecialChars(reportsEntity.getRptTitle().trim());
		String viewName;
		String pdfFile1;
		String pdfFile2;
		String pdfFile3;

		List<Map<String, String>> viewList = reportsEntity.getViewList();

		pdfFile1 = Paths.get(downloadPath, reportName + ".pdf").toString();
		pdfFile3 = Paths.get(downloadPath, reportZipName + File.separator + nameBase + ".pdf").toString();
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
			pdfFile2 = Paths.get(downloadPath, reportZipName + File.separator + nameBase + "_" + viewName + ".pdf")
					.toString();
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

	public boolean validateReportCreationDate(String reportTitle, String actualPath) throws IOException {
		Log.method("ReportsCommonPage.validateReportCreationDate", actualPath);
		String reportDate = null;
		String actualReport = actualPath + getReportPDFFileName(reportTitle, true /*includeExtension*/);
		PDFUtility pdfUtility = new PDFUtility();
		String actualReportString = pdfUtility.extractPDFText(actualReport, 0, 1);
		String[] lines = actualReportString.split("\\n");
		Pattern patternToMatch = Pattern.compile("Report Creation Date");
		for (String line : lines) {
			String formattedLine = line.trim();
			if (patternToMatch.matcher(line).find()) {
				Matcher matcher = patternToMatch.matcher(formattedLine);
				matcher.find();
				reportDate = formattedLine.substring(matcher.end() + 1).trim();
			}
		}
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY hh:mm a zzz");
		String currentDate = dateFormat.format(new Date()).toString();
		if (DateUtility.compareDateTimeFormat(reportDate, true)
				&& (DateUtility.compareDates(currentDate.toString(), reportDate, true))) {
			return true;
		}
		Log.error("Date not match, expected: " + currentDate + ", actual: " + reportDate);
		return false;
	}

	public boolean checkBlankReportErrorTextPresentAndRequiredFieldsHighlighted() {
		openNewReportPage();
		this.clickOnOKButton();
		boolean done = false;
		if (done = isElementPresent(strErrorText)) {
			done &= isHighlightedInRed(inputTitle);
			done &= isHighlightedInRed(inputNELat);
			done &= isHighlightedInRed(inputNELong);
			done &= isHighlightedInRed(inputSWLat);
			done &= isHighlightedInRed(inputNELong);
			for (WebElement view : dataTableViews) {
				done &= isHighlightedInRed(view);
			}
		}
		return done;
	}

	public boolean isInputTitleHighlightedInRed(){
		return isHighlightedInRed(inputTitle);
	}

	public boolean isHighlightedInRed(WebElement element) {
		String background = "background: rgb(255, 206, 206)";
		String border = "border: 1px solid red;";
		boolean highlighted = true;
		String value = element.getAttribute("style");
		if (value == null) {
			return false;
		} else if (value.contains("border: ")) {
			highlighted &= value.contains(border);
		}

		highlighted &= value.contains(background);
		return highlighted;
	}

	@Override
	public void modifyReportViews() {
		this.inputViewInd.click();
		this.inputViewIso.click();
		this.inputViewAnno.click();
	}

	public boolean deleteAllDrivingSurveys() {
		for (WebElement btnDelete : btnDeleteDrivingSurveys) {
			jsClick(btnDelete);
			this.waitForPageToLoad();
		}
		return btnDeleteDrivingSurveys.isEmpty();
	}

	public boolean deleteDrivingSurveyByTag(String tag) {
		String xpathToDeleteSurveyBtnByTag = String.format(deleteSurveyBtnByTagParameter, tag);
		WebElement btnDelete = driver.findElement(By.xpath(xpathToDeleteSurveyBtnByTag));
		jsClick(btnDelete);
		this.waitForPageToLoad();
		return true;
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

	public int getNumberofSurveyRecords() {
		List<WebElement> records = this.numberofSurveyRecords;
		return records.size();
	}

	public String getAreaErrorText() {
		return this.areaErrorText.getText();
	}

	public void selectPercentCoverageReportArea() {
		SelectElement(checkBoxPCRA);
	}

	public void selectPercentCoverageAssetCheckBox() {
		SelectElement(checkBoxPCA);
	}

	public void selectGapTableCheckBox() {
		SelectElement(checkBoxGapTb);
	}

	public void selectIsotopicAnalysisCheckBox() {
		SelectElement(checkBoxIsoAna);
	}

	public void selectIndicationsTableCheckBox() {
		SelectElement(checkBoxIndTb);
	}

	public void selectCustomBoundaryRadioButton() {
		this.customBoundaryRadioButton.click();
	}

	public void selectCustomerBoundaryRadioButton() {
		for(int i=0;i<Constants.DEFAULT_MAX_RETRIES; i++){
			try{
				jsClick(this.customerBoundaryRadioButton);
				this.waitForCustomerBoundarySectionToShow();
				return;
			}catch(Exception e){
				Log.warn("Try "+(i+1) + ":Failed to select customer boundary radio button "+e.toString());
			}
		}
	}

	public void selectViewLayerAssets(Map<String, String> viewLayerMap) {
		for (Entry<String, String> entry : viewLayerMap.entrySet()) {
			String key = entry.getKey(); // Key is Asset/Boundary Id
			String value = entry.getValue(); // Value is Asset/Boundary{Prefix}
												// followed by name of
												// Asset/Boundary
			if (value.startsWith(ReportCommonEntity.ASSET_PREFIX)) {
				// Asset key.
				WebElement assetElement = getViewLayerAssetCheckbox(key);
				if (assetElement != null) {
					SelectElement(assetElement);
				}
			} else if (value.startsWith(ReportCommonEntity.ASSET_ALL_PREFIX)) {
				SelectElement(checkboxViewLayerAllAssets);
			}
		}
	}

	public void selectViewLayerBoundaries(Map<String, String> viewLayerMap) {
		for (Entry<String, String> entry : viewLayerMap.entrySet()) {
			String value = entry.getValue(); // Value is Asset/Boundary{Prefix}
												// followed by name of
												// Asset/Boundary
			if (value.startsWith(ReportCommonEntity.BOUNDARY_PREFIX)) {
				// Boundary key.
				value = value.replace(ReportCommonEntity.BOUNDARY_PREFIX, "");
				WebElement boundaryElement = getViewLayerBoundaryCheckbox(value);
				if (boundaryElement != null) {
					SelectElement(boundaryElement);
				}
			} else if (value.startsWith(ReportCommonEntity.BOUNDARY_ALL_PREFIX)) {
				SelectElement(checkboxViewLayerAllBoundaries);
			}
		}
	}

	public WebElement getViewLayerAssetCheckbox(String key) {
		return WebElementExtender.findElementIfExists(driver, String.format("report-asset-layers-%s", key));
	}

	public WebElement getViewLayerBoundaryCheckbox(String value) {
		return WebElementExtender.findElementIfExists(driver, String.format("report-boundry-layers-%s", value));
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

	public boolean verifyComplianceReportButton(String rptTitle, String strCreatedBy,
			ReportsButtonType buttonType) throws Exception {
		Log.method("ReportsCommonPage.verifyComplianceReportButton", rptTitle, strCreatedBy, buttonType.name());
		return checkButtonOnReportsPageAndClick(rptTitle, strCreatedBy, buttonType, false,
				false /* confirmAction */);
	}

	/**
	 * Verifies that the customer boundary name auto-complete list contains the
	 * specified entries.
	 */
	public boolean verifyCustomerBoundaryLatLongSelectorAutoCompleteListContains(String boundaryFilterType,
			String customerBoundaryName, List<String> autocompleteListEntries) {
		Log.method("ReportsCommonPage.verifyCustomerBoundaryLatLongSelectorAutoCompleteListContains",
				boundaryFilterType, customerBoundaryName, LogHelper.listToString(autocompleteListEntries));
		openCustomerBoundarySelector();
		latLongSelectionControl.waitForModalDialogOpen();
		latLongSelectionControl.switchMode(ControlMode.MapInteraction);
		latLongSelectionControl.waitForMapImageLoad();
		focusOnPage(latLongSelectionControl.getFilterByTypeDropDown());
		latLongSelectionControl.selectCustomerBoundaryType(boundaryFilterType);

		// Type customer boundary name and verify the autocomplete list. If not
		// all entries shown, return false.
		if (!latLongSelectionControl.verifyCustomerBoundaryAutoCompleteListContains(customerBoundaryName,
				autocompleteListEntries)) {
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
	public boolean verifyReportStaticText(ReportCommonEntity reportsEntity) throws IOException {
		Log.method("ReportsCommonPage.verifyReportStaticText", reportsEntity);
		return verifyReportStaticText(reportsEntity, testSetup.getDownloadPath());
	}

	/**
	 * Method to verify the static text
	 *
	 * @param actualPath
	 * @param reportTitle
	 * @return
	 * @throws IOException
	 */
	public boolean verifyReportStaticText(ReportCommonEntity reportsEntity, String actualPath)
			throws IOException {
		Log.method("ReportsCommonPage.verifyReportStaticText", reportsEntity, actualPath);
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportsEntity.getRptTitle());
		String reportId = reportObj.getId();
		String actualReport = actualPath + getReportPrefix() + "-" + reportId.substring(0, 6) + ".pdf";
		setReportName(getReportPrefix() + "-" + reportId);
		setReportName(getReportName());
		String actualReportString = pdfUtility.extractPDFText(actualReport, 0, 1);
		actualReportString = RegexUtility.removeSpecialChars(actualReportString);
		Log.info(String.format("PDF Text Content : %s", actualReportString));
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(STRReportTitle);
		expectedReportString.add(ComplianceReportSSRS_MapHeightWidth);
		if (isCustomBoundarySpecified(reportsEntity)) {
			if (!BaseHelper.isNullOrEmpty(reportsEntity.getNELat())
					&& !BaseHelper.isNullOrEmpty(reportsEntity.getNELong())) {
				expectedReportString.add(ComplianceReportSSRS_NELatNELong);
			}
			if (!BaseHelper.isNullOrEmpty(reportsEntity.getSWLat())
					&& !BaseHelper.isNullOrEmpty(reportsEntity.getSWLong())) {
				expectedReportString.add(ComplianceReportSSRS_SWLatSWLong);
			}
		} else {
			if (!BaseHelper.isNullOrEmpty(reportsEntity.getCustomerBoundaryName())) {
				expectedReportString.add(ComplianceReportSSRS_Boundary);
			}
		}

		Supplier<List<String>> suppliedStaticText = supplySSRSPDFExpectedStaticText(reportsEntity);
		if (suppliedStaticText.get() != null) {
			expectedReportString.addAll(suppliedStaticText.get());
		}

		Log.info(String.format("Expected Strings in PDF Text Content : %s", LogHelper.strListToString(expectedReportString)));
		Map<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
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
	public boolean verifyReportContainsText(String reportTitle, List<String> expectedReportString)
			throws IOException {
		Log.method("ReportsCommonPage.verifyReportContainsText", reportTitle,
				LogHelper.listToString(expectedReportString));
		Map<String, Boolean> actualFirstPage = getStringMatchResultMap(reportTitle, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value)
				return false;
		}
		return true;

	}

	/**
	 * Method to verify static text is NOT matched.
	 *
	 * @param reportTitle
	 * @param expectedReportString
	 * @return
	 * @throws IOException
	 */
	public boolean verifyReportDoesNotContainText(String reportTitle, List<String> expectedReportString)
			throws IOException {
		Log.method("ReportsCommonPage.verifyReportDoesNotContainText", reportTitle,
				LogHelper.listToString(expectedReportString));
		Map<String, Boolean> actualFirstPage = getStringMatchResultMap(reportTitle, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (value)
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
		Log.method("ReportsCommonPage.verifyShowCoverageTable", actualPath, reportTitle);
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, getReportPrefix() + "-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = getReportPrefix() + "-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_ShowCoverage);
		expectedReportString.add(ComplianceReportSSRS_PercentCoverageAssets);
		expectedReportString.add(ComplianceReportSSRS_PercentCoverageForecast);
		expectedReportString.add(ComplianceReportSSRS_PercentCoverageReportArea);

		Map<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.info("Show Coverage Table verification failed");
				return false;
			}
		}
		Log.info("Show Coverage Table verification passed");
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
		Log.method("ReportsCommonPage.verifyCoverageValuesTable", actualPath, reportTitle,
				LogHelper.mapToString(userSelection));
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, getReportPrefix() + "-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = getReportPrefix() + "-" + reportId;
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
		int matchIndex = 0;
		if (userSelection.get(KEYPCA).equals("1")) {
			PCA = matches.get(matchIndex++).replaceAll("[\\D+]", "");
			coverageReportObj.setPercentCoverageAssets(PCA);
			expectedReportString.add(ComplianceReportSSRS_TotalLinearAssetCoverage);
		}
		if (userSelection.get(KEYPCRA).equals("1")) {
			PCRA = matches.get(matchIndex).replaceAll("[\\D+]", "");
			coverageReportObj.setPercentCoverageReportArea(PCRA);
			expectedReportString.add(ComplianceReportSSRS_PercentCoverageReportArea);
		}

		Log.info(String.format("Matching expected report strings-[%s], with actual PDF text.",
				LogHelper.arrayToString(expectedReportString.toArray(new String[expectedReportString.size()]))));
		Map<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
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
		Log.method("ReportsCommonPage.verifyLayersTable", actualPath, reportTitle,
				LogHelper.mapToString(userInput));
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, getReportPrefix() + "-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = getReportPrefix() + "-" + reportId;
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

		Map<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
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
	 * @param viewsList
	 * @return
	 * @throws IOException
	 */
	public boolean verifyViewsTable(String actualPath, String reportTitle, List<Map<String, String>> viewsList)
			throws IOException {
		Log.method("ReportsCommonPage.verifyViewsTable", actualPath, reportTitle,
				LogHelper.mapListToString(viewsList));
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, getReportPrefix() + "-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = getReportPrefix() + "-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_ViewTable);
		expectedReportString.add(ComplianceReportSSRS_ViewName);
		expectedReportString.add(ComplianceReportSSRS_ShowFOV);
		expectedReportString.add(ComplianceReportSSRS_ShowBreadcrumb);
		expectedReportString.add(ComplianceReportSSRS_ShowGaps);
		expectedReportString.add(ComplianceReportSSRS_ShowAssets);
		expectedReportString.add(ComplianceReportSSRS_ShowHighlightGAPAssets);
		expectedReportString.add(ComplianceReportSSRS_ShowBoundaries);
		expectedReportString.add(ComplianceReportSSRS_BaseMap);

		Supplier<List<String>> expectedTextSupplier = supplyViewsTableExpectedStaticText(viewsList);
		if (expectedTextSupplier.get() != null) {
			expectedReportString.addAll(expectedTextSupplier.get());
		}

		String textWithoutLineEndings = actualReportString.replace("\r\n", "");
		Map<String, Boolean> patternMatches = matchSinglePattern(textWithoutLineEndings, expectedReportString);
		for (Boolean value : patternMatches.values()) {
			if (!value) {
				Log.info("Views Table static text verification failed");
				return false;
			}
		}

		InputStream inputStream = new ByteArrayInputStream(actualReportString.getBytes());
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));

		String line = null;
		ArrayList<ReportView> viewListInReport = new ArrayList<ReportView>();
		Log.info("Matching lines to check if it is a Views table row.");
		try {
			while ((line = bufferReader.readLine()) != null) {
				List<String> matchingGroups = RegexUtility.getMatchingGroups(line.trim(), RegexUtility.VIEWS_TABLE_LINE_REGEX_PATTERN);
				if (matchingGroups != null && matchingGroups.size() > 0) {
					line = matchingGroups.get(0);
					Log.info(String.format("Matched line as a table row! Line = [%s]", line));
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
		Log.method("ReportsCommonPage.verifyDrivingSurveysTable", actualPath, reportTitle);
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, getReportPrefix() + "-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = getReportPrefix() + "-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ReportSSRS_SelectedDrivingSurveys);
		Map<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.info("Driving survey table static text verification failed");
				return false;
			}
		}
		String surveyTable;
		String endWith = "Surveyor Date";
		if (RegexUtility.getStringInBetween(actualReportString, "Indication Table", endWith) != null) {
			surveyTable = (RegexUtility.getStringInBetween(actualReportString, "Indication Table", "Surveyor Date"))
					.trim().replaceAll("//s+", "").replace("#", "").replace("LISA ", "");
			if (surveyTable.contains("Gap Table")) {
				// TODO: DEFECT in parsing. SKIP check for this case.
				Log.warn(
						"SKIPPING Driving survey verification. The case of Driving Survey table and Gap table PDF parsing is currently NOT supported!!!");
				return true;
			}
		} else {
			endWith = " Layers";
			surveyTable = (RegexUtility.getStringInBetween(actualReportString, "Selected Driving Surveys", endWith))
					.trim().replaceAll("//s+", "").replace("#", "").replace("LISA ", "");
		}

		surveyTable = surveyTable.replaceAll(System.lineSeparator(), "");
		String datePattern = RegexUtility.getReportRegexDatePattern(true);
		String drivingSurveysLinePattern = datePattern + " *" + datePattern;
		surveyTable = surveyTable.replaceAll("(" + drivingSurveysLinePattern + ")", System.lineSeparator() + "$1");
		String[] lines = surveyTable.split(System.lineSeparator());
		Log.info("Driving survey table contains " + (lines.length - 1) + " records");
		ArrayList<StoredProcComplianceAssessmentGetReportDrivingSurveys> listFromStoredProc = StoredProcComplianceAssessmentGetReportDrivingSurveys
				.getReportDrivingSurveys(reportId);
		for (int i = 1; i < lines.length; i++) {
			boolean validLine = false;
			String expectedLine = "";
			String actualLine = lines[i].replaceAll(" ", "");
			// Log.info("Looking for driving survey '" + actualLine + "' in
			// DB");
			for (StoredProcComplianceAssessmentGetReportDrivingSurveys survey : listFromStoredProc) {
				expectedLine = survey.toString().replaceAll(" ", "");
				Log.info("Driving survey line in DB = [" + expectedLine + "]");
				if (actualLine.startsWith(expectedLine)) {
					Log.info("Found match for driving survey in DB.");
					validLine = true;
					break;
				}
			}
			if (!validLine) {
				Log.error(String.format("Driving survey in PDF is not found in DB, '%s'", actualLine));
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
		Log.method("ReportsCommonPage.verifyEthaneCaptureTable", actualPath, reportTitle);
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = actualPath + getReportPrefix() + "-" + reportId.substring(0, 6) + ".pdf";
		String reportName = getReportPrefix() + "-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_EthaneAnalysisTable);
		Map<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
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

	public boolean verifyMetaDataFilesArePresent(String downloadPath, String reportTitle,
			boolean verifyGapMetaPresent, boolean verifyLisaMetaPresent, boolean verifySurveyMetaPresent, 
			boolean verifyIsotopicMetaPresent, boolean verifyLisaAnalyticsMetaPresent) throws IOException {
		Log.method("verifyMetaDataFilesArePresent", downloadPath, reportTitle,
				verifyGapMetaPresent, verifyLisaMetaPresent, verifySurveyMetaPresent, verifyIsotopicMetaPresent);

		Boolean present = false;
		String pathToMetaDataUnZip = getReportMetaUnzipFolder(downloadPath, reportTitle);
		List<String> filesInDirectory = FileUtility.getFilesInDirectory(Paths.get(pathToMetaDataUnZip), false /*includeFullPath*/);

		String reportID = getReportName(reportTitle);
		String reportPrefix = getReportPrefix();
		String metaFile = String.format("%s-%s-Report.csv", reportPrefix, reportID);
		String metaJsonFile = String.format("%s-%s-Report.json", reportPrefix, reportID);
		String metaGapFile = String.format("%s-%s-ReportGAP.csv", reportPrefix, reportID);
		String metaLISAFile = String.format("%s-%s-ReportLISAS.csv", reportPrefix, reportID);
		String metaLISAAnalyticsFile = String.format("%s-%s-ReportLISAS_Analytics.csv", reportPrefix, reportID);
		String metaSurveyFile = String.format("%s-%s-ReportSurvey.csv", reportPrefix, reportID);
		String metaIsoCaptureFile = String.format("%s-%s-ReportIsotopicCapture.csv", reportPrefix, reportID);

		Log.info(String.format("Files found in MetaData ZIP -> %s", LogHelper.listToString(filesInDirectory)));
		if (filesInDirectory != null && filesInDirectory.size() > 0) {
			present = filesInDirectory.contains(metaFile) && filesInDirectory.contains(metaJsonFile);
			if (!present) {
				Log.error("*Report.csv && *Report.json NOT found in Metadata ZIP");
			}

			if (verifyGapMetaPresent) {
				present = present && filesInDirectory.contains(metaGapFile);
				if (!present) {
					Log.error("*ReportGAP.csv NOT found in Metadata ZIP");
				}
			}

			if (verifyLisaMetaPresent) {
				present = present && filesInDirectory.contains(metaLISAFile);
				if (!present) {
					Log.error("*ReportLISAS.csv NOT found in Metadata ZIP");
				}
			}

			if (verifyLisaAnalyticsMetaPresent) {
				present = present && filesInDirectory.contains(metaLISAAnalyticsFile);
				if (!present) {
					Log.error("*ReportLISAS_Analytics.csv NOT found in Metadata ZIP");
				}
			}
			
			if (verifySurveyMetaPresent) {
				present = present && filesInDirectory.contains(metaSurveyFile);
				if (!present) {
					Log.error("*ReportSurvey.csv NOT found in Metadata ZIP");
				}
			}

			if (verifyIsotopicMetaPresent) {
				present = present && filesInDirectory.contains(metaIsoCaptureFile);
				if (!present) {
					Log.error("*ReportIsotopicCapture.csv NOT found in Metadata ZIP");
				}
			}
		}

		return present;
	}

	public boolean verifyReportSurveyMetaDataFile(String actualPath, String reportTitle)
			throws FileNotFoundException, IOException {
		Log.method("ReportsCommonPage.verifyReportSurveyMetaDataFile", actualPath, reportTitle);
		CSVUtility csvUtility = new CSVUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String pathToMetaDataUnZip = getReportMetaUnzipFolder(actualPath, reportTitle);

		String pathToCsv = pathToMetaDataUnZip + File.separator + getReportPrefix() + "-" + reportId.substring(0, 6)
				+ "-ReportSurvey.csv";
		String reportName = getReportPrefix() + "-" + reportId;

		if (actualPath.endsWith("-ReportSurvey.csv")) {
			pathToCsv = actualPath;
		}

		setReportName(reportName);
		List<Map<String, String>> csvRows = csvUtility.getAllRows(pathToCsv);
		Iterator<Map<String, String>> csvIterator = csvRows.iterator();
		List<StoredProcComplianceAssessmentGetReportDrivingSurveys> reportList = new ArrayList<StoredProcComplianceAssessmentGetReportDrivingSurveys>();
		while (csvIterator.hasNext()) {
			StoredProcComplianceAssessmentGetReportDrivingSurveys reportDrivingObj = new StoredProcComplianceAssessmentGetReportDrivingSurveys();
			Map<String, String> csvRow = csvIterator.next();
			if (!csvRow.get("ReportId").trim().toLowerCase().equals(reportId.trim().toLowerCase())) {
				return false;
			}
			if (!csvRow.get("ReportName").trim().equals(getReportName().trim().substring(0, 9))) {
				return false;
			}
			reportDrivingObj.setPreferredStartDateTimeWithTZ(csvRow.get("SurveyStartDateTime").trim());
			reportDrivingObj.setPreferredEndDateTimeWithTZ(csvRow.get("SurveyEndDateTime").trim());
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

	private String getReportMetaUnzipFolder(String actualPath, String reportTitle) {
		String metaDataZipFileName = getReportMetaZipFileName(reportTitle, false /* includeExtension */);
		String pathToMetaDataUnZip = actualPath;
		String unZipFolder = File.separator + metaDataZipFileName;
		if (!actualPath.endsWith(unZipFolder))
			pathToMetaDataUnZip += unZipFolder;
		return pathToMetaDataUnZip;
	}

	public boolean verifyIsotopicMetaDataFile(String actualPath, String reportTitle)
			throws FileNotFoundException, IOException {
		Log.method("ReportsCommonPage.verifyIsotopicMetaDataFile", actualPath, reportTitle);
		CSVUtility csvUtility = new CSVUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String pathToMetaDataUnZip = getReportMetaUnzipFolder(actualPath, reportTitle);

		String pathToCsv = pathToMetaDataUnZip + File.separator + getReportPrefix() + "-" + reportId.substring(0, 6)
				+ "-ReportIsotopicCapture.csv";
		String reportName = getReportPrefix() + "-" + reportId;
		setReportName(reportName);
		List<Map<String, String>> csvRows = csvUtility.getAllRows(pathToCsv);
		Iterator<Map<String, String>> csvIterator = csvRows.iterator();
		List<StoredProcComplianceGetIsotopics> reportList = new ArrayList<StoredProcComplianceGetIsotopics>();
		while (csvIterator.hasNext()) {
			StoredProcComplianceGetIsotopics reportIsoObj = new StoredProcComplianceGetIsotopics();
			Map<String, String> csvRow = csvIterator.next();
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
		Log.method("ReportsCommonPage.verifyEthaneCaptureMetaDataFile", actualPath, reportTitle);
		return verifyEthaneCaptureMetaDataFile(actualPath, reportTitle, Report.getReport(reportTitle).getId());
	}

	public boolean verifyEthaneCaptureMetaDataFile(String actualPath, String reportTitle, String reportId)
			throws FileNotFoundException, IOException {
		Log.method("ReportsCommonPage.verifyEthaneCaptureMetaDataFile", actualPath, reportTitle, reportId);

		CSVUtility csvUtility = new CSVUtility();
		String pathToMetaDataUnZip = getReportMetaUnzipFolder(actualPath, reportTitle);

		String pathToCsv = pathToMetaDataUnZip + File.separator + getReportPrefix() + "-" + reportId.substring(0, 6)
				+ "-ReportEthaneCapture.csv";
		String reportName = getReportPrefix() + "-" + reportId;

		if (actualPath.endsWith("-ReportEthaneCapture.csv")) {
			pathToCsv = actualPath;
		}
		setReportName(reportName);
		List<Map<String, String>> csvRows = csvUtility.getAllRows(pathToCsv);
		Iterator<Map<String, String>> csvIterator = csvRows.iterator();
		List<StoredProcComplianceGetEthaneCapture> reportList = new ArrayList<StoredProcComplianceGetEthaneCapture>();
		while (csvIterator.hasNext()) {
			StoredProcComplianceGetEthaneCapture ethaneCapture = new StoredProcComplianceGetEthaneCapture();
			Map<String, String> csvRow = csvIterator.next();
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

	public boolean verifyLISASMetaDataFile(String actualPath, String reportTitle) throws Exception{
		Log.method("ReportsCommonPage.verifyLISASMetaDataFile", actualPath, reportTitle);
		return verifyLISASMetaDataFile(actualPath, reportTitle, Report.getReport(reportTitle).getId());
	}

	public boolean verifyNumberOfLISAsInMetaDataFile(String actualPath, String reportTitle, Integer expectedLISACount)
			throws Exception {
		List<String> outLISAs = new ArrayList<String>();
		verifyLISASMetaDataFile(actualPath, reportTitle, Report.getReport(reportTitle).getId(), outLISAs);
		return (outLISAs.size() == expectedLISACount);
	}

	public boolean verifyLISASMetaDataFile(String actualPath, String reportTitle, String reportId)
			throws Exception {
		return verifyLISASMetaDataFile(actualPath, reportTitle, reportId, null /*outLISAs*/);
	}

	public boolean verifyLISASMetaDataFile(String actualPath, String reportTitle, String reportId, List<String> outLISAs)
			throws Exception {
		Log.method("ReportsCommonPage.verifyLISASMetaDataFile", actualPath, reportTitle, reportId);
		CSVUtility csvUtility = new CSVUtility();
		String pathToMetaDataUnZip = getReportMetaUnzipFolder(actualPath, reportTitle);

		String pathToCsv = pathToMetaDataUnZip + File.separator + getReportPrefix() + "-" + reportId.substring(0, 6) + "-ReportLISAS.csv";
		String reportName = getReportPrefix() + "-" + reportId;
		if (actualPath.endsWith("-ReportLISAS.csv")) {
			pathToCsv = actualPath;
		}
		setReportName(reportName);
		
		List<Map<String, String>> csvRows;
		if(!new File(pathToCsv).exists()){
			csvRows = new ArrayList<Map<String,String>>();
		}else{
			csvRows = csvUtility.getAllRows(pathToCsv);
		}
		Iterator<Map<String, String>> csvIterator = csvRows.iterator();
		List<StoredProcComplianceGetIndications> reportList = new ArrayList<StoredProcComplianceGetIndications>();

		// LISAs list. Used to verify LISA numbers sequential ordering. If provided, use Collection from caller.
		List<String> lisasList = outLISAs;
		if (lisasList == null) {
			lisasList = new ArrayList<String>();
		}
		while (csvIterator.hasNext()) {
			StoredProcComplianceGetIndications reportIndObj = new StoredProcComplianceGetIndications();
			Map<String, String> csvRow = csvIterator.next();
			if (!csvRow.get("ReportId").trim().equalsIgnoreCase(reportId.trim())) {
				Log.info("ReportId does NOT match. LISA Meta data file verification failed");
				return false;
			}
			if (!csvRow.get("ReportName").trim().equalsIgnoreCase(getReportName().trim().substring(0, 9))) {
				Log.info("ReportName does NOT match. LISA Meta data file verification failed");
				return false;
			}
			String lisaNumber = csvRow.get("LISANumber").trim();
			reportIndObj.setPeakNumber(lisaNumber.replaceAll("LISA", ""));
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
			try {
				if (!BaseHelper.isNullOrEmpty(csvRow.get("ConfidenceInDisposition").trim())) {
					int aggregatedClassificationconfidenceFloat = (int) (Float
							.parseFloat(csvRow.get("ConfidenceInDisposition").trim()) * 100);
					aggregatedClassificationconfidence = aggregatedClassificationconfidenceFloat + "%";
				} else {
					aggregatedClassificationconfidence = "0%";
				}
			} catch (Exception e) {
				Log.warn(e.toString());
			}
			reportIndObj.setAggregatedClassificationConfidence(aggregatedClassificationconfidence);
			reportList.add(reportIndObj);

			lisasList.add(lisaNumber);
		}

		if (!verifyLisasAreUpperCase(lisasList)) {
			throw new Exception("Incorrect LISA label casing detected.");
		}

		if (!verifyLisaNumbersAreInSequentialOrder(lisasList)) {
			throw new Exception("Incorrect LISA number sequential ordering found in LISAS metadata file.");
		}

		ArrayList<StoredProcComplianceGetIndications> storedPodList = StoredProcComplianceGetIndications.getReportIndications(reportId);

		for (StoredProcComplianceGetIndications reportListObj : reportList) {
			if (!reportListObj.isInList(storedPodList)) {
				Log.info(String.format(
						"LISA Meta data file verification failed. Report object from database -> [%s] NOT found in CSV.",
						reportListObj.toString()));
				return false;
			}
		}
		
		Log.info("LISA Meta data file verification passed");

		return true;
	}

	public Map<Integer, Integer> getLISASAnalyticsRankingMap(String reportTitle) throws Exception{
		Log.method("ReportsCommonPage.getLISASAnalyticsRankingMap", reportTitle);
		Map<Integer, Integer> rankingMap = new HashMap<Integer, Integer>();
		List<Integer> rankingList = getLISASAnalyticsRankingList(reportTitle);
		for(Integer rankingGroup:rankingList){
			int num = 1;
			if(rankingMap.containsKey(rankingGroup)){
				num += rankingMap.get(rankingGroup);
			}
			rankingMap.put(rankingGroup, num);
		}
		return rankingMap;
	}
	
	public List<Integer> getLISASAnalyticsRankingList(String reportTitle) throws Exception{
		String reportId = Report.getReport(reportTitle).getId();
		String actualPath = getDownloadPath(ReportFileType.MetaDataZIP, reportTitle);
		Log.method("ReportsCommonPage.getLISASAnalyticsRankingList",reportTitle);
		CSVUtility csvUtility = new CSVUtility();
		String pathToMetaDataUnZip = getReportMetaUnzipFolder(actualPath, reportTitle);
		String pathToCsv = pathToMetaDataUnZip + File.separator + getReportPrefix() + "-" + reportId.substring(0, 6) + "-ReportLISAS_Analytics.csv";
		if (actualPath.endsWith("-ReportLISAS_Analytics.csv")) {
			pathToCsv = actualPath;
		}	
		List<Map<String, String>> csvRows;
		if(!new File(pathToCsv).exists()){
			csvRows = new ArrayList<Map<String,String>>();
		}else{
			csvRows = csvUtility.getAllRows(pathToCsv);
		}
		return getLISASAnalyticsRankingList(csvRows);
	}
	
	private List<Integer> getLISASAnalyticsRankingList(List<Map<String, String>> csvRows){
		List<Integer> rankingGroup = new ArrayList<Integer>();
		Iterator<Map<String, String>> csvIterator = csvRows.iterator();
		while (csvIterator.hasNext()) {
			Integer ranking = -1;
			Map<String, String> csvRow = csvIterator.next();
			try{
				ranking = Integer.valueOf(csvRow.get("RankingGroup"));
			}catch(Exception e){
				Log.warn("Failed to get the ranking group of a indication "+e);
			}
			rankingGroup.add(ranking);
		}
		return rankingGroup;
	}
	
	public boolean verifyLISASAnalyticsMetaDataFile(String reportTitle) throws Exception{
		String downloadPath = getDownloadPath(ReportFileType.MetaDataZIP, reportTitle);
		return verifyLISASAnalyticsMetaDataFile(downloadPath, reportTitle);
	}
	
	public boolean verifyLISASAnalyticsMetaDataFile(String actualPath, String reportTitle)
			throws Exception {
		String reportId = Report.getReport(reportTitle).getId();
		Log.method("ReportsCommonPage.verifyLISASAnalyticsMetaDataFile", actualPath, reportTitle, reportId);
		CSVUtility csvUtility = new CSVUtility();
		String pathToMetaDataUnZip = getReportMetaUnzipFolder(actualPath, reportTitle);

		String pathToCsv = pathToMetaDataUnZip + File.separator + getReportPrefix() + "-" + reportId.substring(0, 6) + "-ReportLISAS_Analytics.csv";
		String reportName = getReportPrefix() + "-" + reportId;
		if (actualPath.endsWith("-ReportLISAS_Analytics.csv")) {
			pathToCsv = actualPath;
		}
		setReportName(reportName);
		
		List<Map<String, String>> csvRows;
		if(!new File(pathToCsv).exists()){
			csvRows = new ArrayList<Map<String,String>>();
		}else{
			csvRows = csvUtility.getAllRows(pathToCsv);
		}
		List<Integer> rankingGroup = getLISASAnalyticsRankingList(csvRows);
		
		Iterator<Map<String, String>> csvIterator = csvRows.iterator();
		List<StoredProcComplianceGetIndications> reportList = new ArrayList<StoredProcComplianceGetIndications>();

		List<String> lisasList = new ArrayList<String>();
		while (csvIterator.hasNext()) {
			StoredProcComplianceGetIndications reportIndObj = new StoredProcComplianceGetIndications();
			Map<String, String> csvRow = csvIterator.next();
			if (!csvRow.get("ReportId").trim().equalsIgnoreCase(reportId.trim())) {
				Log.info("ReportId does NOT match. LISA Meta data file verification failed");
				return false;
			}
			if (!csvRow.get("ReportName").trim().equalsIgnoreCase(getReportName().trim().substring(0, 9))) {
				Log.info("ReportName does NOT match. LISA Meta data file verification failed");
				return false;
			}
			String lisaNumber = csvRow.get("LISANumber").trim();
			reportIndObj.setPeakNumber(lisaNumber.replaceAll("LISA", ""));
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
			try {
				if (!BaseHelper.isNullOrEmpty(csvRow.get("ConfidenceInDisposition").trim())) {
					int aggregatedClassificationconfidenceFloat = (int) (Float
							.parseFloat(csvRow.get("ConfidenceInDisposition").trim()) * 100);
					aggregatedClassificationconfidence = aggregatedClassificationconfidenceFloat + "%";
				} else {
					aggregatedClassificationconfidence = "0%";
				}
			} catch (Exception e) {
				Log.warn(e.toString());
			}
			reportIndObj.setAggregatedClassificationConfidence(aggregatedClassificationconfidence);
			reportList.add(reportIndObj);

			lisasList.add(lisaNumber);
		}

		if (!verifyLisasAreUpperCase(lisasList)) {
			throw new Exception("Incorrect LISA label casing detected.");
		}

		if (!verifyLisaNumbersAreInSequentialOrder(lisasList)) {
			throw new Exception("Incorrect LISA number sequential ordering found in LISAS metadata file.");
		}

		ArrayList<StoredProcComplianceGetIndications> storedPodList = StoredProcComplianceGetIndications.getReportIndications(reportId);

		int numIndications = 0;
		for (StoredProcComplianceGetIndications reportListObj : reportList) {
			if (!reportListObj.isInList(storedPodList)) {
				Log.info(String.format(
						"LISA Analytics Meta data file verification failed. Report object from database -> [%s] NOT found in CSV.",
						reportListObj.toString()));
				return false;
			}
			if(numIndications>=rankingGroup.size() || rankingGroup.get(numIndications++)<-1){
				Log.error((String.format(
						"LISA Analytics Meta data file verification failed. Report object from database -> [%s] NOT in any ranking group in CSV.",
						reportListObj.toString())));
				return false;
			}
		}
		
		Log.info("LISA Analytics Meta data file verification passed");
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
		Log.method("ReportsCommonPage.verifyIsotopicValueIsFormattedCorrectly", isotopicUncertaintyValue);

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

	private boolean verifyLisasAreUpperCase(List<String> lisasList) {
		Log.method("verifyLisasAreUpperCase", lisasList);
		if (lisasList == null || lisasList.size() == 0) {
			return true;
		}

		// Verify all LISA instances are in Caps.
		Set<String> lisaCollection = lisasList.stream()
				.map(l -> RegexUtility.getMatchingGroups(l, RegexUtility.LISA_REGEX).get(1))
				.collect(Collectors.toSet());

		Log.info(String.format("LISA labels set retrieved from metadata is -> %s", LogHelper.setToString(lisaCollection)));
		return (lisaCollection.size()==1) && (lisaCollection.toArray(new String[1])[0].equals("LISA"));
	}

	private boolean verifyLisaNumbersAreInSequentialOrder(List<String> lisasList) {
		Log.method("verifyLisaNumbersAreInSequentialOrder", lisasList);
		if (lisasList == null || lisasList.size() == 0) {
			return true;
		}

		List<Integer> lisaNumbers = lisasList.stream()
			.map(l -> Integer.valueOf(l.replace("LISA", "").trim()))
			.collect(Collectors.toList());
		Log.info(String.format("LISA numbers retrieved from metadata are -> %s", LogHelper.listToString(lisaNumbers)));
		for (int i = 1; i <= lisaNumbers.size(); i++) {
			if (i != lisaNumbers.get(i-1)) {
				Log.error(String.format("LISA number sequential order mismatch at index-%d. NOT matching LISA number is - %d", i-1, lisaNumbers.get(i)));
				return false;
			}
		}

		return true;
	}

	public boolean verifyUncertaintyValueIsFormattedCorrectly(String isotopicUncertaintyValue) {
		Log.method("ReportsCommonPage.verifyUncertaintyValueIsFormattedCorrectly", isotopicUncertaintyValue);
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
		Log.method("ReportsCommonPage.verifyEthaneAnalysisTable", actualPath, reportTitle);
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, getReportPrefix() + "-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = getReportPrefix() + "-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_EthaneAnalysisTable);
		Log.info(String.format("PDF Text Content : %s", actualReportString));
		Log.info(String.format("Expected Strings in PDF Text Content : %s",
				LogHelper.strListToString(expectedReportString)));

		Map<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.info("Ethane Analysis table verification failed");
				return false;
			}
		}

		if (actualReportString != null) {
			InputStream inputStream = new ByteArrayInputStream(actualReportString.getBytes());
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			try {
				ArrayList<String> reportEthaneList = new ArrayList<String>();
				while ((line = bufferReader.readLine()) != null) {
					if (!line.trim().matches(RegexUtility.INDICATION_TABLE_LINE_REGEX_PATTERN)) {
						List<String> matchingGroups = RegexUtility.getMatchingGroups(line.trim(),
								RegexUtility.ISOTOPIC_ANALYSIS_TABLE_LINE_REGEX_PATTERN);
						if (matchingGroups != null && matchingGroups.size() > 0) {
							line = matchingGroups.get(0);
							line = line.replaceAll(" +", " ").trim();
							reportEthaneList.add(line);
						}
					}
				}

				Log.info(String.format("ReportEthaneCapture ArrayList Values : %s",
						LogHelper.strListToString(reportEthaneList)));
				ArrayList<StoredProcComplianceGetEthaneCapture> storedProcEthaneList = StoredProcComplianceGetEthaneCapture
						.getReportEthaneCapture(reportId);
				Iterator<StoredProcComplianceGetEthaneCapture> lineIterator = storedProcEthaneList.iterator();
				ArrayList<String> storedProcConvStringList = new ArrayList<String>();
				while (lineIterator.hasNext()) {
					StoredProcComplianceGetEthaneCapture objStoredProc = lineIterator.next();
					String objAsString = objStoredProc.toString();
					storedProcConvStringList.add(objAsString.trim());
				}

				Log.info(
						String.format("Checking in ReportEthaneCapture ArrayList, StoredProcConvStringList Values : %s",
								LogHelper.strListToString(storedProcConvStringList)));
				if (!reportEthaneList.equals(storedProcConvStringList)) {
					Log.info(String.format(
							"EthaneCapture Analysis table verification failed, Expected: '%s', Actual: '%s'",
							storedProcConvStringList, reportEthaneList));
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
		Log.method("ReportsCommonPage.verifyIsotopicAnalysisTable", actualPath, reportTitle);
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, getReportPrefix() + "-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = getReportPrefix() + "-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_IsotopicAnalysisTable);

		Log.info(String.format("PDF Text Content : %s", actualReportString));
		Log.info(String.format("Expected Strings in PDF Text Content : %s",
				LogHelper.strListToString(expectedReportString)));

		Map<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.info("Isotopic Analysis table verification failed");
				return false;
			}
		}

		if (actualReportString != null) {
			List<String> reportIsotopicList = new SSRSIsotopicAnalysisTableParser().parse(actualReportString, null);
			Log.info(String.format("ReportIsotopic ArrayList Values : %s",
					LogHelper.strListToString(reportIsotopicList)));
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
		Log.method("ReportsCommonPage.verifySSRSPDFContainsText", actualPath, reportTitle,
				LogHelper.listToString(expectedReportString));
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, getReportPrefix() + "-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = getReportPrefix() + "-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		Map<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
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
	public boolean verifyIndicationTable(String actualPath, String reportTitle) throws IOException{
		Log.method("ReportsCommonPage.verifyIndicationTable", actualPath, reportTitle);
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, getReportPrefix() + "-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = getReportPrefix() + "-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_IndicationTable);
		Log.info(String.format("PDF Text Content : %s", actualReportString));
		Log.info(String.format("Expected Strings in PDF Text Content : %s",
				LogHelper.strListToString(expectedReportString)));

		Map<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
		for (Boolean value : actualFirstPage.values()) {
			if (!value) {
				Log.error("Indication table static text verification failed");
				return false;
			}
		}

		String matchStartString = "Disposition Confidence in Disposition";
		String matchEndString = "Software Version";
		List<String> indicationTables = RegexUtility.getStringsInBetween(actualReportString, matchStartString,
				matchEndString);
		String indicationTable = "";
		for (String table : indicationTables) {
			indicationTable += System.lineSeparator() + table;
		}

		Log.info(String.format("Extracted values between '%s' and '%s' are: %s", matchStartString, matchEndString,
				indicationTable));

		InputStream inputStream = new ByteArrayInputStream(indicationTable.getBytes());
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		ArrayList<String> reportIndicationsList = new ArrayList<String>();
		String extraLines = "";
		try {
			while ((line = bufferReader.readLine()) != null) {
				line = TextUtility.removeNonAsciiSpecialChars(line);
				Log.info(String.format("Matching line to check if it is table row. Line text=[%s]", line));
				if (line.trim().matches(RegexUtility.INDICATION_TABLE_LINE_REGEX_PATTERN)) {
					if (!line.trim().matches(RegexUtility.SSRS_PDF_PAGE_FOOTER_PATTERN)) {
						Log.info("Matched line as a table row!");
						ArrayUtility.appendToLastString(reportIndicationsList, extraLines.replaceAll(" ", ""));
						reportIndicationsList.add(line.replaceAll("\\?", "").trim().replace("+/-", "")
								.replace("0.0 ", "").trim().replaceAll(" ", "").replace(">=", ""));
						extraLines = "";
					}
				} else if (!reportIndicationsList.isEmpty()
						&& line.trim().matches(RegexUtility.FIELD_NOTE_LINE_REGEX_PATTERN)) {
					extraLines += line.trim();
				}
			}
		} finally {
			bufferReader.close();
		}
		ArrayUtility.appendToLastString(reportIndicationsList, extraLines.replaceAll(" ", ""));
		Log.info(String.format("ReportIndications ArrayList Values : %s",
				LogHelper.strListToString(reportIndicationsList)));

		ArrayList<StoredProcComplianceGetIndications> storedProcIndicationsList = StoredProcComplianceGetIndications.getReportIndications(reportId);
		
		Iterator<StoredProcComplianceGetIndications> lineIterator = storedProcIndicationsList.iterator();
		ArrayList<String> storedProcConvStringList = new ArrayList<String>();
		while (lineIterator.hasNext()) {
			StoredProcComplianceGetIndications objStoredProc = lineIterator.next();
			String objAsString = objStoredProc.toString();
			storedProcConvStringList
					.add(objAsString.replace("0.0 ", "0").replaceAll("\\s+", "").trim().replace("+/-", ""));
		}

		Log.info(String.format("Checking in ReportIndications ArrayList, StoredProcConvStringList Values : %s",
				LogHelper.strListToString(storedProcConvStringList)));
		if (!reportIndicationsList.equals(storedProcConvStringList)) {
			Log.error("Indication data table verification failed");
			return false;
		}

		List<String[]> lisasIndicationTblList = getSSRSPDFTableValues(PDFTable.LISAINDICATIONTABLE, reportTitle);
		LISAIndicationTableColumns tableColumn = LISAIndicationTableColumns.valueOf("LISANum");
		List<String> tableValuesList = ArrayUtility.getColumnStringList(lisasIndicationTblList, tableColumn.getIndex());
		if (!SortHelper.isNumberSortedASC(tableValuesList.toArray(new String[tableValuesList.size()]))) {
			Log.error("Lisa numbers present in indications table are not in sequential order");
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
		Log.method("ReportsCommonPage.verifyGapsTable", actualPath, reportTitle);
		PDFUtility pdfUtility = new PDFUtility();
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String actualReport = Paths.get(actualPath, getReportPrefix() + "-" + reportId.substring(0, 6) + ".pdf").toString();
		String reportName = getReportPrefix() + "-" + reportId;
		setReportName(reportName);
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		List<String> expectedReportString = new ArrayList<String>();
		expectedReportString.add(ComplianceReportSSRS_GapTable);
		Map<String, Boolean> actualFirstPage = matchSinglePattern(actualReportString, expectedReportString);
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
		Log.method("ReportsCommonPage.verifySSRSPDFFooter", actualPath, reportTitle, expectedSoftwareVersion,
				expectedReportAuthor);
		String reportPDFFilename = getReportPDFFileName(reportTitle, true /* includeExtension */);
		return verifyPDFFooter(actualPath, reportPDFFilename, expectedSoftwareVersion, expectedReportAuthor);
	}

	public boolean verifyPDFFooter(String actualPath, String pdfFilename, String expectedSoftwareVersion,
			String expectedReportAuthor) throws Exception {
		Log.method("ReportsCommonPage.verifyPDFFooter", actualPath, pdfFilename, expectedSoftwareVersion,
				expectedReportAuthor);
		String actualReport = Paths.get(actualPath, pdfFilename).toString();
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
					// Replace 'Software Version:' and 'Report Author:' by
					// seperator-'|'
					// Resulting string will be in this format: ->
					// <software_version>|<user>|<date>
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
			Log.info(String.format("Comparing item-%d, actual Date value-'%s' with today's date", idx,
					actualReportDate));
			if (!DateUtility.verifyDateMatchesToday(actualReportDate)) {
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
		Log.method("ReportsCommonPage.verifySSRSImages", actualPath, reportTitle, testCase);
		Report reportObj = Report.getReport(reportTitle);
		String reportId = reportObj.getId();
		String reportNameWithoutExt = getReportPrefix() + "-" + reportId.substring(0, 6);
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
				String pathToActualImage = Paths
						.get(testSetup.getDownloadPath(), testCase + "Page_" + pageCounter + ".png").toString();
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

	// NOTE (2016/05/18): As per test case steps we care more about checking the
	// order of views in SSRS PDF.
	// The step - "verify views are in correct sequence" are refering to
	// sequence of views in SSRS PDF,
	// which should be in the same order that the user has added the views from
	// New Compliance Report page.
	// (DE1973) tracks the issue of order in compliance viewer is NOT the same
	// as the input order by user.
	// Once (DE1973) is fixed we could utilize this method for checking correct
	// order in compliance viewer.
	public boolean verifyViewsInComplianceViewerAreInCorrectSequence(List<String> viewNamesList) {
		Log.method("ReportsCommonPage.verifyViewsInComplianceViewerAreInCorrectSequence",
				LogHelper.listToString(viewNamesList));
		List<WebElement> thumbnailImages = driver.findElements(By.xpath("//*[@id='ImageList']/li"));
		if (viewNamesList != null && viewNamesList.size() > 0) {
			Integer numViews = viewNamesList.size();
			Log.info(String.format("Found %d views in Compliance viewer.", thumbnailImages.size()));
			if (thumbnailImages != null && thumbnailImages.size() > 0) {
				Integer numThumbnails = thumbnailImages.size();
				// Loop from end. The thumbnails in the end are the view images.
				for (int i = numThumbnails; i >= 1 && numViews > 0; i--) {
					WebElement viewLabel = driver
							.findElement(By.xpath("//*[@id='ImageList']/li[" + String.valueOf(i) + "]/div/a/p"));
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

	public boolean verifyViewsInSSRSPDFAreInCorrectSequence(List<String> expectedViewNamesList, String reportTitle)
			throws IOException {
		Log.method("ReportsCommonPage.verifyViewsInSSRSPDFAreInCorrectSequence",
				LogHelper.listToString(expectedViewNamesList), reportTitle);
		Log.info(String.format("Expected views are: %s", LogHelper.strListToString(expectedViewNamesList)));

		List<String> actualViewNamesList = getViewNamesFromSSRSPdfViewTable(reportTitle);

		// Returned list has 2 columns. 0-th column is the view name.
		Log.info(String.format("Actual views found in SSRS PDF are: %s",
				LogHelper.strListToString(actualViewNamesList)));
		// Verify lists contain the same elements in the same order.
		return actualViewNamesList.equals(expectedViewNamesList);
	}

	public List<String> getViewNamesFromSSRSPdfViewTable(String reportTitle) throws IOException {
		Log.method("ReportsCommonPage.getViewNamesFromSSRSPdfViewTable", reportTitle);
		String pdfFilename = this.getReportPDFFileName(reportTitle, true /* includeExtension */);
		String pdfFilePath = Paths.get(TestContext.INSTANCE.getTestSetup().getDownloadPath(), pdfFilename).toString();

		PDFUtility pdfUtility = new PDFUtility();
		String actualReportString = pdfUtility.extractPDFText(pdfFilePath);
		SSRSViewNamesParser viewTableParser = new SSRSViewNamesParser();
		return viewTableParser.parse(actualReportString, ViewNamesParserAlgorithm.All);
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
		Log.method("ReportsCommonPage.verifyAllViewsImages", actualPath, reportTitle, testCase, numberOfViews);
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
	public boolean verifyViewsImages(String actualPath, String reportTitle, String testCase, String viewName)
			throws IOException {
		Log.method("ReportsCommonPage.verifyViewsImages", actualPath, reportTitle, testCase, viewName);
		return verifyViewsImages(actualPath, reportTitle, testCase, viewName, true);
	}

	public boolean verifyViewsImages(String actualPath, String reportTitle, String testCase, String viewName,
			boolean inZipFolder) throws IOException {
		Log.method("ReportsCommonPage.verifyViewsImages", actualPath, reportTitle, testCase, viewName, inZipFolder);
		PDFUtility pdfUtility = new PDFUtility();
		String reportName = getReportPDFFileName(reportTitle, false /* includeExtension */);
		String reportZipName = getReportPDFZipFileName(reportTitle, false /* includeExtension */);
		String actualReport = null;
		if (inZipFolder) {
			actualReport = Paths.get(actualPath, reportZipName + File.separator
					+ RegexUtility.replaceSpecialChars(reportTitle.replaceAll("\\s+", "")) + "_" + viewName + ".pdf")
					.toString();
		} else {
			actualReport = Paths.get(actualPath, reportName + "_" + viewName + ".pdf").toString();
		}
		setReportName(reportName);
		String imageExtractFolder = pdfUtility.extractPDFImages(actualReport, testCase);
		File folder = new File(imageExtractFolder);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile()) {
				BufferedImage image = ImageIO.read(file);
				int width = image.getWidth();
				int height = image.getHeight();
				Rectangle rect = new Rectangle(0, 0, width, height - 40);
				image = cropImage(image, rect);
				String actualViewPath = Paths
						.get(TestSetup.getSystemTempDirectory(), file.getName().replace(".pdf", "") + ".png")
						.toString();
				File outputfile = new File(actualViewPath);
				ImageIO.write(image, "png", outputfile);
				String baseViewFile = "";
				if (inZipFolder) {
					baseViewFile = Paths
							.get(TestSetup.getRootPath(), "\\selenium-wd\\data\\test-expected-data\\views-images")
							.toString() + File.separator + testCase + File.separator + "View"
							+ new NumberUtility().getOrdinalNumber(file.getName()) + ".png";
				} else {
					baseViewFile = Paths
							.get(TestSetup.getRootPath(), "\\selenium-wd\\data\\test-expected-data\\views-images")
							.toString() + File.separator + testCase + File.separator + viewName + ".png";
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
		Log.method("verifyShapeFilesWithBaselines", reportTitle, testCaseID, zipIndex);
		String shapeZipFileName = getReportShapeZipFileName(reportTitle, zipIndex, false /* includeExtension */);
		BaseHelper.deCompressZipFile(shapeZipFileName, testSetup.getDownloadPath());
		if (checkAndGenerateBaselineShapeFiles(TestContext.INSTANCE.getTestSetup().getDownloadPath() + shapeZipFileName,
				testCaseID)) {
			Log.info("Shape Files created as a baseline for '" + testCaseID + "', verification will be done on your next test run");
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

	public boolean verifyNumberOfLisasInShapeFiles(String actualDataFolderPath, String reportTitle, int zipIndex, int expectedLisasCount) throws Exception {
		Log.method("verifyNumberOfLisasInShapeFiles", reportTitle, zipIndex, expectedLisasCount);
		String shapeZipFileName = getReportShapeZipFileName(reportTitle, zipIndex, false /* includeExtension */);
		if (!FileUtility.directoryExists(actualDataFolderPath)) {
			BaseHelper.deCompressZipFile(shapeZipFileName, testSetup.getDownloadPath());
		}

		int actualNumLisas = 0;
		ShapeFileUtility shapeFileUtility = new ShapeFileUtility();
		List<String> shpFilesInDirectory = FileUtility.getFilesInDirectory(Paths.get(actualDataFolderPath), "*.shp");
		for (String filePath : shpFilesInDirectory) {
			String fileName = Paths.get(filePath).getFileName().toString();
			if (fileName.endsWith("-LISA.shp")) {
				actualNumLisas = shapeFileUtility.getNumberOfLisas(filePath);
			}
		}

		Log.info(String.format("Expected LISAs count=%d, Found LISAs in shape file=%d", expectedLisasCount, actualNumLisas));
		return (actualNumLisas == expectedLisasCount);
	}

	public void verifyDeleteModalMessageIsCorrect(String reportTitle) {
		Log.method("verifyDeleteModalMessageIsCorrect", reportTitle);

		WebElement modalReportName = driver.findElement(By.xpath(XPATH_DELETE_MODAL_REPORT_NAME));
		WebElement modalDeleteWarning = driver.findElement(By.xpath(XPATH_DELETE_MODAL_DELETE_WARNING));
		WebElement modalDeleteConfirmation = driver.findElement(By.xpath(XPATH_DELETE_MODAL_DELETE_CONFIRMATION));

		boolean reportTitleFound = modalReportName.getText().contains(reportTitle);
		boolean deleteWarningFound = modalDeleteWarning.getText().contains(Resources.getResource(ResourceKeys.Reports_DeleteReportWarning));
		boolean deleteConfirmationFound = modalDeleteConfirmation.getText().contains(Resources.getResource(ResourceKeys.Dialog_ProceedMessage));

		Log.info(String.format("reportTitleFound=[%b], deleteWarningFound=[%b], deleteConfirmationFound=[%b]",
				reportTitleFound, deleteWarningFound, deleteConfirmationFound));
	}

	public String removeReportId(String oriName) {
		return replaceReportIdWith(oriName, "");
	}

	public String replaceReportIdWith(String oriName, String replaceWith) {
		String CR_FilenamePattern = "(" + getReportPrefix() + "\\-)[A-Z0-9]{6}([\\.\\-])";
		Path currPath = Paths.get(oriName);
		String currFileName = currPath.getFileName().toString();
		String currFileDirectory = currPath.getParent().toString();
		String newFileName = currFileName.replaceAll(CR_FilenamePattern, "$1" + replaceWith + "$2");
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

	protected void waitForCustomBoundarySectionToShow() {
		WebElement dvAreaModeCustom = this.divCustomBoundarySection;
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return !dvAreaModeCustom.getAttribute("style").contains("display:none");
			}
		});
	}

	protected void waitForResubmitPopupToShow() {
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

	public void waitForLicenseMissingPopupToShow() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				(new WebDriverWait(driver, timeout + 15))
						.until(ExpectedConditions.presenceOfElementLocated(By.id("licenseMissingModal")));
				WebElement missingLicensePopupSection = d.findElement(By.id("licenseMissingModal"));
				return missingLicensePopupSection.getAttribute("style").contains("display:block")
						|| missingLicensePopupSection.getAttribute("style").contains("display: block");
			}
		});
	}

	public void waitForChangeModeWarningPopupToShow() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				(new WebDriverWait(driver, timeout + 15))
						.until(ExpectedConditions.presenceOfElementLocated(By.id("surveyModal")));
				WebElement changeModeWarningPopupSection = d.findElement(By.id("surveyModal"));
				return changeModeWarningPopupSection.getAttribute("style").contains("display:block")
						|| changeModeWarningPopupSection.getAttribute("style").contains("display: block");
			}
		});
	}
	
	public void waitForConfirmLicenseMissingPopupToClose() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				(new WebDriverWait(driver, timeout + 15))
						.until(ExpectedConditions.presenceOfElementLocated(By.id("licenseMissingModal")));
				WebElement confirmLicenseMissingPopupSection = d.findElement(By.id("licenseMissingModal"));
				return confirmLicenseMissingPopupSection.getAttribute("style").contains("display:none")
						|| confirmLicenseMissingPopupSection.getAttribute("style").contains("display: none");
			}
		});
	}
	
	protected void waitForCustomerBoundarySectionToShow() {
		WebElement dvAreaModeCustomer = this.divCustomerBoundarySection;
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return !dvAreaModeCustomer.getAttribute("style").contains("display:none")
						&& !dvAreaModeCustomer.getAttribute("style").contains("display: none");
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

	private String getZipFileNameWithIndex(String name, int zipIndex) {
		return zipIndex == 0 ? name : name + " (" + zipIndex + ")";
	}

	public void waitForResubmitButton() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return btnResubmitReport.isEnabled();
			}
		});
	}

	public void waitForCancelChangeReportModeButton() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return btnChangeModeCancel.isDisplayed();
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

	public WebElement getViewsAnalysesColumn() {
		return this.viewsAnalysesColumn;
	}

	public WebElement getViewsFieldNoteColumn() {
		return this.viewsFieldNoteColumn;
	}

	public WebElement getTubularAnalysisOption() {
		return this.tubularAnalysisOption;
	}

	public WebElement getFstRptTilNm() {
		return fstRptTilNm;
	}

	@Override
	public WebElement getTable() {
		try {
			refreshPageUntilElementFound(DATA_TABLE_XPATH);
			this.waitForPageLoad();
			driver.findElement(By.xpath(DATA_TABLE_XPATH));
		} catch (Exception e) {
			Log.error("Failed to find datatable: " + DATA_TABLE_XPATH);
		}
		return super.getTable();
	}

	public List<String> getSelectedSurveyTableValuesForColumn(ColumnHeaders column) {
		By tableContextBy = By.id("datatableSurveys_wrapper");
		WebElement tableContext = driver.findElement(tableContextBy);
		DataTablePage surveyTable = DataTablePage.getDataTablePage(driver, tableContext, this.testSetup,
				this.strBaseURL, this.strPageURL);
		return surveyTable.getRecords(column.getName(), -1 /* numRecords */);
	}

	public List<String[]> getSSRSPDFTableValues(PDFTable pdfTable, String reportTitle) throws IOException {
		String pdfFilename = this.getReportPDFFileName(reportTitle, true /* includeExtension */);
		String pdfFilePath = Paths.get(TestContext.INSTANCE.getTestSetup().getDownloadPath(), pdfFilename).toString();
		PDFTableUtility pdfTableUtility = new PDFTableUtility();
		List<String[]> pdfTableList = pdfTableUtility.extractPDFTable(pdfFilePath, pdfTable);
		Log.info("Checking if Array values returned has header...");
		if (ArrayUtility.listValuesHasHeader(pdfTableList)) {
			Log.info("Found header in returned array values. Skipping header...");
			Log.info(String.format("Extracted tables values from PDF (before skipping header) : %s",
					LogHelper.listOfArrayToString(pdfTableList)), LogCategory.SSRSPdfContent);
			Log.info("Checking if Array values returned has header...");
			pdfTableList = ArrayUtility.getListValuesSkipHeader(pdfTableList);
		}
		Log.info(String.format("Extracted tables values from PDF : %s", LogHelper.listOfArrayToString(pdfTableList)),
				LogCategory.SSRSPdfContent);
		return pdfTableList;
	}

	public String getSSRSPdfText(String reportTitle) throws IOException {
		String pdfFilename = this.getReportPDFFileName(reportTitle, true /* includeExtension */);
		String pdfFilePath = Paths.get(TestContext.INSTANCE.getTestSetup().getDownloadPath(), pdfFilename).toString();
		return new PDFUtility().extractPDFText(pdfFilePath);
	}

	protected boolean fillCustomerBoundary(ReportCommonEntity reportsEntity) throws Exception {
		return fillCustomerBoundary(reportsEntity.getCustomerBoundaryFilterType().toString(),
				reportsEntity.getCustomerBoundaryName());
	}

	public boolean fillCustomerBoundary(String customerBoundaryFilterType, String customerBoundaryName) throws Exception {
		// Try few times before failure
		boolean actionSuccess = RetryUtil.retryOnException(
				() -> { return fillCustomerBoundary(customerBoundaryFilterType, customerBoundaryName, null /*outBoundaryNames*/); },
				() -> { return true; },
				Constants.THOUSAND_MSEC_WAIT_BETWEEN_RETRIES,
				Constants.DEFAULT_MAX_RETRIES, true /*takeScreenshotOnFailure*/);

		if (!actionSuccess) {
			Log.error(String.format("fillCustomerBoundary() executed %d times and resulted in exception.", Constants.DEFAULT_MAX_RETRIES));
		}

		return actionSuccess;
	}

	public boolean fillCustomerBoundary(String customerBoundaryFilterType, String customerBoundaryName, List<String> boundaryNamesToVerify) {
		openCustomerBoundarySelector();
		boolean setSuccess = false;
		boolean invalidResults = false;
		try{
			latLongSelectionControl.waitForModalDialogOpen();
			latLongSelectionControl.switchMode(ControlMode.MapInteraction);
			latLongSelectionControl.waitForMapImageLoad();
			waitForAJAXCallsToComplete();
			focusOnPage(latLongSelectionControl.filterByTypeId);
			latLongSelectionControl.selectCustomerBoundaryType(customerBoundaryFilterType);

			if (boundaryNamesToVerify != null) {
				setSuccess = latLongSelectionControl.setVerifyCustomerBoundaryName(customerBoundaryName, boundaryNamesToVerify);
			} else {
				setSuccess = latLongSelectionControl.setVerifyCustomerBoundaryName(customerBoundaryName);
			}
			if (!setSuccess) {
				// If value not set, check if noResults entry has been found.
				Log.info("Invalid entry. Value NOT set. Verifying if no results entry has been found.");
				invalidResults = latLongSelectionControl.verifyNoBoundaryNameSearchResult();
				Log.info(String.format("No results entry FIND status=[%b]", invalidResults));
			}
		}catch(Exception e){
			Log.warn("Exception thrown when filling customer boundary: "+e);
		}finally{
			latLongSelectionControl.switchMode(ControlMode.Default);
			if (setSuccess) {
				latLongSelectionControl.clickOkButton();
			} else {
				latLongSelectionControl.clickCancelButton();
			}

			latLongSelectionControl.waitForModalDialogToClose();
		}
		return setSuccess || invalidResults;
	}

	public boolean noBoundarySearchResultByName(){
		By noResultBy = By.xpath("//ul[@id='ui-id-1']//div[text()='no results...']");
		boolean noResult = WebElementExtender.findElementBy(driver, noResultBy);
		return noResult;
	}

	public String getInputBoundarySelectedIdValue() {
		return inputBoundarySelectedId.getText();
	}

	public void setInputBoundarySelectedIdValue(String boundarySelectedId) {
		WebElementExtender.executeScript(this.inputBoundarySelectedId, driver, String.format("arguments[0].value = '%s';", boundarySelectedId));
	}

	public String getInputBoundarySelectedTextValue() {
		return inputBoundarySelectedText.getText();
	}

	public void setInputBoundarySelectedTextValue(String boundarySelectedText) {
		WebElementExtender.executeScript(this.inputBoundarySelectedText, driver, String.format("arguments[0].value = '%s';", boundarySelectedText));
	}

	protected boolean useCustomBoundaryLatLongSelector(ReportCommonEntity reportsEntity) {
		return reportsEntity.getLatLongXOffset() > 0 && reportsEntity.getLatLongYOffset() > 0
				&& reportsEntity.getLatLongRectWidth() > 0 && reportsEntity.getLatLongRectHeight() > 0;
	}

	protected boolean isCustomBoundarySpecified(ReportCommonEntity reportsEntity) {
		Log.method("isCustomBoundarySpecified", reportsEntity);
		boolean useSelector = false;
		if (reportsEntity != null) {
			boolean textFieldsSpecified = !BaseHelper.isNullOrEmptyOrZero(reportsEntity.getNELat())
					&& !BaseHelper.isNullOrEmptyOrZero(reportsEntity.getNELong())
					&& !BaseHelper.isNullOrEmptyOrZero(reportsEntity.getSWLat())
					&& !BaseHelper.isNullOrEmptyOrZero(reportsEntity.getSWLong());
			boolean latLongFieldsSpecified = useCustomBoundaryLatLongSelector(reportsEntity);
			useSelector = textFieldsSpecified || latLongFieldsSpecified;
		}
		return useSelector;
	}

	protected void fillCustomBoundaryUsingLatLongSelector(ReportCommonEntity reportsEntity) {
		openCustomBoundarySelector();
		latLongSelectionControl.waitForModalDialogOpen().switchMode(ControlMode.MapInteraction).waitForMapImageLoad()
				.drawSelectorRectangle(ReportCommonEntity.CANVAS_X_PATH, reportsEntity.getLatLongXOffset(),
						reportsEntity.getLatLongYOffset(), reportsEntity.getLatLongRectWidth(),
						reportsEntity.getLatLongRectHeight())
				.switchMode(ControlMode.Default).clickOkButton().waitForModalDialogToClose();
	}

	private Map<String, Boolean> getStringMatchResultMap(String reportTitle, List<String> expectedReportString) throws IOException {
		String actualPath = testSetup.getDownloadPath();
		String actualReport = actualPath + getReportPDFFileName(reportTitle, true /*includeExtension*/);
		PDFUtility pdfUtility = new PDFUtility();
		String actualReportString = pdfUtility.extractPDFText(actualReport);
		return matchSinglePattern(actualReportString, expectedReportString);
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

	public void selectSurveyModeForSurvey(SurveyModeFilter surveyModeFilter) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement radioBox = null;
		switch (surveyModeFilter) {
		case All:
			radioBox = this.inputSurModeFilterAll;
			break;
		case Standard:
			radioBox = this.inputSurModeFilterStd;
			break;
		case Operator:
			radioBox = this.inputSurModeFilterOperator;
			break;
		case RapidResponse:
			radioBox = this.inputSurModeFilterRapidResponse;
			break;
		case Manual:
			radioBox = this.inputSurModeFilterManual;
			break;
		case Analytics:
			radioBox = this.inputSurModeFilterAnalytics;
			break;
		default:
			break;
		}

		try {
			if (radioBox != null && !radioBox.isSelected()) {
				js.executeScript("arguments[0].click();", radioBox);
			}
		} catch (Exception e) {
			Log.error(e.toString());
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

	public boolean isReportColumnSorted(String columnName, String type) {
		Log.method("isReportColumnSorted");
		HashMap<String, TableColumnType> columnMap = new HashMap<String, TableColumnType>();
		columnMap.put(columnName, TableColumnType.getTableColumnType(type));
		return checkTableSort("datatable_wrapper", columnMap, pagination, getPaginationOption(),
				SurveyorConstants.NUM_RECORDS_TOBEVERIFIED);
	}

	/* Methods with implementation provided by derived class */

	public Supplier<List<String>> supplyViewsTableExpectedStaticText(List<Map<String, String>> viewsList) {
		return (() -> null);
	}

	public Supplier<List<String>> supplySSRSPDFExpectedStaticText(ReportCommonEntity reportsEntity) {
		return (() -> null);
	}


	/* Overridden methods */

	@Override
	public void addReportSpecificSurveys(String customer, String NELat, String NELong, String SWLat, String SWLong,
			List<Map<String, String>> views) {
		fillCustomBoundaryTextFields(NELat, NELong, SWLat, SWLong);
		addViews(customer, views);
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

	@Override
	public LoginPage logout() {
		// Check if report viewer is open. Close if open.
		FunctionUtil.warnOnError(() -> { if (isReportViewerDialogOpen()) {
			closeReportViewerDialog();
		}});

		return super.logout();
	}

	public boolean isSurveyModesValidForReportMode(String reportMode, List<String> distinctSurveyModes) {
		if(distinctSurveyModes==null){
			return false;
		}
		for(String surveyMode:distinctSurveyModes){
			if(!isSurveyModeValidForReportMode(reportMode, surveyMode)){
				Log.warn(String.format("SurveyMode '%s' is not valid for ReportMode '%s'", surveyMode, reportMode));
				return false;
			}
			Log.info(String.format("SurveyMode '%s' is valid for ReportMode '%s'", surveyMode, reportMode));
		}
		return true;
	}

	public String getDownloadPath(ReportFileType fileType, String reportTitle) throws Exception {
		String fileName = "";
		if (fileType == ReportFileType.PDF || fileType == ReportFileType.InvestigationPDF) {
			return TestContext.INSTANCE.getTestSetup().getDownloadPath();
		} else if (fileType == ReportFileType.ZIP) {
			fileName = getReportPDFZipFileName(reportTitle, false /*includeExtension*/);
		} else if (fileType == ReportFileType.MetaDataZIP) {
			fileName = getReportMetaZipFileName(reportTitle, false /*includeExtension*/);
		} else if (fileType == ReportFileType.ShapeZIP) {
			fileName = getReportShapeZipFileName(reportTitle, false /*includeExtension*/);
		}

		String downloadPath = Paths.get(TestContext.INSTANCE.getTestSetup().getDownloadPath(), fileName).toString();
		return downloadPath;
	}

	protected Map<String, Integer> getColumnIndexMap() {
		Map<String, Integer> columnIdxMap = new HashMap<String, Integer>();
		columnIdxMap.put(COL_HEADER_REPORT_TITLE, COL_IDX_REPORT_TITLE);
		columnIdxMap.put(COL_HEADER_REPORT_NAME, COL_IDX_REPORT_NAME);
		columnIdxMap.put(COL_HEADER_CREATED_BY, COL_IDX_CREATED_BY);
		columnIdxMap.put(COL_HEADER_DATE, COL_IDX_DATE);
		columnIdxMap.put(COL_HEADER_ACTION, COL_IDX_ACTION);
		columnIdxMap.put(COL_HEADER_UPLOAD_STATUS, COL_IDX_UPLOAD_STATUS);
		return columnIdxMap;
	}
}