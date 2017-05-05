package surveyor.unittest.source;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.source.BaseHelper;
import common.source.Constants;
import common.source.Downloader;
import common.source.FileUtility;
import common.source.Log;
import common.source.PDFUtility;
import common.source.PollManager;
import common.source.ProcessUtility;
import common.source.RegexUtility;
import common.source.TestSetup;
import surveyor.dataaccess.source.ConnectionFactory;
import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.ReportJob;
import surveyor.scommon.source.ReportsBasePage;

public class CompareReportsFromMultipleEnvTest {

	private static final String PDF_ZIP_FILE_DOWNLOAD_COMPLIANCE_URL = "Reports/DownloadPdf?reportId=%s&ReportType=Compliance";
	private static final String PDF_ZIP_FILE_DOWNLOAD_ASSESSMENT_URL = "Reports/DownloadPdf?reportId=%s&ReportType=Assessment";
	private static final String PDF_ZIP_FILE_DOWNLOAD_EQ_URL = "Reports/DownloadPdf?reportId=%s&ReportType=EQ";
	private static final String DOWNLOAD_FILE_CMD = "DownloadFile.cmd";

	public static final String ENV1_DB_IP_ADDRESS = "20.20.64.100";
	public static final String ENV1_DB_PORT_NO = "1433";
	public static final String ENV1_DB_NAME = "SurveyorDevelopment_blankDB_20170504";
	public static final String ENV1_DB_USER = "awssa";
	public static final String ENV1_DB_PASSWORD = "j!RuL1Gd7A";
	public static final String ENV1_LOGIN_USER = "AutomationAdmin";
	public static final String ENV1_LOGIN_PWD = "sqa#Picarro$0";
	public static final String ENV1_BASE_URL = "https://p3dev.picarro.com";

	public static final String ENV2_DB_IP_ADDRESS = "20.20.130.210";
	public static final String ENV2_DB_PORT_NO = "1433";
	public static final String ENV2_DB_NAME = "SurveyorSQAAuto_blankDB_20170420";
	public static final String ENV2_DB_USER = "awssa";
	public static final String ENV2_DB_PASSWORD = "3Vf763pSg2";
	public static final String ENV2_LOGIN_USER = "AutomationAdmin";
	public static final String ENV2_LOGIN_PWD = "sqa#Picarro$0";
	public static final String ENV2_BASE_URL = "https://p3sqaauto.picarro.com";

	public static final String COMPLIANCE_REPORT_TYPE_ID = "00000000-0000-0000-0001-000000000000";
	public static final String ASSESSMENT_REPORT_TYPE_ID = "00000000-0000-0000-0006-000000000000";
	public static final String EQ_REPORT_TYPE_ID = "00000000-0000-0000-0005-000000000000";

	public static final String MAP_REPORT_JOB_TYPE_ID = "00000000-0000-0000-0001-000000000000";
	public static final String EQMAP_REPORT_JOB_TYPE_ID = "00000000-0000-0000-0004-000000000000";

	private static TestSetup testSetup = null;
	private static ReportsBasePage reportsBasePage = null;
	private static PDFUtility pdfUtility = null;

	private static Connection env1Connection;
	private static Connection env2Connection;
	private static Environment environment1;
	private static Environment environment2;

	private static Connection createConnection(Environment environment) {
		String dbIpAddress = environment.getDbIPAddress();
		String dbPortNo = environment.getDbPortNo();
		String dbName = environment.getDbName();
		String dbUser = environment.getDbUser();
		String dbPassword = environment.getDbPassword();
		String connectionUrl = String.format(ConnectionFactory.JDBC_SQLSERVER_CONNECTION_STRING,
				dbIpAddress, dbPortNo, dbName);
		return ConnectionFactory.createNewConnection(dbUser, dbPassword, connectionUrl);
	}

	private static void initialize() {
		environment1 = new Environment(ENV1_DB_IP_ADDRESS, ENV1_DB_PORT_NO, ENV1_DB_NAME, ENV1_DB_USER, ENV1_DB_PASSWORD, ENV1_LOGIN_USER, ENV1_LOGIN_PWD, ENV1_BASE_URL);
		environment2 = new Environment(ENV2_DB_IP_ADDRESS, ENV2_DB_PORT_NO, ENV2_DB_NAME, ENV2_DB_USER, ENV2_DB_PASSWORD, ENV2_LOGIN_USER, ENV2_LOGIN_PWD, ENV2_BASE_URL);
		env1Connection = createConnection(environment1);
		env2Connection = createConnection(environment2);
	}

	private void downloadExtractPDFImages(Environment environment, Set<ReportInfo> reportInfosEnv, String reportPrefix) {
		String envShortName = RegexUtility.split(environment.getBaseUrl(), RegexUtility.DOT_SPLIT_REGEX_PATTERN).get(0).replace("https://", "").toUpperCase();
		reportInfosEnv.forEach(ri -> {
			try {
				String downloadFileRelativeUrl = String.format(PDF_ZIP_FILE_DOWNLOAD_COMPLIANCE_URL, ri.getReportId());
				if (reportPrefix.equals("AS")) {
					downloadFileRelativeUrl = String.format(PDF_ZIP_FILE_DOWNLOAD_ASSESSMENT_URL, ri.getReportId());
				} else if (reportPrefix.equals("EQ")) {
					downloadFileRelativeUrl = String.format(PDF_ZIP_FILE_DOWNLOAD_EQ_URL, ri.getReportId());
				}
				String outputFileName = getReportPDFZipFileName(environment, reportPrefix, ri.getReportTitle(), ri.getReportId(), 0, true /* includeExtension */);
				String outputFileFullPath = Paths.get(testSetup.getDownloadPath(), outputFileName).toString();
				downloadFile(environment, downloadFileRelativeUrl, outputFileFullPath);
				waitForFileDownloadToComplete(outputFileName, testSetup.getDownloadPath());
				BaseHelper.deCompressZipFile(outputFileName.replace(".zip", ""), testSetup.getDownloadPath());
				List<String> filesInDir = FileUtility.getFilesInDirectory(Paths.get(testSetup.getDownloadPath(), outputFileName.replace(".zip", "")), true /*includeFullPath*/);
				String testCaseId = ri.getReportTitle().substring(0, (ri.getReportTitle().indexOf('-')>0) ? ri.getReportTitle().indexOf('-') : ri.getReportTitle().length());
				for (int i = 0; i < filesInDir.size(); i++) {
					String fileFullPath = filesInDir.get(i);
					String outputFolder = pdfUtility.extractPDFImages(fileFullPath, String.format("%s_%s-%d", envShortName, testCaseId, i));
					Log.info(String.format("Extracted to [%s] images from [%s] ", outputFolder, fileFullPath));
					// Move files back to base folder.
					List<String> outputFiles = FileUtility.getFilesInDirectory(Paths.get(outputFolder), true /*includeFullPath*/);
					outputFiles.forEach(f -> {
						try {
							Path path = Paths.get(f);
							Path inFolder = Paths.get(testSetup.getDownloadPath(), outputFileName.replace(".zip", ""));
							FileUtility.copyFile(f, Paths.get(inFolder.toString(), "images", path.getFileName().toString()).toString());
						} catch (Exception e) {
							e.printStackTrace();
						}
					});

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public static void downloadFile(Environment environment, String downloadFileRelativeUrl, String outputFileFullPath) {
		try {
			String workingFolder = TestSetup.getRootPath();
			String downloadFileCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib";
			String downloadFileCmdFullPath = downloadFileCmdFolder + File.separator + DOWNLOAD_FILE_CMD;
			String command = "cd \"" + downloadFileCmdFolder + "\" && " + downloadFileCmdFullPath +
					String.format(" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\"",
							workingFolder, environment.getBaseUrl(), downloadFileRelativeUrl, outputFileFullPath,
							environment.getLoginUser(),
							environment.getLoginPwd());
			Log.info("Downloading File. Command -> " + command);
			ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ true);
		} catch (IOException e) {
			Log.error(e.toString());
		}
	}
	private String getReportName(String reportId) {
		return reportId.substring(0, 10);
	}

	private void waitForFileDownloadToComplete(String fileName, String downloadPath) {
		PollManager.poll(() -> (!reportsBasePage.checkFileExists(fileName, downloadPath)),
				Constants.DEFAULT_WAIT_BETWEEN_POLL_IN_MSEC, Constants.DEFAULT_MAX_RETRIES_IN_POLL);
	}

	private String getReportPDFZipFileName(Environment environment, String reportFilePrefix, String reportTitle, String reportId, int zipIndex, boolean includeExtension) {
		String envShortName = RegexUtility.split(environment.getBaseUrl(), RegexUtility.DOT_SPLIT_REGEX_PATTERN).get(0).replace("https://", "").toUpperCase();
		String reportName = envShortName + "_" + reportFilePrefix + "-" + getReportName(reportTitle) + "-PDF";
		reportName = getZipFileNameWithIndex(reportName, zipIndex);
		if (includeExtension) {
			reportName += ".zip";
		}
		return reportName;
	}

	private String getZipFileNameWithIndex(String name, int zipIndex) {
		return zipIndex == 0 ? name : name + " (" + zipIndex + ")";
	}

	private static class ReportInfo {
		private String reportTitle;
		private String reportId;

		public String getReportTitle() { return reportTitle; }
		public void setReportTitle(String reportTitle) { this.reportTitle = reportTitle; }

		public String getReportId() { return reportId; }
		public void setReportId(String reportId) { this.reportId = reportId; }

		public ReportInfo(String rptId, String rptTitle) {
			this.setReportId(rptId);
			this.setReportTitle(rptTitle);
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
		}
	}

	private static class Environment {
		private String dbIPAddress;
		private String dbPortNo;
		private String dbName;
		private String dbUser;
		private String dbPassword;
		private String loginUser;
		private String loginPwd;
		private String baseUrl;

		public String getDbIPAddress() { return dbIPAddress; }
		public void setDbIPAddress(String dbIPAddress) { this.dbIPAddress = dbIPAddress; }

		public String getDbPortNo() { return dbPortNo;}
		public void setDbPortNo(String dbPortNo) { this.dbPortNo = dbPortNo;}

		public String getDbName() { return dbName; }
		public void setDbName(String dbName) { this.dbName = dbName; }

		public String getDbUser() { return dbUser; }
		public void setDbUser(String dbUser) { this.dbUser = dbUser; }

		public String getDbPassword() { return dbPassword; }
		public void setDbPassword(String dbPassword) { this.dbPassword = dbPassword; }

		public String getLoginUser() { return loginUser; }
		public void setLoginUser(String loginUser) { this.loginUser = loginUser; }

		public String getLoginPwd() { return loginPwd; }
		public void setLoginPwd(String loginPwd) { this.loginPwd = loginPwd; }

		public String getBaseUrl() { return baseUrl; }
		public void setBaseUrl(String baseUrl) { this.baseUrl = baseUrl; }

		public Environment(String ipAddress, String portNo, String dbName, String dbUser, String dbPassword, String loginUser, String loginPwd, String baseUrl) {
			this.setBaseUrl(baseUrl);
			this.setDbIPAddress(ipAddress);
			this.setDbName(dbName);
			this.setDbPassword(dbPassword);
			this.setDbPortNo(portNo);
			this.setDbUser(dbUser);
			this.setLoginPwd(loginPwd);
			this.setLoginUser(loginUser);
		}
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		initialize();

		reportsBasePage = new ReportsBasePage(null, null, testSetup, null);
		pdfUtility = new PDFUtility();

		// Initialize TestSetup to instantiate the TestContext.
		testSetup = new TestSetup(false /* skip initialization */);
		String rootPath;
		try {
			rootPath = TestSetup.getRootPath();
			testSetup.loadTestProperties(rootPath);
		} catch (IOException e) {
			Log.error(e.getMessage());
		}
		testSetup.initialize();
		//environment.setTestSetup(testSetup);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCompareComplianceReportTests() {
		downloadExtractReportFiles(COMPLIANCE_REPORT_TYPE_ID, "CR");
	}

	@Test
	public void testCompareAssessmentReportTests() {
		downloadExtractReportFiles(ASSESSMENT_REPORT_TYPE_ID, "AS");
	}

	@Test
	public void testCompareEQReportTests() {
		downloadExtractReportFiles(EQ_REPORT_TYPE_ID, "EQ");
	}

	private void downloadExtractReportFiles(String reportTypeId, String reportPrefix) {
		// ENV1 - Find all reports with map job.
		// Parse TCID
		// Find corresponding reportTitle from ENV2

		// ENV1 -> Reports with Map job.
		Set<Object> reportIDsEnv1 = new ReportJob(env1Connection).getAll().stream()
			.filter(r -> r.getReportJobTypeId().equals(MAP_REPORT_JOB_TYPE_ID))
			.map(r -> r.getReportId())
			.collect(Collectors.toSet());

		Set<ReportInfo> reportInfosEnv1 = new Report(env1Connection).getAll().stream()
			.filter(r -> r.getReportTypeId().equals(reportTypeId))
			.filter(r -> reportIDsEnv1.contains(r.getId()))
			.map(r -> new ReportInfo(r.getId(), r.getReportTitle()))
			.collect(Collectors.toSet());

		// ENV2 -> Reports corresponding to ENV1 reports.
		Set<ReportInfo> reportInfosEnv2 = reportInfosEnv1.stream()
			.map(r -> r.getReportTitle().substring(0, (r.getReportTitle().indexOf('-')>0) ? r.getReportTitle().indexOf('-') : r.getReportTitle().length()))
			.map(rptTitle -> {Report rpt = new Report(env2Connection).getTitleLike(rptTitle); if (rpt != null) { return new ReportInfo(rpt.getId(), rpt.getReportTitle());} return null; })
			.collect(Collectors.toSet());

		// ENV1 Report ->
		//  Invoke ZIP File download
		//  Find number of views
		//  Invoke View download (for each view)
		//  Results saved to OutFolder1
		downloadExtractPDFImages(environment1, reportInfosEnv1, reportPrefix);

		// [REPEAT ENV2]
		// ENV2 Report ->
		//  Invoke ZIP File download
		//  Find number of views
		//  Invoke View download (for each view)
		//  Results saved to OutFolder1
		downloadExtractPDFImages(environment2, reportInfosEnv2, reportPrefix);
	}

}